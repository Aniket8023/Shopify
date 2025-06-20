package com.shopify.app.repositories;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopify.app.entity.User;

@Repository
public class UserRepository {
	
	@Autowired
	private SessionFactory factory ;

	public void registerUser(User user) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(user) ;
		transaction.commit();
		session.close();
	}

	public User getUser(long id) {
		Session session = null ;
		User user = null ;
		try {
			 session = factory.openSession();
		     user = session.get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
	
	
	public User getUserByUserName(String userName) {
		Session session = null ;
		User user = null ;
		try {
			 session = factory.openSession();
			 System.out.println(userName);
		     Criteria criteria = session.createCriteria(User.class) ;
		     criteria.add( Restrictions.eq("username", userName) ) ;
		     user = (User) criteria.uniqueResult();
		     System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	public void updateUser(User user) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(user) ;
		transaction.commit();
		session.close();
	}

	public void deleteUser(long id) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		User user = session.get(User.class, id);
		session.delete(user) ;
		transaction.commit();
		session.close();
	}

}
