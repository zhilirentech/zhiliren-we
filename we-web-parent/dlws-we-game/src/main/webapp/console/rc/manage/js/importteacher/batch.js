define(function (require, exports, module) {
	$(function() { 
		 /**
		  * 验证上传的文件格式
		  */
		 $("input[name=filedata]").on('change',function(){
	    		var imgFileName=$(this).val();
	    		var suffix=imgFileName.substr(imgFileName.lastIndexOf('.')+1);
	  	    	var str="xlsx";
	  	    	if(str.indexOf(suffix)<0){
	  	    		$dialog.alert('不支持该格式的文件','warning');
	  	    		$("input[name=filedata]").val("");
	  	    		return;
	  	    	}
 	     });
		 /**
		  * 下载excel模板
		  */
		 $('.btnDownloadTcert').click(function(){
				we.utils.gotoUrl('/importteacher/downloadExcelTemp.html');
	     });
		 require('validate');
		 $('#batchAddTeachersForm').validate({
			 submitHandler:function(){
				 $ajax.weSubmit($('#batchAddTeachersForm'),{
					 url : '/importteacher/batchAddTeacher.json',
					 success : function(data){					 
						 $.alert(data.message,"success");
					 },
					 error : function(data){
						 setTimeout(function(){						 
							 $.alert(data.message,"warning",20000);
						 },100);
					 }
				 });				 
			 },
			 rules:{
				  filedata:{
                      required:true
                  }
			 }
		 });
	});
});