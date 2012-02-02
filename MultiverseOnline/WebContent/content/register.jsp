<div>
<h2>Register</h2>

<%@ page import="java.util.*" %>
<%
@SuppressWarnings("unchecked")
Map<String, String> map = (Map<String, String>)session.getAttribute("registerMap");
String validateUser="";
String validatePass="";
String validatePass2="";
String validateFirst="";
String validateLast="";
String validateEmail="";
String valid="";
if (map!=null){
	validateUser=map.get("username");
	validatePass=map.get("password");
	validatePass2=map.get("password2");
	validateFirst=map.get("firstname");
	validateLast=map.get("lastname");
	validateEmail=map.get("email");
	valid = map.get("valid");
	//If not invalid, set to "" so it doesn't reprint
	if (!validateUser.contains("Invalid"))validateUser="";
	if (!validatePass.contains("Invalid"))validatePass="";
	if (!validatePass2.contains("Invalid"))validatePass2="";
	if (!validateFirst.contains("Invalid"))validateFirst="";
	if (!validateLast.contains("Invalid"))validateLast="";
	if (!validateEmail.contains("Invalid"))validateEmail="";
}
%>

<form action="check-register">
<table border="1" style="background-color: black;">
<tr><td align="center">Please enter your registration information below:<br><br>
<table border="0">

<tr><td width="150px" align="right">
UserName:
</td><td width="150px">
<input type="text" name="username" style="width:90%"><br>
</td><td width="150px"><font color="red"><%out.println(validateUser); %></font></td></tr>

<tr><td align="right">
Password*:
</td><td>
<input type="password" name="password1" style="width:90%"><br>
</td><td><font color="red"><%out.println(validatePass); %></font></td></tr>

<tr><td  align="right">
*:
</td><td>
<input type="password" name="password2" style="width:90%"><br>
</td><td><font color="red"><%out.println(validatePass2); %></font></td></tr>

<tr><td align="right">
FirstName:
</td><td>
<input type="text" name="firstname" style="width:90%"><br>
</td><td><font color="red"><%out.println(validateFirst); %></font></td></tr>

<tr><td align="right">
LastName:
</td><td>
<input type="text" name="lastname" style="width:90%"><br>
</td><td><font color="red"><%out.println(validateLast); %></font></td></tr>

<tr><td align="right">
Email:
</td><td>
<input type="text" name="email" style="width:90%"><br>
</td><td><font color="red"><%out.println(validateEmail); %></font></td></tr>

<tr><td align="right">
</td>
<td align="center"><button type="submit">Submit</button></td>
<td></td></tr>

</table></td></tr></table>
</form>

</div>