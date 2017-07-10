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
			$('#test_tables').datagrid({
				title:'资料记录 列表',
				iconCls:'icon-save',
				fitColumns:true,
				singleSelect:false,
				fit:true,
				loadMsg:'加载数据,请等待...',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'zljllist1.action',
				columns:[[
	                {field:'userId',title:'用户名',width:getWidth(0.2),align:'left'},
	                {field:'zlmc',title:'资料名称',width:getWidth(0.4),align:'left'},
	                {field:'open_time',title:'上次打开时间',width:getWidth(0.4),align:'left'}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'打开',
					iconCls:'icon-redo',
					handler:function(){
					open();
				}
					}]
			});
		});

		//跳到另外一个页面，打开要浏览的资料
		function open(){
			var selected = $('#test_tables').datagrid('getSelected');
			if(selected){
				$('#zxsp').window('open');
	        		CFrame.document.location.href='../spdh/dzbj.jsp?zlmc=' + encodeURI(encodeURI(selected.zlmc)) +
	    		'&path=' + selected.path + '&filename=' + selected.nr + '&zlid=' + selected.zlbh + '&weizhi=' + selected.weizhi + '&type='+"jxxx";
			}else{
				$.messager.alert('提示','请选择一行数据','warning');
			}
		}

		//宽度
		function getWidth(percent){ 
		    return (document.body.clientWidth-25-7)*percent; 
		}

		//关闭窗口时执行的方法（朱永科）
		 $(function(){ 
			  $('#zxsp').window({
	      		   onClose:function(){
				  document.frames.CFrame.closeNs();
	      	   		}
	  	   });
		 });

		</script>
	</head>
	
	<body class="easyui-layout" style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
	
		<div region="center">
			<table id="test_tables"></table>	
		</div>
		<div id="zxsp" class="easyui-window" closed="true" title="在线视频" icon="icon-edit" maximizable="true"  minimizable="true" style="padding:0px;width:800px;height:450px;overflow-x:hidden;overflow-y:hidden;">
	      <iframe name="CFrame" id="CFrame" frameborder="0" style="padding:0px;width:100%;height:100%;"></iframe>
		</div>
		
	</body>
</html>
   