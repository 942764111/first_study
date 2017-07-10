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
		document.getElementById("ggggg").style.visibility="visible";//ע�⣺��$()��ʽ������
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
	        required:" �����������û�ID",
	        minlength:" ����Ϊ3���ַ�",
	        maxlength:" ����Ϊ10���ַ�"
		    },
		    "teacher.jsbh":{
		        required:" ���������Ľ�ʦ���",
		        minlength:" ����Ϊ3���ַ�",
		        maxlength:" ����Ϊ20���ַ�"
		    },
		    
			"teacher.jsxm":{
		    	 required:" ����Ϊ��",
			   minlength: " ����Ϊ2���ַ�",
			   maxlength:" ����Ϊ50���ַ�"
			},
			"teacher.mz.mzbh":{
			  required:"��ѡ������"
			},
			"teacher.xuyan.xybh":{
			  required:"��ѡ��ѧԺ"
			},
			"teacher.jslb.jslb":{
			  required:"��ѡ�����"
			},
			"teacher.jspy":{
				//required:" ����Ϊ��",
			    minlength:" ����Ϊ2���ַ�",
				maxlength:" ����Ϊ3���ַ�"
			},
			"teacher.jsxb":{
				required:" ����Ϊ��"
			},
			"teacher.jsjg":{
				//required:" ����Ϊ��",
				  minlength: " ����Ϊ2���ַ�",
				   maxlength: " ����Ϊ10���ַ�"
				},
	        "teacher.zzmm":{
					required:" ����Ϊ��"
				},
					
			"teacher.dtsj":{
			   //required:" ����Ϊ��",
			   dateISO:" ���磺2009-1-1"
			},
			"teacher.rjny":{
				//required:" ����Ϊ��",
				 dateISO:" ���磺2009-1-1"
		    },
		    "teacher.cjgzsj":{
		    	 //required:" ����Ϊ��",
		    	 dateISO:" ���磺2009-1-1"
				},
				"teacher.sbd":{
					 // required:" ����Ϊ��", 
					   minlength: " ����Ϊ2���ַ�",
					   maxlength: " ����Ϊ8���ַ�"
					},
					"teacher.jssfz":{
						  // required:" ����Ϊ��",
					       minlength: " ����Ϊ10���ַ�",
						   maxlength: " ����Ϊ18���ַ�"
						},
						"teacher.homephone":{
							 // required:" ����Ϊ��",
							   number:" ������0~9����������",
							   minlength: " ����Ϊ7������",
							   maxlength: " ����Ϊ12������"
							},
							"teacher.officephone":{
								   
								   number:" ������0~9����������",
								   minlength: " ����Ϊ7������",
								   maxlength:" ����Ϊ12������"
								},
								"teacher.handphone":{
									  //required:" ����Ϊ��",
									   number:" ������0~9����������",
									   minlength: " ����Ϊ11",
									   maxlength: " ����Ϊ11"
									},
									"teacher.email":{
										   required:" ����Ϊ��",
										   email:" ����������ȷ��email��ַ"
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
					                         
					                         $.messager.alert('��ʾ','�޸ĳɹ���','info',function(){
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
								$('#userid-hint').html("&nbsp;&nbsp;&nbsp;&nbsp;<span class='formtips onError'> <font color=red>��ID������ </font></span>");
								us=false;
								}else{
								  $('#userid-hint').html("<span class='formtips onError'></span>");
								  if(yuliu2!=userid){
									  $.messager.alert('��ʾ','���޸����û�ID����Ҫ�����ϴ�ͷ��','info');
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
								$('#teahint').html("&nbsp;&nbsp;&nbsp;&nbsp;<span class='formtips onError'> <font color=red>�˱�Ų����� </font></span>");
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