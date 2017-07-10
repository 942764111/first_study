
<%@ page language="java" import="java.util.*,xx.collection.bean.*" pageEncoding="utf-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	
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


	<link rel="stylesheet" type="text/css" href="../../js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../js/ui/themes/icon.css">
	<script type="text/javascript" src="../../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../../js/ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/ui/datagrid-detailview.js"></script>
	<script  type="text/javascript" src="js/jquery.validate.js"></script>
	 <script type="text/javascript" src="js/jxjh_tj.js" ></script>
	 
    <link rel="stylesheet" type="text/css" href="css/thickbox.css" />
	<script >
	
	
	  
	</script>
 
  
</head>
<body >
 <table id="test"></table>
 
   <div id="add" class="easyui-window" title="添加教学计划" style="padding: 5px; width: 820; height: 450;font-size: 2px;" 
		iconCls="icon-add"	closed="true" maximizable="false" minimizable="false" collapsible="false">
		<div style="visibility:hidden;" id="ggggg1">
			<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" style="font-size: 13px;">
			  <tr>
			    <td height="29" colspan="3" align="center"><s:form id="form"><table width="100%" border="0" cellspacing="0" cellpadding="0" style="font-size: 13px;">
			      <tr>
			        <td width="30%" align="center">教师姓名:<s:property value="tname" ></s:property></td>
			        <td width="30%" align="center">课程名称:<s:select id="kcname" list="kcnamelist" listKey="key" listValue="value" headerKey="-1" headerValue="---请选择---">
						</s:select></td>
			        <td width="40%" align="center">对应学期:<input name="d1n" id="n1" style="width:40px;"/>-<input name="d2n" id="n2"style="width:40px;background-color:#EBEBE4;" readonly="readonly"/>学年之第<s:select name="d3n" list="#{1:'1',2:'2'}" listKey="key" listValue="value" id="n3" style="width:40px;"></s:select>学期</td>
			      </tr>
			    </table></s:form></td>
			  </tr>
			  <tr>
			    <td colspan="3" >
				    <table id="test2" style="width:770px;height:320px" title="添加课次" iconCls="icon-edit"  >
						
					</table>   </td>
			  </tr>
			  <tr>
			    <td width="30%" height="34">&nbsp;</td>
			    <td width="42%" align="right"><a id="submit" class="easyui-linkbutton" iconCls="icon-ok"
							href="javascript:void(0)" >提交</a>
						<a class="easyui-linkbutton" iconCls="icon-cancel"
							href="javascript:void(0)" onclick="close1();">取消</a></td>
			    <td width="28%">&nbsp;</td>
			  </tr>
			</table>
	   </div>
   </div> 
   
   <div id="addjxjhfz" class="easyui-window" title="使用该教学计划" style="padding: 5px; width: 450; height: 250;font-size: 2px;" 
		iconCls="icon-add"	closed="true" maximizable="false" minimizable="false" collapsible="false">
		<div style="visibility:hidden;" id="ggggg2">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td width="30%" align="center" height="30px">新教师姓名：</td>
			    <td width="70%"><s:property value="tname" ></s:property></td>
			  </tr>
			  <tr>
			    <td align="center" height="30px">课程名称：</td>
			    <td><input id="fzkcmc"  readonly="readonly" style="background-color:#EBEBE4;"/></td>
			  </tr>
			  <tr>
			    <td align="center" height="30px">学期：</td>
			    <td><input id="n4" style="width:40px;"/>-<input id="n5"style="width:40px;background-color:#EBEBE4;" readonly="readonly"/>学年之第<s:select list="#{1:'1',2:'2'}" listKey="key" listValue="value" id="n6" style="width:40px;"></s:select>学期</td>
			  </tr>
			  <tr>
			    <td align="center" height="30px">教学次数：</td>
			    <td><input id="fzjxcs" readonly="readonly" style="background-color:#EBEBE4;"/></td>
			  </tr>
			  <tr>
			    <td align="center" height="50px">&nbsp;</td>
			    <td align="center">
			      <a class="easyui-linkbutton" iconCls="icon-ok"
							href="javascript:void(0)" onclick="tjfzjxjh();">确认</a>
						<a class="easyui-linkbutton" iconCls="icon-cancel"
							href="javascript:void(0)" onclick="closefzjxjh();">取消</a>
			    
			    </td>
			  </tr>
			</table>
	   </div>
   </div> 
		

</body>
</html>