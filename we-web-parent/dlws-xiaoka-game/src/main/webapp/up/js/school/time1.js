	
	comman(new Date());
	first();
	$(".sel_cal button").on("click",function(){
    first();
    $(".sel_cal").hide();
    sel_sign = 0;
	})
function is_leap(year) { 
   return (year%100==0?res=(year%400==0?1:0):res=(year%4==0?1:0));
} //是否为闰年
function first(){
    var f_date = new Date().getFullYear();
    var f_month = new Date().getMonth()+1;
    var f_day = new Date().getDate();
    var f_week = new Date().getDay();
    var week = parseInt(f_week);
    switch(week) 
    { 
        case 0: sw="周日"; break; 
        case 1: sw="周一";break; 
        case 2: sw="周二"; break; 
        case 3: sw="周三"; break; 
        case 4: sw="周四"; break; 
        case 5: sw="周五"; break; 
        case 6: sw="周六"; break; 
    }
    var f_time = String(f_date)+"/"+f_month+"/"+f_day;
    $(".date span").attr("id",f_time); 
    var c_val=f_month+'月'+f_day+'日'+sw;
    $(".sel_date span").html(c_val);
}
function comman(year){
    $(".sel_cal table").remove();
    var nstr=new Date(year); //传入日期
    var ynew=nstr.getFullYear(); //传入日期年份
    var mnew=nstr.getMonth(); //传入日期月份
    var dnew=nstr.getDate(); //传入日期日期
    var ynow=new Date().getFullYear(); //日期年份
    var mnow=new Date().getMonth(); //传入日期月份
    var dnow=new Date().getDate(); //传入日期日期
    var n1str=new Date(ynew,mnew,1); //当月第一天Date
    var firstday=n1str.getDay(); //当月第一天星期几
    var m_days=new Array(31,28+is_leap(ynew),31,30,31,30,31,31,30,31,30,31); //各月份的总天数
    var tr_str=Math.ceil((m_days[mnew] + firstday)/7); //表格所需要行数
    var winHeight = $(window).height(); 
    var calHeight = winHeight - 41 - 30 - 60;
    var calHeight2 = winHeight - 41 -50;
    var tr_str_height= calHeight / tr_str ;
    $(".year").html(ynew+"年");
    $(".month").html(mnew+1+"月");
    $(".month").show();
    $(".sel_year em").html(ynew);
    $(".sel_month em").html(mnew+1);
    //打印表格第一行（有星期标志）
    var cn ="<table border='0' cellspacing='0' cellpadding='0' align='center'><tr><th align='center'>日</th><th align='center'>一</th><th align='center'>二</th><th align='center'>三</th><th align='center'>四</th><th align='center'>五</th><th align='center' >六</th></tr><tr>";
    for(i=0;i<tr_str;i++) { //表格的行
        for(k=0;k<7;k++) { //表格每行的单元格
        //console.log(k);
            idx=i*7+k; //单元格自然序列号
            date_str=idx-firstday+1; //计算日期
            (date_str<=0 || date_str>m_days[mnew]) ? date_str="&nbsp;" : date_str=idx-firstday+1; //过滤无效日期（小于等于零的、大于月总天数的）
          //打印日期：今天底色为红
            var d_mnow = mnew+1;
            if(d_mnow < 10){
                d_mnow = '0'+String(d_mnow);
            }
            if(date_str < 10){
                n_date_str = '0'+ String(date_str);
            }else{
                n_date_str = String(date_str);
            }
            var dn = String(ynew)+d_mnow+n_date_str;
            var dtime = String(ynew)+"/"+d_mnow+"/"+n_date_str;
            var week = parseInt(k);
            switch(week) 
            { 
                case 0: sw="周日"; break; 
                case 1: sw="周一";break; 
                case 2: sw="周二"; break; 
                case 3: sw="周三"; break; 
                case 4: sw="周四"; break; 
                case 5: sw="周五"; break; 
                case 6: sw="周六"; break; 
            }
            sel_month=parseInt(mnew)+1;
            var s_val=ynew+'年'+sel_month+'月';
            $(".showdate").html(s_val);
            $(".showdate").attr("year",ynew);
            $(".showdate").attr("month",sel_month);
            if(date_str == dnow  && ynew==ynow && mnew==mnow){
                cn =cn + "<td align='center' onclick='current("+sel_month+","+date_str+","+week+","+ynew+")'><span class ='today'>" + date_str + "</span></td>";
                
            }else{
                cn =cn + "<td align='center' onclick='current("+sel_month+","+date_str+","+week+","+ynew+")'><span>" + date_str + "</span></td>";
            }
        }
       var cn = cn + "</tr>";
    }
    var cn = cn + "</table>";
    $(".sel_cal").append(cn);
}
function current(sm,sd,sw,sy){
    switch(sw) 
    { 
        case 0: sw="周日"; break; 
        case 1: sw="周一";break; 
        case 2: sw="周二"; break; 
        case 3: sw="周三"; break; 
        case 4: sw="周四"; break; 
        case 5: sw="周五"; break; 
        case 6: sw="周六"; break; 
    }
    var c_val=sm+'月'+sd+'日'+sw;
    $(".sel_date span").html(c_val);
    var s_val=sy+'年'+sm;
    if(sm < 10){
        sm = '0'+String(sm);
    }
    if(sd < 10){
        sd = '0'+ String(sd);
    }else{
        sd = String(sd);
    }
    var dtime = String(sy)+"/"+sm+"/"+sd;
    $(".sel_date span").attr("id",dtime);
//  $(".sel_cal").hide();
    sel_sign = 0;
}

$(".prev_month").on("click",function(){
	alert(1);
   var nyear = parseInt($(".showdate").attr("year"));
   var nmonth = parseInt($(".showdate").attr("month"));
   if(nmonth == 1){
        nyear = nyear -1;
        nmonth = 12;
   }else{
        nmonth = nmonth -1;
   }
   comman(nyear+"/"+nmonth+"/"+"1");
})

$(".next_month").on("click",function(){
	alert(2);
   var nyear = parseInt($(".showdate").attr("year"));
   var nmonth = parseInt($(".showdate").attr("month"));
   if(nmonth == 12){
        nmonth = 1;
        nyear = nyear +1;
   }else{
        nmonth = nmonth + 1;
   }
   comman(nyear+"/"+nmonth+"/"+"1");
})

