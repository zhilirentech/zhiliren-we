define('cookie',function (require, exports, module) {

    /**
     * @name Cookie
     * @class 提供 Cookie 操作方法，支持读取get、设置set、删除remove。
     * @author 李博龙
     * @version v1.0.0  
     */

    var Cookie = exports;

    var decode = decodeURIComponent;
    var encode = encodeURIComponent;


    /**
     * @desc 获取 cookie 值
     * @param {String} name
     * @param {Function|Object} options (Optional) 
     * @return {String|undefined} <b>注：如果要获取的 cookie 键值不存在，则返回 undefined</b>
     * @example 
     * define(function(require) {
     *     var Cookie = require('cookie');
     * 
     *     // 设置
     *     document.cookie = 'foo=1';
     *     document.cookie = 'bar=2';
     * 
     *     Cookie.get('foo');
     *     // returns '1'
     * 
     *     Cookie.get('bar', function(s) { return parseInt(s); } );
     *     // returns 2
     * });
     */
    Cookie.get = function (name, options) {
        validateCookieName(name);

        if (typeof options === 'function') {
            options = {
                converter: options
            };
        } else {
            options = options || {};
        }

        var cookies = parseCookieString(document.cookie, !options['raw']);
        return (options.converter || same)(cookies[name]);
    };


    /**
     * @desc 设置 cookie 值<br/><b>注：</b>已将 options 默认设置为 {expires: 3650, path: '/', domain: 'we.cn'}
     * @param {string} name
     * @param {*} value
     * @param {Object} options (Optional)
     * @return {string} "name = value"
     * @example 
     * define(function(require) {
     *     var Cookie = require('cookie');
     * 
     *     Cookie.set('foo', 3);
     *     // returns "foo=3; expires=Tue, 07 Mar 2023 06:43:20 GMT; domain=we.cn; path=/"
     * 
     *     Cookie.set('bar', 4, {
     *         domain: 'q.we.cn',
     *         path: '/',
     *         expires: 365
     *     });
     *     // returns "bar=4; expires=Sun, 09 Mar 2014 06:44:54 GMT; domain=q.we.cn; path=/"
     * });
     */
    Cookie.set = function (name, value, options) {
        validateCookieName(name);
        options = options || {};
        var expires = options['expires'] || 3650;
        //TODO 域名哈
        var domain = options['domain'] || 'uxuexi.com';
        var path = options['path'] || '/';

        if (!options['raw']) {
            value = encode(String(value));
        }

        var text = name + '=' + value;

        // expires
        var date = expires;
        if (typeof date === 'number') {
            date = new Date();
            date.setDate(date.getDate() + expires);
        }
        if (date instanceof Date) {
            text += '; expires=' + date.toUTCString();
        }

        // domain
        if (isNonEmptyString(domain)) {
            text += '; domain=' + domain;
        }

        // path
        if (isNonEmptyString(path)) {
            text += '; path=' + path;
        }

        // secure
        if (options['secure']) {
            text += '; secure';
        }

        document.cookie = text;
        return text;
    };


    /**
     * @desc 删除 cookie 值
     * @param {string} name
     * @param {Object} options (Optional)
     * @return {string} "name=; expires=Thu, 01 Jan 1970 00:00:00 GMT".
     * @example
     * define(function(require) {
     *     var Cookie = require('cookie');
     * 
     *     Cookie.remove('foo');
     *     // returns "foo=; expires=Thu, 01 Jan 1970 00:00:00 GMT; domain=we.cn; path=/"
     * 
     *     Cookie.remove('bar', {
     *         domain: 'we.cn',
     *         path: '/'
     *     });
     *     // returns "bar=; expires=Thu, 01 Jan 1970 00:00:00 GMT; domain=we.cn; path=/"
     * });
     */
    Cookie.remove = function (name, options) {
        options = options || {};
        options['expires'] = new Date(0);
        return this.set(name, '', options);
    };


    function parseCookieString(text, shouldDecode) {
        var cookies = {};

        if (isString(text) && text.length > 0) {

            var decodeValue = shouldDecode ? decode : same;
            var cookieParts = text.split(/;\s/g);
            var cookieName;
            var cookieValue;
            var cookieNameValue;

            for (var i = 0, len = cookieParts.length; i < len; i++) {

                // Check for normally-formatted cookie (name-value)
                cookieNameValue = cookieParts[i].match(/([^=]+)=/i);
                if (cookieNameValue instanceof Array) {
                    try {
                        cookieName = decode(cookieNameValue[1]);
                        cookieValue = decodeValue(cookieParts[i].substring(cookieNameValue[1].length + 1));
                    } catch (ex) {
                        // Intentionally ignore the cookie -
                        // the encoding is wrong
                    }
                } else {
                    // Means the cookie does not have an "=", so treat it as
                    // a boolean flag
                    cookieName = decode(cookieParts[i]);
                    cookieValue = '';
                }

                if (cookieName) {
                    cookies[cookieName] = cookieValue;
                }
            }

        }

        return cookies;
    }


    // Helpers

    function isString(o) {
        return typeof o === 'string';
    }

    function isNonEmptyString(s) {
        return isString(s) && s !== '';
    }

    function validateCookieName(name) {
        if (!isNonEmptyString(name)) {
            throw new TypeError('Cookie name must be a non-empty string');
        }
    }

    function same(s) {
        return s;
    }

});