package com.inovace.spring_aop.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Pointcut: all methods in UserService
    @Pointcut("execution(* com.inovace.spring_aop.service.UserService.*(..))")
    public void userServiceMethods() {}

    // Before advice: runs before method execution
    @Before("userServiceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[LOG] Before method: " + joinPoint.getSignature().getName());
    }

    // After advice: runs after method execution
    @After("userServiceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[LOG] After method: " + joinPoint.getSignature().getName());
    }
}