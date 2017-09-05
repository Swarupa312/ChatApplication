package com.niit.dao;

import java.util.List;

import com.niit.Model.BlogComment;
import com.niit.Model.BlogPost;

public interface BlogPostDao {
public void saveBlog(BlogPost blogpost);
public List getblogs(int approval);
public BlogPost getblogbyid(int blogid);
public void updateBlog(BlogPost blogpost);
public void addComment(BlogComment blogcomment);
public List getcomments(int blogid);

}
