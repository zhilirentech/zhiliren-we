define( function(require, exports, module) {
    /**
     * @name Player
     * @class 提供选择学校方法。
     * @requires jquery
     * @author 李博龙
     * @version v1.0.0  
     */
    var  tmpl = [ '<div class="alt_width04">', '<div class="con_bg">', '<div id="province22"></div>', '<div class="div_clear"></div>', "</div>", '<div class="con_bg" id="city_div" style="display:none">', '<div id="city22" style="display:none"></div>', '<div class="div_clear"></div>', "</div>", '<div class="con_bg" id="county_div" style="display:none">', '<div id="county22"></div>', '<div class="div_clear"></div>', "</div>", '<div class="con_list">', '<ul id="school22"></ul>', "</div>", "</div>", '<input type="hidden" name="curprov" id="curprov" value="">', '<input type="hidden" name="curcity" id="curcity" value="">', '<input type="hidden" name="curcounty" id="curcounty" value="">', '<input type="hidden" name="curschoolId" id="curschoolId" value="">', '<input type="hidden" name="curschool" id="curschool" value="">' ].join("");
    require("./style.css");
    var citys = require("./citys");
    $("input[name='school']").live("click", function() {
        $dialog.dialog({
            title: "选择学校",
            lock: true,
            content: tmpl,
            initialize: loadEvent,
            okValue: "确定",
            ok: function() {
                $("input[name='school']").val($("#curschool").val());
                $("#province").val($("#curprov").val());
                $("#city").val($("#curcity").val());
                $("#county").val($("#curcounty").val());
                if ($("#curcounty").val() == "") {
                    $("input[name='orgNo']").val($("#curcity").val());
                } else {
                    $("input[name='orgNo']").val($("#curcounty").val());
                }
                $("input[name='schoolId']").val($("#curschoolId").val());
            }
        });
        $(this).css("border-color", "#C6C6C6");
    });
    var loadEvent = function() {
        var pNode = cNode = rNode = sNode = null;
        //var city,
        $.each(citys, function(index, value) {
            $("<a href='javascript:void(0);' id='p_'" + value.orgNo + ">" + value.name + "</a>").appendTo($("#province22")).click(function() {
                $("#city_div").show();
                $(this).addClass("on_bg");
                if (pNode != null) pNode.removeClass("on_bg");
                pNode = $(this);
                $("#city22").show();
                $("#city22").html("");
                $("#county_div").hide();
                $("#cityname").text("");
                $("#school22").html("");
                $.each(value.organization, function(sindex, svalue) {
                    $("<a href='javascript:void(0);' id='c_" + svalue.orgNo + "'>" + svalue.name + "</a>").appendTo($("#city22")).click(function() {
                        $(this).addClass("on_bg");
                        if (cNode != null) cNode.removeClass("on_bg");
                        cNode = $(this);
                        if (svalue.organization.length == 0) {
                            $("#county_div").hide();
                            $("#cityname").text(svalue.name);
                            $("#school22").html("");
                            //取学校信息
                            $ajax.weAjaxP({
                                url: we.vr.www + "/app/school.json?orgNo=" + svalue.orgNo,
                                success: function(data) {
                                    $.each(data, function(hindex, hvalue) {
                                        $("<li><a href='javascript:void(0);'>" + hvalue + "</a></li>").appendTo($("#school22")).click(function() {
                                            $(this).find("a").addClass("on_bg");
                                            if (sNode != null) sNode.removeClass("on_bg");
                                            sNode = $(this).find("a");
                                            $("#curprov").val(value.orgNo);
                                            $("#curcity").val(svalue.orgNo);
                                            $("#curschoolId").val(hindex);
                                            $("#curschool").val(hvalue);
                                        }).hover(function() {
                                            $(this).addClass("on_bg");
                                        }, function() {
                                            $(this).removeClass("on_bg");
                                        });
                                    });
                                    if(we.utils.isEmpty(data)){
                                		$('#school22').html('暂无数据！！');
                                	}
                                }
                            });
                        } else {
                            $("#county_div").show();
                            $("#county22").html("");
                            $("#cityname").text("");
                            $("#school22").html("");
                            $.each(svalue.organization, function(cindex, cvalue) {
                                $("<a href='javascript:void(0);' id='r_" + cvalue.orgNo + "'>" + cvalue.name + "</a>").appendTo($("#county22")).click(function() {
                                    $(this).addClass("on_bg");
                                    if (rNode != null) rNode.removeClass("on_bg");
                                    rNode = $(this);
                                    //取学校信息
                                    $("#cityname").text(cvalue.name);
                                    $("#school22").html("");
                                    //取学校信息
                                    $ajax.weAjaxP({
                                        url: we.vr.www + "/app/school.json?orgNo=" + cvalue.orgNo,
                                        success: function(data) {
                                            $.each(data, function(hindex, hvalue) {
                                                $("<li><a href='javascript:void(0);'>" + hvalue + "</a></li>").appendTo($("#school22")).click(function() {
                                                    $(this).find("a").addClass("on_bg");
                                                    if (sNode != null) sNode.removeClass("on_bg");
                                                    sNode = $(this).find("a");
                                                    $("#curprov").val(value.orgNo);
                                                    $("#curcity").val(svalue.orgNo);
                                                    $("#curcounty").val(cvalue.orgNo);
                                                    $("#curschoolId").val(hindex);
                                                    $("#curschool").val(hvalue);
                                                }).hover(function() {
                                                    $(this).addClass("on_bg");
                                                }, function() {
                                                    $(this).removeClass("on_bg");
                                                });
                                            });
                                            if(we.utils.isEmpty(data)){
                                        		$('#school22').html("<li><a href='javascript:void(0);'>暂无数据！！</a></li>");
                                        	}
                                        }
                                    });
                                });
                            });
                        }
                    });
                });
            });
        });
    };
});

define("vkoweb/js/common/plugins/school/1.0.0/style-debug.css", [], function() {
    seajs.importStyle(".alt_width04{width:636px;padding:1px;clear:both;z-index:70;background:#fff}.alt_width04 .alt_tit{width:96%}.alt_width04 .con_bg{clear:both;border:#eaeaea 1px solid;background:#f5f5f5;padding:6px;line-height:24px;margin:6px;overflow:auto;_padding:6px 6px 30px}.alt_width04 .con_bg a{display:block;line-height:20px;padding:0 6px;color:#5192be;float:left;clear:none;margin:4px 0;min-width:24px;*float:none;*display:inline;*zoom:1}.alt_width04 .con_bg .on_bg{background:#51b1e2;color:#fff}.alt_width04 .con_bg a:hover{background:#51b1e2;color:#fff;text-decoration:none}.alt_width04 .but_r{clear:both;text-align:right;margin:0 4px 0 0}.alt_width04 .con_list{height:120px;clear:both;border:#eaeaea 1px solid;overflow-y:scroll;margin:6px;padding:6px}.alt_width04 .con_list ul li{width:33%;height:24px;overflow:hidden;float:left;clear:none;line-height:24px;color:#5192be;cursor:pointer;width:31%\\9}.alt_width04 .con_list ul li a{height:24px;float:left;color:#5192be;padding:0 10px 0 15px}.alt_width04 .con_list ul li a:hover{text-decoration:none}.alt_width04 .con_list .on_bg a,.alt_width04 .con_list a.on_bg{background:#51b1e2;color:#fff}");
});


