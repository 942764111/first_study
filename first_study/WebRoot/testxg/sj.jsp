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

	<body>
		<center>
			<font color="#88B04E" size="4">  
                  <s:property value="sjname" />
            </font> 
		</center>
		<s:form action="fx" method="post">

			<!-- ----------------选择题 ----------------------->
			<s:if test="xz1.size!=0">
			<td>
				<font color="#88B0E4" size="4">单选题</font>
			</td>
			<s:iterator value="xz1" var="xz1" status="stat" id="xz1">
			<table>
				 <tr>  
                     <td width="640" height="36">  
                         <font color="blue">  
                         <s:property value="#stat.index+1" />                          .  
                         <s:property value="tg" />
                          <s:hidden name="da1" value="%{da}"/>     
                         </font>  
                     </td>  
                 </tr>
                 
                 	<tr>  
                     <td height="36" width="640" align="left">  
                    
                         <s:radio list='#{"A":"A、"+xx1}'  
                             name="answer[%{#stat.index}]"  theme="simple"/> 
                     </td>  
                 </tr>
                 <tr>  
                     <td height="36" width="640" align="left">  
                    
                         <s:radio list='#{ "B":"B、"+xx2}'  
                             name="answer[%{#stat.index}]"  theme="simple"/> 
                     </td>  
                 </tr>
                 <tr>  
                     <td height="36" width="640" align="left">  
                    
                         <s:radio list='#{ "C":"C、"+xx3}'  
                             name="answer[%{#stat.index}]"  theme="simple"/> 
                     </td>  
                 </tr> 
                 <tr>  
                     <td height="36" width="640" align="left">  
                    
                         <s:radio list='#{"D":"D、"+xx4}'  
                             name="answer[%{#stat.index}]"  theme="simple"/> 
                     </td>  
                 </tr> 
			</table>
			<hr/>
			</s:iterator>
		</s:if>



<!-- ----------------选择题 ----------------------->
		<s:if test="dxz1.size!=0">
			<td>
				<font color="#88B0E4" size="4">多选题</font>
			</td>
			<s:iterator value="dxz1" var="dxz1" status="stat" id="dxz1">

			<table>
				 <tr>  
                     <td width="640" height="36">  
                         <font color="blue">  
                         <s:property value="#stat.index+1" />                          .  
                         <s:property value="tg" />
                          <s:hidden name="da1" value="%{da}"/>     
                         </font>  
                     </td>  
                 </tr>
                 
                 	<tr>  
                     <td height="36" width="640" align="left">  
                    
                         <s:checkboxlist list='#{"A":"A、"+xx1}'  
                             name="answer[%{#stat.index}]"  theme="simple"/> 
                     </td>  
                 </tr>
                 <tr>  
                     <td height="36" width="640" align="left">  
                    
                         <s:checkboxlist list='#{ "B":"B、"+xx2}'  
                             name="answer[%{#stat.index}]"  theme="simple"/> 
                     </td>  
                 </tr>
                 <tr>  
                     <td height="36" width="640" align="left">  
                    
                         <s:checkboxlist list='#{ "C":"C、"+xx3}'  
                             name="answer[%{#stat.index}]"  theme="simple"/> 
                     </td>  
                 </tr> 
                 <tr>  
                     <td height="36" width="640" align="left">  
                    
                         <s:checkboxlist list='#{"D":"D、"+xx4}'  
                             name="answer[%{#stat.index}]"  theme="simple"/> 
                     </td>  
                 </tr> 
			</table>
			<hr/>
			</s:iterator>
		</s:if>

		


			<!---------判断题 ------------>
			<s:if test="pd1.size!=0">
			<td>
				<font color="#88B0E4" size="4">判断题</font>
			</td>
			<s:iterator value="pd1" var="pd1" status="stat" id="pd1">
				<table>
				 <tr> 
                     <td width="640" height="36">  
                         <font color="blue">  
                         <s:property value="#stat.index+1" />                          .  
                         <s:property value="tg" />  
                          <s:hidden name="da1" value="%{da}"/>
                        
                         </font>  
                     </td> 
                     <td height="36" width="640" align="left">  
                         <s:radio list='#{1:"对", 0:"错"}'  
                             name="answer[%{#stat.index}]" value="'answer'+#stat.index" theme="simple"/>  
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
                     <td width="440" height="46">  
                         <font color="blue">  
                         <s:property value="#stat.index+1" />                          .  
                         <s:property value="dttg" />  
                         </font>  
                     </td> 
                 </tr>
                 <tr> 
                     <td width="440" height="46">  
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
			
			
			
			<tr>
				<td>
					<input type="submit" name="foo" value="交卷">
				</td>
			</tr>

		</s:form>

	</body>
</html>
