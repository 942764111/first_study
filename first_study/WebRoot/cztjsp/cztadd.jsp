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
    <script>

    $(function (){
    	 KE.show({    
         	id:'addtg',//TEXTAREA输入框的ID 
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
       
        var s="<%=(String)request.getParameter("value")%>";
        if(s!=null)
        {
           document.getElementById("addtg").value = s;
        }
       
    
    });

    
 

    function getvalue()
    {
        var html = KE.html('addtg');
        return  html;
    }
    
    </script>
  </head>
  
  <body>
<div id="shareit-url">
<textarea  name="addtg" id="addtg"  style="width:510px;height:180px;"></textarea>
</div>
	
  </body>
</html>
