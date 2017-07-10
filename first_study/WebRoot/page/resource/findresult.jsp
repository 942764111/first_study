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
	<script type="text/javascript" src="page/collection/query.js"></script>
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
	var icon = 'icon';
		$(function(){
			$('#tt').tabs({
				fit:true
			});
			$('#ttt').datagrid({
				title:'用户查询情况',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'cxresult.action',
				idField:'lxmc',
				singleSelect:true,
				loadMsg:'加载数据,请等待...',
				fit:true,
				onDblClickRow: function() {
				    var selected = $('#ttt').datagrid('getSelected');
				    var mc = selected.lxmc;
				    if (mc=="选择题"){
				    	addTab("选择题","page/resource/xzchaxun.jsp");
				   }
				    if (mc=="判断题"){
				    	addTab("判断题","page/resource/pdchaxun.jsp");
				   }
				    if (mc=="操作题"){
				    	addTab("操作题","page/resource/cztchaxun.jsp");
				   }
				    if (mc=="多媒体资料"){
				    	addTab("多媒体资料","page/resource/dmtchaxun.jsp");
				   }
				},
				width:getWidth(1),
				columns:[[
				      	{field:'lxmc',title:'资源类型',width:getWidth(0.41),align:'center',editor:'text'},
				      	{field:'lxsl',title:'资源数量',width:getWidth(0.575),align:'center',editor:'text'} 
				      	]],
				pagination:true,
				rownumbers:true
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

		//add选项卡
		function addTab(subtitle,url){
			if(!$('#tt').tabs('exists',subtitle)){
				$('#tt').tabs('add',{
					title:subtitle,
					content:createFrame(url),
					closable:true,
					icon:icon
				});
			}else{
				$('#tt').tabs('select',subtitle);
				$('#mm-tabupdate').click();
			}
			tabClose();
		}
		function createFrame(url)
		{
			var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
			return s;
		}

		function tabClose()
		{
			/*双击关闭TAB选项卡*/
			$(".tabs-inner").dblclick(function(){
				var subtitle = $(this).children(".tabs-closable").text();
				$('#tt').tabs('close',subtitle);
			})
			/*为选项卡绑定右键*/
			$(".tabs-inner").bind('contextmenu',function(e){
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});

				var subtitle =$(this).children(".tabs-closable").text();

				$('#mm').data("currtab",subtitle);
				$('#tt').tabs('select',subtitle);
				return false;
			});
		}
			
</script>
</head>
<body style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
	<div id="tt">
  		<div title="用户查询情况">
    		<table id="ttt"></table>
    
		</div>
	</div>
</body>
</html>