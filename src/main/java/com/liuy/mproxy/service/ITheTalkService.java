package com.liuy.mproxy.service;

import com.liuy.mproxy.model.dto.UserLoginDTO;
import com.liuy.mproxy.model.vo.TheTalkVO;

/**
 * <p>Title: ITheTalkService </p>
 * <p>Description  首页</p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 16:10
 */
public interface ITheTalkService {
    /**
     * <p>Title: queryShouYeData </p>
     * <p>Description 查询首页数据</p>
     * @param
     * @author liuy
     * @CreateDate 2018/4/26 16:12
     * @return
     */
    TheTalkVO queryShouYeData(UserLoginDTO userLoginDTO) throws Exception;
}
