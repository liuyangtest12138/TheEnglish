package com.liuy.mproxy.service.impl;

import com.liuy.mproxy.constant.ProxyConstant;
import com.liuy.mproxy.mapper.TheArticleMapper;
import com.liuy.mproxy.mapper.UserToArticleMapper;
import com.liuy.mproxy.model.dto.TheArticleDTO;
import com.liuy.mproxy.model.dto.TheDynamicDTO;
import com.liuy.mproxy.model.dto.TheWordDTO;
import com.liuy.mproxy.model.entity.TheArticle;
import com.liuy.mproxy.model.entity.UserToArticle;
import com.liuy.mproxy.service.ITheArticleService;
import com.liuy.mproxy.utils.RedisUtil;
import com.liuy.mproxy.utils.UUIDUtil;
import com.liuy.mproxy.utils.ValidateUtil;
import com.xescm.core.exception.BusinessException;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 15:26
 */
@Service
public class TheArticleServiceImpl implements ITheArticleService {
    @Resource
    private TheArticleMapper theArticleMapper;
    @Resource
    private UserToArticleMapper userToArticleMapper;
    @Override
    public TheArticle queryEveryDayArticle(){
        TheArticle theArticle = new TheArticle();
        theArticle.setDel(0);
        String s = RedisUtil.get(ProxyConstant.RecommendKey.RECOMMEND_ARTICLE.getKey());
        Integer integer = Integer.valueOf(s);
        theArticle.setSort(integer);
        TheArticle result = theArticleMapper.selectOne(theArticle);
        return result;
    }

    @Override
    public List<TheArticle> queryRecommendArticle() {
        TheArticle theArticle = new TheArticle();
        theArticle.setDel(0);
        theArticle.setIsRecommend(1);
        List<TheArticle> select = theArticleMapper.select(theArticle);
        return select;
    }

    @Override
    public List<TheArticle> queryArticleList(TheWordDTO theWordDTO) throws Exception {
        ValidateUtil.paramRequired(theWordDTO,"查询文章列表入参为空");
        Integer pageNum = theWordDTO.getPageNum();
        ValidateUtil.paramRequired(pageNum,"当前页码为空");
        Integer pageSize = theWordDTO.getPageSize();
        ValidateUtil.paramRequired(pageSize,"每页多少为空");
        ValidateUtil.paramRequired(theWordDTO.getType(),"文章类型为空");
        if(theWordDTO.getType() == 0){
            theWordDTO.setType(null);
        }
        RowBounds rowBounds = new RowBounds((pageNum-1)*pageSize,pageSize);
        TheArticle theArticle = new TheArticle();
        theArticle.setType(theWordDTO.getType());
        List<TheArticle> theArticles = theArticleMapper.selectByRowBounds(theArticle, rowBounds);
        return theArticles;
    }

    @Override
    public List<TheArticle> queryMyArticle(TheWordDTO theWordDTO) throws Exception {
        String userPhone = theWordDTO.getUserPhone();
        ValidateUtil.paramRequired(userPhone,"用户手机号为空");
        Integer pageNum = theWordDTO.getPageNum();
        Integer pageSize = theWordDTO.getPageSize();
        UserToArticle userToArticle = new UserToArticle();
        userToArticle.setUserPhone(userPhone);
        List<UserToArticle> select = userToArticleMapper.select(userToArticle);
        List<String> ids = new ArrayList<>();
        if(select!=null) {
            for (UserToArticle u : select) {
                ids.add(u.getArticleId());
            }
        }
        if(ids.size() == 0){
            return new ArrayList<>();
        }
        TheDynamicDTO theDynamicDTO = new TheDynamicDTO();
        theDynamicDTO.setPageNum((pageNum-1)*pageSize);
        theDynamicDTO.setPageSize(pageSize);
        theDynamicDTO.setIds(ids);
        List<TheArticle> theArticles = theArticleMapper.selectByIds(theDynamicDTO);
        return theArticles;
    }

    @Override
    public boolean delMyArticle(TheArticle theArticle) throws Exception {
        ValidateUtil.paramRequired(theArticle.getId(),"删除文章id为空");
        UserToArticle userToArticle = new UserToArticle();
        userToArticle.setUserPhone(theArticle.getUserPhone());
        userToArticle.setArticleId(theArticle.getId());
        int i = userToArticleMapper.delete(userToArticle);
        if(i<0) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean uploadArticle(TheArticleDTO theArticleDTO) throws Exception {
        String content1 = "<html><body>"+theArticleDTO.getContent1()+"</body></html>";
        long l = System.currentTimeMillis();
        File file = new File("E:\\imagesuuuu\\article1\\" + l + ".html");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(fileWriter);
            out.write(content1,0,content1.length()-1);
            out.close();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        TheArticle theArticle = new TheArticle();
        theArticle.setDel(0);
        theArticle.setType(theArticleDTO.getType());
        theArticle.setContentPath("article1/"+l+".html");
        theArticle.setCreateTime(new Date());
        theArticle.setId(UUIDUtil.getUUID32Str());
        theArticle.setTitle(theArticleDTO.getTitle());
        theArticle.setTitleImage("article/liulangnan.png");
        theArticle.setUserName(theArticleDTO.getUserName());
        int i = theArticleMapper.insertSelective(theArticle);
        if(i<0) {
            return false;
        }else{
            return true;
        }
    }
}
