<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("addtg") != null ? request.getParameter("addtg") : "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择题管理</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="js/sjld.js"></script>
<script type="text/javascript">
		$(function(){	
			
			$('#test_tables').datagrid({
				title:'选择题管理(双击可查看详细信息！)',
				iconCls:'icon-save',			
				width:getWidth(1),
				nowrap: true,
				striped: true,
				fitColumns:true,
				url:'xz.action',
				loadMsg:'加载数据,请等待...',
				onDblClickRow: function(rowIndex, rowData) {
				       scan(rowData);
				   } ,
				columns:[[     
					{field:'tcname',title:'课程名',width:getWidth(0.09),align:'center'},
					{field:'kcmc',title:'章名',width:getWidth(0.09),align:'center'},                          
					{field:'zmc',title:'节名',width:getWidth(0.09),align:'center'},
					{field:'zsdmc',title:'知识点名',width:getWidth(0.09),align:'center'},			
					{field:'str3',title:'题干',width:getWidth(0.1),align:'left'},
					{field:'str4',title:'A',width:getWidth(0.09),align:'left'},
					{field:'str5',title:'B',width:getWidth(0.09),align:'left'},
					{field:'str6',title:'C',width:getWidth(0.09),align:'left'},
					{field:'str7',title:'D',width:getWidth(0.1),align:'left'},
					{field:'str1',title:'答案',width:getWidth(0.05),align:'center'},
					{field:'int4',title:'难易度(数越小越难)',width:getWidth(0.12),align:'center'}
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

		function getWidth(percent){ 
		    return (document.body.clientWidth-25-11)*percent; 
		}
		
		
		$(function(){
			$('#add').dialog({
				buttons:[{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){		
						var a;
						//取得HTML内容
						html = KE.html('addtg');
						//同步数据后可以直接取得textarea的value  
						KE.sync('addtg');
						//html = document.getElementById('addtg').value;
						html = $('#addtg').val(); //jQuery
						//设置HTML内容
						//KE.html('addtg', '');
						//alert(html);
				        a=html;
						var TCName = $("#bindTCName").find("option:selected").text();
						var CName = $("#bindZh").find("option:selected").text();
						var zmc = $("#bindK").find("option:selected").text();
						var zsdmc = $("#bindZsd").find("option:selected").text();
						//var th = $("#addth").val();
						KE.html('addtg', ''); 
						var tg = a;
						var xx1 = $("#addxx1").val();
						var xx2 = $("#addxx2").val();
						var xx3 = $("#addxx3").val();
						var xx4 = $("#addxx4").val();
						var ddx = $("#addddx").val();
						var da = $("#addda").val();
						if(zsdmc!="--请选择知识点名称--"&&zsdmc!=""){
							$.ajax({
					            type:"POST",
					            url:"insertxz.action",
					            data:{"zmc":zmc,"TCName":TCName,"CName":CName,"zsdmc":zsdmc,
				            	"tg":tg,"xx1":xx1,"xx2":xx2,"xx3":xx3,"xx4":xx4,
				            	"ddx":ddx,"da":da},
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
					            		//th:th,
							            tg:tg,
							            xx1:xx1,
							            xx2:xx2,
							            xx3:xx3,
							            xx4:xx4,
							            ddx:ddx,
							            da:da
					                });  
							   }
				      	});
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
			    	$.messager.confirm('提示','确认删除题号为  '+arr.toString()+'的数据么?',function(id){
			    		if(id){
				    		for(var i=0;i<selected.length;i++){
						    	var single = selected[i];
						     	$.ajax({
						            type:"POST",
						           url:"deletexz.action",
						            data:"th="+single.int5,
						           success:function(){}
						      	});
						     	var index = $('#test_tables').datagrid('getRowIndex',single);//获取某行的行号
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
        		$.messager.confirm('删除','确认删除 题干为  “'+selected.str3+'” 的数据吗?',function(d){					        	
	            	if(d){
		             	$.ajax({
		                      type:"POST",
		                      url:"deletexz.action",
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
			//var selected = $('#test_tables').datagrid('getSelected');						
			if(rowData){				
				$('#scan').window('open');
				document.getElementById("scan").style.visibility="visible";
				$('#zsdmc1').val(rowData.zsdmc);
				$('#zmc1').val(rowData.zmc);
	            $('#kcmc1').val( rowData.kcmc);
	            $('#tcname1').val( rowData.tcname);
	            $('#th1').val(rowData.int5);
	            var x=rowData.str3
	          	//给编辑器设置HTML内容
				KE.html('tg1', x);
	            $('#xx11').val(rowData.str4);
	            $('#xx21').val(rowData.str5);
	            $('#xx31').val(rowData.str6);
	            $('#xx41').val(rowData.str7);
	            $('#ddx1').val(rowData.int2);
	            $('#da1').val(rowData.str1);
	            $('#csrcs1').val(rowData.int1);
	            $('#zqrcs1').val(rowData.int7);
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
			KE.readonly(id);
			KE.g[id].newTextarea.disabled = true;
			}
			});


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
			           url:"addXzcollection.action",
			           data:{"zybh":scth,"sskcmc":sczmc,"zdyflmc":zdyflmc,"zyms":zyms},
			           success:function(r){
				           var wab = r.message;
				           if(wab==1){
				        	   $.messager.alert('提示','您已成功收藏所选数据!');
				        	   close6();
				           }
				           else {
				        	   close6();
				        	   $.messager.alert('提示','该题目已经被收藏过，不用再收藏啦!');
						   }
			           }
				    });
			}
			else {
				$.messager.alert('警告','请选择您的自定义分类','warning');
			}
			
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
	            $('#kcmc').val( selected.kcmc);
	            $('#tcname').val( selected.tcname);
	            $('#th').val(selected.int5);
	            //KE.html('tg', ''); 
	            var x=selected.str3
	          	//给编辑器设置HTML内容
				KE.html('tg', x); //KE.html('tg','ccccccc');为静态赋值,输出为ccccccc
	            //$('#tg').val(selected.str3);
	            $('#xx1').val(selected.str4);
	            $('#xx2').val(selected.str5);
	            $('#xx3').val(selected.str6);
	            $('#xx4').val(selected.str7);
	            $('#ddx').val(selected.int2);
	            $('#da').val(selected.str1);
	            $('#csrcs').val(selected.int1);
	            $('#zqrcs').val(selected.int7);
			}else{
			    $.messager.alert('警告','请选择一行数据','warning');
			 }
					
		}

		
		
	    function edit(){
   			var b;
			//取得HTML内容
			html = KE.html('tg');
			//同步数据后可以直接取得textarea的value  
			KE.sync('tg');
			//html = document.getElementById('tg').value;//原生API
			html = $('#tg').val(); //jQuery
		    b=html;
			var selected = $('#test_tables').datagrid('getSelected');
			var selections = $('#test_tables').datagrid('getSelections');
			var zsdmc = $("#zsdmc").val();
			var zmc = $("#zmc").val();
			var kcmc = $("#kcmc").val();
			var tcname = $("#tcname").val();
			var th = $("#th").val();
			var tg = b;
			var xx1 = $("#xx1").val();
			var xx2 = $("#xx2").val();
			var xx3 = $("#xx3").val();
			var xx4 = $("#xx4").val();
			var ddx = $("#ddx").val();
			var da = $("#da").val();
			var csrcs = $("#csrcs").val();
			var zqrcs = $("#zqrcs").val();
			$.ajax({
	            type:"POST",
	            url:"updatexz.action",
	            data:{"th":th,"zsdmc":zsdmc,"zmc":zmc,"kcmc":kcmc,"tcname":tcname,
	            	"tg":tg,"xx1":xx1,"xx2":xx2,"xx3":xx3,"xx4":xx4,
	            	"ddx":ddx,"da":da,"csrcs":csrcs,"zqrcs":zqrcs},
	           success:function(){
	            	$('#edit').dialog('close');
	            	var index = $('#test_tables').datagrid('getRowIndex', selected);     
					$('#test_tables').datagrid('deleteRow', index);
	            	$('#test_tables').datagrid('appendRow',{
	            		zsdmc:zsdmc,
	            		zmc:zmc,
	            		kcmc:kcmc,
	            		tcname:tcname,
	            		th:th,
			            tg:tg,
			            xx1:xx1,
			            xx2:xx2,
			            xx3:xx3,
			            xx4:xx4,
			            ddx:ddx,
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
		 function close6(){
			   $('#addsc').window('close');
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
					url:"queryxz1.action",
					data:{"queryType":queryType,"queryWord":queryWord},
					cache: false,
					success:function(data){
								if(data.a == 1){
									$('#test_tables').datagrid({
					    	            url:'queryxz.action'
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
		
			<div id="add" closed="true" title="添加选择题" icon="icon-add" style="width:423px;height:400px;padding:5px;background: #fafafa;visibility:hidden;">
					<center>	
					<%=htmlData%>    
					    <s:form id="insert">
					    	<table align="center">	
					    		<tr>
									<td> 
									题干: 
									</td>
									<td>
										<textarea id="addtg" name="addtg" style="width:300px;height:160px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea> 										 
									</td>
								</tr>					    				
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
									选项一: 
									</td>
									<td>
										<input id="addxx1" type="text" style="width:300px;"/>
									</td>
								</tr>
								<tr>
									<td> 
									选项二: 
									</td>
									<td>
										<input id="addxx2" type="text" style="width:300px;"/>
									</td>
								</tr>
								<tr>
									<td> 
									选项三: 
									</td>
									<td>
										<input id="addxx3" type="text" style="width:300px;"/>
									</td>
								</tr>
								<tr>
									<td> 
									选项四: 
									</td>
									<td>
										<input id="addxx4" type="text" style="width:300px;"/>
									</td>
								</tr>
								<tr>
									<td> 
									单多选: 
									</td>
									<td>
										<input id="addddx" type="text" style="width:130px;"/>是单选请输入0，是多选请输入1
									</td>
								</tr>
								<tr>
									<td> 
									答案: 
									</td>
									<td>
										<input id="addda" type="text" style="width:130px;"/>请输入A、B、C、D
									</td>
								</tr>
							</table>
					    </s:form>
					</center>
			   </div>
								
				<div id="edit" closed="true" class="easyui-window" title="修改选择题" icon="icon-edit" style="width:450px;height:370px;padding:5px;background: #fafafa;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
					<center>	    
					    <s:form id="update">
					    	<table align="center">						    				
								<tr style="display:none;">
									<td> 
									知识点名:
									</td>
									<td>										
										<input id="zsdmc" type="text"/>
									</td>
								</tr>				
								<tr style="display:none;">
									<td> 
									节名: 
									</td>
									<td>
										<input id="zmc" type="text"/>
									</td>
								</tr>
								<tr style="display:none;">
									<td> 
									章名: 
									</td>
									<td>
										<input id="kcmc" type="text"/>
									</td>
								</tr>
								<tr style="display:none;">
									<td> 
									课程名: 
									</td>
									<td>
										<input id="tcname" type="text"/>
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
									选项一: 
									</td>
									<td>
										<input id="xx1" type="text" style="width:300px;"/>
									</td>
								</tr>
								<tr>
									<td> 
									选项二: 
									</td>
									<td>
										<input id="xx2" type="text" style="width:300px;"/>
									</td>
								</tr>
								<tr>
									<td> 
									选项三: 
									</td>
									<td>
										<input id="xx3" type="text" style="width:300px;"/>
									</td>
								</tr>
								<tr>
									<td> 
									选项四: 
									</td>
									<td>
										<input id="xx4" type="text" style="width:300px;"/>
									</td>
								</tr>
								<tr>
									<td> 
									单多选: 
									</td>
									<td>
										<input id="ddx" type="text" style="width:130px;"/>是单选请输入0，是多选请输入1
									</td>
								</tr>
								<tr>
									<td> 
									答案: 
									</td>
									<td>
										<input id="da" type="text" style="width:130px;"/>请输入A、B、C、D
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
			   <div id="addsc" closed="true" class="easyui-window" title="添加收藏" icon="icon-add" style="width:450px;height:300px;">
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
				<div id="query" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;visibility:hidden;"
		    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
		        <div>
		        	<form id="cx">
			            <table>
			                <tr>
			                    <td>
			                    	题干：
			                        <%--<select name="select" id="type">
			                        	<option value="tg">题干</option>
			                            <option value="zsdmc">知识点名称</option>
			                            <option value="CName">课程名称</option>
			                            <option value="zmc">章名称</option>
			                       </select> --%>
			                    </td>
			                    <td><input type="text" name="id" id="name"  required="true"></td>
			                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
			                </tr>
			            </table>
		            </form>
		        </div>
		    </div>	
		    
		    <div id="scan" closed="true" class="easyui-window" title="查看详细信息" style="width:450px;height:400px;padding:5px;background: #fafafa;visibility:hidden;"  maximizable="false" minimizable="false" collapsible="false">
					    	<table align="center">						    				
								<tr>
									<td> 
									知识点名:
									</td>
									<td>										
										<input id="zsdmc1" type="text" style="width:300px;" disabled="true"/>
									</td>
								</tr>				
								<tr>
									<td> 
									节名: 
									</td>
									<td>
										<input id="zmc1" type="text" style="width:300px;"  disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									章名: 
									</td>
									<td>
										<input id="kcmc1" type="text" style="width:300px;" disabled="true"/>
									</td>
								</tr>
								<tr
>
									<td> 
									课程名: 
									</td>
									<td>
										<input id="tcname1" type="text" style="width:300px;" disabled="true"/>
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
										<textarea id="tg1" name="tg" style="width:300px;height:130px;visibility:hidden;" disabled="true"></textarea> 					 
									</td>
								</tr>
								<tr>
									<td> 
									选项一: 
									</td>
									<td>
										<input id="xx11" type="text"  style="width:300px;" disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									选项二: 
									</td>
									<td>
										<input id="xx21" type="text"  style="width:300px;" disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									选项三: 
									</td>
									<td>
										<input id="xx31" type="text" style="width:300px;"  disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									选项四: 
									</td>
									<td>
										<input id="xx41" type="text" style="width:300px;"  disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									答案: 
									</td>
									<td>
										<input id="da1" type="text"  style="width:300px;"  disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									测试次数: 
									</td>
									<td>
										<input id="csrcs1" type="text" style="width:300px;"  disabled="true"/>
									</td>
								</tr>
								<tr>
									<td> 
									正确次数: 
									</td>
									<td>
										<input id="zqrcs1" type="text" style="width:300px;"  disabled="true"/>
									</td>
								</tr>
							</table>
			 </div>		
	</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
   