package com.liuy.mproxy.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>Title: UserLoginDTO </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/10 11:10
 */
@Data
public class UserLoginDTO implements Serializable{
    private static final long serialVersionUID = -1625055814764685529L;
    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("用户账户")
    private String userPhone;

    @ApiModelProperty("用户昵称")
    private String userName;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("验证码")
    private String validate;
}
