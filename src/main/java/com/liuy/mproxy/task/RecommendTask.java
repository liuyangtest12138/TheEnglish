package com.liuy.mproxy.task;

import com.liuy.mproxy.constant.ProxyConstant;
import com.liuy.mproxy.utils.RedisUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 22:43
 */
@Component
public class RecommendTask {
    @Scheduled(cron="0 0 0 * * ?")
    public void task(){
        String s = RedisUtil.get(ProxyConstant.RecommendKey.RECOMMEND_ARTICLE.getKey());
        Integer integer = Integer.valueOf(s);
        int i = integer.intValue();
        i += 1;
        RedisUtil.set(ProxyConstant.RecommendKey.RECOMMEND_ARTICLE.getKey(),i+"");
    }
}
