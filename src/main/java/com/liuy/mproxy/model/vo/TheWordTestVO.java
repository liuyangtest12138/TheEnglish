package com.liuy.mproxy.model.vo;

import com.liuy.mproxy.model.entity.WordResult;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/5/2 16:22
 */
@Data
public class TheWordTestVO implements Serializable{
    private static final long serialVersionUID = -5931058376491878770L;
    private String id;

    private String wordContent;

    private String wordChinese;

    private Integer theResult;

}
