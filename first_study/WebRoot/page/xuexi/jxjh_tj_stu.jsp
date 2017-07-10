
<%@ page language="java" import="java.util.*,xx.collection.bean.*" pageEncoding="utf-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>教学计划管理</title>
	<style type="text/css">
	  .high{
	    color:#F0000F;
	  }
	  form.cmxform label.error, label.error {
	  /* remove the next line when you have trouble in IE6 with labels in list */
	  color: red;
	
     }
   </style>


	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="js/ui/datagrid-detailview.js"></script>
	<script  type="text/javascript" src="js/jquery.validate.js"></script>
	 <script type="text/javascript" src="js/jxjh_tj_stu.js" ></script>
	 
    <link rel="stylesheet" type="text/css" href="css/thickbox.css" />
	
 
  
</head>
<body >
 <table id="test"></table>


</body>
</html>