package com.liuy.mproxy.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/25 18:31
 */
@Data
public class TheArticle implements Serializable {
    private static final long serialVersionUID = -6864537625810239182L;

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
}
