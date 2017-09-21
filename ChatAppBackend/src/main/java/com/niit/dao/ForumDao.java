package com.niit.dao;

import java.util.List;

import com.niit.Model.BlogPost;
import com.niit.Model.Forum;
import com.niit.Model.ForumComment;

public interface ForumDao 
{
public void saveForum(Forum forum);
public List getapprovedforums(int status);
public void updateforum(Forum forum);
public Forum getforumById(int forumid);
public List getCommentsByForumId(int forumid);
public void saveComment(ForumComment forumcomment);

}
