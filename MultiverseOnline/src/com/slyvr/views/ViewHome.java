package com.slyvr.views;

import java.io.*; 
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/view-home")
public class ViewHome extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		session.setAttribute("content", "home");
//		PrintWriter out = response.getWriter();
//		out.println("Testing from Servlet");
//		//same as @include file=index.jsp
//		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//	    dispatcher.include(request, response);
		response.sendRedirect("index.jsp");
	}
}
