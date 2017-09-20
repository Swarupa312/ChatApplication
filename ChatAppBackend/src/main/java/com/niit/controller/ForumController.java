package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Model.BlogPost;
import com.niit.Model.Error;
import com.niit.Model.Forum;
import com.niit.Model.Job;
import com.niit.Model.User;
import com.niit.dao.ForumDao;
import com.niit.dao.UserDao;

@Controller
public class ForumController 
{
@Autowired
ForumDao forumDao;

@Autowired
UserDao userDao;


@RequestMapping(value="/saveforum",method=RequestMethod.POST)
public ResponseEntity<?> saveForum(@RequestBody Forum forum, HttpSession session)
{
	if(session.getAttribute("username")==null){
		Error error=new Error(5,"Unauhorized");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	String username=(String) session.getAttribute("username");
	User user=userDao.validateUsername(username);
	forum.setForumdate(new Date());
	forum.setForumBy(user);
	forum.setForumstatus(false);
	try{
		forumDao.saveForum(forum);
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}catch(Exception e){
		Error error=new Error(1,"Unable to save the forum");
		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@RequestMapping(value="/getforumstatus/{status}",method=RequestMethod.GET)
public ResponseEntity<?> getstatusforums(@PathVariable("status") int status,HttpSession session){
	if(session.getAttribute("username")==null){
		Error error=new Error(5,"Unauhorized");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	List<Forum> list=forumDao.getapprovedforums(status);
	return new ResponseEntity<List<Forum>>(list,HttpStatus.OK);
	
	
}

@RequestMapping(value="/updateforum",method=RequestMethod.PUT)
public ResponseEntity<?> updateforum(@RequestBody Forum forum,HttpSession session){
	
	System.out.println("in controller");
	if(session.getAttribute("username")==null){
		Error error=new Error(5,"Unauthorized");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);	
	}
	try{
		System.out.println(forum.isForumstatus());
	forumDao.updateforum(forum);
	System.out.println(forum.isForumstatus());
	return new ResponseEntity<Void>(HttpStatus.OK);
	
	}
	catch(Exception e){
		Error error=new Error(1,"Unable to make changes please do again!!"+e.getMessage());
		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
}
}
