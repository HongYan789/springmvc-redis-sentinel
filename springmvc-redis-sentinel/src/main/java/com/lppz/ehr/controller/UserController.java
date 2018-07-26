package com.lppz.ehr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lppz.ehr.bean.DingUser;
import com.lppz.ehr.exception.InternalException;
import com.lppz.ehr.exception.InvalidArgumentException;
import com.lppz.ehr.service.UserService;
import com.lppz.ehr.util.Config;
import com.lppz.ehr.util.HttpTinyClient;
import com.lppz.ehr.util.JedisUtil;
import com.lppz.ehr.validation.NeedLogin;
import com.lppz.ehr.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value="/auths")
public class UserController {

	@Autowired
	private Validation validation;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JedisUtil jedisUtil;

	@Autowired
	private Config config;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/userinfos", method=RequestMethod.GET)
    public Object userinfos(@RequestParam("dingCode") String dingCode){
		logger.info("接收来自userinfos接口的数据："+dingCode);
		Map<String,Object> result = new HashMap<String, Object>();

		if(dingCode == null || dingCode.length() != 32) {
			throw new InvalidArgumentException("dingCode不能为空且长度需要在32位");
		}

		List<Object> params = new ArrayList<Object>();
		params.add("dingCode");
		params.add(dingCode);

		List<String> headers = new ArrayList<String>();
		headers.add("code");
		headers.add(config.dingdingCode);

		try {
			HttpTinyClient.HttpResult httpResult = HttpTinyClient.httpGet(config.dingdingApi+"/auths/userinfos", headers, params, 3000);
			JSONObject object = JSON.parseObject(httpResult.content);
			JSONObject data = object.getJSONObject("data");
			result.put("data", data);
		} catch (IOException e) {
			throw new InternalException("请求异常"+ e.getMessage());
		}
		logger.info("返回userinfos接口的数据："+result);
		return result;
    }
	
	
	@RequestMapping(value="/configs", method=RequestMethod.GET)
    public Object config(@RequestParam("url") String url){
		logger.info("接收来自configs接口的数据："+url);
		Map<String,Object> result = new HashMap<String, Object>();

		if(url == null) {
			throw new InvalidArgumentException("url不能为空");
		}

		DingUser dingUser = new DingUser();
		List<Object> params = new ArrayList<Object>();
		params.add("url");
		params.add(url);

		List<String> headers = new ArrayList<String>();
		headers.add("code");
		headers.add(config.dingdingCode);

		try {
			HttpTinyClient.HttpResult httpResult = HttpTinyClient.httpGet(config.dingdingApi+"/auths/configs", headers, params, 3000);
			JSONObject object = JSON.parseObject(httpResult.content);
			JSONObject data = object.getJSONObject("data");
			result.put("data", data);
		} catch (IOException e) {
			throw new InternalException("请求异常"+ e.getMessage());
		}
		logger.info("返回configs接口的数据："+result);
		return result;
	}
	
	@NeedLogin
	@RequestMapping(value="/users", method=RequestMethod.PUT)
    public Object put(DingUser loginUser, @RequestBody String body){
		Map<String,Object> result = new HashMap<String, Object>();
		DingUser dingUser = validation.getObject(body, DingUser.class, new String[]{"dingCode"});

		//fix service


		return result;
	}

	@NeedLogin
	@RequestMapping(value="/tests", method=RequestMethod.GET)
    public Object get(DingUser loginUser, @RequestParam(name="nickName") String nickName){
		
        Map<String, Object> result=new HashMap<String, Object>();
        result.put("nickName", nickName);
        result.put("dingUser", loginUser);

        return result;
    }

}
