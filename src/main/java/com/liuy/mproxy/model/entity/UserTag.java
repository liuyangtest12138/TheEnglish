package com.liuy.mproxy.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 10:52
 */
@Data
public class UserTag implements Serializable {
    private static final long serialVersionUID = -7012159964639322386L;

    private String id;

    private String tagCode;

    private String tagName;

    private Integer del;

    private String content;
}
