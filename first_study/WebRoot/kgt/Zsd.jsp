<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jQuery EasyUI</title>
	<link href="css/wjh_main.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
<script type="text/javascript">
var kcmc="";
var zmczj="";
var jmczj="";
var zmcarray=new Array();
var zmcarray1=new Array();
var kmcarray=new Array();
var kmcarray1=new Array();
		function getWidth(percent){ 
		    return (document.body.clientWidth-25-11)*percent; 
		}
		$(function(){
			$('#xz').datagrid({
				width:getWidth(1),
				fitColumns:true,
				nowrap: true,
				striped: true,
				idField:'zsdbh',
				columns:[[	
					{field:'tg',title:'题干',width:getWidth(0.4),align:'left'},
					{field:'xx1',title:'A',width:getWidth(0.1),align:'left'},
					{field:'xx2',title:'B',width:getWidth(0.1),align:'left'},
					{field:'xx3',title:'C',width:getWidth(0.1),align:'left'},
					{field:'xx4',title:'D',width:getWidth(0.1),align:'left'},
					{field:'da',title:'答案',width:getWidth(0.1),align:'center'},
					{field:'nyd',title:'难易度',width:getWidth(0.1),align:'center'}
						]],
				pagination:true,
				singleSelect:true,
				rownumbers:true
			});
			 $('#xz').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
		});	
		$(function(){
			$('#tk').datagrid({
				width:getWidth(1),
				fitColumns:true,
				nowrap: true,
				striped: true,
				idField:'zsdbh',
				columns:[[	
					{field:'tg',title:'题干',width:getWidth(0.8),align:'left'},
					{field:'da',title:'答案',width:getWidth(0.1),align:'center'},
					{field:'nyd',title:'难易度',width:getWidth(0.1),align:'center'}
						]],
				pagination:true,
				singleSelect:true,
				rownumbers:true
			});
			 $('#tk').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
		});	
		$(function(){
			$('#pd').datagrid({
				width:getWidth(1),
				fitColumns:true,
				nowrap: true,
				striped: true,
				idField:'zsdbh',
				columns:[[	
					{field:'tg',title:'题干',width:getWidth(0.8),align:'left'},
					{field:'da',title:'答案',width:getWidth(0.1),align:'center'},
					{field:'nyd',title:'难易度',width:getWidth(0.1),align:'center'}
						]],
				pagination:true,
				singleSelect:true,
				rownumbers:true
			});
			 $('#pd').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
		});	
		$(function(){
			$('#czt').datagrid({
				width:getWidth(1),
				fitColumns:true,
				nowrap: true,
				striped: true,
				idField:'tihao',
				columns:[[	
                    {field:'tihao',title:'操作题号',width:getWidth(0.1)},
                    {field:'jytigan',title:'操作题题干描述',width:getWidth(0.8)},
                    {field:'fenzhi',title:'分值',width:getWidth(0.04),align:'center'}
						]],
				pagination:true,
				singleSelect:true,
				rownumbers:true
			});
			 $('#czt').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
		});	
		$(function(){	
			//$.messager.alert('提示','选中某条数据，双击即可查看其详细信息。','info');			
			$('#test_tables').datagrid({
				title:'知识点(双击可查其详细信息！)',
				iconCls:'icon-save',
				width:getWidth(1),
				nowrap: true,
				striped: true,
				fitColumns:true,
				idField:'int3',		
				url:'listZsd1.action',
				loadMsg:'加载数据,请等待...',
				onLoadSuccess:function(){
					var data=$('#test_tables').datagrid('getData');
					if(data.total==0)
					{
					      $.messager.alert('信息','没有数据','info');
					}				
				},
				onDblClickRow: function(rowIndex, rowData) {
					show(rowData);
				} ,
				columns:[[
                        {field:'int3',title:'知识点编号',width:getWidth(0.1),align:'center'},
                        {field:'kcmc',title:'课程名称',width:getWidth(0.1),align:'center'},
                        {field:'str4',title:'章名称 ',width:getWidth(0.1),align:'center'},
                        {field:'str3',title:'节名称',width:getWidth(0.2),align:'center'}, 
                        {field:'str1',title:'知识点名称',width:getWidth(0.1),align:'center'},
                        {field:'str2',title:'知识点描述',width:getWidth(0.2),align:'center'},
                        {field:'str5',title:'关键词',width:getWidth(0.1),align:'center'},
                        {field:'int4',title:'是否重点',width:getWidth(0.1),align:'center'},
                        {field:'int5',title:'是否难点',width:getWidth(0.1),align:'center'}
                         
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
					$('#btnadd').linkbutton('enable');
					$('#insert').form('clear');
					document.getElementById("add1").style.visibility="visible";
	                $('#add').window('open');
	                $.ajax({
                  		type:"POST",
                  		url:"getkcmc",
                  		dataType:'json',
                  		success:function callback(r){
                  		kcmc=r.kcdto;
                  	  $('#kcmc').combobox({
	      	   	    	    data:kcmc,
	      	   				valueField:'id',
	      	   				textField:'value',
	      	   			    editable:false
	      	   			});
                  		}
               			});
                    

                 	 $('#kcmc').combobox({
                		   onSelect:function(){
              			 var kmc=productFormatter5($("#kcmc").combobox("getValues"));
              			 var i=0;
              			 for(;i<kmcarray.length;i++)
              			 {
                  			 if(kmcarray[i]==kmc)
                  			 {
                  				 zmczj=kmcarray1[i];
                  				 $('#zmccao').combobox({
                  		   	    	    data:kmcarray1[i],
                  		   				valueField:'id',
                  		   				textField:'value',
                  		   			    editable:false
                  		   			   
                  		   			});
               		   			break;
                  			 }
              			 } 
              			 if(i>=kmcarray.length)
              			 {
                  			 
              				 $.ajax({
          	                  		type:"POST",
          	                  		url:"getzmc",
          	                  		data:"kcmc="+kmc,
          	                  		dataType:'json',
          	                  		success:function callback(r){
          	                  			 $('#zmccao').combobox({
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
              			 }  
                		 }}); 
                	 $('#zmccao').combobox({
              		   onSelect:function(){    		      
        				  var zmc=productFormatter6($("#zmccao").combobox("getValues"));
        				  var kmc1=productFormatter5($("#kcmc").combobox("getValues"));
          			 var i=0;
          			 for(;i<zmcarray.length;i++)
          			 {
              			 if(zmcarray[i]==zmc)
              			 {
                  			 jmczj=zmcarray1[i];
              				 $('#jmccao').combobox({
              		   	    	    data:zmcarray1[i],
              		   				valueField:'id',
              		   				textField:'value',
              		   			    editable:false 
              		   			});  			
           		   			break;
              			 }
          			 } 
          			 if(i>=zmcarray.length)
          			 {
              			 var kz={"zmc":zmc,"kcmc":kmc1};
          				 $.ajax({
      	                  		type:"POST",
      	                  		url:"getjmc",
      	                  		data:kz,
      	                  		dataType:'json',
      	                  		success:function callback(r){
      	                  			 $('#jmccao').combobox({
      	         		   	    	    data:r.jdto,
      	         		   				valueField:'id',
      	         		   				textField:'value',
      	         		   			    editable:false 
      	         		   			});
       	         		   			jmczj=r.jdto;
      		         		   		zmcarray.push(zmc);
      		         		   		zmcarray1.push(r.jdto);
      	                  		}
      	               			});
          			 }
          		 }});                 
      				}
				},'-',{
					  text:'删除',
		              iconCls:'icon-cancel',
		              handler:deleteRow
				},'-',{
					text:'修改',
		              iconCls:'icon-edit',
		              handler:function(){
						batchupdate();
					$('#next').linkbutton('enable');
					$('#umzmc').empty()
			} 
      			},'-',{
      				id:'btnquery',
    				text:'查询',
    				iconCls:'icon-search',
    				handler:function(){
      				  
    					$('#btnquery').linkbutton('enable');
    					//$('#chaxun').form('clear');
    					document.getElementById("query1").style.visibility="visible";
    					$('#query').window('open');
    					
      		       }
      			},'-',{
					text:'资源查询',
					iconCls:'icon-search',
					handler:function(){
						$('#findform').form('clear');
						document.getElementById("find1").style.visibility="visible";
						$('#find').window('open');
					}
				}]
				
			});
			$('#test_tables').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
				 });
		});
		 function productFormatter5(value){
				for(var i=0; i<kcmc.length; i++){
					if (kcmc[i].id==value)
						 return  kcmc[i].value;
				}
				return value;
			}
		    function productFormatter6(value){
				for(var i=0; i<zmczj.length; i++){
					if (zmczj[i].id==value) 
						return  zmczj[i].value;
				}
				return value;
			}
		    function productFormatter7(value){
				for(var i=0; i<jmczj.length; i++){
					if (jmczj[i].id==value) 
						return  jmczj[i].value;
				}
				return value;
			}
			
	
       // 添加一个知识点
		 $(function(){
				$('#add').dialog({
					buttons:[{
						text:'提交',
						iconCls:'icon-ok',
						handler:function(){
							//var zsdbh = $("#zsdbh1").val();
							var TCName = productFormatter5($("#kcmc").combobox("getValue"));
							var CName = productFormatter6($("#zmccao").combobox("getValue"));
							var zmc =  productFormatter7($("#jmccao").combobox("getValue"));
							var zsdmc = $("#zsdmc1").val();
							var zsdms = $("#zsdms1").val();
							var zsdkey = $("#zsdkey1").val();
							var sfzd = $("#sfzd1").val();
							var sfdn = $("#sfdn1").val();
						
						 	$.ajax({
						            type:"POST",
						            url:"insertZsd.action",
						            data:{"TCName":TCName,"CName":CName,"zmc":zmc,"zsdmc":zsdmc,"zsdms":zsdms,"zsdkey":zsdkey,"sfzd":sfzd,"sfdn":sfdn},
						            success:function(){
						            	$('#add').dialog('close');						            	
						            	$.messager.alert('添加','添加信息成功!!!','info',function(){
						            		$('#test_tables').datagrid('reload');	
						            	});
						            	$('#test_tables').datagrid('appendRow',{

						            		TCName:TCName,
						            		CName:CName,
						            		zmc:zmc,
						            		zsdmc:zsdmc,
						            		zsdms:zsdms,
						            		zsdkey:zsdkey,
						            		sfzd:sfzd,
						            		sfdn:sfdn
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
		
    
		//批量修改
		function batchupdate(){
		var selections = $('#test_tables').datagrid('getSelections');
		if(selections.length!=0){
			var selected = selections[0];
			if(selections.length==1){
				$('#next').linkbutton('disable');
			}
			update1(selected);
		}else{
		    $.messager.alert('提示','请选择一行数据','error');
		 }
	}

	function update1(selected){
		if(selected){
			document.getElementById("edit11").style.visibility="visible";
			 $('#edit1').window('open');
			 $('#zsdbh').val(selected.int3);
			 $('#TCName').val(selected.kcmc);
			 $('#CName').val(selected.str4);
			 $('#zmc').val(selected.str3);
			 $('#zsdmc').val(selected.str1);
			 $('#zsdms').val(selected.str2);
			 $('#zsdkey').val(selected.str5);
			 $('#sfzd').val(selected.int4);
			 $('#sfdn').val(selected.int5);
		}
	}
	
	function edit1(){

		var selections = $('#test_tables').datagrid('getSelections');
			var selected = selections[0];
				var zsdbh = $("#zsdbh").val();
	  			var TCName= $("#TCName").val();
	  			var CName = $("#CName").val();
	  			var zmc = $("#zmc").val();
	  			var zsdmc = $("#zsdmc").val();
	  			var zsdms = $("#zsdms").val();
	  			var zsdkey = $("#zsdkey").val();
	  			var sfzd = $("#sfzd").val();
	  			var sfdn = $("#sfdn").val();
  			if(zsdbh != selected.int3 || TCName != selected.kcmc || CName != selected.str4 || zmc!= selected.str3 || zsdmc != selected.str1
  		  			|| zsdms != selected.str2 || zsdkey != selected.str5 || sfzd != selected.int4 || sfdn != selected.int5){
  				$.ajax({
  		         type:"POST",
  		         url:"updateZsd.action",
  		         data:{"zsdbh":zsdbh,"TCName":TCName,"CName":CName,"zmc":zmc,"zsdmc":zsdmc,"zsdms":zsdms,"zsdkey":zsdkey,"sfzd":sfzd,"sfdn":sfdn},
  		         success:function(){
  		           	var index = $('#test_tables').datagrid('getRowIndex', selected); 
  					$('#test_tables').datagrid('deleteRow', index);
  		           	$('#test_tables').datagrid('appendRow',{
  		           		zsdbh:zsdbh,
  		           		TCName:TCName,
  		           		CName:CName,
  		           		zmc:zmc,
  		           		zsdmc:zsdmc,
  		           		zsdms:zsdms,
  		           		zsdkey:zsdkey,
  		           		sfzd:sfzd,
  		           		sfdn:sfdn
  		            });
  		          if(selections.length>1){
  	      			batchupdate();
  	      		}else{
  	          	$.messager.alert('提示','数据修改成功!');
  	  			$('#test_tables').datagrid('reload');
  				clos();
  	        		  }
  		         }
  			});
      			}else{
      				$.messager.alert('提示信息','您没有修改任何数据!','info');
      			}
 		
	}
				
	 function clos(){
		 $('#edit1').window('close');
			}
					
	//对修改功能的校验
	/**
	*
		$(function(){
			$("#zsdmc").blur(function(){
			    var selected = {"zsdmc":$('#zsdmc').val()};
				var zsdmc = $("#zsdmc").val();
				var cArr = zsdmc.match(/[^\x00-\xff]/ig);
				var alength = zsdmc.length+(cArr == null ? 0 : cArr.length);
				if(selected&&alength!=0){
		          if(alength<20){
			     $.ajax({
				 type:"POST",
				 url:"Zsdjy.action",
				 data:selected,
				 cache: false,
				 success:function(data){
							if(data.b == 0){
								$("#Error1").empty().append("<font color='red' size='2px'></font>");
								$.messager.alert('提示信息','此记录已存在!!!','info',function(){
							       });
							}else{
							  
							}
						}
			});
		}else{$('#Error1').empty().append("<font color='red' size='2px'></font>");
		$.messager.alert('提示信息','长度不能大于20！','info',function(){
							       });}
		}else{
			$('#Error1').empty().append("<font color='red' size='2px'></font>");
			$.messager.alert('提示信息','不能为空！','info',function(){
							       });
			}
			});	
		});
		*/
		
		//显示详细信息
		function show(rowData){
			//var selected = $('#test_tables').datagrid('getSelected');   
			if (rowData){  
				 $.ajax({
	                 type:"POST",
	                 url:"Show.action",
	                 data:"zsdbh="+rowData.zsdbh,
	                 success:function(data){	
					 $('#show').window('open');
					 document.getElementById("show1").style.visibility="visible";
					 $('#zsdbh2').val(rowData.int3);
					 $('#kcmc2').val(rowData.kcmc);
					 $('#CName2').val(rowData.str4);
					 $('#zmc2').val(rowData.str3);
					 $('#zsdmc2').val(rowData.str1);
					 $('#zsdms2').val(rowData.str2);
					 $('#zsdkey2').val(rowData.str5);
					 $('#sfzd2').val(rowData.int4);
					 $('#sfdn2').val(rowData.int5);	 	
	                }
	             });
			}else{
			    $.messager.alert('提示','请选择一行数据','error');
			 }
		}	
	    
		   		//删除功能
		   			function deleteRow(index){   
		   					 var selected = $('#test_tables').datagrid('getSelections');
							
							if(selected.length!=0){
	   					    	$.messager.confirm('提示','确认删除么?',function(id){
		   					  if(id){
		   					   for(var i=0;i<selected.length;i++){
		   						    	var single = selected[i];
		   						     	$.ajax({
		   						          type:"POST",
		   						         url:"deleteZsd.action",
		   						           data:"zsdbh="+single.int3,
		   						           success:function(){ 
			   						           $.messager.confirm('确认','所选数据已经被成功删除！',function(r){});
			   						           }
		   						     	});
		   						     	 var index = $('#test_tables').datagrid('getRowIndex', single);     
		   								 $('#test_tables').datagrid('deleteRow', index);
		   								
		   					    	}
		   					   
		   					    }
		   					  });
			   					  
		   					   }else{
		   					    $.messager.alert('提示','请选择一行数据','error');
		   					   }
		   				}
                //知识点关键字查询
                function query(){
	            var queryParams = $('#test_tables').datagrid('options').queryParams;
                var zsdtype =queryParams.zsdtype=$('#type').val();
	            var zsdword=queryParams.zsdword= $('#name').val();
	           
	            if(zsdword != ""){
					$.ajax({
						type:"POST",
						url:'searchZsd1.action',
						data:{"zsdtype":zsdtype,"zsdword":zsdword},
						cache: false,
						success:function(data){
							if(data.a == 1){
								$('#test_tables').datagrid({
						            url:'searchZsd.action'
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

	            }else{
					$.messager.alert('warning','请输入查询数据','warning');
		            }
	   				 }
			 
	    	

	/*    		//资源查询功能
	    		function find(){
	    				var zsd_lx = $("#zsd_lx").find("option:selected").text();
	    				var zsd_mc = $("#zsd_mc").find("option:selected").text();
	    				alert(zsd_mc);
	    				if(zsd_lx =="填空题"){zsd_lx = "tk"
	    					}else if(zsd_lx =="选择题"){zsd_lx = "xz"
	    						}else if(zsd_lx =="判断题"){zsd_lx = "pd"
	    							}else if(zsd_lx =="操作题"){zsd_lx = "czt"
	    								}else if(zsd_lx =="多媒体题"){zsd_lx = "dmt"
	    									}
	    				var selected = {"zsd_lx":zsd_lx,"zsd_mc":zsd_mc};
	    				if(selected){
	    					$.ajax({
	    						type:"POST",
	    						url:"zycxtz.action",
	    						data:selected,
	    						success:function(){
	    						window.location.href='listzycx.action';
	    						}
	    					});
	    					
	    				}else{
	    					$.messager.alert('warning','填入数据不能为空','warning')	;
	    				};
	    				}
*/
function find(){
	var zsd_lx = $("#zsd_lx").find("option:selected").text();
	var zsd_mc = $("#zsd_mc").find("option:selected").text();
	var queryParams = $('#test_tables').datagrid('options').queryParams;
	if(zsd_lx =="填空题"){
		zsd_lx = "Tk";
		queryParams.zsdtype=zsd_lx;
	    queryParams.zsdword= zsd_mc;
	    $('#tkt').window('open');
		$('#tk').datagrid({
            url:'getsource.action',
            loadMsg:'加载数据,请等待...'
        });
		$('#find').window('close');
    	$('#tk').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
			 });
	}
	if(zsd_lx =="选择题"){
		zsd_lx = "Xz";
		queryParams.zsdtype=zsd_lx;
	    queryParams.zsdword= zsd_mc;
	    $('#xzt').window('open');
		$('#xz').datagrid({
            url:'getsource.action',
            loadMsg:'加载数据,请等待...'
        });
		$('#find').window('close');
    	$('#xz').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
			 });
	}
	if(zsd_lx =="判断题"){
		zsd_lx = "Pd";
		queryParams.zsdtype=zsd_lx;
	    queryParams.zsdword= zsd_mc;
	    $('#pdt').window('open');
		$('#pd').datagrid({
            url:'getsource.action',
            loadMsg:'加载数据,请等待...'
        });
		$('#find').window('close');
    	$('#pd').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
			 });
	}
	if(zsd_lx =="操作题"){
		zsd_lx = "Czt";
		queryParams.zsdtype=zsd_lx;
	    queryParams.zsdword= zsd_mc;
	    $('#cztt').window('open');
		$('#czt').datagrid({
            url:'getsource.action',
            loadMsg:'加载数据,请等待...'
        });
		$('#find').window('close');
    	$('#czt').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
			 });
	}
}
		function closefindwindow(){
			$('#find').window('close');
			}

		
		function gettx(){
			document.getElementById('zsd_lx').options.length = 0;
			var zsd_mc = $('#zsd_mc').find("option:selected").val();
			$.ajax({
			    url:"gettx",
			    type:"POST",
			    dataType:"json",
			    data:{"zsdmc":zsd_mc},
			    success:function(data){
			    	$.each(data.rows,function(n,value){
						 $("#zsd_lx").append("<option value=\""+value+"\">"+value+"</option>");
					 });
			    }
			   });
		}

	</script>
	</head>
	
	<body class="easyui-layout"  style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
	
		<div region="center"><table id="test_tables" fit="true"></table></div>
			   <div align="center" style="visibility:hidden;" id="add1">
			    <div id="add" icon="icon-add" title="添加知识点信息" closed="true" maximizable="false" minimizable="false" collapsible="false" style="padding:5px;width:400px;height:300px;">
						 <s:form id="insert">
					    	<table align="center">
						    	
								<tr>
									<td> 
									课程名称: 
									</td>
									<td>
										<select id="kcmc"  class="easyui-combobox"  style="width:200px;"   	></select>
									</td>
								</tr>
								<tr>
									<td> 
									章名称: 
									</td>
									<td>
									   	<select id="zmccao"  class="easyui-combobox"   style="width:200px;"   	></select>
									</td>
								</tr>	
								<tr>
									<td> 
									节名称: 
									</td>	
									<td>
									   <select id="jmccao"  class="easyui-combobox"  panelHeight="auto" style="width:200px;"   	></select>
									</td>
								</tr>			
								<tr>
						 			<td> 
									知识点名称:
									</td>
									<td>
										<input type="text" id="zsdmc1" />
									</td>
									<td height="30" class="top_hui_text"><span class="login_txt" id="Error"></span></td>
								</tr>					
								<tr>
						 			<td> 
									知识点描述:
									</td>
									<td>
										<input type="text" id="zsdms1" />
									</td>
								</tr>	
								<tr>
						 			<td> 
									关键词:
									</td>
									<td>
										<input type="text" id="zsdkey1" />
									</td>
								</tr>									
								<tr>
						 			<td> 
									是否重点:
									</td>
									<td>
										<input type="text" id="sfzd1" />请输入0或1
									</td>
								</tr>					
								<tr>
						 			<td> 
									是否难点:
									</td>
									<td>
										<input type="text" id="sfdn1" />请输入0或1
									</td>
								</tr>					
								
							</table>
					</s:form>
			   </div>	</div>
			   <div align="center" style="visibility:hidden;" id="edit11">
			   <div id="edit1" class="easyui-window" icon="icon-edit" title="修改知识点信息" closed="true" maximizable="false" minimizable="false" collapsible="false" style="padding:5px;width:400px;height:300px;">
					<s:form id="update1">
						<table align="center"> 
								<tr>
						 			<td> 
									知识点编号:
									</td>
									<td>
										<input type="text" id="zsdbh" readonly="readonly"/>
									</td>
								</tr>					
								
								<tr>
						 			<td> 
									课程名称:
									</td>
									<td>
										<input type="text" id="TCName" readonly="readonly"/>
									</td>
								</tr>					
								<tr>
									<td> 
									章名称:
									</td>
									<td> 
										 							
										<input id="CName" type="text" readonly="readonly"/>
									</td>
								</tr>				
								<tr>
									<td> 
									节名称: 
									</td>
									<td>
															
										<input id="zmc" type="text" readonly="readonly"/>
									</td>
								</tr>				
								<tr>
						 			<td> 
									知识点名称:
									</td>
									<td>
										<input type="text" id="zsdmc" />
									</td>
									<td height="30" class="top_hui_text"><span class="login_txt" id="Error1"></span></td>
								</tr>					
								<tr>
						 			<td> 
									知识点描述:
									</td>
									<td>
										<input type="textarea" id="zsdms" />
									</td>
								</tr>		
								<tr>
						 			<td> 
									关键词: 
									</td>
									<td>
										<input type="text" id="zsdkey" />
									</td>
								</tr>		
								<tr>
						 			<td> 
									是否重点:
									</td>
									<td>
										<input type="text" id="sfzd" />请输入0或1
									</td>
								</tr>					
								<tr>
						 			<td> 
									是否难点:
									</td>
									<td>
										<input type="text" id="sfdn" />请输入0或1
									</td>
								</tr>					
								
								<tr>
						<td colspan="3" align="right">
						<a id="next" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="edit1()">下一条</a>
						<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="edit1()">修改</a>
						<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="clos()">取消</a>
					</td> 
					</tr>
						</table>
					</s:form>			
				</div>
				</div>
	<div align="center" style="visibility:hidden;" id="query1">
	<div id="query" class="easyui-window" title="查询知识点详细信息" style="padding: 10px;width: 400px;height:100;"
    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
        <div>
        	<form id="chaxun">
	            <table>
	                <tr>
	                     
	                       <td>
	                        <select name="select" id="type">
	                             
	                             <option value="zsdmc" >知识点名称</option>      
	                             <!-- <option value="zsdkey">关键词</option>	   -->                      
	                        </select> 
	                        </td>
	                  
	                    <td><input type="text" name="id" id="name"  required="true"></td>
	                    <td><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0);" onclick="query()">查询</a></td>
	                </tr>
	            </table>
            </form>
        </div>
    </div>
    </div>
    <div align="center" style="visibility:hidden;" id="show1">
    <div id="show" class="easyui-window" title="详细信息" style="padding: 5px;width: 350;height: 215;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="false">
     <form>
			<table align="center" style="font-size: 13px;">
				<tr>
					<td width="35%">知识点编号：</td>
					<td><input type="text" id="zsdbh2" readonly="true"/></td>
				</tr>
				<tr>
					<td>知识点名称：</td>
					<td><input type="text" id="zsdmc2" readonly="true"/></td>
				</tr>
				<tr>
						<td> 课程名称:</td>
							<td>
								<input id="kcmc2" type="text" readonly="true"/>
							</td>
				</tr>
				<tr>
						<td> 章名称:</td>
							<td>
								<input id="CName2" type="text" readonly="true"/>
							</td>
				</tr>
				<tr>
						<td> 节名称:</td>
							<td>
								<input id="zmc2" type="text" readonly="true"/>
							</td>
				</tr>
				<tr>
					<td>知识点描述：</td>
					<td width="65%"><textarea rows="5" cols="20" id="zsdms2" readonly="true"></textarea></td>
				</tr>
			</table>
			</form>
    </div>
    </div>
    <!-- jqueryeasyui实现的资源查询界面 -->
    <div align="center" style="visibility:hidden;" id="find1">
	   <div id="find" closed="true" class="easyui-window" title="查询资源" iconCls="icon-search" 
	   style="width:450px;height:300px;padding:5px;background: #fafafa;" minimizable="false" maximizable="false">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
			<form id="findform">
				<table>
					<tr>
						<td width="20%">
							知识点名称：
						</td>
						<td width="80%"> 
							<s:select id="zsd_mc" list="zsdb" listKey="zsdmc" listValue="zsdmc" name="zsd.zsdmc" onChange="gettx()">
							</s:select>
						</td>
						<td width="20%"><span id="zsd_mc"></span>
						</td>
						
					</tr>						
					<tr>
						<td width="20%">
							知识点类型：
						</td>
						<td width="80%"> 
							<s:select id="zsd_lx" list="{}">
							</s:select>
						</td>
						<td width="20%"><span id="zsd_lx"></span>
						</td>
						
					</tr>
				</table>
			</form>					
			</div>
			<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="find()">查询</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closefindwindow()">取消</a>
			</div>
		</div>
	</div>
	</div>
	<div id="xzt" class="easyui-window" title="选择题资源" iconCls="icon-search" fit="true"
	minimizable="false" maximizable="false"  closed="true">
	     <table id="xz" fit="true"> 
	    </table>
	</div>
	<div id="tkt" closed="true" class="easyui-window" title="填空题资源" iconCls="icon-search" fit="true"
	    minimizable="false" maximizable="false">
	    <table id="tk" fit="true">
	    </table>
	</div>
	
	<div id="pdt" closed="true" class="easyui-window" title="判断题资源" iconCls="icon-search" fit="true"
	    minimizable="false" maximizable="false">
	    <table id="pd" fit="true">
	    </table>
	</div>
	<div id="cztt" closed="true" class="easyui-window" title="操作题资源" iconCls="icon-search" fit="true"
	    minimizable="false" maximizable="false">
	    <table id="czt" fit="true">
	    </table>
	</div> 
		</body>		
	</html>			