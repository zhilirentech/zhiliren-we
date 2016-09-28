define(function(require, exports, module) {
	var init = function() {
	    var mapIniter = require('./initmap.js');
	    mapIniter.init({
	    	itemId:"mapContainer"
	    });
	}
	return{
		init:init
	}
});