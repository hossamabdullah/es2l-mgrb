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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "replies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reply.findAll", query = "SELECT r FROM Reply r"),
    @NamedQuery(name = "Reply.findById", query = "SELECT r FROM Reply r WHERE r.id = :id"),
    @NamedQuery(name = "Reply.findByPictureUrl", query = "SELECT r FROM Reply r WHERE r.pictureUrl = :pictureUrl"),
    @NamedQuery(name = "Reply.findByUp", query = "SELECT r FROM Reply r WHERE r.up = :up"),
    @NamedQuery(name = "Reply.findByDown", query = "SELECT r FROM Reply r WHERE r.down = :down"),
    @NamedQuery(name = "Reply.findByDisabled", query = "SELECT r FROM Reply r WHERE r.disabled = :disabled")})
public class Reply implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Question question;
    @OneToMany(mappedBy = "parentReply")
    private Collection<Reply> replies;
    @JoinColumn(name = "parent_reply_id", referencedColumnName = "id")
    @ManyToOne
    private Reply parentReply;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User ownerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reply")
    private Collection<UserReplyRate> usersRatings;

    public Reply() {
    }

    public Reply(Integer id) {
        this.id = id;
    }

    public Reply(Integer id, String body, boolean disabled) {
        this.id = id;
        this.body = body;
        this.disabled = disabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    
    @XmlTransient
    public Collection<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Collection<Reply> replies) {
        this.replies = replies;
    }

    public Reply getParentReply() {
        return parentReply;
    }

    public void setParentReply(Reply parentReply) {
        this.parentReply = parentReply;
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
        if (!(object instanceof Reply)) {
            return false;
        }
        Reply other = (Reply) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Replies[ id=" + id + " ]";
    }
    
    @XmlTransient
    public Collection<UserReplyRate> getUsersRatings() {
        return usersRatings;
    }

    public void setUsersRatings(Collection<UserReplyRate> usersRatings) {
        this.usersRatings = usersRatings;
    }
}
