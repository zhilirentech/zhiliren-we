define('parsejson',function (require, exports, module) {
	
	/**
     * @name parseJson
     * @class 提供字符串转json方法。
     * @author 李博龙
     * @version v1.0.0  
     * @example
     * define(function(require) {
     *     $(function() {
     *         var  $parsejson = require('parsejson');
     *         seajs.log($parsejson('{name:"dragon"}'));
     *     });
     * });
     */
    var parseJson = function (str) {
        try {
           return require('./json_parse')(str);
        } catch (e) {
			try{
				if ($.type(str) == 'string')
					 return eval('(' + str + ')');
			} catch (e){
				return str;
			}
        }
        return str;
    }

    return parseJson;
});