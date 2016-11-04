define(function(require){
	$(function(){
		$('.knowpointQueryForm input[stype=studyPhase]').live('click',function(){
			var $this =$(this);
			if(!$this.hasClass("btn_modify")){
				$('.knowpointQueryForm input[stype=studyPhase]').removeClass("btn_modify");
				$this.addClass('btn_modify');
			}
			$('input[name=studyPhase]').val($this.attr('id'));
			$('.knowpointQueryForm').submit();
		});
		
		$('.knowpointQueryForm input[stype=subject]').live('click',function(){
			var $this =$(this);
			if(!$this.hasClass("btn_modify")){
				$('.knowpointQueryForm input[stype=subject]').removeClass("btn_modify");
				$this.addClass('btn_modify');
			}
			$('input[name=subject]').val($this.attr('id'));
			$('.knowpointQueryForm').submit();
		});
		
		
	});
});
