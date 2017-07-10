<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userid = (String)request.getSession().getAttribute("uid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jQuery EasyUI</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<link href="uploadify/uploadify.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="uploadify/swfobject.js"></script>
	<script type="text/javascript" src="uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script type="text/javascript" src="js/sjld.js"></script>
	<script type="text/javascript">
		$(function(){
			PdType();
			$.extend($.messager.defaults,{  
        	    ok:"确定",  
        	    cancel:"取消"
        	});
        	
			$('#test_tables').datagrid({
				title:'素材文件列表',
				iconCls:'icon-save',
				nowrap: true,
				striped: true,
				width:getWidth(1),
				singleSelect:false,
				fit:true,
				fitColumns:true,
				loadMsg:'加载数据,请等待...',
				collapsible:true,
				url:'scwjlist.action',
				columns:[[
					{field:'str3',title:'资料名称',width:getWidth(0.2),align:'left'},
					{field:'str1',title:'文件名',width:getWidth(0.2),align:'left'},
					{field:'str2',title:'描述',width:getWidth(0.6),align:'left'}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'_btnadd',
					text:'添加素材文件',
					iconCls:'icon-add',
					handler:_scwj
				},'-',{
					id:'delete',
					text:'删除',
					iconCls:'icon-cancel',
					handler:deleteRows
  				},'-',{
      				id:'btnquery',
    				text:'查询素材文件',
    				iconCls:'icon-search',
    				handler:function(){
    					$('#btnquery').linkbutton('enable');
    					$('#query').window('open');
    					document.getElementById("query").style.visibility="visible";
    					$('#name').val('');
      		        }
      			},'-',{
      				id:'btnquery',
    				text:'查看文件详细信息',
    				iconCls:'icon-search',
    				handler:querydetail
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
		    return (document.body.clientWidth-25-7)*percent; 
		}

		function _scwj() {
			//$('#fileupload').uploadifyClearQueue();
			$('#_btnadd').linkbutton('enable');
			$('#insert').form('clear');
            $('#add').window('open');
            document.getElementById("add").style.visibility="visible";
		}

		function addscwj(){
			//var zlbh = $("#addzlbh").val();
			//$("#sftj").val("a");
			var zlmc = $("#addzlmc").val();
			var zlly = $("#addzlly").val();
			var zlscm = $("#addzlscm").val();
			var zlms = $("#addzlms").val();
			var lxm = $("#lxm").val();
			var changdu = $("#changdu").val();
			var scrq = $("#scrq").val();
			var filePath = $("#filePath").val();
			var fileName = $("#fileName").val();
			var no = $("#no").val();
			//var userId=$("#adduserId").val();
			var tip="a";
			if(lxm.length!=0) {
				if(zlmc.length!=0) {
					$.ajax({
						type:"POST",
			            url:"updatescwj.action",
			            data:{"zlmc":zlmc, "zlly":zlly, "zlscm":zlscm, "zlms":zlms, "tip":tip,"lxm":lxm,"changdu":changdu,"scrq":scrq,"filePath":filePath,"fileName":fileName
								,"no":no},
			            success:function(json) {
									$('#fileupload').uploadifyClearQueue();
			            	$('#add').dialog('close');					            	
			            	$.messager.alert('提示信息','请添加对应知识点!','info',function(){
			            		$('#test_tables').datagrid('reload');
			            		$('#addzlzsddy').window('open');
			            		document.getElementById("addzlzsddy").style.visibility="visible";
			            		$('#addzlmc1').val(json.zlmc);
			            	});
				        }
					});
				} else {
					$.messager.alert('提示信息','资料名称不能为空!','info');
				}
			} else {
				$.messager.alert('提示信息','您还没有上传任何文件!','info');
			}
			
		}

		function add() {
			$('#btnadd').linkbutton('enable');
			//$('#insert1').form('clear');
			$('#bindTCName').val('');
			$('#bindZh').val('');
			$('#bindK').val('');
			$('#bindZsd').val('');
			$('#addweizhi').val('');
            $('#addzlzsddy').window('open');
            document.getElementById("addzlzsddy").style.visibility="visible";
            //$('#addweizhi').val('请填写对应页或时间(s)');
		}

		//添加对应知识点
		function addzlzsddy() {
			var zlmc = $("#addzlmc1").val();
			var TCName = $("#bindTCName").find("option:selected").text();
			var CName = $("#bindZh").find("option:selected").text();
			var zmc = $("#bindK").find("option:selected").text();
			var zsdmc = $("#bindZsd").find("option:selected").text();
			var weizhi = $("#addweizhi").val();

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
					            	$('#addzlzsddy').dialog('close');						            	
					            	$.messager.alert('提示信息','添加信息成功!!!','info');
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
	
		}

		//继续添加对应知识点
		function continueadd() {

			var zlmc = $("#addzlmc1").val();
			var TCName = $("#bindTCName").find("option:selected").text();
			var CName = $("#bindZh").find("option:selected").text();
			var zmc = $("#bindK").find("option:selected").text();
			var zsdmc = $("#bindZsd").find("option:selected").text();
			var weizhi = $("#addweizhi").val();

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
					            	$('#addzlzsddy').dialog('close');						            	
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
		}

		function close1() {
			$('#addzlzsddy').window('close');
		}

		//删除多个文件
		function deleteRows(){   
			var selected = $('#test_tables').datagrid('getSelections');
			var arr = new Array();
			if(selected.length>0){
				$.each(selected,function(n,value) {
					arr.push(value.str1);
				})
			    	$.messager.confirm('提示','确认删除文件名为"' + arr + '"的数据吗?' ,function(id){
			    		if(id){
				    		for(var i=0;i<selected.length;i++){
						    	var single = selected[i];
						    	$.ajax({
				                      type:"POST",
				                      url:"deletescwj.action",
				                      data:{"no":single.no,"filename":single.str1,"zlbh":single.int1},
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

		//删除一个文件
		function deleteRow(index){	        	
    		var selected = $('#test_tables').datagrid('getSelected');
        	if(selected){
        		$.messager.confirm('删除记录','确认删除文件名为'+selected.str1+'的数据吗?',function(d){					        	
	            	if(d){
		             	$.ajax({
		                      type:"POST",
		                      url:"deletescwj.action",
		                      data:{"str2":selected.str2,"filename":selected.str1},
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

		//素材文件查询
		function query(){
	        var queryParams = $('#test_tables').datagrid('options').queryParams;
	        var scwjtype = queryParams.scwjtype = "filename";
	        var scwjword = queryParams.scwjword = $('#name').val();
	        var aa=$('#name').val();
	        if(aa!="") {
	        	$.ajax({
					type:"POST",
					url:"searchscwj1.action",
					data:{"scwjtype":scwjtype,"scwjword":scwjword},
					cache: false,
					success:function(json){
								if(json.a == "1"){
									$('#test_tables').datagrid({
					    	            url:'searchscwj.action'
					    	        });
					    	        $('#query').window('close');
					    	        $('#test_tables').datagrid('getPager').pagination({
					   				 displayMsg:'当前显示从{from}到{to}共{total}记录',
					   				 beforePageText:'第',
					   				 afterPageText:'页'
					   				 });
								}else if(json.a == "0"){
									$.messager.alert('提示信息','查询结果为空!!!','info',function(){
					            });
							}
						}
					});
		    } else {
		    	$.messager.alert('提示','查询条件不能为空!','info');
			}
	        
	    }

		//查看文件详细信息
		function querydetail(){	        	
    		var selected = $('#test_tables').datagrid('getSelected');
        	if(selected){
        		$("#lxm1").val(selected.str6);
        		$("#filename1").val(selected.str1);
        		$("#zlmc1").val(selected.str3);
        		$("#userId1").val(selected.str7);
        		$("#zlly1").val(selected.str5);
        		$("#zlscm1").val(selected.str4);
        		$("#zlms1").val(selected.str2);
        		$("#scrq1").val(selected.dt);
        		$("#llcs1").val(selected.int1);
        		$("#cssl1").val(selected.int2);
        		$("#changdu1").val(selected.int3);
				$("#querydetail").window('open');
				document.getElementById("querydetail").style.visibility="visible";
            }else{
    			$.messager.alert('提示信息','请选择一行数据','info');
        	}
		 }

		//限制文本输入框字数
		var TextUtil = new Object();
		TextUtil.NotMax = function(oTextArea){
			var maxText = oTextArea.getAttribute("maxlength"); 
			if(oTextArea.value.length > maxText){
					oTextArea.value = oTextArea.value.substring(0,maxText);
					$.messager.alert('提示信息','您只能输入200个字','info');  
			}
		}

		//判断角色的类型，如果角色是学生就会失去一些权限
		function PdType(){
			$.ajax({
	            type:"GET",
	           url:"GetType.action",
	           success:function(json){
		           if(json.tip=="S"){
		        	   $('#_btnadd').linkbutton('disable');
			     	   $('#delete').linkbutton('disable');
			          }
		           }
	      	});
		}

		
		//关闭window时执行的方法
		function close2 () {
			var lxm = $("#lxm").val();
			var no = $("#no").val();
			var filePath = $("#filePath").val();
			if(lxm!=0) {
				$.ajax({
	                type:"POST",
	                url:"check.action",
	                data:{"no":no,"filePath":filePath},
	                success:function(json){
						$('#add').window('close');
	                }
				});
			} else {
				$('#add').window('close');
			}
		}
	</script>
    <script type="text/javascript">
    	$(document).ready(function() {
    		$('#fileupload').uploadify({
			    'uploader'  : 'uploadify/uploadify.swf',
			    'script'    : 'upload_file.action?jsessionid=<%=userid%>',
			    'cancelImg' : 'uploadify/cancel.png',
			    'auto'      : true,
			    'multi'          : false, 
			   	// 'buttonText'     : 'brower', 
			    //'simUploadLimit' : 3,
			    'sizeLimit'      : 102400000, 
			    //'queueSizeLimit' : 2,
			    'queueID'        : 'fileQueue',
			    'fileDesc'       : '*.flv; *.swf',
                'fileExt'        : '*.flv; *.swf',
                'fileDataName'    : 'fileupload',
                'removeCompleted'	:	false,
                'wmode'	:	'transparent',
                'width' : 90,
                'buttonImg' :'uploadify/_upload.png',
                onComplete: function (event, queueID, fileObj, response, data) {
					//$('<li></li>').appendTo('.files').text(response);  
					var r = jQuery.parseJSON(response);
					$('#lxm').val(r.lxm);
					$('#changdu').val(r.changdu);
					$('#scrq').val(r.scrq);
					$('#filePath').val(r.filePath);
					$('#fileName').val(r.fileName);
					$('#no').val(r.no);
					//alert(response);
                	jQuery("#" + jQuery(this).attr('id') + ID).find('.percentage').text(' - 完成');         
				},
				onProgress : function(event, queueId, fileObj, response, data) {

				},
                onError: function(event, queueID, fileObj) {  
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
	
	<body class="easyui-layout" style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
					
		<div region="center">
			<table id="test_tables"></table>
		</div>
			
	    <div id="add" icon="icon-add" class="easyui-window" title="添加" closed="true" 
	    		maximizable="false" minimizable="false" collapsible="false"
	    		style="padding:5px;width:528px;height:395px;visibility:hidden;">
		
				<table align="center"  style="height:65px;width:400px;">
					<tr align="center">
				    <td colspan="4">1.文本，您只能上传swf格式(可在本系统中下载swf转换器);2.视频，您只能上传flv格式;3.最大只能上传100M</td>
				  </tr>
					<tr>
						<td align="left" colspan="1">
							<input type="file" name="fileupload" id="fileupload"/>
						</td>
						<td>
							<div id="fileQueue"></div>
						</td>
					</tr>
				</table>
			    <s:form id="insert">
			    	<table align="center">
			    		<tr>
							<td> 
								资料名称:
							</td>
							<td>
								<input type="text" id="addzlmc"  style="width:250px;" maxlength="30"/>
							</td>
						</tr>
						<tr style="display:none;">
							<td> 
								判断是否点击提交:
							</td>
							<td>
								<input type="text" id="sftj"  style="width:250px;" maxlength="30"/>
							</td>
						</tr>
						<tr style="display:none;">
							<td> 
								顺序号:
							</td>
							<td>
								<input type="text" id="no"  style="width:250px;" maxlength="30"/>
							</td>
						</tr>
						<tr style="display:none;">
							<td> 
								类型名:
							</td>
							<td>
								<input type="text" id="lxm"  style="width:250px;" maxlength="30"/>
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
						<tr>
							<td> 
								资料来源:
							</td>
							<td>
								<input type="text" id="addzlly"  style="width:250px;" maxlength="80"/>
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
								资料素材名:
							</td>
							<td>
								<input type="text" id="addzlscm"  style="width:250px;" maxlength="100"/>
							</td>
						</tr>
			    		<tr>
							<td> 
								资料描述:
							</td>
							<td>
								<textarea rows="5" cols="19" id="addzlms"  style="width:250px;"
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
						<tr></tr><tr></tr>
						<tr>
							<td colspan="2" align="right">
								<a class="easyui-linkbutton" iconCls="icon-ok"
									href="javascript:void(0)" onclick="addscwj()">提交</a>
								<a class="easyui-linkbutton" iconCls="icon-cancel"
									href="javascript:void(0)" onclick="close2()">取消</a>
							</td>
						</tr>
					</table>
			    </s:form>
		    </div>
		
		<div id="addzlzsddy" icon="icon-add" class="easyui-window" title="添加知识点" closed="true" 
	    		style="padding:5px;width:400px;height:270px;visibility:hidden;" maximizable="false" 
				minimizable="false" collapsible="false">
			<s:form id="insert1">
				<table align="right">
					<tr>
			 			<td> 
							资料名称:
						</td>
						<td>
							<input type="text" id="addzlmc1" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td> 
							课程名称:
						</td>
						<td>
							<select id="bindTCName" onChange="getZh()" style="width:166px;">
								<option  value="-1">--请选择课程名称--</option>
							</select>
						</td>
					</tr>
					<tr>
						<td> 
							章名称:
						</td>
						<td>
						   	<select id="bindZh"  onChange="getKcbh()" style="width:166px;">
						   		<option  value="-1">--请选择章名称--</option>
						   	</select>
						</td>
					</tr>	
					<tr>
						<td> 
							节名称:
						</td>
						<td>
						   	<select id="bindK"  onChange="getZsd()" style="width:166px;">
						   		<option  value="-1">--请选择节名称--</option>
						   	</select>
						</td>
					</tr>			
					<tr>
						<td> 
							知识点名称:
						</td>
						<td>										
							<select id="bindZsd" onChange="" style="width:166px;">
						     <option  value="-1">--请选择知识点名称--</option>
						    </select>
						</td>
					</tr>
											
					<tr>
						<td> 
							对应位置:
						</td>
						<td>
							<input type="text" id="addweizhi" style="padding:0;width:166px;" maxlength="20"/>
							<br/><br/>
						</td>
					</tr>
					
					<tr>
						<td colspan="3" align="center">
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
	   
	   <div id="query" class="easyui-window" title="查询" style="padding: 10px;width: 400px;height:100;visibility:hidden;"
		   	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
			<div>
				<form>
					<table>
						<tr>
		                	<td>
								文件名:
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
		
		<div id="querydetail" class="easyui-window" title="查看详细详细" style="padding: 10px;width: 550px;height:420px;visibility:hidden;"
		   	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
			<div>
				<table align="center" width="520"> 
					<tr>
						<td> 
							分类:
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
