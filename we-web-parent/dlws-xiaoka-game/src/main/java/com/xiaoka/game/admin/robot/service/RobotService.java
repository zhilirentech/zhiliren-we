package com.xiaoka.game.admin.robot.service;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.admin.robot.form.RobotAddForm;
import com.xiaoka.game.admin.robot.form.RobotModForm;

public interface RobotService {
	/**
	 * 查询机器人列表
	 * @param queryForm
	 * @param pager
	 * @return
	 */
	public Object list(Pager pager);
	/**
	 * 添加机器人
	 * @param addForm
	 * @return
	 */
	public Object add(RobotAddForm addForm);
	/**
	 * 跟据id查找机器人信息
	 * @param id
	 * @return
	 */
	public Object findRobot(long id);
	/**
	 * 修改改机器人信息
	 * @param modForm
	 * @return
	 */
	public Object update(RobotModForm modForm);
	/**
	 * 根据id删除记录
	 * @param id
	 * @return
	 */
	public Object delete(long id);
	/**
	 * 查找参于记录
	 * @param openId
	 * @return
	 */
	public Object show(String openId,Pager pager);
	/**
	 * 生成参于记录
	 * @param openId
	 * @return
	 */
	public Object create(String openId);
	/**
	 * 删除机器人参与记录
	 * @param id
	 * @return
	 */
	public Object delsign(long id);
	
	
}
