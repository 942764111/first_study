
function add(){
	var userid="wwww";
	$.ajax({
        type:"POST",
        url:"tt_uupload_image.action",
        success:function(json){
		//返回的此userid,是最新的，且可以注册的用户id
		userid=json.userId;
		
		$('#add').window('open');
		//往插件中传入最新的用户id
		$('#fileupload').uploadify({
		    'uploader'  : 'uploadify/uploadify.swf',
		    'script'    : 't_upload_image.action?userId='+userid,
		    'cancelImg' : 'uploadify/cancel.png',
		    'auto'      : true,
		    'multi'          : false, 
		   // 'buttonText'     : 'brower', 
		    //'simUploadLimit' : 3, 
		    'sizeLimit'      : 102400000, 
		    //'queueSizeLimit' : 2, 
		    'queueID'        : 'fileQueue',
		    'fileDesc'       : '*.jpg; *.jpeg; *.bmp;*.gif;*.png',
	        'fileExt'        : '*.jpg; *.jpeg; *.bmp;*.gif;*.png',
	        'fileDataName'    : 'fileupload',
	        'removeCompleted'	:	false,
	        'wmode'	:	'transparent',
	        'buttonImg' :'uploadify/uploadImage.jpg',
	        onComplete: function (event, queueID, fileObj, response, data) {   
			    reload();
			    jQuery("#" + jQuery(this).attr('id') + ID).find('.percentage').text(' - 完成');   
			    

			},
	        onError: function(event, queueID, fileObj) {   
				alert("文件:" + fileObj.name + "上传失败");   
			},   
			onCancel: function(event, queueID, fileObj){   
				alert("取消了" + fileObj.name);   
			}
			});
	}
  	});
	
	
	
}


function reload(){
	CFrame.document.location.reload(); 
	}