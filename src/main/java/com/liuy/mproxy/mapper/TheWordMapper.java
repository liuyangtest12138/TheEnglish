package com.liuy.mproxy.mapper;

import com.liuy.mproxy.config.MyMapper;
import com.liuy.mproxy.model.entity.TheWord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 15:50
 */
@Mapper
@Repository
public interface TheWordMapper extends MyMapper<TheWord>{
}
