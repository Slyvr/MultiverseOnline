package com.slyvr.views;

import java.io.*; 
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/view-register")
public class ViewRegister extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		session.setAttribute("content", "register");
		response.sendRedirect("index.jsp");
	}
}
