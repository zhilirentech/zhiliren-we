define(function(require, exports, module) {
	require('amap');
	var init = function() {
	    var mapIniter = require('./initmap.js');
	    mapIniter.initmap({
	    	itemId:	'mapContainer'
	    });
	}
	return{
		init:init
	}
});