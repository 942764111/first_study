<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String fangxiang=(String)request.getParameter("fangxiang");
fangxiang=new String(fangxiang.getBytes("ISO-8859-1"),"utf-8");
String id=(String)request.getParameter("id");
String miaoshu=(String)request.getParameter("miaoshu");
miaoshu=new String(miaoshu.getBytes("ISO-8859-1"),"utf-8");
String picurl=(String)request.getParameter("picurl");
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>用户信息页面 - Bootstrap后台管理系统模版Ace下载</title>
		<meta name="keywords" content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文" />
		<meta name="description" content="JS代码网提供Bootstrap模版,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->
<link rel="stylesheet" href="http://static.mukewang.com/static/css/??base.css,common/common-less.css,course/course_program-less.css,poplogin-less.css?v=1470984066" type="text/css" />
		<link href="../../assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="../../assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="jquery.scrollbar.css" type="text/css" />
<link rel="stylesheet" href="luxian.css" type="text/css" />
		<!--[if IE 7]>
		  <link rel="stylesheet" href="../../assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<link rel="stylesheet" href="../../assets/css/jquery-ui-1.10.3.custom.min.css" />
		<link rel="stylesheet" href="../../assets/css/jquery.gritter.css" />
		<link rel="stylesheet" href="../../assets/css/select2.css" />
		<link rel="stylesheet" href="../../assets/css/bootstrap-editable.css" />

		<!-- fonts -->

		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

		<!-- ace styles -->

		<link rel="stylesheet" href="../../assets/css/ace.min.css" />
		<link rel="stylesheet" href="../../assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="../../assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="js/reveal.css">
	<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="js/jquery.reveal.js"></script>

<script type="text/javascript">
function getzsd(zhang){

$.ajax({
				type : "POST",
				url : "getzsd.action?zcid="+zhang,
			
				success : function(json) {
			
				if(json.state=='0'){
				var ht="";
				$.each(json.list,function(index,content){
				
			    ht=ht+" <a href='ziliao.jsp?zsdid="+content.zsdid+"' class='step-anchor  '><b>"+content.zsdmc+"</b><i class='step-arr'></i></a>&nbsp; &nbsp;";
				})
				  
				  $("#zsd").html(ht);
				   
				   }
				}
				
			});
}
</script>

<script type="text/javascript">
$(function(){
function getzhang(){

var id=$("#zhangid").val();

$.ajax({
				type : "POST",
				url : "getzhang.action?id="+id+"",
			
				success : function(json) {
			
				
				var ht="";
				$.each(json.rows2,function(index,content){
				
				if(index=="0"){
				ht=ht+" <li class='step-item clearfix step-first'><i class='line'></i> <i class='dot'></i><span class='hd l'>"+content.kecheng+"</span>"+
 "<i class='v-line l'></i><div class='bd l clearfix'>";
                var hh="";
                $.each(content.zhang,function(ind,con){
                 hh=hh+"<a href='javascript:;' class='step-anchor'  onclick='getzsd("+con.zbh+");' data-reveal-id='myModal'>"+
"<b>"+con.zhang+"</b><i class='step-arr'></i></a>";
                })
               
                ht=ht+hh+"</div></li>";
				}else if(index==json.rows2.length){
				ht=ht+" <li class='step-item clearfix step-last'><i class='line'></i> <i class='dot'></i><span class='hd l'>"+content.kecheng+"</span>"+
 "<i class='v-line l'></i><div class='bd l clearfix'>";
                var hh="";
                $.each(content.zhang,function(ind,con){
                 hh=hh+"<a href='javascript:;' class='step-anchor'  onclick='getzsd("+con.zbh+");' data-reveal-id='myModal'>"+
"<b>"+con.zhang+"</b><i class='step-arr'></i></a>";
                })
               
                ht=ht+hh+"</div></li>";
				}else{
				ht=ht+" <li class='step-item clearfix'><i class='line'></i> <i class='dot'></i><span class='hd l'>"+content.kecheng+"</span>"+
 "<i class='v-line l'></i><div class='bd l clearfix'>";
                var hh="";
                $.each(content.zhang,function(ind,con){
                 hh=hh+"<a href='javascript:;' class='step-anchor'  onclick='getzsd("+con.zbh+");' data-reveal-id='myModal'>"+
"<b>"+con.zhang+"</b><i class='step-arr'></i></a>";
                })
               
                ht=ht+hh+"</div></li>";
				}
				
			    
				})
				  
				$("#content").html(ht);
				   
				
				}
				
			});
}
getzhang();
})
</script>
		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="../../assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="../../assets/js/html5shiv.js"></script>
		<script src="../../assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
		
<br>
		<div class="main-container" id="main-container">
			

			<div class="main-container-inner">
				

				<div class="main-content">
					

					<div class="page-content">
						

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								

								<div>
									<div id="user-profile-1" class="user-profile row" style="flaot:left">
										<div class="col-xs-12 col-sm-3 center">
											<div>
												<span class="profile-picture">
													<img id="avatar" class="editable img-responsive" alt="Alex's Avatar" src="<%=picurl %>" />
												</span>

												<div class="space-4"></div>

												<div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
													<div class="inline position-relative">
														<a href="#" class="user-title-label dropdown-toggle" data-toggle="dropdown">
															<i class="icon-circle light-green middle"></i>
															
															<span class="white">路径介绍</span>
														</a>

													
													</div>
												</div>
											</div>
<hr>
											<div class="space-6"></div>

											<div class="profile-contact-info">
		
										 <span class="l" style="margin-left:30px;width:230px;text-align:left"><%=miaoshu %></span>         

												<div class="space-6"></div>

												
											</div>

								
										</div>

										
									</div>
									
									<div id="main" style="float:right">


    <div class="g-layer g-layer-left clearfix " style="padding-right:10px">
  
    
    <div class="layer-container">
        <div class="plan-main">
            <div class="plan-top">
                <h2><%=fangxiang %></h2>
                <div class="plan-meta">
                    <div class="r">
                                        <div class="small-share r">
                        
                    </div>
                    </div>
                    <span class="meta meta-tags">
                                                	<span class="g-tag">服务端</span>
                                                    <span class="g-tag">求职</span>
                                                    <span class="g-tag">实战</span>
                                            </span>

                </div>
            </div>
                            
                <div class="plan-step-wrap js-route-panel" id="js-route-panel">
        <ul class="plan-step clearfix" id="content">

        </ul>
    </div>            
            <!-- 学习报告 -->
            
                        <!-- 学习报告 E-->
        </div>
    </div>
</div>







								

								
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
								</div>

<div id="myModal" class="reveal-modal">
		
		<h3>选择知识点</h3>
		<br>
            <div class="bd l clearfix" id="zsd">
                                        <!-- 选中open -->
               
               
                                    </div>
      
		</div>






						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				
			</div><!-- /.main-container-inner -->



<input id="zhangid" value="<%=id %>" hidden>

<div style="display: none">
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Ff0cfcccd7b1393990c78efdeebff3968' type='text/javascript'%3E%3C/script%3E"));
(function (d) {
window.bd_cpro_rtid="rHT4P1c";
var s = d.createElement("script");s.type = "text/javascript";s.async = true;s.src = location.protocol + "//cpro.baidu.com/cpro/ui/rt.js";
var s0 = d.getElementsByTagName("script")[0];s0.parentNode.insertBefore(s, s0);
})(document);
</script>
<script>
(function(){
    var bp = document.createElement('script');
    bp.src = '//push.zhanzhang.baidu.com/push.js';
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(bp, s);
})();
</script>
</div>
	
	</body>
</html>
