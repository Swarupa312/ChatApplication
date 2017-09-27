package com.niit.controller;

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

import com.niit.Model.BlogComment;
import com.niit.Model.Error;
import com.niit.Model.Friend;
import com.niit.Model.User;
import com.niit.dao.FriendDao;
import com.niit.dao.UserDao;

@Controller
public class FriendController 
{
	@Autowired
	FriendDao friendDao;
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/getsuggestedusers",method=RequestMethod.GET)	//To get the suggested friends to user
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
	@RequestMapping(value="/friendrequest/{toId}",method=RequestMethod.POST)		//To send the friend request to perticular user
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

	@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)		//To get the users with pending requests
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
	
	@RequestMapping(value="/updaterequest", method=RequestMethod.POST)	//To approve or reject the request
	public ResponseEntity<?> updateRequest(@RequestBody Friend requestupdate,HttpSession session)
	{
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauhorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		friendDao.updateRequest(requestupdate);
		return new ResponseEntity<Friend>(requestupdate,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getuserdetails/{fromId}", method=RequestMethod.GET)	//To get the particular friend profile
	public ResponseEntity<?> getUserDetails(@PathVariable String fromId,HttpSession session)
	{
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauhorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.validateUsername(fromId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/getfriendlist", method=RequestMethod.GET) 	//To get the friend list
	public ResponseEntity<?> listOfFriends(HttpSession session)
	{
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauhorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String username=(String) session.getAttribute("username");
		List<Friend> friendlist=friendDao.listOfFriends(username);
		return new ResponseEntity<List<Friend>>(friendlist,HttpStatus.OK);
	}

}
