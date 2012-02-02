package com.slyvr.checks;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.slyvr.ConnProvider;
import com.slyvr.pojo.Person;

public class CheckDatabase {
	
	public static boolean checkUser(HttpSession session, String user, String pass){
		Session sess=null;
		SessionFactory sessionFactory=null;
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			sess = sessionFactory.openSession();
			System.out.println("User:"+user+"("+pass+") tried logging in.");
			Query query = sess.createQuery("from Person p where p.userName= :user and p.password= :pass");
			query.setParameter("user", user);
			query.setParameter("pass", pass);
			
			List<Person> list = (List<Person>)query.list();
			if (!list.isEmpty()){
				sess.close();
				sess=null;
				sessionFactory.close();
				sessionFactory=null;
				return true;
			}
		} catch (Exception e) {
			System.out.println("CheckDatabase.checkUser: "+e);
		}
		if (sess!=null){
			sess.close();
			sess=null;
		}
		if (sessionFactory!=null){
			sessionFactory.close();
			sessionFactory=null;
		}
		return false;
	}
	
	public static boolean checkExistingUser(HttpSession session, String user){
		Session sess=null;
		SessionFactory sessionFactory=null;
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			sess = sessionFactory.openSession();
			Query query = sess.createQuery("from Person p where p.userName= :user");
			query.setParameter("user", user);
			List<Person> list = (List<Person>)query.list();
			if (!list.isEmpty()){
				sess.close();
				sess=null;
				sessionFactory.close();
				sessionFactory=null;
				return true;
			}
		} catch (Exception e) {
			System.out.println("CheckDatabase.checkExistingUser: "+e);
		}
		if (sess!=null){
			sess.close();
			sess=null;
		}
		if (sessionFactory!=null){
			sessionFactory.close();
			sessionFactory=null;
		}
		return false;
	}
	
	public static boolean insertUser(String username, String password, String firstname, String lastname, HttpServletRequest req){
		Session sess=null;
		SessionFactory sessionFactory=null;
		Transaction tx=null;
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			Person p = new Person();
			p.setUserName(username);
			p.setPassword(password);
			p.setFirstName(firstname);
			p.setLastName(lastname);
			p.setIpAddress(req.getRemoteHost());
			sess.save(p);
			tx.commit();
			System.out.println(username+" Successfully Registered!");
			sess.close();
			sess=null;
			sessionFactory.close();
			sessionFactory=null;
			return true;
		}catch(Exception e){
			if (tx!=null)tx.rollback();
			System.out.println("CheckDatabase.insertUser: "+e);
		}
		if (sess!=null){
			sess.close();
			sess=null;
		}
		if (sessionFactory!=null){
			sessionFactory.close();
			sessionFactory=null;
		}
		return false;
	}
}
