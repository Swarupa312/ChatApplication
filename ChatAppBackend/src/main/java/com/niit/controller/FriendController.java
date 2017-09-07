package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Model.BlogComment;
import com.niit.Model.Error;
import com.niit.Model.Friend;
import com.niit.Model.User;
import com.niit.dao.FriendDao;

@Controller
public class FriendController 
{
	@Autowired
	FriendDao friendDao;
	
	@RequestMapping(value="/getsuggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?> getSuggestedUsers(HttpSession session)
	{
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauhorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String username=(String) session.getAttribute("username");
		List<User> friendlist=friendDao.getSuggetedUser(username);
		return new ResponseEntity<List<User>>(friendlist,HttpStatus.OK);
		
	}
	@RequestMapping(value="/friendrequest/{toId}",method=RequestMethod.POST)
	public ResponseEntity<?> friendRequest(@PathVariable String toId,HttpSession session)
	{
		
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauhorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String) session.getAttribute("username");
		
		friendDao.friendRequest(username,toId);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
	public ResponseEntity<?> getPendingRequests(HttpSession session)
	{
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauhorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String username=(String) session.getAttribute("username");
		List<Friend> pendinglist=friendDao.pendingRequest(username);
		return new ResponseEntity<List<Friend>>(pendinglist,HttpStatus.OK);
		
	}





}
