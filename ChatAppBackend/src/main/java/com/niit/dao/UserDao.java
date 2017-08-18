package com.niit.dao;

import com.niit.Model.User;

public interface UserDao {
 void registerUser(User user);
 User validateUsername(String username);
 User validateEmail(String email);
 User loginUser(User user);
void updateUser(User user);

}
