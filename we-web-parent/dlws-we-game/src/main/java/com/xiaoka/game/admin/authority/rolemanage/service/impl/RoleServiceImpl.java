package com.xiaoka.game.admin.authority.rolemanage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.xiaoka.game.admin.authority.functionmanage.entity.FunctionEntity;
import com.xiaoka.game.admin.authority.rolemanage.entity.RoleEntity;
import com.xiaoka.game.admin.authority.rolemanage.entity.RoleFunctionMap;
import com.xiaoka.game.admin.authority.rolemanage.form.RoleModForm;
import com.xiaoka.game.admin.authority.rolemanage.service.RoleService;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.util.FormUtil;

@IocBean(name="roleService")
public class RoleServiceImpl implements RoleService{
	
	@Inject
	private IDbDao dbDao;
	
	@Override
	public boolean update(RoleModForm form) {
		//修改角色实体
		FormUtil.modify(dbDao,form, RoleEntity.class);
		updateRoleFunctionMap(form);
		return true ;
	}
	
	/**修改角色功能关系*/
	public void updateRoleFunctionMap(RoleModForm form) {

		String functionIds = form.getFunctionIds() ;
		//根据角色查询关系
		Long roleId = form.getId() ;
		List<RoleFunctionMap> before = dbDao.query(RoleFunctionMap.class, Cnd.where("roleId", "=", roleId), null) ;  
		//欲更新为
		List<RoleFunctionMap> after = new ArrayList<RoleFunctionMap>() ;
		
		if(!Util.isEmpty(functionIds)){
			String[] funIds = functionIds.split(",") ;
			for(String funId : funIds){
				RoleFunctionMap each = new RoleFunctionMap() ;
				each.setRoleId(roleId) ;
				each.setFunctionId(Long.valueOf(funId)) ; 
				
				after.add(each) ;
			}
		}
		dbDao.updateRelations(before, after) ;
	}

	@Override
	public Map<String, Object> findRole(long id) {
		Map<String,Object> obj = new HashMap<String, Object>() ;
		
		//全部功能
		List<FunctionEntity> allFunc =  dbDao.query(FunctionEntity.class, null, null) ;
		
		//根据角色查询关系
		List<RoleFunctionMap> relation = dbDao.query(RoleFunctionMap.class, Cnd.where("roleId", "=", id), null) ; 
		//如果角色有功能
		if(!Util.isEmpty(relation)){
			//该角色的功能id集合
			List<Long> existsFuncIds = new ArrayList<Long>() ;
			for(RoleFunctionMap r : relation){
				existsFuncIds.add(r.getFunctionId()) ;
			}
			
			for(FunctionEntity f : allFunc){
				if(existsFuncIds.contains(f.getId())){
					f.setChecked("true") ;
				}
			}
		}
		obj.put("list",allFunc) ;  
		obj.put("role", dbDao.fetch(RoleEntity.class, id)) ;
		return obj ;
	}
	
}
