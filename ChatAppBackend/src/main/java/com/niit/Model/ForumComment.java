package com.niit.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="forumcomment")
public class ForumComment {
	@Id
	@GeneratedValue
	private int fcommentid;
	private String forumComment;
	public int getFcommentid() {
		return fcommentid;
	}
	public void setFcommentid(int fcommentid) {
		this.fcommentid = fcommentid;
	}
	public String getForumComment() {
		return forumComment;
	}
	public void setForumComment(String forumComment) {
		this.forumComment = forumComment;
	}
	public Date getForumcommentDate() {
		return forumcommentDate;
	}
	public void setForumcommentDate(Date forumcommentDate) {
		this.forumcommentDate = forumcommentDate;
	}
	public User getForumcommentedBy() {
		return forumcommentedBy;
	}
	public void setForumcommentedBy(User forumcommentedBy) {
		this.forumcommentedBy = forumcommentedBy;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	private Date forumcommentDate;
	@JoinColumn(name="usernme")
	@ManyToOne
	private User forumcommentedBy;
	@ManyToOne
	@JoinColumn(name="forumid")
	private Forum forum;
	
}
