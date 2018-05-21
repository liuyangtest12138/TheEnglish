package com.liuy.mproxy.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Title: ListCopyUtil </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author liuy
 * @CreateDate 2018/3/4 14:42
 */
public class ListCopyUtil {

    /**
     *
     * 复制集合
     * @Title: copyTo
     * @Description: <功能详细描述>
     * @author liuy
     * @date 2017年5月25日 下午6:57:23
     * @param source
     * @param destinationClass
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static <E> List<E> copyTo(List<?> source, Class<E> destinationClass) throws Exception {
        if (source.size()==0) {
            return Collections.emptyList();
        }
        List<E> res = new ArrayList<>(source.size());
        for (Object o : source) {
            E e = destinationClass.newInstance();
            BeanUtils.copyProperties(o, e);
            res.add(e);
        }
        return res;
    }

}
