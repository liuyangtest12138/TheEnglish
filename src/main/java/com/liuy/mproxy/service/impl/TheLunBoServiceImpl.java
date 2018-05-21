package com.liuy.mproxy.service.impl;

import com.liuy.mproxy.mapper.TheLunBoMapper;
import com.liuy.mproxy.model.entity.TheLunBo;
import com.liuy.mproxy.service.ITheLunBoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 16:19
 */
@Service
public class TheLunBoServiceImpl implements ITheLunBoService {
    @Resource
    private TheLunBoMapper theLunBoMapper;
    @Override
    public List<TheLunBo> queryTheTalkLunBo() {
        TheLunBo theLunBo = new TheLunBo();
        theLunBo.setIsDel(0);
        theLunBo.setIsShow(1);
        List<TheLunBo> result = theLunBoMapper.select(theLunBo);
        return result;
    }
}
