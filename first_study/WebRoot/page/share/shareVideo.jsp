<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String path2=request.getParameter("filepath");
%>
<html lang="en" class="no-js">
<head>
	<title>Straight</title>

	<meta charset="utf-8">

	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

	<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,700,600,300' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="../../css/fenxiang/bootstrap.css" type="text/css" media="screen">
	<link rel="stylesheet" href="../../css/fenxiang/bootstrap-responsive.css" type="text/css" media="screen">
	<link rel="stylesheet" href="../../css/fenxiang/jquery.bxslider.css" type="text/css" media="screen">
	<link rel="stylesheet" href="../../css/fenxiang/jquery.fancybox.css" type="text/css" media="screen">
	<link rel="stylesheet" href="../../css/fenxiang/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="../../css/fenxiang/fenxiang/responsive.css" type="text/css" media="screen">
 <script type="text/javascript" src="Images/swfobject.js"></script>
   
	

<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
	<style type="text/css">
body {margin:0; padding:0px;  margin-top:1px;font-size:12px; color:#313131;text-align: left; font-family:"宋体",Verdana, Arial, Helvetica, sans-serif; background:white; }
div,ul,li,ol,dl,dt,dd,form,img,p{margin: 0; padding: 0; border: 0; }
p{line-height:24px; }
li{list-style-type:none;}
h1,h4,h5,h6{margin: 0; padding: 0; font-size:14px;}
/* __link */
a {color: #313131; text-decoration:none;}
a:link {color: #313131;text-decoration:none;}
a:visited {color: #313131;text-decoration:none;}
a:hover {color:red; text-decoration:underline;}
a:active {color: #313131;}
.cGray,a.cGray:link,a.cGray:visited{ color:#6D6359; }
.cOrange2,a.cOrange2:link,a.cOrange2:visited{ color:#ce0400; }
.cGreen2,a.cGreen2:link,a.cGreen2:visited{ color:#336600; }
.en {font-family:Verdana, Arial, Helvetica, sans-serif,"宋体"; }
fieldset{border:1px #cccccc solid;padding:10px;margin-bottom:10px;display:block; font-family:Verdana, Arial, Helvetica, sans-serif;}
-->
</style>
</head>
<body>

	<!-- Container -->
	<div id="container">
	
		<!-- Header -->
		<header>
			<!-- Navbar
		    ================================================== -->
			<div class="navbar navbar-inverse navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container">
						<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<div class="logo">
							<a class="brand" href="index.html">
								<img alt="" src="../../images/logo.png">
							</a>
						</div>
						<div class="nav-collapse collapse">
							<ul class="nav">
								<li>
									<a href="http://localhost:8080">首页</a>
												    	
								</li>
								<li>
									<a href="http://localhost:8080">资源</a>
								</li>
								<li>
									<a href="http://localhost:8080">思维导图</a>
									
								</li>
								<li>
									<a href="http://localhost:8080">学习</a>
								</li>
								<li >
									<a href="http://localhost:8080">电子笔记</a>
									
								</li>
								<li >
									<a href="http://localhost:8080">个人信息</a>
									
								</li>
								<li>
									<a href="http://localhost:8080">消息动态</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</header>
		<!-- End Header -->

		<!-- Slider -->
		<div id="banner">
			<fieldset style="width:850px;margin-left:210px">
        <legend><h1>酷播迷你(CuPlayerMiniV1.0)演示1</h1></legend>
<center>
<div id="CuPlayer"  style="margin-top:0px;margin-bottom:20px;">
</center>
</div>
</fieldset>
		</div>
	
		<footer>
			
				<img alt="" src="../../images/footer.png" width="100%">
				
		</footer>
		<!--End Footer -->

	</div>
	<!-- End Container -->

</body>
</html>
<script type="text/javascript">
		var basePath = "<%=basePath+"/"+path2 %>";
	</script>
<script type="text/javascript">
var so = new SWFObject("CuPlayerMiniV10_Black_S.swf","CuPlayer","600","400","9","#000000");
so.addParam("allowfullscreen","true");
so.addParam("allowscriptaccess","always");
so.addParam("wmode","opaque");
so.addParam("quality","high");
so.addParam("salign","lt");
so.addVariable("CuPlayerFile",basePath);
so.addVariable("CuPlayerImage","Images/flashChangfa2.jpg");
so.addVariable("CuPlayerShowImage","true");
so.addVariable("CuPlayerWidth","600");
so.addVariable("CuPlayerHeight","400");
so.addVariable("CuPlayerAutoPlay","true");
so.addVariable("CuPlayerAutoRepeat","true");
so.addVariable("CuPlayerShowControl","true");
so.addVariable("CuPlayerAutoHideControl","false");
so.write("CuPlayer");	

</script>