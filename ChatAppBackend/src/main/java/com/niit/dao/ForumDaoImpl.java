package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Model.Forum;
import com.niit.Model.ForumComment;
@Repository
@Transactional
public class ForumDaoImpl implements ForumDao 
{

	@Autowired
	SessionFactory sessionFactory;
	public void saveForum(Forum forum) {
		Session session=sessionFactory.getCurrentSession();
		session.save(forum);
		
	}

	
	public List getapprovedforums(int status) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Forum where forumstatus="+status);
		return query.list();
	}


	public void updateforum(Forum forum) {
Session session=sessionFactory.getCurrentSession();	
		
		session.update(forum);
		
	}


	
	public Forum getforumById(int forumid) {
		Session session=sessionFactory.getCurrentSession();	
		Forum forum=(Forum) session.get(Forum.class, forumid);
		return forum;
		
	}


	
	public List getCommentsByForumId(int forumid) {
		Session session=sessionFactory.getCurrentSession();	
		Query query=session.createQuery("from ForumComment where forum.forumid="+forumid);
		return query.list();
	}


	
	public void saveComment(ForumComment forumcomment) {
		Session session=sessionFactory.getCurrentSession();
		session.save(forumcomment);
	}

}
