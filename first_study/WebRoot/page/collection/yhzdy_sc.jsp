<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>source collection</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="kindeditor/kindeditor-min.js"></script>
	<script>
		$(function(){
			$('#ttt').datagrid({
				title:'自定义分类详细',
				iconCls:'icon-save',
				nowrap: true,
				striped: true,
				collapsible:true,
				url:'yhzdyfl_xx.action',
				sortName: 'sxh',
				sortOrder: 'asc',
				remoteSort: false,
				idField:'sxh',
				singleSelect:true,
				loadMsg:'加载数据,请等待...',
				width:getWidth(1.02),
				onDblClickRow: function() {
				   var selected = $('#ttt').datagrid('getSelected');
				   var zybh = selected.zybh;
				   var lx = selected.zylx;
					if(lx==1){
						$.ajax({
							type:"POST",
							url:"queryxzscdg.action",
							data:{"queryw":zybh},
							cache: false,
							success:function(json){
								if(json){
									var $win;
									$win = $('#scan1').window({
										top:document.body.scrollTop+100,   
							            left:document.body.scrollLeft+300
									});
									$win.window('open');
									//$('#scan1').window('open');
									document.getElementById("scan1").style.visibility="visible";
									$('#zsdmc1').val(json.zsdmc);
									$('#zmc1').val(json.zmc);
						            $('#kcmc1').val( json.kcmc);
						            $('#tcname1').val(json.tcname);
						            $('#th1').val(json.int5);
						            var x=json.str3
						          	//给编辑器设置HTML内容
									KE.html('tg1', x);
						            $('#xx11').val(json.str4);
						            $('#xx21').val(json.str5);
						            $('#xx31').val(json.str6);
						            $('#xx41').val(json.str7);
						            $('#ddx1').val(json.int2);
						            $('#da1').val(json.str1);
						            $('#csrcs1').val(json.int1);
						            $('#zqrcs1').val(json.int7);
								}
							}
						});
					}
					if(lx==2){
						$.ajax({
							type:"POST",
							url:"querypdscdg.action",
							data:{"queryw":zybh},
							cache: false,
							success:function(json){
								if(json){
									var $win;
									$win = $('#scan2').window({
										top:document.body.scrollTop+100,   
							            left:document.body.scrollLeft+300
									});
									$win.window('open');
									//$('#scan2').window('open');
									document.getElementById("scan2").style.visibility="visible";
									$('#zsdmc2').val(json.zsdmc);
									$('#zmc2').val(json.zmc);
						            $('#kcmc2').val(json.kcmc);
						            $('#zhangm2').val(json.str1);	
						            $('#th2').val(json.int5);
						            var x=json.str3
						          	//给编辑器设置HTML内容
									KE.html('tg2', x);
						            $('#tg2').val(json.str3);
						            $('#da2').val(json.int2);
								}
							}
						});
					}
					if(lx==3){
						ckxxxx(zybh);
					}
					if(lx==4){
						$.ajax({
							type:"POST",
							url:"dmtzlljcx.action",
							data:{"zlbh":zybh},
							cache: false,
							success:function(data){
								var fielpath = data.message;
								var filename = data.filemess;
								window.location.href='spdh/dzbj.jsp?zlmc=' + filename +
				    			'&path=' + fielpath + '&filename=' + selected.nr + '&zlid=' + zybh;
							}
						});
					}
		},
				fit:true,
				//fitColumns:true,
				columns:[[
					    //{field:'zybh',title:'资源编号',width:getWidth(0.15),align:'center',editor:'text'},
				      	{field:'zylx',title:'资源类型',width:getWidth(0.15),align:'center',editor:'text',
					    	  formatter:function(value,rec){
					    	           var temp;
									 if(value==1){
                                           temp="选择"; 
										 }else if(value==2){
                                             temp="判断";
											 }else if(value==3){
												 temp="操作";
												 }else{
													 temp="多媒体";
													 }
			                         return '<font>'+temp+'</font>';
		                       }
					      	},
				      	{field:'sskcmc',title:'所属课程名称',width:getWidth(0.15),align:'center',editor:'text'},
				      	{field:'zyms',title:'资源描述',width:getWidth(0.693),align:'left',editor:'text'} 
				      	]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					text:'删除',
					iconCls:'icon-remove',
					handler:dele
				}]
			});
			$('#ttt').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
				 });
		});
		//宽度
		function getWidth(percent){ 
			return (document.body.clientWidth-25-5)*percent; 
		}
		//删除======================================
		function dele(){
			var selected = $('#ttt').datagrid('getSelected');
			if(selected){
				//alert(selected.id.sxh+","+selected.zylx);
			var select = {"sxh":selected.id.sxh};
			  $.messager.confirm('warning','数据将被永久删除，不可恢复',function(selected){
				if(selected){
					$.ajax({
						type:"POST",
						url:"deleteResource.action",
						data:select,
						success:function callback(r){
							$('#ttt').datagrid('reload');
						}
					});
				};
				
			  });
			}else{
			$.messager.alert('warning','请选择一行数据','warning')};
		}
		
		KE.show({
			id : 'tg1',
			items : [],
			resizeMode : 0,
			afterCreate : function(id) {
			//KE.toolbar.disable(id, []);
			KE.readonly(id);
			KE.g[id].newTextarea.disabled = true;
			}
		});
		
		KE.show({
			id : 'tg2',
			items : [],
			resizeMode : 0,
			afterCreate : function(id) {
			//KE.toolbar.disable(id, []);
			KE.readonly(id);
			KE.g[id].newTextarea.disabled = true;
			}
		});

		//关于操作题的查看
		//宽度
		function getWidth(percent){ 
			return (document.body.clientWidth-25-5)*percent; 
		}
		//高度
		function getHeight(percent){ 
		    return document.body.clientHeight*percent; 
		} 
		  function as1(iframe,width,height)
		  {
			 var iframe1 = $("#".concat(iframe)); 
			 iframe1.width(width);
			 iframe1.height(height); 
		 }
		//查看操作题详细信息
			 function ckxxxx(zybh)
		     {
		        /*var rows = $('#czt').datagrid("getSelections");
		 		if(rows.length>1)
		 		{
		 			$.messager.alert('警告','请选择一行数据','warning');
		 			return;
		 		}
		    	 //var selected = $('#czt').datagrid('getSelected');
		    	 if(!selected)
		    	 {
		    		 $.messager.alert('警告','请选择要查询的行','warning');
		        	 return;
		    	 }*/
		    	    var th={"tihao":zybh};
		    	      $.ajax({
		    	  		type:"POST",
		    	  		url:"searchtihao.action",  
		    	  		data:th,
		    	  		dataType:'json',
		    	  		success:function callback(r){
		    	  	      if(r.message=="success")
		    	  	      {
			    	  	     
		    	  	    	  var percent=(1-800/getWidth(1))/2;
		    	  	    
		    	  	          var p = $('#search1').window({
		    	  				onBeforeOpen:function(){
		    	  				  $('#search1').window('resize',{width:790,height:365,left:getWidth(percent),top:getHeight(0.05)});
		    	  			}
		    	  			}); 
		    	  	   	 
		    	  	         $('#search1').window('open'); 
		    	  	        as1("CFrame2",780,365);
		    	           CFrame2.location=encodeURI("cztjsp/Ckczt1.jsp?tihao="+zybh);        
		    	  	      }
		    	  	  
		    	  		}
		    	  		}); 
		     }


		     function ckxt(tihao)
		     {  
		    
		     	   $('#ckxts').window('open');
		     	   $('#ckxts').window('resize',{left:getWidth(0.1),top:getHeight(0.05),width:getWidth(0.78),height:getHeight(0.7)});
		     	       		$('#ckxts1').datagrid({
		     				fit:true,
		                     fitColumns:true,
		     				singleSelect:true,  
		     				loadMsg:'加载数据,请等待...',
		     				onLoadSuccess:function(){
		     					var data=$('#ckxts1').datagrid('getData');
		     					if(data.total==0)
		     					{
		     					      $.messager.alert('信息','没有数据','info');
		     					}				
		     				},
		     				onClickRow:function(index,data){
		     					s(index,"shareit-box4","ckxts1","CFrame10");
		     			},
		     				url:'getallczt.action?tihao='+tihao, 
		     				 columns:[[
		     			 {field:'th',title:'操作题号',width:getWidth(0.75)*0.1,align:'center' },
		                  {field:'sxh',title:'小题题号',width:getWidth(0.75)*0.1,align:'center'},
		                  {field:'tg',title:'小题题干',width:getWidth(0.75)*0.4,align:'center'},
		                  {field:'xtfz',title:'分值',width:getWidth(0.75)*0.1},
		                  {field:'nyd',title:'难易度',width:getWidth(0.75)*0.1},
		                  {field:'csrcs',title:'测试人数',width:getWidth(0.75)*0.1,align:'center'},
		                  {field:'zqrcs',title:'正确人数',width:getWidth(0.75)*0.1,align:'center'}     
		               ]],
		     				rownumbers:true
		     				});
		     }  

		     var ckexceltihao;
		     function ckwj(cktihao)
		     {
		     ckexceltihao=cktihao;
		  	 $('#ckwj').window('open');
		  	 $('#ckwj').window('resize',{left:getWidth(0.1),top:getHeight(0.05),width:getWidth(0.76),height:getHeight(0.68)})
		     $('#ckwj1').datagrid({
		  				fit:true,
		                fitColumns:true,
		  				nowrap: false,
		  				striped: true,
		  				collapsible:true,
		  				singleSelect:true,  
		  				loadMsg:'加载数据,请等待...',
		  				onLoadSuccess:function(){
		  		var data=$('#ckwj1').datagrid('getData');
		  		if(data.total==0)
		  		{
		  		      $.messager.alert('信息','没有数据','info');
		  		}			
		  	},
		  				url:'getallwj.action?tihao='+cktihao, 
		  				 columns:[[
		  				           {field:'bh',title:'文件编号',width:getWidth(0.74)*0.1,align:'center'},
		  							 {field:'dtth',title:'大题号 ',width:getWidth(0.74)*0.1,align:'center'}, 
		  				             {field:'wjmc',title:'文件名称',width:getWidth(0.74)*0.2,align:'center'},
		  				             {field:'ms',title:'文件描述',width:getWidth(0.74)*0.6,align:'center'}  
		            ]],
		  				rownumbers:true
		  				});
		  			
		     }
				     
		//关于操作题的查看 
</script>
</head>
<body style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
    <table id="ttt"></table>
    <tbale id="xuanzet"></tbale>
	<div id="scan1" closed="true" class="easyui-window" title="查看详细信息" style="width:450px;height:400px;padding:5px;background: #fafafa;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
		    	<table align="center">						    				
					<tr>
						<td> 
						知识点名:
						</td>
						<td>										
							<input id="zsdmc1" type="text" style="width:300px;" disabled="true"/>
						</td>
					</tr>				
					<tr>
						<td> 
						节名: 
						</td>
						<td>
							<input id="zmc1" type="text" style="width:300px;"  disabled="true"/>
						</td>
					</tr>
					<tr>
						<td> 
						章名: 
						</td>
						<td>
							<input id="kcmc1" type="text" style="width:300px;" disabled="true"/>
						</td>
					</tr>
					<tr>
						<td> 
						课程名: 
						</td>
						<td>
							<input id="tcname1" type="text" style="width:300px;" disabled="true"/>
						</td>
					</tr>
					<tr>
						<td> 
						题号: 
						</td>
						<td>
							<input id="th1" type="text" style="width:300px;" disabled="true"/>
						</td>
					</tr>
					<tr>
						<td> 
						题干: 
						</td>
						<td>
							<textarea id="tg1" name="tg" style="width:300px;height:130px;visibility:hidden;" disabled="true"></textarea> 					 
						</td>
					</tr>
					<tr>
						<td> 
						选项一: 
						</td>
						<td>
							<input id="xx11" type="text"  style="width:300px;" disabled="true"/>
						</td>
					</tr>
					<tr>
						<td> 
						选项二: 
						</td>
						<td>
							<input id="xx21" type="text"  style="width:300px;" disabled="true"/>
						</td>
					</tr>
					<tr>
						<td> 
						选项三: 
						</td>
						<td>
							<input id="xx31" type="text" style="width:300px;"  disabled="true"/>
						</td>
					</tr>
					<tr>
						<td> 
						选项四: 
						</td>
						<td>
							<input id="xx41" type="text" style="width:300px;"  disabled="true"/>
						</td>
					</tr>
					<tr>
						<td> 
						答案: 
						</td>
						<td>
							<input id="da1" type="text"  style="width:300px;"  disabled="true"/>
						</td>
					</tr>
					<tr>
						<td> 
						测试次数: 
						</td>
						<td>
							<input id="csrcs1" type="text" style="width:300px;"  disabled="true"/>
						</td>
					</tr>
					<tr>
						<td> 
						正确次数: 
						</td>
						<td>
							<input id="zqrcs1" type="text" style="width:300px;"  disabled="true"/>
						</td>
					</tr>
				</table>
		</div>
		<div id="scan2" closed="true" class="easyui-window" title="查看详细信息" style="width:420px;height:370px;padding:5px;background: #fafafa;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
			    	<table align="center">						    				
						<tr>
							<td> 
							知识点名称:
							</td>
							<td>										
								<input id="zsdmc2" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>				
						<tr>
							<td> 
							节名称: 
							</td>
							<td>
								<input id="zmc2" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td> 
							课程名称: 
							</td>
							<td>
								<input id="kcmc2" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td> 
							章名称: 
							</td>
							<td>
								<input id="zhangm2" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td> 
							题号: 
							</td>
							<td>
								<input id="th2" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td> 
							题干: 
							</td>
							<td>
								<textarea id="tg2" name="tg" style="width:300px;height:130px;visibility:hidden;"  disabled="true"></textarea>
							</td>
						</tr>
						<tr>
							<td> 
							答案: 
							</td>
							<td>
								<input id="da2" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
					</table>
			   </div>
			   
			   <!-- 查询操作题信息 -->
	    	<div id="search1" closed="true" class="easyui-window"   resizable="true"  title="查询操作题信息"  maximizable="false"  minimizable="false"    iconCls="icon-search" style="background: #fafafa;overflow-x:hidden;overflow-y:hidden;">
				<div  border="false" style="background:#fff;overflow-x:hidden;overflow-y:hidden;">
					<iframe  id="CFrame2" src=""   name="CFrame2"  frameborder="0" scrolling="no"   width="100%"  height="100%"></iframe> 
				</div>
			</div>
			<div id="ckxts" closed="true"    class="easyui-window" title="查看小题" iconCls="icon-search" maximizable="false"  minimizable="false" style="background: #fafafa;">
				<div  border="false" style="background:#fff;width:100%;height:100%;">
		     	<table id="ckxts1"></table>
		     	</div>
			</div>
			<div id="ckda" closed="true" class="easyui-window"   maximizable="false"  minimizable="false" title="查看答案" iconCls="icon-search" >
				<div id="ckda1" region="center" border="false" style="background:#fff;overflow-x:hidden;width:100%;height:100%;">
					<table id="ckanswer"></table>
		    	</div>   
  			</div>
    		<div id="ckwj" closed="true" class="easyui-window"   maximizable="false"  minimizable="false" title="文件" iconCls="icon-search" >
				<div   region="center" border="false" style="background:#fff;width:100%;height:100%;">
   					<table id="ckwj1"></table>
	   			</div>		
			</div>
</body>
</html>