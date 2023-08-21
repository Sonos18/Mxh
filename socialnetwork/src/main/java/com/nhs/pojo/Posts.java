/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p"),
    @NamedQuery(name = "Posts.findByPostId", query = "SELECT p FROM Posts p WHERE p.postId = :postId"),
    @NamedQuery(name = "Posts.findByContent", query = "SELECT p FROM Posts p WHERE p.content = :content"),
    @NamedQuery(name = "Posts.findByCreatedAt", query = "SELECT p FROM Posts p WHERE p.createdAt = :createdAt"),
    @NamedQuery(name = "Posts.findByUpdatedAt", query = "SELECT p FROM Posts p WHERE p.updatedAt = :updatedAt"),
    @NamedQuery(name = "Posts.findByImage", query = "SELECT p FROM Posts p WHERE p.image = :image")})
public class Posts implements Serializable {

    @Column(name = "isLocked")
    private Boolean isLocked;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "post_id")
    private Integer postId;
    @Size(max = 100)
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @JoinTable(name = "post_hashtags", joinColumns = {
        @JoinColumn(name = "post_id", referencedColumnName = "post_id")}, inverseJoinColumns = {
        @JoinColumn(name = "hashtag_id", referencedColumnName = "hashtag_id")})
    @ManyToMany
    private Set<Hashtags> hashtagsSet;
    @OneToMany(mappedBy = "postId")
    private Set<Hashtags> hashtagsSet1;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users userId;

    public Posts() {
    }

    public Posts(String content, String image) {
        this.content = content;
        this.image = image;
    }

    public Posts(Integer postId) {
        this.postId = postId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    public Set<Hashtags> getHashtagsSet() {
        return hashtagsSet;
    }

    public void setHashtagsSet(Set<Hashtags> hashtagsSet) {
        this.hashtagsSet = hashtagsSet;
    }

    @XmlTransient
    public Set<Hashtags> getHashtagsSet1() {
        return hashtagsSet1;
    }

    public void setHashtagsSet1(Set<Hashtags> hashtagsSet1) {
        this.hashtagsSet1 = hashtagsSet1;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postId != null ? postId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.postId == null && other.postId != null) || (this.postId != null && !this.postId.equals(other.postId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhs.pojo.Posts[ postId=" + postId + " ]";
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }
    
}
