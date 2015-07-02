<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.sql.Connection,
 java.sql.DriverManager,
 java.sql.ResultSet,java.sql.Statement,java.sql.SQLException"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%! Connection con;Statement stmt; String sql; ResultSet rs;%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<style>
body{
background-image: url(/AutomatedStudentRegistrationSystem/images/homepage.jpeg);
background-size:cover;
background-repeat:no-repeat;
}
h1{
text-align: center;
font-weight:bold;;
}
.header{
text-align: center;
font-weight:bold;
color:#ffffff ;
background: #F38D6B;
opacity:0.9;
width:auto;
line-height: 60px;
vertical-align: middle;
//margin-right: 6%;
//margin-left: 4%;
margin-bottom: 4%;
margin-top:2%;
border: 4px solid #FF8000;
}
input[type=button]{
height:40px;
width:300px;
background:#DCE21C;
opacity:0.6;
font-weight: bold;


}

.sub{

/* margin-top: 2%; */
}
.innersub{
margin:2%;

}
</style>
<%
String user=(String)request.getAttribute("USERNAME");
System.out.println(user);
%>
<body>
<div class="header">
<a href='/AutomatedStudentRegistrationSystem/InvalidateSession' id="logout" title="Log out"><img alt="" src="images/log_off.png" width="100" height="100" border="0" style="float:right;" ></a>
<h1>
Welcome to your profile <%=user%></h1>
</div>
<div>
<div class="innersub"><input type="Button" value="Check Statistics" onclick="parent.location='Registrar/Check Statistics.jsp'"></div>
<div class="innersub"><input type="Button" value="Add New Course" onclick="parent.location='Registrar/Add Course.html'"></div>

</div>
</body>
</html>