package com.liuy.mproxy.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/27 17:19
 */
@Data
public class TheDynamicDTO implements Serializable {
    private static final long serialVersionUID = -2424209335111200919L;

    private Integer pageNum;

    private Integer pageSize;

    private String userName;
    private String userPhone;
    @ApiModelProperty("热门id集合")
    private List<String> ids;
    private String dynaId;
    @ApiModelProperty("动态分组0.全部 1.关注 2.热门")
    private Integer dynaType;
    @ApiModelProperty("评论内容")
    private String dynaContent;
    @ApiModelProperty("点赞按钮操作类型1.点赞 2.取消")
    private Integer goodType;
    @ApiModelProperty("图片")
    private List<MultipartFile> images;
}
