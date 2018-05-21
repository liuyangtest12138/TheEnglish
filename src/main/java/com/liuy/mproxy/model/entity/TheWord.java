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
 * @createTime 2018/4/25 18:43
 */
@Data
public class TheWord implements Serializable {
    private static final long serialVersionUID = -1548924491370567799L;

    private String id;

    private String wordContent;

    private String wordImage;

    private Integer type;

    private Date createTime;
    private Integer sort;
    private Integer del;

    private String wordChinese;
}
