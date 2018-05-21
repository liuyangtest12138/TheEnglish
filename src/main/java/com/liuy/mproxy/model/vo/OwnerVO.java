package com.liuy.mproxy.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/5/2 22:57
 */
@Data
public class OwnerVO implements Serializable {
    private static final long serialVersionUID = 6083998281648019368L;

    private Integer wordNum;

    private Integer articleNum;

    private Integer dynaNum;
}
