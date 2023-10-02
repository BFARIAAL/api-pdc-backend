package com.jlr.ttl.ds.api.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DSLoggingAdvice {

    @Pointcut(value = "execution(* com.jlr.ttl.ds.api.*.*.*(..))")
    public void applicationPointcut(){

    }

    @Before("applicationPointcut()")
    public void dsApplicationLoggerEntering(JoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();

        log.info(className + "::" + methodName + "():: Entering method");
    }

    @After("applicationPointcut()")
    public void dsApplicationLoggerExiting(JoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();

        log.info(className + "::" + methodName + "():: Exiting method");
    }
}
