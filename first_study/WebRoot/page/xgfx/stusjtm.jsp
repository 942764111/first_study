
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
   <%
     String text1=(String)request.getAttribute("sjfx");
     String text=(String)request.getAttribute("thwfx");
     %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>试卷题目页面</title>
 
    <link href="css/login_style.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
<style type="text/css">
	   
	div.container{width:80%;margin: 0 auto;padding:10px 0;text-align:left}
	div.content{float:left;width:50%;padding:6px 0;margin:6px 0;background: #E0EBFF}
	div.nav{float:right;width:15%;padding:6px 0;margin:6px 0;background:#EAF0FF }

</style>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
		
    <link rel="stylesheet" type="text/css" href="css/thickbox.css" />

	<script type='text/javascript'>
	$(function(){
		   $("#text1").val("<%=text1%>");
		  
	     }); 
	</script>
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
								    }else if(title=="教师对本次测试评价"){
	                                     test5();
									    }
				 }
			});
		
       });
     function test1(){
    	 $('#test1').datagrid({
				title:'试题',
				iconCls:'icon-save',
				width:975,
				height:470,
				//singleSelect:true,
				pageSize:15,
				pageList:[15,20,30,40,50],
				nowrap: true,
				striped: true,
				
				url:'stulistXz',
				loadMsg:'',
				fit:true,
				fitColumns:true,
				frozenColumns:[[
	                {field:'ck',checkbox:true}
	                
				]],
				columns:[[
				    //{title:'选择题编号',field:'int1',width:100},
					{field:'str4',title:'题目',width:300,
						formatter:function(value,rec){
						var str=rec.str4;
                     if(str==null||str==""){
                  	     str="空";
                      }else{
                      	if(str.length>32){
                      		str=str.substring(0,32)+"...";
                              
                            }
                              
                          }
                     
                     return '<a title="'+value+'">'+str+'</a>';
                        }
						},
					{field:'str2',title:'你的答案对或错',width:100},
					{field:'str6',title:'关联知识点',width:200},
					{field:'str1',title:'教师分析',width:100,
						formatter:function(value,rec){
							 var ms="";
							 if(value.length<=10){
	                              ms=value;          
							  }else{
	                              ms=value.substring(0, 10)+"...";
							 }
                          return '<a title="'+value+'">'+ms+'</a>';
                    }
                 },
					{field:'str5',title:'教师答疑',width:100,
                 	formatter:function(value,rec){
							 var ms="";
							 if(value.length<=10){
	                             ms=value;          
							  }else{
	                             ms=value.substring(0, 10)+"...";
							 }
	                         return '<a title="'+value+'">'+ms+'</a>';
                     }
     			}
				]],
				pagination:true,                             //显示分页
				rownumbers:true,                             //显示行号
             toolbar:[{
					
					text:'查看教师分析的详细内容',
					iconCls:'icon-search',
					handler:function(){
					    var selected = $('#test1').datagrid('getSelections');
					    if(selected.length==1){
					      //$('#sjsgfx').dialog('open');
					      var $win;
					      $win = $('#sjsgfx').window({
						      top:document.body.scrollTop+10,   
			                  left:document.body.scrollLeft+20
					      });
					      $win.window('open');
					      document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
		    	          $("#text2").val(selected[0].str1);
				    	  $("#tm").html("<span class=\"login_txt\">&nbsp;题目："+selected[0].str4+"&nbsp;&nbsp;&nbsp;正确答案：<font color=red>"+selected[0].str8+"</font></span>");
				    	  $("#xxhms").html("<font class=\"login_txt\">&nbsp;&nbsp;&nbsp;&nbsp;"+selected[0].str7+"</font>");
			                   
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    }
					    
					}
				
				},'-',{
					
					text:'查看教师答疑的详细内容',
					iconCls:'icon-search',
					handler:function(){
					    var selected = $('#test1').datagrid('getSelections');
					    if(selected.length==1){
					          //$('#sjsgdy').dialog('open');
					          var $win;
						      $win = $('#sjsgdy').window({
							      top:document.body.scrollTop+10,   
				                  left:document.body.scrollLeft+20
						      });
						      $win.window('open');
					          document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
						      $("#text3").val(selected[0].str5);
					    	  $("#tm2").html("<span class=\"login_txt\">&nbsp;题目："+selected[0].str4+"&nbsp;&nbsp;&nbsp;正确答案：<font color=red>"+selected[0].str8+"</font></span>");
					    	  $("#xxhms2").html("<font class=\"login_txt\">&nbsp;&nbsp;&nbsp;&nbsp;"+selected[0].str7+"</font>");
							
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
					      
					    	//$('#zl').dialog('open');
					    	var $win;
						      $win = $('#zl').window({
							      top:document.body.scrollTop+10,   
				                  left:document.body.scrollLeft+20
						      });
						      $win.window('open');
					    	jQuery.ajax({
							      url:"ListZl.action",
							      dataType:"json",
							      data:{"xzth":selected[0].int1},
							      cache:false,
			                      success:function (json){
							    	  $('#test7').datagrid('reload'); 
							    	  
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
		 $('#test2').datagrid({
				title:'试题',
				iconCls:'icon-save',
				width:975,
				height:470,
				//singleSelect:true,
				pageSize:15,
				pageList:[15,20,30,40,50],
				nowrap: true,
				striped: true,
				
				url:'stulistPd',
				loadMsg:'处理中，请等待...',
				fit:true,
				fitColumns:true,
				frozenColumns:[[
	                {field:'ck',checkbox:true}
	                
				]],
				columns:[[
				    //{title:'判断题编号',field:'int1',width:100},
					{field:'str4',title:'题目',width:300,
						formatter:function(value,rec){
						var str=rec.str4;
                     if(str==null||str==""){
                  	     str="空";
                      }else{
                      	if(str.length>32){
                      		str=str.substring(0,32)+"...";
                              
                            }
                              
                          }
                     
                     return '<a title="'+value+'">'+str+'</a>';
                        }
						},
					{field:'str2',title:'你的答案对或错',width:100},
					{field:'str6',title:'关联知识点',width:200},
					{field:'str1',title:'教师分析',width:100,
						formatter:function(value,rec){
							 var ms="";
							 if(value.length<=10){
	                             ms=value;          
							  }else{
	                             ms=value.substring(0, 10)+"...";
							 }
	                         return '<a title="'+value+'">'+ms+'</a>';
	                   }
					},
					{field:'str5',title:'教师答疑',width:100,
						formatter:function(value,rec){
							 var ms="";
							 if(value.length<=10){
	                             ms=value;          
							  }else{
	                             ms=value.substring(0, 10)+"...";
							 }
	                         return '<a title="'+value+'">'+ms+'</a>';
	                    }
					}
				]],
				pagination:true,                             //显示分页
				rownumbers:true,                             //显示行号
             toolbar:[{
					
					text:'查看教师分析的详细内容',
					iconCls:'icon-search',
					handler:function(){
					    var selected = $('#test2').datagrid('getSelections');
					    if(selected.length==1){
					         //$('#sjsgfx').dialog('open');
					         var $win;
						      $win = $('#sjsgfx').window({
							      top:document.body.scrollTop+10,   
				                  left:document.body.scrollLeft+20
						      });
						      $win.window('open');
					         document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
					    	 $("#text2").val(selected[0].str1);
							 $("#tm").html("<span class=\"login_txt\">&nbsp;题目："+selected[0].str4+"&nbsp;&nbsp;&nbsp;正确答案：<font color=red>"+selected[0].str8+"</font></span>");
							 $("#xxhms").html("");
					    }else{
					       $.messager.alert('提示','请选择一行数据','warning');
					    }
					    
					}
				
				},'-',{
					
					text:'查看教师答疑的详细内容',
					iconCls:'icon-search',
					handler:function(){
					    var selected = $('#test2').datagrid('getSelections');
					    if(selected.length==1){
					      
						      //$('#sjsgdy').dialog('open');
						      var $win;
						      $win = $('#sjsgdy').window({
							      top:document.body.scrollTop+10,   
				                  left:document.body.scrollLeft+20
						      });
						      $win.window('open');
						      document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
						      $("#text3").val(selected[0].str5);
					    	  $("#tm2").html("<span class=\"login_txt\">&nbsp;题目："+selected[0].str4+"&nbsp;&nbsp;&nbsp;正确答案：<font color=red>"+selected[0].str8+"</font></span>");
					    	  $("#xxhms2").html("");
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
					      
					    	//$('#zl').dialog('open');
					    	var $win;
						      $win = $('#zl').window({
							      top:document.body.scrollTop+10,   
				                  left:document.body.scrollLeft+20
						      });
						      $win.window('open');
					    	jQuery.ajax({
							      url:"ListZl.action",
							      dataType:"json",
							      data:{"pdth":selected[0].int1},
							      cache:false,
			                      success:function (json){
							    	  $('#test7').datagrid('reload'); 
							    	  
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
		$('#test3').datagrid({
			title:'试题',
			iconCls:'icon-save',
			width:975,
			height:470,
			pageSize:15,
			pageList:[15,20,30,40,50],
			nowrap: true,
			striped: true,
			
			url:'stulistCzt',
			loadMsg:'处理中，请等待...',
			fit:true,
			fitColumns:true,
			frozenColumns:[[
                {field:'ck',checkbox:true}
                
			]],
			columns:[[
			    //{title:'操作小题编号',field:'int1',width:100},
			    //{title:'所属大题编号',field:'int2',width:100},
				{field:'str4',title:'操作小题题目',width:200,
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
				{field:'str5',title:'所属操作大题',width:200,
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
				{field:'str2',title:'你的答案对或错',width:50},
				{field:'str6',title:'关联知识点',width:100},
				{field:'str1',title:'教师分析',width:50,
					formatter:function(value,rec){
						 var ms="";
						 if(value.length<=5){
                            ms=value;          
						  }else{
                            ms=value.substring(0, 5)+"...";
						 }
                         return '<a title="'+value+'">'+ms+'</a>';
                    }
				},
				{field:'str7',title:'教师答疑',width:50,
					formatter:function(value,rec){
						 var ms="";
						 if(value.length<=5){
                            ms=value;          
						  }else{
                            ms=value.substring(0, 5)+"...";
						 }   
						 return '<a title="'+value+'">'+ms+'</a>';
                
                   }
				}
			]],
			pagination:true,                             //显示分页
			rownumbers:true,                             //显示行号
            toolbar:[{
				
				text:'查看教师分析的详细内容',
				iconCls:'icon-search',
				handler:function(){
				    var selected = $('#test3').datagrid('getSelections');
				    if(selected.length==1){
				        //$('#sjsgfx').dialog('open');
				        var $win;
					      $win = $('#sjsgfx').window({
						      top:document.body.scrollTop+10,   
			                  left:document.body.scrollLeft+20
					      });
					      $win.window('open');
				        document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
				    	$("#text2").val(selected[0].str1);
				    	$("#tm").html("<font face=\"Comic Sans MS\" class=\"login_txt\">&nbsp;所属操作大题题目："+selected[0].str5+"</font>");
				    	$("#xxhms").html("<font class=\"login_txt\">&nbsp;&nbsp;&nbsp;&nbsp;操作小题题目："+selected[0].str4+"</font>");
					
				    }else{
				       $.messager.alert('提示','请选择一行数据','warning');
				    }
				    
				}
			
			},'-',{
				
				text:'查看教师答疑的详细内容',
				iconCls:'icon-search',
				handler:function(){
				    var selected = $('#test3').datagrid('getSelections');
				    if(selected.length==1){
				         // $('#sjsgdy').dialog('open');
				         var $win;
					      $win = $('#sjsgdy').window({
						      top:document.body.scrollTop+10,   
			                  left:document.body.scrollLeft+20
					      });
					      $win.window('open');
				          document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
					      $("#text3").val(selected[0].str7);
				    	  $("#tm2").html("<font class=\"login_txt\">&nbsp;所属操作大题题目："+selected[0].str5+"</font>");
				    	  $("#xxhms2").html("<font class=\"login_txt\">&nbsp;&nbsp;&nbsp;&nbsp;操作小题题目："+selected[0].str4+"</font>");
						 
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
				      
				    	//$('#zl').dialog('open');
				    	var $win;
					      $win = $('#zl').window({
						      top:document.body.scrollTop+10,   
			                  left:document.body.scrollLeft+20
					      });
					      $win.window('open');
				    	jQuery.ajax({
						      url:"ListZl.action",
						      dataType:"json",
						      data:{"czth":selected[0].int1,"czdth":selected[0].int2},
						      cache:false,
		                      success:function (json){
						    	  $('#test7').datagrid('reload'); 
						    	  
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
		$('#test4').datagrid({
			title:'知识点',
			iconCls:'icon-save',
			width:975,
			height:470,
			//singleSelect:true,
			pageSize:15,
			pageList:[15,20,30,40,50],
			nowrap: true,
			striped: true,
			
			url:'stulistZsd',
			loadMsg:'处理中，请等待...',
			fit:true,
			fitColumns:true,
			frozenColumns:[[
                {field:'ck',checkbox:true}
                
			]],
			columns:[[
			    //{title:'知识点编号',field:'int1',width:100},
				{field:'str4',title:'名称',width:200},
				{field:'str5',title:'知识点描述',width:400,
					formatter:function(value,rec){
					var str=rec.str5;
                    if(str==null||str==""){
                 	     str="空";
                     }else{
                     	if(str.length>40){
                     		str=str.substring(0,40)+"...";
                             
                           }
                             
                         }
                    
                    return '<a title="'+value+'">'+str+'</a>';
                       }
					},
				{field:'str1',title:'教师分析',width:100,
					formatter:function(value,rec){
						 var ms="";
						 if(value.length<=10){
                           ms=value;          
						  }else{
                           ms=value.substring(0, 10)+"...";
						 }
                        return '<a title="'+value+'">'+ms+'</a>';
                   }
				},
				{field:'str7',title:'教师答疑',width:100,
					formatter:function(value,rec){
						 var ms="";
						 if(value.length<=10){
                           ms=value;          
						  }else{
                           ms=value.substring(0, 10)+"...";
						 }
                        return '<a title="'+value+'">'+ms+'</a>';
                   }
				}
			]],
			pagination:true,                             //显示分页
			rownumbers:true,                             //显示行号
            toolbar:[{
				
				text:'查看教师分析内容',
				iconCls:'icon-search',
				handler:function(){
				    var selected = $('#test4').datagrid('getSelections');
				    if(selected.length==1){
				           //$('#sjsgfx').dialog('open');
				           var $win;
					      $win = $('#sjsgfx').window({
						      top:document.body.scrollTop+10,   
			                  left:document.body.scrollLeft+20
					      });
					      $win.window('open');
				           document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
					       $("#text2").val(selected[0].str1);
					       $("#tm").html("<font class=\"login_txt\">&nbsp;知识点名称："+selected[0].str4+"</font>");
					       $("#xxhms").html("<font class=\"login_txt\">&nbsp;&nbsp;&nbsp;&nbsp;描述 --> "+selected[0].str5+"</font>");
						 
				    }else{
				       $.messager.alert('提示','请选择一行数据','warning');
				    }
				    
				}
			
			},'-',{
				
				text:'查看教师答疑内容',
				iconCls:'icon-search',
				handler:function(){
				    var selected = $('#test4').datagrid('getSelections');
				    if(selected.length==1){
				     
					      //$('#sjsgdy').dialog('open');
					      var $win;
					      $win = $('#sjsgdy').window({
						      top:document.body.scrollTop+10,   
			                  left:document.body.scrollLeft+20
					      });
					      $win.window('open');
					      document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
					      $("#text3").val(selected[0].str7);
				    	  $("#tm2").html("<font class=\"login_txt\">&nbsp;知识点名称："+selected[0].str4+"</font>");
				    	  $("#xxhms2").html("<font class=\"login_txt\">&nbsp;&nbsp;&nbsp;&nbsp;描述 --> "+selected[0].str5+"</font>");
						 
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
				      
					       //$('#zl').dialog('open');
					       var $win;
					      $win = $('#zl').window({
						      top:document.body.scrollTop+10,   
			                  left:document.body.scrollLeft+20
					      });
					      $win.window('open');
				    	jQuery.ajax({
						      url:"ListZl.action",
						      dataType:"json",
						      data:{"zsdbh":selected[0].int1},
						      cache:false,
		                      success:function (json){
						    	  $('#test7').datagrid('reload'); 
						    	 
		                      }
						   })//
				    }else{
				       $.messager.alert('提示','请选择一行数据','warning');
				    }
				    
				}
			
			},'-',{
				
				text:'知识点相关试题',
				iconCls:'icon-search',
				handler:function(){
				    var selected = $('#test4').datagrid('getSelections');
				    if(selected.length==1){
				      
					       $('#zsdst').dialog('open');
				    	jQuery.ajax({
						      url:"ListZl.action",
						      dataType:"json",
						      data:{"zsdbh":selected[0].int1},
						      cache:false,
		                      success:function (json){
						    	  $('#test8').datagrid('reload'); 
						    	 $('#test9').datagrid('reload'); 
						    	 $('#test10').datagrid('reload'); 
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
		   document.getElementById("bccspj").style.visibility="visible";//先加载了html时div设为了hidden，在加载脚本时设为visible
		}

  </script>
  <script type="text/javascript">
		

		$(function(){
			$('#test7').datagrid({
				width:'auto',
				height:400,
				singleSelect:true,
				pageSize:12,
				pageList:[12,30,40,50],
				nowrap: false,
				striped: true,
				url:'stuCkListZl',
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
					text:'查看',
					iconCls:'icon-search',
					handler:function(){
			  				var selected = $('#test7').datagrid('getSelections');
			  	        	if(selected.length==1){
			  	        		$('#stuzlzxck').window('open');
			  	        		CFrame.document.location.href='../../spdh/dzbj.jsp?zlmc=' + encodeURI(encodeURI(selected[0].str2))+
			  	      		  '&path=' + selected[0].str4 + '&filename=' + selected[0].str1 + '&zlid=' + selected[0].int1+'&type=xgfx';
			  	                // 在此处加上以下代码既可    
			  	  			   window.event.returnValue = false;
			  			             	
			  					  			        		       			
			  			  }
			  	        	else{
			  	    			$.messager.alert('提示','请选择一行数据','warning');
			  	        	}
					}
  			   },'-']                            
				
			});
			$('#test7').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to} 共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
			
});
	
	</script>
  <script type="text/javascript">
		

		$(function(){
			$('#tt2').tabs({
		    	 width:'100%',
				 height:415
			});
			
			$('#test8').datagrid({
				width:'auto',
				height:384,
				pageSize:12,
				pageList:[12,30,40,50],
				nowrap: true,
				striped: true,
				url:'zsdst_xz',
				loadMsg:'处理中，请等待...',
				
				fitColumns:true,
				frozenColumns:[[
					            {field:'ck',checkbox:true}
					            
				]],
				columns:[[
		           
					{field:'str1',title:'题目',width:300,
						formatter:function(value,rec){
							 var ms="";
							 if(value.length<=20){
	                          ms=value;          
							  }else{
	                          ms=value.substring(0, 20)+"...";
							 }
	                       return '<a title="'+value+'">'+ms+'</a>';
	                  }
					},
					{field:'str2',title:'选项',width:300,
						formatter:function(value,rec){
							 var ms="";
							 if(value.length<=20){
	                          ms=value;          
							  }else{
	                          ms=value.substring(0, 20)+"...";
							 }
	                       return '<a title="'+value+'">'+ms+'</a>';
	                  }
					}
				]],
				pagination:true,                             //显示分页
				rownumbers:true,                             //显示行号
				toolbar:[{
					text:'查看题目详细信息',
					iconCls:'icon-search',
					handler:function(){
			  				var selected = $('#test8').datagrid('getSelections');
			  	        	if(selected.length==1){
			  	        		 $('#ttm').dialog('open');
						       
						    	$("#ttmm").html("<span class=\"login_txt\">&nbsp;题目："+selected[0].str1+"&nbsp;&nbsp;&nbsp;正确答案：<font color=red>"+selected[0].str8+"</font></span>");
						    	$("#xxttmm").html("<font class=\"login_txt\">&nbsp;&nbsp;&nbsp;&nbsp;选项："+selected[0].str2+"</font>");
			  					  			        		       			
			  			    }
			  	        	else{
			  	    			$.messager.alert('提示','请选择一行数据','warning');
			  	        	}
					}
  			   },'-']                            
				
			});
			$('#test8').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to} 共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });

			$('#test9').datagrid({
				width:'auto',
				height:384,
				pageSize:12,
				pageList:[12,30,40,50],
				nowrap: true,
				striped: true,
				url:'zsdst_pd',
				loadMsg:'处理中，请等待...',
				
				fitColumns:true,
				frozenColumns:[[
					            {field:'ck',checkbox:true}
					            
				]],
				columns:[[
		           
					{field:'str1',title:'题目',width:500,
						formatter:function(value,rec){
							 var ms="";
							 if(value.length<=20){
	                          ms=value;          
							  }else{
	                          ms=value.substring(0, 20)+"...";
							 }
	                       return '<a title="'+value+'">'+ms+'</a>';
	                  }
					}
				]],
				pagination:true,                             //显示分页
				rownumbers:true,                             //显示行号
				toolbar:[{
					text:'查看题目详细信息',
					iconCls:'icon-search',
					handler:function(){
			  				var selected = $('#test9').datagrid('getSelections');
			  	        	if(selected.length==1){
			  	        		 $('#ttm').dialog('open');
						       
						    	$("#ttmm").html("<span class=\"login_txt\">&nbsp;判断题题目："+selected[0].str1+"&nbsp;&nbsp;&nbsp;正确答案：<font color=red>"+selected[0].str8+"</font></span>");
						    	$("#xxttmm").html("");			        		       			
			  			    }
			  	        	else{
			  	    			$.messager.alert('提示','请选择一行数据','warning');
			  	        	}
					}
  			   },'-']                            
				
			});
			$('#test9').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to} 共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });

			$('#test10').datagrid({
				width:'auto',
				height:384,
				pageSize:12,
				pageList:[12,30,40,50],
				nowrap: true,
				striped: true,
				url:'zsdst_czt',
				loadMsg:'处理中，请等待...',
				
				fitColumns:true,
				frozenColumns:[[
					            {field:'ck',checkbox:true}
					            
				]],
				columns:[[
		           
					{field:'str1',title:'小题题目',width:300,
						formatter:function(value,rec){
							 var ms="";
							 if(value.length<=20){
	                          ms=value;          
							  }else{
	                          ms=value.substring(0, 20)+"...";
							 }
	                       return '<a title="'+value+'">'+ms+'</a>';
	                  }
					},
					{field:'str2',title:'所属大题题目',width:300,
						formatter:function(value,rec){
							 var ms="";
							 if(value.length<=20){
	                          ms=value;          
							  }else{
	                          ms=value.substring(0, 20)+"...";
							 }
	                       return '<a title="'+value+'">'+ms+'</a>';
	                  }
					}
				]],
				pagination:true,                             //显示分页
				rownumbers:true,                             //显示行号
				toolbar:[{
					text:'查看题目详细信息',
					iconCls:'icon-search',
					handler:function(){
			  				var selected = $('#test10').datagrid('getSelections');
			  	        	if(selected.length==1){
			  	        		 $('#ttm').dialog('open');
						       
						    	$("#ttmm").html("<font class=\"login_txt\">&nbsp;所属大题题目："+selected[0].str2+"</font>");
						    	$("#xxttmm").html("<font class=\"login_txt\">&nbsp;&nbsp;&nbsp;&nbsp;小题题目："+selected[0].str1+"</font>");
			  					  			        		       			
			  			    }
			  	        	else{
			  	    			$.messager.alert('提示','请选择一行数据','warning');
			  	        	}
					}
  			   },'-']                            
				
			});
			$('#test10').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to} 共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
			
});
		
		
	
	</script>
</head>
<body >
	
	<div id="tt"  title="" style="overflow:hidden;">
		
		<div title="教师对本次测试评价"   style="padding:0px;" id="999">
		 <div style="visibility:hidden;width:100%;" id="bccspj" align="center" >
		        
		        <div class="container">
					<div class="content">
							
							<div>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
								  <tr>
								    <td height="50%" colspan="3" align="center">
								      <h3>详细内容如下：</h3>
								        <p align="left">试卷编号： <s:property value="sjnr.sjno"></s:property> </p>
								        <p align="left">试卷名称： <s:property value="sjnr.zxx"></s:property></p>
										<p align="left"><font size=1 >注意：仔细查看老师对本次测评的评价对你今后的学习有不可或缺的好处</font>  </p>
										<br>
										<p></p>
								      <textarea name="text1" readonly="readonly" cols="40" rows="10" id="text1" ></textarea>
								       
								    </td>
								  </tr>
										 
						      </table></div>
					</div>
				
					  <div class="nav">
						<h3>说明：</h3>
						<h4>测试评价  </h4>
						<p></p>
						<p>教师对本次测试的评价  </p>
						<p></p>
					</div>
					
		
	            </div>
			
		   </div>
		</div>
		<div title="选 择 题  部  分"    style="padding:0px;">
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
		<div title="知 识 点  部  分"   style="padding:0px;" >
			<table id="test4" >
				
			</table>
		</div>
		
	
		
	</div>
	
	<div id="sjsgfx" class="easyui-dialog" closed="true" title="教师分析详细内容" icon="icon-edit" style="padding:0px;width:500px;height:320px;">
	   <div align="center" style="visibility:hidden;" id="ggggg1">
	       <table width="100%" height="100%" align="center"  border="0px" class="login_text">
	        
	               <tr>
				    <td height="30%" align="center"><table><tr><td align="left">
				         <span id="tm" ></span><br>
				      <span id="xxhms" ></span>
				    </td></tr></table>
				   </td>
				  </tr>   
	               <tr>
				    <td height="70%" align="center">
				     <hr size="1px"color="#A4BED4"/><br>
				     <p>教师对本题已"分析"的详细内容如下：</p>
				      <textarea name="text2" cols="40" rows="6" id="text2" readonly="readonly"></textarea><br>
				        
				      </td>
				  </tr>  
		   
	       </table>
	    </div>
	         
	</div>
	<div id="sjsgdy" class="easyui-dialog" closed="true" title="教师答疑详细内容" icon="icon-edit" style="padding:0px;width:500px;height:320px;">
	   <div align="center" style="visibility:hidden;" id="ggggg2">
	       <table width="100%" height="100%" align="center"  border="0px" class="login_text">
	        
	               <tr>
				    <td height="30%" align="center"><table><tr><td align="left">
				       <span id="tm2"></span><br>
				      <span id="xxhms2" ></span>
				    </td></tr></table>
				     
				      
				   </td>
				  </tr>   
	               <tr>
				    <td height="70%" align="center">
				     <hr size="1px"color="#A4BED4"/><br>
				      <p >教师对本题已"答疑"的详细内容如下：</p>
				      <textarea name="text3" cols="40" rows="6" id="text3" readonly="readonly"></textarea><br>
				        
				      </td>
				  </tr>  
		   
	       </table>
	    </div>
	         
	</div>
    
    <div class="easyui-dialog" id="zl" closed="true" title="相关素材" icon="icon-edit" style="padding:0px;width:500px;height:300px;">
	           <table id="test7" >
			   </table>	
			
	</div>
	<div class="easyui-dialog" id="zsdst" closed="true" title="知识点部分-知识点相关联的试题" icon="icon-edit" style="padding:0px;width:500px;height:300px;">
	          <div id="tt2" region="center" title="" style="overflow:hidden;">
		
				<div title="关联选择题 "    style="padding:0px;">
					<table id="test8" >
						
					</table>
				</div>
				
				<div title="关联判断题 "   style="padding:0px;">
					<table id="test9" >
						
					</table>
				</div>
				
				<div title="关联操作题"   style="padding:0px;">
					<table id="test10" >
						
					</table>
				</div>
			
			</div>
	</div>
	<div id="ttm" class="easyui-dialog" closed="true" title="详细题目" icon="icon-edit" style="padding:0px;width:500px;height:250px;">
	   <div align="center" >
	       <table width="100%" height="100%" align="center"  border="0px" class="login_text">
	        
	               <tr>
				    <td height="40%" align="left">
				     <span id="ttmm"></span><br><br>
				      <span id="xxttmm" ></span>
				      
				   </td>
				  </tr>   
	              
		   
	       </table>
	    </div>
	         
	</div>
	
	<div id="stuzlzxck" class="easyui-dialog" closed="true" title="资料在线查看" icon="icon-edit" maximizable="true"  minimizable="true" style="padding:0px;width:500px;height:300px;overflow-x:hidden;overflow-y:hidden;">
	   
	       <iframe name="CFrame" id="CFrame" frameborder="0" style="padding:0px;width:100%;height:100%;"></iframe>
	        
	</div>
	
</body>
</html>