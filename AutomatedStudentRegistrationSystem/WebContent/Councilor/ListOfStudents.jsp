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
function loadajax()
{
	var cid=getURL('id');
	$.get('/AutomatedStudentRegistrationSystem/List_Of_Students_Servlet',{id:cid},function(res){
		var txt="";
		var len=res.length;
		console.log(len);
		if(len===0)
		{
			
			$('#text').append("Sorry No Student registered in this course!!");
			$('.None').show();
		}
		else
		{
			var course="";
			txt+="<tr><th>Student ID</th><th>Name</th>/tr>";
			
			$.each(res, function(index, value) {
			course=value.CourseName;
			console.log("course="+course);
			txt+="<tr><td>"+value.Id+"</td><td>"+value.Name+"</td></tr>";
			});			
			$("#table").append(txt);
			$('#head').append("Students Registered in "+course);
			$('#divhead').addClass('header');
		}
	});
}
function getURL(para)
{
	
	var url=window.location.href;
	var results = new RegExp('[\?&]'+para + '=([^&#]*)').exec(url);
	results[1]=decodeURIComponent(results[1]);
	//console.log(results[1]);
	return results[1];
}
</script>
<title>Registration form</title>
</head>
<style>
body{
background-image: url(/AutomatedStudentRegistrationSystem/images/table1.jpg);
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
		background: #C58CFF;
}
	/*  Define the background color for all the EVEN background rows  */
.data tr:nth-child(even){
		background: #C4A7E1;
}
.data th{
 text-align: center;
 border: 2px solid red;
 background-color:#EE8C8C; 
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
background: #3BC1BD;
height:90px;
width:auto;
line-height: 50px;
vertical-align: middle;
border:4px solid #096966;
}
</style>
<body>
<div id="divhead">
<a href='/AutomatedStudentRegistrationSystem/Welcome' title="Home"><img alt="" src="/AutomatedStudentRegistrationSystem/images/home.png" width="100" height="100" border="0" style="float:left"></a><h1 id="head"></h1>
</div>
<div class="box">
	<table class="data" id="table">		
</table>
</div>
<div class="None"><h1 id="text"></h1></div>	
</body>
</html>