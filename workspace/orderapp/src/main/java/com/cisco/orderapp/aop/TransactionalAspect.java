package com.cisco.orderapp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TransactionalAspect {
    Logger logger = LoggerFactory.getLogger(TransactionalAspect.class);

    @Around("@annotation(Tx)")
    public Object doTransaction(ProceedingJoinPoint pjp) throws Throwable {
        Object ret = null;
        try {
            logger.info("Transaction started!!!!");
                    ret = pjp.proceed();
            logger.info("Transaction Commit!!!");
        } catch (Exception ex) {
            logger.info("Transaction rollback!!!");
        }
        return  ret;
    }
}
