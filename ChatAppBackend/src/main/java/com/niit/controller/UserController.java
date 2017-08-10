package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.niit.Model.Error;
import com.niit.Model.User;
import com.niit.dao.UserDao;

@RestController
public class UserController 
{
	
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/registeruser",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		try
		{
		System.out.println("controller");
		User existUser=userDao.validateUsername(user.getUsername());
		if(existUser!=null)
		{
			Error error=new Error(2,"Username already exist, please enter something different");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
				
		}
		User existEmail=userDao.validateUsername(user.getUsername());
		if(existEmail!=null)
		{
			Error error=new Error(3,"Email already exist.");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
				
		}
			userDao.registerUser(user);
			System.out.println(user);
			return new ResponseEntity<User>(user,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			Error error=new Error(1, "Unable to register"+e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@RequestMapping(value="/loginUser",method=RequestMethod.POST)
	public ResponseEntity<?>loginUser(@RequestBody User user,HttpSession session)
	{

		User loginuser=userDao.loginUser(user);
		if(loginuser==null){
			Error error=new Error(4,"Invalid Login credentials Please check!!");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
		}
		user.setOnline(true);
		userDao.onlinestatus(loginuser);
		session.setAttribute("username",loginuser.getUsername());
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/logoutUser",method=RequestMethod.GET)
	public ResponseEntity<?> logoutUser(@RequestBody User user,HttpSession session){
		String username=(String) session.getAttribute("username");
		User userlogout=userDao.validateUsername(username);
		user.setOnline(false);
		userDao.onlinestatus(user);
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
}
