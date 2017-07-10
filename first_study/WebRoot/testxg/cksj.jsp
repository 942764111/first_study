<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>jQuery EasyUI</title>
		<link rel="stylesheet" type="text/css"
			href="js/ui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
       
		<script type="text/javascript">
	
</script>
	</head>

	<body  class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px; font-size:12px;">
			
			<!-- ----------------选择题 --------------------- -->
			<div  region="center"  style="width:900px;padding:30px;">
			<center>
				<font color="#88B04E" size="4">  
	                  <s:property value="sjname" />
                 </font> 
			</center>
			<s:if test="ckxz1.size!=0">
				<td>
					<font color="#88B0E4" size="4">单选题</font>
				</td>
				<s:iterator value="ckxz1" var="ckxz1" status="stat1" id="ckxz1">
						<table>
							<tr>
								<td height="36">
									<font color="green"> 
										<s:property value="#stat1.index+1" />									.
										<s:property value="tg" /> 
									</font>
								</td>
							</tr>
							<tr>  
		                     <td height="36" align="left">  
		                         <s:radio list='#{"A":"A、"+xx1, "B":"B、"+xx2, "C":"C、"+xx3,"D":"D、"+xx4}' name="answer1[%{#stat1.index}]" theme="simple"/>  
		                     </td>  
		                 	</tr>
							</table>
					<hr />
				</s:iterator>
			</s:if>
			<s:if test="ckxz2.size!=0">
				<td>
					<font color="#88B0E4" size="4">多选题</font>
				</td>
				<s:iterator value="ckxz2" var="ckxz2" status="stat" id="ckxz2">
						<table>
							<tr>
								<td height="36">
									<font color="green"> 
										<s:property value="#stat.index+1" />									. 
										<s:property value="tg" /> 
									</font>
								</td>
							</tr>
							<tr>  
		                     <td height="36" align="left">  
		                         <s:checkboxlist list='#{"A":"A、"+xx1, "B":"B、"+xx2, "C":"C、"+xx3,"D":"D、"+xx4}' name="answer[%{#stat.index}]" theme="simple"/>  
		                     </td>  
		                 	</tr>
							</table>
					<hr />
				</s:iterator>
			</s:if>
			
			<!---------判断题 ------------>
			<s:if test="ckpd1.size!=0">
				<td>
					<font color="#88B0E4" size="4">判断题</font>
				</td>
				<s:iterator value="ckpd1" var="ckpd1" status="stat" id="ckpd1">
					<table>
					 <tr>  
	                     <td height="36">  
	                         <font color="green">  
	                         <s:property value="#stat.index+1" />                          .  
	                         <s:property value="tg" />
	                         </font>  
	                     </td>  
	                 </tr>
	                 	<tr>  
	                     <td height="36" align="left">  
	                    
	                         <s:radio list='#{"1":"对", "0":"错"}'  
	                             name="answer[%{#stat.index}]" value="" theme="simple"/> 
	                     </td>  
	                 </tr> 
				</table>
					<hr />
				</s:iterator>
			</s:if>
			
			<!---------操作题 ------------>	
			<s:if test="czt1.size!=0">
				<td>
					<font color="#88B0E4" size="4">操作题</font>
				</td>
				<s:iterator value="czt1" var="czt1" status="stat" id="czt1">
					<table>
					 <tr> 
	                     <td height="46">  
	                         <font color="green">  
	                         <s:property value="#stat.index+1" />                          .  
	                         <s:property value="dttg" />  
	                         </font>  
	                     </td> 
	                 </tr>
	                 <tr> 
	                     <td height="46">  
		                     <%List list=(List)request.getAttribute("xtth");%>
							<%List list1=(List)request.getAttribute("xttg");%>
							<%for(int i=0;i<list.size();i++){ %>
							<p>第<%=list.get(i) %>问：<%=list1.get(i) %> </p>
							<%} %> 
	                     </td> 
	                 </tr>
				</table>
				<hr />
				</s:iterator>
			</s:if>
		</div>
	</body>
</html>
