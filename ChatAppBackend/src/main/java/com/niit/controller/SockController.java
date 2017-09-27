package com.niit.controller;

import java.util.ArrayList;
import java.util.List;


import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;

import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.messaging.simp.annotation.SendToUser;

//import org.springframework.messaging.simp.annotation.SubscribeEvent;

import org.springframework.messaging.simp.annotation.SubscribeMapping;

import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Controller;

import com.niit.Model.Chat;

@Controller
public class SockController 
{
	private static final Log logger = LogFactory.getLog(SockController.class);
	private SimpMessagingTemplate messagingTemplate;
	private List<String> users=new ArrayList<String>();
	
	@Autowired
	public SockController(SimpMessagingTemplate messagingTemplate) {
	
		this.messagingTemplate = messagingTemplate;
	}
	
@SubscribeMapping("/join/{username}")		//To subscribe  with server
public List<String> join(@DestinationVariable("username") String username)
	{
	
	
	if(!users.contains(username))
		users.add(username);
	messagingTemplate.convertAndSend("/topic/join", username);
	return users;
	}

@MessageMapping(value="/chat") 	//To chat with other users
public void chatRecieved(Chat chat)
{
	

	if("all".equals(chat.getTo()))
	{
		
		messagingTemplate.convertAndSend("/queue/chats", chat);
	}
	else
	{
		
		messagingTemplate.convertAndSend("/queue/chats/"+chat.getTo(), chat);
		messagingTemplate.convertAndSend("/queue/chats/"+chat.getFrom(), chat);
		
	}
}


}
