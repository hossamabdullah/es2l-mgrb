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
public class ServiceStatusDTO {
    private String status;
    private String message;

    public ServiceStatusDTO() {
    }

    public ServiceStatusDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
    
    
}
