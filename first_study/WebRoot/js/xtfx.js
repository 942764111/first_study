var text11;
var text22;
$(function(){
	
		
	jQuery.jqplot.config.enablePlugins = true;

	jQuery.ajax({
          type: "post",
	      url:"ksfxjson",
	     // dataType:"json",��Ϊû���ύ�������Բ�Ҫָ���ύ���ݵ����ͣ�����
	    success:function (json){
		  if(json.tip=="true"){
              //�мǴ˴�l2��L2��Сд��ʽ��
              text11=json.text1;
              text22=json.text2;
              alert("kan hao:"+json.list);
	    	  l2 = json.list;
	    	  $.jqplot('chart1', [json.list], {
	    		     seriesDefaults:{                                  //����dataLabel.html
	    		     	  renderer:$.jqplot.PieRenderer,
	    		     	  rendererOptions:{
	    		     	  	  sliceMargin:3,//�����Ƿ�ְ�
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
	var dis=$("#text1").attr("disabled");//����ʱboolenֵ
	
	if(dis){
		$.messager.alert('warning','δ�޸�','warning');
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
							$.messager.alert('warning','�ύ�ɹ�','warning');
							
		                    text11=text1;
							}
						 
					}
				});

			}else{
				$.messager.alert('warning','����δ������','warning');
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
		$.messager.alert('warning','δ�޸�','warning');
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
							$.messager.alert('warning','�ύ�ɹ�','warning');
							
		                     text22=text2;
							}
						 
					}
				});

			}else{
				$.messager.alert('warning','����δ������','warning');
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
	
	$("#text1").attr("disabled","disabled");//���readonly����
	
	
};
function ckdy(){
	$("#text2").val(text22);
	
	$("#text2").attr("disabled","disabled");//���readonly����
	
};
function xgfx(){
	
	$("#text1").removeAttr("disabled");   //ȥ��readonly����

};
function xgdy(){
	
	$("#text2").removeAttr("disabled");  //ȥ��readonly����
};
