package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.niit.Model.Error;
import com.niit.Model.User;
import com.niit.Model.BlogComment;
import com.niit.Model.BlogPost;
import com.niit.dao.BlogPostDao;
import com.niit.dao.UserDao;

@RestController
public class BlogPostController 
{
	@Autowired
	BlogPostDao blogpostDao;
@Autowired
UserDao userDao;

@RequestMapping(value="/saveblog",method=RequestMethod.POST) 	//To save the blog
public ResponseEntity<?> saveBlog(@RequestBody BlogPost blogpost,HttpSession session)
{
	
	if(session.getAttribute("username")==null){
		Error error=new Error(5,"Unauhorized");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	String username=(String) session.getAttribute("username");
	User user=userDao.validateUsername(username);
	blogpost.setBlogdate(new Date());
	blogpost.setAuthor(user);
	try{
		blogpostDao.saveBlog(blogpost);		//calls the DAOIMPL method
		return new ResponseEntity<BlogPost>(blogpost,HttpStatus.OK);
	}catch(Exception e){
		Error error=new Error(1,"Unable to save the blog");
		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
@RequestMapping(value="/getblog/{approved}",method=RequestMethod.GET) //To get the blog list which are approved or unapproved approved=1, unapproved=0
public ResponseEntity<?> getBlog(@PathVariable("approved") int approved,HttpSession session){
	if(session.getAttribute("username")==null){
		Error error=new Error(5,"Unauhorized");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	List<BlogPost> list=blogpostDao.getblogs(approved);
	return new ResponseEntity<List<BlogPost>>(list,HttpStatus.OK);
	
	
}


@RequestMapping(value="/getblogbyid/{blogid}",method=RequestMethod.GET) //To get the perticular blog
public ResponseEntity<?> getBlogById(@PathVariable("blogid") int blogid,HttpSession session){
	if(session.getAttribute("username")==null){
		Error error=new Error(5,"Unauhorized");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	BlogPost blogpost=blogpostDao.getblogbyid(blogid);
	
	return new ResponseEntity<BlogPost>(blogpost,HttpStatus.OK);
	
	
}

@RequestMapping(value="/updateblog",method=RequestMethod.PUT) 	//To Approve the blog
public ResponseEntity<?> updateBlog(@RequestBody BlogPost blogpost, HttpSession session){
	
	
	if(session.getAttribute("username")==null){
		Error error=new Error(5,"Unauthorized");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);	
	}
	try{
	blogpostDao.updateBlog(blogpost);
	
	return new ResponseEntity<Void>(HttpStatus.OK);
	
	}
	catch(Exception e){
		Error error=new Error(1,"Unable to make changes please do again!!"+e.getMessage());
		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
}




@RequestMapping(value="/commentblog",method=RequestMethod.POST) 		//To comment the blog
public ResponseEntity<?> blogComment(@RequestBody BlogComment blogcomment,HttpSession session)
{
	if(session.getAttribute("username")==null){
		Error error=new Error(5,"Unauhorized");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	String username=(String) session.getAttribute("username");
	User user=userDao.validateUsername(username);
	blogcomment.setCommentDate(new Date());
	blogcomment.setCommentedBy(user);
	try{
		blogpostDao.addComment(blogcomment);
		return new ResponseEntity<BlogComment>(blogcomment,HttpStatus.OK);
	}catch(Exception e){
		Error error=new Error(1,"Unable to save the blog");
		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
@RequestMapping(value="/getcomments/{blogid}", method=RequestMethod.GET) 		//To get the comments of perticular blog
public ResponseEntity<?> getblogcomments(@PathVariable("blogid") int blogid,HttpSession session)
{
	
	if(session.getAttribute("username")==null){
		Error error=new Error(5,"Unauhorized");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	List<BlogComment> commentlist=blogpostDao.getcomments(blogid);

	return new ResponseEntity<List<BlogComment>>(commentlist,HttpStatus.OK);
	
}
}
