Ext.require([
    'Ext.form.field.File',
    'Ext.form.Panel',
    'Ext.window.MessageBox'
]);

Ext.onReady(function(){
	
	var msg = function(title, msg) {
        Ext.Msg.show({
            title: title,
            msg: msg,
            minWidth: 200,
            modal: true,
            icon: Ext.Msg.INFO,
            buttons: Ext.Msg.OK
        });
    };
	
    var msgInfo = function(title,msg,icon){
    	Ext.Msg.show({
            title: title,
            msg: msg,
            minWidth: 200,
            modal: true,
            icon: icon,
            buttons: Ext.Msg.OK
        });
    }
    
    Ext.create('Ext.form.Panel', {
        renderTo: 'fileupload-form',
        width: 500,
        frame: true,
        title: '文件上传',
        bodyPadding: '10 10 0',

        defaults: {
            anchor: '100%',
            allowBlank: false,
            msgTarget: 'side',
            labelWidth: 50
        },

        items: [{
            xtype: 'textfield',
            id:'pubWriter',
            name:'pubWriter',
            fieldLabel: '上传者'
        },{
            xtype: 'filefield',
            id: 'resource',
            emptyText: '请选择上传文件',
            fieldLabel: '文件名',
            name: 'resource',
            buttonText: '',
            buttonConfig: {
                iconCls: 'upload-icon'
            }
        }],

        buttons: [{
            text: '上传',
            handler: function(){
                var form = this.up('form').getForm();
                if(form.isValid()){
                    form.submit({
                        url: '/upload_file.action',
                        waitMsg: '文件上传中',
                        success: function(fp, o) {
                    	   if(o.result.responseCode=='000')
                    	   {
                             msgInfo('Success', '上传文件成功',Ext.Msg.INFO);
                    	   }
                    	   else if(o.result.responseCode=='002')
                    	   {
                    		 msgInfo("Fail",'不允许上传该类型的文件',Ext.MessageBox.ERROR);
                    	   }
                    	   else if(o.result.responseCode=='001')
                    	   {
                    		 
                    		 msgInfo("Fail",'上传文件大小超过最大限制'+o.result.responseInfo,Ext.MessageBox.ERROR);
                    	   }
                    	   else
                    	   {
                    		 msgInfo("Fail",'未知错误',Ext.MessageBox.ERROR);
                    	   }
                        }
                    });
                }
            }
        },{
            text: '重置',
            handler: function() {
                this.up('form').getForm().reset();
            }
        }]
    });

});