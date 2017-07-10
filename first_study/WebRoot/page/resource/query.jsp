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
				title:'查询结果',
				iconCls:'icon-save',
				width:getWidth(1),
				pageList:[15,20,40],
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'ceshi.action',
				sortName: 'lx',
				sortOrder: 'asc',
				remoteSort: false,
				idField:'lx',
				singleSelect:true,
				fit:true,
				columns:[[
				      	{field:'lx',title:'题型',width:getWidth(0.196),align:'center',sortable:'true'},
				      	{field:'no',title:'题号',width:getWidth(0.29),align:'center',sortable:'true'},
				      	{field:'tg',title:'题干',width:getWidth(0.5),align:'center'}
				      	]],
				pagination:true,
				rownumbers:true,
			});
			$('#test').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
				 });
		});
		
		function getWidth(percent){ 
			return (document.body.clientWidth-25-5)*percent; 
		}
</script>
</head>
<body>
    <table id="test"></table>
</body>
</html>