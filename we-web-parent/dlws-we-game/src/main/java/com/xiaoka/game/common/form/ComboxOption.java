package com.xiaoka.game.common.form;

import lombok.Data;

/**联动菜单项，在封装的dwz框架下使用，后台数据组装成此对象的json数组返回即可，nutz框架的json视图*/
@Data
public class ComboxOption {
	
	private Object value ;
	
	private String text ;
	
}
