/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.replies.dto;

import com.hossam.abyb.persistence.entities.Question;
import com.hossam.abyb.persistence.entities.Reply;
import com.hossam.abyb.persistence.entities.User;

/**
 *
 * @author Hossam ElDeen
 */
public class ReplyAddingDTO {
    Integer parentReplyId;
    Integer ownerId;
    String body;
    String pictures;
    Integer questionId;

    public ReplyAddingDTO() {
    }

    public ReplyAddingDTO(Integer parentReplyId, Integer ownerId, String body, String pictures, Integer questionId) {
        this.parentReplyId = parentReplyId;
        this.ownerId = ownerId;
        this.body = body;
        this.pictures = pictures;
        this.questionId = questionId;
    }

    public Integer getParentReplyId() {
        return parentReplyId;
    }

    public void setParentReplyId(Integer parentReplyId) {
        this.parentReplyId = parentReplyId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    

    public Object getEntity(User user,Reply parent,Question question) {
        Reply reply = new Reply();
        reply.setBody(this.getBody());
        reply.setOwnerId(user);
        reply.setPictureUrl(this.getPictures());
        reply.setParentReply(parent);
        reply.setQuestion(question);
        return reply;
    }
    
    
}
