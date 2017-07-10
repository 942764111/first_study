<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主界面</title>
	<link href="css/wjh_main.css" rel="stylesheet" />
	 <link href="css/maintop.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/Htmlboxjs/htmlbox.undoredomanager.js"></script>
	<script type="text/javascript" src="js/Htmlboxjs/htmlbox.min.js"></script>
	
<script type="text/javascript">

    var easts=0;
    var wests=0;//0代表没有缩进
	$(function(){
			var p = $('body').layout('panel','west').panel({
				onCollapse:function(){
				    as1("iframe");
				  wests=1;
				}			
			});
			
			var p1 = $('body').layout('panel','west').panel({
					onExpand:function(){
					if(easts==0)
					{
					as("iframe");
					}
					else
					{
					as2("iframe");
					}
					 wests=0;
				}
			});
			var p2 = $('body').layout('panel','east').panel({
					onCollapse:function(){
					if(wests==0)
					{
					as2("iframe");
					}
					else
					{
					as3("iframe");
					}
					easts=1;
				}
			});
			
			var p3 = $('body').layout('panel','east').panel({
					onExpand:function(){
					if(wests==0)
					{
					as("iframe");
					}
					else
					{
					as1("iframe");
					}
					easts=0;
				}
			});

			iconfunc();
			

			
		});
	//var s="test";
	
	function unloadpage(){
	   /*if(s=="test"){
	     alert("关闭窗口！");
	     jQuery.post("quitLogin");
	   }else{
	     alert("刷新窗口！");
	     s="test";
	   }*/
       jQuery.post("quitLogin");
	}
	function onloadpage(){
	   as("iframe");	
	   tiaozheng("content");
	}
	
	function bfunload(){
        /*var n = window.event.screenX - window.screenLeft;   
        var m =document.documentElement.scrollWidth-20;
       var b = n > m;

       if(b && window.event.clientY < 0 || window.event.altKey||window.event.ctrlKey)   
       {   
         alert("是关闭");   
       }else{
         if(window.clientX<=0&&window.clientY<=0){
               alert("是关闭选项卡"); 
           }else{
              s="no";
              alert("是刷新");
           }
        
       }*/
         
	}
	
	
	function switchMenu(value) {
         
		var oItem = document.getElementById('menu1').getElementsByTagName("li");
		var j = 0;
		for ( var i = 0; i < oItem.length; i++) {
			var x = oItem[i];
			if (x == value)
				j = i;
			x.className = "";
		}
		value.className = "Selected";
		var Items = document.getElementById("right_item").getElementsByTagName(
				"ul");
		for ( var i = 0; i < Items.length; i++) {
			var Item = Items[i];
			Item.style.display = "none";
			if (Item.id == 'Item' + j)
				Item.style.display = "block";
		}

	}
	
	
	$(document).ready(function(){
			
		})
		function addmenu(){
	
			var header = $('.layout-expand .layout-button-down').parent().parent();
			var menu = $('<div style="position:absolute;left:0;top:0;background:#fafafa;"></div>').appendTo(header);
			var btn = $('<a href="#">test</a>').appendTo(menu);
			btn.menubutton({
				menu:'#mymenu'
			});
		}
	
		function show2(){
	
			$.messager.show({
				title:'离线信息',
				msg:'Message will be closed after 5 seconds.',
				timeout:5000,
				showType:'slide'
			});
		}
function tiaozheng(content){
	var zheight=document.body.clientHeight;
	var h = (zheight-80-85)
	$("#".concat(content)).height(h); 
}



 function as(iframe)
 {
 var zwidth=document.body.clientWidth;
 var zheight=document.body.clientHeight;
 var we=205/zwidth;
 var h1=110/zheight;
 var w=zwidth*(1-we);
 var h=zheight*(1-h1);
 var iframe1 = $("#".concat(iframe)); 
 iframe1.width(w);
 iframe1.height(h); 

 }
 
  function as1(iframe)//west
 {
  var zwidth=document.body.clientWidth;
 var zheight=document.body.clientHeight;
 var we=22/zwidth;
 var h1=110/zheight;
 var w=zwidth*(1-we);
 var h=zheight*(1-h1);
 var iframe1 = $("#".concat(iframe)); 
 iframe1.width(w);
 iframe1.height(h); 
 }
 
 function as2(iframe)//east
 {
  var zwidth=document.body.clientWidth;
 var zheight=document.body.clientHeight;
 var e=22/zwidth;
 var we=205/zwidth;
 var h1=110/zheight;
 var w=zwidth*(1-we-e);
  var h=zheight*(1-h1);
 var iframe1 = $("#".concat(iframe)); 
 iframe1.width(w);
 iframe1.height(h); 
 }
 
   function as3(iframe)//east west
 {
  var zwidth=document.body.clientWidth;
 var zheight=document.body.clientHeight;
var z=50/zwidth;
 var h1=110/zheight;
 var w=zwidth*(1-z);
 var h=zheight*(1-h1);
 var iframe1 = $("#".concat(iframe)); 
 iframe1.width(w);
 iframe1.height(h); 
 
 }

var resizeTimer = null;  

$(window).resize(function() {  

    if (resizeTimer) clearTimeout(resizeTimer);  

    resizeTimer = setTimeout("sizechange()", 500);  

}); 


function sizechange()
{
if(wests==0&&easts==0)
{
as("iframe");
}
else if(wests==1&&easts==0)
{
as1("iframe");
}
else if(wests==0&&easts==1)
{
as2("iframe");
}
else if(wests==1&&easts==1)
{
as3("iframe");
}
}
//点击左侧菜单中的功能名称，出现图标
function clickfunction(obj){
	var oItem = document.getElementById("right_item").getElementsByTagName("a"); 
    for(var i=0; i<oItem.length; i++){
        var x = oItem[i];    
        x.className = "";
    }
	obj.className="Selected";
}
//设置左侧菜单的分类图标
function iconfunc(){
	var oItem = document.getElementById("menu1").getElementsByTagName("img"); 
    for(var i=0; i<oItem.length; i++){
        var temp=oItem[i].name;
        
        if(temp=="工作"){
      	  oItem[i].src='img/index/picture/login-icon.gif';
          }else if(temp=="教育"){
          	     oItem[i].src='img/index/picture/Book.gif';
          	   }else if(temp=="笔记"){
          		      oItem[i].src='img/index/picture/33.gif';
              	   }else if(temp=="资源"){
              		   oItem[i].src='img/index/picture/Download.gif';
                  	   }else{
                  		   oItem[i].src='img/index/picture/wap.gif';
                      	   }
    }
}
		
	</script>

</head>
<body class="easyui-layout" onunload="unloadpage()" onload="onloadpage()" onbeforeunload="bfunload()">
		<div region="north"  border="false" style="height:90px;overflow:hidden;">
             <div id="pic">
               <ul>
			    	<li id="pic1"><a href="userAction.action" target="iframe"><img src="img/index/picture/userInfo.gif"></a></li>
			        <li id="pic3"><a href="register.action"><img src="img/index/picture/join.gif"></a></li>
			        <li id="pic2"><a href="quitLogin.action"><img src="img/index/picture/quit.gif"></a></li>
			    </ul>
              </div> 
           
             
        </div>
		<div region="south" split="false" style="height:20px;background:url(img/index/picture/2.jpg);">
				<p align="center">版权   河北北方学院科研室</p>
		</div>




		
		<div id="westss" region="west" title="用户&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;<%=session.getAttribute("uid")%>" split="true" style="width:205px;overflow:hidden;">
			
			<div id="left">
				<div id="test" class="menu_top" ></div>
				<div id="content" class="menu_center">
					<ul id="menu1">
					<s:iterator value="mds" id="mds" status="st">
						<s:if test="#st.isFirst()">
						<li onclick="switchMenu(this)"; class="Selected">
								<img src="" name="<s:property value="#mds.mclassname" />"/>
								<s:property value="#mds.mclassname" />
							</li>
						</s:if>
						<s:else>
							<li onclick="switchMenu(this)";>
								<img src="" name="<s:property value="#mds.mclassname" />"/>
								<s:property value="#mds.mclassname" />
							</li>
							</s:else>
						</s:iterator>
					</ul>
				
			<div id="right_item">
				<s:iterator value="mds" id="mds" status="st">
							<s:iterator value="als" id="als">
								<s:if test="#als.moduleclass.equals(#mds.mclassname)">
								<s:if test="#st.isFirst()">
						        	<ul id="Item${st.index}">
						        	   	<li id="now11">
										    <img src="img/index/picture/tb.gif" />&nbsp;&nbsp;${modulename}
											
										</li>
										<s:iterator value="functionname">
											<li>
											    
												<a href="${actionname}"
													target="iframe" title="${functionname}" onclick="clickfunction(this);" class="" ><s:property 
														value="functionname" />
												</a>
											</li>
										</s:iterator>
									</ul></s:if>
								<s:else>
									<ul id="Item${st.index}" style="display: none;">
										<li id="now11">
										    <img src="img/index/picture/tb.gif" />&nbsp;&nbsp;${modulename}
											
										</li>
										<s:iterator value="functionname">
											<li>
											    
												<a href="${actionname}"
													target="iframe" title="${functionname}" onclick="clickfunction(this);" class="" ><s:property
														value="functionname" />
												</a>
											</li>
										</s:iterator>
									</ul>
									</s:else>
								</s:if>
							</s:iterator>
						</s:iterator>
			</div>
			</div>
			<div id="test1" class="menu_bottom"></div>
			</div>
			
		</div>
		
		
		
		
		
		<div region="center"   id="center" border="false"  style="overflow-x:hidden;overflow-y:hidden;">
		<iframe src="index.jsp" id="iframe" name="iframe" frameborder="0" ></iframe>
		</div>
		<!--  <form action="quitLogin" name="myform" id="myform"></form>-->
</body>
</html>