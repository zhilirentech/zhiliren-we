define('scrollbar',function (require, exports, module) {
	require('./js/jquery.mousewheel.min.js');
	require('./js/jquery.mCustomScrollbar.concat.min.js');
	require('./css/jquery.mCustomScrollbar.css');
	
	$(function(){
		$(".ztree-wrap").mCustomScrollbar({
			scrollInertia:0,
			autoDraggerLength:false
		});
	});
});