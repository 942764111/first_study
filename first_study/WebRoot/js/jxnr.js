var fts=[];
var flag=1;
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
						max: 20,
						//cacheLength :1,
						matchSubset :false,
						autoFill: false,
						multiple :false,
						mustMatch: false,
						multipleSeparator :"",
						matchContains: true,
						scrollHeight: 100,
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
	flag=1;
	if(zsd!=""&&zsd!=null){
		
		$.each(fts,function(n,value){
   			if(zsd==value){
   				flag=2;
   				
   			}
         });
		if(flag==2){
			jQuery.ajax({
		          type: "post",
			      url:"queryKname",
			      dataType:"json",
			      data:{"mc":zsd},
			      success:function (json){
			    	  
			    	  $("#jmc1").val(json.jmc);
		         }
			});
		}else{
			$.messager.alert('提示','此知识点不存在！','info');
			$("#jmc1").val("");
		}
		
	}else{
		$("#jmc1").val("");
	}
}

	
	$(function(){
		$('#test').datagrid({
			title:'教学计划管理—课程概况-教学内容',
			iconCls:'icon-save',
			width:978,
			height:550,
			striped: true,
			fit:true,
			url:'JxnrJson.action',
			singleSelect:true,
			idField:'id',
			columns:[[		
		            {title:'资料名称',field:'zlmc',width:getWidth(0.3)},
		            {title:'文件名',field:'nr',width:getWidth(0.3)},
		            {title:'资料描述',field:'wjms',width:getWidth(0.4)}
					]],
			pagination:true,
			rownumbers:true,
			toolbar:[{
				id:'btnadd',
				text:'添加资料',
				iconCls:'icon-add',
				handler:function(){
					//$('#fileupload').uploadifyClearQueue();
					//$('#btnadd').linkbutton('enable');
					//$('#insert').form('clear');
					
					$('#addzlly').val("");
					$('#addzlmc').val("");
					$('#lxm').val("");
					$('#changdu').val("");
					$('#scrq').val("");
					$('#filePath').val("");
					$('#fileName').val("");
					$('#addzlscm').val("");
					$('#addzlms').val("");
					$('#no').val("");
					
						//$('#add').window('open');
					var $win;
					$win = $('#add').window({
						top:document.body.scrollTop+70,   
			            left:document.body.scrollLeft+200
					});
					$win.window('open');
						document.getElementById("add").style.visibility="visible";//注意：用$()方式不好用
						$('#fileupload').uploadifyClearQueue();
					
               		 
				}
			},'-',{
				id:'btnInsert',
				text:'已有资料',
				iconCls:'icon-edit',
				handler:function(){
					var $win;
					$win = $('#bdAdd').window({
						top:document.body.scrollTop+200,   
			            left:document.body.scrollLeft+300
					});
					$win.window('open');
	           		 //$('#bdAdd').window('open');
	           		document.getElementById("ggggg2").style.visibility="visible";//注意：用$()方式不好用
				}
			},'-',{
				id:'btndelete',
				text:'删除',
				iconCls:'icon-cut',
				handler:function(){
					deletejxnr();
				}
			},'-',{
				id:'btnjxnr',
				text:'打开',
				iconCls:'icon-redo',
				handler:function(){
					open();
				}
			},'-',{
				id:'xsj',
				text:'生成新试卷',
				iconCls:'icon-redo',
				handler:function(){
				   //开始调用小丽的生成试卷的
				    $('#scxsj').window('open');
           		    document.getElementById("ggggg4").style.visibility="visible";//注意：用$()方式不好用
				}
			},'-',{
				
				text:'后退至上一页面',
				iconCls:'icon-back',
				handler:function(){
				   //location.href='jxjh.action';
				   // 在此处加上以下代码既可    
					//window.event.returnValue = false;
				    window.history.back();
				}
			},'-',{
				
				text:'  <select id="xl" style="width:220px;"><option value=-1>-请选择-</option></select> <input type="button" value="开始测试" onclick="kscs();"/>',
				iconCls:'icon-redo',
				handler:function(){
				   //跳转到小丽的
				}
			},'-']
		});
		//这个test是用来显示已有资料的
		$('#test2').datagrid({
			title:'已有资料',
			iconCls:'icon-save',
			width:633,
			height:300,
			url:'querylistZl.action',
			pageSize:8,
			pageList:[8,15,20,30],
			striped: true,
			columns:[[
					{field:'ck',checkbox:true},		
					{title:'资料编号',field:'zlbh',width:50},
		            {title:'资料名称',field:'zlmc',width:150},
		            {title:'资料描述',field:'zlms',width:350}
					]],
			pagination:true,
			rownumbers:true,
			toolbar:[{
				id:'addYiYou',
				text:'添加到教学内容',
				iconCls:'icon-add',
				handler:function(){
						AddYiyou();
               		 $('#bdAdd1').window('close');
				}
			}]
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
		 var p = $('#test').datagrid('getPager');
			if (p){
				$(p).pagination({
					onBeforeRefresh:function(){
					  $('#test').datagrid('clearSelections');
					}
				});
			}
			var p2 = $('#test2').datagrid('getPager');
			if (p2){
				$(p2).pagination({
					onBeforeRefresh:function(){
					  $('#test2').datagrid('clearSelections');
					}
				});
			}
		 //为测试试卷的产生下拉菜单
		 $.ajax({
			    url:"ToJxnrXlsj",
			    type:"post",
			    dataType:"json",
			    success:function(json){
						 $.each(json.listsjname,function(n,value){
							  $("#xl").append("<option value=\""+value.sjno+"\">"+value.zxx+"</option>");
								 
						  })
		            }
			   });
	});
	//开始试卷的测试
	function kscs(){
		var count=$("#xl option").length;
		
		var sjbh2=$("#xl").val();
		var sjname = $("#xl").find("option:selected").text();
		
		if(sjbh2==-1){
			if(count==1){
				$.messager.alert('提示','老师还未出卷，没有试卷共您选择！','info');
			}else{
				$.messager.alert('警告','请选择试卷！','warning');
			}
			
		}else{
			sjbh2=sjbh2+"";
			//alert(typeof(sjbh2));
			$.ajax({
		           type:"post",
		           url:"sjxztnr.action",
		           data:{"sjname":sjname,"sjbh2":sjbh2},
		           success:function(json){
					window.location.href="csj.action";
					window.event.returnValue = false;
				    }
		      	});
		}
		
		
	}
	//该方法是活动不同浏览器的宽度，减去的数是datagrid中的每列的边线和显示行数的列宽
	function getWidth(percent){ 
	    return (document.body.clientWidth-25-6)*percent; 
	}
	//判断角色的类型，如果角色是学生就会失去一些权限
	function PdType(){
		$.ajax({
            type:"GET",
           url:"GetType.action",
           success:function(json){
	           if(json.tip=="S"){
	        	   $('#btnadd').remove();
		     	   $('#btnInsert').remove();
		     	   $('#btndelete').remove();
		     	  
		     	 $("#xsj").remove();

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
		   if(selected.length!=0){
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
		     //$.messager.confirm('确认','所选数据已经被成功删除！',function(r){});
		    }
		   });
		   }else{
		    $.messager.alert('提示','请选择将删除数据','warning');
		   }
	}
	function close2 () {
		$('#add').window('close');
	}
	
	function close22 () {
		var lxm = $("#lxm").val();
		var no = $("#no").val();
		var filePath = $("#filePath").val();
		if(lxm!=0) {
			$.ajax({
                type:"POST",
                url:"check.action",
                data:{"no":no,"filePath":filePath},
                success:function(json){
					$('#add').window('close');
                }
			});
		} else {
			$('#add').window('close');
		}
	}
	
	//当上传完教学资料后会向后台提交关于资料的数据等信息，后台将这些信息写入到表格中,有一个tip变量，是为了标记是否添加教学资料的
	function addscwj(){
		//var zlbh = $("#addzlbh").val();
		var zlly = $("#addzlly").val();
		var zlmc = $("#addzlmc").val();
		var lxm = $("#lxm").val();
		var changdu = $("#changdu").val();
		var scrq = $("#scrq").val();
		var filePath = $("#filePath").val();
		var fileName = $("#fileName").val();
		var zlscm = $("#addzlscm").val();
		var zlms = $("#addzlms").val();
		var no = $("#no").val();
		//var userId=$("#adduserId").val();
		//var lxm=$("#addlxm").val();
		var data={"zlly":zlly,"zlscm":zlscm,"zlms":zlms,"lxm":lxm,"tip":tip,"zlmc":zlmc,"changdu":changdu,"scrq":scrq,"filePath":filePath,"fileName":fileName
				,"no":no};
		
	
		var tip="jxnr";
		if(lxm.length!=0) {
			if(zlmc.length!=0) {
				$.ajax({
		            type:"POST",
		            url:"updatescwj.action",
		            data:data,
		            success:function(json){
						$('#fileupload').uploadifyClearQueue();
		            	$('#add').dialog('close');	
		            	$("#addzlly").val("");$("#addzlscm").val("");$("#addzlms").val("");$("#addzlmc").val("");
		            	
		            	$.messager.alert('提示','请添加对应知识点!','info',function(){
		            		$('#test').datagrid('reload');
		            		$('#AddZsd1').window('open');
		            		document.getElementById("ggggg3").style.visibility="visible";//注意：用$()方式不好用
		            		document.getElementById("AddZsd1").style.visibility="visible";
		            		$('#zlmc1').val(json.mc);
		            		$('#zlbh1').val(json.zlbh);
		            		$("#wz1").val("0");
		            		$("#zmc1").val(json.CName);
		            		$("#kmc1").val(json.zmc);
		            		$("#jmc1").val("");
		            		$("#website").val("");
		            		bind();
		            	});
				   }
            	});
			} else {
				$.messager.alert('提示信息','资料名称不能为空!','info');
			}
		} else {
			$.messager.alert('提示信息','您还没有上传任何文件!','info');
		}
		
		
	}
	
	//跳到另外一个页面，打开要浏览的资料
	function open(){
		var selected = $('#test').datagrid('getSelected');
		if(selected){
			$('#zxsp').window('open');
    		CFrame.document.location.href='../spdh/dzbj.jsp?zlmc=' + encodeURI(encodeURI(selected.zlmc)) +
    		'&path=' + selected.fpath + '&filename=' + selected.nr + '&zlid=' + selected.zlid;
            // 在此处加上以下代码即可    
			window.event.returnValue = false;
		}else{
			$.messager.alert('提示','请选择一行数据','warning');
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
			        	$('#test2').datagrid('reload');
			        	//$('#bdAdd1').window('open');
			        	var $win;
						$win = $('#bdAdd1').window({
							top:document.body.scrollTop+100,   
				            left:document.body.scrollLeft+300
						});
						$win.window('open');
			        	$('#bdAdd').window('close');
				   }
	        	   
		       }
	      	});
		});
	});
	//如果找到已有资料的话，在选中后就可以将已有资料添加到教学内容中(-----------好的思路----------)
	function AddYiyou(){
		var arr=new Array();
		var tt=0;
		var selected = $('#test2').datagrid('getSelections');
		if(selected){
	    	$.messager.confirm('提示','确认添加么?',function(id){
			    if(id){
			    	
			    	$.each(selected,function(n,value){
			    		var single = value;
				     	$.ajax({
				            type:"POST",
				           url:"BdAdd.action",
				            data:"zlbh="+single.zlbh,
				           success:function(json){
				     		    if(json.tip=="true"){
				     		    	arr.push(single.zlmc);

				     		    }else{}
				     		   if(tt==selected.length-1){
			     		    		if(arr.length!=0){
						     			
						     			$.messager.alert('提示',arr.toString()+'已经在该教学内容中，您不必再添加了！','info',function(r){
							    			$('#test').datagrid('reload');
							    		});
							    	}else{
							    		$('#test').datagrid('reload');
							    	}
							    	
						     	}
				     			tt=tt+1;
					           }
				      	});
				     	
			    	});
			    	
			     	
		   
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
		//Zsd=Zsd.toString();
		var Wz =$("#wz1").val();
		var zlbh = $("#zlbh1").val();
		if(Zsd==null||Zsd==""){
			$.messager.alert('提示','请您选择对应知识点！','info');
		}else{
			if(flag==2){
				if(Wz==""){
					Wz="0";
				}else{
					$.ajax({
			            type:"POST",
			            url:"insertzlzsddy.action",
			            data:{"zlmc":zlmc,"zmc":jmc,"CName":Zmc,
			        		"zsdmc":Zsd,"weizhi":Wz,"zlbh":zlbh},
			           	success:function(){
			        			//flag=3;
			        			
					   }
			  		});
					return true;
				}
			}else{
				$.messager.alert('提示','此知识点不存在！','info');
			}
			
		}
		
	}
	//当提交一个知识点后可以继续添加
	function continueAdd(){
		   var f=false;
		    f=TjZsd();
		    if(f){
		    	$("#wz1").val("0");
				$("#jmc1").val("");
				$("#website").val("");
		    }
			
		
		
	}
	//这是点完成的时候，调用对应的方法，将知识点提交，将窗口关闭
	function over(){
		var f=false;
		 f=TjZsd();
		if(f){
			$.messager.alert('提示','所选知识点已成功添加！');
			quxiao();
		}
		
	}
	//将添加知识点的对话框关闭
	function quxiao(){
		$('#AddZsd1').window('close');
	}
	
	function more(){
		$('#more').window('open');
	}
	
	//关闭窗口时执行的方法（朱永科）
	 $(function(){ 
		  $('#zxsp').window({
      		   onClose:function(){
			  document.frames.CFrame.closeNs();
      	   		}
  	   });
	 });