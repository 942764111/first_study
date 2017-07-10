<%@ page language="java" import="java.util.*,xx.collection.bean.Studentifno,java.text.SimpleDateFormat" pageEncoding="utf-8"%>            
<%@ taglib prefix="s" uri="/struts-tags"%>

<html style="height:100%;width:100%;">
<head>

<title>jQuery EasyUI</title>

<style type="text/css">
	  .high{
	    color:#F0000F;
	  }
	  form.cmxform label.error, label.error {
	  /* remove the next line when you have trouble in IE6 with labels in list */
	  color: red;
	
     }
</style>
    <link href="css/login_style.css" type="text/css" rel="stylesheet">
	<script  type="text/javascript" src="js/jquery.js" ></script>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	
   <script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
    <script  type="text/javascript" src="js/jquery.validate.js"></script>
	<script type="text/javascript" src="js/validateStudent.js" charset="gb2312"></script>
	<script type="text/javascript" src="js/bzx_sjld/bzx_sjld.js"></script>
<script type="text/javascript" >
$(function(){
	<%
	Studentifno stu=(Studentifno)request.getAttribute("student"); 
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //时间格式化的格式 
	sDateFormat.setLenient(false);
	Date tt=stu.getRxny();
	String t="";
	if(tt!=null){
		 t = sDateFormat.format(tt); //当前时间
	}
	
	%>
	var tt="<%=t%>";
	
	$(".datebox :text").val(tt);
})
</script>

</head>
<body style="height:100%;width:100%;overflow:hidden;border:none;" >


	<div id="w" class="easyui-window" title="学生个人信息" iconCls="icon-save" style="width:978;height:475;padding:5px;background: #fafafa;" fit="true">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:0px;background:#fff;border:1px solid #ccc;">
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%"><!-- height="70%" -->
      <tr>
       
        <td valign="top"> <s:form><table width="100%" height="80%" border="1px" style= "BORDER-COLLAPSE:   collapse " bordercolor="#C6C6C6" cellspacing="0" cellpadding="0" class="login_txt">
							  <tr bgcolor="#EFEFEF">
							    <td height="44" colspan="4" align="center" id="login_logo2">显示学生详细信息</td>
							  </tr>
							  <tr>
							    <td width="16%" height="38" align="center">注册ID：</td>
							    <td width="34%">
							      <s:property value="student.userinfo.userId"></s:property>   </td>
							    <td width="16%" align="center">姓名：</td>
							    <td width="34%"><s:property  value="student.SName" ></s:property></td>
							  </tr>
							  <tr bgcolor="#EFEFEF">
							    <td height="37" align="center">学号：</td>
							    <td><s:property value="student.SNo"></s:property></td>
							    <td align="center">性别：</td>
							    <td><s:property  value="student.SSex" /></td>
							  </tr>
							  <tr>
							     <td height="37" align="center">学院：</td>
							    <td><s:property value="student.bjxx.zyxx.xuyan.xymc" ></s:property></td>
							    <td align="center">专业：</td>
							    <td><s:property value="student.bjxx.zyxx.zymc" ></s:property></td>
							  </tr>
							  <tr bgcolor="#EFEFEF">
							    <td height="37" align="center">班级名称：</td>
							    <td><s:property value="student.bjxx.bjmc" ></s:property></td>
							    <td align="center">职务：</td>
							    <td><s:property value="student.xszw"></s:property></td>
							   
							  </tr>
							  <tr>
							    <td height="36" align="center">手机：</td>
							    <td><s:property value="student.handphone"></s:property></td>
							    <td align="center">E-mail：</td>
							    <td><s:property value="student.email" ></s:property></td>
							  </tr>
							  <tr bgcolor="#EFEFEF">
							    <td height="35" align="center">入学年月：</td>
							    <td colspan="3"><s:property value="student.rxny"></s:property></td>
							    </tr>
							</table></s:form></td>
        
      </tr>
    </table>
		
			</div>
			<div region="south" border="false" style="text-align:center;height:30%;">
				
				
				<a class="easyui-linkbutton" iconCls="icon-ok" href="#" id="nextWindows">修改</a>
				
			</div>
		</div>
	</div>
	
	
	<div id="test" class="easyui-window" closed="true" modal="true" title="学生个人信息" style="width:978px;height:475px;" fit="true">
			
			<div class="easyui-layout" fit="true" style="visibility:hidden;" id="ggggg">
				<div region="center" border="false" style="padding:0px;background:#fff;border:1px solid #ccc;">
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
	      <tr>
	       
	        <td valign="top"> <s:form id="allSS" class="cmxform"><table width="100%" height="80%" border="1px" style= "BORDER-COLLAPSE:   collapse " bordercolor="#C6C6C6"cellspacing="0" cellpadding="0" class="login_txt">
						        
									  <tr bgcolor="#EFEFEF">
									    <td height="44" colspan="4" align="center" id="login_logo2">修改或添加学生详细信息</td>
									  </tr>
									  <tr>
									    <td width="16%" height="38" align="center">注册ID：<em class="high">*</em></td>
									    <td width="34%">
									      <s:textfield id="userid" name="student.userinfo.userId" ></s:textfield><span id="userid-hint" class=""></span>    </td>
									    <td width="16%" align="center">姓名：<em class="high">*</em></td>
									    <td width="34%"><s:textfield  label="姓名" name="student.SName" ></s:textfield></td>
									  </tr>
									  <tr bgcolor="#EFEFEF">
									    <td height="37" align="center">学号：<em class="high">*</em></td>
									    <td><s:textfield label="学号" name="student.SNo" id="sno"></s:textfield><span id="stuhint" class=""></span> </td>
									    <td align="center">性别：</td>
									    <td><s:select  name="student.SSex" list="{'男 ','女 '}"/></td>
									  </tr>
									  <tr>
									    <td height="37" align="center">学院：<em class="high">*</em></td>
									    <td><select id="bindX" onChange="getZybh()" style="width:150px;">
									          <option  value="<s:property value="student.bjxx.zyxx.xuyan.xymc" ></s:property>"><s:property value="student.bjxx.zyxx.xuyan.xymc" ></s:property></option>
									        </select><span id="hintx" class=""></span></td>
									    <td align="center">专业：<em class="high">*</em></td>
									    <td><select id="bindZ" onChange="getBjbh()" style="width:150px;">
									   		<option  value="<s:property value="student.bjxx.zyxx.zymc" ></s:property>"><s:property value="student.bjxx.zyxx.zymc" ></s:property></option>
									   	</select><span id="hintz" class=""></span></td>
									    
									  </tr>
									  <tr bgcolor="#EFEFEF">
									    <td height="37" align="center">班级名称：<em class="high">*</em></td>
									    <td><select name="student.bjxx.bjbh" id="bjbh" onChange="getBjmc()"style="width:150px;">
										     <option value="<s:property value="student.bjxx.bjbh" ></s:property>"><s:property value="student.bjxx.bjmc" ></s:property></option>
										    </select><span id="hintb" class=""></span></td>
									    <td align="center">职务：</td>
									    <td><s:textfield name="student.xszw"></s:textfield></td>
									  </tr>
									  <tr>
									    <td height="36" align="center">手机：</td>
									    <td><s:textfield name="student.handphone"></s:textfield></td>
									    <td align="center">E-mail：</td>
									    <td><s:textfield name="student.email" ></s:textfield></td>
									  </tr>
									  <tr bgcolor="#EFEFEF">
									    <td height="35" align="center">入学年月：</td>
									    <td colspan="3"><input name="student.rxny" class="easyui-datebox"></input></td>
									    </tr>
									 
					                </table></s:form></td>
	       
	      </tr>
	    </table>
					
					
					
				</div>
				<div region="south" border="false" style="text-align:center;height:30%;">
			
				   <a class="easyui-linkbutton" iconCls="icon-ok" href="#" id="submit">提交</a>
				
			    </div>
			</div>
	</div>
</body>
</html>