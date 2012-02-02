package com.slyvr.checks;

import java.io.*; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.slyvr.ConnProvider;
import com.slyvr.pojo.Person;

@WebServlet("/check-register")
public class CheckRegister extends HttpServlet {
	HttpSession session;
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String valid="true";
		Map<String, String> map = new HashMap<String, String>();
		session = request.getSession();
		
		//Validation
		if (username==null||username.trim().equals("")){
			valid="false";
			username="Invalid: Null";
		}
		if (password==null||password.trim().equals("")){
			valid="false";
			password="Invalid: Null";
		}
		else if (password.length()<6){
			valid="false";
			password="Invalid: Length (min:6)";
		}
		if (!password2.equals(password)&&!password.contains("Invalid")){
			valid="false";
			password2="Invalid: Not Equal";
		}
		if (firstname==null||firstname.trim().equals("")){
			valid="false";
			firstname="Invalid: Null";
		}
		if (lastname==null||lastname.trim().equals("")){
			valid="false";
			lastname="Invalid: Null";
		}
		if (email==null||email.trim().equals("")){
			valid="false";
			email="Invalid: Null";
		}
		else if (!isEmail(email)){
			valid="false";
			email="Invalid: Not A Proper Email";
		}
		
		//Insert into DB or Map and Return
		if (valid.equals("true")){
			boolean exists=CheckDatabase.checkExistingUser(session, username);
			if (!exists) {
				boolean completed=CheckDatabase.insertUser(username, password, firstname, lastname, request);
				if (completed){
					session.setAttribute("registerMap", null);
					session.setAttribute("content","home");
					session.setAttribute("username",username);
					session.setAttribute("loginstatus", "Valid");
					response.sendRedirect("index.jsp");
				}
			}
			else username="Invalid: Already Exists";
		}
		map.put("username", username);
		map.put("password", password);
		map.put("password2", password2);
		map.put("firstname", firstname);
		map.put("lastname", lastname);
		map.put("email", email);
		map.put("valid", valid);
		session.setAttribute("registerMap", map);
		response.sendRedirect("index.jsp");
	}
	
	public static boolean isEmail(String x) {
		boolean b = false;
		if (x.length() <= 30) {
			// regular expression testing for non whitespace with none of these:
			// ! # $ % * / ? ^ " ` { | } ~ @ ( ) 
			String validchars = "[\\S&&[^\\;]&&[^\\!]&&[^\\#]&&[^\\*]&&[^\\/]&&[^\\{]&&[^\\}]&&[^\\|]&&[^\\~]&&[^\\(]&&[^\\)]&&[^\\:]&&[^\\\"]&&[^\\']&&[^\\%]&&[^\\^]&&[^\\?]&&[^\\@]]*";
			String regExp = validchars + "\\@" + validchars + "\\." + validchars;
			Pattern p = Pattern.compile(regExp);
			// match the pattern with the given string
			Matcher matcher = p.matcher(x);
			b = matcher.matches();
		}
		return b;
	}
}
