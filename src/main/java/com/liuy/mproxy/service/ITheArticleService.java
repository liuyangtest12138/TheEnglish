package com.liuy.mproxy.service;

import com.liuy.mproxy.model.dto.TheArticleDTO;
import com.liuy.mproxy.model.dto.TheWordDTO;
import com.liuy.mproxy.model.entity.TheArticle;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 15:23
 */
public interface ITheArticleService {
    /**
     * <p>Title: queryEveryDayArticle </p>
     * <p>Description 查询每日推荐文章</p>
     * @param
     * @author liuy
     * @CreateDate 2018/4/26 15:25
     * @return
     */
    TheArticle queryEveryDayArticle();
    /**
     * <p>Title: queryRecommendArticle </p>
     * <p>Description 查询精品推荐文章</p>
     * @param
     * @author liuy
     * @CreateDate 2018/4/26 15:48
     * @return List<TheArticle>
     */
    List<TheArticle> queryRecommendArticle();
    /**
     * <p>Title: . </p>
     * <p>Description 查询文章列表</p>
     * @param
     * @author liuy
     * @CreateDate 2018/5/1 20:55
     * @return
     */
    List<TheArticle> queryArticleList(TheWordDTO theWordDTO) throws Exception;

    List<TheArticle> queryMyArticle(TheWordDTO theWordDTO) throws Exception;

    boolean delMyArticle(TheArticle theArticle) throws Exception;

    boolean uploadArticle(TheArticleDTO theArticleDTO) throws Exception;
}
