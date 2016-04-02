/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.common.dto.businessResponse;

/**
 *
 * @author Hossam ElDeen
 */
public class ServiceDataWrapperDTO extends ServiceStatusDTO{
    private Object data;

    public ServiceDataWrapperDTO() {
    }
    
    public ServiceDataWrapperDTO(Object data,String status, String message){
        super(status, message);
        this.data = data;
    }
    
    public ServiceDataWrapperDTO(Object data, ServiceStatusDTO serviceStatusDTO) {
        super(serviceStatusDTO.getStatus(), serviceStatusDTO.getMessage());
        this.data = data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

}
