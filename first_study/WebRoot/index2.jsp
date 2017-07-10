<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>WebSocket Chat Client</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  	<script src="js/jquery-1.7.1.min.js" type="text/javascript" ></script>
    <script type="text/javascript">
      
      	//初始化判断浏览器是否支持Websocket
      $(document).ready(function() {    
    
			  if(!("WebSocket" in window)){    
			  		$('#chatLog, input, button, #examples').fadeOut("fast");    
			  		$('<p>您的浏览器不支持Html5的Websocket</p>').appendTo('#container');    
			  }else{
			  
			  	//文本框注册回车发送事件	
			   $('#text').keypress(function(event) {    
              		
              		if (event.keyCode == '13') {    
                		send();    
              		}    
         			 }); 
			  
			  }
			  
			  
		});  
	 var socket;  
	 //连接
	 function connect(){    
         
            
          var host = "ws://localhost:1984";    
    
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
    
    
    function login(){
    	 var msg = {'msgtype':'login','username':'sunhanfei'};
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
         
          $('#son').contents().find("#content123").append(msg);
	
		  document.getElementById('son').contentWindow.gundong();
          }    
    
          $('#disconnect').live('click',function(){
        	  
        	  var msg = "{'msgtype':'exit'}";
         	  socket.send(msg);
        	
        	
          });
               

    </script>
    
  </head>
  <body>
 <iframe src="login1.jsp" name="frm2" id="son" width="1350" height="580"></iframe>
  </body>
</html>