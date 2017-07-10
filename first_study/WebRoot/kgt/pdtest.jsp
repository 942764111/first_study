<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>判断题测试</title>
<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">

	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
<script type="text/javascript">

//***************资源收藏**********************
var pdvalues = document.getElementsByName("tg");
var pdth = document.getElementsByName("th");
var pdzhangming = document.getElementsByName("zhangming");
$(function(){
	for(var i=0;i<pdvalues.length;i++){
		var j=i+4;
		$("#"+j).append('<a class="easyui-window" href="javascript:void(0)" onclick="shoucangpd('+j+')">收藏本题</a>');
		}
});

function shoucangpd(h){
		$.ajax({
				type:'post',
				url:'listyhzdy.action',
				success:function(data){
					var a = data.rows;
					$('#zdyflmc').combobox({
						data:a,
						valueField:'value',
						textField:'value'
					});
				}
				});

		$('#addsc').window('open');
		$('#scth').val(pdth[h-4].value);
		$('#sczmc').val(pdzhangming[h-4].value);
		$('#sctg').val(pdvalues[h-4].value);
}


function ShouCang(){
	var scth = $('#scth').val();
	var sczmc = $('#sczmc').val();
	var zdyflmc = $('#zdyflmc').combobox('getValue');
	var zyms = $("#sctg").val();
	if(!(zdyflmc==null)){
		$.ajax({
	           type:"POST",
	           url:"addPdcollection.action",
	           data:{"zybh":scth,"sskcmc":sczmc,"zdyflmc":zdyflmc,"zyms":zyms},
	           success:function(r){
		           var wab = r.message;
		           if(wab==1){
		        	   $.messager.alert('提示','您已成功收藏所选数据!');
		        	   close6();
		           }
		           else {
		        	   close6();
		        	   $.messager.alert('提示','该题目已经被收藏过，不用再收藏啦!');
				   }
	           }
		    });
	}
	else {
		$.messager.alert('警告','请选择您的自定义分类','warning');
	}
	
}


function close6(){
	   $('#addsc').window('close');
}


//保存判断题答案
function bcda(){
	$.ajax({
		type:"POST",
	   	url:"saveanswerp",
	   	data:$('#pd').serialize(),//这样做后台saveanswer.action的json数据就不会返回到前台了，前台就不会出现让下载返回结果的对话框了
	   	success:function(){
		$.messager.alert('提示信息','保存成功！！','info');
	   	}
	});
}

</script>
</head>
  <body  style="font-size:12px;">
	<s:form id="pd" fit="true" action="saveanswerp" method="post" target="form_iframe">		
			<s:iterator value="pdlist1" var="pdlist1"  status="stat" id="PanDuan"> 
					<table>
						 <tr> 
	                     	<td width="700" height="30">  
	                        	<font color="green">   
	                         	<s:property value="#stat.index+1" />                          .  
	                         	<s:property value="str1" />     [1分]  
	                         	<s:hidden name="da2" value="%{int1}"/>
		                       <s:hidden name="zsdbh1" value="%{int2}"/> 
		                       <s:hidden name="th" value="%{int4}" />
		                       <s:hidden name="zhangming" value="%{zmc}"/>
		                       <s:hidden name="tg" value="%{str1}"/>   
	                         	</font>  
	                     	</td> 
	                     	<td><s:div id="%{#stat.index+4}" /></td>
	                     	</tr>
	                     	<tr>
	                    	 <td height="30" width="700"  align="left">  
	                         	<s:radio list='#{1:"T", 0:"F"}' name="answer[%{#stat.index}]" theme="simple"/>  
	                     	</td>  
	                	 </tr>
					</table>
					<hr/>
		 	</s:iterator>
		 <input id="tj" type="button" onclick="bcda();disabled=true" value="请保存本页答案"/>
		</s:form>
		<iframe name="form_iframe" width="0" height="0" scrolling="no"></iframe>
		<center>
			<div id="zy" style="color:blue;">(共有<s:property value="zy"/>页,当前是第<s:property value="p"/>页)</div>
		</center>
		<div id="addsc" class="easyui-window" closed="true" title="添加收藏" icon="icon-add" style="width:450px;height:300px;">
	   	<center>
	   		<s:form id="scpdform">
	   			<table>
	   				<tr>
						<td> 
						题号: 
						</td>
						<td>
							<input id="scth" type="text" readonly="true" style="width:250px;"/>
						</td>
					</tr>
					<tr>
						<td> 
						章名称: 
						</td>
						<td>
							<input id="sczmc" type="text" readonly="true" style="width:250px;"/>
						</td>
					</tr>
					
					<tr>
						<td> 
						资源描述: 
						</td>
						<td>
							<textarea id="sctg" name="sctg" style="width:250px;height:100px;" readonly="true"></textarea> 										 
						</td>
					</tr>	
						<tr>
						<td> 
						自定义分类名称: 
						</td>
						<td>
							<input id="zdyflmc"  class="easyui-combobox" name="language" style="width:250px;"/>
						</td>
					</tr>
					<tr>
						<td>
							<a class="easyui-linkbutton" iconCls="icon-ok" 
		   					href="javascript:void(0)" onclick="ShouCang()">收藏</a>
		   				&nbsp;&nbsp;&nbsp;&nbsp;
						
						</td>
						<td>
							<a class="easyui-linkbutton" iconCls="icon-cancel"
							href="javascript:void(0)" onclick="close6()">取消</a>
						</td>
					</tr>
	   			</table>
	   		</s:form>
	   	</center>
	   </div>
  </body>
</html>
