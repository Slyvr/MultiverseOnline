package com.slyvr;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.slyvr.pojo.Person;

import junit.framework.TestCase;


public class DBInsertTest extends TestCase {

	SessionFactory sessionFactory;
	
	protected void setUp() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public void test() throws Exception{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Person p = new Person();
			p.setUserName("test2");
			p.setPassword("test2");
			p.setIpAddress("testip2");
			session.save(p);
			tx.commit();
			System.out.println("saved");
		}catch (Exception e){
			System.out.println("Exception: "+e);
			tx.rollback();
		}
		session.close();
	}
}
