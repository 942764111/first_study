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
		<!--
		.style1 {
			color: #FF0000;
			font-size: 6px;
		}
		-->
	</style>
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
			$.ajax({
		           type:"POST",
		           url:"jcpd.action",
		           dataType:'json',
		           success:function(r){
			           var wab = r.message;
			           if(wab!=""){
			        	   $.messager.alert('提示','您所收藏的题号为'+wab+'的题目已被管理员删除，您对该题目的收藏已无效!');
			           }
		           }
			});
			//判断题
			$('#puanduant').datagrid({
				title:'判断题收藏',
				iconCls:'icon-save',			
				width:getWidth(1),
				nowrap: true,
				striped: true,	//隔行变色
				collapsible:true,
				//fitColumns:true,
				fit:true,
				loadMsg:'加载数据,请等待...',
				singleSelect:true,
				url:'spd.action',
				onDblClickRow: function() {
				    var selected = $('#puanduant').datagrid('getSelected');
				    if (selected){
				      scan2();
				   }} ,
				columns:[[ 
					{field:'kcmc',title:'课程名',width:getWidth(0.11),align:'left'},	
					{field:'str1',title:'章名',width:getWidth(0.11),align:'left'},	
					{field:'zmc',title:'节名',width:getWidth(0.11),align:'left'},
					{field:'zsdmc',title:'知识点名',width:getWidth(0.09),align:'left'}, 	
					{field:'str3',title:'题干',width:getWidth(0.496),align:'left'},					
					{field:'int2',title:'答案',width:getWidth(0.04),align:'center'},
					{field:'int4',title:'难易度',width:getWidth(0.04),align:'center'}
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
				},{
					id:'houtui',
					text:'后退',
					iconCls:'icon-back',
					handler:function(){
					 	location.reload();
					}
				  }]
			});
			$('#puanduant').datagrid('getPager').pagination({
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
			var selected = $('#puanduant').datagrid('getSelected');
			if(selected){
				//alert(selected.id.sxh+","+selected.zylx);
			var select = {"zylx":2,"zybh":selected.int5};
			  $.messager.confirm('warning','数据将被永久删除，不可恢复',function(selected){
				if(selected){
					$.ajax({
						type:"POST",
						url:"deleteResource1.action",
						data:select,
						success:function callback(r){
							$('#puanduant').datagrid('reload');
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
					url:"querypdsc1.action",
					data:{"queryw":queryWord},
					cache: false,
					success:function(data){
								if(data.a == 1){
									$('#puanduant').datagrid({
					    	            url:'querypdsc.action?queryw='+queryWord
					    	        });
					    	        $('#query').window('close');
					    	        $('#puanduant').datagrid('getPager').pagination({
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
		
		
		//查看判断题详细信息
		
		function scan2(){
			var selected = $('#puanduant').datagrid('getSelected');						
			if(selected){
				$('#scan2').window('open');
				document.getElementById("scan2").style.visibility="visible";
				$('#zsdmc2').val(selected.zsdmc);
				$('#zmc2').val(selected.zmc);
	            $('#kcmc2').val(selected.kcmc);
	            $('#zhangm2').val(selected.str1);	
	            $('#th2').val(selected.int5);
	            var x=selected.str3
	          	//给编辑器设置HTML内容
				KE.html('tg2', x);
	            $('#tg2').val(selected.str3);
	            $('#da2').val(selected.int2);
	            //$('#csrcs1').val(selected.int1);
	           // $('#zqrcs1').val(selected.int7);
	            //$('#nyd1').val(selected.int4);	
	            //$('#md51').val(selected.str2);
			}else{
			    $.messager.alert('警告','请选择一行数据','warning');
			 }
		}
		KE.show({
			id : 'tg2',
			items : [],
			resizeMode : 0,
			afterCreate : function(id) {
			//KE.toolbar.disable(id, []);
			KE.readonly(id);
			KE.g[id].newTextarea.disabled = true;
			}
		});
				
</script>
</head>
<body style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
		<table id="puanduant"></table>
	    			    <div id="scan2" closed="true" class="easyui-window" title="查看详细信息" style="width:420px;height:370px;padding:5px;background: #fafafa;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
			    	<table align="center">						    				
						<tr>
							<td> 
							知识点名称:
							</td>
							<td>										
								<input id="zsdmc2" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>				
						<tr>
							<td> 
							节名称: 
							</td>
							<td>
								<input id="zmc2" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td> 
							课程名称: 
							</td>
							<td>
								<input id="kcmc2" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td> 
							章名称: 
							</td>
							<td>
								<input id="zhangm2" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td> 
							题号: 
							</td>
							<td>
								<input id="th2" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td> 
							题干: 
							</td>
							<td>
								<textarea id="tg2" name="tg" style="width:300px;height:130px;visibility:hidden;"  disabled="true"></textarea>
							</td>
						</tr>
						<tr>
							<td> 
							答案: 
							</td>
							<td>
								<input id="da2" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
					</table>
			   </div>
			   			<div id="query" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;visibility:hidden;"
		    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
		        <div>
		        	<form id="cx">
			            <table>
			                <tr>
			                    <td>
			                    	题号：
			                    </td>
			                      <td><input type="text" name="name" id="name"  class="easyui-numberbox"><span class="style1">*输入数字</span></td>
			                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
			                </tr>
			            </table>
		            </form>
		        </div>
		    </div>		
</body>
</html>