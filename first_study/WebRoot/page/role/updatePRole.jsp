<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<!-- 
	<h2>修改角色</h2>
	<s:form action="updateRole">
			<s:hidden name="role.roleid" value="%{role.roleid}"></s:hidden>
			角色名称：<s:textfield value="%{role.rolename}" name="role.rolename"></s:textfield><br/>
			角色描述：<s:textfield value="%{role.miaoshu}" name="role.miaoshu"></s:textfield><br/>
			<s:submit></s:submit>
	</s:form>
	 -->
	<div id="all">
	  <div id="bg_top">
	    <div>
	      <div> <img id="icon" src="images/role/icon.gif" /> <span id="daohang">您所在的位置:</span> <span id="weizhi">[<a href="#">后台</a>]--[<a href="#">首页</a>]</span> </div>
	    </div>
	  </div>
	  <div id="content">
	  
	    <table>
	    <s:form action="updateRole">
	    <s:hidden name="role.roleid" value="%{role.roleid}"></s:hidden>
	      <caption>
	      <h2>修改角色</h2><br/>
	      </caption>
	      <tr>
	        <td align="left">&nbsp;&nbsp;&nbsp;角色名称:<s:textfield value="%{role.rolename}" name="role.rolename"/></td>
	      </tr>
	      <tr>
	      	<td align="left">
	      	&nbsp;&nbsp;&nbsp;角色描述:<s:textfield value="%{role.miaoshu}" name="role.miaoshu"/></td>
	      </tr>
	      <tr>
	      	<td align="left">
	      		<s:submit value="提交"></s:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:reset value="取消" onClick="window.location.href='listRoles.action'"></s:reset>
	      	</td>
	      </tr>
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
