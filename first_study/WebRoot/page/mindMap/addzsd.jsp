<%@page import="xx.mindMap.action.AllFile"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
List<AllFile> rList=(List<AllFile>)request.getSession().getAttribute("rows");
	System.out.println("haha   "+rList);
 %>	
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>机构树菜单树 - Bootstrap后台管理系统模版Ace下载</title>
		<meta name="keywords" content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文" />
		<meta name="description" content="JS代码网提供Bootstrap模版,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="../../assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="../../assets/css/font-awesome.min.css" />


		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

		<!-- ace styles -->

		<link rel="stylesheet" href="../../assets/css/ace.min.css" />
		<link rel="stylesheet" href="../../assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="../../assets/css/ace-skins.min.css" />

 <script src="../../assets/tree/jquery-1.11.3.min.js"></script>

		
		
	 <link rel="stylesheet" href="../../assets/tree/jquery.tree-multiselect.min.css">
	

		<script src="../../assets/js/ace-extra.min.js"></script>
     <script src="../../assets/tree/jquery-ui.min.js"></script>
     <script src="../../assets/tree/jquery.tree-multiselect.js"></script>
	</head>

	<body>
		
		<div class="main-container" id="main-container">
			

			<div class="main-container-inner">

				<div class="main-content">
					
					<div class="page-content">
						

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="row">
								<div id="user-profile-2" class="user-profile">
										<div class="tabbable">
											<ul class="nav nav-tabs padding-18">
												<li class="active">
													<a data-toggle="tab" href="#home">
														<i class="green  icon-folder-open bigger-120"></i>
														选择指定文件
													</a>
												</li>

												
											</ul>

											<div id="content" class="tab-content no-border padding-24">
												<div id="home" class="tab-pane in active">
													<div class="row">

										<div class="widget-main">
													<select id="test-select" multiple="multiple">
													<%
													for(int i=0;i<rList.size()/2;i++){
													AllFile aFil=rList.get(i);
													%>
													 <option value='<%=aFil.getZlid() %>' data-section='<%=aFil.getKey() %>'><%=aFil.getName() %></option>
													<%
													}
													 %>
     
     
    </select>

    <script type="text/javascript">
      $("#test-select").treeMultiselect({ enableSelectAll: true, sortable: true });
    </script>
												
	
												</div>
										
										
									
									</div>
									
								</form>
														</div><!-- /span -->
														<br>
														<div class="messagebar-item-right">
																	<span class="inline btn-send-message">
																		<button id="tijiao" type="button" class="btn btn-sm btn-primary no-border">
																			<span class="bigger-110">保存</span>

																			<i class="icon-arrow-right icon-on-right"></i>
																		</button>
																	</span>
																</div>
													</div><!-- /row-fluid -->

													
												</div><!-- #home -->
												<br><br><br><br><br>
											<button id="state" class="btn btn-lg btn-success" style="margin-left:37%">
												<i class="icon-ok"></i>
												保存成功
											</button>
												
</div>
</div>

									
								</div>

							</div><!-- /.col -->
							
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				
			</div><!-- /.main-container-inner -->

		</div><!-- /.main-container -->
	<script src="../../assets/jsmind/jquery-1.4.4.min.js" type="text/javascript"></script>
		
<script type="text/javascript">
			$(function(){
			$("#state").hide();
			
			$('#tijiao').click(function(){
			var mapid= parent.document.getElementById("mapid").value;
            var nodeid=parent.get_selected_nodeid();
			
			var a ="";
			$("#test-select option:selected").each(function(){
            
            a=a+$(this).val()+",";
            });
             var mind_string = parent.show_data();
           var da={"gaishu":a,"mapid":mapid,"nodeid":nodeid,"type":"3","jsondata":mind_string};
         
            $.ajax({
						type : "POST",
						url : "saveZhishidian.action",
						data : da,
						success : function(json) {
							if (json.state == 0) {
								$("#content").hide();
								$("#state").show();
								$("#submit-button").hide();
								parent.mapid(json.mapid);
							} else {
								$.messager.alert("操作提示", "操作失败！", "error");
							}
						}
					});
			});
			});
			</script>	
		
	</body>
</html>
