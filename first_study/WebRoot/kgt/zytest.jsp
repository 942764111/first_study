<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自由测试</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/sjld.js"></script>
	<script type="text/javascript" src="kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="kindeditor/kindeditor-min.js"></script>
	
	<script type='text/javascript' src='js/auto/jquery.bgiframe.js'></script>
	<script type='text/javascript' src='js/auto/jquery.ajaxQueue.js'></script>
	<script type='text/javascript' src='js/auto/thickbox-compressed.js'></script>
	<script type='text/javascript' src='js/auto/jquery.autocomplete.js'></script>
	<script type='text/javascript' src='js/auto/localdata.js'></script>
	<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
	
	<script type="text/javascript">
	var zsdmcp=[];
	var zsdmcx=[];
	//使用知识点名称获取选择题时的自动完成
	$(function(){
		$.ajax({
			  type: "post",
		      url:"queryzsdmc",
		      dataType:"json",
		      success:function (json){
			  zsdmcx.length=0;
		   		$.each(json.zsdmc1,function(n,value){
		   			zsdmcx.push(value);
              });
		   		$("#zsdmc").flushCache(); //清空website处的缓存
		   		$("#zsdmc").autocomplete(zsdmcx,{
					minChars: 0,
					max: 120,
					matchSubset :false,
					autoFill: false,
					multiple :false,//控制是否可多次输入(是否把原来的内容覆盖掉)
					mustMatch: false,
					multipleSeparator :"",
					matchContains: true,
					scrollHeight: 220,
					formatItem: function(data, i, total) {
						return "<I>"+data[0]+"</I>";
					},
					formatMatch: function(data, i, total) {
						return data[0];
					},
					formatResult: function(data) {
						return data[0];
					}
				});

		      }

			});
		});

	//使用知识点名称获取判断题时的自动完成
	$(function(){
		$.ajax({
			  type: "post",
		      url:"queryzsdmc",
		      dataType:"json",
		      success:function (json){
			  zsdmcp.length=0;
		   		$.each(json.zsdmc1,function(n,value){
		   			zsdmcp.push(value);
              });
		   		$("#zsdmcp").flushCache(); //清空website处的缓存
		   		$("#zsdmcp").autocomplete(zsdmcp,{
					minChars: 0,
					max: 120,
					matchSubset :false,
					autoFill: false,
					multiple :false,//控制是否可多次输入(是否把原来的内容覆盖掉)
					mustMatch: false,
					multipleSeparator :"",
					matchContains: true,
					scrollHeight: 220,
					formatItem: function(data, i, total) {
						return "<I>"+data[0]+"</I>";
					},
					formatMatch: function(data, i, total) {
						return data[0];
					},
					formatResult: function(data) {
						return data[0];
					}
				});
		      }
			});
		});

//判断题自由测试三级联动	
	$(function(){
	      $.ajax({
		    url:"bindTCName",
		    type:"GET",
		    dataType:"json",
		    success:function(json){
	  		$.each(json.w,function(n,value){
				 $("#bindTCName1").append("<option value=\""+value+"\">"+value+"</option>");
			 });
			}
		   });
	     }
	 );

	function geta(){
	    //清除出第一个以外的下拉框
		$("#bindZh1").children().eq(0).siblings().remove();
	    $("#bindK1").children().eq(0).siblings().remove();
	    var temp1=$("#bindTCName1").find("option:selected").val();
	    $.ajax({
		   url:"bindZh",
		   type:"get",
		   dataType:"json",
		   data:{"TCName":temp1},
		   success: function(data){
			   var zbhmc=new Array();
			   zbhmc=data.second;
			   $.each(zbhmc,function(n,value){
					 $("#bindZh1").append("<option value=\""+value.zbh+"\">"+value.CName+"</option>");
				 });
		   }
		  });
	   }
	function getb(){
	     $("#bindK1").children().eq(0).siblings().remove();
	     var temp2=$("#bindZh1").find("option:selected").val();
	     $.ajax({
		    url:"bindK",
		    type:"get",
		    dataType:"json",
		    data:{"zbh":temp2},
		    success: function(data){
		    	var jbhmc=new Array();
				 jbhmc=data.third;
		    	$.each(jbhmc,function(n,value){
					 $("#bindK1").append("<option value=\""+value.id.CId+"\">"+value.zmc+"</option>");
				 });
		    }
		   });
	    }

	
		function getWidth(percent){ 
		    return (document.body.clientWidth-25-6)*percent; 
		}
		function getWidth1(percent){ 
		    return (document.body.clientWidth)*percent; 
		}
		function getHeight(percent){ 
		    return (document.body.clientHeight)*percent; 
		}
		//判断题查看
		$(function(){				
			$('#pd_tables').datagrid({
				title:'判断题列表',
				iconCls:'icon-save',			
				striped: true,	
				fit:true,
				fitColumn:true,
				singleSelect:true,
				singleSelect:true,
				loadMsg:'加载数据,请等待...',		
				url:'pdanswer.action',
				onDblClickRow: function() {
				    var selected = $('#pd_tables').datagrid('getSelected');
				    if (selected){
				      scanp();
				   }} ,
				columns:[[                               
					//{field:'int5',title:'题号',width:getWidth(0.05),align:'center'},	
					{field:'tg',title:'题干',width:getWidth(0.9),align:'left'},					
					{field:'da',title:'答案',width:getWidth(0.05),align:'center'}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnquery',
    				text:'查询',
    				iconCls:'icon-search',
    				handler:function(){
    					$('#btnquery').linkbutton('enable');
    					$('#query').window('open');
    				}
    			}]
			});
			
			});
		       
		function query(){
	        var queryParams = $('#pd_tables').datagrid('options').queryParams;
	        var queryType =queryParams.queryType="tg";
	        var queryWord=queryParams.queryWord= $('#name1').val();
					$.ajax({
						type:"POST",
						url:"zyquerypdjy.action",
						data:{"queryType":queryType,"queryWord":queryWord},
						cache: false,
						success:function(data){
									if(data.a == 1){
										$('#pd_tables').datagrid({
						    	            url:'zyquerypd.action'
						    	        });
						    	        $('#query').window('close');
						    	        $('#pd_tables').datagrid('getPager').pagination({
						   				 displayMsg:'当前显示从{from}到{to}共{total}记录',
						   				 beforePageText:'第',
						   				 afterPageText:'页'
						   				 });
									}else if(data.a == 0){
										$.messager.alert('提示信息','查询结果为空!!!','info',function(){
						            	});
								}
							}
						});
	   				 }
	    
	    //选择题查看
	    $(function(){				
			$('#xz_tables').datagrid({
				title:'选择题列表',
				iconCls:'icon-save',			
				fit:true,
				fitColumn:true,
				singleSelect:true,
				striped: true,
				loadMsg:'加载数据,请等待...',			
				url:'xzanswer.action',
				onDblClickRow: function() {
				    var selected = $('#xz_tables').datagrid('getSelected');
				    if (selected){
				      scanx();
				   }} ,
				columns:[[                               
					{field:'tg',title:'题干',width:getWidth(0.9),align:'left'},
					{field:'da',title:'答案',width:getWidth(0.05),align:'center'}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
    				id:'btnquery',
    				text:'查询',
    				iconCls:'icon-search',
    				handler:function(){
    					$('#btnquery').linkbutton('enable');
    					$('#xzquery').window('open');
    				}
    			}]
			});
			$('#xz_tables').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
				 });
			});

		function xzquery(){
	        var queryParams = $('#xz_tables').datagrid('options').queryParams;
	        var queryType =queryParams.queryType="tg";
	        var queryWord=queryParams.queryWord= $('#name').val();
					$.ajax({
						type:"POST",
						url:"zyqueryxzjy.action",
						data:{"queryType":queryType,"queryWord":queryWord},
						cache: false,
						success:function(data){
									if(data.a == 1){
										$('#xz_tables').datagrid({
						    	            url:'zyqueryxz.action'
						    	        });
						    	        $('#xzquery').window('close');
						    	        $('#xz_tables').datagrid('getPager').pagination({
						   				 displayMsg:'当前显示从{from}到{to}共{total}记录',
						   				 beforePageText:'第',
						   				 afterPageText:'页'
						   				 });
									}else if(data.a == 0){
										$.messager.alert('提示信息','查询结果为空!!!','info',function(){
						            	});
								}
							}
						});
	    }

//**********************选择题*************************	    
		
		//根据章名称、课程名称查出选择题题目
	    function xzcs(){
	    	var TCName = $("#bindTCName").find("option:selected").text();
			var CName = $("#bindZh").find("option:selected").text();
			var zmc = $("#bindK").find("option:selected").text();
			var zsdmc = $("#zsdmc").val();
			var zbh = $("#bindZh").find("option:selected").val();//章编号
			var jbh = $("#bindK").find("option:selected").val();//节编号
			var x=1;
			if(TCName=="--请选择--"&&zsdmc==""){
				$.messager.alert('提示信息','请选择测试范围!','info');
				}else{
					$.ajax({
			            type:"POST",
			            url:"xzcs2",
			            dataType:"json",
			            data:{"jbh":jbh,"zbh":zbh,"x":x,"zsdmc":zsdmc,"zmc":zmc,"CName":CName,"TCName":TCName},
			           	success:function(json){
				           	if(json.a==1){
				           		//var url = "xzcs3.action";
				    			//var params = {"zmc":zmc,"CName":CName,"TCName":TCName};
				    			//$('#x1').load(url,params);
					        	CFrame4.location="xzcs3.action";
					           	}else if(json.a==0){
					           		$.messager.alert('提示信息','此选择范围内没有题!!!','info',function(){
					            	});
						           	}
					   			}
		      				});
					}

	    }

		
	    //上一页
	    function syy(){
	    	var page=-1;
	    	var zmc = $("#bindK").val();
			var CName = $("#bindZh").val();
			var TCName = $("#bindTCName").val();
			var x=0;
			$.ajax({
	            type:"POST",
	            url:"xzcs2",
	            dataType:"json",
	            data:{"page":page,"zmc":zmc,"CName":CName,"TCName":TCName,"x":x},
	           success:function(json){
	 	           if(json.pages>0){
	 	        	  var url = "xzcs3.action";
	 	        	 var params = {"zmc":zmc,"CName":CName,"TCName":TCName};
		    			$('#x1').load(url,params);
		 	           }else if(json.pages==0){
		 	        	  $.messager.alert('提示信息','当前页已是第一页!!!','info',function(){
			            	});
			 	       }
		 	     }
      		});
	    }
	  
	  //选择题下 一页
	    function xyy(){
	    	var page=0;
	    	var TCName = $("#bindTCName").find("option:selected").text();
			var CName = $("#bindZh").find("option:selected").text();
			var zmc = $("#bindK").find("option:selected").text();
			var zsdmc = $("#zsdmc").val();
			var zbh = $("#bindZh").find("option:selected").val();//章编号
			var jbh = $("#bindK").find("option:selected").val();//节编号
			var x=0;
			$.ajax({
	            type:"POST",
	            url:"xzcs2",
	            dataType:"json",
	            data:{"jbh":jbh,"zbh":zbh,"page":page,"TCName":TCName,"zmc":zmc,"CName":CName,"x":x,"zsdmc":zsdmc},
	            success:function(json){
	 	           if(json.pagex<=json.zy){
	 	        	  //var url = "xzcs3.action";
	 	        	  //var params = {"zmc":zmc,"CName":CName,"TCName":TCName};
		    			//$('#x1').load(url,params);
			        	CFrame4.location="xzcs3.action";
			 	           }else if(json.pagex=json.zy+1){
	 	        	  $.messager.alert('提示信息','已是最后一页,或者您还没有选择测试范围!!!','info',function(){
		            	});
		 	           }
		 	       }
      		});
	    }

	//跳转到某一页
	function tz(){
		var page=$("#tzy").val();
		var zmc = $("#bindK").val();
		var CName = $("#bindZh").val();
		var TCName = $("#bindTCName").val();
		var x=0;
		$.ajax({
            type:"POST",
            url:"xzcs2",
            dataType:"json",
            data:{"TCName":TCName,"page":page,"zmc":zmc,"CName":CName,"x":x},
            success:function(json){
                if(json.paget<=json.zy){
                	//var url = "xzcs3.action";
                	//var params = {"zmc":zmc,"CName":CName,"TCName":TCName};
	    			//$('#x1').load(url,params);
		        	CFrame4.location="xzcs3.action";
                    }else if(json.paget>json.zy){
                    	$.messager.alert('提示信息','已超过最大页!!!','info',function(){
		            	});
                        }
            	
                }
  		});
		}

	
	//选择题提交答案
	function tj(){
		
		$('#xzjg').window('open');
		var TCName = $("#bindTCName").find("option:selected").text();
		var CName = $("#bindZh").find("option:selected").text();
		var zmc = $("#bindK").find("option:selected").text();
		var url = "xzfx3.action";
		var params = {"zmc":zmc,"CName":CName,"TCName":TCName};
		$('#xzjg').load(url,params);
		}

	function scanx(){
		var selected = $('#xz_tables').datagrid('getSelected');						
		if(selected){				
			$('#scanx').window('open');
			document.getElementById("scanx").style.visibility="visible";
            var x=selected.tg
          	//给编辑器设置HTML内容
			KE.html('tgx', x);
            $('#dax').val(selected.da);
		}else{
		    $.messager.alert('警告','请选择一行数据','warning');
		 }
	}
	KE.show({
		id : 'tgx',
		items : [],
		resizeMode : 0,
		afterCreate : function(id) {
		//KE.toolbar.disable(id, []);
		KE.readonly(id);
		KE.g[id].newTextarea.disabled = true;
		}
		});
//**********************判断题***************************
	//根据章名称、课程名称查出题目
    function pdcs(){
    	var TCName = $("#bindTCName1").find("option:selected").text();
		var CName = $("#bindZh1").find("option:selected").text();
		var zmc = $("#bindK1").find("option:selected").text();
		var zsdmc = $("#zsdmcp").val();
		var zbh = $("#bindZh1").find("option:selected").val();//章编号
		var jbh = $("#bindK1").find("option:selected").val();//节编号
		var x=1;
		if(TCName=="--请选择--"&&zsdmc==""){
			$.messager.alert('提示信息','请选择测试范围!','info');
			}else{
				$.ajax({
		            type:"POST",
		            url:"pdcs",
		            dataType:"json",
		            data:{"jbh":jbh,"zbh":zbh,"TCName":TCName,"zmc":zmc,"CName":CName,"x":x,"zsdmc":zsdmc},
		           	success:function(json){
			           	if(json.a==1){
			           		//var url = "pdcs1.action";
			    			//var params = {"zmc":zmc,"CName":CName,"TCName":TCName};
			    			//$('#p1').load(url,params);
				        	CFrame5.location="pdcs1.action";
				           	}else if(json.a==0){
				           		$.messager.alert('提示信息','此选择范围内没有题!!!','info',function(){
				            	});
					           	}
				   		}
		  		});
			}
    }

	  //判断题下 一页
    function pxyy(){
    	var page=0;
    	var TCName = $("#bindTCName1").find("option:selected").text();
		var CName = $("#bindZh1").find("option:selected").text();
		var zmc = $("#bindK1").find("option:selected").text();
		var zsdmc = $("#zsdmcp").val();
		var zbh = $("#bindZh1").find("option:selected").val();//章编号
		var jbh = $("#bindK1").find("option:selected").val();//节编号
		var x=0;
		$.ajax({
            type:"POST",
            url:"pdcs",
            dataType:"json",
            data:{"jbh":jbh,"zbh":zbh,"page":page,"TCName":TCName,"zmc":zmc,"CName":CName,"x":x,"zsdmc":zsdmc},
            success:function(json){
 	           if(json.pagex<=json.zy){
 	        	  //var url = "pdcs1.action";
 	        	  //var params = {"zmc":zmc,"CName":CName,"TCName":TCName};
	    			//$('#p1').load(url,params);
		        	CFrame5.location="pdcs1.action";
		 	           }else if(json.pagex=json.zy+1){
 	        	  $.messager.alert('提示信息','已是最后一页,或者您还没有选择测试范围!!!','info',function(){
	            	});
	 	           }
	 	       }
  		});
    }
    
	//判断题提交答案
	function ptj(){
		var TCName = $("#bindTCName1").find("option:selected").text();
		var CName = $("#bindZh1").find("option:selected").text();
		var zmc = $("#bindK1").find("option:selected").text();
		var url = "pdfx3.action";
		var params = {"zmc":zmc,"CName":CName,"TCName":TCName};
		$('#xzjg').load(url,params);
		$('#xzjg').window('open');
		}

	function scanp(){
		var selected = $('#pd_tables').datagrid('getSelected');						
		if(selected){				
			$('#scanp').window('open');
			document.getElementById("scanp").style.visibility="visible";
          	//给编辑器设置HTML内容
			KE.html('tgp', selected.tg);
            $('#dap').val(selected.da);
		}else{
		    $.messager.alert('警告','请选择一行数据','warning');
		 }
	}
	KE.show({
		id : 'tgp',
		items : [],
		resizeMode : 0,
		afterCreate : function(id) {
		//KE.toolbar.disable(id, []);
		KE.readonly(id);
		KE.g[id].newTextarea.disabled = true;
		}
		});

</script>




	</head>
	
	<body class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
	<div region="center">
		<div id="tt"  fit="true" class="easyui-tabs" style="width:1070px;height:573px;">
			<div title="选择题自测">
			<div class="easyui-layout" fit="true" style="width:900px;padding:10px;height:392px">
				<div id="xzcs"  region="north" style="width:900px;padding:10px;height:70px">
					<form>
					请选择所要测试的范围:
						课程名称:<select id="bindTCName" onChange="getZh()" style="width:160px;">
								<option  value="-1">--请选择--</option></select>
						章名称:<select id="bindZh" onChange="getKcbh()" style="width:160px;">
								<option  value="-1">--请选择--</option></select>
						节名称: <select id="bindK" onChange="xzcs()"  style="width:160px;">
						   		<option  value="-1">--请选择--</option></select>
						<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="xzcs()">确定</a>
						<br>
						您也可以通过知识点选择测试范围:
							知识点名称:<input id="zsdmc" type="text" style="width:260px;"/>
							<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="xzcs()">确定</a>&nbsp;&nbsp;&nbsp;(注：如果两种方式都选，则默认按照知识点名称确定范围)
					</form>
					</div>
				
				<div region="center" id="x1" style="padding:10px;overflow-x:hidden;overflow-y:hidden;">
					<iframe  id="CFrame4" src=""   name="CFrame4"  frameborder="0"  style="padding:0px;width:100%;height:100%;"></iframe> 
				</div>	
					<div region="east" id="x2" split="false" style="width:209px;padding:10px;">
						<div style="padding:25px;margin-top:100px;margin-left:20px;">
							<center>
								<%--<a class="easyui-linkbutton" iconCls="icon-undo" href="javascript:void(0)" onclick="syy()">上一页</a>--%><br><br><br>
								<a class="easyui-linkbutton" iconCls="icon-redo" href="javascript:void(0)" onclick="xyy()">下一页</a><br><br><br><%--
								跳转到第<input type="text" id="tzy" style="width:30px;">页<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="tz()">跳          转</a><br>
								<br><br>
								--%>
								<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="tj()">提	    交</a>
							</center>
						</div>
					</div>
				</div>
				<div id="scanx" closed="true" class="easyui-window" title="查看详细信息" style="width:400px;height:220px;padding:5px;background: #fafafa;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
			    	<table align="center">						    				
						<tr>
							<td> 
							题干: 
							</td>
							<td>
								<textarea id="tgx" name="tg" style="width:300px;height:130px;visibility:hidden;" disabled="true"></textarea> 					 
							</td>
						</tr>
						<tr>
							<td> 
							答案: 
							</td>
							<td>
								<input id="dax" type="text"  style="width:300px;"  disabled="true"/>
							</td>
						</tr>
					</table>
			 </div>		
			</div>
			<div title="判断题自测">
			<div class="easyui-layout" fit="true" style="width:900px;padding:10px;height:392px">
				<div id="pdcs"  region="north" style="width:900px;padding:10px;height:70px">
					<form>
					请选择所要测试的范围:
						课程名称:<select id="bindTCName1" onChange="geta()" style="width:160px;">
								<option  value="-1">--请选择--</option></select>
						章名称:<select id="bindZh1" onChange="getb()" style="width:160px;">
								<option  value="-1">--请选择--</option></select>
						节名称: <select id="bindK1" onChange="pdcs()" style="width:160px;">
						   		<option  value="-1">--请选择--</option></select>
						<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="pdcs()">确定</a>
						<br>
						您也可以通过知识点选择测试范围:
							知识点名称:<input id="zsdmcp" type="text" style="width:260px;"/>
							<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="pdcs()">确定</a>&nbsp;&nbsp;&nbsp;(注：如果两种方式都选，则默认按照知识点名称确定范围)
					</form>
					<br>
					</div>
			 	
					<div region="center" id="p1" style="padding:10px;overflow-x:hidden;overflow-y:hidden;">
					<iframe  id="CFrame5" src=""   name="CFrame5"  frameborder="0"  style="padding:0px;width:100%;height:100%;"></iframe>
					</div>
					
					<div region="east" split="false" style="width:209px;padding:10px;">
						<div style="padding:25px;margin-top:100px;margin-left:20px;">
							<center>
								<%--<a class="easyui-linkbutton" iconCls="icon-redo" href="javascript:void(0)" onclick="psyy()">上一页</a>--%><br><br><br>
								<a class="easyui-linkbutton" iconCls="icon-redo" href="javascript:void(0)" onclick="pxyy()">下一页</a><br><br><br><%--
								跳转到第<input type="text" id="tzy" style="width:30px;">页<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="ptz()">跳转</a><br>
								<br><br>
								--%>
								<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="ptj()">提	交</a>
							</center>
						</div>
					</div>
				</div>
				<div id="scanp" closed="true" class="easyui-window" title="查看详细信息" style="width:400px;height:220px;padding:5px;background: #fafafa;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
			    	<table align="center">						    				
						<tr>
							<td> 
							题干: 
							</td>
							<td>
								<textarea id="tgp" name="tg" style="width:300px;height:130px;visibility:hidden;" disabled="true"></textarea> 					 
							</td>
						</tr>
						<tr>
							<td> 
							答案: 
							</td>
							<td>
								<input id="dap" type="text"  style="width:300px;"  disabled="true"/>
							</td>
						</tr>
					</table>
			 </div>	
			</div>
			
			<div title="查看选择题答案" style="padding:0px;">
				<table id="xz_tables"></table>
				<div id="xzquery" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;"
		    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
		        <div>
		        	<form>
			            <table>
			                <tr>
			                     <td>
			                    	题干：
			                    </td>
			                    <td><input type="text" name="id" id="name"  required="true"></td>
			                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="xzquery()">查询</a></td>
			                </tr>
			            </table>
		            </form>
		        </div>
		    </div>		
			</div>
			<div title="查看判断题答案" style="padding:0px;">
				<table id="pd_tables"></table>
				<div id="query" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;"
		    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
		        <div>
		        	<form>
			            <table>
			                <tr>
			                    <td>
			                     	  题干：
			                    </td>
			                    <td><input type="text" name="id" id="name1"  required="true"></td>
			                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
			                </tr>
			            </table>
		            </form>
		        </div>
		    </div>		
		</div>
		</div>
		<div id="xzjg" class="easyui-window" closed="true" title="结果分析" style="padding:5px;width:360px;height:350px;"  maximizable="false" minimizable="false" collapsible="false"></div>
	</div>
	</body>
</html>
   