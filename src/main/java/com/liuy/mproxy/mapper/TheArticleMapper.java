package com.liuy.mproxy.mapper;

import com.liuy.mproxy.config.MyMapper;
import com.liuy.mproxy.model.dto.TheDynamicDTO;
import com.liuy.mproxy.model.entity.TheArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 15:22
 */
@Mapper
@Repository
public interface TheArticleMapper extends MyMapper<TheArticle>{

    List<TheArticle> selectByIds(TheDynamicDTO theDynamicDTO);

    int updateArticleById(TheArticle theArticle);
}
