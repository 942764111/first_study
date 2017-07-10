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
				title:'节信息管理 （双击可查看详细信息！）',
				iconCls:'icon-save',
				width:getWidth(1),
				nowrap: true,
				striped: true,
				collapsible:true,
				fitColumns:true,
				loadMsg:'加载数据,请等待...',
				url:'jielist.action',
				onDblClickRow: function(rowIndex,rowData) {
				      scan(rowData);
				   } ,
				columns:[[	               			
					{field:'jbh',title:'节编号',width:getWidth(0.06),align:'center'},
					{field:'jmc',title:'节名称',width:getWidth(0.15)},
					{field:'jms',title:'节描述',width:getWidth(0.64)},
					{field:'zhangm',title:'章名称',width:getWidth(0.15)}
					
										
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
		});

		 $(function(){
				$('#add').dialog({
					buttons:[{
						text:'提交',
						iconCls:'icon-ok',
						handler:function(){
						var zmc = $("#addjmc").val();
						var zms = $("#addjms").val();
						var CName = $("#addCName").find("option:selected").text();
							if(CName.length!=0){
									if(CName.length<20){
										$.ajax({
											type:"POST",
											url:"zjya.action",
											data:{"zmc":zmc},
											cache: false,
											success:function(data){
														if(data.b == 1){
															$.messager.alert('提示信息','此节名称已存在!','info');
														}else{
															$.ajax({
													            type:"POST",
													            url:"insertjie.action",
													            data:{"zmc":zmc,"zms":zms,"CName":CName},
													           success:function(){
													            	$('#add').dialog('close');						            	
													            	$.messager.alert('提示信息','添加信息成功!!!','info',function(){
													            		$('#test_tables').datagrid('reload');
													            	});
													            	$('#test_tables').datagrid('appendRow',{
													            		zmc:zmc,
													            		zms:zms,
													            		CName:CName
													                });  
															  	   }
												      		   });
															}
												}
									});
								}else{$.messager.alert('提示信息','节名称 长度不能大于20!','info');}
							}else{$.messager.alert('提示信息','节名称不能为空!','info');}
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
					updata();
				}else{
				    $.messager.alert('提示','请选择一行数据','error');
				 }
			}
	    function updata(){
			var selected = $('#test_tables').datagrid('getSelected');
			if(selected){
				//var CName = selected.CName;
				//var count=$("#zbh option").length;
				//var obj1=document.getElementById("CName");						
				//for(var i=0;i<count;i++){						
				//	 obj1.options[i].selected=false;
		        //	 if (obj1.options[i].value==CName){
		        //		 obj1.options[i].selected=true;
		        //		 break;
		        //	 }
				//}				
				$('#edit').window('open');
				document.getElementById("edit").style.visibility="visible";
				$('#update').show();
				$('#jbh').val(selected.jbh);
	            $('#jmc').val(selected.jmc);
	            $('#jms').val(selected.jms);
	            $('#CName').val(selected.zhangm);
			}else{
			    $.messager.alert('警告','请选择一行数据','warning');
			 }
					
		}
				function edit(){
					var selected = $('#test_tables').datagrid('getSelected');
					var selections = $('#test_tables').datagrid('getSelections');
					var CId = $("#jbh").val();
					var zmc = $("#jmc").val();
					var zms = $("#jms").val();
					var CName = $("#CName").val();
					if(zmc.length!=0){
						if(zmc.length<20){
							$.ajax({
								type:"POST",
								url:"zjy2.action",
								data:{"CId":CId,"zmc":zmc},
								cache: false,
								success:function(data){
											if(data.b == 1){
												$.messager.alert('提示信息','此节名称已存在!','info');
											}else{

												$.ajax({
											        type:"POST",
											        url:"updatejie.action",
											        data:{"CId":CId,"zmc":zmc,"zms":zms,"CName":CName},
											       success:function(){
											        	//$('#edit').dialog('close');
											        	var index = $('#test_tables').datagrid('getRowIndex', selected);     
														$('#test_tables').datagrid('deleteRow', index);
											        	$('#test_tables').datagrid('appendRow',{
											        		CId:CId,
													        zmc:zmc,
													        zms:zms,
													        CName:CName
											            });
											        	if(selections.length>1){
				            			           			batchupdate();
				            			           		}else{
				            			           			$.messager.alert('提示','您已成功修改所选数据!');
				            			           			$('#test_tables').datagrid('reload');
				            			           			close2();
				            				           	}
												   }
												});
										}
									}
							});
						}else{$.messager.alert('提示信息','节名称 长度不能大于20!','info');}
						}else{
							$.messager.alert('提示信息','节名称不能为空!','info');
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
			                       arr.push(value.jmc);
			                    });
						    	$.messager.confirm('提示','确认删除节名称为“'+arr.toString()+'”的数据吗?',function(id){
						    		if(id){
						    			$.each(selected,function(m,value1){
									    	var single = value1;
									     	$.ajax({
									            type:"POST",
									           url:"deletejie.action",
									            data:"zmc="+single.jmc,
									           success:function(json){
													if(json.b==1){
														var index = $('#test_tables').datagrid('getRowIndex',single);//获取某行的行号
														$('#test_tables').datagrid('deleteRow',index);	//通过行号移除该行
														$.messager.confirm('确认','节名称为“'+single.jmc+'”的数据已经被成功删除！',function(r){$('#test_tables').datagrid('reload');});
														}else{$.messager.alert('禁止删除','删除节名称为“'+single.jmc+'”的数据会破坏系统数据库的完整性,所以禁止删除这条数据！！','warning');}
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
			        		$.messager.confirm('删除','确认删除节名称为“'+selected.jmc+'”的数据吗?',function(d){
				            	if(d){
						            /*将数据删除
						             * $('#test_tables').datagrid('deleteRow',index);
						             * */
					             	$.ajax({
					                      type:"POST",
					                      url:"deletejie.action",
					                      data:"CName="+selected.zhangm,
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
	    						url:"queryjie1.action",
	    						data:{"queryType":queryType,"queryWord":queryWord},
	    						cache: false,
	    						success:function(data){
   									if(data.a == 1){
   										$('#test_tables').datagrid({
   						    	            url:'queryjie.action'
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
			
	    		//查看详细信息
	    		function scan(rowData){
	    			if(rowData){
	    				$('#scan').window('open');
	    				document.getElementById("scan").style.visibility="visible";
	    				$('#jbh1').val(rowData.jbh);
	    	            $('#jmc1').val(rowData.jmc);
	    	            $('#jms1').val(rowData.jms);
	    	            $('#CName1').val(rowData.zhangm);
	    			}else{
	    			    $.messager.alert('警告','请选择一行数据','warning');
	    			 }
	    		} 
	</script>
	</head>
	
	<body class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
	
		<div region="center"><table id="test_tables" fit="true"></table></div>
						   
			    <div id="add" icon="icon-add" title="添加章信息" closed="true" style="padding:5px;width:260px;height:200px;visibility:hidden;" maximizable="false" minimizable="false" collapsible="false">
					<center>	    
					    <s:form id="insert">
					    	<table align="center">
								<tr>
									<td> 
									节名称: 
									</td>
									<td>
										<input type="text" id="addjmc" />									
									</td>
									<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error3"></span></td>--%>
								</tr>				
								<tr>
									<td> 
									节描述: 
									</td>
									<td>
										<s:textarea id="addjms" style="height:50px;width:150px;"></s:textarea>
									</td>
								</tr>
								<tr>
									<td>
									章名称: 
									</td>
									<td>
										<s:select id="addCName" list="CNameList" name="CName" style="width:155px;"></s:select>
									</td>
									<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error"></span></td>--%>
								</tr>
							</table>
					    </s:form>
					</center>
			   </div>	
			   
			   <div id="edit" class="easyui-window" icon="icon-edit" title="修改章信息" closed="true" style="padding:5px;width:300px;height:200px;visibility:hidden;" maximizable="false" minimizable="false" collapsible="false">
					<s:form id="update">
						<table align="center"> 
								<tr style="display:none">
						 			<td> 
									节编号:
									</td>
									<td>
										<input type="text" id="jbh"/>
									</td>
								</tr>					
								<tr>
									<td> 
									节名称: 
									</td>
									<td>										
										<input type="text" id="jmc"  style="width:180px;"/>
									</td>
									<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error2"></span></td>--%>
								</tr>				
								<tr>
									<td> 
									节描述: 
									</td>
									<td>
										<s:textarea id="jms" style="height:80px;width:180px;"></s:textarea>
									</td>
								</tr>
								<tr style="display:none">
									<td> 
									章名称: 
									</td>
									<td>
										<input type= "text" id="CName"/>
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
			                        <select name="select" id="type">
			                        	<option value="zmc" selected="selected">节名称</option>
			                        	<option value="zms">节描述 </option>
			                        </select>
			                    </td>
			                    <td><input type="text" name="id" id="name"  required="true"></td>
			                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
			                </tr>
			            </table>
		            </form>
		        </div>
		    </div>
			<div id="scan" closed="true" class="easyui-window" title="查看详细信息" style="width:280px;height:250px;padding:5px;background: #fafafa;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
		    	<table align="center">	
		    		<tr>
			 			<td> 
						节编号:
						</td>
						<td>
							<input type="text" id="jbh1" disabled="true" style="width:180px;"/>
						</td>
					</tr>					
					<tr>
						<td> 
						节名称: 
						</td>
						<td>										
							<input type="text" id="jmc1"  style="width:180px;" disabled="true"/>
						</td>
					</tr>				
					<tr>
						<td> 
						节描述: 
						</td>
						<td>
							<s:textarea id="jms1" style="height:80px;width:180px;" disabled="true"></s:textarea>
						</td>
					</tr>
					<tr>
						<td> 
						章名称: 
						</td>
						<td>
							<input type= "text" id="CName1" disabled="true" style="width:180px;"/>
						</td>
					</tr>			    				
				</table>
			</div>		
		</body>
</html>
				
