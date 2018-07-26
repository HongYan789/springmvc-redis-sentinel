package com.lppz.ehr.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JsonTop {
	public String path() default "I_INPUT.business_data";
	public String type() default "string/array/object";
}
