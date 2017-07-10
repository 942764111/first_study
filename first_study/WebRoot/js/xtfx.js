var text11;
var text22;
$(function(){
	
		
	jQuery.jqplot.config.enablePlugins = true;

	jQuery.ajax({
          type: "post",
	      url:"ksfxjson",
	     // dataType:"json",因为没有提交数据所以不要指定提交数据的类型！！！
	    success:function (json){
		  if(json.tip=="true"){
              //切记此处l2是L2的小写形式；
              text11=json.text1;
              text22=json.text2;
              alert("kan hao:"+json.list);
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
	   });// 
	
});

function commit1(){
	var dis=$("#text1").attr("disabled");//返回时boolen值
	
	if(dis){
		$.messager.alert('warning','未修改','warning');
		}else{
			var text1=$("#text1").val();
			
			if(text1!=null){
				$.ajax({
					type:"POST",
					dataType:"json",
					url:"tjfx.action",
					data:{"text1":text1},
					success:function (json){
						if(json.tip=="true"){
							$.messager.alert('warning','提交成功','warning');
							
		                    text11=text1;
							}
						 
					}
				});

			}else{
				$.messager.alert('warning','您还未分析！','warning');
				}
	 }
	
	
};
function cancel1(){
	var dis=$("#text1").attr("disabled");
	if(dis){
		}else{
			$("#text1").val("");
			}
	
}; 
function commit2(){
	var dis=$("#text2").attr("disabled");
	if(dis){
		$.messager.alert('warning','未修改','warning');
		}else{
			var text2=$("#text2").val();
			alert(text2);
			if(text2!=null){
				$.ajax({
					type:"POST",
					dataType:"json",
					url:"tjfx.action",
					data:{"text2":text2},
					success:function (json){
						if(json.tip=="true"){
							$.messager.alert('warning','提交成功','warning');
							
		                     text22=text2;
							}
						 
					}
				});

			}else{
				$.messager.alert('warning','您还未分析！','warning');
				}
			}
	
};
function cancel2(){
	var dis=$("#text2").attr("disabled");
	if(dis){
		}else{
			$("#text2").val("");
			}
};

function ckfx(){
	$("#text1").val(text11);
	
	$("#text1").attr("disabled","disabled");//添加readonly属性
	
	
};
function ckdy(){
	$("#text2").val(text22);
	
	$("#text2").attr("disabled","disabled");//添加readonly属性
	
};
function xgfx(){
	
	$("#text1").removeAttr("disabled");   //去除readonly属性

};
function xgdy(){
	
	$("#text2").removeAttr("disabled");  //去除readonly属性
};
