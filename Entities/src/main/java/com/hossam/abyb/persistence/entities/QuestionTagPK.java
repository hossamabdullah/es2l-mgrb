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
public class QuestionTagPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "TAG_id")
    private int tagId;
    @Basic(optional = false)
    @Column(name = "QUESTION_id")
    private int questionId;

    public QuestionTagPK() {
    }

    public QuestionTagPK(int tagId, int questionId) {
        this.tagId = tagId;
        this.questionId = questionId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
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
        hash += (int) tagId;
        hash += (int) questionId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionTagPK)) {
            return false;
        }
        QuestionTagPK other = (QuestionTagPK) object;
        if (this.tagId != other.tagId) {
            return false;
        }
        if (this.questionId != other.questionId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.QuestionTagsPK[ tAGid=" + tagId + ", qUESTIONid=" + questionId + " ]";
    }
    
}
