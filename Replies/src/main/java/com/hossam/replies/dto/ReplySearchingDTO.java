/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.replies.dto;

import com.hossam.abyb.persistence.entities.Reply;

/**
 *
 * @author Hossam ElDeen
 */
public class ReplySearchingDTO {

    Integer key;
    private RetrievalType retrievalType;

    public static enum RetrievalType {

        PARENT_REPLY, QUESTION, REPLY, OWNER_ID
    };

    public ReplySearchingDTO() {
    }

    public ReplySearchingDTO(Integer key, RetrievalType retrievalType) {
        this.key = key;
        this.retrievalType = retrievalType;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public RetrievalType getRetrievalType() {
        return retrievalType;
    }

    public void setRetrievalType(RetrievalType retrievalType) {
        this.retrievalType = retrievalType;
    }

    public Reply getEntity() throws IllegalAccessException {
        if (retrievalType == RetrievalType.REPLY) {
            Reply reply = new Reply(key);
            return reply;
        }
        throw new IllegalAccessException("you can not access this method unless RetrievalType is REPLY");
    }
}
