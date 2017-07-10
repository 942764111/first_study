<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String uid=(String)request.getSession().getAttribute("uid");

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

		<link href="../../css/ace/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="../../css/ace/font-awesome.min.css" />

		
		<link rel="stylesheet" href="../../css/ace/bootstrap-editable.css" />

		<!-- fonts -->

		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

		<!-- ace styles -->

		<link rel="stylesheet" href="../../css/ace/ace.min.css" />
	
		<script type="text/javascript" src="../../js/jquery-1.7.1.min.js"></script>
		
		 <script type="text/javascript">
      
      	//初始化判断浏览器是否支持Websocket
      $(document).ready(function() {    
    
			  if(!("WebSocket" in window)){    
			  		$('#chatLog, input, button, #examples').fadeOut("fast");    
			  		$('<p>您的浏览器不支持Html5的Websocket</p>').appendTo('#container');    
			  }else{
			  
			  	$('#text').keypress(function(event) {    
              		
              		if (event.keyCode == '13') {    
                		var text=$('#text').val();
                		$('#text').val('');
               	
                 send(text);  
              		}    
         			 }); 
			  
			  }
			  
			  
		});  
	 var socket;  
	 //连接
	 function connect(){    
         
            
          var host = "ws://127.0.0.1:1984";    
    
          try{    
          
              socket = new WebSocket(host);
              
              socket.onopen = function(){
             	
                 login();  
                 
            
              };    
    
              socket.onmessage = function(msg){
              
                var msgJson = JSON.parse(msg.data);
                if(msgJson.msgtype == 'chat'){
                	message(msgJson.msg);
                }
                
              } ;   
    
              socket.onclose = function(){    
               // alert('您退出了房间!');
             
              };           
    
          } catch(exception){    
             message('<p>Error'+exception);    
          };
    } 
    
    connect();
    function login(){
    	 var msg = {'msgtype':'login','username':$("#uid").val()};
    	 
    	 socket.send(JSON.stringify(msg));
    
   }

		//发送消息
  		function send(text){    
           
              if(text==""){    
                  message('<p class="warning">Please enter a message');    
                  return ;    
              }    
              try{
              
              	var msg = {'msgtype':'chat','msg':text};
                  socket.send(JSON.stringify(msg));    
                 
              } catch(exception){    
                 message('<p class="warning">');    
              }    
              $('#text').val("");    
          }    
    
          function message(msg){
         
          $("#content123").append(msg);
	
		  gundong();
          }    
    
          $('#disconnect').live('click',function(){
        	  
        	  var msg = "{'msgtype':'exit'}";
         	  socket.send(msg);
        	
        	
          });
               

    </script>
		
		
		<script type="text/javascript">
		$(function(){
	
         			 
         $('#Reply').click(function(){
       
          var text=$('#text').val();
          $('#text').val('');
         
          send(text);
       });
       
		
		})
		</script>
		 <script type="text/javascript">

function gundong(){

$('#content123').scrollTop( $('#content123')[0].scrollHeight );
}</script>
		<script type="text/javascript">
		$(function(){
		 $.ajax({  
			    type:'post',      
			    url:'onlineUser',  
			   
			    cache:false,  
			    dataType:'json',  
			    success:function(data){ 
			
			jQuery.each(data.list, function(i,item){ 
			
			$("#profile-feed-1").append("<div class='profile-activity clearfix'><div><img class='pull-left' alt='Alex Doe's avatar' src='../../assets/avatars/touxiang.jpg' />'"+
			
			"<a class='user' href='#'>"+item+"</a><div class='time'><i class='icon-time bigger-110'></i>每天都要保持好心情</div></div><div class='tools action-buttons'>"+
			
			"</div></div>");
			
														
	         })}
	          });
		})
		</script>
		
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
				
						

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								

								<div style="width:1100px;margin-left:80px">
									<div id="user-profile-1" class="user-profile row">
										
										<div class="col-xs-12 col-sm-9">
											


											<div class="widget-box transparent">
												<div class="widget-header widget-header-small">
													<h4 class="blue smaller">
														
														在线用户
													</h4>

													

													<div class="widget-toolbar action-buttons">
												
													&nbsp;
														<a href="#" data-action="reload">
															<i class="icon-refresh blue"></i>
														</a>

														
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-8">
														<div id="profile-feed-1" class="profile-feed" style="height:400px;overflow:auto;" >
															

															
															
															
															
															
															
															
															
															
															
														</div>
													</div>
												</div>
												
											</div>

											<div class="hr hr2 hr-double"></div>

											<div class="space-6"></div>

										</div>
										
										<div class="col-sm-6">
										<div class="widget-box ">
											<div class="widget-header">
												<h4 class="lighter smaller">
													<i class="icon-comment blue"></i>
													会话
												</h4>
											</div>

											<div class="widget-body">
												<div class="widget-main no-padding">
													<div class="dialogs" style="height:300px;overflow:auto;" id="content123">
						
														
													</div>

												
														<div class="form-actions">
															<div class="input-group">
																<input placeholder="输入要发送的消息 ..." type="text" class="form-control" name="message" id="text" />
																<span class="input-group-btn">
																	<button class="btn btn-sm btn-info no-radius" type="button" id="Reply">
																		<i class="icon-share-alt"></i>
																		发送
																	</button>
																</span>
															</div>
														</div>
												
												</div><!-- /widget-main -->
											</div><!-- /widget-body -->
										</div><!-- /widget-box -->
									</div><!-- /span -->
									</div>
									
									
									
									
									
									
									
								
								</div>

								
								
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
						
						
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				
			</div><!-- /.main-container-inner -->

		</div><!-- /.main-container -->

	<input type="text" id="uid" value="<%=uid %>" hidden>
	

		
	</body>
</html>
