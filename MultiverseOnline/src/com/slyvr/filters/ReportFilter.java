package com.slyvr.filters;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.slyvr.ConnProvider;
import com.slyvr.pojo.ConnectionHistory;
import com.slyvr.pojo.Person;

public class ReportFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String host=req.getRemoteHost();
		if (host.equals("76.253.57.129")) host="LOCALHOST";
		
		System.out.println(host+" USER:"+session.getAttribute("username") + " tried to access "
				+ req.getRequestURL() + " on " + new Date() + ".");
		
		//Check database if record for ip and date already submitted today
		Timestamp timeStampDate=new Timestamp(new Date().getTime());
		Session sess=null;
		SessionFactory sessionFactory=null;
		boolean exists=false;
		try {
			sessionFactory=new Configuration().configure().buildSessionFactory();
			sess = sessionFactory.openSession();
			Query query = sess.createQuery("from ConnectionHistory ch where ch.connectionIp = :ip");
			query.setParameter("ip", host);
			List<ConnectionHistory> list = (List<ConnectionHistory>)query.list();
			for (int i=0; i<list.size(); i++){
				Timestamp date=(Timestamp) list.get(i).getConnectionDate();
				//3600000 = approx an hour --- +/- an hour
				if(Math.abs(date.getTime()-timeStampDate.getTime()) < 3600000){
					sess.close();
					sess=null;
					sessionFactory.close();
					sessionFactory=null;
					exists=true;
					break;
				}
			}
		} catch (Exception e) {
			//System.out.println("ReportConnection1: "+e);
		}
		if (sess!=null){
			sess.close();
			sess=null;
		}
		if (sessionFactory!=null){
			sessionFactory.close();
			sessionFactory=null;
		}
		//send host to CONNECTION_HISTORY
		if (!exists){
			Transaction tx=null;
			try{
				sessionFactory=new Configuration().configure().buildSessionFactory();
				sess = sessionFactory.openSession();
				tx = sess.beginTransaction();
				ConnectionHistory ch = new ConnectionHistory();
				ch.setConnectionIp(host);
				ch.setConnectionDate(timeStampDate);
				sess.save(ch);
				tx.commit();
//				System.out.println(host+" USER:"+session.getAttribute("username") + " tried to access "
//						+ req.getRequestURL() + " on " + new Date() + ".");
			}catch (Exception e){
				//System.out.println("ReportConnection2: "+e);
				if (tx!=null)tx.rollback();
			}
			if (sess!=null){
				sess.close();
				sess=null;
			}
			if (sessionFactory!=null){
				sessionFactory.close();
				sessionFactory=null;
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}