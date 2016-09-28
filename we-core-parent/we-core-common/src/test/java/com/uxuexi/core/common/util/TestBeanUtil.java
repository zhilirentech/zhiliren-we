package com.uxuexi.core.common.util;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uxuexi.core.common.entity.MockEntity;

/**


 * @Date	 2013-2-1
 */
public class TestBeanUtil {

	@Test
	public void testCopyProperties() {
		MockEntity mockEntity = new MockEntity();
		mockEntity.setTitle("title");
		MockEntity mockEntity2 = new MockEntity();
		BeanUtil.copyProperties(mockEntity, mockEntity2);
		Assert.assertEquals("title", mockEntity2.getTitle());
	}

	@Test
	public void testContain() {
		Assert.assertEquals(true, BeanUtil.contain(MockEntity.class, "title"));
	}

	@Test
	public void testGet() {
		MockEntity mockEntity = new MockEntity();
		mockEntity.setStr("a");
		mockEntity.setTitle("b");
		Assert.assertEquals("b", BeanUtil.get(mockEntity, "title"));
	}

	@Test
	public void testGetString() {
		MockEntity mockEntity = new MockEntity();
		mockEntity.setStr("a");
		mockEntity.setTitle("b");
		Assert.assertEquals("a", BeanUtil.getString(mockEntity, "str"));

	}

	@Test
	public void testIsFieldEmpty() {
		MockEntity mockEntity = new MockEntity();
		mockEntity.setStr("a");
		mockEntity.setTitle("");
		mockEntity.setFatherName("dad");
		mockEntity.setAge(0);
		Assert.assertFalse(BeanUtil.isFieldEmpty(mockEntity, "str"));
		Assert.assertFalse(BeanUtil.isFieldEmpty(mockEntity, "fatherName"));
		Assert.assertTrue(BeanUtil.isFieldEmpty(mockEntity, "title"));
		Assert.assertFalse(BeanUtil.isFieldEmpty(mockEntity, "age"));
	}

	@Test
	public void testMap2Object() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("str", "T");
		params.put("title", "S");

		MockEntity mockEntity = BeanUtil.map2Object(params, MockEntity.class);
		Assert.assertEquals("S", mockEntity.getTitle());

	}

	@Test
	public void testObj2Map() {
		MockEntity mockEntity = new MockEntity();
		mockEntity.setStr("T");
		mockEntity.setTitle("S");
		Map<String, Object> map = BeanUtil.obj2Map(mockEntity);
		Assert.assertEquals("T", map.get("str"));
	}

	/*@Test
	public void testObj2MapSimple() {
		MockEntity mockEntity = new MockEntity();
		mockEntity.setStr("1");
		mockEntity.setTitle("2");
		Assert.assertEquals("1", BeanUtil.obj2MapSimple(mockEntity).get("str"));
	}*/

}
