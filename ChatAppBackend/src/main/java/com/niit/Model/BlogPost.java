package com.niit.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="blog")
public class BlogPost 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogid;
	@NotEmpty
	private String blogtitle;
	@NotEmpty
	private String blogdesc;
	private Date blogdate;
	@ManyToOne
	@JoinColumn(name="username")
	private User author;
	private boolean approved;
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public String getBlogtitle() {
		return blogtitle;
	}
	public void setBlogtitle(String blogtitle) {
		this.blogtitle = blogtitle;
	}
	public String getBlogdesc() {
		return blogdesc;
	}
	public void setBlogdesc(String blogdesc) {
		this.blogdesc = blogdesc;
	}
	public Date getBlogdate() {
		return blogdate;
	}
	public void setBlogdate(Date blogdate) {
		this.blogdate = blogdate;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	

}
