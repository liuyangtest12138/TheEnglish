package com.liuy.mproxy.model.vo;

import com.liuy.mproxy.model.entity.TheArticle;
import com.liuy.mproxy.model.entity.TheLunBo;
import com.liuy.mproxy.model.entity.TheWord;
import com.liuy.mproxy.model.entity.WordResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 15:13
 */
@Data
public class TheTalkVO implements Serializable{
    private static final long serialVersionUID = 3885600475328782346L;
    @ApiModelProperty("轮播图列表")
    private List<TheLunBo> lunBos;

    @ApiModelProperty("推荐文章")
    private TheArticle theArticle;

    @ApiModelProperty("推荐单词")
    private TheWord theWord;

    @ApiModelProperty("上次测试成绩")
    private WordResult lastResult;

    @ApiModelProperty("精品推荐文章")
    private List<TheArticle> theArticleList;
}
