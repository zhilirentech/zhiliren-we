package com.xiaoka.game.common.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.ModForm;

/**数据状态form，仅用于更新数据状态的操作*/
@Data
@EqualsAndHashCode(callSuper = true)
public class AlterStatusForm extends ModForm{
	/**
	 * 状态，0-冻结，1-启用，2-删除
	 */
	private Integer status = 0 ;
}
