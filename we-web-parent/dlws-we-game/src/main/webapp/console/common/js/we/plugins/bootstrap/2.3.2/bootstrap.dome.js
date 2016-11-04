define(function (require, exports, module) {
	$(function(){
		require('./css/bootstrap.min.css')
		require('./css/bootstrap-responsive.min.css')
		require('./css/buttons.css')
		require('./css/font-awesome.min.css')
		require('./css/showcase.css')
		require('./css/docs.css')
		require('./common.css')
		require('./css/prettify.css')
		require('./js/bootstrap.min.js')
		require('./js/holder.min.js')
		require('./js/prettify.js');
		require('./js/respond.min.js');
		
		
		//$('.navbar-example').scrollspy();
	    $('[data-toggle="tooltip"]').tooltip();
		$('.dropdown-toggle').dropdown();
		prettyPrint()
	});
});