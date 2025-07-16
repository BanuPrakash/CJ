package com.cisco.orderapp.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Transactional like
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Tx {
}
