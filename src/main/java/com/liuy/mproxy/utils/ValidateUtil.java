package com.liuy.mproxy.utils;

import com.liuy.mproxy.exception.ParamDefectException;
import com.liuy.mproxy.exception.ParamMistakeException;
import com.xescm.core.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 校验数据有效性工具类
 * 
 * @Title: ValidateUtil.java
 * @Description: <功能详细描述>
 * @author liuy
 * @date 2017年4月26日下午6:25:03
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ValidateUtil {

	public static Logger logger = LoggerFactory.getLogger(ValidateUtil.class);

	/**
	 * 
	 * 校验条件是否为空，如果符合则抛出参数缺失异常
	 * 
	 * @Title: paramRequired
	 * @Description: <功能详细描述>
	 * @author liuy
	 * @date 2017年5月2日 下午6:42:08
	 * @param value
	 * @param message
	 * @throws ParamDefectException
	 * @see [类、类#方法、类#成员]
	 */
	public static void paramRequired(Object value, String message)
	        throws ParamDefectException {
		if (value == null) {
			throw new ParamDefectException(message);
		} else {
			if (value instanceof String
			        && StringUtils.isBlank(String.valueOf(value))) {
				throw new ParamDefectException(message);
			}
		}
	}

	/**
	 * 校验条件是否符合，如果符合则抛出参数错误异常
	 * 
	 * @Title: paramValidate
	 * @Description: <功能详细描述>
	 * @author liuy
	 * @date 2017年5月2日 下午6:42:40
	 * @param checked
	 * @param message
	 * @throws ParamMistakeException
	 * @see [类、类#方法、类#成员]
	 */
	public static void paramValidate(boolean checked, String message)
	        throws ParamMistakeException {
		if (checked) {
			throw new ParamMistakeException(message);
		}
	}

	/**
	 * 校验条件是否符合，如果符合则抛出业务异常
	 * 
	 * @Title: businessValidate
	 * @Description: <功能详细描述>
	 * @author liuy
	 * @date 2017年5月2日 下午6:42:53
	 * @param checked
	 * @param message
	 * @throws BusinessException
	 * @see [类、类#方法、类#成员]
	 */
	public static void businessValidate(boolean checked, String message)
	        throws BusinessException {
		if (checked) {
			throw new BusinessException(message);
		}
	}
}
