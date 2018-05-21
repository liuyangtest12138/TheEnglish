package com.liuy.mproxy.service;

import com.liuy.mproxy.model.dto.TheWordDTO;
import com.liuy.mproxy.model.entity.TheNotebook;
import com.liuy.mproxy.model.vo.OwnerVO;

import java.util.List;

/**
 * <p>Title: INoteBookService </p>
 * <p>Description  单词本</p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/5/2 10:32
 */
public interface INoteBookService {
    /**
     * <p>Title: . </p>
     * <p>Description 查询单词本单词列表</p>
     * @param
     * @author liuy
     * @CreateDate 2018/5/2 11:03
     * @return
     */
    List<TheNotebook> queryNoteBookList(TheWordDTO theWordDTO) throws Exception;
    /**
     * <p>Title: . </p>
     * <p>Description 修改单词状态</p>
     * @param
     * @author liuy
     * @CreateDate 2018/5/2 14:28
     * @return
     */
    boolean modifyStateNoteBookById(TheWordDTO theWordDTO) throws Exception;
    /**
     * <p>Title: . </p>
     * <p>Description 删除单词</p>
     * @param
     * @author liuy
     * @CreateDate 2018/5/2 14:33
     * @return
     */
    boolean deleteNoteBookById(TheWordDTO theWordDTO) throws Exception;

    OwnerVO queryOwnerData(TheWordDTO theWordDTO) throws Exception;
}
