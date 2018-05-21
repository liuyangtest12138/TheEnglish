package com.liuy.mproxy.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/5/2 22:54
 */
@Data
public class UserToArticle implements Serializable {
    private static final long serialVersionUID = 6376587723841217828L;

    private String id;

    private String userPhone;

    private String articleId;
}
