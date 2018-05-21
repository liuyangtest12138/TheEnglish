/**  
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: UUIDUtil.java
 * @Prject: llmj-common
 * @Package: com.llmj.utils
 * @Description: <功能详细描述>
 * @author: pandaijun  
 * @date: 2016-7-21 下午1:54:48
 * @version: V1.0  
 */
package com.liuy.mproxy.utils;

import java.util.UUID;

/**
 * <一句话功能简述>
 * 
 * @Title: UUIDUtil.java
 * @Description: <功能详细描述>
 * @author liuy
 * @date 2016-7-21下午1:54:48
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UUIDUtil {

	public static UUID getUUID() {
		return UUID.randomUUID();
	}

	public static String getUUID36Str() {
		return getUUID().toString();
	}

	public static String getUUID32Str() {
		return getUUID().toString().replaceAll("-", "");
	}
}
