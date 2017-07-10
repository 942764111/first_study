<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jQuery EasyUI</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		$(function(){	
			PdType();
			$.extend($.messager.defaults,{  
       	    	ok:"确定",  
       	    	cancel:"取消"  
       		});
        			
			$('#test_tables').datagrid({
				title:'文件类型列表',
				iconCls:'icon-save',
				width:getWidth(1),
				fitColumns:true,
				height:550,
				singleSelect:false,
				fit:true,
				loadMsg:'加载数据,请等待...',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'wjlxlist.action',
				columns:[[
	                {field:'lxm',title:'分类',width:getWidth(0.2),align:'left'},
	                {field:'kzm',title:'扩展名',width:getWidth(0.2),align:'left'},
	                {field:'ms',title:'描述',width:getWidth(0.6),align:'left'}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'添加文件类型',
					iconCls:'icon-add',
					handler:add
					},'-',{
						id:'delete',
						  text:'删除',
			              iconCls:'icon-cancel',
			              handler:deleteRows
					},'-',{
						id:'update',
						  text:'修改',
			              iconCls:'icon-edit',
			              handler:batchupdate
	     		}]
			});
			$('#test_tables').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			});
		});

		//宽度
		function getWidth(percent){ 
		    return (document.body.clientWidth-25-7)*percent; 
		}

		function add() {
			$('#btnsave').linkbutton('enable');
			$('#insert').form('clear');
	        $('#add').window('open');
	        document.getElementById("add").style.visibility="visible";
		}

		//添加
		function addwjlx() {
			var lxm = $("#addlxm").val();
			var ms = $("#addms").val();
			var kzm = $("#addkzm").val();
			if(lxm=="") {
				$.messager.alert('提示信息','分类不能为空!','info')
			}else {
				$.ajax({
		            type:"POST",
		            url:"insertwjlx1.action",
		            data:{"lxm":lxm,"ms":ms,"kzm":kzm},
		            success:function(json){
			            if(json.a == '1') {
				            $.ajax({
				            	type:"POST",
					            url:"insertwjlx.action",
					            data:{"lxm":lxm,"ms":ms,"kzm":kzm},
					            success:function(json) {
					            	$.messager.alert('提示信息','添加信息成功!','info',function(){
					            		$('#test_tables').datagrid('reload');
					            	});
						        }
					        })
			            	$('#add').dialog('close');					            	
			            	
				        }else {
							$.messager.alert('提示信息','您添加的分类已存在!','info');
					    }
		            	
				   }
	      	});
			}
		 	
		}

		//继续添加
		function continueAddwjlx() {
			var lxm = $("#addlxm").val();
			var ms = $("#addms").val();
			var kzm = $("#addkzm").val();
			if(lxm=="") {
				$.messager.alert('提示信息','类型名不能为空!','info')
			}else {
			 	$.ajax({
			            type:"POST",
			            url:"insertwjlx1.action",
			            data:{"lxm":lxm,"ms":ms,"kzm":kzm},
			            success:function(json){
			            	if(json.a == '1') {
					            $.ajax({
					            	type:"POST",
						            url:"insertwjlx.action",
						            data:{"lxm":lxm,"ms":ms,"kzm":kzm},
						            success:function(json) {
						            	$('#test_tables').datagrid('reload');
							        }
						        })
				            	$('#add').dialog('close');
				            	add();				            	
				            	
					        }else {
								$.messager.alert('提示信息','您添加的分类已存在!','info');
						    }
					   }
		      	});
			}
		}

		//删除多行
		function deleteRows(){   
			 var selected = $('#test_tables').datagrid('getSelections');
			 var arr = new Array();
			   if(selected.length>0){
				   $.each(selected,function(n,value) {
						arr.push(value.lxm);
					})
			    	$.messager.confirm('提示','确认删除分类为"' + arr + '"的数据吗?',function(id){
			    		if(id){
				    		for(var i=0;i<selected.length;i++){
						    	var single = selected[i];
						    	$.ajax({
				                      type:"POST",
				                      url:"deletewjlx.action",
				                      data:"lxm="+single.lxm,
				                      success:function(){
				                      }
								});
						     	var index = $('#test_tables').datagrid('getRowIndex',selected[i]);//获取某行的行号
								$('#test_tables').datagrid('deleteRow',index);	//通过行号移除该行
						    }
				    		$.messager.confirm('确认','所选数据已经被成功删除！',function(r){$('#test_tables').datagrid('reload');});
				    	}
			   });
			   }else{
			    $.messager.alert('提示','请选择一行数据','warning');
			   }
		}

		//删除一行
		function deleteRow(index){
				
	   		var selected = $('#test_tables').datagrid('getSelected');
	       	if(selected){
				$.messager.confirm('提示信息','确认删除类型名为"'+selected.lxm+'"的数据吗?',function(d){
					if(d){
		             	$.ajax({
		                      type:"POST",
		                      url:"deletewjlx.action",
		                      data:"lxm="+selected.lxm,
		                      success:function(){
		                      	$('#test_tables').datagrid('reload');
		                      }
						});
					}	
				});	       			
			}else{
				$.messager.alert('警告','请选择一行数据','warning');
		    }
		}

		//修改文件类型
		function batchupdate(){
			var selections = $('#test_tables').datagrid('getSelections');
			if(selections.length!=0){
				var selected = selections[0];
				update();
			}else{
			    $.messager.alert('提示','请选择一行数据','warning');
			 }
		}
	
		function update(){
			var selected = $('#test_tables').datagrid('getSelected');
			if(selected){
				var ms = selected.ms;
				var kzm = selected.kzm;
				var count=$("#lxm option").length;
				var obj=document.getElementById("lxm");						
				for(var i=0;i<count;i++){						
					obj.options[i].selected=false;
		        	if (obj.options[i].value==lxm){
		        		obj.options[i].selected=true;
		        		break;
		        	 }
				}
				
				$('#edit').window('open');
				document.getElementById("edit").style.visibility="visible";
				$('#update').show();
				$('#lxm').val(selected.lxm);
	            $('#ms').val(selected.ms);
	            $('#kzm').val(selected.kzm);
			}else{
			    $.messager.alert('警告','请选择一行数据','warning');
			 }
					
		}
			
		function edit(){
			var selected = $('#test_tables').datagrid('getSelected');
			var selections = $('#test_tables').datagrid('getSelections');
			var lxm = $("#lxm").val();
			var ms = $("#ms").val();
			var kzm = $("#kzm").val();
			if(kzm!=selected.kzm||ms!=selected.ms) {
				$.ajax({
		            type:"POST",
		            url:"updatewjlx.action",
		            data:{"lxm":lxm,"ms":ms,"kzm":kzm},
		           	success:function(){
		            	$('#edit').dialog('close');
		            	var index = $('#test_tables').datagrid('getRowIndex', selected);     
						$('#test_tables').datagrid('deleteRow', index);
		            	$('#test_tables').datagrid('appendRow',{
		            		lxm:lxm,
		            		ms:ms,
		            		kzm:kzm
		                });
		            	if(selections.length>1){
		           			batchupdate();
		           		}else{
		           			$.messager.alert('提示','您已成功修改所选数据!');
		           			$('#test_tables').datagrid('reload');
		           			close2();
			           	}
				   }
		  		});
			} else {
				$.messager.alert('提示信息','您没有修改任何数据!','info');
			}
			
		}

		function close1() {
			$('#add').window('close');
		}
		
		function close2(){
			$('#edit').window('close');
		}

		//判断角色的类型，如果角色是学生就会失去一些权限
		function PdType(){
			$.ajax({
	            type:"GET",
	           url:"GetType.action",
	           success:function(json){
		           if(json.tip=="S"){
		        	   $('#btnadd').linkbutton('disable');
			     	   $('#delete').linkbutton('disable');
			     	  	$('#update').linkbutton('disable');
			          }
		           }
	      	});
		}
	</script>
	<script language="JavaScript" type="text/javascript">	
			var TextUtil = new Object();
			TextUtil.NotMax = function(oTextArea){
				var maxText = oTextArea.getAttribute("maxlength"); 
				if(oTextArea.value.length > maxText){
						oTextArea.value = oTextArea.value.substring(0,maxText);
						alert("只能输入50个字");
				}
			}
	</script>
	</head>
	
	<body class="easyui-layout" style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
	
		<div region="center">
			<table id="test_tables"></table>	
		</div>	
		
	    <div id="add" class="easyui-window" icon="icon-add" title="添加" closed="true" style="padding:5px;width:350px;height:230px;visibility:hidden;"
	    	maximizable="false" minimizable="false" collapsible="false">
			<center>	    
			    <s:form id="insert">
			    	<table align="center">
				    	<tr>
				 			<td> 
								分类:
							</td>
							<td>
								<input type="text" id="addlxm" maxlength="8"/>
							</td>
						</tr>	
						<tr>
							<td> 
								扩展名:
							</td>
							<td>
								<input type="text" id="addkzm" width="50" maxlength="50"/>
							</td>
						</tr>				
						<tr>
							<td> 
								描述:
							</td>
							<td>
								<textarea rows="4" cols="19" id="addms"
									onpropertychange="TextUtil.NotMax(this)" maxlength="50"></textarea><br/><br/>
							</td>
						</tr>
						
						<tr>
							<td colspan="3" align="right">
								<a class="easyui-linkbutton" iconCls="icon-add"
									href="javascript:void(0)" onclick="continueAddwjlx()">继续添加</a>
								<a class="easyui-linkbutton" iconCls="icon-ok"
									href="javascript:void(0)" onclick="addwjlx()">完成</a>
								<a class="easyui-linkbutton" iconCls="icon-cancel"
									href="javascript:void(0)" onclick="close1()">取消</a>
							</td>
						</tr>				
						
					</table>
			    </s:form>
			</center>
	   </div>	
	   
	   <div id="edit" class="easyui-window" icon="icon-edit" title="修改" closed="true" 
	   		style="padding:5px;width:350px;height:230px;visibility:hidden;" maximizable="false" 
	   		minimizable="false" collapsible="false">
			<s:form id="update">
				<table align="center"> 
					<tr>
			 			<td> 
							分类:
						</td>
						<td>
							<input type="text" id="lxm" style="border:0px;" readonly="readonly" maxlength="8"/>
						</td>
					</tr>
					<tr>
						<td> 
							扩展名: 
						</td>
						<td>
							<input type="text" id="kzm" maxlength="50"/>
						</td>
					</tr>					
					<tr>
						<td> 
							描述: 
						</td>
						<td>	
							<textarea rows="4" cols="19" id="ms" onpropertychange="TextUtil.NotMax(this)" maxlength="50"></textarea><br/><br/>							
						</td>
					</tr>				
					
					<tr>
						<td colspan="2" align="right">
							<a class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" onclick="edit()">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="easyui-linkbutton" iconCls="icon-cancel"
								href="javascript:void(0)" onclick="close2()">取消</a>
						</td>
					</tr>
				</table>
			</s:form>			
		</div>
									
	</body>
</html>
   