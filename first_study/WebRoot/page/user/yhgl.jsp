
<%@ page language="java" import="java.util.*,xx.collection.bean.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>查询用户列表</title>
 <link href="css/login_style.css" type="text/css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	
    <link rel="stylesheet" type="text/css" href="css/thickbox.css" />
	
	
  <script type='text/javascript'>
  var g=0;                  //修改的变量
  var arrays1=new Array();  //修改的变量
  var validator;
  var Type="";//角色指定的变量
  $(function(){
	   
		$('#test').datagrid({
			title:'查询&管理用户（查询显示数据！）',
			iconCls:'icon-save',
			width:900,
			height:465,
			pageSize:10,
			pageList:[10,20,30,40,50],
			nowrap: true,
			striped: true,
			url:'listUser',
			loadMsg:'处理中，请等待...',
			fit:true,
			fitColumns:true,
			frozenColumns:[[
			                {field:'ck',checkbox:true}
			                
						]],
			columns:[[
               
               {title:'用户ID',field:'userId',width:80,sortable:true},
			   {field:'userPw',title:'用户姓名',width:100},
				{field:'safeQuestion',title:'安全问题',width:200},
				{field:'safeAnswer',title:'安全答案',width:200},
				{field:'type',title:'类型',width:100},
				{field:'typeId',title:'类型Id',width:100}
			]],
			pagination:true,                             //显示分页
			rownumbers:true,                             //显示行号
			toolbar:[{
				text:'查询',
				iconCls:'icon-search',
				handler:select
			},'-',{
				text:'添加',
				iconCls:'icon-add',
				handler:add
			},'-',{
				
				text:'修改',
				iconCls:'icon-edit',
				handler:update
			},'-',{
				
				text:'取消登录权限',
				iconCls:'icon-cancel',
				handler:deleteRow
			},'-',{
				
				text:'角色指定',
				iconCls:'icon-remove',
				handler:toJuese
			},'-',{
				
				text:'查看详细信息',
				iconCls:'icon-ok',
				handler:ckxx
			}]
		});
		$('#test').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to} 共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
	
	});

  function select(){
	    $('#w').form('clear');
		$('#select').dialog('open');
		document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
    }
  function add(){
	    $("#safeQuestion1").val("");
	    $("#safeAnswer1").val("");
	    $("#userid1").val("");
	    $("#pass1").val("");
		$('#add').dialog('open');
		document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
		var us=true;
		$("#userid1").blur(function(){
		      var t=null;
			  var userid=$("#userid1").val();
			  if(userid.length>2){t=userid;}
			
	        $.ajax({
		         	  type: "post",
				      url:"RegUser.action",
				      dataType:"json",
				      data:{userid:t}, 
				      cache:false,
		              success:function (json){
		                if (json.tip) {
							$('#userid-hint').html("<span color='red'>  此ID不可用 </span>");
							
							}else{
								$('#userid-hint').html("<span >  </span>"); 
								
							}	   
		                 				                      
		               }
	         	});
	        
	    });

		validator=$("#a").validate({
			rules: {
			    "edu.userId":{
			        required:true,
			        minlength:3,
			        maxlength: 10
			    },
				"edu.userPw": {
					required: true,
					minlength: 6,
					maxlength: 20
				},
				"edu.safeQuestion":{
				   required:true,
				   minlength: 6,
				   maxlength: 200
				},
			    "edu.safeAnswer":{
			       required:true,
			       minlength: 6,
				   maxlength: 20
				   
			    },
			    "edu.type":{
			    	required:true
			    }
				
			},	
			messages: {
				
				"edu.userId":{
			        required:"登录 ID 不能为空",
			        minlength:"长度应大于  2",
			        maxlength:"2～10个字符,字母区分大小写"
			    },
				
				"edu.userPw": {
					required: "请输入您的密码",
					minlength: "密码至少6个字符长",
					maxlength:"6～20个字符,字母区分大小写"
				},
				
				"edu.safeQuestion":{
				    required:"请输入你的安全问题",
				    minlength:"6～200个字符,字母区分大小写",
				    maxlength:"6～200个字符,字母区分大小写"
				},
				"edu.safeAnswer":{
				    required:"请输入你的安全问题的答案" ,
				    minlength:"6～20个字符,字母区分大小写",
				    maxlength:"6～20个字符,字母区分大小写"
				},
				"edu.type":{
					required:"请选择类型" 
			    }
			 }	
		})


		
	    
		
	
	}
  function update(){
	   var selected = $('#test').datagrid('getSelections');  //获取所有选中行的数据
       if(selected.length!=0){
           $.messager.confirm('提示', '确认修改吗?', function(r){
								if (r){
								     $('#edit').dialog('open');
								     document.getElementById("ggggg3").style.visibility="visible";//注意：用$()方式不好用
									 $("#userid2").val(selected[0].userId);
									 $("#pass2").val(selected[0].userPw);
									 $("#safeQuestion2").val(selected[0].safeQuestion);
									 $("#safeAnswer2").val(selected[0].safeAnswer);
									 $("#type2").val(selected[0].type);
									 arrays1.length=0;
									 g=0;

									 if(selected.length==1){
										 $("#xiayige").html('<font>完成</font>');
									 }else{
										 $("#xiayige").html('<font>下一个</font>');
									 }

									 for(i=1;i<selected.length;i++){
										    arrays1.push(selected[i]);
								     }
									
								}
			})//
	     }else{$.messager.alert('警告','请选择将修改的数据！','warning');}
	}
  function toJuese(){
		
		var selected = $('#test').datagrid('getSelections');
		if(selected.length==1){
			    $('#rolesure').dialog('open');
			    document.getElementById("ggggg4").style.visibility="visible";//注意：用$()方式不好用
             $("#userid3").val(selected[0].userId);
             Type=selected[0].type;
			}else{$.messager.alert('警告','请选择一行数据','warning');}
	}
  function ckxx(){
		
		var selected = $('#test').datagrid('getSelections');
		if(selected.length==1){
			
			$.ajax({
                type:"POST",
                url:"listSingle.action",
                dataType:'json',
                data:{"UserId":selected[0].userId,"Type":selected[0].type},
                success:function(json){
                    if(json.tip=="student"){
                    	$('#w1').window('open');
                        if(json.tip2!="空"){
	                        	$("#studentuserinfouserId").html("<span>"+json.student.userinfo.userId+"</span>");$("#studentSName").html("<span>"+json.student.SName+"</span>"); $("#studentSNo").html("<span>"+json.student.SNo+"</span>"); $("#studentSSex").html("<span>"+json.student.SSex+"</span>"); 
	                        	$("#studentbjxxzyxxxuyanxymc").html("<span>"+json.student.bjxx.zyxx.xuyan.xymc+"</span>");$("#studentbjxxzyxxzymc").html("<span>"+json.student.bjxx.zyxx.zymc+"</span>"); $("#studentbjxxbjmc").html("<span>"+json.student.bjxx.bjmc+"</span>"); $("#studentxszw").html("<span>"+json.student.xszw+"</span>");   
	                        	$("#studenthandphone").html("<span>"+json.student.handphone+"</span>");$("#studentemail").html("<span>"+json.student.email+"</span>"); $("#studentrxny").html("<span>"+json.student.rxny+"</span>");  
	                        
                            }else{
                            	$("#studentuserinfouserId").html("<span>"+selected[0].userId+"</span>");$("#studentSName").html(""); $("#studentSNo").html(""); $("#studentSSex").html(""); 
	                        	$("#studentbjxxzyxxxuyanxymc").html("");$("#studentbjxxzyxxzymc").html(""); $("#studentbjxxbjmc").html(""); $("#studentxszw").html("");   
	                        	$("#studenthandphone").html("");$("#studentemail").html(""); $("#studentrxny").html("");  
	                        
                                }
                    	  
                    	
                    }else{
                        if(json.tip=="teacher"){
		                        	$('#w2').window('open');  
		                        	if(json.tip2!="空"){
			                        	$("#teacheruserinfouserId").html("<span>"+json.teacher.userinfo.userId+"</span>");$("#teacherjsxm").html("<span>"+json.teacher.jsxm+"</span>"); $("#teacherjsxb").html("<span>"+json.teacher.jsxb+"</span>"); $("#teachermzmzmc").html("<span>"+json.teacher.mz.mzmc+"</span>"); 
			                        	$("#teacherjsjg").html("<span>"+json.teacher.jsjg+"</span>");$("#teacherjsbh").html("<span>"+json.teacher.jsbh+"</span>"); $("#teacherxuyanxymc").html("<span>"+json.teacher.xuyan.xymc+"</span>"); $("#teacherjslblbmc").html("<span>"+json.teacher.jslb.lbmc+"</span>");   
			                        	$("#teacherjspy").html("<span>"+json.teacher.jspy+"</span>");$("#teacherjssfz").html("<span>"+json.teacher.jssfz+"</span>"); $("#teacher.zzmm").html("<span>"+json.teacher.zzmm+"</span>");  
			                        	$("#teacherdtsj").html("<span>"+json.teacher.dtsj+"</span>");$("#teacherrjny").html("<span>"+json.teacher.rjny+"</span>"); $("#teachercjgzsj").html("<span>"+json.teacher.cjgzsj+"</span>"); $("#teachersbd").html("<span>"+json.teacher.sbd+"</span>"); 
			                        	$("#teacherofficephone").html("<span>"+json.teacher.officephone+"</span>");$("#teacherhandphone").html("<span>"+json.teacher.handphone+"</span>"); $("#teacherhomephone").html("<span>"+json.teacher.homephone+"</span>"); $("#teacheremail").html("<span>"+json.teacher.email+"</span>");   
			                        	 
			                        
		                            }else{
		                            	$("#teacheruserinfouserId").html("<span>"+selected[0].userId+"</span>");$("#teacherjsxm").html(""); $("#teacherjsxb").html(""); $("#teachermzmzmc").html(""); 
			                        	$("#teacherjsjg").html("");$("#teacherjsbh").html(""); $("#teacherxuyanxymc").html(""); $("#teacherjslblbmc").html("");   
			                        	$("#teacherjspy").html("");$("#teacherjssfz").html(""); $("#teacher.zzmm").html("");  
			                        	$("#teacherdtsj").html("");$("#teacherrjny").html(""); $("#teachercjgzsj").html(""); $("#teachersbd").html(""); 
			                        	$("#teacherofficephone").html("");$("#teacherhandphone").html(""); $("#teacherhomephone").html(""); $("#teacheremail").html("");   
			                        	
		                                }
                            }
                    	    
                    }
                   
                }
          	})// 
			   
			}else{$.messager.alert('警告','请选择一行数据','warning');}
	}
//====================================	
	function deleteRow(index){
		            var arr=new Array();
		        	var selected = $('#test').datagrid('getSelections');    //获取第一个选中行的数据
		        	if(selected.length>=1){
		        	    $.each(selected,function(n,value){
                           arr.push(value.userId);
                        });
			        	$.messager.confirm('取消','确认取消用户Id:  " '+arr.toString()+' "的登录权限吗?',function(d){
			        	
			            	if(d){   
			            	        jQuery.ajaxSettings.traditional = true;   //关键，可传递数组
			            		    $.ajax({
				                      type:"POST",
				                      url:"delUser.action",
				                      dataType:'json',
				                      data:{userid:arr},
				                      success:function(json){
				                         if(json.tip=="true"){
				                            
				                             $.messager.alert('提示','取消权限成功！','info',function(){
				                            	 $('#test').datagrid('reload');
				                             })//
				                         }
				                      }
				                	})//
				               	
			        		  }
			        	 })//
			        }else{$.messager.alert('警告','请选择将取消权限的用户！','warning');}
		 };
	
  $(function(){
	     
		$('#select').dialog({
				buttons:[{
					text:'确定',
					iconCls:'icon-ok',
					handler:function(){
						       var userid=$("#u_serid").val();
							   var type=$("#t_ype").val();
							  
							   jQuery.ajax({
							      url:"select.action",
							      dataType:"json",
							      data:{"UserId":userid,"Type":type},
							      cache:false,
			                      success:function (json){
			                        
			                             $('#select').dialog('close');
			                             $('#test').datagrid('reload');
			                          
			                      }
							   })//
						 
					}
				},{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						$('#select').dialog('close');
					}
				}]
			});
		$('#add').dialog({
			buttons:[{
				text:'确定',
				iconCls:'icon-ok',
				handler:function(){
				   
				   if(validator.form()){
                           if($('#userid-hint').text()!="此ID不可用"){
                        	   jQuery.ajax({
							          type: "post",
								      url:"addUser.action",
								      data:$('#a').serialize(), 
								      cache:false,
				                      success:function (json){
				                         if(json.tip=="true"){
						                         $('#add').dialog('close');
					                             $.messager.alert('提示','添加成功！','info',function(){
					                            	 $('#test').datagrid('reload');
					                             });
					                            
				                          }   
				                         				                      
				                       }
								  })//
                           }
					   }     
				        
					 
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$('#add').dialog('close');
				}
			}]
		});
		//==============批量修改的校验====================
		
		validator=$("#e").validate({
				rules: {
				    "edu.userId":{
				        required:true,
				        minlength:3,
				        maxlength: 10
				    },
					"edu.userPw": {//此处对应的是“用户名”
						required: true,
						minlength: 2,
						maxlength: 20
					},
					"edu.safeQuestion":{
					   required:true,
					   minlength: 6,
					   maxlength: 200
					},
				    "edu.safeAnswer":{
				       required:true,
				       minlength: 6,
					   maxlength: 20
					   
				    },
				    "edu.type":{
				    	required:true
				    }
					
				},	
				messages: {
					
					"edu.userId":{
				        required:"登录 ID 不能为空",
				        minlength:"长度应大于  2",
				        maxlength:"2～10个字符,字母区分大小写"
				    },
					
					"edu.userPw": {
						required: "请输入您的密码",
						minlength: "密码至少6个字符长",
						maxlength:"6～20个字符,字母区分大小写"
					},
					
					"edu.safeQuestion":{
					    required:"请输入你的安全问题",
					    minlength:"6～200个字符,字母区分大小写",
					    maxlength:"6～200个字符,字母区分大小写"
					},
					"edu.safeAnswer":{
					    required:"请输入你的安全问题的答案" ,
					    minlength:"6～20个字符,字母区分大小写",
					    maxlength:"6～20个字符,字母区分大小写"
					},
					"edu.type":{
						required:"请选择类型" 
				    }
				 }	
			});
		
		

		$('#rolesure').dialog({
		    
			buttons:[{
				text:'确定',
				iconCls:'icon-ok',
				handler:function(){
				    var userid=$("#userid3").val();
				    var roleid=$("#roleid3").val();
				   
					jQuery.ajax({
					          type: "post",
						      url:"rolesure.action",
						      data:{"UserId":userid,"RoleId":roleid,"Type":Type}, 
						      cache:false,
		                      success:function (json){
		                         if(json.tip=="true"){
		                        	 $('#rolesure').dialog('close');
		                             $.messager.alert('提示','指定成功！','info',function(){
		                            	 $('#test').datagrid('reload');
		                             });
		                          }   
		                      }
				    })
				    
				}
			}]
		})

	});
     //===============批量修改=======================
	function updatexiayige(){
			
			if(validator.form()){
				jQuery.ajax({
			          type: "post",
				      url:"updatUser.action",
				      data:$('#e').serialize(), 
				      cache:false,
	                  success:function (json){
	                     if(json.tip=="true"){
	                    	 
	                      }   
	                  }
		        });
	            if(g<arrays1.length){
					
					$("#userid2").val(arrays1[g].userId);
					 $("#pass2").val(arrays1[g].userPw);
					 $("#safeQuestion2").val(arrays1[g].safeQuestion);
					 $("#safeAnswer2").val(arrays1[g].safeAnswer);
					 $("#type2").val(arrays1[g].type);
					 
					 if(g==arrays1.length-1){
						  $("#xiayige").html('<font>完成</font>');
					    }
				}else{
				     					     
	                 $.messager.alert('提示','修改完成！','info',function(){
	                	 $('#edit').dialog('close');
	                	 $("#xiayige").html('<font>下一个</font>');
	                	 $('#test').datagrid('reload');
	                 })
				  
				}
				g=g+1;	   
			 }

		}
	function closexiayige(){
		   $('#edit').dialog('close');
		}
  </script>
  <style type="text/css">
	  .high{
	    color:#F0000F;
	  }
	 
	</style>
</head>
<body >
  	
	
	   <table id="test"></table>
	
	
	<div id="select" class="easyui-dialog" closed="true"  title="用户查询" icon="icon-search" style="padding:0px;width:400px;height:200px;">
	   <div align="center" style="visibility:hidden;" id="ggggg1">
	   <s:form id="w" name="form"><table width="100%" height="70%" align="center" class="login_text">
	            <tr>
	              <td align="left">&nbsp;&nbsp;&nbsp;用户类型：<s:select id="t_ype" name="Type" list="leixing" listKey="key" listValue="value"></s:select></td>
	            </tr>
	             <tr>
	              <td align="left">&nbsp;&nbsp; <em class="high">*</em>&nbsp;<font style="font-size:12px;LINE-HEIGHT: 20px; FONT-FAMILY: Arial, Helvetica, sans-serif;font-style:italic;">可以仅根据&nbsp;"&nbsp;用户类型&nbsp;"&nbsp;查询</font>
	                                     </td>
	            </tr>
	            <tr>
	              <td align="left">&nbsp;&nbsp; 用 户 ID：<s:textfield id="u_serid" name="UserId" ></s:textfield>
	                                     </td>
	            </tr>
	            
	       </table></s:form>     
	    </div>
	         
	</div>
	<div id="add" class="easyui-dialog" closed="true" title="用户添加" icon="icon-add" style="padding:0px;width:570px;height:250px;">
	    <div align="center" style="visibility:hidden;" id="ggggg2">
	   <s:form id="a" name="form"><table width="100%" height="70%" align="center" class="login_text">
	       
	       
	            <tr>
	              <td align="left">&nbsp;&nbsp; 用 户 ID：<s:textfield id="userid1" name="edu.userId" ></s:textfield>
					        <span id="userid-hint" class="">&nbsp;</span></td>
	                                     
	            </tr>
	            <tr><td height="8px"></td></tr>
	            <tr>
	              <td align="left">&nbsp;&nbsp; 用户密码：<s:password id="pass1" label="密码" name="edu.userPw"></s:password></td>
	                                     
	            </tr> <tr><td height="8px"></td></tr>
	            <tr>
	              <td align="left">&nbsp;&nbsp; 安全问题：<s:textfield id="safeQuestion1" label="安全问题" name="edu.safeQuestion"></s:textfield></td>
	                                     
	            </tr> <tr><td height="8px"></td></tr>
	            <tr>
	              <td align="left">&nbsp;&nbsp; 安全答案：<s:textfield id="safeAnswer1" label="安全答案" name="edu.safeAnswer"></s:textfield></td>
	                                     
	            </tr> <tr><td height="8px"></td></tr>
	            <tr>
	              <td align="left">&nbsp;&nbsp;&nbsp;用户类型：<s:select id="type1" name="edu.type" list="leixing" listKey="key" listValue="value"></s:select></td>
	            </tr>
	      
	      
	       </table></s:form>     
	    </div>
	</div>
	
	<div id="edit" class="easyui-dialog" closed="true" title="用户修改" icon="icon-edit" style="padding:0px;width:570px;height:250px;">
	     <div align="center" style="visibility:hidden;" id="ggggg3">
	   <s:form id="e" name="form"> <table width="100%" height="70%" align="center" class="login_text">
	       
	       
	            <tr>
	              <td align="left">&nbsp;&nbsp; 用 户 ID：<s:textfield readonly="true" style="background-color:#EBEBE4" id="userid2" name="edu.userId" ></s:textfield>
					        <span id="userid-hint" class="">&nbsp;</span></td>
	                                     
	            </tr> <tr><td height="8px"></td></tr>
	            <tr>
	              <td align="left">&nbsp;&nbsp; 用户姓名：<s:textfield readonly="true" style="background-color:#EBEBE4" id="pass2" label="密码" ></s:textfield></td>
	                                     
	            </tr> <tr><td height="8px"></td></tr>
	            <tr>
	              <td align="left">&nbsp;&nbsp; 安全问题：<s:textfield id="safeQuestion2" label="安全问题" name="edu.safeQuestion"></s:textfield></td>
	                                     
	            </tr> <tr><td height="8px"></td></tr>
	            <tr>
	              <td align="left">&nbsp;&nbsp; 安全答案：<s:textfield id="safeAnswer2" label="安全答案" name="edu.safeAnswer"></s:textfield></td>
	                                     
	            </tr> <tr><td height="8px"></td></tr>
	            <tr>
	              <td align="left">&nbsp;&nbsp;&nbsp;用户类型：<s:select id="type2" name="edu.type" list="leixing" listKey="key" listValue="value"></s:select></td>
	            </tr>
	      
	       
	       </table></s:form>    
	       <div border="false" style="text-align:right;height:30px;line-height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="updatexiayige();"><span id="xiayige">下一个</span></a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closexiayige();">取消</a>
			</div>
	    </div>
	</div>
	<div id="rolesure" class="easyui-dialog" closed="true" title="角色指定" icon="icon-edit" style="padding:0px;width:370px;height:170px;">
	     <div align="center" style="visibility:hidden;" id="ggggg4">
	         <s:form id="r" name="form"><table width="100%" height="70%" align="center" class="login_text">
	       
	            <tr>
	              <td align="left"></td>
	            </tr>
	       <tr>
	              <td align="left"></td>
	            </tr>
	            <tr>
	              <td align="left">&nbsp;&nbsp; 用 户 ID：<s:textfield id="userid3" name="UserId" readonly="true" style="background-color:#EBEBE4"></s:textfield>
	                                     </td>
	            </tr>
	            <tr>
	              <td align="left"></td>
	            </tr>
	            <tr>
	              <td align="left">&nbsp;&nbsp;&nbsp;可选角色：<s:select id="roleid3" name="roleid" list="role" listKey="roleid" listValue="rolename"></s:select></td>
	            </tr>
	      
	      
	       </table></s:form>     
	    </div>
	</div>
	
	<div id="w1" class="easyui-window" title="学生详细信息" closed="true" iconCls="icon-save" style="width:800;height:450;padding:5px;background: #fafafa;" >
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:0px;background:#fff;border:1px solid #ccc;">
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
      <tr>
       
        <td valign="top"> <table width="100%" height="80%" border="1px" style= "BORDER-COLLAPSE:   collapse " bordercolor="#C6C6C6" cellspacing="0" cellpadding="0" class="login_txt">
							  <tr bgcolor="#EFEFEF">
							    <td height="44" colspan="4" align="center" id="login_logo2">显示学生详细信息</td>
							  </tr>
							  <tr>
							    <td width="16%" height="38" align="center">注册ID：</td>
							    <td width="34%" id="studentuserinfouserId">
							      </td>
							    <td width="16%" align="center">姓名：</td>
							    <td width="34%" id="studentSName" ></td>
							  </tr>
							  <tr bgcolor="#EFEFEF">
							    <td height="37" align="center">学号：</td>
							    <td id="studentSNo"></td>
							    <td align="center">性别：</td>
							    <td id="studentSSex"></td>
							  </tr>
							  <tr>
							     <td height="37" align="center">学院：</td>
							    <td id="studentbjxxzyxxxuyanxymc"></td>
							    <td align="center">专业：</td>
							    <td id="studentbjxxzyxxzymc"></td>
							  </tr>
							  <tr bgcolor="#EFEFEF">
							    <td height="37" align="center">班级名称：</td>
							    <td id="studentbjxxbjmc"></td>
							    <td align="center">职务：</td>
							    <td id="studentxszw"></td>
							   
							  </tr>
							  <tr>
							    <td height="36" align="center">手机：</td>
							    <td id="studenthandphone"></td>
							    <td align="center">E-mail：</td>
							    <td id="studentemail"></td>
							  </tr>
							  <tr bgcolor="#EFEFEF">
							    <td height="35" align="center">入学年月：</td>
							    <td colspan="3" id="studentrxny"></td>
							    </tr>
							</table></td>
        
      </tr>
    </table>
		
			</div>
			
		</div>
	</div>

<div id="w2" class="easyui-window" title="教师详细信息" iconCls="icon-save" closed="true" style="width:800;height:450;background: #fafafa;" >
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:0px;background:#fff;border:1px solid #ccc;">
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
      <tr>
       
        <td valign="top" > <table width="100%" height="90%" border="1" style= "BORDER-COLLAPSE:   collapse " bordercolor="#C6C6C6" cellspacing="0" cellpadding="0" class="login_txt">
			  <tr align="center" bgcolor="#EFEFEF">
			    <td height="44" colspan="4" id="login_logo2">显示教师详细信息</td>
			  </tr>
			  <tr>
			    <td width="16%" align="center">注册ID：</td>
			    <td width="34%"  id="teacheruserinfouserId"></td>
			    <td width="16%" align="center">姓名：</td>
			    <td width="34%" id="teacherjsxm"></td>
			  </tr>
			  <tr bgcolor="#EFEFEF">
			    <td align="center">性别：</td>
			    <td id="teacherjsxb"></td>
			    <td align="center">教师照片：</td>
			    <td>浏览照片图标<img src="images/role/icon.gif" align="bottom"/></td>
			  </tr>
			  <tr>
			    <td align="center">民族：</td>
			    <td id="teachermzmzmc"></td>
			    <td align="center">籍贯：</td>
			    <td id="teacherjsjg"></td>
			  </tr>
			  <tr bgcolor="#EFEFEF">
			    <td align="center">教师编号：</td>
			    <td id="teacherjsbh"></td>
			    <td align="center">所属学院：</td>
			    <td id="teacherxuyanxymc"></td>
			  </tr>
			  <tr>
			    <td align="center">教师类别：</td>
			    <td id="teacherjslblbmc"></td>
			    <td align="center">拼音：</td>
			    <td id="teacherjspy"></td>
			  </tr>
			  <tr bgcolor="#EFEFEF">
			    <td align="center">身份证号：</td>
			    <td id="teacherjssfz"></td>
			    <td align="center">政治面貌：</td>
			    <td id="teacherzzmm"></td>
			  </tr>
			  <tr>
			    <td align="center">党团时间：</td>
			    <td id="teacherdtsj"></td>
			    <td align="center">任教年月：</td>
			    <td id="teacherrjny"></td>
			  </tr>
			  <tr bgcolor="#EFEFEF">
			    <td align="center">参加工作时间：</td>
			    <td id="teachercjgzsj"></td>
			    <td align="center">硕博导：</td>
			    <td id="teachersbd"></td>
			  </tr>
			  <tr>
			    <td align="center">办公室电话：</td>
			    <td  id="teacherofficephone"></td>
			    <td align="center">手机：</td>
			    <td id="teacherhandphone"></td>
			  </tr>
			  <tr bgcolor="#EFEFEF">
			    <td align="center" >家庭电话：</td>
			    <td  id="teacherhomephone"></td>
			    <td align="center">电子邮箱：</td>
			    <td id="teacheremail"></td>
			  </tr>
			 
			</table></td>
        
      </tr>
    </table>
		
			</div>
			
		</div>
	</div>
	
</body>
</html>