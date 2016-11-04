define(function (require, exports, module) {

    /**
     * @name vko
     * @class 公用常量、变量。
     * @author 李博龙
     * @version v1.0.0
     */
	
	var we = {
		/* 网站root */
		vr: {
			static : typeof weFileLoader.root!='undefined' ? weFileLoader.root : 'http://static.we.cn'
	    },
	    /* 用户信息 */
	    user: {
	        id: '',
	        name: '亲',
	        school: '',
	        home: 'http://www.we.cn',
	        pri_letter: '0',
	        comment:'0',
	        at: '0',
	        follow: '0',
	        profile: '/v5/images/vkoweb/pic/user404'
	    },
	    /* 记录ajax请求 */
	    ajax:[],
	    /* 标记是否登录 */
	    isLogin: undefined,
	    /* 浏览器相关 */
	    browser: {
	        w: 1024,
	        h: 768,
	        msie: false,
	        ie6: false,
	        iev: undefined
	    },
	    /* 键盘操作 */
	    keyCode: {
			ENTER: 13, ESC: 27, END: 35, HOME: 36,
			SHIFT: 16, TAB: 9,
			LEFT: 37, RIGHT: 39, UP: 38, DOWN: 40,
			DELETE: 46, BACKSPACE:8
		},
		/* 分页信息 */
		pageInfo: {
			pageNum:"pageNumber", 
			numPerPage:"pageSize", 
			orderField:"orderField", 
			orderDirection:"orderDirection"
		},
		/* 全局事件 */
		eventType: {
			pageClear:"pageClear"	// 用于重新ajaxLoad、关闭nabTab, 关闭dialog时，去除editor等需要特殊处理的资源
		}
	};
	
    return we;
});