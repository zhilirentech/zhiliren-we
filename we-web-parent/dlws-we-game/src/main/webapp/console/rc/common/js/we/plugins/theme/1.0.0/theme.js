define(function(require, exports, module){

	/**
     * @name Theme
     * @class 提供切换界面主题风格处理。
     * @requires jquery, cookie
     * @author 李博龙
     * @version v1.0.0  
     */
	var $ = require('jquery'),
		$cookie = require('cookie');
	
	var setTheme = function(name){
		$('#themeStyle').attr('href', we.vr.static+'/platform/vko-ria/themes/'+name+'/style.css');
		$('#themeList [theme='+name+']').addClass('current').siblings().removeClass('current');
		$cookie.set('vko_theme', name);
	}
	
	var curTheme = $cookie.get('vko_theme');
	if(!we.utils.isEmpty(curTheme)){
		setTheme(curTheme);
	}
	
	$('#themeList li').click(function(){
		setTheme($(this).attr('theme'));
	});
	
	exports.set = setTheme;
});