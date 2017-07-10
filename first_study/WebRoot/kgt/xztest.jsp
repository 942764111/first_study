<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择题测试</title>

<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">

	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
<script type="text/javascript">


//***************资源收藏**********************
var values = document.getElementsByName("tg");
var th = document.getElementsByName("th");
var zhangming = document.getElementsByName("zhangming");
$(function(){
	for(var j=0;j<values.length;j++){
		$("#"+j).append('<a class="easyui-window" href="javascript:void(0)" onclick="shoucang('+j+')">收藏本题</a>');
		}
});

function shoucang(h){
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
			
		$('#addxzsc').window('open');
		//document.getElementById("scform").style.visibility="visible";
		$('#scth').val(th[h].value);
		$('#sczmc').val(zhangming[h].value);
		$('#sctg').val(values[h].value);
}


function ShouCang(){
	var scth = $('#scth').val();
	var sczmc = $('#sczmc').val();
	var zdyflmc = $('#zdyflmc').combobox('getValue');
	var zyms = $("#sctg").val();
	if(!(zdyflmc==null)){
		$.ajax({
	           type:"POST",
	           url:"addXzcollection.action",
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
	   $('#addxzsc').window('close');
}

//保存选择题答案
function bcda(){
	$.ajax({
		type:"POST",
	   	url:"saveanswer",
	   	data:$('#x').serialize(),//这样做后台saveanswer.action的json数据就不会返回到前台了，前台就不会出现让下载返回结果的对话框了
	   	success:function(){
		$.messager.alert('提示信息','保存成功！！','info');
	   	}
	});
}

</script>
</head>
  <body style="font-size:12px;">
		<form id="x" action="saveanswer" method="post" fit="true" target="form_iframe">
			<s:iterator value="xzlist1" var="xzlist1"  status="stat" id="XuanZ"> 
				<s:if test="ddx==0">               
					<table>
						<tr>  
		                    <td width="770" height="25">  
		                        <font color="green">  
			                        <s:property value="#stat.index+1" />                          .                            
			                        <s:property value="tg" />[单选，1分]
			                        <s:hidden name="th" value="%{th}" />
			                        <s:hidden name="tg" value="%{tg}"/>
			                        <s:hidden name="zhangming" value="%{zhangname}"/>
			                        <s:hidden name="da1" value="%{da}"/>
	                          		<s:hidden name="zsdbh1" value="%{zsdbh}"/>    
		                        </font>  
		                    </td>  
		                    <td><s:div id="%{#stat.index}" /></td>
		                </tr>
	                 	<tr>  
	                     <td height="40" width="770" align="left">  
	                         <s:radio list='#{"A":"A、"+xx1, "B":"B、"+xx2, "C":"C、"+xx3,"D":"D、"+xx4}' name="answer[%{#stat.index}]" theme="simple"/>  
	                     </td>  
	                 	</tr>
					</table>
					<hr>
				</s:if>
				<s:else>
		          <table>
					  <tr>  
			             <td width="770" height="25">  
			               <font color="green">  
				               <s:property value="#stat.index+1" />                          .  
				               <s:property value="tg" />[多选，1分]
				               <s:hidden name="tg" value="%{tg}"/>
				                <s:hidden name="th" value="%{th}" />
				                  <s:hidden name="zhangming" value="%{zhangname}"/>
				               <s:hidden name="da1" value="%{da}"/>
		                       <s:hidden name="zsdmc1" value="%{zsd.zsdmc}"/>   
			               </font>  
			            </td> 
			            <td><s:div id="%{#stat.index}" /></td>
			          </tr>
			          <tr>  
			             <td height="40" width="770" align="left">  
			                  <s:checkboxlist list='#{"A":"A、"+xx1, "B":"B、"+xx2, "C":"C、"+xx3,"D":"D、"+xx4}' name="answer[%{#stat.index}]" theme="simple"/>
			             </td>  
			          </tr> 
			       </table>
			       <hr/>
		        </s:else>
			</s:iterator>
			<input id="tj" type="button" onclick="bcda();disabled=true" value="点此保存本页答案以便提交"/>
			</form>
		<iframe name="form_iframe" width="0" height="0" scrolling="no"></iframe>
		<center>
			<div id="zy" style="color:blue;">(共有<s:property value="zy"/>页,当前是第<s:property value="p"/>页)</div>
		</center>
		
		<div id="addxzsc" class="easyui-window" closed="true" title="添加收藏" icon="icon-add" style="width:450px;height:300px;">
		   	<center>
		   		<s:form id="scform">
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
