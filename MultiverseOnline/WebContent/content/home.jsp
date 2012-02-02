<div>
<%
String user=(String)session.getAttribute("username");
String loginstat=(String)session.getAttribute("loginstatus");
if (user==null||user.trim().equals("")||loginstat.equals("Invalid")){%>
	<h2>Home</h2>
<%}else{
	out.println("<h2>Home - Profile: "+user+"</h2");
	if (user.equals("admin")||user.equals("127.0.0.1")){
		%><%@include file="admin.jsp"%><%
	}
}%>
<br>

<table border="1" style="background-color: black;" align="center" width="60%">
<tr><td width="100px">
Multiverse is an indie game developed by Matt Schrum (aka Slyvr) and is currently in the early stages of development.
The game involves a mixture of block-placement, classic side scrolling, and travel through alternate 'verses' in order to solve complex
puzzles and defeat creatures that stand in your way.
</td></tr>
</table>
<br><br>
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) {return;}
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=10812399285";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<div class="fb-like" data-href="76.253.57.129/MultiverseOnline" data-send="false" data-layout="box_count" data-width="450" data-show-faces="true" data-font="lucida grande"></div>

</div>