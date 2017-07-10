seajs.config({
	// 鍒悕閰嶇疆
	alias: {
		'seajs-text' : '/static/sea-modules/seajs/seajs-text/1.0.3/seajs-text',
		'seajs-combo': '/static/sea-modules/seajs/seajs-combo/1.0.0/seajs-combo',
		//'jquery'     : '/static/sea-modules/jquery/jquery/1.9.1/jquery',
		'store'      : '/static/sea-modules/gallery/store/1.3.14/store',
		'underscore' : '/static/sea-modules/gallery/underscore/1.3.14/underscore',
		'moment'     : '/static/sea-modules/gallery/moment/2.3.1/moment',

		'jquery'     : '/static/lib/jquery/1.9.1/jquery',
		'util'       : '/static/lib/util/0.1.0/util',
		'jwplayer'   : '/static/lib/jwplayer/1.0.0/jwplayer',
		'doT'        : '/static/lib/dot/1.0.0/doT',
		'socket.io'  : '/static/lib/socket.io/1.3.5/socket.io.min',
		'ace'        : '/static/lib/ace/1.0.0/ace',

		'drag'       : '/static/component/base/drag/drag',
		'tab'        : '/static/component/base/tab/tab',
		'scroll_load': '/static/component/base/scroll_load/scroll_load',

		'common'     : '/static/component/logic/common/common',
		'login'      : '/static/component/logic/login/login',
		'login_sns'  : '/static/component/logic/login/login-regist',
		'chat'       : '/static/component/logic/chat/im',
		'player'     : '/static/component/logic/player/player',
		'ceditor'    : '/static/component/logic/ceditor/ceditor', //涓绘彁浜ょ紪杈戝櫒
		'publish'    : '/static/component/logic/publish/publish',

		'show_data'  : '/static/page/course/common/show_data',
		'codeEditor' : '/static/page/course/common/code_editor',  //鍩烘湰鏌ョ湅浠ｇ爜
	    'Module-layer' : '/static/lib/layer/1.6.0/layer.min.js',
        'placeholder': '/static/component/base/placeholder/placeholder.js'
	},
	map: [

		[ /^(.*\.(?:css|js|tpl))(.*)$/i, '$1?'+seajsTimestamp ]
	],

	// 璺緞閰嶇疆
	paths: {
		'lib': '/static/lib',
		'util': '/static/component/base/util'
	},

	// 鍙橀噺閰嶇疆
	//vars: {
	//	'locale': 'zh-cn'
	//},

	// 鏄犲皠閰嶇疆
	//map: [
	//	['http://example.com/js/app/', 'http://localhost/js/app/']
	//],

	// 棰勫姞杞介」

	preload: ['jquery', 'seajs-text','common'],
	//preload: ['jquery', 'seajs-text', 'seajs-combo'],

	// 璋冭瘯妯″紡
	debug: true,

	// Sea.js 鐨勫熀纭€璺緞
	//base: 'http://example.com/path/to/base/',

	// 鏂囦欢缂栫爜
	charset: 'utf-8'
});