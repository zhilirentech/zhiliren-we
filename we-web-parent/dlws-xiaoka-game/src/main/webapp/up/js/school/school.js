/**
Copyright @ 团购
 */
Zepto(function($){
	$('#cityScroll').height($(window).height() - 48);
	var myScroll;
	myScroll = new iScroll('cityScroll');
	document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
	
	$('.schoolSelect-content').each(function(){
		$('#'+$(this).attr('id')).height($(window).height() - 48);
		var myScroll2;
		myScroll2 = new iScroll($(this).attr('id'));
		document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
		$('.scrollbar').remove();
	});
	
	// 鼠标点击搜索框时动作
	touch.on($(".school .schoolSearchForm form"), 'tap', function(){
	    $('.schoolSearch').addClass('show');
		$('.schoolSelect').removeClass('show');
		$('.schoolSearchForm input[type="button"]').addClass('button');
		$('.schoolSearchForm input[type="text"]').addClass('text').focus();
		$('.schoolSearchForm').addClass('searchForm');
		$('.schoolClose').addClass('show');
	});
	touch.on($(".schoolClose"), 'tap', function(){
		$('.schoolSearch').removeClass('show');
		$('.schoolSelect').addClass('show');
		$('.schoolSearchForm input[type="button"]').removeClass('button');
		$('.schoolSearchForm input[type="text"]').removeClass('text').blur();
		$('.schoolSearchForm').removeClass('searchForm');
		$('.schoolClose').removeClass('show');
	});

	// 屏幕移动，城市随着移动
	// touch.on($(window), 'touchstart touchmove', function(ev){dd();});
//	$(document).scroll(function() {dd()});
//	function dd(){
//		if ($(window).scrollTop() >= 48) {
//			$('.schoolSelect-city').css({'position':'fixed','top':'0'});
//		}else if($(window).scrollTop() < 48 || ($(window).scrollTop() == 0)){
//			$('.schoolSelect-city').removeAttr('style');
//		}
//	}

	// 鼠标点击城市切换内容
	touch.on($(".schoolSelect .schoolSelect-city li"), 'touchstart', function(){
		$(this).addClass('active').siblings().removeClass('active');
		var id = $(this).attr('data-tab');
		$("#scroll_"+id).addClass('show').siblings('.schoolSelect-content').removeClass('show');
		$('.schoolSelect-content').each(function(){
			$('#'+$(this).attr('id')).height($(window).height() - 48);
			var myScroll2;
			myScroll2 = new iScroll($(this).attr('id'));
			document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
		});
		$('.scrollbar').remove();
	});
//	
//	
//	var contentHeight = $(window).height()-$('.schoolSearchForm').height()-21;
//    $(".swiper-container-v").height(contentHeight);
//    if ($('.swiper-scrollbar').length < 1) {
//          $("body").append('<div class="swiper-scrollbar"></div>');
//      }
//
//      // tab标签内容上下滚动
//      var swiper = new Swiper('.swiper-container-v', {
//          scrollbar: '.swiper-scrollbar',
//          direction: 'vertical',
//          slidesPerView: 'auto',
//          mousewheelControl: true,
//          freeMode: true
//      });
	
	
	
});

