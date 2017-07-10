<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jQuery EasyUI</title>

	</head>
  
  <body>
  	<s:if test="x==2">
	  	<center>
	  		您已提交答案，或您还没有做题！！！
	  	</center>
  	</s:if>
  	<s:else>
  		<p>你的成绩是：<s:property value="score"/>分。</p>
  		<p>你的正确率是：<s:property value="zql"/>%</p>
  		<%List list=(List)request.getAttribute("zsdmc2");%>
		<%List list1=(List)request.getAttribute("zsdz2");%>
		<%for(int i=0;i<list.size();i++){ %>
		<p>知识点  "<%=list.get(i) %>"  的正确率是：<%=list1.get(i) %>%</p>
		<%} %> 
	</s:else>
  </body>
</html>
