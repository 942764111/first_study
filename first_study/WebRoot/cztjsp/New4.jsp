<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<link rel="stylesheet" type="text/css" href="../js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../js/ui/themes/icon.css">
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../js/input-file.js"></script>
	<style type="text/css">
.s1{font-size:10pt}
.type-file-box{ position:relative;width:300px}
input{ vertical-align:middle; margin:0; padding:0}
.type-file-text{ height:22px; border:1px solid #cdcdcd; width:225px;}
.type-file-button{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:60px;}
.type-file-file{ position:absolute; top:0; right:0; height:24px; filter:alpha(opacity:0);opacity: 0;width:220px }
</style>
	
<script language="javascript" type="text/javascript">

window.onload =function (){

	 
	$.extend($.messager.defaults,{  
	    ok:"确定",  
	    cancel:"取消"  
	});  

	
	var s=<%=request.getAttribute("sf")%>;
	if(s==1)
	{
	$.messager.alert('文件录入','上一文件录入成功','info');
	}
	else  if(s==0)
	{
		$.messager.alert('文件录入','此题已经存在Excel文件','info');
	}
	}

    function  beforesubmit()
    {
   var str=document.getElementById("file").value;
       if(document.getElementById("file").value==""||document.getElementById("wjms0").value=="")
       {
       $.messager.alert('警告','请将内容输入完整','warning');
  
       }
      else if(document.getElementById("wjms0").value.length>50)
      {
          $.messager.alert('警告','输入字符过长,请在50字之内','warning');
      }
  else if(str.substring(str.length-3,str.length)=="xls")
  {
    document.form.submit();
  }
  else 
  {
    $.messager.alert('警告','上传类型出错，支持(.xls)','warning');
  }
    }
    
     
     
</script>
	
</head>
<body class="s1"  >
<div   class="type-file-box">

	<s:form action="fileUpload" method="post"  id="form"  name="form"  enctype="multipart/form-data">  
	<input type="hidden"  name="tihao"  value=" <%=(String)request.getParameter("tihao") %>"/>
	<input type="hidden"  name="filenum"  id="filenum"  />
    <input  type ="file"  name ="file"  id="file"    class="type-file-file"  />   
    <p>文件描述 :</p>
    <textarea   name="cztywjlist[0].ms"     id="wjms0" class="easyui-validatebox"  style="height:80px;width:300px;"></textarea>
     <br/>
       <a   href="javascript:beforesubmit()" class="easyui-linkbutton" iconCls="icon-add">提交文件信息</a>
        </s:form> 
     </div> 
</body>
</html>