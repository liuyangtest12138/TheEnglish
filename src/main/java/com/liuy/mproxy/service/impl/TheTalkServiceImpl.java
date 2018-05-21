package com.liuy.mproxy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.liuy.mproxy.constant.ProxyConstant;
import com.liuy.mproxy.model.dto.UserLoginDTO;
import com.liuy.mproxy.model.entity.TheArticle;
import com.liuy.mproxy.model.entity.TheLunBo;
import com.liuy.mproxy.model.entity.TheWord;
import com.liuy.mproxy.model.entity.WordResult;
import com.liuy.mproxy.model.vo.TheTalkVO;
import com.liuy.mproxy.service.ITheArticleService;
import com.liuy.mproxy.service.ITheLunBoService;
import com.liuy.mproxy.service.ITheTalkService;
import com.liuy.mproxy.service.ITheWordService;
import com.liuy.mproxy.utils.RedisUtil;
import com.liuy.mproxy.utils.ValidateUtil;
import com.liuy.mproxy.web.restcontroller.LoginRestController;
import com.xescm.core.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 16:13
 */
@Service
public class TheTalkServiceImpl implements ITheTalkService{
    protected Logger logger = LoggerFactory.getLogger(LoginRestController.class);
    @Resource
    private ITheArticleService theArticleService;
    @Resource
    private ITheWordService theWordService;
    @Resource
    private ITheLunBoService theLunBoService;
    @Override
    public TheTalkVO queryShouYeData(UserLoginDTO userLoginDTO) throws Exception{
        ValidateUtil.paramRequired(userLoginDTO,"查询首页数据入参为空");
        ValidateUtil.paramRequired(userLoginDTO.getUserPhone(),"查询首页数据用户手机号为空");
        logger.info("查询首页数据",userLoginDTO);
        TheTalkVO theTalkVO = new TheTalkVO();
        TheArticle everyDayArticle = theArticleService.queryEveryDayArticle();
        ValidateUtil.paramRequired(everyDayArticle, "每日推荐文章为空，请及时添加文章");
        theTalkVO.setTheArticle(everyDayArticle);
        List<TheArticle> theArticles = theArticleService.queryRecommendArticle();
        ValidateUtil.paramRequired(theArticles,"精品推荐文章为空，请及时添加");
        theTalkVO.setTheArticleList(theArticles);
        TheWord theWord = theWordService.queryEveryDayWord();
        ValidateUtil.paramRequired(theWord,"每日推荐单词为空，请及时添加单词");
        theTalkVO.setTheWord(theWord);
        List<TheLunBo> theLunBos = theLunBoService.queryTheTalkLunBo();
        ValidateUtil.paramRequired(theLunBos,"轮播图为空，请及时添加");
        theTalkVO.setLunBos(theLunBos);
        String s = RedisUtil.get(ProxyConstant.RecommendKey.RESULT_WORD.getKey() + userLoginDTO.getUserPhone());
        if(s == null || "".equals(s)){
            theTalkVO.setLastResult(null);
        }else {
            WordResult wordResult = JSON.parseObject(s, WordResult.class);
            theTalkVO.setLastResult(wordResult);
        }
        return theTalkVO;
    }
}
