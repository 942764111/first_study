<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>电子笔记</title>
    <link href="css/wjh_main.css" rel="stylesheet" />
	<link href="css/maintop.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		function closeNs() {
			 if(navigator.appName.indexOf("Microsoft") != -1){
                 document.getElementById('video').closeNs();
           	 } else {
                 document.embeds['video'].closeNs();
           	 }
		}
	</script>
  </head>
  
  <body class="easyui-layout">
  	<table id="test_tables"></table>
	  	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
					id="video" width="100%" height="99%"
					codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
				<param name="src" value="/studentswf/PlayerModule.swf" />
				<param name="quality" value="high" />
				<param name="bgcolor" value="#869ca7" />
				<param name="allowFullScreen" value="true" />
				<param name="FlashVars" value="courseName=1&chapterName=1
						&keci=1&i_d=1&zlmc=1&zlid=1&filename=1&path=1
						&weizhi=1&type=1"/>
				<param name="allowScriptAccess" value="sameDomain" />
				<embed src="/studentswf/PlayerModule.swf" quality="high" bgcolor="#869ca7"
						flashVars="courseName=1&chapterName=1
							&keci=1&i_d=1&zlmc=1&zlid=1&filename=1&path=1
							&weizhi=1&type=1"
						width="100%" height ="99%" name="FBVideo" align="middle"
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
