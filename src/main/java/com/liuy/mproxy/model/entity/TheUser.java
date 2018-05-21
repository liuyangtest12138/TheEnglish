package com.liuy.mproxy.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: TheUserEntity </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/9 18:01
 */
@Data
public class TheUser implements Serializable {
    private static final long serialVersionUID = 6504203106850253516L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("用户账号")
    private String userPhone;

    @ApiModelProperty("用户昵称")
    private String userName;

    @ApiModelProperty("用户真实姓名")
    private String userDealName;

    @ApiModelProperty("用户密码")
    private String userPassword;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("状态")
    private Integer state;

    @ApiModelProperty("是否激活（用户激活才可以登录（0.未激活，1.已激活））")
    private Integer isActivate;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("用户头像")
    private String imagePath;

    @ApiModelProperty("是否删除")
    private Integer del;

    @ApiModelProperty("母语名称")
    private String motherLanguageName;

    @ApiModelProperty("所在地国家编码")
    private String countryCode;

    @ApiModelProperty("所在地国家名称")
    private String countryName;

    @ApiModelProperty("所在地城市编码")
    private String cityCode;

    @ApiModelProperty("所在地城市名称")
    private String cityName;

    @ApiModelProperty("个人简介")
    private String userContent;

    @ApiModelProperty("版本号")
    private String version;
}
