/**
 * 右侧快速操作
 * kongge@office.weiphone.com
 * 2012.06.07
*/
jQuery(function($){
	//创建DOM
	var 
	quickHTML = '<div class="quick_links_panel"><div id="quick_links" class="quick_links"><a href="#top" class="return_top"><i class="top"></i><span style="color:#000000">返回顶部</span></a><a href="#" class="message_list" ><div><img src="img/ic_menu_copy_holo_light.png" width="40px" height="40px"></img></div><span style="color:#000000">常用文档</span><em class="num" style="display:none"></em></a><a href="#" class="history_list"><div><img src="img/ic_menu_view.png" width="40px" height="40px"></img></div><span style="color:#000000">最近浏览</span></a></div><div class="quick_toggle"><a href="javascript:;" class="toggle" title="展开/收起">×</a></div></div><div id="quick_links_pop" class="quick_links_pop hide"></div>',
	quickShell = $(document.createElement('div')).html(quickHTML).addClass('quick_links_wrap'),
	quickLinks = quickShell.find('.quick_links');
	quickPanel = quickLinks.parent();
	quickShell.appendTo('body');
	
	//具体数据操作 
	var 
	quickPopXHR,
	loadingTmpl = '<div class="loading" style="padding:30px 80px"><i></i><span>Loading...</span></div>',
	popTmpl = '<div class="title"><h3><%=title%></h3></div><div class="pop_panel"><%=content%></div><div class="arrow"><i></i></div><div class="fix_bg"></div>',
	
	quickPop = quickShell.find('#quick_links_pop'),
	quickDataFns = {
		//个人中心
		
		//站内消息
		message_list: {
			title: '常用文件',
			content: loadingTmpl,
			init: function(ops){
				//function del(){
				
				//获取实时最近浏览
				quickPopXHR = $.ajax({
					url: 'getCommonFile.action',
					dataType: 'json',
					cache: false,
					success: function(data){
						
						if(data.list==null||data.list==""){
							var html = '<div class="no_data"><div><img src="img/ic_menu_copy_holo_light.png" width="40px" height="40px"></img><div><span>没有常用文件</span></div>';
	                        
						}else{var html = "";
						
						$.each(data.list, function(i, item){     
							 
							html=html+"<li><a><span class='wendang' id="+data.list[i].id+" name="+data.list[i].zlmc+" path="+data.list[i].path+">"+data.list[i].zlmc+"</span><img class='as' style='float:right' src='img/del.jpg' name="+data.list[i].id+"></img></a></li>"
							
						});  
						}
						quickPop.html(ds.tmpl(popTmpl, {
							title: ops.title,
							content: '<div class="links" id="aa"><ul>'+ html +'</ul></div>'
						}));
						$(".wendang").click(function(){
							var id=$(this).attr('id');
							var filepath=$(this).attr('path');
							var name=$(this).attr('name');
							new Dialog({type:'iframe',title:'哈哈',value:'spdh/dzbj2.jsp?filename='+name+"&path="+filepath+"&zlid="+id}).show();
							
							
						})
						$(".as").click(function(){
							var id=$(this).attr('name');
							jQuery.ajax({
								type:'post',
								url: 'delCommonFile',
								data:{"id":id},
								dataType:'json',
								error:function(value){
									ds.dialog.alert("删除失败");},
								success: function(value){
									if(value.state=="0"){
										ds.dialog.alert("删除成功&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
										$('.message_list').click();
										
										$('.message_list').click(function(){
											
										})
									}else{
										ds.dialog.alert("删除失败");
									}
									}});});
					}
				});
				
			}
		},
		
		
		//最近浏览
		history_list: {
			title: '最近浏览',
			content: loadingTmpl,
			init: function(ops){
				//获取实时最近浏览
				quickPopXHR = $.ajax({
					url: 'getRecentview.action',
					dataType: 'json',
					cache: false,
					success: function(data){
						if(data.list2==null||data.list2==""){
							var html = '<div class="no_data"><i></i><span>没有浏览记录</span></div>';
	                        
						}else{
						
						var html = "";
						
						$.each(data.list2, function(i, item){     
							  
							html=html+"<li><a><span class='wendang' id="+data.list2[i].id+" name="+data.list2[i].zlmc+" path="+data.list2[i].path+">"+data.list2[i].zlmc+"</span><span style='float:right'>"+data.list2[i].viewtime+"</span></a></li>"
							
							
						});  
						}
						quickPop.html(ds.tmpl(popTmpl, {
							title: ops.title,
							content: '<div class="links"><ul>'+ html +'</ul></div>'
						}));
						$(".wendang").click(function(){
							var id=$(this).attr('id');
							var filepath=$(this).attr('path');
							var name=$(this).attr('name');
							new Dialog({type:'iframe',title:'哈哈',value:'spdh/dzbj2.jsp?filename='+name+"&path="+filepath+"&zlid="+id}).show();
							
							
						})
					}
				});
			}
		},
		//给客服留言
		leave_message: {
			title: '给我们留言',
			content: '<form action="./" method="post"><div class="types"><input type="radio" name="type" id="type_1" value="1" checked /><label for="type_1">联系客服</label><input type="radio" name="type" id="type_3" value="2" /><label for="type_3">我要投诉</label><input type="radio" name="type" id="type_4" value="3"/><label for="type_4">不良信息举报</label></div><div class="txt"><textarea name="msg" id="msg" cols="30" rows="10" placeholder="客服你好，我想问一下..."></textarea></div><br><br><div class="btns"><button type="submit" class="btn"><span>提交</span></button></div></form>',
			init: function(ops){
				setTimeout(function(){
					quickPop.find('textarea').focus();
				}, 100);
				//验证码
				quickPop.find('#token_txt').bind('focus', getValidateCode);
				
				//效验 & 提交数据
				var form = quickPop.find('form');
				form.attr("action",saveMessageUrl);
				form.bind('submit', function(e){
					e.preventDefault();
					var data = form.serialize();
					if(!checkMessageForm()){
						return false;
					}
					var type=quickPop.find(':radio:checked').val();
					jQuery.ajax({
						type:'post',
						url: saveMessageUrl, 
						data:{"message_style":type,"message_content":$("#msg").val(),"checkcode":$("#token_txt").val()},
						dataType:"json",
						error:function(value){
							ds.dialog.alert('留言成功&nbsp;&nbsp;&nbsp;&nbsp;');
						},
						success: function(value){
							var success = value.success;
							var info = value.info;
							if(success==1){
								hideQuickPop();
								showInfoTip(info, 'success');
							}else{
								ds.dialog.alert(info);
							}
						}
					});
				});
			}
		}
	};
	
	//showQuickPop
	var 
	prevPopType,
	prevTrigger,
	doc = $(document),
	popDisplayed = false,
	hideQuickPop = function(){
		if(prevTrigger){
			prevTrigger.removeClass('current');
		}
		popDisplayed = false;
		prevPopType = '';
		quickPop.hide();
	},
	showQuickPop = function(type){
		if(quickPopXHR && quickPopXHR.abort){
			quickPopXHR.abort();
		}
		if(type !== prevPopType){
			var fn = quickDataFns[type];
			quickPop.html(ds.tmpl(popTmpl, fn));
			fn.init.call(this, fn);
		}
		doc.unbind('click.quick_links').one('click.quick_links', hideQuickPop);

		quickPop[0].className = 'quick_links_pop quick_' + type;
		popDisplayed = true;
		prevPopType = type;
		quickPop.show();
	};
	quickShell.bind('click.quick_links', function(e){
		e.stopPropagation();
	});

	//通用事件处理
	var 
	view = $(window),
	quickLinkCollapsed = !!ds.getCookie('ql_collapse'),
	getHandlerType = function(className){
		return className.replace(/current/g, '').replace(/\s+/, '');
	},
	showPopFn = function(){
		var type = getHandlerType(this.className);
		if(popDisplayed && type === prevPopType){
			return hideQuickPop();
		}
		showQuickPop(this.className);
		if(prevTrigger){
			prevTrigger.removeClass('current');
		}
		prevTrigger = $(this).addClass('current');
	},
	quickHandlers = {
		//购物车，最近浏览，商品咨询
		my_qlinks: showPopFn,
		message_list: showPopFn,
		history_list: showPopFn,
		leave_message: showPopFn,
		//返回顶部
		return_top: function(){
			ds.scrollTo(0, 0);
			hideReturnTop();
		},
		toggle: function(){
			quickLinkCollapsed = !quickLinkCollapsed;
			
			quickShell[quickLinkCollapsed ? 'addClass' : 'removeClass']('quick_links_min');
			ds.setCookie('ql_collapse', quickLinkCollapsed ? '1' : '', 30);
		}
	};
	quickShell.delegate('a', 'click', function(e){
		var type = getHandlerType(this.className);
		if(type && quickHandlers[type]){
			quickHandlers[type].call(this);
			e.preventDefault();
		}
	});
	
	//Return top
	var scrollTimer, resizeTimer, minWidth = 1350;

	function resizeHandler(){
		clearTimeout(scrollTimer);
		scrollTimer = setTimeout(checkScroll, 160);
	}
	function checkResize(){
		quickShell[view.width() > 1340 ? 'removeClass' : 'addClass']('quick_links_dockright');
	}
	function scrollHandler(){
		clearTimeout(resizeTimer);
		resizeTimer = setTimeout(checkResize, 160);
	}
	function checkScroll(){
		view.scrollTop()>100 ? showReturnTop() : hideReturnTop();
	}
	function showReturnTop(){
		quickPanel.addClass('quick_links_allow_gotop');
	}
	function hideReturnTop(){
		quickPanel.removeClass('quick_links_allow_gotop');
	}

	
	function login(){
		alert(123);
		ds.dialog.alert("123");
	}
	view.bind('scroll.go_top', resizeHandler).bind('resize.quick_links', scrollHandler);
	quickLinkCollapsed && quickShell.addClass('quick_links_min');
	resizeHandler();
	scrollHandler();
	

	//校验商品咨询表单
	function  checkMessageForm(){
		var content = $("#msg");
		if(content.val()==""){
			ds.dialog({
				   title : '消息',
				   content : 'http:www.baidu.com',
				   onyes : function(){
						this.close();
				   },
				   width : 200,
				   lock : true
			});
			return false;
		}

		var checkcode = $("#token_txt").val();
		if(checkcode=="" || checkcode=="点击获取"){
			ds.dialog({
				   title : '消息',
				   content : "验证码不能为空，请输入验证码！",
				   onyes : function(){
						this.close();
				   },
				   width : 200,
				   lock : true
			});
			return false;
		}
		return true;
	}

	//获取验证码
	function getValidateCode(){
		this.value="";
		var validateCodeUrl = validateCode_url+'?t='+Math.random();
		$("#code_img").html('<img id="validate_code_img_id_1" src="' + validateCodeUrl + '" height="20" width="80" alt="验证码" />');
		return;
	}
	
	$("#share").click(function(){
		
		callExternalInterface();
	})
	
	
	
	
});

//首次加载站内消息总数
jQuery(function($){
	var shell = $('#quick_links a.message_list');
	if(shell.find("em").length){
		
		$.ajax({
			url: unreadNewMsgUrl,
			dataType: 'json',
			cache: false,
			success: function(data){
				if(1 == data.success){
					if(0 == data.msgtotal){
						shell.find('em').remove();
					}else{
						var num = data.msgtotal;
						//消息总数最大只显示 99
						shell.append('<em class="num"><b>'+ Math.min(99, num) +'</b></em>').show();
					}
				}
			}
		});
	}
});