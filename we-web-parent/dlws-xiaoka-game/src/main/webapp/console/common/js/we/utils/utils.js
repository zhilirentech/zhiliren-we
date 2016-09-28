define('utils',['we/utils/string.prototype','we/utils/array.prototype'],function(require, exports, module){
	
    /**
     * @name utils
     * @class 全站公用工具类。
     * @requires jquery
     * @author 李博龙
     * @version v1.0.0
     */
    var $ = require('jquery'), utils = {};
    
    require('./string.prototype');
    require('./array.prototype');
    utils = {
		/**
	     * @function   
	     * @desc 判空。
	     * @param {String|Number|Array|Object} param
	     * @return {Boolean} true|false
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.utils.isEmpty('dragon');  //返回：false
	     *         we.utils.isEmpty(undefined); //返回：true
	     *         we.utils.isEmpty(NaN);       //返回：true
	     *     });
	     * });
	     */
        isEmpty:function(param){
            if(typeof param == 'object'){
            	/* 是否为数组 */
            	if((param==null) || (Object.prototype.toString.call(param)==='[object Array]' && param.length==0) || (param.hasOwnProperty('length') && param.length==0)){
            		return true;
            	}
                for(var name in param){
                    return false;
                }
                return true;
            }
            //return typeof param == 'undefined' || typeof param == 'string' && (param == '' || param == 'undefined' || param == '0') || typeof param == 'number' && (param == 0 || isNaN(param));
            return (typeof param == 'undefined' || (typeof param == 'string' && param == '') || (typeof param == 'number' && isNaN(param)));
        },
        /**
	     * @function   
	     * @desc 校验字符串、数字、数组、对象、日期是否相等（暂时不支持Function类型）。
	     * @param {String|Number|Array|Object|Date} paramA
	     * @param {String|Number|Array|Object|Date} paramB
	     * @return {Boolean} true|false
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.utils.isEqual([1,2,3],[0,1,2]);  //返回：false
	     *     });
	     * });
	     */
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
        /**
	     * @function   
	     * @desc 获取页面宽高度。
	     * @return {Object} {w:bw, h:bh}
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.utils.getBrowserWH();  //返回：{w:1024, h:768}
	     *     });
	     * });
	     */
        getBrowserWH:function(){
            var bw, bh;
            if(window.innerWidth){
                bw = window.innerWidth;
            } else if(document.body && document.body.clientWidth){
                bw = document.body.clientWidth;
            }
            if(window.innerHeight){
                bh = window.innerHeight;
            } else if(document.body && document.body.clientHeight){
                bh = document.body.clientHeight;
            }
            if(document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth){
                bh = document.documentElement.clientHeight;
                bw = document.documentElement.clientWidth;
            }
            return {
                w:bw,
                h:bh
            };
        },
        /**
	     * @function   
	     * @desc 获取浏览器信息。
	     * @return {Object} {w:1024, h:768, msie:false, ie6:false, iev:0}
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.browser  //返回：{w:1024（宽度）, h:768（高度）, msie:false（是否IE）, ie6:false（是否IE6）, iev:0（ie版本，非IE返回0）}
	     *     });
	     * });
	     */
        getBrowserInfo: function (){
        	var msVersion = navigator.userAgent.match(/MSIE ([0-9]{1,}[\.0-9]{0,})/),
  	      		msie = !!msVersion,
  	      		iev = msie ? parseFloat(msVersion[1]) : 0,
  	      		ie6 = msie && parseFloat(msVersion[1]) < 7;
        	return $.extend(we.browser,{msie:msie, ie6:ie6, iev:iev},utils.getBrowserWH());
        },
        /**
	     * @function   
	     * @desc 窗口变化执行方法。
	     * @param {Array} array
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.utils.winResize([{name:we.utils.getBrowserInfo, param:null},{name:initLayout, param:null}]);
	     *     });
	     * });
	     */
        winResize:function(arr){
        	if(arr.length == undefined){
        		arr = [arr];
        	}
            $.each(arr, function(i, func){
                if($.isFunction(func.name))func.name.call(this, func.param);
            });
            $(window).bind('resize.we', function(){
                $.each(arr, function(i, func){
                    func.name.call(this, func.param);
                });
            });
        },
        /**
	     * @function   
	     * @desc 链接跳转。
	     * @param {String} url
	     * @param {String} target
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.utils.gotoUrl('www.we.cn','_blank');
	     *     });
	     * });
	     */
        gotoUrl:function(url, target){
        	if(utils.isEmpty(url))return;
        	if(utils.getBrowserInfo().ie6 && $ && $.fn.isTag && !$.isWindow(event.srcElement)){
        		var $target = $(event.srcElement);
        		if($target.isTag('a'))$target.attr('href','#');
        	}
            url = decodeURIComponent(url);
            if(/MSIE (\d+\.\d+);/.test(navigator.userAgent) || /MSIE(\d+\.\d+);/.test(navigator.userAgent)){
                var referLink = document.createElement('a');
                referLink.href = url;
                if(target == '_blank'){
                    referLink.target = target;
                }
                document.body.appendChild(referLink);
                referLink.click();
            }else{
                if(target == '_blank'){
                    window.open(url);
                }else{
                    location.href = url;
                }
            }
        },
        /**
	     * @function   
	     * @desc 检查id格式为name0,name1,name2,name3...的对象是否存在，返回不存在的序列号。
	     * @param {String} name
	     * @return {Number} num
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.utils.checkId('vkoId'); //如果存在vkoId0,vkoId1,vkoId2，则返回：3
	     *     });
	     * });
	     */
        checkId:function(name){
            var i = 0;
            while (i < 1e3){
                if($('#' + name + i).size() > 0){
                    i++;
                }else{
                    break;
                }
            }
            return i;
        },
        /**
	     * @function   
	     * @desc 获取url指定参数值。
	     * @param {String} name
	     * @return {String} val
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         //链接：http://www.we.cn?dragon=30
	     *         we.utils.getUrlParam('dragon'); //返回：30
	     *     });
	     * });
	     */
        getUrlParam:function(name){
            var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)');
            var r = window.location.search.substr(1).match(reg);
            if(r != null){
                return unescape(r[2]);
            }
            return null;
        },
        /**
	     * @function   
	     * @desc 图片加载失败替换方法。
	     * @param {Object} imgs
	     * @param {String} src
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         //图片加载失败替换为404图
	     *         setTimeout(function(){
	     *             we.utils.checkImg($('.uphoto img'),we.vr.static + '/v5/images/vkoweb/pic/user404_middle.jpg');
	     *             we.utils.checkImg($('.zphoto img'),we.vr.static + '/v5/images/vkoweb/pic/zao404.jpg');
	     *         },500);
	     *     });
	     * });
	     */     
        checkImg:function(items, img){
            if(utils.isEmpty(items) || items.size() < 1) return;
            if(utils.isEmpty(img)) return;
            items.each(function(){
                utils.imgloaderror(this, function(){
                    this.src = img;
                });
            });
        },
        /**
	     * @function   
	     * @desc 图片加载失败执行回调方法。
	     * @param {Object} img
	     * @param {Function} error
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.utils.imgloaderror(this, function(){
	     *             this.src = img;
	     *         });
	     *     });
	     * });
	     */    
        imgloaderror:function($img, error){
            var img = new Image();
            img.onerror = function(){
                error && error.call($img);
            };
            img.src = $img.attr('src');
        },
        /**
	     * @function   
	     * @desc 自定义加入收藏夹。
	     * @param {String} title
	     * @param {String} url
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         $('.addbook').click(function(){
	     *             we.utils.addBookmark('微课网','http://www.we.cn');
	     *         });
	     *     });
	     * });
	     */    
        addBookmark:function(title, url){
            url = url || a.href;
            title = title || a.title;
            try {
                // IE
                window.external.addFavorite(url, title);
            } catch (e){
                try {
                    // Firefox
                    window.sidebar.addPanel(title, url, '');
                } catch (e){
                    if(/Opera/.test(window.navigator.userAgent)){
                        // Opera
                        a.rel = 'sidebar';
                        a.href = url;
                        return true;
                    }
                    alert('亲，请使用 Ctrl+D 进行添加');
                }
            }
            return false;
        },
        /**
	     * @function   
	     * @desc 设为首页。
	     * @param {String} url
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         $('.addbook').click(function(){
	     *             we.utils.setHome('http://www.we.cn');
	     *         });
	     *     });
	     * });
	     */    
        setHome:function(url){
        	if(document.all){
    	        document.body.style.behavior='url(#default#homepage)';
    	          document.body.setHomePage(window.location.href);
    	    }else if(window.sidebar){
    	        if(window.netscape){
    	            try{
    	                netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');
    	            }catch (e){
    	                alert('该操作被浏览器拒绝，如果想启用该功能，请在地址栏内输入 about:config,然后将项 signed.applets.codebase_principal_support 值该为true');
    	            }
    	        }
    	        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components. interfaces.nsIPrefBranch);
    	        prefs.setCharPref('browser.startup.homepage',window.location.href);
    	    }else{
    	    	if($dialog){
    	        	$dialog.alert('您的浏览器不支持自动设置首页, 请使用浏览器菜单手动设置!','warning');
    	        }
    	    }
        },
        /**
	     * @function   
	     * @desc 阻止事件冒泡,使成为捕获型事件触发机制。
	     * @param {Object} event
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         //<a class="addbook" href="http://www.we.cn">加入收藏夹</a>
	     *         $('.addbook').click(function(){
	     *             we.utils.stopBubble(e);
	     *         });
	     *     });
	     * });
	     */    
        stopBubble:function(e){
        	//如果提供了事件对象，则这是一个非IE浏览器 
        	if(e && e.stopPropagation){
        		//因此它支持W3C的stopPropagation()方法 
        	    e.stopPropagation(); 
        	}else{
        		//否则，我们需要使用IE的方式来取消事件冒泡 
        		window.event.cancelBubble = true; 
        	}
        },
        /**
	     * @function   
	     * @desc 阻止浏览器的默认行为。
	     * @param {Object} event
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         //<a class="addbook" href="http://www.we.cn">加入收藏夹</a>
	     *         $('.addbook').click(function(){
	     *             we.utils.stopBubble(e);
	     *         });
	     *     });
	     * });
	     */    
        stopDefault:function(e){
    		//阻止默认浏览器动作(W3C) 
        	if(e && e.preventDefault){
        		 e.preventDefault(); 
        	}else{
        		//IE中阻止函数器默认动作的方式 
        		window.event.returnValue = false; 
        	}
            return false; 
        },
        /**
	     * @function   
	     * @desc 动态创建对象。
	     * @param {String} key
	     * @param {String|Number|Array|Object} value
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.utils.newObj(we.utils.isEmpty(skey)?name:skey,value);
	     *     });
	     * });
	     */    
    	newObj: function(key, value){
    		var obj = new Object();
    		eval('obj.'+key+'=value');
    		return obj;
    	},
    	//添加对象的属性
    	addObjAttr:function(object,key, value){
    		object[key]=value;
    		return object;
    	},
    	/**
	     * @function   
	     * @desc 判断在数组中的位置。
	     * @param {String|Number|Array|Object} val
	     * @param {Array} arr
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         if(we.utils.inArray(options,we.ajax)!=-1){seajs.log('已经有啦！')}
	     *     });
	     * });
	     */ 
    	inArray: function(val, arr){
    		for(var i in arr){
				if(utils.isEqual(val,arr[i])){
					return i;
				}
    		}
    		return -1;
    	},
    	/**
	     * @function   
	     * @desc 数组添加唯一值。
	     * @param {String|Number|Array|Object} val
	     * @param {Array} arr
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.utils.pushOnly('1',[0,2,3]);
	     *     });
	     * });
	     */
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
		},
    	/**
	     * @function   
	     * @desc 重新加载当前页面。
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.utils.reload();
	     *     });
	     * });
	     */
    	reload: function(){
    		window.location.reload();
    	},
    	/**
	     * @function   
	     * @desc 获取页面Flash。
	     * @param {String} movieName
	     * @return {Object} object
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.utils.getSwf('movieName');
	     *     });
	     * });
	     */
        getSwf: function (movieName){
            if(window.document[movieName]){
                return window.document[movieName];
            }
            if(navigator.appName.indexOf('Microsoft Internet') == -1){
                if(document.embeds && document.embeds[movieName]){
                    return document.embeds[movieName];
                }
            }else{
                // if(navigator.appName.indexOf('Microsoft Internet')!=-1)
                return document.getElementById(movieName);
            }
        },
        /**
	     * @function   
	     * @desc 页面滚动方法。
	     * @param {Number} top
	     * @param {Function} callback
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *     	   //示例1：滚动到距页面顶部100px;
	     *         we.utils.scrollTo(100);
	     *         
	     *         //示例1：滚动到某个div处，并报告；
	     *         we.utils.scrollTo($('.div').offset().top, function(){
	     *         	    seajs.log('我滚到了！');
	     *         });
	     *     });
	     * });
	     */
        scrollTo: function(top, callback){
        	$('html,body').animate({scrollTop:top},{duration:300, complete:function(){
        		if(!utils.isEmpty(callback) && $.isFunction(callback))callback.call(this);
        	}});
        },
        /**
	     * @function   
	     * @desc 检测当前浏览器Flash支持情况。
	     * @return {Object} {hasflash, version}
	     * @example
	     * define(function(require){
	     *     $(function(){
	     *         we.utils.flashChecker();	//返回{hasflash: true, version: 11}
	     *     });
	     * });
	     */
        flashChecker: function(){
        	var hasFlash = false, flashVersion = 0;
        	if(utils.getBrowserInfo().msie){
        		var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash'); 
        		if(swf){
        			hasFlash = true;
        			VSwf = swf.GetVariable("$version");
        			flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]); 
        		}
        	}else{
        		if(navigator.plugins && navigator.plugins.length > 0){
        			var swf = navigator.plugins["Shockwave Flash"];
        			if(swf){
        				hasFlash = true;
    					var words = swf.description.split(" ");
						for(var i = 0; i < words.length; ++i){
							if(isNaN(parseInt(words[i]))) continue;
							flashVersion = parseInt(words[i]);
						}
					}
        		}
        	}
        	return {hasflash:hasFlash,version:flashVersion};
        }/*,
        isWindow: function(obj){
        	if(!obj || !obj.window || !obj.document )  
        	    return false;  
        	  var expando = "dom"+(new Date-0)    //生成一个随机变量名  
        	  var doc = obj.document;  
        	  //全局解析代码，IE的eval只对原作用域有效  
        	  //详见http://www.javaeye.com/topic/519098  
        	  //加之eval与with是 html5严格模式下要禁止的东西，弃之不用！  
        	  try{  
        	    var js =  doc.createElement("script");  
        	    var head = doc.getElementsByTagName("head")[0];  
        	    head.insertBefore(js,head.firstChild);  
        	    js.text = expando + " = {};"  
        	    head.removeChild(js);  
        	    var ret =  (doc.parentWindow || doc.defaultView)[expando] === obj[expando];  
        	    obj[expando] = void 0;  
        	  }catch(e){  
        	    return false;  
        	  }  
        	  return ret;  
        }*/
    }

    return utils;
});