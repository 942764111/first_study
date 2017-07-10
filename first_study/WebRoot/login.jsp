<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>

<title>混合式学习</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">-->
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
    <link href="css/mainxin.css" rel="stylesheet" type="text/css">
    
    <script type="text/javascript">
    function Rload(){
        var img = document.getElementById("safe");
        img.src = "page/user/image.jsp?" + Math.random(); 
    }
    $(function(){

    	var x = document.getElementById('userid'); //获取控件对象 
        x.onkeydown = txtsubmit;
         var y = document.getElementById('passw'); 
          y.onkeydown = txtsubmit;
          var z = document.getElementById('safecode'); 
          z.onkeydown = txtsubmit;
        
        $("#sub").click(function(){
    	var userid=$("#userid").val();
    	var passw=$("#passw").val();
    	var safecode=$("#safecode").val();
    	 $.ajax({
        	 	type:"POST",
    			url:"login.action",
    			data:{"userid":userid,"userPw":passw,"safecode":safecode},
    			success:function(json){
    				$("#safeError").empty();
        			var tip = json.tip1;
    				if(tip=="islogin"){
        				$("#PwError").empty();
        				$("#passw").empty();
        				$("#safecode").empty();
    					$("#nameError").empty().append("<font color='red'>此ID已登录</font>");
    					Rload();
    					}else if(tip=="safeerror"){
							$("#safeError").empty().append("<font color='red'>验证码错误</font>");
							$("#safecode").val("");
							
							Rload();
        					}else if(tip=="success"){
		    					$("#form").submit();
		    					}else if(tip=="PwError"){
		    						Rload();
		    						$("#nameError").empty();
		    						$("#safecode").val("");
		    						$("#PwError").empty().append("<font color='red'>密码错误</font>");
			    					}else if(tip=="nameError"){
			    						Rload();
			    						$("#PwError").empty();
			    						$("#nameError").empty().append("<font color='red'>ID错误</font>");
			    					}
		    	    	   }
    		 });
        });
    });
    
       function txtsubmit() {//该方法是为了让登陆是按回车键就可以实现提交的功能
           var e = event.srcElement;
           if (event.keyCode == 13) {
               document.getElementById('sub').click(); //提交按钮Button
                return false;          
           }
       }
   
    </script>
</head>
<body class="easyui-layout" onload="Rload()">
		<div region="north"  border="false" style="height:90px;overflow:hidden;">
              <div id="logo1"></div>
              <!--  <div id="top1"></div>-->
        </div>
 <div region="center"  border="false" style="overflow:hidden;"> <!-- 此处的id=login_bg为背景图片 -->
          <TABLE cellSpacing="0" cellPadding="0" height="100%" width="100%" 
      border="0">
        <TBODY>
        <TR>
          <TD width="48%" height="100%" align="right" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" >

            <tr>
              <td height="0" valign="middle" class="layout-panel" id="login_height"  style="padding:30px; line-height:130%" /><p align="center">简 介</p>
              <p>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="login_txt">混合式学习系统课件核心教学内容来源为河北北方学院信息科学与工程学院的教学课件、电子教案<br>
                和试题资料。课件在充分使用目前成熟的Java、Flex以及网络交互视频技术的基础之上，<br>
                对混合式学习的具体方式提出了大胆的探索和尝试。<br>
                本系统主要有以下功能:</span></p>
                <ol>
                  <li class="login_txt"><ol>
                  <li class="login_txt">学生主要功能： </li>
                  <li class="login_txt">学生注册功能 </li>
                  <li class="login_txt">学生的随堂学习功能 </li>
                  <li class="login_txt">学生继续学习功能 </li>
                  <li class="login_txt">学生自由练习功能 </li>
                  <li class="login_txt">课程考核测试功能</li>
                  <li class="login_txt">资源管理功能 </li>
                  <li class="login_txt">消息通讯管理功能 </li>
                </ol> </li>
                  <li class="login_txt"><ol>
                  <li class="login_txt">教师主要功能：. . .  </li>
                </ol> </li>
                  
                </ol>
                <p class="login_txt">在课件的制作过程当中，我们借鉴、参考和使用了网络上的许多电子资源，对于资源提供的各<br>
                位老师和相关人员的辛勤工作表示衷心感谢。<br>
                具体见课件内部“资源引用声明”！ </p>
            </tr>
          </table></TD>
          <TD width="2%" height="100%" valign="bottom">&nbsp;</TD>
          <TD width="50%" height="100%" valign="bottom"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                     <tr>
              <td width="7%" height="55">&nbsp;</td>
              <td height="188" align="left" valign="bottom"><SPAN 
                  class="login_txt_bt">管理员用户登录</SPAN></td>
              <td width="1%" height="55">&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td height="0" colspan="2">
              <s:form action="adminfunctions" id="form" name="form" method="post">
                        <TABLE id="table212" cellSpacing="0" 
                        cellPadding="0" width="100%" border="0">
                          <TBODY>
                          <TR>
                            <TD width="14%" height="30" class="top_hui_text"><SPAN 
                              class="login_txt">用户id：&nbsp;&nbsp; </SPAN></TD>
                            <TD height="30" class="top_hui_text"><s:textfield id="userid" name="edu.userId" required="true"></s:textfield></TD>
                            <TD height="30" class="top_hui_text"><span class="login_txt" id="nameError"></span>
										</TD>
                          </TR>
                          <TR>
                            <TD width="14%" height="30" class="top_hui_text"><SPAN
                              class="login_txt">密 &nbsp;&nbsp;  码：</SPAN></TD>
                            <TD height="30" class="top_hui_text"><s:password id="passw" name="edu.userPw"></s:password> <IMG 
                              height="18" src="img/login/luck.gif" width="19"> 
                              <SPAN class="Submit" style="MARGIN-TOP:0px"><A
								href="findPasswd.action">找回密码？</A></SPAN>
                            </TD>
                            <TD height="30" class="top_hui_text">
                            
                            <span class="login_txt" id="PwError"></span></TD>
                          </TR>
                          <tr>
                          	<td width="14%" height="30" class="top_hui_text"><span class="login_txt"></span>验证码：</td>
                          	<td><s:textfield name="safecode" theme="simple" size="4"  maxlength="4" id="safecode"></s:textfield>
                          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          		  <img src="" alt="点击刷新" id="safe" width="50" height="20" onclick="return Rload()">
                          		<!-- 让src=""否侧的话有些情况下，此action会自动进行两次 <img src="imagejsp.action" alt="看不清？点击刷新" id="safe" width="50" height="20" onclick="this.src=this.src+';'">-->
                          	</td>
                          	<TD height="30" class="top_hui_text"><span class="login_txt" id="safeError"></span></TD>
                          </tr>
                          <TR>
                            <TD height="30">&nbsp;</TD>
                            <TD width="45%" height="30">
                            <input id="sub" type="button" value="提交">
                           </TD>
                            <TD width="41%" height="30" class="top_hui_text"></TD></TR>
                          
                          </TBODY>
                    </TABLE>
                    </s:form></td>
              </tr>
            <tr>
              <td height="0" rowspan="2">&nbsp;</td>
              <td height="100" align="right" valign="bottom" style=" padding-top:30px;"><SPAN class="Submit" style="MARGIN-TOP: 40px">如果您本系统未注册用户，请点击<A
								href="register.action">用户注册</A>  。</SPAN>
                
                <SPAN id="ValrPassword" style="DISPLAY: none; COLOR: red"></SPAN>
                <SPAN id="ValrValidateCode" style="DISPLAY: none; COLOR: red"></SPAN>
                <DIV id="ValidationSummary1" style="DISPLAY: none; COLOR: red"></DIV></td>
              <td height="0" rowspan="2">&nbsp;</td>
            </tr>
           
           
          </table></TD></TR>
        
        </TBODY>
    </TABLE>

</div>
 <div region="south" split="false" border="false" style="height:30px;
	background:url(img/index/picture/2.jpg); 
	background-position:0 -75px; 
	font-size:12px; 
	padding-top:6px; 
	color:1a77b9; overflow:hidden;" align="center" >河北北方学院信息科学与工程学院</div>
</body>
</html>