package com.lppz.ehr.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberCompare {
	//对比
	public String eq();
	//数据类型
	public String type() default "int";
	//最小值
	public long min() default 0;
	//最大值
	public long max() default 0;
	//小于
	public long gt() default 0;
	//大于
	public long lt() default 0;
	//添加例外
	public String exceptionKey() default "str1,str2";
	public String exceptionValue() default "str1,str2";

}
