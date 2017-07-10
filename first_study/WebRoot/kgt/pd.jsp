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
	<script type="text/javascript" src="kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="js/sjld.js"></script>
		
<script type="text/javascript">

		function getWidth(percent){ 
		    return (document.body.clientWidth-25-11)*percent; 
		}
		$(function(){
			
			$('#test_tables').datagrid({
				title:'判断题管理（双击可查看详细信息！）',
				iconCls:'icon-save',			
				width:getWidth(1),
				nowrap: true,
				striped: true,	//隔行变色
				collapsible:true,
				fitColumns:true,
				fit:true,
				loadMsg:'加载数据,请等待...',
				url:'pd.action',
				onDblClickRow: function(rowIndex, rowData) {
				   scan(rowData);
				} ,
				columns:[[ 
					{field:'kcmc',title:'课程名',width:getWidth(0.11),align:'left'},	
					{field:'str1',title:'章名',width:getWidth(0.11),align:'left'},	
					{field:'zmc',title:'节名',width:getWidth(0.11),align:'left'},
					{field:'zsdmc',title:'知识点名',width:getWidth(0.09),align:'left'}, 	
					{field:'str3',title:'题干',width:getWidth(0.41),align:'left'},					
					{field:'int2',title:'答案',width:getWidth(0.14),align:'center',
						formatter:function(val,rec){ 
						val= rec.int2;
						if(val==0){
							val= "错误";
							}
						if(val==1){
								val= "正确";
								}
						return val; 
						}
					},
					{field:'int4',title:'难易度(数越小越难)',width:getWidth(0.14),align:'center'}
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
    					$('#cx').form('clear');
    					$('#query').window('open');
    					 document.getElementById("query").style.visibility="visible";
    				}
    			},'-',{
					 
					  text:'返回组卷页面',
					  iconCls:'icon-back',
		              handler:function(){
		              window.location.href="Ttest.action";
		              window.event.returnValue = false;
			   			} 
  			},'-',{
				 
				  text:'添加收藏',
				  iconCls:'icon-add',
	              handler:function(){
					$('#scform').form('clear');
	             	addsc();
		   		} 
		}
      			]
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
						var a;
						//同步数据后可以直接取得textarea的value  
						KE.sync('addtg');
						//html = document.getElementById('addtg').value;
						html = $('#addtg').val(); //jQuery
				        a=html;
						var TCName = $("#bindTCName").find("option:selected").text();
						var CName = $("#bindZh").find("option:selected").text();
						var zmc = $("#bindK").find("option:selected").text();
						var zsdmc = $("#bindZsd").find("option:selected").text();
						KE.html('addtg', ''); 
						var tg = a;
						var da = $("#addda").val();
						if(zsdmc!="--请选择知识点名称--"&&zsdmc!=""){
							if(da!=0&da!=1){
								$.messager.alert('提示信息','请在答案这一栏填写0或1!','info');
								}else{
									$.ajax({
							            type:"POST",
							            url:"insertpd2.action",
							            data:{"zmc":zmc,"TCName":TCName,"CName":CName,"zsdmc":zsdmc,
						            	"tg":tg,"da":da},
							           success:function(){
							            	$('#add').dialog('close');
							            	$.messager.alert('提示信息','添加信息成功!!!','info',function(){
							            		$('#test_tables').datagrid('reload');
							            	});
							            	$('#test_tables').datagrid('appendRow',{					            		
							            		zmc:zmc,
							            		TCName:TCName,
							            		CName:CName,
							            		zsdmc:zsdmc,
									            tg:tg,
									            da:da
							                });  
									     }
						      		});
									}
							
						}else{$.messager.alert('提示信息','知识点名称不能为空!','info');}
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

		KE.show({    
			id : 'addtg',//TEXTAREA输入框的ID 
			items : ['subscript','superscript', 'bold','image'],
			imageUploadJson : '../../jsp/upload_json.jsp',  
		    fileManagerJson : '../../jsp/file_manager_json.jsp',  
		    allowFileManager : true, 
		    allowUpload : true, //允许上传图片 
		    afterCreate : function(id) {  
		        KE.event.ctrl(document, 13, function() {  
		            KE.util.setData(id);  
		            document.forms['example'].submit();  
		        });  
		        KE.event.ctrl(KE.g[id].iframeDoc, 13, function() {  
		            KE.util.setData(id);  
		            document.forms['example'].submit();  
		        });  
		    } 
			});

		
		//删除多行
		function deleteAll(){   
			 var selected = $('#test_tables').datagrid('getSelections');
			 var arr=new Array();
			   if(selected.length>0){
				   $.each(selected,function(n,value){
                       arr.push(value.int5);
                    });
			    	$.messager.confirm('提示','确认删除题号为'+arr.toString()+'的数据么?',function(id){
			    		if(id){
				    		for(var i=0;i<selected.length;i++){
						    	var single = selected[i];
						     	$.ajax({
						            type:"POST",
						            url:"deletepd.action",
						            data:"th="+single.int5,
						           success:function(){}
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
        		$.messager.confirm('删除','确认删除 题干为   “'+selected.str3+'”  的数据吗?',function(d){					        	
	            	if(d){
		             	$.ajax({
		                      type:"POST",
		                      url:"deletepd.action",
		                      data:"th="+selected.int5,
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
				$('#zsdmc1').val(rowData.zsdmc);
				$('#zmc1').val(rowData.zmc);
	            $('#kcmc1').val(rowData.kcmc);
	            $('#zhangm1').val(rowData.str1);	
	            $('#th1').val(rowData.int5);
	            var x=rowData.str3
	          	//给编辑器设置HTML内容
				KE.html('tg1', x);
	           if(rowData.int2==1){
	        	   $('#da1').val("正确");
		           }else{
		        	   $('#da1').val("错误");
			           }
	            $('#csrcs1').val(rowData.int1);
	            $('#zqrcs1').val(rowData.int7);
	            $('#nyd1').val(rowData.int4);	
	            $('#md51').val(rowData.str2);
			}else{
			    $.messager.alert('警告','请选择一行数据','warning');
			 }
		}
		KE.show({
			id : 'tg1',
			items : [],
			resizeMode : 0,
			afterCreate : function(id) {
			//KE.toolbar.disable(id, []);
			KE.readonly(id);//只读
			KE.g[id].newTextarea.disabled = true;
			}
			});

		//添加收藏
		function addsc(){
			var selected = $('#test_tables').datagrid('getSelected');		
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
				$('#scth').val(selected.int5);
				$('#sczmc').val(selected.kcmc);
				$('#sctg').val(selected.str3);
			}
			else{
			    $.messager.alert('警告','请选择一行数据','warning');
			 }/**/
		}

		function ShouCang(){
			var selected = $('#test_tables').datagrid('getSelected');
			var scth = $('#scth').val();
			var sczmc = $('#sczmc').val();
			var zdyflmc = $('#zdyflmc').combobox('getValue');
			var zyms = $("#sctg").val();
			if(!(zdyflmc==null)){
				$.ajax({
			           type:"POST",
			           url:"addPdcollection.action",
			           data:{"zybh":scth,"sskcmc":sczmc,"zdyflmc":zdyflmc,"zyms":zyms},
			           success:function(r){
				           var wab = r.message;
				           if(wab==2){
				        	   close6();
				        	   $.messager.alert('提示','该题目已经被收藏过，不用再收藏啦!');
				        	   
				           }
				           else {
				        	   $.messager.alert('提示','您已成功收藏所选题目!');
				        	   close6();
					       }
			           }
				    });
			}
			else {
				$.messager.alert('警告','请选择您的自定义分类','warning');
			}
		}

		 function close6(){
			   $('#addsc').window('close');
		}

		
		//修改
		function updata(){
			var selected = $('#test_tables').datagrid('getSelected');						
			if(selected){
				$('#edit').window('open');
				document.getElementById("edit").style.visibility="visible";
				$('#update').show();
				$('#zsdmc').val(selected.zsdmc);
				$('#zmc').val(selected.zmc);
	            $('#kcmc').val(selected.kcmc);
	            $('#zhangm').val(selected.str1);	
	            $('#th').val(selected.int5);
	            $('#tg').val(selected.str3);
	            var x=selected.str3
	          	//给编辑器设置HTML内容
				KE.html('tg', x); 
	            $('#da').val(selected.int2);
	            $('#csrcs').val(selected.int1);
	            $('#zqrcs').val(selected.int7);
	            $('#nyd').val(selected.int4);	
	            $('#md5').val(selected.str2);
			}else{
			    $.messager.alert('警告','请选择一行数据','warning');
			 }
		}
	    function edit(){
	    	var b;
			//同步数据后可以直接取得textarea的value  
			KE.sync('tg');
			//html = document.getElementById('tg').value;
			html = $('#tg').val(); //jQuery
			b=html;
			var selected = $('#test_tables').datagrid('getSelected');
			var selections = $('#test_tables').datagrid('getSelections');
			var kcmc = $("#kcmc").val();
			var zhangm=$("#zhangm").val();
			var zmc = $("#zmc").val();
			var zsdmc = $("#zsdmc").val();
			var th = $("#th").val();
			var tg = b;
			var da = $("#da").val();
			var csrcs = $("#csrcs").val();
			var zqrcs = $("#zqrcs").val();
			$.ajax({
	            type:"POST",
	            url:"updatepd.action",
	            data:{"zmc":zmc,"kcmc":kcmc,"zsdmc":zsdmc,"zhangm":zhangm,"th":th,
            	"tg":tg,"da":da,"csrcs":csrcs,"zqrcs":zqrcs},
	           success:function(){
	            	$('#edit').dialog('close');
	            	var index = $('#test_tables').datagrid('getRowIndex', selected);     
					$('#test_tables').datagrid('deleteRow', index);
	            	$('#test_tables').datagrid('appendRow',{
	            		zmc:zmc,
	            		kcmc:kcmc,
	            		zsdmc:zsdmc,
	            		zhangm:zhangm,
	            		th:th,
			            tg:tg,
			            da:da,
			            csrcs:csrcs,
			            zqrcs:zqrcs
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
		 function close2(){
			   $('#edit').window('close');
		}
		
		 KE.show({    
				id : 'tg',//TEXTAREA输入框的ID 
				items : ['subscript','superscript', 'bold','image'],
				imageUploadJson : '../../jsp/upload_json.jsp',  
			    fileManagerJson : '../../jsp/file_manager_json.jsp',  
			    allowFileManager : true, 
			    allowUpload : true, //允许上传图片 
			    afterCreate : function(id) {  
			        KE.event.ctrl(document, 13, function() {  
			            KE.util.setData(id);  
			            document.forms['example'].submit();  
			        });  
			        KE.event.ctrl(KE.g[id].iframeDoc, 13, function() {  
			            KE.util.setData(id);  
			            document.forms['example'].submit();  
			        });  
			    }
			});
			
		
		function query(){
	        var queryParams = $('#test_tables').datagrid('options').queryParams;
	        var queryType =queryParams.queryType="tg";
	        var queryWord=queryParams.queryWord= $('#name').val();
	        if(queryWord!=""){
					$.ajax({
						type:"POST",
						url:"querypd1.action",
						data:{"queryType":queryType,"queryWord":queryWord},
						cache: false,
						success:function(data){
									if(data.a == 1){
										$('#test_tables').datagrid({
						    	            url:'querypd.action'
						    	        });
						    	        $('#query').window('close');
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
	
		<div region="center"><table id="test_tables"></table></div>
			
			<div id="add" closed="true" title="添加判断题" icon="icon-add" style="width:423px;height:400px;padding:5px;background: #fafafa;visibility:hidden;">
					<center>	    
					    <s:form id="insert">
					    	<table align="center">	
					    		<tr>
									<td> 
									课程名称: 
									</td>
									<td>
										<select id="bindTCName" onChange="getZh()" style="width:300px;">
										</select>
									</td>
								</tr>
								<tr>
									<td> 
									章名称: 
									</td>
									<td>
									   	<select id="bindZh"  onChange="getKcbh()" style="width:300px;">
									   		<option  value="-1">--请选择章名称--</option>
									   	</select>
									</td>
								</tr>	
								<tr>
									<td> 
									节名称: 
									</td>
									<td>
									   	<select id="bindK"  onChange="getZsd()" style="width:300px;">
									   		<option  value="-1">--请选择节名称--</option>
									   	</select>
									</td>
								</tr>			
								<tr>
									<td> 
									知识点名称:
									</td>
									<td>										
										<select id="bindZsd" onChange="" style="width:300px;">
									     <option  value="-1">--请选择知识点名称--</option>
									    </select>
									</td>
								</tr>
								<tr>
									<td> 
									题干: 
									</td>
									<td>
										<textarea id="addtg" name="addtg" style="width:300px;height:160px;visibility:hidden;"></textarea> 										 
									</td>
								</tr>
								<tr>
									<td> 
									答案: 
									</td>
									<td>
										<input id="addda" type="text" style="width:130px;"/>请输入0（错误）或1（正确）
									</td>
								</tr>
							</table>
					    </s:form>
					</center>
			   </div>
			<div id="addsc" closed="true" class="easyui-window" title="添加收藏" icon="icon-add" style="width:400px;height:300px;">
			   	<center>
			   		<s:form id="scform">
			   			<table>
			   				<tr>
								<td> 
								题号: 
								</td>
								<td>
									<div style="200px;">
										<input id="scth" type="text" readonly="true" style="width:250px;"/>
									</div>
								</td>
							</tr>
							<tr>
								<td> 
								章名称: 
								</td>
								<td>
									<input id="sczmc" type="text" readonly="true" style="width:250px;"/>
								</td>
							</tr>
							
							<tr>
								<td> 
								资源描述: 
								</td>
								<td>
									<textarea id="sctg" name="sctg" style="width:250px;height:100px;" readonly="true"></textarea> 										 
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
			   			</table>
			   		</s:form>
			   		<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			   				<a class="easyui-linkbutton" iconCls="icon-ok" 
			   					href="javascript:void(0)" onclick="ShouCang()">收藏</a>
			   				&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="easyui-linkbutton" iconCls="icon-cancel"
								href="javascript:void(0)" onclick="close6()">取消</a>
					</div>
			   	</center>
			   </div>					
				<div id="edit" closed="true" class="easyui-window" title="修改判断题" icon="icon-edit" style="width:435px;height:280px;padding:5px;background: #fafafa;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
					<center>	    
					    <s:form id="update">
					    	<table align="center">						    				
								<tr style="display:none;">
									<td> 
									知识点名称:
									</td>
									<td>										
										<input id="zsdmc" type="text"/>
									</td>
								</tr>				
								<tr style="display:none;">
									<td> 
									节名称: 
									</td>
									<td>
										<input id="zmc" type="text"/>
									</td>
								</tr>
								<tr style="display:none;">
									<td> 
									课程名称: 
									</td>
									<td>
										<input id="kcmc" type="text"/>
									</td>
								</tr>
								<tr style="display:none;">
									<td> 
									章名称: 
									</td>
									<td>
										<input id="zhangm" type="text"/>
									</td>
								</tr>
								<tr style="display:none;">
									<td> 
									题号: 
									</td>
									<td>
										<input id="th" type="text"/>
									</td>
								</tr>
								<tr>
									<td> 
									题干: 
									</td>
									<td>
										<textarea id="tg" name="tg" style="width:300px;height:160px;visibility:hidden;"></textarea> 										 
									</td>
									
								</tr>
								<tr>
									<td> 
									答案: 
									</td>
									<td>
										<input id="da" type="text" style="width:130px;"/>请输入0（错误）或1（正确）
									</td>
								</tr>
								<tr style="display:none;">
									<td> 
									测试次数: 
									</td>
									<td>
										<input id="csrcs" type="text"/>
									</td>
								</tr>
								<tr style="display:none;">
									<td> 
									正确次数: 
									</td>
									<td>
										<input id="zqrcs" type="text"/>
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
					</center>
			   </div>		
				<div id="query" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;visibility:hidden;"
		    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
		        <div>
		        	<form id="cx">
			            <table>
			                <tr>
			                    <td>
			                     	  题干：
			                    </td>
			                    <td><input type="text" name="id" id="name"  required="true"></td>
			                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
			                </tr>
			            </table>
		            </form>
		        </div>
		    </div>	
		    <div id="scan" closed="true" class="easyui-window" title="查看详细信息" style="width:420px;height:370px;padding:5px;background: #fafafa;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
			    	<table align="center">						    				
						<tr>
							<td> 
							知识点名称:
							</td>
							<td>										
								<input id="zsdmc1" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>				
						<tr>
							<td> 
							节名称: 
							</td>
							<td>
								<input id="zmc1" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td> 
							课程名称: 
							</td>
							<td>
								<input id="kcmc1" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td> 
							章名称: 
							</td>
							<td>
								<input id="zhangm1" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td> 
							题号: 
							</td>
							<td>
								<input id="th1" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td> 
							题干: 
							</td>
							<td>
								<textarea id="tg1" name="tg" style="width:300px;height:130px;visibility:hidden;"  disabled="true"></textarea>
							</td>
						</tr>
						<tr>
							<td> 
							答案: 
							</td>
							<td>
								<input id="da1" type="text" style="width:300px;" disabled="true"/>
							</td>
						</tr>
					</table>
			   </div>		
		    	  							
	</body>
</html>
   