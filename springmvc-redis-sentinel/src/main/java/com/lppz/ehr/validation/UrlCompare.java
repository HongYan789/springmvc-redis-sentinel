package com.lppz.ehr.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlCompare {
	public String url() default "";
	public String param() default "";
	public String response() default "";
	public String body() default "{}";
	public String bodyParams() default "type,days";

}
