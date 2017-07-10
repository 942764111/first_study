
$(document).ready(
     function(){
      $.ajax({
    url:"bindXAction",
    type:"post",
    dataType:"json",
    success:bindXList
   });
    
     }
 );


//回调函数
function bindXList(json){
 data=(json.x);
 
 var temp=$("#bindX").find("option:selected").val();
 $.each(json.x,function(n,value){
	 
	 if(temp!=value){
		 $("#bindX").append("<option value=\""+value+"\">"+value+"</option>");
	 }
	 
  });
 getZybh();

}
 

function getZybh(){
     //绑定之前 清空第一个以外的option
     $("#bindZ").children().eq(0).siblings().remove();
     $("#bjbh").children().eq(0).siblings().remove();
     var temp=$("#bindX").find("option:selected").val();
    
     if(temp.length!=0){
    	  $('#hintx').html("<span ><font color=red></font></span>");
    	 }
     $.ajax({
    url:"bindZAction",
    type:"post",
    dataType:"json",
    data:"xymc="+temp,
    success:bindZList
   });
    }
    //回调函数
    function bindZList(json){
     data=(json.z);
     
     var temp = $("#bindZ").find("option:selected").val();
     $.each(json.z,function(n,value){
    	 if(temp!=value){
    		 $("#bindZ").append("<option value=\""+value+"\">"+value+"</option>");
    	 }
    	 
      });

     getBjbh();
    }
    
    
    function getBjbh(){
     //绑定之前 清空第一个以外的option
     $("#bjbh").children().eq(0).siblings().remove();
     var temp = $("#bindZ").find("option:selected").val();
     if(temp.length!=0){
    	 $('#hintz').html("<span ><font color=red></font></span>");
     }
	     $.ajax({
		    url:"bindBAction",
		    type:"post",
		    dataType:"json",
		    data:"zymc="+temp,
		    success:bindBList
	     });
    }
    //回调函数
    function bindBList(json){
     data=(json.list3);
    
     var temp = $("#bjbh").find("option:selected").val();
     $.each(json.list3,function(n,value){
    	 if(temp!=value.bjbh){
    		 $("#bjbh").append("<option value=\""+value.bjbh+"\">"+value.bjmc+"</option>");
    	 }
    	
    	 
      });

    }
    function getBjmc(){
    	var temp = $("#bjbh").find("option:selected").val();
    	//alert(temp);
        if(temp!=0){
       	 $('#hintb').html("<span ><font color=red></font></span>");
        }
    }
