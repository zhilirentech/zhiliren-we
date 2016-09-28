package com.xiaoka.template.admin.authority.usermanage.service;

import java.util.List;
import java.util.Map;

import com.xiaoka.template.admin.authority.functionmanage.entity.FunctionEntity;
import com.xiaoka.template.admin.authority.usermanage.entity.UserEntity;
import com.xiaoka.template.admin.authority.usermanage.form.UserModForm;

public interface UserService {
	
	/**
	 * 修改用户信息
	 * @param form 
	 * @return
	 */
	public boolean update(UserModForm form) ;
	
	/**
	 * 查询用户信息(包含用户的角色)
	 * list - 角色列表
	 * role - 用户实体
	 * @param id  用户id
	 */
	Map<String,Object> findUser(long userId) ;
	
	/**根据用户名和密码查询用户*/
	UserEntity findUser(final String userName,final String passwd) ;
	
	List<FunctionEntity> findUserFunctions(long userId) ;
	
	
	
}
