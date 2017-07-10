<%@ page language="java" import="java.util.*,org.apache.struts2.ServletActionContext" pageEncoding="utf-8"%>
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
	   .style1 {
				color: #FF0000;
				font-size: 6px;
			}
	
	</style>
	<script>
		$(function(){
			$('#jxjhsc').datagrid({
				title:'多媒体收藏',
				iconCls:'icon-save',	
				width:getWidth(1),
				nowrap: true,
				striped: true,
				singleSelect:true,
				fit:true,
				//fitColumns:true,
				url:'jxjhsc.action',
				//fieldid:'zybh',
				loadMsg:'加载数据,请等待...',

				onDblClickRow: function() {
					var selected = $('#jxjhsc').datagrid('getSelected');
					var sskcmc1 = selected.sskcmc;
					var sszmc1 = selected.sszmc;
					var sskcxh1 = selected.sskcxh;
					$.ajax({
						type:"POST",
						url:"dakaidmt.action",
						data:{"sskcmc1":sskcmc1,"sszmc1":sszmc1,"sskcxh1":sskcxh1},
						cache: false,
						success:function(data){
							$('#zxsp').window('open');
							CFrame.document.location.href='spdh/dzbj.jsp?zlmc=' + encodeURI(encodeURI(selected.zymc)) +
			    			'&path=' + selected.zyms + '&filename=' + selected.nr + '&zlid=' + selected.zybh + '&type='+"zysc";
						}
					});
					//window.location.href="dakaidmt.action?dmtzybh="+selected.zybh; //用于跳转到能打开多媒体的页面
		},
				columns:[[     
							{field:'zybh',title:'资源编号',width:getWidth(0.1),align:'center'},
							{field:'zymc',title:'资源名称',width:getWidth(0.15),align:'center'},
							{field:'sskcmc',title:'所属课程',width:getWidth(0.15),align:'center'},                          
							{field:'sszmc',title:'所属章',width:getWidth(0.22),align:'center'},
							{field:'sskcxh',title:'课次',width:getWidth(0.14),align:'center'},
							{field:'scsj',title:'收藏时间',width:getWidth(0.229),align:'center'}	
						]],

				pagination:true,
				rownumbers:true,
				toolbar:[{
					text:'删除',
					iconCls:'icon-remove',
					handler:dele
				},{
					text:'查询',
					iconCls:'icon-search',
					handler:function(){
						$('#cx').form('clear');
						$('#query').window('open');
						document.getElementById("query").style.visibility="visible";
				}
				}]
				
			});
			$('#jxjhsc').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
				 });
		});
		//宽度
		function getWidth(percent){ 
			return (document.body.clientWidth-25-5)*percent; 
		}

		//删除======================================
		function dele(){
			var selected = $('#jxjhsc').datagrid('getSelected');
			if(selected){
			var select = {"zylx":15,"zybh":selected.zybh};
			  $.messager.confirm('warning','数据将被永久删除，不可恢复',function(selected){
				if(selected){
					$.ajax({
						type:"POST",
						url:"deleteResource2.action",
						data:select,
						success:function callback(r){
							$('#jxjhsc').datagrid('reload');
						}
					});
				};
				
			  });
			}else{
			$.messager.alert('warning','请选择一行数据','warning')};
		}

		//查询功能
		function query(){
	        var queryWord = $('#name').val();
	        if(queryWord!=""){
	        	$.ajax({
					type:"POST",
					url:"searchjxjhsc.action",
					data:{"queryw":queryWord},
					cache: false,
					success:function(data){
								if(data.a == 1){
									$('#jxjhsc').datagrid({
					    	            url:'searchjxjhsc1.action?queryw='+queryWord
					    	        });
					    	        $('#query').window('close');
					    	        $('#jxjhsc').datagrid('getPager').pagination({
					   				 displayMsg:'当前显示从{from}到{to}共{total}记录',
					   				 beforePageText:'第',
					   				 afterPageText:'页'
					   				 });
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
<body style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
		<table id="jxjhsc"></table>
		<div id="query" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;visibility:hidden;"
	    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
	        <div>
	        	<form id="cx">
		            <table>
		                <tr>
		                    <td>
		                    	题号：
		                    </td>
		                    <td><input type="text" name="name" id="name"  class="easyui-numberbox"><span class="style1"><font size="1">*输入数字</font></span></td>
		                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
		                </tr>
		            </table>
	            </form>
	        </div>
	    </div>	
	    <div id="zxsp" class="easyui-dialog" closed="true" title="在线视频" icon="icon-edit" maximizable="true"  minimizable="true" style="padding:0px;width:800px;height:450px;overflow-x:hidden;overflow-y:hidden;">
	       <iframe name="CFrame" id="CFrame" frameborder="0" style="padding:0px;width:100%;height:100%;"></iframe>
		</div>
</body>
</html>