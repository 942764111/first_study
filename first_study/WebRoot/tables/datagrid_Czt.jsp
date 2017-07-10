<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作题管理</title>
   <link href="../css/wjh_main.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="../js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../js/ui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../css/czt.css">
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../js/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../js/czt.js"></script>



	
<script >
var role="";
            $(function(){ 
            	$.extend($.messager.defaults,{  
            	    ok:"确定",  
            	    cancel:"取消"  
            	});  
            	   $('#update').window({
            		   onClose:function(){
                       if(sfxg==1)
                       {
            		   $('#test_t').datagrid('reload');
            		   sfxg=0;
                       }
                       $('#test_t').datagrid('clearSelections');
            	   }
            	   });
            	   $('#search1').window({
            		   onClose:function(){
                       $('#test_t').datagrid('clearSelections');
            	   }
            	   });
            	   $('#deletext').window({
            		   onClose:function(){
                       delid=0;
                      $('#test_t').datagrid('clearSelections');
            	   }
            	   });
            	   
            	   $('#addxxxt').window({
            		   onClose:function(){
            		   xxxtlength=0;
            		   editcount2=0;
            	   }
            	   });

            		               	  	            
    $('#test_t').datagrid({
				title:'操作题管理',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				fit:true,
				fitColumns:true,
				loadMsg:'加载数据,请等待...',
				url:'<%=basePath%>gets', 
				onLoadSuccess:function(){
					var data=$('#test_t').datagrid('getData');
					if(data.total==0)
					{
					      $.messager.alert('信息','没有数据','info');
					}				
				},		 
				 columns:[[
			 {field:'ck',checkbox:true},
             {field:'tihao',title:'操作题号',width:getWidth(0.1)},
             {field:'jytigan',title:'操作题题干描述',width:getWidth(0.8)},
             {field:'fenzhi',title:'分值',width:getWidth(0.04),align:'center'}
        ]],
			    pagination:true,
				rownumbers:true,
				toolbar:[{
				    id:'btnadd',
					text:'新建',
					iconCls:'icon-add',
					handler:add  
				},'-',{
					  text:'追加小题',
		              iconCls:'icon-add',
		              handler:addxxxt
				},'-',{
					  text:'追加答案',
		              iconCls:'icon-add',
		              handler:addxxda
				},'-',{
					  text:'追加文件',
		              iconCls:'icon-add',
		              handler:addxxwj
				},'-',{
					  text:'修改',
		              iconCls:'icon-edit',
		              handler:edit
				},'-',{
					  text:'搜索',
		              iconCls:'icon-search',
		              handler:search
		        },'-',{
					  text:'查看',
		              iconCls:'icon-search',
		              handler:ckxxxx
		        },'-',{
					  text:'大题录入状态',
		              iconCls:'icon-search',
		              handler:cktmxxxx
		        },'-',{
					  text:'批量删除',
		              iconCls:'icon-cancel',
		              handler:pldel
		        },'-',{
					  text:'删除小题文件',
		              iconCls:'icon-cancel',
		              handler:delczt
				},'-',{
					  id:'btn',
					  text:'练习',
		              iconCls:'icon-redo',
		              handler:lianxi
				},'-',{
					  text:'返回组卷页面',
					  iconCls:'icon-back',
		              handler:function(){
						
						 location.href="<%=basePath%>Ttest.action";
						 // 在此处加上以下代码既可    
							window.event.returnValue = false;
					 }
		   		},'-',{
							text:'收藏',
						    iconCls:'icon-add',
				            handler:function(){
		  					$('#scform').form('clear');
		  					addsc();
					   	} 		   			
  			}]
	                     });
	                      $('#test_t').datagrid('getPager').pagination({
			 displayMsg:'当前显示从{from}到{to}共{total}记录',
			 beforePageText:'第',
			 afterPageText:'页'
		 });
	                     role='<%=session.getAttribute("uid")%>';
	                      if(role!="null")
	                      {
	                      var username={"username":role};
	                      $.ajax({
	                   		type:"POST",
	                      	url:"<%=basePath%>getrole",
	                      	data:username,
	                      	dataType:'json',
	                      	success:function callback(r){ 
	                      	   	sfgetrole=r.id;
	                      	}
	                      });
	                      }

	   
	                    

	                	$('#shareit-box').mouseleave(function () {
	                		var selected = $('#tt').datagrid('getSelected');
	                		var index = $('#tt').datagrid('getRowIndex',selected);
	                		if(index+1>lurulength)
	                		{
	                		editrow(index);	
	                		selected.tg=CFrame6.getvalue();
	                        saverow(index);
	                        editrow(index); 
	                		}   
	                		$(this).hide();
	                	});

	                	$('#shareit-box1').mouseleave(function () { 			 
	                		$(this).hide();
	                	});
	                	$('#shareit-box2').mouseleave(function () { 
	                		var selected = $('#xxxt').datagrid('getSelected');
	                		var index = $('#xxxt').datagrid('getRowIndex',selected);
	                		if(editcount2==1&&index==xxxtlength)
	                		{
	                			selected.tg=CFrame8.getvalue();
		                		saverow2(index);
		                		editrow2(index);
	                		}			 
	                		$(this).hide();
	                	});

	                	$('#shareit-box3').mouseleave(function () { 
		                	if(editcount4==1)
		                	{
		                		var selected = $('#xt1').datagrid('getSelected');
		                		var index = $('#xt1').datagrid('getRowIndex',selected);
		                		selected.tg=CFrame9.getvalue();
		                		saverow4(index);
		                		editrow4(index);
		                	}			 
	                		$(this).hide();
	                	});

	                	$('#shareit-box4').mouseleave(function () { 			 
	                		$(this).hide();
	                	});
	                	$('#shareit-box5').mouseleave(function () { 			 
	                		$(this).hide();
	                	});
	                	$('#shareit-box6').mouseleave(function () { 			 
	                		$(this).hide();
	                	});
	                         });
                
   
            var yzsxzj = [
              		    {productid:'1',name:'工作表插入',selected:true},
              		    {productid:'2',name:'工作表删除'},
              		    {productid:'3',name:'工作表移动'},
              		    {productid:'4',name:'工作表复制'},
              		    {productid:'5',name:'工作表重命名'},
              		    {productid:'6',name:'行插入'},
              		    {productid:'7',name:'行删除'},
              		    {productid:'8',name:'行移动'},
              		    {productid:'9',name:'列插入'},
              		    {productid:'10',name:'列移动'},
              		    {productid:'11',name:'列删除'},
              		    {productid:'12',name:'单元格合并'},
              		    {productid:'13',name:'单元格编辑'},
              		    {productid:'14',name:'行高'},
              		    {productid:'15',name:'列宽'},
              		    {productid:'16',name:'单元格字体'},
              		    {productid:'17',name:'单元格字号'},
              		    {productid:'18',name:'单元格水平对齐'},
              		    {productid:'19',name:'单元格垂直对齐'},
              		    {productid:'20',name:'单元格边框'},
              		    {productid:'21',name:'单元格底纹类型'},
              		    {productid:'22',name:'单元格字体颜色'},
              		    {productid:'23',name:'单元格数字类型'},
              		    {productid:'24',name:'数据填充'},
              		    {productid:'25',name:'单元格公式'},
              		    {productid:'26',name:'图表类型'},
              		    {productid:'27',name:'分类轴'},
              		    {productid:'28',name:'数值轴'},
              		    {productid:'29',name:'坐标轴字体'},
              		    {productid:'30',name:'坐标轴字号'},
              		    {productid:'31',name:'坐标轴颜色'},
              		    {productid:'32',name:'图列'},
              		    {productid:'33',name:'图表数据序列'},
              		    {productid:'34',name:'图表标题文字'},
              		    {productid:'35',name:'图表标题字体'},
              		    {productid:'36',name:'图表标题字号'},
              		    {productid:'37',name:'图表标题颜色'},
              		    {productid:'38',name:'高级筛选'},
              		    {productid:'39',name:'排序'},
              		    {productid:'40',name:'汇总'},
              		    {productid:'41',name:'数据透视表行字段'},
              		    {productid:'42',name:'数据透视表数据项'},
              		    {productid:'43',name:'数据透视表列字段'},
              		    {productid:'44',name:'数据项的汇总方式'}
              		];

   
   
   function getWidth(percent){ 
    return (document.body.clientWidth)*percent; 
} 
   function getHeight(percent){ 
    return document.body.clientHeight*percent; 
} 
 
   

   var xiazaitihao;
   var dt=0;
 
   
     function productFormatter(value){
			for(var i=0; i<allzsd.length; i++){
				if (allzsd[i].id == value) return allzsd[i].value;
			}
			return -1;
		}
    function     productFormatter1(value)
    {
    for(var i=0; i<yzsxzj.length; i++){
				if (yzsxzj[i].productid == value) return yzsxzj[i].name;
			}
			return value;
    }
    function     productFormatter2(value)
    {
    for(var i=0; i<yzsxzj.length; i++){
				if (yzsxzj[i]. name== value) return yzsxzj[i].productid;
			}
			return value;
    }

    function productFormatter3(value){
		for(var i=0; i<allzsd.length; i++){
			if (allzsd[i].value== value) return allzsd[i].id;
		}
		return -1;
	}


    function productFormatter4(value){
		for(var i=0; i<zsdkeydtos.length; i++){
			if (zsdkeydtos[i].id==value) return zsdkeydtos[i].value;
		}
		return -1;
	}
    function productFormatter5(value){
		for(var i=0; i<kcmc.length; i++){
			if (kcmc[i].id==value) return  kcmc[i].value;
		}
		return value;
	}
    function productFormatter6(value){
		for(var i=0; i<zmczj.length; i++){
			if (zmczj[i].id==value) return  zmczj[i].value;
		}
		return value;
	}
    function productFormatter7(value){
		for(var i=0; i<jmczj.length; i++){
			if (jmczj[i].id==value) return  jmczj[i].value;
		}
		return value;
	}


    function openwj()
    { 
    	if(download==0)
		{
			 $.messager.alert('错误','文件没有下载完毕','info');
			return;
		}
   	 excelpj.wjmc=wjm;
   	 excelpj.openexcel();
    }	
    
      
       var dir;
       var mc;
       var path;
       var pfjs=0;
      function excelexercise()
      {
   	   excelpj.wjmc=wjm;
   	   var exist=excelpj.existwj();
   	   if(exist!=1)
   	   {
   		   $.messager.alert('错误','文件不存在是否改变文件名称,删除,移动等操作?','info');
   	   }
   	   else
   	   {
      $.messager.alert('等待','评分需要时间,请等待','info');
     var selected={"tihao":xiazaitihao}; 
     var defen= new Array();
     var id;
      
      $.ajax({
   		type:"POST",
   	   url:"<%=basePath%>getpfda", 
   		data:selected,
   		dataType:'json',
   		success:function callback(r){ 
         id=r.id;
   		for(var i=0;i<id;i++)
   		{
   	   		var da1=r.cztdalist[i].da;
   	   		var da2=r.cztdalist[i].cda;
   		   var answers=r.cztdalist[i].sheetname+"|"+r.cztdalist[i].wz+"|"+da1+"|"+r.cztdalist[i].carea+"|"+da2;
   		   var fen=r.cztdalist[i].bufz;
   		   var bh=r.cztdalist[i].yzsx;
   		   var fen1=0;
   		   excelpj.fenzhi=fen;
   		   excelpj.daan=answers;
   		   excelpj.bianhao=bh;
   		   excelpj.wjmc=wjm; 
   		   var fen1=excelpj.pf(); 
   		   defen[i]=fen1;   
   		}
   	var df="";
   	for(var i=0;i<id-1;i++)
   	{
   	   df=df+"defen["+i+"]="+defen[i]+"&";
   	}
   	var x=id-1;
   	df=df+"defen["+x+"]="+defen[id-1]+"&tihao="+xiazaitihao;
   	$.ajax({
   		type:"POST",
       	url:"<%=basePath%>putpf",
       	data:df,
       	dataType:'json',
       	success:function callback(r){ 
       	if(r.message=="success")
       	{	
       	$.messager.alert('评分','评分结束 !','info');	
       	pfjs=1;
       	}
       	}
   		});
     	}
   	}); 
   	}
      }


   	
   	
   	function bg()
   	{
   	if(pfjs==1)
   	{
   		$('#treegrid').window('open');
   		$('#treegrid').window('resize',{left:getWidth(0.15),top:getHeight(0.15),width:getWidth(0.76),height:getHeight(0.71)});
   		   $('#df').treegrid({
   		    fit:true,
   		    fitColumns:true,
   		    nowrap: false,
   		    rownumbers: true,
   		    animate:true,
   		    collapsible:true,
   		    url:'getbg',
   		    idField:'id',
   		    treeField:'id',
   		    columns:[[
   		    {field:'id',title:'编号',width:getWidth(0.74)*0.1},
   		    {field:'jytg',title:'简易题干',width:getWidth(0.74)*0.5,formatter:function(value){ return  productFormatter1(value);   }},
   		    {field:'sjdf',title:'实际得分',width:getWidth(0.74)*0.1},
   		    {field:'ydf',title:'应得分',width:getWidth(0.74)*0.1,align:'right'},
   		    {field:'zsd',title:'知识点',width:getWidth(0.74)*0.2,align:'right'}
   		     ]]
   		   }); 
   	 }
   	 else
   	 {
   	 	$.messager.alert('未评分','没有进行判卷','warning');
   	 }
   	 }


      
        
  

   
   var wjm="";
   var zzlx=0;
   var download=0;
   function lianxi()
   {
	//   if(document.all.excelpj.object == null) { 
	//	   $.messager.alert('警告','activex控件没有安装 ，请查看说明设置 internet设置','info'); 
	//	   return;
	//   }
	   if(zzlx==1)
	   {
		   $.messager.alert('警告','正在练习','info'); 
		   return;
	   }
	   var rows = $('#test_t').datagrid("getSelections");
		if(rows.length>1)
		{
			$.messager.alert('警告','请选择一行数据','warning');
			return;
		}
   var selected = $('#test_t').datagrid('getSelected');
	if(selected){
	xiazaitihao=selected.tihao;
	var select = {"tihao":selected.tihao};
			$.ajax({
				type:"POST",
				url:"<%=basePath%>getexercise",
				data:select,
				dataType:'json',
				success:function callback(data){
				if(data.message=="no")
				 {
				  $.messager.alert('警告','没有文件或者答案!','warning');
				  $('#test_t').datagrid('clearSelections');
				 }
				 else
				 {
					 var p = $('#exercise').window({
							onBeforeOpen:function(){
							  $('#exercise').window('resize',{left:getWidth(0.2),top:getHeight(0.05),width:getWidth(0.7),height:getHeight(0.7)});
						}
						});
				  $('#exercise').window('open');
				  zzlx=1;
				  $('#exercise1').append('<div id="exer"  align="left"  style="padding:16px">'+data.message+'</div>');
				
				    var tihao={"tihao":xiazaitihao};
				    $.ajax({
						type:"POST",
				    	url:"<%=basePath%>getwjmc",
				    	data:tihao,
				    	dataType:'json',
				    	success:function callback(r){ 
					    	excelpj.serverurl='<%=basePath%>download?tihao='+xiazaitihao;
				           var clienturls=r.filename;
				           excelpj.tihao=xiazaitihao;
				           excelpj.clienturl="D:\\myexcel\\"+xiazaitihao+"\\"+clienturls;
				           excelpj.wjmc="D:\\myexcel\\"+xiazaitihao+"\\"+clienturls;
				           if(download==0)
				           {
				           var xz=excelpj.Downloadwj();
				           download=1;
				           }
				           wjm="D:\\myexcel\\"+xiazaitihao+"\\"+r.filename;
				    	}
						});
				  }
				}
			});
	}else{
	$.messager.alert('警告','请选择一行数据','warning')};
    }
   

 

   var tihao;  
   var sfgetrole=-1;             
   function add(){  
   if(role=="null")
   {
	   $.messager.alert('警告','时间超时,请重新登录','warning');
	   return;
   }
   else  if(sfgetrole!=0)
   {
 	   $('#add').window('open'); 
   	   $('#add').window('move',{left:getWidth(0.2),top:getHeight(0.1)});
   	   as1("CFrame4",800,400);  
   	   CFrame4.location="../cztjsp/cztdadd.jsp";
    }
	   else
	   {
			 $.messager.alert('警告','您无权操作','warning');
			 return;
	   }

   }
   

 

   function tijiaonext()
   {
	   if(dt==0)
	   {
		   $.messager.alert('警告','本题没有录入','warning');
	   }
	   else if(dtvalue!=document.all["htmlbox_icon_set_default"].value)
	   {
		   $.messager.alert('警告','请先提交本题更新数据','warning');
	   }
	   else
	   {
		   $.messager.confirm('信息','是否录入下一题？',function(select){
			   if(select)
			   {
		   close3();
		   add();
			   }
		   });
	   }
   }
   
   

   function luruwj(cztdtihao,id)
   {
     $('#luruwj').window('open');
      if(id==1)
      {
          $.messager.alert('大题录入','大题录入成功','info');
      }
      else if(id==2)
      {
    	  $.messager.alert('大题录入','大题修改成功','info');
      }
     as1("CFrame3",380,250);
    CFrame3.location="../cztjsp/New4.jsp?tihao="+cztdtihao;
   }

 
 
	


function s(index,box,datagrid,iframe)
{
	var top2=60+index*20;
	var left2=200;	
	$('#'+box).show();
	$('#'+box).css({'top':top2, 'left':left2});
	as1(iframe,530,200);
	$('#'+datagrid).datagrid('selectRow',index);
	var selected = $('#'+datagrid).datagrid('getSelected');
	document.getElementById(iframe).src=encodeURI("../cztjsp/cztadd.jsp?value="+selected.tg);
}



var xtlength=0;
var lurulength=0;
function xiaotiluru(cztdtihao,id)
{

	tihao=cztdtihao;
	$('#addxt').window('open'); 
	$('#addxt').window('resize',{left:getWidth(0.2),top:getHeight(0.15),width:getWidth(0.76),height:getHeight(0.68)});
	if(id==1)
	{
		$.messager.alert('大题录入','大题已被录入','info');
	}
	else if(id==2)
	{
		$.messager.alert('修改大题','大题修改成功','info');
	}
	$('#tt').datagrid({
	fit:true,
	fitColumns:true,
	singleSelect:true, 
	loadMsg:'加载数据,请等待...',
	url:'<%=basePath%>getallczt?tihao='+cztdtihao, 
	onLoadSuccess:function(){
		var data=$('#tt').datagrid('getData');
		xtlength=data.total;
		lurulength=data.total;			
	},
	onClickRow:function(index,data){
			s(index,"shareit-box","tt","CFrame6");
	},
	columns:[[
	{field:'th',title:'题号',width:80},
	{field:'sxh',title:'顺序号',width:80},
	{field:'tg',title:'小题描述',width:400,rowspan:1},
	{field:'csrcs',title:'测试人数',width:100,editor:'numberbox'},
	{field:'zqrcs',title:'正确人数',width:100,editor:'numberbox'},
	{field:'xtfz',title:'分值',width:80,editor:'numberbox'},
	{field:'nyd',title:'难易度',width:80,editor:'numberbox'}
	]],
	toolbar:[{
	text:'增加一行',
	iconCls:'icon-add',
	handler:addrow
	},{
	text:'录入小题',
	iconCls:'icon-save',
	handler:saveall
	},{
	text:'录入答案',
	iconCls:'icon-save',
	handler:saveda
	},{
		text:'删除',
		iconCls:'icon-cancel',
		handler:cancelall
	}],
	onBeforeEdit:function(index,row){
	row.editing = true;
	$('#tt').datagrid('refreshRow', index);
	},
	onAfterEdit:function(index,row){
	row.editing = false;
	$('#tt').datagrid('refreshRow', index);

	}
	}).datagrid('loadData',{total:0,rows:[]});
}


function editrow(index){
$('#tt').datagrid('beginEdit', index);
}
function saverow(index){
$('#tt').datagrid('endEdit', index);
}



function addrow(){	
$('#tt').datagrid('appendRow',{
th:tihao,
sxh:xtlength+1,
tg:'',
nyd:'',
csrs:'',
zqrs:'',
fz:''
});
$('#tt').datagrid('selectRow',xtlength);
editrow(xtlength);
xtlength++;
}


function saveall(){
	    var id=0;
        $('#tt').datagrid('acceptChanges');
        if(lurulength==xtlength)
        {
        	$.messager.alert('信息','当前没有增加小题','info');
        }
	    for(var i=lurulength;i<xtlength;i++)
	    {    	
	    $('#tt').datagrid('clearSelections');
		$('#tt').datagrid('selectRow',i);
		var selected = $('#tt').datagrid('getSelected');
		 if(selected.tg.length==0||selected.nyd.length==0||selected.csrcs.length==0||selected.zqrcs.length==0||selected.xtfz.length==0)
		 {
		    $.messager.alert('警告','请将内容输入完全','info');  var indexs = $('#tt').datagrid('getRowIndex',selected);
		   editrow(indexs);
		   id++;
		   break;
		}
		 else if(selected.tg.length>80)
		 {
		  $.messager.alert('警告','长度太长,请在80字之内','warning');
		   var indexs = $('#tt').datagrid('getRowIndex',selected);
		   editrow(indexs);
		   id++;
		   break;
		}
		else if(selected.tg=="<P>&nbsp;</P>"||selected.tg=="<P></P>")
		{
			 $.messager.alert('警告','请输入小题描述','warning');
			   var indexs = $('#tt').datagrid('getRowIndex',selected);
			   editrow(indexs);
			   id++;
			   break;
		}
	
	    }
	    if(id==0)
	    {
		if(xtlength==lurulength)
		{
			return;
		}
	    var select="";
	    var haoma=0;
	    for(var i=lurulength;i<xtlength-1;i++)
	    {
	    $('#tt').datagrid('clearSelections');
	   	$('#tt').datagrid('selectRow',i);
	   	var selected = $('#tt').datagrid('getSelected');
	   	var tg=selected.tg;
	 	tg=tg.replace("&","$^");
	   	select=select+'rows['+haoma+'].th='+selected.th+'&rows['+haoma+'].sxh='+selected.sxh+'&rows['+haoma+'].tg='+tg+'&rows['+haoma+'].nyd='+selected.nyd+'&rows['+haoma+'].zqrcs='+selected.zqrcs+'&rows['+haoma+'].csrcs='+selected.csrcs+'&rows['+haoma+'].xtfz='+selected.xtfz+'&';
         haoma++;
	   	}
	    $('#tt').datagrid('clearSelections');
	   	$('#tt').datagrid('selectRow',xtlength-1);
	   	var selected = $('#tt').datagrid('getSelected');
	   	var tg=selected.tg;
	   	tg=tg.replace("&","$^");
		select=select+'rows['+haoma+'].th='+selected.th+'&rows['+haoma+'].sxh='+selected.sxh+'&rows['+haoma+'].tg='+tg+'&rows['+haoma+'].nyd='+selected.nyd+'&rows['+haoma+'].zqrcs='+selected.zqrcs+'&rows['+haoma+'].csrcs='+selected.csrcs+'&rows['+haoma+'].xtfz='+selected.xtfz;
 
	    $.ajax({
			type:"POST",
			 url:"<%=basePath%>plluruxt",  
			data:select,
			dataType:'json',
			success:function callback(r){
				 $.messager.alert('小题录入',r.message,'info');	
				 lurulength=xtlength;
			}
			}); 		
         }
}



function cancelall()
{
	var selected = $('#tt').datagrid('getSelected');
	if(!selected)
	{
		 $.messager.alert('警告','请选中删除的行','warning');
		return;
	}
	 var index = $('#tt').datagrid('getRowIndex',selected);
	 if(index+1<=lurulength)
	 {
		 $.messager.alert('警告','此题已被录入','warning');	
	 }
	 else
	 {
	 var sxh=selected.sxh;
     $('#tt').datagrid('deleteRow',index);
     xtlength--;
     for(var i=sxh-1;i<xtlength;i++)
     {
    	$('#tt').datagrid('selectRow',i);
 		var selected = $('#tt').datagrid('getSelected');
        editrow(i);
        selected.sxh=i+1;
        saverow(i);
     }
	 }
	
}

	










function  saveda(){
	var rows=$('#tt').datagrid('getSelections');
	if(rows.length==0)
	{
		 $.messager.alert('警告','请选择数据','info');
		return;
	}
	else if(rows.length>1)
	{
		 $.messager.alert('警告','请选择一行数据','warning');
		 return;
	}
  	var selected = $('#tt').datagrid('getSelected');
	var indexs = $('#tt').datagrid('getRowIndex',selected);
	if(indexs+1<=lurulength)
	{
	addxxda();
	datihaosxx.value=selected.th;
	xiaotishunxusxx.value=selected.sxh;
	datihaosxx.disabled=true;
    xiaotishunxusxx.disabled=true;
	}
	else
	{
		 $.messager.alert('警告','请先录入小题','info');
	}
}





      var wjth;
      var alldel="";
      var delid=0;
	  function delczt()
	  {
		   if(role=="null")
		   {
			   $.messager.alert('警告','时间超时,请重新登录','warning');
			   return;
		   }
		   else  if(sfgetrole!=0)
		   {	
			var rows=$('#test_t').datagrid("getSelections");
			if(rows.length==0)
			{
				$.messager.alert('警告','请选择要删除的数据','warning');
				return;
			}
            alldel=rows;
            $('#test_t').datagrid('clearSelections');
            var indexs = $('#test_t').datagrid('getRowIndex',alldel[delid]);
    		$('#test_t').datagrid('selectRow',indexs);
    		delid++;
    		var selected = $('#test_t').datagrid('getSelected');
			wjth=selected;
		  $('#deletext').window('open');
		     $('#deletext').window('resize',{left:getWidth(0.1),top:getHeight(0.05),width:getWidth(0.81),height:getHeight(0.68)});
     

		       		$('#ss').datagrid({
					fit:true,
	                fitColumns:true,
	                border:false,
					loadMsg:'加载数据,请等待...', 
					onClickRow:function(index,data){
		    			s(index,"shareit-box1","ss","CFrame7");
		    	},
					onLoadSuccess:function(){
						var data=$('#ss').datagrid('getData');
						if(data.total==0)
						{
						      $.messager.alert('信息','操作题小题没有数据','info');
						}				
					},
					url:'<%=basePath%>getallczt?tihao='+selected.tihao, 
					 columns:[[
                                 {field:'ck',checkbox:true},
					             {field:'th',title:'操作题号',width:100,align:'center' },
					             {field:'sxh',title:'小题题号',width:100,align:'center'},
					             {field:'tg',title:'小题题干',width:300,align:'center'},
					             {field:'xtfz',title:'分值',width:80},
					             {field:'nyd',title:'难易度',width:80},
					             {field:'csrcs',title:'测试人数',width:100,align:'center'},
					             {field:'zqrcs',title:'正确人数',width:100,align:'center'}     
					            ]],
					            rownumbers:true,
					toolbar:[{
						  text:'批量删除小题 ',
			              iconCls:'icon-cancel',
			              handler:pldelxt
					},'-',{
						  text:'删除此小题答案',
			              iconCls:'icon-cancel',
			              handler:deloda
					},'-',{
						  text:'删除文件  ',
			              iconCls:'icon-cancel',
			              handler:delwj
					},'-',{
						  text:'删除下一操作题  ',
			              iconCls:'icon-cancel',
			              handler:delnextczt
					}] 
					});	 
		    }
		   else
		   {
				 $.messager.alert('警告','您无权操作','warning');
				 return;
		   }	
		  }



  function delnextczt()
  {
	    if(alldel.length<delid+1)
	    {      
		    $.messager.alert('信息','没有下一删除的操作题','info');
		    return;
	    }
	  $.messager.confirm('删除','确定删除下一操作题',function(s){
			if(s){
	    $('#test_t').datagrid('clearSelections');
	    var indexs = $('#test_t').datagrid('getRowIndex',alldel[delid]);
		$('#test_t').datagrid('selectRow',indexs);
		delid++;
		var selected = $('#test_t').datagrid('getSelected');
		wjth=selected;
		$.ajax({
			type:"POST",
			url:'<%=basePath%>getallczt?tihao='+selected.tihao,
			dataType:'json',
			success:function callback(data){
				$('#ss').datagrid('loadData',{total:data.total,rows:data.rows})
			}
		});
			}});
  }
	  

function pldelxt()
{
	var rows=$('#ss').datagrid("getSelections");
	if(rows.length==0)
	{
	    $.messager.alert('删除','请选择删除行','info');
	}
	else
	{
	$.messager.confirm('删除','确定删除这些数据？',function(selected){
	if(selected){

	var  cztdtos="tihao="+rows[0].th+"&";
	for(var i =0;i<rows.length-1;i++){
	       cztdtos=cztdtos+"delsxh["+i+"]="+rows[i].sxh+"&";
	   }
	   var  length=rows.length-1;
	  cztdtos=cztdtos+"delsxh["+length+"]="+rows[length].sxh;
	    		$.ajax({
			type:"POST",
			url:"<%=basePath%>delczt",
			data:cztdtos,
			dataType:'json',
			success:function callback(data){
				$.messager.alert('警告',data.message,'info',function(){$('#ss').datagrid('reload');});
				
			}
		});
	}});
	}
}


   
  
   function delwj()
    {
   $('#deletewj').window('open');
   $('#deletewj').window('resize',{left:getWidth(0.15),top:getHeight(0.15),width:getWidth(0.8),height:getHeight(0.68)});

		    $('#shanchuwj').datagrid({
				nowrap: false,
				fit:true,
                fitColumns:true,
				striped: true,
				collapsible:true,
				onLoadSuccess:function(){
				var data=$('#shanchuwj').datagrid('getData');
				if(data.total==0)
				{
				      $.messager.alert('信息','没有数据','info');
				}				
			},
				url:'<%=basePath%>getallwj?tihao='+wjth.tihao, 
				loadMsg:'加载数据,请等待...',
				 columns:[[
				           {field:'ck',checkbox:true},
			 {field:'bh',title:'文件编号',width:getWidth(0.74)*0.1,align:'center'},
			 {field:'dtth',title:'大题号 ',width:getWidth(0.74)*0.1,align:'center'}, 
             {field:'wjmc',title:'文件名 称',width:getWidth(0.74)*0.25,align:'center'},
             {field:'ms',title:'文件描述',width:getWidth(0.74)*0.55,align:'center'}  
          ]],
				rownumbers:true,
				toolbar:[{
					  text:'删除文件 ',
		              iconCls:'icon-cancel',
		              handler:pldelwj
				}] 
				});
    }
    
    
    
    function pldelwj()
    {

      var rows = $('#shanchuwj').datagrid("getSelections");
    if(rows.length==0)
    {
             $.messager.alert('选择删除行','请选择删除 行','warning');
    }
    else
    { $.messager.confirm('删除','确定删除数据？',function(selected){
		if(selected){
        var  bianhao="";
         for(var i =0;i<rows.length-1;i++){
                bianhao=bianhao+"bianhao["+i+"]="+rows[i].bh+"&";
	        }
	        var  length=rows.length-1;
	        bianhao=bianhao+"bianhao["+length+"]="+rows[length].bh;
	         		$.ajax({
				type:"POST",
				url:"<%=basePath%>pldelwj",
				data:bianhao,
				dataType:'json',
				success:function callback(data){
				  $.messager.alert('批量删除文件',data.message,'info');
				  for(var i =0;i<rows.length;i++){
		          var index = $('#shanchuwj').datagrid('getRowIndex',rows[i]);
		          $('#shanchuwj').datagrid('deleteRow',index);	
	            }
				}
			});
		}});
    }
     
    }
    

    
 
  
  var delda;
  function deloda()
{


  var rows = $('#ss').datagrid("getSelections");
  if(rows.length==0)
  {
           $.messager.alert('选择删除行','请选择删除行','warning');
          return;
  }
  else  if(rows.length>1)
  {
	  $.messager.alert('选择删除行','请选择一条记录','warning');
	  return;
  }
  var selected = $('#ss').datagrid('getSelected');
  delda=selected;
	if(selected)
	{
	    
	         $('#deleteda').window('open');
	         $('#deleteda').window('resize',{left:getWidth(0.15),top:getHeight(0.15),width:getWidth(0.85),height:getHeight(0.68)}); 
		     		$('#delanswer').datagrid({
				fit:true,
                fitColumns:true, 
				onLoadSuccess:function(){
						var data=$('#delanswer').datagrid('getData');
						if(data.total==0)
						{
						      $.messager.alert('信息','没有数据','info');
						}				
					},
				url:'<%=basePath%>getanswers?tihao='+selected.th+'&bh='+selected.sxh, 
				loadMsg:'加载数据,请等待...',
				 columns:[[
                             {field:'ck',checkbox:true},
			   	             {field:'th',title:'操作题号',width:130,align:'center' },
			 	             {field:'sxh',title:'小题题号',width:130,align:'center'},
				             {field:'bzh',title:'步骤号',width:100,align:'center'},
				             {field:'zsdmc',title:'知识点名称',width:220},
				             {field:'bufz',title:'分值',width:80},
				             {field:'sheetname',title:'sheetposition',width:200,align:'center'},
				             {field:'position',title:'位置',width:80,align:'center'}, 
				             {field:'answers',title:'答案',width:350,align:'center'},  
				             {field:'yzsx',title:'原子属性',width:220,align:'center',formatter:function(value){
				                                                                                                return  productFormatter1(value);   }}  
				          ]],      
				rownumbers:true,
					toolbar:[{
					  text:'批量删除答案 ',
		              iconCls:'icon-cancel',
		              handler:pldelda
				},'-',{
					  text:'删除所有答案  ',
		              iconCls:'icon-cancel',
		              handler:delallda
				}] 
				});
			
				}
				 else{
	$.messager.alert('警告','请选择数据','warning');
	}

}
  
  
  function pldelda()
  {
   
    var rows = $('#delanswer').datagrid("getSelections");
    if(rows.length==0)
    {
             $.messager.alert('选择删除行','请选择删除 行','warning');
    }
    else
    { $.messager.confirm('删除','确定删除该所有答案？',function(selected){
		if(selected)
			{
        var  bianhao="tihao="+rows[0].th+"&bh="+rows[0].sxh+"&";
         for(var i =0;i<rows.length-1;i++){
                bianhao=bianhao+"bzhs["+i+"]="+rows[i].bzh+"&";
	        }
	        var  length=rows.length-1;
	        bianhao=bianhao+"bzhs["+length+"]="+rows[length].bzh;
	         		$.ajax({
				type:"POST",
				url:"<%=basePath%>pldelda",
				data:bianhao,
				dataType:'json',
				success:function callback(data){
				  $.messager.alert('批量删除答案',data.message,'info',function(){ $('#delanswer').datagrid('reload');});
				}
			});
			}});
    }
 
  }




  function delallda()
  {
	  $.messager.confirm('删除','确定删除该数据？',function(selected){
			if(selected){
  var bh={"tihao":delda.th,"bh":delda.sxh};
    $.ajax({
		type:"POST",
		url:"<%=basePath%>delallda",
		data:bh,
		success:function  callback(r){	
		$('#delanswer').datagrid('reload');
		$.messager.alert('删除所有答案',r.message,'info');
		}
		});	
  }});
 } 
    
   


   

  
  function  pldel()
  {
	  if(role=="null")
	   {
		   $.messager.alert('警告','时间超时,请重新登录','warning');
		   return;
	   }
	   else  if(sfgetrole!=0)
	   {  
     var rows = $('#test_t').datagrid("getSelections");
       if(rows.length==0)
        {
             $.messager.alert('删除','请选择删除行','info');
        }
        else
        {
       
            $.messager.confirm('删除','确定后 将删除 大题 ,所有小题 ,文件和答案    删除数据数据无法回复,确认要删除?',function(selected){
		if(selected){
  
         var  bianhao="";
         for(var i =0;i<rows.length-1;i++){
                bianhao=bianhao+"bianhao["+i+"]="+rows[i].tihao+"&";
	        }
	        var  length=rows.length-1;
	        bianhao=bianhao+"bianhao["+length+"]="+rows[length].tihao;
	         		$.ajax({
				type:"POST",
				url:"<%=basePath%>pldel",
				data:bianhao,
				dataType:'json',
				success:function callback(data){
				  $.messager.alert('删除',data.message+"  确定后刷新数据",'info',function(){
						  $('#test_t').datagrid('reload');
						  $('#test_t').parent().find("div .datagrid-header-check").children("input[type='checkbox']").eq(0).attr("checked", false);					  
				  });			  	  
				}
			});
        }});
        }
	    }
		   else
		   {
				 $.messager.alert('警告','您无权操作','warning');
				 return;
		   }   
  }
        
            


   
   
 
     
     var xxsxh;
     var xxxtlength=0;
     function addxxxt()
     {
    	if(role=="null")
  	   {
  		   $.messager.alert('警告','时间超时,请重新登录','warning');
  		   return;
  	   }
  	   else  if(sfgetrole!=0)
  	   {
       $('#addxxxt').window('open');    
       $('#addxxxt').window('resize',{left:getWidth(0.1),top:getHeight(0.15),width:getWidth(0.8),height:getHeight(0.68)}); 
         
$('#xxxt').datagrid({
fit:true,
fitColumns:true,
singleSelect:true,
onClickRow:function(index,data){
	s(index,"shareit-box2","xxxt","CFrame8");
},
columns:[[
{field:'th',title:'题号',width:80,editor:'numberbox'},
{field:'tg',title:'题干',width:300},
{field:'nyd',title:'难易度',width:100,editor:'numberbox'},
{field:'csrs',title:'测试人数',width:100,editor:'numberbox'},
{field:'zqrs',title:'正确人数',width:100,editor:'numberbox'},
{field:'fz',title:'分值',width:80,editor:'numberbox'}
]],
toolbar:[{
text:'增加一行',
iconCls:'icon-add',
handler:addrow2
},{
text:'录入此小题',
iconCls:'icon-save',
handler:saveall2
},{
text:'录入答案',
iconCls:'icon-save',
handler:savextda
},{
	text:'取消',
	iconCls:'icon-cancel',
	handler:cancelall2
	}],
onBeforeEdit:function(index,row){
row.editing = true;
$('#xxxt').datagrid('refreshRow', index);
editcount2++;
},
onAfterEdit:function(index,row){
row.editing = false;
$('#xxxt').datagrid('refreshRow', index);
editcount2--;
}
}).datagrid('loadData',{total:0,rows:[]});
  	    }
		   else
		   {
				 $.messager.alert('警告','您无权操作','warning');
				 return;
		   }
}
     
var editcount2=0;
function editrow2(index){
$('#xxxt').datagrid('beginEdit', index);
}
function saverow2(index){
$('#xxxt').datagrid('endEdit', index);
}


function addrow2(){
if(editcount2>0)
{
	$.messager.alert('警告','当前有一条记录在添加','warning');
	return;
}
$('#xxxt').datagrid('appendRow',{
th:'',
tg:'',
nyd:'',
csrs:'',
zqrs:'',
fz:''
});
editrow2(xxxtlength);
$('#xxxt').datagrid('selectRow',xxxtlength);
}
function saveall2(){
$('#xxxt').datagrid('acceptChanges');
var selected = $('#xxxt').datagrid('getSelected');
if(!selected)
{
	$.messager.alert('警告','选中录入的小题','warning');
	return ;
}
var indexs = $('#xxxt').datagrid('getRowIndex',selected);
		if (selected){
	    if(indexs+1<=xxxtlength)
	    {
	    	$.messager.alert('警告','此小题已被录入','warning');
	    }
	    else if(selected.tg.length>80)
		{
		  $.messager.alert('长度过长','长度太长,请在80字之内','warning');
		  var indexs = $('#xxxt').datagrid('getRowIndex',selected);
		  editrow2(indexs);
		}
		else if(selected.th.length==0||selected.tg.length==0||selected.nyd.length==0||selected.csrs.length==0||selected.zqrs.length==0||selected.fz.length==0)
		{
		    $.messager.alert('小题内容不完全','请将内容输入完全','warning');
		      var indexs = $('#xxxt').datagrid('getRowIndex',selected);
		      editrow2(indexs);
		}
		else
		{
			var select={"czt.id.dtTh":selected.th,"czt.tg":selected.tg,"czt.nyd":selected.nyd,"czt.xtfz":selected.fz,"czt.csrcs":selected.csrs,"czt.zqrcs":selected.zqrs};
		 $.ajax({
		type:"POST",
		 url:"<%=basePath%>addxxxt",  
		data:select,
		dataType:'json',
		success:function callback(r){
			 $.messager.alert('录入小题',r.message,'info');
		if(r.message!="小题录入成功")
		{
		  var indexs = $('#xxxt').datagrid('getRowIndex',selected);
	       $('#xxxt').datagrid('deleteRow',indexs); 
	     }
	       if(r.message=="小题录入成功")
	       {     
		   xxxtlength=xxxtlength+1;    
	       xxsxh=r.id;
	       }
		}
		}); 
		}
	}
}


function cancelall2(){
	if(editcount2!=0)
	{
    $('#xxxt').datagrid('deleteRow',xxxtlength); 
	editcount2=0;
	}
	}







var xxda;
function  savextda(){
var selected = $('#xxxt').datagrid('getSelected');
if(!selected)
{
	$.messager.alert('警告','选中需要录入答案的小题','warning');
	return;
}
var indexs = $('#xxxt').datagrid('getRowIndex',selected);
xxda=selected;
if(indexs+1>xxxtlength)
{
$.messager.alert('录入小题','请先录入小题','warning');
}
else if(indexs+1<xxxtlength)
{
	$.messager.alert('录入答案','答案已被录入,请到添加答案处添加','warning');
}
else 
{

	addxxda();
	datihaosxx.value=selected.th;
	xiaotishunxusxx.value=xxsxh;
	datihaosxx.disabled=true;
    xiaotishunxusxx.disabled=true;
}
}


var allzsd="";
var zsdxz=0;
function addxxda()
{
	
	   if(role=="null")
	   {
		   $.messager.alert('警告','时间超时,请重新登录','warning');
		   return;
	   }
	   else  if(sfgetrole!=0)
	   {
	    $('#addxxda').window('open'); 
		$('#addxxda').window('resize',{left:getWidth(0.2),top:getHeight(0.15),width:470,height:280});
		qk();
        if(zsdxz==0)
        {
        $.ajax({
     		type:"POST",
     		url:"getzsdzj",
     		dataType:'json',
     		success:function callback(r){
     		allzsd=r.zsddto;
     		zsdxz=1;
     		$('#zsdsxx').combobox({
    	   	    data:allzsd,
    			valueField:'id',
   				textField:'value'
   			});
        }
        });
        }
        else
        {            
			$('#zsdsxx').combobox({
    	   	    data:allzsd,
    			valueField:'id',
   				textField:'value'
   			});
        }

		$('#yzsxsxx').combobox({
			data:yzsxzj,
			valueField:'productid',
			textField:'name',
			editable:false 	
		});
		datihaosxx.value="";
		xiaotishunxusxx.value="";
	    }
	   else
	   {
			 $.messager.alert('警告','您无权操作','warning');
			 return;
	   }
}


function qk()
{
	fsxx.value="";
    snsxx.value="";
    positionsxx.value="";
    answersxx.value="";
    position1xx.value="";
    position2xx.value="";
    position3xx.value="";
    position4xx.value="";
    answer1xx.value="";
    answer2xx.value="";
    answer3xx.value="";
    answer4xx.value="";  
}


var sflrxxda=0;
var sfnext=0;
var dtthsj;
var dtsxhsj;
function savexxda1(){
              if(sflrxxda==1)
              {
            	  $.messager.alert('警告','此答案已经被录入','warning');
            	  return;
              }
              if(datihaosxx.disabled==true)
              {
                  sfnext=1;
                  dtthsj=datihaosxx.value;
                  dtsxhsj=xiaotishunxusxx.value;
              }
              else
              {
                  sfnext=0;
              }
			  var yzsx=$('#yzsxsxx').combobox('getValue'); 
			  var zsd=productFormatter($('#zsdsxx').combobox('getValue'));
			  if(datihaosxx.value==""||xiaotishunxusxx.value==""||yzsx==""||zsd==""||fsxx.value=="")
			  {
			 	 $.messager.alert('警告','请将大题号,顺序号,知识点,原子属性,分值录入','warning');
			 	 return;
			  }
			  if(zsd==-1)
			  {
				  $.messager.alert('警告','请选择知识点下拉框内信息','warning');
				  return;
			  }
			 var selected= {"cztdadto.th":datihaosxx.value,"cztdadto.sxh": xiaotishunxusxx.value,"cztdadto.bufz":fsxx.value,"cztdadto.zsdmc":zsd,"cztdadto.yzsx":yzsx,"cztdadto.sheetname":snsxx.value,"cztdadto.position":positionsxx.value,"cztdadto.answers":answersxx.value,"cztdadto.position1":position1xx.value,"cztdadto.position2":position2xx.value,"cztdadto.position3":position3xx.value,"cztdadto.position4":position4xx.value,"cztdadto.answer1":answer1xx.value,"cztdadto.answer2":answer2xx.value,"cztdadto.answer3":answer3xx.value,"cztdadto.answer4":answer4xx.value};
			   $.ajax({
		type:"POST",
	    url:"<%=basePath%>addxxda",  
		data:selected,
		dataType:'json',
		success:function callback(r){
		if(r.message=="答案录入成功")
		{
			sflrxxda=1; 
		}
	    $.messager.alert('答案录入',r.message,'info');
		 }
		}); 
}


    function savexxda2()
    {
    	if(sflrxxda==1)
    	{	
        sflrxxda=0;
        if(sfnext==0)	
        {
      	 datihaosxx.value="";
         xiaotishunxusxx.value="";
        }
        else
        {
        	datihaosxx.value=dtthsj;
            xiaotishunxusxx.value=dtsxhsj;
        }
        qk();
    	}
    	else
    	{
            if(datihaosxx.disabled==true)
            {
                sfnext=1;
                dtthsj=datihaosxx.value;
                dtsxhsj=xiaotishunxusxx.value;
            }
            else
            {
                sfnext=0;
            }
			  var yzsx=$('#yzsxsxx').combobox('getValue'); 
			  var zsd=productFormatter($('#zsdsxx').combobox('getValue'));
			  if(datihaosxx.value==""||xiaotishunxusxx.value==""||yzsx==""||zsd==""||fsxx.value=="")
			  {
			 	 $.messager.alert('警告','请将大题号,顺序号,知识点,原子属性,分值录入','warning');
			 	 return;
			  }
			  if(zsd==-1)
			  {
				  $.messager.alert('警告','请选择知识点下拉框内信息','warning');
				  return;
			  }
			 var selected= {"cztdadto.th":datihaosxx.value,"cztdadto.sxh": xiaotishunxusxx.value,"cztdadto.bufz":fsxx.value,"cztdadto.zsdmc":zsd,"cztdadto.yzsx":yzsx,"cztdadto.sheetname":snsxx.value,"cztdadto.position":positionsxx.value,"cztdadto.answers":answersxx.value,"cztdadto.position1":position1xx.value,"cztdadto.position2":position2xx.value,"cztdadto.position3":position3xx.value,"cztdadto.position4":position4xx.value,"cztdadto.answer1":answer1xx.value,"cztdadto.answer2":answer2xx.value,"cztdadto.answer3":answer3xx.value,"cztdadto.answer4":answer4xx.value};
			   $.ajax({
		type:"POST",
	    url:"<%=basePath%>addxxda",  
		data:selected,
		dataType:'json',
		success:function callback(r){
	    $.messager.alert('答案录入',r.message,'info');
		 }
		});
			   sflrxxda=0;
		        if(sfnext==0)	
		        {
		      	 datihaosxx.value="";
		         xiaotishunxusxx.value="";
		        }
		        else
		        {
		        	datihaosxx.value=dtthsj;
		            xiaotishunxusxx.value=dtsxhsj;
		        }
		       qk();
    	}      
    }






     
     function addxxwj()
     {
    	
    	 if(role=="null")
  	   {
  		   $.messager.alert('警告','时间超时,请重新登录','warning');
  		   return;
  	   }
  	   else  if(sfgetrole!=0)
  	   {
     $('#addxxwjs').window('open');
     $('#th1').val("");
  	    }
		   else
		   {
				 $.messager.alert('警告','您无权操作','warning');
				 return;
		   }
     }
     
     function addxxwj1()
     {
     if($('#th1').val()=="")
     {
      $.messager.alert('警告','请输入题号!','info');
     }
     else
     {
      var th={"tihao":$('#th1').val()};
    $.ajax({
		type:"POST",
		url:"<%=basePath%>searchtihao",  
		data:th,
		dataType:'json',
		success:function callback(r){
	      if(r.message=="success")
	      {
	      $('#addxxwjs').window('close');
	      $('#luruwj').window('open');
          as1("CFrame3",360,250);
          CFrame3.location="../cztjsp/New4.jsp?tihao="+$('#th1').val();  
	      }
	      else
	      {
	      $.messager.alert('警告','不存在这个题号!','info');
	      $('#th1').val("");
	      }
		}
		}); 
  }
     }

     var sfxg=0;
     function sfupdate()
     {
         sfxg=1;
     }

     
     function edit(){	         
    	 if(role=="null")
    	   {
    		   $.messager.alert('警告','时间超时,请重新登录','warning');
    		   return;
    	   }
    	   else  if(sfgetrole!=0)
    	   {  
    	 var rows = $('#test_t').datagrid("getSelections");
    		if(rows.length>1)
    		{
    			$.messager.alert('警告','请选择一行数据','warning');
    			return;
    		}
    var selected = $('#test_t').datagrid('getSelected');
    if(selected){
        var percent=(1-800/getWidth(1))/2;
    	var p = $('#update').window({
				onBeforeOpen:function(){
				  $('#update').window('resize',{left:getWidth(percent),top:getHeight(0.1),width:780,height:365});
			}
			});
		 $('#update').window('open');	
		 as1("CFrame1",780,365); 
		 CFrame1.location="../cztjsp/New1.jsp?tihao="+selected.tihao;
	}else{
		$.messager.alert('选择数据','请选择一行数据','warning');
		}	
   	    }
		   else
		   {
				 $.messager.alert('警告','您无权操作','warning');
				 return;
		   }
	         
	}


     var updatetihao;
     function updatext(cztdtihao)
      {  
     	   
     	   $('#xt').window('open');
     	     $('#xt').window('resize',{left:getWidth(0.1),top:getHeight(0.05),width:getWidth(0.77),height:getHeight(0.68)});
     	     
     	       		$('#xt1').datagrid({
     				fit:true,
                     fitColumns:true,
     				singleSelect:true, 
     				loadMsg:'加载数据,请等待...', 
     				onLoadSuccess:function(){
     					var data=$('#xt1').datagrid('getData');
     					if(data.total==0)
     					{
     					      $.messager.alert('信息','没有数据','info');
     					}				
     				},
     				onClickRow:function(index,data){
     					s(index,"shareit-box3","xt1","CFrame9");
     			},
     				url:'<%=basePath%>getallczt?tihao='+cztdtihao, 
     				 columns:[[
     				      	{field:'th',title:'题号',width:80},
     		            	{field:'sxh',title:'顺序号',width:80},
     		            	{field:'tg',title:'小题描述',width:400,rowspan:1},
     		            	{field:'csrcs',title:'测试人数',width:100,editor:'numberbox'},
     		            	{field:'zqrcs',title:'正确人数',width:100,editor:'numberbox'},
     		            	{field:'xtfz',title:'分值',width:80,editor:'numberbox'},
     		            	{field:'nyd',title:'难易度',width:80,editor:'numberbox'}              
               ]],
     				rownumbers:true,
     				toolbar:[{
     					  text:'修改此小题 ',
     		              iconCls:'icon-edit',
     		              handler:xgxt
     				},'-',{
   					  text:'确认修改 ',
 		              iconCls:'icon-add',
 		              handler:qrxg
 			     	},'-',{
     					  text:'取消修改 ',
   		              iconCls:'icon-cancel',
   		              handler:qxxg
   			     	},'-',{
     					  text:'修改答案 ',
     		              iconCls:'icon-edit',
     		              handler:xgda
     				}],
     				onBeforeEdit:function(index,row){
         		    editcount4++;
     	       		row.editing = true;
     	       		$('#xt1').datagrid('refreshRow', index);
     	       		},
     	       		onAfterEdit:function(index,row){
         	       	editcount4--;
     	       		row.editing = false;
     	       		$('#xt1').datagrid('refreshRow', index);
     				}});
       				updatetihao=cztdtihao;
     }  
      	       		function editrow4(index){
         	       		$('#xt1').datagrid('beginEdit', index);
         	       		}
         	       		function saverow4(index){
         	       		$('#xt1').datagrid('endEdit', index);
         	       		}


         	           

     var xgtg="";
     var xgxtf="";
     var xgnyd="";
     var xgcs="";
     var xgzq="";
     var editcount4=0;
     function  xgxt()
     {
     if(editcount4==1)
     {
    	 $.messager.alert('警告','目前有一题在修改中','warning');
    	 return;
     }
     var selected = $('#xt1').datagrid('getSelected');
     	if(selected){
     		var index = $('#xt1').datagrid('getRowIndex',selected);	
     		editrow4(index);  
     		xgtg=selected.tg;
     		xgxtf=selected.xtfz;
     		xgnyd=selected.nyd;
     		xgcs=selected.csrcs;
     		xgzq=selected.zqrcs;
        }
        else{
     	$.messager.alert('警告','请选择一行数据','warning');
     	}

     }


     function qrxg()
     {
    	 
    	 if(editcount4==0)
    	 {
    		 $.messager.alert('警告','没有行处于修改状态','warning');
    		 return;
    	 }
    	 var selected = $('#xt1').datagrid('getSelected');
    	 var index = $('#xt1').datagrid('getRowIndex',selected);	
      
       if(selected.tg.length==0||selected.nyd.length==0||selected.csrcs.length==0||selected.zqrcs.length==0||selected.xtfz.length==0)
		 {
		    $.messager.alert('warning','请将内容输入完全','info');  var indexs = $('#tt').datagrid('getRowIndex',selected);

		}
		 else if(selected.tg.length>80)
		 {
		  $.messager.alert('警告','长度太长,请在80字之内','warning');
		}
		else if(selected.tg=="<P>&nbsp;</P>")
		{
			 $.messager.alert('警告','请输入小题描述','warning');
		}
		else if(xgtg==selected.tg&&xgxtf==selected.xtfz&&xgnyd==selected.nyd&&xgcs==selected.csrcs&&xgzq==selected.zqrcs)
           {
          	 $.messager.alert('警告','您没有进行修改','warning');
           }
         else
         {
        	 $('#xt1').datagrid('acceptChanges');
        	  var selected={"cztdto.th":selected.th,"cztdto.sxh":selected.sxh,"cztdto.tg":selected.tg,"cztdto.xtfz":selected.xtfz,"cztdto.nyd":selected.nyd,"cztdto.csrcs":selected.csrcs,"cztdto.zqrcs":selected.zqrcs};
              $.ajax({
             		type:"POST",
             		url:"<%=basePath%>updatext",
             		data:selected,
             		dataType:'json',
             		success:function callback(r){
             		$.messager.alert('修改小题',r.message,'info');
             		saverow4(index);
             		}
             		});
         }
     }

     function qxxg()
     {
         editcount4=0;
    	 $('#xt1').datagrid('rejectChanges');
     }

    



     function xgda()
     {
       
       var selected = $('#xt1').datagrid('getSelected');
     	if(selected)
     	{
     	         $('#xgda').window('open');
     	    $('#xgda').window('resize',{left:getWidth(0.1),top:getHeight(0.05),width:getWidth(0.85),height:getHeight(0.68)}); 
     	       		$('#answer').datagrid({
     		        fit:true,
                     fitColumns:true,
     				singleSelect:true,  
     				loadMsg:'加载数据,请等待...',
     				onLoadSuccess:function(){
     					var data=$('#answer').datagrid('getData');
     					if(data.total==0)
     					{
     					      $.messager.alert('信息','没有数据','info');
     					}				
     				},
     				url:'<%=basePath%>getanswers?tihao='+updatetihao+'&bh='+selected.sxh, 
     				 columns:[[
     			 {field:'th',title:'操作题号',width:130,align:'center' },
                  {field:'sxh',title:'小题题号',width:130,align:'center'},
                  {field:'bzh',title:'步骤号',width:100,align:'center'},
                  {field:'zsdmc',title:'知识点名称',width:220},
                  {field:'bufz',title:'分值',width:80},
                  {field:'sheetname',title:'sheetposition',width:200,align:'center'},
                  {field:'position',title:'位置',width:80,align:'center'}, 
                  {field:'answers',title:'答案',width:350,align:'center'},  
                  {field:'yzsx',title:'原子属性',width:220,align:'center',formatter:function(value){
                                                                                                     return  productFormatter1(value);   }}  
               ]],
               
     				rownumbers:true,
     				toolbar:[{
     					  text:'修改答案详细信息 ',
     		              iconCls:'icon-edit',
     		              handler:xgda1
     				}]
     				});
     			
     				}
     				 else{
     	$.messager.alert('警告','请选择一行数据','warning');
     	}

     }
       
           
        function xgda1()
        {
            var selected = $('#answer').datagrid('getSelected');
            if(selected)
     	    {
     	    $('#xgda1').window('open');
     	     $('#xgda1').window('resize',{left:getWidth(0.1),top:getHeight(0.2),width:500,height:300});
     	    if(zsdxz==0)
            {
            $.ajax({
         		type:"POST",
         		url:"getzsdzj",
         		dataType:'json',
         		success:function callback(r){
         		allzsd=r.zsddto;
         		zsdxz=1;
         		$('#zsds').combobox({
        	   	    data:allzsd,
        			valueField:'id',
       				textField:'value'
       			});
            }
            });
            }
            else
            {            
    			$('#zsds').combobox({
        	   	    data:allzsd,
        			valueField:'id',
       				textField:'value'
       			});
            }

         		
     	    
     			$('#yzsxs').combobox({
     				data:yzsxzj,
     				valueField:'productid',
     				textField:'name',
     				editable:false,
     				onLoadSuccess: function() { 
     				$('#yzsxs').combobox("setValue", productFormatter1(selected.yzsx));                  
     				}
     			});
     	    datihaos.value=selected.th;
             xiaotishunxus.value=selected.sxh;
             bzhs.value=selected.bzh;
             fs.value=selected.bufz;
             sns.value=selected.sheetname;
             positions.value=selected.position;
             answers.value=selected.answers;
             position1.value=selected.position1;
             position2.value=selected.position2;
             position3.value=selected.position3;
             position4.value=selected.position4;
             answer1.value=selected.answer1;
             answer2.value=selected.answer2;
             answer3.value=selected.answer3;
             answer4.value=selected.answer4;  
     	    }
     	   else{
     	$.messager.alert('警告','请选择一行数据','warning');
     	}
     	   }
     	    
    
           

        function xgda2()
        { 
      
      var yzsx= productFormatter2($('#yzsxs').combobox('getValue')); 
      var zsd=productFormatter3(productFormatter($('#zsds').combobox('getValue')));
      if(yzsx==""||zsd==""||fs.value=="")
      {
     	 $.messager.alert('警告','请将知识点,源自属性,分值录入','warning');
     	 return;
      }
      if(zsd==-1)
	  {
		  $.messager.alert('警告','请选择知识点下拉框内信息','warning');
		  return;
	  }
      var selected= {"cztdadto.th":datihaos.value,"cztdadto.sxh": xiaotishunxus.value,"cztdadto.bzh":bzhs.value,"cztdadto.bufz":fs.value,"cztdadto.zsdmc":zsd,"cztdadto.yzsx":yzsx,"cztdadto.sheetname":sns.value,"cztdadto.position":positions.value,"cztdadto.answers":answers.value,"cztdadto.position1":position1.value,"cztdadto.position2":position2.value,"cztdadto.position3":position3.value,"cztdadto.position4":position4.value,"cztdadto.answer1":answer1.value,"cztdadto.answer2":answer2.value,"cztdadto.answer3":answer3.value,"cztdadto.answer4":answer4.value };
       $.ajax({
     		type:"POST",
     		url:"<%=basePath%>updateda",
     		data:selected,
     		dataType:'json',
     		success:function callback(r){
     		$.messager.alert('修改答案',r.message,'info');
     		if(r.message=="答案修改成功")
     		{
     		$('#answer').datagrid('reload');
     		}
     		}
     		});
        }
  

        var updateexceltihao;
        function updatewj(cztdtihao)
        {
         updateexceltihao=cztdtihao;
     	 $('#updatewj').window('open');
     	   $('#updatewj').window('resize',{left:getWidth(0.1),top:getHeight(0.05),width:getWidth(0.76),height:getHeight(0.68)});
        $('#wj').datagrid({
     			    fit:true,
                     fitColumns:true,
     				singleSelect:true,
     				nowrap: false,
     				striped: true,
     				collapsible:true,
     				loadMsg:'加载数据,请等待...',
     				onLoadSuccess:function(){
     		var data=$('#wj').datagrid('getData');
     		if(data.total==0)
     		{
     		      $.messager.alert('信息','没有数据','info');
     		}				
     	},
     				url:'<%=basePath%>getallwj?tihao='+cztdtihao, 
     				 columns:[[
     			 {field:'bh',title:'文件编号',width:getWidth(0.74)*0.1,align:'center'},
     			 {field:'dtth',title:'大题号 ',width:getWidth(0.74)*0.1,align:'center'}, 
                  {field:'wjmc',title:'文件名称',width:getWidth(0.74)*0.2,align:'center'},
                  {field:'ms',title:'文件描述',width:getWidth(0.74)*0.6,align:'center'}  
               ]],
     				rownumbers:true,
     				toolbar:[{
     					  text:'修改文件 ',
     		              iconCls:'icon-edit',
     		              handler:xgwj
     				},'-',{
     					  text:'打开文件(需要时间,请耐心等待) ',
     		              iconCls:'icon-add',
     		              handler:openxtwj
     			     	}] 
     				});
     			
        }

    function openxtwj()
    {
 	   if(document.all.excelpj.object == null) { 
		   $.messager.alert('警告','activex控件没有安装 ，请查看说明设置 internet设置','info'); 
		   return;
	   }
    	var selected = $('#wj').datagrid('getSelected');
    	if(selected)
    	{
             excelpj.wjmc="D:\\myexcel\\"+updateexceltihao+"\\"+selected.wjmc;
             var is=excelpj.isexist();
             if(is==0)
             {
            	 excelpj.tihao=updateexceltihao;
            	 excelpj.serverurl='<%=basePath%>download?tihao='+updateexceltihao;
		         excelpj.clienturl="D:\\myexcel\\"+updateexceltihao+"\\"+selected.wjmc;
                 excelpj.Downloadwj();
             }
             excelpj.wjmc="D:\\myexcel\\"+updateexceltihao+"\\"+selected.wjmc;
             excelpj.openexcelread();
    	}
    	else
    	{
    		$.messager.alert('警告','请选中行数据','warning');
    	}
    }

        
     function xgwj()
     {
      var selected = $('#wj').datagrid('getSelected');
            if(selected)
     	    {
     	    $('#xgwj').window('open');
     	    $('#xgwj').window('resize',{left:getWidth(0.2),top:getHeight(0.05),width:400,height:250});;
     	     as1("CFrame5",400,250);
            CFrame5.location="../cztjsp/New5.jsp?bh="+selected.bh;
     	    }
     	   else{
     	$.messager.alert('警告','请选择一行数据','warning');
     	}    
     }


     function xgwj1(s)
     {
     	
     	$('#xgwj').window('close');
     	if(s==1)
     	{
     	$('#wj').datagrid('reload');
     	$.messager.alert('文件修改','文件修改成功','info');
     	}
     	else
     	{
     		$.messager.alert('文件修改','文件修改失败','info');
     	}
     }
          
 

    



     function cktmxxxx()
     {
    	 $('#cktmxxxx').window('open');
	     $('#cktmxxxx').window('resize',{left:getWidth(0.1),top:getHeight(0.05),width:getWidth(0.81),height:getHeight(0.78)});
 
    		$('#tmxxxx').datagrid({
 		        fit:true,
                fitColumns:true,
 				singleSelect:true,  
 				loadMsg:'加载数据,请等待...',
 				onLoadSuccess:function(){
 					var data=$('#tmxxxx').datagrid('getData');
 					if(data.total==0)
 					{
 					      $.messager.alert('信息','没有数据','info');
 					}				
 				},
 				url:'<%=basePath%>getcztxxxx', 
 				 columns:[[
 			{field:'th',title:'操作题号',width:130,align:'center' },
 			{field:'tgxx',title:'操作题干',width:400,align:'center' },
 			{field:'xtcount',title:'小题数目',width:80,align:'center' },
 			{field:'noda',title:'未录入答案小题顺序号',width:130,align:'center' },
 			{field:'file',title:'是否录入文件',width:80,align:'center' }
           ]],
                pagination:true,
 				rownumbers:true
 				});
         $('#tmxxxx').datagrid('getPager').pagination({
   			 displayMsg:'当前显示从{from}到{to}共{total}记录',
   			 beforePageText:'第',
   			 afterPageText:'页'
   		 });
     }

     function ckxxxx()
     {
        var rows = $('#test_t').datagrid("getSelections");
 		if(rows.length>1)
 		{
 			$.messager.alert('警告','请选择一行数据','warning');
 			return;
 		}
    	 var selected = $('#test_t').datagrid('getSelected');
    	 if(!selected)
    	 {
    		 $.messager.alert('警告','请选择要查询的行','warning');
        	 return;
    	 }
    	  	    	  var percent=(1-800/getWidth(1))/2;
    	  	          var p = $('#search1').window({
    	  				onBeforeOpen:function(){
    	  				  $('#search1').window('resize',{width:790,height:365,left:getWidth(percent),top:getHeight(0.05)});
    	  			}
    	  			}); 
    	             
    	  	         $('#search1').window('open'); 
    	  	         as1("CFrame2",780,365);
    	           CFrame2.location=encodeURI("../cztjsp/Ckczt1.jsp?tihao="+selected.tihao);       
     }
     
  
     function ckxt(tihao)
     {  
    
     	   $('#ckxts').window('open');
     	   $('#ckxts').window('resize',{left:getWidth(0.1),top:getHeight(0.05),width:getWidth(0.78),height:getHeight(0.7)});
     	       		$('#ckxts1').datagrid({
     				fit:true,
                     fitColumns:true,
     				singleSelect:true,  
     				loadMsg:'加载数据,请等待...',
     				onLoadSuccess:function(){
     					var data=$('#ckxts1').datagrid('getData');
     					if(data.total==0)
     					{
     					      $.messager.alert('信息','没有数据','info');
     					}				
     				},
     				onClickRow:function(index,data){
     					s(index,"shareit-box4","ckxts1","CFrame10");
     			},
     				url:'<%=basePath%>getallczt?tihao='+tihao, 
     				 columns:[[
     			 {field:'th',title:'操作题号',width:getWidth(0.75)*0.1,align:'center' },
                  {field:'sxh',title:'小题题号',width:getWidth(0.75)*0.1,align:'center'},
                  {field:'tg',title:'小题题干',width:getWidth(0.75)*0.4,align:'center'},
                  {field:'xtfz',title:'分值',width:getWidth(0.75)*0.1},
                  {field:'nyd',title:'难易度',width:getWidth(0.75)*0.1},
                  {field:'csrcs',title:'测试人数',width:getWidth(0.75)*0.1,align:'center'},
                  {field:'zqrcs',title:'正确人数',width:getWidth(0.75)*0.1,align:'center'}     
               ]],
     				rownumbers:true,
     				toolbar:[{
     					  text:'查看答案 ',
     		              iconCls:'icon-search',
     		              handler:ckda
     				}]
     				});
     }  







     function ckda()
     {
    	 var selected = $('#ckxts1').datagrid('getSelected');
    		if(selected)
    		{
    		         $('#ckda').window('open');
    		         $('#ckda').window('resize',{left:getWidth(0.1),top:getHeight(0.05),width:getWidth(0.85),height:getHeight(0.68)}); 
    		       		$('#ckanswer').datagrid({
    					fit:true,
    	                fitColumns:true,
    					singleSelect:true, 
    					loadMsg:'加载数据,请等待...',
    					onLoadSuccess:function(){
    						var data=$('#ckanswer').datagrid('getData');
    						if(data.total==0)
    						{
    						      $.messager.alert('信息','没有数据','info');
    						}				
    					}, 
    					url:'<%=basePath%>getanswers?tihao='+selected.th+'&bh='+selected.sxh , 
    					 columns:[[
    					             {field:'th',title:'操作题号',width:130,align:'center' },
    					             {field:'sxh',title:'小题题号',width:130,align:'center'},
    					             {field:'bzh',title:'步骤号',width:100,align:'center'},
    					             {field:'zsdmc',title:'知识点名称',width:220},
    					             {field:'bufz',title:'分值',width:80},
    					             {field:'sheetname',title:'sheetposition',width:200,align:'center'},
    					             {field:'position',title:'位置',width:80,align:'center'}, 
    					             {field:'answers',title:'答案',width:350,align:'center'},  
    					             {field:'yzsx',title:'原子属性',width:220,align:'center',formatter:function(value){
    					                                                                                                return  productFormatter1(value);   }}  
    					          ]],
    	          
    					rownumbers:true,
    					toolbar:[{
    					    id:'btnadd',
    						text:'查看答案详细信息',
    						iconCls:'icon-edit',
    						handler:ckda1
    					}]
    					});	
    					}
    					 else{
    		$.messager.alert('警告','请选择一行数据','warning');
    		}
     }


     
     function ckda1()
     {
         var selected = $('#ckanswer').datagrid('getSelected');
         if(selected)
     	    {
     	    $('#ckda2').window('open');
     	    $('#ckda2').window('resize',{left:getWidth(0.1),top:getHeight(0.2),width:500,height:280});

        		 $('#ckzsds').combobox({
     	    	    data:allzsd,
     				valueField:'id',
     				textField:'value',
     				onLoadSuccess: function() { 
     				$('#ckzsds').combobox("setValue", selected.zsdmc);                  
     				}
     			});

     	  
     			$('#ckyzsxs').combobox({
     				data:yzsxzj,
     				valueField:'productid',
     				textField:'name',
     				onLoadSuccess: function() { 
     				$('#ckyzsxs').combobox("setValue", productFormatter1(selected.yzsx));                  
     				}
     			});
     	  ckdatihaos.value=selected.th;
          ckxiaotishunxus.value=selected.sxh;
          ckbzhs.value=selected.bzh;
          ckfs.value=selected.bufz;
          cksns.value=selected.sheetname;
          ckpositions.value=selected.position;
          ckanswers.value=selected.answers;
          ckposition1.value=selected.position1;
          ckposition2.value=selected.position2;
          ckposition3.value=selected.position3;
          ckposition4.value=selected.position4;
          ckanswer1.value=selected.answer1;
          ckanswer2.value=selected.answer2;
          ckanswer3.value=selected.answer3;
          ckanswer4.value=selected.answer4;  
     	    }
     	   else{
     	$.messager.alert('警告','请选择一行数据','warning');
     	}
     }




     var ckexceltihao;
     function ckwj(cktihao)
     {
     ckexceltihao=cktihao;
  	 $('#ckwj').window('open');
  	 $('#ckwj').window('resize',{left:getWidth(0.1),top:getHeight(0.05),width:getWidth(0.76),height:getHeight(0.68)})
     $('#ckwj1').datagrid({
  				fit:true,
                fitColumns:true,
  				nowrap: false,
  				striped: true,
  				collapsible:true,
  				singleSelect:true,  
  				loadMsg:'加载数据,请等待...',
  				onLoadSuccess:function(){
  		var data=$('#ckwj1').datagrid('getData');
  		if(data.total==0)
  		{
  		      $.messager.alert('信息','没有数据','info');
  		}			
  	   },
  				url:'<%=basePath%>getallwj?tihao='+cktihao, 
  				 columns:[[
  				           {field:'bh',title:'文件编号',width:getWidth(0.74)*0.1,align:'center'},
  							 {field:'dtth',title:'大题号 ',width:getWidth(0.74)*0.1,align:'center'}, 
  				             {field:'wjmc',title:'文件名称',width:getWidth(0.74)*0.2,align:'center'},
  				             {field:'ms',title:'文件描述',width:getWidth(0.74)*0.6,align:'center'}  
            ]],
  				rownumbers:true,
  				toolbar:[{
				    id:'btnadd',
					text:'打开文件(需要时间,请耐心等待)',
					iconCls:'icon-search',
					handler:ckexcelwj
				}]
  				});
  			
     }


     function ckexcelwj()
     {
  	   if(document.all.excelpj.object == null) { 
		   $.messager.alert('警告','activex控件没有安装 ，请查看说明设置 internet设置','info'); 
		   return;
	   }
    	 var selected = $('#ckwj1').datagrid('getSelected');
         if(selected){
             if(selected.wjmc.indexOf(".xls") > 0)
             {
                 excelpj.wjmc="D:\\myexcel\\"+ckexceltihao+"\\"+selected.wjmc;
                 var is=excelpj.isexist();
                 if(is==0)
                 {
                	 excelpj.tihao=ckexceltihao;
                	 excelpj.serverurl='<%=basePath%>download?tihao='+ckexceltihao;
			         excelpj.clienturl="D:\\myexcel\\"+ckexceltihao+"\\"+selected.wjmc;
                     excelpj.Downloadwj();
                 }
                 excelpj.wjmc="D:\\myexcel\\"+ckexceltihao+"\\"+selected.wjmc;
                 excelpj.openexcelread();
             }
             else
             {
            	  $.messager.alert('信息','只打开Excel文件','info');
             }
         }
         else
         {
        	  $.messager.alert('警告','请选中一行数据','warning');			
         }
     }

     

     function ShowTip(event,obj,message)
     {
        var xOffset = -10; 
        var yOffset = 10;       
        $('body').append( '<div id="tipmessage">'+message+'</div>');
        $('div#tipmessage').css("top", $(obj).position().top-$(obj).height() + "px").css("left", $(obj).position().left+"px").fadeIn("slow");
     }

     function HideTip()
     {
       $("div#tipmessage").fadeOut("slow").remove();
     }

     /**
      * 添加收藏js代码begin
      */

      function addsc(){
     		var selected = $('#test_t').datagrid('getSelected');
     		if(selected){
     			$.ajax({
     				type:'post',
     				url:'<%=basePath%>listyhzdy',
     				success:function(data){
     					 var a = data.rows;
     					$('#zdyflmc').combobox({
     						data:a,
     						valueField:'value',
     						textField:'value'
     					});
     				}
     				});
     			
     			$('#addsc').window('open');
     			$('#scth').val(selected.tihao);
     			$('#sctg').val(selected.jytigan);
     		}
     		else{
     		    $.messager.alert('警告','请选择一行数据','warning');
     		 }/**/
     	}

      function ShouCang(){
     		//var selected = $('#test_tables').datagrid('getSelected');
     		var scth = $('#scth').val();
     		var zdyflmc = $('#zdyflmc').combobox('getValue');
     		var zyms = $("#sctg").val();

     		$.ajax({
     	           type:"POST",
     	           url:"<%=basePath%>addCztcollection",
     	           data:{"zybh":scth,"zdyflmc":zdyflmc,"zyms":zyms},
     	           success:function(){
     	        	  // alert("sss");
     	        	   $.messager.alert('提示','您已成功收藏所选数据!');
     	        	   closesc();
     	           }
     		    });
     	}

      function closesc(){
     	   $('#addsc').window('close');
      }

      /**
       * 添加收藏js代码end
       */
          

     
 
    function closewindow(id)
    {
    	 $('#'+id).window('close');
    }
    
   function close1()
   {
      $('#exercise').window('close');
      $('#exer').remove(); 
      pfjs=0; 
      download=0;
      zzlx=0;
      $('#test_t').datagrid('clearSelections');
   }
   
   function close2()
   {
	datihaosxx.disabled=false;
    xiaotishunxusxx.disabled=false;
	$('#addxxda').window('close');
	sflrxxda=0;
   }
    function close3(sflr){
    $('#add').window('close');
    if(sflr==1)
    {
    $('#test_t').datagrid('reload');
    }
    }   

 function as(iframe)
 {
 var zwidth=document.body.clientWidth;
 var zheight=document.body.clientHeight;
 var w=zwidth;
 var h=zheight;
 var iframe1 = $("#".concat(iframe)); 
 iframe1.width(w);
 iframe1.height(h); 
 }

 
 
  function as1(iframe,width,height)
 {
 var iframe1 = $("#".concat(iframe)); 
 iframe1.width(width);
 iframe1.height(height); 
 }


	</script>
</head>
<body class="easyui-layout">




<div region="center" title="" style="overflow:hidden;">
	<table id="test_t" ></table>	
	
	
	
	
    
<div id="search1" closed="true" class="easyui-window"   resizable="true"  title="查询操作题信息"  maximizable="false"  minimizable="false"    iconCls="icon-search" style="background: #fafafa;overflow-x:hidden;overflow-y:hidden;">
	
			<div  border="false" style="background:#fff;overflow-x:hidden;overflow-y:hidden;">
<iframe  id="CFrame2" src=""   name="CFrame2"  frameborder="0" scrolling="no"   width="100%"  height="100%"></iframe> 
		</div>
	</div>
	
	    
<div id="luruwj" closed="true" class="easyui-window"   maximizable="false"  minimizable="false" title="录入文件" iconCls="icon-edit"  style="width:360px;height:290px;">
	<div class="easyui-layout" fit="true">
			<div  id="luruwj1" region="center" border="false" style="overflow-x:hidden;">
<iframe  id="CFrame3" src=""   name="CFrame3"  frameborder="0"  scrolling="auto"  ></iframe> 
	 </div>
	</div>
	</div>

	




	 <div id="search2" closed="true" class="easyui-window"  closable="false"  title="查询操作题信息" iconCls="icon-search"   maximizable="false"  minimizable="false"  style="width:330px;height:200px;background: #fafafa;">

		<div region="center" border="false" style="padding:10px;background:#fff;">
                    查询依据 :<select  id="cxgj" style="width:200px;" onchange="change()" >
        <option >请选择</option>
		<option >知识点名称</option>
		<option >知识点关键字</option>
		<option >课程名称</option>
		<option >题干关键字</option>
		</select>
		<div id="cxdiv">  </div>
		</div>
		<br/>
            <div region="south" border="false" style="text-align:right;height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="query()">确定</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closewindow('search2')">取消</a>
			</div>
</div>
	
	
		 <div id="addxxwjs" closed="true" class="easyui-window"  closable="false" maximizable="false"  minimizable="false"  title="添加文件" iconCls="icon-add" style="width:300px;height:110px;background: #fafafa;overflow-x:hidden;overflow-y:hidden;">
		<div class="easyui-layout" fit="true">
		<div region="center" border="false" style="padding:10px;background:#fff;">
<p>大题题号:<input type="text"  id="th1"   class="easyui-numberbox"   required="true" > </p>

</div>

   <div region="south" border="false" style="text-align:right;height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="addxxwj1()">确定</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closewindow('addxxwjs')">取消</a>
			</div>

</div>
</div>
	
	
	
	
	
	
	
	

	
	
<div id="add" closed="true"   class="easyui-window" title="新建操作题"  closable="false" maximizable="false"  minimizable="false"  iconCls="icon-add"style="background: #fafafa;width:830px;height:420px;overflow-x:hidden;" >

	<iframe  id="CFrame4" src=""   name="CFrame4"  frameborder="0" scrolling="auto"  width="100%" height="100%" ></iframe>
</div>
	
	
	
		
	 <div id="addxt" closed="true" class="easyui-window" title="添加操作题小题"  maximizable="false"  minimizable="false"   iconCls="icon-add"   >
	
	 <div  id="addxt1"  region="center" border="false" style="background: #fafafa;width:100%;height:100%;overflow-x:hidden;overflow-y:hidden;">
   
      <table id="tt"></table>
      	  		<div id="shareit-box">
	<div id="shareit-body">
		<iframe  id="CFrame6" src=""   name="CFrame6"  frameborder="0"  scrolling="auto"  ></iframe> 
	</div>
      </div>
	</div>
	</div>
	
	
	  
  

	
	
	  
  <div id="addxxda" closed="true"  closable="false"  class="easyui-window" title="录入答案" iconCls="icon-add"   maximizable="false"  minimizable="false" >
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="background:#fff;overflow-y:hidden;">
			<br/>
<p>大题号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  class="easyui-numberbox"   required="true"   id="datihaosxx"   />&nbsp;&nbsp;&nbsp; 小题顺序号:<input type="text"  class="easyui-numberbox"   required="true"  id="xiaotishunxusxx" /></p>
<p>答案:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="answersxx" >&nbsp;&nbsp;&nbsp;&nbsp;分值:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="fsxx"   class="easyui-numberbox"   required="true" ></p> 
<p>知识点:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id="zsdsxx" style="width:154px;"> </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原子属性:&nbsp;&nbsp;&nbsp;<select id="yzsxsxx"  style="width:154px;"></select></p>
<p>位置:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="positionsxx" >&nbsp;&nbsp;&nbsp;&nbsp;工作表:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  id="snsxx"  > </p>
<p>对比位置1:&nbsp;&nbsp;<input type="text"   id="position1xx" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对比答案1:<input type="text"  id="answer1xx" > </p>
<p>对比位置2:&nbsp;&nbsp;<input type="text"   id="position2xx" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对比答案2:<input type="text"  id="answer2xx" > </p>
<p>对比位置3:&nbsp;&nbsp;<input type="text"   id="position3xx" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对比答案3:<input type="text"  id="answer3xx" > </p>
<p>对比位置4:&nbsp;&nbsp;<input type="text"  id="position4xx" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对比答案4:<input type="text"  id="answer4xx" > </p>
<br/>
<br/>
<a class="easyui-linkbutton" iconCls="icon-add" href="javascript:void(0)" onclick="savexxda1()">录入答案</a>	
<a class="easyui-linkbutton" iconCls="icon-add" href="javascript:void(0)" onclick="savexxda2()">录入下一步答案</a>
<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close2()">关闭</a>				
		</div>
	</div>
	</div>
	
		
		

	   <div id="deletext" closed="true"    class="easyui-window" title="删除小题" iconCls="icon-cancel" maximizable="false"  minimizable="false" >
		
			<div  border="false" style="overflow-x:hidden;overflow-y:hidden;width:100%;height:100%;">
     <table id="ss"  ></table>
           	  		<div id="shareit-box1">
	<div id="shareit-body1">
		<iframe  id="CFrame7" src=""   name="CFrame7"  frameborder="0"  scrolling="auto"  ></iframe> 
	</div>
      </div>
	   </div>
	</div>
	
	
		<div id="deletewj" closed="true" class="easyui-window" title="删除文件" iconCls="icon-cancel"  maximizable="false"  minimizable="false"  >

			<div id="deletewj1"  region="center" border="false" style="background:#fff;overflow-x:hidden;width:100%;height:100%;">
  
         <table id="shanchuwj"></table>
	 </div> 	
	</div>
	
	
	
	<div id="deleteda" closed="true" class="easyui-window"   title="删除答案信息" iconCls="icon-cancel"  maximizable="false"  minimizable="false"  >	
			<div  id="deleteda1"  border="false"  style="width:100%;height:100%;">
	<table id="delanswer"></table>
             </div>	
	</div>
	
	
	
	<div id="update" closed="true" class="easyui-window" title="修改操作题信息"     minimizable="false"  maximizable="false" iconCls="icon-edit" style="overflow-x:hidden;overflow-y:hidden;">
<iframe  id="CFrame1" src=""   name="CFrame1"  frameborder="0" scrolling="auto"  width="100%" height="100%" ></iframe>
	</div>



	<div id="exercise" closed="true" class="easyui-window" title="练习操作题"   closable="false" minimizable="false"  iconCls="icon-back">
		<div class="easyui-layout" fit="true">
			<div id="exercise2"  region="center" border="false" style="background:#fff;width:100%;height:85%">

          <div   id="exercise1">
           <br/>
          <p  align="left"><b><font size="2px"> &nbsp;&nbsp;&nbsp;文件已被下载到D:\myexcel文件夹相应题号目录中           请按照下题目要求做题</font></b></p>
          <br></br>
          </div>
          <br></br>
          <p  align="center">
              <object  id="excelpj"  classid="clsid:7F33BAF1-BDBB-4A3F-85C9-C14F53893590"  height="0" width="0"   codebase="<%=basePath%>tables/excelpfxt.cab#version=1,0,0,3"  > </object>   
              
     </p>   
	 </div>
	        <div region="south" border="false" style="text-align:right;height:30px">
	         <a class="easyui-linkbutton" iconCls="icon-add" href="javascript:void(0)"   onclick="excelexercise()"  onmouseover="ShowTip(event,this,'判卷需要对Internet选项设置,请查看说明')" onmouseout="HideTip()">判卷</a>
               <a class="easyui-linkbutton" iconCls="icon-add" href="javascript:void(0)" onclick="bg()">查看得分</a>
               <a class="easyui-linkbutton" iconCls="icon-add" href="javascript:void(0)" onclick="openwj()">打开文件(需要时间,请耐心等待)</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="close1()">关闭</a>
			</div>
	</div>
	</div>

	
	
	
	<div id="addxxxt" closed="true" class="easyui-window" title="添加小题信息" iconCls="icon-add" maximizable="false"  minimizable="false"  style="background: #fafafa;" >
    <div  id="addxxxt1" style="background:#fafafa;width:100%;height:100%;">
	<table id="xxxt"></table>
	      	  		<div id="shareit-box2">
	<div id="shareit-body2">
		<iframe  id="CFrame8" src=""   name="CFrame8"  frameborder="0"  scrolling="auto"  ></iframe> 
	</div>
      </div>
	</div>
	</div>
	
	


<div id="treegrid" closed="true" class="easyui-window" title="得分信息" iconCls="icon-save" minimizable="false"  maximizable="false" style="background: #fafafa;">
		
			<div  id="treegrid1" region="center" border="false"  style="height:100%;width:100%;">
	<table id="df"></table>

	 </div>
	</div>
	

    <div id="xt" closed="true"    class="easyui-window" title="修改小题" iconCls="icon-edit" maximizable="false"  minimizable="false" style="background: #fafafa;">
		
	<div id="xt2" border="false" style="background:#fff;width:100%;height:100%">
    <table id="xt1"></table>
           	  		<div id="shareit-box3">
	<div id="shareit-body3">
		<iframe  id="CFrame9" src=""   name="CFrame9"  frameborder="0"  scrolling="auto"  ></iframe> 
	</div>
    </div>
	</div>
	</div>
	

  
  
  
  
  
   <div id="xgda" closed="true"     class="easyui-window" title="修改答案" iconCls="icon-edit"  maximizable="false"  minimizable="false" >
	
			<div  id="xgda2"  border="false" style="background:#fff;width:100%;height:100%;overflow-x:hidden;">
<table id="answer"></table>

	    </div>
  
  </div>
  
  
    <div id="updatewj" closed="true" class="easyui-window" title="修改文件" iconCls="icon-edit"   maximizable="false"  minimizable="false" >	
			<div  id="updatewj1" region="center" border="false" style="background:#fff;overflow-x:hidden;overflow-y:hidden;width:100%;height:100%;">
     <table id="wj"></table>
            <div id="shareit-box5">
	        <div id="shareit-body5">
	<img id="wj1"     style="width:200px;height:200px;" />
	        </div>
	        </div>
	        </div>		
	</div>

  
  
  
  <div id="xgda1" closed="true"  closable="false"  class="easyui-window" title="修改答案" iconCls="icon-edit"   maximizable="false"  minimizable="false" style="width:650px;height:550px;background: #fafafa;">	
			<div region="center" border="false" style="background:#fff;overflow-y:hidden;">
			<br/>
<p>大题号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="datihaos"  disabled="true" />&nbsp;&nbsp;&nbsp; 小题顺序号:<input type="text" id="xiaotishunxus"  disabled="true"/></p>
<p>步骤号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="bzhs"  disabled="true"  />&nbsp;&nbsp;&nbsp;&nbsp;分值:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="fs"   class="easyui-numberbox"   required="true" ></p> 
<p>知识点:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id="zsds" style="width:154px;"> </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原子属性:&nbsp;&nbsp;&nbsp;<select id="yzsxs"  style="width:154px;"></select></p>
<p>位置:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="positions" >&nbsp;&nbsp;&nbsp;&nbsp;工作表:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  id="sns"  > </p>
<p>对比位置1:&nbsp;&nbsp;<input type="text"   id="position1" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对比答案1:<input type="text"  id="answer1" > </p>
<p>对比位置2:&nbsp;&nbsp;<input type="text"   id="position2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对比答案2:<input type="text"  id="answer2" > </p>
<p>对比位置3:&nbsp;&nbsp;<input type="text"   id="position3" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对比答案3:<input type="text"  id="answer3" > </p>
<p>对比位置4:&nbsp;&nbsp;<input type="text"  id="position4" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对比答案4:<input type="text"  id="answer4" > </p>
<p>答案:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="answers" ></p>
<br/>
<div region="south" border="false" style="text-align:right;height:30px">
<a class="easyui-linkbutton" iconCls="icon-edit" href="javascript:void(0)" onclick="xgda2()">修改</a>	
<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick=" closewindow('xgda1')">关闭</a>	
</div>
		</div>
	</div>
	
	
	
	 <div id="xgwj" closed="true"   class="easyui-window" title="文件" iconCls="icon-edit"   maximizable="false"  minimizable="false" style="overflow-x:hidden;overflow-y:hidden;">
    <iframe  id="CFrame5" src=""   name="CFrame5"  frameborder="0" scrolling="auto"  ></iframe>	
	</div>
	
	
	
	
	
	
	
	
	
	
	
	  <div id="ckxts" closed="true"    class="easyui-window" title="查看小题" iconCls="icon-search" maximizable="false"  minimizable="false" style="background: #fafafa;">	
			<div  border="false" style="background:#fff;width:100%;height:100%;">
     <table id="ckxts1"></table>
           	  		<div id="shareit-box4">
	                <div id="shareit-body4">
		<iframe  id="CFrame10" src=""   name="CFrame10"  frameborder="0"  scrolling="auto"  ></iframe> 
	                </div>  
	        </div>
	</div>
	</div>
	
	
	

  
  
  
  
  
   <div id="ckda" closed="true" class="easyui-window"   maximizable="false"  minimizable="false" title="查看答案" iconCls="icon-search" >	
			<div id="ckda1" region="center" border="false" style="background:#fff;overflow-x:hidden;width:100%;height:100%;">
<table id="ckanswer"></table>
	    </div>   
  </div>
  
  
  
  <div id="ckda2" closed="true"    class="easyui-window" title="查看答案" iconCls="icon-search"   maximizable="false"  minimizable="false" >
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;overflow-y:hidden;">
<p>大题号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="ckdatihaos"  disabled="true" />&nbsp;&nbsp;&nbsp; 小题顺序号:<input type="text" id="ckxiaotishunxus"  disabled="true"/></p>
<p>步骤号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="ckbzhs"  disabled="true"  />&nbsp;&nbsp;&nbsp;&nbsp;分值:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="ckfs"   class="easyui-numberbox"   disabled="true" ></p> 
<p>知识点:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id="ckzsds" style="width:154px;"> </select>&nbsp;&nbsp;&nbsp;&nbsp;原子属性:&nbsp;&nbsp;&nbsp;<select id="ckyzsxs"  style="width:154px;"></select></p>
<p>位置:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="ckpositions" >&nbsp;&nbsp;&nbsp;&nbsp;工作表:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  id="cksns"  > </p>
<p>对比位置1:&nbsp;&nbsp;<input type="text"   id="ckposition1" >&nbsp;&nbsp;&nbsp;&nbsp;对比答案1:&nbsp;<input type="text"  id="ckanswer1" > </p>
<p>对比位置2:&nbsp;&nbsp;<input type="text"   id="ckposition2" >&nbsp;&nbsp;&nbsp;&nbsp;对比答案2:&nbsp;<input type="text"  id="ckanswer2" > </p>
<p>对比位置3:&nbsp;&nbsp;<input type="text"   id="ckposition3" >&nbsp;&nbsp;&nbsp;&nbsp;对比答案3:&nbsp;<input type="text"  id="ckanswer3" > </p>
<p>对比位置4:&nbsp;&nbsp;<input type="text"  id="ckposition4" >&nbsp;&nbsp;&nbsp;&nbsp;对比答案4:&nbsp;<input type="text"  id="ckanswer4" > </p>
<p>答案:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   id="ckanswers" ></p>			</div>
	</div>
	</div>
  
    <div id="ckwj" closed="true" class="easyui-window"   maximizable="false"  minimizable="false" title="文件" iconCls="icon-search" >
		
	  <div   region="center" border="false" style="background:#fff;width:100%;height:100%;">
     <table id="ckwj1"></table>
            	  		<div id="shareit-box6">
	                    <div id="shareit-body6">
	<img id="wj2"   style="width:200px;height:200px;"/>
	                    </div>
	                    </div>	
	</div>
	</div>
	
	  <div id="cktmxxxx" closed="true" class="easyui-window"   maximizable="false"  minimizable="false" title="题目详细信息" iconCls="icon-search" >	
			<div   region="center" border="false" style="background:#fff;width:100%;height:100%;">
       <table id="tmxxxx"></table>
	       </div>
	  </div>
	  
	  
	  <!-- ***********添加收藏html代码 *************-->

	
	<div id="addsc" closed="true" class="easyui-window" title="添加收藏" icon="icon-add" style="width:400px;height:240px;" closed="true">
	   	<center>
	   		<s:form id="scform">
	   			<table>
	   				<tr>
						<td> 
						题号: 
						</td>
						<td>
							<input id="scth" type="text" readonly="true" style="width:250px;"/>
						</td>
					</tr>
					<tr>
						<td> 
						自定义分类名称: 
						</td>
						<td>
							<input id="zdyflmc"  class="easyui-combobox" name="language" style="width:250px;"/>
						</td>
					</tr>
					<tr>
						<td> 
						资源描述: 
						</td>
						<td>
							<textarea id="sctg" name="tg" style="width:250px;height:100px;"></textarea>										 
						</td>
					</tr>	
	   			</table>
	   		</s:form>
   			<div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
   				<a class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" onclick="ShouCang()">收藏</a>
   				&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="closesc()">取消</a>
			</div>
	   	</center>
	  </div>	


<!-- ***********添加收藏html代码 *************-->
	  
	</div>

</body>
</html>
