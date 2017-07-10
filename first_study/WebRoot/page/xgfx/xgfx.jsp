
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>查询模块列表</title>
 
 
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	
    <link rel="stylesheet" type="text/css" href="css/thickbox.css" />
    
	
	<script type='text/javascript'>
	 $(function(){
		   
			$('#test').datagrid({
				title:'选择试卷---页面',
				iconCls:'icon-save',
				width:978,//978
				
				height:550,
				pageSize:15,
				pageList:[15,30,40,50],
				nowrap: false,
				striped: true,
				url:'listSjnr',
				loadMsg:'处理中，请等待...',
				fit:true,
				fitColumns:true,
				frozenColumns:[[
	                {field:'ck',checkbox:true}
	                
				]],
				columns:[[
				    {title:'试卷编号',field:'int1',width:100},
					{field:'str4',title:'试卷名称',width:500},
					{field:'str1',title:'出卷时间',width:200},
					{field:'int2',title:'试卷份数',width:100},
					{field:'str2',title:'是否分析',width:100}
				]],
				pagination:true,                             //显示分页
				rownumbers:true,                             //显示行号
				toolbar:[{
					
					text:'确定',
					iconCls:'icon-ok',
					handler:function(){
					    var selected = $('#test').datagrid('getSelections');
					    if(selected.length==1){
					    	  if(selected[0].int2 > 0){
					    		    location.href = 'xzsj.action?sjnno='+selected[0].int1;
							        window.event.returnValue = false;
						    	  }else{
						    		     $.messager.alert('提示','您晚点再来分析吧，该试卷还没有学生使用呢！','info');
							    	  }
					      
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    }
					    
					}
				
				},'-']
			});
			$('#test').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to} 共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
		
		});
	  
  </script>
  
  
</head>
<body >

	
	   <table id="test"></table>
	

</body>
</html>