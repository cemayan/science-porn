package com.cayan.userservice.aop;

import org.apache.commons.lang.time.StopWatch;
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

        LoggerFactory.getLogger(className).info(request.getRemoteHost() + " " + request.getLocalPort() + " " +  request.getMethod() + " " + request.getRequestURI());

        return proceed;
    }
}
