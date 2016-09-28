define(function(require, exports, module) {
    /**
     * @name vko
     * @class 全站公用工具类。
     * @requires jquery, cookie, imgready
     * @author 李博龙
     * @version v1.0.0
     */
    var $ = require("jquery"), utils = {};
    utils = {
        /* 判空 */
        isEmpty:function(param) {
            if (typeof param == "object") {
            	/* 是否为数组 */
            	if((param==null) || (Object.prototype.toString.call(param)==='[object Array]' && param.length==0) || (param.hasOwnProperty('length') && param.length==0)){
            		return true;
            	}
                for (var name in param) {
                    return false;
                }
                return true;
            }
            return typeof param == "undefined" || typeof param == "string" && (param == "" || param == "undefined" || param == "0") || typeof param == "number" && (param == 0 || isNaN(param));
        },
        /* 校验字符串、对象、数组、日期是否相等（暂时不支持Function类型）*/
        isEqual: function(paramA, paramB){
    		if(paramA instanceof Array){
    			if(!(paramB instanceof Array)){ 
                     return false; 
                 } 
                 var aLen = paramA.length, 
                     bLen = paramB.length; 
                 if(aLen != bLen){ 
                     return false; 
                 } 
                 var isEqual = true,num = 0; 
                 for(var i = 0;i < aLen;i++){ 
                     if(paramA[i]!=paramB[i] && typeof paramA[i]=='object' && typeof paramB[i]=='object'){ 
                       isEqual = arguments.callee.apply(null,[paramA[i],paramB[i]]); 
                       //isEqual = compareobj(paramA[i],paramB[i]); 
                     }else if($.isFunction(paramA[i]) && $.isFunction(paramB[i])){
                    	 //跳过Function类型判断
                    	 isEqual = true;
                     }else{ 
                       isEqual = paramA[i]===paramB[i]; 
                     } 
                     if(isEqual){ 
                         num++; 
                     } 
                 } 
                 if(num != aLen){ 
                     return false; 
                 }else{ 
                     return true; 
                 } 
             }else if(paramA instanceof Date){ 
                 if(!(paramB instanceof Date)){ 
                     return false; 
                 }else{ 
                     return paramA.getTime() == paramB.getTime(); 
                 } 
             }else if(paramA instanceof Object){ 
                 if((paramB instanceof Array) || (paramB instanceof Date) || !(paramB instanceof Object)){ 
                     return false; 
                 }else{ 
                    var aLen=bLen=0; 
                    for(var i in paramA){ 
                         aLen++; 
                    } 
                    for(var i in paramB){ 
                         bLen++; 
                    } 
                    if(aLen != bLen){ 
                         return false; 
                    } 
                    var isEqual = true,num = 0; 
                    for(var i in paramA){ 
                      if(typeof paramA[i] == 'object' && typeof paramB[i] == 'object' && paramA[i]!=paramB[i]){ 
                         isEqual = arguments.callee.apply(null,[paramA[i],paramB[i]]); 
                        // isEqual = compareobj(paramA[i],paramB[i]); 
                      }else if($.isFunction(paramA[i]) && $.isFunction(paramB[i])){
                     	 //跳过Function类型判断
                     	 isEqual = true;
                      }else{ 
                        isEqual = paramA[i]===paramB[i]; 
                      } 
                      if(isEqual){ 
                         num++; 
                      } 
                    } 
                    if(num != aLen){ 
                         return false; 
                    }else{ 
                         return true; 
                    } 
                 } 
             }else{ 
                 if(paramB instanceof Object){ 
                     return false; 
                 }else{ 
                     return paramA===paramB; 
                 } 
             }
        },
        /* 获取vg */
        getVgid:function() {
            var $cookie = require("cookie"), gid = $cookie.get("vg");
            if (gid == undefined) {
                gid = "j-";
                for (var i = 1; i <= 32; i++) {
                    gid += Math.floor(Math.random() * 16).toString(16);
                }
                $cookie.set("vg", gid);
            }
            return gid;
        },
        /* 获取页面宽高 */
        getBrowserWH:function() {
            var bw, bh;
            if (window.innerWidth) {
                bw = window.innerWidth;
            } else if (document.body && document.body.clientWidth) {
                bw = document.body.clientWidth;
            }
            if (window.innerHeight) {
                bh = window.innerHeight;
            } else if (document.body && document.body.clientHeight) {
                bh = document.body.clientHeight;
            }
            if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
                bh = document.documentElement.clientHeight;
                bw = document.documentElement.clientWidth;
            }
            return {
                w:bw,
                h:bh
            };
        },
        /* 窗口变化 */
        winResize:function(arr) {
        	if(arr.length == undefined){
        		arr = [arr];
        	}
            $.each(arr, function(i, func) {
                if($.isFunction(func.name))func.name.call(this, func.param);
            });
//            $(window).bind("resize.vko", function() {
//                $.each(arr, function(i, func) {
//                    func.name.call(this, func.param);
//                });
//            });
        },
        /* 链接跳转 */
        gotoUrl:function(url, target) {
            url = decodeURIComponent(url);
            if (/MSIE (\d+\.\d+);/.test(navigator.userAgent) || /MSIE(\d+\.\d+);/.test(navigator.userAgent)) {
                var referLink = document.createElement("a");
                referLink.href = url;
                if (target == "_blank") {
                    referLink.target = target;
                }
                document.body.appendChild(referLink);
                referLink.click();
            } else {
                if (target == "_blank") {
                    window.open(url);
                } else {
                    location.href = url;
                }
            }
        },
        /* 检查id */
        checkId:function(name) {
            var i = 0;
            while (i < 1e3) {
                if ($("#" + name + i).size() > 0) {
                    i++;
                } else {
                    break;
                }
            }
            return i;
        },
        /* 获取url指定参数值 */
        getUrlParam:function(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return unescape(r[2]);
            }
            return null;
        },
        /* 图片加载失败替换方法 */
        checkImg:function(items, img) {
            if (utils.isEmpty(items) || items.size() < 1) return;
            if (utils.isEmpty(img)) return;
            items.each(function() {
                utils.imgloaderror(this, function() {
                    this.src = img;
                });
            });
        },
        imgloaderror:function($img, error) {
            var img = new Image();
            img.onerror = function() {
                error && error.call($img);
            };
            img.src = $img.src;
        },
        /* 加入收藏 */
        addBookmark:function(title, url) {
            url = url || a.href;
            title = title || a.title;
            try {
                // IE
                window.external.addFavorite(url, title);
            } catch (e) {
                try {
                    // Firefox
                    window.sidebar.addPanel(title, url, "");
                } catch (e) {
                    if (/Opera/.test(window.navigator.userAgent)) {
                        // Opera
                        a.rel = "sidebar";
                        a.href = url;
                        return true;
                    }
                    alert("亲，请使用 Ctrl+D 进行添加");
                }
            }
            return false;
        },
        /* 设为首页 */
        setHome:function(url) {
        	if (document.all){
    	        document.body.style.behavior='url(#default#homepage)';
    	          document.body.setHomePage(window.location.href);
    	    }else if (window.sidebar){
    	        if(window.netscape){
    	            try{
    	                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
    	            }catch (e){
    	                alert( "该操作被浏览器拒绝，如果想启用该功能，请在地址栏内输入 about:config,然后将项 signed.applets.codebase_principal_support 值该为true" );
    	            }
    	        }
    	        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components. interfaces.nsIPrefBranch);
    	        prefs.setCharPref('browser.startup.homepage',window.location.href);
    	    }else{
    	    	if($dialog){
    	        	$dialog.alert('您的浏览器不支持自动自动设置首页, 请使用浏览器菜单手动设置!','warning');
    	        }
    	    }
        },
        /* 阻止事件冒泡,使成为捕获型事件触发机制 */
        stopBubble:function stopBubble(e) {
        	//如果提供了事件对象，则这是一个非IE浏览器 
        	if ( e && e.stopPropagation ){
        		//因此它支持W3C的stopPropagation()方法 
        	    e.stopPropagation(); 
        	}else{
        		//否则，我们需要使用IE的方式来取消事件冒泡 
        		window.event.cancelBubble = true; 
        	}
        },
        /* 阻止浏览器的默认行为 */
        stopDefault:function(e) {
    		//阻止默认浏览器动作(W3C) 
        	if ( e && e.preventDefault ){
        		 e.preventDefault(); 
        	}else{
        		//IE中阻止函数器默认动作的方式 
        		window.event.returnValue = false; 
        	}
            return false; 
        },
        jsonEval:function(data) {
    		try{
    			if ($.type(data) == 'string')
    				return eval('(' + data + ')');
    			else return data;
    		} catch (e){
    			return {};
    		}
    	},
    	/* 动态创建对象 */
    	newObj: function(key, value){
    		var obj = new Object();
    		eval('obj.'+key+'=value');
    		return obj;
    	},
    	/* 判断在数组中的位置 */
    	inArray: function(val, arr){
    		for(var i in arr){
				if(utils.isEqual(val,arr[i])){
					return i;
				}
    		}
    		return -1;
    	},
    	/* 数组添加唯一值 */
    	pushOnly: function(val, arr){
    		if(utils.inArray(val,arr)==-1){
    			arr.push(val);
    		}
    	},
    	/**
	     * @function   
	     * @desc 冒泡排序。
	     * @param {Array} arr
	     * @param {String} type(asc|desc)
	     * @param {Function} callback
	     * @return {Array} arr
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.utils.pushOnly('1',[0,2,3]);
	     *     });
	     * });
	     */
    	bubbleSort: function (arr, type, callback){
    		//asc,desc
			var temp,exchange;
			for(var i=0; i<arr.length; i++){
				exchange = false;
				for(var j=arr.length-2; j>=i; j--){
					if(type=='asc'){//asc升序
						if(arr[j+1]<arr[j]){
							temp = arr[j+1];
							arr[j+1] = arr[j];
							arr[j] = temp;
							if(!we.utils.isEmpty(callback) && $.isFunction(callback))callback.call(this,j,j+1);
							exchange = true;
						}
					}else{//desc降序
						if(arr[j+1]>arr[j]){
							temp = arr[j+1];
							arr[j+1] = arr[j];
							arr[j] = temp;
							if(!we.utils.isEmpty(callback) && $.isFunction(callback))callback.call(this,j,j+1);
							exchange = true;
						}
					}
					
				}
				if(!exchange)break;
			}
			return arr;
		}
    }

    $.extend(String.prototype,{
    	/* 增加字符串的trim */
		trim: function() {
	        // 用正则表达式将前后空格
	        // 用空字符串替代。
	        return this.replace(/(^\s*)|(\s*$)/g, "");
	    },
	    /* 增加字符串替换方法 */
	    replaceAll: function(reallyDo, replaceWith, ignoreCase) {  
	        if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
	            return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
	        } else {  
	            return this.replace(reallyDo, replaceWith);  
	        }  
	    },
	    /* 判断是否站外链接 */
	    isExternalUrl: function (){
			return this.isUrl() && this.indexOf("://"+document.domain) == -1;
		},
		/* 判断是静态链接链接 */
	    isStaticUrl: function (){
			return this.isUrl() && (this.indexOf("://static.we.cn") == -1 || this.indexOf("://cdn.we.cn") == -1);
		},
		isUrl: function(){
			return (new RegExp(/^[a-zA-z]+:\/\/([a-zA-Z0-9\-\.]+)([-\w .\/?%&=:]*)$/).test(this));
		},
		isPositiveInteger:function(){
			return (new RegExp(/^[1-9]\d*$/).test(this));
		},
		skipChar:function(ch) {
			if (!this || this.length===0) {return '';}
			if (this.charAt(0)===ch) {return this.substring(1).skipChar(ch);}
			return this;
		}
    });
    
	$.extend(Array.prototype,{
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
	});
    
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
    return utils;
});