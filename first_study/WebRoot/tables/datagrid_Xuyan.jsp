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
				title:'学院信息管理表',
				iconCls:'icon-save',
				width:getWidth(1),
				nowrap: true,
				striped: true,
				fitColumns:true,	
				url:'listXuyan.action',
				idField:'xybh',	
				loadMsg:'加载数据,请等待...',
				onLoadSuccess:function(){
					var data=$('#test_tables').datagrid('getData');
					if(data.total==0)
					{
					      $.messager.alert('信息','没有数据','info');
					}				
				},
				 columns:[[
            {field:'xybh',title:'学院编号',width:getWidth(0.4),align:'center'},
            {field:'xymc',title:'学院名称',width:getWidth(0.6),align:'center'}
        ]],
				pagination:true,
				rownumbers:true,
				toolbar:[{					
					text:'添加',
					iconCls:'icon-add',
					handler:function(){					
					$('#insert').form('clear');
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

		    $(function(){
				$('#add').dialog({
					buttons:[{
						text:'提交',
						iconCls:'icon-ok',
						handler:function(){
							var xymc = $("#xymc2").val();
							if(xymc.length!=0){
								if(xymc.length<20){
									$.ajax({
										type:"POST",
										url:"Xyjy.action",
										data:{"xymc":xymc},
										cache: false,
										success:function(data){
													if(data.b == 0){
														$("#Error").empty().append("<font color='red' size='2px'>此记录已存在</font>");
													}else{
														$.ajax({
												            type:"POST",
												            url:"insertXuyan.action",
												            data:{"xymc":xymc},
												            success:function(){
												              	$('#add').dialog('close');
												            	
												            	$.messager.alert('提示信息','添加信息成功!!!','info',function(){
												            		$('#test_tables').datagrid('reload');
												            	});
												            	
												            	$('#test_tables').datagrid('appendRow',{
												            		xymc:xymc
												                });  
														   }
											      	});
												}
											}
									});
								}else{$('#Error').empty().append("<font color='red' size='2px'>长度不能大于20！</font>");}
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
			 $('#xybh1').val(selected.xybh);
	         $('#xymc1').val(selected.xymc);
		}else{
		    $.messager.alert('提示','请选择一行数据','error');
		 }
	}
	
	function edit1(){
		var selections = $('#test_tables').datagrid('getSelections');
		var selected = selections[0];
		var xybh= $("#xybh1").val();
		var xymc = $("#xymc1").val();
		$.ajax({
           type:"POST",
          url:"updateXuyan.action",
           data:{"xybh":xybh,"xymc":xymc},
          success:function(){
	           	var index = $('#test_tables').datagrid('getRowIndex', selected); 
				$('#test_tables').datagrid('deleteRow', index);
	           	$('#test_tables').datagrid('appendRow',{
	           		xybh:xybh,
	           		xymc:xymc
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
		$("#xymc1").blur(function(){
		    var selected = {"xymc":$('#xymc1').val()};
			var xymc = $("#xymc1").val();
			var cArr = xymc.match(/[^\x00-\xff]/ig);
			var alength = xymc.length+(cArr == null ? 0 : cArr.length);
			if(selected&&alength!=0){
	          if(alength<20){
		     $.ajax({
			 type:"POST",
			 url:"Xyjy.action",
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
						function deleteAll(){   
							 var selected = $('#test_tables').datagrid('getSelections');
							   if(selected){
							    	$.messager.confirm('提示','确认删除么?',function(id){
							    		if(id){
								    		for(var i=0;i<selected.length;){
										    	var single = selected[i];
										     	$.ajax({
										            type:"POST",
										           url:"deleteXuyan.action",
										              data:"xybh="+single.xybh,
										           success:function(){}
										      	});
										     	var index = $('#test_tables').datagrid('getRowIndex',single);//获取某行的行号
												$('#test_tables').datagrid('deleteRow',index);	//通过行号移除该行
										    }
								    		$.messager.confirm('确认','所选数据已经被成功删除！',function(r){});
								    	}
							   });
							   }else{
							    $.messager.alert('提示','请选择一行数据','warning');
							   }
						}

               //查询学院信息
		    		function query(){
		    	           var queryParams = $('#test_tables').datagrid('options').queryParams;
                           var xytype =queryParams.xytype=$('#type').val();
	                       var xyword=queryParams.xyword= $('#name').val();
		    					$.ajax({
						            type:"POST",
						           url:'searchXuyan1.action',
						          data:{"xytype":xytype,"xyword":xyword},
						          cache: false,
		    						success:function(data){
		    									if(data.a == 1){
		    										$('#test_tables').datagrid({
		    						    	            url:'searchXuyan.action'
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
		    	   				 }

	</script>
	</head>
	
	<body class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
	
		<div region="center"><table id="test_tables" fit="true"></table></div>
						   
			    <div id="add" icon="icon-add" title="添加学院信息" closed="true" style="padding:5px;width:400px;height:150px;" maximizable="false" minimizable="false" collapsible="false">
					<center>	    
					    <s:form id="insert">
					    	<table align="center">
								<tr>
									<td> 
									学院名称: 
									</td>
									<td>										
										<input id="xymc2" type="text"/>
									</td>
									<td height="30" class="top_hui_text"><span class="login_txt" id="Error"></span></td>
								</tr>				
							</table>
					    </s:form>
					</center>
			   </div>	
			   
			 <!-- 修改界面 -->
	<div id="edit1" closed="true" maximizable="false" minimizable="false" collapsible="false" class="easyui-window" title="修改学院信息" iconCls="icon-edit" style="width:350px;height:150px;padding:5px;background: #fafafa;">
	  <form id="update1">
		 <table align="center">
    	    <tr>
    			<td align="left">
    				学院编号：
    				</td>
    				<td>
    				<input id="xybh1" readonly="readonly">
    			</td>
    		</tr>
    		<tr>
    			<td align="left">
    				学院族名称：
    				</td>
    				<td>
    				<input id="xymc1" type="text">
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
			                    <td>
			                        <select name="select" id="type">
			                        	   <option value="xybh">学院编号</option>   
	                                       <option value="xymc">学院名称</option>  
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
   