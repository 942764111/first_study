<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
     <link href="../css/wjh_main.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="../js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../js/ui/themes/icon.css">
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="../kindeditor/kindeditor-min.js"></script>

 <style type="text/css">
      #tipmessage
      {
        display:none;
        z-index:1000;
        border:1px solid #448833;
        position:absolute;
        background:#ffffff;
        max-width:300px;
        max-height:40px;
        padding:5px 10px;
      }
      
      
	
		#shareit-url {
			height:100px;
			text-align:center;
		}
		#shareit-box {
	position:absolute;
	display:none;
}
    </style>

	

<script>

var shumu=0;
$(function(){
	 KE.show({    
	     	id : 'addtg',//TEXTAREA输入框的ID 
	    	items : ['fullscreen', 'undo', 'redo', 'cut', 'copy', 'paste','plainpaste', 
						'wordpaste', 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 
						'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent',
				 		'subscript','-','superscript','selectall', 'title', 'fontname', 'fontsize', 
				 		'textcolor', 'bgcolor', 'bold','italic', 'underline', 'strikethrough',
				 		'removeformat','image', 'hr', 'emoticons'
				 	],
	     	imageUploadJson : '../../jsp/upload_json.jsp',  
	         fileManagerJson : '../../jsp/file_manager_json.jsp',  
	         allowFileManager : true, 
	         allowUpload : true
	     	});  
	$.extend($.messager.defaults,{  
	    ok:"确定",  
	    cancel:"取消"  
	});  
	
});
var html;
function check()
{
html = KE.html('addtg');
if($('#dtfen').val()==""||KE.count("addtg", "text")==0)
{
 
   $.messager.alert('警告','请将大题部分填充完全!','warning');
   return 0;
}
 return 1;
}



var dt=0;
var sfcheck=0;
var tihao;
var dtvalue="";
var fz="";
function tijiao()
{ 
	
html = KE.html('addtg');
sfcheck=check();
if(dt==1&&dtvalue==html&&$('#dtfen').val()==fz)
{
 $.messager.alert('警告','此题已经被提交','info');
}
else
{
if(sfcheck==1)
{
if(KE.count("addtg", "text")<15)
{
    $.messager.alert('警告','输入长度过短(多于15字)','warning');
	return;
}
if(html.length>800)
{
$.messager.alert('警告','输入长度过长,如果是复制,请修改格式后录入','warning');
return;
}

    var selected="";
	selected ={"cztd.tg":html,"cztd.dtfz":$('#dtfen').val()};
	if(dt==0)
	{
    $.ajax({
		type:"POST",
		url:"<%=basePath%>lu",  
		data:selected,
		dataType:'json',
		success:function callback(r){
	       
	       if(r.message=="操作题录入成功")
	       {
	    	$.messager.alert('大题录入',r.message,'info');
	        tihao=r.tihao;
	        dt=1;
	        dtvalue=html;
	        fz=$('#dtfen').val();
	       }
	       else
	       {
	    	   $.messager.alert('大题录入','此题已经被录入','info');
	       }
		}
		}); 
}
else
{
	   var selected={"cztd.th":tihao,"cztd.dtfz":$('#dtfen').val(),"cztd.tg":html};
	    $.ajax({
			type:"POST",
			url:"<%=basePath%>Cztupdate",
			data:selected,
			dataType:'json',
			success:function callback(r){
			$.messager.alert('修改大题',r.message,'info');
			 dtvalue=html;
			 fz=$('#dtfen').val();
			}
			}); 
}
}
}
}


function tijiaonext()
{
	html = KE.html('addtg');
	   if(dt==0)
	   {
		   $.messager.alert('警告','本题没有录入','warning');
	   }
	   else if(dtvalue!==html)
	   {
		   $.messager.alert('警告','请先提交本题更新数据','warning');
	   }
	   else
	   {
		   $.messager.confirm('信息','是否录入下一题？',function(select){
			   if(select)
			   {
		  close1();
		  parent.window.add();
			   }
		   });
	   }
}


var lwj=0;
function luruwj()
{
	html = KE.html('addtg');
if(dt==1&&dtvalue==html)
{
	 parent.window.luruwj(tihao,0);
 }
 else 
 {
     sfcheck=check();
  if(sfcheck==1)
{
if(html.length>800)
{
$.messager.alert('警告','输入长度过长,如果是复制,请修改格式后录入','warning');
return;
}
if(KE.count("addtg", "text")<15)
{
	$.messager.alert('警告','输入长度过短(多于15字)','warning');
	return;
}
var selected="";
	tg=html;
	selected ={"cztd.tg":tg,"cztd.dtfz":$('#dtfen').val()};
	if(dt==0)
	{
  $.ajax({
		type:"POST",
		url:"<%=basePath%>lu",  
		data:selected,
		dataType:'json',
		success:function callback(r){

	       if(r.message=="操作题录入成功")
	       {
	        tihao=r.tihao;
	        dt=1;
	        parent.window.luruwj(tihao,1); 
	       }
	        else
	       {
	  	    	   $.messager.alert('大题录入','此题已经被录入','info');
	       }
		}
		}); 
}
	else
	{
		 var selected={"cztd.th":tihao,"cztd.dtfz":$('#dtfen').val(),"cztd.tg":tg};
		    $.ajax({
				type:"POST",
				url:"<%=basePath%>Cztupdate",
				data:selected,
				dataType:'json',
				success:function callback(r){
				 parent.window.luruwj(tihao,2);
				 dtvalue=html;
				}
				}); 
	}
 }
}
}



	




function  luruxt(){
	html = KE.html('addtg');
if(dt==1&&dtvalue==html)
{
	 parent.window.xiaotiluru(tihao,0);
}
else 
{
sfcheck=check();
 if(sfcheck==1)
{

if(html.length>800)
{
$.messager.alert('长度过长','输入长度过长,如果是复制,请修改格式后录入','warning');
return;
}
if(KE.count("addtg", "text")<15)
{
	$.messager.alert('警告','输入长度过短(多于15字)','warning');
	return;
}
var selected="";
	tg=html;
	selected ={"cztd.tg":tg,"cztd.dtfz":$('#dtfen').val()};
	if(dt==0)
	{
  $.ajax({
		type:"POST",
		url:"<%=basePath%>lu",  
		data:selected,
		dataType:'json',
		success:function callback(r){
	       if(r.message=="操作题录入成功")
	       {
	        tihao=r.tihao;
	        dt=1;
	        parent.window.xiaotiluru(tihao,1);
            dtvalue=html;    
	       }
	       else
	       {
	    	   $.messager.alert('警告','此题已经被录入','info');
	       }
		}
		}); 
}
	else
	{
		   var selected={"cztd.th":tihao,"cztd.dtfz":$('#dtfen').val(),"cztd.tg":tg};
		    $.ajax({
				type:"POST",
				url:"<%=basePath%>Cztupdate",
				data:selected,
				dataType:'json',
				success:function callback(r){			
				parent.window.xiaotiluru(tihao,2);
				 dtvalue=html;
				}
				}); 
	}
}
}
}

function ShowTip(event,obj,message)
{
   var xOffset = -10; // x distance from mouse
   var yOffset = 10; // y distance from mouse    
  
   //obj.style.top = (event.clientX + yOffset)+"px";
   //obj.style.left = (event.clientY + xOffset)+"px";
   //获取元素width: $(obj).width(); 
   //获取元素height：$(obj).height()
   
   //获取元素位置：$(obj).position().top和$(obj).offset().top 
   
   $('body').append( '<div id="tipmessage">'+message+'</div>');
   $('div#tipmessage').css("top", $(obj).position().top-$(obj).height() + "px").css("left", $(obj).position().left+"px").fadeIn("slow");
}

function HideTip()
{
  $("div#tipmessage").fadeOut("slow").remove();
}






function close1()
{
	 parent.window.close3(dt);
}

</script>
  </head>
  
  <body  >
<div  title="" style="overflow:hidden;">

	<p  align="left">
    <br/>
   	&nbsp;&nbsp;大题分值:&nbsp;<input class="easyui-numberbox"   required="true"  type="text"  id="dtfen"   /> &nbsp;&nbsp;&nbsp;&nbsp;<b>请输入操作题大题部分(不包括小题,包括题目)</b> <br/>	
   <br/>
   </p>
   <p align="left">
   <table>
         <tr><td><font size="2px"   onmouseover="ShowTip(event,this,'大题描述,不包括小题')" onmouseout="HideTip()">大题描述:</font></td><td><textarea id="addtg" name="addtg" style="width:710px;height:260px;visibility:hidden;"></textarea></td></tr>
    </table>
    <br/>
    <br/>
    <p align="center">
     <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add"  onmouseover="ShowTip(event,this,'对小题部分的录入')" onmouseout="HideTip()"  onclick="luruxt()">录入小题</a>
     <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onmouseover="ShowTip(event,this,'对文件的上传')" onmouseout="HideTip()" onclick="luruwj()">录入文件</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add"  onmouseover="ShowTip(event,this,'对大题描述数据的提交')" onmouseout="HideTip()"onclick="tijiao()">提交数据</a>
     <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add"  rel="shareit"  onclick="tijiaonext()">录入下一小题</a> 
  	<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close1()">关闭</a>
  	</p>
  	
  	
  		
		
	 <div id="addxt" closed="true" class="easyui-window" title="添加操作题小题"  maximizable="false"  minimizable="false"   iconCls="icon-add"   >
	
	 <div  id="addxt1"  region="center" border="false" style="background: #fafafa;width:100%;height:100%;overflow-x:hidden;overflow-y:hidden;">
   
      <table id="tt"></table>
      </div>
	
	</div>
	
	
	  
  
  <div id="addda" closed="true"  closable="false"  class="easyui-window" title="录入答案" iconCls="icon-add"   maximizable="false"  minimizable="false" >
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="background:#fff;overflow-y:hidden;">
			<br/>
<p>大题号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  class="easyui-numberbox"   required="true"   id="datihaos"   />&nbsp;&nbsp;&nbsp; 小题顺序号:<input type="text"  class="easyui-numberbox"   required="true"  id="xiaotishunxus" /></p>
<p>答案:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="answers" >&nbsp;&nbsp;&nbsp;&nbsp;分值:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="fs"   class="easyui-numberbox"   required="true" ></p> 
<p>知识点:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id="zsds" style="width:154px;"> </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原子属性:&nbsp;&nbsp;&nbsp;<select id="yzsxs"  style="width:154px;"></select></p>
<p>position:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="positionsxx" >&nbsp;&nbsp; sheetname:<input type="text"  id="snsxx"  > </p>
<p>对比位置1:&nbsp;&nbsp;<input type="text"   id="position1" >&nbsp;&nbsp;&nbsp;&nbsp;对比答案1:<input type="text"  id="answer1" > </p>
<p>对比位置2:&nbsp;&nbsp;<input type="text"   id="position2" >&nbsp;&nbsp;&nbsp;&nbsp;对比答案2:<input type="text"  id="answer2" > </p>
<p>对比位置3:&nbsp;&nbsp;<input type="text"   id="position3" >&nbsp;&nbsp;&nbsp;&nbsp;对比答案3:<input type="text"  id="answer3" > </p>
<p>对比位置4:&nbsp;&nbsp;<input type="text"  id="position4" >&nbsp;&nbsp;&nbsp;&nbsp;对比答案4:<input type="text"  id="answer4" > </p>
<br/>
<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="saveda1()">录入答案</a>	
<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="saveda2()">录入下一步答案</a>
<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close1()">关闭</a>				
		</div>
	</div>
	</div>
	


	

	</div>
  </body>
</html>
