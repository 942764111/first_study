//一级数据源
$(function(){
      $.ajax({
	    url:"zjTCName",
	    type:"POST",
	    dataType:"json",
	    success:function(json){
    		$.each(json.w,function(n,value){
    			 $("#zjTCName").append("<option value=\""+value+"\">"+value+"</option>");
    		 });
    	}
	   });
     });


//二级数据源
function getZjz(){
    //清除出第一个以外的下拉框
	$("#zjz1").children().eq(0).siblings().remove();
    $("#zjz2").children().eq(0).siblings().remove();
    var temp1=$("#zjTCName").find("option:selected").val();
    $.ajax({
	   url:"zjz1",
	   type:"POST",
	   dataType:"json",
	   data:{"TCName":temp1},
	   success:function(data){
		   var zbhmc=new Array();
		   zbhmc=data.secondz;
		   $.each(zbhmc,function(n,value){
				 $("#zjz1").append("<option value=\""+value.zbh+"\">"+value.CName+"</option>");
			 });
	   }
	  });
   }

//三级数据源
function getZjjzz(){
     $("#zjz2").children().eq(0).siblings().remove();
     var temp1=$("#zjTCName").find("option:selected").val();
     var temp2=$("#zjz1").find("option:selected").val();//取的是章编号
     $.ajax({
	    url:"zjz2",
	    type:"POST",
	    dataType:"json",
	    data:{"zbh":temp2,"TCName":temp1},
	    success: function(data){
	    	 var zbhmc2=new Array();
			 zbhmc2=data.thirdz;
	    	$.each(zbhmc2,function(n,value){
				 $("#zjz2").append("<option value=\""+value.zbh+"\">"+value.CName+"</option>");
			 });
	    }
	   });
    }

