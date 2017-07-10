<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<!-- 要求外层div style="padding:5px;width:500px;height:160px;align:center;" -->
	<table width="100%" border="1" cellspacing="0" cellpadding="0" style= "BORDER-COLLAPSE:   collapse " bordercolor="#C6C6C6">
	  <tr>
	    <td align="center">最大只能上传100M</td>
	  </tr>
	  <tr>
	    <td height='50px' style="background-color:#EEFA7D"><div id="fileQueue"></div></td>
	  </tr>
	  <tr>
	    <td><table width="100%" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="25%">&nbsp;</td>
	        <td width="25%"><input type="file" name="fileupload" id="fileupload" /></td>
	        <td width="25%">&nbsp;</td>
	        <td width="25%">&nbsp;</td>
	      </tr>
	    </table></td>
	  </tr>
	</table>

