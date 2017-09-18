package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Model.Friend;
import com.niit.Model.User;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao
{

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<User>getSuggetedUser(String username) {
		Session session=sessionFactory.getCurrentSession();
		String queryString="select * from chatUser where username in (select username from chatUser where username!=? minus (select fromId from friend where toId=? union select toId from friend where fromId=?))";
		SQLQuery query=session.createSQLQuery(queryString);
		query.setString(0,username);
		query.setString(1,username);

		query.setString(2,username);
		query.addEntity(User.class);
		
		List<User> suggestedusers=query.list();
		return suggestedusers;
	}

	
	public void friendRequest(String username, String toId) {
		Session session=sessionFactory.getCurrentSession();
		Friend friend=new Friend();
		friend.setFromId(username);
		friend.setToId(toId);
		friend.setStatus('p');
		session.save(friend);
		
	}

	public List<Friend> pendingRequest(String username) 
	{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Friend where toId=? and status='p'");
			query.setString(0, username);
			return query.list();
	}



	public void updateRequest(Friend requestupdate) {
		Session session =sessionFactory.getCurrentSession();
		if(requestupdate.getStatus()=='D')
		{
			session.delete(requestupdate);
		}
		else
		{
			session.update(requestupdate);
		}
		
	}



	public List<Friend> listOfFriends(String username) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend where (fromId=? or toId=?) and status='A'");
		query.setString(0,username);
		query.setString(1,username);
		
		return query.list();
		
	}



	
}
