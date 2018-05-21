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
 * @createTime 2018/4/25 18:34
 */
@Data
public class TheDynamic implements Serializable {
    private static final long serialVersionUID = -4108384789536530334L;

    private String id;

    private String dynaContent;

    private String userPhone;

    private String userName;

    private String headImage;
    private String images;
    private Integer del;

    private Date createTime;

    private Date modifyTime;

    private Integer gradeCode;

    private String fatherId;
}
