seajs.config({
    alias:{
        /* seajs */
        'seajs-combo':'seajs/seajs-combo/1.0.1/seajs-combo',
        'seajs-log':'seajs/seajs-log/1.0.1/seajs-log',
        'seajs-style':'seajs/seajs-style/1.0.2/seajs-style',
        'seajs-debug':'seajs/seajs-debug/1.1.1/seajs-debug',
        /* gallery */
        'json':'gallery/json/1.0.3/json',
        'editor':'gallery/ckeditor/4.4.3/editor',
        'swfobject':'gallery/swfobject/2.3.0/swfobject',
        /* jquery */
        'jquery':'jquery/jquery/1.8.3/jquery',
        'easing':'jquery/easing/1.3.0/easing',
        'jqueryui':'jquery/jqueryui/1.11.2/jqueryui',
        'livequery':'jquery/livequery/1.3.6/livequery',
        'metadata':'jquery/metadata/1.0.0/metadata',
        /* we--project */
        'config':'we/config/config',
        'utils':'we/utils/utils',
        /* we-init */
        'init':'we/init/init',
        'uuid':'we/init/uuid',
        /* we-plugins */
        'ajax':'we/plugins/ajax/1.0.0/ajax',
        'page':'we/plugins/page/1.0.0/page',
        'validate':'we/plugins/validate/1.0.0/validate',
        'events':'we/plugins/events/1.0.0/events',
        'respond': 'we/plugins/respond/respond',
        'parsejson':'we/plugins/parsejson/1.0.0/parsejson',
        'ztree':'we/plugins/ztree/1.0.0/ztree',
        'icheck':'we/plugins/icheck/1.0.0/icheck',
        'dialog':'we/plugins/dialog/1.0.0/dialog',
        'udialog':'we/plugins/dialog/2.0.0/dialog',
        'wedialog':'we/plugins/artdialog/1.0.0/dialog',
        'echarts':'we/plugins/echarts/2.0.0/echarts',
        'chart':'we/plugins/chart/1.0.0/chart',
        'table':'we/plugins/table/1.0.0/table',
        'scrollbar':'we/plugins/mcustomscrollbar/3.1.0/mcustomscrollbar',
        'pdfobject':'we/plugins/pdfobject/1.2.0/pdfobject',
        'select':'we/plugins/select/1.0.0/select',
        'cookie':'we/plugins/cookie/1.0.0/cookie',
        'superslide':'we/plugins/superslide/2.1.1/superslide',
        'ckplayer':'we/plugins/ckplayer/ckplayer',
        'tab':'we/plugins/tab/tab',
        'loading':'we/plugins/loading/loading',
        'datepicker':'we/plugins/datepicker/1.0.0/datepicker',
        'lazyload':'we/plugins/lazyload/lazyload',
        'mcustomscrollbar':'we/plugins/mcustomscrollbar/3.1.0/mcustomscrollbar',
        'uploadify':'we/plugins/uploadify/1.0.0/uploadify',
        'json2':'we/plugins/json/1.0.0/json2',
        'flashchart':'we/plugins/flashchart/1.0.0/flashchart',
        'fancybox':'we/plugins/fancybox/1.0.0/fancybox',
        'bootstrap':'we/plugins/bootstrap/2.3.2/bootstrap',
        'jquery-ui':'we/plugins/jqueryui/1.0.0/js/jquery-ui',
        'angular':'we/plugins/angularjs/1.3.0/angular',
        'wecalendar':'we/plugins/calendar/1.0.0/calendar',
        'ueditor':'we/plugins/ueditor/1.2.2/ueditor',
        //action 表示与业务相关
        'school':'we/action/school/1.0.0/school',
        //播放器
        'polyvplayer':'http://static.polyv.net/file/polyvplayer_v2.0.min.js',
        /*高德地图*/
        "amap": "http://webapi.amap.com/maps?v=1.3&key=09820078d0fb862b19121e84d54327d7&plugin=AMap.DistrictSearch&callback=init",
        "amapinit": "we/plugins/amap/amapinit",
        "shouyemap": "we/plugins/amap/shouyemap"

    },
    base:weFileLoader.root + '/console/common/js/',
    map:[ [ /(^(?!.*(config|jquery|seajs-log|seajs-style|seajs-combo|seajs-debug)\.(css|js)).*)$/i, '$1' + weFileLoader.version ] ],
   /* preload:[ 'seajs-log', 'seajs-style', 'init', weFileLoader.isonline ? 'seajs-combo' : ''],*/
    preload:[ 'seajs-log', 'seajs-style', 'init', weFileLoader.isonline ? '' : ''],
    debug:weFileLoader.isonline ? false : true,
    charset:'utf-8'
});
seajs.use('init');
