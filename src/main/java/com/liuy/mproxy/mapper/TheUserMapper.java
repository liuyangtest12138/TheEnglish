package com.liuy.mproxy.mapper;

import com.liuy.mproxy.config.MyMapper;
import com.liuy.mproxy.model.entity.TheUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: TheUserMapper </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/9 18:32
 */
@Mapper
@Repository
public interface TheUserMapper extends MyMapper<TheUser>{

    int updateUser(TheUser theUser);
}
