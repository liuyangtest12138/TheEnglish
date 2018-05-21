package com.liuy.mproxy.service;

import com.liuy.mproxy.model.dto.TheWordDTO;
import com.liuy.mproxy.model.entity.TheWord;
import com.liuy.mproxy.model.entity.WordResult;
import com.liuy.mproxy.model.vo.TheWordTestVO;

import java.util.List;

/**
 * <p>Title: ITheWordService </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 15:52
 */
public interface ITheWordService {
    /**
     * <p>Title: queryEveryDayWord </p>
     * <p>Description 每日推荐单词</p>
     * @param
     * @author liuy
     * @CreateDate 2018/4/26 15:53
     * @return TheWord
     */
    TheWord queryEveryDayWord();
    /**
     * <p>Title: . </p>
     * <p>Description 查询单词列表（分页）</p>
     * @param
     * @author liuy
     * @CreateDate 2018/5/1 11:24
     * @return
     */
    List<TheWord> queryWordList(TheWordDTO theWordDTO) throws Exception;
    /**
     * <p>Title: . </p>
     * <p>Description 单词测试开始</p>
     * @param
     * @author liuy
     * @CreateDate 2018/5/2 16:06
     * @return
     */
    List<TheWord> wordTestBegin(WordResult wordResult) throws Exception;


}
