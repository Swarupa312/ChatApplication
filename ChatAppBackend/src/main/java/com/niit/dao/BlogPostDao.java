package com.niit.dao;

import java.util.List;

import com.niit.Model.BlogPost;

public interface BlogPostDao {
public void saveBlog(BlogPost blogpost);
public List getblogs(int approval);
public BlogPost getblogbyid(int blogid);
public void updateBlog(BlogPost blogpost);

}
