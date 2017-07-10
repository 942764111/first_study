<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<TITLE>找回密码</TITLE>

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
var us=false;
function Rload(){
    var img = document.getElementById("safe");
    img.src = "page/user/image.jsp?" + Math.random(); 
}
function html(){
	
	var t=$("#safeAnswer").val();
	if(t.length==0){
		$('#hint').html("<span >请输入安全问题的答案 </span>");
		 us=false;
		}else if(t.length<2){
			$('#hint').html("<span >请输入2～20个字符 </span>");
			us=false;
			}else{
				$('#hint').html("<span ></span>");
				us=true;
				}
	
}
function finish(){
	html();
	if(us){
          $("#signupForm").submit();
		}
}

</SCRIPT>

</HEAD>
<body class="easyui-layout" onLoad="Rload();">
	<div region="north"  border="false" style="height:90px; overflow:hidden;">
              <div id="logo1"></div>
             
        </div>
	<div region="center" >
<TABLE cellSpacing="0" cellPadding="0" width="100%" align="center" border="0" height="100%" >
<tbody>
  <TR>
	 <TD width="100%" height="100%" valign="middle" >
	    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
	           
   				<tr>
					<td width="50%"  colspan="2" align="right">
					    <p class="login_txt_bt" align="center">密码显示区域</p>
					    <div align="center" style="padding:10px;border:1px solid;border-color:#A4BED4;">
					    <table width="70%" border="0" align="center" cellpadding="0" cellspacing="0" height="70%">
					       <tr>
					           <td height="100" class="login_txt" width="10%"></td>
					           <td height="100" class="login_txt" width="35%">
							                  请牢记您的密码：
						       </td>
						        <td height="100" class="login_txt">
						             <s:property value="edu1.userPw"></s:property>
						        </td></tr>
						     <tr>
						        <td height="100" class="login_txt" width="10%"></td>
						        <td height="100" class="login_txt" width="35%">
							                 
						       </td>
						        <td height="100" class="login_txt">
						            
						        </td></tr>
					    </table></div>
					</td>
					<td colspan="2" align="left">
					
				<table width="70%" border="0" align="center" cellpadding="0" cellspacing="0" class="login_txt">
				<s:form id="signupForm" action="passwdPFind">
				
			
					<tr>
						<td height="25" class="login_txt_bt">
							您的安全问题：
						</td>
						<td height="25" class="login_txt_bt">
						   <s:property value="edu.safeQuestion"></s:property>
						</td>
						<td height="25">
						   
						</td>
						
					</tr>
					<tr><td height="10"></td>
					</tr>
					<tr>
						<td height="25" width="30%" class="login_txt">
							您的安全答案：
						</td>
						<td height="25" width="40%" >
						  <s:textfield id="safeAnswer" label="您的安全答案" name="edu.safeAnswer" onkeyup="html();"></s:textfield>
						</td>
						<td height="25" width="40%" id="hint">
						  <s:fielderror cssStyle="color:red"><s:param>safeAnswer</s:param></s:fielderror>
						 
						</td>
						
					</tr>
					
					<tr><td height="10"></td>
					</tr>
					
					<TR>
                       <TD height="23" width="25%" class="login_txt">验证码：</TD>
                        
                        <TD height="25" width="40%" > <s:textfield  name="safecode" theme="simple" size="4"  maxlength="4"  id="safecode" />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <img src="" alt="点击刷新" id="safe" width="50" height="20" onclick="return Rload()">
                           <!--  <img src="imagejsp.action" id="safecode" alt="看不清？点击刷新" width="50" height="20" onClick="this.src=this.src" >-->
                           
                       </TD>
                       <TD height="23" width="40%"><s:fielderror cssStyle="color:red"><s:param>safecode</s:param></s:fielderror></TD>
                   </TR>
                  
					<tr>
						<td height="40" >
						</td>
						<td height="40" colspan="2" >
					    <input value="完成" type="button" onClick="finish();">
						
						</td>
						<td></td>
					</tr>
					
				</s:form>
				</table></td>
					
				</tr>
				
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			         <td height="62" align="right"  ></td>
			      </tr>
			      
			    </table>  
			    
			    
			</TD>	
	
     </TR> <!--这一行只能填一列，否侧于上下文不对齐  -->
   <TR>
    <TD  height="2" colspan="2"align="center" valign="middle" ></TD>
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
