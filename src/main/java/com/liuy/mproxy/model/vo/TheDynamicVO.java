package com.liuy.mproxy.model.vo;

import com.liuy.mproxy.model.entity.TheDynamic;
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
 * @createTime 2018/4/27 16:57
 */
@Data
public class TheDynamicVO implements Serializable{
    private static final long serialVersionUID = -419905589433979857L;
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("动态内容")
    private String dynaContent;
    @ApiModelProperty("用户手机")
    private String userPhone;
    @ApiModelProperty("用户昵称")
    private String userName;

    @ApiModelProperty("用户头像")
    private String headImage;
    @ApiModelProperty("配图")
    private String images;
    private List<String> imagesList;
    @ApiModelProperty("删除状态")
    private Integer del;
    @ApiModelProperty("发布时间")
    private Date createTime;
    @ApiModelProperty("最后修改时间")
    private Date modifyTime;
    @ApiModelProperty("等级码1.动态,2.评论,3.回复评论,4.评论文章")
    private Integer gradeCode;
    @ApiModelProperty("父id")
    private String fatherId;

    @ApiModelProperty("评论")
    private List<TheDynamic> reviews;

    @ApiModelProperty("是否被当前用户收藏")
    private Integer isCollect;
}
