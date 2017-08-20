package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
