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
			//选择题

			$('#xuanzet').datagrid({
				title:'选择题',
				iconCls:'icon-save',			
				width:getWidth(1),
				nowrap: true,
				striped: true,
				singleSelect:true,
				fit:true,
				//fitColumns:true,
				url:'cxxz.action',
				loadMsg:'加载数据,请等待...',
				onDblClickRow: function() {
				    var selected = $('#xuanzet').datagrid('getSelected');
				    if (selected){
				      scan1();
				   }} ,
				columns:[[     
					{field:'tcname',title:'课程名',width:getWidth(0.1),align:'center'},
					{field:'kcmc',title:'章名',width:getWidth(0.1),align:'center'},                          
					{field:'zmc',title:'节名',width:getWidth(0.1),align:'center'},
					{field:'zsdmc',title:'知识点名',width:getWidth(0.1),align:'center'},			
					{field:'str3',title:'题干',width:getWidth(0.1),align:'left'},
					{field:'str4',title:'A',width:getWidth(0.1),align:'left'},
					{field:'str5',title:'B',width:getWidth(0.1),align:'left'},
					{field:'str6',title:'C',width:getWidth(0.1),align:'left'},
					{field:'str7',title:'D',width:getWidth(0.1),align:'left'},
					{field:'str1',title:'答案',width:getWidth(0.05),align:'center'},
					{field:'int4',title:'难易度',width:getWidth(0.05),align:'center'}
				]],
				pagination:true,
				rownumbers:true
			});
			$('#xuanzet').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
				 });
		});
		//宽度
		function getWidth(percent){ 
			return (document.body.clientWidth-25-5)*percent; 
		}
		//查看选择题详细信息

		function scan1(){
					var selected = $('#xuanzet').datagrid('getSelected');						
					if(selected){				
						$('#scan1').window('open');
						document.getElementById("scan1").style.visibility="visible";
						$('#zsdmc1').val(selected.zsdmc);
						$('#zmc1').val(selected.zmc);
			            $('#kcmc1').val( selected.kcmc);
			            $('#tcname1').val( selected.tcname);
			            $('#th1').val(selected.int5);
			            var x=selected.str3
			          	//给编辑器设置HTML内容
						KE.html('tg1', x);
			            $('#xx11').val(selected.str4);
			            $('#xx21').val(selected.str5);
			            $('#xx31').val(selected.str6);
			            $('#xx41').val(selected.str7);
			            $('#ddx1').val(selected.int2);
			            $('#da1').val(selected.str1);
			            $('#csrcs1').val(selected.int1);
			            $('#zqrcs1').val(selected.int7);
					}else{
					    $.messager.alert('警告','请选择一行数据','warning');
					 }
							
				}
				KE.show({
					id : 'tg1',
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
		<table id="xuanzet"></table>
				    	<div id="scan1" closed="true" class="easyui-window" title="查看详细信息" style="width:450px;height:400px;padding:5px;background: #fafafa;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
					    	<table align="center">						    				
								<tr>
									<td> 
									知识点名:
									</td>
									<td>										
										<input id="zsdmc1" type="text" style="width:300px;" disabled="true"/>
									</td>
								</tr>				
								<tr>
									<td> 
									节名: 
									</td>
									<td>
										<input id="zmc1" type="text" style="width:300px;"  disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									章名: 
									</td>
									<td>
										<input id="kcmc1" type="text" style="width:300px;" disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									课程名: 
									</td>
									<td>
										<input id="tcname1" type="text" style="width:300px;" disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									题号: 
									</td>
									<td>
										<input id="th1" type="text" style="width:300px;" disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									题干: 
									</td>
									<td>
										<textarea id="tg1" name="tg" style="width:300px;height:130px;visibility:hidden;" disabled="true"></textarea> 					 
									</td>
								</tr>
								<tr>
									<td> 
									选项一: 
									</td>
									<td>
										<input id="xx11" type="text"  style="width:300px;" disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									选项二: 
									</td>
									<td>
										<input id="xx21" type="text"  style="width:300px;" disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									选项三: 
									</td>
									<td>
										<input id="xx31" type="text" style="width:300px;"  disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									选项四: 
									</td>
									<td>
										<input id="xx41" type="text" style="width:300px;"  disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									答案: 
									</td>
									<td>
										<input id="da1" type="text"  style="width:300px;"  disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									测试次数: 
									</td>
									<td>
										<input id="csrcs1" type="text" style="width:300px;"  disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									正确次数: 
									</td>
									<td>
										<input id="zqrcs1" type="text" style="width:300px;"  disabled="true"/>
									</td>
								</tr>
							</table>
					</div>
</body>
</html>