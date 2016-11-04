define('tab',function (require, exports, module) {
	var init = function(options){
		
		var options = $.extend({
			
			},options);
		
		childF = $('.silder *[tab=1]').children();
		childS = $('.silder *[tab=2]').children();
		$.each(childF,function(i,n){
			var _self = $(this);
			_self.live('click',function(){
				_self.addClass('crt').siblings().removeClass('crt');
				childS.eq(i).show().siblings().hide();
				var selfHtml = _self.html();
				$(options.em).html(selfHtml);
			})
		})
		
	}
	return{
		init:init
	}

});