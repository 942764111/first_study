<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>试卷答案</title>
		<link rel="stylesheet" type="text/css"
			href="js/ui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	</head>
	<body  style="font-size:12px;">
		<s:form action="sj">
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
                     <td width="640" height="20">  
                         <font color="green">  
	                         <s:property value="#stat1.index+1" />                          .  
	                         <s:property value="tg" />
                         </font>  
                     </td>  
                 </tr>
                 <tr>  
                     <td height="20" width="640" align="left">  
                     	A、<s:property value="xx1" />
                     	<%--<s:radio list='#{"A":"A、"+sjxztxx1}' name="answer[%{#stat1.index}]" value="" theme="simple"/> 
                     --%>
                     </td>  
                 </tr>
                 <tr>  
                     <td height="20" width="640" align="left">  
                     	B、<s:property value="xx2" />
                         <%--<s:radio list='#{ "B":"B、"+sjxztxx2}'  
                             name="answer[%{#stat.index}]" value="" theme="simple"/> 
                     --%></td>  
                 </tr>
                 <tr>  
                     <td height="20" width="640" align="left">  
                     	C、<s:property value="xx3" />
                         <%--<s:radio list='#{ "C":"C、"+sjxztxx3}'  
                             name="answer[%{#stat.index}]" value="" theme="simple"/> 
                     --%></td>  
                 </tr> 
                 <tr>  
                     <td height="20" width="640" align="left"> 
                     	D、<s:property value="xx4" /> 
                         <%--<s:radio list='#{"D":"D、"+sjxztxx4}'  
                             name="answer[%{#stat.index}]" value="" theme="simple"/>--%> 
                     </td>  
                 </tr> 
                 <tr>
                 	<TD>
                 			 答案是： <s:property value="da" />
                 	</TD>
                 </tr>
			</table>
			<hr/>
			</s:iterator>
			</s:if>
			<s:if test="ckxz2.size!=0">
			<td>
				<font color="#88B0E4" size="4">多选题</font>
			</td>
			<s:iterator value="ckxz2" var="ckxz2" status="stat" id="ckxz2">
			<table>
				 <tr>  
                     <td width="640" height="20">  
                         <font color="green">  
	                         <s:property value="#stat.index+1" />                          .  
	                         <s:property value="tg" />
                         </font>  
                     </td>  
                 </tr>
                 <tr>  
                     <td height="20" width="640" align="left">  
                    	A、<s:property value="xx1" />
                         <%--<s:checkboxlist list='#{"A":"A、"+sjxztxx1}'  
                             name="answer[%{#stat.index}]" value="" theme="simple"/> 
                     --%></td>  
                 </tr>
                 <tr>  
                     <td height="20" width="640" align="left">  
                    	B、<s:property value="xx2" />
                         <%--<s:checkboxlist list='#{ "B":"B、"+sjxztxx2}'  
                             name="answer[%{#stat.index}]" value="" theme="simple"/> 
                     --%></td>  
                 </tr>
                 <tr>  
                     <td height="20" width="640" align="left">  
                    	C、<s:property value="xx3" />
                         <%--<s:checkboxlist list='#{ "C":"C、"+sjxztxx3}'  
                             name="answer[%{#stat.index}]" value="" theme="simple"/> 
                     --%></td>  
                 </tr> 
                 <tr>  
                     <td height="20" width="640" align="left">  
                    	D、<s:property value="xx4" />
                         <%--<s:checkboxlist list='#{"D":"D、"+sjxztxx4}'  
                             name="answer[%{#stat.index}]" value="" theme="simple"/> 
                     --%></td>  
                 </tr> 
                 <tr>
                 	<TD>
                 			 答案是： <s:property value="da" />
                 	</TD>
                 </tr>
			</table>
			<hr/>
			</s:iterator>
			</s:if>
			<s:if test="ckpd1.size!=0">
			<td>
				<font color="#88B0E4" size="4">判断题</font>
			</td>
			<s:iterator value="ckpd1" var="ckpd1" status="stat" id="ckpd1">
				<table>
				 <tr>  
                     <td width="640" height="20">  
                         <font color="green">  
                         <s:property value="#stat.index+1" />                          .  
                         <s:property value="tg" />
                     	
                         </font>  
                     </td>  
                 </tr>
                 	<tr>  
                     <td height="20" width="640" align="left">
                     	<s:if test="da==\"0\"">
                     	  	答案是：错
                     	</s:if>
                     	<s:elseif test="da==\"1\"">
                     		答案是：对
                    	</s:elseif>
                        <%--   <s:radio list='#{"1":"对", "0":"错"}'  
                             name="answer[%{#stat.index}]" value="" theme="simple"/> 
                     --%></td>  
                 </tr> 
			</table>
				<hr />
			</s:iterator>
			</s:if>
			<s:if test="czt1.size!=0">
			<td>
				<font color="#88B0E4" size="4">操作题</font>
			</td>
				<s:iterator value="czt1" var="czt1" status="stat" id="czt1">
						<table>
						 <tr> 
		                     <td width="440" height="20">  
		                         <font color="green">  
		                         <s:property value="#stat.index+1" />                          .  
		                         <s:property value="dttg" />  
		                         </font>  
		                     </td> 
		                 </tr>
		                 <tr> 
		                     <td width="440" height="20">  
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
		</s:form>
	</body>
</html>
