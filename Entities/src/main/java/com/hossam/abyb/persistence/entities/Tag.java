/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.abyb.persistence.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Hossam ElDeen
 */
@Entity
@Table(name = "tags")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t"),
    @NamedQuery(name = "Tag.findById", query = "SELECT t FROM Tag t WHERE t.id = :id"),
    @NamedQuery(name = "Tag.findByTitle", query = "SELECT t FROM Tag t WHERE t.title = :title"),
    @NamedQuery(name = "Tag.findByDisabled", query = "SELECT t FROM Tag t WHERE t.disabled = :disabled")})
public class Tag implements Serializable {

    @ManyToMany(mappedBy = "tagCollection")
    private Collection<Question> questionCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "disabled")
    private boolean disabled;
    @ManyToMany(mappedBy = "tags")
    private List<Question> questions;

    public Tag() {
    }

    public Tag(Integer id) {
        this.id = id;
    }

    public Tag(Integer id, String title, boolean disabled) {
        this.id = id;
        this.title = title;
        this.disabled = disabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @XmlTransient
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) object;
        if (this.title.equals(other.title)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "pojos.Tags[ id=" + id + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Question> getQuestionCollection() {
        return questionCollection;
    }

    public void setQuestionCollection(Collection<Question> questionCollection) {
        this.questionCollection = questionCollection;
    }

}
