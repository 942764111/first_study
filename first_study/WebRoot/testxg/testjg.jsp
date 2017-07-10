<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>jQuery EasyUI</title>
	
	</head>

	<body align="center" class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
		
		<div region="center" fit="true">
			<s:if test="tip==\"a\"">
				<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" 
			       codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" 
			       WIDTH="321px" HEIGHT="291px" id="main" align="center"> 
			
				  <PARAM NAME="movie" VALUE="/donghua/1.swf"> 
				  <PARAM NAME="quality" VALUE="high"> 
				  <PARAM NAME="bgcolor" VALUE="#336699"> 
				  <EMBED src="/donghua/1.swf" 
				         quality="high" bgcolor="#336699" 
				         WIDTH="321px" HEIGHT="291px"
				         NAME="main" ALIGN="center" 
				         TYPE="application/x-shockwave-flash" 
				         PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer"> 
					</EMBED>
				</OBJECT> 
			</s:if>
			<s:elseif test="tip==\"b\"">
				<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" 
			       codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" 
			      WIDTH="321px" HEIGHT="291px" id="main" ALIGN="center"> 
			
				  <PARAM NAME="movie" VALUE="/donghua/2.swf"> 
				  <PARAM NAME="quality" VALUE="high"> 
				  <PARAM NAME="bgcolor" VALUE="#336699"> 
				  <EMBED src="/donghua/2.swf" 
				         quality="high" bgcolor="#336699" 
				        WIDTH="321px" HEIGHT="291px"
				         NAME="main" ALIGN="" 
				         TYPE="application/x-shockwave-flash" 
				         PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer"> 
					</EMBED>
				</OBJECT> 
			</s:elseif>
		
			<s:elseif test="tip==\"c\"">
				<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" 
			       codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" 
			       WIDTH="321px" HEIGHT="291px" id="main" ALIGN="center"> 
			
				  <PARAM NAME="movie" VALUE="/donghua/3.swf"> 
				  <PARAM NAME="quality" VALUE="high"> 
				  <PARAM NAME="bgcolor" VALUE="#336699"> 
				  <EMBED src="/donghua/3.swf" 
				         quality="high" bgcolor="#336699" 
				         WIDTH="321px" HEIGHT="291px" 
				         NAME="main" ALIGN="" 
				         TYPE="application/x-shockwave-flash" 
				         PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer"> 
					</EMBED>
				</OBJECT> 
			</s:elseif>
			
			<s:elseif test="tip==\"d\"">
				<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" 
			       codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" 
			       WIDTH="321px" HEIGHT="291px" id="main" ALIGN="center"> 
			
				  <PARAM NAME="movie" VALUE="/donghua/4.swf"> 
				  <PARAM NAME="quality" VALUE="high"> 
				  <PARAM NAME="bgcolor" VALUE="#336699"> 
				  <EMBED src="/donghua/4.swf" 
				         quality="high" bgcolor="#336699" 
				         WIDTH="321px" HEIGHT="291px" 
				         NAME="main" ALIGN="" 
				         TYPE="application/x-shockwave-flash" 
				         PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer"> 
					</EMBED>
				</OBJECT> 
			</s:elseif>
			
			<s:elseif test="tip==\"e\"">
				<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" 
			       codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" 
			       WIDTH="321px" HEIGHT="291px" id="main" ALIGN="center"> 
			
				  <PARAM NAME="movie" VALUE="/donghua/5.swf"> 
				  <PARAM NAME="quality" VALUE="high"> 
				  <PARAM NAME="bgcolor" VALUE="#336699"> 
					<EMBED src="/donghua/5.swf" 
				         quality="high" bgcolor="#336699" 
				         WIDTH="321px" HEIGHT="291px" 
				         NAME="main" ALIGN="" 
				         TYPE="application/x-shockwave-flash" 
				         PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer"> 
					</EMBED>
				</OBJECT> 
			</s:elseif>
		</div>	
	</body>
</html>
