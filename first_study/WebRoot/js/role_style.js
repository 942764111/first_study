function init(){
		document.getElementById("content").style.height=document.body.scrollHeight-60;
}
function show2(){
	$.messager.show({
		title:'My Title',
		msg:'Message will be closed after 5 seconds.',
		timeout:5000,
		showType:'slide'
	});
}