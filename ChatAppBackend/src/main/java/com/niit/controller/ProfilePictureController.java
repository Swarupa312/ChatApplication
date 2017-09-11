package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.Model.Error;
import com.niit.Model.ProfilePicture;
import com.niit.Model.User;
import com.niit.dao.ProfilePictureDao;
import com.niit.dao.UserDao;

@Controller
public class ProfilePictureController
{
	@Autowired
	ProfilePictureDao profilepictureDao;
	@Autowired
	UserDao userDao;
	@RequestMapping(value="/uploadprofile",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePic(@RequestParam CommonsMultipartFile pimage,HttpSession session)
	{
		//User user=(User)session.getAttribute("username");
		String username=(String) session.getAttribute("username");
		User user=userDao.validateUsername(username);
		if(user==null)
		{
			Error error=new Error(5,"Unauthorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		ProfilePicture profilepicture=new ProfilePicture();
		profilepicture.setUsername(user.getUsername());
		profilepicture.setPimage(pimage.getBytes());
		profilepictureDao.uploadProfilePic(profilepicture);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	@RequestMapping(value="/getprofilepic/{username}", method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePic(@PathVariable String username,HttpSession session){
		String username1=(String) session.getAttribute("username");
		User user=userDao.validateUsername(username1);
		if(user==null)
			return null;
		else
		{
			ProfilePicture profilepicture=profilepictureDao.getProfilePicture(username);
			if(profilepicture==null)
				return null;
			else
				return profilepicture.getPimage();
		}
		
}
}
