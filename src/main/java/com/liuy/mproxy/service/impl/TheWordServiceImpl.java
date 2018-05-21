package com.liuy.mproxy.service.impl;

import com.alibaba.fastjson.JSON;
import com.liuy.mproxy.constant.ProxyConstant;
import com.liuy.mproxy.mapper.TheWordMapper;
import com.liuy.mproxy.model.dto.TheWordDTO;
import com.liuy.mproxy.model.entity.TheWord;
import com.liuy.mproxy.model.entity.WordResult;
import com.liuy.mproxy.model.vo.TheWordTestVO;
import com.liuy.mproxy.service.ITheWordService;
import com.liuy.mproxy.utils.RedisUtil;
import com.liuy.mproxy.utils.ValidateUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/26 15:54
 */
@Service
public class TheWordServiceImpl implements ITheWordService {
    @Resource
    private TheWordMapper theWordMapper;
    @Override
    public TheWord queryEveryDayWord() {
        TheWord theWord = new TheWord();
        theWord.setDel(0);
        String s = RedisUtil.get(ProxyConstant.RecommendKey.RECOMMEND_ARTICLE.getKey());
        Integer integer = Integer.valueOf(s);
        theWord.setSort(integer);
        TheWord result = theWordMapper.selectOne(theWord);
        // RedisUtil.set(ProxyConstant.RecommendKey.RECOMMEND_WORD.getKey(),integer.toString());
        return result;
    }

    @Override
    public List<TheWord> queryWordList(TheWordDTO theWordDTO) throws Exception{
        ValidateUtil.paramRequired(theWordDTO,"查询单词列表入参为空");
        Integer pageNum = theWordDTO.getPageNum();
        ValidateUtil.paramRequired(pageNum,"当前页码为空");
        Integer pageSize = theWordDTO.getPageSize();
        ValidateUtil.paramRequired(pageSize,"每页多少为空");
        ValidateUtil.paramRequired(theWordDTO.getType(),"单词类型为空");
        if(theWordDTO.getType() == 0){
            theWordDTO.setType(null);
        }
        RowBounds rowBounds = new RowBounds((pageNum-1)*pageSize,pageSize);
        TheWord theWord = new TheWord();
        theWord.setType(theWordDTO.getType());
        List<TheWord> theWords = theWordMapper.selectByRowBounds(theWord, rowBounds);
        return theWords;
    }

    @Override
    public List<TheWord> wordTestBegin(WordResult wordResult) throws Exception {
        ValidateUtil.paramRequired(wordResult,"测试开始入参为空");
        ValidateUtil.paramRequired(wordResult.getWordTotle(),"单词总数为空");
        RowBounds rowBounds = new RowBounds(wordResult.getWordTotle(), 0);
        TheWord theWord = new TheWord();
        theWord.setDel(0);
        List<TheWord> theWords = theWordMapper.selectByRowBounds(theWord, rowBounds);
        return theWords;
    }
}
