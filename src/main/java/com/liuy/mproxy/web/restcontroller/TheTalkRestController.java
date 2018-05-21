package com.liuy.mproxy.web.restcontroller;

import com.liuy.mproxy.model.dto.UserLoginDTO;
import com.liuy.mproxy.model.entity.TheUser;
import com.liuy.mproxy.model.vo.TheTalkVO;
import com.liuy.mproxy.service.ITheTalkService;
import com.liuy.mproxy.service.ITheUserService;
import com.liuy.mproxy.utils.EncriptUtil;
import com.xescm.base.model.wrap.WrapMapper;
import com.xescm.base.model.wrap.Wrapper;
import com.xescm.core.exception.BusinessException;
import com.xescm.core.utils.PublicUtil;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 16:47
 */
@RestController
@RequestMapping("/page/talk")
public class TheTalkRestController {
    protected Logger logger = LoggerFactory.getLogger(LoginRestController.class);
    @Resource
    private ITheTalkService theTalkService;
    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/theTalk",method = RequestMethod.POST)
    public Wrapper<TheTalkVO> theTalk(UserLoginDTO userLoginDTO){
        logger.info("==> 查询首页数据userLoginDTO={}",userLoginDTO);
        TheTalkVO theTalkVO;
        try {
            theTalkVO = theTalkService.queryShouYeData(userLoginDTO);
            logger.info("theTalkVO={}", theTalkVO);
        }catch (BusinessException ex) {
            logger.error("查询首页数据={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("查询首页数据={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, theTalkVO);
    }
}
