<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String fileType=(String)request.getSession().getAttribute("fileType");
int maxSize=(Integer)request.getSession().getAttribute("maxSize");
String filesPath=(String)request.getSession().getAttribute("filesPath");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>muploadDemo</title>
    <link rel="stylesheet" type="text/css" href="../js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../js/ui/themes/icon.css">
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../js/ui/jquery.easyui.min.js"></script>
	
    <script type="text/javascript">
		function tjbd(){
			var username = $("#addusername").val();
			var password = $("#addpassword").val();
		 	$.ajax({
				type:"POST",
				url:"tjbd.action",
				data:{"username":username,"password":password},
				success:function(){
					$.messager.alert('add','提交成功!!!','info',function(){
					});
				}
      		});
		}

    </script>
    <script type="text/javascript">
    function toggle(targetid){
        if (document.getElementById){
            target=document.getElementById(targetid);
                if (target.style.display=="block"){
                    target.style.display="none";
                } else {
                    target.style.display="block";
                }
        }
    }
    </script>
  </head>
  <body>
  	<input type="button" id="butn" value="文件" iconCls="/img/upload/upload.gif" onclick="toggle('flex')"/>
	<div id="flex" style="display:none">
	  	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
				id="upload" width="545" height="277"
				codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
			<param name="src" value="/upload_swf/uploadTest.swf" />
			<param name="quality" value="high" />
			<param name="bgcolor" value="#869ca7" />
			<param name="FlashVars" value="fileType=<%=fileType %>&maxSize=<%=maxSize %>"/>
			<param name="allowScriptAccess" value="sameDomain" />
			<embed src="/upload_swf/uploadTest.swf" quality="high" bgcolor="#869ca7"
					flashVars="fileType=<%=fileType %>&maxSize=<%=maxSize %>"
					width="541" height="277" name="VAnalyze" align="middle"
					play="true"
					loop="false"
					quality="high"
					allowScriptAccess="sameDomain"
					allowFullScreen="false"
					type="application/x-shockwave-flash"
					pluginspage="http://www.adobe.com/go/getflashplayer">
			</embed>
		 </object>
	</div>
	<div align="left">
	 	<form id="add">
	 		<table>
	 			<tr>
	 				<td>
	 					用户名:<input type="text" id="addusername"/><br>
	 				</td>
	 			</tr>
			 	<tr>
	 				<td>
	 					密码:&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" id="addpassword"/><br>
	 				</td>
	 			</tr>
			 	<tr>
			 		<td>
			 			<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="tjbd()">提交</a>
			 		</td>
			 		<td>
			 			<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="cancel()">取消</a>
			 		</td>
			 	</tr>
	 		</table>
	 	</form>
	 </div>
	 
  </body>
</html>
