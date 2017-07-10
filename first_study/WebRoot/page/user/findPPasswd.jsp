<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<TITLE>用户通行验证</TITLE>

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
var us;
/*此方法实现json通信，校验userid是否可用。*/
$(function(){
	$("#userid").val("");
	
	 $("#userid").blur(function(){
	      var t=null;
		  var userid=$("#userid").val();
		  if(userid.length>2){t=userid;}
		
        $.ajax({
	         	  type: "post",
			      url:"RegUser.action",
			      dataType:"json",
			      data:{userid:t}, 
			      cache:false,
	              success:function (json){
	                if (json.tip) {
						$('#userid-hint').html("<span >  </span>");
						//us=false;
						us=true;
						}else{
							$('#userid-hint').html("<span >此ID不存在 </span>");
							//us=true;
							us=false;
						}	   
	                 				                      
	               }
         	});
        
    })
    
})
/*此方法是实现了jquery的异步校验*/
$().ready(function(){
   // validate signup form on keyup and submit
	$("#signupForm1").validate({
		rules: {
		    "edu.userId":{
		        required:true,
		        minlength:3
		    }
			
		},	
		messages: {
			
			"edu.userId":{
		        required:"登录 ID 不能为空",
		        minlength:"长度应大于  2"
		    }
		 }	
	})
   

})

function finish(){
    if(us){
    return true;
    }else{
    return false;
    }
}
function Rload(){
        var img = document.getElementById("safe");
        img.src = "page/user/image.jsp?" + Math.random(); 
    }

</SCRIPT>

</HEAD>
<body class="easyui-layout" onLoad="Rload();">
	<div region="north"  border="false" style="height:90px;overflow:hidden;">
              <div id="logo1"></div>
             
        </div>
	<div region="center" >
	
	          
<TABLE cellSpacing="0" cellPadding="0" width="100%" align="center" border="0" height="100%" >
<tbody>
 
 
  <TR>
	 <TD width="100%" height="100%" valign="middle" >
	    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
	           
   				<tr>
   				    <td width="50%"  colspan="2" align="right" height="125"></td>
					<td align="left" height="125">
					
				<table width="70%" border="0" align="center" cellpadding="0" cellspacing="0" class="login_txt">
				<s:form action="passwdFind" id="signupForm1" >
				
			         <tr>
					    <td height="30" class="login_txt_bt">
					                    用户通行验证
					    </td>
					    <td height="30">
					    </td>
					   <td height="30"></td>
					</tr>
					<tr>
					    <td height="30" width="25%" class="login_txt">
					        用&nbsp;&nbsp;户&nbsp;&nbsp;ID：
					    </td>
					    <td height="30" width="40%" >
					        <s:textfield id="userid" name="userid" ></s:textfield>
					        
					    </td>
					   <td width="40%" height="30"><span id="userid-hint" class="">&nbsp;</span></td>
					</tr>
					
					<tr><td height="5"></td>
					</tr>
					
					<TR>
                       <TD height="30" width="25%" class="login_txt">验证码：</TD>
                        
                            <TD height="30" width="40%"> <s:textfield  name="safecode" theme="simple" size="4"  maxlength="4"  id="safecode" />
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <img src="" alt="点击刷新" id="safe" width="50" height="20" onclick="return Rload()">
                               <!-- <img src="imagejsp.action" id="safecode" alt="看不清？点击刷新" width="50" height="20" onClick="this.src=this.src" > -->
                               
                           </TD>
                      <td height="30" width="40%"><s:fielderror cssStyle="color:red"><s:param>safecode</s:param></s:fielderror></td>
                   </TR>
                   
				
					<tr>
						<td height="30">
						</td>
						<td height="30">
					    <s:submit value="完成" onclick="return finish();"></s:submit>
					    </td>
					    <td height="30"></td>
					</tr>
					
				</s:form>
				</table></td>
					
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
