package com.lppz.ehr.exception;

import com.lppz.ehr.util.CommonUtil;
import com.lppz.ehr.util.Constant.OpCode;
import com.lppz.ehr.util.Lang;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultExceptionHandlers {
	
	private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandlers.class);
	
	@Autowired
	private Lang lang;
	
	@ExceptionHandler({
		InvalidArgumentException.class,
		com.lppz.ehr.exception.InvalidHttpArgumentException.class})
	public com.lppz.ehr.exception.ExceptionResponse handleII(Exception e) {
		return new com.lppz.ehr.exception.ExceptionResponse(OpCode.INVALID_PARAMS, lang.getLang(e.getMessage() == null ? e.getClass().getName() : e.getMessage()));
    }
	
	@ExceptionHandler(ResourceNotExistException.class)
	public com.lppz.ehr.exception.ExceptionResponse handleRNFE(Exception e) {
		return new com.lppz.ehr.exception.ExceptionResponse(OpCode.RESOURCE_NOT_FOUND, lang.getLang(e.getMessage() == null ? e.getClass().getName() : e.getMessage()));
    }
	
	@ExceptionHandler(OperationNotAllowedException.class)
	public com.lppz.ehr.exception.ExceptionResponse handleONAE(Exception e) {
		return new com.lppz.ehr.exception.ExceptionResponse(OpCode.NOT_PERMITED, lang.getLang(e.getMessage() == null ? e.getClass().getName() : e.getMessage()));
    }
	
	@ExceptionHandler(NeedAuthorizationException.class)
	public Map<String, Object> handleNAE(com.lppz.ehr.exception.NeedAuthorizationException e) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("code", OpCode.NEED_AUTHORIZATION);
		response.put("desc", lang.getLang("NEED_LOGIN"));
		response.put("loginUrl", e.getLoginUrl());
		return response;
    }
	
	@ExceptionHandler(Exception.class)
	public com.lppz.ehr.exception.ExceptionResponse handleE(Exception e) {
		log.error("operation failed, " + "exception:\n" + CommonUtil.getStackTrace(e));
		return new com.lppz.ehr.exception.ExceptionResponse(OpCode.INTERNAL_EXCEPTION, lang.getLang("INTERNAL_EXCEPTION")
		        + ":" + lang.getLang(e.getMessage() == null ? e.getClass().getName() : e.getMessage()));
    }
	
}











