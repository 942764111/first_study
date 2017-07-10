<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>source collection</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script>
		$(function(){
			$('#ttt').datagrid({
				title:'用户自定义分类',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'listyhzdymc.action',
				sortName: 'zdyflno',
				sortOrder: 'asc',
				remoteSort: false,
				idField:'zdyflno',
				singleSelect:true,
				loadMsg:'加载数据,请等待...',
				fit:true,
				//fitColumns:true,
				width:getWidth(1),
				
				columns:[[
				      	{field:'zdyflno',title:'自定义分类序号',width:getWidth(0.2),align:'center',editor:'text'},
				      	{field:'zdyflmc',title:'自定义分类名称',width:getWidth(0.2),align:'center',editor:'text'},
				      	{field:'zysl',title:'资源数量',width:getWidth(0.593),align:'center',editor:'text'} 
				      	]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
					$("#addform").form('clear');
					$('#add').window('open');
      				}
				},{
					text:'删除',
					iconCls:'icon-remove',
					handler:dele
				},{
					text:'修改',
					iconCls:'icon-edit',
					handler:edit
				},{
					text:'详细查询',
					iconCls:'icon-search',
					handler:find
				}]
			});
			$('#ttt').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
				 });
		});
		//宽度
		function getWidth(percent){ 
			return (document.body.clientWidth-25-5)*percent; 
		}
		//添加===================================
		function add(){
			var selected = {"yhzdymc.zdyflmc":$("#zdyflmc_add").val(),"yhzdymc.zysl":$("#zysl_add").val()};
			if(selected){
				$.ajax({
					type:"POST",
					url:"addyhzdymc.action",
					data:selected,
					success:function(){
						$("#add").window('close');
		            	$.messager.alert('add','添加信息成功!!!','info',function(r){
	            			$('#ttt').datagrid('reload');
	            		});
					}
				});
				}
			//$('#test').datagrid('reload');
			}
		//删除======================================
		function dele(){
			var selected = $('#ttt').datagrid('getSelected');
			if(selected){
			  var select = {"zdyflno":selected.zdyflno};
			  $.messager.confirm('warning','数据将被永久删除，不可恢复',function(selected){
				if(selected){
					$.ajax({
						type:"POST",
						url:"deleteyhzdymc.action",
						data:select,
						success:function callback(r){
							alert("删除成功");
							$('#ttt').datagrid('reload');
						}
					});
				};
				
			  });
			}else{
			$.messager.alert('warning','请选择一行数据','warning')};
		}
		//实现用户自定义分类下的详细信息查看
		function find(){
			var selected = $('#ttt').datagrid('getSelected');
			alert(selected.zdyflno);
			if(selected){
				$.ajax({
					type:"POST",
					url:"zdyfl_list.action",
					data:{"zdyflno":selected.zdyflno},
					dataType:"json",
					success:function(r){
						window.location.href='tiaozh.action';
						}
				});
				
			}else{
				$.messager.alert('warning','请选择一行数据','warning')	
			};
		
		}
		//修改=======================================
		var dd;
		function edit(){
			var selected = $('#ttt').datagrid('getSelected');
			if(selected){
				dd = selected.userinfo.userId;
				$('#update').window('open');
				$('#zdyflno_update').val(selected.zdyflno);
				$('#zdyflmc_update').val(selected.zdyflmc);
				$('#zysl_update').val(selected.zysl);
				}else{
				$.messager.alert('warning','请选择一行数据','warning')
				}
			}
			function update(){
			var up={"yhzdymc.userinfo.userId":dd,"yhzdymc.zdyflno":$("#zdyflno_update").val(),"yhzdymc.zdyflmc":$("#zdyflmc_update").val(),"yhzdymc.zysl":$("#zysl_update").val()};
			$.ajax({
					type:"POST",
					url:"updateyhzdymc.action",
					data:up,
					success:function(r){
								$('#ttt').datagrid('reload')	
							}
					});
			$('#update').window('close');
			}
		//窗口关闭===================================
		function closeaddwindow(){
			$("#add").window('close');
			}
		function closeupdatewindow(){
			$("#update").window('close');
			}
</script>
</head>
<body style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
    <table id="ttt"></table>
     <!-- jqueryeasyui实现的添加界面 -->
	   <div id="add" closed="true" class="easyui-window" title="添加分类" iconCls="icon-add" style="width:300px;height:200px;padding:5px;background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
			<form id="addform">
				<table>
								<tr>
									<td> 
									分类名称: 
									</td>
									<td>
									<input id="zdyflmc_add" type="text"/>
									</td>
								</tr>
								<tr>
									<td> 
									资源数量: 
									</td>
									<td>
									<input id="zysl_add" type="text"/>
									</td>
								</tr>
				</table>
			</form>					
			</div>
			<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="add()">确定</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeaddwindow()">取消</a>
			</div>
		</div>
	</div>
	 <!-- jqueryeasyui实现的修改界面 -->
	 <div id="update" closed="true" class="easyui-window" title="修改分类" iconCls="icon-add" style="width:300px;height:210px;padding:5px;background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
			<form id="addform">
				<table align="left">
								<tr>
									<td> 
									分类序号: 
									</td>
									<td>
									<input id="zdyflno_update" type="text" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td> 
									分类名称: 
									</td>
									<td>
									<input id="zdyflmc_update" type="text"/>
									</td>
								</tr>
								<tr>
									<td> 
									资源数量: 
									</td>
									<td>
									<input id="zysl_update" type="text"/>
									</td>
								</tr>
				</table>
			</form>					
			</div>
			<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="update()">确定</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeupdatewindow()">取消</a>
			</div>
		</div>
	</div>
</body>
</html>