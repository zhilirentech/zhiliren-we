package com.xiaoka.game.common.actionfilter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.nutz.json.JsonFormat;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ViewWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.uxuexi.core.web.view.Utf8JsonTransferView;
import com.xiaoka.game.common.access.AccessConfig;
import com.xiaoka.game.common.access.AccessCore;
import com.xiaoka.game.common.annotation.NoFilter;

/**
 * 访问接口过滤器，实现对合作方接口的MD5访问验证
 * @author 朱晓川
 *
 */
public class PayNotifyFilter extends BaseActionFilter {
	
	protected Logger logger  = LoggerFactory.getLogger(PayNotifyFilter.class) ; 
	
	@Override
	public View match(ActionContext ac) {
		if (ac.getModule().getClass().isAnnotationPresent(NoFilter.class)) {
			return null;
		}
		
		HttpServletRequest request = Mvcs.getReq(); 
		/**
		 * 请求参数:
		 * 有时像checkbox这样的组件会有一个name对应对个value的时候，所以该Map中键值对是<String,String[]>的实现
		 */
		Map<String,String[]> sParaTemp = request.getParameterMap() ;
		Map<String,String> spara = new HashMap<String, String>() ;
		
		Set<String> keys = sParaTemp.keySet() ;
		if(!Util.isEmpty(keys)){
			for(String key : keys){
				String[] value = sParaTemp.get(key) ;
				
				//因为不存在checkbox之类的参数传递，因此取第一个值即可
				String newVal = null ;
				if(null != value && value.length > 0){
					newVal = value[0] ;
				}
				spara.put(key, newVal) ;
			}
		}
		
		String sign = spara.get("sign") ;
		logger.info("请求端签名值:" + sign) ;
		if(!Util.isEmpty(sign)){
			boolean verify = AccessCore.verify(spara,AccessConfig.xiaoka_wxpay_secret) ;
		    if(verify){
		    	return null ;
		    }else{
		    	return new ViewWrapper(new Utf8JsonTransferView(new JsonFormat()), JsonResult.error("签名验证失败")) ;
		    }
		}else{
			return new ViewWrapper(new Utf8JsonTransferView(new JsonFormat()), JsonResult.error("签名值不能为空")) ; 
		}
	}

}
