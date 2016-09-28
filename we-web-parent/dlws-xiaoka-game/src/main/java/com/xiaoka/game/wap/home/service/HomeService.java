package com.xiaoka.game.wap.home.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.xiaoka.game.wap.home.entity.CityInfoEntity;
import com.xiaoka.game.wap.home.entity.CustomerEntity;
import com.xiaoka.game.wap.home.entity.SchoolEntity;
import com.xiaoka.game.wap.home.entity.UpCustomerEntity;
import com.xiaoka.game.wap.home.form.AttendForm;

public interface HomeService {
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：根据openId获取用户信息
	 * 创建人： ln
	 * 创建时间： 2016年8月15日
	 * 标记：
	 * @param openId
	 * @return
	 * @version
	 */
	CustomerEntity getCustomerById(String openId);
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：获取用户在up系统中维护的信息
	 * 创建人： ln
	 * 创建时间： 2016年8月15日
	 * 标记：wap
	 * @param openId
	 * @return
	 * @version
	 */
	UpCustomerEntity getUpCustomerByOpenId(String openId);
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：获取城市，获取城市下学校
	 * 创建人： ln
	 * 创建时间： 2016年8月15日
	 * 标记：wap
	 * @param openId
	 * @return
	 * @version
	 */
	List<CityInfoEntity> selectSchool(String openId);
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：用户选择学校，进行学校的保存
	 * 创建人： ln
	 * 创建时间： 2016年8月15日
	 * 标记：wap
	 * @param openId
	 * @param schoolId
	 * @version
	 */
	void saveSchool(String openId, String schoolId);
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：根据学校名称筛选学校
	 * 创建人： ln
	 * 创建时间： 2016年8月15日
	 * 标记：
	 * @param schoolName
	 * @return
	 * @version
	 */
	List<SchoolEntity> findSchoolInfosBySchoolName(String schoolName);
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：up保存用户姓名
	 * 创建人： ln
	 * 创建时间： 2016年8月15日
	 * 标记：
	 * @param customerName
	 * @version
	 */
	void saveCustomerInfo(String customerName,String openId);
	/**
	 * 获取首页信息
	 * @param session
	 * @return
	 */
	Map<String,Object> index(HttpSession session);
	/**
	 * 签到
	 * @param session
	 * @return
	 */
	Map<String,Object> signIn(HttpSession session);
	/**
	 * 获取账户余额和签到日期集合
	 * @param session
	 * @return
	 */
	Map<String,Object> attend(HttpSession session);
	/**
	 * 参与签到
	 * @param form
	 * @return
	 */
	Map<String,Object> attendSignIn(HttpSession session,AttendForm form);
	/**
	 * 分成
	 */
	void divided();
	/**
	 * 项目名称：dlws-xiaoka-game
	 * 描述：用户账户充值1元
	 * 创建人： ln
	 * 创建时间： 2016年8月19日
	 * 标记：
	 * @param openId
	 * @version
	 */
	void saveAccount(String openId);

}
