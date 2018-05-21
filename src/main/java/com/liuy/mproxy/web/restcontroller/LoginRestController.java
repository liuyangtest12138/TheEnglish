package com.liuy.mproxy.web.restcontroller;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.liuy.mproxy.constant.ProxyConstant;
import com.liuy.mproxy.model.dto.UserLoginDTO;
import com.liuy.mproxy.model.entity.TheUser;
import com.liuy.mproxy.model.entity.UserToUser;
import com.liuy.mproxy.service.ITheUserService;
import com.liuy.mproxy.utils.EncriptUtil;
import com.liuy.mproxy.utils.RedisUtil;
import com.liuy.mproxy.utils.SendSMSUtil;
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
import java.util.List;
import java.util.Random;

/**
 * <p>Title: SupportResourceRestController.java </p>
 * <p>Description </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createDate 2018/1/22 15:34
 */
@RestController
@RequestMapping("/page/proxy")
public class LoginRestController {
    protected Logger logger = LoggerFactory.getLogger(LoginRestController.class);
    @Resource
    private ITheUserService theUserService;
    @ApiParam(name="用户手机号",value="传入json格式")
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public Wrapper<TheUser> userLogin(UserLoginDTO userLoginDTO){
        logger.info("==> 用户登录userLoginDTO={}",userLoginDTO);
        String userPhone = userLoginDTO.getUserPhone();
        if(userPhone == null || "".equals(userPhone)){
            return WrapMapper.wrap(Wrapper.ERROR_CODE,"用户手机号不能为空");
        }
        String userPassword = userLoginDTO.getPassword();
        if(userPassword == null || "".equals(userPassword)){
            return WrapMapper.wrap(Wrapper.ERROR_CODE,"用户密码不能为空");
        }
        TheUser theUser;
        try {
            theUser = theUserService.queryUserByUserPhone(userPhone);
            logger.info("theUserEntity={}", theUser);
            if(PublicUtil.isEmpty(theUser)){
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "用户账户不存在!");
            } else {
                if(EncriptUtil.md5(theUser.getUserPassword()).equals(userPassword)) {
                    return WrapMapper.wrap(Wrapper.ERROR_CODE, "密码错误!");
                }
            }
        }catch (BusinessException ex) {
            logger.error("查询用户信息={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("查询用户信息={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, theUser);
    }
    @ApiParam(name="用户实体DTO",value="传入json格式")
    @RequestMapping(value = "/userRegiste",method = RequestMethod.POST)
    public Wrapper<Boolean> userRegiste(UserLoginDTO userLoginDTO){
        logger.info("==> 用户注册userLoginDTO={}",userLoginDTO);
        boolean result;
        try {
            result = theUserService.addTheUser(userLoginDTO);
        }catch (BusinessException ex) {
            logger.error("用户注册={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }  catch (Exception e) {
            logger.error("用户注册={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        logger.info("添加用户完成={}", result);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
    }

    @ApiParam(name="用户实体DTO",value="传入json格式")
    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    public Wrapper<String> sendMessage(UserLoginDTO userLoginDTO) {
        String userPhone = userLoginDTO.getUserPhone();
        if (PublicUtil.isEmpty(userPhone)) {
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "用户手机号为空");
        }
        logger.info("==> 发送验证码userLoginDTO={}", userLoginDTO);
        String result;
        Random random = new Random();
        String content = "";
        for(int i = 0; i < 6;i++){
            int num = random.nextInt(10);
            content += num;
        }
        try {
            result = SendSMSUtil.sendSMS("d284d3dd780b4665afd066341ee3ae4e",
                    userPhone,"@1@="+content,null);
            RedisUtil.set(userPhone + "sendmessage",content);
        } catch (BusinessException ex) {
            logger.error("发送验证码={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception e) {
            logger.error("发送验证码={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        logger.info("发送验证码结果={}", result);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "发送成功",result);
    }

    @ApiParam(name="用户实体DTO",value="传入json格式")
    @RequestMapping(value = "/modifyPassword",method = RequestMethod.POST)
    public Wrapper<Boolean> modifyPassword(UserLoginDTO userLoginDTO) {
        logger.info("修改密码开始={}",userLoginDTO);
        boolean result;
        try {
            result = theUserService.modifyTheUser(userLoginDTO);
        } catch (BusinessException ex) {
            logger.error("修改密码,出现异常={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception e) {
            logger.error("修改密码，出现异常={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        logger.info("修改密码结果={}", result);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "修改密码成功",result);
    }

    @ApiParam(name="用户实体DTO",value="传入json格式")
    @RequestMapping(value = "/queryAttention",method = RequestMethod.POST)
    public Wrapper<List<UserToUser>> queryAttention(UserLoginDTO userLoginDTO) {
        logger.info("查询用户关注好友={}",userLoginDTO);
        List<UserToUser> result;
        try {
            result = theUserService.queryAttention(userLoginDTO);
        } catch (BusinessException ex) {
            logger.error("查询用户关注好友,出现异常={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception e) {
            logger.error("查询用户关注好友，出现异常={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        logger.info("查询用户关注好友={}", result);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "查询用户关注好友",result);
    }

    @ApiParam(name="用户实体DTO",value="传入json格式")
    @RequestMapping(value = "/delAttention",method = RequestMethod.POST)
    public Wrapper<Boolean> delAttention(UserLoginDTO userLoginDTO) {
        logger.info("删除用户关注好友={}",userLoginDTO);
        boolean result;
        try {
            result = theUserService.delAttention(userLoginDTO);
        } catch (BusinessException ex) {
            logger.error("删除用户关注好友,出现异常={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception e) {
            logger.error("删除用户关注好友，出现异常={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        logger.info("删除用户关注好友={}", result);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除用户关注好友",result);
    }
    @ApiParam(name="用户实体DTO",value="传入json格式")
    @RequestMapping(value = "/queryMyFans",method = RequestMethod.POST)
    public Wrapper<List<UserToUser>> queryMyFans(UserLoginDTO userLoginDTO) {
        logger.info("查询用户粉丝={}",userLoginDTO);
        List<UserToUser> result;
        try {
            result = theUserService.queryMyFans(userLoginDTO);
        } catch (BusinessException ex) {
            logger.error("查询用户粉丝,出现异常={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception e) {
            logger.error("查询用户粉丝，出现异常={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        logger.info("查询用户粉丝={}", result);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "查询用户粉丝",result);
    }

    @ApiParam(name="用户实体DTO",value="传入json格式")
    @RequestMapping(value = "/queryOneUser",method = RequestMethod.POST)
    public Wrapper<TheUser> queryUserOne(UserLoginDTO userLoginDTO) {
        logger.info("查询用户={}",userLoginDTO);
        TheUser result;
        try {
            result = theUserService.queryUserByUserPhone(userLoginDTO.getUserPhone());
        } catch (BusinessException ex) {
            logger.error("查询用户,出现异常={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception e) {
            logger.error("查询用户，出现异常={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        logger.info("查询用户={}", result);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "查询用户",result);
    }

    @ApiParam(name="用户实体DTO",value="传入json格式")
    @RequestMapping(value = "/modifyOneUser",method = RequestMethod.POST)
    public Wrapper<Boolean> modifyOneUser(TheUser theUser) {
        logger.info("修改用户={}",theUser);
        boolean result;
        try {
            result = theUserService.updateUserInfo(theUser);
        } catch (BusinessException ex) {
            logger.error("修改用户,出现异常={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception e) {
            logger.error("修改用户，出现异常={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        logger.info("修改用户={}", result);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "查询用户",result);
    }
}
