package com.slyvr.listeners;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {
	private static int totalSessionCount = 0;
	private int currentSessionCount = 0;
	private int maxSessionCount = 0;
	private ServletContext context = null;

	public void sessionCreated(HttpSessionEvent event) {
		totalSessionCount++;
		currentSessionCount++;
		if (currentSessionCount > maxSessionCount) {
			maxSessionCount = currentSessionCount;
		}
		if (context == null) {
			storeInServletContext(event);
		}
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		currentSessionCount--;
	}

	// Register self in the servlet context so that
	// servlets and JSP pages can access the session
	// counts.
	private void storeInServletContext(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		context = session.getServletContext();
		context.setAttribute("sessionCounter", this);
	}
	public static int getTotalSessionCount(){
		return totalSessionCount;
	}
}