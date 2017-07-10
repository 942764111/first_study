<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>weihu</title>
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
	<script type="text/javascript">
	$(function(){
		$('#test').datagrid({
			title:'分类维护',
			iconCls:'icon-save',
			width:978,
			height:550,
			fitColumns:true,
			fit:true,
			pageList:[15,20,30,40,50],
			width:getWidth(1),
			striped: true,
			url:'weihulist.action',
			idField:'mname',
			frozenColumns:[[
                {field:'ck',checkbox:true}
			]],
			columns:[[		
					{title:'模块名称',field:'mname',width:getWidth(0.519),align:'center'},
			        {title:'ID',field:'molderid',width:getWidth(0.45),align:'center'}
					]],
			pagination:true,
			rownumbers:true,
			toolbar:[{
				id:'btnadd',
				text:'提交',
				iconCls:'icon-add',
				handler:function(){
					sub();
				}
			},'-'],
			onLoadSuccess:function(){
					aaa();
			}
		});
		 $('#test').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
		 
	});
	
	function sub(){
		var selected = $('#test').datagrid('getSelections');
		var arr = new Array();
		for(var i=0;i<selected.length;i++){
			arr[i] = selected[i].mname;
		}
		$.ajax({
            type:"POST",
            url:"weihutijiao.action",
            data:"mnames="+arr,
           success:function(){
			 $('#test').datagrid('reload');
			 $.messager.alert('提示','维护成功！','info');
	         }
      	});
	}
	function aaa(){
		var select = $('#test').datagrid('getRows');
		for(var i=0;i<select.length;i++){
			if(select[i].shifou=="Y"){
				$('#test').datagrid('selectRow',i);
				}
			}
		}
	function getWidth(percent){ 
		return (document.body.clientWidth-25-5)*percent; 
	}
	</script>

  </head>
  
  <body>
   <table id="test"></table>
  </body>
</html>
