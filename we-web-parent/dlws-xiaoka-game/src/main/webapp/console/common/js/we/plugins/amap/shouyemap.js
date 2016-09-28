define(function(require, exports, module) {
	var init = function(options) {
		var lnglatXY=[$('input[name="longitude"]').val(), $('input[name="latitude"]').val()];
		if($('input[name="longitude"]').val()==''){
			$('.ditu-text').text('该teacher还没有设置常用授课地点');
		};
		var map = new AMap.Map('ditu',{
		            resizeEnable: true,
		            zoom: 13,
		            center: lnglatXY
		});
	    AMap.plugin('AMap.Geocoder',function(){
		        var geocoder = new AMap.Geocoder({
		            city: "010"//城市，默认：“全国”
		        });
		        var marker = new AMap.Marker({
		            map:map,
		            bubble:true
		        });

		        
		        
		        geocoder.getAddress(lnglatXY,function(status,result){
	              if(status=='complete'){
	              	marker.setPosition(lnglatXY);
	                
	                $('.ditu-text').text(result.regeocode.formattedAddress);
	              }
	            })
	    });
	}
	return {
		shouyemap:init
	}
})