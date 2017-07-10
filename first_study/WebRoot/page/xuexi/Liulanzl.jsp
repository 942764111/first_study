<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <style type="text/css">
   body{
    margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
   }
 	</style>
    <title>My JSP 'Liulanzl.jsp' starting page</title>
    <script type="text/javascript" src="js/jquery.js"></script>
	<script language="javascript">
	$(function(){
		  callflex();
		});

	         function callflex() {
		        setTimeout(callFlexFunction, 1000);
	         }
			 function callFlexFunction() {
				    
				  var fl=getSWF("PDFToFlex");
				  fl.loadSwf('page/xuexi/Paper.swf');
			   }
			 function getSWF(name){ 
			 	  var e=document.getElementById(name);
			 	  return (navigator.appName.indexOf("Microsoft") != -1)?e:e.getElementsByTagName("embed")[0];
			 }
    </script>
  </head>
  
  <body style="margin-left: 0px;margin-top: 0px;margin-bottom: 0px;margin-right: 0px;margin: 0px;border-bottom: 0px;">
    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" id="PDFToFlex" width="100%" height="100%"
			codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
		<param name="movie" value="page/flex/PDFToFlex.swf" />
		<param name="quality" value="high" />
		<param name="bgcolor" value="#869ca7" />
		<param name="allowScriptAccess" value="sameDomain" />
		
		<embed id="appli" src="page/flex/PDFToFlex.swf" quality="high" bgcolor="#869ca7" width="100%" height="100%" 
		name="VAnalyze" align="middle" play="true" loop="false" quality="high" allowScriptAccess="sameDomain" 
		allowFullScreen="true" type="application/x-shockwave-flash" pluginspage="http://www.adobe.com/go/getflashplayer">
		</embed>
	</object>
	
  </body>
</html>
