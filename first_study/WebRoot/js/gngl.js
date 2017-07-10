var fts=[];
//用来对自动完成组件赋值的

//该方法就是利用组件完成自动添加
	$(function(){
		//====================================
		
		$("#type").blur(function(){
			var mname=$("#type").val();
			jQuery.ajax({
		          type: "post",
			      url:"queryFun",
			      dataType:"json",
			      data:{"mname":mname},
			      success:function (json){
			    	  fts.length=0;
				   		$.each(json.functions,function(n,value){
				   			fts.push(value);
                         });
				   		$("#website").flushCache(); //清空website处的缓存
				   		$("#website").autocomplete(fts,{
							minChars: 0,
							max: 120,
							//cacheLength :1,
							matchSubset :false,
							autoFill: false,
							multiple :true,
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
			});
		});
	});
	$(document).ready(function(){
		$('#test').datagrid({
			title:'功能管理',
			iconCls:'icon-save',
			striped: true,
			fit:true,
			fitColumns:true,
			url:'jsonFunc',
			idField:'action',
			pageSize:15,
			pageList:[50,40,30,20,15],
			columns:[[	
					{title:'模块分类',field:'moduleclass',width:getWidth(0.2),align:'center'},
					{title:'模块名称',field:'module',width:getWidth(0.2),align:'center'},
				  {title:'action名称',field:'action',width:getWidth(0.2),align:'center'},
		          {title:'功能名称',field:'gongneng',width:getWidth(0.2),align:'center'},
	              {title:'功能备注',field:'comments',width:getWidth(0.2),align:'center'}
					]],
			pagination:true,
			rownumbers:true,
			toolbar:[{
				id:'btnadd',
				text:'添加',
				iconCls:'icon-add',
				handler:function(){
					$('#btnadd').linkbutton('enable');
					$('#add').form('clear');
					$('#flength').empty();
					$('#alength').empty();
					$('#dd1').window('open');
					document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
					$("#addmcname").focus();
				}
			},'-',{
				id:'btnupdate',
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					batchupdate();
					$('#next').linkbutton('enable');
					$('#ufunctionname').empty();
					$('#uactionname').empty();
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
				iconCls:'icon-cancel',
				handler:DelAff
			}]
		});
		$('#test').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
			 });
		
	});
	//该方法是活动不同浏览器的宽度，减去的数是datagrid中的每列的边线和显示行数的列宽
	function getWidth(percent){ 
	    return (document.body.clientWidth-25-8)*percent; 
	}
	function DelAff(){   
		 var selected = $('#test').datagrid('getSelections');
		   if(selected.length!=0){
			   var names="";
			   $.each(selected,function(mm,valuess){
				   names+=valuess.gongneng+" ";
			   });
		    	$.messager.confirm('提示','确认删除:'+names+'吗?',function(id){
		    		if(id){
			    		
		    			$.each(selected,function(n,value){
					    	var single = value;
					    	
					     	$.ajax({
					           type:"POST",
					           url:"deleteFunctions.action",
					           dataType:"json",
					            data:"actionname="+single.action,
					           success:function(json){
					     		  if(json.tip=="true"){
					     			$.messager.alert("提示","成功删除了：'"+single.gongneng+"' 功能",'info');
					     			var index = $('#test').datagrid('getRowIndex', single);  
									 $('#test').datagrid('deleteRow', index);
									 
					     		  }else{
					     			
						     		$.messager.alert('警告',"请先取消“角色管理”下的"+json.tip+"下的 '"+single.gongneng+"' 功能的权限，才可以删除：'"+single.gongneng+"'功能",'info');
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
	//用来批量更新时调用想对应的方法
	function batchupdate(){
		
		var selections = $('#test').datagrid('getSelections');
		
		if(selections.length!=0){
			var selected = selections[0];
			
			if(selections.length==1){
				$('#next').hide();
				$('#update3').show();
				//$('#next').linkbutton('disable');
			}else{
				$('#next').show();
				$('#update3').hide();
			}
			update1(selected);
		}else{
		    $.messager.alert('提示','请选择一行数据','warning');
		 }
	}
	//该方法将所选中的角色按顺序显示在修改的对话框中
	function update1(selected){
		if(selected){
			var mname = selected.module;
			var count=$("#mname1 option").length;
			var obj=document.getElementById("mname1");
			for(var i=0;i<count;i++)  
		     {  
				 obj.options[i].selected=false;
		       	 if(obj.options[i].value==mname){
		       		 obj.options[i].selected=true;
		       		 break;
		       	 }
		    }
		    
			var mclassname=selected.moduleclass;
			var count1=$("#updatemcname option").length;
			var obj1=document.getElementById("updatemcname");
			for(var i=0;i<count1;i++)  
		     {  
				 obj1.options[i].selected=false;
		       	 if(obj1.options[i].value==mclassname){
		       		 obj1.options[i].selected=true;
		       		 break;
		       	 }
		    }
		    
			
		 $('#edit1').window('open');
		 document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
		$('#actionname1').val(selected.action);
           $('#functionname1').val(selected.gongneng);
           $('#comments1').val(selected.comments);
		}else{
		    $.messager.alert('提示','请选择一行数据','warning');
		 }
	}
	//点击确定修改后，向后台提交修改后的数据。如果有下一条，将显示下一条的信息
	function edit1(){
		//$('#next').linkbutton('disable');
		var selections = $('#test').datagrid('getSelections');
		var	selected = selections[0];
		var module = $("#mname1").find("option:selected").text();
		var functionname = $("#functionname1").val();
		var actionname = $("#actionname1").val();
		var comments = $("#comments1").val();
		var moduleclass = $("#updatemcname").find("option:selected").text();
		$.ajax({
           type:"POST",
          url:"updateFunction.action",
           data:{"module":module,"functionname":functionname,"actionname":actionname,"comments":comments},
          success:function(){
	           	var index = $('#test').datagrid('getRowIndex', selected);     
				$('#test').datagrid('deleteRow', index);
	           	$('#test').datagrid('appendRow',{
	           		action:actionname,
	           		gongneng:functionname,
	           		comments:comments,
	           		module:module,
	           		moduleclass:moduleclass
	               });
	               if(selections.length>0){
	           			batchupdate();
	           		}else{
	           			$('#edit1').window('close');
		           	}
	           	}
 		});
	}
	
	 function close3(){
		   $('#edit1').window('close');
		   var selections = $('#test').datagrid('getSelections');
		   selections.length=0;
	}

	
	//对添加验证，会对模块名称，功能名称，actionname分别验证
	 $(function(){
		 var a=0;
		 var b=0;
		 var c=0;
		$("#addfunctionname").blur(function(){
			var functionname = $("#addfunctionname").val();
			var cArr = functionname.match(/[^\x00-\xff]/ig);
			var flength = functionname.length+(cArr == null ? 0 : cArr.length);
			if(flength>20){
				$('#flength').empty().append("<font color='red' size='2px'>长度不能大于20字节！</font>");
			}else if(flength==0){
				$('#flength').empty().append("<font color='red' size='2px'>长度不能为空！</font>");
			}else{
				$('#flength').empty();
				$.ajax({
		            type:"POST",
		           url:"beforeInsert.action",
		            data:"functionname="+functionname,
		           success:function(data){
		           		if(data.tip=="have"){
			           		$('#flength').empty().append("<font color='red' size='2px'>此functionname已存在</font>");
		           		}else{
			           		a=1;
		           		}
				   }
	      		});
			}
		});		
		$("#addactionname").blur(function(){
			var actionname = $("#addactionname").val();
			var cArr = actionname.match(/[^\x00-\xff]/ig);
			var alength = actionname.length+(cArr == null ? 0 : cArr.length);
			if(alength>20){
				$('#alength').empty().append("<font color='red' size='2px'>长度不能大于20字节！</font>");
			}else if(alength==0){
				$('#alength').empty().append("<font color='red' size='2px'>长度不能为空！</font>");
			}else{
				$('#alength').empty()
				$.ajax({
		            type:"POST",
		           url:"beforeInsert.action",
		            data:"actionname="+actionname,
		           success:function(data){
		           		if(data.tip=="have"){
			           		$('#alength').empty().append("<font color='red' size='2px'>此ationname已存在</font>");
		           		}else{
			           		b=1;
		           		}
				   }
	      		});
			}
		});	
		$("#addmname").blur(function(){
			var module = $("#addmname").find("option:selected").text();
			if(module==""){
				$('#amname').empty().append("<font color='red' size='2px'>模块名称不能为空</font>");
			}else{
				$('#amname').empty();
				c=1;
			}
		});
		$("#tijiao").click(function(){
			if(a==1&&b==1&&c==1){
		 		sub();
		 		a=0; b=0; c=0;
		 		}else{
		 			$.messager.alert('提示','请按规则添加数据','warning');
		 		}
	 	});
	 });
//对修改验证，会对模块名称，功能名称，actionname分别验证
	 $(function(){	
			$("#functionname1").blur(function(){
				var functionname = $("#functionname1").val();
				var cArr = functionname.match(/[^\x00-\xff]/ig);
				var flength = functionname.length+(cArr == null ? 0 : cArr.length);
				if(flength>20){
					$('#ufunctionname').empty().append("<font color='red' size='2px'>长度不大于20字节！</font>");
				}else if(flength==0){
					$('#ufunctionname').empty().append("<font color='red' size='2px'>长度不能为空！</font>");
				}else{
					$('#ufunctionname').empty();
					$.ajax({
			            type:"POST",
			           url:"beforeInsert.action",
			            data:"functionname="+functionname,
			           success:function(data){
			           		if(data.tip!=null&&data.tip!=functionname){
				           		$('#ufunctionname').empty().append("<font color='red' size='2px'>此functionname已存在</font>");
			           		}
					   }
		      		});
				}
			});		
			$("#actionname1").blur(function(){
				var actionname = $("#actionname1").val();
				var cArr = actionname.match(/[^\x00-\xff]/ig);
				var alength = actionname.length+(cArr == null ? 0 : cArr.length);
				if(alength>20){
					$('#uactionname').empty().append("<font color='red' size='2px'>长度不大于20字节！</font>");
				}else if(flength==0){
					$('#uactionname').empty().append("<font color='red' size='2px'>长度不能为空！</font>");
				}else{
					$('#uactionname').empty()
					$.ajax({
			            type:"POST",
			           url:"beforeInsert.action",
			            data:"actionname="+actionname,
			           success:function(data){
			           		if(data.tip!=null&&data.tip!=actionname){
				           		$('#uactionname').empty().append("<font color='red' size='2px'>此ationname已存在</font>");
			           		}
					   }
		      		});
				}
			});	
			$("#mname1").blur(function(){
				var module = $("#mname1").find("option:selected").text();
				if(module==""){
					$('#umname').empty().append("<font color='red' size='2px'>模块名称不能为空</font>");
				}else{
					$('#umname').empty();
				}
			});
			$("#update3").click(function(){
					edit1();
		 	});
	});
	//当验证通过之后，向后台提交数据
	function sub(){
		var module = $("#addmname").find("option:selected").text();
		var functionname = $("#addfunctionname").val();
		var actionname = $("#addactionname").val();
		var comments = $("#addcomments").val();
		var moduleclass = $("#addmcname").find("option:selected").text();
		$.ajax({
            type:"POST",
           url:"insertFunctions.action",
            data:{"module":module,"functionname":functionname,"actionname":actionname,"comments":comments},
           success:function(){
            	close1();
       			$.messager.alert('提示','添加信息成功!!!','info',function(){});
       			
            	$('#test').datagrid('appendRow',{
            		action:actionname,
               		gongneng:functionname,
               		comments:comments,
               		module:module,
               		moduleclass:moduleclass
                });
            }
  		});
	}
	
	function close1(){
		$('#dd1').window('close');
	}
	//用来查询功能时调用的方法，向后台传递两个参数
	 function query(){
	        var queryParams = $('#test').datagrid('options').queryParams;
	        queryParams.queryType = $('#type').val();
	        queryParams.queryWord = $('#website').val();
	        $('#test').datagrid({
	            url:'queryfunction.action'
	        });
	        $('#query').window('close');
	        $('#test').datagrid('getPager').pagination({
				 displayMsg:'当前显示从{from}到{to}共{total}记录',
				 beforePageText:'第',
				 afterPageText:'页'
				 });
	    }