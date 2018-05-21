package com.liuy.mproxy.web.restcontroller;

import com.liuy.mproxy.model.dto.TheDynamicDTO;
import com.liuy.mproxy.model.dto.TheWordDTO;
import com.liuy.mproxy.model.entity.TheArticle;
import com.liuy.mproxy.model.entity.TheWord;
import com.liuy.mproxy.model.vo.TheDynamicVO;
import com.liuy.mproxy.service.ITheArticleService;
import com.liuy.mproxy.service.ITheWordService;
import com.liuy.mproxy.utils.YouDaoUtil;
import com.xescm.base.model.wrap.WrapMapper;
import com.xescm.base.model.wrap.Wrapper;
import com.xescm.core.exception.BusinessException;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/5/1 11:47
 */
@RestController
@RequestMapping("/page/comm")
public class CommunityRestController {
    protected Logger logger = LoggerFactory.getLogger(LoginRestController.class);
    @Resource
    private ITheWordService theWordService;
    @Resource
    private ITheArticleService theArticleService;
    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/theWordList",method = RequestMethod.POST)
    public Wrapper<List<TheWord>> theWordList(TheWordDTO theWordDTO){
        logger.info("==> 查询单词列表theWordDTO={}",theWordDTO);
        List<TheWord> theWords;
        try {
            theWords = theWordService.queryWordList(theWordDTO);
            logger.info("theWords={}", theWords);
        }catch (BusinessException ex) {
            logger.error("查询单词列表={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("查询单词列表={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, theWords);
    }

    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/theArticleList",method = RequestMethod.POST)
    public Wrapper<List<TheArticle>> theArticleList(TheWordDTO theWordDTO){
        logger.info("==> 查询文章列表theWordDTO={}",theWordDTO);
        List<TheArticle> theArticles;
        try {
            theArticles = theArticleService.queryArticleList(theWordDTO);
            logger.info("theArticles={}", theArticles);
        }catch (BusinessException ex) {
            logger.error("查询文章列表={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("查询文章列表={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, theArticles);
    }
    @ApiParam(name="用户输入文本",value="传入json格式")
    @RequestMapping(value = "/fanYI",method = RequestMethod.POST)
    public Wrapper<String> fanYI(TheWordDTO theWordDTO){
        logger.info("==> 翻译theWordDTO={}",theWordDTO);
        String wordContent = theWordDTO.getWordContent();
        if(wordContent == null || "".equals(wordContent)){
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "翻译内容为空");
        }
        String from = theWordDTO.getFrom();
        if(from == null || "".equals(from)){
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "原语言类型为空");
        }
        String to = theWordDTO.getTo();
        if(to == null || "".equals(to)){
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "翻译后的语言类型为空");
        }
        String result;
        try {
            result = YouDaoUtil.fanYI(wordContent, from, to);
            logger.info("result={}", result);
        }catch (BusinessException ex) {
            logger.error("查询文章列表={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("查询文章列表={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
    }
}
