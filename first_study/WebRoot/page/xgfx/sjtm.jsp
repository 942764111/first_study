
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>试卷题目页面</title>
	 <link href="css/login_style.css" type="text/css" rel="stylesheet">
 <link href="css/votecss.css" rel="stylesheet" type="text/css" />

	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">

	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script src="js/Studyplay_vote.js"></script>
	<script type="text/javascript" src="js/ui/datagrid-detailview.js"></script>
	
	
    <link rel="stylesheet" type="text/css" href="css/thickbox.css" />
	
	<script type='text/javascript'>
	 var sjnooo=0;
	  <%
      Integer sjnooo=(Integer)request.getAttribute("sjnooo");
 
     %>
     sjnooo=<%=sjnooo%>;
     
	 $(function(){

		    $('#tt').tabs({
		    	width:'auto',
			    //width:document.body.clientWidth,
			    height:document.body.clientHeight,
			    onSelect:function(title){   
				    if(title=="选 择 题  部  分"){
	                        test1();
					    }else if(title=="判 断 题  部  分"){
                              test2();
						    }else if(title=="操 作 题  部  分"){
                                   test3();
							    }else if(title=="知 识 点  部  分"){
                                       test4();
								    }else if(title=="试  卷  得  分  情  况 "){
                                         test5();
									    }
				 },
				tools:[{
					text:'试卷编号'+sjnooo,
					iconCls:'icon-reload',
					handler: function(){
						
						location.reload();
					}
				}]
			});
	 		   
			//===================dialog=========================
			$('#sjsgfx').dialog({
				buttons:[{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
                        var text1=$("#text1").val();
					    
						if(text1!=""&&text1!="未曾分析！"){
							$.ajax({
								type:"POST",
								dataType:"json",
								url:"tjfx.action",
								data:{"text1":text1},
								success:function (json){
									if(json.tip=="true"){
										$.messager.alert('提示','提交成功','info');
										
					                    //text11=text1;
										}
									 
								}
							});
	
						}else{
							$.messager.alert('提示','您还未分析！','info');
							}
							 
						 
					}
				},{
					text:'清空',
					iconCls:'icon-cancel',
					handler:function(){
					   $("#text1").val("");
					}
				}]
			});
			$('#sjsgdy').dialog({
				buttons:[{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
                        var text2=$("#text2").val();
					    
						if(text2!=""&&text2!="未曾分析！"){
							$.ajax({
								type:"POST",
								dataType:"json",
								url:"tjfx.action",
								data:{"text2":text2},
								success:function (json){
									if(json.tip=="true"){
										$.messager.alert('提示','提交成功','info');
										
					                    //text11=text1;
										}
									 
								}
							});
	
						}else{
							$.messager.alert('提示','您还未分析！','info');
							}
							 
						 
					}
				},{
					text:'清空',
					iconCls:'icon-cancel',
					handler:function(){
					   $("#text2").val("");
					}
				}]
			});
			//===========================================
			 
		
			$('#test6').datagrid({
				
				width:'auto',
				height:415,
				pageSize:10,
				pageList:[10,20,30,40,50],
				nowrap: true,
				striped: true,
				
				url:'listCstu',
				loadMsg:'处理中，请等待...',
				
				frozenColumns:[[
	                {field:'ck',checkbox:true}
	                
				]],
				columns:[[
				    {title:'班级编号',field:'int1',width:100},
					{field:'str1',title:'学生姓名',width:500}
					
				]],
				pagination:true,                             //显示分页
				rownumbers:true                            
				
			});
		    $('#test6').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to} 共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
		    
		});
		function test1(){
			var t1=0;
			  $('#test1').datagrid({
					title:'试题',
					iconCls:'icon-save',
					width:975,//975
					height:480,
					//singleSelect:true,
					pageSize:15,
					pageList:[15,20,30,40,50],
					nowrap: true,
					striped: true,
					url:'listXz',
					loadMsg:'',
					fit:true,
					fitColumns:true,
					frozenColumns:[[
		                {field:'ck',checkbox:true}
		                
					]],
					columns:[[
					    //{title:'编号',field:'int1',width:100},
						{field:'str4',title:'题目',width:500,
	                          formatter:function(value,rec){
							var str=rec.str4;
	                        if(str==null||str==""){
	                     	     str="空";
	                         }else{
	                         	if(str.length>46){
	                         		str=str.substring(0,46)+"...";
	                                 
	                               }
	                                 
	                             }
	                        
	                        return '<a title="'+value+'">'+str+'</a>';
	                           }

							},
						{field:'arr1',title:'图形',width:300,
							formatter:function(value,rec){
							    t1=t1+1;
							    var z1=value[0].bfb;
								var z2=value[1].bfb;
							   
							    var zz="z"+t1;
							   
							return '<span><div id="a'+zz+'a" style="margin-top:0px;width:300px;align:center;">'+
							           '<dl id="studyvote">'+
										 '<dd class="dd"><div class="fl">正确：</div><div class="outbar"><div class="inbar" style="width:'+z1+';background:#39c;"></div></div>'+
										 '<div class="fl">'+z1+'&nbsp;&nbsp;</div>'+
										 '<div class="fl"> 错误：</div><div class="outbar"><div class="inbar" style="width:'+z2+';background:#F00;"></div></div>'+
										 '<div class="fl">'+z2+'</div></dd>'+
							       '</div></span>';
	                          
		                        }
							},
							{field:'str6',title:'关联知识点',width:100}
					]],
					
					pagination:true,                             //显示分页
					rownumbers:true,                             //显示行号
					toolbar:[{
						
						text:'教师手工分析',
						iconCls:'icon-edit',
						handler:function(){
						    var selected = $('#test1').datagrid('getSelections');
						    if(selected.length==1){
						     // location.href = 'ksfx.action?xzth='+selected[0].int1;
						    	$('#sjsgfx').dialog('open');
						    	document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
						    	jQuery.ajax({
								      url:"ksfxjson.action",
								      dataType:"json",
								      data:{"xzth":selected[0].int1},
								      cache:false,
				                      success:function (json){
								    	  $("#text1").val(json.text1);
								    	  $("#tm").html("<font face=\"Comic Sans MS\">&nbsp;题目："+selected[0].str4+"</font>");
								    	  $("#xxhms").html("<font>&nbsp;&nbsp;&nbsp;&nbsp;"+json.xzda+"</font>");
				                      }
								   });//
						    }else{
						       $.messager.alert('提示','请选择一行数据','warning');
						    }
						    
						}
					
					},'-',{
						
						text:'教师手工答疑',
						iconCls:'icon-edit',
						handler:function(){
						    var selected = $('#test1').datagrid('getSelections');
						    if(selected.length==1){
						      //location.href = 'ksfx.action?xzth='+selected[0].int1;
							      $('#sjsgdy').dialog('open');
							      document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
						    	jQuery.ajax({
								      url:"ksfxjson.action",
								      dataType:"json",
								      data:{"xzth":selected[0].int1},
								      cache:false,
				                      success:function (json){
								    	  $("#text2").val(json.text2);
								    	  $("#tm2").html("<font >&nbsp;题目："+selected[0].str4+"</font>");
								    	  $("#xxhms2").html("<font>&nbsp;&nbsp;&nbsp;&nbsp;"+json.xzda+"</font>");
				                      }
								   });//
						    }else{
						       $.messager.alert('提示','请选择一行数据','warning');
						    }
						    
						}
					
					},'-',{
						
						text:'错误学生名单',
						iconCls:'icon-search',
						handler:function(){
						    var selected = $('#test1').datagrid('getSelections');
						    if(selected.length==1){
						      //location.href = 'ksfx.action?xzth='+selected[0].int1;
						        var $win;
								$win = $('#md').window({
									top:document.body.scrollTop+80,   
						            left:document.body.scrollLeft+200
								});
								$win.window('open');
						    	//$('#md').dialog('open');
						    	jQuery.ajax({
								      url:"listCstuSJ.action",
								      dataType:"json",
								      data:{"xzth":selected[0].int1},
								      cache:false,
				                      success:function (json){
								    	  $('#test6').datagrid('reload'); 
				                      }
								   })//
						    	
						    }else{
						       $.messager.alert('提示','请选择一行数据','warning');
						    }
						    
						}
					
					},'-',{
						
						text:'测试题相关材料',
						iconCls:'icon-search',
						handler:function(){
						    var selected = $('#test1').datagrid('getSelections');
						    if(selected.length==1){
						      //location.href = 'ksfx.action?xzth='+selected[0].int1+'&ys=1';
						    	$('#zl').dialog('open');
						    	jQuery.ajax({
								      url:"listCstuSJ.action",
								      dataType:"json",
								      data:{"xzth":selected[0].int1,"glzsd":selected[0].str6},
								      cache:false,
				                      success:function (json){
								    	  $('#test7').datagrid('reload'); 
								    	  $('#mb1').show();
								    	  $('#mb2').show();
								    	  zlzsddy(json.list);
				                      }
								   })//
						    }else{
						       $.messager.alert('提示','请选择一行数据','warning');
						    }
						    
						}
					
					}]
				});
			    $('#test1').datagrid('getPager').pagination({
					 displayMsg:'当前显示从{from}到{to} 共{total}记录',
					 beforePageText:'第',
					 afterPageText:'页'
				 });
			}
		function test2(){
			var t2=0;
			$('#test2').datagrid({
				title:'试题',
				iconCls:'icon-save',
				width:975,
				height:480,
				//singleSelect:true,
				pageSize:15,
				pageList:[15,20,30,40,50],
				nowrap: true,
				striped: true,
				url:'listPd',
				loadMsg:'处理中，请等待...',
				fit:true,
				fitColumns:true,
				frozenColumns:[[
	                {field:'ck',checkbox:true}
	                
				]],
				columns:[[
				    //{title:'编号',field:'int1',width:100},
					{field:'str4',title:'题目',width:500,
						formatter:function(value,rec){
						var str=rec.str4;
                        if(str==null||str==""){
                     	     str="空";
                         }else{
                         	if(str.length>46){
                         		str=str.substring(0,46)+"...";
                                 
                               }
                                 
                             }
                        
                        return '<a title="'+value+'">'+str+'</a>';
                           }
						},
					{field:'arr1',title:'图形',width:300,
						formatter:function(value,rec){
						    t2=t2+1;
						    var z1=value[0].bfb;
							var z2=value[1].bfb;
						   
						    var zz="z"+t2;
						    
						return '<span><div id="a'+zz+'a" style="margin-top:0px;width:300px;align:center;">'+
						           '<dl id="studyvote">'+
									 '<dd class="dd"><div class="fl">正确：</div><div class="outbar"><div class="inbar" style="width:'+z1+';background:#39c;"></div></div>'+
									 '<div class="fl">'+z1+'&nbsp;&nbsp;</div>'+
									 '<div class="fl">错误：</div><div class="outbar"><div class="inbar" style="width:'+z2+';background:#f00;"></div></div>'+
									 '<div class="fl">'+z2+'</div></dd>'+
						       '</div></span>';
                          
	                        }
						},
						{field:'str6',title:'关联知识点',width:100}
				]],
				pagination:true,                             //显示分页
				rownumbers:true,                             //显示行号
				toolbar:[{
					
					text:'教师手工分析',
					iconCls:'icon-edit',
					handler:function(){
					    var selected = $('#test2').datagrid('getSelections');
					    if(selected.length==1){
					      //location.href = 'ksfx.action?pdth='+selected[0].int1;//////////////////test2/////////////////////
						      $('#sjsgfx').dialog('open');
						      document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
					    	jQuery.ajax({
							      url:"ksfxjson.action",
							      dataType:"json",
							      data:{"pdth":selected[0].int1},
							      cache:false,
			                      success:function (json){
							    	  $("#text1").val(json.text1);
							    	  $("#tm").html("<font face=\"Comic Sans MS\">&nbsp;题目："+selected[0].str4+"</font>");
							    	  $("#xxhms").html("");
			                      }
							   });//
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    }
					    
					}
				
				},'-',{
					
					text:'教师手工答疑',
					iconCls:'icon-edit',
					handler:function(){
					    var selected = $('#test2').datagrid('getSelections');
					    if(selected.length==1){
					      //location.href = 'ksfx.action?pdth='+selected[0].int1;
						      $('#sjsgdy').dialog('open');
						      document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
					    	jQuery.ajax({
							      url:"ksfxjson.action",
							      dataType:"json",
							      data:{"pdth":selected[0].int1},
							      cache:false,
			                      success:function (json){
							    	  $("#text2").val(json.text2);
							    	  $("#tm2").html("<font >&nbsp;题目："+selected[0].str4+"</font>");
							    	  $("#xxhms2").html("");
			                      }
							   });//
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    }
					    
					}
				
				},'-',{
					
					text:'错误学生名单',
					iconCls:'icon-search',
					handler:function(){
					    var selected = $('#test2').datagrid('getSelections');
					    if(selected.length==1){
					    	var $win;
							$win = $('#md').window({
								top:document.body.scrollTop+80,   
					            left:document.body.scrollLeft+200
							});
							$win.window('open');
					      
						      //$('#md').dialog('open');
					    	jQuery.ajax({
							      url:"listCstuSJ.action",
							      dataType:"json",
							      data:{"pdth":selected[0].int1},
							      cache:false,
			                      success:function (json){
							    	  $('#test6').datagrid('reload'); 
			                      }
							   })//
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    }
					    
					}
				
				},'-',{
					
					text:'测试题相关材料',
					iconCls:'icon-search',
					handler:function(){
					    var selected = $('#test2').datagrid('getSelections');
					    if(selected.length==1){
					      
						      $('#zl').dialog('open');
					    	jQuery.ajax({
							      url:"listCstuSJ.action",
							      dataType:"json",
							      data:{"pdth":selected[0].int1,"glzsd":selected[0].str6},
							      cache:false,
			                      success:function (json){
							    	  $('#test7').datagrid('reload'); 
							    	  $('#mb1').show();
							    	  $('#mb2').show();
							    	  zlzsddy(json.list);
			                      }
							   })//
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    }
					    
					}
				
				}]
			});
			$('#test2').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to} 共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
			}
		function test3(){
			var t3=0;
			$('#test3').datagrid({
				title:'试题',
				iconCls:'icon-save',
				width:975,
				height:480,
				//singleSelect:true,
				pageSize:15,
				pageList:[15,20,30,40,50],
				nowrap: true,
				striped: true,
				
				url:'listCzt',
				loadMsg:'处理中，请等待...',
				fit:true,
				fitColumns:true,
				frozenColumns:[[
	                {field:'ck',checkbox:true}
	                
				]],
				columns:[[
				    //{title:'操作小题编号',field:'int1',width:100},
				    //{title:'所属操作大题编号',field:'int2',width:100},
					{field:'str4',title:'操作小题题目',width:180,
						formatter:function(value,rec){
						var str=rec.str4;
                        if(str==null||str==""){
                     	     str="空";
                         }else{
                         	if(str.length>24){
                         		str=str.substring(0,24)+"...";
                                 
                               }
                                 
                             }
                        
                        return '<a title="'+value+'">'+str+'</a>';
                           }
						},
					{field:'str5',title:'所属操作大题',width:180,
							formatter:function(value,rec){
							var str=rec.str5;
	                        if(str==null||str==""){
	                     	     str="空";
	                         }else{
	                         	if(str.length>24){
	                         		str=str.substring(0,24)+"...";
	                                 
	                               }
	                                 
	                             }
	                        
	                        return '<a title="'+value+'">'+str+'</a>';
	                           }
							},
					{field:'arr1',title:'图形',width:300,
						formatter:function(value,rec){
						    t3=t3+1;
						    var z1=value[0].bfb;
							var z2=value[1].bfb;
						   
						    var zz="z"+t3;
						   
						return '<span><div id="a'+zz+'a" style="margin-top:0px;width:300px;align:center;">'+
						           '<dl id="studyvote">'+
									 '<dd class="dd"><div class="fl">正确：</div><div class="outbar"><div class="inbar" style="width:'+z1+';background:#39c;"></div></div>'+
									 '<div class="fl">'+z1+'&nbsp;&nbsp;</div>'+
									 '<div class="fl">错误：</div><div class="outbar"><div class="inbar" style="width:'+z2+';background:#f00;"></div></div>'+
									 '<div class="fl">'+z2+'</div></dd>'+
						       '</div></span>';
                          
	                        }
						},
						{field:'str6',title:'关联知识点',width:100}
				]],
				pagination:true,                             //显示分页
				rownumbers:true,                             //显示行号
				toolbar:[{
					
					text:'教师手工分析',
					iconCls:'icon-edit',
					handler:function(){
					    var selected = $('#test3').datagrid('getSelections');
					    if(selected.length==1){
					      
						      $('#sjsgfx').dialog('open');
						      document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
					    	jQuery.ajax({
							      url:"ksfxjson.action",
							      dataType:"json",
							      data:{"czth":selected[0].int1,"czdth":selected[0].int2},
							      cache:false,
			                      success:function (json){
							    	  $("#text1").val(json.text1);
							    	  $("#tm").html("<span >&nbsp;所属大题目："+selected[0].str5+"</span>");
							    	  $("#xxhms").html("<font>&nbsp;&nbsp;&nbsp;&nbsp;小题题目："+selected[0].str4+"</font>");
			                      }
							   });//
					     
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    	 
					    }
					    
					}
				
				},'-',{
					
					text:'教师手工答疑',
					iconCls:'icon-edit',
					handler:function(){
					    var selected = $('#test3').datagrid('getSelections');
					    if(selected.length==1){
					      //location.href = 'ksfx.action?czth='+selected[0].int1+'&czdth='+selected[0].int2;
						      $('#sjsgdy').dialog('open');
						      document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
					    	jQuery.ajax({
							      url:"ksfxjson.action",
							      dataType:"json",
							      data:{"czth":selected[0].int1,"czdth":selected[0].int2},
							      cache:false,
			                      success:function (json){
							    	  $("#text2").val(json.text2);
							    	  $("#tm2").html("<span >&nbsp;所属大题目："+selected[0].str5+"</span>");
							    	  $("#xxhms2").html("<font>&nbsp;&nbsp;&nbsp;&nbsp;小题题目："+selected[0].str4+"</font>");
			                      }
							   });//
					     
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    	 
					    }
					    
					}
				
				},'-',{
					
					text:'错误学生名单',
					iconCls:'icon-search',
					handler:function(){
					    var selected = $('#test3').datagrid('getSelections');
					    if(selected.length==1){
					    	var $win;
							$win = $('#md').window({
								top:document.body.scrollTop+80,   
					            left:document.body.scrollLeft+200
							});
							$win.window('open');
					      
						      //$('#md').dialog('open');
					    	jQuery.ajax({
							      url:"listCstuSJ.action",
							      dataType:"json",
							      data:{"czth":selected[0].int1,"czdth":selected[0].int2},
							      cache:false,
			                      success:function (json){
							    	  $('#test6').datagrid('reload'); 
			                      }
							   })//
					     
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    	 
					    }
					    
					}
				
				},'-',{
					
					text:'测试题相关材料',
					iconCls:'icon-search',
					handler:function(){
					    var selected = $('#test3').datagrid('getSelections');
					    if(selected.length==1){
					     
						      $('#zl').dialog('open');
					    	jQuery.ajax({
							      url:"listCstuSJ.action",
							      dataType:"json",
							      data:{"czth":selected[0].int1,"czdth":selected[0].int2,"glzsd":selected[0].str6},
							      cache:false,
			                      success:function (json){
							    	  $('#test7').datagrid('reload'); 
							    	  $('#mb1').show();
							    	  $('#mb2').show();
							    	  zlzsddy(json.list);
			                      }
							   })//
					     
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    	
					    }
					    
					}
				
				}]
			});
			$('#test3').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to} 共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
			}
		function test4(){
			var t4=0;
            $('#test4').datagrid({
				title:'试题',
				iconCls:'icon-save',
				width:975,
				height:480,
				//singleSelect:true,
				pageSize:15,
				pageList:[15,20,30,40,50],
				nowrap: true,
				striped: true,
				
				url:'listZsd',
				loadMsg:'处理中，请等待...',
				fit:true,
				fitColumns:true,
				frozenColumns:[[
	                {field:'ck',checkbox:true}
	                
				]],
				columns:[[
				    //{title:'编号',field:'int1',width:70},
					{field:'str4',title:'名称',width:200},
					{field:'str5',title:'知识点描述',width:300,
                           formatter:function(value,rec){
						        var str=rec.str5;
                               if(str==null||str==""){
                            	     str="空";
                                }else{
                                	if(str.length>30){
                                		str=str.substring(0,30)+"...";
                                        
                                      }
                                        
                                    }
                               
                               return '<a title="'+value+'">'+str+'</a>';
                           }
						},
					{field:'arr1',title:'图形',width:300,
						formatter:function(value,rec){
						    t4=t4+1;
						    var z1=value[0].bfb;
							var z2=value[1].bfb;
						   
						    var zz="z"+t4;
						    
						return '<span><div id="a'+zz+'a" style="margin-top:0px;width:300px;align:center;">'+
						           '<dl id="studyvote">'+
									 '<dd class="dd"><div class="fl">正确：</div><div class="outbar"><div class="inbar" style="width:'+z1+';background:#39c;"></div></div>'+
									 '<div class="fl">'+z1+'&nbsp;&nbsp;</div>'+
									 '<div class="fl">错误：</div><div class="outbar"><div class="inbar" style="width:'+z2+';background:#f00;"></div></div>'+
									 '<div class="fl">'+z2+'</div></dd>'+
						       '</div></span>';
                          
	                        }
						}
				]],
				pagination:true,                             //显示分页
				rownumbers:true,                             //显示行号
				toolbar:[{
					
					text:'教师手工分析',
					iconCls:'icon-edit',
					handler:function(){
					    var selected = $('#test4').datagrid('getSelections');
					    if(selected.length==1){
					      //location.href = 'ksfx.action?zsdbh='+selected[0].int1;
						       $('#sjsgfx').dialog('open');
						       document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
					    	jQuery.ajax({
							      url:"ksfxjson.action",
							      dataType:"json",
							      data:{"zsdbh":selected[0].int1},
							      cache:false,
			                      success:function (json){
							    	  $("#text1").val(json.text1);
							    	  $("#tm").html("<font face=\"Comic Sans MS\">&nbsp;知识点名称："+selected[0].str4+"</font>");
							    	  $("#xxhms").html("<font>&nbsp;&nbsp;&nbsp;&nbsp;描述 --> "+selected[0].str5+"</font>");
			                      }
							   });//
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    }
					    
					}
				
				},'-',{
					
					text:'教师手工答疑',
					iconCls:'icon-edit',
					handler:function(){
					    var selected = $('#test4').datagrid('getSelections');
					    if(selected.length==1){
					      //location.href = 'ksfx.action?zsdbh='+selected[0].int1;
						      $('#sjsgdy').dialog('open');
						      document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
					    	jQuery.ajax({
							      url:"ksfxjson.action",
							      dataType:"json",
							      data:{"zsdbh":selected[0].int1},
							      cache:false,
			                      success:function (json){
							    	  $("#text2").val(json.text2);
							    	  $("#tm2").html("<font >&nbsp;知识点名称："+selected[0].str4+"</font>");
							    	  $("#xxhms2").html("<font>&nbsp;&nbsp;&nbsp;&nbsp;描述 --> "+selected[0].str5+"</font>");
			                      }
							   });//
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    }
					    
					}
				
				},'-',{
					
					text:'知识点相关材料',
					iconCls:'icon-search',
					handler:function(){
					    var selected = $('#test4').datagrid('getSelections');
					    if(selected.length==1){
					      //location.href = 'ksfx.action?zsdbh='+selected[0].int1+'&ys=1';
						       $('#zl').dialog('open');$('#zl').dialog('close');$('#zl').dialog('open');
					    	jQuery.ajax({
							      url:"listCstuSJ.action",
							      dataType:"json",
							      data:{"zsdbh":selected[0].int1,"glzsd":selected[0].str4},
							      cache:false,
			                      success:function (json){
							    	  $('#test7').datagrid('reload'); 
							    	  $('#mb1').hide();
							    	  $('#mb2').hide();
							    	  
			                      }
							   })//
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    }
					    
					}
				
				}]
			});
			$('#test4').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to} 共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
			}
		function test5(){
			$('#test5').datagrid({
				title:'得分情况',
				iconCls:'icon-save',
				width:'auto',
				height:200,//480
				//singleSelect:true,
				pageSize:15,
				pageList:[15,20,30,40,50],
				nowrap: true,
				striped: true,
				
				url:'listFs',
				loadMsg:'处理中，请等待...',
				//fit:true,
				fitColumns:true,
				frozenColumns:[[
	                {field:'ck',checkbox:true}
	                
				]],
				columns:[[
				    {title:'试卷编号',field:'int1',width:100},
					{field:'int2',title:'60分以下 人数',width:180},
					{field:'int3',title:'60~80分  人数',width:180},
					{field:'int4',title:'80~100分  人数',width:180},
					{field:'str5',title:'平均分',width:100},
					{field:'str4',title:'总分',width:100}
				]],
				//pagination:true,                             //显示分页
				//rownumbers:true,                             //显示行号
				toolbar:[{
					
					text:'教师手工添加对此次测试的评价',
					iconCls:'icon-edit',
					handler:function(){
					    var selected = $('#test5').datagrid('getSelections');
					    if(selected.length==1){
					      location.href = 'fsfx.action?total6='+selected[0].int2+'&total8='+selected[0].int3+'&total10='+selected[0].int4;
					      window.event.returnValue=false;
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    }
					    
					}
				
				},'-']
			});
			$('#test5').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to} 共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
			}
	   function getWidth(percent){ 
		    return document.body.clientWidth*percent; 
		   
		};
	   function zlzsddy(data){
		   $("#zsd").html("");
		   $.each(data,function(n,value){
				     
					 $("#zsd").append("<option value=\""+value.id.zsdbh+"\">"+value.zsdmc+"</option>");
				
			  });
	   }
		
  </script>
   <SCRIPT   LANGUAGE="JavaScript">           
   function   textCounter(field,   countfield,   maxlimit)   {     
 //   定义函数，传入3个参数，分别为表单区的名字，表单域元素名，字符限制；     
   if   (field.value.length   >   maxlimit)       
	   //如果元素区字符数大于最大字符数，按照最大字符数截断；      
	    field.value   =   field.value.substring(0,   maxlimit);       
   else       //在记数区文本框内显示剩余的字符数；    
	      countfield.value   =   maxlimit   -   field.value.length;      
    }        
</SCRIPT> 
  <script type="text/javascript" src="uploadify/swfobject.js"></script>
	<script type="text/javascript" src="uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<link href="uploadify/uploadify.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
		

		$(function(){
			$('#test7').datagrid({
				width:'auto',
				height:415,
				pageSize:15,
				pageList:[15,30,40,50],
				nowrap: true,
				striped: true,
				url:'listZl',
				loadMsg:'处理中，请等待...',
				fit:true,
				fitColumns:true,
				frozenColumns:[[
		            {field:'ck',checkbox:true}
		            
				]],
				columns:[[
		           // {title:'资料编号',field:'int1',width:100},
				    //{title:'素材编号',field:'int2',width:100},
					{field:'str2',title:'资料名称',width:300},
					
					{field:'str6',title:'对应知识点',width:300
				     }
				]],
				pagination:true,                             //显示分页
				rownumbers:true,                             //显示行号
				toolbar:[{
					
					text:'上传新资料',
					iconCls:'icon-ok',
					handler:function(){
							$('#btnadd').linkbutton('enable');
							$('#insert').form('clear');
							//$.messager.alert('提示','1.文本，您只能上传swf格式(可在本系统中下载swf转换器)<br/>2.视频，您只能上传flv格式文件！','info',function(d){
								$.ajax({
				                      type:"POST",
				                      url:"tt_uupload_file.action",
				                      success:function(json){
									      $("#zsdbh").val(json.zsdbh);
				                      }
				                	});
							     $('#add').window('open');
							     document.getElementById("ggggg3").style.visibility="visible";//注意：用$()方式不好用
							//});
							
					}
				
				},'-',{
					
					text:'从已有资料中指定',
					iconCls:'icon-edit',
					handler:function(){
						//调用小猪的	
						location.href ='zlzsddy1.action';
						// 在此处加上以下代码既可    
						window.event.returnValue = false;
						 
					}
				
				},'-',{
					text:'删除',
					iconCls:'icon-cancel',
					handler:deleteRow
  			   },'-',{
					text:'查看',
					iconCls:'icon-search',
					handler:function(){
			  				var selected = $('#test7').datagrid('getSelected');
			  	        	if(selected){
			  	        		$('#zlzxck').window('open');//encodeURI(encodeURI(selected.str2))这是jsp与jsp之间传值防止乱码
			  	        		CFrame.document.location.href='../../spdh/dzbj.jsp?zlmc=' + encodeURI(encodeURI(selected.str2)) +
			  	      		  '&path=' + selected.str4 + '&filename=' + selected.str1 + '&zlid=' + selected.int1+'&type=xgfx';
			  	                // 在此处加上以下代码既可    
			  	  			   window.event.returnValue = false;
			  	        					  					  			        		       			
			  			  }
			  	        	else{
			  	    			$.messager.alert('提示','请选择一行数据','warning');
			  	        	}
					}
  			   }]                            
				
			});
			$('#test7').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to} 共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
			 
			
});

		function addscwj(){
			
			var zlms = $("#addzlms").val();
            var lxm=$("#addlxm").val();
            var zlscm = $("#addzlscm").val();
            var zlly = $("#addzlly").val();
         //取消upload.java中静态变量之后，添加到此处的
			var zsdbh = $("#zsdbh").val();
			if(zsdbh==0){
				  zsdbh=$("#zsd").val();
				}
			var userId=$("#userid").val();
			var changdu=$("#changdu").val();
			var scrq=$("#scrq").val();
			var filePath=$("#filePath").val();
			var fileName=$("#fileName").val();
			var zlmc=$("#zlmc").val();
						
						if(zlly==""||lxm==""||zlscm==""||zlms==""||zlmc==""){
							$.messager.alert('警告','<font color=red>*</font>标记的必须填！','warning');

						}else{
							$.ajax({
					            type:"POST",
					            url:"t_updatescwj.action",
					           
					             data:{"zlly":zlly,"zlscm":zlscm,"zlms":zlms,"lxm":lxm,"userId":userId,"changdu":changdu,"scrq":scrq,"filepath":filePath,"fileName":fileName,"zlmc1":zlmc,"zsdbh":zsdbh},
					            success:function(){
					            	$('#add').dialog('close');		
					            	document.getElementById("ggggg3").style.visibility="hidden";//注意：用$()方式不好用			            	
					            	$.messager.alert('提示','添加信息成功!!!','info',function(){
					            		$('#test7').datagrid('reload');
					            	});
	
					            	
							   }
				      	    });

						}
				
				}
			function closescwj(){
					$('#add').dialog('close');
					document.getElementById("ggggg3").style.visibility="hidden";//注意：用$()方式不好用
				}
		

		function deleteRow(index){	        	
    		var selected = $('#test7').datagrid('getSelected');
        	if(selected){
        		$.messager.confirm('删除','确认删除吗?',function(d){					        	
	            	if(d){
		             	$.ajax({
		                      type:"POST",
		                      url:"t_deletescwj.action",
		                      data:{"no":selected.int2,"zsdmc":selected.str6},
		                      success:function(){
		                    	  $('#test7').datagrid('reload');
		                      }
		                	});
				         }
        		 });			        		       			
		  }
        	else{
    			$.messager.alert('提示','请选择一行数据','warning');
        	}
		 }
		
		
	</script>
    <script type="text/javascript">
      var flags=false;
    	$(document).ready(function() {
    		$('#fileupload').uploadify({
			    'uploader'  : 'uploadify/uploadify.swf',
			    'script'    : 't_upload_file.action?userId=<%=session.getAttribute("uid")%>',
			    'cancelImg' : 'uploadify/cancel.png',
			    'auto'      : true,
			    'multi'          : false, 
			   // 'buttonText'     : 'brower', 
			    //'simUploadLimit' : 3, 
			    'sizeLimit'      : 102400000, 
			    //'queueSizeLimit' : 2, 
			    'queueID'        : 'fileQueue',
			    'fileDesc'       : '*.flv; *.swf; *.pdf',
                'fileExt'        : '*.flv; *.swf; *.pdf',
                'fileDataName'    : 'fileupload',
                'removeCompleted'	:	false,
                'wmode'	:	'transparent',
                'buttonImg' :'uploadify/_upload.png',
                onComplete: function (event, queueID, fileObj, response, data) { 
                	flags=true; 
                	//alert(flags);
                	var r = jQuery.parseJSON(response);
    				//$('#zlmc').val(r.zlmc1);
    				$('#addlxm').val(r.lxm);
    				$('#changdu').val(r.changdu);
    				$('#scrq').val(r.scrq);
    				$('#fileName').val(r.fileName);
    				$('#filePath').val(r.filepath);
    				$('#userid').val('<%=session.getAttribute("uid")%>');
                    jQuery("#" + jQuery(this).attr('id') + ID).find('.percentage').text(' - 完成'); 
                	         
				},
                onError: function(event, queueID, fileObj) {   
					alert("文件:" + fileObj.name + "上传失败");   
				},   
				onCancel: function(event, queueID, fileObj){   
					alert("取消了" + fileObj.name);   
				}
 			});
    	});
    </script>
    

</head>
<body >

	<div id="tt" title="" style="overflow:hidden;">
		
		<div title="选 择 题  部  分"  style="padding:0px;">
			<table id="test1" >
				
			</table>
		</div>
		
		<div title="判 断 题  部  分"   style="padding:0px;">
			<table id="test2" >
				
			</table>
		</div>
		
		<div title="操 作 题  部  分"   style="padding:0px;">
			<table id="test3" >
				
			</table>
		</div>
		<div title="知 识 点  部  分"   style="padding:0px;">
			<table id="test4" >
				
			</table>
		</div>
		
		<div title="试  卷  得  分  情  况 "   style="padding:0px;">
			<table id="test5" ></table>
			
		</div>
	
		
	</div>
    <div id="sjsgfx" class="easyui-dialog" closed="true" title="教师手工分析" icon="icon-edit" style="padding:0px;width:800px;height:450px;" >
	   <div align="center" style="visibility:hidden;" id="ggggg1">
	       <table width="100%" height="100%" align="center"  border="0px" class="login_text">
	       <s:form id="form1" name="form1" >  
	               <tr>
				    <td height="40%" align="center"><table><tr><td align="left">
				      <span id="tm"></span><br><br>
				      <span id="xxhms" ></span>
				    </td></tr></table>
				     
				      
				   </td>
				  </tr>   
	               <tr>
				    <td height="60%" align="center">
				     <hr size="1px"color="#A4BED4"/><br>
				      <p>输入您 的 内 容 (<300 字)</p>
				      <textarea name="text1" cols="70" rows="10" id="text1" onkeyup="textCounter(this.form.text1,this.form.remLen1,300);"></textarea><br>
				        <div >
				                         尚能输入 <input   readonly   type=text   name=remLen1   size=4   maxlength=4   value="300">个字符</div> 
				      </td>
				  </tr>  
		    </s:form>  
	       </table>
	    </div>
	         
	</div>
	<div id="sjsgdy" class="easyui-dialog" closed="true" title="教师手工答疑" icon="icon-edit" style="padding:0px;width:800px;height:450px;"  >
	   <div align="center" style="visibility:hidden;" id="ggggg2">
	       <table width="100%" height="100%" align="center"  border="0px" class="login_text">
	       <s:form id="form1" name="form1" >  
	               <tr>
				    <td height="40%" align="center"><table><tr><td align="left">
				       <span id="tm2"></span><br><br>
				      <span id="xxhms2" ></span>
				    </td></tr></table>
				     
				   </td>
				  </tr>   
	               <tr>
				    <td height="60%" align="center">
				     <hr size="1px"color="#A4BED4"/><br>
				      <p>输入您 的 内 容 (<300 字)</p>
				      <textarea name="text2" cols="70" rows="10" id="text2" onkeyup="textCounter(this.form.text2,this.form.remLen1,300);"></textarea><br>
				        <div >
				                         尚能输入 <input   readonly   type=text   name=remLen1   size=4   maxlength=4   value="300">个字符</div> 
				      </td>
				  </tr>  
		    </s:form>  
	       </table>
	    </div>
	         
	</div>
	<div id="md" class="easyui-dialog" closed="true" title="错误学生名单" icon="icon-edit" style="padding:0px;width:800px;height:450px;">
	   <div >
	       <table id="test6" ></table>
	    </div>
	         
	</div>
	<div class="easyui-dialog" id="zl" closed="true" title="相关素材" icon="icon-edit" style="padding:0px;width:750px;height:450px;">
	           <table id="test7" >
			   </table>	
			  <div id="add" icon="icon-add" title="添加" class="easyui-dialog" closed="true" style="padding:5px;width:520px;height:450px;align:center;" >
			    <div style="visibility:hidden;" id="ggggg3">
			    		<div>
							<span style="color:red;">
								*&nbsp;
							</span>
							<span style="font-size:9pt;">
								1.文本，您只能上传pdf格式<br/>2.视频，您只能上传flv格式文件！
							</span>
						</div><br/>
					   <div id="fileQueue"></div>
						<input type="file" name="fileupload" id="fileupload" />
					    <s:form id="insert">
					    	<table align="left" class="login_text" >
					    	  <tr style="display:none;">
					    			<td>
					    				zsdbh编号：
					    			</td>
					    			<td>
					    				<input type="text" id="zsdbh" />
					    			</td>
					    		</tr>
					    		<tr style="display:none;">
						 			<td> 
										userId:
									</td>
									<td>
										<input id="userid" type="text"/>
									</td>
								</tr> 
						<tr style="display:none;">
							<td> 
								长度:
							</td>
							<td>
								<input type="text" id="changdu"  style="width:250px;" maxlength="30"/>
							</td>
						</tr>
						<tr style="display:none;">
							<td> 
								上传日期:
							</td>
							<td>
								<input type="text" id="scrq"  style="width:250px;" maxlength="30"/>
							</td>
						</tr>
						<tr style="display:none;">
							<td> 
								文件路径:
							</td>
							<td>
								<input type="text" id="filePath"  style="width:250px;" maxlength="30"/>
							</td>
						</tr>
						<tr style="display:none;">
							<td> 
								文件名:
							</td>
							<td>
								<input type="text" id="fileName"  style="width:250px;" maxlength="30"/>
							</td>
						</tr>
						<tr >
							<td> 
								资料名称:
							</td>
							<td>
								<input type="text" id="zlmc"  style="width:250px;" maxlength="30"/><span style="color:red;">
											*&nbsp;
										</span>
										必填<br/>
							</td>
						</tr>
						<!--会选择隐藏的部分  -->
					    	    <tr id="mb1">
						 			<td > 
										资料对应知识点:
									</td>
									<td>
										<select id="zsd" ></select>&nbsp;&nbsp;&nbsp;&nbsp;
										
									</td>
								</tr>
								<tr id="mb2">
						 			<td height="10px"></td>
									<td >
										<span style="color:red;">
											*&nbsp;
										</span>
										请选择该资料对应本题的哪个知识点<br/>
									</td>
								</tr>
								<tr style="display:none;">
						 			<td height="10px"> 
										类型名:<br/>
									</td>
									<td>
										<!--  <s:select id="addlxm" list="wjlxlist" listKey="lxm" listValue="lxm" ></s:select>-->
										<input id="addlxm"/>&nbsp;&nbsp;&nbsp;&nbsp;
										<span style="color:red;">
											*&nbsp;
										</span>
										请选择上传文件的类型名<br/>
									</td>
								</tr>
								<tr>
									<td> 
										资料来源:<br/>
									</td>
									<td>
										<input type="text" id="addzlly" />&nbsp;&nbsp;
										<span style="color:red;">
											*&nbsp;
										</span>
										请填写上传文件的来源<br/>
									</td>
								</tr>
								<tr>
									<td> 
										资料素材名:<br/>
									</td>
									<td>
										<input type="text" id="addzlscm" />&nbsp;&nbsp;
										<span style="color:red;">
											*&nbsp;
										</span>
										请填写资料素材名<br/>
									</td>
								</tr>
					    		<tr>
									<td> 
										资料描述: <br/><br/>
									</td>
									<td>
									    <textarea name="text3" cols="10" rows="7" id="addzlms" onkeyup="textCounter(this.form.text3,this.form.remLen1,100);" style="padding:0;width:300px;height:100px;"></textarea>
										<span style="color:red;">
											*
										</span>
										描述<br/>
									</td>
								</tr>
								<tr>
								    <td> </td>
								  <td colspan="2" align="right">
									<a class="easyui-linkbutton" iconCls="icon-ok"
										href="javascript:void(0)" onclick="if(flags){addscwj();flags=false;}else{$.messager.alert('提示','您未上传呢！','info');}">提交</a>
									<a class="easyui-linkbutton" iconCls="icon-cancel"
										href="javascript:void(0)" onclick="closescwj();flags=false;">取消</a>
								  </td>
					             </tr>
							</table>
					    </s:form></div>
			   </div>
	         
	</div>
	
	<div id="zlzxck" class="easyui-dialog" closed="true" title="资料在线查看" icon="icon-edit" maximizable="true"  minimizable="true" style="padding:0px;width:800px;height:450px;overflow-x:hidden;overflow-y:hidden;">
	   
	       <iframe name="CFrame" id="CFrame" frameborder="0" style="padding:0px;width:100%;height:100%;"></iframe>
	        
	</div>
</body>
</html>