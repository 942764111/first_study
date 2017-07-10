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
	<!-- autocomplete -->
	<script type='text/javascript' src='js/auto/jquery.autocomplete.js'></script>
	<link rel="stylesheet" type="text/css" href="js/auto/jquery.autocomplete.css" />

	
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
		var queryword=["ddd","sssss"];

	          	
		$().ready(function() {

				$("#queryword").autocomplete("suggest.action",{
					minChars: 1,
		            max: 20,
		            delay: 500,
		            //matchContains:true,
		            scrollHeight: 300,
		            scroll:true,
		            dataType:'json',//返回数据类型
		            extraParams:{   
		                radio2:function(){
						var radio1 = $('[name=radio]:radio:checked').val();
		                return radio1;//url的参数传递   
		                }   
		            },   
					parse: function(r) {
			            var rows1 = [];
			            for(var i=0; i<r.rows.length; i++){
			            rows1[rows1.length] = {
		                    data:r.rows[i],
		                    value:r.rows[i],
		                    result:r.rows[i]
		                    };
		                }
			                return rows1;
			        },
		           formatItem:function(row, i, n) { 
		               return "<font color='#990099'>"+row+"</font>";    
		          } 
			}).result(function() {
				check();
			});
		});
	</script>
	<script type="text/javascript">
	//验证
	function check(){
		if($('#queryword').val()==""){
			$.messager.alert('warning','请输入要查询的关键字','warning');
		}
		else {
			search();
		}
	}
	//查询提交
	function search(){
		var queryword = $('#queryword').val();
		var radio = $('[name=radio]:radio:checked').val();
		//alert(zy);
		//alert(radio);

		$.ajax({
	           type:"POST",
	           url:"zy.action",
	           data:{"zyqueryword":queryword,"radio":radio},
	           success:function(){
	        	   window.location.href='zy_tz.action';
			   }
	 		});
		
	}


	

	</script>
	
</head>
<body>
    <div id="p" class="easyui-panel" title="资源查询" icon="icon-save" collapsible="true" fit="true" style="padding:10px;background:#fafafa;">
    	<div style="margin-top: 180px;margin-left: 380px;">
			 <form id="zycx" method="get">
			 	<input type="text" style="width:300px;height:25px;border:1px solid blue;" id="queryword" name="queryword" autocomplete="off">
			 	
			 	
			 	<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="check()">资源查询</a> 
			 	<!-- <input type="button" style="height:28px;" onclick="check()" value="资源查询"> -->
			 	<!-- suggest 搜索建议功能-->
			 	<br/>
			 	<div id="content"></div>
			 	<input type="radio" id="radio" name="radio" value="1" checked="checked">按知识点&nbsp&nbsp
			 	<input type="radio" id="radio" name="radio" value="2">按关键字
			 	
			 </form>
		 </div>
		<!-- <p>
			<label>Web Site:</label>
			<input type="text" id="website" />
			<input type="button" id="getvalue" value="Get Value" />
		</p> -->
		
	</div>
	
	
	
	
	
</body>
</html>