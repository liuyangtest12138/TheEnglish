package com.liuy.mproxy.service;

import com.liuy.mproxy.model.dto.UserLoginDTO;
import com.liuy.mproxy.model.entity.TheUser;
import com.liuy.mproxy.model.entity.UserToUser;

import java.util.List;

/**
 * <p>Title: ITheUserService </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/9 18:44
 */
public interface ITheUserService {
    /**
     * <p>Title: queryUserByUserPhone. </p>
     * <p>Description </p>
     * @param userPhone
     * @author liuy
     * @CreateDate 2018/4/9 18:46
     * @return TheUserEntity
     */
    TheUser queryUserByUserPhone(String userPhone);
    /**
     * <p>Title: addTheUser </p>
     * <p>Description </p>
     * @param userLoginDTO
     * @author liuy
     * @CreateDate 2018/4/18 17:12
     * @return boolean
     */
    boolean addTheUser(UserLoginDTO userLoginDTO) throws Exception;
    /**
     * <p>Title: modifyTheUser </p>
     * <p>Description 修改用户信息</p>
     * @param userLoginDTO
     * @author liuy
     * @CreateDate 2018/4/25 16:06
     * @return boolean
     */
    boolean modifyTheUser(UserLoginDTO userLoginDTO) throws Exception;

    /**
     * 查询用户关注的用户
     * @param userLoginDTO
     * @return
     * @throws Exception
     */
    List<UserToUser> queryAttention(UserLoginDTO userLoginDTO) throws Exception;
    boolean delAttention(UserLoginDTO userLoginDTO) throws Exception;

    List<UserToUser> queryMyFans(UserLoginDTO userLoginDTO) throws Exception;

    boolean updateUserInfo(TheUser theUser);
}
