package com.niit.dao;

import java.util.List;

import com.niit.Model.BlogPost;
import com.niit.Model.Forum;

public interface ForumDao 
{
public void saveForum(Forum forum);
public List getapprovedforums(int status);
public void updateforum(Forum forum);

}
