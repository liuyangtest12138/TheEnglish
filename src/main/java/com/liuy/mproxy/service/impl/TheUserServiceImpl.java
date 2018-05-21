package com.liuy.mproxy.service.impl;

import com.liuy.mproxy.mapper.TheUserMapper;
import com.liuy.mproxy.mapper.UserToUserMapper;
import com.liuy.mproxy.model.dto.UserLoginDTO;
import com.liuy.mproxy.model.entity.TheUser;
import com.liuy.mproxy.model.entity.UserToUser;
import com.liuy.mproxy.service.ITheUserService;
import com.liuy.mproxy.utils.EncriptUtil;
import com.liuy.mproxy.utils.RedisUtil;
import com.liuy.mproxy.utils.UUIDUtil;
import com.liuy.mproxy.utils.ValidateUtil;
import com.xescm.core.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/9 18:46
 */
@Service
public class TheUserServiceImpl implements ITheUserService{
    @Resource
    private TheUserMapper theUserMapper;
    @Resource
    private UserToUserMapper userToUserMapper;
    @Override
    public TheUser queryUserByUserPhone(String userPhone) {
        if(userPhone == null || "".equals(userPhone)){
            throw new RuntimeException("用户手机号不能为空!");
        }
        TheUser theUser = new TheUser();
        theUser.setUserPhone(userPhone);
        return theUserMapper.selectOne(theUser);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addTheUser(UserLoginDTO userLoginDTO) throws Exception{
        ValidateUtil.paramRequired(userLoginDTO,"添加用户入参为空");
        String userPhone = userLoginDTO.getUserPhone();
        ValidateUtil.paramRequired(userPhone,"用户手机号为空");
        TheUser theUser = new TheUser();
        theUser.setUserPhone(userPhone);
        TheUser user = theUserMapper.selectOne(theUser);
        if(user != null){
            throw new RuntimeException("该手机号已注册,如果忘记密码请点击找回密码");
        }
        String userName = userLoginDTO.getUserName();
        ValidateUtil.paramRequired(userName,"用户昵称不能为空");
        String password = userLoginDTO.getPassword();
        ValidateUtil.paramRequired(password,"用户密码不能为空");
        password = EncriptUtil.md5(password);
        String id = UUIDUtil.getUUID32Str();
        theUser.setId(id);
        theUser.setImagePath("image/message.png");
        theUser.setIsActivate(1);
        theUser.setUserName(userName);
        theUser.setUserPassword(password);
        int i = theUserMapper.insertSelective(theUser);
        if(i > 0){
            return true;
        } else {
            return false;
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean modifyTheUser(UserLoginDTO userLoginDTO) throws Exception {
        ValidateUtil.paramRequired(userLoginDTO,"修改用户入参为空");
        String userPhone = userLoginDTO.getUserPhone();
        ValidateUtil.paramRequired(userPhone,"用户手机号为空");
        String validate = userLoginDTO.getValidate();
        ValidateUtil.paramRequired(validate,"验证码为空");
        String s = RedisUtil.get(userPhone + "sendmessage");
        if(!validate.equals(s)){
            throw new BusinessException("验证码错误,请核对后输入");
        }
        TheUser theUser = new TheUser();
        theUser.setUserPhone(userPhone);
        TheUser user = theUserMapper.selectOne(theUser);
        if(user == null) {
            throw new RuntimeException("该手机号未注册,请前往注册页面注册！");
        }
        String password = userLoginDTO.getPassword();
        ValidateUtil.paramRequired(password,"用户密码不能为空");
        password = EncriptUtil.md5(password);
        user.setUserPassword(password);
        int i = theUserMapper.updateByPrimaryKeySelective(user);
        System.out.println(i);
        if(i >= 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserToUser> queryAttention(UserLoginDTO userLoginDTO) throws Exception {
        String userPhone = userLoginDTO.getUserPhone();
        ValidateUtil.paramRequired(userPhone,"用户手机号为空");
        UserToUser userToUser = new UserToUser();
        userToUser.setUserPhone(userPhone);
        List<UserToUser> select = userToUserMapper.select(userToUser);
        return select;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delAttention(UserLoginDTO userLoginDTO) throws Exception {
        String id = userLoginDTO.getId();
        ValidateUtil.paramRequired(id,"删除关注关系id为空");
        UserToUser userToUser = new UserToUser();
        userToUser.setId(id);
        int i = userToUserMapper.delete(userToUser);
        if(i >= 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserToUser> queryMyFans(UserLoginDTO userLoginDTO) throws Exception {
        String userPhone = userLoginDTO.getUserPhone();
        ValidateUtil.paramRequired(userPhone,"用户手机号为空");
        UserToUser userToUser = new UserToUser();
        userToUser.setToUserPhone(userPhone);
        List<UserToUser> select = userToUserMapper.select(userToUser);
        return select;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUserInfo(TheUser theUser) {
        int i = theUserMapper.updateUser(theUser);
        if(i < 0) {
            return false;
        }else {
            return true;
        }
    }
}
