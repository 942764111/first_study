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

	  <script >
  
  
         var sf=0;
         var dativalue;
         var defenvalue;
     $(function(){ 

    	 $.extend($.messager.defaults,{  
     	    ok:"确定",  
     	    cancel:"取消"  
     	}); 
      	
		 KE.show({    
	         	id : 'xgtg',//TEXTAREA输入框的ID 
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

	      	 
      var tihao={"tihao":"<%=(String)request.getParameter("tihao")%>"};
       $.ajax({
		type:"POST",
		url:"<%=basePath%>getdt",
		data:tihao,
		success:function  callback(r){	
		var tg=r.cztd.tg;
		czth.value=r.cztd.th;
		fen.value=r.cztd.dtfz;
		KE.html("xgtg", tg);
		dativalue=r.cztd.tg;
		defenvalue=r.cztd.dtfz;
		}
		});

    });
    
    



    function update(){
        if(dativalue==KE.html('xgtg')&&defenvalue==$("#fen").val())
        {
        	$.messager.alert('修改大题','大题没有修改','info');
        	return;
        }
        else  if(KE.count("xgtg", "text")==0||$("#fen").val()=="")
        {
        	$.messager.alert('修改大题','请填充完全','info');
        	return;
        }
        else if(KE.count("xgtg", "text")<15)
        {
        	$.messager.alert('警告','输入长度过短(至少15字)','warning');
        	return;
        } else if(KE.html("xgtg")>800)
        {
        	$.messager.alert('警告','输入长度过长','warning');
        	return;
        }
        
       
    	var selected={"cztd.th":$("#czth").val(),"cztd.dtfz":$("#fen").val(),"cztd.tg":KE.html('xgtg')};
    	    $.ajax({
    			type:"POST",
    			url:"<%=basePath%>Cztupdate",
    			data:selected,
    			dataType:'json',
    			success:function callback(r){
    			$.messager.alert('修改大题',r.message,'info');
    			dativalue=KE.html('xgtg');
    			defenvalue=$("#fen").val();
    			parent.window.sfupdate();
    			}
    			}); 
    	      }
    	  

    	function updatext()
    	 {  
    		   parent.window.updatext($("#czth").val());
    		   
    	}  


   	     function updatewj()
   	     {
   	    	 parent.window.updatewj($("#czth").val());
   	     }
     

     
</script>
</head>
<body>
<div>
   <br/>
  &nbsp;题号:<input class="type"       id="czth"  disabled="true"  /> 
  &nbsp;分值:<input type="text" name="fen"  id="fen" class="easyui-numberbox" /><br/>
  <br/>
  <table>
         <tr>
	         <td>
	         	<font size="2px">大题题干:</font>
	         </td>
	         <td>
		         <textarea  name="xgtg" id="xgtg"  style="width:640px;height:240px;visibility:hidden;">
		         </textarea>
	         </td>
         </tr>
    </table>
  <br/>
</div>

<p align="center">
 &nbsp;<a class="easyui-linkbutton" iconCls="icon-edit" href="javascript:void(0)" onclick="update()">提交修改信息</a>
 &nbsp;<a class="easyui-linkbutton" iconCls="icon-edit" href="javascript:void(0)" onclick="updatext()">修改小题</a>
 &nbsp;<a class="easyui-linkbutton" iconCls="icon-edit" href="javascript:void(0)" onclick="updatewj()">修改文件</a>
</p>


  
	

</body>
</html>