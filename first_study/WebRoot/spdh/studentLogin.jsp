<%--暂时不用改页面--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>智能学习系统</title>
    <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
    <script type="text/javascript">
	    window.onunload=function(){
	    	jQuery.post("quitLogin");
		}
    </script>
  </head>
  <body class="easyui-layout" bgcolor="#E5ECFD">
  <center>
  	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
					id="video" width="1000" height="600"
					codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
				<param name="src" value="/studentswf/StudentLoginMain.swf" />
				<param name="quality" value="high" />
				<param name="wmode" value="transparent"/>
				<param name="bgcolor" value="#869ca7" />
				<param name="allowFullScreen" value="true" />
				<param name="FlashVars" value=""/>
				<param name="allowScriptAccess" value="always" />
				<embed src="/studentswf/StudentLoginMain.swf" quality="high" bgcolor="#869ca7"
						flashVars=""
						wmode="transparent"
						width="1000" height="600" name="FBVideo" align="middle"
						play="true"
						loop="false"
						quality="high"
						allowScriptAccess="always"
						allowFullScreen="true"
						type="application/x-shockwave-flash"
						pluginspage="http://www.adobe.com/go/getflashplayer">
				</embed>
		</object>
		</center>
  </body>
</html>
