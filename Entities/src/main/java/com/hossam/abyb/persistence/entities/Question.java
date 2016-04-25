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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Hossam ElDeen
 */
@Entity
@Table(name = "questions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findById", query = "SELECT q FROM Question q WHERE q.id = :id"),
    @NamedQuery(name = "Question.findByHeader", query = "SELECT q FROM Question q WHERE q.header = :header"),
    @NamedQuery(name = "Question.findByPictureUrl", query = "SELECT q FROM Question q WHERE q.pictureUrl = :pictureUrl"),
    @NamedQuery(name = "Question.findByUp", query = "SELECT q FROM Question q WHERE q.up = :up"),
    @NamedQuery(name = "Question.findByDown", query = "SELECT q FROM Question q WHERE q.down = :down"),
    @NamedQuery(name = "Question.findByDisabled", query = "SELECT q FROM Question q WHERE q.disabled = :disabled")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "header")
    private String header;
    @Basic(optional = false)
    @Lob
    @Column(name = "body")
    private String body;
    @Column(name = "picture_url")
    private String pictureUrl;
    @Column(name = "up")
    private Integer up;
    @Column(name = "down")
    private Integer down;
    @Basic(optional = false)
    @Column(name = "disabled")
    private boolean disabled;
    @JoinTable(name = "question_tags", joinColumns = {
        @JoinColumn(name = "QUESTION_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "TAG_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Tag> tags;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User ownerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private Collection<UserQuestionRate> usersRatings;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private Collection<Reply> replies;

    public Question() {
    }

    public Question(Integer id) {
        this.id = id;
    }

    public Question(Integer id, String header, String body, boolean disabled) {
        this.id = id;
        this.header = header;
        this.body = body;
        this.disabled = disabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @XmlTransient
    public Collection<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Collection<Reply> replies) {
        this.replies = replies;
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

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public Integer getDown() {
        return down;
    }

    public void setDown(Integer down) {
        this.down = down;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @XmlTransient
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
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
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Questions[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<UserQuestionRate> getUsersRatings() {
        return usersRatings;
    }

    public void setUsersRatings(Collection<UserQuestionRate> usersRatings) {
        this.usersRatings = usersRatings;
    }
}
