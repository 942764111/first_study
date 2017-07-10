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
		           url:"jcdmtzl.action",
		           dataType:'json',
		           success:function(r){
			           var wab = r.message;
			           if(wab!=""){
			        	   $.messager.alert('提示','您所收藏的题号为'+wab+'的题目已被管理员删除，您对该题目的收藏已无效!');
			           }
		           }
			  });

			$('#dmtzl').datagrid({
				title:'多媒体收藏',
				iconCls:'icon-save',			
				width:getWidth(1),
				nowrap: true,
				striped: true,
				singleSelect:true,
				fit:true,
				//fitColumns:true,
				url:'dmtzlsclist.action',
				loadMsg:'加载数据,请等待...',
				columns:[[
			                {field:'str1',title:'用户名',width:getWidth(0.1),align:'center'},
							{field:'str2',title:'分类',width:getWidth(0.1),align:'center'},
							{field:'str3',title:'资料名称',width:getWidth(0.15),align:'left'},
							{field:'str5',title:'资料描述',width:getWidth(0.2),align:'left'},
							{field:'str6',title:'资料来源',width:getWidth(0.14),align:'left'},
							{field:'dt',title:'上传日期',width:getWidth(0.148),align:'center'},
							{field:'int2',title:'浏览次数',width:getWidth(0.08),align:'center'},
							{field:'int3',title:'收藏数量',width:getWidth(0.08),align:'center'}
						]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
      				text:'详细详细',
		            iconCls:'icon-search',
		            handler:querydetail
          		},{
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
			$('#dmtzl').datagrid('getPager').pagination({
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
			var selected = $('#dmtzl').datagrid('getSelected');
			if(selected){
				//alert(selected.id.sxh+","+selected.zylx);
			var select = {"zylx":4,"zybh":selected.int1};
			  $.messager.confirm('warning','数据将被永久删除，不可恢复',function(selected){
				if(selected){
					$.ajax({
						type:"POST",
						url:"deleteResource1.action",
						data:select,
						success:function callback(r){
							$('#dmtzl').datagrid('reload');
						}
					});
				};
				
			  });
			}else{
			$.messager.alert('warning','请选择一行数据','warning')};
		}

		//多媒体资料查询
		//查询功能
		function query(){
	        var queryWord = $('#name').val();
	        if(queryWord!=""){
	        	$.ajax({
					type:"POST",
					url:"searchdmtsc1.action",
					data:{"queryw":queryWord},
					cache: false,
					success:function(data){
								if(data.a == 1){
									$('#dmtzl').datagrid({
					    	            url:'searchdmtsc.action?queryw='+queryWord
					    	        });
					    	        $('#query').window('close');
					    	        $('#dmtzl').datagrid('getPager').pagination({
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
		
		 //查看多媒体详细信息
		function querydetail(){	        	
    		var selected = $('#dmtzl').datagrid('getSelected');
        	if(selected){
        		$("#lxm1").val(selected.str2);
        		$("#zlmc1").val(selected.str3);
        		$("#userId1").val(selected.str1);
        		$("#filename1").val(selected.str4);
        		$("#zlly1").val(selected.str6);
        		$("#zlscm1").val(selected.str3);
        		$("#zlms1").val(selected.str5);
        		$("#scrq1").val(selected.dt);
        		$("#llcs1").val(selected.int2);
        		$("#cssl1").val(selected.int3);
        		$("#changdu1").val(selected.int4);
				$("#querydetail").window('open');
				document.getElementById("querydetail").style.visibility="visible";
            }else{
    			$.messager.alert('提示信息','请选择一行数据','info');
        	}
		 }		
</script>
</head>
<body style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
		<table id="dmtzl"></table>
		<div id="querydetail" class="easyui-window" title="查看详细详细" style="padding: 10px;width: 550px;height:420px;visibility:hidden;"
		   	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
			<div>
				<table align="center" width="520"> 
					<tr>
						<td> 
							资料分类:
						</td>
						<td>
							<input type="text" id="lxm1" readonly="readonly" style="border:0px;width:80px;"/>
						</td>
						<td>
							用户名:
						</td>
						<td>
							<input type="text" id="userId1" readonly="readonly" style="border:0px"/>
						</td>
					</tr>
					<tr>
						<td> 
							长度: 
						</td>
						<td>
							<input type="text" id="changdu1" readonly="readonly" style="border:0px"/>
						</td>
						<td> 
							上传日期: 
						</td>
						<td>
							<input type="text" id="scrq1" readonly="readonly" style="border:0px"/>
						</td>
					</tr>
					<tr>
						<td> 
							浏览次数: 
						</td>
						<td>
							<input type="text" id="llcs1" readonly="readonly" style="border:0px"/>
						</td>
						<td> 
							收藏数量: 
						</td>
						<td>
							<input type="text" id="cssl1" readonly="readonly" style="border:0px"/>
						</td>
					</tr>
				</table>
				<table align="center" width="520">
					<tr>
						<td>
							资料名称:
						</td>
						<td>
							<textarea rows="2" readonly="readonly" cols="19" id="zlmc1" style="width:400px;">
							</textarea>
						</td>
					</tr>
					<tr>
						<td>
							文件名:
						</td>
						<td>
							<textarea rows="2" readonly="readonly" cols="19" id="filename1" style="width:400px;">
							</textarea>
						</td>
					</tr>
					<tr>
						<td> 
							资料来源:
						</td>
						<td>
							<textarea rows="2" readonly="readonly" cols="19" id="zlly1" style="width:400px;">
							</textarea>
						</td>
					</tr>
					<tr>
						<td> 
							资料素材名:
						</td>
						<td>
							<textarea rows="2" readonly="readonly" cols="19" id="zlscm1" style="width:400px;">
							</textarea>
						</td>
					</tr>
					
					<tr>
						<td> 
							资料描述:
						</td>
						<td>
							<textarea rows="5" readonly="readonly" cols="19" id="zlms1" style="width:400px;">
							</textarea>
						</td>
					</tr>
				</table>
		    </div>
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