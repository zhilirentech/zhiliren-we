define(function(require){
	$(function(){
		$("#studyId").change(function(){
			 var $this = $(this);
			 subjectSelect($this.val());
		});
		function subjectSelect(id){
			$ajax.vkoAjax({
				url: "/audit/listBySubject.json",
				data: {
				    id: id
				},
				type: "POST",
				success: function(data){
					var sk = $("#subId");
					for(var i=0;i<data.length;i++){
						sk.append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
					}
					$('[name=subject]').selectRefresh();
				}
			});
		};
		subjectSelect($("#studyId").val());
	});
});
