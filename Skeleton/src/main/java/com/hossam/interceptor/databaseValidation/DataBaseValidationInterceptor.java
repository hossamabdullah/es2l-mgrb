/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.interceptor.databaseValidation;

import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.util.DataBaseUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hossam ElDeen
 */
@Aspect
@Component
public class DataBaseValidationInterceptor {
    //this method will get executed only when the method returned normally with no exceptions
    @Around("execution(public com.hossam.common.dto.businessResponse.* com..business..impl.*.*(..))")
    public Object afterReturning(ProceedingJoinPoint joinPoint)throws Throwable{
        if(DataBaseUtil.isDataBaseRunning())
            return joinPoint.proceed();
        else
            return new ServiceDataWrapperDTO(null,"FAILED", "system is down, try again later.");
    }
}
