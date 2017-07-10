<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>时间轴 - Bootstrap后台管理系统模版Ace下载</title>
		<meta name="keywords" content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文" />
		<meta name="description" content="JS代码网提供Bootstrap模版,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="../../assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="../../assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="../../assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<!-- fonts -->

		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

		<!-- ace styles -->

		<link rel="stylesheet" href="../../assets/css/ace.min.css" />
		<link rel="stylesheet" href="../../assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="../../assets/css/ace-skins.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="../../assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		<script src="../../assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="../../assets/js/html5shiv.js"></script>
		<script src="../../assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
		

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				

				<div class="main-content">
					
					<div class="page-content">
						<div class="page-header">
							<h1>
								<i class="icon-th-large" style="color:orange"></i> &nbsp;知识分享
								<small style="margin-left:400px">
									
									&nbsp;&nbsp;分&nbsp;享&nbsp;你&nbsp;的&nbsp;知&nbsp;识&nbsp;，&nbsp;留&nbsp;下&nbsp;美&nbsp;好&nbsp;瞬&nbsp;间&nbsp;!
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="align-right">
									<span class="green middle bolder">刷新 &nbsp;
																			<a href="index.html"><i class="icon-refresh blue bigger-120"></i></a>
									</span>

									
								</div>
<br><br>
								<div id="timeline-1">
									<div class="row">
										<div class="col-xs-12 col-sm-10 col-sm-offset-1">
											<div class="">
												

												<div class="timeline-items">
													<div class="timeline-item">
														<div class="timeline-info">
															<img alt="Susan't Avatar" src="../../assets/avatars/touxiang.jpg" />
															
															<span style="margin-top:15px"class="label label-info label-sm">孙英龙</span>
														</div>

														<div class="widget-box transparent">
															<div class="widget-header widget-header-small">
																<h5 class="smaller">
																	<a href="#" class="blue"></a>
																	
																</h5>

																<span class="widget-toolbar no-border">
																	<i class="icon-time bigger-110"></i>
																	2016-05-04 16:22
																</span>

																
															</div>

															<div class="widget-body">
																<div class="widget-main">
																<div style="margin-left:30px">
																	C语言详细思维导图，每个知识点之间层次关联密切，层次分明
																	
																	能够很好地帮助我对C语言的学习 &hellip;</div>
																	<div class="space-6"></div>

																	<div class="widget-toolbox clearfix">
																		<div class="pull-left">
																			<i class="icon-hand-right grey bigger-125"></i>
																			<a href="#" class="bigger-110">查看详情 &hellip;</a>
																		</div>

																		<div class="pull-right action-buttons">
																			<a href="#">
																				<i class="bigger-130"><img src="../../img/vote_yes.png" title="点赞">108</i>
																				
																			</a>

																			&nbsp;&nbsp;&nbsp;

																			<a href="#">
																				<i class="red bigger-125"><img src="../../img/vote_no.png" title="不喜欢">2</i>
																			</a>
																		</div>
																	</div>
																</div>
															</div>
															
														</div>
													</div>

													
														</div>
														
														
													</div>

													
												</div><!-- /.timeline-items -->
											</div><!-- /.timeline-container -->

											

											
										</div>
										<div id="timeline-1">
									<div class="row">
										<div class="col-xs-12 col-sm-10 col-sm-offset-1">
											<div class="">
												

												<div class="timeline-items">
													<div class="timeline-item">
														<div class="timeline-info">
															<img alt="Susan't Avatar" src="../../assets/avatars/touxiang.jpg" />
															
															<span style="margin-top:15px"class="label label-info label-sm">孙涵飞</span>
														</div>

														<div class="widget-box transparent">
															<div class="widget-header widget-header-small">
																<h5 class="smaller">
																	<a href="#" class="blue"></a>
																	
																</h5>

																<span class="widget-toolbar no-border">
																	<i class="icon-time bigger-110"></i>
																	2016-05-04 16:22
																</span>

																
															</div>

															<div class="widget-body">
																<div class="widget-main">
																<div style="margin-left:30px">
																	C语言详细思维导图，每个知识点之间层次关联密切，层次分明
																	
																	能够很好地帮助我对C语言的学习 &hellip;</div>
																	<div class="space-6"></div>

																	<div class="widget-toolbox clearfix">
																		<div class="pull-left">
																			<i class="icon-hand-right grey bigger-125"></i>
																			<a href="#" class="bigger-110">查看详情 &hellip;</a>
																		</div>

																		<div class="pull-right action-buttons">
																			<a href="#">
																				<i class="bigger-130"><img src="../../img/vote_yes.png" title="点赞">108</i>
																				
																			</a>

																			&nbsp;&nbsp;&nbsp;

																			<a href="#">
																				<i class="red bigger-125"><img src="../../img/vote_no.png" title="不喜欢">2</i>
																			</a>
																		</div>
																	</div>
																</div>
															</div>
															
														</div>
													</div>

													
														</div>
														
														
													</div>

													
												</div><!-- /.timeline-items -->
											</div><!-- /.timeline-container -->

											

											
										</div>
										
									</div>
								</div>

								

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				
			</div><!-- /.main-container-inner -->

			
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->


		<!-- <![endif]-->

		<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='../../assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='../../assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='../../assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="../../assets/js/bootstrap.min.js"></script>
		<script src="../../assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!-- ace scripts -->

		<script src="../../assets/js/ace-elements.min.js"></script>
		<script src="../../assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			jQuery(function($) {
				$('[data-toggle="buttons"] .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					$('[id*="timeline-"]').addClass('hide');
					$('#timeline-'+which).removeClass('hide');
				});
			});
		</script>
	</body>
</html>
