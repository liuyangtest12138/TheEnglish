package com.liuy.mproxy.utils;

/**
 * describe: 公用方法类
 *
 * @author: liuy
 * @creatDate: 2018/3/12
 **/
public class PubUtil {

    /**
     * 调用方法时校验参数的合法性
     *
     * @param expression   布尔类型的表达式
     * @param errorMessage 如果检查失败，使用的异常消息; 异常消息会转换为一个字符串  {@link String#valueOf(Object)}
     * @throws RuntimeException 如果 {@code expression} is false
     */
    public static void checkArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new RuntimeException(String.valueOf(errorMessage));
        }
    }
}
