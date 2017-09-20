package com.niit.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="forum")
public class Forum 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int forumid;
	@ManyToOne
	@JoinColumn(name="username")
	private User forumBy;
	public int getForumid() {
		return forumid;
	}
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}
	public User getForumBy() {
		return forumBy;
	}
	public void setForumBy(User forumBy) {
		this.forumBy = forumBy;
	}
	public String getForumText() {
		return forumText;
	}
	public boolean isForumstatus() {
		return forumstatus;
	}
	public void setForumstatus(boolean forumstatus) {
		this.forumstatus = forumstatus;
	}
	public void setForumText(String forumText) {
		this.forumText = forumText;
	}
	public Date getForumdate() {
		return forumdate;
	}
	public void setForumdate(Date forumdate) {
		this.forumdate = forumdate;
	}
	private String forumText;
	private Date forumdate;
	private boolean forumstatus;
}
