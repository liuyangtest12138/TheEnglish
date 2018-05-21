package com.liuy.mproxy.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/5/4 18:15
 */
@Data
public class TheArticleDTO implements Serializable {
    private static final long serialVersionUID = 1986243665098403645L;

    private String id;

    private String title;

    private String titleImage;

    private String contentPath;

    private String userPhone;

    private String userName;

    private Date createTime;

    private Integer articleFrom;

    private Integer del;

    private Integer isRecommend;

    private Integer sort;

    private Integer type;

    private String content1;
}
