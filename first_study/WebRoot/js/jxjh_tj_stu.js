//此页面的区别jxjh_tj.js之处是在“pagination:true,   //显示分页”上少了detailview
var teaname="";
var dyz=0;
var n1="";
var t=0;
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
			sortName: 'str2',
			sortOrder: 'desc',
			remoteSort: false,
			fit:true,
			fitColumns:true,
			onDblClickRow:function(rowIndex, rowData){
			   location.href='jxjh.action?kch='+rowData.int2+'&xq='+rowData.str2+'&jsbh='+rowData.str1+'&tip=bht_stu'+'&jhid='+rowData.int3+'&courseName='+rowData.str4;
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
							location.href='jxjh.action?kch='+selections[0].int2+'&xq='+selections[0].str2+'&jsbh='+selections[0].str1+'&tip=bht_stu'+
							'&jhid='+selections[0].int3+'&courseName='+selections[0].str4;
							// 在此处加上以下代码既可    
							window.event.returnValue = false;
							
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
