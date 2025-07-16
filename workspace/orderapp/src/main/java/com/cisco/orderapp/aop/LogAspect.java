package com.cisco.orderapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Aspect
public class LogAspect {
    Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.cisco.orderapp.service.*.*(..))")
    public void logBefore(JoinPoint jp) {
        logger.info("Called : " + jp.getSignature());
        Object[] args = jp.getArgs();
        for(Object arg: args) {
            logger.info("Argument : " + arg);
        }
    }

    @After("execution(* com.cisco.orderapp.service.*.*(..))")
    public void logAfter(JoinPoint jp) {
        logger.info("*********");
    }

    @Around("execution(* com.cisco.orderapp.service.*.*(..))")
    public Object doProfile(ProceedingJoinPoint pjp) throws  Throwable {
        long startTime = new Date().getTime();
            Object obj = pjp.proceed(); // invoke actual method
        long endTime = new Date().getTime();
        logger.info("Time : " + pjp.getSignature() + " ---> " + (endTime - startTime) + " ms");
        return  obj;
    }
}
