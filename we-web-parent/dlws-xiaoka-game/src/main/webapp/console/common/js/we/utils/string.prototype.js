define('we/utils/string.prototype',function (require) {
	
	/**
     * @name String
     * @class String方法扩展。
     * @author 李博龙
     * @version v1.0.0
     */
	var prototype = {
		/**
    	 * @name String#trim
	     * @function   
	     * @desc 字符串的trim。
	     */
		trim: function() {
	        // 用正则表达式将前后空格
	        // 用空字符串替代。
	        return this.replace(/(^\s*)|(\s*$)/g, "");
	    },
	    /**
    	 * @name String#replaceAll
	     * @function   
	     * @desc 字符串替换方法。
	     */
	    replaceAll: function(reallyDo, replaceWith, ignoreCase) {
	        if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
	            return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
	        } else {  
	            return this.replace(reallyDo, replaceWith);  
	        }  
	    },
	    /**
    	 * @name String#isExternalUrl
	     * @function   
	     * @desc 判断是否站外链接。
	     */
	    isExternalUrl: function (){
			return this.isUrl() && this.indexOf("://"+document.domain) == -1;
		},
		/**
    	 * @name String#isUrl
	     * @function   
	     * @desc 判断是否链接。
	     */
		isUrl: function(){
			return (new RegExp(/^[a-zA-z]+:\/\/([a-zA-Z0-9\-\.]+)([-\w .\/?%&=:]*)$/).test(this));
		},
		/**
    	 * @name String#isStaticUrl
	     * @function   
	     * @desc 判断是否静态链接链接。
	     */
	    isStaticUrl: function (){
			return this.isUrl() && (this.indexOf("://static.we.cn") == -1 || this.indexOf("://cdn.we.cn") == -1);
		},
		/**
    	 * @name String#isPositiveInteger
	     * @function   
	     * @desc 判断是否正整数。
	     */
		isPositiveInteger:function(){
			return (new RegExp(/^[1-9]\d*$/).test(this));
		},
		/**
    	 * @name String#skipChar
	     * @function   
	     * @desc 删除第一个字符。
	     */
		skipChar:function(ch) {
			if (!this || this.length===0) {return '';}
			if (this.charAt(0)===ch) {return this.substring(1).skipChar(ch);}
			return this;
		}
	}
	$.extend(String.prototype,prototype);
});