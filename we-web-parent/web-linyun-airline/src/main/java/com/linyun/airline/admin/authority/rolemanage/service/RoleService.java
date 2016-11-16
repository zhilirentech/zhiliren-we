package com.linyun.airline.admin.authority.rolemanage.service;

import java.util.Map;

import com.linyun.airline.admin.authority.rolemanage.form.RoleModForm;

public interface RoleService {
	
	/**
	 * 修改角色信息
	 * @param form 
	 * @return
	 */
	public boolean update(RoleModForm form) ;
	
	/**
	 * 查询角色信息(包含功能)
	 * list - 功能列表
	 * role - 角色实体
	 * @param id  角色id
	 */
	Map<String,Object> findRole(long id) ;
}
