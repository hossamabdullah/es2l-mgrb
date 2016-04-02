/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.questions.dto;

/**
 *
 * @author Hossam ElDeen
 */
public class QuestionRetrievalDTO {
    private Object keyword;
    private RetrievalType retrievalType;
    public static enum RetrievalType { ID, HEADER, BODY, HEADER_OR_BODY};

    public QuestionRetrievalDTO() {
    }

    public QuestionRetrievalDTO(Object keyword, RetrievalType retrievalType) {
        this.keyword = keyword;
        this.retrievalType = retrievalType;
    }

    public Object getKeyword() {
        return keyword;
    }

    public void setKeyword(Object keyword) {
        this.keyword = keyword;
    }

    public RetrievalType getRetrievalType() {
        return retrievalType;
    }

    public void setRetrievalType(RetrievalType retrievalType) {
        this.retrievalType = retrievalType;
    }
    
    
}
