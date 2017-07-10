<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userid = (String)request.getSession().getAttribute("uid");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'jxnr.jsp' starting page</title>
    <base href="<%=basePath%>">
    <style type="text/css">
	  .high{
	    color:#F0000F;
	  }
	  form.cmxform label.error, label.error {
	  /* remove the next line when you have trouble in IE6 with labels in list */
	  color: green;
	
     }
     
     table{
		border:1px;
		border-color:black;
		width:100%;
	}
     
   </style>
   
    <link href="uploadify/uploadify.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script  type="text/javascript" src="js/jquery.validate.js"></script>
	
	<script type="text/javascript" src="uploadify/swfobject.js"></script>
	<script type="text/javascript" src="uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script type="text/javascript" src="js/sjld.js"></script>
	<script type="text/javascript" src="js/jxnr.js"></script>
	
	<script type='text/javascript' src='js/auto/jquery.bgiframe.js'></script>
	<script type='text/javascript' src='js/auto/jquery.ajaxQueue.js'></script>
	<script type='text/javascript' src='js/auto/thickbox-compressed.js'></script>
	<script type='text/javascript' src='js/auto/jquery.autocomplete.js'></script>
	<script type='text/javascript' src='js/auto/localdata.js'></script>
	<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
	  <link rel="stylesheet" type="text/css" href="js/auto/thickbox.css" />

	  
	  
	  
	<script type="text/javascript">
	$(function(){
		<%
	    String CName=(String)request.getAttribute("zmc");
	    %>
	    var CName ='<%=CName%>';
	    
		$.ajax({
            type:"POST",
           	url:"jyts1.action",
            data:{"CName":CName},
           success:function(json){
            	if(json.total1>=50){
                	$("#ts5").empty().append("<font color='red' size='2px'>题数不能大于50条</font>");
                    }else{
                    	$("#ts5").empty().append("<font color='red' size='2px'>题数不能大于"+json.total1+"条</font>");
                        }
	            if(json.total2>=50){
	            	$("#ts6").empty().append("<font color='red' size='2px'>题数不能大于50条</font>");
		            }else{
		            	$("#ts6").empty().append("<font color='red' size='2px'>题数不能大于"+json.total2+"条</font>");
		            }	
	            if(json.total3>=50){
	            	$("#ts7").empty().append("<font color='red' size='2px'>题数不能大于50条</font>");
		            }else{
		            	$("#ts7").empty().append("<font color='red' size='2px'>题数不能大于"+json.total3+"条</font>");
			            }
	            if(json.total4>=50){
	            	$("#ts8").empty().append("<font color='red' size='2px'>题数不能大于50条</font>");
		            }else{
		            	$("#ts8").empty().append("<font color='red' size='2px'>题数不能大于"+json.total4+"条</font>");
			            }
	            	
		   }
  		});
		//对出题个数的校验
		 var validator=$("#formm").validate({
			
			rules: {
			    "xz":{
	                number:true,
			        required:true,
			        min:0,
			        digits:true,
			        max:50,
			        maxlength:10
			    },
			    
			    "dxz": {
					required: true,
					number:true,
					min:0,
			        digits:true,
			        max:50,
					maxlength:10
					
				},
				"cz": {
					required: true,
					number:true,
					min:0,
			        digits:true,
			        max:50,
					maxlength:10
				},
				"pd": {
					required: true,
					number:true,
					min:0,
			        digits:true,
			        max:50,
					maxlength:10
				}
				
			},	
			messages: {
				
				"xz":{
			        required: " (空)",
					number:" (数字)",
					min:"(最小0)",
			        digits:"(整数)",
			        max:"(最大50)",
					maxlength:"(最多10位)"
			    },
				
				"dxz": {
					required: " (空)",
					number:" (数字)",
					min:"(最小0)",
			        digits:"(整数)",
			        max:"(最大50)",
					maxlength:"(最多10位)"
				} ,
				"cz": {
					required: " (空)",
					number:" (数字)",
					min:"(最小0)",
			        digits:"(整数)",
			        max:"(最大50)",
					maxlength:"(最多10位)"
				} ,
				"pd":{
					required: " (空)",
					number:" (数字)",
					min:"(最小0)",
			        digits:"(整数)",
			        max:"(最大50)",
					maxlength:"(最多10位)"
				   
				}
			 }	
		});

  		$('#submitsj').click(function(){
  	  		if(validator.form()){
	  	  		      var input = {"xz":$('#xz').val(),"dxz":$('#dxz').val(),"pd":$('#pd').val(),"cz":$('#cz').val(),"CName":CName};
	    		      $.ajax({
	    				type:"POST",
	    				url:"zjgz.action",
	    				data:input,
	    				success:function(json){
	    					if(json.tip!="xz"){
	    						if(json.tip!="dxz"){
	    							if(json.tip!="pd"){
	    								if(json.tip!="cz"){
	    									window.location.href="xztnr.action";
	    									 $('#input3').window('close');
	    									  $('#test_t').datagrid('reload');
	    									}else{$.messager.alert('提示信息','所选操作题数大于已有题数！！！','info');}
	    								}else{$.messager.alert('提示信息','所选判断题数大于已有题数！！！','info');}
	    							}else{$.messager.alert('提示信息','所选多选题数大于已有题数！！！','info');}
	    						}else{$.messager.alert('提示信息','所选单选题数大于已有题数！！！','info');}
	    					}
	    				});
  	  	  		}
  			
  	     });
  	     
	});
	 function quxiaosj(){
		 $('#scxsj').window('close');
		 $('#xz').val("");$('#dxz').val("");$('#pd').val("");$('#cz').val("");
     } 
     
	</script>
	<script type="text/javascript">
	//限制字数
	var TextUtil = new Object();
	TextUtil.NotMax = function(oTextArea){
		var maxText = oTextArea.getAttribute("maxlength"); 
		if(oTextArea.value.length > maxText){
				oTextArea.value = oTextArea.value.substring(0,maxText);
				$.messager.alert('提示信息','您只能输入200个字','info');  
		}
	}
	$(document).ready(function() {
		$('#fileupload').uploadify({
		    'uploader'  : 'uploadify/uploadify.swf',
		    'script'    : 'upload_file.action?jsessionid=<%=userid%>',
		    'cancelImg' : 'uploadify/cancel.png',
		    'auto'      : true,
		    'multi'          : false, 
		   	// 'buttonText'     : 'brower', 
		    //'simUploadLimit' : 3,
		    'sizeLimit'      : 102400000, 
		    //'queueSizeLimit' : 2,
		    'queueID'        : 'fileQueue',
		    'fileDesc'       : '*.flv; *.swf;*.pdf',
            'fileExt'        : '*.flv; *.swf;*.pdf',
            'fileDataName'    : 'fileupload',
            'removeCompleted'	:	false,
            'wmode'	:	'transparent',
            'width' : 90,
            'buttonImg' :'uploadify/_upload.png',
            onComplete: function (event, queueID, fileObj, response, data) {
				//$('<li></li>').appendTo('.files').text(response); 
				var r = jQuery.parseJSON(response);
				$('#lxm').val(r.lxm);
				$('#changdu').val(r.changdu);
				$('#scrq').val(r.scrq);
				$('#fileName').val(r.fileName);
				$('#filePath').val(r.filePath);
				$('#no').val(r.no);
            	jQuery("#" + jQuery(this).attr('id') + ID).find('.percentage').text(' - 完成');         
            	
			},
			onProgress : function(event, queueId, fileObj, response, data) {

			},
            onError: function(event, queueID, fileObj) {
				$('#fileupload').uploadifyClearQueue();   
				$.messager.alert('提示信息','您上传的文件太大','info');
			},   
			onCancel: function(event, queueID, fileObj){ 
				$.messager.alert('提示信息','您取消' +fileObj.name + '的上传!','info');  
				alert("取消了" + fileObj.name);   
			}
			});
	});
    </script>
    <script type="text/javascript" language="javascript">   
function iFrameHeight() {   
var ifm= document.getElementById("iframepage");   
var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;   
if(ifm != null && subWeb != null) {
   ifm.height = subWeb.body.scrollHeight;
   ifm.width = subWeb.body.scrollWidth;
}   
}   
</script>
  </head>
  
  <body style="margin-left: 0px;margin-top: 0px;margin-bottom: 0px;margin-right: 0px;margin: 0px;border-bottom: 0px;">
   <div id="zxsp" class="easyui-window" closed="true" title="在线资源" icon="icon-edit" maximizable="true"  minimizable="true" style="padding:0px;width:800px;height:450px;overflow-x:hidden;overflow-y:hidden;">
	      <iframe name="CFrame" id="CFrame" frameborder="0" style="padding:0px;width:100%;height:100%;"></iframe>
		</div>	
		<div id="scxsj" class="easyui-window" title="生成新试卷" style="padding: 5px; width: 480; height: 280;font-size: 2px;" iconCls="icon-edit"
			closed="true" maximizable="false" minimizable="false" collapsible="false">
			<div style="visibility:hidden;" id="ggggg4">
				<form id="formm"><table align="center" width="410px" style="font-size: 13px;">
					<tr>
						<td>
							单选题题数:<input id="xz" name="xz" type="text"/>
						</td>
						<td id="ts5"></td>
					</tr>
					<tr>
						<td>
							多选题题数:<input id="dxz" name="dxz" type="text" />
						</td>
						<td id="ts6"></td>
					</tr>
					<tr>
						<td>
							判断题题数:<input id="pd" name="pd" type="text" >
						</td>
						<td id="ts7"></td>
						
					</tr>
					<tr>
						<td>
							操作题题数:<input id="cz" name="cz" type="text">
						</td>
						<td id="ts8"></td>
					</tr>
					<tr><td height="15px"></td><td></td></tr>
					<tr>
						<td colspan="2" align="right">
							
							<a id="submitsj" class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" >生成</a>
							<a class="easyui-linkbutton" iconCls="icon-cancel"
								href="javascript:void(0)" onclick="quxiaosj()">取消</a>
						</td>
					</tr>
				</table></form>
				</div>
		</div>
    <table id="test"></table>
	<div id="add" icon="" class="" title="." closed="true" 
	    		maximizable="false" minimizable="false" collapsible="false"
	    		style="padding:3px;width:700px;height:395px;visibility:hidden;">
	    		<center>
		     <iframe src="page/xuexi/upload.html" id="iframepage" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" onLoad="iFrameHeight()">
		   </center>
		    </div>
		   <div id="bdAdd" class="easyui-window" title="从已有资源添加" style="padding: 5px; width: 700; height: 100;font-size: 2px;" iconCls="icon-edit"
			closed="true" maximizable="false" minimizable="false" collapsible="false">
			<div style="visibility:hidden;" id="ggggg2">
				<table align="center" style="font-size: 13px;">
					<tr>
						<td>选择查询根据:
						</td>
						<td>
							<select name="select" id="type">
	                            <option value="zlmc">资料名称</option>
	                            <option value="zlms">资料描述</option>
	                        </select>
						</td>
						<td>输入关键字：
						</td>
                        <td>
                        	<input type="text" id="name"  required="true"/>
						</td>
						<td>文件类型：
						</td>
						<td>
							<select name="select" id="wjlx">
	                            <option value="图像">图像</option>
	                            <option value="文本">文本</option>
	                            <option value="视频">视频</option>
	                            <option value="音频">音频</option>
	                        </select>
						</td>
						<td colspan="3" align="center">
							<a id="tijiao" class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)">查询</a>
						</td>
					</tr>
				</table>
				</div>
		</div>
		
		<div id="bdAdd1" class="easyui-window" title="从已有资源添加" style="padding: 5px; width: 658; height: 362;font-size: 2px;" iconCls="icon-edit"
			closed="true" maximizable="false" minimizable="false" collapsible="false">
			<table id="test2"></table>
		</div>
		
		<div id="AddZsd1" class="easyui-window" title="添加对应知识点" style="padding: 5px; width: 480; height: 300;font-size: 2px;" iconCls="icon-edit"
			closed="true" maximizable="false" minimizable="false" collapsible="false">
			<div style="visibility:hidden;" id="ggggg3">
				<table align="center" width="410px" style="font-size: 13px;">
					<tr style="visibility:hidden;">
						<td>
							资料编号:
						</td>
						<td>
							<input id="zlbh1" type="text" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td>
							资料名称:
						</td>
						<td>
							<input id="zlmc1" type="text" readonly="readonly" style="background-color:#EBEBE4;"/>
						</td>
					</tr>
					<tr>
						<td>
							起始页/时间:
						</td>
						<td>
							<input id="wz1" type="text"/>
						</td>
					</tr>
					<tr>
						<td>
							课程名称:
						</td>
						<td>
							<input id="kmc1" type="text" readonly="readonly" style="background-color:#EBEBE4;">
						</td>
					</tr>
					<tr>
						<td>
							章名称:
						</td>
						<td>
							<input id="zmc1" type="text" readonly="readonly" style="background-color:#EBEBE4;">
						</td>
					</tr>
					<tr>
						<td>
							知识点:
						</td>
						<td>
							<input id="website" type="text" onblur="getKname()"/>
						</td>
						
					</tr>
					<tr>
						<td>
							所属节名称:
						</td>
						<td>
							<input id="jmc1" type="text" readonly="readonly" style="background-color:#EBEBE4;">
						</td>
						
					</tr>
					<tr>
						<td colspan="3" align="right">
							<a id="jixu1" class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" onclick="continueAdd()">添加下一个</a>
							<a id="tianjia1" class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" onclick="over()">完成</a>
							<a class="easyui-linkbutton" iconCls="icon-cancel"
								href="javascript:void(0)" onclick="quxiao()">取消</a>
						</td>
					</tr>
				</table>
				</div>
		</div>
		<div id="more" class="easyui-window" title="添加对应知识点" style="padding: 5px; width: 435; height: 260;font-size: 2px;" iconCls="icon-edit"
			closed="true" maximizable="false" minimizable="false" collapsible="false">
			<table id="test1"></table>
		</div>
		
		
		
  </body>
</html>
