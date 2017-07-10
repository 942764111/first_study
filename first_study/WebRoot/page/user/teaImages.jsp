<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>touxiang</title>
    <meta http-equiv="pragma" content="no-cache"/>
 
    <meta http-equiv="cache-control" content="no-cache"/>
 
    <meta http-equiv="expires" content="0"/>
    
    <script type="text/javascript">
       <%
          String uid=(String)session.getAttribute("uid");
       %>
       function road(){
    	   var img = document.getElementById("image");
           img.src = "../../upload/teaImages/<%=uid%>.jpg?" + Math.random(); 
           }
   </script>
  </head>
  
   <body onload="road()">
    <table><tr><td>
      <img id="image" src="" align="middle"/>
    </td></tr></table>
  </body>
</html>
