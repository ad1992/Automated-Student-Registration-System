<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script src="/AutomatedStudentRegistrationSystem/Jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {		
	loadajax();
});
function loadajax(){
	var Name=getURL('name');
	var Id=getURL('id');	
	$('#hname').val(Name);
	$('#hid').val(Id);
	//console.log(results);	
	$.get('StudentBioDataServlet',{name:Name,id:Id},function(res){
		$.each(res, function(index, value) {
			  $('#id'+index).text(value);
			  if(index===2){
				$('#hcourse').val(value);
			  	console.log(value);
			  }
		      });
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body{
background-image: url(/AutomatedStudentRegistrationSystem/images/prof.jpg);
}
.box{
	border:4px solid #FCC306;
	margin: 0px;
	text-align: left;
	top: 55%;
	left: 25%;
	width:auto;
    height:auto;
    margin-top:4%;	
    margin-right:4%;
    margin-left:4%;
    margin-bottom:4%;
    background-color:#FCDD77;    
    opacity:0.6;
   
	
}
input[type=submit]
{
border-radius: 25px;
background: #FAB469;
width: 250px;
height:40px;
color: white; 
font-weight: bold;
}
.sub{
margin-left:5%;
color: blue;
margin-top: 4%;


}
.text{
color:blue;
font-weight: normal;
font-size: x-large;
width: auto;
display: inline;
//border:2px solid gray;

}
</style>
<title>Insert title here</title>
<script src="jquery-1.11.3.min.js"></script>
<script type="text/javascript">
</script>
</head>

<body>
<a href='/AutomatedStudentRegistrationSystem/Welcome' title="Home"><img alt="" src="/AutomatedStudentRegistrationSystem/images/home.png" width="100" height="100" border="0" ></a>
<form   method="post" action="DeleteStudentProfile">
<input type="submit" value="Delete this Profile" name="action"/>
<div class="box">
<input type="hidden" name="name" id="hname" >
<input type="hidden" name="id" id="hid" />
<input type="hidden" name="course" id="hcourse" />
<div class="sub">
Name :<div class="text" id="id0" style="margin-left: 10%;"></div>
</div>
<div class="sub">
Unique ID :<div class="text" id="id1" style="margin-left: 7%;"></div>
</div>
<div class="sub">
Registered in :<div class="text" id="id2" style="margin-left: 5%;"></div>
</div>
<div class="sub">
Address :<div class="text" id="id3" style="margin-left: 8%;margin-right:5%"></div> Pincode :<div class="text" id="id4" style="margin-left: 10%;  "></div>
</div>
<div class="sub">
Contact :<div class="text" id="id5" style="margin-left: 8%;"></div>
</div>
<div class="sub">
Email :<div class="text" id="id6" style="margin-left: 9%;"></div>
</div>
<div class="sub">
Birth Date :<div class="text" id="id7" style="margin-left: 6%;"></div>
</div>
<div class="sub">
Degree :<div class="text" id="id8" style="margin-left: 8%;"></div>
</div>
<div class="sub">
Stream :<div class="text" id="id9" style="margin-left: 8%;"></div>
</div>
<div class="sub">
cgpa :<div class="text" id="id10" style="margin-left: 9.5%;"></div>
</div>
<div class="sub" style="margin-bottom:4%;">
Gender :<div class="text"id="id11" style="margin-left: 8%;"></div>
</div>
</div>
</form>
</body>
</html>