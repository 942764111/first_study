var teaname="";
var dyz=0;
var n1="";
var t=0;
var validator1;
var listcc=new Array();
  $(function(){
	   
		$('#test').datagrid({
			title:'教学计划管理',
			iconCls:'icon-save',
			width:900,
			height:465,
			pageSize:15,
			pageList:[15,20,30,40,50],
			nowrap: true,
			striped: true,
			url:'jxjh_tj_json',
			loadMsg:'处理中，请等待...',
			singleSelect:true,
			sortName:'str2',
			sortOrder:'desc',
			remoteSort: false,
			fit:true,
			fitColumns:true,
			onDblClickRow:function(rowIndex, rowData){
			   location.href='jxjh.action?kch='+rowData.int2+'&xq='+rowData.str2+'&jsbh='+rowData.str1+'&tip=bht_tea'+'&jhid='+rowData.int3+'&courseName='+rowData.str4;
			   window.event.returnValue=false;
			},
			
			columns:[[
               
               {title:'课程名称',field:'str4',width:200},
               //{title:'章名称',field:'str6',width:100},
					{title:'学期',field:'str2',width:200,sortable:true,
		            	   formatter:function(value,rec){
							var str=rec.str2;
		                   if(str==null||str==""){
		                	     str="空";
		                    }else{
		                    	var nian=str.substring(0, 4);
		                    	nian=Number(nian);
		    					var nianD=nian+1;
		    					nianD=nianD.toString();
		    					nian=nian.toString();
		    					var xueqi=str.substring(4);
		    					var str=nian+"-"+nianD+"学年之第"+xueqi+"学期";
		                            
		                    }
		                   
		                   return '<a title="'+value+'">'+str+'</a>';
		                      }
					},
			        {title:'教师姓名',field:'str5',width:200},
			        {title:'教学次数',field:'int1',width:100,
			                formatter:function(value,rec){
			        	          if(rec.str2==n1&&rec.int2==dyz&&rec.str1==teaname){
			        	        	  return 0;
			        	          }else{
			        	        	  return value;
			        	          }
			                }	
			        }
			]],
			pagination:true,                             //显示分页
			rownumbers:true,                             //显示行号
			toolbar:[{
				text:'打开',
				iconCls:'icon-redo',
				handler:function(){
						var selections = $('#test').datagrid('getSelections');
						if(selections.length==1){
							location.href='jxjh.action?kch='+selections[0].int2+'&xq='+selections[0].str2+'&jsbh='+selections[0].str1+'&tip=bht_tea'+
							'&jhid='+selections[0].int3+'&courseName='+selections[0].str4;
							// 在此处加上以下代码既可    
							window.event.returnValue = false;
							
						}else{
						    $.messager.alert('提示','请选择一行数据','warning');
						 }
				
			     }
			},'-',{
				id:'tjxdxjh',
				text:'添加新的教学计划',
				iconCls:'icon-add',
				handler:function(){
						$('#add').window('open');
						document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
						
				
			     }
			},'-',{
				id:'jxjhfz',
				text:'使用该教学计划',
				iconCls:'icon-undo',
				handler:function(){
						
						var selections = $('#test').datagrid('getSelections');
						if(selections.length==1){
							$('#addjxjhfz').window('open');
							document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
							
							$('#fzkcmc').val(selections[0].str4);
							var nian=selections[0].str2.substring(0, 4);
							
	                    	nian=Number(nian);
	    					var nianD=nian+1;
	    					nianD=nianD.toString();
	    					nian=nian.toString();
	    					var xueqi=selections[0].str2.substring(4);
	    					
							$('#n4').val(nian);
							$('#n5').val(nianD);
							$('#n6').val(xueqi);
							$('#fzjxcs').val(selections[0].int1);
							
						}else{
						    $.messager.alert('提示','请选择一行数据','warning');
						 }
				
			     }
			},'-',{
				
				text:'删除教学计划',
				iconCls:'icon-cancel',
				handler:function(){
						var selections = $('#test').datagrid('getSelections');
						if(selections.length==1){
							$.messager.confirm('提示', '您确定删除该教学计划吗?', function(r){
								if (r){
									$.ajax({
									    url:"scjxjhAction",
									    type:"post",
									    dataType:"json",
									    data:{"jhid":selections[0].int3},
									    success:function(json){
													if(json.tip=="ok!"){
														var index = $('#test').datagrid('getRowIndex', selections[0]);
														$('#test').datagrid('deleteRow', index);
														$.messager.alert('提示','删除教学计划成功！','info');
													}		    	
									        }
									   });
								}
							});
							
						}else{
						    $.messager.alert('提示','请选择一行数据','warning');
						 }
										
			     }
			},'-',{
				text:'  课程名称：<select id="kcm" onChange="ld();" style="width:150px;"><option value=\"-1\">-请选择-</option></select> 学期：<select id="xq" style="width:175px;"><option value=\"-1\">-请选择-</option></select> <input type="button" onclick="chaxun();" value="查询"/>',
				iconCls:'icon-search',
				handler:function(){
										
			     }
			},'-']
		});
		$('#test').datagrid('getPager').pagination({
			 displayMsg:'(刷新可查看表格中查询之前的数据)   当前显示从{from}到{to} 共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
			 
		 });
		
		 //为页面产生级联下拉菜单
		 
		$.ajax({
		    url:"kcmAction",
		    type:"post",
		    dataType:"json",
		    success:function(json){
					 $.each(json.list,function(n,value){
						  $("#kcm").append("<option value=\""+value+"\">"+value+"</option>");
							 
					  })
	            }
		   });
		
		
		
		//对学期的校验
		var validator=$("#form").validate({
			
			rules: {
			    "d1n":{
	                number:true,
	                digits:true,
			        required:true,
			        minlength:4,
			        maxlength:4
			        
			    },
			    
			    "d2n": {
					required: true,
					number:true,
					minlength:4,
					maxlength:4
					
				},
				"d3n": {
					required: true,
					number:true,
					minlength:1,
					maxlength:1
					
				}
				
			},	
			messages: {
				
				"d1n":{
			        required: " (空)",
			        digits:"(整数)",
					number:" (数字)",
					minlength: " (4位)",
					maxlength:" (4位)"
			    },
				
				"d2n": {
					required: " (空)",
					number:" (数字)",
					minlength: " (4位)",
					maxlength:" (4位)"
				} ,
				"d3n":{
					required: " (空)",
					number:" (数字)",
					minlength: " (1位)",
					maxlength:" (1位)"
				}
			 }	
		});
		//对课时的校验
		 validator1=$("#addkc").validate({
			
			rules: {
			    "ks":{
	                number:true,
			        digits:true,
			        maxlength:10

			    }
				
			},	
			messages: {
				
				"ks":{
			        
					number:" (数字)",
					digits:"(整数)",
					maxlength:"(最多10位)"

			    }
			 }	
		});
		$('#n1').blur(function(){
			var n1=$('#n1').val();
			if(n1.length==4){
				var n1=Number(n1)+1;
				
					$('#n2').val(n1);
				
				
			}
			
		});
		$('#n4').blur(function(){
			var n1=$('#n4').val();
			if(n1.length==4){
				var n1=Number(n1)+1;
				$('#n5').val(n1);
			
			}else{
				alert("时间不准确！");
				$('#n5').val('');
			}
			
		});

		
		var lastIndex;
		
		$('#test2').datagrid({
			columns:[[
				      	{field:'status',title:'标记',width:60,align:"center"},
		            	{field:'addJhmc',title:'内容概述',width:200,editor:{type:'validatebox',options:{required:true}}},
		            	{field:'addXsh',title:'课时',width:100,editor:{type:'numberbox',options:{precision:0,required:true}}},
		            	{field:'dyz',title:'对应章',width:200,editor:{type:'combobox',options:{valueField:'zbh',textField:'CName',data:listcc,required:true}}}              
            ]],
			toolbar:[{
				id:'addyckk',
				text:'添加五次课',
				iconCls:'icon-add',
				handler:function(){
					for(var i=0;i<5;i++){
				    	tjyck();
				    }
				}
			},'-',{
				id:'scyckk',
				text:'删除一次课',
				iconCls:'icon-remove',
				handler:function(){
					var row = $('#test2').datagrid('getSelected');
				
					if (row){
						var index = $('#test2').datagrid('getRowIndex', row);
						$('#test2').datagrid('deleteRow', index);
					}
				}
			}],
			singleSelect:true,
			onClickRow:function(rowIndex,rowData){
			  
				if (lastIndex != rowIndex){
					$('#test2').datagrid('endEdit', lastIndex);
					$('#test2').datagrid('beginEdit', rowIndex);
				}
				lastIndex = rowIndex;
			}
		});
		$('#kcname').change(function(){////////////////////////////////////////////////////////
			var kcname=$('#kcname').val();
			if(kcname!="-1"){
				ldkcm();
				$('#addyckk').linkbutton('enable');
				$('#scyckk').linkbutton('enable');
			}else{
				$('#test2').datagrid('rejectChanges');
				$('#addyckk').linkbutton('disable');
				$('#scyckk').linkbutton('disable');
			}
		});
      $("#submit").click(function (){
    	    
    		var kcname=$('#kcname').val();
    		
    		if(kcname=="-1"){
    			$.messager.alert("警告","课程名称，学期都不可为空！","warning");
    			}else{
    	               if(validator.form()){
    	            	    n1=$('#n1').val();
    	            	   var n3=$('#n3').val();
    	            	   n1=n1.toString()+n3.toString();
    	            	   //提交
    	            	   if(true){
    	            		   
    	            		   
    	            		   $('#test2').datagrid('acceptChanges');//只有accept后才可value.addJhmc才管用//---------开始------------
          					    $('#test2').datagrid('selectAll');
          					    var row=$('#test2').datagrid('getSelections');
          					    
          					    var arr=new Array();
          					    $.each(row,function(n,value){                                          //------------test2中没有了校验提示时,才可被accept.这时的value.addJhmc才有定义即代码可以顺利进行否则就停在了此处！!---------------
          					    	
          					    	arr.push(value.addJhmc);
          					    	if(value.addXsh==""){
          					    		arr.push("0");
          					    	}else{
          					    		arr.push(value.addXsh);
          					    	}
          					    	arr.push(value.dyz);                                               //------------结束--------------
          					    	
          					    });
    	            		   
    	            	   
    	            	   $.ajax({
    	            		    url:"AddJxjh",
    	            		    type:"post",
    	            		    dataType:"json",
    	            		    data:{"kname":kcname,"xq":n1,"tip":"jxjh_tj"},
    	            		    success:function(json){
    	            		    	if(json.tip1=="cuowu"){
    	            		    		
    	            		    		$.messager.alert("提示","此计划已存在！","info");
    	            		    	}else{
    	            		    		
    	            		    		if(row.length==0){
    	          					    	
    	          					    	close2();
    	          					    	$('#test').datagrid('reload');
    	          					    }
    	            		    		
   	            					  if(arr.length>0){
   	            						 jQuery.ajaxSettings.traditional = true;   //关键
                                         $.ajax({
   	   	            		               type:"POST",
   	   	            		               url:"blukaddyck",
   	   	            		               dataType:"json",
   	   	            		               data:{"arr":arr},      //.java 文件中用String funId接受，序列化时把数组变为了逗号隔开的字符串了！！！
   	   	            		               success:function(json){
   	   	            		            	   
   	   	            		            	    close1();
   	   	            		            	$('#test2').datagrid('selectAll');                         //-----为了提交后清空test2--------
   	   	          					        var rs=$('#test2').datagrid('getSelections');
   	   	          					        //alert("rs:"+rs.length);
   	   	            		            	$.each(rs,function(n,value){                                          
   	   	            		            	  var index = $('#test2').datagrid('getRowIndex', value);
   	   	      						          $('#test2').datagrid('deleteRow', index);                                   
   	   	          					    	
   	   	          					        });
   	   	            		                $('#test2').datagrid('acceptChanges');                    //---------------------------------

   	    	            					   $('#test').datagrid('reload');
   	   	            		                  
   	   	            		                }
   	   	            		               });
   	            					  }
   	            					  

   	            					   
    	            		    	}
    	            					 
    	            	            }
    	            		   });
    	            	   }
    	               }
    				}
    	  
      });
    
	});
//添加教学计划时，添加多次课
	$(function(){
		$('#addyckk').linkbutton('disable');
		$('#scyckk').linkbutton('disable');
	});

  
function ld(){
	var kcm=$('#kcm').val();
	$("#xq").children().eq(0).siblings().remove();
	
	if(kcm!="-1"){
		$.ajax({
		    url:"xqAction",
		    type:"post",
		    dataType:"json",
		    data:{"kcm":kcm},
		    success:function(json){
					 $.each(json.list,function(n,value){
						 
						  $("#xq").append("<option value=\""+value+"\">"+value+"</option>");
							 
					  })
	            }
		   });
	}
	
}
function chaxun(){
	var kcm=$('#kcm').val();
	var xq=$('#xq').val();
	if(kcm=="-1"||xq=="-1"){
       $.messager.alert("提示","请选择课程名称与学期","info");  
	}else{
		$.ajax({
		    url:"queryJxjhTj",
		    type:"post",
		    dataType:"json",
		    data:{"kcm":kcm,"xq":xq},
		    success:function(json){
		    	   $('#test').datagrid('reload');
	            }
		   });

	}
	
}
function ldkcm(){//对应章的联动
	var kcm=$('#kcname').val();
	
	$.ajax({
	    url:"dyzAction",
	    type:"post",
	    dataType:"json",
	    data:{"kcm":kcm},
	    success:function(json){
	    	//listcc=json.listcc;
	    	$.each(json.listcc,function(n,value){
	    		
	    		listcc.push(value);
	    	})

	        }
	   });
}

function close1(){
	 
	 var obj = document.getElementById("kcname");   
	obj.selectedIndex = 0;
	
	//$("#dyz").children().eq(0).siblings().remove();
	 $('#n1').val('');$('#n2').val('');$('#n3').val('');
	 $('#test2').datagrid('rejectChanges');
	 $('#addyckk').linkbutton('disable');
	 $('#scyckk').linkbutton('disable');
	$('#add').window('close');
}
function close2(){
	 
	 var obj = document.getElementById("kcname");   
	obj.selectedIndex = 0;
	 $('#n1').val('');$('#n2').val('');$('#n3').val('');
	 
	 $('#addyckk').linkbutton('disable');
	 $('#scyckk').linkbutton('disable');
	$('#add').window('close');
}
function tjyck(){
	$('#test2').datagrid('endEdit', lastIndex);
	 var count=$('#test2').datagrid('getRows').length+1;
		$('#test2').datagrid('appendRow',{
			addJhmc:'',
			addXsh:'0',
			dyz:'',
			status:'P'
		});
		var lastIndex = $('#test2').datagrid('getRows').length-1;
		//alert(lastIndex);
		$('#test2').datagrid('beginEdit', lastIndex);
}
function tjfzjxjh(){//提交复制教学计划
	var n1=$('#n4').val();
	if(n1.length==4){
		var selections = $('#test').datagrid('getSelections');
		var nian=$('#n4').val();
		var xueqi=$('#n6').val();
		nian=nian+xueqi;
		$.ajax({
		    url:"fzjxjhAction",
		    type:"post",
		    dataType:"json",
		    data:{"kch":selections[0].int2,"xq":nian,"jhid":selections[0].int3,"jsbh":selections[0].str1,"xq2":selections[0].str2},
		    success:function(json){
			    	if(json.tip=="no!"){
			    		$.messager.alert('提示','该教学计划已存在！','warning');
			    	}else{
			    		$.messager.alert('提示','成功使用该教学计划！','info');
				    	closefzjxjh();
			    	}
		    	
		        }
		   });
	}else{
		alert("时间不准确！");
		
	}
	
}
function closefzjxjh(){
	$('#addjxjhfz').window('close');
}



