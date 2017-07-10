<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>图形页面</title>
	<link rel="stylesheet" type="text/css" href="js/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/ui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/thickbox.css" />
<style type="text/css">
	   
div.container{width:650px;margin: 0 auto;padding:10px 0;text-align:left}
div.content{float:left;width:540px;padding:10px 0;margin:10px 0;background: #E0EBFF}
div.nav{float:right;width:100px;padding:10px 0;margin:10px 0;background:#EAF0FF }

</style>
	
	<!-- 关键处，IE9以下都要用 -->
    <script language="javascript" type="text/javascript" src="js/jqplot/excanvas.js"></script>
 
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/ui/jquery.easyui.min.js"></script>



<script type="text/javascript" >

var sjnooo=0;

<%
Integer sjnooo=(Integer)request.getAttribute("sjnoo");%>

sjnooo=<%=sjnooo%>;
$(document).ready(function(){

	/*$('#tt').tabs({
		
		width:document.body.clientWidth,
	    height:document.body.clientHeight,
	    
		tools:[{
			text:'试卷编号'+sjnooo,
			iconCls:'icon-reload',
			handler: function(){
				
				location.reload();
			}
		}]
	});*/
});
</script>

	<script language="javascript" type="text/javascript" src="js/jqplot/jquery.jqplot.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/jqplot/plugins/jqplot.pieRenderer.js"></script>
    <link rel="stylesheet" type="text/css" href="js/jqplot/jquery.jqplot.css" />
    <!-- <link rel="stylesheet" type="text/css" href="js/jqplot/examples.css" /> 与上面的
	   <link rel="stylesheet" type="text/css" href="css/thickbox.css" />冲突因为要保持easyui-tab风格所以去掉了它-->
	   
	   
<script type="text/javascript" >	
var text11;
$(document).ready(function(){
	jQuery.jqplot.config.enablePlugins = true;

	jQuery.ajax({
          type: "post",
	      url:"fsfxjson",
	     // dataType:"json",因为没有提交数据所以不要指定提交数据的类型！！！
	    success:function (json){
		  if(json.tip=="true"){
              //切记此处l2是L2的小写形式；
             text11=json.text1;
              $("#text1").val(json.text1);
	    	  l2 = json.list;
	    	       $.jqplot('chart1', [json.list], {
	    		     seriesDefaults:{                                  //参照dataLabel.html
	    		     	  renderer:$.jqplot.PieRenderer,
	    		     	  rendererOptions:{
	    		     	  	  sliceMargin:3,//控制是否分瓣
	    		     	  	  showDataLabels:true,
	    		     	  	  dataLabelThreshold:1,
	    		     	  	  dataLabelFormatString:'%.1f%%'
	    		     	  	} 
	    		     	},
	    		      legend:{show:true}
	    		    }); 

          }			                      
         }
	  
  
     });
});

//=============================================================================
function commit1(){
	
	var text1=$("#text1").val();
	
	if(text11==text1&&text1!="未评价！"){
		$.messager.alert('提示','未修改','warning');
		}else{
			
			if(text1==null||text1==""||text1=="未评价！"){
				$.messager.alert('提示','您还未评价！','warning');
				
			}else{
				$.ajax({
					type:"POST",
					dataType:"json",
					url:"tjfsfx.action",
					data:{"text1":text1},
					success:function (json){
						if(json.tip=="true"){
							$.messager.alert('提示','提交成功','info');
							
							}
						 
					}
				})
			}
	 }
	
	
};
function cancel1(){
	$("#text1").val("");
		
}
</script>
 <SCRIPT   LANGUAGE="JavaScript">           
   function   textCounter(field,   countfield,   maxlimit)   {     
 //   定义函数，传入3个参数，分别为表单区的名字，表单域元素名，字符限制；     
   if   (field.value.length   >   maxlimit)       
	   //如果元素区字符数大于最大字符数，按照最大字符数截断；      
	    field.value   =   field.value.substring(0,   maxlimit);       
   else       //在记数区文本框内显示剩余的字符数；    
	      countfield.value   =   maxlimit   -   field.value.length;      
    }        
</SCRIPT> 


</head>
  
<body >

 <!--  <div id="tt" title="" style="overflow:hidden;" >-->
        
        <div title="图  形  分  析 部  分"   style="padding:0px;" align="center" ><hr size="1px"color="#A4BED4"/>
            <div class="container">
				<div class="content">
						<div id="chart1" style="margin-top:0px;width:400px; height:200px;align:center;"></div>
						
				</div>
				
				<div class="nav">
				    <h2>说明：</h2>
					<h3>饼图</h3>
					<p>此饼图列出了，各个成绩段人数的百分比</p>
					<p></p>
				</div>
				
            </div>
            
            <div class="container">
				<div class="content">
						
						<div>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
							  <tr>
							    <td align="center"><form id="form1" name="form1" method="post" action="">
							      <p>输入您 的 内 容 (<300 字)</p>
							      <textarea name="text1" cols="65" rows="13" id="text1" onkeyup="textCounter(this.form.text1,this.form.remLen1,300);"></textarea><br>
							        <div > 
							                         尚能输入 <input   readonly   type=text   name=remLen1   size=4   maxlength=4   value="300">个字符</div> 
							    </form>    </td>
							  </tr>
							<tr >
							    
							    <td align="right">
							     
							     <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="commit1()">提交</a>
							     <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="cancel1()">清空</a>
							    </td>
							    
							   
							    
							</tr>		 
					      </table></div>
				</div>
			
				  <div class="nav">
				    <h2>说明：</h2>
					<h3>测试评价</h3>
					<p>教师手动在此添加对本次测试的评价  </p>
					<p></p>
				</div>
				
	
            </div>
           			
		</div>
		
<!--  </div>-->

  
</body>
</html>
