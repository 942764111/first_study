        var g=0;                  //修改的变量
		    var t=true;               //添加的变量
		    var arrays1=new Array();  //修改的变量
		    var arrays2=new Array();
		    var validator;
		    var flag3=true;//模块修改的变量
    $(function(){
    	    
			$('#test').datagrid({
				title:'模块管理（查询显示数据！）',
				iconCls:'icon-save',
				width:900,
				height:550,
				pageSize:15,
				pageList:[50,40,30,20,15],
				nowrap: true,
				striped: true,
				url:'listModules',
				loadMsg:'处理中，请等待...',
				fit:true,             //
				fitColumns:true,
				frozenColumns:[[
	                {field:'ck',checkbox:true},
	                {title:'分类名称',field:'str1',width:100,sortable:true}
				]],
				columns:[[
				   
			    {field:'str2',title:'模块名称',width:100},//80
					{field:'str4',title:'描述',width:220},//120
					{field:'int1',title:'模块排序',width:60},//60
					{field:'str3',title:'图标',width:100,align:'center',
					    formatter:function(value,rec){
						  return '<img src="img/index/picture/tb.gif"/>';
						}
					}
				]],
				pagination:true,                             //显示分页
				rownumbers:true,                             //显示行号
				toolbar:[{
					
					text:'查询',
					iconCls:'icon-search',
					handler:selectm
				},'-',{
					
					text:'添加',
					iconCls:'icon-add',
					handler:addm
				},'-',{
					
					text:'修改',
					iconCls:'icon-edit',
					handler:editm
				},'-',{
					
					text:'删除',
					iconCls:'icon-cancel',
					handler:deleteRow
				},'-',{
					
					text:'维护功能',
					iconCls:'icon-redo',
					handler:weihum
				}]
			});
			$('#test').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to} 共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
			 });
		
		});
        //====================================
        function selectm(){
        	$('#w').form('clear');
        	
			$('#select').dialog('open');
			
			document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用

			//alert($('#select').attr("style"));
        } 
        function addm(){
		    
			$('#add').dialog('open');
			document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
			$('#jy').val("");
			//鼠标点击添加时，动态增加option
			$("#mk").children().eq(0).siblings().remove();
			websites1.length=0;
			
			$.ajax({
			    url:"mk",
			    type:"post",
			    dataType:"json",
			    success:function (json){
						$.each(json.mokuai,function(n,value){
							 
								 $("#mk").append("<option value=\""+value+"\">"+value+"</option>");
								 websites1.push(value);
						  });
			         }
			   });
			//校验模块是否重复
			$('#jy').blur(function(){
			   var jy=$('#jy').val();
			   var flag=false;
			   t=true;
			  
			   if(jy.length==0){
				  // t=false;
				   //$.messager.alert('My Title','模块名不可为空！','warning');
			   }else{
				   $.each(websites1,function(n,value){
				       if(jy==value){
				           flag=true;
				        }
				    });
				    if(flag){
				       
				       $.messager.alert('警告','模块名已经存在！','warning');
				       t=false;
				    }
			   }
			   
			      
			});
			
			$('#jy1').change(function(){//添加更多分类
			   var jy1=$('#jy1').val();
			   if(jy1=="tjgd"){
			       $('#add1').window('open');
			       document.getElementById("ggggg5").style.visibility="visible";//注意：用$()方式不好用
			   }
			
			});
			validator=$("#a").validate({
				rules: {
				    "module.moduleclass.mclassname":{
				        required:true
				    },
					"module.mname": {
						required: true
					},
					"module.molderid":{
					   required:true,
					   number:true
					}
					
				},	
				messages: {
					
					"module.moduleclass.mclassname":{
				        required:" 必须选择分类！"
				    },
					
					"module.mname": {
						required: " 不可为空！"
					},
					
					"module.molderid":{
					    required:" 不可为空！",
					    number:" 必须是数字"
					}
				 }	
			})
			
		
		}
        function editm(){
			   var selected = $('#test').datagrid('getSelections');  //获取所有选中行的数据
		        if(selected.length!=0){
                 $.messager.confirm('提示', '确认修改吗?', function(r){
										if (r){
										     $('#edit').dialog('open');
										     document.getElementById("ggggg3").style.visibility="visible";//注意：用$()方式不好用
										     g=0;arrays1.length=0;
											 $("#f").val(selected[0].str1);
											 $("#m").val(selected[0].str2);
											 $("#p").val(selected[0].int1);
											 $("#ms").val(selected[0].str4);
											 
											 if(selected.length==1){
												 $("#xiayige").html('<font>完成</font>');
											 }else{
												 $("#xiayige").html('<font>下一个</font>');
											 }
											 for(i=1;i<selected.length;i++){
												    arrays1.push(selected[i]);
											 }
											 
											 
										}
					})//
			     }else{$.messager.alert('提示','选择您将修改的对象！','info');}
			}
        function weihum(){
			
			$('#weihu').dialog('open');
			document.getElementById("ggggg4").style.visibility="visible";//注意：用$()方式不好用
			//鼠标点击"维护功能"时，动态增加option
			$("#weihu2").children().eq(0).siblings().remove();
			$("#func").children().eq(0).siblings().remove();
			$.ajax({
			    url:"mk",
			    type:"post",
			    dataType:"json",
			    success:function (json){
						$.each(json.mokuai,function(n,value){
							 
								 $("#weihu2").append("<option value=\""+value+"\">"+value+"</option>");
								 $("#func").append("<option value=\""+value+"\">"+value+"</option>");
						  });
			         }
			   });
		}
      //====================================
		$(function(){
			
			
			//查询时选择了分类之后自动填充模块
			$("#mclassname1").blur(function(){
				var mclassname1=$("#mclassname1").val();
				jQuery.ajax({
			          type: "post",
				      url:"mkNameld",
				      dataType:"json",
				      data:{"mclassname":mclassname1},
				    success:function (json){
					   		websites.length=0;
					   		$.each(json.mokuai,function(n,value){
                                
					   			websites.push(value);
                             });
					   		$("#website").flushCache(); //清空website处的缓存
					   		
					   		$("#website").autocomplete(websites,{
								minChars: 0,
								max: 120,
								//cacheLength :1,
								matchSubset :false,
								autoFill: false,
								multiple :false,//控制是否可多次输入
								mustMatch: false,
								multipleSeparator :"",
								matchContains: true,
								scrollHeight: 220,
								formatItem: function(data, i, total) {
									return "<I>"+data[0]+"</I>";
								},
								formatMatch: function(data, i, total) {
									return data[0];
								},
								formatResult: function(data) {
									return data[0];
								}
							})
			         }
				   })// 
			});
		     //====================================	
			
			//====================================	
			$('#select').dialog({
					buttons:[{
						text:'确定',
						iconCls:'icon-ok',
						handler:function(){
							 
								   var mclassname1=$("#mclassname1").val();
								   var mname1=$("#website").val();
								   jQuery.ajax({
								      url:"listModules.action",
								      dataType:"json",
								      data:{"mclassname":mclassname1,"mname":mname1},
								      cache:false,
				                      success:function (json){
				                         if(json.total!=0){
				                             
				                             $('#select').dialog('close');
				                             $('#test').datagrid('reload');
				                          }else{
				                            $.messager.alert('提示','查询结果为空！','info');
				                          }
				                      }
								   })//
							 
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$('#select').dialog('close');
						}
					}]
				});
			
			$('#add').dialog({
					buttons:[{
						text:'确定',
						iconCls:'icon-ok',
						handler:function(){
						     var fl=$('#jy1').val();
						     
						     if(t){
						    	 if(validator.form()){
						    		 if(fl!=-1){
									      jQuery.ajax({
									          type: "post",
										      url:"addModule.action",
										      data:$('#a').serialize(), 
										      cache:false,
						                      success:function (json){
						                         if(json.tip=="true"){
								                         $('#add').dialog('close');
							                             $.messager.alert('提示','添加成功！','info',function(){
							                                 
							                            	 $('#test').datagrid('reload');
							                             });
							                            
						                          }   
						                         				                      
						                       }
										  })//
									  }
						    	 }
							}  
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$('#add').dialog('close');
						}
					}]
				});
			
			
	  //=======================维护功能====================	
		    var arrays3=new Array();
	        var arrays4=new Array(); 
	        var arrays5=new Array();
	        var arrays6=new Array();
			  $('#datagrid1').datagrid({
				title:'模块已有功能',
				
				width:"100%",
				height:304,
				nowrap: false,
				striped: true,
				url:'mfunction',
				loadMsg:'处理中，请等待...',
				frozenColumns:[[
	                {field:'ck',checkbox:true}
	            ]],
			    columns:[[
				   
			    {field:'str1',title:'   功能名称',width:230}
				
				]],
				
				rownumbers:true,
				toolbar:['-',{
					text:'提交修改',
					iconCls:'icon-ok',
					handler:function(){
					   if(arrays5.length==0&&arrays6.length){
						   $.messager.alert('提示','您未作任何操作！','info');
					   }else{
					     var arrays7=new Array();
					     var arrays8=new Array();
					    for(i=0;i<arrays5.length;i++){
				            arrays3.push(arrays5[i]);                     
                        }
                        
                        for(var j=0;j<arrays3.length;j++){
                            var flag=true;
				            for(var k=0;k<arrays4.length;k++){
				               if(arrays3[j]==arrays4[k]) 
				                  flag=false;
				            
				            } 
				            if(flag)
				            arrays6.push(arrays3[j]);
				                             
                        }
                       
                       for(var n=0;n<arrays5.length;n++){
                           for(var m=0;m<arrays6.length;m++){
                             if(arrays5[n]==arrays6[m]){
                                arrays7.push(n);
                                arrays8.push(m);
                             }
                           }
                       }
                      
                       $.each(arrays7,function(n3,value3){
                           delete arrays5[value3];
                       });
                       arrays7.length=0;
                      
                       $.each(arrays8,function(n2,value2){
                           delete arrays6[value2];
                       });
                       arrays8.length=0;  
                      
                       arrays5.sort();
                       arrays6.sort();
                       if(arrays5.length!=0||arrays6.length!=0){ 
                    	   
	                       jQuery.ajaxSettings.traditional = true;   //关键
	                       $.post("weiHuAction",{arrays6:arrays6,arrays5:arrays5},function(data,textStatus){
	                    	       
	                               if(data.tip=="add"){
	                        		   $.messager.confirm('完成','成功添加：'+arrays6.toString(),function(r){
	 								      if(r){
	 								         arrays3.length=0;
	 								         arrays4.length=0;
	 								         arrays5.length=0;
	 								         arrays6.length=0;

	 								      }
	 								   })//
	                        	   }else if(data.tip=="del"){
	                        		   $.messager.confirm('完成','已删除：'+arrays5.toString(),function(r){
		 								      if(r){
		 								         arrays3.length=0;
		 								         arrays4.length=0;
		 								         arrays5.length=0;
		 								         arrays6.length=0;

		 								      }
		 								   })//
	                        	   }else if(data.tip=="adddel"){
	                        		   $.messager.confirm('完成','成功添加：'+arrays6.toString()   +';已删除：'+arrays5.toString(),function(r){
	 								      if(r){
	 								         arrays3.length=0;
	 								         arrays4.length=0;
	 								         arrays5.length=0;
	 								         arrays6.length=0;

	 								      }
	 								  })//
	                        	   }
	                           
	                       },"json");
                   
					 }}
					}
					},'-']
			});
			
			$('#datagrid2').datagrid({
				title:'所查询的功能',
				
				width:"99.5%",
				height:200,
				nowrap: false,
				striped: true,
				url:'listfunctions',
				loadMsg:'处理中，请等待...',
				frozenColumns:[[
	                {field:'ck',checkbox:true}
	            ]],
			    columns:[[
				   
			    {field:'str1',title:'   功能名称',width:230}
				
				]],
				
				rownumbers:true
				
			});
			     
				$('#weihucha').click(function(){                                      
					
							      jQuery.ajax({
							          type: "post",
								      url:"listfunctions.action",
								      data:$('#weihu1').serialize(), 
								      cache:false,
				                      success:function (json){
				                         if(json.tip=="true"){
						                       $('#datagrid2').datagrid('reload');  
						                       if(json.rows.length==0){
							                    	 
							                    	  $.messager.alert('提示','该模块没有功能!','info',function(){
												        
												     })//
							                      }
				                          }   
				                         				                      
				                       }
								   })//
					
				});
				
				$('#weihu2').change(function(){
				          arrays6.length=0;
				          arrays5.length=0;
				          arrays3.length=0;
				          arrays4.length=0;
				          var mname=$('#weihu2').val();  
				          jQuery.ajax({
							          type: "post",
								      url:"mfunction.action",
								      dataType:"json",
								      data:{"mname":mname}, 
								      cache:false,
				                      success:function (json){
				                         if(json.tip=="true"){
				                              $.each(json.rows,function(n,value){
				                                
				                                 arrays5.push(value.str1);
				                              });
						                      $('#datagrid1').datagrid('reload');
						                      
						                      if(json.rows.length==0){
						                    	 
						                    	  $.messager.alert('提示','该模块没有功能!','info',function(){
											        
											     })//
						                      }
				                          }   
				                         				                      
				                       }
								   })//     
				}); 
		//================维护的添加和删除============== 
				
				$('#weihuadd').click(function(){
				    var selected = $('#datagrid2').datagrid('getSelections');  //获取所有选中行的数据
			        if(selected.length!=0){
			             for(i=0;i<selected.length;i++){
						    arrays3.push(selected[i].str1);
						    $('#datagrid1').datagrid('appendRow',{
							    str1:selected[i].str1
						    });
						    var index = $('#datagrid2').datagrid('getRowIndex', selected[i]);
							$('#datagrid2').datagrid('deleteRow', index);
							
						 }
			        }
				}); 
				$('#weihudel').click(function(){
				     var selected = $('#datagrid1').datagrid('getSelections');  //获取所有选中行的数据
			        if(selected.length!=0){
			            for(i=0;i<selected.length;i++){
						    arrays4.push(selected[i].str1);
						    var index = $('#datagrid1').datagrid('getRowIndex', selected[i]);
							$('#datagrid1').datagrid('deleteRow', index);
							
						 }
			        }
				})//
		});
		//==========批量修改=============
		
		function update(){
			if(flag3){
				   jQuery.ajax({
					          type: "post",
						      url:"updateModule.action",
						      data:$('#e').serialize(), 
						      cache:false,
		                      success:function (json){
		                         if(json.tip=="true"){
				                       
		                          }   
		                      }
				    });
							
					if(g<arrays1.length){
						 
						 $("#f").val(arrays1[g].str1);
						 $("#m").val(arrays1[g].str2);
						 $("#p").val(arrays1[g].int1);
						 $("#ms").val(arrays1[g].str4);
						 if(g==arrays1.length-1){
							  $("#xiayige").html('<font>完成</font>');
						    }
					}else{
					     $.messager.alert('提示','已修改完成!','info',function(){
					          $('#edit').dialog('close');
					          $("#xiayige").html('<font>下一个</font>');
					          $('#test').datagrid('reload');
					     })//
					     
					  
					}
					g=g+1;
				  }
		  }
		function closexiayige(){
			$('#edit').dialog('close');
		}
		
		//校验模块修改中的“模块排序”
		function xiugai_up()  
		  {  
		    //考虑小键盘上的数字键  
		    if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))) 
		    	{
		    	$("#xg_Shuzi").append("<font color='red' size='2px'>请输入数字</font>");
		    	flag3=false;
		    	}else{
						if(p.value.length>=1&&p.value.length<5){
							flag3=true;
							   
							}else{
								$("#xg_Shuzi").append("<font color='red' size='2px'>长度小于5位</font>");
								   flag3=false; 
								}
							
			    	}  
		  }
		function onlyNum_down()  
		  {  
		    	
		    	$("#xg_Shuzi").empty();
		  }
		function deleteRow(index){
		            var arr=new Array();
		        	var selected = $('#test').datagrid('getSelections');    //获取第一个选中行的数据
		        	if(selected.length!=0){
		        	    $.each(selected,function(n,value){
                           arr.push(value.str2);
                        });
			        	$.messager.confirm('删除','确认删除 "'+arr.toString()+'模块" 吗?',function(d){
			        	
			            	if(d){   
			            	        jQuery.ajaxSettings.traditional = true;   //关键
			            		    $.ajax({
				                      type:"POST",
				                      url:"delModule.action",
				                      dataType:'json',
				                      data:{mnames:arr},
				                      success:function(json){
				                         if(json.tip=="true"){
				                            
				                             $.messager.alert('提示','删除成功！','info',function(){
				                            	 $('#test').datagrid('reload');
				                             })//
				                         }else{
				                        	 $.messager.alert('警告','您必须先删除功能表里 :"'+json.tip+'" 才可以删除该模块；或者把它们指定到其它模块中','info',function(){})//
				                         }
				                      }
				                	})//
				               	
			        		  }
			        	 })//
			        }else{$.messager.alert('提示','选择您将删除的对象！','info');}
		 }
	//=====================模块分类添加===========
				 
		function add1(){
		var selected = {"moduleclass.mclassname":$('#mclassname').val(),"moduleclass.classolder":$('#classolder').val(),"moduleclass.comments":$('#comments').val()};
		$.ajax({
				type:"POST",
				url:"saveModuleclass.action",
				data:selected,
				success:function(){}
				});
		  $('#add1').window('close');
          $.messager.alert('提示','添加分类成功！','info',function(){
                location.href = 'mModules.action';
                window.event.returnValue=false;
                $("#loading").show();	
            })
		}
	   function close1(){
	     $('#add1').window('close');
	     }