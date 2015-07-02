<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style>
h3{
text-align: center;
font-weight;bolder;
color:#F179AB;
text-shadow: blue;
}</style>
<%String uniqueid=(String)request.getAttribute("UNIQUEID");
String error=(String)request.getAttribute("error");
String course=(String)request.getAttribute("course");
%>
<body>
<a href='/AutomatedStudentRegistrationSystem/Welcome' title="Home"><img alt="" src="/AutomatedStudentRegistrationSystem/images/home.png" width="100" height="100" border="0" ></a>
<h3>
Congratulations you have successfully registered in <%=course%>.Kindly note your UNIQUE ID <%=uniqueid %>
</h3>
</body>
</html>