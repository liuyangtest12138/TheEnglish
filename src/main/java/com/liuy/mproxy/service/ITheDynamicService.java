package com.liuy.mproxy.service;

import com.liuy.mproxy.model.dto.TheDynamicDTO;
import com.liuy.mproxy.model.entity.TheDynamic;
import com.liuy.mproxy.model.vo.TheDynamicVO;

import java.util.List;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/28 10:46
 */
public interface ITheDynamicService {
    /**
     * <p>Title: queryTheDynamicList </p>
     * <p>Description </p>
     * @param
     * @author liuy
     * @CreateDate 2018/4/28 10:47
     * @return List<TheDynamicVO>
     */
    List<TheDynamicVO> queryTheDynamicList(TheDynamicDTO theDynamicDTO) throws Exception;
    /**
     * <p>Title: addTheDynamicComment </p>
     * <p>Description 添加评论</p>
     * @param theDynamicDTO
     * @author liuy
     * @CreateDate 2018/4/30 12:20
     * @return
     */
    boolean addTheDynamicComment(TheDynamicDTO theDynamicDTO) throws Exception;
    /**
     * <p>Title: . </p>
     * <p>Description 点赞</p>
     * @param
     * @author liuy
     * @CreateDate 2018/4/30 13:27
     * @return
     */
    boolean addTheDynamicGood(TheDynamicDTO theDynamicDTO) throws Exception;
    /**
     * <p>Title: . </p>
     * <p>Description 上传动态</p>
     * @param
     * @author liuy
     * @CreateDate 2018/4/30 17:38
     * @return
     */
    boolean uploadTheDynamic(TheDynamicDTO theDynamicDTO) throws Exception;

    List<TheDynamic> queryMyDynamic(TheDynamicDTO theDynamicDTO) throws Exception;

    boolean delMyDynamic(TheDynamicDTO theDynamicDTO) throws Exception;
}
