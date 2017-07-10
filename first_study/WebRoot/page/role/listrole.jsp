<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>角色管理</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/listrole.js"></script>
	<style type="text/css">
	body{
    margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-size: 12px;
   }
   .table{
   	font-size: 12px;
   }
	
	</style>
	<script>
			
	
	</script>
  </head>
  
  <body>
    	<table id="test">
    	</table>
    	
    	<div id="dd1" class="easyui-window" title="添加角色" style="padding: 5px;width: 400;height: 210;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="false">
    <div style="visibility:hidden;" id="ggggg1">
		<form id="add">
			<table align="center" style="font-size: 13px;">
				<tr>
					<td width="30%">角色名称：</td>
					<td width="40%" ><input type="text" id="addrolename"/></td>
					<td width="30%"><span id="urolename"></span></td>
				</tr>
				<tr>
					<td >角色描述：</td>
					<td><textarea rows="5" cols="20" id="addmiaoshu"></textarea></td>
				</tr>
				<tr>
						<td colspan="3" align="right"><a class="easyui-linkbutton" iconCls="icon-ok" 
								href="javascript:void(0)" onclick="add()">提交</a>
						
							<a class="easyui-linkbutton" iconCls="icon-cancel"
								href="javascript:void(0)" onclick="close1()">取消</a>
						</td>
					</tr>
			</table>
			</form>
			</div>
	</div>
    <div id="edit1" class="easyui-window" title="修改" style="padding: 5px;width: 400;height: 240;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="false">
    <div style="visibility:hidden;" id="ggggg2">
     <form id="update1">
			<table align="center"  style="font-size: 13px;">
				<tr>
					<td width="25%">角色ID：</td>
					<td><input type="text" id="roleid1" readonly="true" style="background-color:#EBEBE4"/></td>
					
				</tr>
				<tr>
					<td>角色名称：</td>
					<td><input type="text" id="rolename1"/></td>
					<td><span id="urolename1"></span></td>
				</tr>
				<tr>
					<td>角色描述：</td>
					<td width="35%"><textarea rows="5" cols="20"id="miaoshu1"></textarea></td>
				</tr>
				<tr>
					<td colspan="3" align="right">
						<a id="next" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="edit1()">下一条</a>
						<a id="wancheng" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="edit1()">完成</a>
						<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="clos()">取消</a>
					</td> 
				</tr>
			</table>
			</form>
			</div>
    </div>
    
    <div id="show" class="easyui-window" title="详细信息" style="padding: 5px;width: 350;height: 215;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="false">
    <div style="visibility:hidden;" id="ggggg3">
     <form>
			<table align="center" style="font-size: 13px;">
				<tr>
					<td width="35%">角色ID：</td>
					<td><input type="text" id="show_roleid" readonly="true" style="background-color:#EBEBE4"/></td>
				</tr>
				<tr>
					<td>角色名称：</td>
					<td><input type="text" id="show_rolename" readonly="true" style="background-color:#EBEBE4"/></td>
				</tr>
				<tr>
					<td>角色描述：</td>
					<td width="65%"><textarea rows="5" cols="20" id="show_miaoshu" readonly="true" style="background-color:#EBEBE4"></textarea></td>
				</tr>
			</table>
			</form>
			</div>
    </div>
	 <div id="query" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;"
    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
        <div style="visibility:hidden;" id="ggggg4">
        	<form>
	            <table>
	                <tr>
	                    <td>
	                        <select name="select" id="type">
	                            <option value="roleid">角色ID</option>
	                            <option value="rolename">角色名称</option>
	                            <option value="miaoshu">角色描述</option>
	                        </select>
	                    </td>
	                    <td><input type="text" name="id" id="name"  required="true"></td>
	                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
	                </tr>
	            </table>
            </form>
        </div>
    </div>
  </body>
</html>
