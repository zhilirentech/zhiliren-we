define('config',function (require, exports, module) {

    /**
     * @name config
     * @class 公用常量、变量。
     */
	var config = {};
	config = {
		/**
		 * 【weFileLoader】	依赖于wefileload.js文件。	
	     * @function   
	     * @desc 网站root。
	     * @example
	     */
		vr: {
			static : weFileLoader.root,
			cookie_domain:weFileLoader.getDomain(),
			www:'http://www.'+weFileLoader.getDomain(),
			sso:'http://sso.'+weFileLoader.getDomain(),
			zujuan:'http://zujuan.'+weFileLoader.getDomain(),
			manage:'http://manage.'+weFileLoader.getDomain(),
            fenxi:'http://fenxi.'+weFileLoader.getDomain(),
            fudao:'http://fudao.'+weFileLoader.getDomain(),
            kecheng:'http://kecheng.'+weFileLoader.getDomain(),
            order:'http://order.'+weFileLoader.getDomain(),
            shuju:'http://shuju.'+weFileLoader.getDomain(),
            wish:'http://wish.'+weFileLoader.getDomain(),
            college:'http://college.'+weFileLoader.getDomain(),
            sxwl:'http://sxwl.'+weFileLoader.getDomain(),
            tiku:'http://tiku.'+weFileLoader.getDomain(),
            ushangxue:'http://ushangxue.'+weFileLoader.getDomain(),
            yueke:'http://yueke.'+weFileLoader.getDomain(),
            kingberry:'http://manage.'+weFileLoader.getDomain()

            
	    },
	    /**
	     * @function   
	     * @desc 浏览器相关。
	     * @example
	     * Object{
	     *     w: 1024,
	     *     h: 768,
	     *     msie: false,
	     *     ie6: false,
	     *     iev: undefined
	     * }
	     */
	    browser: {
	        w: 1024,
	        h: 768,
	        msie: false,
	        ie6: false,
	        iev: undefined
	    },
	    /**
	     * @function   
	     * @desc 键盘操作。
	     * @example
	     * Object{
	     *     ENTER: 13, ESC: 27, END: 35, HOME: 36,
	     *     SHIFT: 16, TAB: 9,
	     *     LEFT: 37, RIGHT: 39, UP: 38, DOWN: 40,
	     *     DELETE: 46, BACKSPACE:8
	     * }
	     */
	    keyCode: {
			ENTER: 13, ESC: 27, END: 35, HOME: 36,
			SHIFT: 16, TAB: 9,
			LEFT: 37, RIGHT: 39, UP: 38, DOWN: 40,
			DELETE: 46, BACKSPACE:8
		},
		/**
	     * @function   
	     * @desc 记录ajax请求。
	     * Array['www.we.cn/login','www.we.cn/register'...]
	     */
	    ajax:[]
	};
	
    return config;
});
