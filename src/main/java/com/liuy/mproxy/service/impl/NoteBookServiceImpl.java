package com.liuy.mproxy.service.impl;

import com.liuy.mproxy.mapper.TheDynamicMapper;
import com.liuy.mproxy.mapper.TheNoteBookMapper;
import com.liuy.mproxy.mapper.UserToArticleMapper;
import com.liuy.mproxy.model.dto.TheWordDTO;
import com.liuy.mproxy.model.entity.TheDynamic;
import com.liuy.mproxy.model.entity.TheNotebook;
import com.liuy.mproxy.model.entity.UserToArticle;
import com.liuy.mproxy.model.vo.OwnerVO;
import com.liuy.mproxy.service.INoteBookService;
import com.liuy.mproxy.utils.ValidateUtil;
import com.liuy.mproxy.web.restcontroller.LoginRestController;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: NoteBookServiceImpl </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/5/2 11:05
 */
@Service
public class NoteBookServiceImpl implements INoteBookService {
    protected Logger logger = LoggerFactory.getLogger(LoginRestController.class);
    @Resource
    private TheNoteBookMapper theNoteBookMapper;
    @Resource
    private UserToArticleMapper userToArticleMapper;
    @Resource
    private TheDynamicMapper theDynamicMapper;
    @Override
    public List<TheNotebook> queryNoteBookList(TheWordDTO theWordDTO) throws Exception{
        ValidateUtil.paramRequired(theWordDTO,"查询单词本入参为空");
        String userPhone = theWordDTO.getUserPhone();
        ValidateUtil.paramRequired(userPhone,"用户账户为空");
        Integer pageNum = theWordDTO.getPageNum();
        ValidateUtil.paramRequired(pageNum,"当前页码为空");
        Integer pageSize = theWordDTO.getPageSize();
        ValidateUtil.paramRequired(pageSize,"每页多少为空");
        RowBounds rowBounds = new RowBounds((pageNum-1)*pageSize,pageSize);
        TheNotebook theNotebook = new TheNotebook();
        theNotebook.setUserPhone(userPhone);
        theNotebook.setDel(0);
        return theNoteBookMapper.selectByRowBounds(theNotebook, rowBounds);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean modifyStateNoteBookById(TheWordDTO theWordDTO) throws Exception {
        ValidateUtil.paramRequired(theWordDTO,"修改单词状态入参为空");
        String id = theWordDTO.getId();
        ValidateUtil.paramRequired(id,"id为空");
        Integer state = theWordDTO.getState();
        ValidateUtil.paramRequired(state,"状态为空");
        TheNotebook theNotebook = new TheNotebook();
        theNotebook.setId(id);
        theNotebook.setState(state);
        int i = theNoteBookMapper.updateTheNoteBookState(theNotebook);
        if(i < 0) {
            return false;
        }else {
            return true;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteNoteBookById(TheWordDTO theWordDTO) throws Exception {
        ValidateUtil.paramRequired(theWordDTO,"删除单词入参为空");
        String id = theWordDTO.getId();
        ValidateUtil.paramRequired(id,"id为空");
        TheNotebook theNotebook = new TheNotebook();
        theNotebook.setId(id);
        theNotebook.setDel(1);
        int i = theNoteBookMapper.updateTheNoteBookState(theNotebook);
        if(i < 0) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public OwnerVO queryOwnerData(TheWordDTO theWordDTO) throws Exception {
        String userPhone = theWordDTO.getUserPhone();
        ValidateUtil.paramRequired(userPhone,"用户手机号为空");
        TheNotebook theNotebook = new TheNotebook();
        theNotebook.setUserPhone(userPhone);
        int i = theNoteBookMapper.selectCount(theNotebook);
        OwnerVO ownerVO = new OwnerVO();
        ownerVO.setWordNum(i);
        UserToArticle userToArticle = new UserToArticle();
        userToArticle.setUserPhone(userPhone);
        int i1 = userToArticleMapper.selectCount(userToArticle);
        ownerVO.setArticleNum(i1);
        TheDynamic theDynamic = new TheDynamic();
        theDynamic.setUserPhone(userPhone);
        int i2 = theDynamicMapper.selectCount(theDynamic);
        ownerVO.setDynaNum(i2);
        return ownerVO;
    }
}
