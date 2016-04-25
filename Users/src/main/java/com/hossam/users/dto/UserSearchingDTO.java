/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.users.dto;

/**
 *
 * @author Hossam ElDeen
 */
public class UserSearchingDTO {
    private Object key;
    private RetrievalType retrievalType;
    public static enum RetrievalType {NAME, EMAIL};

    public UserSearchingDTO() {
    }

    public UserSearchingDTO(Object key, RetrievalType retrievalType) {
        this.key = key;
        this.retrievalType = retrievalType;
    }
    
    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public RetrievalType getRetrievalType() {
        return retrievalType;
    }

    public void setRetrievalType(RetrievalType retrievalType) {
        this.retrievalType = retrievalType;
    }
}
