define(function(require, exports, module){
	/**
     * @name Stock
     * @class 提供大数据图表展示。
     * @author 李博龙
     * @version v1.0.0  
     */
	var $ = require('jquery');
	require('./highstock');
	var initStock = function(options){
		var op = $.extend({
				table: '',
				type: 'line',
				title: '图表展示',
				ytitle: '',
				container: ''
			},options),
			$table = (typeof op.table=='string'?$(op.table):op.table),
			$container = (typeof op.container=='string'?$(op.container):op.container);
		
		if(we.utils.isEmpty($table) || we.utils.isEmpty($container)){
			seajs.log('图表数据有误，请检查！');
			return;
		}
		
		var seriesOptions = [],
			yAxisOptions = [],
			seriesCounter = 0,
			names = [],
			colors = Highstock.getOptions().colors;
		
		$('thead th',$table).each(function(index){
			if(index!=0){
				names.push($(this).text());
			}
		});
		$.each(names, function(i, name){
			var data = [];
			$('tbody tr',$table).each(function(index){
				var sdata = [];
				var $tr = $(this);
				var t = new Date($('th',$tr).text()+' 23:59:59').valueOf()
				sdata.push(t)
				$('td',$tr).each(function(index){
					if(index==i){
						sdata.push(parseInt($(this).text()))
					}
				});
				data.push(sdata);
			});
			seriesOptions[i] = {
				name: name,
				data: data
			}
			seriesCounter++;

			if (seriesCounter == names.length) {
				window.dragon = seriesOptions;
				$container.highstock('StockChart', {
				    /*chart: {
				    },*/
				    title : {
						text : op.title
					},
				    rangeSelector: {
				        selected: 1
				    },
				    yAxis: [{
				        title: {
				            text: op.ytitle
				        }
				    }],
				    /*yAxis: {
				    	labels: {
				    		formatter: function() {
				    			return (this.value > 0 ? '+' : '') + this.value + '%';
				    		}
				    	},
				    	plotLines: [{
				    		value: 0,
				    		width: 2,
				    		color: 'silver'
				    	}]
				    },*/
				    
				   /* plotOptions: {
				    	series: {
				    		compare: 'percent'
				    	}
				    },*/
				    
				    tooltip: {
				    	//pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.change}%)<br/>',
				    	pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
				    	valueDecimals: 2
				    },
				    
				    series: seriesOptions
				});
			}
		});
	}
	
	return{
		init: initStock
	}
	
});