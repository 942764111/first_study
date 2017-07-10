<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String fileName = (String)request.getSession().getAttribute("fileName");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>视频列表</title>
    <link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#test_tables').datagrid({
				title:'视频播放',
				iconCls:'icon-save',
				width:getWidth(1),
				height:0,
				fitColumns:true,
			});
		});

		function getWidth(percent){ 
		    return (document.body.clientWidth)*percent; 
		}
	</script>
  </head>
  <body style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
  	<table id="test_tables"></table>
		  	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
						id="video" width="100%" height="100%"
						codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
					<param name="src" value="/studentswf/FBVideoPlayList.swf" />
					<param name="quality" value="high" />
					<param name="bgcolor" value="#869ca7" />
					<param name="FlashVars" value="fileName=<%=fileName %>"/>
					<param name="allowScriptAccess" value="sameDomain" />
					<embed src="/studentswf/FBVideoPlayList.swf" quality="high" bgcolor="#869ca7"
							flashVars="fileName=<%=fileName %>"
							width="100%" height="100%" name="FBVideo" align="middle"
							play="true"
							loop="false"
							quality="high"
							allowScriptAccess="sameDomain"
							allowFullScreen="true"
							type="application/x-shockwave-flash"
							pluginspage="http://www.adobe.com/go/getflashplayer">
					</embed>
			</object>
  </body>
</html>
