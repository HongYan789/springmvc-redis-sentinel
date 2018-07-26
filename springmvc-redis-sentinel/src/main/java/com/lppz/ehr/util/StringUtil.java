package com.lppz.ehr.util;

public class StringUtil {

	
	public static boolean isEmpty(String text) {
		
		if(text == null || text.equals("")) {
			return true;
		}
		return false;
	}	
	
	public static boolean isNotEmpty(Object text) {
		
		if(text == null || text.equals("")) {
			return false;
		}
		return true;
	}
	
	public static Object isEmptyStr(Object text) {
		
		if(text == null || text.equals("")) {
			return "";
		}
		return text;
	}
	
}
