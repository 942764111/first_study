<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>模块分类</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	<style type="text/css">
	body{
    margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-size: 12px;
   }
   .table{
   	font-size: 12px;
   }
	
	</style>
	<script> 
	var flag1=false;//标示“模块分类”是否符合校验的要求，它为true时才可以提交；
	var flag2=false;var g=0;//标示“分类顺序”是否符合校验的要求，它为true时才可以提交；
	var flag3=true;
	var arrays1=new Array();  //批量修改的变量
	$(function(){ 
	$('#tt').datagrid({ 
	title:'分类管理', 
	iconCls:'icon-save', 
	width:978,
	height:550,
	width:getWidth(1),
	nowrap: false,
	striped: true,
	pageList:[15,20,30,40,50],
	url:'listModuleclass.action',
	loadMsg:'加载数据,请等待...',
	sortName:'classolder',
	sortOrder:'asc',
	remoteSort: false,
	fitColumns:true,
	//singleSelect:true,
	fit:true,
	columns:[[
	{field:'mclassname',title:'模块分类名称',align:'center',width:getWidth(0.15)}, 
	{field:'classolder',title:'分类顺序',align:'center',width:getWidth(0.15),sortable:true},
	{field:'flimage',title:'图标',width:getWidth(0.2),align:'center',
		formatter:function(value,rec){
		var temp=rec.mclassname;
		if(temp=="工作"){
	      	  return '<img src="img/index/picture/login-icon.gif"/>';
	          }else if(temp=="教育"){
	        	  return '<img src="img/index/picture/Book.gif"/>';
	          	     
	          	   }else if(temp=="笔记"){
	          		 return '<img src="img/index/picture/33.gif"/>';
	          		     
	              	   }else if(temp=="资源"){
	              		 return '<img src="img/index/picture/Download.gif"/>';
	              		   
	                  	   }else{
	                  		 return '<img src="img/index/picture/wap.gif"/>';
	                  		   
	                      	   }
		  
        }

		},
	{field:'comments',title:'备注',width:getWidth(0.5),align:'left'} 
	]],
	pagination:true,
	rownumbers:true, 
	toolbar:[{
		text:'维护',
		iconCls:'icon-redo',
		handler:function(){
		var selected = $('#tt').datagrid('getSelections');
		if(selected.length==1){
		var ver = selected[0].mclassname;
		$.ajax({
			type:"POST",
			data:"Mclassname="+ver,
			//url:"ceshi.action"
			url:"weihuchuanzhi.action"
		});
		window.location.href='weihulist_tz.action';
		window.event.returnValue=false;
		}else{$.messager.alert('提示','请选择一行数据','warning');}
		}
	},'-',{
		text:'增加',
		iconCls:'icon-add',
		handler:function(){
			$('#addform').form('clear');
			$("#Error").empty();
			$('#add').window('open');
			document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
			    }
	},{
		text:'删除',
		iconCls:'icon-cancel',
		handler:dele
	},{
		text:'修改',
		iconCls:'icon-edit',
		handler:edit
	},{
		text:'查询',
		iconCls:'icon-search',
		handler:function(){
        $('#find').window('open');
        document.getElementById("ggggg3").style.visibility="visible";//注意：用$()方式不好用
       }
	}]
   });
	 $('#tt').datagrid('getPager').pagination({
		 displayMsg:'当前显示从{from}到{to}共{total}记录',
		 beforePageText:'第',
		 afterPageText:'页'
	 });
});
	
function getWidth(percent){ 
	return (document.body.clientWidth-25-5)*percent; 
}
//增加功能
function add(){
	
	if(flag1&&flag2){
		var selected = {"moduleclass.mclassname":$('#mclassname').val(),"moduleclass.classolder":$('#classolder').val(),"moduleclass.comments":$('#comments').val()};
		$.ajax({
				type:"POST",
				url:"saveModuleclass.action",
				data:selected,
				success:function(){$('#tt').datagrid('reload');}
				});
				$('#add').window('close');
		}
	
}
//修改功能
function edit(){
var selected = $('#tt').datagrid('getSelections');
if(selected.length!=0){
	$('#update').window('open');
	document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
	$('#mclassname1').val(selected[0].mclassname);
	$('#classolder1').val(selected[0].classolder);
	$('#comments1').val(selected[0].comments);
	if(selected.length==1){
		 $("#xiayige").html('<font>完成</font>');
	 }else{
		 $("#xiayige").html('<font>下一个</font>');
	 }
	for(i=1;i<selected.length;i++){
	    arrays1.push(selected[i]);
	 }
	 //alert(arrays1.length);
	}else{
	$.messager.alert('提示','请选择将修改的数据','warning')
	}
}
function update(){
	
	if(flag3){
		  var up={"moduleclass.mclassname":$("#mclassname1").val(),"moduleclass.classolder":$("#classolder1").val(),"moduleclass.comments":$("#comments1").val()}
		  $.ajax({
				type:"POST",
				url:"updateModuleclass.action",
				data:up,
				success:function(){	
			              
						}
				});
	if(g<arrays1.length){
		 
		$('#mclassname1').val(arrays1[g].mclassname);
		$('#classolder1').val(arrays1[g].classolder);
		$('#comments1').val(arrays1[g].comments);
		if(g==arrays1.length-1){
			$("#xiayige").html('<font>完成</font>');
	    }
	}else{
	     $.messager.alert('提示','已修改完成!','info',function(){
		     g=0;
		     arrays1.length=0;
		     $("#xiayige").html('<font>下一个</font>');
	    	 $('#update').window('close');
             $('#tt').datagrid('reload');
             
	     })//
	     
	  
	}
	g=g+1;
	
	}
}
//删除功能
function dele(){
	var selected = $('#tt').datagrid('getSelections');
	if(selected.length!=0){
		var names="";
	  $.each(selected,function(m,values){
              names+=" '"+values.mclassname+"'";
		  });
	  $.messager.confirm('删除模块分类','您确定要删除： '+names+' 分类吗？',function(d){
		if(d){

			$.each(selected,function(n,value){
		    	var single = value;
		    	
		     	$.ajax({
		           type:"POST",
		           url:"removeModuleclass.action",
		           dataType:"json",
		            data:{"mclassname":single.mclassname},
		           success:function(json){
		     		  if(json.tip=="true"){
		     			$.messager.alert("提示","成功删除了：'"+single.mclassname+"' 分类",'info');
		     			var index = $('#tt').datagrid('getRowIndex', single);  
						 $('#tt').datagrid('deleteRow', index);
						 
		     		  }else{
		     			
			     		$.messager.alert('警告',"您必须先删除“模块管理”下的"+json.tip+" 模块，才可以删除：'"+single.mclassname+"'分类；或者将它们指定到其它分类中",'warning');
		     		  }
		     	    
		     	     
		     	   }
		      	});
		     	
			});
           
		};
		
	  });
	}else{
	$.messager.alert('警告','请选择将删除的数据','warning')};
}
//查询功能
function find(){
	var mc = $('#mclassname2').val();
	var selected = {"mclassname":mc};
	
	if(mc){
		$.ajax({
			type:"POST",
			url:"findModuleclass.action",
			data:selected,
			dataType:"json",
			success:function (json){
			if(json.moduleclass!=null){
				  close3();
				  $('#result').window('open');
				  document.getElementById("ggggg4").style.visibility="visible";//注意：用$()方式不好用
				  $('#mclassname3').val(json.moduleclass.mclassname);
				  $('#classolder3').val(json.moduleclass.classolder);
				  $('#comments3').val(json.moduleclass.comments);
				}else{$.messager.alert('提示','没有相关分类','info');}				
			}
		});
	};

}
	//校验功能
	$(function (){
		$('#mclassname').blur(function(){
			var mcn = $('#mclassname').val();
			var selected = {"mclassname":$('#mclassname').val()};
			if(selected&&mcn.length!=0){
				if(mcn.length<10){
					$.ajax({
						type:"POST",
						url:"verifyModuleclass.action",
						data:selected,
						cache: false,
						success:function(data){
									if(data.b == 0){
										$("#Error").empty().append("<font color='red' size='2px'>此分类已存在</font>");
										flag1=false;
									}else{
										$("#Error").empty();
                                         flag1=true;
										}
								}
					});
				}else{$('#Error').empty().append("<font color='red' size='2px'>长度不能大于10</font>");flag1=false;}
				}else{
					$('#Error').empty().append("<font color='red' size='2px'>分类不能为空</font>");
					flag1=false;
					}
			})
		});


	 //关闭各种窗口
	 function close1(){
     $('#add').window('close');
     }
     
     function close2(){
     $('#update').window('close');
     }
	 function close3(){
	 $('#find').window('close');
	 }
	 function close4(){
	 $('#result').window('close');
	 }
	
	 //============
	 function onlyNum_up()  
	  {  
	    //考虑小键盘上的数字键  
	    if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))) 
	    	{
	    	$("#Shuzi").append("<font color='red' size='2px'>请输入数字</font>");
	           event.returnvalue=false;
	           flag2=false;
	    	}else{
		    	
					if($("#classolder").val().length>5){
						   $("#Shuzi").append("<font color='red' size='2px'>长度不能大于5</font>");
						   flag2=false;
						}else{
                              flag2=true;
							}
						
		    	}
    	
	  }
	 function onlyNum_down()  
	  {  
	    	$("#Shuzi").empty();
	    	$("#xg_Shuzi").empty();
	  }
	 function xiugai_up()  
	  {  
	    //考虑小键盘上的数字键  
	    if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))) 
	    	{
	    	$("#xg_Shuzi").append("<font color='red' size='2px'>请输入数字</font>");
	    	flag3=false;
	    	}else{
					if(classolder1.value.length>5){
						   $("#xg_Shuzi").append("<font color='red' size='2px'>长度不能大于5</font>");
						   flag3=false;
						}else{
                                flag3=true;
							}
						
		    	}  
	  }  	
	</script>

  </head>
  
  <body>
    <table id="tt"></table>
    <!-- jqueryeasyui实现的添加界面 -->
	  <div id="add" closed="true" class="easyui-window" title="添加分类" iconCls="icon-add" style="width:400px;height:300px;padding:5px;background: #fafafa;">
	 
		<div class="easyui-layout" fit="true" style="visibility:hidden;" id="ggggg1">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
			<form id="addform">
				<table>
				<tr>
					<td>模块分类：</td>
					<TD><input id="mclassname" name="mclassname" ></TD>
					<TD height="30" class="top_hui_text"><span class="login_txt" id="Error"></span></TD>
										
				</tr>
				<tr>
					<td>分类顺序：</td>
					<TD><input id="classolder" name="classolder" class="easyui-validatebox" onkeydown="onlyNum_down();" onkeyup="onlyNum_up();"></TD>
					<TD height="30" class="top_hui_text"><span class="login_txt" id="Shuzi"></span></TD>
				</tr>
				<tr>
					<td>备注：</td>
					<td><textarea id="comments" name="comments" validType="length[1,20]" class="easyui-validatebox" style="height:60px;"></textarea></td>
				</tr>
				</table>
			</form>					
			</div>
			<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="add()">提交</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close1()">取消</a>
			</div>
		</div>
		
	</div>
  <!-- jqueryeasyui实现的修改界面 -->
	  <div id="update" closed="true" class="easyui-window" title="修改分类" iconCls="icon-edit" style="width:400px;height:300px;padding:5px;background: #fafafa;">
	  
		<div class="easyui-layout" fit="true" style="visibility:hidden;" id="ggggg2">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
				<table>
				<tr>
					<td>模块分类：</td>
					<TD><input id="mclassname1" name="mclassname" readonly="readonly" style="background-color:#EBEBE4"></TD>
				</tr>
				<tr>
					<td>分类顺序：</td>
					<TD><input id="classolder1" name="classolder" class="easyui-validatebox" onkeydown="onlyNum_down();" onkeyup="xiugai_up();"></TD>
					<TD height="30" class="top_hui_text"><span class="login_txt" id="xg_Shuzi"></span></TD>
				</tr>
				<tr>
					<td>备注：</td>
					<td><textarea id="comments1" name="comments" validType="length[1,100]" class="easyui-validatebox" style="height:60px;"></textarea></td>
				</tr>
				</table>					
			</div>
			<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="update()"><span id="xiayige">下一个</span></a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close2()">取消</a>
			</div>
		</div>
		
	</div>
	  <!-- jqueryeasyui实现的查找界面 -->
	  <div id="find" closed="true" class="easyui-window" title="查询分类" iconCls="icon-search" style="width:350px;height:200px;padding:5px;background: #fafafa;">
	  
		<div class="easyui-layout" fit="true" style="visibility:hidden;" id="ggggg3">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
				<table>
				<tr>
					<td>模块分类：</td>
					<TD><input id="mclassname2" name="mclassname"></TD>
				</tr>
				</table>					
			</div>
			<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="find()">查询</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close3()">取消</a>
			</div>
		</div>
		
	</div>
	 <!-- jqueryeasyui实现的查询结果显示界面 -->
	  <div id="result" closed="true" class="easyui-window" title="查询结果" style="width:350px;height:300px;padding:5px;background: #fafafa;">
	  
		<div class="easyui-layout" fit="true" style="visibility:hidden;" id="ggggg4">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
				<table>
				<tr>
					<td>模块分类：</td>
					<TD><input id="mclassname3" name="mclassname" readonly="readonly" style="background-color:#EBEBE4"></TD>
				</tr>
				<tr>
					<td>分类顺序：</td>
					<TD><input id="classolder3" name="classolder" readonly="readonly" style="background-color:#EBEBE4"></TD>
				</tr>
				<tr>
					<td>备注：</td>
					<td><textarea id="comments3" name="comments" readonly="readonly" class="easyui-validatebox" style="height:60px;background-color:#EBEBE4;"></textarea></td>
				</tr>
				</table>					
			</div>
			<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="close4()">确定</a>
			</div>
		</div>
		
	</div>
	
  </body>
</html>
