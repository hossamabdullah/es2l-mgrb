/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.questions.dto;

import com.hossam.abyb.persistence.entities.Question;
import com.hossam.abyb.persistence.entities.User;
/**
 *
 * @author Hossam ElDeen
 */
public class QuestionAddingDTO {
    String header;
    String body;
    String pictureUrl;
    Integer ownerId;
    
    
    public QuestionAddingDTO() {
    }

    public QuestionAddingDTO(String header, String body, String pictureUrl, Integer ownerId) {
        this.header = header;
        this.body = body;
        this.pictureUrl = pictureUrl;
        this.ownerId = ownerId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
    
    public Question getEntity(User user){
        Question question = new Question();
        question.setHeader(this.getHeader());
        question.setBody(this.getBody());
        question.setPictureUrl(this.getPictureUrl());
        question.setOwnerId(user);
        return question;
    }
}
