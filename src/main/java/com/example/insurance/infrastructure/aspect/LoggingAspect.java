package com.example.insurance.infrastructure.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.example.insurance.service.*.*(..))")
    public void serviceMethods() {
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerMethods() {
    }

    @Around("controllerMethods()")
    public Object logControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecution(joinPoint, "CONTROLLER");
    }

    @Around("serviceMethods()")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecution(joinPoint, "SERVICE");
    }

    public Object logExecution(ProceedingJoinPoint joinPoint, String layer) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        log.info("Start: {} | Args: {}", methodName, Arrays.toString(args));

        long start = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            log.info("End: {} | Duration: {} ms | Result: {}", methodName, duration, result);
            return result;
        } catch (Throwable ex) {
            log.error("Exception in {} | Message: {}", methodName, ex.getMessage(), ex);
            throw ex;
        }
    }
}