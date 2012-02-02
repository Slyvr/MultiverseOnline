package com.slyvr.admin;

import java.io.*; 
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/check-login")
public class LockSite extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html");
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		HttpSession session = request.getSession();
		
		//ban all access to site except localhost
		
	}
}
