package com.we.generator;

/**
 * 用于生成实体类
 * <p>
 *
 * @author   朱晓川
 * @Date	 2016年8月30日 	 
 */
public class GenerateTest {

	public static void main(String[] args) throws Exception {
		Generator generator = new Generator();
		generator.generateEntity();
		generator.generatorModule();
	}
}
