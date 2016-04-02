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
public class QuestionDeletingDTO {
    Integer questionId;
    Integer userId;

    public QuestionDeletingDTO() {
    }

    public QuestionDeletingDTO(Integer questionId, Integer userId) {
        this.questionId = questionId;
        this.userId = userId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    
}
