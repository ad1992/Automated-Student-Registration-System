<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style>
.sub{
font-weight:bold;
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
<%String auth=(String)request.getAttribute("auth");%>	
<body>
<div>
<a href='/AutomatedStudentRegistrationSystem/Welcome' title="Home"><img alt="" src="/AutomatedStudentRegistrationSystem/images/home.png" width="100" height="100" border="0"></a>
</div>
<div class="sub">
<h1>
<%if(auth==null){%>
Error 400 Bad Request
<%}else if(auth.equals("yes")){%>
Student Profile Deleted Successfully
<%} %>
</h1>
</div>	
</body>
</html>