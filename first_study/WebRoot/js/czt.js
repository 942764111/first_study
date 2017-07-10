
    var zsdkeydtos="";
    var kcmc="";
    var zmczj=""; 
    var jmczj="";
     var cxopen=0;
     var cxvalue="";
     function search()
     {
      $('#search2').window('open');
      if(cxopen==0)
      {
      $('#cxdiv').append('<div id="cxcx"></div>');
      cxvalue="请选择";  
      cxopen++;  
      }
      else
      {
      var index = $('#cxgj').find(':selected').text(); 
	  cxvalue=index;       
      if (index !='请选择'&&index!='题干关键字')
      {
    	  $('#cxtj').combobox('clear');
      	  $('#cxtj').combobox('select',1);
    	  
      }
      else if(index=='题干关键字'){
          tggjz.value="";
      }
      }
     }



     function change()
     {
   	  $('#cxcx').remove();
   	  var index = $('#cxgj').find(':selected').text(); 
   	  cxvalue=index;       
         if (index =='知识点名称'||index=='知识点关键字') { 
       	$('#cxdiv').append('<div id="cxcx">查询条件:&nbsp;&nbsp;<select id="cxtj"  class="easyui-combobox"   style="width:200px;"   	></select></div>');        
            Potcxtj();        
            }   
         else if(index=='课程名称')
         {
       	  $('#cxdiv').append('<div id="cxcx">课名称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id="kcmc"  class="easyui-combobox"  style="width:200px;"   	></select></div>');        
             $('#cxcx').append('<div id="zmc1">章名称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id="zmc"  class="easyui-combobox"   style="width:200px;"   	></select></div>'); 
             $('#cxcx').append('<div id="jmc1">节名称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id="jmc"  class="easyui-combobox"  panelHeight="auto" style="width:200px;"   	></select></div>');  
             Potcxtj();   
         }
         else  if(index=='题干关键字')
         {
       	  $('#cxdiv').append('<div id="cxcx"><br/>查询条件<br/><input  id="tggjz" type="text"  maxlength="20"  style="width:270px;"><br/>请输入题干关键字(小于等于3),关键字之间用空格分开</input></div>'); 
         }
     }

   
      

     var zmcarray=new Array();
     var zmcarray1=new Array();
     var kmcarray=new Array();
     var kmcarray1=new Array();
     var keyxz=0;
     var kcxz=0;
     function 	Potcxtj()
     {

    	 var name = $('#cxgj').find(':selected').text(); 
    	 if(name=="知识点名称")
    	 {
    		 if(zsdxz==0)
    	        {
    	        $.ajax({
    	     		type:"POST",
    	     		url:"getzsdzj",
    	     		dataType:'json',
    	     		success:function callback(r){
    	     		allzsd=r.zsddto;
    	     		zsdxz=1;
    	     		 $('#cxtj').combobox({
    	 	        	    data:allzsd,
    	 	    			valueField:'id',
    	 	    			textField:'value'	
    	 		    	});
    	        }
    	        });
    	        }
    		 else
    		 {
        		 $('#cxtj').combobox({
 	        	    data:allzsd,
 	    			valueField:'id',
 	    			textField:'value'	
 		    	});
    		 }
 
    
      
    	 }
    	 else  if(name=="知识点关键字")
    	 {
            if(keyxz==0)
            {
             $.ajax({
 	      		type:"POST",
 	      		url:"getzsdkey",
 	      		dataType:'json',
 	      		success:function callback(r){
 	      		zsdkeydtos=r.zsdkeydto;
 	      		keyxz=1;
 	      	 $('#cxtj').combobox({
	   	    	    data:zsdkeydtos,
	   				valueField:'id',
	   				textField:'value'
	   			});
 	      		}
 	      		});
            }
            else
            {
            	$('#cxtj').combobox({
	   	    	    data:zsdkeydtos,
	   				valueField:'id',
	   				textField:'value'
	   			});
            }
    	      	
    	 }
    	 else if(name=="课程名称")
    	 {
             
             if(kcxz==0)
             {
             $.ajax({
         		type:"POST",
         		url:"getkcmc",
         		dataType:'json',
         		success:function callback(r){
         		kcmc=r.kcdto;		
         		kcxz=1;
       		 $('#kcmc').combobox({
   	   	    	    data:kcmc,
   	   				valueField:'id',
   	   				textField:'value',
   	   			    editable:false
   	   			});
         		}
      			});
             }
             else
             {
    		 $('#kcmc').combobox({
	   	    	    data:kcmc,
	   				valueField:'id',
	   				textField:'value',
	   			    editable:false
	   			});
             }
         
         
    		 $('#kcmc').combobox({
      		   onSelect:function(){
    			 var kmc=productFormatter5($("#kcmc").combobox("getValues"));
    			 var i=0;
    			 for(;i<kmcarray.length;i++)
    			 {
        			 if(kmcarray[i]==kmc)
        			 {
        				 zmczj=kmcarray1[i];
        				 $('#zmc').combobox({
        		   	    	    data:kmcarray1[i],
        		   				valueField:'id',
        		   				textField:'value',
        		   			    editable:false
        		   			   
        		   			});
     		   			break;
        			 }
    			 } 
    			 if(i>=kmcarray.length)
    			 {
        			 
    				 $.ajax({
	                  		type:"POST",
	                  		url:"getzmc",
	                  		data:"kcmc="+kmc,
	                  		dataType:'json',
	                  		success:function callback(r){
	                  			 $('#zmc').combobox({
	         		   	    	    data:r.zdto,
	         		   				valueField:'id',
	         		   				textField:'value',
	         		   			    editable:false 
	         		   			});
	                  			 zmczj=r.zdto;
		         		   		kmcarray.push(kmc);
		         		   		kmcarray1.push(r.zdto);
	                  		}
	               			});
    			 }  
      		 }}); 


      		 
    		 $('#zmc').combobox({
        		   onSelect:function(){    		      
  				  var zmc=productFormatter6($("#zmc").combobox("getValues"));
  				  var kmc1=productFormatter5($("#kcmc").combobox("getValues"));
    			 var i=0;
    			 for(;i<zmcarray.length;i++)
    			 {
        			 if(zmcarray[i]==zmc)
        			 {
            			 jmczj=zmcarray1[i];
        				 $('#jmc').combobox({
        		   	    	    data:zmcarray1[i],
        		   				valueField:'id',
        		   				textField:'value',
        		   			    editable:false 
        		   			});  			
     		   			break;
        			 }
    			 } 
    			 if(i>=zmcarray.length)
    			 {
        			 var kz={"zmc":zmc,"kcmc":kmc1};
    				 $.ajax({
	                  		type:"POST",
	                  		url:"getjmc",
	                  		data:kz,
	                  		dataType:'json',
	                  		success:function callback(r){
	                  			 $('#jmc').combobox({
	         		   	    	    data:r.jdto,
	         		   				valueField:'id',
	         		   				textField:'value',
	         		   			    editable:false 
	         		   			});
 	         		   			jmczj=r.jdto;
		         		   		zmcarray.push(zmc);
		         		   		zmcarray1.push(r.jdto);
	                  		}
	               			});
    			 }
    		 }});                            
    	 }      
     }    

     
     
     var sfcx=0;
     function query(){   
         if(cxvalue=="请选择")
         {
         $.messager.alert('警告','请选择查询依据!','warning');
         return;
         }
         else if(cxvalue=="题干关键字")
         {
             if($("#tggjz").val().length==0)
             {
            	 $.messager.alert('警告','请输入题干关键字!','warning');
            	 return;
             }
         }
         else if(cxvalue=="知识点名称"||cxvalue=="知识点关键字")
         {
        	 var value=$('#cxtj').combobox('getValues');
        	 if(value=="")
        	 {
        		 $.messager.alert('警告','请将查询条件填充','warning');
        		 return;
        	 }
        	
         }
         var zsdallmc="";
         var  zsdkeymc="";
         if(cxvalue=="知识点名称")
         {
         var value=$("#cxtj").combobox("getValues"); 
         zsdallmc=productFormatter(value);
         if(zsdallmc==-1)
         {
        	 $.messager.alert('警告','请选择提示数据','warning');
        	 return;
         }
         }
         else if(cxvalue=="知识点关键字")
         {
        	 var value=$("#cxtj").combobox("getValues"); 
             zsdkeymc=productFormatter4(value);
        	 if(zsdkeymc==-1)
             {
            	 $.messager.alert('警告','请选择提示数据','warning');
            	 return;
             }
         }
         if(cxvalue=="知识点名称")
         {
        	 $('#search2').window('close');
        	 $('#test_t').datagrid('getPager').pagination({pageNumber: 1});
        	 $('#test_t').datagrid('options').url="cxzsd";
        	 $('#test_t').datagrid('options').queryParams={"zsdallmc":zsdallmc};
        	 $("#test_t").datagrid('reload'); 	     
        	 sfcx=1;
         }
         else  if(cxvalue=="知识点关键字")
         {
        	 $('#search2').window('close');
        	 $('#test_t').datagrid('getPager').pagination({pageNumber: 1}); 
        	 $('#test_t').datagrid('options').url="cxzsdkey";
        	 $('#test_t').datagrid('options').queryParams={"zsdkeymc":zsdkeymc};
        	
        	 $("#test_t").datagrid('reload'); 	     
        	 sfcx=1;
         }
         else  if(cxvalue=="题干关键字")
         {
        	 $('#search2').window('close');
         	 $('#test_t').datagrid('getPager').pagination({pageNumber: 1}); 
        	 $('#test_t').datagrid('options').url="cxtggjz"; 
        	 $('#test_t').datagrid('options').queryParams={"tggjz":$("#tggjz").val()};
        	 $("#test_t").datagrid('reload'); 	     
        	 sfcx=1;
         }
         else if(cxvalue=="课程名称")
         {

        	 var kvalue=$("#kcmc").combobox("getValues");
        	 var zvalue=$("#zmc").combobox("getValues");
        	 if(kvalue==""||zvalue=="")
        	 {
        		 $.messager.alert('警告','请将查询 所需的 课程 章 节填充完全','warning');
            	 return;
        	 }
        	 var jvalue=$("#jmc").combobox("getValues");
        	 if(jvalue=="")
        	 {
        		 $.messager.alert('警告','请将查询 所需的 课程 章 节填充完全','warning');
            	 return;
        	 }
        	 $('#test_t').datagrid('getPager').pagination({pageNumber: 1}); 
        	 var kcmcvalue=productFormatter5($("#kcmc").combobox("getValues"));
        	 var zmcvalue=productFormatter6($("#zmc").combobox("getValues"));
        	 var jmcvalue=productFormatter7($("#jmc").combobox("getValues"));
        	 $('#search2').window('close');
        	 $('#test_t').datagrid('options').url="cxkc";
        	 $('#test_t').datagrid('options').queryParams={"kcmc":kcmcvalue,"zmc":zmcvalue,"jmc":jmcvalue};
        	 $("#test_t").datagrid('reload'); 	     
        	 sfcx=1;
         }
          }                      

