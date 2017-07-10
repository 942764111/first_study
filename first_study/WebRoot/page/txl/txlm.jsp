<%@ page language="java" import="javax.servlet.http.HttpSession" import="java.net.URLEncoder" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>好友管理</title>
    <link href="css/wjh_main.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css" charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css" charset="UTF-8">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js" charset="UTF-8"></script>

	<script>
	$.messager.defaults={ok:"确认",cancel:"取消"};

	function getWidth(percent){ 
	    return (document.body.clientWidth-25-11)*percent; 
	}

	//初始化添加好友表
	$(function(){
		$('#test1').datagrid({
			width:getWidth(1),
		//	title:'添加好友',
		//	height:380,
		    pageList:[10,15,20,25],
			striped: true,
			fitColumns:true,
			loadMsg:'装载数据......',
			idField:'userid',
			columns:[[
                    {title:'用户名称',field:'username',width:getWidth(0.35),align:'center'},
				    {title:'用户编号',field:'userid',width:getWidth(0.35),align:'center'},
				    {title:'身份',field:'shenfen',width:getWidth(0.2),align:'center'}
					]],
			pagination:true,
			rownumbers:true,
			toolbar:[{
				id:'btnadd1',
				text:'添加',
				iconCls:'icon-add',
				handler:function(){
				zhidinggroup();
				var selections = $('#test1').datagrid('getSelections');
				if(selections.length<=1){
					$('#next').linkbutton('disable');
				}else{
					$('#next').linkbutton('enable');
				}
				    batchupdate();
				    $('#urolename').empty();
				}
			},'-',{
				id:'btnquery1',
				text:'查询',
				iconCls:'icon-search',
				handler:function(){
					$('#query').window('open');
				}
			}]
		});
		 
	});		
	//初始化群组表
	$(function(){
		$('#test2').datagrid({
			title:'群组',
		//	iconCls:'icon-save',
		//	width:getWidth(1),
		//	height:380,
			striped: true,
			pagination:false,
			fitColumns:true,
			url:'json2.action',
			loadMsg:'装载数据......',
			idField:'groupname',
			onClickRow:function (){listmember();},
			columns:[[
		            {title:'群组名称',field:'groupname',width:getWidth(1),align:'center'}
					]],
			rownumbers:true,
			singleSelect:true,
			toolbar:[{
				id:'btnadd2',
				text:'新建群',
				iconCls:'icon-add',
				handler:function(){
				    $('#addgroup').window('open');
				}
			},'-',{
				id:'updategg',
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
				   var selected = $('#test2').datagrid('getSelections');
				   if(selected.length==1){ 
				    $('#groupnameupdate').val(selected[0].groupname);
				    $('#updateg').window('open');
				   }else{
					   $.messager.alert('警告','请选择一条数据','警告');
				   }
				}
			},'-',{
				id:'deleteg',
				text:'删除',
				iconCls:'icon-cut',
				handler:function(){
				deleteg();
				}
			}/*,'-',{
				id:'listmember',
				text:'成员',
				iconCls:'icon-search',
				handler:function(){
				listmember();
			}
				}*/]
		});
		
	});	
	//初始化成员表
	$(function(){
		$('#test3').datagrid({
			title:'成员',
		//	width:getWidth(1),
		//	height:380,
			striped: true,
			pagination:false, 
			remoteSort:false,
			fitColumns:true,
			idField:'id',
			columns:[[
                    {title:'编号',field:'memberid',width:getWidth(0.5),align:'center'},
		            {title:'成员',field:'memberg',width:getWidth(0.5),align:'center'}
					]],
			singleSelect:true,
			rownumbers:true,
			toolbar:[{
				id:'btnadd3',
				text:'添加成员',
				iconCls:'icon-add',
				handler:function(){
				   $("#dd11").window('open');
					$('#query1').window('open');
				}
			},'-',{
			    id:'deldel',
				text:'删除',
				iconCls:'icon-cut',
				handler:function(){
				   deletem();
			    }
			}]

		});
		$('#btnadd3').linkbutton('disable');
		$('#deldel').linkbutton('disable');
	});	
	
	//初始化通讯录表
	$(function(){
		$('#test').datagrid({
			title:'通讯录',
			iconCls:'icon-save',
			width:getWidth(1),
	//		height:415,
			striped: true,
			collapsible:true,
			fitColumns:true,
			url:'json1.action',
			loadMsg:'装载数据......',
			idField:'friendid',
			columns:[[
			        {field:'ck',checkbox:true},
					{title:'好友编号',field:'friendid',width:getWidth(1),align:'left'},
			        {title:'好友名称',field:'friendname',width:getWidth(1),align:'left'},
		            {title:'好友备注',field:'mark',width:getWidth(1),align:'left'}
					]],
			pagination:true,
			rownumbers:true,
			toolbar:[{
				id:'btnadd',
				text:'添加好友',
				iconCls:'icon-add',
				handler:function(){
					$('#dd1').window('open');
					$('#query').window('open');
				}
			},'-',{
				id:'btndelete',
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
				zhidinggroup2();
				var selections = $('#test').datagrid('getSelections');
				if(selections.length<=1){
					$('#nextfriend').linkbutton('disable');
				}else{
					$('#nextfriend').linkbutton('enable');
			    }
				    updatefriend();
				    $('#friendname1').empty();
				}
			},'-',{
				id:'btndelete',
				text:'删除',
				iconCls:'icon-cut',
				handler:function(){
				var selected1 = $('#test').datagrid('getSelections');
				if(selected1.length>0){
				    var sss='';
				    var ddd='';
				    for(var i=0;i<selected1.length;i++){
				       sss+=selected1[i].friendname+',';
				       ddd+=selected1[i].friendid+',';
				       }
				    sss=sss.substring(0,sss.length-1);
				    ddd=ddd.substring(0,ddd.length-1);
					$.messager.confirm('警告','确认删除好友 "'+sss+'" 么',function(r){
						if(r){
					DelAff(ddd);
					$.messager.alert('提示','删除好友成功','提示');
						}
					});
				}else
				 $.messager.alert('提示','请选择一行数据','提示');
			}
			}]
		});
		 $('#test').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
	});
    //初始化添加成员表
    $(function(){
		$('#test11').datagrid({
		//	title:'添加成员',
			width:getWidth(1),
		//	height:380,
			striped: true,
			singleSelect:true,
			fitColumns:true,
		//	url:'json_u.action',
		//	loadMsg:'装载数据......',
			idField:'userid',
			columns:[[
                    {title:'用户名称',field:'username',width:getWidth(0.35),align:'center'},
				    {title:'用户编号',field:'userid',width:getWidth(0.35),align:'center'},
				    {title:'身份',field:'shenfen',width:getWidth(0.2),align:'center'}
					]],
			pagination:true,
			rownumbers:true,
			toolbar:[{
				id:'invite',
				text:'邀请入群',
				iconCls:'icon-add',
				handler:function(){
					inviteg();
				}
			},'-',{
				id:'btnquery1',
				text:'查询',
				iconCls:'icon-search',
				handler:function(){
					$('#query1').window('open');
				}
			}]
		});
		 $('#test11').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
	});	
	//删除好友
	function DelAff(ddd){
	        
			     	$.ajax({
			            type:"POST",
			           url:"deleteFriend.action",
			            data:{"friendid":ddd},
			           success:function(){
			           $('#test').datagrid('reload');
					   }
			      	});
	}
	//添加好友时验证选中的数据
	function batchupdate(){
		var selections = $('#test1').datagrid('getSelections');
		if(selections.length!=0){
			var selected = selections[0];
			if(selections.length==1){
				$('#next').linkbutton('disable');
			}
			update1(selected);
		}else{
		    $.messager.alert('警告','请选择一行数据','警告');
		 }
	}

    //初始化添加备注窗口
	function update1(selected){
		if(selected){
			$('#usererror').html("好友名称不可修改");
			 $('#edit1').window('open');
           $('#username1').val(selected.username);
           $('#roleid1').val(selected.userid);
		}else{
		    $.messager.alert('警告','请选择一行数据','警告');
		 }
	}
	//添加好友逻辑
	function save1(){
		var selections = $('#test1').datagrid('getSelections');
		var selected = selections[0];
		var friendid = $("#roleid1").val();
		var friendname = $("#username1").val();
		var mark = $("#mark").val();
		var groupname = $('#zhiding').find("option:selected").val();
		if(groupname!='不指定群组'){
		 $.ajax({
		            type:"POST",
		           url:"checkmember.action",
		            data:{"groupname":groupname,"memberid":friendid},
		           success:function(data){
				           if(data.tip==friendid){
				        	   $.messager.alert('警告','用户 '+groupname+' 已经在群组 '+groupname+' 中','警告');
				           }else{
			    $.ajax({
		            type:"POST",
		           url:"groupinvite.action",
		            data:{"groupname":groupname,"memberg":friendname,"memberid":friendid},
		           success:function(){
		            	
				      }
		      	});
	          }
	          }
	          });
	          }
		$.ajax({
			type:"POST",
			url:"checkfriend.action",
			data:{"friendid":friendid},
			success:function(data){
           		if(data.tip==friendid){
	           		$('#usererror').html("<font color='red' size='2px'>此好友已存在</font>");
	           		$.messager.alert('提示',friendname+' 已是你的好友，自动跳过','提示');
	           		var index = $('#test1').datagrid('getRowIndex', selected);
	           		$('#test1').datagrid('deleteRow', index);
	           		$('#mark').val("");
	           		if(selections.length>0){
	           			 
	           			batchupdate();
	           		}
           		}else{
           			if(data.tip=='self'){
    	           		$('#usererror').html("<font color='red' size='2px'>不能添加自己为好友</font>");
    	           		$.messager.alert('提示','不能添加自己为好友，自动跳过','提示');
    	           		var index = $('#test1').datagrid('getRowIndex', selected);
    	           		$('#test1').datagrid('deleteRow', index);
    	           		$('#mark').val("");
    	           		if(selections.length>0){
    	           			 
    	           			batchupdate();
    	           		}
               		}else{
         
		$.ajax({
           type:"POST",
          url:"savefriend.action",
           data:{"friendid":friendid,"friendname":friendname,"mark":mark},
          success:function(){
                var index = $('#test1').datagrid('getRowIndex', selected); 
        	    $('#test1').datagrid('deleteRow', index);
	           	$('#test').datagrid('appendRow',{
	           		friendid:friendid,
	           		friendname:friendname,
	           		mark:mark
	            });
	            $('#mark').val("");
	           	if(selections.length>0){
           			batchupdate();
           		}else{
           			$.messager.alert('确认','添加信息成功!!!','确认');
					clos();
	           	}
		   }
		});
     		   }
           		}
 		}
		});
	}
    //关闭添加备注窗口
	function clos(){
		$('#edit1').window('close');
	//	$('#dd1').window('close');
	}
	//添加好友时查询系统用户
	function query(){
		if($('#quserid').val()==""&&$('#qusername').val()==""){
			$.messager.alert('提示','查询编号和查询姓名不能都为空','提示');
		}else{
        var queryParams = $('#test1').datagrid('options').queryParams;
        if($('#quserid').val()==""){
        	queryParams.queryType = "username";
        	queryParams.queryWord = $('#qusername').val();
        }else{
        	queryParams.queryType = "userId";
        	queryParams.queryWord = $('#quserid').val();
        }
        
        $('#test1').datagrid({
            url:'queryusers.action',
            onLoadSuccess:function(data){
        	$('#test1').datagrid('clearSelections');
            if(data.total==0){
                $.messager.alert('提示','无符合条件结果','提示');
                
            }
        }
   		 });
        $('#test1').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
        $('#query').window('close');
        $('#qusername').val("");
		$('#quserid').val("");
		}
    }
    //修改好友备注时验证选中的数据
	function updatefriend(){
		var selections = $('#test').datagrid('getSelections');
		if(selections.length!=0){
			var selected = selections[0];
			if(selections.length==1){
				$('#nextfriend').linkbutton('disable');
			}
			updatef(selected);
		}else{
		    $.messager.alert('警告','请选择一行数据','警告');
		 }
	}
    //初始化修改备注窗口
	function updatef(selected){
		if(selected){
		   $('#editmarkerror').html("备注限制在50个字");
		   $('#editf').window('open');
           $('#friendnameedit').val(selected.friendname);
           $('#friendidedit').val(selected.friendid);
           $('#markedit').val(selected.mark);
		}else{
		    $.messager.alert('警告','请选择一行数据','警告');
		 }
	}
	//修改备注逻辑
	function editfriend(){
		var selections = $('#test').datagrid('getSelections');
		var selected = selections[0];
		var friendid = $("#friendidedit").val();
		var friendname = $("#friendnameedit").val();
		var mark = $("#markedit").val();
		if(mark==selected.mark){
			$('#editmarkerror').html("<font color='red' size='3px'>未做任何修改</font>");
		}
		$.ajax({
           type:"POST",
          url:"updatefriend.action",
           data:{"friendid":friendid,"friendname":friendname,"mark":mark},
          success:function(){
	           	var index = $('#test').datagrid('getRowIndex', selected); 
				$('#test').datagrid('deleteRow', index);
	           	$('#test').datagrid('appendRow',{
	           		friendid:friendid,
	           		friendname:friendname,
	           		mark:mark
	            });
	    var groupname = $('#zhiding2').find("option:selected").val();
		if(groupname!='不指定群组'){
		 $.ajax({
		            type:"POST",
		           url:"checkmember.action",
		            data:{"groupname":groupname,"memberid":friendid},
		           success:function(data){
				           if(data.tip==friendid){
				        	   $.messager.alert('警告','用户 '+groupname+' 已经在群组 '+groupname+' 中','警告');
				           }else{
			    $.ajax({
		            type:"POST",
		           url:"groupinvite.action",
		            data:{"groupname":groupname,"memberg":friendname,"memberid":friendid},
		           success:function(){
		            	
				      }
		      	});
	          }
	          }
	          });
	          }
	           	if(selections.length>0){
           			updatefriend();
           		}else{
					closf();
	           	}
		   }
 		});
	}
	//关闭修改好友窗口
	function closf(){
		$("#editf").window('close');
	}
	//添加群组
	function savegroup(){
		var groupname = $("#groupname").val();
		if(groupname==""){
			$.messager.alert('提示','群组名称不能为空！！','提示');
		}else{
		$.ajax({
			type:"POST",
	          url:"checkgroup.action",
	           data:{"groupname":groupname},
	          success:function(data){
	 	          if(data.tip==groupname){
		 	          $.messager.alert('提示',groupname+' 群组已存在，请重填','提示');
	 	          }else{
	 	
		$.ajax({
           type:"POST",
          url:"savegroup.action",
           data:{"groupname":groupname},
          success:function(){
	           	$('#test2').datagrid('appendRow',{
	           		groupname:groupname
	            });
           			$.messager.alert('确认','添加信息成功!!!','确认');
					closgroup();
		   }
 		});
	 	          }
	 	        }
		});
		}
	}
	//删除群组
	function deleteg(){
		var selected = $('#test2').datagrid('getSelections');
		 if(selected.length>0){
		    	$.messager.confirm('警告','确认删除群组 '+selected[0].groupname+' 吗?',function(id){
			    if(id){
		    	for(var i=0;i<selected.length;){
				    	var single = selected[i];
				    	var groupname = single.groupname;
				    	$.ajax({
				            type:"POST",
				           url:"deletegroup.action",
				            data:{"groupname":groupname},
				           success:function(){}
				      	});
				    	var index = $('#test2').datagrid('getRowIndex', single);     
						 $('#test2').datagrid('deleteRow', index);
		    	}
		    	  $.messager.alert('确认','所选群组已经被成功删除！','确认');
			    };
			   });
			   }else{
			    $.messager.alert('警告','请选择一行数据','警告');
			   }
	}
	//修改群组名称
	function updateg(){
		var selected = $('#test2').datagrid('getSelections');
		var groupname = $('#groupnameupdate').val();
		if(groupname==""){
			$.messager.alert('提示','群组名称不能为空！！','提示');
		}else{
		$.ajax({
			type:"POST",
	          url:"checkgroup.action",
	           data:{"groupname":groupname},
	          success:function(data){
	 	          if(data.tip==groupname){
		 	          $('#updategrouperror').html("<font color='red' size='2px'>您没有做任何修改</font>");
	 	          }else{
		 	          
		$.ajax({
	           type:"POST",
	          url:"updategroupname.action",
	           data:{"groupname":selected[0].groupname,"newgroupname":groupname},
	          success:function(){
	        		var index = $('#test2').datagrid('getRowIndex', selected[0]); 
					$('#test2').datagrid('deleteRow', index);
		           	$('#test2').datagrid('appendRow',{
		           		groupname:groupname
		            });
		          $('#updateg').window('close');
		          $('#updategrouperror').html(" ");
			   }
	 		});
	 	          }}});
		}
	}
	//列出群组的成员
	function listmember(){
		var selected = $('#test2').datagrid('getSelections');
		if(selected.length!=1){
			$.messager.alert('提示','请选择一行数据','提示');
		}else{
			var groupname = selected[0].groupname;
			
		$('#test3').datagrid({
            url:'listmember.action?groupname='+groupname,
            loadMsg:'装载数据......',
            onLoadSuccess:function(data){
            if(data.total==0){
                $.messager.alert('提示','该群组暂时没有成员，请点击添加成员按钮来完成添加成员','info');
            }
        }
        });
		}
		
	}
	//添加成员时查询系统用户
	function query1(){
		if($('#quserid1').val()==""&&$('#qusername1').val()==""){
			$.messager.alert('提示','查询编号和查询姓名不能都为空','提示');
		}else{
        var queryParams = $('#test11').datagrid('options').queryParams;
        if($('#quserid1').val()==""){
        	queryParams.queryType = "username";
        	queryParams.queryWord = $('#qusername1').val();
        }else{
        	queryParams.queryType = "userId";
        	queryParams.queryWord = $('#quserid1').val();
        }
        
        $('#test11').datagrid({
            url:'queryusers.action',
            loadMsg:'装载数据......',
            onLoadSuccess:function(data){
        	$('#test11').datagrid('clearSelections');
            if(data.total==0){
                $.messager.alert('提示','无符合条件结果','提示');
            }
        }
   		 });
        $('#test11').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
        $('#query1').window('close');
        $('#qusername1').val("");
		$('#quserid1').val("");
		}
    }
    //删除成员
	function deletem(){
		var selectedm1 = $('#test3').datagrid('getSelections');
		var selectedg1 = $('#test2').datagrid('getSelections');
		var groupname = selectedg1[0].groupname;
		if(selectedm1.length!=0){
			    var single = selectedm1[0];
			    var memberid = single.memberid;
			    var memberg = single.memberg;
			    $.messager.confirm('确认','确定删除 '+memberg+' 吗？',function(r){
				    if(r){
			    $.ajax({
		            type:"POST",
		           url:"memberdelete.action",
		            data:{"groupname":groupname,"memberid":memberid},
		           success:function(data){
				           if(data.tip!=memberid){
				        	   var index = $('#test3').datagrid('getRowIndex', single);
								 $('#test3').datagrid('deleteRow', index);
								 $.messager.alert('确认','删除成功!','确认');
				           }else{
					           $.messager.alert('警告','不能删除自己!','警告');
				           }
				      }
		      	});
			    }
			    });
		  }else{
			  $.messager.alert('警告','至少选择一行数据','警告');
		  }
	}
	//邀请系统用户入群
	function inviteg(){
		var selectedg = $('#test2').datagrid('getSelections');
		var selectedr = $('#test11').datagrid('getSelections');
		var groupname = selectedg[0].groupname;
	    if(selectedr.length!=0){
			    var username = selectedr[0].username;
			    var userid= selectedr[0].userid;
			    $.ajax({
		            type:"POST",
		           url:"checkmember.action",
		            data:{"groupname":groupname,"memberid":userid},
		           success:function(data){
				           if(data.tip==userid){
				        	   $.messager.alert('警告','用户 '+username+' 已经在群组 '+groupname+' 中','警告');
				           }else{
			    $.ajax({
		            type:"POST",
		           url:"groupinvite.action",
		            data:{"groupname":groupname,"memberg":username,"memberid":userid},
		           success:function(){
		            	$('#test3').datagrid('appendRow',{
			           		memberg:username,
			           		memberid:userid
			            });
				      }
		      	});
		      		$.messager.alert('确认','入群成功!','确认');
				      $('#dd11').window('close');
	          }
        }
    });     
		  }else{
			  $.messager.alert('警告','请选择一行数据','警告');
		  }
	}
	//关闭修改群组名称窗口
	function closeupdateg(){
		$('#updateg').window('close');
		$('#updategrouperror').html(" ");
	}
	//关闭添加群组窗口
	function closgroup(){
		$('#addgroup').window('close');
	}
	/*
	$(function(){
　　$("#tabs").tabs({ 
　　　　width:$("#tabs").parent().width(), 
　　　　height:$("#tabs").parent().height() 
　　}); 
}); */
   //添加好友时将好友指定到群组
   function zhidinggroup(){
       document.getElementById('zhiding').options.length = 0;
       $("#zhiding").append("<option value='不指定群组'>不指定群组</option>");
       $.ajax({
			    url:"json2",
			    type:"POST",
			    dataType:"json",
			    success:function(data){
			    	$.each(data.rows,function(n,value){
						 $("#zhiding").append("<option value=\""+value.groupname+"\">"+value.groupname+"</option>");
					 });
			    }
			   });
   }
   //修改备注时将好友指定到群组
   function zhidinggroup2(){
       document.getElementById('zhiding2').options.length = 0;
       $("#zhiding2").append("<option value='不指定群组'>不指定群组</option>");
       $.ajax({
			    url:"json2",
			    type:"POST",
			    dataType:"json",
			    success:function(data){
			    	$.each(data.rows,function(n,value){
						 $("#zhiding2").append("<option value=\""+value.groupname+"\">"+value.groupname+"</option>");
					 });
			    }
			   });
   }
	</script>
  </head>
  
  <body style="margin-left: 0px;margin-top: 0px;margin-bottom: 0px;">
   <table height="100%" width="100%" border="0" style="overflow: hidden;">
    <tr >
      <td width="50%"><table id="test" fit="true"></table></td>
      <td width="25%"><table id="test2" fit="true"></table></td>
      <td width="25%"><table id="test3" fit="true"></table></td>
    </tr>
   </table>
    	
    	<div id="dd1" class="easyui-window" title="添加好友" style="padding: 5px;width: 600;height: 375;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="true" draggable="false">
				<table id="test1" fit="true">
               </table>
	</div>
    <div id="edit1" class="easyui-window" title="添加备注" style="padding: 5px;width: 400;height: 175;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="false">
     <form id="update1">
			<table align="left">
				<tr>
					<td>好友名称：</td>
					<td><input type="text" id="username1" readonly="true"/></td><td><span id="usererror"></span></td>
				</tr>
				<tr>
					<td>好友编号：</td>
					<td><input type="text" id="roleid1" readonly="true"/></td><td><span>好友编号不可修改</span></td>
				</tr>
				<tr>
					<td>添加备注：</td>
					<td><input type="text" id="mark"/></td><td><span>备注限制在50个字</span></td>
				</tr>
				<tr>
				    <td> 指定分组：</td>
				    <td><s:select id="zhiding" list="{}"></s:select></td>
				</tr>
				<tr>
					<td colspan="3">
						<a id="next" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="save1()">下一条</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="save1()">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="clos()">取消</a>
					</td> 
				</tr>
			</table>
			</form>
    </div>
    <div id="editf" class="easyui-window" title="修改备注" style="padding: 5px;width: 400;height: 175;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="false">
     <form id="updatef">
			<table align="left">
				<tr>
					<td>好友名称：</td>
					<td><input type="text" id="friendnameedit" readonly="true"/></td><td><span>好友名称不可修改</span></td>
				</tr>
				<tr>
					<td>好友编号：</td>
					<td><input type="text" id="friendidedit" readonly="true"/></td><td><span>好友编号不可修改</span></td>
				</tr>
				<tr>
					<td>修改备注：</td>
					<td><input type="text" id="markedit"/></td><td><span id="editmarkerror">备注限制在50个字</span></td>
				</tr>
				<tr>
				   <td>指定分组：</td>
				   <td> <s:select id="zhiding2" list="{}"></s:select></td>
				</tr>
				<tr>
					<td colspan="3">
						<a id="nextfriend" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="editfriend()">下一条</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="editfriend()">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closf()">取消</a>
					</td> 
				</tr>
			</table>
			</form>
    </div>
    
	 <div id="query" class="easyui-window" title="查询系统用户" style="padding: 10px;width: 375;height:150;"
    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
	            <table>
	                    <tr><td>查询编号：<input type="text" id="quserid" /></td></tr>
	                    <tr><td>查询姓名：<input type="text" id="qusername"  ></td>
	                  
	                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
	                </tr>
	            </table>  姓名与编号二选一，如都填则按编号查询
    </div>
   <div id="addgroup" class="easyui-window" title="新建群组" style="padding: 5px;width: 250;height: 110;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="false">
		
		   <tr>  群组名称:<input type="text" id="groupname"/></tr></br>
		   <tr></tr></br>
		     <tr>
						<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="savegroup()">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closgroup()">取消</a>
		    </tr>
	</div>
	<div id="updateg" class="easyui-window" title="修改群组名称" style="padding: 10px;width: 230;height:130;"
    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false" >
    	
    	<input id="groupnameupdate" type="text" /></br><span id="updategrouperror"></span></br>
    	  <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0);" onclick="updateg()">提交</a>
    	  &nbsp;&nbsp;&nbsp;&nbsp;
    	  <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0);" onclick="closeupdateg()">取消</a>
    </div>
    <div id="dd11" class="easyui-window" title="添加成员" style="padding: 5px;width: 600;height: 375;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="true" draggable="false">
		<table id="test11" fit="true">
    	</table>
    <div id="query1" class="easyui-window" title="查询系统用户" style="padding: 10px;width: 375;height:150;"
    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
        <div>
        	<form>
	            <table>
	                    <tr><td>查询编号：<input type="text" id="quserid1" /></td></tr>
	                    <tr><td>查询姓名：<input type="text" id="qusername1"  ></td>
	                  
	                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query1()">查询</a></td>
	                </tr>
	            </table>  姓名与编号二选一，如都填则按编号查询
            </form>
        </div>
    </div>
  </body>
</html>
