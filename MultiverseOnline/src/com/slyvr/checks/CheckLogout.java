package com.slyvr.checks;

import java.io.*; 
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/check-logout")
public class CheckLogout extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String username=(String)session.getAttribute("username");
		session.setAttribute("content", "home");
		session.setAttribute("loginstatus","Logout");
		session.setAttribute("username", "");
		System.out.println(username+" Successfully Logged Out!");
		response.sendRedirect("index.jsp");
	}
}
