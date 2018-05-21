package com.liuy.mproxy.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/5/1 11:18
 */
@Data
public class TheWordDTO implements Serializable{
    private static final long serialVersionUID = -5746338558184452144L;

    private Integer pageNum;
    private Integer pageSize;

    private String id;
    private String wordContent;
    private Integer type;
    private String wordChinese;
    /**
     * 文章标题
     */
    private String title;

    private String from;
    private String to;

    private String userPhone;
    /**
     * 单词状态
     */
    private Integer state;
}
