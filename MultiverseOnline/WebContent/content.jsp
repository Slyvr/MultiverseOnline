<div id="logo" align="center">
<font color="white" size="2">
<%
	String content = (String)session.getAttribute("content");
	if (content==null||content.trim().equals("")){
%>
		<%@include file="content/home.jsp"%>
<%
	}
	else if (content.equals("home")){
%>
		<%@include file="content/home.jsp"%>
<%
	}
	else if (content.equals("register")){
%>
		<%@include file="content/register.jsp"%>
<%
	}
	else if (content.equals("contactus")){
%>
		<%@include file="content/contactus.jsp"%>
<%
	}
	else if (content.equals("news")){
%>
		<%@include file="content/news.jsp"%>
<%
	}
%>
</font>
</div>