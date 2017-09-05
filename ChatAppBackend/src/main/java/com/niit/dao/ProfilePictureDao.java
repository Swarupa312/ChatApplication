package com.niit.dao;

import com.niit.Model.ProfilePicture;

public interface ProfilePictureDao 
{

	void uploadProfilePic(ProfilePicture profilepicture);

	ProfilePicture getProfilePicture(String username);

}
