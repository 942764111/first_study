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
			$('#test_t').datagrid({
				title:'课程、章基本信息列表 (双击可查看详细信息！)',
				iconCls:'icon-save',
				nowrap: true,
				striped: true,
				fitColumns:true,	
				url:'listKcxx.action',
				loadMsg:'加载数据,请等待...',
				onLoadSuccess:function(){
					var data=$('#test_t').datagrid('getData');
					if(data.total==0)
					{
					      $.messager.alert('信息','没有数据','info');
					}				
				},
				onDblClickRow: function(rowIndex, rowData) {
				      scan(rowData);
				   } ,
				columns:[[
		            {field:'zbh',title:'章编号',width:getWidth(0.1),align:'center'},
		            {field:'CName',title:'章名称',width:getWidth(0.2),align:'left'},
		            {field:'kcms',title:'章描述',width:getWidth(0.5),align:'left'},
		            {field:'TCName',title:'课程名称',width:getWidth(0.2),align:'center'}
		        ]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
					   $('#insert').form('clear');
	                   $('#add').window('open');
	                   document.getElementById("add").style.visibility="visible";	
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
	                     $('#test_t').datagrid('getPager').pagination({
				          displayMsg:'当前显示从{from}到{to}共{total}记录',
				          beforePageText:'第',
				          afterPageText:'页'
				 });
	          });
	
    //修改功能
    function batchupdate(){
			var selections = $('#test_t').datagrid('getSelections');
			if(selections.length!=0){
				var selected = selections[0];
				edit();
			}else{
			    $.messager.alert('提示','请选择一行数据','error');
			 }
		}


  //查看详细信息
	function scan(rowData){
		if(rowData){
			$('#scan').window('open');
			document.getElementById("scan").style.visibility="visible";
			$('#zbh2').val(rowData.zbh);
			$('#CName2').val(rowData.CName);
			$('#kcms2').val(rowData.kcms);
			$('#TCName2').val(rowData.TCName);
		}else{
		    $.messager.alert('警告','请选择一行数据','warning');
		 }
	}

	
    function edit(){
    	var selected = $('#test_t').datagrid('getSelected');
	    if(selected){
			$('#update').window('open');
			document.getElementById("update").style.visibility="visible";
			$('#zbh1').val(selected.zbh);
			$('#CName1').val(selected.CName);
			$('#kcms1').val(selected.kcms);
			$('#TCName1').val(selected.TCName);
			}else{
			$.messager.alert('提示信息','请选择一行数据','提示信息');
			}
	   }
    function update(){
    	var selected = $('#test_t').datagrid('getSelected');
    	var selections = $('#test_t').datagrid('getSelections');
		var zbh = $("#zbh1").val();
		var CName = $("#CName1").val();
		var TCName = $("#TCName1").val();
		var kcms = $("#kcms1").val();
		if(CName.length!=0){
			if(CName.length<20){
				$.ajax({
					type:"POST",
					url:"Kcxxjy2.action",
					data:{"CName":CName,"zbh":zbh},
					cache: false,
					success:function(data){
								if(data.b == 1){
									$.messager.alert('提示信息','此章已存在!','info');
								}else{
									$.ajax({
										type:"POST",
										url:"updateKcxx.action",
										data:{"zbh":zbh,"CName":CName,"TCName":TCName,"kcms":kcms},
										success:function(){
											$('#update').dialog('close');
											var index = $('#test_t').datagrid('getRowIndex', selected);     
											$('#test_t').datagrid('deleteRow', index);
							            	$('#test_t').datagrid('appendRow',{
							            		zbh:zbh,
							        			CName:CName,
							    				TCName:TCName,
							    				kcms:kcms
							                });
							            	if(selections.length>1){
	            			           			batchupdate();
	            			           		}else{
	            			           			$.messager.alert('提示','您已成功修改所选条数据!');
	            			           			$('#test_t').datagrid('reload');
	            			           			close2();
	            				           	}
										}
									});
								}
							}
				});
			}else{$.messager.alert('提示信息','章名称长度不能大于20!','info');}
			}else{
				$.messager.alert('提示信息','章名称不能为空!','info');
				}
    		}


    
    //删除多行
	function deleteAll(){   
		 var selected = $('#test_t').datagrid('getSelections');
		 var arr=new Array();
		   if(selected.length>0){
			   $.each(selected,function(n,value){
                   arr.push(value.zbh);
                });
		    	$.messager.confirm('提示','确认删除章编号为'+arr.toString()+'的数据么?',function(id){
		    		if(id){
		    			$.each(selected,function(m,value1){
					    	var single = value1;
					    	$.ajax({
					            type:"POST",
					            url:"deleteKcxx.action",
					            data:"zbh="+single.zbh,
					           success:function(json){
									if(json.a==1){
										var index = $('#test_t').datagrid('getRowIndex',single);//获取某行的行号
										$('#test_t').datagrid('deleteRow',index);	//通过行号移除该行
										$.messager.confirm('确认','章编号为'+single.zbh+'的数据已经被成功删除！',function(r){$('#test_t').datagrid('reload');});
										}else{$.messager.alert('禁止删除','删除章编号为'+single.zbh+'的数据会破坏系统数据库的完整性,所以禁止删除这条数据！！','warning');}
						           }
					      	});
					    });
			    	}
		   		});
		   }else{$.messager.alert('提示','请选择一行数据','warning');}
	}

	//删除一行		
function dele(){
	var selected = $('#test_t').datagrid('getSelected');
	if(selected){
	  $.messager.confirm('提示信息','确定删除章编号为'+selected.zbh+'的数据吗？',function(d){
		if(d){
			$.ajax({
				type:"POST",
				url:"deleteKcxx.action",
				data:"zbh="+selected.zbh,
				success:function (){
					$.messager.alert('提示信息','删除成功！','提示信息');
					$('#test_t').datagrid('reload');
				}
			});
		};
		
	  });
	}else{
	$.messager.alert('提示信息','请选择一行数据','提示信息')};
}
	
  //查询功能
  function query(){
  	        var queryParams = $('#test_t').datagrid('options').queryParams;
  	        var kcxxtype =queryParams.kcxxtype=$('#type').val();
  	        var kcxxword=queryParams.kcxxword= $('#name').val();
  	      	//if(kcxxtype!=null){
  	      		if(kcxxword!=""){
  					$.ajax({
  						type:"POST",
  						url:"querykz.action",
  						data:{"kcxxtype":kcxxtype,"kcxxword":kcxxword},
  						cache: false,
  						success:function(data){
							if(data.a == 1){
								$('#test_t').datagrid({
						            url:'searchKcxx.action'
						        });
				    	        $('#query').window('close');
				    	        $('#test_t').datagrid('getPager').pagination({
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

    //增加功能
function add(){
//var zbh = $('#zbh').val();
var CName = $('#CName').val();
var TCName = $('#TCName').val();
var kcms = $('#kcms').val();
if(TCName.length!=0){
	if(CName.length!=0){
		if(CName.length<20){
			$.ajax({
				type:"POST",
				url:"Kcxxjy.action",
				data:{"CName":CName,"TCName":TCName},
				cache: false,
				success:function(data){
							if(data.b == 0){
								$.messager.alert('提示信息','此章已存在!','info');
							}else{
								//增加功能
							     $.ajax({
									type:"POST",
									url:"insertKcxx.action",
									data:{"CName":CName,"TCName":TCName,"kcms":kcms},
									success:function(){
										$('#add').dialog('close');
						            	
						            	$.messager.alert('提示信息','添加信息成功!!!','info',function(){
						            		$('#test_t').datagrid('reload');
						            	});
						            	
						            	$('#test_t').datagrid('appendRow',{
						            		//zbh:zbh,
						        			CName:CName,
						    				TCName:TCName,
						    				kcms:kcms
						                });
							  		}
								});
							}
						}
			});
			}else{$.messager.alert('提示信息','章名称长度不能大于20!','info');}
			}else{
				$.messager.alert('提示信息','章名称不能为空!','info');
				}
			}else{
				$.messager.alert('提示信息','课程名称不能为空!','info');
				}
	}
    
 //关闭各种窗口
	 function close1(){
     $('#add').window('close');
     }
     
     function close2(){
     $('#update').window('close');
     }

	</script>
</head>
<body class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
		<div region="center"><table id="test_t" fit="true"></table></div>
		
       <div id="add" closed="true" maximizable="false" minimizable="false" collapsible="false" class="easyui-window" title="添加课程、章信息" icon="icon-add" class="easyui-window" style="padding:5px;width:300px;height:250px;visibility:hidden;">
		<form id="insert">
			 <table align="center">
	    		<tr>	
	    			<td align="left">
	    				课程名称：
	    			</td>
	    			<td>
	    				<input id="TCName">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td align="left">
	    				章名称：
	    			</td>
	    			<td>
	    				<input id="CName" required="true" class="easyui-validatebox">
	    			</td>
	    			<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error"></span></td>--%>
	    		</tr>
	    		<tr>
	    			<td align="left">
	    				章描述：
	    			</td>
	    			<td>
	    				<s:textarea id="kcms" style="width:150px;height:80px;"></s:textarea>
	    			</td>
	    			 <%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error1"></span></td>--%>
	    		</tr>
	    		<tr>
	    		  <td>
	    		    <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="add()">确定</a>
	    		    </td>
	    			<td>
					<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close1()">取消</a>
	    		  </td>
	    		</tr>
	        </table>
        </form>
		</div>
	
	<!-- 修改界面 -->
	<div id="update" closed="true" maximizable="false" minimizable="false" collapsible="false" class="easyui-window" title="修改课程、章信息" icon="icon-edit" style="width:300px;height:230px;padding:5px;background: #fafafa;visibility:hidden;">
		 <table align="center">
    	    <tr style="display:none;">
    			<td align="left">
    				章编号：
   				</td>
   				<td>
    				<input id="zbh1" type="text" readonly="readonly">
    			</td>
    		</tr>
    		<tr>
	   			<td align="left">
	   				章名称:
	 			</td>
	 			<td>
	   				<input id="CName1" type="text">
	   			</td>
	   			<%--<td height="30" class="top_hui_text"><span class="login_txt" id="Error1"></span></td>--%>
    		</tr>
    		<tr>
    			<td align="left">
    				章描述：
   				</td>
   				<td>
    				<s:textarea id="kcms1" style="height:80px;width:150px;"></s:textarea>
    			</td>
    		</tr>
    		<tr>
    			<td align="left">
    				课程名称：
    			</td>
    			<td>
    				<input id="TCName1" type="text"> 
    			</td>
    		</tr>
    		<tr>
    		   <td align="left">
    		     <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="update()">确定</a>
    		     </td>
    			 <td align="center">
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close2()">取消</a>
    		   </td>
    		  
    		</tr>
        </table>
	</div>
	<div id="query" class="easyui-window" title="查询课程、章信息" style="padding: 10px;width: 400px;height:100;visibility:hidden;"
    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
        <div id="cx">
        	<form>
	            <table>
	                <tr>
	                  <td>
                        <select name="select" id="type">
                             <option value="CName">章名称 </option>   
                             <option value="TCName">课程名称</option>                      
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
    				章编号：
   				</td>
   				<td>
    				<input id="zbh2" type="text" disabled="true" style="width:180px;">
    			</td>
    		</tr>
    		<tr>
	   			<td>
	   				章名称:
	 			</td>
	 			<td>
	   				<input id="CName2" type="text" disabled="true" style="width:180px;">
	   			</td>
    		</tr>
    		<tr>
    			<td>
    				章描述：
   				</td>
   				<td>
    				<s:textarea id="kcms2" disabled="true" style="width:180px;height:90px;"></s:textarea>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				课程名称：
    			</td>
    			<td>
    				<input id="TCName2" type="text" disabled="true" style="width:180px;"> 
    			</td>
    		</tr>				
		</table>
	</div>		
</body>
</html>