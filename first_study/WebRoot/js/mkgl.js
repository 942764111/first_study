        var g=0;                  //�޸ĵı���
		    var t=true;               //��ӵı���
		    var arrays1=new Array();  //�޸ĵı���
		    var arrays2=new Array();
		    var validator;
		    var flag3=true;//ģ���޸ĵı���
    $(function(){
    	    
			$('#test').datagrid({
				title:'ģ�������ѯ��ʾ���ݣ���',
				iconCls:'icon-save',
				width:900,
				height:550,
				pageSize:15,
				pageList:[50,40,30,20,15],
				nowrap: true,
				striped: true,
				url:'listModules',
				loadMsg:'�����У���ȴ�...',
				fit:true,             //
				fitColumns:true,
				frozenColumns:[[
	                {field:'ck',checkbox:true},
	                {title:'��������',field:'str1',width:100,sortable:true}
				]],
				columns:[[
				   
			    {field:'str2',title:'ģ������',width:100},//80
					{field:'str4',title:'����',width:220},//120
					{field:'int1',title:'ģ������',width:60},//60
					{field:'str3',title:'ͼ��',width:100,align:'center',
					    formatter:function(value,rec){
						  return '<img src="img/index/picture/tb.gif"/>';
						}
					}
				]],
				pagination:true,                             //��ʾ��ҳ
				rownumbers:true,                             //��ʾ�к�
				toolbar:[{
					
					text:'��ѯ',
					iconCls:'icon-search',
					handler:selectm
				},'-',{
					
					text:'���',
					iconCls:'icon-add',
					handler:addm
				},'-',{
					
					text:'�޸�',
					iconCls:'icon-edit',
					handler:editm
				},'-',{
					
					text:'ɾ��',
					iconCls:'icon-cancel',
					handler:deleteRow
				},'-',{
					
					text:'ά������',
					iconCls:'icon-redo',
					handler:weihum
				}]
			});
			$('#test').datagrid('getPager').pagination({
				 displayMsg:'��ǰ��ʾ��{from}��{to} ��{total}��¼',
				 beforePageText:'��',
				 afterPageText:'ҳ'
			 });
		
		});
        //====================================
        function selectm(){
        	$('#w').form('clear');
        	
			$('#select').dialog('open');
			
			document.getElementById("ggggg1").style.visibility="visible";//ע�⣺��$()��ʽ������

			//alert($('#select').attr("style"));
        } 
        function addm(){
		    
			$('#add').dialog('open');
			document.getElementById("ggggg2").style.visibility="visible";//ע�⣺��$()��ʽ������
			$('#jy').val("");
			//��������ʱ����̬����option
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
			//У��ģ���Ƿ��ظ�
			$('#jy').blur(function(){
			   var jy=$('#jy').val();
			   var flag=false;
			   t=true;
			  
			   if(jy.length==0){
				  // t=false;
				   //$.messager.alert('My Title','ģ��������Ϊ�գ�','warning');
			   }else{
				   $.each(websites1,function(n,value){
				       if(jy==value){
				           flag=true;
				        }
				    });
				    if(flag){
				       
				       $.messager.alert('����','ģ�����Ѿ����ڣ�','warning');
				       t=false;
				    }
			   }
			   
			      
			});
			
			$('#jy1').change(function(){//��Ӹ������
			   var jy1=$('#jy1').val();
			   if(jy1=="tjgd"){
			       $('#add1').window('open');
			       document.getElementById("ggggg5").style.visibility="visible";//ע�⣺��$()��ʽ������
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
				        required:" ����ѡ����࣡"
				    },
					
					"module.mname": {
						required: " ����Ϊ�գ�"
					},
					
					"module.molderid":{
					    required:" ����Ϊ�գ�",
					    number:" ����������"
					}
				 }	
			})
			
		
		}
        function editm(){
			   var selected = $('#test').datagrid('getSelections');  //��ȡ����ѡ���е�����
		        if(selected.length!=0){
                 $.messager.confirm('��ʾ', 'ȷ���޸���?', function(r){
										if (r){
										     $('#edit').dialog('open');
										     document.getElementById("ggggg3").style.visibility="visible";//ע�⣺��$()��ʽ������
										     g=0;arrays1.length=0;
											 $("#f").val(selected[0].str1);
											 $("#m").val(selected[0].str2);
											 $("#p").val(selected[0].int1);
											 $("#ms").val(selected[0].str4);
											 
											 if(selected.length==1){
												 $("#xiayige").html('<font>���</font>');
											 }else{
												 $("#xiayige").html('<font>��һ��</font>');
											 }
											 for(i=1;i<selected.length;i++){
												    arrays1.push(selected[i]);
											 }
											 
											 
										}
					})//
			     }else{$.messager.alert('��ʾ','ѡ�������޸ĵĶ���','info');}
			}
        function weihum(){
			
			$('#weihu').dialog('open');
			document.getElementById("ggggg4").style.visibility="visible";//ע�⣺��$()��ʽ������
			//�����"ά������"ʱ����̬����option
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
			
			
			//��ѯʱѡ���˷���֮���Զ����ģ��
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
					   		$("#website").flushCache(); //���website���Ļ���
					   		
					   		$("#website").autocomplete(websites,{
								minChars: 0,
								max: 120,
								//cacheLength :1,
								matchSubset :false,
								autoFill: false,
								multiple :false,//�����Ƿ�ɶ������
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
						text:'ȷ��',
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
				                            $.messager.alert('��ʾ','��ѯ���Ϊ�գ�','info');
				                          }
				                      }
								   })//
							 
						}
					},{
						text:'ȡ��',
						iconCls:'icon-cancel',
						handler:function(){
							$('#select').dialog('close');
						}
					}]
				});
			
			$('#add').dialog({
					buttons:[{
						text:'ȷ��',
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
							                             $.messager.alert('��ʾ','��ӳɹ���','info',function(){
							                                 
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
						text:'ȡ��',
						iconCls:'icon-cancel',
						handler:function(){
							$('#add').dialog('close');
						}
					}]
				});
			
			
	  //=======================ά������====================	
		    var arrays3=new Array();
	        var arrays4=new Array(); 
	        var arrays5=new Array();
	        var arrays6=new Array();
			  $('#datagrid1').datagrid({
				title:'ģ�����й���',
				
				width:"100%",
				height:304,
				nowrap: false,
				striped: true,
				url:'mfunction',
				loadMsg:'�����У���ȴ�...',
				frozenColumns:[[
	                {field:'ck',checkbox:true}
	            ]],
			    columns:[[
				   
			    {field:'str1',title:'   ��������',width:230}
				
				]],
				
				rownumbers:true,
				toolbar:['-',{
					text:'�ύ�޸�',
					iconCls:'icon-ok',
					handler:function(){
					   if(arrays5.length==0&&arrays6.length){
						   $.messager.alert('��ʾ','��δ���κβ�����','info');
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
                    	   
	                       jQuery.ajaxSettings.traditional = true;   //�ؼ�
	                       $.post("weiHuAction",{arrays6:arrays6,arrays5:arrays5},function(data,textStatus){
	                    	       
	                               if(data.tip=="add"){
	                        		   $.messager.confirm('���','�ɹ���ӣ�'+arrays6.toString(),function(r){
	 								      if(r){
	 								         arrays3.length=0;
	 								         arrays4.length=0;
	 								         arrays5.length=0;
	 								         arrays6.length=0;

	 								      }
	 								   })//
	                        	   }else if(data.tip=="del"){
	                        		   $.messager.confirm('���','��ɾ����'+arrays5.toString(),function(r){
		 								      if(r){
		 								         arrays3.length=0;
		 								         arrays4.length=0;
		 								         arrays5.length=0;
		 								         arrays6.length=0;

		 								      }
		 								   })//
	                        	   }else if(data.tip=="adddel"){
	                        		   $.messager.confirm('���','�ɹ���ӣ�'+arrays6.toString()   +';��ɾ����'+arrays5.toString(),function(r){
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
				title:'����ѯ�Ĺ���',
				
				width:"99.5%",
				height:200,
				nowrap: false,
				striped: true,
				url:'listfunctions',
				loadMsg:'�����У���ȴ�...',
				frozenColumns:[[
	                {field:'ck',checkbox:true}
	            ]],
			    columns:[[
				   
			    {field:'str1',title:'   ��������',width:230}
				
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
							                    	 
							                    	  $.messager.alert('��ʾ','��ģ��û�й���!','info',function(){
												        
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
						                    	 
						                    	  $.messager.alert('��ʾ','��ģ��û�й���!','info',function(){
											        
											     })//
						                      }
				                          }   
				                         				                      
				                       }
								   })//     
				}); 
		//================ά������Ӻ�ɾ��============== 
				
				$('#weihuadd').click(function(){
				    var selected = $('#datagrid2').datagrid('getSelections');  //��ȡ����ѡ���е�����
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
				     var selected = $('#datagrid1').datagrid('getSelections');  //��ȡ����ѡ���е�����
			        if(selected.length!=0){
			            for(i=0;i<selected.length;i++){
						    arrays4.push(selected[i].str1);
						    var index = $('#datagrid1').datagrid('getRowIndex', selected[i]);
							$('#datagrid1').datagrid('deleteRow', index);
							
						 }
			        }
				})//
		});
		//==========�����޸�=============
		
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
							  $("#xiayige").html('<font>���</font>');
						    }
					}else{
					     $.messager.alert('��ʾ','���޸����!','info',function(){
					          $('#edit').dialog('close');
					          $("#xiayige").html('<font>��һ��</font>');
					          $('#test').datagrid('reload');
					     })//
					     
					  
					}
					g=g+1;
				  }
		  }
		function closexiayige(){
			$('#edit').dialog('close');
		}
		
		//У��ģ���޸��еġ�ģ������
		function xiugai_up()  
		  {  
		    //����С�����ϵ����ּ�  
		    if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))) 
		    	{
		    	$("#xg_Shuzi").append("<font color='red' size='2px'>����������</font>");
		    	flag3=false;
		    	}else{
						if(p.value.length>=1&&p.value.length<5){
							flag3=true;
							   
							}else{
								$("#xg_Shuzi").append("<font color='red' size='2px'>����С��5λ</font>");
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
		        	var selected = $('#test').datagrid('getSelections');    //��ȡ��һ��ѡ���е�����
		        	if(selected.length!=0){
		        	    $.each(selected,function(n,value){
                           arr.push(value.str2);
                        });
			        	$.messager.confirm('ɾ��','ȷ��ɾ�� "'+arr.toString()+'ģ��" ��?',function(d){
			        	
			            	if(d){   
			            	        jQuery.ajaxSettings.traditional = true;   //�ؼ�
			            		    $.ajax({
				                      type:"POST",
				                      url:"delModule.action",
				                      dataType:'json',
				                      data:{mnames:arr},
				                      success:function(json){
				                         if(json.tip=="true"){
				                            
				                             $.messager.alert('��ʾ','ɾ���ɹ���','info',function(){
				                            	 $('#test').datagrid('reload');
				                             })//
				                         }else{
				                        	 $.messager.alert('����','��������ɾ�����ܱ��� :"'+json.tip+'" �ſ���ɾ����ģ�飻���߰�����ָ��������ģ����','info',function(){})//
				                         }
				                      }
				                	})//
				               	
			        		  }
			        	 })//
			        }else{$.messager.alert('��ʾ','ѡ������ɾ���Ķ���','info');}
		 }
	//=====================ģ��������===========
				 
		function add1(){
		var selected = {"moduleclass.mclassname":$('#mclassname').val(),"moduleclass.classolder":$('#classolder').val(),"moduleclass.comments":$('#comments').val()};
		$.ajax({
				type:"POST",
				url:"saveModuleclass.action",
				data:selected,
				success:function(){}
				});
		  $('#add1').window('close');
          $.messager.alert('��ʾ','��ӷ���ɹ���','info',function(){
                location.href = 'mModules.action';
                window.event.returnValue=false;
                $("#loading").show();	
            })
		}
	   function close1(){
	     $('#add1').window('close');
	     }