package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Model.BlogComment;
import com.niit.Model.BlogPost;

@Repository
@Transactional
public class BlogPostDaoImpl implements BlogPostDao{

@Autowired
SessionFactory sessionFactory;
	public void saveBlog(BlogPost blogpost) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blogpost);
		
	}
	
	public List<BlogPost> getblogs(int approved) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approved="+approved);
		return query.list();
	}

	
	public BlogPost getblogbyid(int blogid) {
		Session session=sessionFactory.getCurrentSession();
		BlogPost blogpost=(BlogPost) session.get(BlogPost.class, blogid);
		return blogpost;
	}


	public void updateBlog(BlogPost blogpost) {
		Session session=sessionFactory.getCurrentSession();	
		
		session.update(blogpost);
	}

	
	public void addComment(BlogComment blogcomment) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blogcomment);
	}

	public List getcomments(int blogid) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogComment where blogpost.blogid="+blogid);
		return query.list();
		
	}

}
