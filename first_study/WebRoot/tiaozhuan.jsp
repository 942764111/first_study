<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>

<title>混合式学习</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">-->
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
    <link href="css/mainxin.css" rel="stylesheet" type="text/css">
    
    <script type="text/javascript">
    function Rload(){
    	window.location.href='adminfunctions.action?main=' + 'teacher' +
		'&userlogin=' + 'login1';
    }
    </script>
</head>
<body class="easyui-layout" onload="Rload()">
</body>
</html>