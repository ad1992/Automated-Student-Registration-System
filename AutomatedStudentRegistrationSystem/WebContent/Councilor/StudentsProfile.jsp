<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/AutomatedStudentRegistrationSystem/Jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {		
	loadajax();
});
function loadajax(){
	$.get('StudentProfileServlet',function(res){
		var txt="";
		console.log(res);
		var len=res.length;
		if(len==0)
		{
			$('#text').append("Sorry no students have been registered yet");
			$('.None').show();
		}
		else
		{
			txt+="<tr><th>Student ID</th><th>Name</th><th>Course</th></tr>";		
			$.each(res, function(index, value) {
				txt+="<tr><td>"+value.id+"</td><td><a href=\"StudentBioData.jsp?name="+value.Name+"&id="+value.id+"\">"+value.Name+"</a></td><td>"+value.Course+"</td></tr>";
			});
			 $("#table").append(txt);
		}
	});
}
</script>
<title>Students Profile</title>
</head>
<style>
body{
background-image: url(/AutomatedStudentRegistrationSystem/images/prof.jpg);
}
.box{

	width: auto;
	margin: 0px;
	text-align: left;
	position: fixed;
	top: 55%;
	left: 25%;
	width: 20em;
	height: 25em; 
	margin-top: -12em;
	margin-left: -10em;
	
}
.data{
 border:2px;
 width:800px;
 height:auto;
 }
 .data td{
 padding:7px; 
 border:#4e95f4 1px solid;
 color: white;
 font-weight: bold;
 }
.data tr:nth-child(odd){ 
		background: #F78181;
}
	/*  Define the background color for all the EVEN background rows  */
.data tr:nth-child(even){
		background: #F4C4C4;
}
.data th{
 text-align: center;
 border: 2px solid #2F64F5;
 background-color:#608AFC; 
 color: white;
 height: 50px;
 
 }
 label{
color:#ED7A7A;
font-family: fantasy;
font-weight: bold;
}
.header{
text-align: center;
font-weight:bold;
color:#FFFFFF ;
background: #B3E281;
height:90px;
width:auto;
line-height:50px;
vertical-align: middle;
opacity:0.9;
border:solid 4px #82E815;
}
.None{
	display: none;
	font-weight:bold;
	color:#ffffff;
	width: auto;
	margin: 0px;
	text-align:center;
	position: fixed;
	top: 55%;
	left: 40%;
	margin-top: -12em;
	margin-left: -10em;
	background-color:teal;
	line-height:50px;
	border:4px solid #096966;
	
}
a{
text-decoration:none;
color:#FFFFFF ;
font-weight: bold;

}
a:HOVER {
	color:#2020FA;
}
</style>
<%
%>
<body>
<div class="header">
<a href='/AutomatedStudentRegistrationSystem/Welcome' title="Home"><img alt="" src="/AutomatedStudentRegistrationSystem/images/home.png" width="100" height="100" border="0" style="float:left;" ></a>
<h1 align="center">List Of  Registered Students</h1></div>


<div class="box">
<table class="data" id="table">
</table>			
</div>
<div class="None"><h2 id="text"></h2></div>				
</body>
</html>