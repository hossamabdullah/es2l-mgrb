/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.abyb.persistence.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Hossam ElDeen
 */
@Embeddable
public class UserQuestionRatePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "question_id")
    private int questionId;

    public UserQuestionRatePK() {
    }

    public UserQuestionRatePK(int userId, int questionId) {
        this.userId = userId;
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) questionId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserQuestionRatePK)) {
            return false;
        }
        UserQuestionRatePK other = (UserQuestionRatePK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.questionId != other.questionId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.UserQuestionRatePK[ userId=" + userId + ", questionId=" + questionId + " ]";
    }
    
}
