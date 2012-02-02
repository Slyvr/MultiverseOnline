package com.slyvr;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.slyvr.pojo.Person;

import junit.framework.TestCase;


public class DBConnectTest extends TestCase {

	SessionFactory sessionFactory;
	
	protected void setUp() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public void test() throws Exception{
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Person");
		Iterator it = query.iterate();
		while(it.hasNext()){
			Person row = (Person)it.next();
			System.out.println(row.getUserName()+" "+row.getPassword()+" :"+row.getIpAddress());
		}
		session.close();
	}
}
