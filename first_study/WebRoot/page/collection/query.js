function findlearn(){
			var zsd_mc = $("#guanjianzi").value();
			var selected = {"zsd_mc":zsd_mc};
			if(selected){
				$.ajax({
					type:"POST",
					url:"zycx_tz.action",
					data:selected,
					success:function(){
					window.location.href='list_zycx.action';
					}
				});
				
			}else{
				$.messager.alert('��ѯ��ʾ','���벻��Ϊ��','warning')	
			};
			}
function closefindlearnwindow(){
			$('#findlearn').window('close');
			}