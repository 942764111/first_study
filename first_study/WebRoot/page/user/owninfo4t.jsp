<%@ page language="java" import="java.util.*,xx.collection.bean.Teacher,java.text.SimpleDateFormat" pageEncoding="utf-8"%>            
<%@ taglib prefix="s" uri="/struts-tags"%>

<html style="height:100%;width:100%;">
<head>

<title>jQuery EasyUI</title>
<meta http-equiv="pragma" content="no-cache"/>
 
<meta http-equiv="cache-control" content="no-cache"/>
 
<meta http-equiv="expires" content="0"/>

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
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	
    
   <script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
    <script  type="text/javascript" src="js/jquery.validate.js"></script>
	<script type="text/javascript" src="js/validateTeacher.js" charset="gb2312"></script>
	
	<script type="text/javascript" src="uploadify/swfobject.js"></script>
	<script type="text/javascript" src="uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<link href="uploadify/uploadify.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="js/bzx_sjld/uploadImage.js"></script>
<script type="text/javascript" >
$(function(){
	<%
	Teacher tea=(Teacher)request.getAttribute("teacher"); 
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //时间格式化的格式 
	sDateFormat.setLenient(false);
	Date dtt=tea.getDtsj();
	Date rtt=tea.getRjny();
	Date ctt=tea.getCjgzsj();
	String dt="";
	String rt="";
	String ct="";
	if(dtt!=null){
		 dt = sDateFormat.format(dtt); //当前时间
	}
	if(rtt!=null){
		rt = sDateFormat.format(rtt); //当前时间
	}
	if(ctt!=null){
		 ct = sDateFormat.format(ctt); //当前时间
	}
	
		  System.out.println("student.rxny:"+dt+" "+rt+" "+ct);
	%>
	var dt="<%=dt%>";
	var rt="<%=rt%>";
	var ct="<%=ct%>";
	$('#t1').datebox('setValue',dt);
	$('#t2').datebox('setValue',rt);
	$('#t3').datebox('setValue',ct);
})

    function road(){
	        <%
             String uid=(String)session.getAttribute("uid");
            %>
    	   var img = document.getElementById("img");
           img.src = "../../upload/teaImages/<%=uid%>.jpg?" + Math.random(); 
    }
</script>
</head>
<body style="height:100%;width:100%;overflow:hidden;border:none;" onload="road()">


	<div id="w" class="easyui-window" title="教师个人信息" iconCls="icon-save" style="width:978;height:475;padding:5px;background: #fafafa;" fit="true">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:0px;background:#fff;border:1px solid #ccc;">
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
      <tr>
       
        <td valign="top" ><s:form>
           <table width="100%" height="90%" border="1" style= "BORDER-COLLAPSE:   collapse " bordercolor="#C6C6C6" cellspacing="0" cellpadding="0" class="login_txt">
			  <tr align="center" bgcolor="#EFEFEF">
			    <td height="41" colspan="5" id="login_logo2">显示教师详细信息</td>
			  </tr>
			  <tr>
			    <td width="14%" align="center">注册ID：</td>
			    <td width="32%"><s:property   value="teacher.userinfo.userId" ></s:property></td>
			    <td width="14%" align="center">姓名：</td>
			    <td colspan="2"><s:property  value="teacher.jsxm" ></s:property></td>
			  </tr>
			  <tr bgcolor="#EFEFEF">
			    <td align="center">性别：</td>
			    <td><s:property  value="teacher.jsxb" /></td>
			    <td align="center">教师照片：</td>
			    <td width="19%" align="right"><img src="js/ui/themes/icons/qianjin.png" align="middle"/></td>
			    <td width="21%" rowspan="7" align="center">  <img id="img" src="" align="middle"/></td>
			  </tr>
			  <tr>
			    <td align="center">民族：</td>
			    <td><s:property value="teacher.mz.mzmc"></s:property></td>
			    <td align="center">籍贯：</td>
			    <td><s:property value="teacher.jsjg"></s:property></td>
			  </tr>
			  <tr bgcolor="#EFEFEF">
			    <td align="center">教师编号：</td>
			    <td><s:property value="teacher.jsbh"></s:property></td>
			    <td align="center">所属学院：</td>
			    <td><s:property  value="teacher.xuyan.xymc"></s:property></td>
			  </tr>
			  <tr>
			    <td align="center">教师类别：</td>
			    <td><s:property  value="teacher.jslb.lbmc"></s:property ></td>
			    <td align="center">拼音：</td>
			    <td><s:property value="teacher.jspy" ></s:property></td>
			  </tr>
			  <tr bgcolor="#EFEFEF">
			    <td align="center">身份证号：</td>
			    <td><s:property   value="teacher.jssfz"></s:property ></td>
			    <td align="center">政治面貌：</td>
			    <td><s:property   value="teacher.zzmm" ></s:property ></td>
			  </tr>
			  <tr>
			    <td align="center">党团时间：</td>
			    <td><s:property value="teacher.dtsj"></s:property></td>
			    <td align="center">任教年月：</td>
			    <td><s:property   value="teacher.rjny"></s:property ></td>
			  </tr>
			  <tr bgcolor="#EFEFEF">
			    <td align="center">参加工作时间：</td>
			    <td><s:property  value="teacher.cjgzsj"></s:property ></td>
			    <td align="center">硕博导：</td>
			    <td><s:property   value="teacher.sbd"></s:property ></td>
			  </tr>
			  <tr>
			    <td align="center">办公室电话：</td>
			    <td><s:property   value="teacher.officephone"></s:property ></td>
			    <td align="center">手机：</td>
			    <td colspan="2"><s:property  value="teacher.handphone"></s:property ></td>
			  </tr>
			  <tr bgcolor="#EFEFEF">
			    <td align="center">家庭电话：</td>
			    <td><s:property  value="teacher.homephone"></s:property ></td>
			    <td align="center">电子邮箱：</td>
			    <td colspan="2"><s:property  value="teacher.email"></s:property ></td>
			  </tr>
			 
			</table></s:form>
        </td>
        
      </tr>
    </table>
		
			</div>
			<div region="south" border="false" style="text-align:center;height:30%;">
				
				
				<a class="easyui-linkbutton" iconCls="icon-ok" href="#" id="nextWindows">修改</a>
				
			</div>
		</div>
	</div>
	
	
	<div id="test" class="easyui-window" closed="true" modal="true" title="教师个人信息" style="width:978px;height:475px;" fit="true">
			
			<div class="easyui-layout" fit="true" style="visibility:hidden;" id="ggggg">
				<div region="center" border="false" style="padding:0px;background:#fff;border:1px solid #ccc;">
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
	      <tr>
	       
	        <td valign="top"> <s:form id="allPP" > <table width="100%" height="90%" border="1" style= "BORDER-COLLAPSE:   collapse " bordercolor="#C6C6C6" cellspacing="0" cellpadding="0" class="login_txt">
									      
											    
										   <tr align="center" bgcolor="#EFEFEF">
											    <td height="41" colspan="5" id="login_logo2">修改或添加教师详细信息</td>
											  </tr>
											  <tr>
											    <td width="14%" align="center">注册ID：<em class="high">*</em></td>
											    <td width="32%"><s:textfield   name="teacher.userinfo.userId" id="userid"></s:textfield><span id="userid-hint" class=""></span></td>
											    <td width="14%" align="center">姓名：<em class="high">*</em></td>
											    <td colspan="2"><s:textfield  label="姓名" name="teacher.jsxm" ></s:textfield></td>
											  </tr>
											  <tr bgcolor="#EFEFEF">
											    <td align="center">性别：</td>
											    <td><s:select  name="teacher.jsxb" list="{'男 ','女 '}"/></td>
											    <td align="center">教师照片：</td>
											    <td width="19%" align="left"><a href="#" onclick="add();"><font size=1 color=blue>修改头像</font></a></td>
											    <td width="21%" rowspan="7" align="center"><iframe name="CFrame" id="CFrame" frameborder="0" src="page/user/teaImages.jsp"></iframe></td>
											  </tr>
											  <tr>
											    <td align="center">民族：<em class="high">*</em></td>
											    <td><select id="bindMZ" name="teacher.mz.mzbh" style="width:150px;">
											          <option  value="<s:property value="teacher.mz.mzbh"></s:property>"><s:property value="teacher.mz.mzmc"></s:property></option>
											        </select></td>
											    <td align="center">籍贯：</td>
											    <td><s:textfield name="teacher.jsjg"></s:textfield></td>
											  </tr>
											  <tr bgcolor="#EFEFEF">
											    <td align="center">教师编号：<em class="high">*</em></td>
											    <td><s:textfield name="teacher.jsbh" id="teajsbh" readonly="true"></s:textfield><span id="teahint" class=""></span></td>
											    <td align="center">所属学院：<em class="high">*</em></td>
											    <td><select id="bindXY" name="teacher.xuyan.xybh" style="width:150px;">
											          <option  value="<s:property  value="teacher.xuyan.xybh"></s:property>"><s:property  value="teacher.xuyan.xymc"></s:property></option>
											        </select></td>
											  </tr>
											  <tr>
											    <td align="center">教师类别：<em class="high">*</em></td>
											    <td><select id="bindLB" name="teacher.jslb.jslb" style="width:150px;">
											          <option  value="<s:property  value="teacher.jslb.jslb"></s:property >"><s:property  value="teacher.jslb.lbmc"></s:property ></option>
											        </select></td>
											    <td align="center">拼音：</td>
											    <td><s:textfield name="teacher.jspy" ></s:textfield></td>
											  </tr>
											  <tr bgcolor="#EFEFEF">
											    <td align="center">身份证号：</td>
											    <td><s:textfield  label="身份证号" name="teacher.jssfz"></s:textfield></td>
											    <td align="center">政治面貌：</td>
											    <td><s:select  label="政治面貌" name="teacher.zzmm" list="{'群众','团员','党员'}"></s:select></td>
											  </tr>
											  <tr>
											    <td align="center">党团时间：</td>
											    <td><input id="t1" name="teacher.dtsj" class="easyui-datebox"></input></td>
											    <td align="center">任教年月：</td>
											    <td><input  id="t2" label="任教年月" name="teacher.rjny" class="easyui-datebox"></input></td>
											  </tr>
											  <tr bgcolor="#EFEFEF">
											    <td align="center">参加工作时间：</td>
											    <td><input id="t3"  label="参加工作时间" name="teacher.cjgzsj" class="easyui-datebox"></input></td>
											    <td align="center">硕博导：</td>
											    <td><s:textfield  name="teacher.sbd"></s:textfield></td>
											  </tr>
											  <tr>
											    <td align="center">办公室电话：</td>
											    <td><s:textfield  label="办公室电话" name="teacher.officephone"></s:textfield></td>
											    <td align="center">手机：</td>
											    <td colspan="2"><s:textfield name="teacher.handphone"></s:textfield></td>
											  </tr>
											  <tr bgcolor="#EFEFEF">
											    <td align="center">家庭电话：</td>
											    <td><s:textfield  label="家庭电话" name="teacher.homephone"></s:textfield></td>
											    <td align="center">电子邮箱：<em class="high">*</em></td>
											    <td colspan="2"><s:textfield name="teacher.email"></s:textfield></td>
											  </tr>
										  
									      
									    </table> </s:form></td>
	       
	      </tr>
	    </table>
					
					
					
				</div>
				<div region="south" border="false" style="text-align:center;height:30%;">
				
				
				   <a class="easyui-linkbutton" iconCls="icon-ok" href="#" id="submit">提交</a>
				
			    </div>
			</div>
	</div>
	
	<div id="add" icon="icon-add" class="easyui-window" title="添加" closed="true" style="padding:5px;width:500px;height:160px;align:center;">
			    <jsp:include flush="true" page="uploadImages.jsp"></jsp:include>
	</div>
</body>
</html>