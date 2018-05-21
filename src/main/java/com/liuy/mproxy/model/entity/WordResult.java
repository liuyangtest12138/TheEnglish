package com.liuy.mproxy.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 16:03
 */
@Data
public class WordResult implements Serializable{
    private static final long serialVersionUID = 6307298079048776340L;
    @ApiModelProperty("用户手机号")
    private String userPhone;
    @ApiModelProperty("用户昵称")
    private String userName;
    @ApiModelProperty("单词总数")
    private Integer wordTotle;
    @ApiModelProperty("正确个数")
    private Integer trueNum;
    @ApiModelProperty("错误个数")
    private Integer errorNum;
    @ApiModelProperty("测试时间")
    private Date resultDate;
    @ApiModelProperty("测试的单词组")
    private List<TheWord> words;
}
