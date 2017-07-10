<%@ page language="java"
	import="java.util.*,com.easysearching.lucene.beans.QueryResult,org.apache.lucene.document.Document,com.easysearching.configuration.*;"
	
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

	if (request.getAttribute("queryResults") == null||request.getAttribute("searchingTime")==null||request.getAttribute("totalPages")==null||request.getAttribute("queryString")==null) {
		return;
	}
	
	QueryResult queryResults = (QueryResult) request.getAttribute("queryResults");
	
	double searchingTime = (Double)request.getAttribute("searchingTime");
    
    //查询的结果集
	List<Document> documents = queryResults.getResultList();

	//查询的总结果
	int resultCount = queryResults.getResultCount();
	System.out.println("resultCount:"+resultCount);
	//查询的总页数
	int totalPages = Integer.parseInt(String.valueOf(request.getAttribute("totalPages")));
	System.out.println("totalPages:"+totalPages);
	//查询的字符串
	String queryString = String.valueOf(request.getAttribute("queryString"));
	System.out.println("queryString:"+queryString);
	//页数显示
	int pageCount = Integer.parseInt(String.valueOf(request.getAttribute("pageCount")));
	System.out.println("pageCount:"+pageCount);
	int currentPage=Integer.parseInt(String.valueOf(request.getAttribute("currentPage")));
	System.out.println("currentPage:"+currentPage);
	pageCount=totalPages;
	//if(totalPages>10 && pageCount<10)
	//{
	//   pageCount = 10;
	//}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>查询结果</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>

		<script type="text/javascript" src="/js/jquery.json-2.4.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
        <link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
            <link href="../../images/style.css" rel="stylesheet" type="text/css" />
           <script type="text/javascript" src="assets/skin/default/js/common.js?v=725"></script>
<script type="text/javascript" src="assets/skin/default/js/dialog.js"></script>
           	<link rel="stylesheet" type="text/css" href="assets/skin/default/css/base.css?v=606" />
<link rel="stylesheet" type="text/css" href="assets/skin/default/css/dialog.css" />
           
            <script type="text/javascript">
function subck(){
	var q = document.getElementById("kw").value;
	if(q=='' || q=='请输入关键字搜索网页'){return false;}else{return true;}
}
</script>
<script type="text/javascript">
$(function(){

  var id = {"q":$("#queryString").val(),"radio2":"1"};
 
   $.ajax({  
			    type:'post',      
			    url:'suggest2.action',  
			    data:id,  
			    cache:false,  
			    dataType:'json',  
			    success:function(data){
			   var arr=data.rows;
			  
			    $.each(arr, function(i,item){ 
			     
   $("#xiangguan").append("<li><a href='/Search2.html?queryString="+item+"'>"+item+"</a></li>");     
});  
			    }
			    })
})
</script>
		<script type="text/javascript">
		   function checkSubmit()
		   {
		      var content = document.getElementById("queryString");
		      if(""==content.value)
		      {
		         return false;
		      }
		      return true;
		   }
		   
		   function handlerNextPage()
		   {
		      var queryString = document.getElementById("queryString").value;
		      var pageCount = document.getElementById("nowpage").value;
		      pageCount = parseInt(pageCount)+1;
		      location.href="/Search2.html?queryString="+queryString+"&pageCount="+pageCount+"&currentPage="+pageCount;
		   }
		   
		   function handlerPrePage()
		   {
		
		       var queryString = document.getElementById("queryString").value;
		       var pageCount = document.getElementById("nowpage").value;
		       pageCount = parseInt(pageCount)-1;
		       location.href="/Search2.html?queryString="+queryString+"&pageCount="+pageCount+"&currentPage="+pageCount;
		   }
		   
		   window.onload=function()
		   {
		     var prePageDiv = document.getElementById("prePageDiv");
		     var nextPageDiv = document.getElementById("nextPageDiv");
		     var totalPages = document.getElementById("totalPages").value;
		     var pageCount = document.getElementById("pageCount").value;
		     if(totalPages>10&&pageCount<totalPages)
		     {
		         nextPageDiv.style.display="inline";
		     }
		     if(pageCount>=11)
		     {
		         prePageDiv.style.display="inline";
		     }
		   }
		</script>
		<script type="text/javascript">
	
		
		//跳到另外一个页面，打开要浏览的资料
	function openCK(viewPath,zlmc,i_id,keci,zlid){
	new Dialog({type:'iframe',title:'哈哈',value:'spdh/dzbj2.jsp?filename='+zlmc+"&path="+viewPath+"&zlid="+zlid}).show();
							
		
	}
	
		</script>
	</head>

	<body>
	
	<div id="header">
  <div class="con" style="margin-left:85px;" >
      
      <div class="searchbox">
       <form action="/Search.html" method="post" onsubmit="return checkSubmit();"><input align="middle" name="queryString" class="q" id="queryString" value="<%=queryString %>" maxlength="100" size="50" autocomplete="off" baiduSug="1" /><input name="re" type="hidden" value="1" /><input id="btn" style="background:#39a6df" class="btn" align="middle" value="搜索一下" type="submit" />
              </form>
      </div>
      
  </div>
</div><!--header-->
	<div id="hd_main">
<div id="res" class="res">
 <div id="resinfo">为您找到"<h1><%=queryString %></h1>"相关结果约<%=resultCount %>个&nbsp;(用时<span style="color:red"><%=searchingTime %></span>秒)</div><div id="result">	
 
 <% 
			for (Document document : documents) { 
			String viewpath=String.valueOf(document.get("viewPath"));
			System.out.println(viewpath);
			viewpath=viewpath.replace("\\", "\\\\");
		%>
 <div class="g">
 <h2><a   class="s" rel="nofollow" onclick="openCK('<%=viewpath %>','<%=document.get("ZName")%>','<%=document.get("i_id")%>','<%=document.get("keci")%>','<%=document.get("zlid")%>');"><%=document.get("name")%></a></h2>
 <div class="std"><%=document.get("content")%></div>
 <span class="a">发布者:<%=document.get("pubWriter")%></span>
 <span class="a">发布日期:<%=document.get("pubDate")%></span>
&nbsp;
					-&nbsp;<a style="font-size:11pt;color: #333333;" href='/FileDownload.html?filePath=<%=document.get("path")%>'>下载</a>
					&nbsp;
					-&nbsp;<span style="font-size:11pt;color: #333333;" href='#' onclick="openCK('<%=viewpath %>','<%=document.get("ZName")%>','<%=document.get("i_id")%>','<%=document.get("keci")%>','<%=document.get("zlid")%>');">预览</span>
				
 </div>    
		<%
			}
		%>	
</div> 

<div class="cl"></div>
   
   <div id="sopage">
	 
		<p>
		<center>
		    <%
			
			int num=0;
			
			   if(pageCount-currentPage>10){
			   num=10;
			   }else{
			   num=pageCount-currentPage;
			   }
			
			
			int isShowShang=0;
			int isShowXia=0;
			int nowpage=currentPage;
			for(int i=1;i<=num;i++){
			nowpage=nowpage+1; 
			if(nowpage==1){
			isShowShang=1;
			}
			if(nowpage==pageCount){
			isShowXia=1;
			}
			}
			
			if(isShowShang!=1){
			%>
			<div id="prePageDiv"><a href='#' class="n" style='font-size:10pt;' id='prePage' onclick='handlerPrePage()'>上一页</a></div>&nbsp;&nbsp;
			
			<% 
			}
			
			for(int i=1;i<=num;i++){
			currentPage=currentPage+1;
			if(i==1){
			%>
			<a class="this" style="color:#000" href="/Search2.html?queryString=<%=queryString %>&currentPage=<%=currentPage %>&pageCount=<%=currentPage %>"><%=currentPage %></a>
			<input id="nowpage" value=<%=currentPage %> hidden>
			<%
			} else{
			%>
			
			<a class="this" href="/Search2.html?queryString=<%=queryString %>&currentPage=<%=currentPage %>&pageCount=<%=currentPage %>"><%=currentPage %></a>
			
			&nbsp;&nbsp;
			<%}} 
			if(isShowXia!=1){
			%>
			
			
			<div id="nextPageDiv"><a href='#' class="n" style='font-size:10pt;' id='nextPage' onclick='handlerNextPage()'>下一页</a></div>
		<%
		}
		 %>
		</center>
		</p>
		
		</div>
		<div id="zxsp" class="easyui-window" closed="true" title="在线资源" icon="icon-edit" maximizable="true"  minimizable="true" style="padding:0px;width:700px;height:540px;overflow-x:hidden;overflow-y:hidden;">
	  
	      <iframe name="CFrame" id="CFrame" frameborder="0" style="padding:0px;width:100%;height:550px;"></iframe>
		</div>	
   <div class="cl10"></div>
   <!--a href="http://www.somao123.com/" target="_blank"><img src="http://demo.somao123.com/static/images/gg54080.jpg" alt="搜索源码" width="540" height="80" /></a>
<div class="cl10"></div-->

   <div class="xglist">
   <h4>相关搜索</h4><ul id="xiangguan">
  
   
 
   </ul><div class="cl10"></div></div></div><!--res-->

<div id="sidebar">

<div class="cl10"></div><div class="rankbox">
<div class="title">搜索排行榜</div>
<ul class="ranklist">
        <li><span class="num top1">1</span><a href="">C语言常量</a></li>
                <li><span class="num top2">2</span><a href="">函数应用</a></li>
                <li><span class="num top3">3</span><a href="">数组指针</a></li>
                <li><span class="num">4</span><a href="">For循环</a></li>
                <li><span class="num">5</span><a href="">标识符</a></li>
                <li><span class="num">6</span><a href="">算法结构</a></li>
                <li><span class="num">7</span><a href="">C语言发展</a></li>
                <li><span class="num">8</span><a href="">Java应用</a></li>
                <li><span class="num">9</span><a href="">基本语法</a></li>
                <li><span class="num">10</span><a href="">后台框架</a></li>
        
</ul>
</div><!--rankbox-->

<div class="cl10"></div><div class="cl10"></div>
<div class="rankbox">
<div class="title">历史搜索</div>
<ul class="ranklist">
<li><span class="arrow"></span><a href="http://demo.somao123.com/sm_%E5%B8%82%E5%9C%BA%E8%90%A5%E9%94%80%E8%AF%BE%E7%A8%8B.html">Java基础知识
</div><!--rankbox-->
</div><!--sidebar-->

</div><!--main-->	
	</body>
</html>