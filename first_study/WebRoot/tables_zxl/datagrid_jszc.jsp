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
				title:'教师职称管理',
				iconCls:'icon-save',
				width:getWidth(1),
				nowrap: true,
				striped: true,
				fitColumns:true,	
				//singleSelect:true,	
				loadMsg:'加载数据,请等待...',
				url:'jszclist.action',
				columns:[[
	               
	                {title:'职称编号',field:'zcbh',width:getWidth(0.2),align:'center'},
	                {field:'zcmc',title:'职称名称',width:getWidth(0.4),align:'center'},
					{field:'zcjb',title:'职称级别',width:getWidth(0.4),align:'center'}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
					$('#btnsave').linkbutton('enable');
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

//添加教师职称信息
		$(function(){
			$('#add').dialog({
				buttons:[{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
						var zcmc = $("#addzcmc").val();
						var zcjb = $("#addzcjb").val();
						if(zcmc.length!=0){
							if(zcmc.length<8){
								$.ajax({
									type:"POST",
									url:"zcjy.action",
									data:{"zcmc":zcmc},
									cache: false,
									success:function(data){
												if(data.b == 0){
													$.messager.alert('提示信息','此职称名称已存在!','info');
												}else{
													$.ajax({
											            type:"POST",
											            url:"insert.action",
											            data:{"zcmc":zcmc,"zcjb":zcjb},
											            success:function(){
											            	$('#add').dialog('close');					            	
											            	$.messager.alert('提示信息','信息添加成功!!!','info',function(){
											            		$('#test_tables').datagrid('reload');
											            	});
											            	$('#test_tables').datagrid('appendRow',{
											            		zcmc:zcmc,
											            		zcjb:zcjb
											                });  
													   }
										      	});
											}
										}
								});
							}else{$.messager.alert('提示信息','职称名称 长度不能大于8!','info');}
							}else{
								$.messager.alert('提示信息','职称名称不能为空!','info');
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
		//获得所选的数据
		    function updata(){
				var selected = $('#test_tables').datagrid('getSelected');
				if(selected){
					//var zcmc = selected.zcmc;
					//var zcjb = selected.zcjb;
					//var count=$("#zcbh option").length;
					//var obj1=document.getElementById("zcmc");						
					//for(var i=0;i<count;i++){						
					//	 obj1.options[i].selected=false;
			        //	 if (obj1.options[i].value==zcmc){
			        //		 obj1.options[i].selected=true;
			        //		 break;
			        //	 }
					//}
					//var obj2=document.getElementById("zcjb");
					//for(var i=0;i<count;i++){
					//	 obj2.options[i].selected=false;
			        //	 if (obj2.options[i].value==zcjb){
			        //		 obj2.options[i].selected=true;
			        //		 break;
			        //	 }
					//}
					$('#edit').window('open');
					 document.getElementById("edit").style.visibility="visible";
					$('#update').show();
					$('#zcbh').val(selected.zcbh);
		            $('#zcmc').val(selected.zcmc);
		            $('#zcjb').val(selected.zcjb);
				}else{
				    $.messager.alert('警告','请选择一行数据','warning');
				 }
						
			}


		//修改信息
			//var x = $('#test_tables').datagrid('getSelections');
		    function edit(){
				var selected = $('#test_tables').datagrid('getSelected');
				var selections = $('#test_tables').datagrid('getSelections');
				var zcbh = $("#zcbh").val();
				var zcmc = $("#zcmc").val();
				var zcjb = $("#zcjb").val();
				if(zcmc.length!=0){
							if(zcmc.length<8){
								$.ajax({
									type:"POST",
									url:"zcjy1.action",
									data:{"zcmc":zcmc,"zcbh":zcbh},
									cache: false,
									success:function(data){
												if(data.b == 1){
													$.messager.alert('提示信息','此职称名称已存在!','info');
												}else{
													$.ajax({
				            							type:"POST",
				            							url:"update.action",
				            							data:{"zcbh":zcbh,"zcmc":zcmc,"zcjb":zcjb},
				          								success:function(){
				            								//$('#edit').dialog('close');
				            								var index = $('#test_tables').datagrid('getRowIndex', selected);     
															$('#test_tables').datagrid('deleteRow', index);
				            								$('#test_tables').datagrid('appendRow',{
				            									zcbh:zcbh,
				            									zcmc:zcmc,
				            									zcjb:zcjb
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
							}else{$.messager.alert('提示信息','职称名称 长度不能大于8!','info');}
							}else{
								$.messager.alert('提示信息','职称名称不能为空!','info');
								}
						}

		  //删除多行
			function deleteAll(){   
				 var selected = $('#test_tables').datagrid('getSelections');
				 var arr=new Array();
				   if(selected.length>0){
					   $.each(selected,function(n,value){
	                       arr.push(value.zcbh);
	                    });
				    	$.messager.confirm('提示','确认删除职称编号为'+arr.toString()+'的数据吗?',function(id){
				    		if(id){
				    			$.each(selected,function(m,value1){
							    	var single = value1;
							     	$.ajax({
							            type:"POST",
							           url:"delete.action",
							            data:"zcbh="+single.zcbh,
							           success:function(json){
											if(json.a==1){
												var index = $('#test_tables').datagrid('getRowIndex',single);//获取某行的行号
												$('#test_tables').datagrid('deleteRow',index);	//通过行号移除该行
								    		$.messager.confirm('确认','职称编号为'+single.zcbh+'的数据已经被成功删除！',function(r){});
												}else{$.messager.alert('禁止删除','删除职称编号为'+single.zcbh+'的数据会破坏系统数据库的完整性,所以禁止删除这条数据！！','warning');}
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
	        		$.messager.confirm('提示信息','确认删除职称编号为“'+selected.zcbh+'”的数据吗?',function(d){
	                	if(d){
				        /*将数据删除
				         * $('#test_tables').datagrid('deleteRow',index);
				         * */    
				             	$.ajax({
				                      type:"POST",
				                      url:"delete.action",
				                      data:"zcbh="+selected.zcbh,
				                      success:function(){
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
	        var queryType =queryParams.queryType=$('#type').val();
	        var queryWord=queryParams.queryWord= $('#name').val();
	        //if(queryType!=null){
		        if(queryWord!=""){
						$.ajax({
							type:"POST",
							url:"queryzc1.action",
							data:{"queryType":queryType,"queryWord":queryWord},
							cache: false,
							success:function(data){
										if(data.a == 1){
											$('#test_tables').datagrid({
									            url:'queryzc.action'
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
	        //}else{$.messager.alert('提示','查询条件不能为空!');}
		   }

		 function close2(){
			   $('#edit').window('close');
		}

	</script>
	</head>
	
	<body class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
	
		<div region="center"><table id="test_tables" fit="true"></table></div>
						   
			    <div id="add" icon="icon-add" title="添加教师职称" closed="true" style="padding:5px;width:300px;height:150px;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
					<center>	    
					    <s:form id="insert">
					    	<table align="center">
								<tr>
									<td> 
									职称名称: 
									</td>
									<td>	
										<input type="text" id="addzcmc" />	
									</td>
									<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error"></span></td>--%>
								</tr>				
								<tr>
									<td> 
									职称级别: 
									</td>
									<td>
									<input type="text" id="addzcjb" />
									</td>
								</tr>
							</table>
					    </s:form>
					</center>
			   </div>	
			   
			   <div id="edit" class="easyui-window" icon="icon-edit" title="修改教师职称" closed="true" style="padding:5px;width:300px;height:140px;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
					<s:form id="update">
						<table align="center"> 
								<tr style="display:none;">
						 			<td> 
									职称编号:
									</td>
									<td>
										<input type="text" id="zcbh"/>
									</td>
								</tr>					
								<tr>
									<td> 
									职称名称: 
									</td>
									<td>	
										<input type="text" id="zcmc" />										
									</td>
									<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error1"></span></td>--%>
								</tr>				
								<tr>
									<td> 
									职称级别: 
									</td>
									<td>
										<input type="text" id="zcjb" />
									<%--<s:select id="zcjb" list="jszclist" listKey="zcjb" listValue="zcjb"></s:select>--%>
									</td>
								</tr>
								<tr>
									
									<td>	
										<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="edit()">完成</a>
									</td>
									<td>
										<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close2()">取消</a>
									</td>
									<%--<td>
										<a id="next" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="edit()">修改下一条</a>
									</td>
								--%></tr>
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
			                        <select name="select" id="type">
			                        	<option value="zcmc">职称名称</option>
			                        	<option value="zcjb">职称级别</option>
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
   