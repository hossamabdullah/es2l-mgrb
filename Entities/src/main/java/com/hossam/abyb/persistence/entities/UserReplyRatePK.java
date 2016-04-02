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
public class UserReplyRatePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "reply_id")
    private int replyId;

    public UserReplyRatePK() {
    }

    public UserReplyRatePK(int userId, int replyId) {
        this.userId = userId;
        this.replyId = replyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) replyId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserReplyRatePK)) {
            return false;
        }
        UserReplyRatePK other = (UserReplyRatePK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.replyId != other.replyId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.UserReplyRatePK[ userId=" + userId + ", replyId=" + replyId + " ]";
    }
    
}
