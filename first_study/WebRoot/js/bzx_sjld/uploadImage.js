
function add(){
	var userid="wwww";
	$.ajax({
        type:"POST",
        url:"tt_uupload_image.action",
        success:function(json){
		//���صĴ�userid,�����µģ��ҿ���ע����û�id
		userid=json.userId;
		
		$('#add').window('open');
		//������д������µ��û�id
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
			    jQuery("#" + jQuery(this).attr('id') + ID).find('.percentage').text(' - ���');   
			    

			},
	        onError: function(event, queueID, fileObj) {   
				alert("�ļ�:" + fileObj.name + "�ϴ�ʧ��");   
			},   
			onCancel: function(event, queueID, fileObj){   
				alert("ȡ����" + fileObj.name);   
			}
			});
	}
  	});
	
	
	
}


function reload(){
	CFrame.document.location.reload(); 
	}