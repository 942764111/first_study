<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>HtmlBox - Demo Default Icons</title>
	
	
    <link href="../css/wjh_main.css" rel="stylesheet" >
	<link rel="stylesheet" type="text/css" href="../js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../js/ui/themes/icon.css">
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../js/ui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="../kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="../kindeditor/kindeditor-min.js"></script>
    		<style type="text/css">
.s1{font-size:10pt}
</style>
	  <script >
  
  
        
     $(function(){ 
    	 $.extend($.messager.defaults,{  
     	    ok:"确定",  
     	    cancel:"取消"  
     	});  
    	 KE.show({    
	         	 id : 'cktg', //TEXTAREA输入框的ID 
	         	items : ['fullscreen', 'undo', 'redo', 'cut', 'copy', 'paste','plainpaste', 
	 					'wordpaste', 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 
	 					'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent',
	 			 		'subscript','-','superscript','selectall', 'title', 'fontname', 'fontsize', 
	 			 		'textcolor', 'bgcolor', 'bold','italic', 'underline', 'strikethrough',
	 			 		'removeformat','image', 'hr', 'emoticons'
	 			 	],
	         	 imageUploadJson: '../../jsp/upload_json.jsp',  
	             fileManagerJson: '../../jsp/file_manager_json.jsp',  
	             allowFileManager: true, 
	             allowUpload : true,
	             afterCreate : function() {
    		     KE.toolbar.disable('cktg', []);
    		     KE.readonly('cktg');
    		     KE.g['cktg'].newTextarea.disabled = true;
    		 }
    		 	             
	         	}); 
      var tihao={"tihao":"<%=(String)request.getParameter("tihao")%>"};
       $.ajax({
		type:"POST",
		url:"<%=basePath%>getdt",
		data:tihao,
		success:function  callback(r){	
		czth.value=r.cztd.th;
		fen.value=r.cztd.dtfz;
		KE.html("cktg", r.cztd.tg);
		}
		});
    }  
    );
    
    

    
    
            
     function ckxt()
     {
    	 parent.window.ckxt($("#czth").val());
     }
          

   function ckwj()
   {
		 parent.window.ckwj($("#czth").val());		
   }
     

  

     

     
</script>
</head>
<body  >
 <div region="center" title="" style="overflow:hidden;">
 <p align="center"  >
 </br>
题号:<input class="type"       id="czth"  disabled="true"  />
分值:<input type="text" name="fen"  id="fen"   disabled="true"/><br/>
<br/>
<table>
         <tr><td><font size="2px">大题题干:</font></td><td><textarea  name="cktg" id="cktg"  style="width:640px;height:240px;visibility:hidden;" ></textarea></td></tr>
         
    </table>
<br/>
<p align="center">
 <a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0)" onclick="ckxt()">查看小题</a>
 <a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0)" onclick="ckwj()">查看文件</a>
</p>


  
</div>

</body>
</html>