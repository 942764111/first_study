<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String path2=(String)request.getParameter("filepath");
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
   
    <script type="text/javascript" src="Images/jquery1.7.1.min.js"></script>
	<script type="text/javascript" src="Images/flexpaper_flash.js"></script>

<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
	<style type="text/css">
body {margin:0; padding:0px;  margin-top:0px;font-size:12px; color:#313131;text-align: left; font-family:"宋体",Verdana, Arial, Helvetica, sans-serif; background:white; }
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
		<div id="banner" style="height:600px;margin-top:260px">
			
			<div style="position:absolute;left:220px;top:10px;">

 		</div>
 		<script type="text/javascript">
		var basePath = "<%=basePath+"/"+path2 %>";
	</script>
 		<div style="position:absolute;left:280px;top:160px;">
	        <a id="viewerPlaceHolder" style="width:800px;height:590px;display:block"></a>
	         <script type="text/javascript"> 
	         var fpv = new FlexPaperViewer(	
        			 basePath + 'page/share/FlexPaperViewer',
					 'viewerPlaceHolder', { config : {
					 SwfFile : escape(basePath),//要浏览的swf文件
					 Scale : 0.9, // 初始化缩放比例，参数值应该是大于零的整数
					 ZoomTransition : 'easeOut',//Flexpaper中缩放样式   easenone, easeout, linear, easeoutquad
					 ZoomTime : 0.5,//从一个缩放比例变为另外一个缩放比例需要花费的时间，该参数值应该为0或更大。
					 ZoomInterval : 0.2,//缩放比例之间间隔，默认值为0.1，该值为正数。
					 FitPageOnLoad : true,// 初始化得时候自适应页面，与使用工具栏上的适应页面按钮同样的效果。
					 FitWidthOnLoad : true,//初始化的时候自适应页面宽度，与工具栏上的适应宽度按钮同样的效果。
					 FullScreenAsMaxWindow : true,//全屏
					 ProgressiveLoading : false,//当设置为true的时候，展示文档时不会加载完整个文档，而是逐步加载，但是需要将文档转化为9以上的flash版本（使用pdf2swf的时候使用-T 9 标签）。
					 MinZoomSize : 0.2,//设置最小的缩放比例。
					 MaxZoomSize :2,// 最大的缩放比例。
					 SearchMatchAll : false,//设置为true的时候，单击搜索所有符合条件的地方高亮显示。
					 InitViewMode : 'Portrait',//设置启动模式如"Portrait" or "TwoPage".
					 PrintPaperAsBitmap : false,// 以位图的形式打印页面
					 
					 ViewModeToolsVisible : false,//工具栏上是否显示样式选择框。
					 ZoomToolsVisible : true,//工具栏上是否显示缩放工具。
					 NavToolsVisible : true,//工具栏上是否显示导航工具。
					 CursorToolsVisible : true,//工具栏上是否显示光标工具。
					 SearchToolsVisible : true,//工具栏上是否显示搜索。
						
					 localeChain: 'zh_CN' //设置地区（语言）
				}});
				
	        	
	        </script>
	    </div>  
		</div>
	
		<footer>
			
				<img alt="" src="../../images/footer.png" width="100%">
				
		</footer>
		<!--End Footer -->

	</div>
	<!-- End Container -->

</body>
</html>
