<%@ page language="java" import="javax.servlet.http.HttpSession" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>消息管理</title>
    
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css" charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css" charset="UTF-8">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js" charset="UTF-8"></script>
	<link type="text/css" rel="stylesheet" href="js/rte/jquery.rte.css" />
	<script type="text/javascript" src="js/rte/jquery.rte.js"></script>
	<script type="text/javascript" src="js/rte/jquery.rte.tb.js"></script>
	<script type="text/javascript" src="js/rte/jquery.ocupload-1.1.4.js"></script>
   <style type="text/css">
	body, textarea {
	    font-family:sans-serif;
	    font-size:12px;
	}
	</style>
	<script type="text/javascript">
	$.messager.defaults={ok:"确认",cancel:"取消"};
	function getWidth(percent){ 
	    return (document.body.clientWidth-25-11)*percent; 
	}
	
	 var abc;
		//编辑器
		$(document).ready(function() {
			
			var array =$('.rte-zone').rte({
				css: ['default.css'],
				controls_rte: rte_toolbar,
				controls_html: html_toolbar
			});
			abc=array;
		});
		
		
$(function(){
		$('#test').datagrid({
			title:'消息管理',
			iconCls:'icon-edit',
			width:getWidth(1),
			striped: true,
			fitColumns:true,
			url:'listmsg.action',
			loadMsg:'装载数据......',
			idField:'msgid',
			singleSelect:true,
			columns:[[
					{field:'msgid',width:0},
					{title:'发送者',field:'senderid',width:getWidth(0.1),align:'left'},
			        {title:'主题',field:'msgTitle',width:getWidth(0.1),align:'left'},
		            {title:'消息内容',field:'msgcomments',width:getWidth(0.2),align:'left'},
		            {title:'发送时间',field:'sendtime',width:getWidth(0.2),align:'left',formatter:function(val){
		               return val;
		            }},
		            {title:'接收时间',field:'receivedtime',width:getWidth(0.2),align:'left',formatter:function(val){
		               if(val==null){return 'xxxx-xx-xx xx:xx:xx';}else{return val;}
		            }},
		            {title:'是否已接收',field:'msgstate',width:getWidth(0.1),align:'left',formatter:function(val){
		               if(val==null){return '未接收';}else{return '已接收';}
		            }}
					]],
			pagination:true,
			rownumbers:true,
			toolbar:[{
				text:'显示详细信息',
				iconCls:'icon-search',
				handler:function(){
				    var selected = $('#test').datagrid('getSelections');
				    if(selected.length!=1){
				    $.messager.alert('提示','请选择一行数据','提示');
				    }else{
				    var msg = selected[0];
				    $('#detail').window('open');
				    showdetail(msg);
					}
				}
			},'-',{
				text:'删除',
				iconCls:'icon-cut',
				handler:function(){
				var selected = $('#test').datagrid('getSelections');
				    if(selected.length!=1){
				    $.messager.alert('提示','请选择一行数据','提示');
				    }else{
				    var msg = selected[0];
				    delmsg(msg);
				    }
				}
			},'-',{
				text:'快速回复',
				iconCls:'icon-edit',
				handler:function(){
				    var selected = $('#test').datagrid('getSelections');
				    if(selected.length!=1){
				    $.messager.alert('提示','请选择一行数据','提示');
				    }else{
				    $('#newuserid').html(selected[0].senderid);
					$('#revert').window('open');
					}
				}
			}]
		});
		 $('#test').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
	});
	
	function delmsg(msg){
	    var flag='';
	    if(msg.msgstate==null){
	      flag+='本消息还未接收，';
	    }
		$.messager.confirm('确认',flag+'确认要删除本消息吗？',function(r){
		if(r){
		$.ajax({
           type:"POST",
          url:"delmsg.action",
           data:{"msgid":msg.msgid},
          success:function(){
            var index = $('#test').datagrid('getRowIndex', msg); 
		    $('#test').datagrid('deleteRow', index);
		    $.messager.alert('确认','删除消息成功','确认');
          }
          });
		}
		});	    
	}
	
	function showdetail(msg){
	$('#senderid').html(msg.senderid);
	$('#msgTitle').html(msg.msgTitle);
	$('#msgcomments').html(msg.msgcomments);
	$('#sendtime').html(msg.sendtime);
	if(msg.msgstate==null){
	$.ajax({
           type:"POST",
          url:"toread.action",
           data:{"msgid":msg.msgid},
          success:function(data){
	           	var index = $('#test').datagrid('getRowIndex', msg); 
				$('#test').datagrid('deleteRow', index);
	           	$('#test').datagrid('appendRow',{
	           		senderid:msg.senderid,
	           		msgTitle:msg.msgTitle,
	           		msgcomments:msg.msgcomments,
	           		sendtime:msg.sendtime,
	           		receivedtime:data.tip,
	           		msgstate:'已接收'
	            });
	            $('#receivedtime').html(data.tip);
		   }
 		});
 		}else{
	$('#receivedtime').html(msg.receivedtime);
	}
	}
	
	function detailrevert(){
	   $('#newuserid').html($('#senderid').html());
	   $('#newmsgTitle').val('');
	   $('#revert').window('open');
	}
	
	function addmsg(){
	         var userid = $('#newuserid').html();
	         var msgtitle = $("#newmsgTitle").val();
	         var msgcomments = abc['newmsgcomments'].get_content();
	         if($("#newmsgTitle").val()==""){
	        	 $.messager.alert('提示',"请为你的留言加一个主题!",'提示');
  			}else if(abc['newmsgcomments'].get_content()==""){
  				$.messager.alert('提示',"留言不能为空!",'提示');
  			}else{
  			$.messager.confirm('确认消息','确认回复  '+userid+'</br>主题：'+msgtitle,function(r){
	             if(r){
	        	  $.ajax({
	    		  type:"POST",
		           url:"revertmsg.action",
		            data:{"userid":userid,"msgTitle":msgtitle,"msgcomments":msgcomments},
		            success:function(){
		            	$.messager.confirm('确认','信息发送成功！！','确认');
	    	        }
	    	     });
		    		            }
	            	  });
	            }
	}
	function closedetail(){
	$('#detail').window('close');
	}
	function closerevert(){
	$('#revert').window('close');
	}
	</script>
	 </head>
  
  <body class="easyui-layout" style="margin-left: 0px;margin-top: 0px;margin-bottom: 0px;">
    	
		<div region="center"><table id="test" fit="true">
    	</table></div>
   <div id="detail" class="easyui-window" title="信息详情" style="width: 760;height: 430;background-color:#bbbbbb;" 
    closed="true" maximizable="false" minimizable="false" collapsible="false">
      <form><table style="width:700;">
      <tr>
         <td> 发件人：<span id="senderid" ></span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
         <td> 发送时间：<span id="sendtime"></span></br></td>
          </tr>
          <tr>
         <td> 主&nbsp;&nbsp;&nbsp;&nbsp;题：<span id="msgTitle" ></span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>接收时间：<span id="receivedtime" ></span></br></td>
          </tr>
          </table>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span id="msgcomments" style="width:700;height:300;padding:40px;background-color:RGB(204,232,207);" ></span>
        <a style="padding-left:200px" class="easyui-linkbutton" iconCls="icon-edit" href="javascript:void(0)" onclick="detailrevert()">快速回复</a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a  class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closedetail()">关闭继续查看</a>
      </form>
    </div>
   <div id="revert" class="easyui-window" title="回复消息" style="width: 825;height: 430;background-color:#bbbbbb;" 
    closed="true" maximizable="false" minimizable="false" collapsible="false">
      <form><table style="width:750;"><tr>&nbsp;&nbsp;
         <td> 收件人：<span id="newuserid" ></span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>  主&nbsp;&nbsp;&nbsp;&nbsp;题：<input type="text" id="newmsgTitle" /></br></td>
         </tr> </table>
       <p style="padding-left:10px;">
        <textarea id="newmsgcomments" class="rte-zone" style="width:790;height:250;" ></textarea>
        </p>
        <a style="padding-left:200px" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="addmsg()">提交回复</a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a  class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closerevert()">关闭继续查看</a>
      </form>
    </div>
  </body>
</html>
    	