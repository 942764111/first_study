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
    <link href="uploadify/uploadify.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="uploadify/swfobject.js"></script>
	<script type="text/javascript" src="uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script type="text/javascript" src="js/sjld.js"></script>
	<script type="text/javascript" src="js/uploadFile.js"></script>
	
	<script type='text/javascript' src='js/auto/jquery.bgiframe.min.js'></script>
	<script type='text/javascript' src='js/auto/jquery.ajaxQueue.js'></script>
	<script type='text/javascript' src='js/auto/thickbox-compressed.js'></script>
	<script type='text/javascript' src='js/auto/jquery.autocomplete.js'></script>
	<script type='text/javascript' src='js/auto/localdata.js'></script>
	<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
	
	<script type="text/javascript">
	
	</script>
	<script type="text/javascript">
    var flag=false;//标示是否上传过文件
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
			    'fileDesc'       : '*.flv; *.swf; *.pdf',
                'fileExt'        : '*.flv; *.swf; *.pdf',
                'fileDataName'    : 'fileupload',
                'removeCompleted'	:	false,
                'wmode'	:	'transparent',
                'width' : 90,
                'buttonImg' :'uploadify/_upload.png',
                onComplete: function (event, queueID, fileObj, response, data) {
                	 flag=true;
                	jQuery("#" + jQuery(this).attr('id') + ID).find('.percentage').text(' - 完成');         

				},
				onProgress : function(event, queueId, fileObj, response, data) {

				},
                onError: function(event, queueID, fileObj) {   
					$.messager.alert('提示信息','您上传的文件太大','info');
				},   
				onCancel: function(event, queueID, fileObj){ 
					$.messager.alert('提示信息','您取消' +fileObj.name + '的上传!','info');  
					alert("取消了" + fileObj.name);   
				}
 			});
    	});
    </script>
  </head>
  
  <body style="margin-left: 0px;margin-top: 0px;margin-bottom: 0px;margin-right: 0px;margin: 0px;border-bottom: 0px;">
    <table id="test"></table>
			<div id="add" icon="icon-add" class="easyui-window" title="添加" closed="true" 
	    		maximizable="false" minimizable="false" collapsible="false"
	    		style="padding:5px;width:528px;height:350px;">
	    <div style="visibility:hidden;" id="ggggg1">
			1.文本，您只能上传swf格式(可在本系统中下载swf转换器);2.视频，您只能上传flv格式文件！
			<div>
				<table align="left"  style="height:65px;width:400px;">
					<tr>
						<td align="left">
							<input type="file" name="fileupload" id="fileupload"/>
						</td>
						<td>
							<div id="fileQueue">
							</div>
						</td>
					</tr>
				</table>
			</div>
		   
		    <s:form id="insert">
		    	<table align="left" style="font-size: 13px;">
		    		<tr>
						<td> 
							资料名称:
						</td>
						<td>
							<input type="text" id="addzlmc"  style="width:250px;" maxlength="30"/>
						</td>
					</tr>
					<tr>
						<td> 
							资料来源:
						</td>
						<td>
							<input type="text" id="addzlly"  style="width:250px;" maxlength="80"/>
						</td>
						<td style="color:red;">
							&nbsp;*&nbsp;
						</td>
						<td>
							请写明资料出处
						</td>
					</tr>
					<tr>
						<td> 
							资料素材名:
						</td>
						<td>
							<input type="text" id="addzlscm"  style="width:250px;" maxlength="100"/>
						</td>
					</tr>
		    		<tr>
						<td> 
							资料描述:
						</td>
						<td>
							<textarea rows="5" cols="19" id="addzlms"  style="width:250px;"
								onpropertychange="TextUtil.NotMax(this)" maxlength="200">
							</textarea>
						</td>
						<td style="color:red;">
							&nbsp;*&nbsp;
						</td>
						<td>
							最多只能输入200字
						</td>
					</tr>
					<tr></tr><tr></tr>
					<tr>
						<td colspan="2" align="right">
							<a class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" onclick="if(flag){addscwj();}else{$.messager.alert('警告','您未上传呢！','info');}">提交</a>
							<a class="easyui-linkbutton" iconCls="icon-cancel"
								href="javascript:void(0)" onclick="close2()">取消</a>
						</td>
					</tr>
				</table>
		    </s:form>
		    </div>
		</div>
		   <div id="bdAdd" class="easyui-window" title="从已有资源添加" style="padding: 5px; width: 328; height: 200;font-size: 2px;" iconCls="icon-edit"
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
						</td></tr><tr>
						<td>输入关键字：
						</td>
                        <td>
                        	<input type="text" id="name"  required="true"/>
						</td></tr><tr>
						<td>文件类型：
						</td>
						<td>
							<select name="select" id="wjlx">
	                            <option value="图像">图像</option>
	                            <option value="文本">文本</option>
	                            <option value="视频">视频</option>
	                            <option value="音频">音频</option>
	                        </select>
						</td></tr><tr>
						<td colspan="2" align="right">
							<a id="tijiao" class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)">查询</a>
						</td>
					</tr>
				</table>
				</div>
		</div>
		
		<div id="bdAdd1" class="easyui-window" title="资源列表" style="width: 528px; height: 350px;" iconCls="icon-edit"
			closed="true" maximizable="false" minimizable="false" collapsible="false">
			<table id="test2"></table>
		</div>
		
		<div id="AddZsd1" class="easyui-window" title="添加对应知识点" style="padding: 5px; width: 480; height: 300;font-size: 2px;" iconCls="icon-edit"
			closed="true" maximizable="false" minimizable="false" collapsible="false">
			<div style="visibility:hidden;" id="ggggg3">
				<table align="center" width="410px" style="font-size: 13px;">
					<tr>
						<td>
							资料编号:
						</td>
						<td>
							<input id="zlbh1" type="text" readonly="readonly" style="background-color:#EBEBE4"/>
						</td>
					</tr>
					<tr>
						<td>
							资料名称:
						</td>
						<td>
							<input id="zlmc1" type="text" readonly="readonly" style="background-color:#EBEBE4"/>
						</td>
					</tr>
					<tr>
						<td>
							知识点:
						</td>
						<td>
							<input id="website" type="text" onblur="getKname()">
						</td>
						<!-- 
						<td>
							<a id="jixu1" class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" onclick="more()">更多</a>
						</td>
						 -->
					</tr>
					<tr>
						<td>
							课程名称:
						</td>
						<td>
							<input id="kmc1" type="text">
						</td>
					</tr>
					<tr>
						<td>
							章名称:
						</td>
						<td>
							<input id="zmc1" type="text">
						</td>
					</tr>
					<tr>
						<td>
							节名称:
						</td>
						<td>
							<input id="jmc1" type="text">
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
		<div id="xiangxi" class="easyui-window" title="详细信息" style="padding: 5px; width: 328; height: 200;font-size: 2px;" iconCls="icon-edit"
			closed="true" maximizable="false" minimizable="false" collapsible="false">
			<div style="visibility:hidden;" id="ggggg20">
				<table align="center" style="font-size: 13px;">
					<tr>
						<td>资料编号:
						</td>
						<td>
							<input id="wobh" type="text" readonly="readonly" style="background-color:#EBEBE4"></input>
						</td></tr><tr>
						<td>资料名称：
						</td>
                        <td>
                        	<input type="text" id="womc"  readonly="readonly" style="background-color:#EBEBE4"/>
						</td></tr><tr>
						<td>资料描述：
						</td>
						<td>
							<s:textarea id="woms" cols="20" rows="5" readonly="readonly" style="background-color:#EBEBE4"></s:textarea>
						</td></tr>
				</table>
				</div>
		</div>
  </body>
</html>
