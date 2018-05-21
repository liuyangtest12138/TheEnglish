package com.liuy.mproxy.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 10:55
 */
@Data
public class UserToUser implements Serializable{
    private static final long serialVersionUID = 5079359814880101661L;

    private String id;

    private String userPhone;

    private String userName;

    private String userImage;

    private String toUserPhone;

    private String toUserName;

    private String toUserImage;

    private Integer del;

    private Date createTime;

    private Integer relation;
}
