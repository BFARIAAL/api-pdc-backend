package com.jlr.ttl.ds.api.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExecutionTimeAdvice {

    @Around("@annotation(com.jlr.ttl.ds.api.annotation.TrackExecutionTime)")
    public Object trackExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        long startTime = System.currentTimeMillis();
        Object res = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info(className + "::" + methodName + "()::Execution time = " + (endTime - startTime) + " ms");
        return res;
    }
}