;(function(){
	
	/**
     * @name weFileLoader
     * @class 提供时间戳version参数及动态加载static.we.cn下js/css的方法load()等。
     * @author 李博龙
     * @author 庄君祥
     * @version v1.0.0
     * @example
     * weFileLoader.version      //返回时间戳，每次发包均会修改，默认值：?t=160207022013
     * weFileLoader.root         //返回本js所在域名地址，默认值：http://static.we.cn
     * weFileLoader.isonline     //返回是否上线，每次发包均会修改，默认值：false
     * weFileLoader.getHost(url) //返回指定url（默认为当前页面）域名地址
     * weFileLoader.load(urls)   //加载js/css，参数支持单个和数组形式
     */
	var root = '', version = '?t=00002013',
	/**
	 * 获取指定url（默认为当前页面）域名地址
	 * @param {String} url
	 */
	getHost = function(url) {
	    var host = 'null';
	    if (typeof url == 'undefined' || null == url) {
	        url = window.location.href;
	    }
	    var regex = /.*\:\/\/([^\/]*).*/;
	    var match = url.match(regex);
	    if (typeof match != 'undefined' && null != match) {
	        host = match[1];
	    }
	    return host;
	},
	/**
	 * 获取本js所在域名地址，默认返回static.we.cn
	 */
	getroot = function(){
		var list = document.getElementsByTagName('script');
		for(var i in list){
			if(list[i].src!=undefined && list[i].src.indexOf('wefileloader.js')>0){
				return 'http://'+getHost(list[i].src);
			}
		}
		return 'http://static.we.cn'
	},
	getDomain=function(){
		var host = getHost();
		return host.substring(host.indexOf(".")+1);
	},
	/**
	 * 去除链接时间戳
	 * @param {String} url
	 */
	formatUrl = function(url){
		if(typeof url != 'string'){
    		return '';
    	}
		var t = url.indexOf('?');
		if(t!=-1){
			url = url.substring(0,t);
		}
		return url;
	},
	/**
	 * 判断重复
	 * @param {String} url
	 */
	isExist = function(url,tagename,attr){
		var list = [];
		list = document.getElementsByTagName(tagename);
        for(var i=0; i<list.length; i++){
        	if(formatUrl(list[i].getAttribute(attr))==url){
        		//console.log('文件已存在：'+url);
    			return true;
    		}
    	}
        return false;
	},
	/**
	 * 加载js文件
	 * @param {String} url
	 */
	importJS = function(url){
        var script;
        script = document.createElement('script');
        script.type = 'text/javascript';
        append(script, url, 'src');
	},
	/**
	 * 加载css文件 
	 * @param {String} url
	 */
	importCSS = function(url){
        var link;
        link = document.createElement('link');
        link.rel = 'stylesheet';
        link.type = 'text/css';
        link.media = 'screen';
    	append(link, url, 'href');
	},
	/**
	 * 添加标签 
	 * @param {Object, String, String} tag, url, attr
	 */
	append = function(tag, url, attr){
    	head = document.getElementsByTagName('head')[0];
    	if(url.indexOf('://')==-1){
    		url = root+url;
    	}
    	tag.setAttribute(attr,url+version);
    	head.appendChild(tag);
    },
	/**
	 * 根据后缀判断加载css/js文件 
	 * @param {String} url
	 */
    load = function(url){
    	if(typeof url != 'string'){
    		return;
    	}
    	url = formatUrl(url);
        if(/\.js(?:\?\S+|#\S+)?$/.test(url)){
        	if(isExist(url,'script','src')){
    			return;
    		}
        	importJS(url);
        }else if(/\.css(?:\?\S+|#\S+)?$/.test(url)){
        	if(isExist(url,'link','href')){
    			return;
    		}
        	importCSS(url);
        }
    }
    root = getroot();
	/* 公开参数+方法 */
	window['weFileLoader'] = {};
	window['weFileLoader']['version'] = version;
	window['weFileLoader']['root'] = root;
	window['weFileLoader']['domain'] = root.substring(root.indexOf(".")+1);
	window['weFileLoader']['getDomain'] = getDomain;
	window['weFileLoader']['isonline'] = version.substring(3,version.length)=='00002013'?false:true;
	window['weFileLoader']['getHost'] = getHost;
	window['weFileLoader']['load'] = function(urls){
		var urls = ('string'===typeof urls)?[urls]:urls,
			len = (urls instanceof Array)?urls.length:0;
	    for(var i=0;i<len;i++){
	        load(urls[i]);
	    }
	}
})();