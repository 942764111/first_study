
$(function(){
      $.ajax({
	    url:"bindTCName",
	    type:"POST",
	    dataType:"json",
	    success:function(json){
    		$.each(json.w,function(n,value){
    			 $("#bindTCName").append("<option value=\""+value+"\">"+value+"</option>");
    		 });
    		}
	   });
     }
 );



function getZh(){
    //清除出第一个以外的下拉框
	$("#bindZh").children().eq(0).siblings().remove();
    $("#bindK").children().eq(0).siblings().remove();
    $("#bindZsd").children().eq(0).siblings().remove();
    var temp1=$("#bindTCName").find("option:selected").val();
    
    $.ajax({
	   url:"bindZh",
	   type:"POST",
	   dataType:"json",
	   data:{"TCName":temp1},
	   success: function(data){
		   var zbhmc=new Array();
		   zbhmc=data.second;
		   $.each(zbhmc,function(n,value){
				 $("#bindZh").append("<option value=\""+value.zbh+"\">"+value.CName+"</option>");
			 });
	      }
	  });
   }


function getKcbh(){
     $("#bindK").children().eq(0).siblings().remove();
     $("#bindZsd").children().eq(0).siblings().remove();
     var temp2=$("#bindZh").find("option:selected").val();
     $.ajax({
	    url:"bindK",
	    type:"POST",
	    dataType:"json",
	    data:{"zbh":temp2},
	    success: function(data){
	    	var jbhmc=new Array();
			 jbhmc=data.third;
	    	$.each(jbhmc,function(n,value){
				 $("#bindK").append("<option value=\""+value.id.CId+"\">"+value.zmc+"</option>");
			 });
	      }
	   });
    }

    function getZsd(){
     $("#bindZsd").children().eq(0).siblings().remove();
     var temp3 = $("#bindK").find("option:selected").val();
     $.ajax({
	    url:"bindZsd",
	    type:"POST",
	    dataType:"json",
	    data:{"CId":temp3},
	    success: function(data){
	    	var zsdbhmc=new Array();
	    	zsdbhmc=data.fourth;
	    	$.each(zsdbhmc,function(n,value){
				 $("#bindZsd").append("<option value=\""+value.id.zsdbh+"\">"+value.zsdmc+"</option>");
			 });
	       }
	   });
    }

