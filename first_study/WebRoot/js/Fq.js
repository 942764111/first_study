var ss=0;//该变量是用来标志用户是否对角色的权限进行操作的
var fts=[];
//用来对自动完成组件赋值的

//该方法就是利用组件完成自动添加
	$(function(){
		//$.messager.alert('提示','赋权时，应点击行中数据，不要点击行开头的复选框！','info');
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
				   		$("#name").flushCache(); //清空website处的缓存
				   		$("#name").autocomplete(fts,{
							minChars: 0,
							max: 10,
							//cacheLength :1,
							matchSubset :false,
							autoFill: false,
							multiple :false,
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
			title:'角色管理   (赋权时，应点击行中数据，不要点击行开头的复选框！)',
			iconCls:'icon-save',
			striped: true,
			fit:true,
			width:getWidth(1),
			url:'Fqjson.action',
			pageList:[50,40,30,20,15],
			idField:'action',
			sortName:'moduleclass',
			sortOrder:'asc',
			columns:[[		
					{field:'ck',checkbox:true},
					{title:'模块分类名称',field:'moduleclass',width:getWidth(0.1),align:'center',sortable:true},
					{title:'模块名称',field:'module',width:getWidth(0.1),align:'center'},
					{title:'功能名称',field:'gongneng',width:getWidth(0.1),align:'center'},
			        {title:'Actionname',field:'action',width:getWidth(0.1),align:'center'},
			        {title:'功能描述',field:'comments',width:getWidth(0.385),align:'center'},
			        {title:'是否已授权',field:'shifou',width:getWidth(0.2),align:'center'}
					]],
			pagination:true,
			rownumbers:true,
			onClickRow:function(){
					gg();
			},
			toolbar:[{
				id:'btnadd',
				text:'确定用户权限',
				iconCls:'icon-add',
				handler:function(){
					check();
				}
			},'-',{
				id:'btnquery',
				text:'查询',
				iconCls:'icon-search',
				handler:function(){
					$('#query').window('open');
				}
			}],
			onLoadSuccess:function(){
					aaa();
			}
		});
		 $('#test').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
		 
	});
	//该方法是活动不同浏览器的宽度，减去的数是datagrid中的每列的边线和显示行数的列宽
	function getWidth(percent){ 
	    return (document.body.clientWidth-25-25-12)*percent; 
	}
	//在该页面每成功加载一次datagrid时都会调用该方法，在赋权时将用户原来所拥有的权限选中
	function aaa(){
		var select = $('#test').datagrid('getRows');//获得所有行
		for(var i=0;i<select.length;i++){
			if(select[i].shifou=="已拥有"){
				$('#test').datagrid('selectRow',i);
				}
			}
		}
	//如果用户选中或去掉角色的每一项权限就调用该方法，将ss改变。在点击“确定用户权限”时会根据ss的不同出现的提示不同
	function gg(){
		ss=1;
		}
	//这是在单击“确定用户权限”时调用的，若成功调用该方法，就会对角色赋权
	function check(){
		
		if(ss==1){
			$.messager.confirm('提示', '确定为该用户赋权吗?', function(r){
				if (r){
					var selected = $('#test').datagrid('getSelections');
					var arr = new Array();
					for(var i=0;i<selected.length;i++){
						arr[i] = selected[i].action;
					}
					$.ajax({
			            type:"POST",
			           url:"ShouQuan.action",
			            data:"funId="+arr,
			           success:function(){
				           ss=0;
						 $('#test').datagrid('reload');
						 $.messager.alert('提示','赋权成功！');
				          }
			      	});
				}
			});
		}else{
			$.messager.alert('提示','您还没有对用户的权限做出更改，请再更改后在确定！');
		}
		
	}
	//这是查询功能的，会向后台传递两个参数queryType，queryWord
	function query(){
        var queryParams = $('#test').datagrid('options').queryParams;
        queryParams.queryType = $('#type').val();
        queryParams.queryWord = $('#name').val();
        $('#test').datagrid({
            url:'Fqqueryfunction.action'
        });
        $('#query').window('close');
        $('#test').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
			 });
    }