package com.liuy.mproxy.mapper;

import com.liuy.mproxy.config.MyMapper;
import com.liuy.mproxy.model.dto.TheDynamicDTO;
import com.liuy.mproxy.model.entity.TheArticle;
import com.liuy.mproxy.model.entity.TheDynamic;
import com.liuy.mproxy.model.vo.TheDynamicVO;
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
public interface TheDynamicMapper extends MyMapper<TheDynamic>{
    /**
     * <p>Title: queryDynamicList </p>
     * <p>Description </p>
     * @param theDynamicDTO
     * @author liuy
     * @CreateDate 2018/4/28 16:11
     * @return
     */
    List<TheDynamicVO> queryDynamicList(TheDynamicDTO theDynamicDTO);

    int updateDynamic(TheDynamic theDynamic);
}
