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
				title:'学校基本信息管理 (双击可查看详细信息！)',
				iconCls:'icon-save',
				width:getWidth(1),
				nowrap: true,
				striped: true,	
				fitColumns:true,
				loadMsg:'加载数据,请等待...',
				onDblClickRow: function(rowIdex,rowData) {
				      scan(rowData);
				   } ,
				url:'schoollist.action',
				columns:[[
	                {title:'学校编号',field:'xxbh',width:getWidth(0.05),align:'center'},
					{field:'xxmc',title:'学校名称',width:getWidth(0.1),align:'left'},
					{field:'xxms',title:'学校简介',width:getWidth(0.85)}					
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


		

		$(function(){
			$('#add').dialog({
				buttons:[{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
						var xxmc = $("#addxxmc").val();
						var xxms = $("#addxxms").val();
						if(xxmc.length!=0){
							if(xxmc.length<20){
								$.ajax({
									type:"POST",
									url:"xxjy.action",
									data:{"xxmc":xxmc},
									cache: false,
									success:function(data){
												if(data.b == 0){
													$.messager.alert('提示信息','此 学校名称已存在!','info');
												}else{
													$.ajax({
											            type:"POST",
											            url:"insertxx.action",
											            data:{"xxmc":xxmc,"xxms":xxms},
											           success:function(){
											            	$('#add').dialog('close');
											            	
											            	$.messager.alert('提示信息 ','添加信息成功!!!','info',function(){
											            		$('#test_tables').datagrid('reload');
											            	});
											            	
											            	$('#test_tables').datagrid('appendRow',{
											            		xxmc:xxmc,
											            		xxms:xxms
											                });  
											            	  
													   }
										      	});
											}
										}
								});
							}else{$.messager.alert('提示信息','学校名称 长度不能大于20!','info');}
							}else{
								$.messager.alert('提示信息','学校名称不能为空!','info');
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

		//查看详细信息
		 function scan(rowData){
			if(rowData){
				$('#scan').window('open');
				document.getElementById("scan").style.visibility="visible";
				$('#xxbh1').val(rowData.xxbh);
	            $('#xxmc1').val(rowData.xxmc);
	            $('#xxms1').val(rowData.xxms);
			}else{
			    $.messager.alert('警告','请选择一行数据','warning');
			 }
		}
		
		//修改
	    function updata(){
			var selected = $('#test_tables').datagrid('getSelected');						
			if(selected){
				//var xxmc = selected.xxmc;			
				//var count=$("#xxmc option").length;
				//var obj1=document.getElementById("xxmc");						
				//for(var i=0;i<count;i++){						
				//	 obj1.options[i].selected=false;
		        //	 if (obj1.options[i].value==xxmc){
		        ///		 obj1.options[i].selected=true;
		        //		 break;
		        //	 }
				//}
				$('#edit').window('open');
				document.getElementById("edit").style.visibility="visible";
				$('#update').show();
				$('#xxbh').val(selected.xxbh);
	            $('#xxmc').val(selected.xxmc);
	            $('#xxms').val(selected.xxms);
			}else{
			    $.messager.alert('警告','请选择一行数据','warning');
			 }
					
		}
	    function edit(){
			var selected = $('#test_tables').datagrid('getSelected');
			var selections = $('#test_tables').datagrid('getSelections');
			var xxbh = $("#xxbh").val();
			var xxmc = $("#xxmc").val();
			var xxms = $("#xxms").val();
			if(xxmc.length!=0){
				if(xxmc.length<20){
					$.ajax({
						type:"POST",
						url:"xxjy1.action",
						data:{"xxmc":xxmc,"xxbh":xxbh},
						cache: false,
						success:function(data){
									if(data.b == 1){
										$.messager.alert('提示信息','此 学校名称已存在!','info');
										//$("#Error1").empty().append("<font color='red' size='2px'>此记录已存在</font>");
									}else{
										$.ajax({
								            type:"POST",
								            url:"updatexx.action",
								            data:{"xxbh":xxbh,"xxmc":xxmc,"xxms":xxms},
								           success:function(){
								            	//$('#edit').dialog('close');
								            	var index = $('#test_tables').datagrid('getRowIndex', selected);     
												$('#test_tables').datagrid('deleteRow', index);
								            	$('#test_tables').datagrid('appendRow',{
								            		xxbh:xxbh,
								            		xxmc:xxmc,
								            		xxms:xxms
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
					}else{$.messager.alert('提示信息','学校名称不能大于20!','info');
						//$('#Error1').empty().append("<font color='red' size='2px'>长度不能大于40！</font>");
					}
				}else{
					$.messager.alert('提示信息','学校名称不能为空!','info');
					//$('#Error1').empty().append("<font color='red' size='2px'>学校名不能为空！</font>");
					}
			}
		
	  //删除多行
		function deleteAll(){   
			 var selected = $('#test_tables').datagrid('getSelections');
			 var arr=new Array();
			   if(selected.length>0){
				   $.each(selected,function(n,value){
                       arr.push(value.xxmc);
                    });
			    	$.messager.confirm('提示','确认删除学校名称为“'+arr.toString()+'”的数据么?',function(id){
			    		if(id){
			    			$.each(selected,function(m,value1){
						    	var single = value1;
						     	$.ajax({
						            type:"POST",
						           url:"deletexx.action",
						            data:"xxbh="+single.xxbh,
						           success:function(json){
										if(json.b==1){
											var index = $('#test_tables').datagrid('getRowIndex',single);//获取某行的行号
											$('#test_tables').datagrid('deleteRow',index);	//通过行号移除该行
											$.messager.confirm('确认','学校编号为'+single.xxbh+'的数据已经被成功删除！',function(r){});
											}else{$.messager.alert('禁止删除','删除学校编号为'+single.xxbh+'的数据会破坏系统数据库的完整性,所以禁止删除这条数据！！','warning');}
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
		        		$.messager.confirm('删除','确认删除学校名称为“'+selected.xxmc+'”的数据吗?',function(d){
					         if(d){
					            /*将数据删除
					             * $('#test_tables').datagrid('deleteRow',index);
					             * */    
				             	$.ajax({
				                      type:"POST",
				                      url:"deletexx.action",
				                      data:"xxbh="+selected.xxbh,
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
						url:"queryschool1.action",
						data:{"queryType":queryType,"queryWord":queryWord},
						cache: false,
						success:function(data){
							if(data.a == 1){
								$('#test_tables').datagrid({
						            url:'queryschool.action'
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
			 
	    function close2(){
	    	$('#edit').window('close');
		}
	</script>
	</head>
	
	<body class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
	
		<div region="center"><table id="test_tables" fit="true"></table></div>	
						   
			    <div id="add" icon="icon-add" title="添加学校信息" closed="true" style="padding:5px;width:260px;height:210px;visibility:hidden;" maximizable="false" minimizable="false" collapsible="false">
					<center>	    
					    <s:form id="insert">
					    	<table align="center">
								<tr>
									<td> 
									学校名称: 
									</td>
									<td>										
										<input type="text" id="addxxmc" />
									</td>
									<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error"></span></td>--%>
								</tr>				
								<tr>
									<td> 
									学校描述: 
									</td>
									<td>
										<s:textarea id="addxxms" style="height:80px"></s:textarea>
									</td>
								</tr>
							</table>
					    </s:form>
					</center>
			   </div>	
			   
			   <div id="edit" class="easyui-window" icon="icon-edit" title="修改学校信息" closed="true" style="padding:5px;width:285px;height:200px;visibility:hidden;" maximizable="false" minimizable="false" collapsible="false">
					<s:form id="update">
						<table align="center"> 
								<tr style="display:none;">
						 			<td> 
									学校编号:
									</td>
									<td>
										<input type="text" id="xxbh"/>
									</td>
								</tr>					
								<tr>
									<td> 
									学校名称: 
									</td>
									<td>
										<input type="text" id="xxmc" />
									</td>
									<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error1"></span></td>--%>
								</tr>				
								<tr>
									<td> 
									学校描述: 
									</td>
									<td>
										<s:textarea id="xxms" style="height:80px"></s:textarea>
									</td>
								</tr>
								<tr></tr>
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
			                        <select name="select" id="type">
			                        	<option value="xxmc">学校名称</option>
			                        	<option value="xxms">学校描述</option>
			                        </select>
			                    </td>
			                    <td><input type="text" name="id" id="name"  required="true"></td>
			                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
			                </tr>
			            </table>
		            </form>
		        </div>
		    </div>	
		    
		    
		    <div id="scan" class="easyui-window" title="查看详细信息" closed="true" style="padding:5px;width:400px;height:230px;visibility:hidden;" maximizable="false" minimizable="false" collapsible="false">
				<table align="center"> 
				<tr>
		 			<td> 
					学校编号:
					</td>
					<td>
						<input type="text" id="xxbh1" style="width:300px;" disabled="true"/>
					</td>
				</tr>					
				<tr>
					<td> 
					学校名称: 
					</td>
					<td>
						<input type="text" id="xxmc1"  style="width:300px;" disabled="true"/>
					</td>
				</tr>				
				<tr>
					<td> 
					学校描述: 
					</td>
					<td>
						<s:textarea id="xxms1" style="width:300px;height:100px" disabled="true"></s:textarea>
					</td>
				</tr>
			</table>
		</div>							
	</body>
</html>
   