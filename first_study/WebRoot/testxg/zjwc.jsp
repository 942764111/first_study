<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>组卷完成 </title>
		<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
		
	<script type="text/javascript">
		var valuex = document.getElementsByName("xth");
		var valuedx = document.getElementsByName("dxth");
		var valuepd = document.getElementsByName("pdth");
		var values = document.getElementsByName("dtth"); 
		var pdt=0;//用以存放旧的判断题号
		var xt=0;//用以存放旧的单选题号
		var dxt=0;//用以存放旧的多选题号

		$(function(){ 
				for(var i=0;i<valuex.length;i++){
					$("#"+i).append('<a href="javascript:void(0)" onclick="ghxzt('+i+')">点此更换本题</a>');
					}
				
				for(var x=0;x<valuedx.length;x++){
					var a=x+valuex.length;
					$("#"+a).append('<a href="javascript:void(0)" onclick="ghdxt('+x+')">点此更换本题</a>');
					}
				
				for(var n=0;n<valuepd.length;n++){
					var b=n+valuex.length+valuedx.length;
					$("#"+b).append('<a href="javascript:void(0)" onclick="ghpdt('+n+')">点此更换本题</a>');
					}
				
				for(var j=0;j<values.length;j++){
					var c=j+valuex.length+valuedx.length+valuepd.length;
					$("#"+c).append('<a href="javascript:void(0)" onclick="ghczt('+j+')">点此更换本题</a>');
					}
				});
			
		//*********************更换操作题里的某一道题********************************
		function ghczt(czt){
			 var ghth=document.getElementsByName("dtth")[czt].value;
			 $.messager.confirm('提示','确认要更换这道操作题么?',function(id){
		    		if(id){
		    			$.ajax({
					   		type:"POST",
					       	url:"ghczttm",
					       	data:{"ghth":ghth},
					       	dataType:'json', 
					       	success:function (json){ 
						       	if(json.tip=="myt"){
						       		$.messager.confirm('确认','已没有题可以更换！',function(r){});
							       	}else{
							       		$.messager.alert('确认','更换成功!!!','info',function(){
							       			location.href='xztnr';
						            	});
								    }
					       	}
					   	});
			 		}
		   		});
			}		
		//*********************更换单选题里的某一道题********************************
		//获得单选题列表
		function ghxzt(x){
			 var ghth=document.getElementsByName("xth")[x].value;
			 xt=ghth;
			 $('#xz1').datagrid({
					nowrap: true,
					striped: true,
					fitColumns:true,
					fit:true,	
					singleSelect:true,	
					loadMsg:'加载数据,请等待...',
					url:'changexz1.action',
					columns:[[
		                {title:'题号',field:'th',width:'30',align:'center'},
		                {field:'tg',title:'题干',width:'200',align:'left'},
		                {field:'xx1',title:'选项一',width:'60',align:'left'},
		                {field:'xx2',title:'选项二',width:'60',align:'left'},
		                {field:'xx3',title:'选项三',width:'60',align:'left'},
		                {field:'xx4',title:'选项四',width:'60',align:'left'},
		                {field:'da',title:'答案',width:'30',align:'center'}
					]],
					pagination:true,
					rownumbers:true,
					toolbar:[{
						id:'btnadd',
						text:'确定更换',
						iconCls:'icon-ok',
						handler:function(){
	                          qdxz1();
						}
					},'-',{
	    				text:'查询',
	    				iconCls:'icon-search',
	    				handler:function(){
	    					$('#cxxz1').form('clear');
	    					$('#queryxz1').window('open');
	    					 document.getElementById("queryxz1").style.visibility="visible";
	    				}
	    			}]
				});
	        	$('#changgexz1').window('open');
	        	 document.getElementById("changgexz1").style.visibility="visible";
			}
		
		//把新的题号和旧的题号传给后台	
		function qdxz1(){
			var selected = $('#xz1').datagrid('getSelected');
			if(selected){
				var xxt=selected.th;//新题号
				 $.messager.confirm('提示','确认要更换这道单选题么?',function(id){
			    if(id){
			    	if(selected.tg==""||(selected.xx1==""&&selected.xx2==""&&selected.xx3==""&&selected.xx4=="")||selected.da==""){
			    		$.messager.alert('警告','本题不完整，不能用来出卷,请选别的题！','warning');
				    	}else{
				    		$.ajax({
						   		type:"POST",
						       	url:"ghdanxtm",
						       	data:{"ghth":xt,"xinth":xxt},
						       	dataType:'json', 
						       	success:function (json){ 
							       	if(json.tip=="myt"){
							       		$.messager.confirm('确认','试卷中已包含这道题！！',function(r){});
								       	}else{
								       		$('#changgexz1').window('close');
								       		$.messager.alert('确认','更换成功!!!','info',function(){
								       			location.href='xztnr';
							            	});
									       	}
						       		}
						   		});
					    	}
			    		}
			   		});
				}else{$.messager.alert('警告','请选择一行数据','warning');}
			
		}


		//查询单选题
		function queryxz1(){
	        var queryParams = $('#xz1').datagrid('options').queryParams;
	        var queryType =queryParams.queryType="tg";
	        var queryWord=queryParams.queryWord= $('#name1').val();
	        if(""!=queryWord){
					$.ajax({
						type:"POST",
						url:"ghqueryxzjy1",
						data:{"queryType":queryType,"queryWord":queryWord},
						cache: false,
						success:function(data){
							
									if(data.a == 1){
										$('#xz1').datagrid({
						    	            url:'ghqueryxz1',
						    	            columns:[[
						  			                {title:'题号',field:'th',width:'30',align:'center'},
									                {field:'tg',title:'题干',width:'200',align:'left'},
									                {field:'xx1',title:'选项一',width:'60',align:'left'},
									                {field:'xx2',title:'选项二',width:'60',align:'left'},
									                {field:'xx3',title:'选项三',width:'60',align:'left'},
									                {field:'xx4',title:'选项四',width:'60',align:'left'},
									                {field:'da',title:'答案',width:'30',align:'center'}
						  						]]
						    	        });
						    	        $('#queryxz1').window('close');
									}else if(data.a == 0){
										$.messager.alert('提示信息','查询结果为空!!!','info',function(){
						            	});
								}
							}
						});
	        			}else{$.messager.alert('提示','查询条件不能为空!');}
	   				 }	


		//*********************更换多选题里的某一道题********************************
		
		//查出多选题列表
		function ghdxt(dx){
			 var ghth=document.getElementsByName("dxth")[dx].value;
			 dxt=ghth;
			 $('#xz2').datagrid({
					nowrap: true,
					striped: true,
					fitColumns:true,
					fit:true,	
					singleSelect:true,	
					loadMsg:'加载数据,请等待...',
					url:'changexz2.action',
					columns:[[
		                {title:'题号',field:'th',width:'30',align:'center'},
		                {field:'tg',title:'题干',width:'200',align:'left'},
		                {field:'xx1',title:'选项一',width:'60',align:'left'},
		                {field:'xx2',title:'选项二',width:'60',align:'left'},
		                {field:'xx3',title:'选项三',width:'60',align:'left'},
		                {field:'xx4',title:'选项四',width:'60',align:'left'},
		                {field:'da',title:'答案',width:'30',align:'center'}
					]],
					pagination:true,
					rownumbers:true,
					toolbar:[{
						id:'btnadd',
						text:'确定更换',
						iconCls:'icon-ok',
						handler:function(){
	                          qdxz2();
						}
					},'-',{
	    				text:'查询',
	    				iconCls:'icon-search',
	    				handler:function(){
	    					$('#cxxz2').form('clear');
	    					$('#queryxz2').window('open');
	    					 document.getElementById("queryxz2").style.visibility="visible";
	    				}
	    			}]
				});
	        	$('#changgexz2').window('open');
	        	 document.getElementById("changgexz2").style.visibility="visible";
			}


		//把新的题号和旧的题号传给后台	
		function qdxz2(){
			var selected = $('#xz2').datagrid('getSelected');
			if(selected){
				var dxxt=selected.th;//新题号
				 $.messager.confirm('提示','确认要更换这道多选题么?',function(id){
			    if(id){
			    	if(selected.tg==""||(selected.xx1==""&&selected.xx2==""&&selected.xx3==""&&selected.xx4=="")||selected.da==""){
			    		$.messager.alert('警告','本题不完整，不能用来出卷,请选别的题！','warning');
				    	}else{
				    		$.ajax({
						   		type:"POST",
						       	url:"ghduoxtm",
						       	data:{"ghth":dxt,"xinth":dxxt},
						       	dataType:'json', 
						       	success:function (json){ 
							       	if(json.tip=="myt"){
							       		$.messager.confirm('确认','试卷中已包含这道题！！',function(r){});
								       	}else{
								       		$('#changgexz2').window('close');
								       		$.messager.alert('确认','更换成功!!!','info',function(){
								       			location.href='xztnr';
							            	});
									       	}
						       		}
						   		});
					    	}
			    		}
			   		});
				}else{$.messager.alert('警告','请选择一行数据','warning');}
			
		}

		//查询多选题
		function queryxz2(){
	        var queryParams = $('#xz2').datagrid('options').queryParams;
	        var queryType =queryParams.queryType="tg";
	        var queryWord=queryParams.queryWord= $('#name2').val();
	        if(queryWord!=""){
					$.ajax({
						type:"POST",
						url:"ghqueryxzjy2.action",
						data:{"queryType":queryType,"queryWord":queryWord},
						cache: false,
						success:function(data){
									if(data.a == 1){
										$('#xz2').datagrid({
						    	            url:'ghqueryxz2',
						    	            columns:[[
						  			                {title:'题号',field:'th',width:'30',align:'center'},
									                {field:'tg',title:'题干',width:'200',align:'left'},
									                {field:'xx1',title:'选项一',width:'60',align:'left'},
									                {field:'xx2',title:'选项二',width:'60',align:'left'},
									                {field:'xx3',title:'选项三',width:'60',align:'left'},
									                {field:'xx4',title:'选项四',width:'60',align:'left'},
									                {field:'da',title:'答案',width:'30',align:'center'}
						  						]]
						    	        });
						    	        $('#queryxz2').window('close');
									}else if(data.a == 0){
										$.messager.alert('提示信息','查询结果为空!!!','info',function(){
						            	});
								}
							}
						});
	        			}else{$.messager.alert('提示','查询条件不能为空!');}
	   				 }	
		
		
//****************更换判断题里面的某一道题*********************		
		//列出判断题，选择想要的题
		function ghpdt(pd){
			 	var ghth=document.getElementsByName("pdth")[pd].value;
				pdt=ghth;
	        	$('#pd').datagrid({
					nowrap: true,
					striped: true,
					fitColumns:true,
					fit:true,	
					singleSelect:true,	
					loadMsg:'加载数据,请等待...',
					url:'changepd.action',
					columns:[[
		                {title:'题号',field:'th',width:'100',align:'center'},
		                {field:'tg',title:'题干',width:'300',align:'left'},
		                {field:'da',title:'答案',width:'100',align:'center'}
					]],
					pagination:true,
					rownumbers:true,
					toolbar:[{
						id:'btnadd',
						text:'确定更换',
						iconCls:'icon-ok',
						handler:function(){
	                          qdpd();
						}
					},'-',{
	    				text:'查询',
	    				iconCls:'icon-search',
	    				handler:function(){
	    					$('#cx').form('clear');
	    					$('#query').window('open');
	    					 document.getElementById("query").style.visibility="visible";
	    				}
	    			}]
				});
	        	$('#changgepd').window('open');
	        	 document.getElementById("changgepd").style.visibility="visible";
		}	        	
	//把新的题号和旧的题号传给后台	
	function qdpd(){
		var selected = $('#pd').datagrid('getSelected');
		if(selected){
			var xpdt=selected.th;//新题号
			$.messager.confirm('提示','确认要更换这道判断题么?',function(id){
		    if(id){
		    	if(selected.tg==""||(selected.da!=0&&selected.da!=1)){
		    		$.messager.alert('警告','本题不完整，不能用来出卷,请选别的题！','warning');
			    	}else{
			    		$.ajax({
						  	type:"POST",
						     	url:"ghpdtm",
						      	data:{"ghth":pdt,"xinth":xpdt},
						      	dataType:'json', 
						      	success:function (json){ 
							      	if(json.tip=="yy"){
							      		$.messager.confirm('确认','试卷中已包含这道题！',function(r){});
							       	}else{
							       		$('#changgepd').window('close');
							       		$.messager.alert('确认','更换成功!!!','info',function(){
							       			location.href='xztnr';
						            	});
									       	}
						      		}
						   	});
				    	}
		    		}
		   		});
			}else{$.messager.alert('警告','请选择一行数据','warning');}
		
	}
	//查询判断题
	function query(){
        var queryParams = $('#pd').datagrid('options').queryParams;
        var queryType =queryParams.queryType="tg";
        var queryWord=queryParams.queryWord= $('#name').val();
        if(queryWord!=""){
				$.ajax({
					type:"POST",
					url:"ghquerypd1.action",
					data:{"queryType":queryType,"queryWord":queryWord},
					cache: false,
					success:function(data){
								if(data.a == 1){
									$('#pd').datagrid({
					    	            url:'ghquerypd',
					    	            columns:[[
					  			                {title:'题号',field:'th',width:'100',align:'center'},
					  			                {field:'tg',title:'题干',width:'400',align:'left'},
					  			              	{field:'da',title:'答案',width:'100',align:'center'}
					  						]]
					    	        });
					    	        $('#query').window('close');
								}else if(data.a == 0){
									$.messager.alert('提示信息','查询结果为空!!!','info',function(){
					            	});
							}
						}
					});
        			}else{$.messager.alert('提示','查询条件不能为空!');}
   				 }	
		</script>
		
	</head>

	<body class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;" onload="test();">
		<div  region="center"  style="width:900px;padding:30px;">
			<center>
				<font color="#88B04E" size="4">  
	                  <s:property value="sjname" />
                 </font> 
			</center>
		<s:if test="xz1.size!=0">
			<td>
				<font color="orange" size="4">单选题</font>
			</td>
			<s:iterator value="xz1" var="xz1" status="stat1" id="xz1">
			<table>
				 <tr>  
                     <td width="640" height="36">  
                         <font color="green">  
	                         <s:property value="#stat1.index+1" />                          .  
	                         <s:property value="tg" />
	                         <s:hidden id="xth1" name="xth" value="%{th}"/>
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
               <tr><td><s:div id="%{#stat1.index}" /></td></tr>
	             </table>
			<hr/>
			</s:iterator>
	</s:if>
	<s:if test="dxz1.size!=0">
			<td>
				<font color="orange" size="4">多选题</font>
			</td>
			<s:iterator value="dxz1" var="dxz1" status="stat" id="dxz1">
			<table>
				 <tr>  
                     <td width="640" height="36">  
                         <font color="green">  
	                         <s:property value="#stat.index+1" />                          .  
	                         <s:property value="tg" />
	                         <s:hidden id="dxth1" name="dxth" value="%{th}"/>
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
                   <tr><td><s:div id="%{#stat.index+#request.xz1.size()}" /></td></tr> 
	             </table>
			<hr/>
			</s:iterator>
	</s:if>
	<s:if test="pd1.size!=0">
			<td>
				<font color="orange" size="4">判断题</font>
			</td>
			<s:iterator value="pd1" var="pd1" status="stat2" id="pd1">
				<table>
				 <tr> 
                     <td height="36">  
                         <font color="green">  
                         <s:property value="#stat2.index+1" />                          .  
                         <s:property value="tg" />
                        <s:hidden name="da1" value="%{da}"/>
                        <s:hidden id="pdth1"   name="pdth" value="%{th}"/>
                         </font>  
                     </td> 
                 </tr>
                 <tr><td><s:div id="%{#stat2.index+#request.xz1.size()+#request.dxz1.size()}" /></td></tr>
			</table>
				<hr />
			</s:iterator>
		</s:if>
		<s:if test="czt1.size!=0">	
			<td>
				<font color="orange" size="4">操作题</font>
			</td>
			<s:iterator value="czt1" var="czt1" status="stat3" id="czt1">
				<table>
				 <tr> 
                     <td height="36">  
                         <font color="green">  
	                         <s:property value="#stat3.index+1" />                          .  
	                         <s:property value="dttg" />
	                         <s:hidden id="dtth" name="dtth" value="%{dtth}"/>     
                         </font>  
                     </td> 
                 </tr>
                 <tr> 
                     <td height="36">  
	                     <%List list=(List)request.getAttribute("xtth");%>
						<%List list1=(List)request.getAttribute("xttg");%>
						<%for(int i=0;i<list.size();i++){ %>
						<p>第<%=list.get(i) %>问：<%=list1.get(i) %> </p>
						<%} %> 
                     </td> 
                 </tr>
                 <tr><td><s:div id="%{#stat3.index+#request.xz1.size()+#request.dxz1.size()+#request.pd1.size}" /></td></tr>
			</table>
				<hr />
			</s:iterator>
		</s:if>
		</div>
		
		
		<div id="changgepd" class="easyui-window"  closed="true" title="更改判断题" style="padding:0px;width:634px;height:456px;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
			<div  region="center" border="false" style="background: #fafafa;width:100%;height:100%;">
				<table id="pd"></table>
		   </div>
		   <div id="query" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;visibility:hidden;"
		    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
		        <div>
		        	<form id="cx">
			            <table>
			                <tr>
			                    <td>
			                     	  题干：
			                    </td>
			                    <td><input type="text" name="id" id="name"  required="true"></td>
			                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
			                </tr>
			            </table>
		            </form>
		        </div>
		    </div>	
		</div>
		
		<div id="changgexz1" class="easyui-window"  closed="true" title="更改单选题" style="padding:0px;width:634px;height:456px;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
			<div  region="center" border="false" style="background: #fafafa;width:100%;height:100%;">
				<table id="xz1"></table>
		   </div>
		   <div id="queryxz1" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;visibility:hidden;"
		    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
		        <div>
		        	<form id="cxxz1">
			            <table>
			                <tr>
			                    <td>
			                     	  题干：
			                    </td>
			                    <td><input type="text1" name="id" id="name1"  required="true"></td>
			                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="queryxz1()">查询</a></td>
			                </tr>
			            </table>
		            </form>
		        </div>
		    </div>	
		</div>
		
		
		<div id="changgexz2" class="easyui-window"  closed="true" title="更改多选题" style="padding:0px;width:634px;height:456px;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
			<div  region="center" border="false" style="background: #fafafa;width:100%;height:100%;">
				<table id="xz2"></table>
		   </div>
		   <div id="queryxz2" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;visibility:hidden;"
		    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
		        <div>
		        	<form id="cxxz2">
			            <table>
			                <tr>
			                    <td>
			                     	  题干：
			                    </td>
			                    <td><input type="text2" name="id" id="name2"  required="true"></td>
			                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="queryxz2()">查询</a></td>
			                </tr>
			            </table>
		            </form>
		        </div>
		    </div>	
		</div>
	</body>
</html>
