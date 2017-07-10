<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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
<style type="text/css">
form.cmxform label.error, label.error {
	/* remove the next line when you have trouble in IE6 with labels in list */
	color: red;
	
}
</style>
<SCRIPT type="text/javascript">
function Rload(){
    var img = document.getElementById("safe");
    img.src = "page/user/image.jsp?" + Math.random(); 
}
var us=false;//检验uid的标志
var flag=false;//检验教师注册编号是否有效

/*此方法实现json通信，校验userid是否可用。*/
function checkID(){
	$('#userid-hint').html("<span ><img src=\"images/tree_loading.gif\"></img></span>");
	// $("#userid").blur(function(){
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
						$('#userid-hint').html("<span ><font color=red>此ID不可用</font></span>");
						us=false;
						}else{
							$('#userid-hint').html("<span>此ID已通过检测</span>");
							us=true;
						}	   
	                 				                      
	               }
         	})//;
        
    //})
    
}
/*此方法是实现了jquery的异步校验*/
$().ready(function(){
   // validate signup form on keyup and submit
	$("#signupForm").validate({
		rules: {
		    "edu.userId":{
		        required:true,
		        minlength:3,
		        maxlength: 10
		    },
			"edu.userPw": {
				required: true,
				minlength: 6,
				maxlength: 20
			},
			"confirm_password": {
				required: true,
				minlength: 6,
				equalTo: "#pass1"
			},
			"edu.safeQuestion":{
			   required:true,
			   minlength: 6,
			   maxlength: 20
			},
		    "edu.safeAnswer":{
		       required:true,
		       minlength: 6,
			   maxlength: 20
			   
		    }
			
		},	
		messages: {
			
			"edu.userId":{
		        required:"登录 ID 不能为空",
		        minlength:"长度应大于  2",
		        maxlength:"2～10个字符"
		    },
			
			"edu.userPw": {
				required: "请输入您的密码",
				minlength: "密码至少6个字符长",
				maxlength:"6～20个字符"
			},
			"confirm_password": {
				required: "请输入您的密码",
				minlength: "密码至少6个字符长",
				equalTo: "请输入与上面相同的密码"
			} ,
			"edu.safeQuestion":{
			    required:"请输入你的安全问题",
			    minlength:"6～20个字符,字母区分大小写,一个汉字占两个字符",
			    maxlength:"6～20个字符,字母区分大小写,一个汉字占两个字符"
			},
			"edu.safeAnswer":{
			    required:"请输入你的安全问题的答案" ,
			    minlength:"6～20个字符,字母区分大小写,一个汉字占两个字符",
			    maxlength:"6～20个字符,字母区分大小写,一个汉字占两个字符"
			}
		 }	
	})
   

});
$().ready(function(){
	$("#safeQuestion").change(function(){
		   var value=$("#safeQuestion").val();
		   if(value=="zdy"){
			   
                 var name=$("#edusafeQuestion").attr("name");
                
                 if(name==undefined){
                	 $("#anquan").after("<tr id=\"zdyTs1\"><td height=\"10\"></td></tr>"+
                             "<tr id=\"zdyText\"><td height=\"25\" align=\"right\" width=\"50%\">自定义安全问题：</td>"+
                                 "<td height=\"25\" align=\"left\" width=\"50%\"><input id=\"edusafeQuestion\" name=\"edu.safeQuestion\"></input></td></tr>"+
                             "<tr id=\"zdyTs2\"><td height=\"10\" align=\"right\" width=\"50%\"></td>"+
     					        "<td height=\"5\" align=\"left\"><span id=\"note_txt\">6～20个字符（字母、数字、符号）,字母区分大小写,一个汉字占两个字符</span></td>"+
     					     "</tr>");
                     }
                 
			   }else{
				    
				     var name1=$("#edusafeQuestion").attr("name");
	                
	                 if(name1=="edu.safeQuestion"){
	                	   $("#zdyText").html("");
	                	   $("#zdyTs1").html("");
	                	   $("#zdyTs2").html("");
                          
		                 }
				   }
		})
		
});
function isUserAgree(){//onclick是否同意协议条款
   var isAgree = document.getElementsByName('servItems')[0].checked;
   if(isAgree){
      $("#isServItems1").html("");
   }else{
          $("#isServItems").after("<tr id=\"isServItems1\">"+
						"<td height=\"40\" align=\"right\" valign=\"middle\">"+
						"<font color=red>您未接受\"服务条款\"</font>"+
						"</td>"+
						"<td height=\"40\" colspan=\"2\" align=\"left\" valign=\"middle\">"+
					              "<font color=red>和\"隐私权保护和个人信息利用政策\"，不能注册账户！</font>"+
						"</td>"+
					"</tr>");
   }
   
}
function finish(){
		var isAgree = document.getElementsByName('servItems')[0].checked;
		if(isAgree){
		   var h=document.getElementsByName('edu.type')[1].checked;
			if(h){
			   //blurs();
			   if(us&&flag){
			    return true;
			    flag =false;
			    }else{
			    return false;
			    }
			}else{
			   if(us){
			    return true;
			    }else{
			    return false;
			    }
			}
		}else{
		  return false;
		}	
   
}
function clicktype(obj){
       if(obj.value=="T"){
       $("#edutype").after("<tr id=\"edutype1\"><td height=\"10\" width=\"50%\" align=\"right\"> </td>"+
                         "<td height=\"10\" align=\"left\"><span id=\"note_txt\">注册教师用户必须有教师注册编号就是“教师编号”</span></td></tr>"+
					
					"<tr id=\"edutype2\"><td height=\"25\" align=\"right\" width=\"50%\" >教师注册编号："+
						"</td><td height=\"25\" align=\"left\">"+
						"<input type=\"text\" id=\"teajoinNo\"label=\"教师注册编号\" name=\"teajoinNo\"></input><span id=\"idedutype\"></span>"+
						"</td>"+
					"</tr>");
		$("#teajoinNo").blur(function (){
		   blurs();
		   blurs2();
		});	
		
					
       }else{
         $("#edutype1").html("");
         $("#edutype2").html("");
       }
}
function clickreset(){
         $("#edutype1").html("");
         $("#edutype2").html("");
         
         $("#isServItems1").html("");
}
function blurs(){
      var temp = $("#teajoinNo").val();
		    if(temp.length==0){
		       $('#idedutype').html("<font color=red>请输入教师注册编号</font>");
		       flag =false;
		    }else{
		       $('#idedutype').html("");
		    } 
}
function blurs2(){
         var temp = $("#teajoinNo").val();
         if(temp.length!=0){
           $.ajax({
         	  type: "post",
		      url:"RegTea.action",
		      dataType:"json",
		      data:{jsbh:temp,type:"1"},
		      cache:false,
              success:function (json){
                if (json.tip) {//true表示教师注册编号不可用，有两种可能一是已经注册过；二是该注册编号不存在
                        $('#idedutype').html("<font color=red>教师注册编号不可用</font>");
						flag = false;
					}else{
						$('#idedutype').html("");
					    flag = true;
					}	   
                 				                      
               }
           })//;
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
	 <TD width="100%" height="100%" valign="top" >
	    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
	           
   				<tr>
					<td width="100%"  colspan="2" align="center">
					<s:form action="newUser" id="signupForm" class="cmxform">
				<table width="70%" border="0" align="center" cellpadding="0" cellspacing="0" class="login_txt">
				
				<s:hidden name="edu.roles.roleid" value="220"></s:hidden>
			
				    <tr>
		                <td height="71" align="center" width="50%"><span id="login_logo">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;    &nbsp;&nbsp;     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  创建您的帐号
		                </span></td>
						<td height="71"></td>
					
   				    </tr>  
   				    <tr><td height="1" align="right" width="50%" id="sanjiao1">
   				           <hr/>&nbsp;
   				        </td><td height="1" align="left" width="50%"><hr/>&nbsp;</td>
					</tr>
   				   
				
					<tr>
					    <td height="30" align="right" width="50%">
					        登&nbsp;&nbsp;陆&nbsp;&nbsp;ID：
					    </td>
					    <td height="30" align="left">
					        <s:textfield id="userid" name="edu.userId" ></s:textfield>
					        <span id="userid-hint" class="">&nbsp;</span>
					    </td>
					   
					</tr>
					<tr><td height="10" align="right" width="50%"></td>
					    <td height="10" align="left"><input type="button" value="检测" onclick="checkID();"></input><span id="note_txt">2～10个字符（字母、数字、符号）,区分大小写</span></td>
					</tr>
					<tr><td height="10"></td> 
					</tr>
					<tr>
						<td height="25" align="right" width="50%">
							密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：
						</td>
						<td height="25" colspan="2" align="left">
						  <s:password id="pass1" label="密码" name="edu.userPw"></s:password>
						</td>
					</tr>
					<tr><td height="10" align="right" width="50%"></td>
					    <td height="10" align="left"><span id="note_txt">6～20个字符（字母、数字、符号）,区分大小写</span></td>
					</tr>
					<tr>
						<td height="25" align="right" width="50%">
							重复密码：
						</td>
						<td height="25"  align="left">
						  <s:password id="pass2" label="密码" name="confirm_password"></s:password>
						</td>
						
					</tr> 
					<tr><td height="10" align="right" width="50%"></td>
					    <td height="10" align="left"><span id="note_txt">请输入与上面相同的密码</span></td>
					</tr>
					 <tr>
		                <td height="71" align="center" width="50%"><span id="login_logo">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;    &nbsp;    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  安全信息设置
		                </span></td>
						<td height="71" ></td>
					
   				    </tr>
   				    
   				     <tr><td height="1" align="right" width="50%" id="sanjiao1">
   				           <hr/>&nbsp;
   				        </td><td height="1" align="left" width="50%"><hr/>&nbsp;</td>
					</tr>
   				    <tr><td height="10" align="right" width="50%"><span id="note_txt_1">以下信息对保护您的</span></td>
   				        <td height="10" align="left"><span id="note_txt_1">帐号安全极为重要，请慎重填写并牢记</span></td>
					</tr>
					<tr><td height="10" ></td>
   				        
					</tr>
					<tr id="anquan">
						<td height="25" align="right" width="50%">
							安全问题：
						</td>
						<td height="25" align="left">
						  <select id="safeQuestion" label="安全问题" name="safeQuestion">
						      
						      <option value="我手机号码的后6位？">我手机号码的后6位？</option>
						      <option value="我母亲的生日？">我母亲的生日？</option>
						      <option value="我父亲的生日？">我父亲的生日？</option>
						      <option value="我小学校名全称？">我小学校名全称？</option>
						      <option value="我中学校名全称？">我中学校名全称？</option>
						      <option value="我最喜爱的电影？">我最喜爱的电影？</option>
						      <option value="我最喜爱的歌曲？">我最喜爱的歌曲？</option>
						      <option value="我最喜爱的食物？">我最喜爱的食物？</option>
						      <option value="我最大的爱好？">我最大的爱好？</option>
						      <option value="zdy">自定义</option>
						  </select><span id="hint" class="">&nbsp;</span>
						  
						</td>
						
					</tr>
					<tr><td height="10"></td>
					</tr>
					<tr>
						<td height="25" align="right" width="50%">
							安全答案：
						</td>
						<td height="25" align="left">
						  <s:textfield id="safeAnswer" label="安全答案" name="edu.safeAnswer"></s:textfield>
						</td>
						
					</tr>
					<tr><td height="10" align="right" width="50%"></td>
					    <td height="10" align="left"><span id="note_txt">6～20个字符（字母、数字、符号）,字母区分大小写,一个汉字占两个字符</span></td>
					</tr>
					<tr id="edutype">
						<td height="25" align="right" width="50%">
							身&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份：
						</td>
						<td height="25" colspan="2" align="left">
							
						  <s:radio onclick="clicktype(this)" id="edu_type" name="edu.type" list="#{'S':'学生','T':'教师'}" value="'S'"></s:radio>
						</td>
					</tr>
					
				    <tr><td height="10"></td>
					</tr>
					<TR>
                       <TD width="50%" align="right" height="23">验证码：</TD>
                        
                            <TD height="25" align="left"> <s:textfield  name="safecode" theme="simple" size="4"  maxlength="4"  id="safecode" />
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <img src="" alt="点击刷新" id="safe" width="50" height="20" onclick="return Rload()">
                               <!--  <img src="imagejsp.action" id="safecode" alt="看不清？点击刷新" width="50" height="20" onClick="this.src=this.src" >-->
                               
                           </TD>
                      
                   </TR>
                   <tr><td height="5"></td>
					</tr>
                    <TR><td height="10" align="right" width="50%"></td>
						<TD height="10"><s:fielderror cssStyle="color:red"><s:param>safecode</s:param></s:fielderror> </TD></TR>
					  <tr>
		                <td height="71" align="center" width="50%"><span id="login_logo">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;    &nbsp;&nbsp;     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  服务条款
		                </span> </td>
						<td height="71"></td>
					
   				    </tr>
					 <tr><td height="1" align="right" width="50%" id="sanjiao1">
   				           <hr/>&nbsp;
   				        </td><td height="1" align="left" width="50%"><hr/>&nbsp;</td>
					</tr>
					<tr id="isServItems">
						<td height="40" align="right" valign="middle">
						<label>
                        <input type="checkbox" name="servItems" id="servItems" checked="true" onclick="isUserAgree();"/>
                                                  我已阅读并接受</label>&ldquo;
						</td>
						<td height="40" colspan="2" align="left" valign="middle">
					    
                      <a href="statement.jsp" target="_blank">服务条款</a>&rdquo;和&ldquo;
                      <a href="privacy_right.jsp"  target="_blank">隐私权保护和个人信息利用政策</a>&rdquo;
						</td>
					</tr>
					
					
					<tr>
						<td height="40" align="right" valign="middle">
						</td>
						<td height="40" colspan="2" align="left" valign="middle">
					    <s:submit value="创建账号" onclick="return finish();"></s:submit>
							<s:reset value="重写" onclick="clickreset();"></s:reset>
						</td>
					</tr>
					
				
				</table></s:form></td>
					
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
