<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String fileName = (String)request.getSession().getAttribute("fileName");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>在线视频</title>
    <link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$.extend($.messager.defaults,{  
        	    ok:"确定",  
        	    cancel:"取消"  
        	});
        	
			$('#test_tables').datagrid({
				title:'在线视频列表',
				iconCls:'icon-save',
				width:getWidth(1),
				height:550,
				nowrap: true,
				striped: true,
				collapsible:true,
				singleSelect:true,
				fit:true,
				fitColumns:true,
				loadMsg:'加载数据,请等待...',
				url:'zxsplblist.action',
				columns:[[
					{field:'str3',title:'资料名称',width:getWidth(0.2),align:'left'},
					{field:'str1',title:'文件名',width:getWidth(0.4),align:'left'},
					{field:'str2',title:'描述',width:getWidth(0.4),align:'left'}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
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
      				id:'btnzxsp',
      				text:'在线播放',
      				iconCls:'icon-play',
					handler:zxbf
      			},'-',{
      				text:'查看详细信息',
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

		function getWidth(percent){ 
		    return (document.body.clientWidth-25-13)*percent; 
		}

		function zxbf(){
			//parent.window.www("s");
    		var selected = $('#test_tables').datagrid('getSelected');
        	if(selected){
        		//var filename=selected.str1;
        		//var zlbh = selected.int2;
        		//$.ajax({
        			//type:"POST",
     	            //url:"zxsp.action",
     	            //data:{"zlbh":zlbh,"filename":filename},
            		//});
        		//$('#zxsp').window('open');
            	window.location.href='zxsp.action?filename=' + selected.str1 +
            		'&zlmc=' + selected.str3 + '&zlbh=' + selected.int2;
		  	}
        	else{
    			$.messager.alert('警告','请选择你要播放的视频','warning');
        	}
		}

		//文件名查询
		//素材文件查询
		function query(){
	        var queryParams = $('#test_tables').datagrid('options').queryParams;
	        var scwjtype = queryParams.scwjtype = "filename";
	        var scwjword = queryParams.scwjword = $('#name').val();
	        var aa=$('#name').val();
	        if(aa!="") {
	        	$.ajax({
					type:"POST",
					url:"searchscwj2.action",
					data:{"scwjtype":scwjtype,"scwjword":scwjword},
					cache: false,
					success:function(json){
								if(json.a == "1"){
									$('#test_tables').datagrid({
					    	            url:'searchscwj3.action'
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

		//查看视频列表详细信息
		function querydetail(){	        	
    		var selected = $('#test_tables').datagrid('getSelected');
        	if(selected){
        		$("#lxm1").val(selected.str6);
        		$("#zlmc1").val(selected.str3);
        		$("#userId1").val(selected.str7);
        		$("#filename1").val(selected.str1);
        		$("#zlly1").val(selected.str4);
        		$("#zlscm1").val(selected.str5);
        		$("#zlms1").val(selected.str2);
        		$("#scrq1").val(selected.dt);
        		$("#llcs1").val(selected.int3);
        		$("#cssl1").val(selected.int4);
        		$("#changdu1").val(selected.int5);
				$("#querydetail").window('open');
				document.getElementById("querydetail").style.visibility="visible";
            }else{
    			$.messager.alert('提示信息','请选择一行数据','info');
        	}
		 }

	</script>
  </head>
	<body style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px;">
	
		<table id="test_tables"></table>
		
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
		
		<div id="querydetail" class="easyui-window" title="查看详细信息" style="padding: 10px;width: 550px;height:400px;visibility:hidden;"
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
		
		<div id="zxsp" closed="true" class="easyui-window" style="padding:5px;width:440px;height:390px;" title="视频播放">
			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
						id="video" width="100%" height="100%"
						codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
					<param name="src" value="/spdhswf/FBVideo.swf" />
					<param name="quality" value="high" />
					<param name="bgcolor" value="#869ca7" />
					<param name="FlashVars" value="fileName=<%=fileName %>"/>
					<param name="allowScriptAccess" value="sameDomain" />
					<embed src="/spdhswf/FBVideo.swf" quality="high" bgcolor="#869ca7"
							flashVars="fileName=<%=fileName %>"
							width="100%" height="100%" name="FBVideo" align="middle"
							play="true"
							loop="false"
							quality="high"
							allowScriptAccess="sameDomain"
							allowFullScreen="true"
							type="application/x-shockwave-flash"
							pluginspage="http://www.adobe.com/go/getflashplayer">
					</embed>
			</object>
		</div>
	</body>
</html>
