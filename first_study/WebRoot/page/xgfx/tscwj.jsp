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
	
	<script type="text/javascript" src="uploadify/swfobject.js"></script>
	<script type="text/javascript" src="uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<link href="uploadify/uploadify.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
	//此jsp页面好像没有被使用

		$(function(){
			$('#test7').datagrid({
				title:'参考素材',
				iconCls:'icon-save',
				width:975,
				height:550,
				singleSelect:true,
				pageSize:20,
				pageList:[20,30,40,50],
				nowrap: false,
				striped: true,
				
				url:'listZl',
				loadMsg:'处理中，请等待...',
				fit:true,
				fitColumns:true,
				frozenColumns:[[
		            {field:'ck',checkbox:true}
		            
				]],
				columns:[[
		           // {title:'资料编号',field:'int1',width:100},
				    //{title:'素材编号',field:'int2',width:100},
					{field:'str1',title:'素材名称',width:300},
					
					{field:'opt',title:'查看资料详细内容',width:300,
						formatter:function(value,rec){
						return '<span style="color:red">选择</span>';
					          }
				     }
				]],
				pagination:true,                             //显示分页
				rownumbers:true,                             //显示行号
				toolbar:[{
					
					text:'补充资料',
					iconCls:'icon-ok',
					handler:function(){
							$('#btnadd').linkbutton('enable');
							$('#insert').form('clear');
							$.ajax({
			                      type:"POST",
			                      url:"tt_uupload_file.action",
			                      success:function(json){
                                      $("#zsdbh").val(json.zsdbh);
			                      }
			                	});
						 $('#add').window('open');
					}
				
				},'-',{
					text:'删除',
					iconCls:'icon-cancel',
					handler:deleteRow
  			   },'-',{
					text:'查看',
					iconCls:'icon-search',
					handler:function(){
			  				var selected = $('#test7').datagrid('getSelected');
			  	        	if(selected){
			  	        		
			  			             	$.ajax({
			  			                      type:"POST",
			  			                      url:"zxck.action",
			  			                      data:{"zlbh":selected.int1},
			  			                      success:function(json){
				  			                      if(json.tip=="视频"){
					  			                         //调用小猪
				  			                    	     location.href ='zxsp.action?filename='+selected.str1+'&zlbh=' + selected.int1;    
					  			                      }else{
					  			                    	 //location.href ='zxsp.action?no='+selected.int2;
					  			                    	 //调用田路强
					  			                    	  $.ajax({
					  			          	           	      type:"POST",
							  			          	           url:"OpenZl.action",
							  			          	            data:"zlmc="+selected.str1,
							  			          	           success:function(){
							  			          		           window.location.href="Tz.action";
							  			          		          }
							  			          	      	});
						  			                      }
			  			                    	  
			  			                      }
			  			                	});
			  					  			        		       			
			  			  }
			  	        	else{
			  	    			$.messager.alert('warning','请选择一行数据','warning');
			  	        	}
					}
  			   }]                            
				
			});
			$('#test7').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to} 共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
			$('#add').dialog({
				buttons:[{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
					var lxm=$("#addlxm").val();
					var zlly = $("#addzlly").val();
					var zlscm = $("#addzlscm").val();
					var zlms = $("#addzlms").val();
							var zsdbh = $("#zsdbh").val();
							var userId=$("#userid").val();
							var changdu=$("#changdu").val();
							var scrq=$("#scrq").val();
							var filePath=$("#filePath").val();
							var fileName=$("#fileName").val();
							var zlmc=$("#zlmc").val();
							
						 	$.ajax({
						            type:"POST",
						            url:"t_updatescwj.action",
						            data:{"zsdbh":zsdbh,"zlly":zlly,"zlscm":zlscm,"zlms":zlms,"userId":userId,"lxm":lxm,"changdu":changdu,"scrq":scrq,"filepath":filePath,"fileName":fileName,"zlmc1":zlmc},
						            success:function(){
						            	$('#add').dialog('close');					            	
						            	$.messager.alert('add','添加信息成功!!!','info',function(){
						            		$('#test7').datagrid('reload');
						            	});
		
						            	
								   }
					      	});
						
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
		

		function deleteRow(index){	        	
    		var selected = $('#test7').datagrid('getSelected');
        	if(selected){
        		$.messager.confirm('删除','确认删除吗?',function(d){					        	
	            	if(d){
		             	$.ajax({
		                      type:"POST",
		                      url:"t_deletescwj.action",
		                      data:{"no":selected.int2,"fileName":selected.str1},
		                      success:function(){
		                    	  $('#test7').datagrid('reload');
		                      }
		                	});
				         }
        		 });			        		       			
		  }
        	else{
    			$.messager.alert('warning','请选择一行数据','warning');
        	}
		 }
		
		
	</script>
    <script type="text/javascript">
    	$(document).ready(function() {
    		$('#fileupload').uploadify({
			    'uploader'  : 'uploadify/uploadify.swf',
			    'script'    : 't_upload_file.action?userId=<%=session.getAttribute("uid")%>',
			    'cancelImg' : 'uploadify/cancel.png',
			    'auto'      : true,
			    'multi'          : false, 
			   // 'buttonText'     : 'brower', 
			    //'simUploadLimit' : 3, 
			    'sizeLimit'      : 102400000, 
			    //'queueSizeLimit' : 2, 
			    'queueID'        : 'fileQueue',
			    'fileDesc'       : '*.flv; *.swf; *.pdf',
                'fileExt'        : '*.flv; *.swf; *.pdf',
                'fileDataName'    : 'fileupload',
                'removeCompleted'	:	false,
                'wmode'	:	'transparent',
                'buttonImg' :'uploadify/upload.jpg',
                onComplete: function (event, queueID, fileObj, response, data) {   
					//$('<li></li>').appendTo('.files').text(response); 
                	var r = jQuery.parseJSON(response);
    				//$('#zlmc').val(r.zlmc1);
    				$('#changdu').val(r.changdu);
    				$('#scrq').val(r.scrq);
    				$('#fileName').val(r.fileName);
    				$('#filePath').val(r.filepath);
    				$('#userid').val(<%=session.getAttribute("uid")%>);
    				
                	jQuery("#" + jQuery(this).attr('id') + ID).find('.percentage').text(' - 完成');            
				},
                onError: function(event, queueID, fileObj) {   
					//alert("文件:" + fileObj.name + "上传失败");  
					$('#fileupload').uploadifyClearQueue();   
					$.messager.alert('提示信息','您上传的文件太大','info'); 
				},   
				onCancel: function(event, queueID, fileObj){   
					$.messager.alert('提示信息','您取消' +fileObj.name + '的上传!','info');  
				}
 			});
    	});
    </script>
	</head>
	
	<body style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;" class="easyui-layout">
	<div  region="center" title="" style="overflow:hidden;">
	   
	
				<table id="test7" >
				
			   </table>	
			
			    <div id="add" icon="icon-add" title="添加" closed="true" style="padding:5px;width:500px;height:400px;align:center;">
			    		最大只能上传100M<br/><br/>
					   <div id="fileQueue"></div>
						<input type="file" name="fileupload" id="fileupload" />
					    <s:form id="insert">
					    	<table align="left">
					    	    <tr style="display:none;">
					    			<td>
					    				zsdbh编号：
					    			</td>
					    			<td>
					    				<input type="text" id="zsdbh" />
					    			</td>
					    		</tr>
					    		<tr style="display:none;">
						 			<td> 
										userId:
									</td>
									<td>
										<input id="userid" type="text"/>
									</td>
								</tr> 
						<tr style="display:none;">
							<td> 
								长度:
							</td>
							<td>
								<input type="text" id="changdu"  style="width:250px;" maxlength="30"/>
							</td>
						</tr>
						<tr style="display:none;">
							<td> 
								上传日期:
							</td>
							<td>
								<input type="text" id="scrq"  style="width:250px;" maxlength="30"/>
							</td>
						</tr>
						<tr style="display:none;">
							<td> 
								文件路径:
							</td>
							<td>
								<input type="text" id="filePath"  style="width:250px;" maxlength="30"/>
							</td>
						</tr>
						<tr style="display:none;">
							<td> 
								文件名:
							</td>
							<td>
								<input type="text" id="fileName"  style="width:250px;" maxlength="30"/>
							</td>
						</tr>
						<tr >
							<td> 
								资料名称:
							</td>
							<td>
								<input type="text" id="zlmc"  style="width:250px;" maxlength="30"/>
							</td>
						</tr>
								<tr>
						 			<td> 
										类型名:
									</td>
									<td>
										<s:select id="addlxm" list="wjlxlist" listKey="lxm" listValue="lxm" ></s:select>
									</td>
								</tr>
								<tr>
									<td> 
										资料来源:
									</td>
									<td>
										<input type="text" id="addzlly" />
									</td>
								</tr>
								<tr>
									<td> 
										资料素材名:
									</td>
									<td>
										<input type="text" id="addzlscm" />
									</td>
								</tr>
					    		<tr>
									<td> 
										资料描述: 
									</td>
									<td>
										<input type="text" id="addzlms" style="padding:0;width:300px;height:100px;"/>
									</td>
								</tr>
							</table>
					    </s:form>
			   </div>	
		</div>
		</body>
</html>
