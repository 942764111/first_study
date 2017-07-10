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
    <style type="text/css">
    .top_bar { position: fixed;float:right; z-index: 90; bottom: 100px; left: 0; right:-1100px; margin:auto; font-family: Helvetica, Tahoma, Arial, Microsoft YaHei, sans-serif; }
    </style>
    <script type="text/javascript" src="studentswf/AC_OETags.js"></script>
    <script type="text/javascript" src="studentswf/swfobject.js"></script>
    <script type="text/javascript" src="studentswf/swfaddress.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script language="JavaScript" type="text/javascript">
	
	
	<!--
	// -----------------------------------------------------------------------------
	// Globals
	// Major version of Flash required
	var requiredMajorVersion = 9;
	// Minor version of Flash required
	var requiredMinorVersion = 0;
	// Minor version of Flash required
	var requiredRevision = 124;
	// -----------------------------------------------------------------------------
	// -->
	</script>

	<script type="text/javascript">
    	swfobject.registerObject("wendang"); 
    </script>
    <script type="text/javascript">
        window.onbeforeunload = function(){ return Examplemethod(); }
        function Examplemethod()
        {
        	var x;
            if(navigator.appName.indexOf("Microsoft") != -1){
                  x=document.getElementById('index').myFlexFunction();
            }
            else {
                  x=document.embeds['index'].myFlexFunction();
            }
            
	   		if(x=="stu") {
	   			jQuery.post("quitLogin");
	        } else {
				
	        }
        }
    </script>
    
  </head>
  <body class="easyui-layout" bgcolor="#292929">
  	<center>
  		<div id="content">
	  		<script type="text/javascript">
	  		
			  	//<!--
			  	// Version check for the Flash Player that has the ability to start Player Product Install (6.0r65)
			  	var hasProductInstall = DetectFlashVer(6, 0, 65);
		
			  	// Version check based upon the values defined in globals
			  	var hasRequestedVersion = DetectFlashVer(requiredMajorVersion, requiredMinorVersion, requiredRevision);
		
		
			  	// Check to see if a player with Flash Product Install is available and the version does not meet the requirements for playback
			  	if ( hasProductInstall && !hasRequestedVersion ) {
			  	    // MMdoctitle is the stored document.title value used by the installation process to close the window that started the process
			  	    // This is necessary in order to close browser windows that are still utilizing the older version of the player after installation has completed
			  	    // DO NOT MODIFY THE FOLLOWING FOUR LINES
			  	    // Location visited after installation is complete if installation is required
			  	    var MMPlayerType = (isIE == true) ? "ActiveX" : "PlugIn";
			  	    var MMredirectURL = encodeURI(window.location);
			  	    document.title = document.title.slice(0, 47) + " - Flash Player Installation";
			  	    var MMdoctitle = document.title;
		
			  	    AC_FL_RunContent(
			  	        "src", "playerProductInstall",
			  	        "FlashVars", "MMredirectURL="+MMredirectURL+'&MMplayerType='+MMPlayerType+'&MMdoctitle='+MMdoctitle+"",
			  	        "width", "1000",
			  	        "height", "600",
			  	        "align", "middle",
			  	        "id", "index",
			  	        "quality", "high",
			  	        "bgcolor", "#ffffff",
			  	        "name", "/studentswf/WenDangModule.swf",
			  	        "allowScriptAccess","sameDomain",        
			  	        "type", "application/x-shockwave-flash",
			  	        //"wmode", "opaque",
			  	        "pluginspage", "http://www.adobe.com/go/getflashplayer"
			  	    );
			  	} else if (hasRequestedVersion) {
			  	    // if we've detected an acceptable version
			  	    // embed the Flash Content SWF when all tests are passed
			  	    
				  	AC_FL_RunContent(
				  	        "src", "/studentswf/WenDangModule.swf",
				  	        "width", "1000",
				  	        "height", "650",
				  	        "align", "middle",
				  	        "id", "index",
				  	        "quality", "high",
				  	        "bgcolor", "#ffffff",
				  	        "name", "index",
				  	        "allowScriptAccess","sameDomain",
				  	        "allowFullScreen","true",
				  	        "type", "application/x-shockwave-flash",
				  	        //"wmode", "opaque",
				  	        "pluginspage", "http://www.adobe.com/go/getflashplayer"
				  	    );
				  	    
				  	  } else {  // flash is too old or we can't detect the plugin
				  	    var alternateContent = '正常浏览智能学习系统需要安装Adobe Flash Player插件，'
				  	    + '您当前的系统中并没有安装该浏览器插件，请点击以下链接到Adobe的官方网站下载该插件。 '
				  	    + '<a href=http://www.adobe.com/go/getflash/>下载Flash Player</a>';
				  	   // document.write(alternateContent);  // insert non-flash content
				  	  }
				  	// -->
			</script>
	  		<noscript>
	  			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
					id="index" width="1000" height="600"
					codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
					<param name="src" value="/studentswf/studentLogin/com/ctrls/WenDangModule.swf" />
					<param name="quality" value="high" />
					<param name="bgcolor" value="#869ca7" />
					<param name="allowFullScreen" value="true" />
					<param name="allowScriptAccess" value="sameDomain" />
					<param name="wmode" value="opaque"/>
					<embed src="/studentswf/studentLogin/com/ctrls/WenDangModule.swf" quality="high" bgcolor="#869ca7"
							width="1000" height="650" name="index" align="middle"
							play="true"
							loop="false"
							quality="high"								
							allowScriptAccess="sameDomain"				
							allowFullScreen="true"
							type="application/x-shockwave-flash"
							wmode="opaque"
							pluginspage="http://www.adobe.com/go/getflashplayer">
					</embed>
				</object>
	  		</noscript>
  		</div>

  		<div class="top_bar">
 <img src="1.png" id="img">
</div>

		
	</center>
  </body>
</html>
