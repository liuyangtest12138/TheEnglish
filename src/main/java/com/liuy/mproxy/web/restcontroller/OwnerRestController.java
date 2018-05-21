package com.liuy.mproxy.web.restcontroller;

import com.alibaba.fastjson.JSON;
import com.liuy.mproxy.constant.ProxyConstant;
import com.liuy.mproxy.model.dto.TheArticleDTO;
import com.liuy.mproxy.model.dto.TheWordDTO;
import com.liuy.mproxy.model.entity.TheArticle;
import com.liuy.mproxy.model.entity.TheNotebook;
import com.liuy.mproxy.model.entity.TheWord;
import com.liuy.mproxy.model.entity.WordResult;
import com.liuy.mproxy.model.vo.OwnerVO;
import com.liuy.mproxy.service.INoteBookService;
import com.liuy.mproxy.service.ITheArticleService;
import com.liuy.mproxy.service.ITheWordService;
import com.liuy.mproxy.utils.RedisUtil;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
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
@RequestMapping("/page/owner")
public class OwnerRestController {
    protected Logger logger = LoggerFactory.getLogger(LoginRestController.class);
    @Resource
    private INoteBookService noteBookService;
    @Resource
    private ITheWordService theWordService;
    @Resource
    private ITheArticleService theArticleService;
    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/theNoteBookList",method = RequestMethod.POST)
    public Wrapper<List<TheNotebook>> theNoteBookList(TheWordDTO theWordDTO){
        logger.info("==> 查询单词本列表theWordDTO={}",theWordDTO);
        List<TheNotebook> theNotebooks;
        try {
            theNotebooks = noteBookService.queryNoteBookList(theWordDTO);
            logger.info("theNotebooks={}", theNotebooks);
        }catch (BusinessException ex) {
            logger.error("查询单词本列表={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("查询单词本列表={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, theNotebooks);
    }

    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/theNoteBookModify",method = RequestMethod.POST)
    public Wrapper<Boolean> theNoteBookModify(TheWordDTO theWordDTO){
        logger.info("==> 修改单词状态theWordDTO={}",theWordDTO);
        boolean result;
        try {
            result = noteBookService.modifyStateNoteBookById(theWordDTO);
            logger.info("result={}", result);
        }catch (BusinessException ex) {
            logger.error("修改单词状态={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("修改单词状态={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
    }

    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/theNoteBookDel",method = RequestMethod.POST)
    public Wrapper<Boolean> theNoteBookDel(TheWordDTO theWordDTO){
        logger.info("==> 删除单词theWordDTO={}",theWordDTO);
        boolean result;
        try {
            result = noteBookService.deleteNoteBookById(theWordDTO);
            logger.info("result={}", result);
        }catch (BusinessException ex) {
            logger.error("删除单词={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("删除单词={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
    }
    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/wordTestBegin",method = RequestMethod.POST)
    public Wrapper<List<TheWord>> wordTestBegin(WordResult wordResult){
        logger.info("==> 测试开始wordResult={}",wordResult);
        List<TheWord> result;
        try {
            result = theWordService.wordTestBegin(wordResult);
            logger.info("result={}", result);
        }catch (BusinessException ex) {
            logger.error("测试开始={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("测试开始={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
    }

    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/pushResult",method = RequestMethod.POST)
    public Wrapper<Boolean> pushResult(WordResult wordResult){
        logger.info("==> 上传成绩wordResult={}",wordResult);
        try {
            wordResult.setResultDate(new Date());
            String s = JSON.toJSONString(wordResult);
            RedisUtil.set(ProxyConstant.RecommendKey.RESULT_WORD.getKey()+wordResult.getUserPhone(),s);
        }catch (BusinessException ex) {
            logger.error("上传成绩={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("上传成绩={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, true);
    }
    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/ownerData",method = RequestMethod.POST)
    public Wrapper<OwnerVO> ownerData(TheWordDTO theWordDTO){
        logger.info("==> 查询我的页面数据theWordDTO={}",theWordDTO);
        OwnerVO ownerVO;
        try {
            ownerVO = noteBookService.queryOwnerData(theWordDTO);
        }catch (BusinessException ex) {
            logger.error("查询我的页面数据={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("查询我的页面数据={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, ownerVO);
    }

    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/myArticle",method = RequestMethod.POST)
    public Wrapper<List<TheArticle>> myArticle(TheWordDTO theWordDTO){
        logger.info("==> 查询收藏文章theWordDTO={}",theWordDTO);
        List<TheArticle> theArticles;
        try {
            theArticles = theArticleService.queryMyArticle(theWordDTO);
        }catch (BusinessException ex) {
            logger.error("查询收藏文章={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("查询收藏文章={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, theArticles);
    }

    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/delMyArticle",method = RequestMethod.POST)
    public Wrapper<Boolean> delMyArticle(TheArticle theArticle){
        logger.info("==> 删除收藏文章theArticle={}",theArticle);
        boolean result;
        try {
            result = theArticleService.delMyArticle(theArticle);
        }catch (BusinessException ex) {
            logger.error("删除收藏文章={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("删除收藏文章={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
    }

    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/testKind",method = RequestMethod.POST)
    public boolean testKind(TheArticleDTO article){
        logger.info("==> 上传文章theArticle={}",article);
        try {
            theArticleService.uploadArticle(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
