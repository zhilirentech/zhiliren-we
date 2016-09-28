define('table',function (require, exports, module) {
	    var init = function(options){
	    	var op = $.extend({
	    		id:'colgroup',
    	        className:['thead','tbody'],
				table:'table'
	    	},options);
	    	    var id = $('#'+op.id);
	    	    var span = id.attr('span');   //跨度
	    	    var width = id.attr('width'); //表格总宽
	    	    var color = id.attr('color');
	    	    var bg = id.attr('bg');
	    	    var oddeven = id.attr('oe');


	    	    $('#'+op.id).find('li').each(function(i){
	    	        var colWidth = $(this).attr('width');  //li宽度
	    	        $('.'+op.table+' ul').each(function(){
	    	            $(this).find('li').eq(i).css('width',colWidth);
	    	            if(width !== null){
	    	                for(var s=0;s<span;s++){
	    	                    $(this).find('li').eq(s).css({
	    	                        'width':width,
	    	                        'color':color,
	    	                        'background':bg
	    	                    });
	    	                }
	    	            }
	    	            // if(oddeven == null){
	    	            //     return;
	    	            // }else{
	    	            //     $(this).find('li'+oddeven).css('background',bg);
	    	            // }
	    	        })
	    	    });
	    }
	    return{
	    	init:init
	    }
})
