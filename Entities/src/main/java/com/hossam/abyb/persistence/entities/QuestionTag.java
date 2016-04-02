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
@Table(name = "question_tags")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionTag.findAll", query = "SELECT q FROM QuestionTag q"),
    @NamedQuery(name = "QuestionTag.findByTagId", query = "SELECT q FROM QuestionTag q WHERE q.questionTagPK.tagId = :tagId"),
    @NamedQuery(name = "QuestionTag.findByQuestionId", query = "SELECT q FROM QuestionTag q WHERE q.questionTagPK.questionId = :questionId"),
    @NamedQuery(name = "QuestionTag.findByDisabled", query = "SELECT q FROM QuestionTag q WHERE q.disabled = :disabled")})
public class QuestionTag implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected QuestionTagPK questionTagPK;
    @Basic(optional = false)
    @Column(name = "disabled")
    private boolean disabled;
    @JoinColumn(name = "QUESTION_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Question question;
    @JoinColumn(name = "TAG_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tag tag;

    public QuestionTag() {
    }

    public QuestionTag(QuestionTagPK questionTagPK) {
        this.questionTagPK = questionTagPK;
    }

    public QuestionTag(QuestionTagPK questionTagPK, boolean disabled) {
        this.questionTagPK = questionTagPK;
        this.disabled = disabled;
    }

    public QuestionTag(int tagId, int questionId) {
        this.questionTagPK = new QuestionTagPK(tagId, questionId);
    }

    public QuestionTagPK getQuestionTagPK() {
        return questionTagPK;
    }

    public void setQuestionTagPK(QuestionTagPK questionTagPK) {
        this.questionTagPK = questionTagPK;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionTagPK != null ? questionTagPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionTag)) {
            return false;
        }
        QuestionTag other = (QuestionTag) object;
        if ((this.questionTagPK == null && other.questionTagPK != null) || (this.questionTagPK != null && !this.questionTagPK.equals(other.questionTagPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.QuestionTags[ questionTagsPK=" + questionTagPK + " ]";
    }
    
}
