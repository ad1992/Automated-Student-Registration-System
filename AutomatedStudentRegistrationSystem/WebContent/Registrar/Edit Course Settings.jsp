<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.error{
   	display: none;
  //  margin-left: 10px;
    //z-index: -1;
}       
 
.error_show{
    color: red;
    font-weight:lighter;
    font-style:oblique;
   // margin-left: 10px;
   z-index: -1;
   
}
body{
background-image: url(/AutomatedStudentRegistrationSystem/images/addcourse.jpeg);
  
}
.editable{
background-color: #7805F2
}
.uneditable{
background-color: #C6A9E4;
}
.box{
	border:3px;
	margin: 0px;
	text-align: left;	
	margin:4%;	
	height: 40em; 
	width:900px;
	padding:  4%;
	background-color:#F1F3B0;
	border: 4px solid blue;
	
	
}
input[type=button]{

height:40px
}
input[type='text'],input[type='date'] {
    border : 3px solid #cccccc;
    background: #C6A9E4;
    color: white;
    width: 250px;
	height:30px

}
#submit,#clear,#delete
{
border-radius: 25px;
background: #FAB469;
width: 250px;
height:40px;
color: white; 
font-weight: bold;
}
.sub{
position:relative;
//display: inline;
margin-top: 4%;
margin-bottom: 4%;
}
label{
color:#FC6D6D;
font-family: fantasy;
font-weight: bold;
}
.header{
text-align: center;
font-weight:bold;
color:#FFFFFF ;
background:#F75A5A;
height:90px;
width:auto;
line-height: 50px;
vertical-align: middle;
opacity:0.5;
border:4px solid #F21616;
}
.spandiv{
margin-left:27%;
}
</style>
<title>Insert title here</title>
<link href="/AutomatedStudentRegistrationSystem/Jquery/jquery-ui.min.css" rel="stylesheet">
<script src="/AutomatedStudentRegistrationSystem/Jquery/jquery-1.11.3.min.js"></script>
<script src="/AutomatedStudentRegistrationSystem/Jquery/jquery-ui.min.css"></script>
<script src="/AutomatedStudentRegistrationSystem/Jquery/jquery-ui.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {	
	
	loadajax();
	initdatepicker();
	$('#submit').click(function(event){
		//$course=$('#Course_Name').attr("value");
		$('.Date').datepicker('enable');
	    var form_data=$('#updateform').serializeArray();
	  	console.log(form_data);
	    var error_free=true;
	    for (var input in form_data){
	    	var id=form_data[input]['name'];
	        var element=$("#"+id);	
	        var value=element.val();
	        console.log(value);
	        var error_element=$("span", element.parent());
	        if($.trim(value).length==0)
	        {
	        	error_free=false;
	        	error_element.removeClass("error").addClass("error_show");	        	
	        	
	        }
	        else if(id==="Course_Duration"||id==="Capacity")
	        {
	        	var re=/^\d+$/;
	        	var isvalid=re.test(value);
	        	if(!isvalid)
	        	{
	        		error_free=false;
	        		$('#'+"span"+id).text("This field contain invalid values");
		        	error_element.removeClass("error").addClass("error_show");	      
	        	}	
	        	  	
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
	        alert('No errors: Changes will be updated');
	    }  
	    
	});
	$('.EditSave').click(function(){
		$btnText = $(this).attr('value');
		$id=$(this).attr("id");		
		$idin=$id.substring(4,$id.length);	
		$idc="cancel"+$idin;	
		//alert(btnText);
		if($btnText == 'Edit')
	    {
			
	        $(this).val('Save');      
	        $('#'+$idin).prop("readonly", false);	    	       
	        $('#'+$idc).show();
	        $('#'+$idin).removeClass('uneditable').addClass('editable');
	       	if($('#'+$idin).hasClass('Date'))
	       	{
	       		$('#'+$idin).datepicker('enable');	       		
	       		
	       	}
	        
	    }	
	    else
	    {
			$(this).val('Edit');			
			$text=$('#'+$idin).val();	
			$prevtext=$('#'+$idin).attr("value");
			//alert($text1);
			if($.trim($text).length==0)
			{
				$text=$prevtext;
				//alert($text);
				$('#'+$idin).val($text);
			}
			$('#'+$idc).hide();
			//$('#'+$idin).val($text);
			$('#'+$idin).attr("value",$text);
			$('#'+$idin).prop("readonly", "readonly");
			$('#'+$idin).removeClass('editable').addClass('uneditable');
			if($('#'+$idin).hasClass('Date'))
	       	{
	       		$('#'+$idin).datepicker('disable');	       		
	       		
	       	}
			//$text=$('#'+$idin).attr("value");
			
	   }
	});
	$('.cancel').click(function(){
		$idc=$(this).attr("id");
		$idin=$idc.substring(6,$idc.length);	
		$id="edit"+$idin;	
		$text=$('#'+$idin).attr("value");
		//alert($text);
		$('#'+$idin).val($text);
	    $(this).hide();
	    $('#'+$id).val('Edit');
	    $('#'+$idin).prop("readonly", "readonly");
	    $('#'+$idin).removeClass('editable').addClass('uneditable');
	    if($('#'+$idin).hasClass('Date'))
       	{
       		$('#'+$idin).datepicker('disable');	       		
       		
       	}
	});
	
	
	$('#clear').click(function(){
		loadajax();
		$('#Course_Name,#Course_Duration,#Capacity').removeClass("invalid valid");
		$("span").removeClass("error_show").addClass("error");
	});
	
	
	
});
function loadajax()
{
	var cid=getURL('id');
	$.get('Edit_Course_Settings_Servlet',{id:cid},function(res){
		$.each(res, function(index, value) {
			console.log(value.capacity);
			$('#Course_Name').val(value.coursename).addClass('uneditable');
			$('#Course_Duration').val(value.duration).addClass('uneditable');
			$('#Start_Date').val(value.start_date).addClass('uneditable');
			$('#End_Date').val(value.end_date).addClass('uneditable');
			$('#Capacity').val(value.capacity).addClass('uneditable');
			$('#hidname').val(value.coursename);
			$('#hidid').val(cid);
			
			$('#Course_Name').attr("value",value.coursename);
			$('#Course_Duration').attr("value",value.duration);
			$('#Start_Date').attr("value",value.start_date);
			$('#End_Date').attr("value",value.end_date);
			$('#Capacity').attr("value",value.capacity);
			$('#hidname').attr("value",value.coursename);
			$('#hidid').attr("value",cid);
			
			
		});
		
	});
}
function initdatepicker(){
	$('.Date').datepicker({
		  dateFormat:'dd-mm-yy',
		  disabled: true
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
</head>
<body>
<a href='/AutomatedStudentRegistrationSystem/Welcome' title="Home"><img alt="" src="/AutomatedStudentRegistrationSystem/images/home.png" width="100" height="100" border="0" ></a>
<form  action="CourseUpdate" method="post" id="updateform" >
<input type="submit" value="Delete this course" name="action" id="delete"/>
<div class="box">
<input type="hidden" name="hidname" id="hidname" />
<input type="hidden" name="hidid" id="hidid" />
 
<div class="sub">
<label for="Course_Name">Name of the Course :</label>

<input type="text" id="Course_Name" name="Course_Name" readonly="readonly" style="width: 250px;height:30px; margin-left:45px;"/>
<input id="editCourse_Name"  class="EditSave" type="button" value="Edit" />
<input id="cancelCourse_Name"  class="cancel"   type="button" value="cancel" style="display:none;"  />
<div class="spandiv"><span class="error" >This field is required</span></div>
</div>

<div class="sub">
<label for="Course_Duration">Course Duration :</label>
<input type="text" id="Course_Duration" readonly="readonly" name="Course_Duration"  style="width: 250px;height:30px;margin-left:78px;"/>
<input id="editCourse_Duration"  class="EditSave" type="button" value="Edit"/>
<input id="cancelCourse_Duration"  class="cancel"  type="button" value="cancel" style="display:none;"  />
<div class="spandiv"><span class="error" id="spanCourse_Duration">This field is required</span></div>
</div>

<div class="sub">
<label for="Start_Date" >Starting Date :</label>
<input type="text" id="Start_Date" class="Date" readonly="readonly" name="Start_Date"  style="width: 250px;height:30px;margin-left: 99.5px;"/>
<input id="editStart_Date"  class="EditSave" type="button" value="Edit"/>
<input id="cancelStart_Date"  class="cancel"  type="button" value="cancel" style="display:none;"  />

</div>

<div class="sub">
<label for="End_Date" >Ending date :</label>
<input type="text" id="End_Date" class="Date" readonly="readonly" name="End_Date"  style="width: 250px;height:30px;margin-left: 113.5px;"/>
<input id="editEnd_Date"  class="EditSave" type="button" value="Edit"/>
<input id="cancelEnd_Date"  class="cancel"  type="button" value="cancel" style="display:none;"  />

</div>

<div class="sub">
<label for="Capacity">Capacity :</label><input type="text" id="Capacity" readonly="readonly" name="Capacity" style="width: 250px;height:30px;margin-left:145.5px;" />
<input id="editCapacity"  class="EditSave" type="button" value="Edit"/>
<input id="cancelCapacity"  class="cancel"  type="button" value="cancel" style="display:none;"  />
<div class="spandiv"><span class="error" id="spanCapacity">This field is required</span></div>

</div>

<input type="submit" id="submit" value="Update" name="action" style="width: 230px;height:40px;"/>
<input type="button" value="Clear" id="clear" style="width: 230px;height:40px;margin-left: 100px;" />

</div>
</form>

</body>
</html>