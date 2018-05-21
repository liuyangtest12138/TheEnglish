package com.liuy.mproxy.web.restcontroller;

import com.liuy.mproxy.model.dto.TheDynamicDTO;
import com.liuy.mproxy.model.dto.UserLoginDTO;
import com.liuy.mproxy.model.entity.TheDynamic;
import com.liuy.mproxy.model.vo.TheDynamicVO;
import com.liuy.mproxy.model.vo.TheTalkVO;
import com.liuy.mproxy.service.ITheDynamicService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/28 14:17
 */
@RestController
@RequestMapping("/page/dyna")
public class TheDynamicRestController {
    protected Logger logger = LoggerFactory.getLogger(LoginRestController.class);
    @Resource
    private ITheDynamicService theDynamicService;
    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/theDynamic",method = RequestMethod.POST)
    public Wrapper<List<TheDynamicVO>> theDynamic(TheDynamicDTO theDynamicDTO){
        logger.info("==> 查询动态列表theDynamicDTO={}",theDynamicDTO);
        List<TheDynamicVO> theDynamicVOS;
        try {
            theDynamicVOS = theDynamicService.queryTheDynamicList(theDynamicDTO);
            logger.info("theDynamicVOS={}", theDynamicVOS);
        }catch (BusinessException ex) {
            logger.error("查询动态列表={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("查询动态列表={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, theDynamicVOS);
    }

    @ApiParam(name="添加评论",value="传入json格式")
    @RequestMapping(value = "/addTheDynamicComment",method = RequestMethod.POST)
    public Wrapper<Boolean> addTheDynamicComment(TheDynamicDTO theDynamicDTO){
        logger.info("==> 添加评论theDynamicDTO={}",theDynamicDTO);
        boolean result;
        try {
            result = theDynamicService.addTheDynamicComment(theDynamicDTO);
            logger.info("result={}", result);
        }catch (BusinessException ex) {
            logger.error("添加评论={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("添加评论={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
    }
    @ApiParam(name="添加评论",value="传入json格式")
    @RequestMapping(value = "/goodTheDynamic",method = RequestMethod.POST)
    public Wrapper<Boolean> goodTheDynamic(TheDynamicDTO theDynamicDTO){
        logger.info("==> 点赞theDynamicDTO={}",theDynamicDTO);
        boolean result;
        try {
            result = theDynamicService.addTheDynamicGood(theDynamicDTO);
            logger.info("result={}", result);
        }catch (BusinessException ex) {
            logger.error("点赞={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("点赞={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
    }
    @ApiParam(name="上传动态",value="传入json格式")
    @RequestMapping(value = "/uploadDyna",method = RequestMethod.POST)
    public Wrapper<Boolean> uploadDyna(TheDynamicDTO theDynamicDTO){
        logger.info("==> 上传动态theDynamicDTO={}",theDynamicDTO);
        boolean result;
        try {
            result = theDynamicService.uploadTheDynamic(theDynamicDTO);
            logger.info("result={}", result);
        }catch (BusinessException ex) {
            logger.error("上传动态={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("上传动态={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
    }

    @ApiParam(name="查询我的动态",value="传入json格式")
    @RequestMapping(value = "/myDynamic",method = RequestMethod.POST)
    public Wrapper<List<TheDynamic>> myDynamic(TheDynamicDTO theDynamicDTO){
        logger.info("==> 我的动态theDynamicDTO={}",theDynamicDTO);
        List<TheDynamic> theDynamics;
        try {
            theDynamics = theDynamicService.queryMyDynamic(theDynamicDTO);
            logger.info("theDynamics={}", theDynamics);
        }catch (BusinessException ex) {
            logger.error("我的动态={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("我的动态={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, theDynamics);
    }
    @ApiParam(name="查询我的动态",value="传入json格式")
    @RequestMapping(value = "/delMyDynamic",method = RequestMethod.POST)
    public Wrapper<Boolean> delMyDynamic(TheDynamicDTO theDynamicDTO){
        logger.info("==> 删除我的动态theDynamicDTO={}",theDynamicDTO);
        boolean b;
        try {
             b = theDynamicService.delMyDynamic(theDynamicDTO);
            logger.info("result={}", b);
        }catch (BusinessException ex) {
            logger.error("删除我的动态={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("删除我的动态={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, b);
    }
}
