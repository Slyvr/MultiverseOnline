<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Multiverse Online</title>
<style type="text/css">
html, body {height:100%; margin:0; padding:0;}
#page-background {position:fixed; top:0; left:0; width:100%; height:100%;}
#content {position:relative; z-index:1; padding:10px;}
</style>
<!--[if IE 6]>
<style type="text/css">
html {overflow-y:hidden;}
body {overflow-y:auto;}
#page-background {position:absolute; z-index:-1;}
#content {position:static;padding:10px;}
</style>
<![endif]-->
</head>
<body link="yellow" vlink="orange">
	<div id="page-background"><img src="resources/wallpaper-1502.jpg" width="100%" height="100%" alt="earth_cropped"></div>
	<div id="content">
	
		<table border="0" width="80%" height="800px" align="center" cellpadding="20">
		<tr height="20%"><td width="75%" align="center">
		<!-- LOGO INCLUDE -->
		<%@include file="logo.jsp" %>
		</td><td width="25%" valign="top">
		<!-- LOGIN INCLUDE -->
		<%@include file="login.jsp" %>
		</td></tr>
		<tr height="15%"><td>
		<!-- NAVIGATION INCLUDE -->
		<%@include file="navigation.jsp" %>
		</td><td align="center">
		
		<!-- EXTRA COLUMN -->

		</td></tr>
		<tr height="65%">
		<td valign="top">
		<!-- CONTENT INCLUDE -->
		<%@include file="content.jsp" %>
		</td><td>
				
		</td></tr>
		</table>
	</div>
</body>
</html>
