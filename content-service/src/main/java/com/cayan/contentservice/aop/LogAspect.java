package com.cayan.contentservice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        final String className = joinPoint.getSignature().getDeclaringTypeName();
        final String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        LoggerFactory.getLogger(className).info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }


    @Around("@annotation(LogRequest)")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {

        final String className = joinPoint.getSignature().getDeclaringTypeName();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Object proceed = joinPoint.proceed();


        LoggerFactory.getLogger(className).info(request.getMethod());

        return proceed;
    }
}
