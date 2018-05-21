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
 * @createTime 2018/4/25 18:38
 */
@Data
public class TheLunBo implements Serializable {
    private static final long serialVersionUID = -5797482147562833230L;

    private String id;

    private String lunboPath;

    private String address;

    private Integer isShow;

    private Integer isDel;

    private Date createTime;
}
