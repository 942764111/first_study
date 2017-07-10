<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Roles授权页</title>
		<link rel="stylesheet" type="text/css" href="css/role_style.css">
		<script type="text/javascript" src="js/role_style.js"></script>
		<script type="text/javascript" >
			function tableEvent(){
				var rows = document.getElementsByTagName('tr');
				for (var i=0;i<rows.length;i++){
					rows[i].onmouseover = function(){	
					//鼠标在行上面的时候
					this.className += 'altrow';
					}
					rows[i].onmouseout = function(){		//鼠标离开时
						this.className = this.className.replace('altrow','');
					}
				}
			}
		</script>
	</head>
	
	<body onLoad="tableEvent();init();" onResize="init();">
	<div id="all">
	  <div id="bg_top">
	    <div>
	      <div></div>
	    </div>
	  </div>
	  <div id="content">
	    <table border="1">
	      <caption>
	      <h2>table</h2><br/>
	      </caption>
	      <s:form action="updateRoleToFunction">
		<table border="1">
			<s:hidden name="role.roleid" value="%{role.roleid}"></s:hidden>
			<tr>
				<th>1</th>
				<th>2</th>
				<th>3</th>
	         </tr>
					
			<tr>
				<td>1
				</td>
				<td>2
				</td>
				<td>3
				</td>
			</tr>
				
			
			</table>
		</s:form>
	     </table>
	     </div>
	  <div id="bg_bottom">
	    <div>
	      <div></div>
	    </div>
	  </div>
	 </div>

	</body>
</html>