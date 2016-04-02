/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.abyb.persistence.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hossam ElDeen
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByPictureUrl", query = "SELECT u FROM User u WHERE u.pictureUrl = :pictureUrl"),
    @NamedQuery(name = "User.findByDisabled", query = "SELECT u FROM User u WHERE u.disabled = :disabled")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "picture_url")
    private String pictureUrl;
    @Basic(optional = false)
    @Column(name = "disabled")
    private boolean disabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId")
    private Collection<Reply> replies;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId")
    private Collection<Question> questions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<UserQuestionRate> questionsRated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<UserReplyRate> repliesRated;
    
    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String email, String name, String password, boolean disabled) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.disabled = disabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @XmlTransient
    public Collection<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Collection<Reply> replies) {
        this.replies = replies;
    }

    @XmlTransient
    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }

    @XmlTransient
    public Collection<UserQuestionRate> getQuestionsRated() {
        return questionsRated;
    }

    public void setQuestionsRated(Collection<UserQuestionRate> questionsRated) {
        this.questionsRated = questionsRated;
    }

    @XmlTransient
    public Collection<UserReplyRate> getRepliesRated() {
        return repliesRated;
    }

    public void setRepliesRated(Collection<UserReplyRate> repliesRated) {
        this.repliesRated = repliesRated;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Users[ id=" + id + " ]";
    }

    
}
