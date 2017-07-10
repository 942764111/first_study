<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jQuery EasyUI</title>
	<link href="css/wjh_main.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="../js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../js/ui/themes/icon.css">
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../js/ui/jquery.easyui.min.js"></script>
		
<script type="text/javascript">
		function getWidth(percent){ 
		    return (document.body.clientWidth-25-11)*percent; 
		}	
		$(function(){
			$('#test_tables').datagrid({
				title:'民族信息管理表',
				iconCls:'icon-save',
				width:getWidth(1),
				nowrap: true,
				striped: true,
				fitColumns:true,
				url:'listMz.action',
				idField:'mzbh',	
				loadMsg:'加载数据,请等待...',
				onLoadSuccess:function(){
					var data=$('#test_tables').datagrid('getData');
					if(data.total==0)
					{
					      $.messager.alert('信息','没有数据','info');
					}				
				},
				 columns:[[
            {field:'mzbh',title:'民族编号',width:getWidth(0.4),align:'center'},
            {field:'mzmc',title:'民族名称',width:getWidth(0.6),align:'center'}
        ]],
				pagination:true,
				rownumbers:true,
				toolbar:[{					
					text:'添加',
					iconCls:'icon-add',
					handler:function(){					
					$('#insert').form('clear');
					$('#Error').empty();
	                $('#add').window('open');
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
					   $('#next').linkbutton('enable');
			   } 
      			},'-',{
    				id:'btnquery',
    				text:'查询',
    				iconCls:'icon-search',
    				handler:function(){
    					$('#btnquery').linkbutton('enable');
    					$('#query').window('open');
    				}
    			}]
			});
			$('#test_tables').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
				 });
		});
  //添加功能
  
		    $(function(){
				$('#add').dialog({
					buttons:[{
						text:'提交',
						iconCls:'icon-ok',
						handler:function(){
							var mzmc = $("#mzmc2").val();
							if(mzmc.length!=0){
								if(mzmc.length<20){
									$.ajax({
										type:"POST",
										url:"Mzjy.action",//对添加的校验
										data:{"mzmc":mzmc},
										cache: false,
										success:function(data){
													if(data.b == 0){
														$("#Error").empty().append("<font color='red' size='2px'>此记录已存在</font>");
														
													}else{
														$.ajax({
												            type:"POST",
												            url:"insertMz.action",
												            data:{"mzmc":mzmc},
												            success:function(){
												            	$('#add').dialog('close');
												            	
												            	$.messager.alert('提示信息','添加信息成功!!!','info',function(){
												            		$('#test_tables').datagrid('reload');
												            	});
												            	
												            	$('#test_tables').datagrid('appendRow',{
												            		mzmc:mzmc
												                });  
														   }
											      	});
												}
											}
									});
								}else{
									$('#Error').empty().append("<font color='red' size='2px'>长度不能大于20！</font>");}
							
								}else{
									$('#Error').empty().append("<font color='red' size='2px'>不能为空！</font>");
									
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

			

    //修改功能 		
		function batchupdate(){
		var selections = $('#test_tables').datagrid('getSelections');
		if(selections.length!=0){
			var selected = selections[0];
			if(selections.length==1){
				$('#next').linkbutton('disable');
			}
			update1(selected);
		}else{
		    $.messager.alert('提示','请选择一行数据','error');
		 }
	}

	function update1(selected){
		if(selected){
			 $('#edit1').window('open');
			 $('#mzbh1').val(selected.mzbh);
	         $('#mzmc1').val(selected.mzmc);
		}else{
		    $.messager.alert('提示','请选择一行数据','error');
		 }
	}
	
	function edit1(){
		var selections = $('#test_tables').datagrid('getSelections');
		var selected = selections[0];
		var mzbh= $("#mzbh1").val();
		var mzmc = $("#mzmc1").val();
		$.ajax({
           type:"POST",
          url:"updateMz.action",
           data:{"mzbh":mzbh,"mzmc":mzmc},
          success:function(){
	           	var index = $('#test_tables').datagrid('getRowIndex', selected); 
				$('#test_tables').datagrid('deleteRow', index);
	           	$('#test_tables').datagrid('appendRow',{
	           		mzbh:mzbh,
	           		mzmc:mzmc
	            });
	           	if(selections.length>0){
           			batchupdate();
           		}else{
           			$.messager.alert('提示','数据修改成功!');
           			$('#test_tables').datagrid('reload');
					clos();
	           	}
		   }
 		});
	}
	
	  function clos(){
     $('#edit1').window('close');
     }
     //对修改功能的校验
	$(function(){
		$("#mzmc1").blur(function(){
		    var selected = {"mzmc":$('#mzmc1').val()};
			var mzmc = $("#mzmc1").val();
			var cArr = mzmc.match(/[^\x00-\xff]/ig);
			var alength = mzmc.length+(cArr == null ? 0 : cArr.length);
			if(selected&&alength!=0){
	          if(alength<20){
		     $.ajax({
			 type:"POST",
			 url:"Mzjy.action",
			 data:selected,
			 cache: false,
			 success:function(data){
						if(data.b == 0){
							$("#Error1").empty().append("<font color='red' size='2px'></font>");
							$.messager.alert('提示信息','此记录已存在!!!','info',function(){
						       });
						}else{
						  
						}
					}
		});
	}else{$('#Error1').empty().append("<font color='red' size='2px'></font>");
	$.messager.alert('提示信息','长度不能大于20！','info',function(){
						       });}
	}else{
		$('#Error1').empty().append("<font color='red' size='2px'></font>");
		$.messager.alert('提示信息','不能为空！','info',function(){
						       });
		}
		});	
	});
					//批量删除
					//通过民族编号删除所选数据
						function deleteAll(){   
							 var selected = $('#test_tables').datagrid('getSelections');
							   if(selected.length!=0){
							    	$.messager.confirm('提示','确认删除么?',function(id){
							    		if(id){
								    		for(var i=0;i<selected.length;){
										    	var single = selected[i];
										     	$.ajax({
										            type:"POST",
										           url:"deleteMz.action",
										              data:"mzbh="+single.mzbh,
										           success:function(){}
										      	});
										     	var index = $('#test_tables').datagrid('getRowIndex',single);//获取某行的行号
												$('#test_tables').datagrid('deleteRow',index);	//通过行号移除该行
										    }
								    		$.messager.alert('确认','所选数据已经被成功删除！','info',function(r){});
								    	}
							   });
							   }else{
							    $.messager.alert('提示','请选择一行数据','warning');
							   }
						}

               //查询民族信息
		    		function query(){
		    	           var queryParams = $('#test_tables').datagrid('options').queryParams;
                           var mztype =queryParams.mztype=$('#type').val();//查询类型
	                       var mzword =queryParams.mzword= $('#name').val();//查询字段
	                       if(mzword.length!=0){
		    					$.ajax({
						            type:"POST",
						           url:'searchMz1.action',
						          data:{"mztype":mztype,"mzword":mzword},
						          cache: false,
		    						success:function(data){
		    									if(data.a == 1){
		    										$('#test_tables').datagrid({
		    						    	            url:'searchMz.action'
		    						    	        });
		    						    	        $('#query').window('close');
		    						    	        $('#test_tables').datagrid('getPager').pagination({
		    						   				 displayMsg:'当前显示从{from}到{to}共{total}记录',
		    						   				 beforePageText:'第',
		    						   				 afterPageText:'页'
		    						   				 });
		    									}else if(data.a == 0){
		    										$.messager.alert('提示信息','查询结果为空!','info',function(){
		    						            	});
		    								}
		    							}
		    						});
	                              }else{
									$.messager.alert('提示信息','民族名称不能为空','warning',function(){});
		                          }
	   						
		    	   				 }

	</script>
	</head>
	
	<body class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
	
		<div region="center"><table id="test_tables" fit="true"></table></div>
						   
			    <div id="add" icon="icon-add" title="添加民族信息" closed="true" style="padding:5px;width:400px;height:150px;" maximizable="false" minimizable="false" collapsible="false">
					<center>	    
					    <s:form id="insert">
					    	<table align="center">
								<tr>
									<td> 
									民族名称: 
									</td>
									<td>										
										<input id="mzmc2" type="text"/>
									</td>
									 <td height="30" class="top_hui_text"><span class="login_txt" id="Error"></span></td>
								</tr>				
							</table>
					    </s:form>
					</center>
			   </div>	
			   
			 <!-- 修改界面 -->
	<div id="edit1" closed="true" maximizable="false" minimizable="false" collapsible="false" class="easyui-window" title="修改民族信息" iconCls="icon-edit" style="width:350px;height:150px;padding:5px;background: #fafafa;">
	  <form id="update1">
		 <table align="center">
    	    <tr>
    			<td align="left">
    				民族编号：
    				</td>
    				<td>
    				<input id="mzbh1" readonly="readonly">
    			</td>
    		</tr>
    		<tr>
    			<td align="left">
    				民族名称：
    				</td>
    				<td>
    				<input id="mzmc1" type="text">
    			</td>
    			<td height="30" class="top_hui_text"><span class="login_txt" id="Error1"></span></td>
    		</tr>
    		<tr>
						<td colspan="3" align="right">
						<a id="next" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="edit1()">下一条</a>
						<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="edit1()">修改</a>
						<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="clos()">取消</a>
					</td> 
					</tr>
        </table>
      </form>  
	</div>
				
				<div id="query" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;"
		    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
		        <div>
		        	<form>
			            <table>
			                <tr>
			                <td >
			                			
			                               <select name="select" id="type">
			                        	  <!--  <option value="mzbh">民族编号</option>    -->
	                                       <option value="mzmc">民族名称</option>  
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
   