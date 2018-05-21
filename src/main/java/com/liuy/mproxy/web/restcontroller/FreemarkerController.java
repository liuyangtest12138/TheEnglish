package com.liuy.mproxy.web.restcontroller;

import com.alibaba.fastjson.JSON;
import com.liuy.mproxy.constant.ProxyConstant;
import com.liuy.mproxy.model.entity.WordResult;
import com.liuy.mproxy.utils.RedisUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/25 18:24
 */
@RestController
@RequestMapping("/page/test")
public class FreemarkerController {
    @RequestMapping("/test")
    public void demo() {
        WordResult wordResult = new WordResult();
        wordResult.setUserPhone("18600787140");
        wordResult.setWordTotle(10);
        wordResult.setTrueNum(5);
        wordResult.setErrorNum(5);
        String s = JSON.toJSONString(wordResult);
        RedisUtil.set(ProxyConstant.RecommendKey.RESULT_WORD.getKey()+"18600787140",s);

    }
}
