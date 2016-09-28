package com.xiaoka.game.wap.game.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xiaoka.game.wap.game.form.GameAddForm;


public interface GameService {
	/**
	 * pk或发起pk
	 * @param session
	 * @return
	 */
	Map<String,Object> game(HttpSession session);
	/**
	 * 发起pk
	 * @param session
	 * @return
	 */
	Map<String,Object> launch(HttpSession session);
	/**
	 * 添加pk游戏
	 * @param session
	 * @return
	 */
	Map<String,Object> add(HttpServletRequest req,GameAddForm addForm);
	/**
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	Map<String,Object> show(HttpSession session, Integer id);
	/**
	 * pk签到
	 * @param session
	 * @return
	 */
	Map<String, Object> signIn(HttpSession session) ;
	/**
	 * 接受pK
	 * @param session
	 * @param id
	 * @return
	 */
	Map<String, Object> acceptPK(HttpServletRequest req, Integer id);
	/**
	 * 分享入口
	 * @param id
	 * @return
	 */
	Map<String, Object> entrance(Long id);
	/**
	 * 定时任务，修改游戏状态
	 */
	void updateGameStatus();
}
