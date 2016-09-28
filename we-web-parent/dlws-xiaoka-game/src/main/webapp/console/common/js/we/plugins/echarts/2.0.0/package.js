define('we/plugins/package',function (require, exports, module) {
    var op;
    var init = function(options){
        /*myChart.showLoading({text:'正在努力读取数据。。。'});*/
        op = {};
        // 基于准备好的dom，初始化echarts图表
        if($('.uPie').size()>0){
            $('.uPie').attr('id','pie');
            var $pie = $("#pie");
            var pie = document.getElementById('pie');
            $pie.css({
            	'width':$pie.attr('width')+'px',
            	'height':$pie.attr('height')+'px'
            });
            pieChart = echarts.init(pie);
            
            var pieOption = {
                    title:{
                        text:$pie.attr('title'),
                        subtext:$pie.attr('subtit'),
                        x:$pie.attr('x')
                    },
                    tooltip : {
                        trigger: 'item' //'item' | 'axis'
                    },
                    toolbox:{
                        show:true,
                        y:'top',
                        feature : {
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : false,
                    legend:{
                        orient:'vertical',
                        data:[],
                        x:'left'
                    },
                    series:[
                        {
                            type:$pie.attr('id'),
                            center:['50%','60%'],
                            radius:'50%',
                            data:[]
                        }
                        
                    ]
            }
        };
        if($('.uBar').size()>0){
            $('.uBar').attr('id','bar');
            var $bar = $("#bar");
            var bar = document.getElementById('bar');
            $bar.css({
            	'width':$bar.attr('width')+'px',
            	'height':$bar.attr('height')+'px'
            });
            barChart = echarts.init(bar);
            
            var barOption = {
                    title:{
                        text:$bar.attr('title'),
                        subtext:$bar.attr('subtit'),
                        x:$bar.attr('x')
                    },
                    toolbox:{
                        show:true,
                        y:'top',
                        feature : {
                            saveAsImage : {show: true}
                        }
                    },
                    legend:{
                        show:false,
                        orient:'vertical', //'horizontal' | 'vertical'
                        data:[],
                        x:$bar.attr('x')
                    },
                    tooltip : {
                        trigger: 'item'
                    },
                    grid:{
                        backgroundColor:'#FFF',
                        borderWidth:0,
                        borderColor:'#f1f1f3',
                        z:1
                    },
                    areaStyle:{
                        color:'#FF0',
                        type:'default'
                    },
                    xAxis:[
                        {
                            data:[]
                        }
                    ],
                    yAxis:[
                        {
                            type:'value',
                            position: 'right'
                        }
                    ],
                    series:[
                        {
                            type:$bar.attr('id'),
                            data:[],
                            itemStyle:{
                                normal:{
                                    color:'#00bffe',
                                    barBorderRadius:[5, 5, 0, 0],
                                    label:{
                                        show: true,
                                        position:'top',
                                        textStyle:{
                                            color:'#00bffe'
                                        }
                                    } 
                                }
                            }
                        }
                    ]   
                
            }
        };
        
        op.barOption=barOption;
        op.pieOption=pieOption;
        op.pieChart=pieChart;
        op.barChart=barChart;

        return op;
    }
    return{
        init:init
    }
    
    
});