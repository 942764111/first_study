<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	//Integer dmtzybhjs = Integer.parseInt(request.getParameter("dmtzybh"));
 %>
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
		$(function(){	
			PdType();
			$.extend($.messager.defaults,{  
        	    ok:"确定",  
        	    cancel:"取消"  
        	});
        			
			$('#test_tables').datagrid({
				title:'多媒体资料列表',
				iconCls:'icon-save',
				width:getWidth(1),
				height:550,
				nowrap: true,
				striped: true,
				singleSelect:false,
				fit:true,
				fitColumns:true,
				loadMsg:'加载数据,请等待...',
				collapsible:true,
				url:'dmtzllist.action',
				columns:[[
	                {field:'str1',title:'用户名',width:getWidth(0.1),align:'center'},
					{field:'str2',title:'分类',width:getWidth(0.1),align:'center'},
					{field:'str3',title:'资料名称',width:getWidth(0.15),align:'left'},
					{field:'str5',title:'资料描述',width:getWidth(0.2),align:'left'},
					{field:'str6',title:'资料来源',width:getWidth(0.14),align:'left'},
					{field:'dt',title:'上传日期',width:getWidth(0.15),align:'center'},
					{field:'int2',title:'浏览次数',width:getWidth(0.08),align:'center'},
					{field:'int3',title:'收藏数量',width:getWidth(0.08),align:'center'}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'update',
					  text:'修改多媒体资料',
		              iconCls:'icon-edit',
		              handler:batchupdate
				},'-',{
      				id:'btnquery',
    				text:'查询',
    				iconCls:'icon-search',
    				handler:function(){
    					$('#btnquery').linkbutton('enable');
    					$('#query').window('open');
    					document.getElementById("query").style.visibility="visible";
    					$('#name').val('');
      		       }
      			},'-',{
      				text:'详细详细',
		            iconCls:'icon-search',
		            handler:querydetail
          		},'-',{
      				text:'资源收藏 ',
		            iconCls:'icon-add',
		            handler:function(){
    					$('#scform').form('clear');
		             	addsc();
			   		} 
          		}]
			});

			$('#test_tables').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			});
		});

		function getWidth(percent){ 
		    return (document.body.clientWidth-25-10)*percent; 
		}

		//删除多行
		function deleteRows(){   
			 var selected = $('#test_tables').datagrid('getSelections');
			 var arr = new Array();
			   if(selected.length>0){
				   $.each(selected,function(n,value) {
						arr.push(value.str3);
					})
			    	$.messager.confirm('提示','确认删除资料名称为"' + arr + '"的数据吗?',function(id){
			    		if(id){
				    		for(var i=0;i<selected.length;i++){
						    	var single = selected[i];
						    	$.ajax({
				                      type:"POST",
				                      url:"deletedmtzl.action",
				                      data:{"zlmc":single.str3},
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

		//删除一行
		function deleteRow(index){	        	
    		var selected = $('#test_tables').datagrid('getSelected');
        	if(selected){
        		$.messager.confirm('提示信息','确认删除资料名称为'+selected.str3+'的资料吗?',function(d){					        	
	            	if(d){
		             	$.ajax({
		                      type:"POST",
		                      url:"deletedmtzl.action",
		                      data:{"zlmc":selected.str3},
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
				var userId = $("#userId");
				
				var count=$("#zlbh option").length;
				var obj2=document.getElementById("userId");						
				for(var i=0;i<count;i++){						
					obj2.options[i].selected=false;
		        	if (obj2.options[i].value==userId){
		        		obj2.options[i].selected=true;
		        		break;
		        	 }
				}
				$('#edit').window('open');
				document.getElementById("edit").style.visibility="visible";
				$('#update').show();
				$('#zlbh').val(selected.int1);
	            $('#userId').val(selected.str1);
	            $('#lxm').val(selected.str2);
	            $('#zlmc').val(selected.str3);
	            $('#filename').val(selected.str4);
	            $('#zlms').val(selected.str5);
	            $('#zlly').val(selected.str6);
	            $('#zlscm').val(selected.str7);
	            $('#zmfilename').val(selected.str8);
	            $('#scrq').val(selected.dt);
	            $('#llcs').val(selected.int2);
	            $('#cssl').val(selected.int3);
	            $('#changdu').val(selected.int4);
	            $('#zlmd5').val(selected.str9);
			}else{
			    $.messager.alert('警告','请选择一行数据','warning');
			}
		}
		
		function edit(){
			var selected = $('#test_tables').datagrid('getSelected');
			var selections = $('#test_tables').datagrid('getSelections');
			var zlbh = $("#zlbh").val();
			var userId = $("#userId").find("option:selected").text();
			var lxm = $("#lxm").val();
			var zlmc = $("#zlmc").val();
			var filename = $("#filename").val();
			var zlms = $("#zlms").val();
			var zlly = $("#zlly").val();
			var zlscm = $("#zlscm").val();
			var zmfilename = $("#zmfilename").val();
			var scrq = $("#scrq").val();
			var llcs = $("#llcs").val();
			var cssl = $("#cssl").val();
			var changdu = $("#changdu").val();
			var zlmd5 = $("#zlmd5").val();
			if(zlly!=selected.str6||zlscm!=selected.str7||zlms!=selected.str5||zlmc!=selected.str3) {
				$.ajax({
		            type:"POST",
		            url:"updatedmtzl.action",
		            data:{"zlbh":zlbh,"userId":userId,"lxm":lxm,"zlmc":zlmc,"filename":filename,"zlms":zlms,"zlly":zlly,"zlscm":zlscm
		            	,"zmfilename":zmfilename,"scrq":scrq,"llcs":llcs,"cssl":cssl,"changdu":changdu,"zlmd5":zlmd5},
		           	success:function(){
		            	$('#edit').dialog('close');
		            	var index = $('#test_tables').datagrid('getRowIndex', selected);     
						$('#test_tables').datagrid('deleteRow', index);
		            	$('#test_tables').datagrid('appendRow',{
			            	zlbh:zlbh,
		            		userId:userId,
		            		lxm:lxm,
		            		zlmc:zlmc,
		            		filename:filename,
		            		zlms:zlms,
		            		zlly:zlly,
		            		zlscm:zlscm,
		            		zmfilename:zmfilename,
		            		scrq:scrq,
		            		llcs:llcs,
		            		cssl:cssl,
		            		changdu:changdu,
		            		zlmd5:zlmd5
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

		//多媒体资料查询
		function query(){
	        var queryParams = $('#test_tables').datagrid('options').queryParams;
	        var dmtzltype = queryParams.dmtzltype = $('#type').val();
	        var dmtzlword = queryParams.dmtzlword = $('#name').val();
	        var aa=$('#name').val();
	        if(aa!="") {
	        	$.ajax({
					type:"POST",
					url:"searchdmtzl1.action",
					data:{"dmtzltype":dmtzltype,"dmtzlword":dmtzlword},
					cache: false,
					success:function(data){
								if(data.a == 1){
									$('#test_tables').datagrid({
					    	            url:'searchdmtzl.action'
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

	    //查看多媒体详细信息
		function querydetail(){	        	
    		var selected = $('#test_tables').datagrid('getSelected');
        	if(selected){
        		$("#lxm1").val(selected.str2);
        		$("#zlmc1").val(selected.str3);
        		$("#userId1").val(selected.str1);
        		$("#filename1").val(selected.str4);
        		$("#zlly1").val(selected.str6);
        		$("#zlscm1").val(selected.str3);
        		$("#zlms1").val(selected.str5);
        		$("#scrq1").val(selected.dt);
        		$("#llcs1").val(selected.int2);
        		$("#cssl1").val(selected.int3);
        		$("#changdu1").val(selected.int4);
				$("#querydetail").window('open');
				document.getElementById("querydetail").style.visibility="visible";
            }else{
    			$.messager.alert('提示信息','请选择一行数据','info');
        	}
		 }

		//判断角色的类型，如果角色是学生就会失去一些权限
		function PdType(){
			$.ajax({
	            type:"GET",
	           url:"GetType.action",
	           success:function(json){
		           if(json.tip=="S"){
		        	   $('#update').linkbutton('disable');
			          }
		           }
	      	});
		}
	</script>
	<script language="JavaScript" type="text/javascript">	
			var TextUtil = new Object();
			TextUtil.NotMax = function(oTextArea){
				var maxText = oTextArea.getAttribute("maxlength"); 
				if(oTextArea.value.length > maxText){
						oTextArea.value = oTextArea.value.substring(0,maxText);
						alert("只能输入50个字");
				}
			}
			
			
			//添加收藏
		function addsc(){
			var selected = $('#test_tables').datagrid('getSelected');		
			//alert(selected);
			if(selected){
				$.ajax({
     				type:'post',
     				url:'listyhzdy.action',
     				success:function(data){
     					var a = data.rows;
     					$('#zdyflmc').combobox({
     						data:a,
     						valueField:'value',
     						textField:'value'
     					});
     				}
     				});
				$('#addsc').window('open');
				$('#scth').val(selected.int1);
				$('#sctg').val(selected.str5);
			}
			else{
			    $.messager.alert('警告','请选择一行数据','warning');
			 }/**/
		}

		function ShouCang(){
			var selected = $('#test_tables').datagrid('getSelected');
			var scth = $('#scth').val();
			var zdyflmc = $('#zdyflmc').combobox('getValue');
			var zyms = $("#sctg").val();
			if(!(zdyflmc==null)){
				$.ajax({
			           type:"POST",
			           url:"addDmtcollection.action",
			           data:{"zybh":scth,"zdyflmc":zdyflmc,"zyms":zyms,"zylx":4},
			           success:function(r){
				           var wab = r.message;
				           if(wab==1){
				        	   $.messager.alert('提示','您已成功收藏所选数据!');
				        	   closesc();
				           }
				           else {
				        	   closesc();
				        	   $.messager.alert('提示','该题目已经被收藏过，不用再收藏啦!');
						   }
			           }
				    });
			}
			else {
				$.messager.alert('警告','请选择您的自定义分类','warning');
			}
			
		}
		function closesc(){
			$('#addsc').window('close');
		}
			
			
	</script>
	</head>
	
	<body class="easyui-layout" style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
		<div region="center">
			<table id="test_tables">
			</table>
		</div>	
			   
		<div id="edit" class="easyui-window" icon="icon-edit" title="修改" closed="true" 
				style="padding:5px;width:460px;height:320px;visibility:hidden;" maximizable="false" 
				minimizable="false" collapsible="false">
			<s:form id="update">
				<table> 
					<tr>
						<td> 
							分类:
						</td>
						<td>
							<input type="text" id="lxm" readonly="readonly" style="border:0px"/>
						</td>
						<td> 
							上传日期: 
						</td>
						<td>
							<input type="text" id="scrq" readonly="readonly" style="border:0px"/>
						</td>
					</tr>
					<tr>
						<td> 
							浏览次数: 
						</td>
						<td>
							<input type="text" id="llcs" readonly="readonly" style="border:0px"/>
						</td>
						<td> 
							收藏数量: 
						</td>
						<td>
							<input type="text" id="cssl" readonly="readonly" style="border:0px"/>
						</td>
					</tr>
					<tr style="display:none;">
						<td> 
							长度: 
						</td>
						<td>
							<input type="text" id="changdu" readonly="readonly"/>
						</td>
					</tr>
				</table>
				<table>
					<tr>
			 			<td>
							资料名称:
						</td>
						<td>
							<input type="text" id="zlmc" style="width:200px;" />
						</td>
					</tr>
					<tr style="display:none;">
			 			<td>
							UserId:
						</td>
						<td>
							<s:select id="userId" list="userinfolist" listKey="userId" listValue="userId"></s:select>		
						</td>
					</tr>					
									
					<tr style="display:none;">
						<td> 
							资料文件名: 
						</td>
						<td>
							<input type="text" id="filename" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td> 
							资料来源:
						</td>
						<td>
							<input type="text" id="zlly" maxlength="80" style="width:200px;"/>
						</td>
						<td style="color:red;">
							&nbsp;*&nbsp;
						</td>
						<td>
							请写明资料出处
						</td>
					</tr>
					<tr>
						<td> 
							资料素材名:<br/><br/>
						</td>
						<td>
							<input type="text" id="zlscm" maxlength="100" style="width:200px;"/>
						</td>
					</tr>
					<tr>
						<td> 
							资料描述:
						</td>
						<td>
							<textarea rows="5" cols="19" id="zlms"  style="width:200px;"
								onpropertychange="TextUtil.NotMax(this)" maxlength="200">
							</textarea>
						</td>
						<td style="color:red;">
							&nbsp;*&nbsp;
						</td>
						<td>
							最多只能输入200字
						</td>
					</tr>
					<tr style="display:none;">
						<td> 
							字幕文件: 
						</td>
						<td>
							<input type="text" id="zmfilename" readonly="readonly"/>
						</td>
					</tr>
					
					<tr style="display:none;">
						<td> 
							MD5值:
						</td>
						<td>
							<input type="text" id="zlmd5" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<a class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" onclick="edit()">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
		                       		<option value="zlms">资料描述</option>   
		                       		<option value="zlly">资料来源</option>   
		                       		<option value="zlscm">资料素材名</option>   
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
		
		<!-- ***********添加收藏html代码 *************-->

	<div id="addsc" closed="true" class="easyui-window" title="添加收藏" icon="icon-add" style="width:400px;height:240px;" closed="true">
	   	<center>
	   		<s:form id="scform">
	   			<table>
	   				<tr>
						<td> 
						资料名称: 
						</td>
						<td>
							<input id="scth" type="text" readonly="true" style="width:250px;"/>
						</td>
					</tr>
						<tr>
						<td> 
						自定义分类名称: 
						</td>
						<td>
							<input id="zdyflmc"  class="easyui-combobox" name="language" style="width:250px;"/>
						</td>
					</tr>
					<tr>
						<td> 
						资源描述: 
						</td>
						<td>
							<textarea id="sctg" name="tg" style="width:250px;height:100px;"></textarea>										 
						</td>
					</tr>	
	   			</table>
	   		</s:form>
   			<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
   				<a class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" onclick="ShouCang()">收藏</a>
   				&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="closesc()">取消</a>
			</div>
	   	</center>
	  </div>	

<!-- ***********添加收藏html代码 *************-->
		
		
		
		
		
		
		<div id="querydetail" class="easyui-window" title="查看详细详细" style="padding: 10px;width: 550px;height:420px;visibility:hidden;"
		   	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
			<div>
				<table align="center" width="520"> 
					<tr>
						<td> 
							资料分类:
						</td>
						<td>
							<input type="text" id="lxm1" readonly="readonly" style="border:0px;width:80px;"/>
						</td>
						<td>
							用户名:
						</td>
						<td>
							<input type="text" id="userId1" readonly="readonly" style="border:0px"/>
						</td>
					</tr>
					<tr>
						<td> 
							长度: 
						</td>
						<td>
							<input type="text" id="changdu1" readonly="readonly" style="border:0px"/>
						</td>
						<td> 
							上传日期: 
						</td>
						<td>
							<input type="text" id="scrq1" readonly="readonly" style="border:0px"/>
						</td>
					</tr>
					<tr>
						<td> 
							浏览次数: 
						</td>
						<td>
							<input type="text" id="llcs1" readonly="readonly" style="border:0px"/>
						</td>
						<td> 
							收藏数量: 
						</td>
						<td>
							<input type="text" id="cssl1" readonly="readonly" style="border:0px"/>
						</td>
					</tr>
				</table>
				<table align="center" width="520">
					<tr>
						<td>
							资料名称:
						</td>
						<td>
							<textarea rows="2" readonly="readonly" cols="19" id="zlmc1" style="width:400px;">
							</textarea>
						</td>
					</tr>
					<tr>
						<td>
							文件名:
						</td>
						<td>
							<textarea rows="2" readonly="readonly" cols="19" id="filename1" style="width:400px;">
							</textarea>
						</td>
					</tr>
					<tr>
						<td> 
							资料来源:
						</td>
						<td>
							<textarea rows="2" readonly="readonly" cols="19" id="zlly1" style="width:400px;">
							</textarea>
						</td>
					</tr>
					<tr>
						<td> 
							资料素材名:
						</td>
						<td>
							<textarea rows="2" readonly="readonly" cols="19" id="zlscm1" style="width:400px;">
							</textarea>
						</td>
					</tr>
					
					<tr>
						<td> 
							资料描述:
						</td>
						<td>
							<textarea rows="5" readonly="readonly" cols="19" id="zlms1" style="width:400px;">
							</textarea>
						</td>
					</tr>
				</table>
		    </div>
		</div>
						
	
	</body>
	
</html>
   