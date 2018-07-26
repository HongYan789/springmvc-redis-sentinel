package com.lppz.ehr.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lppz.ehr.bean.DingUser;
import com.lppz.ehr.bean.Log;
import com.lppz.ehr.bean.User;
import com.lppz.ehr.exception.ExceptionHandlerMap;
import com.lppz.ehr.exception.NeedAuthorizationException;
import com.lppz.ehr.mapperlog.LogMapper;
import com.lppz.ehr.util.*;
import com.lppz.ehr.validation.NeedLogin;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;


//@Component
@Aspect
public class IndexAspect {

	@Autowired
	private ExceptionHandlerMap exceptionHandlerMap;
	
	@Autowired
	private JedisUtil jedisUtil;

    @Autowired
    private LogMapper logMapper;
	
	private Logger logger = LoggerFactory.getLogger(IndexAspect.class);

	//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution(* com.lppz.ehr.controller..*(..))")
	public void aspect(){
	}
	
	public IndexAspect(){
		logger.info("aspect init...");
	}
	
	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@SuppressWarnings("unchecked")
	@Around("aspect()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object response = null;
        
        Object[] args = pjp.getArgs();
        Object[] argsModified = new Object[args.length];

        MethodSignature signature = (MethodSignature) pjp.getSignature(); 
        Method method = signature.getMethod();
        Class<?> clazz = method.getDeclaringClass();

        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        String pathString = "";
        String methodString = "";
        if (requestMapping != null) {
            String[] paths = requestMapping.value();
            for (String path: paths) {
                pathString += path + ",";
            }
            if ("".equals(pathString)) {
                pathString = "unknowPath";
            } else {
                pathString = pathString.substring(0, pathString.length() - 1);
            }

            RequestMethod[] httpMethods = requestMapping.method();

            for (RequestMethod httpMethod: httpMethods) {
                methodString += httpMethod.name() + ",";
            }

            if ("".equals(methodString)) {
                methodString = "unknowMethod";
            } else {
                methodString = methodString.substring(0, methodString.length() - 1);
            }
        }

        //需要校验权限

        StringBuilder urlContent = new StringBuilder();

        String sourceIp = "";
        String url = "";
        DingUser loginInfo = null;
        short code = Constant.OpCode.SUCCESS;
        try {
            ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = sra.getRequest();
            sourceIp = NetworkUtil.getIpAddress(request);
            urlContent.append("ip: " + sourceIp);
            url = request.getRequestURL().toString();
            urlContent.append(" , url: " + url);
            List<Object> list = new ArrayList<Object>();
            if (clazz.isAnnotationPresent(NeedLogin.class) || method.isAnnotationPresent(NeedLogin.class)) {
            int i = 0;
            for (Object o: args) {
                if (o instanceof User) {
                    argsModified[i++] = loginInfo;
                } else {
                    argsModified[i++] =  o;
                }
            }
            //参数从header转变为?ticket来实现
            String ticket = request.getParameter("ticket");
            if (ticket != null && !"".equals(ticket)) {
                try {
                    loginInfo = JSON.parseObject(jedisUtil.get(ticket), DingUser.class);
                    urlContent.append(", jobNumber: " + loginInfo.getJobnumber() + ", userName: " + loginInfo.getName());
                } catch(Exception e) {
                    throw new NeedAuthorizationException(Constant.RedisNameSpace.LOGIN_URL);
                }
            }

            if (loginInfo == null) {
            	 throw new NeedAuthorizationException("ticket过期，请重新登录");
            }else {
            	//刷新时间
            	jedisUtil.set(MD5Util.string2MD5(Constant.RedisNameSpace.LOGIN + loginInfo.getUserid()), JSON.toJSONString(loginInfo), Constant.RedisNameSpace.LOGIN_TIME);
            }


        } else {
        	//by zhangyan 2018-05-21 添加非@NeedLogin注解情况下的request信息输出
            argsModified = args;
        }

			response = pjp.proceed(argsModified);
            urlContent.append(", response: " + JSON.toJSONString(response));
			Map<String, Object> castResponse;
			if (response == null) {
				response = new HashMap<String, Object>();
			}

			if (response instanceof Map) {
				castResponse = (Map<String, Object>) response;
				castResponse
						.put("cost",
								(double) (System.currentTimeMillis() - startTime) / 1000);
				castResponse.put("code", Constant.OpCode.SUCCESS);
			}
		} catch (Exception e) {
			Method methodE = exceptionHandlerMap.getMethod(e.getClass());
			if (methodE != null) {
				response = methodE.invoke(
						exceptionHandlerMap.getExceptionHandler(methodE), e);
                String responseStr = JSON.toJSONString(response);
                urlContent.append(", response: " + responseStr);
                code = JSONObject.parseObject(responseStr).getShort("code");
				return response;
			}
		}finally {
            logger.info("request. " + urlContent.toString());
            Log log = new Log();
            log.setIp(sourceIp);
            log.setUrl(url);
            log.setCode(code);
            log.setMethod(methodString);
            log.setParams(Arrays.asList(argsModified).toString());
            log.setRequest(pathString);
            log.setResponse(JSON.toJSONString(response));
            log.setOperator(StringUtil.isEmptyStr(loginInfo).toString());
            logMapper.insert(log);
            logger.info("insert log :{} ",log.toString());
        }


        return response;
	}
	
}
