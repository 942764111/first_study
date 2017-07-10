var b=0;
var validator;
var validator1;
$(function(){
		$('#test').datagrid({
			title:'教学计划管理---课程概况',
			iconCls:'icon-save',
			width:978,
			height:550,
			striped: true,
			fit:true,
			url:'JxjhJson.action',
			//singleSelect:true,
			idField:'id',
			onDblClickRow:function(rowIndex, rowData){
			   //location.href='jxjh.action?kch='+rowData.int2+'&xq='+rowData.str2+'&jsbh='+rowData.str1+'&tip=bht'+'&jhid='+rowData.int3+'&courseName='+rowData.str4;
			   $.ajax({
	                  type:"POST",
	                 url:"ReceiveJhId.action",//在JxnrAction.java中
	                  //data:"jhid="+selected.id,
	                  data:{"jhid":rowData.id, "chapterName":rowData.kname,"chapterbh":rowData.zbh},
	                 success:function(){}
	              });
				window.location.href='ToJxnr.action?jhid='+rowData.zbh;
				window.event.returnValue=false;
			},
			columns:[[		
			        {title:'课次顺序',field:'id',width:getWidth(0.1),align:'center',
			            formatter:function(value,rec){
			        	    //标记第几次课
			        	    var num = $('#test').datagrid('getRowIndex', rec)+1; 
			        	    return '<span>第'+num+'次课</span>';
			            }	
			        },
					{title:'该次课的内容概述',field:'jhmc',width:getWidth(0.45),align:'center'},
			        {title:'课时',field:'xsh',width:getWidth(0.05),align:'center'},
			        {title:'对应的章名称',field:'kname',width:getWidth(0.4),align:'center'}
		            
		            
					]],
			pagination:true,
			rownumbers:true,
			
			toolbar:[{
				id:'btnadd',
				text:'添加',
				iconCls:'icon-add',
				handler:function(){
					$('#btnsave').linkbutton('enable');
					$('#arolename').empty()
					$('#add').form('clear');
					$('#dd1').window('open');
					document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
				}
			},'-',{
				id:'btnupdate',
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					batchupdate();
					$('#next').linkbutton('enable');
				}
			},'-',{
				id:'btnsearch',
				text:'查询',
				iconCls:'icon-search',
				handler:function(){
					$('#btnsearch').linkbutton('enable');
					$('#query').window('open');
					document.getElementById("ggggg3").style.visibility="visible";//注意：用$()方式不好用
				}
			},'-',{
				id:'btndelete',
				text:'删除',
				iconCls:'icon-cut',
				handler:function(){
				DelAff();
				}
			},'-',{
				id:'btnjxnr',
				text:'教学内容',
				iconCls:'icon-redo',
				handler:function(){
					ToJxnr();
				}
			},'-',{
				id:'btnadd1',
				text:'试卷管理',
				iconCls:'icon-redo',
				handler:function(){
					//跳转到小丽的
				    location.href='Ttest.action';
				 // 在此处加上以下代码既可    
					window.event.returnValue = false;
				}
			},'-',{
				id:'btnadd2',
				text:'学生答题记录管理',
				iconCls:'icon-redo',
				handler:function(){
					//跳转到小丽的
				    location.href='xsdjjlgl.action';
				 // 在此处加上以下代码既可    
					window.event.returnValue = false;
				}
			},'-',{
				
				text:'后退至上一页面',
				iconCls:'icon-back',
				handler:function(){
					/*$.ajax({
			            type:"GET",
			           url:"GetType.action",
			           success:function(json){//因为不知道js文件中如何取session中的值所以先这样！在jsp中用session即可
				           if(json.tip=="S"){
				        	   location.href='jxjh_tj_stu.action';	
					           }else{
					        	   location.href='jxjh_tj.action';
					           }
				           }
			        });*/
				window.history.back();
				    
				}
			},'-']
		});
		 $('#test').datagrid('getPager').pagination({
			 displayMsg:'(刷新可查看表格中查询之前的数据)   当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
		 
		//对学期的校验
			 validator=$("#form").validate({
				
				rules: {
				    "ksxg":{
				        number:true,
			            digits:true,
				        min:0,
			            max:50,
		                maxlength:10

				    }
					
				},	
				messages: {
					
					"ksxg":{
					    number:" (数字)",
					    digits:"(整数)",
				        min:"(最小0)",
				        max:"(最大50)",
						maxlength:"(最多10位)"

				    }
				 }	
			});
			//对课时的校验
			 validator1=$("#add").validate({
				
				rules: {
				    "ks":{
				        number:true,
			            digits:true,
				        min:0,
		                max:50,
		                maxlength:10

				    }
					
				},	
				messages: {
					
					"ks":{
					    number:"(数字)",
					    digits:"(整数)",
					    min:"(最小0)",
			            max:"(最大50)",
						maxlength:"(最多10位)"

				    }
				 }	
			});
		//为对应章产生下拉菜单
			 $.ajax({
				    url:"dyzAction",
				    type:"post",
				    dataType:"json",
				    success:function(json){
							 $.each(json.listcc,function(n,value){
								  
								  $("#dyz").append("<option value=\""+value.zbh+"\">"+value.CName+"</option>");
									 
							  })
				        }
				   });
		 
	});
	//判断角色的类型，如果角色是学生就会失去一些权限
    /*
     * 此处的判断移到了jxjh.jsp页面上了
     * */
	//这个方法是查看教学计划对应的教学资料的，将跳转到教学内容action
	function ToJxnr(){
		var selected = $('#test').datagrid('getSelections');   
		if (selected.length==1){  
			 $.ajax({
                  type:"POST",
                 url:"ReceiveJhId.action",//在JxnrAction.java中
                  //data:"jhid="+selected.id,
                  data:{"jhid":selected[0].id, "chapterName":selected[0].kname,"chapterbh":selected[0].zbh},
                 success:function(){}
              });
			window.location.href='ToJxnr.action?jhid='+selected[0].zbh;
			// 在此处加上以下代码既可    
			window.event.returnValue = false;
		}else{
		    $.messager.alert('提示','请选择一行数据','warning');
		 }
	}
	//添加或修改教学计划时，负责调用相对应的方法
	$(function(){
		
		$("#tijiao1").click(function(){
		 	edit1();
	 	});
		$("#tijiaojiao").click(function(){//继续添加一次课
			 b=1;
		 	sub(b);
	 	});
		$("#tijiao").click(function(){//完成添加一次课
			b=2;
		 	sub(b);
	 	});
		//PdType();
	});
	//该方法是活动不同浏览器的宽度，减去的数是datagrid中的每列的边线和显示行数的列宽
	function getWidth(percent){ 
	    return (document.body.clientWidth-25-8)*percent; 
	} 
	//当确定添加教学计划后向后台提交数据
	//去左空格;
	function ltrim(s){return s.replace( /^\s*/, "");}
	//去右空格;
	function rtrim(s){return s.replace( /\s*$/, "");}
	function sub(b){
		//alert("b:"+b);
		var jhmc = $("#addJhmc").val();
		var xsh = $("#addXsh").val();
		var dyz=$("#dyz").val();
		xsh=rtrim(ltrim(xsh));
		if(xsh==""){//默认就是String类型
			xsh=0;
		}
		
		 if(jhmc==""||dyz==-1||dyz==null){
				$.messager.alert('警告','内容概述和对应章不能为空!','warning');
				}else{
					if(validator1.form()){
						
						$.ajax({
				            type:"POST",
				           url:"AddJxjh.action",
				            data:{"jhmc":jhmc,"xsh":xsh,"kch":dyz},
				           success:function(){
				            	//$('#test').datagrid('appendRow',{
				            	//	zcmc:zcmc,
				            	//	zcjb:zcjb
				               // });
				            	if(b==2){
				            		close1();
				            		
				            	}
				            	$("#addJhmc").val("");
				            	$("#addXsh").val("");
				            	
				       			$('#test').datagrid('reload');
				            }
				  		});
					}
					
		}
		
	}
	
	function as1(iframe,width,height)
  	 {
  	 var iframe1 = $("#".concat(iframe)); //这里即$("#CFrame4");
  	 iframe1.width(width);
  	 iframe1.height(height); 
  	 }
	
	//删除教学计划，可批量删除
	function DelAff(){   
		 var selected = $('#test').datagrid('getSelections');
		   if(selected.length!=0){
			   var names="";
			   $.each(selected,function(mm,valuess){
				   names+="'"+valuess.jhmc+"' ";
			   });
			  $.messager.confirm('提示','确认删除内容概述为:'+names+'的课次吗?',function(id){
					    if(id){
					    	
					    	$.each(selected,function(n,value){
						    	var single = value;
						    	
						     	$.ajax({
						           type:"POST",
						           url:"deleteJxjh.action",
						           dataType:"json",
						            data:"jhid="+single.id,
						           success:function(json){
						     		  if(json.tip=="true"){
						     			//$.messager.alert("提示","成功删除了：'"+single.jhmc+"' 课次",'info');
						     			var index = $('#test').datagrid('getRowIndex', single);  
										 $('#test').datagrid('deleteRow', index);
										 
						     		  }else{
						     			
							     		$.messager.alert('警告',"请先删除课次:‘"+single.jhmc+"’下的 所有教学内容;<br/>才可以删除：'"+single.jhmc+"'",'info');
						     		  }
						     	    
						     	     
						     	   }
						      	});
						     	
			    			});
					    	
					    	
					    	
					  }
			   });
		   }else{
		    $.messager.alert('提示','请选择一行数据','warning');
		   }
	}
	function close1(){
		$('#dd1').window('close');
	}
	//用来批量更新时调用想对应的方法
	function batchupdate(){
		var selections = $('#test').datagrid('getSelections');
		if(selections.length!=0){
			var selected = selections[0];
			if(selections.length==1){
				$('#next').linkbutton('disable');
			}
			update1(selected);
		}else{
		    $.messager.alert('提示','请选择一行数据','warning');
		 }
	}
	//修改教学计划的时候，将要修改的教学计划放入到修改的对话框中
	function update1(selected){
		if(selected){

		    var kname = selected.kname;

		    $("#updateKcm option:contains('"+kname+"')").attr('selected', 'selected');
			 $('#edit1').window('open');
			 document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
			 $('#updateXsh').val(selected.xsh);
             
            $('#updateJhmc').val(selected.jhmc);
            $('#jhbh').val(selected.id);
		}else{
		    $.messager.alert('提示','请选择一行数据','warning');
		 }
	}
	//实际提交修改后的数据
	function edit1(){
		var selections = $('#test').datagrid('getSelections');
		var	selected = selections[0];
		var id = $("#jhbh").val();
		
		var zbh = $("#updateKcm").val();
		var jhmc = $("#updateJhmc").val();
		
		var ks = $("#updateXsh").val();
		 if(zbh==-1){
			$.messager.alert('提示','请选择对应章！');
			}else if(jhmc==""){
				$.messager.alert('提示','内容概述不能为空！');
				}else{
				 if(validator.form()){
					$.ajax({
				           type:"POST",
				          url:"updateJxjh.action",
				           data:{"jhid":id,"kch":zbh,"jhmc":jhmc,"xsh":ks},
				          success:function(){
				        	   var index = $('#test').datagrid('getRowIndex', selected);     
								$('#test').datagrid('deleteRow', index);
					           	
								if(selections.length>0){
				           			batchupdate();
				           		}else{
				           			$('#edit1').window('close');
				           			$('#test').datagrid('reload');
					           	}
					           }
				 		});
				}
		}
		
	}
	function close3(){
		   $('#edit1').window('close');
	}
	//查询教学计划，根据课程名称和学期名称查询
	function query(){
		
       var queryword=$('#nrgs').val();
//       if(queryword==""){
//    	   $.messager.alert("警告","查询条件不可为空！","warning");
//       }else{
    	   $.ajax({
	           type:"POST",
	           url:"queryJxjh.action",
	           dataType:"json",
	           data:{"queryword":queryword},
	          success:function(json){
	        	   $('#query').window('close');
	        	      if(json.tip=="kong"){
	        	    	  $.messager.alert('提示','查询结果为空！','info');
	        	      }else{
	        	    	  $('#test').datagrid({
	  					    url:'JxjhJson.action?queryword='+queryword,
	  					    loadMsg:'更新数据......',
	  					    pageNumber:1
	  					   });
	  				      displayMsg();
	        	      }
		           }
	 		});
       //}
      
    }
	 function displayMsg(){
		 $('#test').datagrid('getPager').pagination({
			 displayMsg:'(刷新可查看表格中查询之前的数据)   当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
   }