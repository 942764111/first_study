<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>source collection</title>
	<link rel="stylesheet" type="text/css" href="../../js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../js/ui/themes/icon.css">
	<script type="text/javascript" src="../../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../../js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="../../kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="../../page/collection/query.js"></script>
	<style type="text/css">
	body{
    margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-size: 12px;
   }
   .table{
   	font-size: 12px;
   }
	
	</style>
	<script>
		$(function(){
			$('#test').datagrid({
				title:'资源收藏',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'listResource.action',
				sortName: 'sxh',
				sortOrder: 'asc',
				remoteSort: false,
				idField:'sxh',
				width:getWidth(1),
				singleSelect:true,
				loadMsg:'加载数据,请等待...',
				fit:true,
				pageList:[15,20,40],
				columns:[[
					    {field:'zdyflmc',title:'自定义分类名称',width:getWidth(0.15),align:'center',editor:'text',
					    	formatter:function(val,rec)
					      	{
							return val = rec.yhzdymc.zdyflmc;
						    }
					    },
				      	{field:'zylx',title:'资源类型',width:getWidth(0.15),align:'center',editor:'text'},
				      	{field:'zybh',title:'资源编号',width:getWidth(0.15),align:'center',editor:'text'},
				      	{field:'sskcmc',title:'所属课程名称',width:getWidth(0.15),align:'center',editor:'text'},
				      	{field:'zyms',title:'资源描述',width:getWidth(0.4),align:'center',editor:'text'}				      	 
				      	]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					text:'删除',
					iconCls:'icon-remove',
					handler:dele
				},{
					text:'自定义分类',
					iconCls:'icon-save',
					handler:function(){
					//var selected = $('#test').datagrid('getSelected');
					$("#sort").window('open');
					window.location.href='listy.action';
					}
				}]

				
			});
			$('#test').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
				 });

			 
		});

		
		//宽度
		function getWidth(percent){ 
			return (document.body.clientWidth-25-5)*percent; 
		}
//删除功能===============================================
	function dele(){
	var selected = $('#test').datagrid('getSelected');
	if(selected){
	var select = {"sxh":selected.id.sxh};
	  $.messager.confirm('warning','数据将被永久删除，不可恢复',function(selected){
		if(selected){
			$.ajax({
				type:"POST",
				url:"deleteResource.action",
				data:select,
				success:function callback(r){
					$('#test').datagrid('reload');
				}
			});
		};
		
	  });
	}else{
	$.messager.alert('warning','请选择一行数据','warning')};
}
	
//窗口关闭===============================================
		function closeaddwindow(){
			$('#add').window('close');
			}
		function closefindwindow(){
			$('#find').window('close');
			}
		function closesortwindow(){
			$('#sort').window('close');
			}		
</script>
</head>
<body style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
	   		<table id="test"></table>
</body>
</html>