package com.linyun.airline.admin.authority.usermanage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import com.linyun.airline.admin.authority.functionmanage.entity.FunctionEntity;
import com.linyun.airline.admin.authority.rolemanage.entity.RoleEntity;
import com.linyun.airline.admin.authority.usermanage.entity.UserEntity;
import com.linyun.airline.admin.authority.usermanage.entity.UserRoleMap;
import com.linyun.airline.admin.authority.usermanage.form.UserModForm;
import com.linyun.airline.admin.authority.usermanage.service.UserService;
import com.linyun.airline.common.enums.UserStatusEnum;
import com.linyun.airline.common.enums.UserTypeEnum;
import com.uxuexi.core.common.util.EnumUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.util.DbSqlUtil;
import com.uxuexi.core.web.base.service.BaseService;
import com.uxuexi.core.web.util.FormUtil;

@IocBean(name = "userService")
public class UserServiceImpl extends BaseService<UserEntity> implements UserService {

	@Override
	public boolean update(UserModForm form) {
		//修改用户实体
		FormUtil.modify(dbDao, form, UserEntity.class);

		//更新用户的角色
		updateUserRoleMap(form);
		return true;
	}

	@Override
	public Map<String, Object> findUser(long userId) {
		Map<String, Object> obj = new HashMap<String, Object>();

		//全部角色
		List<RoleEntity> allRole = dbDao.query(RoleEntity.class, null, null);

		//根据用户查询user_role
		List<UserRoleMap> relation = dbDao.query(UserRoleMap.class, Cnd.where("userId", "=", userId), null);

		//如果该用户拥有功能
		if (!Util.isEmpty(relation)) {
			//该角色的功能id集合
			List<Long> existsRoleIds = new ArrayList<Long>();
			for (UserRoleMap r : relation) {
				existsRoleIds.add(r.getRoleId());
			}

			for (RoleEntity role : allRole) {
				if (existsRoleIds.contains(role.getId())) {
					role.setChecked(true);
				} else {
					role.setChecked(false);
				}
			}
		}
		obj.put("roleList", allRole); //所有角色
		obj.put("user", dbDao.fetch(UserEntity.class, userId)); //用户实体
		obj.put("userTypeEnum", EnumUtil.enum2(UserTypeEnum.class));//用户类型
		obj.put("userStatusEnum", EnumUtil.enum2(UserStatusEnum.class)); //用户状态
		return obj;
	}

	/**更新用户的角色*/
	public void updateUserRoleMap(UserModForm userForm) {
		List<UserRoleMap> mapList = userForm.getMap();

		Long userId = userForm.getId();
		//根据用户查询用户角色关系
		List<UserRoleMap> before = dbDao.query(UserRoleMap.class, Cnd.where("userId", "=", userId), null);

		if (!Util.isEmpty(mapList)) {
			for (UserRoleMap map : mapList) {
				//角色id为空则不处理
				if (Util.isEmpty(map.getRoleId())) {
					continue;
				}
				map.setUserId(userId);
			}
		}
		dbDao.updateRelations(before, mapList);
	}

	@Override
	public UserEntity findUser(String userName, String passwd) {
		return dbDao.fetch(UserEntity.class, Cnd.where("userName", "=", userName).and("password", "=", passwd));
	}

	@Override
	public List<FunctionEntity> findUserFunctions(long userId) {
		Sql sql = Sqls.create(sqlManager.get("user_function_all"));
		sql.params().set("userId", userId);
		return DbSqlUtil.query(dbDao, FunctionEntity.class, sql, null);
	}

}
