<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专业信息管理</title>
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
				title:'专业信息管理',
				iconCls:'icon-save',
				width:getWidth(1),
				nowrap: false,
				striped: true,	
				fitColumns:true,
				loadMsg:'加载数据,请等待...',
				url:'zyxxlist.action',
				columns:[[
	                {field:'int1',title:'专业编号',width:getWidth(0.2),sortable:true,align:'center'},					
					{field:'str2',title:'学院名称',width:getWidth(0.4),align:'center'},
					{field:'str1',title:'专业名称',width:getWidth(0.4),sortable:true,align:'center'}					
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
					$('#btnadd').linkbutton('enable');
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
    					$('#cx').form('clear');
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
							var zybh = $("#addzybh").val();
							var xymc = $("#addxymc").find("option:selected").text();
							var zymc = $("#addzymc").val();
							if(zybh!=''){
							if(zymc.length!=0){
								if(zymc.length<20){
									$.ajax({
										type:"POST",
										url:"zyjy.action",
										data:{"zymc":zymc,"zybh":zybh},
										cache: false,
										success:function(data){
													if(data.b == 0){
														$.messager.alert('提示信息','此专业名称已存在!','info');
													}else if(data.a == 0){
														$.messager.alert('提示信息','此专业编号已存在!','info');
														}else{
														$.ajax({
												            type:"POST",
												            url:"insertzyxx.action",
												            data:{"zybh":zybh,"xymc":xymc,"zymc":zymc},
												           success:function(){
												            	$('#add').dialog('close');						            	
												            	$.messager.alert('提示信息','添加信息成功!!!','info',function(){
												            		$('#test_tables').datagrid('reload');
												            	});
												            	$('#test_tables').datagrid('appendRow',{
												            		zybh:zybh,
												            		xymc:xymc,
												            		zymc:zymc
												                });  
												            	  
														   }
											      	});
												}
											}
									});
								}else{$.messager.alert('提示信息','专业名称长度不能大于20!','info');}
								}else{$.messager.alert('提示信息','专业名称不能为空!','info');}
								}else{$.messager.alert('提示信息','专业编号不能为空!','info');}
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
				var xymc = selected.xymc;
				//var zymc = selected.zymc;
				var count=$("#zybh option").length;
				var obj1=document.getElementById("xymc");						
				for(var i=0;i<count;i++){						
					 obj1.options[i].selected=false;
		        	 if (obj1.options[i].value==xymc){
		        		 obj1.options[i].selected=true;
		        		 break;
		        	 }
				}
				//var obj2=document.getElementById("zymc");
				//for(var i=0;i<count;i++){
				//	 obj2.options[i].selected=false;
		        //	 if (obj2.options[i].value==zymc){
		        //		 obj2.options[i].selected=true;
		        //		 break;
		        //	 }
				//}
				$('#edit').window('open');
				document.getElementById("edit").style.visibility="visible";
				$('#update').show();
				$('#zybh').val(selected.int1);
	            $('#xymc').val(selected.str2);
	            $('#zymc').val(selected.str1);
			}else{
			    $.messager.alert('警告','请选择一行数据','warning');
			 }
					
		}
				function edit(){
					var selected = $('#test_tables').datagrid('getSelected');
					var selections = $('#test_tables').datagrid('getSelections');
					var zybh = $("#zybh").val();
					var xymc = $("#xymc").find("option:selected").text();
					var zymc = $("#zymc").val();
					if(zymc.length!=0){
						if(zymc.length<20){
							$.ajax({
								type:"POST",
								url:"zyjy1.action",
								data:{"zybh":zybh,"zymc":zymc},
								cache: false,
								success:function(data){
											if(data.b == 1){
												$.messager.alert('提示信息','此专业名称已存在!','info');
											}else{
												$.ajax({
											        type:"POST",
											        url:"updatezyxx.action",
											        data:{"zybh":zybh,"xymc":xymc,"zymc":zymc},
											       success:function(){
											        	//$('#edit').dialog('close');
											        	var index = $('#test_tables').datagrid('getRowIndex', selected);     
														$('#test_tables').datagrid('deleteRow', index);
											        	$('#test_tables').datagrid('appendRow',{
											        		zybh:zybh,
											        		xymc:xymc,
										            		zymc:zymc
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
							}else{$.messager.alert('提示信息','专业名称长度不能大于20!','info');}
						}else{$.messager.alert('提示信息','专业名称不能为空!','info');}
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
			                       arr.push(value.str1);
			                    });
						    	$.messager.confirm('提示','确认删除专业名称为“'+arr.toString()+'”的数据么?',function(id){
						    		if(id){
						    			$.each(selected,function(m,value1){
									    	var single = value1;
									     	$.ajax({
									            type:"POST",
									           url:"deletezyxx.action",
									            data:"zybh="+single.int1,
									           success:function(json){
										           if(json.a==1){
										        	   var index = $('#test_tables').datagrid('getRowIndex',single);//获取某行的行号
														$('#test_tables').datagrid('deleteRow',index);	//通过行号移除该行
														$.messager.confirm('确认','专业名称为'+single.int1+'的数据已经被成功删除！',function(r){$('#test_tables').datagrid('reload');});
											           }else{$.messager.alert('禁止删除','删除专业名称为'+single.int1+'的数据会破坏系统数据库的完整性,所以禁止删除这条数据！！','warning');}
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
			        		$.messager.confirm('删除','确认删除专业名称为“'+selected.str1+'”的数据吗?',function(d){
				            	if(d){
						            /*将数据删除
						             * $('#test_tables').datagrid('deleteRow',index);
						             * */    
					             	$.ajax({
					                      type:"POST",
					                      url:"deletezyxx.action",
					                      data:"zybh="+selected.int1,
					                      success:function(){$('#test_tables').datagrid('reload');}
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
	    	        var queryType=queryParams.queryType = "zymc";
	    	        var queryWord=queryParams.queryWord= $('#name').val();
	    	        if(queryWord!=""){
	    					$.ajax({
	    						type:"POST",
	    						url:"queryzy2.action",
	    						data:{"queryType":queryType,"queryWord":queryWord},
	    						cache: false,
	    						success:function(data){
	    									if(data.a == 1){
	    										$('#test_tables').datagrid({
	    						   		            url:'queryzyxx.action'
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
	    	   				 }
	</script>
	</head>
	
	<body class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
	
		<div region="center"><table id="test_tables" fit="true"></table></div>
						   
			    <div id="add" icon="icon-add" title="添加专业信息" closed="true" style="padding:5px;width:300px;height:180px;visibility:hidden;" maximizable="false" minimizable="false" collapsible="false">
					<center>	    
					    <s:form id="insert">
					    	<table align="center">
						    	<tr>
						 			<td> 
									专业编号:
									</td>
									<td>
										<input type="text" id="addzybh"/>
									</td>
									<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Errora"></span></td>--%>
								</tr>					
								<tr>
									<td> 
									学院名称: 
									</td>
									<td>										
										<s:select id="addxymc" list="zyxxb1" name="xymc"></s:select>
									</td>
								</tr>				
								<tr>
									<td> 
									专业名称: 
									</td>
									<td>
										<input type="text" id="addzymc" />
									</td>
									<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error"></span></td>--%>
								</tr>
							</table>
					    </s:form>
					</center>
			   </div>	
			   
			   <div id="edit" class="easyui-window" icon="icon-edit" title="修改专业信息" closed="true" style="padding:5px;width:300px;height:150px;visibility:hidden;" maximizable="false" minimizable="false" collapsible="false">
					<s:form id="update">
						<table align="center"> 
								<tr style="display:none;">
						 			<td> 
									专业编号:
									</td>
									<td>
										<input type="text" id="zybh"/>
									</td>
								</tr>					
								<tr>
									<td> 
									学院名称: 
									</td>
									<td>										
										<s:select id="xymc" list="zyxxb1"  name="xymc"></s:select>
									</td>
								</tr>				
								<tr>
									<td> 
									专业名称: 
									</td>
									<td>
									<input type="text" id="zymc" />
									<%--<s:select id="zymc" list="zyxxb" listKey="zymc" listValue="zymc"></s:select>--%>
									</td>
									<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error1"></span></td>--%>
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
					<form id="cx">
						<table>
							<tr>
								<td>
									专业名称：
								</td>
								<td>
									<input type="text" name="id" id="name"  required="true"/>
								</td>
								 <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
							</tr>
						</table>
					</form>
				</div>	
			</div>			
		</body>
</html>
			

   