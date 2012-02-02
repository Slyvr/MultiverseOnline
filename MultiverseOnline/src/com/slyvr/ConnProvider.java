package com.slyvr;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ConnProvider {
	private static SessionFactory sessionFactory;
	private static Configuration config=new Configuration().configure();

	public static SessionFactory getSessionFactory() throws Exception{
		sessionFactory=config.buildSessionFactory();
		return sessionFactory;
	}
	public static Session openSession() throws Exception{
		sessionFactory=config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}
	public static void closeSession(Session session){
		session.close();
	}
}
