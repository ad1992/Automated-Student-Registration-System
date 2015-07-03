<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>courseupdate</title>
</head>
<style>
.sub{
	font-weight:bold;
	color:teal;;
	width: auto;
	margin: 0px;
	text-align: left;
	position: fixed;
	top: 55%;
	left: 40%;
	margin-top: -12em;
	margin-left: -10em;
	
	
}
</style>
<%String coursename=(String)request.getAttribute("course");
coursename=coursename.toUpperCase();
String action=(String)request.getAttribute("action");%>
<body>
<div>
<a href='/AutomatedStudentRegistrationSystem/Welcome' title="Home"><img alt="" src="/AutomatedStudentRegistrationSystem/images/home.png" width="100" height="100" border="0" style="float:left;" ></a>
</div>
<div class="sub">
<h3>
<%if(action==null){%>
Error 400 Bad Request
<%}else if(action.equals("update")){%>
<%=coursename %> HAS BEEN UPDATED SUCCESSFULLY.THANK YOU
<%}else if(action.equals("new")){%>
<%=coursename %> HAS BEEN ADDED SUCCESSFULLY.THANK YOU
<%}else if(action.equals("delete")){%>
<%=coursename %> HAS BEEN DELETED SUCCESSFULLY.THANK YOU
<%}%>
</h3>
</div>
</body>
</html>