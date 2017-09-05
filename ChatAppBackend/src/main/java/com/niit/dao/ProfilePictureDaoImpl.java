package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Model.ProfilePicture;
@Repository
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDao
{

	@Autowired
	SessionFactory sessionFactory;
	public void uploadProfilePic(ProfilePicture profilepicture) {
		Session session=sessionFactory.openSession();
		session.saveOrUpdate(profilepicture);
		session.flush();
		session.close();
		
	}

	
	public ProfilePicture getProfilePicture(String username) 
	{
		Session session=sessionFactory.openSession();
		ProfilePicture profilepicture=(ProfilePicture) session.get(ProfilePicture.class,username);
		session.close();
		return profilepicture;
		
	}

}
