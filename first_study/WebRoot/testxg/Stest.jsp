<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>jQuery EasyUI</title>
		<link href="css/wjh_main.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		 function getWidth(percent){ 
			    return (document.body.clientWidth-25-11)*percent; 
			}
		$(function(){ 
			$('#test_t').datagrid({
				title:'学生测试页面',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				fit:true,
				fitColumns:true,	
				toolbar:[{
					id:'btnadd',
					text:'查看试卷',
					iconCls:'icon-search',
					handler:function(){
                   $('#input1').window('open');
                   document.getElementById("input1").style.visibility="visible";	
                }
				},'-',{
					 
					  text:'进行测试',
		              iconCls:'icon-search',
		              handler:function(){
		             $('#input2').window('open');
		             document.getElementById("input2").style.visibility="visible";	
		              }
				}
				
				]
	   });
	});
		
//查看试卷用三级联动
    var kcmc="";
    var zmczj="";
    var jmczj="";
    var zmcarray=new Array();
    var zmcarray1=new Array();
    var kmcarray=new Array();
    var kmcarray1=new Array();
    $(function(){ 
       $.ajax({
                 		type:"POST",
                 		url:"<%=basePath%>getkcmc",
                 		dataType:'json',
                 		success:function callback(r){
                 		kcmc=r.kcdto;
                 		 $('#kcmc1').combobox({
  	    	    data:kcmc,
  				valueField:'id',
  				textField:'value',
  			    editable:false 
  			});
  			
  			
  				 $('#kcmc1').combobox({
 		   onSelect:function(){
			 var kmc=productFormatter5($("#kcmc1").combobox("getValues"));
	
   			 
				 $.ajax({
                 		type:"POST",
                 		url:"<%=basePath%>getzmc",
                 		data:"kcmc="+kmc,
                 		dataType:'json',
                 		success:function callback(r){
                 			 $('#zmc1').combobox({
        		   	    	    data:r.zdto,
        		   				valueField:'id',
        		   				textField:'value',
        		   			    editable:false 
        		   			});
                 			 zmczj=r.zdto;
                 		}
              			});
			  
 		 }}); 
 		 
 		 
 		  $('#zmc1').combobox({
   		   onSelect:function(){    		      
				  var zmc=productFormatter6($("#zmc1").combobox("getValues"));
				 
				  var kmc1=productFormatter5($("#kcmc1").combobox("getValues"));
			
		
   			 var kz={"zmc":zmc,"kcmc":kmc1};
				 $.ajax({
                 		type:"POST",
                 		url:"<%=basePath%>getsjbh",
                 		data:kz,
                 		dataType:'json',
                 		success:function callback(r){
                 			 $('#jmc1').combobox({
        		   	    	    data:r.sjbhdto,
        		   				valueField:'id',
        		   				textField:'value',
        		   			    editable:false 
        		   			});
                 			jmczj=r.sjbhdto;
                 		}
              			});
			 
		 }});                
              }
              });
              });
              			
              			



//测试用


    $(function(){ 
       $.ajax({
                 		type:"POST",
                 		url:"<%=basePath%>getkcmc",
                 		dataType:'json',
                 		success:function callback(r){
                 		kcmc=r.kcdto;
                 		 $('#kcmc').combobox({
  	    	    data:kcmc,
  				valueField:'id',
  				textField:'value',
  			    editable:false 
  			});
  			
  			
  				 $('#kcmc').combobox({
 		   onSelect:function(){
			 var kmc=productFormatter5($("#kcmc").combobox("getValues"));
   			 
				 $.ajax({
                 		type:"POST",
                 		url:"<%=basePath%>getzmc",
                 		data:"kcmc="+kmc,
                 		dataType:'json',
                 		success:function callback(r){
                 			 $('#zmc').combobox({
        		   	    	    data:r.zdto,
        		   				valueField:'id',
        		   				textField:'value',
        		   			    editable:false 
        		   			});
                 			 zmczj=r.zdto;
                 		}
              			});
 		 }}); 
 		 
 		 
 		  $('#zmc').combobox({
   		   onSelect:function(){    		      
				  var zmc=productFormatter6($("#zmc").combobox("getValues"));
				  var kmc1=productFormatter5($("#kcmc").combobox("getValues"));

   			 var kz={"zmc":zmc,"kcmc":kmc1};
				 $.ajax({
                 		type:"POST",
                 		url:"<%=basePath%>getsjbhcs",
                 		data:kz,
                 		dataType:'json',
                 		success:function callback(r){
                 			 $('#jmc').combobox({
        		   	    	    data:r.sjbhdto,
        		   				valueField:'id',
        		   				textField:'value',
        		   			    editable:false 
        		   			});
         		   			jmczj=r.sjbhdto;
                 		}
              			});
		 }});                
              }
              });
              });
              			
              			
    function productFormatter5(value){
	for(var i=0; i<kcmc.length; i++){
		if (kcmc[i].id==value) return  kcmc[i].value;
	}
	return value;
}
function productFormatter6(value){
	for(var i=0; i<zmczj.length; i++){
		if (zmczj[i].id==value) return  zmczj[i].value;
	}
	return value;
}
//获得试卷编号（String类型）
function productFormatter7(value){
		for(var i=0; i<jmczj.length; i++){
			if (jmczj[i].id==value) return  jmczj[i].sjbh;
		}
		return value;
	}
//获得试卷名称
function productFormatter8(value){
	for(var i=0; i<jmczj.length; i++){
		if (jmczj[i].id==value) return  jmczj[i].value;
	}
	return value;
}



		 //查看试卷用
    function input1(){
    	var zmc=$("#zmc1").combobox("getValue");
    	
		if(zmc=="--请选择章名称--")
	 {
		 $.messager.alert('警告','请将查询 所需的 课程 章 填充完全','warning');
   	 return;
	 }
	var jmc=$("#jmc1").combobox("getValue");
	var jmc1=productFormatter7($("#jmc1").combobox("getValue"));//试卷编号
	var jmc2=productFormatter8($("#jmc1").combobox("getValue"));//试卷名称
	//alert(jmc);
	//alert(jmc1);
	//alert(jmc2);
	if(jmc=="--请选择试卷名称--")
	 {
		 $.messager.alert('警告','请将查询 所需的试卷编号填充完全','warning');
  	 return;
	 }
    var input = {"zbh":zmc,"sjbh2":jmc1,"sjname":jmc2};

 $.ajax({
	type:"POST",
	url:"sjxztnr.action",
	data:input,
	success:function(){
		window.location.href="sj.action";
		window.event.returnValue = false;
	}
	});
 $('#input1').window('close');
 $('#test_t').datagrid('reload');
 }
	
	
	 function close1(){
     $('#input1').window('close');
     }
     



//测试试卷用
    function cs(){
    	var zmc=$("#zmc").combobox("getValue");
		if(zmc=="--请选择章名称--")
		 {
			 $.messager.alert('警告','请将查询 所需的章 填充完全','warning');
	   	 return;
		 }
		var jmc=$("#jmc").combobox("getValue");
		var jmc1=productFormatter7($("#jmc").combobox("getValue"));//试卷编号
		var jmc2=productFormatter8($("#jmc").combobox("getValue"));//试卷名称
		//alert(jmc);
		//alert(jmc1);
		//alert(jmc2);
		if(jmc=="--请选择试卷名称--")
		 {
			 $.messager.alert('警告','请将查询 所需的试卷名称填充完全','warning');
	  	 return;
		 }
    var a = {"zbh":zmc,"sjbh2":jmc1,"sjname":jmc2};
 $.ajax({
	type:"POST",
	url:"sjxztnr.action",
	data:a,
	success:function(){
		window.location.href="csj.action";
		window.event.returnValue = false;
	}
	});
 $('#cs').window('close');
 $('#test_t').datagrid('reload');
 }
	
	
	 function close3(){
     $('#input2').window('close');
     }

</script>
</head>
	<body  class="easyui-layout">
		<div region="center" title="" style="overflow:hidden;">
			<table id="test_t" class="easyui-datagrid">
				<thead>
					<tr>
						<th field="f1" width="70">编号</th>
						<th field="f2" width="450">说明</th>	
						<th field="f3" width="450">备注</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td align="center">本界面为学生测试界面</td>
						<td align="center">权限受控制</td>
					</tr>
					
					<tr>
						<td>2</td>
						<td align="center">本界面用来查看试卷和测试</td>
						<td align="center">权限受控制</td>
					</tr>
				</tbody>
			</table>
		 	<div id="input1" closed="true" maximizable="false" minimizable="false" collapsible="false" class="easyui-window" title="查看试卷" iconCls="icon-input" style="width:380px;height:270px;padding:5px;background: #fafafa;visibility:hidden;">
				<div class="easyui-layout" fit="true">
					<div region="center" border="false" style="padding:24px;background:#fff;border:6px solid #ccc;">
				 		<table>
							<tr>
								<td align="left">
									课程名称:
									<select id="kcmc1" class="easyui-combobox" style="width: 200px;">
									<option  >--请选择课程名称--</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="left">
									章&nbsp;&nbsp;名&nbsp;&nbsp;称:
									<select id="zmc1" class="easyui-combobox" style="width: 200px;">
									<option  >--请选择章名称--</option>
									</select>
								</td>
							</tr>
	
							<tr>
								<td align="left">
									试卷名称:
									<select id="jmc1" class="easyui-combobox" style="width: 200px;">
									<option  >--请选择试卷名称--</option>
									</select>
								</td>
							</tr>
						</table>
						<div region="south" border="false" style="text-align:right;height:60px;line-height:60px;">
							<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="input1()">确定</a>
							<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close1()">取消</a>
						</div>
			       </div>
				</div>
			</div>
			<div id="input2" closed="true" maximizable="false" minimizable="false" collapsible="false" class="easyui-window" title="测试选择试卷" iconCls="icon-input" style="width:380px;height:270px;padding:5px;background: #fafafa;visibility:hidden;">
				<div class="easyui-layout" fit="true">
					<div region="center" border="false" style="padding:24px;background:#fff;border:6px solid #ccc;">
				 		<table>
							<tr>
								<td align="left">
									课程名称:
									<select id="kcmc" class="easyui-combobox" style="width: 200px;">
									<option  >--请选择课程名称--</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="left">
									章&nbsp;&nbsp;名&nbsp;&nbsp;称:
									<select id="zmc" class="easyui-combobox" style="width: 200px;">
									<option  >--请选择章名称--</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="left">
									试卷名称:
									<select id="jmc" class="easyui-combobox" style="width: 200px;">
									<option  >--请选择试卷名称--</option>
									</select>
								</td>
							</tr>
						</table>
						<div region="south" border="false" style="text-align:right;height:60px;line-height:60px;">
							<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="cs()">确定</a>
							<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close3()">取消</a>
						</div>
			       </div>
				</div>
			</div>
		</div>
	</body>
</html>
