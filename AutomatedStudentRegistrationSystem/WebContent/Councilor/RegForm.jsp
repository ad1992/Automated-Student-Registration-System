<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script src="/AutomatedStudentRegistrationSystem/Jquery/jquery-1.11.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body{
//background-image: url(/AutomatedStudentRegistrationSystem/images/newreg.jpg);
//background-repeat:no-repeat;
//background-size:cover;
background-color: #F7E3A1;


}
.outer{
border: 10px solid #F03F3F;
background-color: #F7E3A1;
height: 1300px; 
width:auto;	
}
.header{
text-align: center;
font-weight:bold;
color:#FFFFFF ;
background: #F49CF1;
height:90px;
width:auto;
line-height: 100px;
vertical-align: middle;
opacity:0.9;
margin: 4%;
}
/*body:AFTER{
opacity:0.4;
background-repeat: repeat;

}*/

input[type='text'] {
    border : 3px solid #cccccc;
    background: #C6A9E4;
    color: white;

}
 .box{
	border:3px;
	margin: 0px;
	text-align: left;	
	margin-top: 4%;
	margin-left: 10%;
	height: 1000px; 
	width:auto;	
	
	//background-color: white;
	
	
}
input[type=button]{
height:40px
}    
 .radio{
 margin-left:110px;
 position: relative;
 }   
#submit,#reset
{
border-radius: 25px;
background: #FAB469;
width: 250px;
height:40px;
color: white; 
font-weight: bold;
}

.error{
   	display: none;
    margin-left: 10px;
}       
 
.error_show{
    color: red;
    font-weight:lighter;
    font-style:oblique;
    margin-left: 10px;
    font-size: small;
}
input.invalid{
    border: 2px solid red;
}
 
input.valid{
    border: 2px solid green;
}
.sub{
position:relative;
//display: inline;
margin-top: 4%;
}

.innersub{
display:inline-table;
}
label{
color:blue;
font-family: fantasy;
font-weight: bold;}
.spandiv{
margin-left:40%;
}
</style>


<title>Registration Form</title>

<script type="text/javascript">
$(document).ready(function() {
	loadCourses();
	var watermarkEmail = 'Enter  your email address';
	var watermarkFirstName ='Enter your First Name';
	var watermarkLastName ='Enter your Last Name';
	var watermarkAddress = 'Enter  your Address';
	var watermarkContact = 'Enter  your Contact Number';
	var watermarkPincode = 'Enter your pin code';
	var watermarkCGPA = 'Enter your CGPA';
	//init, set watermark text and class
	$('#Email').val( watermarkEmail).addClass('watermark');
	$('#FirstName').val( watermarkFirstName).addClass('watermark');
	$('#LastName').val( watermarkLastName).addClass('watermark');
	$('#Address').val( watermarkAddress).addClass('watermark');
	$('#Contact').val( watermarkContact).addClass('watermark');
	$('#Pincode').val( watermarkPincode).addClass('watermark');
	$('#CGPA').val( watermarkCGPA).addClass('watermark');
	//if blur and no value inside, set watermark text and class again.
 	$('#Email').blur(function(){
  		if ($(this).val().length == 0){
    		$(this).val(watermarkEmail).addClass('watermark');
		}
 	});
 
	//if focus and text is watermrk, set it to empty and remove the watermark class
	$('#Email').focus(function(){
  		if ($(this).val() == watermarkEmail){
    		$(this).val('').removeClass('watermark');
		}
 	});
	$('#FirstName').blur(function(){
  		if ($(this).val().length == 0){
    		$(this).val( watermarkFirstName).addClass('watermark');
		}
 	});
 
	//if focus and text is watermrk, set it to empty and remove the watermark class
	$('#FirstName').focus(function(){
  		if ($(this).val() == watermarkFirstName){
    		$(this).val('').removeClass('watermark');
		}
 	});
	$('#LastName').blur(function(){
  		if ($(this).val().length == 0){
    		$(this).val(watermarkLastName).addClass('watermark');
		}
 	});
 
	//if focus and text is watermrk, set it to empty and remove the watermark class
	$('#LastName').focus(function(){
  		if ($(this).val() == watermarkLastName){
    		$(this).val('').removeClass('watermark');
		}
 	});
	$('#Address').blur(function(){
  		if ($(this).val().length == 0){
    		$(this).val(watermarkAddress).addClass('watermark');
		}
 	});
 
	//if focus and text is watermrk, set it to empty and remove the watermark class
	$('#Address').focus(function(){
  		if ($(this).val() == watermarkAddress){
    		$(this).val('').removeClass('watermark');
		}
 	});
	$('#Contact').blur(function(){
  		if ($(this).val().length == 0){
    		$(this).val(watermarkContact).addClass('watermark');
		}
 	});
 
	//if focus and text is watermrk, set it to empty and remove the watermark class
	$('#Contact').focus(function(){
  		if ($(this).val() == watermarkContact){
    		$(this).val('').removeClass('watermark');
		}
 	});
	$('#Pincode').blur(function(){
  		if ($(this).val().length == 0){
    		$(this).val(watermarkPincode).addClass('watermark');
		}
 	});
 
	//if focus and text is watermrk, set it to empty and remove the watermark class
	$('#Pincode').focus(function(){
  		if ($(this).val() == watermarkPincode){
    		$(this).val('').removeClass('watermark');
		}
 	});
	$('#CGPA').blur(function(){
  		if ($(this).val().length == 0){
    		$(this).val(watermarkCGPA).addClass('watermark');
		}
 	});
 
	//if focus and text is watermrk, set it to empty and remove the watermark class
	$('#CGPA').focus(function(){
  		if ($(this).val() == watermarkCGPA){
    		$(this).val('').removeClass('watermark');
		}
 	});
	$('#FirstName').on('input', function() {
	    var input=$(this);
	    console.log(input);
	    var is_name=input.val();
	    if(is_name){
	    	
	    	input.removeClass("invalid").addClass("valid");
	    }
	    else{
	    	
	    	input.removeClass("valid").addClass("invalid");
	    	
	   	}
	});
	$('#LastName').on('input', function() {
	    var input=$(this);
	    console.log(input);
	    var is_name=input.val();
	    if(is_name){
	    	
	    	input.removeClass("invalid").addClass("valid");
	    }
	    else{
	    	
	    	input.removeClass("valid").addClass("invalid");
	    	
	   	}
	});
	$('#Contact').on('input',function() {
	    var input=$(this);		   
	    var re=/^[789]{1}\d{9}$/;
	    var isvalid=re.test(input.val());	   
	    console.log("mobile"+input.val().length);
	    if(isvalid){	    	
	    	input.removeClass("invalid").addClass("valid");
	    }
	    else{
	    	
	    	input.removeClass("valid").addClass("invalid");
	    	
	   	}
	});
	$('#Email').on('input', function() {
	    var input=$(this);
	    var re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+\.([a-zA-Z0-9-]){2,3}$/;
	    var is_email=re.test(input.val());
	    if(is_email){
	    	input.removeClass("invalid").addClass("valid");
	    }
	    else{
	    	input.removeClass("valid").addClass("invalid");
	   }
	});
	$('#Address').keyup(function(event) {
	    var input=$(this);
	    var message=$(this).val();
	    console.log(message);
	    if(message){
	    	input.removeClass("invalid").addClass("valid");
	    }
	    else{
	    	input.removeClass("valid").addClass("invalid");
	    }   
	});
	$('#Pincode').on('input', function() {
	    var input=$(this);
	    var re=/^\d+$/;
	    var isvalid=re.test(input.val());	   	   
	   
	    if(isvalid){
	    	
	    	input.removeClass("invalid").addClass("valid");
	    }
	    else{
	    	
	    	input.removeClass("valid").addClass("invalid");
	    	
	   	}
	});
	$('#CGPA').on('input', function() {
	    var input=$(this);
	    console.log(input);
	    var re=/^\d+(\.\d{1,3})?$/;
	    var isvalid=re.test(input.val());	   
	    if(isvalid){
	    	
	    	input.removeClass("invalid").addClass("valid");
	    }
	    else{
	    	
	    	input.removeClass("valid").addClass("invalid");
	    	
	   	}
	});
	
	$('#submit').click(function(event){
	    var form_data=$('#regform').serializeArray();
	   // console.log(form_data);
	    var error_free=true;
	    for (var input in form_data){
	        var element=$("#"+form_data[input]['name']);	
	        console.log(element.val());
	        var valid=element.hasClass("valid");
	       	var required=element.hasClass("required");
	       // console.log(valid+" "+element);
	        var error_element=$("span", element.parent());
	        if (required&&!valid){
	        	error_element.removeClass("error").addClass("error_show");
	        	error_free=false;
	        }
	        else if(!required&&element.val()==='none')
	        {
	        	error_element.removeClass("error").addClass("error_show");
	        	error_free=false;
	        }
	        else{
	        	error_element.removeClass("error_show").addClass("error");
	        }
	    }
	   	     
	    if (!error_free){
	    	 event.preventDefault(); 
	    	// alert("errors");
	    }
	    else{
	        alert('No errors: Form will be submitted');
	    }
	});
	
	$('#month').change(function(event) {
		var y = $("select#year").val();
		var m=$("select#month").val();
		console.log(y+" "+m);
		$.get('BirthDayServlet', {
			year:y,month:m
		}, function(res) {

		var select = $('#day');		
		select.find('option').remove();
	 	  $.each(res, function(index, value) {
		  $('<option>').val(value).text(value).appendTo(select);
	      });
		});
		}).change();
	$('#year').change(function(event) {
		var y = $("select#year").val();		
		console.log(y);
		$.get('BirthDayServlet', {
			year:y,
		}, function(res) {
		var select = $('#month');		
		select.find('option').remove();
		
	 	  $.each(res, function(index, value) {
		  $('<option>').val(index).text(value).appendTo(select);
	      });
		});
		$('#month').change();
		}).change();
	
	$('#reset').click(function(event){	
		
		$('.required').removeClass("invalid valid");
		$("span").removeClass("error_show").addClass("error");	
		$('#Email').val( watermarkEmail).addClass('watermark');
		$('#FirstName').val( watermarkFirstName).addClass('watermark');
		$('#LastName').val( watermarkLastName).addClass('watermark');
		$('#Address').val( watermarkAddress).addClass('watermark');
		$('#Contact').val( watermarkContact).addClass('watermark');
		$('#Pincode').val( watermarkPincode).addClass('watermark');
		$('#CGPA').val( watermarkCGPA).addClass('watermark');
		loadCourses();
		$('#Degree').prop('selectedIndex',0);
		$('#Stream').prop('selectedIndex',0);
		$('#year').val("Select Year");
		$('#year').change();
		$('input:radio[name=Gender]')[0].checked = true;
		
		});
		
		
		
	
});
function loadCourses(){
	$.get('All_Courses_Servlet',function(res){
		var select=$('#Course');
		select.find('option').remove();
		$.each(res, function(index, value) {
			  $('<option>').val(value).text(value).appendTo(select);
		      });
	});
}
</script>
</head>
<body>
<div class="outer">

<div class="header">
<a href='/AutomatedStudentRegistrationSystem/Welcome' title="Home"><img alt="" src="/AutomatedStudentRegistrationSystem/images/home.png" width="100" height="100" border="0" style="float:left;" ></a>
<h1 align="center">Welcome to the Registration Forum 2014</h1></div>


<div class="box">

<form id="regform" action="GenerateUnique" method ="POST" name="regform" onsubmit="return validateForm()">

<div class="sub" >

<div class="innersub">
<label for="FirstName">First Name :</label>
<input id="FirstName" class="required watermark" type="text" style="width: 250px;height:30px; margin-left:73px;" name="FirstName">
<div class="spandiv"><span class="error">This field is required</span></div>
</div>
<div class="innersub" style="margin-left:4%;">
<label for="LastName" >Last Name :</label>
<input type="text" class="required" id="LastName" name="LastName" style="width: 250px;height:30px; margin-left:70px;"/>
<div class="spandiv"><span class="error">This field is required</span></div>
</div>
</div>

<div class="sub">
<label for="Contact">Contact(+91)  :</label>
<input type="text" class="required" id="Contact" name="Contact" style="width: 250px;height:30px;margin-left:50px;"/>
<span class="error">Enter a valid 10 digit mobile number</span>
</div>

<div class="sub">
<label for="Address">Address :</label>
<input type="text" class="required" id="Address" name="Address"  style="width: 590px;height:30px;margin-left:93px;"/>
<span class="error">This field is required</span>
</div>

<div class="sub">
<label for="Pincode">Pincode :</label>
<input type="text" class="required" id="Pincode" name="Pincode"style="width: 130px;height:30px;margin-left:93px;"/>
<span class="error">Enter a valid pincode</span>
</div>

<div class="sub">
<label>Gender :</label>
<input type="radio" name="Gender" value ="M" checked="checked" style="margin-left:100px;"/>Male  
<input type="radio" name="Gender" value ="F" />Female 
<input type="radio" name="Gender" value ="O" />Other
</div>

<div class="sub">
<label for="Email">Email :</label>
<input type="text" class="required" id="Email" name="Email" style="width: 250px;height:30px;margin-left: 113px;"/>
<span class="error">A valid Email is required</span>
</div>

<div class="sub">
<label>Select Course :</label><select name="Course" id="Course"  style="margin-left:42px;" >
<option value="none">Select Course</option>
</select>
<span class="error">Select a course from the list</span>
</div >

<div class="sub">
<label>Birth Date :</label>
<select name="year" id="year"  style="margin-left:70px;">
<option >Select Year</option>
<%for(int i=1986;i<=1996;i++)
 {%>
	<option value="<%=i%>"><%=i%></option>
<%}%>	
</select>
<select name="month" id="month">
<option >Select Month</option>

</select>
<select name="day" id="day">
<option >Select Day</option>
</select>
<span class="error">Invalid Birth Date</span>
</div>
<div class="sub">
<div class="innersub">
<label>Highest Degree :</label>
<select name="Degree" id="Degree" style="margin-left:24px;">
<option value="none">select</option>
<option value="B.Tech">B.Tech</option>
<option value="B.E">B.E</option>
<option value="MCA">MCA</option>
<option value="BCA">BCA</option>
<option value="M.Tech">M.Tech</option>
<option value="Diploma">Diploma</option>
</select>
<div class="spandiv" style="margin-left:46.5%;"><span class="error">Select a Degree from the list</span></div>
</div>
<div class="innersub"  style="margin-left:28%;">
<label for="CGPA">CGPA :</label>
<input type="text" id="CGPA" name="CGPA" class="required" style="width:110px;height:30px;margin-left:48.1px "/>
<div class="spandiv"><span class="error">A valid CGPA is required</span></div>
</div>
</div>
<div class="sub">
<label>Stream :</label>
<select name="Stream" id="Stream" style="margin-left:94px; ">
<option value="none">Select</option>
<option value="Information Technology">Information Technology</option>
<option value="Computer Science And Engineering">Computer Science And Engineering</option>
<option value="Electronics And Instrumentation">Electronics And Instrumentation</option>
<option value="Electronics Communication And Engineering">Electronics Communication And Engineering</option>
<option value="Civil Engineering">Civil Engineering</option>
<option value="Leather Technology">Leather Technology</option>
<option value="Textile Technology">Textile Technology</option>
<option value="Electrical Engineering">Electrical Engineering</option>
</select>
<span class="error" >Select a Stream from the list</span>
</div>
<div class="sub">
<input type="submit" id="submit" name="Submit"  value="Submit"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" id="reset" name ="Reset" value="Reset"/>
</div>
</form>
</div>
</div>
</body>
</html>