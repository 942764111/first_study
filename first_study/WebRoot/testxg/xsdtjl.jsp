<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生答题记录管理</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	
	
<script type="text/javascript">
		function getWidth(percent){ 
		    return (document.body.clientWidth-25-11)*percent; 
		}
		$(function(){			
			$('#test_tables').datagrid({
				title:'学生答卷记录管理',
				iconCls:'icon-save',
				nowrap: true,
				striped: true,
				fitColumns:true,	
				loadMsg:'加载数据,请等待...',
				url:'djjlgl.action',
				columns:[[
					{field:'papername',title:'所测试卷名称',width:getWidth(0.1),align:'center'},
	                {title:'测试者名称',field:'studentname',width:getWidth(0.1),align:'center'}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'删除学生答卷记录',
					iconCls:'icon-cancel',
					handler:deleteAll
				},'-',{
    				id:'btnquery',
    				text:'查询',
    				iconCls:'icon-search',
    				handler:function(){
    					$('#btnquery').linkbutton('enable');
    					$('#cx').form('clear');
    					$('#query').window('open');
    					document.getElementById("query").style.visibility="visible";
    				}
    			}]
			});
		});


		//删除多行
		function deleteAll(){   
			 var selected = $('#test_tables').datagrid('getSelections');
			 var arr=new Array();
			   if(selected.length>0){
				   $.each(selected,function(n,value){
                       arr.push(value.papername);
                    });
			    	$.messager.confirm('提示','确认试卷号为“'+arr.toString()+'”的数据么?',function(id){
			    		if(id){
			    			$.each(selected,function(m,value1){
						    	var single = value1;
						    	var papername=single.papername;
						    	var studentname=single.studentname;
						     	$.ajax({
						            type:"POST",
						           url:"deletexsdjjl.action",
						            data:{"papername":papername,"studentname":studentname},
						           success:function(){
											var index = $('#test_tables').datagrid('getRowIndex',single);//获取某行的行号
											$('#test_tables').datagrid('deleteRow',index);	//通过行号移除该行
											$.messager.confirm('确认','试卷号为'+single.papername+'的数据已经被成功删除！',function(r){
												$('#test_tables').datagrid('reload');	
												});
							           }
						      	});
						    });
				    	}
			   		});
			   }else{
			    $.messager.alert('提示','请选择一行数据','warning');
			   }
		}


		function query(){
	        var queryParams = $('#test_tables').datagrid('options').queryParams;
	        var queryType=queryParams.queryType = "studentname";
	        var queryWord=queryParams.queryWord= $('#name').val();
	        if(queryWord!=""){
					$.ajax({ 
						type:"POST",
						url:"querydtjl1.action",
						data:{"queryType":queryType,"queryWord":queryWord},
						cache: false,
						success:function(data){
									if(data.a == 1){
										$('#test_tables').datagrid({
						   		            url:'querydtjl.action'
						   		        });
						   		        $('#query').window('close');
									}else if(data.a == 0){
										$.messager.alert('提示信息','查询结果为空!!!','info',function(){
						            	});
								}
							}
						});
		}else{$.messager.alert('提示','查询条件不能为空!');}
	   				 }
		
	</script>
	</head>
	
	<body class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
		<div region="center"><table id="test_tables" fit="true"></table></div>
		<div id="query" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;visibility:hidden;"
		    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
				<div>
					<form id="cx">
						<table>
							<tr>
								<td>
									测试者名称：
								</td>
								<td>
									<input type="text" name="id" id="name"  required="true"/>
								</td>
								 <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
							</tr>
						</table>
					</form>
				</div>	
			</div>	
	</body>
</html>
   