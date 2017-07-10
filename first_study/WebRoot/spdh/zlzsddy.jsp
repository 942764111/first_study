<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jQuery EasyUI</title>
	<link href="css/wjh_main.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/sjld.js"></script>
	<script type="text/javascript">
		$(function(){	
			PdType();
			$.extend($.messager.defaults,{  
        	    ok:"确定",  
        	    cancel:"取消"  
        	});
        			
			$('#test_tables').datagrid({
				title:'对应知识点列表',
				iconCls:'icon-save',
				width:getWidth(1),
				height:550,
				nowrap: false,
				striped: true,
				singleSelect:false,
				fit:true,
				fitColumns:true,
				loadMsg:'加载数据,请等待...',
				collapsible:true,
				url:'zlzsddylist.action',
				columns:[[
							{field:'str1',title:'资料名称',width:getWidth(0.2),align:'left'},
							{field:'str2',title:'课程名称',width:getWidth(0.2),align:'left'},
	                    	{field:'str3',title:'章名称 ',width:getWidth(0.15),align:'left'},
	                    	{field:'str4',title:'节名称 ',width:getWidth(0.15),align:'left'},
		                    {field:'str5',title:'知识点名称',width:getWidth(0.15),align:'left'},
		                    {field:'str6',title:'位置 ',width:getWidth(0.15),align:'left'}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'添加对应知识点',
					iconCls:'icon-add',
					handler:add
				},'-',{
					id:'delete',
					  text:'删除对应知识点',
		              iconCls:'icon-cancel',
		              handler:deleteRows
				},'-',{
					id:'update',
					  text:'修改对应位置',
		              iconCls:'icon-edit',
		              handler:update
	  			},'-',{
		  			  id:'btnquery',
					  text:'知识点查询',
		              iconCls:'icon-edit',
		              handler:function(){
						$('#btnquery').linkbutton('enable');
						$('#query').window('open');
						document.getElementById("query").style.visibility="visible";
						$('#name').val('');
  		        	}
	  			}]
			});
			$('#test_tables').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			});
		});

		//宽度
		function getWidth(percent){ 
		    return (document.body.clientWidth-25-11)*percent; 
		}

		//添加对应知识点
		function add() {
			$('#btnadd').linkbutton('enable');
			$('#insert').form('clear');
            $('#add').window('open');
            document.getElementById("add").style.visibility="visible";
            $('#addweizhi').val('请填写对应页或时间(s)');
		};

		//聚焦
		function onfocus1() {
			var weizhi = $('#addweizhi').val();
			if(weizhi=='请填写对应页或时间(s)') {
				$('#addweizhi').val('');
			}else {
				$('#addweizhi').val('请填写对应页或时间(s)');
			}
		}

		//失焦
		function onblur1() {
			var weizhi = $('#addweizhi').val();
			if(weizhi=='') {
				$('#addweizhi').val('请填写对应页或时间(s)');
			}
		}

		//提交对应知识点
		function addzlzsddy() {

			var zlmc = $("#addzlmc").val();
			var TCName = $("#bindTCName").find("option:selected").text();
			var CName = $("#bindZh").find("option:selected").text();
			var zmc = $("#bindK").find("option:selected").text();
			var zsdmc = $("#bindZsd").find("option:selected").text();
			var weizhi = $("#addweizhi").val();
			if(zlmc!='--请选择资料名称--'&&zlmc!=null&&zlmc!='') {
				if(TCName!='--请选择课程名称--'&&TCName!=''&&TCName!='') {
					if(CName!='--请选择章名称--'&&CName!=null&&CName!='') {
						if(zmc!='--请选择节名称--'&&zmc!=null&&zmc!='') {
							if(zsdmc!='--请选择知识点名称--'&&zsdmc!=null&&zsdmc!='') {
								$.ajax({
						            type:"POST",
						            url:"insertzlzsddy.action",
						            data:{"zlmc":zlmc,"TCName":TCName,"CName":CName,"zmc":zmc,
					            		"zsdmc":zsdmc,"weizhi":weizhi},
						           	success:function(){
						            	$('#add').dialog('close');						            	
						            	$.messager.alert('提示信息','添加信息成功!!!','info',function(){
						   					$('#test_tables').datagrid('reload');
						            	});
						            	$('#test_tables').datagrid('appendRow',{
						            		zlmc:zlmc,
						            		TCName:TCName,
						            		CName:CName,
						            		zmc:zmc,
						            		zsdmc:zsdmc,
						            		weizhi:weizhi
					              			});  
								   	}
						      	});
							} else {
								$.messager.alert('提示信息','知识点名称不能为空!','info');
							}
						} else {
							$.messager.alert('提示信息','节名称不能为空!','info');
						}
					} else {
						$.messager.alert('提示信息','章名称不能为空!','info');
					}

				} else {
					$.messager.alert('提示信息','课程名称不能为空!','info');
				}
			} else {
				$.messager.alert('提示信息','请选择资料名称!','info');
			}
			
		}

		//继续添加
		function continueadd() {

			var zlmc = $("#addzlmc").val();
			var TCName = $("#bindTCName").find("option:selected").text();
			var CName = $("#bindZh").find("option:selected").text();
			var zmc = $("#bindK").find("option:selected").text();
			var zsdmc = $("#bindZsd").find("option:selected").text();
			var weizhi = $("#addweizhi").val();

			if(zlmc!='--请选择资料名称--'&&zlmc!=null&&zlmc!='') {
				if(TCName!='--请选择课程名称--'&&TCName!=null&&TCName!='') {
					if(CName!='--请选择章名称--'&&CName!=null&&CName!='') {
						if(zmc!='--请选择节名称--'&&zmc!=null&&zmc!='') {
							if(zsdmc!='--请选择知识点名称--'&&zsdmc!=null&&zsdmc!='') {
								$.ajax({
						            type:"POST",
						            url:"insertzlzsddy.action",
						            data:{"zlmc":zlmc,"TCName":TCName,"CName":CName,"zmc":zmc,
					            		"zsdmc":zsdmc,"weizhi":weizhi},
						           	success:function(){
						            	$('#add').dialog('close');						            	
						   				$('#test_tables').datagrid('reload');
						            	$('#test_tables').datagrid('appendRow',{
						            		zlmc:zlmc,
						            		TCName:TCName,
						            		CName:CName,
						            		zmc:zmc,
						            		zsdmc:zsdmc,
						            		weizhi:weizhi
					              		});
					              		add();
								   	}
						      	});
							} else {
								$.messager.alert('提示信息','知识点名称不能为空!','info');
							}
						} else {
							$.messager.alert('提示信息','节名称不能为空!','info');
						}
					} else {
						$.messager.alert('提示信息','章名称不能为空!','info');
					}

				} else {
					$.messager.alert('提示信息','课程名称不能为空!','info');
				}
			} else {
				$.messager.alert('提示信息','请选择资料名称!','info');
			}
			
		}

		function close1() {
			$('#add').window('close');
		}

		//删除多个知识点
		function deleteRows(){   
			 var selected = $('#test_tables').datagrid('getSelections');
			 var arr = new Array();
			   if(selected.length>0){
				   $.each(selected,function(n,value) {
						arr.push(value.str5);
					})
			    	$.messager.confirm('提示','确认删除知识点名称为"' + arr + '"的数据吗?',function(id){
			    		if(id){
				    		for(var i=0;i<selected.length;i++){
						    	var single = selected[i];
						    	$.ajax({
				                      type:"POST",
				                      url:"deletezlzsddy.action",
				                      data:"zsdmc="+single.str5,
				                      success:function(){
				                      }
								});
						     	var index = $('#test_tables').datagrid('getRowIndex',selected[i]);//获取某行的行号
								$('#test_tables').datagrid('deleteRow',index);	//通过行号移除该行
						    }
				    		$.messager.confirm('确认','所选数据已经被成功删除！',function(r){$('#test_tables').datagrid('reload');});
				    	}
			   });
			   }else{
			    $.messager.alert('提示','请选择一行数据','warning');
			   }
		}

		//删除单个知识点
		function deleteRow(index){
			
	   		var selected = $('#test_tables').datagrid('getSelected');
	       	if(selected){
				$.messager.confirm('提示信息','确认删除知识点名为'+selected.str1+'的数据吗?',function(d){
					if(d){
		             	$.ajax({
		                      type:"POST",
		                      url:"deletezlzsddy.action",
		                      data:"zsdmc="+selected.str5,
		                      success:function(){
		                      	$('#test_tables').datagrid('reload');
		                      }
						});
					}	
				});	       			
			}else{
				$.messager.alert('警告','请选择一行数据','warning');
		    }
		}

		//修改文件类型
		function batchupdate(){
			var selections = $('#test_tables').datagrid('getSelections');
			if(selections.length!=0){
				var selected = selections[0];
				update();
			}else{
			    $.messager.alert('提示','请选择一行数据','warning');
			 }
		}
	
		function update(){
			var selected = $('#test_tables').datagrid('getSelected');
			if(selected){
	
				$('#edit').window('open');
				document.getElementById("edit").style.visibility="visible";
				$('#update').show();
				$('#zlmc').val(selected.str1);
				$('#TCName').val(selected.str2);
				$('#CName').val(selected.str3);
				$('#zmc').val(selected.str4)
				$('#zsdmc').val(selected.str5);
				$('#weizhi').val(selected.str6);
				
			}else{
			    $.messager.alert('警告','请选择一行数据！','warning');
			}
					
		}
		
		function edit(){
			var selected = $('#test_tables').datagrid('getSelected');
			var selections = $('#test_tables').datagrid('getSelections');
			var zlmc = $("#zlmc").val();
			var TCName = $("#TCName").val();
			var CName = $("#CName").val();
			var zmc = $("#zmc").val();
			var zsdmc = $("#zsdmc").val();
			var weizhi = $("#weizhi").val();
			if(weizhi!=selected.str6) {
				$.ajax({
			        type:"POST",
			        url:"updatezlzsddy.action",
			        data:{"zlmc":zlmc,"TCName":TCName,"CName":CName,"zmc":zmc,"zsdmc":zsdmc,"weizhi":weizhi},
			       	success:function(){
			        	$('#edit').dialog('close');
			        	var index = $('#test_tables').datagrid('getRowIndex', selected);     
						$('#test_tables').datagrid('deleteRow', index);
			        	$('#test_tables').datagrid('appendRow',{
			        		zlmc:zlmc,
			        		TCName:TCName,
			        		CName:CName,
			        		zmc:zmc,
			        		zsdmc:zsdmc,
			        		weizhi:weizhi
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
			} else {
				$.messager.alert('提示信息','您没有修改任何数据!','info');
			}
			
		}
		
		function close2(){
			   $('#edit').window('close');
		}

		//知识点对应查询
		function query(){
	        var queryParams = $('#test_tables').datagrid('options').queryParams;
	        var zlzsddytype = queryParams.zlzsddytype = $('#type').val();
	        var zlzsddyword = queryParams.zlzsddyword = $('#name').val();
	        var aa=$('#name').val();
	        if(aa!="") {
	        	$.ajax({
					type:"POST",
					url:"searchzlzsddy1.action",
					data:{"zlzsddytype":zlzsddytype,"zlzsddyword":zlzsddyword},
					cache: false,
					success:function(data){
								if(data.a == 1){
									$('#test_tables').datagrid({
					    	            url:'searchzlzsddy.action'
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
		    } else {
		    	$.messager.alert('提示','查询条件不能为空!','info');
			}
	        
	    }

		//判断角色的类型，如果角色是学生就会失去一些权限
		function PdType(){
			$.ajax({
	            type:"GET",
	           url:"GetType.action",
	           success:function(json){
		           if(json.tip=="S"){
		        	   $('#btnadd').linkbutton('disable');
			     	   $('#delete').linkbutton('disable');
			     	  	$('#update').linkbutton('disable');
			          }
		           }
	      	});
		}
	</script>
	</head>
  
	<body class="easyui-layout" style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
	
		<div region="center">
	  		<table id="test_tables"></table>
	  	</div>
	  	
	    <div id="add" icon="icon-add" class="easyui-window" title="添加知识点" closed="true" 
	    		style="padding:5px;width:400px;height:310px;visibility:hidden;" maximizable="false" 
				minimizable="false" collapsible="false">
			<s:form id="insert">
				<table align="right">
					<tr>
			 			<td> 
							资料名称:<br/><br/>
						</td>
						<td>
							<s:select id="addzlmc" list="dmtzllist" listKey="zlmc" listValue="zlmc" >
							</s:select><br/><br/>
						</td>
					</tr>
					<tr>
						<td> 
							课程名称:<br/><br/>
						</td>
						<td>
							<select id="bindTCName" onChange="getZh()" style="width:166px;">
								<option  value="-1">--请选择课程名称--</option>
							</select><br/><br/>
						</td>
					</tr>
					<tr>
						<td> 
							章名称:<br/><br/>
						</td>
						<td>
						   	<select id="bindZh"  onChange="getKcbh()" style="width:166px;">
						   		<option  value="-1">--请选择章名称--</option>
						   	</select><br/><br/>
						</td>
					</tr>	
					<tr>
						<td> 
							节名称:<br/><br/>
						</td>
						<td>
						   	<select id="bindK"  onChange="getZsd()" style="width:166px;">
						   		<option  value="-1">--请选择节名称--</option>
						   	</select><br/><br/>
						</td>
					</tr>			
					<tr>
						<td> 
							知识点名称:<br/><br/>
						</td>
						<td>										
							<select id="bindZsd" onChange="" style="width:166px;">
						     <option  value="-1">--请选择知识点名称--</option>
						    </select><br/><br/>
						</td>
					</tr>
											
					<tr>
						<td> 
							对应位置:<br/><br/>
						</td>
						<td>
							<input type="text" id="addweizhi" style="padding:0;width:166px;" onfocus="onfocus1()" onblur="onblur1()"/>
							<br/><br/>
						</td>
					</tr>
					
					<tr>
						<td colspan="3" align="left">
							<a class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" onclick="continueadd()">添加下一知识点</a>
							<a class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" onclick="addzlzsddy()">完成</a>
							<a class="easyui-linkbutton" iconCls="icon-cancel"
								href="javascript:void(0)" onclick="close1()">取消</a>
						</td>
					</tr>
					
				</table>
			</s:form>
	   </div>	
	   
	   <div id="edit" class="easyui-window" icon="icon-edit" title="修改知识点对应位置" closed="true" 
	   		style="padding:5px;width:350px;height:280px;visibility:hidden;" maximizable="false" 
	   		minimizable="false" collapsible="false">
			<s:form id="update">
				<table align="right"> 
					<tr>
			 			<td> 
							资料名称:<br/><br/>
						</td>
						<td>
							<input type="text" id="zlmc" style="width:166px;border:0px" readonly="readonly"/><br/><br/>
						</td>
					</tr>
					<tr>
						<td> 
							课程名称: <br/><br/>
						</td>
						<td>
							<input id="TCName" type="text" style="width:166px;border:0px" readonly="readonly"> 
						   	<br/><br/>
						</td>
					</tr>	
					<tr>
						<td> 
							章名称:<br/><br/>
						</td>
						<td>
							<input id="CName" type="text" style="width:166px;border:0px" readonly="readonly"/>
							<br/><br/>
						</td>
					</tr>
					<tr>
						<td> 
							节名称: <br/><br/>
						</td>
						<td>
							<input id="zmc" type="text" style="width:166px;border:0px" readonly="readonly"/> 
						   	<br/><br/>
						</td>
					</tr>	
							
					<tr>
						<td> 
							知识点名称:<br/><br/>
						</td>
						<td>										
							<input id="zsdmc" type="text" style="width:166px;border:0px" readonly="readonly"/>
						     <br/><br/>
						</td>
					</tr>
					
					<tr>
						<td> 
							对应位置:<br/><br/>
						</td>
						<td>
							<input type="text" id="weizhi" style="padding:0;width:140px;"/>
							<span style="color:red;">
								*&nbsp;&nbsp;
							</span>
							对应页或时间(s)<br/><br/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<a class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" onclick="edit()">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
		                       		<option value="zlmc">资料名称</option> 
		                       		<option value="zsdmc">知识点名称</option>     
								</select>
		                 	</td>
		               		<td>
								<input type="text" name="id" id="name"  required="true">
		               		</td>
		               		<td>
		               			<a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a>
		               		</td>
		              	</tr>
		          	</table>
		         </form>
		     </div>
		</div>
								
	</body>
</html>