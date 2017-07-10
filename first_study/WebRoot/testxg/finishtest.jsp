<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>测试完成</title>
		<link rel="stylesheet" type="text/css"
			href="js/ui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	</head>
	<body  style="font-size:12px;">
		<center>
			<font color="#88B04E" size="4">  
                  <s:property value="sjname" />
            </font> 
		</center>
		<s:form action="sj">
		<s:if test="testxz1.size!=0">
			<td>
				<font color="#88B0E4" size="4">单选题</font>
			</td>
			<s:iterator value="testxz1" var="testxz1" status="stat1" id="testxz1">
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
                     </td>  
                 </tr>
                 <tr>  
                     <td height="20" width="640" align="left">  
                     	B、<s:property value="xx2" />
                     </td>  
                 </tr>
                 <tr>  
                     <td height="20" width="640" align="left">  
                     	C、<s:property value="xx3" />
                     </td>  
                 </tr> 
                 <tr>  
                     <td height="20" width="640" align="left"> 
                     	D、<s:property value="xx4" /> 
                     </td>  
                 </tr> 
                 <tr>
                 	<TD>
                 			 答案是： <s:property value="da" />
                 	</TD>
                 </tr>
                 <tr>
                 	<TD>
                 		你的答案是： <s:property value="md5" />
                 	</TD>
                 </tr>
			</table>
			<hr/>
			</s:iterator>
		</s:if>
		<s:if test="testxz2.size!=0">
			<td>
				<font color="#88B0E4" size="4">多选题</font>
			</td>
			<s:iterator value="testxz2" var="testxz2" status="stat" id="testxz2">
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
                     </td>  
                 </tr>
                 <tr>  
                     <td height="20" width="640" align="left">  
                    	B、<s:property value="xx2" />
                     </td>  
                 </tr>
                 <tr>  
                     <td height="20" width="640" align="left">  
                    	C、<s:property value="xx3" />
                     </td>  
                 </tr> 
                 <tr>  
                     <td height="20" width="640" align="left">  
                    	D、<s:property value="xx4" />
                     </td>  
                 </tr> 
                 <tr>
                 	<TD>
                 		答案是： <s:property value="da" />
                 	</TD>
                 </tr>
                 <tr>
                 	<TD>
                 		你的答案是： <s:property value="md5" />
                 	</TD>
                 </tr>
			</table>
			<hr/>
			</s:iterator>
		</s:if>
		<s:if test="testpd1.size!=0">
			<td>
				<font color="#88B0E4" size="4">判断题</font>
			</td>
			<s:iterator value="testpd1" var="testpd1" status="stat2" id="testpd1">
				<table>
				 <tr>  
                     <td width="640" height="20">  
                         <font color="green">  
                         <s:property value="#stat2.index+1" />                          .  
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
                     </td>  
                 </tr> 
                 <tr>
                 	<TD>
                 		<s:if test="md5==\"0\"">
                     	  	你的答案是：错
                     	</s:if>
                     	<s:elseif test="md5==\"1\"">
                     		你的答案是：对
                    	</s:elseif>
                    	<s:else>
                    		你的答案是： <s:property value="md5" />
                    	</s:else>
                 		
                 	</TD>
                 </tr>
			</table>
				<hr />
			</s:iterator>
		</s:if>
		<s:if test="testczt.size!=0">
			<td>
				<font color="#88B0E4" size="4">操作题</font>
			</td>
				<s:iterator value="testczt" var="testczt" status="stat3" id="testczt">
						<table>
						 <tr> 
		                     <td width="440" height="20">  
		                         <font color="green">  
		                         <s:property value="#stat3.index+1" />                          .  
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
