package com.niit.dao;

import java.util.List;

import com.niit.Model.Friend;
import com.niit.Model.User;

public interface FriendDao 
{
	public List<User> getSuggetedUser(String username);

	public void friendRequest(String username, String toId);
	
	public List<Friend> pendingRequest(String username);
}
