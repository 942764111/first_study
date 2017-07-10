<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <title>msgInfo发送消息</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css" >
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css" >
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../kindeditor/kindeditor.js"></script>
     <script type="text/javascript" src="../kindeditor/kindeditor-min.js"></script>
     <link rel="stylesheet" type="text/css" href="../kindeditor/skins/default.css">
	<script type="text/javascript" src="js/rte/jquery.ocupload-1.1.4.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<style type="text/css">
	body, textarea {
	    font-family:sans-serif;
	    font-size:12px;
	}
	.icon-friend{
	background:url('img/friend.png') no-repeat;
    }
	.icon-group{
	background:url('img/group.png') no-repeat;
    }
	</style>
<head>
	<script language="javascript" type="text/javascript">
//初始化KE编辑器
$(function(){
KE.show({
	id : 'content1',
	imageUploadJson : '../../jsp/upload_json.jsp',
	fileManagerJson : '../../jsp/file_manager_json.jsp',
	resizeMode : 1,
	width:'95%',
	height:'290px',
	items: ['undo', 'redo',
		 '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 
			'subscript','superscript', '|', 'title', 'fontname', 'fontsize', '|', 
			'textcolor', 'bgcolor', 'bold','italic', 'underline', 'strikethrough', 'removeformat', '|',
			 'image', 'advtable', 'hr','|','fullscreen']
});
});
	//初始化好友树
		$(function(){
			var a = null;
			$.ajax({
				type:"post",
				url:'listallbody.action',
				dataType:'json',
				success:function(r){
				a = r.rows;
				$('#tt2').tree({
					data: r.rows,
					onClick:function(node){
					  var id=node.id;
					  var name=node.text;
					  if(id!=null){
					    mes(id,name);
					  }
				   }
				});
			}
			});			
		});
		
		//初始化群组树
		$(function(){
			var a = null;
			$.ajax({
				type:"post",
				url:'grouptree.action',
				dataType:'json',
				success:function(r){
				a = r.rows;
				$('#g').tree({
					data: r.rows,
					onClick:function(node){
					  var id=node.id;
					  var name=node.text;
					  if(id!=null&&$('#tt2').tree('getChildren', node.target).length==0){
					    mes(id,name);
					  }else if($('#tt2').tree('getChildren', node.target).length>0){
						  var children = $('#tt2').tree('getChildren', node.target);
						  mesg(children);
					  }
				   }
				});
			}
			});			
		});
			  //好友树动作
	 function mes(id,name){
		 var user = name+"<"+id+">;";
		 var username=$('#username').val();
	     if(username.match(user)){
	    	 var aa=username.split(user);
			 var username=aa.join('');
			 $('#username').val(username);
		 }else{
			 $('#username').val(username+user);
		 }
	 }
	  //群组树动作
	 function mesg(children){
		 if(children.length>0&&children[0].text!='暂无成员'){
			 for(var i=0;i<children.length;i++){
		       var user = children[i].text+"<"+children[i].id+">;";
		       var username=$('#username').val();
			   if(username.match(user)){
				   var aa=username.split(user);
				   var username=aa.join('');
				   $('#username').val(username);
			   }
				   $('#username').val(username+user);
			 }
		 }
	 }
	  function close1(){
	   $('#add').window('close');
	  }
	  function close2(){
	   $('#edit').window('close');
	  }

	  
	  function getSelect(){
		  var id;
	   var select = $('#tt').datagrid('getSelected');
	   if(select){
	    $('#edit').window('open');
	    $('#ff').show();
	    $('#ff').appendTo('#ee');
	    $('#name').val(select.name);
	    $('#code').val(select.code);
	    id = select.id;
	   }else{
	    $.messager.alert('warning','请选择一行数据','warning');
	   }
	  }
	  
	  function addmsg(){
		     var username = $('#username').val();
	         var msgtitle = $("#msgTitle").val();
	         KE.sync('content1');
	         var msgcomments = $('#content1').val();
	         if($("#msgTitle").val()==""){
	        	 $.messager.alert('提示',"请为你的留言加一个主题!",'提示');
  				return false;
  			}else if($("#username").val()==""){
  				$.messager.alert('提示',"请你填写收件人!",'提示');
  				return false;
  			}else if(msgcomments==""){
  				$.messager.alert('提示',"留言不能为空!",'提示');
  				return false;
  			}else{
  				
  			
	    	  $.messager.confirm('确认消息','向 '+username+' 发送信息</br>主题：'+msgtitle,function(r){
	             if(r){
	            	 
		    		  
	        	  $.ajax({
	    		  type:"POST",
		           url:"savemsg.action",
		            data:{"userid":username,"msgTitle":msgtitle,"msgcomments":msgcomments},
		            success:function(){
		            	$.messager.confirm('确认','信息发送成功！！',function(r){
  			    		  if(r){
  		    		  location.href="msg.action";
  			    		  }
  		    		  });
	    	        }
	    	     });
		    		            }
	            	  });
	            }
			}

		//重置收件人，主题，消息内容
	function reset(){
			$('#username').val('');
			$('#msgTitle').val('');
			KE.html('content1','');
			
			}
	function todel(){
		
			var utext=$('#username').val();
			var ustr=utext.split(';');
			D2.options.length=0;
			D1.options.length=0;
			for(var i=0;i<ustr.length-1;i++){
				var newoption=document.createElement("OPTION");
				newoption.innerText=ustr[i];
				newoption.value=ustr[i];
				var target = eval("document.all.D2");
				D1.appendChild(newoption);
			}
			$('#light').window('open');
		}
	function moveselect(obj,target,all){
		if (!all) all=0;
		if (obj!="[object]") obj=eval("document.all."+obj);
		target=eval("document.all."+target);
		if (all==0){
		while (obj.selectedIndex>-1){
		//alert(obj.selectedIndex)
		mot=obj.options[obj.selectedIndex].text;
		mov=obj.options[obj.selectedIndex].value;
		obj.remove(obj.selectedIndex);
		var newoption=document.createElement("OPTION");
		newoption.text=mot;
		newoption.value=mov;
		target.add(newoption);
		}
		}else{
		//alert(obj.options.length)
		for (i=0;i<obj.length;i++)
		{
		mot=obj.options[i].text;
		mov=obj.options[i].value;
		var newoption=document.createElement("OPTION");
		newoption.text=mot;
		newoption.value=mov;
		target.add(newoption);
		}
		obj.options.length=0;
		}
		}
	function guanbi(){
		//下面上把右边select的值传到文本框内
		var user=document.getElementById("username");
		user.value="";
		var huoQu=document.getElementById("D1");
		for(var k=0;k<huoQu.length;k++)
		user.value=user.value + huoQu.options[k].value + ";";
		$('#light').window('close');
		}
	function closelight(){
		$('#light').window('close');
	}
	//收件人格式
	function geshi(){
		$.messager.alert('收件人格式说明',"当手动输入收件人时，每个收件人的id必填，并且用‘<>’括起来，每个收件人必须以英文半角符号‘;’结束；</br>也可以通过点击右边好友或群组生成",'提示');
	}
	</script>
  </head> 
   <body  style="background-color:white" >
	 
    <table width="100%" height="100%">
	<tr>
	  <td width="8%" align="center">收件人：</td >
	<!--   <td width="72%" colspan="2"><input type="text"  id="username" style="width:75%;" onclick="todel()" /><font size=1>点击右边通讯录中的好友产生</font></td> -->
	  <td width="72%" colspan="2"><input type="text"  id="username" style="width:75%;" title="收件人：请注意收件人格式"/>
	     <a href="javascript:void(0);" onclick="geshi()" style="text-decoration:none; color: blue;"><font size=1>收件人格式说明</font></a>
	  </td>
	  <td width="20%" rowspan="4"><div class="easyui-tabs"  plain="true" style="width:160px;height:361px;" title="通讯录">
			<div title="好友" >
				<ul id="tt2" >
               </ul>
             </div>
			<div title="群组" >
				  <ul id="g" >
				</ul>
			</div>
		</div>
	  </td>
	</tr>
	<tr>
	  <td width="8%" align="center">主&nbsp;&nbsp;题：</td>
	  <td width="72%" colspan="2"><input type="text" id="msgTitle" style="width:75%;"/><font size=1>须填写</font></td>
	</tr>
   <tr>
    <td height="200" colspan="3">
      <p style="padding-left:40px;" title="留言">
        <textarea  style="width:100%;" name="neirong" id="content1" ></textarea>
      </p>
    </td>
    
   <tr>
    <td width="8%">&nbsp;</td>
    <td width="36%"><a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="addmsg()">提交留言</a></td>
    <td width="36%"><a  class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="reset()" title="重置收件人和主题">重置</a></td>

   </tr>
	 
	</table>
   
   
     <div id="query" class="easyui-window" title="查询" style="padding: 10px;width: 380;height:106;"
    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
        	<form>
	            <table>
	            <td>查询姓名：</td>
	                    <td><input type="text" id="friendname"></td>
	                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
	            </table>
            </form>
        </div>
    <div id="member" class="easyui-window" style="width:166px;height:361px;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="false">
		<table id="test3" fit="true" plain="true">
    	</table>
	</div>
	
	<div id="light" class="easyui-window" title="编辑收件人" 
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="false">
<table border="1" width="350" id="table4" bordercolor="#CCCCCC" bordercolordark="#CCCCCC" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
<tr>
<td width="150" height="200" align="center" valign="middle">
收件人
<select size="12" name="D1" id="D1" ondblclick="moveselect(this,'D2')" multiple="multiple" style="width:140px">
</select>
</td>
<td width="50" height="200" align="center" valign="middle">
<input type="button" value="<<" name="B3" onclick="moveselect('D2','D1',1)" /><br />
<input type="button" value="<" name="B5" onclick="moveselect('D2','D1')" /><br />
<input type="button" value=">" name="B6" onclick="moveselect('D1','D2')" /><br />
<input type="button" value=">>" name="B4" onclick="moveselect('D1','D2',1)" /><br />
</td>
<td width="150" height="200" align="center" valign="middle">
将要移除的收件人
<select size="12" name="D2" id="D2" ondblclick="moveselect(this,'D1')" multiple="multiple" style="width:140px">
</select>
</td>
</tr>
<tr><td>
<input type="button" name="button" onclick="guanbi()" value="确定" /></td>
<td>
<input type="button" name="button" onclick="closelight()" value="取消" /></td></tr></table>
</div>
   </body>
  </html>
 

	
