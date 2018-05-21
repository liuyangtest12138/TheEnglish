package com.liuy.mproxy.mapper;

import com.liuy.mproxy.config.MyMapper;
import com.liuy.mproxy.model.entity.TheArticle;
import com.liuy.mproxy.model.entity.UserToUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/5/3 11:39
 */
@Mapper
@Repository
public interface UserToUserMapper extends MyMapper<UserToUser> {
}
