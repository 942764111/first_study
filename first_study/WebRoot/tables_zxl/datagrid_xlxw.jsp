<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jQuery EasyUI</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
		
<script type="text/javascript">
		function getWidth(percent){ 
		    return (document.body.clientWidth-25-11)*percent; 
		}	
		$(function(){	
			$('#test_tables').datagrid({
				title:'学历学位管理',
				iconCls:'icon-save',			
				width:getWidth(1),
				nowrap: true,
				striped: true,	
				fitColumns:true,
				loadMsg:'加载数据,请等待...',	
				url:'xlxwlist.action',
				columns:[[               
	                {field:'xlbh',title:'学历编号',width:getWidth(0.2),align:'center'},
	                {field:'xlmc',title:'学历名称',width:getWidth(0.4),align:'center'},
					{field:'type',title:'学历类别',width:getWidth(0.4),align:'center'}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{					
					text:'添加',
					iconCls:'icon-add',
					handler:function(){					
					$('#insert').form('clear');
	                $('#add').window('open');
	                document.getElementById("insert").style.visibility="visible";
      				}
				},'-',{
					 
					  text:'删除',
		              iconCls:'icon-cancel',
		              handler:deleteAll
				},'-',{
					 
					  text:'修改',
		              iconCls:'icon-edit',
		              handler:function(){
						batchupdate();
					  // $('#next').linkbutton('enable');
			   			} 
      			},'-',{
    				id:'btnquery',
    				text:'查询',
    				iconCls:'icon-search',
    				handler:function(){
    					$('#btnquery').linkbutton('enable');
    					$('#name').val('');
    					$('#query').window('open');
    					document.getElementById("query").style.visibility="visible";
    				}
    			}]
			});
			$('#test_tables').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
				 });
		});

		    $(function(){
				$('#add').dialog({
					buttons:[{
						text:'提交',
						iconCls:'icon-ok',
						handler:function(){
							var xlmc = $("#addxlmc").val();
							var type = $("#addtype").val();
							if(xlmc.length!=0){
								if(xlmc.length<8){
									$.ajax({
										type:"POST",
										url:"xljy.action",
										data:{"xlmc":xlmc},
										cache: false,
										success:function(data){
													if(data.b == 0){
														$.messager.alert('提示信息','此学历名称已存在!','info');
													}else{
														$.ajax({
												            type:"POST",
												            url:"insertxlxw.action",
												            data:{"xlmc":xlmc,"type":type},
												           success:function(){
												            	$('#add').dialog('close');
												            	
												            	$.messager.alert('提示信息','添加信息成功!!!','info',function(){
												            		$('#test_tables').datagrid('reload');
												            	});
												            	
												            	$('#test_tables').datagrid('appendRow',{
												            		xlmc:xlmc,
												            		type:type
												                });  
														   }
											      	});
												}
											}
									});
								}else{$.messager.alert('提示信息','学历名称 长度不能大于8!','info');}
								}else{
									$.messager.alert('提示信息','学历名称不能为空!','info');
									}
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$('#add').dialog('close');
						}
					}]
				});
			});




		    function batchupdate(){
				var selections = $('#test_tables').datagrid('getSelections');
				if(selections.length!=0){
					var selected = selections[0];
					//if(selections.length==1){
					//	$('#next').linkbutton('disable');
					//}
					updata();
				}else{
				    $.messager.alert('提示','请选择一行数据','error');
				 }
			}

				    function updata(){
						var selected = $('#test_tables').datagrid('getSelected');						
						if(selected){
							//var xlmc = selected.xlmc;
							//var type = selected.type;
							//var count1=$("#xlmc option").length;
							//var obj1=document.getElementById("xlmc");
													
							//for(var i=0;i<count1;i++){						
							//	 obj1.options[i].selected=false;
					        //	 if (obj1.options[i].value==xlmc){
					        //		 obj1.options[i].selected=true;
					        //		 break;
					        //	 }
							//}
							//var count2=$("#type option").length;
							//var obj2=document.getElementById("type");
							//for(var i=0;i<count2;i++){
							//	 obj2.options[i].selected=false;
					        //	 if (obj2.options[i].value==type){
					        //		 obj2.options[i].selected=true;
					        //		 break;
					        //	 }
							//}
							$('#edit').window('open');
							document.getElementById("edit").style.visibility="visible";
							$('#update').show();
							$('#xlbh').val(selected.xlbh);
				            $('#xlmc').val(selected.xlmc);
				            $('#type').val(selected.type);
						}else{
						    $.messager.alert('警告','请选择一行数据','warning');
						 }
								
					}
				    function edit(){
						var selected = $('#test_tables').datagrid('getSelected');
						var selections = $('#test_tables').datagrid('getSelections');
						var xlbh = $("#xlbh").val();
						//var xlmc = $("#xlmc").find("option:selected").text();
						//var type = $("#type").find("option:selected").text();
						var xlmc = $("#xlmc").val();
						var type = $("#type").val();
						if(xlmc.length!=0){
							if(xlmc.length<8){
								$.ajax({
									type:"POST",
									url:"xljy1.action",
									data:{"xlmc":xlmc,"xlbh":xlbh},
									cache: false,
									success:function(data){
												if(data.b == 1){
													$.messager.alert('提示信息','此学历名称已存在!','info');
												}else{
													$.ajax({
											            type:"POST",
											            url:"updatexlxw.action",
											            data:{"xlbh":xlbh,"xlmc":xlmc,"type":type},
											           success:function(){
											            	//$('#edit').dialog('close');
											            	var index = $('#test_tables').datagrid('getRowIndex', selected);     
															$('#test_tables').datagrid('deleteRow', index);
											            	$('#test_tables').datagrid('appendRow',{
											            		xlbh:xlbh,
											            		xlmc:xlmc,
											            		type:type
											                });
											            	if(selections.length>1){
					            			           			batchupdate();
					            			           		}else{
					            			           			$.messager.alert('提示','您已成功修改所选条数据!');
					            			           			$('#test_tables').datagrid('reload');
					            			           			close2();
					            				           	}
													   }
											  	});
											}
										}
								});
							}else{$.messager.alert('提示信息','学历名称 长度不能大于8!','info');}
							}else{
								$.messager.alert('提示信息','学历名称不能为空!','info');
								}
					}
					 function close2(){
						   $('#edit').window('close');
					}
						
					//删除多行
						function deleteAll(){   
							 var selected = $('#test_tables').datagrid('getSelections');
							 var arr=new Array();
							   if(selected.length>0){
								   $.each(selected,function(n,value){
				                       arr.push(value.xlmc);
				                    });
							    	$.messager.confirm('提示','确认删除学历名称为“'+arr.toString()+'”的数据么?',function(id){
							    		if(id){
							    			$.each(selected,function(m,value1){
										    	var single = value1;
										     	$.ajax({
										            type:"POST",
										           url:"deletexlxw.action",
										            data:"xlbh="+single.xlbh,
										           success:function(json){
														if(json.b==1){
															var index = $('#test_tables').datagrid('getRowIndex',single);//获取某行的行号
															$('#test_tables').datagrid('deleteRow',index);	//通过行号移除该行
															$.messager.confirm('确认','学历编号为'+single.xlbh+'的数据已经被成功删除！',function(r){});
															}else{$.messager.alert('禁止删除','删除学历编号为'+single.xlbh+'的数据会破坏系统数据库的完整性,所以禁止删除这条数据！！','warning');}
											           }
										      	});
										    });
								    	}
							   		});
							   }else{
							    $.messager.alert('提示','请选择一行数据','warning');
							   }
						}

						//删除一行		    		
		    		function deleteRow(index){	        	
		            		var selected = $('#test_tables').datagrid('getSelected');
				        	if(selected){
				        		$.messager.confirm('删除','确认删除学历名称为“'+selected.xlmc+'”的数据吗?',function(d){					        	
					            	if(d){
						             	$.ajax({
						                      type:"POST",
						                      url:"deletexlxw.action",
						                      data:"xlbh="+selected.xlbh,
						                      success:function(){
						             			$.messager.alert('提示信息','删除成功！','提示信息');
						             			$('#test_tables').datagrid('reload');
						                      }
						                	});
								         }
				        		 });			        		       			
		        		  }
				        	else{
			        			$.messager.alert('警告','请选择一行数据','warning');
				        	}
		   			 }

		    		function query(){
		    	        var queryParams = $('#test_tables').datagrid('options').queryParams;
		    	        var queryType =queryParams.queryType=$('#type1').val();
		    	        var queryWord=queryParams.queryWord= $('#name').val();
		    	        //if(queryType!=null){
			    	        if(queryWord!=""){
		    					$.ajax({
		    						type:"POST",
		    						url:"queryxl1.action",
		    						data:{"queryType":queryType,"queryWord":queryWord},
		    						cache: false,
		    						success:function(data){
    									if(data.a == 1){
    										$('#test_tables').datagrid({
    						    	            url:'queryxl.action'
    						    	        });
    						    	        $('#query').window('close');
    						    	        $('#test_tables').datagrid('getPager').pagination({
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
		    	        }else{$.messager.alert('提示','查询条件不能为空!');}
		    	      // }else{$.messager.alert('提示','查询条件不能为空!');}
		    	   }

	</script>
	</head>
	
	<body class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
	
		<div region="center"><table id="test_tables" fit="true"></table></div>
						   
			    <div id="add" icon="icon-add" title="添加学历学位信息" closed="true" style="padding:5px;width:250px;height:150px;visibility:hidden;" maximizable="false" minimizable="false" collapsible="false">
					<center>	    
					    <s:form id="insert">
					    	<table align="center">
								<tr>
									<td> 
									学历名称: 
									</td>
									<td>										
										<input id="addxlmc" type="text"/>
									</td>
									<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error"></span></td>--%>
								</tr>				
								<tr>
									<td> 
									学历类型: 
									</td>
									<td>
										<input id="addtype" type="text"/>
									</td>
								</tr>
							</table>
					    </s:form>
					</center>
			   </div>	
			   
			   <div id="edit" class="easyui-window" icon="icon-edit" title="修改学历学位信息" closed="true" style="padding:5px;width:290px;height:140px;visibility:hidden;" maximizable="false" minimizable="false" collapsible="false">
					<s:form id="update">
						<table align="center"> 
								<tr style="display:none">
						 			<td> 
									学历编号:
									</td>
									<td>
										<input type="text" id="xlbh"/>
									</td>
								</tr>					
								<tr>
									<td> 
									学历名称: 
									</td>
									<td>	
										<input id="xlmc" type="text"/>									
										<%--<s:select id="xlmc" list="xlxwlist" listKey="xlmc" listValue="xlmc"></s:select>--%>
									</td>
									<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error1"></span></td>--%>
								</tr>				
								<tr>
									<td> 
									学历类型: 
									</td>
									<td>
										<input id="type" type="text"/>
									<%--<s:select id="type" list="xlxwlist" listKey="type" listValue="type"></s:select>--%>
									</td>
								</tr>
								<tr>
									<td>
										<a class="easyui-linkbutton" iconCls="icon-ok"
											href="javascript:void(0)" onclick="edit()">修改</a>
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a class="easyui-linkbutton" iconCls="icon-cancel"
											href="javascript:void(0)" onclick="close2()">取消</a>
									</td>
								</tr>
						</table>
					</s:form>			
				</div>	
				
				<div id="query" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;visibility:hidden;"
		    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
		        <div>
		        	<form>
			            <table>
			                <tr>
			                    <td>
			                        <select name="select" id="type1">
			                        	<option value="xlmc">学历名称</option>
			                        	<option value="type">学历类型</option>
			                        </select>
			                    </td>
			                    <td><input type="text" name="id" id="name"  required="true"></td>
			                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
			                </tr>
			            </table>
		            </form>
		        </div>
		    </div>									
	</body>
</html>
   