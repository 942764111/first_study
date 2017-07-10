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
			$.ajax({
		           type:"POST",
		           url:"jcczt.action",
		           dataType:'json',
		           success:function(r){
			           var wab = r.message;
			           if(wab!=""){
			        	   $.messager.alert('提示','您所收藏的题号为'+wab+'的题目已被管理员删除，您对该题目的收藏已无效!');
			           }
		           }
			    });
			//操作题
			$('#czt').datagrid({
				title:'操作题管理',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				fit:true,
				//fitColumns:true,
				width:getWidth(1),
				loadMsg:'加载数据,请等待...',
				url:'getsc.action', 
				onLoadSuccess:function(){
					var data=$('#czt').datagrid('getData');
					if(data.total==0)
					{
					      //$.messager.alert('信息','没有数据','info');
					}				
				},		 
			columns:[[
				 {field:'ck',checkbox:true},
	             {field:'tihao',title:'操作题号',width:getWidth(0.1)},
	             {field:'jytigan',title:'操作题题干描述',width:getWidth(0.8)},//665
	             {field:'fenzhi',title:'分值',width:getWidth(0.075),align:'center'}
	        ]],
			    pagination:true,
				rownumbers:true,
				toolbar:[{					
					text:'题目详细信息',
		              iconCls:'icon-search',
		              handler:ckxxxx
				},{
					text:'删除',
					iconCls:'icon-remove',
					handler:dele
				}]
	    });
		    
	     $('#czt').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
		});
		//宽度
		function getWidth(percent){ 
			return (document.body.clientWidth-25-5)*percent; 
		}
		//高度
		function getHeight(percent){ 
		    return document.body.clientHeight*percent; 
		} 

		//删除======================================
		function dele(){
			var selected = $('#czt').datagrid('getSelected');
			if(selected){
				//alert(selected.id.sxh+","+selected.zylx);
			var select = {"zylx":3,"zybh":selected.tihao};
			  $.messager.confirm('warning','数据将被永久删除，不可恢复',function(selected){
				if(selected){
					$.ajax({
						type:"POST",
						url:"deleteResource1.action",
						data:select,
						success:function callback(r){
							$('#czt').datagrid('reload');
						}
					});
				};
				
			  });
			}else{
			$.messager.alert('warning','请选择一行数据','warning')};
		}
		
			

		
		
		//as1
		  function as1(iframe,width,height)
		  {
			 var iframe1 = $("#".concat(iframe)); 
			 iframe1.width(width);
			 iframe1.height(height); 
		 }
		//查看操作题详细信息
			 function ckxxxx()
		     {
		        var rows = $('#czt').datagrid("getSelections");
		 		if(rows.length>1)
		 		{
		 			$.messager.alert('警告','请选择一行数据','warning');
		 			return;
		 		}
		    	 var selected = $('#czt').datagrid('getSelected');
		    	 if(!selected)
		    	 {
		    		 $.messager.alert('警告','请选择要查询的行','warning');
		        	 return;
		    	 }
		    	    var th={"tihao":selected.tihao};
		    	      $.ajax({
		    	  		type:"POST",
		    	  		url:"searchtihao.action",  
		    	  		data:th,
		    	  		dataType:'json',
		    	  		success:function callback(r){
		    	  	      if(r.message=="success")
		    	  	      {
			    	  	     
		    	  	    	  var percent=(1-800/getWidth(1))/2;
		    	  	    
		    	  	          var p = $('#search1').window({
		    	  				onBeforeOpen:function(){
		    	  				  $('#search1').window('resize',{width:790,height:365,left:getWidth(percent),top:getHeight(0.05)});
		    	  			}
		    	  			}); 
		    	  	   	 
		    	  	         $('#search1').window('open'); 
		    	  	        as1("CFrame2",780,365);
		    	           CFrame2.location=encodeURI("../../cztjsp/Ckczt1.jsp?tihao="+selected.tihao);        
		    	  	      }
		    	  	  
		    	  		}
		    	  		}); 
		     }


		     function ckxt(tihao)
		     {  
		    
		     	   $('#ckxts').window('open');
		     	   $('#ckxts').window('resize',{left:getWidth(0.1),top:getHeight(0.05),width:getWidth(0.78),height:getHeight(0.7)});
		     	       		$('#ckxts1').datagrid({
		     				fit:true,
		                     fitColumns:true,
		     				singleSelect:true,  
		     				loadMsg:'加载数据,请等待...',
		     				onLoadSuccess:function(){
		     					var data=$('#ckxts1').datagrid('getData');
		     					if(data.total==0)
		     					{
		     					      $.messager.alert('信息','没有数据','info');
		     					}				
		     				},
		     				onClickRow:function(index,data){
		     					s(index,"shareit-box4","ckxts1","CFrame10");
		     			},
		     				url:'getallczt.action?tihao='+tihao, 
		     				 columns:[[
		     			 {field:'th',title:'操作题号',width:getWidth(0.75)*0.1,align:'center' },
		                  {field:'sxh',title:'小题题号',width:getWidth(0.75)*0.1,align:'center'},
		                  {field:'tg',title:'小题题干',width:getWidth(0.75)*0.4,align:'center'},
		                  {field:'xtfz',title:'分值',width:getWidth(0.75)*0.1},
		                  {field:'nyd',title:'难易度',width:getWidth(0.75)*0.1},
		                  {field:'csrcs',title:'测试人数',width:getWidth(0.75)*0.1,align:'center'},
		                  {field:'zqrcs',title:'正确人数',width:getWidth(0.75)*0.1,align:'center'}     
		               ]],
		     				rownumbers:true
		     				});
		     }  

		     var ckexceltihao;
		     function ckwj(cktihao)
		     {
		     ckexceltihao=cktihao;
		  	 $('#ckwj').window('open');
		  	 $('#ckwj').window('resize',{left:getWidth(0.1),top:getHeight(0.05),width:getWidth(0.76),height:getHeight(0.68)})
		     $('#ckwj1').datagrid({
		  				fit:true,
		                fitColumns:true,
		  				nowrap: false,
		  				striped: true,
		  				collapsible:true,
		  				singleSelect:true,  
		  				loadMsg:'加载数据,请等待...',
		  				onLoadSuccess:function(){
		  		var data=$('#ckwj1').datagrid('getData');
		  		if(data.total==0)
		  		{
		  		      $.messager.alert('信息','没有数据','info');
		  		}			
		  	},
		  				url:'getallwj.action?tihao='+cktihao, 
		  				 columns:[[
		  				           {field:'bh',title:'文件编号',width:getWidth(0.74)*0.1,align:'center'},
		  							 {field:'dtth',title:'大题号 ',width:getWidth(0.74)*0.1,align:'center'}, 
		  				             {field:'wjmc',title:'文件名称',width:getWidth(0.74)*0.2,align:'center'},
		  				             {field:'ms',title:'文件描述',width:getWidth(0.74)*0.6,align:'center'}  
		            ]],
		  				rownumbers:true
		  				});
		  			
		     }
				     

				
</script>
</head>
<body style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
		<table id="czt"></table>
	    	<div id="search1" closed="true" class="easyui-window"   resizable="true"  title="查询操作题信息"  maximizable="false"  minimizable="false"    iconCls="icon-search" style="background: #fafafa;overflow-x:hidden;overflow-y:hidden;">
				<div  border="false" style="background:#fff;overflow-x:hidden;overflow-y:hidden;">
					<iframe  id="CFrame2" src=""   name="CFrame2"  frameborder="0" scrolling="no"   width="100%"  height="100%"></iframe> 
				</div>
			</div>
			
				
	
	  <div id="ckxts" closed="true"    class="easyui-window" title="查看小题" iconCls="icon-search" maximizable="false"  minimizable="false" style="background: #fafafa;">
		
			<div  border="false" style="background:#fff;width:100%;height:100%;">
     <table id="ckxts1"></table>
	</div>
	</div>
	
	
	

  
  
  
  
  
   <div id="ckda" closed="true" class="easyui-window"   maximizable="false"  minimizable="false" title="查看答案" iconCls="icon-search" >
		
			<div id="ckda1" region="center" border="false" style="background:#fff;overflow-x:hidden;width:100%;height:100%;">
<table id="ckanswer"></table>

	    </div>   
  </div>
  
  
 
  
    <div id="ckwj" closed="true" class="easyui-window"   maximizable="false"  minimizable="false" title="文件" iconCls="icon-search" >
			<div   region="center" border="false" style="background:#fff;width:100%;height:100%;">
   <table id="ckwj1"></table>
	   </div>		
	</div>
</body>
</html>