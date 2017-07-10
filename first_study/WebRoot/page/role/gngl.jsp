<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>功能管理</title>
    
    <link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/gngljs/gngl_add.js"></script>
	<script type="text/javascript" src="js/gngl.js"></script>
	
	<script type='text/javascript' src='js/auto/jquery.bgiframe.js'></script>
	<script type='text/javascript' src='js/auto/jquery.ajaxQueue.js'></script>
	<script type='text/javascript' src='js/auto/thickbox-compressed.js'></script>
	<script type='text/javascript' src='js/auto/jquery.autocomplete.js'></script>
	<script type='text/javascript' src='js/auto/localdata.js'></script>
	<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
	<script>
	
	</script>
  </head>

	<body style="margin-left: 0px;margin-top: 0px;margin-bottom: 0px;margin-right: 0px;margin: 0px;border-bottom: 0px;">
		<table id="test"></table>
		<div id="dd1" class="easyui-window" title="添加功能" style="padding: 5px; width: 435; height: 250;font-size: 2px;" iconCls="icon-edit"
			closed="true" maximizable="false" minimizable="false" collapsible="false">
			<div style="visibility:hidden;" id="ggggg1">
			<s:form id="add" action="insertFunctions">
				<table align="center" width="410px" style="font-size: 13px;">
					<tr>
						<td width="25%">
							模块分类名称：
						</td>
						<td width="40%"> 
							<select id="addmcname" name="addmcname" onChange="getMname()" style="width:100px;">
							<option  value="-1">--请选择--</option>
							</select>
						</td>
						<td width="35%">
						</td>
					</tr>
					<tr>
						<td>
							模块名称：
						</td>
						<td> 
							<select id="addmname" onchange="getM()" style="width:100px;">
							<option  value="-1">--请选择--</option>
							</select>
						</td>
						<td width="40%"><span id="amname"></span>
						</td>
					</tr>
					<tr> 
						<td>
							功能名称：
						</td>
						<td>  
							<input type="text" id="addfunctionname"/>
						</td>
						<td><span id="flength"></span>
						</td>
					</tr>
					<tr>
						<td>
							actionname：
						</td>
						<td>
							<s:textfield id="addactionname" name="function.actionname"></s:textfield>
						</td>
						<td><span id="alength"></span>
						</td>
					</tr>
					<tr>
						<td>
							功能备注：
						</td>
						<td>
							<s:textfield id="addcomments" name="function.comments"></s:textfield>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="right">
							<a id="tijiao" class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)">提交</a>
							&nbsp;
							<a class="easyui-linkbutton" iconCls="icon-cancel"
								href="javascript:void(0)" onclick="close1()">取消</a>
						</td>
					</tr>
				</table>
				</s:form>
				</div>
		</div>
		<div id="edit1" class="easyui-window" title="修改" style="padding: 5px; width: 430; height: 250;" iconCls="icon-edit"
			closed="true" maximizable="false" minimizable="false" collapsible="false">
			<div style="visibility:hidden;" id="ggggg2">
			<form id="update1">
				<table align="center" style="font-size: 13px;">
					<tr>
						<td width="25%">
							模块分类名称：
						</td>
						<td width="40%"> 
							<select id="updatemcname" onChange="getMname1()" style="width:100px;">
							<option  value="-1">--请选择--</option>
							</select>
						</td>
						<td width="35%">
						</td>
					</tr>
					<tr>
						<td>
							模块名称：
						</td>
						<td> 
							<s:select id="mname1" list="modulelist" listKey="mname" listValue="mname" onchange="getM1()" style="width:100px;">
							</s:select>						
						</td>
						<td width="20%"><span id="umname"></span>
						</td>
					</tr>

					<tr>
						<td>
							actionname：
						</td>
						<td>
							<input type="text" id="actionname1" readonly="readonly" style="background-color:#EBEBE4"/>
						</td>
						<td width="40%"><span id="uactionname"></span>
						</td>
					</tr>
					<tr>
						<td>
							功能名称：
						</td>
						<td>
							<input type="text" id="functionname1" />
						</td>
						<td width="40%"><span id="ufunctionname"></span>
						</td>
					</tr>
					<tr>
						<td>
							comments：
						</td>
						<td>
							<input id="comments1" type="text" />
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="right">
							<a class="easyui-linkbutton" iconCls="icon-ok" id="next" href="javascript:void(0)" onclick="edit1()">下一条</a>
							&nbsp;
							<a class="easyui-linkbutton" iconCls="icon-ok" id="update3" href="javascript:void(0)">完成</a>
							&nbsp;
							<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close3()">取消</a>
						</td>
					</tr>
				</table>
			</form>
			</div>
		</div>
		
		<div id="query" class="easyui-window" title="查询" style="padding: 5px; width:600px; height: 100px;"
			iconCls="icon-search" closed="true" maximizable="false"	minimizable="false" collapsible="false">
			<div style="visibility:hidden;" id="ggggg3">
				<form>
					<table style="font-size: 13px;">
						<tr>
							<td>模块名称：
							<s:select id="type" list="modulelist" listKey="mname" listValue="mname">
							</s:select>	
							</td>
							<td>
								功能名称<input id="website" type="text" />
							</td>
							<td>
								<a id="search" class="easyui-linkbutton" iconCls="icon-search"
									href="javascript:void(0);" onclick="query()">查询</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div> 
	<div id="add1" closed="true" class="easyui-window" title="添加分类" iconCls="icon-add" style="width:350px;height:200px;padding:5px;background: #fafafa;">
		<div class="easyui-layout" fit="true" style="visibility:hidden;" id="ggggg4">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
				<table style="font-size: 13px;">
				<tr>
					<td>模块分类：</td>
					<TD><input id="mclassname" name="mclassname"></TD>
				</tr>
				<tr>
					<td>分类顺序：</td>
					<TD><input id="classolder" name="classolder"></TD>
				</tr>
				<tr>
					<td>评论：</td>
					<TD><input id="comments" name="comments"></TD>
				</tr>
				</table>					
			</div>
			<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="add1()">增加</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close4()">取消</a>
			</div>
		</div>
	</div>
	
	<div id="add2" closed="true" class="easyui-window" title="添加模块分类" iconCls="icon-add" style="width:350px;height:240px;padding:5px;background: #fafafa;">
		<div class="easyui-layout" fit="true" style="visibility:hidden;" id="ggggg5">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
			<s:form id="a" name="name">
				<table style="font-size: 13px;">
				<tr>
					<td>模块分类名称：</td>
					<TD><s:textfield id="addmcname1" name="module.moduleclass.mclassname" readonly="true" style="background-color:#EBEBE4"></s:textfield></TD>
				</tr>
				<tr>
					<td>模块名称：</td>
					<TD><s:textfield id="addmname1" name="module.mname"></s:textfield></TD>
				</tr>
				<tr>
					<td>模块排序：</td>
					<TD><s:textfield id="classolder1" name="module.molderid"></s:textfield></TD>
				</tr>
				<tr>
					<td>评论：</td>
					<TD><s:textfield id="modulecomments" name="module.comments"></s:textfield></TD>
				</tr>
				</table>
			</s:form>					
		</div>
			<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="add2()">增加</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close5()">取消</a>
			</div>
		</div>
	</div>
	</body>
</html>
