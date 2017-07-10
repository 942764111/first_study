<%@ page language="java" import="java.util.*,xx.collection.bean.Userinfo" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教学计划</title>
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
	<script  type="text/javascript" src="js/jquery.validate.js"></script>
	<script type="text/javascript" src="js/jxjh.js"></script>
	<script type="text/javascript">
	  $(function(){
              <%
                 Userinfo user=(Userinfo)session.getAttribute("userifno");
                 String type=user.getType();
                 int roleid=user.getRoles().getRoleid();
                 String tea_stu=(String)session.getAttribute("tip");
                 System.out.println("type: "+type+" roleid: "+roleid+" tea_stu: "+tea_stu);
              %>
              var tipp="<%=type%>";
              var rid="<%=roleid%>";
              var tea_stu_temp="<%=tea_stu%>";
              //alert(tipp);
              if(tipp=="S"||rid==106){
                  if(tea_stu_temp=="bht_stu"){//表示请求是来自jxjh_tj_stu.jsp页面
                	   $('#btnadd').remove();//添加一次课按钮
	   	        	   $('#btnadd1').remove();//试卷管理按钮
	   	        	   $('#btnadd2').remove();//学生答题记录管理按钮
	   	        	   $('#btnupdate').remove();//修改一次课按钮
	   	        	   $('#btndelete').remove();//删除一次课按钮
                      }
	        	   

		           }
		  })
	</script>
  </head>
  
  <body style="margin-left: 0px;margin-top: 0px;margin-bottom: 0px;margin-right: 0px;margin: 0px;border-bottom: 0px;">
  <table id="test"></table>
		
	<div id="dd1" class="easyui-window" title="添加一次课" style="padding: 5px; width: 450; height: 195;font-size: 2px;" 
		iconCls="icon-edit"	closed="true" maximizable="false" minimizable="false" collapsible="false">
		<div style="visibility:hidden;" id="ggggg1">
		<s:form id="add" >
			<table align="center" width="410px" style="font-size: 13px;">
				<tr>
					<td>
						内容概述：
					</td>
					<td>
						<s:textfield id="addJhmc" name="jhmc" maxLength="40"></s:textfield>
					</td>
				</tr>
				<tr> 
					<td>
						课时：
					</td>
					<td>  
						<input id="addXsh" name="ks" type="text"/>
					</td>
				</tr>
				<tr> 
					<td>
						对应章：
					</td>
					<td>  
						<select id="dyz" style="width:150px;">
						   <option value=-1>---请选择---</option>
						</select>
					</td>
				</tr>
				<tr>
				    <td colspan="2" align="right">
				       <table>
				       <tr><td><a id="tijiaojiao" class="easyui-linkbutton" iconCls="icon-ok"
							href="javascript:void(0)">继续添加</a></td>
				         <td><a id="tijiao" class="easyui-linkbutton" iconCls="icon-ok"
							href="javascript:void(0)">完成</a></td>
				         <td><a class="easyui-linkbutton" iconCls="icon-cancel"
							href="javascript:void(0)" onclick="close1()">取消</a></td>
				       </tr></table>
						
					</td>
					
				</tr>
			</table>
			</s:form>
			</div>
	</div> 
			
	<div id="edit1" class="easyui-window" title="修改该次课" style="padding: 0px; width: 450; height: 270;font-size: 2px;" 
		iconCls="icon-edit"	closed="true" maximizable="false" minimizable="false" collapsible="false">
		<div style="visibility:hidden;" id="ggggg2">
		<s:form id="form">
			<table align="center" width="410px" style="font-size: 13px;">
				 <tr>
					<td height="2px">
					</td>
					<td> 
						<input id="jhbh" name="id" readonly="readonly" style="background-color:#EBEBE4; visibility:hidden;"/>
					</td>
				</tr>
				
				
				<tr>
					<td>
						内容概述：
					</td>
					<td>
						<s:textfield id="updateJhmc" name="jhmc1" maxLength="40"></s:textfield>
					</td>
				</tr>
				<tr><td height="10px"></td><td></td></tr>
				<tr> 
					<td>
						课时：
					</td>
					<td>  
						<input id="updateXsh" name="ksxg" type="text"/>
					</td>
				</tr>
				<tr><td height="10px"></td><td></td></tr>
				<tr> 
					<td>
						对应章：
					</td>
					<td>  
						<s:select id="updateKcm" list="kcxxs" listKey="zbh" listValue="CName" headerKey='-1' headerValue="---请选择---" name="Kname1">
						</s:select>
					</td>
				</tr>
				<tr><td height="20px"></td><td></td></tr>
				<tr>
					<td colspan="2" align="right" >
						<a class="easyui-linkbutton" iconCls="icon-ok" id="next" href="javascript:void(0)" onclick="edit1()">下一条</a>
						<a id="tijiao1" class="easyui-linkbutton" iconCls="icon-ok"
							href="javascript:void(0)">完成</a>
						<a class="easyui-linkbutton" iconCls="icon-cancel"
							href="javascript:void(0)" onclick="close3()">取消</a>
					</td>
				</tr>
			</table>
			</s:form>
			</div>
		</div> 
	<div id="query" class="easyui-window" title="查询" style="padding: 5px; width: 430px; height: 95px;"
			iconCls="icon-search" closed="true" maximizable="false"
			minimizable="false" collapsible="false">
			<div style="visibility:hidden;" id="ggggg3">
				<form>
					<table style="font-size: 13px;">
						<tr>
								<td>
								内容概述：<s:textfield id="nrgs"></s:textfield>
							
								</td>
								<td>
								<a id="search" class="easyui-linkbutton" iconCls="icon-search"
									href="javascript:void(0);" onclick="query()">模糊查询</a>
								</td>
						</tr>
					</table>
				</form>
			</div>
		</div> 
	 </body>
</html>
