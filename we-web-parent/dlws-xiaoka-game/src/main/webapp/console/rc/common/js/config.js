seajs.config({
    alias:{
        /* we */
        'we':'pwe/config/we',
        'iinit':'pwe/init/init',
        'utils':'pwe/utils/utils',
        'login':'pwe/action/login/1.0.0/login',
        'ajax':'pwe/plugins/ajax/1.0.0/ajax',
        'dialog':'pwe/plugins/dialog/1.0.0/dialog',
        'fixpng':'pwe/plugins/fixpng/1.0.0/fixpng',
        'navtab':'pwe/plugins/navtab/1.0.0/navtab',
        'page':'pwe/plugins/page/1.0.0/page',
        'pagination':'pwe/plugins/pagination/1.0.0/pagination',
        'placeholder':'pwe/plugins/placeholder/1.0.0/placeholder',
        'scrollbar':'pwe/plugins/scrollbar/1.0.0/scrollbar',
        'select':'pwe/plugins/select/1.0.0/select',
        'slides':'pwe/plugins/slides/1.0.0/slides',
        'stock':'pwe/plugins/stock/1.0.0/stock',
        'tab':'pwe/plugins/tab/1.0.0/tab',
        'table':'pwe/plugins/table/1.0.0/table',
        'theme':'pwe/plugins/theme/1.0.0/theme',
        'tree':'pwe/plugins/tree/1.0.0/tree',
        'iconfig':'pwe/config/config',
        'chart':'we/plugins/chart/1.0.0/chart',
    },
    paths: {
    	 manage: weFileLoader.root + '/console/rc/manage',
    	 pwe: weFileLoader.root + '/console/rc/common/js/we'
    },
    debug:true,
    preload:[ 'iinit'],
    charset:'utf-8'
});
seajs.use('iinit');