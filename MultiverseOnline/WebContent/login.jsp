<style type="text/css">
#login {
	background: url(resources/btnLogin.png) no-repeat 0 0;
	width: 54px;
	height: 21px;
	display: block;
	text-indent: -9999px;
}
#login:hover { background-position: 0 -21px; }
#logout {
	background: url(resources/btnLogin.png) no-repeat -54px 0;
	width: 54px;
	height: 21px;
	display: block;
	text-indent: -9999px;
}
#logout:hover { background-position: -54px -21px; }
#register {
	background: url(resources/btnRegister.png) no-repeat 0 0;
	width: 63px;
	height: 21px;
	display: block;
	text-indent: -9999px;
}
#register:hover { background-position: 0 -21px; }
</style>

<div id="logo">
<%
	String username = (String)session.getAttribute("username");
	String loginstatus = (String)session.getAttribute("loginstatus");
	String disabled="enabled";
	String formUrl="check-login";
	String submitValue="login";
	boolean invalid=false;
	if (loginstatus==null)loginstatus="";
	if (username==null)username="";
	if (!username.equals("")&&loginstatus.equals("Valid")){
		formUrl="check-logout";
		submitValue="logout";
		disabled="disabled";
	}
	if (loginstatus.equals("Invalid")) invalid=true;
	if ((username.equals("")||loginstatus.equals("Invalid"))&&!loginstatus.equals("Logout")){
		username="";
	}
	if (loginstatus.equals("Logout")){
		formUrl="check-login";
		disabled="enabled";
		submitValue="login";
		username="";
	}
%>
<form action="<%=formUrl%>" name="loginform">
<table border="0" width="100%">
	<tr><td width="100px">
		<font color="white" size="2">UserName:</font>
	</td><td width="100px">
		<input type="text" value="<%=username %>" name="username" onKeyDown="if(event.keyCode==13) document['loginform'].submit();" <%=disabled%>>
	</td></tr>
	<tr><td>
		<font color="white" size="2">Password:</font>
	</td><td>
		<input type="password" name="password" onKeyDown="if(event.keyCode==13) document['loginform'].submit();" <%=disabled%>>
	</td></tr><tr height="10px"></tr>
	<tr><td>
		<a href="#" onclick="document['loginform'].submit()" id="<%=submitValue%>"><span><%=submitValue%></span></a>
	</td><td>	
		<% if (invalid)%> <font size="2" color="red">Invalid Login Parameters</font>
	</td></tr>
	<tr><td>
		<% if (username.equals("")||loginstatus.equals("Logout")){ %>
		<a href="view-register" id="register"><span>Register</span></a>
		<%} %>
	</td><td></td>
	</tr>
</table>
</form>

</div>