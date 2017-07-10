var fts=[];
//用来对自动完成组件赋值的

//该方法就是利用组件完成自动添加
	function bind(){
		//====================================
			jQuery.ajax({
		          type: "GET",
			      url:"queryZsd",
			      dataType:"json",
			      success:function (json){
			    	  fts.length=0;
				   		$.each(json.zsds,function(n,value){
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
	}
	function getKname(){
		var zsd=$("#website").val();
		if(zsd!=""&&zsd!=null){
			jQuery.ajax({
		          type: "post",
			      url:"queryKname",
			      dataType:"json",
			      data:{"zsdmc":zsd},
			      success:function (json){
			    	  $("#kmc1").val(json.kmc);
			    	  $("#zmc1").val(json.zmc);
			    	  $("#jmc1").val(json.jmc);
		         }
			});
		}
	}
	
	$(function(){
		$('#test').datagrid({
			//title:'资料',
			//iconCls:'icon-save',
			width:978,
			height:550,
			striped: true,
			fit:true,
			fitColumns:true,
			url:'JxnrJson.action',
			idField:'id',
			columns:[[		
					{title:'顺序号',field:'id',width:getWidth(0.1)},
		            {title:'资料名称',field:'nr',width:getWidth(0.3)},
		            {title:'文件描述',field:'wjms',width:getWidth(0.6)}
					]],
			pagination:true,
			rownumbers:true,
			toolbar:[{
				id:'btnadd',
				text:'添加资料',
				iconCls:'icon-add',
				handler:function(){
					$('#btnadd').linkbutton('enable');
					$('#insert').form('clear');
					
						$('#add').window('open');
						document.getElementById("ggggg1").style.visibility="visible";//注意：用$()方式不好用
					
               		 
				}
			},'-',{
				id:'btnInsert',
				text:'已有资料',
				iconCls:'icon-edit',
				handler:function(){
           		 $('#bdAdd').window('open');
           		document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
				}
			},'-',{
				id:'btndelete',
				text:'删除',
				iconCls:'icon-cut',
				handler:function(){
					deletejxnr();
				}
			}]
		});
		//这个test是用来显示已有资料的
		$('#test2').datagrid({
			//title:'已有资料',
			//iconCls:'icon-save',
			width:500,
			height:340,
			pageSize:10,
			pageList:[10,15,20,30],
			nowrap: true,
			striped: true,
			url:'query.action',
			fit:true,
			fitColumns:true,
			columns:[[
							
					{title:'资料编号',field:'zlbh',width:100},
		            {title:'资料名称',field:'zlmc',width:100},
		            {title:'资料描述',field:'zlms',width:140}
					]],
			pagination:true,
			rownumbers:true,
			onDblClickRow:function(rowIndex, rowData){
			  
			    $('#xiangxi').window('open');
			    $('#wobh').val(rowData.zlbh);
			    $('#womc').val(rowData.zlmc);
			    $('#woms').val(rowData.zlms);
			    document.getElementById("ggggg20").style.visibility="visible";//注意：用$()方式不好用
			    
		    },
			toolbar:[{
				id:'addYiYou',
				text:'添加到教学内容',
				iconCls:'icon-add',
				handler:function(){
						AddYiyou();
               		 $('#bdAdd1').window('close');
				}
			},'-']
		});

		$('#test2').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
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
	//判断角色的类型，如果角色是学生就会失去一些权限
	function PdType(){
		$.ajax({
            type:"GET",
           url:"GetType.action",
           success:function(json){
	           if(json.tip=="S"){
	        	   $('#btnadd').linkbutton('disable');
		     	   $('#btnInsert').linkbutton('disable');
		     	   $('#btndelete').linkbutton('disable');
		     	  $('#btnupdate').linkbutton('disable');
		          }
	           }
      	});
	}
	//判断上传文件的类型，如果符合就会提交
	$(function(){
		$('#tijiao1').click(function(){//貌似没用！！！（郭强注）
			var ext = $('#file').val().split('.').pop().toLowerCase();
			if($.inArray(ext, ['swf','pdf']) == -1) {
				$.messager.alert('对不起！您只能上传swf和pdf格式的文件！');
			}else{
				document.form.submit();
				}
		});
		PdType();
	}); 
	//删除教学内容，根据教学内容的id值，可以批量删除
	function deletejxnr(){
		var selected = $('#test').datagrid('getSelections');
		   if(selected){
		    	$.messager.confirm('提示','确认删除么?',function(id){
		    if(id){
		    	for(var i=0;i<selected.length;i++){
			    	var single = selected[i];
			     	$.ajax({
			            type:"POST",
			           url:"DeleteJxnr.action",
			            data:"jxnr_id="+single.id,
			           success:function(){
			     			$('#test').datagrid('reload');
				           }
			      	});
		    	}
		     $.messager.confirm('确认','所选数据已经被成功删除！',function(r){});
		    }
		   });
		   }else{
		    $.messager.alert('提示','请选择一行数据','warning');
		   }
	}
	function close2 () {
		$('#add').window('close');
	}
	//当上传完教学资料后会向后台提交关于资料的数据等信息，后台将这些信息写入到表格中,有一个tip变量，是为了标记是否添加教学资料的
	function addscwj(){
		//var zlbh = $("#addzlbh").val();
		var zlly = $("#addzlly").val();
		if(zlly==""){
			$.messager.alert('提示','资料来源不可为空！');
		}else{
			var zlscm = $("#addzlscm").val();
			var zlms = $("#addzlms").val();
			//var userId=$("#adduserId").val();
			var lxm=$("#addlxm").val();
			var tip="jxnr";
		 	$.ajax({
		            type:"POST",
		            url:"updatescwj.action",
		            data:{"zlly":zlly,"zlscm":zlscm,"zlms":zlms,"lxm":lxm,"tip":tip},
		            success:function(json){
		            	$('#add').dialog('close');					            	
		            	$.messager.alert('提示','请添加对应知识点!','info',function(){
		            		$('#test').datagrid('reload');
		            		$('#AddZsd1').window('open');
		            		document.getElementById("ggggg3").style.visibility="visible";//注意：用$()方式不好用
		            		document.getElementById("AddZsd1").style.visibility="visible";
		            		$('#zlmc1').val(json.mc);
		            		$('#zlbh1').val(json.zlbh);
		            		$("#wz1").val("");
		            		$("#zmc1").val("");
		            		$("#kmc1").val("");
		            		$("#jmc1").val("");
		            		$("#website").val("");
		            		bind();
		            	});
		                
				   }
	      	});
		}
		
	}
	
	//这个方法是查询已有资料的，将查询的条件传向后台，并判断是否有符合的数据
	$(function(){
		$('#tijiao').click(function(){
			var type=$("#type").val();
			var name=$("#name").val();
			var wjlx=$("#wjlx").val();
			
			$.ajax({
	            type:"POST",
	           url:"queryZl.action",
	           data:{"type":type,"name":name,"wjlx":wjlx},
	           cache:false,
	           success:function(json){
		           if(json.tip==0){
		        	   $.messager.alert('提示','对不起，没有找到符合要求的数据！','warning');
			       }else{
			        	
			        	$('#bdAdd1').window('open');
			        	$('#test2').datagrid('reload');
			        	$('#bdAdd1').window('close');
			        	$('#bdAdd1').window('open');
			        	$('#test2').datagrid('reload');
			        	$('#bdAdd').window('close');
				   }
	        	   
		       }
	      	});
		});
	});
	//如果找到已有资料的话，在选中后就可以将已有资料添加到教学内容中
	function AddYiyou(){
		var selected = $('#test2').datagrid('getSelections');
		if(selected){
	    	$.messager.confirm('提示','确认添加么?',function(id){
	    if(id){
	    	for(var i=0;i<selected.length;i++){
		    	var single = selected[i];
		     	$.ajax({
		            type:"POST",
		           url:"BdAdd.action",
		            data:"zlbh="+single.zlbh,
		           success:function(){
		     			$('#test').datagrid('reload');
			           }
		      	});
	    	}
	     $.messager.confirm('确认','所选资料已经被成功添加到教学内容！',function(r){});
	    }
	   });
	   }else{
	    $.messager.alert('提示','请至少选择一行数据');
	   }
	}
	//关闭添加已有资料对话框
	function close1(){
		$('#bdAdd').window('close');
	}
	//将对应资料的知识点提交的后台
	function TjZsd(){
		var zlmc = $("#zlmc1").val();
		var Zmc = $("#zmc1").val();
		var jmc = $("#jmc1").val();
		var Zsd = $("#website").val();
		var Wz =$("#wz1").val();
		var zlbh = $("#zlbh1").val();
		$.ajax({
            type:"POST",
            url:"insertzlzsddy.action",
            data:{"zlmc":zlmc,"zmc":jmc,"CName":Zmc,
        		"zsdmc":Zsd,"weizhi":Wz,"zlbh":zlbh},
           	success:function(){
        			
		   }
  		});
	}
	//当提交一个知识点后可以继续添加
	function continueAdd(){
		TjZsd();
		$("#wz1").val("");
		$("#zmc1").val("");
		$("#kmc1").val("");
		$("#jmc1").val("");
		$("#website").val("");
		
	}
	//这是点完成的时候，调用对应的方法，将知识点提交，将窗口关闭
	function over(){
		TjZsd();
		$.messager.alert('提示','所选知识点已成功添加！');
		quxiao();
	}
	//将添加知识点的对话框关闭
	function quxiao(){
		$('#AddZsd1').window('close');
	}
	
	function more(){
		$('#more').window('open');
	}