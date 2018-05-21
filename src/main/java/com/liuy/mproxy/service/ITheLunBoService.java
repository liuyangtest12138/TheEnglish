package com.liuy.mproxy.service;

import com.liuy.mproxy.model.entity.TheLunBo;

import java.util.List;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 16:18
 */
public interface ITheLunBoService {
    /**
     * <p>Title: queryTheTalkLunBo </p>
     * <p>Description 查询首页轮播图</p>
     * @param
     * @author liuy
     * @CreateDate 2018/4/26 16:19
     * @return List<TheLunBo>
     */
    List<TheLunBo> queryTheTalkLunBo();
}
