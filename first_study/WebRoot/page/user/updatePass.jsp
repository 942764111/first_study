<%@ page language="java" import="java.util.*,xx.collection.bean.Teacher,java.text.SimpleDateFormat" pageEncoding="utf-8"%>            
<%@ taglib prefix="s" uri="/struts-tags"%>

<html style="height:100%;width:100%;">
<head>

<title>修改用户密码页面</title>
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
	  FONT-SIZE: 2px;
     }
   
	</style>
	
	<link href="css/login_style.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	
    
   <script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
    <script  type="text/javascript" src="js/jquery.validate.js"></script>
	
	
<script type="text/javascript" >
var validater;
var flag=false;
$(function(){

	$("#oldPass").blur(function(){
           var oldpass = $("#oldPass").val();
           var temp = "<%=session.getAttribute("pass")%>";
           if(oldpass!=temp){
               if(oldpass.length!=0){
            	     $("#tishi").html("<font size=2px color=red>密码不正确！</font>");
                   }else{$("#tishi").html("");}
                  
               }else{
            	   $("#tishi").html("");
            	   flag = true;
                   }
		});
	
	validater = $("#submit").validate({
		rules: {
		   "oldpassword":{
		       required:true,
		       minlength: 6,
			   maxlength: 20
		    },
		    "password":{
			       required:true,
			       minlength: 6,
				   maxlength: 20
			    }
			
		},	
		messages: {
			"oldpassword": {
				required: "请输入您的密码",
				minlength: "密码至少6个字符长",
				maxlength:"字符太长"
			},
			"password": {
				required: "请输入您的密码",
				minlength: "密码至少6个字符长",
				maxlength:"字符太长"
			} 
		 }	
	})
	
});
function sub(){
	var newPass = $("#newPass").val();
	if(validater.form()&&flag){
		jQuery.ajax({
			  type:"post",
		      url:"updatPass.action",
		      dataType:"json",
		      data:{"newPass":newPass},
		      cache:false,
            success:function (json){
		    	  $("#test").window('open');
					document.getElementById("ggggg").style.visibility="visible";//注意：用$()方式不好用	
					$("#new").html("<font color=red>"+json.newPass+"</font>");
            }
		   })//
			
	 
    }
}
</script>
</head>
<body style="height:100%;width:100%;overflow:hidden;border:none;">


	<div id="w" class="easyui-window" title="修改密码" iconCls="icon-save" style="width:978;height:475;padding:5px;background: #fafafa;" fit="true">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:0px;background:#fff;border:1px solid #ccc;">
				<br>
				<table width="60%" border="0" cellspacing="0" cellpadding="0"  height="80%" align="center" bgcolor="#EFEFEF">
      <tr>
       
        <td>
		        <fieldset>
		        <legend><font id="login_logo2"><img src="images/role/icon.gif"/>修改密码：</font></legend><s:form id="submit">
		        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="login_text">
		        	<tr>
		            <td width="29%" align="right"></td>
		            <td width="28%"></td>
		            <td width="43%">&nbsp;</td>
		          </tr>
		          <tr>
		            <td width="29%" align="right">旧密码：&nbsp;&nbsp;</td>
		            <td width="43%"><input type="text" id="oldPass" name="oldpassword"/><span id="tishi"></span></td>
		            <td width="28%">&nbsp;</td>
		          </tr>
		          <tr>
		            <td width="29%" align="right"></td>
		            <td width="28%"></td>
		            <td width="43%">&nbsp;</td>
		          </tr>
		          <tr >
		            <td width="29%" align="right">新密码：&nbsp;&nbsp;</td>
		            <td width="43%"><input type="text" id="newPass" name="password"/></td>
		            <td width="28%"></td>
		          </tr>
		          <tr>
		            <td width="29%" align="right"></td>
		            <td width="28%"></td>
		            <td width="43%">&nbsp;</td>
		          </tr>
		          <tr>
		            <td width="29%" align="right"><em class="high">*</em>&nbsp;&nbsp;</td>
		            <td width="28%"><font size =2px>密码6～20个字符,字母区分大小写,一个汉字占两个字符</font></td>
		            <td width="43%">&nbsp;</td>
		          </tr>
		        </table></s:form>
		       </fieldset>
        
        </td>
        
      </tr>
    </table>
		
			</div>
			<div region="south" border="false" style="text-align:center;height:30%;">
				
				
				<a class="easyui-linkbutton" iconCls="icon-ok" href="#" id="nextWindows" onclick="sub();">修改</a>
				
			</div>
		</div>
	</div>
	
	
	<div id="test" class="easyui-window" closed="true" modal="true" title="修改密码" style="width:978px;height:475px;" fit="true">
			
			<div class="easyui-layout" fit="true" style="visibility:hidden;" id="ggggg">
				<div region="center" border="false" style="padding:0px;background:#fff;border:1px solid #ccc;">
					<br>
					<table width="60%" border="0" cellspacing="0" cellpadding="0"  height="80%" align="center" bgcolor="#EFEFEF">
					      <tr>
					       
					        <td>
							        <fieldset>
							        <legend><font id="login_logo2"><img src="images/role/icon.gif"/>修改密码：</font></legend><s:form>
							        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="login_text">
							        	<tr>
							            <td width="29%" align="right"></td>
							            <td width="28%"></td>
							            <td width="43%">&nbsp;</td>
							          </tr>
							          <tr>
							            <td width="29%" align="right">密码修</td>
							            <td width="28%">改成功！</td>
							            <td width="43%">&nbsp;</td>
							          </tr>
							          <tr>
							            <td width="29%" align="right"></td>
							            <td width="28%"></td>
							            <td width="43%">&nbsp;</td>
							          </tr>
							          <tr >
							            <td align="right">新密码：&nbsp;&nbsp;</td>
							            <td id="new"></td>
							            <td></td>
							          </tr>
							          <tr>
							            <td width="29%" align="right"></td>
							            <td width="28%"></td>
							            <td width="43%">&nbsp;</td>
							          </tr>
							          
							        </table></s:form>
							       </fieldset>
					        
					        </td>
					        
					      </tr>
					    </table>
					
					
					
				</div>
				<div region="south" border="false" style="text-align:center;height:30%;">
			    </div>
			</div>
	</div>
	
</body>
</html>