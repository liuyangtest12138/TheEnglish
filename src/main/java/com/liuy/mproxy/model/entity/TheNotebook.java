package com.liuy.mproxy.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/25 18:40
 */
@Data
public class TheNotebook implements Serializable {
    private static final long serialVersionUID = 4888558892723218678L;

    private String id;

    private String wordContent;

    private String userPhone;

    private String userName;

    private String wordId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private Integer del;

    private String wordChinese;

    private Integer state;

    private String wordImage;
}
