/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.common.util;

import java.util.Date;

import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;

/**
 *
 * @author Hossam ElDeen
 */
public class ServiceUtil {
    /**
     * 
     * @param object
     * @param status
     * @param message
     * @return 
     */
    public static ServiceDataWrapperDTO transformToServiceDataWrapper(Object object, String status, String message) {
        if(status==null || message==null){
            throw new IllegalArgumentException("status or message arguments has a value of null");
        }
        
        ServiceStatusDTO serviceStatusDTO = new ServiceStatusDTO(status, message);
        ServiceDataWrapperDTO serviceWrapper = new ServiceDataWrapperDTO(object, serviceStatusDTO);
        return serviceWrapper;
    }
    
    
    public static Date prepareDates(Long dateTime){
        Date date;
        
        if(dateTime == null)
            date = null;
        else
            date = new Date(dateTime);
        
        return date;
    }

    public static String prepareDays(String days) {
        if(days == null)
            return "1234567";
        else
            return days;
    }
}
