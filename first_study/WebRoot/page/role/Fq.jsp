<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>赋权</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/Fq.js"></script>
	
	<script type='text/javascript' src='js/auto/jquery.bgiframe.js'></script>
	<script type='text/javascript' src='js/auto/jquery.ajaxQueue.js'></script>
	<script type='text/javascript' src='js/auto/thickbox-compressed.js'></script>
	<script type='text/javascript' src='js/auto/jquery.autocomplete.js'></script>
	<script type='text/javascript' src='js/auto/localdata.js'></script>
	<script type="text/javascript">
	
    
	</script>

  </head>
  
  <body style="margin-left: 0px;margin-top: 0px;margin-bottom: 0px;margin-right: 0px;margin: 0px;border-bottom: 0px;">
   <table id="test"></table>
   <div id="query" class="easyui-window" title="查询" style="padding: 5px; width: 600px; height: 100px;"
			iconCls="icon-search" closed="true" maximizable="false"
			minimizable="false" collapsible="false">
			<div>
				<form>
					<table style="font-size: 13px;">
						<tr>
							<td>模块名称：
								<s:select id="type" list="modulelist" listKey="mname" listValue="mname">
								</s:select>	
							</td>
						
							<td>
								功能名称：
							</td>
							<td>
								<input id="name" type="text" />
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
  </body>
</html>
