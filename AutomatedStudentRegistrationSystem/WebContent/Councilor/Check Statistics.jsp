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
	$.post('/AutomatedStudentRegistrationSystem/Check_Statistics_Servlet',function(res){
		var txt="";
		console.log(res);
		$.each(res, function(index, value) {
			txt+="<tr><td>"+value.course+"</td><td>"+value.capacity+"</td><td>"+value.vacancy+
					"</td><td><a href=\"ListOfStudents.jsp?id="+value.cid+"\">"+value.enroll+"</a></td></tr>";
			
		});
		$("#table").append(txt);
	});
}
</script>
<title>Statistics</title>
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
.data{
 border:2px;
 width:800px;
 height:auto;
 }
 .data td{
 padding:7px;
 border:#4e95f4 1px solid;
 color: #8F8888;
 font-weight: bold;
 text-align: center;
 }
.data tr:nth-child(odd){ 
		background: #b8d1f3;
}
	/*  Define the background color for all the EVEN background rows  */
.data tr:nth-child(even){
		background: #dae5f4;
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
text-align:center;
font-weight:bold;
color:#FFFFFF ;
background: #6589ED;
height:90px;
width:auto;
line-height: 50px;
vertical-align: middle;
opacity:0.5;
border:solid 4px #124DEF;

}
a{
text-decoration:none;
color: #8F8888;
font-weight: bold;

}
a:HOVER {
	color:#2020FA;
}
</style>
<body>
<div class="header">
<a href='/AutomatedStudentRegistrationSystem/Welcome' title="Home"><img alt="" src="/AutomatedStudentRegistrationSystem/images/home.png" width="100" height="100" border="0" style="float:left;" ></a>
<h1>Latest Statistics</h1></div>		
		<div class="box">
			
			<table class="data" id="table" >
				<tr>
					<th>Courses</th>
					<th>Capacity</th>
					<th>Vacancy</th>
					<th>Registered</th>
				</tr>					
			</table>
			</div>
	
</body>
</html>