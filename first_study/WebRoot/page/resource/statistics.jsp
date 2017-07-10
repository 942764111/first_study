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
	getUserQX();
		$(function(){
			$('#test').treegrid({
				title:'资源统计',
				iconCls:'icon-save',
				width:getWidth(1),
				//height:auto,
				fit:true,
				singleSelect:true,
				//collapsible:true,
				nowrap: false,
				//rownumbers: true,
				animate:true,
				//collapsible:true,statistics.jsp
				idField:'cname',
				treeField:'cname',
				url:'Tjlist.action',
				loadMsg:'加载数据,请等待...',
				columns:[[
				      	{field:'cname',title:'课程名称',width:getWidth(0.418),align:'left'},
				      	{field:'czt_sys',title:'操作题数量',width:getWidth(0.1),align:'center'},
				      	{field:'dmt_sys',title:'多媒体数量',width:getWidth(0.1),align:'center'},
				      	{field:'pd_sys',title:'判断题数量',width:getWidth(0.1),align:'center'},
				      	{field:'tk_sys',title:'填空题数量',width:getWidth(0.1),align:'center'},
				      	{field:'xz_sys',title:'选择题数量',width:getWidth(0.1),align:'center'},
				      	{field:'zysc_sys',title:'用户收藏数量',width:getWidth(0.1),align:'center'}
				      	]],
				toolbar:[{
					text:'更新',
					iconCls:'icon-search',
					disabled:getUQX(),
					handler:function(){
						window.location.href='ResourceStatistics.action';
						$('#test').datagrid('reload');
			       }
				}]
			});
		});
		var jieguo=0;
		function getWidth(percent){ 
			return (document.body.clientWidth-25-5)*percent; 
		}
		
		function getUserQX(){
			$.ajax({
				type:"POST",
				url:"panduanbutton.action",
				success:function(data){
							jieguo=data.roleid;
							document.cookie="jieguo="+jieguo;
							var date=new Date();
							//将date设置为过去的时间
							date.setTime(date.getTime()+10);
							//将userId这个cookie删除
							document.cookie="expire="+date.toGMTString();
						}
					});
		}
		function getUQX(){
			var strcookie=document.cookie;
			var arrcookie=strcookie.split("; ");
			var roleid;
			//遍历cookie数组，处理每个cookie对
			for(var i=0;i<arrcookie.length;i++){
			      var arr=arrcookie[i].split("=");
			      //找到名称为userId的cookie，并返回它的值
			      if("jieguo"==arr[0]){
			             roleid=arr[1];
			             break;
			      }
			}
		}
</script>
</head>
<body>
    <table id="test"></table>
</body>
</html>