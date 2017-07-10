
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>查询模块列表</title>
 <style type="text/css">
	  
	  form.cmxform label.error, label.error {
	  /* remove the next line when you have trouble in IE6 with labels in list */
	  color: red;
	
     }
</style>
 <link href="css/login_style.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	<script type='text/javascript' src='js/auto/jquery.bgiframe.js'></script>
	<script type='text/javascript' src='js/auto/jquery.ajaxQueue.js'></script>
	<script type='text/javascript' src='js/auto/thickbox-compressed.js'></script>
	<script type='text/javascript' src='js/auto/jquery.autocomplete.js'></script>
	<script type='text/javascript' src='js/auto/localdata.js'></script>
	<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
    <link rel="stylesheet" type="text/css" href="css/thickbox.css" />
	
	<script type='text/javascript'>
	 var websites=[];
	 var websites1=[];
	
		
  </script>
  <script type='text/javascript' src='js/mkgl.js' charset="gb2312"></script>
  <style type="text/css">


#a label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}
.high{
	    color:#F0000F;
	  }

</style>
</head>
<body >

	

	 <table id="test"></table> 

	<div id="select" class="easyui-dialog" closed="true" title="模块查询" icon="icon-search" style="padding:0px;width:400px;height:200px;"	>
	   <div align="center" style="visibility:hidden;" id="ggggg1">
	   <table width="100%" height="70%" align="center" class="login_text" id="table">
	       <s:form id="w" name="form">
	       
	            <tr>
	              <td align="left">&nbsp;&nbsp; 分类名称：<s:select id="mclassname1" name="mclassname" list="feileiname" headerKey="-1" headerValue="--请选择--" ></s:select>
	                                     </td>
	            </tr>
	            
	             <tr>
	              <td align="left">&nbsp;&nbsp; <em class="high">*</em>&nbsp;<font style="font-size:12px;LINE-HEIGHT: 20px; FONT-FAMILY: Arial, Helvetica, sans-serif;font-style:italic;">可以仅根据&nbsp;"&nbsp;分类名称&nbsp;"&nbsp;查询</font>
	                                     </td>
	            </tr>
	            
	            <tr>
	              <td align="left">&nbsp;&nbsp;&nbsp;模块名称：<s:textfield name="mname" id="website" ></s:textfield></td>
	            </tr>
	      
	      </s:form>     
	   </table>
	    </div>
	         
	</div>
	<div id="add" class="easyui-dialog" closed="true" title="模块添加" icon="icon-add" style="padding:0px;width:500px;height:250px;">
	     <div align="center" style="visibility:hidden;" id="ggggg2">
	          <table  width="100%" height="10%" valign="top" class="login_text">
	            
	             <tr align="center"><td align="center">已有模块名：&nbsp;&nbsp;&nbsp;<select id="mk" >
	                                                                                       <option value="-1">---只供参考---</option> 
	                                                                                </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
	             <tr><td><hr size="1px"color="#A4BED4"/></td></tr>
	             
	         </table>
	     <s:form id="a" name="form">     
	   <table width="100%" height="70%" align="center" class="login_text">
	        
	        
	             <tr>
	              <td align="right" width="40%">分类名称：</td>
                  <td >  &nbsp;&nbsp; <select id="jy1" name="module.moduleclass.mclassname">
                            <option value="-1">--请选择--</option>   
                                <s:iterator value="feileiname" id="bean">   
                                <option><s:property/></option>   
                                 </s:iterator>
                                <option value="tjgd">--添加更多--</option>
                                </select></td></tr>
	              <tr> <td align="right" width="40%">模块名称：</td>
	                   <td >&nbsp;&nbsp;&nbsp;<s:textfield id="jy" name="module.mname" ></s:textfield></td>
	             </tr>
	            <tr>
	               <td align="right" width="40%">模块排序：</td>
	               <td >&nbsp;&nbsp;&nbsp;<s:textfield name="module.molderid" /></td></tr>
	            <tr>
	               <td align="right" width="40%">描述：</td>
	               <td >&nbsp;&nbsp;&nbsp;<s:textfield name="module.comments" ></s:textfield></td>
	            </tr>
	           
	          
	       
	       </table> </s:form>  
	    </div>
	</div>
	
	<div id="edit" class="easyui-dialog" closed="true" title="模块修改" icon="icon-edit" style="padding:0px;width:400px;height:250px;">
	     <div align="center" style="visibility:hidden;" id="ggggg3">
	     <s:form id="e" name="form">
	   <table width="100%" height="70%" align="center" class="login_text" >
	      <tr>
	              <td   height="10px"></td><td width="30%" align="left"></td>
	              
	              </tr>
	            <tr>
	              <td >&nbsp;&nbsp;&nbsp;分类名称：<s:select name="module.moduleclass.mclassname" id="f" style="width:152px;" list="feileiname" headerKey="-1" headerValue="--请选择--">
	                                 </s:select>&nbsp;&nbsp;</td><td></td>
	              
	              </tr>
	              <tr>
	              
	               <td >&nbsp;&nbsp;&nbsp;模块名称：<s:textfield id="m" name="module.mname" readonly="true" style="background-color:#EBEBE4"></s:textfield>&nbsp;&nbsp;</td><td></td>
	              </tr>
	            <tr>
	               <td >&nbsp;&nbsp;&nbsp;模块排序：<s:textfield id="p" name="module.molderid" onkeydown="onlyNum_down();" onkeyup="xiugai_up();"></s:textfield>&nbsp;&nbsp;</td>
	               <td><span class="login_txt" id="xg_Shuzi"></span></td>
	               
	            </tr>
	            <tr>
	               
	               <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;描述：<s:textfield id="ms" name="module.comments" ></s:textfield> &nbsp;&nbsp;</td>
	            </tr>
	    
	        
	       </table></s:form>  
	       <div border="false" style="text-align:right;height:30px;line-height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="update();"><span id="xiayige">下一个</span></a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closexiayige();">取消</a>
			</div>
	    </div>
	</div>
	
	<div id="weihu" class="easyui-dialog" closed="true" title="维护功能（左侧是将维护模块---右侧是提供数据模块）" icon="icon-save" style="padding:0px;width:750px;height:400px;">
	    
	    <div style="visibility:hidden;" id="ggggg4">
	        <table align="center" width="100%" height="100%" >
	          <tbody>
	             <tr>
	               <td width="42.5%" height="100%">
	                 <div style="padding:1px;border:1px solid; border-color:#A4BED4;">
	                  <div  style="padding:0px;border:1px solid; border-color:#A4BED4;">
					       <table width="100%" height="40px" class="login_text">
					        <tbody>
					           <tr><td align="center">选择将维护的模块名称：<select id="weihu2" >
					                                                 <option value="-1">--请选择--</option>
					                                           </select></td></tr>
					        </tbody>
					       </table>
					    </div>
	                  <div >
	                    <table id="datagrid1"></table>
	                  </div>
	                  </div>
	               </td>
	               <td align="center" width="15%">
	                  <div  class="toolbar" >
	                   <table>
	                      <tr><td><a href="#" id="weihuadd" iconCls="icon-undo" class="easyui-linkbutton" >添加</a></td></tr>
	                      <tr height="2"><td ></td></tr>
	                      <tr><td><a href="#" id="weihudel" iconCls="icon-redo" class="easyui-linkbutton"  >移除</a></td></tr>
	                    </table>
	                   </div> 
	               </td>
	               <td width="42.5%" height="100%">
	                  <div style="padding:1px;border:1px solid;border-color:#A4BED4;">
	                   <div style="padding:0px;border:1px solid;border-color:#A4BED4;">
	                    
	                      <table width="100%" height='140px' class="login_text">
	                         <s:form id="weihu1" name="form">
	                           <tr><td align="left">&nbsp;&nbsp;&nbsp;模块名称：<select name="mname" id="func" >
	                                                                                     <option value="-1">--请选择--</option>
	                                                                              </select></td></tr>
	                            <tr>
					              <td align="left">&nbsp;&nbsp; <em class="high">*</em>&nbsp;<font style="font-size:12px;LINE-HEIGHT: 20px; FONT-FAMILY: Arial, Helvetica, sans-serif;font-style:italic;">可以仅根据&nbsp;"&nbsp;模块名称&nbsp;"&nbsp;查询</font>
					                                     </td>
					            </tr>
	                           <tr><td align="left">&nbsp;&nbsp;&nbsp;功能名称：<s:textfield name="functionname" ></s:textfield></td></tr>
	                           
	                           <tr><td align="right" valign="bottom"><a href="#" id="weihucha" class="easyui-linkbutton"  iconCls="icon-ok">查询</a></td></tr>
	                         </s:form>
	                      </table>
	                   </div>
	                  
	                   <div>
	                      <table id="datagrid2" ></table>
	                   </div>
	                </div>
	             </td>
	             </tr>
	          </tbody>
	        </table>
	    </div>
	    
    </div>
	
	
	<div id="add1" closed="true" class="easyui-window" title="添加分类" iconCls="icon-add" style="width:350px;height:200px;padding:5px;background: #fafafa;">
		<div class="easyui-layout" fit="true" style="visibility:hidden;" id="ggggg5">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
				<table class="login_text">
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
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close1()">取消</a>
			</div>
		</div>
	</div>
	
</body>
</html>