$(document).ready(function(){
	var yuliu=$("#sno").val();
	$('.easyui-datebox').datebox({
		
		  formatter:function(date){
					   var y = date.getFullYear();
					   var m = date.getMonth()+1;
					   var d = date.getDate();
					   
					   return y+'-'+m+'-'+d;
					   
		  }
		 });
	$(".datebox :text").attr("readonly","readonly");
	
	
	$('#nextWindows').click(function(){
		var us=true;
		var js=true;
		$('#test').window('open');
		document.getElementById("ggggg").style.visibility="visible";//注意：用$()方式不好用
		var validator=$("#allSS").validate({
			
			rules: {
			    "student.userinfo.userId":{
			        required:true,
			        minlength:3
			    },
			    
			    "student.SNo": {
					required: true,
					number:true,
					minlength:13,
					maxlength:13
				},
				"student.xszw": {
					//required: true,
					minlength: 2
					
				},
				"student.SName":{
				   required:true,
				   minlength: 2,
				   maxlength:6
				   
				},
				"student.email":{
				   //required:true,
				   email:true
				  
				},
				"student.SSex":{
				   required:true
				},
				"student.handphone":{
				   //required:true,
				   number:true,
				   minlength: 11,
				   maxlength: 11
				},
				"student.rxny":{
				   //required:true,
			       dateISO:true
			      
			    }
				
			},	
			messages: {
				
				"student.userinfo.userId":{
			        required:" 登录 ID 不能为空",
			        minlength:" 长度应大于  2"
			    },
				
				"student.SNo": {
					required: " 请输入您的学号",
					number:" 必须是数字",
					minlength: " 学号长度13位",
					maxlength:" 学号长度13位"
				} ,
				"student.xszw":{
				    //required:" 请输入您的职位",
				    minlength:" 至少2个字符"
				   
				},
				"student.SName":{
				   required:" 请输入您的名字",
				   minlength: " 至少2个字符",
				   maxlength:" 至多6个字符"
				},
				"student.email":{
					//required:" 请输入您的email",
				   email:" 请正确输入email地址"
				  
				},
				"student.SSex":{
				   required:" 请选择您的性别"
				},
				"student.handphone":{
				   
				   number:" 必须是0~9阿拉伯数字",
				   minlength: " 长度是11位",
				   maxlength: " 长度是11位"
				},
				"student.rxny":{
					//required:" 请输入您的入学年月",
				    dateISO:" 例如：2009-1-1"
				}
			 }	
		});
		    
			$("#submit").click(function(){
				      var tx=true;var tz=true;var tb=true;
				      var tempx=$("#bindX").find("option:selected").val();
				      var tempz=$("#bindZ").find("option:selected").val();
				      var tempb=$("#bjbh").find("option:selected").val();
				      
				      if(tempx.length==0){
				    	  $('#hintx').html("<span ><font color=red>请选择您的学院</font></span>");tx=false;
				      }
				      if(tempz.length==0){
				    	  $('#hintz').html("<span ><font color=red>请选择您的学院</font></span>");tz=false;
				      }
				      if(tempb.length==0){
				    	  $('#hintb').html("<span ><font color=red>请选择您的学院</font></span>");tb=false;
				      }
			       	 if(validator.form()){
				       	 
					        	 if(us&&tx&&tz&&tb&&js){
					        		
						         	$.ajax({
							         	  type: "post",
									      url:"updateUser.action",
									      dataType:"json",
									      data:$("#allSS").serialize(), 
									      cache:false,
							              success:function (json){
							                 if(json.tip=="true"){
							                         
							                         $.messager.alert('My Title','修改成功！','info',function(){
							                             location.href ="userAction.action";
							                             window.event.returnValue=false;
							                         });
							                        
							                  }   
							                 				                      
							               }
						         	});
					        	}
				       	
			       	 }
	       	 
	         });
		
			$("#userid").blur(function(){
			      var t=null;
				  var userid=$("#userid").val();
				  
		          $("#userid").value =1111;
				  if(userid.length>2){t=userid;}
				
				        $.ajax({
			         	  type: "post",
					      url:"RegUser1.action",
					      dataType:"json",
					      data:{userid:t}, 
					      cache:false,
			              success:function (json){
			                if (json.tip) {
								$('#userid-hint').html("&nbsp;&nbsp;&nbsp;&nbsp;<span class='formtips onError'><font color=red> 此ID不可用 </font></span>");
								us=false;
								}else{
								  $('#userid-hint').html("<span class='formtips onError'></span>");  
									us=true;
								}	   
			                 				                      
			               }
		         	   });
				 
		    });
			
			$("#sno").blur(function(){
			      
				  var stusno=$("#sno").val();
				  if(yuliu!=stusno){
					  $.ajax({
			         	  type: "post",
					      url:"RegStu.action",
					      dataType:"json",
					      data:{"sno":stusno}, 
					      cache:false,
			              success:function (json){
			                if (json.tip) {
								$('#stuhint').html("&nbsp;&nbsp;&nbsp;&nbsp;<span class='formtips onError'> <font color=red>此编号不可用 </font></span>");
								js=false;
								}else{
								  $('#stuhint').html("<span class='formtips onError'></span>");
								  
									js=true;
								}	   
			                 				                      
			               }
		         	   }); 
				  }else{
					  $('#stuhint').html("<span class='formtips onError'></span>");
					  
						js=true;
				  }			
				 
				
				  
		    });
		 
         
		
	});

});