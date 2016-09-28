/**
 * Generator.java
 * com.we.generator
 * Copyright (c) 2016, 北京科技有限公司版权所有.
*/

package com.we.generator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.velocity.VelocityContext;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.uxuexi.core.common.util.EnumUtil;
import com.uxuexi.core.common.util.Util;
import com.we.generator.config.LoadConfig;
import com.we.generator.core.ActionDesc;
import com.we.generator.core.ModuleDesc;
import com.we.generator.core.PageFieldDesc;
import com.we.generator.core.ServiceDesc;
import com.we.generator.core.VelocityHandler;
import com.we.generator.core.enums.LogicEnum;
import com.we.generator.load.EntityDescriptor;
import com.we.generator.load.EntityLoader;
import com.we.generator.util.ExcelReader;
import com.we.generator.util.Utils;

/**
 * 
 * 代码生成器入口
 * 
 * @author   朱晓川
 * @Date	 2016年9月3日 	 
 */
public class Generator {

	private static final Log log = Logs.get();

	public void generateEntity() throws Exception {
		Ioc ioc = new NutIoc(new JsonLoader(LoadConfig.IOC_DBCFG_PATH));
		PropertiesProxy propConfig = ioc.get(PropertiesProxy.class, "propConfig");
		boolean useLombok = false;//是否使用lombok注解
		boolean forceCover = false; //是否覆盖已经存在的文件 
		useLombok = Boolean.valueOf(propConfig.get("use_lombok"));
		forceCover = Boolean.valueOf(propConfig.get("force_cover"));

		String template = LoadConfig.TEMPLATE_PATH + "entity.vm";
		String formTemplate = LoadConfig.TEMPLATE_PATH + "form.vm";
		String addFormTemplate = LoadConfig.TEMPLATE_PATH + "addForm.vm";
		String updateFormTemplate = LoadConfig.TEMPLATE_PATH + "updateForm.vm";
		if (useLombok) {
			template = LoadConfig.TEMPLATE_PATH + "entity4lombok.vm";
			formTemplate = LoadConfig.TEMPLATE_PATH + "form4lombok.vm";
			addFormTemplate = LoadConfig.TEMPLATE_PATH + "addForm4lombok.vm";
			updateFormTemplate = LoadConfig.TEMPLATE_PATH + "updateForm4lombok.vm";
		}

		Pattern includePattern = Pattern.compile(".*");
		String entityPkgName = LoadConfig.ENTITY_PKG_NAME; //entity所在的包名 
		String javaOutput = LoadConfig.JAVA_OUTPUT;
		String baseUri = "/";
		String basePkg = propConfig.get("base_package");
		String pkgName = basePkg + "." + entityPkgName;//实体包
		String formPkgName = basePkg + "." + LoadConfig.FORM_PKG_NAME;//form包

		EntityLoader loader = ioc.get(EntityLoader.class, "loader");
		Map<String, EntityDescriptor> entityMapping = loader.load(ioc, basePkg, baseUri, entityPkgName);

		for (Map.Entry<String, EntityDescriptor> entry : entityMapping.entrySet()) {
			String tableName = entry.getKey();
			if (includePattern != null) {
				if (!includePattern.matcher(tableName).find()) {
					log.info("skip " + tableName);
					continue;
				}
			}

			EntityDescriptor entityDesc = entry.getValue();
			VelocityHandler handler = new VelocityHandler();

			String packagePath = Utils.getPath4Pkg(pkgName);
			String entityClassName = entityDesc.getEntityClassName();
			String addFormClassName = entityDesc.getAddFormName();
			String updateFormClassName = entityDesc.getUpdateFormName();

			VelocityContext context = new VelocityContext();
			context.put("table", entityDesc);
			context.put("packageName", pkgName);

			//entity
			File file = new File(javaOutput, packagePath + "/" + entityClassName + ".java");
			log.info("generate " + file.getName() + " for table :" + tableName);
			handler.writeToFile(context, template, file, forceCover);

			//form
			String formPkgPath = Utils.getPath4Pkg(formPkgName);
			String formClassName = entityDesc.getEntityClassName().split("Entity")[0] + "Form"; //entity所对应的form类名
			File formFile = new File(javaOutput, formPkgPath + "/" + formClassName + ".java");
			File addFormFile = new File(javaOutput, formPkgPath + "/" + addFormClassName + ".java");
			File updateFormFile = new File(javaOutput, formPkgPath + "/" + updateFormClassName + ".java");
			VelocityContext formContext = new VelocityContext();
			formContext.put("form", entityDesc);
			formContext.put("packageName", formPkgName);
			handler.writeToFile(formContext, formTemplate, formFile, forceCover);
			handler.writeToFile(formContext, addFormTemplate, addFormFile, forceCover);
			handler.writeToFile(formContext, updateFormTemplate, updateFormFile, forceCover);
		}
		log.info("done!");

	}

	public void generatorModule() throws Exception {
		Ioc ioc = new NutIoc(new JsonLoader(LoadConfig.IOC_KVCFG_PATH));
		PropertiesProxy propConfig = ioc.get(PropertiesProxy.class, "propConfig");
		String basePkg = propConfig.get("base_package");
		boolean forceCover = false; //是否覆盖已经存在的文件 
		forceCover = Boolean.valueOf(propConfig.get("force_cover"));

		String moduleTpl = LoadConfig.TEMPLATE_PATH + "module.vm";
		String serviceTpl = LoadConfig.TEMPLATE_PATH + "service.vm";

		//读取excel功能模块信息
		InputStream ins = ClassLoader.getSystemResourceAsStream("code-generator/module.xlsx");

		Map<Integer, String[]> map = loadExcel(ins);
		for (int i = 1; i <= map.size(); i++) {
			String[] rowArr = map.get(i); //每一行
			genService(forceCover, basePkg, serviceTpl, rowArr);
		}

		genModuleCode(forceCover, basePkg, moduleTpl, map);
		log.info("done!");
	}

	private void genModuleCode(boolean force, String basePkg, String moduleTpl, Map<Integer, String[]> moduleInfo)
			throws IOException, ClassNotFoundException {
		String baseUri = "/";
		String javaOutput = LoadConfig.JAVA_OUTPUT;

		Map<String, ModuleDesc> moduleMap = Maps.newHashMap();

		for (int i = 1; i <= moduleInfo.size(); i++) {
			String[] rowArr = moduleInfo.get(i); //每一行
			//逻辑划分
			String logic = rowArr[0];
			//模块code
			String moduleCode = rowArr[2];
			//功能code
			String functionCode = rowArr[4];
			//视图类型
			String viewType = rowArr[5];

			ModuleDesc md = moduleMap.get(moduleCode);
			if (Util.isEmpty(md)) {
				md = new ModuleDesc();
				moduleMap.put(moduleCode, md);
			} else {
				if (!LoadConfig.defaultMethods.contains(functionCode)) {
					ActionDesc ad = new ActionDesc();
					ad.setActionName(functionCode);
					ad.setViewType(viewType);
					md.getActionList().add(ad);
				}
				continue;
			}

			double dl = Double.valueOf(logic);
			int intL = (int) dl;
			LogicEnum le = EnumUtil.get(LogicEnum.class, intL);
			String logicPath = le.value();

			String mdPkgName = basePkg + "." + logicPath + "." + moduleCode + "." + LoadConfig.MODULE_PKG_NAME;
			String moduleClassName = Utils.upperFirst(moduleCode) + "Module";
			String serviceClassName = Utils.upperFirst(moduleCode) + "ViewService";
			String serviceFullClassName = Joiner.on(".").join(basePkg, logicPath, moduleCode,
					LoadConfig.SERVICE_PKG_NAME, serviceClassName);

			md.setPackageName(mdPkgName.toLowerCase());
			md.setServiceClassName(serviceClassName);
			String atUrl = baseUri + logicPath + "/" + moduleCode;
			md.setAtUrl(atUrl);
			md.setModuleClassName(moduleClassName);
			md.setPackageName(mdPkgName);

			md.setServiceFullClassName(serviceFullClassName);
			md.setServiceInstanceName(Utils.lowerFirst(serviceClassName));
			md.setViewType(viewType);

			//默认实体
			String entityClassName = rowArr[6];
			String entityPkgName = Joiner.on(".").join(basePkg, LoadConfig.ENTITY_PKG_NAME);
			String fullEntityClassName = Joiner.on(".").join(entityPkgName, entityClassName);
			md.setEntityClassName(entityClassName);
			md.setFullEntityClassName(fullEntityClassName);
		}

		VelocityHandler writer = new VelocityHandler();
		Set<String> modules = moduleMap.keySet();
		for (String mkey : modules) {
			ModuleDesc md = moduleMap.get(mkey);

			VelocityContext context = new VelocityContext();
			context.put("module", md);

			//module
			String moduleFilePath = Utils.getPath4Pkg(md.getPackageName());
			File file = new File(javaOutput, moduleFilePath + "/" + md.getModuleClassName() + ".java");
			writer.writeToFile(context, moduleTpl, file, force);

			//jsp
			genJsp(force, writer, md);
		}
	}

	private void genJsp(boolean force, VelocityHandler handler, ModuleDesc md) throws ClassNotFoundException,
			IOException {
		String fullEntityClassName = md.getFullEntityClassName();
		Class<?> entityClass = Class.forName(fullEntityClassName);
		Mirror<?> mirror = Mirror.me(entityClass);
		Field[] fields = mirror.getFields();
		List<PageFieldDesc> fieldList = Lists.newArrayList();
		for (Field f : fields) {
			if ("id".equals(f.getName())) {
				continue;
			}

			PageFieldDesc pd = new PageFieldDesc();
			pd.setName(f.getName());

			if (f.isAnnotationPresent(Comment.class)) {
				Comment comment = f.getDeclaredAnnotation(Comment.class);
				pd.setComment(comment.value());
			} else {
				pd.setComment("标题");
			}
			fieldList.add(pd);
		}

		String jspOutPut = LoadConfig.JSP_OUTPUT;
		String pageFilePath = md.getAtUrl();

		VelocityContext jspCtx = new VelocityContext();
		jspCtx.put("fieldList", fieldList);
		jspCtx.put("atUrl", md.getAtUrl());

		String listTpl = LoadConfig.TEMPLATE_PATH + "/view/list.vm";
		File listPage = new File(jspOutPut, pageFilePath + "/" + "list.jsp");
		handler.writeToFile(jspCtx, listTpl, listPage, force);

		String updateTpl = LoadConfig.TEMPLATE_PATH + "/view/update.vm";
		File updatePage = new File(jspOutPut, pageFilePath + "/" + "update.jsp");
		handler.writeToFile(jspCtx, updateTpl, updatePage, force);

		String addTpl = LoadConfig.TEMPLATE_PATH + "/view/add.vm";
		File addPage = new File(jspOutPut, pageFilePath + "/" + "add.jsp");
		handler.writeToFile(jspCtx, addTpl, addPage, force);

		for (ActionDesc ad : md.getActionList()) {
			File commonPage = new File(jspOutPut, pageFilePath + "/" + ad.getActionName() + ".jsp");
			String commonTpl = LoadConfig.TEMPLATE_PATH + "/view/common.vm";
			handler.writeToFile(jspCtx, commonTpl, commonPage, force);
		}
	}

	private void genService(boolean force, String basePkg, String serviceTpl, String[] rowArr) throws IOException {

		String javaOutput = LoadConfig.JAVA_OUTPUT;

		//逻辑划分
		String logic = rowArr[0];

		//模块code
		String moduleCode = rowArr[2];

		//默认实体
		String entityClassName = rowArr[6];
		String entityPkgName = Joiner.on(".").join(basePkg, LoadConfig.ENTITY_PKG_NAME);
		String fullEntityClassName = Joiner.on(".").join(entityPkgName, entityClassName);

		double dl = Double.valueOf(logic);
		int intL = (int) dl;
		LogicEnum le = EnumUtil.get(LogicEnum.class, intL);
		String logicPkg = le.value();

		String sdPkgName = basePkg + "." + logicPkg + "." + moduleCode + "." + LoadConfig.SERVICE_PKG_NAME;
		String serviceClassName = Utils.upperFirst(moduleCode) + "ViewService";

		ServiceDesc sd = new ServiceDesc();
		sd.setPackageName(sdPkgName.toLowerCase());
		sd.setServiceClassName(serviceClassName);
		sd.setEntityClassName(entityClassName);
		sd.setFullEntityClassName(fullEntityClassName);

		VelocityContext context = new VelocityContext();
		context.put("service", sd);

		//service
		String serviceFilePath = Utils.getPath4Pkg(sdPkgName);
		File file = new File(javaOutput, serviceFilePath + "/" + serviceClassName + ".java");

		VelocityHandler generator = new VelocityHandler();
		generator.writeToFile(context, serviceTpl, file, force);
	}

	private Map<Integer, String[]> loadExcel(InputStream ins) {
		Map<Integer, String[]> map = null;
		try {
			ExcelReader excelReader = new ExcelReader();
			map = excelReader.readExcelContent(ins);
		} catch (Exception e) {
			log.info("文件格式错误，请使用模板文件进行操作");
		}
		return map;
	}
}
