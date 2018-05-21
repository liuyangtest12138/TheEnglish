package com.liuy.mproxy.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * describe: spring工具类
 *
 * @author: liuy
 * @creatDate: 2017/12/21
 **/
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 描述： 根据bean名称获取spring容器的bean
     *
     * @param beanName bean名称
     * @author liuy
     * @createDate 2017/12/21
     * return
     **/
    public static Object getBeanByName(String beanName) {
        return getApplicationContext().getBean(beanName);
    }

    /**
     * 描述： 根据bean的class获取spring容器的bean
     *
     * @param tClass bean的class
     * @author liuy
     * @createDate 2017/12/21
     * return
     **/
    public static <T> T getBeanByClass(Class<T> tClass) {
        return getApplicationContext().getBean(tClass);
    }

    /**
     * 描述： 根据bean名称与bean的class获取spring容器的bean
     *
     * @param beanName bean名称
     * @param tClass   bean的class
     * @author liuy
     * @createDate 2017/12/21
     * return
     **/
    public static <T> T getBeanByNameAndClass(String beanName, Class<T> tClass) {
        return getApplicationContext().getBean(beanName, tClass);
    }
}
