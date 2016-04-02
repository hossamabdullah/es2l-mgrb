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
@Table(name = "user_question_rate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserQuestionRate.findAll", query = "SELECT u FROM UserQuestionRate u"),
    @NamedQuery(name = "UserQuestionRate.findByUserId", query = "SELECT u FROM UserQuestionRate u WHERE u.userQuestionRatePK.userId = :userId"),
    @NamedQuery(name = "UserQuestionRate.findByQuestionId", query = "SELECT u FROM UserQuestionRate u WHERE u.userQuestionRatePK.questionId = :questionId"),
    @NamedQuery(name = "UserQuestionRate.findByRate", query = "SELECT u FROM UserQuestionRate u WHERE u.rate = :rate")})
public class UserQuestionRate implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserQuestionRatePK userQuestionRatePK;
    @Basic(optional = false)
    @Column(name = "rate")
    private boolean rate;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "question_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Question question;

    public UserQuestionRate() {
    }

    public UserQuestionRate(UserQuestionRatePK userQuestionRatePK) {
        this.userQuestionRatePK = userQuestionRatePK;
    }

    public UserQuestionRate(UserQuestionRatePK userQuestionRatePK, boolean rate) {
        this.userQuestionRatePK = userQuestionRatePK;
        this.rate = rate;
    }

    public UserQuestionRate(int userId, int questionId) {
        this.userQuestionRatePK = new UserQuestionRatePK(userId, questionId);
    }

    public UserQuestionRatePK getUserQuestionRatePK() {
        return userQuestionRatePK;
    }

    public void setUserQuestionRatePK(UserQuestionRatePK userQuestionRatePK) {
        this.userQuestionRatePK = userQuestionRatePK;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userQuestionRatePK != null ? userQuestionRatePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserQuestionRate)) {
            return false;
        }
        UserQuestionRate other = (UserQuestionRate) object;
        if ((this.userQuestionRatePK == null && other.userQuestionRatePK != null) || (this.userQuestionRatePK != null && !this.userQuestionRatePK.equals(other.userQuestionRatePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.UserQuestionRate[ userQuestionRatePK=" + userQuestionRatePK + " ]";
    }
    
}
