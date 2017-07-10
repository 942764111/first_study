<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
            + request.getServerName() + ":" + request.getServerPort()  
            + path + "/";  
     session.setAttribute("th",0);
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>测试试卷</title>
		<link rel="stylesheet" type="text/css"href="js/ui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
		
		
		<script type="text/javascript">
		 var dcqk= new Array();
		 var wjmarray=new Array();
		 var datitihao=new Array();
		var vartime, h, m, s, hstr, mstr, sstr;  
		var xtime = 0.1* 60 * 60;  
		var time;
		function takeCount() {  
		    if (xtime >= 0) {  
		        h = parseInt(xtime / 3600);  
		        m = parseInt((xtime % 3600) / 60);  
		        s = (xtime % 3600) % 60;  
		        hstr = h < 10 ? ("0" + h) : h;  
		        mstr = m < 10 ? ("0" + m) : m;  
		        sstr = s < 10 ? ("0" + s) : s  
		        vartime = hstr + ":" + mstr + ":" + sstr;  
		        document.getElementById("timer").value = vartime;  
		        xtime--;  
		        time=setTimeout("timeclock();", 1000);  //隔一秒就调用timeclock（），只执行一次
		    } else { 
			    if(xtime!=-10){
				    
				    	if(time!=0){
					    	clearTimeout(time);
					    	 $.messager.alert('提示','时间到！系统自动交卷！','info');
					    	ss();
					    	time=0;
						    }
				    } else{
				    	if(time!=0){
					    	clearTimeout(time);
					    	time=0;
						    }
					    }
		    		}  
				}  

 $(function(){ 
			setInterval("takeCount();",1000);//每隔一秒执行一次takeCount()
			var values = document.getElementsByName("dtth");
			for(var j=0;j<values.length;j++){
				$("#"+j).append('<a class="easyui-linkbutton" href="javascript:void(0)" onclick="openwj('+j+')">点此打开文件,以对此题进行解答</a>');
				}
			});


	
		 function openwj(hm)
		    {
			    var xiazaitihao=document.getElementsByName("dtth")[hm].value;
			    var tihao={"tihao":xiazaitihao};
			    var wjm;
			    var k=0;	
				for(;k<datitihao.length;k++)
				{
					if(datitihao[k]==xiazaitihao)
				    {
					    wjm=wjmarray[k];
					    break;
				    }
				}
				if(k>=datitihao.length)
				{
			 $.ajax({
					type:"POST",
			    	url:"getwjmc",
			    	data:tihao,
			    	dataType:'json',
			    	success:function callback(r){ 
				    	excelpj.serverurl='<%=basePath%>download?tihao='+xiazaitihao;
			           var clienturls=r.filename;
			           excelpj.tihao=xiazaitihao;
			           excelpj.clienturl="D:\\myexcel\\"+xiazaitihao+"\\"+clienturls;
			           excelpj.wjmc="D:\\myexcel\\"+xiazaitihao+"\\"+clienturls;
			           wjm="D:\\myexcel\\"+xiazaitihao+"\\"+r.filename; 
			       
			           var xz=excelpj.Downloadwj();
			           wjmarray.push(wjm);
			           datitihao.push(xiazaitihao);     
			        	 excelpj.wjmc=wjm;
					   	 var exist=excelpj.openwj();
					   	 if(exist!=1)
					   	 {
					   		   $.messager.alert('错误','文件不存在是否改变文件名称,删除,移动等操作?','info');
					   	 }
			    	}
					});
				}
				else
				{ excelpj.wjmc=wjm;
			   	 var exist=excelpj.openwj();
					
				}
		 
		    }	


			function ss()
			{    
				 var wjm;
				var values = document.getElementsByName("dtth");
				if(values=="undefined")
				{
					//alert(values);
					return;
				}
				for(var j=0;j<values.length;)
				{
					var k=0;					  
					for(;k<datitihao.length;k++)
					{
						if(datitihao[k]==values[j].value)
					    {
						    wjm=wjmarray[k];
						    break;
					    }
					}
					if(k>=datitihao.length)
					{
						var z=j;
						var noopen={"noopen":1,"tihao":values[z].value,"sjbh":document.getElementById("sjbh").value};
						j=j+1;
					 	$.ajax({
					   		type:"POST",
					       	url:"<%=basePath%>gettf",
					       	data:noopen, 	
					       	dataType:'json',
					    	async:false,
					       	success:function callback(r){ 
					       	dcqk.push(r.pf);       
				       	}
					 	});
					}
					else
					{
						var z=j;
					   	var tihao=values[z].value;
						j=j+1;
		    	var selected={"tihao":values[z].value}; 
		        var defen= new Array();
		        var id;      
		      $.ajax({
		   		type:"POST",
		   	   url:"<%=basePath%>getpfda", 
		   		data:selected,
		   		dataType:'json',
		   		async:false,
		   		success:function callback(r){ 
			   		
		            id=r.id;
		   		for(var i=0;i<id;i++)
		   		{
		   		   var answers=r.cztdalist[i].sheetname+"|"+r.cztdalist[i].wz+"|"+r.cztdalist[i].da+"|"+r.cztdalist[i].carea+"|"+r.cztdalist[i].cda;
		   		   var fen=r.cztdalist[i].bufz;
		   		   var bh=r.cztdalist[i].yzsx;
		   		   var fen1=0;
		   		   excelpj.fenzhi=fen;
		   		   excelpj.daan=answers;
		   		   excelpj.bianhao=bh;
		   		   excelpj.wjmc=wjm; 
		   		   var fen1=excelpj.pf(); 
		   		   defen[i]=fen1;   
		   		}
		   	var df="";
		   	for(var i=0;i<id-1;i++)
		   	{
		   	   df=df+"defen["+i+"]="+defen[i]+"&";
		   	}
		   	var x=id-1;
		   	df=df+"defen["+x+"]="+defen[id-1]+"&tihao="+tihao+"&sjbh="+document.getElementById("sjbh").value;
		   	$.ajax({
		   		type:"POST",
		       	url:"gettf",
		       	data:df,
		       	dataType:'json',
		       	async:false,
		       	success:function callback(r){ 
			       	dcqk.push(r.pf);
			       
		       	}
		   		});
		     	}
		   	}); 
				}
				}
				setTimeout("tj();",0);
			}



			 function tj(){
			//	 alert("ss");
				 xtime=-10;
					  jQuery.ajaxSettings.traditional = true;   //关键
					 
					  var values = document.getElementsByName("dtth");
					  if(values=="undefined")
					  {
						  $.ajax({
						   		type:"POST",
						       	url:"fx",
						       	data:$('#form1').serialize(),//这样做后台fx.action的json数据就不会返回到前台了，前台就不会出现让下载返回结果的对话框了
						       	success:function(){
					       			var zmc="";
						       		var params = {"zmc":zmc};
						    		var url="/dhxs";
						    		$('#jg').load(url,params);
						    		$('#jg').window('open');
						       		}
						   		});
					  }
					  else
					  {
						  $.ajax({
						   		type:"POST",
						       	url:"cztfxczt",
						       	data:{"dcqk":dcqk},
						       	dataType:'json', 
						       	success:function (json){ 
						       		//document.getElementById("form1").submit(); 
						       		//$("#form1").submit();
						       		if(json.tip=="yc"){
						       		 $.messager.alert('提示','您已测过本套试卷！！！','info');
							       		}else{
							       			$.ajax({
										   		type:"POST",
										       	url:"fx",
										       	data:$('#form1').serialize(),//这样做后台fx.action的json数据就不会返回到前台了，前台就不会出现让下载返回结果的对话框了
										       	success:function(){
									       			var zmc="";
										       		var params = {"zmc":zmc};
										    		var url="/dhxs";
										    		$('#jg').load(url,params);
										    		$('#jg').window('open');
										       		}
										   		});
								       		}
						       		}
						   		});
					  }
						  $('#tj').linkbutton('disable');
					}

			 $(function(){ 
				  $('#jg').window({
		       		   onClose:function(){
						  location.href="finishtest";
						  window.event.returnValue = false;
		       	   		}
	       	   });
			 });
</script>
</head>
<body class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">

 	<object  id="excelpj"  classid="clsid:7F33BAF1-BDBB-4A3F-85C9-C14F53893590"  height="0" width="0"   codebase="../tables/excelpfxt.cab#version=1,0,0,3"  > </object>
	<div region="north" id="showTime" style="display: block" align="center"  style="width:900px;padding:0px;height:30px;">
		剩余时间: <input type="text" id="timer" name="txt" size="4" readonly="readonly" style="overflow:hidden;border:red;"/>  
    </div>
	<br><br><br>
		
	<div  region="center"  style="width:900px;padding:30px;">
	 	<center>
			<font color="#88B04E" size="4">  
                  <s:property value="sjname" />
            </font> 
		</center>
		<s:form action="fx" method="post" id="form1">
		<s:if test="ckxz1.size!=0">
			<td>
				<font color="#88B0E4" size="4">单选题</font>
			</td>
			<td><s:hidden id="sjbh"  value="%{sjbh1}"></s:hidden></td>
			<s:iterator value="ckxz1" var="ckxz1" status="stat1" id="ckxz1">
					<table>
						<tr>
							<td height="36">
								<font color="green"> 
									<s:property value="#stat1.index+1" />									.
									<s:property value="tg" /> 
									<s:hidden name="da1" value="%{da}" />
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
									<s:hidden name="da" value="%{da}" /> 
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
		<s:if test="ckpd2.size!=0">
			<td>
				<font color="#88B0E4" size="4">判断题</font>
			</td>
			<s:iterator value="ckpd2" var="ckpd2" status="stat2" id="ckpd2">
				<table>
					<tr>
						<td height="36">
							<font color="green"> 
								<s:property value="#stat2.index+1" />								. 
								<s:property value="tg" /> 
								<s:hidden name="da2" value="%{da}" /> 
							</font>
						</td>
					</tr>
					<tr>
						<td height="36" align="left">
							<s:radio list='#{"1":"对", "0":"错"}' name="answer2[%{#stat2.index}]"	value="" theme="simple" />
						</td>
					</tr>
				</table>
				<hr />
			</s:iterator>
		</s:if>
		<s:if test="czt1.size!=0">
			<td>
				<font color="#88B0E4" size="4">操作题</font>
			</td>
			<s:iterator value="#request.czt1" var="czt1" status="stat" id="czt1">
					<table>
						 <tr> 
		                     <td height="36">  
		                         <font color="green">  
		                         <s:property value="#stat.index+1" />                          .  
		                         <s:property value="dttg" />  
		                         <s:hidden id="dtth"    name="dtth" value="%{dtth}"/>   
		                         </font>  
		                     </td> 
		                     <td><s:div id="%{#stat.index}" /></td>
		                 </tr>
		                 <tr> 
		                     <td height="46">  
			                     <%List list=(List)request.getAttribute("xtth");%>
								<%List list1=(List)request.getAttribute("xttg");%>
								<%for(int i=0;i<list.size();i++){ %>
								<p style="">第<%=list.get(i) %>问：<%=list1.get(i) %> </p>
								<%} %> 
		                     </td> 
		                 </tr>
					</table>
					<hr />
				</s:iterator>
			</s:if>
				<tr align="center">
					<td>
						<a id="tj" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="ss()">交	    卷</a>
					</td>
				</tr>
			</s:form>
			<div id="jg" class="easyui-window"  closed="true" title="结果" style="padding:0px;width:336px;height:326px;"  maximizable="false" minimizable="false" collapsible="false"></div>
		</div>
	</body>
</html>
