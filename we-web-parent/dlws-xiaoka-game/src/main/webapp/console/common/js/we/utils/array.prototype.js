define('we/utils/array.prototype',function (require) {
	
	/**
     * @name Array
     * @class Array方法扩展。
     * @author 李博龙
     * @version v1.0.0
     */
	var prototype = {
		/**
    	 * @name Array#max
	     * @function   
	     * @desc 返回数组中最大值。
	     */
		max: function() {
	        // 用正则表达式将前后空格
	        // 用空字符串替代。
			return Math.max.apply({},this)
	    },
	    /**
    	 * @name Array#min
	     * @function   
	     * @desc 返回数组中最小值。
	     */
	    min: function() {
	    	return Math.min.apply({},this)
	    },
	    /**
    	 * @name Array#pushOnly
	     * @function   
	     * @desc 添加唯一值。
	     */
    	pushOnly: function(val){
    		var hasrule = true;
    		try{
    			if(typeof we.utils.inArray == 'undefined'){
    				hasrule = false;
    			}
    		}catch(e){
    			hasrule = false;
    		}
    		if(hasrule && we.utils.inArray(val,this)==-1){
    			return this.push(val);
    		}else{
    			return this;
    		}
    	}
	}
	$.extend(Array.prototype,prototype);
	
	/* 解决ie下indexOf兼容 */
    if (!Array.indexOf) {
        Array.prototype.indexOf = function(obj) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == obj) {
                    return i;
                }
            }
            return -1;
        };
    }
});