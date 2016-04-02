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
public class QuestionRatingDTO {
    Integer questionId;
    Integer userId;
    Boolean rate;

    public QuestionRatingDTO() {
    }

    public QuestionRatingDTO(Integer questionId, Integer userId, Boolean rate) {
        this.questionId = questionId;
        this.userId = userId;
        this.rate = rate;
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

    public Boolean getRate() {
        return rate;
    }

    public void setRate(Boolean rate) {
        this.rate = rate;
    }
}
