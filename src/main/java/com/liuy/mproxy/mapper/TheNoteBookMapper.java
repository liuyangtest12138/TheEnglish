package com.liuy.mproxy.mapper;

import com.liuy.mproxy.config.MyMapper;
import com.liuy.mproxy.model.dto.TheWordDTO;
import com.liuy.mproxy.model.entity.TheArticle;
import com.liuy.mproxy.model.entity.TheNotebook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Title: TheNoteBookMapper </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/5/2 11:07
 */
@Mapper
@Repository
public interface TheNoteBookMapper extends MyMapper<TheNotebook> {

    /**
     * 修改单词状态
     * @param theNotebook
     * @return
     */
    int updateTheNoteBookState(TheNotebook theNotebook);
}
