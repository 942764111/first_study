<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>试卷管理</title>
		<link href="css/wjh_main.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/sjld.js"></script>  
		<script type="text/javascript" src="js/jdzj.js"></script> 
		<script  type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript">   
		var validator;
		var validator1;
		 $(function(){ 
			$('#test_t').datagrid({
				title:'教师组卷页面',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				fitColumns:true,	
				fit:true,
				toolbar:[{
					id:'btnadd',
					text:'章测试组卷',
					iconCls:'icon-add',
					handler:function(){
					 $('#zj').form('clear');
                   $('#input').window('open');
                   $('#xz').val('0');
                   $('#dxz').val('0');
                   $('#pd').val('0');
                   $('#cz').val('0');
                   document.getElementById("input").style.visibility="visible";	
                }
				},'-',{
					 
					  text:'阶段测试组卷',
		              iconCls:'icon-add',
		              handler:function(){
					 	$('#jdzj').form('clear');
		              	$('#input3').window('open');
		              	$('#xz2').val('0');
	                   $('#dxz2').val('0');
	                   $('#pd2').val('0');
	                   $('#cz2').val('0');
		              document.getElementById("input3").style.visibility="visible";	
		              }
				},'-',{
					 
					  text:'修改选择题',
		              iconCls:'icon-back',
		              handler:function(){
		              window.location.href="xuanze.action";
		              }
				},'-',{
					 
					  text:'修改判断题',
		              iconCls:'icon-back',
		              handler:function(){
		              window.location.href="panduan.action";
		              }
				},'-',{
					 
					  text:'修改操作题',
		              iconCls:'icon-back',
		              handler:function(){
		              window.location.href="getCztd.action";
		              }
				},'-',{
					 
					  text:'查看试卷',
		              iconCls:'icon-search',
		              handler:function(){
		              $('#input1').window('open');
		              document.getElementById("input1").style.visibility="visible";	
		              }
				},'-',{
					  text:'删除试卷',
		              iconCls:'icon-cancel',
		              handler:function(){
					  $('#sc').form('clear');
		              $('#input2').window('open');
		              document.getElementById("input2").style.visibility="visible";	
		              }
				}
				
				]
	   });
	});



		
//查看试卷用三级联动
    var kcmc="";
    var zmczj="";
    var jmczj="";
    var zmcarray=new Array();
    var zmcarray1=new Array();
    var kmcarray=new Array();
    var kmcarray1=new Array();
    $(function(){ 
       $.ajax({
          		type:"POST",
          		url:"<%=basePath%>getkcmc",
          		dataType:'json',
          		success:function callback(r){
          		kcmc=r.kcdto;
          		 $('#kcmc1').combobox({
  	    	    data:kcmc,
  				valueField:'id',
  				textField:'value',
  			    editable:false 
  					});
              }
		});
  			
  		$('#kcmc1').combobox({
 		   onSelect:function(){
			 var kmc=productFormatter5($("#kcmc1").combobox("getValues"));
				 $.ajax({
                 		type:"POST",
                 		url:"<%=basePath%>getzmc",
                 		data:"kcmc="+kmc,
                 		dataType:'json',
                 		success:function callback(r){
                 			 $('#zmc1').combobox({
        		   	    	    data:r.zdto,
        		   				valueField:'id',
        		   				textField:'value',
        		   			    editable:false 
        		   			});
                 			 zmczj=r.zdto;
                 		}
              		});  
 		 		}
		 }); 
 		 
 		 
 		  $('#zmc1').combobox({
   		   onSelect:function(){    		      
				  var zmc=productFormatter6($("#zmc1").combobox("getValues"));
				  var kmc1=productFormatter5($("#kcmc1").combobox("getValues"));
	
   			 var kz={"zmc":zmc,"kcmc":kmc1};
				 $.ajax({
                 		type:"POST",
                 		url:"<%=basePath%>getsjbh",
                 		data:kz,
                 		dataType:'json',
                 		success:function callback(r){
                 			 $('#jmc').combobox({
        		   	    	    data:r.sjbhdto,
        		   				valueField:'id',
        		   				textField:'value',
        		   			    editable:false 
        		   			});
         		   			jmczj=r.sjbhdto;
                 		}
              			});
		 }});                
       });


  //删除试卷用三级联动
 
    $(function(){ 
       $.ajax({
         		type:"POST",
         		url:"<%=basePath%>getkcmc",
          		dataType:'json',
          		success:function callback(r){
          		kcmc=r.kcdto;
          		 $('#kcmc3').combobox({
 	    	    data:kcmc,
 				valueField:'id',
 				textField:'value',
 			    editable:false 
  			});
  			
  			
  		   $('#kcmc3').combobox({
 		   onSelect:function(){
			 var kmc=productFormatter5($("#kcmc3").combobox("getValues"));
   			 
				 $.ajax({
                 		type:"POST",
                 		url:"<%=basePath%>getzmc",
                 		data:"kcmc="+kmc,
                 		dataType:'json',
                 		success:function callback(r){
                 			 $('#zmc3').combobox({
        		   	    	    data:r.zdto,
        		   				valueField:'id',
        		   				textField:'value',
        		   			    editable:false 
        		   			});
                 			 zmczj=r.zdto;
	         		   		kmcarray.push(kmc);
	         		   		kmcarray1.push(r.zdto);
                 		}
              			});
 
 		 }}); 
 		 
 		 
 		  $('#zmc3').combobox({
   		   onSelect:function(){    		      
				  var zmc=productFormatter6($("#zmc3").combobox("getValues"));
				  var kmc1=productFormatter5($("#kcmc3").combobox("getValues"));

   			 var kz={"zmc":zmc,"kcmc":kmc1};
				 $.ajax({
                 		type:"POST",
                 		url:"<%=basePath%>getsjbh",
                 		data:kz,
                 		dataType:'json',
                 		success:function callback(r){
                 			 $('#jmc3').combobox({
        		   	    	    data:r.sjbhdto,
        		   				valueField:'id',
        		   				textField:'value',
        		   			    editable:false 
        		   			});
         		   			jmczj=r.sjbhdto;
	         		   		zmcarray.push(zmc);
	         		   		zmcarray1.push(r.sjbhdto);
                 		}
              			});
		 }});                
              }
              });
              });
              			
              			
    function productFormatter5(value){
	for(var i=0; i<kcmc.length; i++){
		if (kcmc[i].id==value) return  kcmc[i].value;
	}
	return value;
}
    function productFormatter6(value){
		for(var i=0; i<zmczj.length; i++){
			if (zmczj[i].id==value) return  zmczj[i].value;
		}
		return value;
	}
    function productFormatter7(value){
		for(var i=0; i<jmczj.length; i++){
			if (jmczj[i].id==value) return  jmczj[i].sjbh;
		}
		return value;
	}
    
    function productFormatter8(value){
		for(var i=0; i<jmczj.length; i++){
			if (jmczj[i].id==value) return  jmczj[i].value;
		}
		return value;
	}
	//组卷用		
		function input(){
			var zmc=$("#bindZh").find("option:selected").text();//章名称
			var zbh=$("#bindZh").find("option:selected").val();//章编号
			if(zmc=="--请选择章名称--")
			 {
				 $.messager.alert('警告','请将查询 所需的 课程 章 节填充完全','info');
		   	 return;
			 }

			if(validator1.form()){
				  var input = {"xz":$('#xz').val(),"dxz":$('#dxz').val(),"pd":$('#pd').val(),"cz":$('#cz').val(),"CName":zmc,"zbh1":zbh};
				  if($('#xz').val()==0&&$('#dxz').val()==0&&$('#pd').val()==0&&$('#cz').val()==0){
					  	$.messager.alert('警告','不允许所有题有题型题数都为零','info');
					  }else{
						  if($('#xz').val()>50||$('#dxz').val()>50||$('#pd').val()>50||$('#cz').val()>50){
							  $.messager.alert('警告','题数不能大于50！！','info');
							  }else{
								  if($('#xz').val()<0||$('#dxz').val()<0||$('#pd').val()<0||$('#cz').val()<0){
									  $.messager.alert('警告','题数不允许为负值','info');
									  }else{
										  if(parseInt($('#xz').val())+parseInt($('#dxz').val())+parseInt($('#pd').val())+parseInt($('#cz').val())>100){
											  $.messager.alert('警告','所有题型总的题数不能超过100道！！','info');
											  }else{
												  $.ajax({
														type:"POST",
														url:"zjgz.action",
														data:input,
														success:function(json){
														    	 if(json.tip!="xz"){
																		if(json.tip!="dxz"){
																			if(json.tip!="pd"){
																				if(json.tip!="cz"){
																					window.location.href="xztnr.action";
																					$('#input').window('close');
																				    $('#test_t').datagrid('reload');
																					}else{$.messager.alert('提示信息','所选操作题数大于已有题数！！！','info');}
																				}else{$.messager.alert('提示信息','所选判断题数大于已有题数！！！','info');}
																			}else{$.messager.alert('提示信息','所选多选题数大于已有题数！！！','info');}
																		}else{$.messager.alert('提示信息','所选单选题数大于已有题数！！！','info');}
																}
														});
												  }
										  }
							  }
						}
				}
     }
	//异步校验
	$(function(){
		$("#sjname").blur(function(){
			var sjname = $("#sjname").val();
				$.ajax({
		            type:"POST",
		           url:"beforezj.action",
		            data:"sjname="+sjname,
		           success:function(data){
		           		if(data.tip=="aaa"){
		           			$.messager.alert('提示信息','该试卷名称已存在，请输入别的试卷名称！','info');
		           		}else if(data.tip=="bbb"){
		           			$.messager.alert('提示信息','请输入试卷名称！','info');
			           		}
				   }
	      		});
			
		});	
	});
	

	//进行章组卷时用来校验单选、多选、判断、操作题的题数。
	function jyts(){
		//var CName = $("#bindZh").find("option:selected").text();
		var zbh = $("#bindZh").find("option:selected").val();
		$.ajax({
            type:"POST",
           	url:"jyts1.action",
            data:{"zbh1":zbh},
           success:function(json){
                if(json.total1>=50){
                	$("#ts5").empty().append("<font color='red' size='2px'>题数不能大于50条</font>");
                    }else{
                    	$("#ts5").empty().append("<font color='red' size='2px'>题数不能大于"+json.total1+"条</font>");
                        }
	            if(json.total2>=50){
	            	$("#ts6").empty().append("<font color='red' size='2px'>题数不能大于50条</font>");
		            }else{
		            	$("#ts6").empty().append("<font color='red' size='2px'>题数不能大于"+json.total2+"条</font>");
		            }	
	            if(json.total3>=50){
	            	$("#ts7").empty().append("<font color='red' size='2px'>题数不能大于50条</font>");
		            }else{
		            	$("#ts7").empty().append("<font color='red' size='2px'>题数不能大于"+json.total3+"条</font>");
			            }
	            if(json.total4>=50){
	            	$("#ts8").empty().append("<font color='red' size='2px'>题数不能大于50条</font>");
		            }else{
		            	$("#ts8").empty().append("<font color='red' size='2px'>题数不能大于"+json.total4+"条</font>");
			            }	
	            	
		   }
  		});
	}


	
	//关闭章组卷窗口
	 function close1(){
     $('#input').window('close');
     }
     
    
    //查看试卷用
    function input1(){
    	var zmc=$("#zmc1").combobox("getValue");
		var jmc=$("#jmc").combobox("getValue");//顺序号
		var jmc1=productFormatter7($("#jmc").combobox("getValue"));//试卷编号
		var jmc2=productFormatter8($("#jmc").combobox("getValue"));//试卷名称
			if(zmc=="--请选择章名称--")
			 {
				 $.messager.alert('警告','请将查询 所需的 课程 章 填充完全','info');
		   	 	return;
			 }
			if(jmc=="--请选择试卷名称--")
			 {
				 $.messager.alert('警告','请将查询 所需的试卷编号填充完全','info');
		  	 	return;
			 }
    	var input = {"zbh":zmc,"sjbh2":jmc1,"sjname":jmc2};
		 $.ajax({
			type:"POST",
			url:"sjxztnr.action",
			data:input,
			success:function(){
				window.location.href="sj.action";
			}
			});
		 $('#input1').window('close');
		 $('#test_t').datagrid('reload');
	}
	
	
	 function close2(){
     $('#input1').window('close');
     }


	 $(function(){ 

		//对输入题数的校验(阶段组卷时)
		validator=$("#jdzj").validate({
			rules: {
			    "dxz":{
	                number:true,
			        required:true,
			        min:0,
			        digits:true,
			        maxlength:10
			    },
			    "ddxz": {
					required: true,
					number:true,
					min:0,
			        digits:true,
					maxlength:10
				},
				"dcz": {
					required: true,
					number:true,
					min:0,
			        digits:true,
					maxlength:10
				},
				"dpd": {
					required: true,
					number:true,
					min:0,
			        digits:true,
					maxlength:10
				}
			},	
			messages: {
				"dxz":{
			        required: " (不能为空)",
					number:" (数字)",
					min:"(最小0)",
			        digits:"(整数)",
					maxlength:"(最多10位)"
			    },
				"ddxz": {
					required: " (不能为空)",
					number:" (数字)",
					min:"(最小0)",
			        digits:"(整数)",
					maxlength:"(最多10位)"
				} ,
				"dcz": {
					required: " (不能为空)",
					number:" (数字)",
					min:"(最小0)",
			        digits:"(整数)",
					maxlength:"(最多10位)"
				} ,
				"dpd":{
					required: " (不能为空)",
					number:" (数字)",
					min:"(最小0)",
			        digits:"(整数)",
					maxlength:"(最多10位)"
				}
			 }	
		});


		//对输入题数的校验(章组卷时)
		validator1=$("#zj").validate({
			rules: {
			    "xz":{
	                number:true,
			        required:true,
			        maxlength:10,
			        min:0,
			        digits:true
			        
			    },
			    "dxz": {
					required: true,
					number:true,
					min:0,
			        digits:true,
					maxlength:10
				},
				"cz": {
					required: true,
					number:true,
					min:0,
			        digits:true,
					maxlength:10
				},
				"pd": {
					required: true,
					number:true,
					min:0,
			        digits:true,
					maxlength:10
				}
			},	
			messages: {
				"xz":{
			        required: " (不能为空)",
					number:" (数字)",
					min:"(最小0)",
			        digits:"(整数)",
					maxlength:"(最多10位)"
			    },
				"dxz": {
					required: " (不能为空)",
					number:" (数字)",
					min:"(最小0)",
			        digits:"(整数)",
					maxlength:"(最多10位)"
				} ,
				"cz": {
					required: " (不能为空)",
					number:" (数字)",
					min:"(最小0)",
			        digits:"(整数)",
					maxlength:"(最多10位)"
				} ,
				"pd":{
					required: " (不能为空)",
					number:" (数字)",
					min:"(最小0)",
			        digits:"(整数)",
					maxlength:"(最多10位)"
				}
			 }	
		});
	 });
	
//阶段组卷用
	 function input3(){
			var TCName = $("#zjTCName").find("option:selected").text();
			var cname1 = $("#zjz1").find("option:selected").text();//起始章名称
			var cname2 = $("#zjz2").find("option:selected").text();//截止章名称
			var zbh1 = $("#zjz1").find("option:selected").val();//起始章编号
			var zbh2 = $("#zjz2").find("option:selected").val();//截止章编号
			if(cname2=="--请选择截止章名称--")
			 {
				 $.messager.alert('警告','请将查询 所需的 课程 章 填充完全','info');
		   	 return;
			 }
			
			if(validator.form()){
				var input = {"xz":$('#xz2').val(),"dxz":$('#dxz2').val(),"pd":$('#pd2').val(),"cz":$('#cz2').val(),"zbh1":zbh1,"zbh2":zbh2,"TCName":TCName,"CName":cname1,"CName1":cname2};
				 if($('#xz2').val()==0&&$('#dxz2').val()==0&&$('#pd2').val()==0&&$('#cz2').val()==0){
					  	$.messager.alert('警告','不允许所有题有题型题数都为零','info');
					  }else{
						  if($('#xz2').val()>50||$('#dxz2').val()>50||$('#pd2').val()>50||$('#cz2').val()>50){
							  $.messager.alert('警告','题数不能大于50！！','info');
							  }else{
								  if($('#xz2').val()<0||$('#dxz2').val()<0||$('#pd2').val()<0||$('#cz2').val()<0){
									  $.messager.alert('警告','题数不允许为负值','info');
									  }else{
										  if(parseInt($('#xz2').val())+parseInt($('#dxz2').val())+parseInt($('#pd2').val())+parseInt($('#cz2').val())>100){
											  $.messager.alert('警告','所有题型总的题数不能超过100道！！','info');
											  }else{
												  $.ajax({
														type:"POST",
														url:"jdzjgz.action",
														data:input,
														success:function(json){
															if(json.tip!="xz"){
																if(json.tip!="dxz"){
																	if(json.tip!="pd"){
																		if(json.tip!="cz"){
																			window.location.href="xztnr.action";
																			 $('#input3').window('close');
																			  $('#test_t').datagrid('reload');
																			}else{$.messager.alert('提示信息','所选操作题数大于已有题数！！！','info');}
																		}else{$.messager.alert('提示信息','所选判断题数大于已有题数！！！','info');}
																	}else{$.messager.alert('提示信息','所选多选题数大于已有题数！！！','info');}
																}else{$.messager.alert('提示信息','所选单选题数大于已有题数！！！','info');}
															}
														});
												  }
										  }
								  }
						  }
					}
			  }

	//进行阶段组卷时用来校验单选、多选、判断、操作题的题数。
		function jyts2(){
			//var cname1 = $("#zjz1").find("option:selected").text();//起始章名称
			//var cname2 = $("#zjz2").find("option:selected").text();//截止章名称
			var zbh1 = $("#zjz1").find("option:selected").val();//起始章编号
			var zbh2 = $("#zjz2").find("option:selected").val();//截止章编号
			$.ajax({
	            type:"POST",
	           url:"jyts2.action",
	            data:{"zbh1":zbh1,"zbh2":zbh2},
	           success:function(json){
	 	           if(json.total1>=50){
	 	        		$("#ts1").empty().append("<font color='red' size='2px'>题数不能大于50条</font>");
		 	           }else{
		 	        	  $("#ts1").empty().append("<font color='red' size='2px'>题数不能大于"+json.total1+"条</font>");
			 	           }
	            	if(json.total2>=50){
	            		$("#ts2").empty().append("<font color='red' size='2px'>题数不能大于50条</font>");
		            	}else{
		            		$("#ts2").empty().append("<font color='red' size='2px'>题数不能大于"+json.total2+"条</font>");
			            	}
	            	if(json.total3>=50){
	            		$("#ts3").empty().append("<font color='red' size='2px'>题数不能大于50条</font>");
		            	}else{
		            		$("#ts3").empty().append("<font color='red' size='2px'>题数不能大于"+json.total3+"条</font>");
		            	}
	            if(json.total4>=50){
	            	$("#ts4").empty().append("<font color='red' size='2px'>题数不能大于50条</font>");
		            }else{
		            	$("#ts4").empty().append("<font color='red' size='2px'>题数不能大于"+json.total4+"条</font>");
			            }
			   		}
	  			});
			}
		
	//异步校验试卷名称是否存在
		$(function(){
			$("#sjname2").blur(function(){
				var sjname = $("#sjname2").val();
					$.ajax({
			            type:"POST",
			           url:"beforezj.action",
			            data:"sjname="+sjname,
			           success:function(data){
						if(data.tip=="aaa"){
		           			$.messager.alert('提示信息','该试卷名称已存在，请输入别的试卷名称！','info');
		           		}else if(data.tip=="bbb"){
		           			$.messager.alert('提示信息','请输入试卷名称！','info');
			           		}
					   }
		      		});
				
			});	
		});
		
	 function close4(){
  $('#input3').window('close');
  }



	 //删除试卷用
	    function input2(){
	    	var zmc=$("#zmc3").combobox("getValue");
			var jmc=$("#jmc3").combobox("getValue");//顺序号
			var jmc1=productFormatter7($("#jmc3").combobox("getValue"));//试卷编号
			var jmc2=productFormatter8($("#jmc3").combobox("getValue"));//试卷名称
			var jmc=$("#jmc3").combobox("getValue");//得到的是顺序号
			if(zmc=="--请选择章名称--"||zmc=='')
			 {
				 $.messager.alert('警告','请将查询 所需的章 填充完全','info');
		   	 return;
			 }
			
			if(jmc=="--请选择试卷名称--"||jmc=='')
			 {
				 $.messager.alert('警告','请将查询 所需的试卷名称填充完全','info');
		  	 return;
			 }
			$.messager.confirm('删除','确认删除试卷名称为   “'+jmc2+'”  的数据吗?',function(d){					        	
            	if(d){
            		$.ajax({
            			type:"POST",
            			url:"delsj.action",
            			data:{"zbh":zmc,"sjbh2":jmc1},
            			success:function(json){
            				
            				if(json.tip=="k"){
            					 $.messager.alert('提示信息','试卷名称为   “'+jmc2+'”  的数据删除成功！','info');
        		   				 $('#input2').window('close');
                				}
            				if (json.tip=="b"){
                					$.messager.alert('禁止删除','删除试卷名称为   “'+jmc2+'”  的数据会破坏系统数据库的完整性,所以禁止删除这条数据！！','warning')
                					$('#input2').window('close');
                    				}
            					}
            			});
		   				
			        }
    		 });			        		       			
		 }
		
		 function close3(){
	     $('#input2').window('close');
	     }

	
		</script>

	</head>
	<body  class="easyui-layout">
		<div region="center" title="" style="overflow:hidden;">
			<table id="test_t" class="easyui-datagrid" style="visibility:hidden;">
				<thead>
					<tr>
						<th field="f1" width="70">编号</th>
						<th field="f2" width="450">说明</th>	
						<th field="f3" width="450">备注</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<td>1</td>
						<td align="center">本界面为教师管理界面，非教师人员不能登录</td>
						<td align="center">权限受控制</td>
					</tr>
					
					<tr>
						<td>2</td>
						<td align="center">本界面只用来进行组卷工作</td>
						<td align="center">涉及操作试题请进入其它界面</td>
					</tr>
				</tbody>
			</table>
		 	<div id="input" closed="true" maximizable="false" minimizable="false" collapsible="false" class="easyui-window" title="输入组卷规则" iconCls="icon-input" style="width:490px;height:370px;padding:5px;background: #fafafa;visibility:hidden;">
				<div class="easyui-layout" fit="true">
					<div region="center" border="false" style="padding:24px;background:#fff;border:6px solid #ccc;">
						<form id="zj">
						<table align="center">
							<tr>
								<td align="center">
									<h2>
										请输入组卷规则
									</h2>
								</td>
							</tr>
							<tr>
								<td align="left">
									课程名称：
									<%--<select id="kcmc" style="width: 160px;">
									<option >--请选择课程名称--</option>
									</select>--%>
									<select id="bindTCName" onChange="getZh()" style="width:150px;">
									<option  value="-1">--请选择课程名称--</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="left">
									章&nbsp;&nbsp;名&nbsp;&nbsp;称：
									<%--<select id="zmc"  onChange="jyts()" style="width: 160px;">
									<option >--请选择章名称--</option>
									</select>--%>
									<select id="bindZh"  onChange="jyts()" style="width:150px;">
									   		<option  value="-1">--请选择章名称--</option>
									   	</select>
								</td>
							</tr>
							<tr>
								<td align="left">
									单选题数：
									<input id="xz" name="xz">
								</td>
								<td height="30" class="top_hui_text"><span class="login_txt" id="ts5">*&nbsp;请输入单选题的个数</span></td>
							</tr>
							<tr>
								<td align="left">
									多选题数：
									<input id="dxz" name="dxz">
								</td>
								<td height="30" class="top_hui_text"><span class="login_txt" id="ts6">*&nbsp;请输入多选题的个数</span></td>
							</tr>
							<tr>
								<td align="left">
									判断题数：
									<input id="pd" name="pd">
								</td>
								<td height="30" class="top_hui_text"><span class="login_txt" id="ts7">*&nbsp;请输入判断题的个数</span></td>
							</tr>
							<tr>
								<td align="left">
									操作题数：
									<input id="cz" name="cz">
								</td>
								<td height="30" class="top_hui_text"><span class="login_txt" id="ts8">*&nbsp;请输入操作题的个数</span></td>
							</tr>

						</table>
						<div region="south" border="false" style="text-align:right;height:60px;line-height:60px;">
							<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="input()">确定</a>
							<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close1()">取消</a>
						</div>
						</form>
					</div>
				</div>
			</div>
		
		 	<div id="input3" closed="true" maximizable="false" minimizable="false" collapsible="false" class="easyui-window" title="输入阶段组卷规则" iconCls="icon-input" style="width:490px;height:388px;padding:5px;background: #fafafa;visibility:hidden;">
				<div class="easyui-layout" fit="true">
					<div region="center" border="false" style="padding:20px;background:#fff;border:6px solid #ccc;">
					<form id="jdzj">
						<table align="center">
							<tr align="center">
								<td>
									<h2>
										请输入组卷规则
									</h2>
								</td>
							</tr>
							<tr>
								<td align="left">
									课程名称：
										<select id="zjTCName" onChange="getZjz()" style="width:150px;">
										<option  value="-1">--请选择课程名称--</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="left">
									起&nbsp;&nbsp;始&nbsp;&nbsp;章：
									   	<select id="zjz1"  onChange="getZjjzz()" style="width:150px;">
									   		<option  value="-1">--请选择起始章名称--</option>
									   	</select>
									</td>
								</tr>
								<tr>
									<td align="left">
									截&nbsp;&nbsp;止&nbsp;&nbsp;章：
									   	<select id="zjz2"  onChange="jyts2()"  style="width:150px;">
									   		<option  value="-1">--请选择截止章名称--</option>
									   	</select>
									</td>
								</tr>	
							<tr>
								<td align="left">
									单选题数：
									<input id="xz2" name="dxz">
								</td>
								<td height="30" class="top_hui_text"><span class="login_txt" id="ts1">*&nbsp;请输入单选题的个数</span></td>
							</tr>
							<tr>
								<td align="left">
									多选题数：
									<input id="dxz2" name="ddxz">
								</td>
								<td height="30" class="top_hui_text"><span class="login_txt" id="ts2">*&nbsp;请输入多选题的个数</span></td>
							</tr>
							<tr>
								<td align="left">
									判断题数：
									<input id="pd2" name="dpd">
								</td>
								<td height="30" class="top_hui_text"><span class="login_txt" id="ts3">*&nbsp;请输入判断题的个数</span></td>
							</tr>
							<tr>
								<td align="left">
									操作题数：
									<input id="cz2" name="dcz">
								</td>
								<td height="30" class="top_hui_text"><span class="login_txt" id="ts4">*&nbsp;请输入操作题的个数</span></td>
							</tr>

						</table>
						<div region="south" border="false" style="text-align:right;height:60px;line-height:60px;">
							<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="input3()">确定</a>
							<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close4()">取消</a>
						</div>
						</form>
					</div>
				</div>
			</div>
			
			<div id="input1" closed="true" maximizable="false" minimizable="false" collapsible="false" class="easyui-window" title="查看试卷" iconCls="icon-input" style="width:350px;height:260px;padding:5px;background: #fafafa;visibility:hidden;">
				<div class="easyui-layout" fit="true">
					<div region="center" border="false" style="padding:24px;background:#fff;border:6px solid #ccc;">
						<table>
							<tr>
								<td align="left">
									课程名称:
									<select id="kcmc1" class="easyui-combobox"  style="width: 200px;">
									<option  >--请选择课程名称--</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="left">
									章&nbsp;&nbsp;名&nbsp;&nbsp;称:
									<select id="zmc1" class="easyui-combobox" style="width: 200px;">
									<option  >--请选择章名称--</option>
									</select>
								</td>
							</tr>
	
							<tr>
								<td align="left">
									试卷名称:
									<select id="jmc" class="easyui-combobox"  style="width: 200px;">
									<option  >--请选择试卷名称--</option>
									</select>
								</td>
							</tr>
						</table>
					<div region="south" border="false" style="text-align:right;height:60px;line-height:60px;">
						<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="input1()">确定</a>
						<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close2()">取消</a>
					</div>
	       		</div>
			</div>
		</div>
			
			
		<div id="input2" closed="true" maximizable="false" minimizable="false" collapsible="false" class="easyui-window" title="删除试卷" iconCls="icon-input" style="width:350px;height:260px;padding:5px;background: #fafafa;visibility:hidden;">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false" style="padding:24px;background:#fff;border:6px solid #ccc;">
				<s:form id="sc">
		 			<table>
						<tr>
							<td align="left">
								课程名称:
								<select id="kcmc3" class="easyui-combobox"  style="width: 200px;">
								<option  >--请选择课程名称--</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left">
								章&nbsp;&nbsp;名&nbsp;&nbsp;称:
								<select id="zmc3" class="easyui-combobox"  style="width: 200px;">
								<option  >--请选择章名称--</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left">
								试卷名称:
								<select id="jmc3" class="easyui-combobox"  style="width: 200px;">
								<option  >--请选择试卷名称--</option>
								</select>
							</td>
						</tr>
					</table>
					
				<div region="south" border="false" style="text-align:right;height:60px;line-height:60px;">
					<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="input2()">确定</a>
					<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close3()">取消</a>
				</div>
				</s:form>
	       </div>
		</div>
	</div>
		
	</div>
</body>
</html>
