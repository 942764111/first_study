<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 
 * @Author: http://www.somao.net
 * @ O.W. ：http://www.somao123.com
 * @   TEL: (0371)5600 9696
 * @    QQ: 22568190
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>资源搜索。</title>
<link rel="stylesheet" type="text/css" href="/js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/js/ui/themes/icon.css">
	<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="/js/jquery.json-2.4.js"></script>
	<script type="text/javascript" src="/js/ui/jquery.easyui.min.js"></script>

<link href="../../images/home.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='/js/auto/jquery.autocomplete.js'></script>
	<link rel="stylesheet" type="text/css" href="/js/auto/jquery.autocomplete.css" />

 
	<script type="text/javascript">
		var queryword=["ddd","sssss"];         	
		$().ready(function() {

				$("#TCName").autocomplete("getCNames.action",{
					minChars: 1,
		            max: 20,
		            delay: 500,
		            //matchContains:true,
		            scrollHeight: 300,
		            scroll:true,
		            dataType:'json',//返回数据类型
		            extraParams:{   
		                radio2:function(){
						var radio1 = '1';
		                return radio1;//url的参数传递   
		                }   
		            },   
					parse: function(r) {
					
			            var cNamelList = [];
			            for(var i=0; i<r.cNamelList.length; i++){
			            cNamelList[cNamelList.length] = {
		                    data:r.cNamelList[i],
		                    value:r.cNamelList[i],
		                    result:r.cNamelList[i]
		                    };
		                }
			                return cNamelList;
			        },
		           formatItem:function(row, i, n) {
		           if(row==null){
		         $('#tips').html("*没有找到与此相关的课程");
		         $("#zhangId").empty();
	             $("#zhangId").append("<option value='全部'>全部</option>");
		           }else{
		            $('#tips').html("");
		            return "<font>"+row+"</font>";    
		           }
		           
		          } 
			}).result(function() {
				getZC();
			});
			
			function getZC(){
		
			var cname = {"TCName":$("#TCName").val()};//序列化表格内容为字符串  
			
			    $.ajax({  
			    type:'post',      
			    url:'getZCName',  
			    data:cname,  
			    cache:false,  
			    dataType:'json',  
			    success:function(data){ 
			   $("#zhangId").empty();
	           $("#zhangId").append("<option value='全部'>全部</option>");
	          
			   $.each(data.ZCList, function(i, item) {
			   var values=item.zbh+",,,,,"+item.CName;
	               $("#zhangId").append(
	                       "<option value='"+values+"'>"+item.CName
	                              + "</option>");
            
        });
			    }  
			}); 	
			}  
	})
	</script>
	<script type="text/javascript">
	
	
    
    function checkName(){
    var userid={"userid":$('#userid').val()}
    $('#tip2').html("");
    if($('#userid').val()!==""){
     $.ajax({  
			    type:'post',      
			    url:'RegUser.action',  
			    data:userid,  
			    cache:false,  
			    dataType:'json',  
			    success:function(data){ 

			  if(data.tip==false){
			  $('#tip2').html("*该用户不存在");
			  }else{
			  $('#tip2').html("");
			  }
			    }  
			}); 
			}   
    } 
	</script>
	<!-- 提交数据	
	<script type="text/javascript">
	$(function(){
	 $.fn.serializeObject = function(){  
            var o = {};  
            var a = this.serializeArray();  
            $.each(a, function(){  
                if (o[this.name]) {  
                    if (!o[this.name].push) {  
                        o[this.name] = [o[this.name]];  
                    }  
                    o[this.name].push(this.value || '');  
                }  
                else {  
                    o[this.name] = this.value || '';  
                }  
            });  
            return o;  
        };  
        
	$('#tijiao').click(function(){
	 var jsonuserinfo = $.toJSON($('#form').serializeObject());
	 alert(jsonuserinfo);
	 $.ajax({  
			    type:'post',      
			    url:'/SeniorSearchAction.html',  
			    data:jsonuserinfo,  
			    cache:false,  
			    dataType:'json',  
			    success:function(data){ 
			    }  
			}); 
	})
	})
	</script>
 -->
<script type="text/javascript">
		   function checkSubmit()
		   {
		      var content = document.getElementById("queryString");
		      if(""==content.value)
		      {
		         return false;
		      }
		      return true;
		   }
		</script>
</head>

<body>
<br/><br/><br/>
<div id="wrap">

<div>


 <form action="/SeniorSearchAction.html" id="form" method="post" style="margin-left:32%;color:#757575;font-family:微软雅黑">
 
 <div style="margin:10px 0% 10px;font-size:14px">关&nbsp;键&nbsp;字&nbsp;&nbsp;：&nbsp;&nbsp;
<label><input align="middle" name="queryString" class="p1" id="zsd"  value="" autocomplete="off" onfocus="javascript:if(this.value=='请输入知识点'){this.value='';this.style.color='#333';this.style.borderColor='dddddd';}" onblur="checkName();"/></label></div>

<div style="margin:20px 0% 10px;font-size:14px">搜索类型：&nbsp;&nbsp;
<label><input name="searchType" type="checkbox" value="全部" checked/>&nbsp;全部 </label>&nbsp;&nbsp;
<label><input name="searchType" type="checkbox" value="文本" />&nbsp;文本 </label>&nbsp;&nbsp;
<label><input name="searchType" type="checkbox" value="视频" />&nbsp;视频 </label>&nbsp;&nbsp;
<label><input name="searchType" type="checkbox" value="音频" />&nbsp;音频 </label>&nbsp;&nbsp;
<label><input name="searchType" type="checkbox" value="图像" />&nbsp;图像 </label>
 <div style="margin:20px 0% 10px;font-size:14px">课程名称：&nbsp;&nbsp;
<label><input align="middle" name="TCName" class="p1" id="TCName" autocomplete="off"  value="" onfocus="javascript:if(this.value=='请输入课程名称关键字'){this.value='';this.style.color='#333';this.style.borderColor='dddddd';}" onblur="javascript:if(this.value==''){this.value='请输入课程名称关键字';this.style.color='#CCC';this.style.borderColor='#CFC7C8';}"/></label>
<label id="tips" style="color:red;font-family:楷体">&nbsp;</label>
</div>

 <div style="margin:30px 0% 10px;font-size:14px">用户账号：&nbsp;&nbsp;
<label><input align="middle" name="userid" class="p1" id="userid"  value="" onfocus="javascript:if(this.value=='请输入上传者账号'){this.value='';this.style.color='#333';this.style.borderColor='dddddd';}" onblur="checkName();"/></label>
<label id="tip2" style="color:red;font-family:楷体">&nbsp;</label>
</div>
 
 
  <div style="margin:40px 0% 10px;font-size:15px">
<label><input id="btn" style=" width:113px;width:94px\9\0; height:33px;line-height:33px; border:none; background-color:#7c9eb2; cursor:pointer;margin-left:20px; display:inline-block; color:#FFF; text-align:center; font-size:15px;-webkit-appearance: none;" align="middle" value="重  置" type="button" /></label>
<label><input id="tijiao" style=" width:113px;width:94px\9\0; height:33px;line-height:33px; border:none; background-color:#39a6df; cursor:pointer;margin-left:80px; display:inline-block; color:#FFF; text-align:center; font-size:15px;-webkit-appearance: none;" align="middle" value="搜索一下" type="submit" /></label>
</div>
   </form>
</div>
</body>
</html>
