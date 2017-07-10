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
			//多媒体

			$('#dmtzl').datagrid({
				title:'多媒体',
				iconCls:'icon-save',			
				width:getWidth(1),
				nowrap: true,
				striped: true,
				singleSelect:true,
				fit:true,
				//fitColumns:true,
				url:'dmtzlcxlist.action',
				
				onDblClickRow: function() {
					var selected = $('#dmtzl').datagrid('getSelected');
					var zlbh = selected.int1;
					$.ajax({
						type:"POST",
						url:"dmtzlljcx.action",
						data:{"zlbh":zlbh},
						cache: false,
						success:function(data){
							var fielpath = data.message;
							window.location.href='../../spdh/dzbj.jsp?zlmc=' + selected.str3 +
			    			'&path=' + fielpath + '&filename=' + selected.nr + '&zlid=' + selected.int1;
						}
					});
					//window.location.href="dakaidmt.action?dmtzybh="+selected.zybh; //用于跳转到能打开多媒体的页面
		},
				loadMsg:'加载数据,请等待...',
				columns:[[
			                {field:'str1',title:'用户名',width:getWidth(0.1),align:'center'},
							{field:'str2',title:'分类',width:getWidth(0.1),align:'center'},
							{field:'str3',title:'资料名称',width:getWidth(0.146),align:'left'},
							{field:'str5',title:'资料描述',width:getWidth(0.2),align:'left'},
							{field:'str6',title:'资料来源',width:getWidth(0.14),align:'left'},
							{field:'dt',title:'上传日期',width:getWidth(0.15),align:'center'},
							{field:'int2',title:'浏览次数',width:getWidth(0.08),align:'center'},
							{field:'int3',title:'收藏数量',width:getWidth(0.08),align:'center'}
						]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
      				text:'详细详细',
		            iconCls:'icon-search',
		            handler:querydetail
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
</body>
</html>