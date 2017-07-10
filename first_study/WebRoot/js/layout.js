Ext.onReady(function(){ 
	var clientWidth = document.body.clientWidth;
	var clientHeight = document.body.clientHeight*1.5;
	var panel=new Ext.Panel({
	renderTo:'mainFrame',
	title:"搜索排行榜",
	width:clientWidth,
	height:clientHeight,
	layout: {
        type: 'table',
        columns: 2
    },
    defaults: {frame:true,width:clientWidth/2,height:clientHeight/2},
	items:[{title:"柱状图显示",html:"<iframe src='columnChart.html' width='100%' height='100%' frameborder='0' scrolling='yes' id='setframe' name='setframe'/>"},
	
	       {title:"折线图显示",html:"<iframe src='lineChart.html' width='100%' height='100%' frameborder='0' scrolling='yes' id='setframe' name='setframe'/>"},
	       
	       {title:"区域图显示",html:"<iframe src='areaChart.html' width='100%' height='100%' frameborder='0' scrolling='yes' id='setframe' name='setframe'/>"},
	       
	       {title:"雷达图显示",html:"<iframe src='radarFillChart.html' width='100%' height='100%' frameborder='0' scrolling='yes' id='setframe' name='setframe'/>"}
	      ]
	}
	);
	});
