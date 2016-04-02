/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.interceptor.logging;

import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hossam ElDeen
 */
@Aspect
@Component
public class LoggingInterceptor{
    private static final Logger logger = org.apache.log4j.Logger.getLogger(LoggingInterceptor.class);

    public LoggingInterceptor() {
          logger.debug("Logger Interceptor created.");
    }
      
//    @Before("execution(* *(..))")
//    public void before(JoinPoint joinPoint) throws Throwable {
//        logger.debug("Start of METHOD: "+joinPoint.getSignature().getName()+" with SIGNATURE: "
//                +joinPoint.getSignature().toLongString());
//    }
    
    //this method will get executed after the end of the methods either with normal end or with exception
//    @After("execution(* *(..))")
//    public void after(JoinPoint joinPoint) throws Throwable {
//        logger.debug("End of METHOD: "+joinPoint.getSignature().getName()+" with SIGNATURE: "
//                +joinPoint.getSignature().toLongString());
//    }
    
    @AfterThrowing(pointcut = "execution(* *( ..))", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint,Throwable throwable)throws Throwable{
        logger.error("Exceptional flow happened in METHOD: "+joinPoint.getSignature().getName()
                +" with SIGNATURE: "+joinPoint.getSignature().toLongString()+" with EXCEPTION",throwable);
    }
    
    //this method will get executed only when the method returned normally with no exceptions
//    @AfterReturning("execution(* *(..))")
//    public void afterReturning(JoinPoint joinPoint)throws Throwable{
//        logger.debug("Normal flow happened in METHOD: "+joinPoint.getSignature().getName()
//                +" with SIGNATURE: "+joinPoint.getSignature().toLongString());
//    }
}
