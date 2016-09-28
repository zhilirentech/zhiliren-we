define('icheck',['we/plugins/icheck/1.0.0/js/jquery.icheck.min.js','we/plugins/icheck/1.0.0/skins/all.css'],function (require, exports, module) {
	/**
     * @name icheck 
     * @class 美化checkbox和radio的UI，保持原来事件的可用，以及数据可以正常提交.
     * 
     * <h2> 初始化参数选项值如下</h2>
     * <ul>
     * 		<li>type：提供选择的有：flat|futurico|line|minimal|polaris|square</li>
     * 		<li>color：提供选择的有：red|green|blue|aero|grey|orange|yellow|pink|purple|minimal</li>
     * </ul>
     *      
     * <h2>提供八个回调事件:</h2>
     * <ul>
     *  	<li>is.Clicked	用户点击了自定义的输入框或与其相关联的label</li>
	     *	<li>is.Changed	输入框的 checked 或 disabled 状态改变了</li>
	     *	<li>is.Checked	输入框的状态变为 checked</li>
	     *	<li>is.Unchecked	checked 状态被移除</li>
	     *	<li>is.Disabled	输入框状态变为 disabled</li>
	     *	<li>is.Enabled	disabled 状态被移除</li>
	     *	<li>is.Created	输入框被应用了iCheck样式</li>
	     *	<li>is.Destroyed	iCheck样式被移除</li>
     *  </ul>
     *  <pre>
     *	例子：
     *   $("input").live("is.Changed",function(even,index){
     *		seajs.log(1);
     *	 });
     *  </pre>
     *
     *	<h2>提供六个操作ui的方法：</h2>
     *  <ul>
     *  		<li>check  将输入框的状态设置为checked</li>
     *  		<li>uncheck  移除 checked 状态</li>
     *  		<li>disable  将输入框的状态设置为 disabled</li>
     *  		<li>enable   移除 disabled 状态</li>
     *  		<li>update  apply input changes, which were done outside the plugin</li>
     *  		<li>destroy 移除iCheck样式	</li>
     *  </ul>
     *  <pre>
     *  例子：
     *   $('input').iCheck('check'); 
     *  </pre>
     *  <h3>
     *  注意事项 ：
     *  	如果有用到这个插件来美化UI，在所有操作时，请通过提供的六个方法进行操作，不要使用原始的方法鸟。
     *  </h3>
     *  
     * @requires jquery
     * @author 庄君祥
     * @version v1.0.0  
     */
	require('./css/position.css');
	require('./skins/all.css');
	require('./js/jquery.icheck.min.js');
	/**
     * @name icheck#init
     * @function   
     * @desc 初始化
     * @param {Object} options
     * @example
     * define(function(require) {
     *     $(function() {
     *         require.async('icheck',function($icheck){
	 *  			$icheck.init({
	 *    				items:$item   			//【必填】 要美化的元素
	 *  				type: 'square',  		//【选填】 美化的类型
	 *					color: 'blue',			//【选填】 美化时使用的颜色
	 *	    			increaseArea: '20%',    //【选填】 覆盖周围的大小百分比
 	 *   			});
	 *    		});
     *     });
     * });
     */
	var init = function(options){
		var op = $.extend({
			type: 'square',
			color: 'blue',
		    increaseArea: '20%'
		},options);
		/**
		 * 打印日志，高度时使用，平时可以关掉。。。。关掉就是注释掉的意思，嗯哼
		 */
		var log = function(obj,event){
			seajs.log("input's name ["+obj.attr('name')+"]:"+event.type+event.namespace);
		}
		var checkboxClass = 'icheckbox'+"_"+op.type+"-"+op.color;
		var radioClass = 'iradio'+"_"+op.type+"-"+op.color;
		var $items = $(op.items);
		if(we.utils.isEmpty($items)){
			seajs.log('没有对应的元素，所以不能icheck');
			return;
		}
		$items.iCheck({
			checkboxClass: checkboxClass,
			radioClass: radioClass,
			increaseArea: op.increaseArea
		});
		
		$items.each(function(i,item){
			var $item = $(item);
			if(!we.utils.isEmpty($item.attr('checked'))){
				$item.iCheck('check');
			}else{
				$item.iCheck('uncheck');
			}
			$item.live('is.Checked',function(){
				$(this).attr("checked",true);
			}).live('is.Unchecked',function(){
				$(this).attr("checked",false);
			})
		});
		//$($this).attr('checked') 明明已经是undefined了，可是标签上的属性没有改变。。在构建ui时，永远用的是标签上的值？？？？？？？？？？ TODO
	}
	return {
		init:init
	}
});