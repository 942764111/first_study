<%@ page language="java" import="java.util.*,xx.collection.bean.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
   Userinfo user = (Userinfo)session.getAttribute("userifno");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<TITLE>用户注册</TITLE>
<!-- 作者:guoqiang -->
<script language="JavaScript"> 
<!-- 
self.moveTo(-4,-4); 
self.resizeTo(screen.availWidth+8,screen.availHeight+8);

//--> 
</script>
<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>


<script src="js/jquery.validate.js" type="text/javascript"></script>

<link href="css/login_style.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/register.css">

<SCRIPT type="text/javascript">


  	function reloadyemian()//最好不要用reload这个关键字,因为很容易和其它函数冲突 
	{ 
		
		var temp = "<%=user.getType()%>";
		if(temp=="T"){
		   window.location.href="registSuccessjsp"; 
		}else{
		   window.location.href="login1.jsp"; 
		}
		
	} 
	 window.setTimeout("reloadyemian();",5000); 


</SCRIPT>

</HEAD>
<body class="easyui-layout" onLoad="x();">
	<script language="JavaScript"> 
<!-- 
function x(){
	if(document.body.offsetHeight>document.body.scrollHeight){
	login_height.height=eval(login_height.height)+(document.body.offsetHeight-document.body.scrollHeight)-5;
	}
}
 //--> 
</script>
	<div region="north"  border="false" style="height:90px;overflow:hidden;">
              <div id="logo1"></div>
              
        </div>
	
	
			
	<div region="center" >
	
	          
<TABLE cellSpacing="0" cellPadding="0" width="100%" align="center" border="0" height="100%" >
<tbody>
 
 
  <TR>
	 <TD width="100%" height="100%" valign="top" >
	    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
	           
   				<tr>
					<td width="100%"  colspan="2" align="center">
					
				<table width="70%" border="0" align="center" cellpadding="0" cellspacing="0" >
				   <tr>
					<td height="81" colspan="3" id="login_logo" valign="bottom"  ><font color=red>恭喜页面</font></td>
   				</tr>
                <tr>
					<td width="260" height="10px" align="right"></td>
                    <td width="65" align="left"></td>
                    <td width="155" align="left"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;
						
					</td>
					<td colspan="2" align="left">&nbsp;
					  
					</td>
				</tr>
				<tr>
				  <td align="right"></td>
				  <td colspan="2" align="left">&nbsp;</td>
			  </tr>
				<tr>
				  <td height="104" colspan="3" align="right"><h2><font color="red">恭喜ID:${edu.userId}用户注册信息提交成功！</font></h2></td>
			  </tr>
			  <tr>
				  <td height="104" colspan="2" align="right"><h2>
				  <%if("T".equals(user.getType())){%>
				  
				    <font color="red">5 秒之后自动跳转主页面 </font>
				    
				  <% }else{%>
				  
				    <font color="red">5 秒之后自动跳转登录页面 </font>
				    
				  <% } %>
				  </h2></td>
			  </tr>
				</table></td>
					
				</tr>
				
	</table>
			
			</TD>	
	
     </TR> <!--这一行只能填一列，否侧于上下文不对齐  -->
   <TR>
    <TD  height="2" colspan="2"align="center" valign="middle"  ></TD>
     </TR>
  
  </tbody>
 </TABLE>
	</div>
	
<div region="south" split="false" border="false"   style="height:25px;
	background:url(img/index/picture/2.jpg); 
	background-position:0 -75px; 
	font-size:12px; 
	padding-top:6px; 
	color:1a77b9; overflow:hidden;" align="center" >河北北方学院信息科学与工程学院</div>
</body>
 </HTML>
