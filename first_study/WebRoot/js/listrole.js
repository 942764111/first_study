$(function(){
		$('#test').datagrid({
			title:'角色管理   (双击查看详细信息！)',
			striped: true,
			iconCls:'icon-save',
			width:getWidth(1),
			height:550,
			fit:true,
			pageList:[15,20,30,40,50],
			url:'json.action',
			idField:'roleid',
			loadMsg:'',
			onDblClickRow:function(rowIndex, rowData){
				$.ajax({
	                type:"POST",
	               url:"Show.action",
	                data:"roleid="+rowData.roleid,
	               success:function(data){	
					 $('#show').window('open');
					 document.getElementById("ggggg3").style.visibility="visible";//注意：用$()方式不好用
						$('#show_roleid').val(rowData.roleid);
			           $('#show_rolename').val(rowData.rolename);
			           $('#show_miaoshu').val(data.miaoshu);			 	
	               }
	            });
			} ,
			columns:[[		
					{title:'角色编号',field:'roleid',width:getWidth(0.2),align:'center'},
			        {title:'角色名称',field:'rolename',width:getWidth(0.3),align:'center'},
		            {title:'角色描述',field:'miaoshu',width:getWidth(0.485)}
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
					$('#urolename').empty()
				}
			},'-',{
				id:'btnquery',
				text:'查询',
				iconCls:'icon-search',
				handler:function(){
					$('#btnquery').linkbutton('enable');
					$('#query').window('open');
					document.getElementById("ggggg4").style.visibility="visible";//注意：用$()方式不好用
				}
			},'-',{
				id:'btnfuquan',
				text:'赋权',
				iconCls:'icon-undo',
				handler:function(){
					$('#btnfuquan').linkbutton('enable');
					fuquan();
				}
			},'-',{
				id:'btndelete',
				text:'删除',
				iconCls:'icon-cancel',
				handler:function(){
				DelAff();
				}
			},'-']
		});
		 $('#test').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
	});
//该方法是活动不同浏览器的宽度，减去的数是datagrid中的每列的边线和显示行数的列宽
	function getWidth(percent){ 
	    return (document.body.clientWidth-25-5)*percent; 
	}
	
	//删除角色，可以选中多条然后同时删除
	function DelAff(){   
		 var selected = $('#test').datagrid('getSelections');
		   if(selected.length!=0){
			   var names="";
			   $.each(selected,function(mmm,valuesss){
				   names+=valuesss.rolename+" ";
			   });
		    	$.messager.confirm('提示','确认删除:'+names+'吗?',function(id){
		    if(id){
		    	
		    	$.each(selected,function(n,value){
			    	var single = value;
			    	
			     	$.ajax({
			           type:"POST",
			           url:"deleteRole.action",
			           dataType:"json",
			           data:"roleid="+single.roleid,
			           success:function(json){
			     		  if(json.tip=="true"){
			     			$.messager.alert("提示","成功删除了：'"+single.rolename+"' 角色",'info');
			     			var index = $('#test').datagrid('getRowIndex', single);  
							 $('#test').datagrid('deleteRow', index);
							 
			     		  }else{
			     			
				     		$.messager.alert('警告',json.tip+"，才可以删除：'"+single.rolename+"'角色",'warning');
			     		  }
			     	    
			     	     
			     	   }
			      	});
			     	
    			});
		    	
		    }
		   });
		   }else{
		    $.messager.alert('提示','请选择将删除的数据','error');
		   }
	}
	//用来批量更新时调用想对应的方法
	function batchupdate(){
		var selections = $('#test').datagrid('getSelections');
		if(selections.length!=0){
			var selected = selections[0];
			if(selections.length==1){
				$('#next').hide();
				$('#wancheng').show();
				//$('#next').linkbutton('disable');
			}else{
				$('#next').show();
				$('#wancheng').hide();
			}
			update1(selected);
		}else{
		    $.messager.alert('提示','请选择一行数据','error');
		 }
	}
//该方法将所选中的角色按顺序显示在修改的对话框中
	function update1(selected){
		if(selected){
			 $('#edit1').window('open');
			 document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
			$('#roleid1').val(selected.roleid);
           $('#rolename1').val(selected.rolename);
           $('#miaoshu1').val(selected.miaoshu);
		}else{
		    $.messager.alert('提示','请选择一行数据','error');
		 }
	}
	//点击确定修改后，向后台提交修改后的数据。如果有下一条，将显示下一条的信息
	function edit1(){
		var selections = $('#test').datagrid('getSelections');
		var selected = selections[0];
		var roleid = $("#roleid1").val();
		var rolename = $("#rolename1").val();
		var miaoshu = $("#miaoshu1").val();
		if(rolename==""||rolename==null){
			$.messager.alert('提示','角色名称不能为空!!!');
		}else{
			$.ajax({
		           type:"POST",
		          url:"updateRole.action",
		           data:{"roleid":roleid,"rolename":rolename,"miaoshu":miaoshu},
		          success:function(){
			           	var index = $('#test').datagrid('getRowIndex', selected); 
						$('#test').datagrid('deleteRow', index);
			           	$('#test').datagrid('appendRow',{
			           		roleid:roleid,
			           		rolename:rolename,
			           		miaoshu:miaoshu
			            });
			           	if(selections.length>0){
		           			batchupdate();
		           		}else{
		           			$.messager.alert('提示','数据修改成功','info');
							clos();
			           	}
				   }
		 		});
		}
		
	}
	//对角色赋权时，单击“赋权”时调用此方法，页面将跳转到赋权界面
	function fuquan(){
		var selected = $('#test').datagrid('getSelected');   
		if (selected){  
			 $.ajax({
                  type:"POST",
                 url:"fuquanRole.action",
                  data:"roleid="+selected.roleid,
                 success:function(){				 	
                 }
              });
			 window.location.href='ToFq.action';
			// 在此处加上以下代码既可    
			window.event.returnValue = false;
		}else{
		    $.messager.alert('提示','请选择一行数据');
		 }
	}  
//添加时对输入信息验证，如果输入角色已存在或是角色名称长于20字节会提示
	$(function(){
		$("#addrolename").blur(function(){
			var rolename = $("#addrolename").val();
			var cArr = rolename.match(/[^\x00-\xff]/ig);
			var alength = rolename.length+(cArr == null ? 0 : cArr.length);
			if(alength>20){
				$('#arolename').empty().append("<font color='red' size='2px'>长度不能大于20字节！</font>");
			}else if(alength==0){
				$('#arolename').empty().append("<font color='red' size='2px'>角色名称不能为空！</font>");
			}
			else{
				$('#alength').empty()
				$.ajax({
		            type:"POST",
		           url:"beforeCreat.action",
		            data:"rolename="+rolename,
		           success:function(data){
		           		if(data.tip=="have"){
			           		$('#arolename').empty().append("<font color='red' size='2px'>此角色已存在</font>");
		           		}
				   }
	      		});
			}
		});	
	});
//修改时对输入信息验证
	$(function(){
		$("#addrolename").blur(function(){
			var rolename = $("#addrolename").val();
			var cArr = rolename.match(/[^\x00-\xff]/ig);
			var alength = rolename.length+(cArr == null ? 0 : cArr.length);
			if(alength>20){
				$('#urolename').empty().append("<font color='red' size='2px'>长度不能大于20字节！</font>");
			}else{
				$('#urolename').empty()
				$.ajax({
		            type:"POST",
		           url:"beforeCreat.action",
		            data:"rolename="+rolename,
		           success:function(data){
		           		if(data.tip!=null){
			           		$('#urolename').empty().append("<font color='red' size='2px'>此角色已存在</font>");
		           		}
				   }
	      		});
			}
		});	
		$("#rolename1").blur(function(){
			var rolename = $("#rolename1").val();
			var cArr = rolename.match(/[^\x00-\xff]/ig);
			var alength = rolename.length+(cArr == null ? 0 : cArr.length);
			if(alength>20){
				$('#urolename1').empty().append("<font color='red' size='2px'>长度不能大于20字节！</font>");
			}else{
				$('#urolename1').empty()
				$.ajax({
		            type:"POST",
		           url:"beforeCreat.action",
		            data:"rolename="+rolename,
		           success:function(data){
		           		if(data.tip!=null){
			           		$('#urolename1').empty().append("<font color='red' size='2px'>此角色已存在</font>");
		           		}
				   }
	      		});
			}
		});	
	});
	//添加角色时当单击确定后向后台提交数据
	function add(){
			var rolename = $("#addrolename").val();
			var miaoshu = $("#addmiaoshu").val();
			if(rolename==null||rolename==""){
				$.messager.alert('提示','角色名称不能为空!!!');
			}else{
				$.ajax({
		            type:"POST",
		           url:"creatRole.action",
		            data:{"rolename":rolename,"miaoshu":miaoshu},
		           success:function(data){
		            	close1();
		            	$.messager.alert('提示','添加信息成功!!!',function(){});
		            	var roleid = data.roleid;
		            	$('#test').datagrid('appendRow',{
		            		roleid:roleid,
		            		rolename:rolename,
		            		miaoshu:miaoshu
		                });  
				   }
	      	});
			}
		}
	
	function close1(){
		$('#dd1').window('close');
	}
	function clos(){
		$('#edit1').window('close');
	}
	//查询角色时调用，向后台传递两个参数。查询出来后datagrid会自动更新
	function query(){
        var queryParams = $('#test').datagrid('options').queryParams;
        queryParams.queryType = $('#type').val();
        queryParams.queryWord = $('#name').val();
        $('#test').datagrid({
            url:'queryroles.action'
        });
        $('#query').window('close');
        $('#test').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
    }