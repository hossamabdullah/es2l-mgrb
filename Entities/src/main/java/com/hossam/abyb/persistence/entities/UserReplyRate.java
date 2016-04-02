/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.abyb.persistence.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hossam ElDeen
 */
@Entity
@Table(name = "user_reply_rate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserReplyRate.findAll", query = "SELECT u FROM UserReplyRate u"),
    @NamedQuery(name = "UserReplyRate.findByUserId", query = "SELECT u FROM UserReplyRate u WHERE u.userReplyRatePK.userId = :userId"),
    @NamedQuery(name = "UserReplyRate.findByReplyId", query = "SELECT u FROM UserReplyRate u WHERE u.userReplyRatePK.replyId = :replyId"),
    @NamedQuery(name = "UserReplyRate.findByRate", query = "SELECT u FROM UserReplyRate u WHERE u.rate = :rate")})
public class UserReplyRate implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserReplyRatePK userReplyRatePK;
    @Basic(optional = false)
    @Column(name = "rate")
    private boolean rate;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "reply_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reply reply;

    public UserReplyRate() {
    }

    public UserReplyRate(UserReplyRatePK userReplyRatePK) {
        this.userReplyRatePK = userReplyRatePK;
    }

    public UserReplyRate(UserReplyRatePK userReplyRatePK, boolean rate) {
        this.userReplyRatePK = userReplyRatePK;
        this.rate = rate;
    }

    public UserReplyRate(int userId, int replyId) {
        this.userReplyRatePK = new UserReplyRatePK(userId, replyId);
    }

    public UserReplyRatePK getUserReplyRatePK() {
        return userReplyRatePK;
    }

    public void setUserReplyRatePK(UserReplyRatePK userReplyRatePK) {
        this.userReplyRatePK = userReplyRatePK;
    }

    public boolean getRate() {
        return rate;
    }

    public void setRate(boolean rate) {
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userReplyRatePK != null ? userReplyRatePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserReplyRate)) {
            return false;
        }
        UserReplyRate other = (UserReplyRate) object;
        if ((this.userReplyRatePK == null && other.userReplyRatePK != null) || (this.userReplyRatePK != null && !this.userReplyRatePK.equals(other.userReplyRatePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.UserReplyRate[ userReplyRatePK=" + userReplyRatePK + " ]";
    }
    
}
