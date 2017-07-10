<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看试卷</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>
	
	
<script type="text/javascript">
		function getWidth(percent){ 
		    return (document.body.clientWidth-25-11)*percent; 
		}
		$(function(){			
			$('#ck').datagrid({
				title:'查看试卷',
				iconCls:'icon-save',
				nowrap: true,
				striped: true,
				singleSelect:true,
				fitColumns:true,	
				loadMsg:'加载数据,请等待...',
				url:'sjlist.action',
				columns:[[
	                {title:'试卷编号',field:'sjno',width:getWidth(0.1),align:'center'},
	                {title:'试卷名称',field:'zxx',width:getWidth(0.5),align:'left'},
	                {field:'zbh',title:'所属章',width:getWidth(0.2),align:'left',
		                formatter:function(val,rec){ 
							val= rec.courseChapter.CName;
							return val; 
							} 
		                },
		             {field:'c_name',title:'所属课程',width:getWidth(0.2),align:'left',
			             formatter:function(val,rec){ 
							val= rec.courseChapter.TCName;
							return val; 
							} 
			           }
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'查看试卷',
					iconCls:'icon-search',
					handler:function(){
                          cksj();
					}
				}]
			});
		});

		function cksj(){
			var selected = $('#ck').datagrid('getSelections');
				if(selected.length!=0){
					var no=selected[0].sjno;
					var zbh=selected[0].courseChapter.zbh;
					var zxx=selected[0].zxx;
					$.ajax({
					   		type:"POST",
					       	url:"sjxztnr",
					       	data:{"sjbh2":no,"zbh":zbh,"sjname":zxx},
					       	success:function callback(r){
						       	if(r){
						       		$('#cksj').window('open');
						       		as1("CFrame4",599,379);
						        	CFrame4.location="sjda.action";
							       	}
					       		}
					   		});
						}else{
				    		$.messager.alert('警告','请选择一行数据','warning');
				 			}
				}
				
		 function as1(iframe,width,height)
       	 {
       	 var iframe1 = $("#".concat(iframe)); //这里即$("#CFrame4");
       	 iframe1.width(width);
       	 iframe1.height(height); 
       	 }

	</script>
	</head>
	
	<body class="easyui-layout" style="margin-left: 0px; margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
		<div region="center"><table id="ck" fit="true"></table></div>
		<div id="cksj" class="easyui-window"  closed="true" title="查看试卷" style="padding:20px;width:634px;height:456px;"  maximizable="false" minimizable="false" collapsible="false">
			<center>
						<font color="red" size="5">  
			                  <s:property value="sjname" />
			            </font> 
			         </center>
			<div  region="center" border="false" style="background: #fafafa;width:100%;height:100%;">
			
				<iframe  id="CFrame4" src=""   name="CFrame4"  frameborder="0"  scrolling="auto"  >
					
				</iframe> 
		   </div>
		</div>	
	</body>
</html>
   