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
				title:'用户收藏情况',
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
				onDblClickRow: function() {
					find();
				},
				fit:true,
				//fitColumns:true,
				width:getWidth(1),
				columns:[[
				      	{field:'zdyflno',title:'自定义分类序号',width:getWidth(0.1),align:'center',editor:'text'},
				      	{field:'zdyflmc',title:'自定义分类名称',width:getWidth(0.3),align:'center',editor:'text'},
				      	{field:'zysl',title:'收藏资源数量',width:getWidth(0.575),align:'center',editor:'text'} 
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
				},/*{
					text:'详细查看',
					iconCls:'icon-search',
					handler:find
				},{
					text:'收藏概况',
					iconCls:'icon-search',
					handler:function(){
						addTab("收藏概况","page/collection/shoucanggk.jsp");
      				}
				},*/{
					text:'选择题收藏',
					iconCls:'icon-search',
					handler:function(){
						addTab("选择题收藏","page/collection/xzshoucang.jsp");
      				}
				},{
					text:'判断题收藏',
					iconCls:'icon-search',
					handler:function(){
						addTab("判断题收藏","page/collection/pdshoucang.jsp");
	  				}
				},{
					text:'操作题收藏',
					iconCls:'icon-search',
					handler:function(){
					addTab("操作题收藏","page/collection/cztshoucang.jsp");
  				}
				},{
					text:'多媒体收藏',
					iconCls:'icon-search',
					handler:function(){
					addTab("多媒体收藏","page/collection/dmtshoucang.jsp");
  				}
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
			var selected = {"yhzdymc.zdyflmc":$("#zdyflmc_add").val(),"yhzdymc.zysl":0};
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
			  $.messager.confirm('warning','该分类被删除后，将同时删除您在该分类下收藏的题目，您确定要删除吗',function(selected){
				if(selected){
					$.ajax({
						type:"POST",
						url:"deleteyhzdymc.action",
						data:select,
						success:function callback(r){
							$.messager.alert('warning','删除成功','info');
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
			});
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
	<div id="tt" class="easyui-tabs">
  		<div title="用户收藏情况">
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
				<table>
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
	</div>
	</div>
</body>
</html>