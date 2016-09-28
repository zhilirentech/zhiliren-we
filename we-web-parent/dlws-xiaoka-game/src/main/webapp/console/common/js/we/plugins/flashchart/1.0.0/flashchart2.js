define(function (require, exports, module) {

    /**
     * @name FlashChart
     * @class 提供 initVkoPlayer, playVideo, getVkoSWF 方法。
     * @requires jquery, swfobject, json
     * @author 李博龙
     * @version v1.0.0  
     */

    var $ = require('jquery'),
        swfobject = require('swfobject'),
        contentTmpl = ['<div id="{{ contentId }}">',
            '<h1>亲，正在加载图表控件，请稍等！<br><span>如未加载成功，你可能需要较新版本的 Adobe Flash Player 哦！</span></h1>',
            '<p><a href="http://www.adobe.com/go/getflashplayer" target="_blank"><img src="'+we.vr.static+'/v5/images/common/pic/get_flash_player.gif" alt="Get Adobe Flash player" title="去 Adobe 官网获取 Flash 播放器" /></a></p>',
            '</div>'
        ].join('');
    require('json');
    
    var defaultInitParams = {
        url: we.vr.static+'/common/js/vko/plugins/flashchart/1.0.0/flash/open-flash-chart.swf',
        width: '100%',
        height: '100%',
        version: '8.0.0'
    }
    var defaultVars = {
    	//variables:'true&title=李博龙,{font-size: 20;}&y_legendx=Open Flash Chart,12,0x736AFF&y_label_size=15&y_ticks=5,10,4&bar=50,0xff0000,昨天,10&values=9,6,7,9,5,7,6,9,9&x_labels=January,,March,,May,,June,,August&x_axis_steps=2&y_max=9'
		//variables:'true&title=Enter+Shikari,{font-size: 20px; color: #000080}&x_axis_steps=1&y_ticks=5,10,4&line=3,#87421F&values=487037,1772914,1392943,1975542,69344,354675,605845,958523,1397209&x_labels=0,1,2,3,4,5,6,7,8&y_min=0&y_max=2000000'
    	//variables: "&bg_colour=#ffffff&x_axis_colour=#cfcfcf&y_axis_colour=#cfcfcf&x_grid_colour=#deeef2&y_grid_colour=#deeef2&x_label_style=12,#333333,0,1&x_axis_steps=2&y_ticks=5,10,2&x_labels=一月,二月,三月,四月,五月,六月,七月,八月,九月&y_min=0&y_max=10&bar=80,#999999,全网平均,12&values=2,3,2,3,5,3,4,5,2&bar_2=80,#f18b38,我的,12&values_2=7,5,6,7,5,6,9,9,5&links=javascript:alert('Kittens:+Bar+0'),javascript:alert('Kittens:+Bar+1'),javascript:alert('Kittens:+Bar+2'),javascript:alert('Kittens:+Bar+3'),javascript:alert('Kittens:+Bar+4'),javascript:alert('Kittens:+Bar+5'),javascript:alert('Kittens:+Bar+6'),javascript:alert('Kittens:+Bar+7'),javascript:alert('Kittens:+Bar+8')&links_2=javascript:alert('Dragon:+Bar+0'),javascript:alert('Dragon:+Bar+1'),javascript:alert('Dragon:+Bar+2'),javascript:alert('Dragon:+Bar+3'),javascript:alert('Dragon:+Bar+4'),javascript:alert('Dragon:+Bar+5'),javascript:alert('Dragon:+Bar+6'),javascript:alert('Dragon:+Bar+7'),javascript:alert('Dragon:+Bar+8')"
    	//variables:"true&title=Bar+Chart,{font-size: 26px;}&x_label_style=10,#9933CC,0,2&x_axis_steps=2&y_legend=Open Flash Chart,12,0x736AFF&y_ticks=5,10,2&x_labels=January,February,March,April,May,June,July,August,September&y_min=0&y_max=10&bar=50,#0066CC,Kittens,10&values=3,9,6,4,4,3,4,3,5&links=javascript:alert('Kittens:+Bar+0'),javascript:alert('Kittens:+Bar+1'),javascript:alert('Kittens:+Bar+2'),javascript:alert('Kittens:+Bar+3'),javascript:alert('Kittens:+Bar+4'),javascript:alert('Kittens:+Bar+5'),javascript:alert('Kittens:+Bar+6'),javascript:alert('Kittens:+Bar+7'),javascript:alert('Kittens:+Bar+8')"
    
    
    	//variables: '&bg_colour=#ffffff&x_axis_steps=1&y_ticks=5,10,5&line=3,#87421F&y_min=0&y_max=20&pie=80,#fefefe,{font-size: 12px; color: #404040;&values=20,10,10,10,10,10,10,10,10&pie_labels=语文,数学,英语,物理,化学,生物,历史,地理,政治&colours=#df385f,#edd277,#fdf4a7,#ed9877,#90c3d2,#95f7b3,#63e2e6,#34a9cc,#eaadaf&links=&tool_tip=%23val%23%25&'
    }
    var defaultParams = {
        menu: 'false',
        quality: 'high',
        wmode: 'opaque',
        bgcolor: '#ffffff',
        allowFullScreen: 'true',
        allowScriptAccess: 'always'
    }
    var defaultAttrs = {
        id: 'vkoflashchart',
        name: 'vkoflashchart'
    }

    /**
     * @name FlashChart#initChart
     * @function   
     * @desc 图表初始化方法。
     * @param {Object} options
     */
    var initChart = function (options) {
    	var op = $.extend({
	            container: '',
	            initParams: null,
	            vars: {
	            	bg_colour: '#ffffff',			//设置背景颜色
	            	x_axis_colour: '#cfcfcf',		//设置X轴颜色
	            	y_axis_colour: '#cfcfcf',		//设置Y轴颜色
	            	x_grid_colour: '#deeef2',		//设置X轴线条颜色
	            	y_grid_colour: '#deeef2',		//设置Y轴线条颜色
	            	x_label_style: '8,#333333,2,1',	//设置X轴样式（字体大小，字体颜色，角度[0:0度1:90度2:45度]，设置X轴线条间隔，X轴间隔线条颜色）
	            	y_label_style: '',				//设置X轴样式（字体大小，字体颜色）
	            	x_axis_steps: '2',				//设置X轴线条间隔
	            	x_ticks: '',					//控制X轴坐标标记显示（X轴坐标标记长度）
	            	y_ticks: '5,10,2'				//控制Y轴坐标标记显示（Y轴坐标标记最小长度，Y轴坐标标记最大长度，Y轴坐标标记最大长度）
	            },
	            params: null,
	            attrs: null
	        }, options),
	        myVariables = '&';
	    
        var $container = typeof op.container=='string'?$(op.container):op.container;
        if($container.size()<1)return;
        var contentId = 'vkoflashchartcontent'+we.utils.checkId('vkoflashchartcontent'),
        	chartId = 'vkoflashchart'+we.utils.checkId('vkoflashchart');
        $container.append(contentTmpl.replace('{{ contentId }}',contentId));
        $.each(op.vars, function(i,n){
	    	myVariables = myVariables + i + '=' + n;
	    });
        op.initParams = $.extend({},defaultInitParams, op.initParams);
        op.vars = $.extend({},defaultVars, {variables:myVariables});
        op.params = $.extend({},defaultParams, op.params);
        op.attrs = $.extend({
        	id: chartId,
        	name: chartId
        },defaultAttrs, op.attrs);
        
        
        swfobject.embedSWF(op.initParams.url, contentId, op.initParams.width, op.initParams.height, op.initParams.version, swfobject.installUrl, op.vars, op.params, op.attrs);
    }
    
    /**
     * @name FlashChart#tableChart
     * @function   
     * @desc 格式化表格为图表。
     * @param {Object} $table
     * @example
     * define(function(require) {
     *     $(function() {
     *         var  $chart = require('flashchart');
     *         $chart.tablechart($('table'));
     *     });
     * });
     */
    var tableChart = function($tables){
    	if(we.utils.isEmpty($tables) || $tables.size()<1)return;
    	
    	$tables.each(function(){
    		var $this = $(this),chartType = $this.attr('chartType');
    		if(!we.utils.isEmpty($this.data('flashchart')))return;
    		if(we.utils.isEmpty(chartType)){
    			$this.attr('chartType','bar');
    		}
    		$this.data('flashchart',{draw:'start'});
    		if(chartType == 'bar'){
    			drawBarChart($this);
    		}else if(chartType == 'pie'){
    			drawPieChart($this);
    		}
    		$this.data('flashchart',{draw:'complete'});
    	});
    }
    
    //柱状图表
    var drawBarChart = function($table){
    	//var myVariables = ["&bg_colour=#ffffff&x_axis_colour=#cfcfcf&y_axis_colour=#cfcfcf&x_grid_colour=#deeef2&y_grid_colour=#deeef2&x_label_style=8,#333333,2,1&x_axis_steps=2&y_ticks=5,10,2"]
		/* 数据处理 */
		var titleArr = [];
		$('thead th',$table).each(function(i){
			if(i!==0)titleArr.push({txt:$(this).text(),color:$(this).attr('chartColor')});
		});
		
		var x_labels = [];
		$('tbody tr',$table).each(function(){
			x_labels.push($(this).children('td:first').text());
		});
		
		var y_datas = new Array(titleArr.length);
		$.each(y_datas,function(i,n){
			y_datas[i] = [];
		});
		$('tbody tr',$table).each(function(index){
			$(this).children('td').each(function(i){
				if(i!=0)y_datas[i-1].push(parseInt($(this).text()));
			});
		});
		
		var barArr = [], dataArr = [];
		$.each(y_datas, function(i,n){
			barArr.push((i==0?('bar'):('bar_'+(i+1)))+'=80,'+titleArr[i].color+','+titleArr[i].txt+',12&'+(i==0?('values='):('values_'+(i+1)+'='))+n.join(','));
			dataArr = dataArr.concat(n);
		});
		
		myVariables.push('x_labels='+x_labels.join(','));	//设置X轴坐标显示
		myVariables.push('y_max='+dataArr.max());			//设置Y轴坐标最大值
		//myVariables.push('y_min='+dataArr.min());			//设置Y轴坐标最小值
		myVariables.push('y_min=0');			//设置Y轴坐标最小值
		myVariables.push(barArr.join('&'));					//设置柱状图
		
		//$('table.flashchart').hide('slow');
		var container = $table.attr('container');
		if(we.utils.isEmpty(container)){
			$table.wrap('<div class="vkoflashchart"></div>')
		}
		initChart({
			container: we.utils.isEmpty(container)?$table.parent():container,
			vars: {
				variables: myVariables.join('&')
			}
		});
    }
    
    //饼状图表
    var drawPieChart = function($table){
    	var myVariables = ["&bg_colour=#ffffff&x_axis_steps=1&y_ticks=5,10,5&line=3,#87421F&pie=80,#999999,{font-size: 12px; color: #404040;}&links=&tool_tip=%23val%23%25&"],
    		coloursArr=['#df385f','#edd277','#fdf4a7','#ed9877','#90c3d2','#95f7b3','#63e2e6','#34a9cc','#eaadaf'];
		
    	/* 数据处理 */
		var pie_labels = [],y_datas = [],colors = [];
		$('tbody tr',$table).each(function(index){
			$(this).children('td').each(function(i){
				var val = $(this).text();
				if(i==0){
					pie_labels.push(val);
				}else if(i==1 && val!='0'){
					y_datas.push(parseInt(val));
					colors.push(coloursArr[index]);
				}else{
					pie_labels.pop();
				}
			});
		});
		
		myVariables.push('pie_labels='+pie_labels.join(','));	//设置X轴坐标显示
		myVariables.push('values='+y_datas.join(','));	        //设置X轴坐标显示
		myVariables.push('y_max='+y_datas.max());			    //设置Y轴坐标最大值
		myVariables.push('y_min='+y_datas.min());			    //设置Y轴坐标最小值
		myVariables.push('colours='+colors.join(','));	        //设置X轴坐标显示
		
		//$('table.flashchart').hide('slow');
		var container = $table.attr('container');
		if(we.utils.isEmpty(container)){
			$table.wrap('<div class="vkoflashchart"></div>')
		}
		initChart({
			container: we.utils.isEmpty(container)?$table.parent():container,
			vars: {
				variables: myVariables.join('&')
			}
		});
    }
    
    
  
	
	
	
	
    
   /*   
    var data_1 = {
    		  "elements": [
    		    {
    		      "type": "bar",
    		      "values": [
    		        9,
    		        8,
    		        7,
    		        6,
    		        5,
    		        4,
    		        3,
    		        2,
    		        1
    		      ]
    		    }
    		  ],
    		  "title": {
    		    "text": "Thu Dec 19 2013"
    		  }
    		};
    		var data_2 = {
    		  "elements": [
    		    {
    		      "type": "bar",
    		      "values": [
    		        1,
    		        7,
    		        8,
    		        8,
    		        2,
    		        6,
    		        2,
    		        5,
    		        2
    		      ]
    		    }
    		  ],
    		  "title": {
    		    "text": "Chart 2 :-)"
    		  }
    		};
    window.ofc_ready = function(){
    	seajs.log('FlashChart:ofc_ready');
    }
    window.open_flash_chart_data = function(){
    	seajs.log('FlashChart:请求数据...');
    	return JSON.stringify(data_1);
    }
    window.load1 = function()
    {
      tmp = getVkoSWF("vkoflashchart");
      //x = tmp.reload('true&title=Profit,{font-size: 25px; color: #FF8040}&x_label_style=10,0x9933CC,0,6&x_axis_steps=1&y_ticks=5,10,5&line_hollow=3,#9933CC,Beer money,18,6&values=8,4,8,8,2,5,4,9,2,6,3,2,6,3,3,3,4,3,3,7,3,7,8,6&x_labels=January+07,February+07,March+07,April+07,May+07,June+07,July+07,August+07,September+07,October+07,November+07,December+07,January+08,February+08,March+08,April+08,May+08,June+08,July+08,August+08,September+08,October+08,November+08,December+08&y_min=0&y_max=20');
      x = tmp.reload('true&title=Enter+Shikari,{font-size: 20px; color: #000080}&x_axis_steps=1&y_ticks=5,10,4&line=3,#87421F&values=487037,1772914,1392943,1975542,69344,354675,605845,958523,1397209&x_labels=0,1,2,3,4,5,6,7,8&y_min=0&y_max=2000000');
    }

    window.load2 = function()
    {
      seajs.log("loading data_2");
      tmp = getVkoSWF("vkoflashchart");
      x = tmp.load(JSON.stringify(data_2));
    }*/

    return {
        init: initChart,
        tablechart: tableChart
    }
});