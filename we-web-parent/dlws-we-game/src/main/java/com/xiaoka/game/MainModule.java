package com.xiaoka.game;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.ChainBy;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.annotation.Views;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import com.xiaoka.game.common.actionfilter.AuthFilter;
import com.xiaoka.game.common.actionfilter.LoginFilter;
import com.xiaoka.game.common.annotation.NoFilter;
import com.uxuexi.core.web.view.WeViewMaker;

@IocBy(type = ComboIocProvider.class, args = { "*org.nutz.ioc.loader.json.JsonLoader", "webconfig/",
		"*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.xiaoka"})
@Encoding(input = "UTF-8", output = "UTF-8")
@Modules(scanPackage = true)
@Localization("msg")
@Ok("json")
@Fail("fail")
@SetupBy(WeSetup.class)
@ChainBy(args = { "${app.root}/WEB-INF/classes/webconfig/chains.js" })
@Views(WeViewMaker.class)
@IocBean
@Filters({/*@By(type = CSRFFilter.class),*/@By(type = LoginFilter.class),@By(type = AuthFilter.class)})
public class MainModule {

	@At("/")
	@Ok("jsp:admin.login")
	@NoFilter
	public Object main() {
		return null;
	}
}
