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
import com.niit.Model.Job;
import com.niit.Model.User;
import com.niit.dao.JobDao;
import com.niit.dao.UserDao;
@RestController
public class JobController
{
	@Autowired
	UserDao userDao;
	@Autowired
	JobDao jobDao;
	
	@RequestMapping(value="/savejob",method=RequestMethod.POST) 		//To save the Job
	public ResponseEntity<?>saveJob(@RequestBody Job job,HttpSession session)
	{
		if(session.getAttribute("username")==null)
		{
			Error error=new Error(5,"Unauthorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String) session.getAttribute("username");
		User user=userDao.validateUsername(username);
		if(user.getRole().equals("Admin"))
		{
			try{
				job.setJpostedon(new Date());
				jobDao.saveJob(job);
				return new ResponseEntity<Job>(job,HttpStatus.OK);
				
				}
			catch(Exception e)
			{
				Error error=new Error(1,"Unable to save please do again!!"+e.getMessage());
				return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else{	
			Error error=new Error(6,"Access denied!!");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
			}
	}
	
	@RequestMapping(value="/showjob", method=RequestMethod.GET)		//To show all jobs
	public ResponseEntity<?> showJob(HttpSession session)
	{
		if(session.getAttribute("username")==null)
		{
			Error error=new Error(5,"Unauthorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Job> joblist=jobDao.showJob();
		return new ResponseEntity<List<Job>>(joblist,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getjobbyid/{jid}", method=RequestMethod.GET)	//To get particular
	public ResponseEntity<?> getJobById(@PathVariable("jid") int jid,HttpSession session)
	{
		
		if(session.getAttribute("username")==null)
		{
			Error error=new Error(5,"Unauthorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Job job=jobDao.getJobById(jid);
		System.out.println(job.getJtitle());
		return new ResponseEntity<Job>(job,HttpStatus.OK);
		
	}

}
