//这是用来绑定模块分类名称的
$(document).ready(
     function(){
      $.ajax({
		    url:"bindMclassname",
		    type:"post",
		    dataType:"json",
		    success:function(json){//回调函数，将模块分类名称加入到相对应的select中，在最后有一个“添加更多”，是在没有用户想要的模块分类时执行添加模块的。
		    	  data=(json.MCName);
		    	  $.each(data,function(n,value){
		    	  	var option = document.createElement("option");
			    	  document.getElementById("addmcname").appendChild(option);
		    	 	  option.text=value;
		    	 	  $("#updatemcname").append("<option value=\""+value+"\">"+value+"</option>");
			    	 
			      });
			      var option = document.createElement("option");
		    	  document.getElementById("addmcname").appendChild(option);
		    	  option.text="--添加更多--";
		    	   $("#updatemcname").append("<option value=\""+"--添加更多--"+"\">"+"--添加更多--"+"</option>");
      			}
		  });
     }
 );



//根据之前模块分类的不同绑定相对应模块名称
function getMname(){
	 //绑定之前 清空第一个以外的option
	
     $("#addmname").children().eq(0).siblings().remove();
     var temp=$("#addmcname").find("option:selected").val();
     if(temp=="--添加更多--"){
    	 $('#add1').window('open');
    	 document.getElementById("ggggg4").style.visibility="visible";//注意：用$()方式不好用
     }else{
    	 $.ajax({
    		    url:"bindMName",
    		    type:"post",
    		    dataType:"json",
    		    data:"mname="+temp,
    		    success:bindMList
    	 });
     }
     
  }
    //回调函数
    function bindMList(json){
     data=(json.MName);
     $.each(data,function(n,value){
        var option = document.createElement("option");
        document.getElementById("addmname").appendChild(option);
	      option.text=value;
        
     });
     var option = document.createElement("option");
     document.getElementById("addmname").appendChild(option);
     option.text="--添加更多--";
    }
    
    function getM(){
    	 var temp=$("#addmname").find("option:selected").val();
    	 var aa = $("#addmcname").find("option:selected").val();
    	 if(temp=="--添加更多--"){
    		 $('#addmcname1').val(aa);
    		 $('#add2').window('open');
    		 document.getElementById("ggggg5").style.visibility="visible";//注意：用$()方式不好用
    	 }
    }
    function getM1(){
   	 var temp=$("#mname1").find("option:selected").val();
   	 var aa = $("#updatemcname").find("option:selected").val();
   	 if(temp=="--添加更多--"){
   		 $('#addmcname1').val(aa);
   		 $('#add2').window('open');
   		document.getElementById("ggggg5").style.visibility="visible";//注意：用$()方式不好用
   	 }
   }
    
    function getMname1(){
   	 //绑定之前 清空第一个以外的option
        $("#mname1").children().siblings().remove();
        var temp=$("#updatemcname").find("option:selected").val();
        if(temp=="--添加更多--"){
       	 $('#add1').window('open');
       	document.getElementById("ggggg4").style.visibility="visible";//注意：用$()方式不好用
        }else{
       	 $.ajax({
       		    url:"bindMName",
       		    type:"post",
       		    dataType:"json",
       		    data:"mname="+temp,
       		    success:bindMList1
       	 });
        }
        
     }
    function bindMList1(json){
        data=(json.MName);
        
        $.each(data,function(n,value){
          var option = document.createElement("option");
          document.getElementById("mname1").appendChild(option);
	         option.text=value;
        });
        var option = document.createElement("option");
        document.getElementById("mname1").appendChild(option);
        option.text="--添加更多--";
       }
    //添加模块分类
    function add1(){
		var selected = {"moduleclass.mclassname":$('#mclassname').val(),"moduleclass.classolder":$('#classolder').val(),
				"moduleclass.comments":$('#comments').val()};
		$.ajax({
				type:"POST",
				url:"saveModuleclass.action",
				data:selected,
				success:function(){}
				});
		  $('#add1').window('close');
          $.messager.alert('提示','添加分类成功！','info',function(){
                location.href = 'listfunc.action';
                window.event.returnValue=false;
            })
		}
	   function close4(){
	     $('#add1').window('close');
	     }
    //添加模块
	   function add2(){
		   jQuery.ajax({
			          type: "post",
				      url:"addModule.action",
				      data:$('#a').serialize(),
				      cache:false,
                    success:function (json){
                       if(json.tip=="true"){
		                         close5();
	                             $.messager.alert('提示','添加成功！','info',function(){
	                                 location.href = 'listfunc.action';
	                                 window.event.returnValue=false;
	                             });
                        }   
                     }
				  })
	   }
	   
	   function close5(){
	     $('#add2').window('close');
	     }
