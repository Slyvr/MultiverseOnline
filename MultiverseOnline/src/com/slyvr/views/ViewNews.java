package com.slyvr.views;

import java.io.*; 
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/view-news")
public class ViewNews extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		session.setAttribute("content", "news");
		response.sendRedirect("index.jsp");
	}
}
