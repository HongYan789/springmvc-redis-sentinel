package com.lppz.ehr.util;

/**
 * 系统配置，一经定义只能添加，不能修改！
 */
public class Constant {
	
	public static class OpCode {
		public static final short SUCCESS = 200;
		public static final short INVALID_PARAMS = 400;
		public static final short NEED_AUTHORIZATION = 401;
		public static final short NOT_PERMITED = 403;
		public static final short RESOURCE_NOT_FOUND = 404;
		public static final short INTERNAL_EXCEPTION = 500;
		public static final String METHOD="method";
		public static final String DATA="data";
		public static final String CODE="code";
		public static final String PARAM="PARAM";
		public static final String VALUE="VALUE";
		public static final String ITEM="item";
		public static final String I_KEYNA="I_KEYNA";
		public static final String BODY="soap-env:Body";
		public static final String RESPONSE="n0:Z_02HR_IF_OAResponse";
		public static final String OUT_PUT="ET_OUTPUT";
		public static final String BEAN_PACKAGE="com.lppz.ehr.rule.";
		public static final String RULE="Rule";
		public static final String STRING="string";
		public static final String OBJECT="object";
		public static final String INT="int";
		public static final String DOUBLE="double";
		public static final String FILING="processFiling";
		
	}
	
	public static class RedisNameSpace {
		public static final String NAMESPACE = "ehr-api";
		
		public static final String LOGIN = NAMESPACE+"_login_user_id_";
		public static final String DEPT = NAMESPACE+"_dept_id_";
		//登陆URL
		public static final String LOGIN_URL = "http://www.xxx.com/login.html";
		
		//登陆有效期
		public static final int LOGIN_TIME = 60 * 30;
	}
	
	public static class SapCode {
		public static final String USER_CODE = "HR201";
		public static final String ORGANIZATION_CODE = "HR101";
		public static final String USER_CODE_RESPONSE = "HR214";
		public static final String ORGANIZATION_CODE_RESPONSE = "HR114";
		//保留状态/默认值
		public static final int USER_STATUS_DEFAULT = 0;
		public static final int USER_STATUS_SELECT = 1;
		public static final int USER_STATUS_CREATE = 2;
		public static final int USER_STATUS_INSERT = 2;
		public static final int USER_STATUS_UPDATE = 3;
		public static final int USER_STATUS_DELETE = 4;
		//在职
		public static final int USER_STATUS_JOB = 3;
		//离职
		public static final int USER_STATUS_LEAVE = 0;
		//默认扫描状态（需扫描为0）
		public static final int STATUS_DEFAULT = 0;
		public static final int ORG_STATUS_INSERT = 10;
		public static final int ORG_STATUS_UPDATE = 20;
		public static final int ORG_STATUS_DELETE = 30;
		//处理成功的数据（不扫描状态）
		public static final int STATUS_SUCCESS_JOB = 99;
		//离职成功的数据
		public static final int USER_STATUS_LEAVE_JOB = 100;
		//一级部门编号
		public static final String USER_DEPT_ID = "80000000";
		//父ID
		public static final String ORG_PARENT_ID = "0";
		public static final String ORG_P_ID = "00000000";
		//部门id
		public static final String ORG_DEPT_ID = "10000000";
		
		//手机号码不匹配的设置为88状态
		public static final int USER_MOBILE_EQUALS = 88;
		//手机号码不存在的用户设置为89状态
		public static final int USER_MOBILE_NOT_EXIST = 89;
		
		//dingding-api调用dingding超时
		public static final int NO_RESULT = 999;



		//由于sap侧跟钉钉侧不同步造成离职失败的状态码
		public static final int USER_STATUS_LEAVE_JOB_FAIL=98;

		//找不到该用户的不扫描状态以及手机号码和登陆钉钉的手机号码不一致的不扫描状态
		public static final int USER_STATUS_NO_USER=97;
		
	}
	
	public static class DingTalkCode {
		public static final String SELECT = "get";
		public static final String CREATE = "create";
		public static final String UPDATE = "update";
		public static final String DELETE = "delete";
		public static final String DELETEALL = "deleteall";
		public static final short SUCCESS = 0;
		//进行钉钉删除操作时由于没有钉钉id的返回码
		public static final int NO_DINGID=41009;
		//sap用户查询部门信息时对应部门不存在则不请求钉钉，因为钉钉会返回该错误码
		public static final int NO_DEPTID=40009;
		//钉钉返回错误码60121,找不到该用户
		public static final int NO_USERID=60121;
        //由于全量同步的原因，后期出现dingdingid跟job_no不匹配的状态
		//企业中的手机号码和登陆钉钉的手机号码不一致,暂时不支持修改用户信息,可以删除后重新添加
		public static final int NO_MATCH_DINGID=40022;
        //sap用户所对应的部门在部门表中未成功绑定钉钉id
		public static final int NO_DEPT_ID=60003;
		//钉钉返回提示：手机号码在公司中已存在，处理办法：将返回的dingdingid设置到数据库
		public static final int EXIST_MOBILE=60104;

	}
	
	public static  class FtpUpload{
		public static final int MAXLENGTH = 10 * 1048576;// 最大10M
	}
}























