/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: EntityToMapUtil.java
 * @Prject: llmj-common
 * @Package: com.llmj.common.utils
 * @Description: <功能详细描述>
 * @author: LiMG  
 * @date: 2017年5月3日 上午11:36:47
 * @version: V1.0  
 */
package com.liuy.mproxy.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: EntityToMapUtil.java
 * @Description: 实体类转map集合工具类
 * @author  liuy
 * @date 2017年5月3日上午11:36:47
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class EntityToMapUtil {
	
	/**
	 * @Title: convert
	 * @Description: 将对象的属性和值保存到map集合
	 * @author liuy
	 * @date 2017年5月3日上午11:38:43
	 * @param obj
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Map<String, Object> convert(Object obj) {
		if (null == obj) {
			return null;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Class<?> clazz = obj.getClass();
		// 获取子类的所有属性字段（不包含父类）
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// 设置属性可以访问
			field.setAccessible(true);
			try {
				Object object = field.get(obj);
				if (null != object && !"".equals(object)) {
					resultMap.put(field.getName(), object);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultMap;
	}

}
