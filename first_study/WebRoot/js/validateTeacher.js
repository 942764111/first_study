$(document).ready(function(){
	var yuliu=$("#teajsbh").val();
	var yuliu2=$("#userid").val();
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
		var validator=$("#allPP").validate({
			rules: {
			"teacher.userinfo.userId":{
			        required:true,
			        minlength:3,
			        maxlength:10
			    },
			    "teacher.jsbh":{
			        required:true,
			        minlength:3,
			        maxlength:20
			    },
			    
				"teacher.jsxm":{
					required:true,
		           minlength: 2,
				   maxlength:50
				   
				},
				"teacher.mz.mzbh":{
				  required:true
				},
				"teacher.xuyan.xybh":{
				  required:true
				},
				"teacher.jslb.jslb":{
				 required:true
				},
				"teacher.jspy":{
					//required:true,
					minlength: 2,
					maxlength:3
				  
				},
				"teacher.jsxb":{
					required:true
				},
				"teacher.jsjg":{
					//required:true,
					   minlength: 2,
					   maxlength: 10
					},
		        "teacher.zzmm":{
						required:true
					},
						
				"teacher.dtsj":{
				   //required:true,
				   dateISO:true
				},
				"teacher.rjny":{
					//required:true,
					 dateISO:true
			     },
			    "teacher.cjgzsj":{
			    	// required:true,
			    	 dateISO:true
					},
					"teacher.sbd":{
						//required:true,
					       minlength: 2,
						   maxlength: 8
						},
						"teacher.jssfz":{
							//required:true,
				               minlength: 10,
							   maxlength: 18
							},
							"teacher.homephone":{
								//required:true,
								   number:true,
								   minlength: 7,
								   maxlength: 12
								},
								"teacher.officephone":{
									
						               number:true,
									   minlength: 7,
									   maxlength: 12
									},
									"teacher.handphone":{
										//required:true,
								           number:true,
										   minlength: 11,
										   maxlength: 11
										},
										"teacher.email":{
											required:true,
									           email:true
										}
			},	
			messages: {
				
			"teacher.userinfo.userId":{
	        required:" 请输入您的用户ID",
	        minlength:" 至少为3个字符",
	        maxlength:" 至多为10个字符"
		    },
		    "teacher.jsbh":{
		        required:" 请输入您的教师编号",
		        minlength:" 至少为3个字符",
		        maxlength:" 至多为20个字符"
		    },
		    
			"teacher.jsxm":{
		    	 required:" 不能为空",
			   minlength: " 至少为2个字符",
			   maxlength:" 至多为50个字符"
			},
			"teacher.mz.mzbh":{
			  required:"请选择民族"
			},
			"teacher.xuyan.xybh":{
			  required:"请选择学院"
			},
			"teacher.jslb.jslb":{
			  required:"请选择类别"
			},
			"teacher.jspy":{
				//required:" 不能为空",
			    minlength:" 至少为2个字符",
				maxlength:" 至多为3个字符"
			},
			"teacher.jsxb":{
				required:" 不能为空"
			},
			"teacher.jsjg":{
				//required:" 不能为空",
				  minlength: " 至少为2个字符",
				   maxlength: " 至多为10个字符"
				},
	        "teacher.zzmm":{
					required:" 不能为空"
				},
					
			"teacher.dtsj":{
			   //required:" 不能为空",
			   dateISO:" 例如：2009-1-1"
			},
			"teacher.rjny":{
				//required:" 不能为空",
				 dateISO:" 例如：2009-1-1"
		    },
		    "teacher.cjgzsj":{
		    	 //required:" 不能为空",
		    	 dateISO:" 例如：2009-1-1"
				},
				"teacher.sbd":{
					 // required:" 不能为空", 
					   minlength: " 至少为2个字符",
					   maxlength: " 至多为8个字符"
					},
					"teacher.jssfz":{
						  // required:" 不能为空",
					       minlength: " 长度为10个字符",
						   maxlength: " 长度为18个字符"
						},
						"teacher.homephone":{
							 // required:" 不能为空",
							   number:" 必须是0~9阿拉伯数字",
							   minlength: " 至少为7个数字",
							   maxlength: " 至多为12个数字"
							},
							"teacher.officephone":{
								   
								   number:" 必须是0~9阿拉伯数字",
								   minlength: " 至少为7个数字",
								   maxlength:" 至多为12个数字"
								},
								"teacher.handphone":{
									  //required:" 不能为空",
									   number:" 必须是0~9阿拉伯数字",
									   minlength: " 长度为11",
									   maxlength: " 长度为11"
									},
									"teacher.email":{
										   required:" 不能为空",
										   email:" 请您输入正确的email地址"
										}
			 }	
		});
	
         $('#submit').click(function(){
        	
			 if(validator.form()){
		       	 
			        	 if(us&&js){
			        		
				         	$.ajax({
					         	  type: "post",
							      url:"updateUser.action",
							      dataType:"json",
							      data:$("#allPP").serialize(), 
							      cache:false,
					              success:function (json){
					                 if(json.tip=="true"){
					                         
					                         $.messager.alert('提示','修改成功！','info',function(){
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
								$('#userid-hint').html("&nbsp;&nbsp;&nbsp;&nbsp;<span class='formtips onError'> <font color=red>此ID不可用 </font></span>");
								us=false;
								}else{
								  $('#userid-hint').html("<span class='formtips onError'></span>");
								  if(yuliu2!=userid){
									  $.messager.alert('提示','您修改了用户ID，需要重新上传头像！','info');
								  }
								  
									us=true;
								}	   
			                 				                      
			               }
		         	   }); 
				
				  
		    });
         $("#teajsbh").blur(function(){
		      
			  var teajsbh=$("#teajsbh").val();
			  	if(yuliu!=teajsbh){
			  		$.ajax({
			         	  type: "post",
					      url:"RegTea.action",
					      dataType:"json",
					      data:{"jsbh":teajsbh,"type":"2"}, 
					      cache:false,
			              success:function (json){
			                if (json.tip) {
								$('#teahint').html("&nbsp;&nbsp;&nbsp;&nbsp;<span class='formtips onError'> <font color=red>此编号不可用 </font></span>");
								js=false;
								}else{
								  $('#teahint').html("<span class='formtips onError'></span>");
									js=true;
								}	   
			                 				                      
			               }
		         	   });
			  	}else{
			  		$('#teahint').html("<span class='formtips onError'></span>");
			  		js=true;
			  	}		
			  
			
			  
	    });
		
	});
	
	
	
	
});
$(document).ready(function(){
	 $.ajax({
		    url:"listmz",
		    type:"post",
		    dataType:"json",
		    success:listmz
		   });
	 $.ajax({
		    url:"listxy",
		    type:"post",
		    dataType:"json",
		    success:listxy
		   });
	 $.ajax({
		    url:"listlb",
		    type:"post",
		    dataType:"json",
		    success:listlb
		   })
});
function listmz(json){
	data=(json.m);
	 
	 var temp=$("#bindMZ").find("option:selected").val();
	 $.each(json.m,function(n,value){
		 
		 if(temp!=value.mzbh){
			 $("#bindMZ").append("<option value=\""+value.mzbh+"\">"+value.mzmc+"</option>");
		 }
		 
	  });
}
function listxy(json){
	data=(json.x);
	 
	 var temp=$("#bindXY").find("option:selected").val();
	 $.each(json.x,function(n,value){
		 
		 if(temp!=value.xybh){
			 $("#bindXY").append("<option value=\""+value.xybh+"\">"+value.xymc+"</option>");
		 }
		 
	  });
}
function listlb(json){
	data=(json.l);
	 
	 var temp=$("#bindLB").find("option:selected").val();
	 $.each(json.l,function(n,value){
		 
		 if(temp!=value.jslb){
			 $("#bindLB").append("<option value=\""+value.jslb+"\">"+value.lbmc+"</option>");
		 }
		 
	  });
}