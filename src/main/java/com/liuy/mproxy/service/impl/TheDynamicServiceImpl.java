package com.liuy.mproxy.service.impl;

import com.liuy.mproxy.constant.ProxyConstant;
import com.liuy.mproxy.mapper.TheDynamicMapper;
import com.liuy.mproxy.model.dto.TheDynamicDTO;
import com.liuy.mproxy.model.entity.TheDynamic;
import com.liuy.mproxy.model.entity.TheUser;
import com.liuy.mproxy.model.vo.TheDynamicVO;
import com.liuy.mproxy.service.ITheDynamicService;
import com.liuy.mproxy.service.ITheUserService;
import com.liuy.mproxy.utils.RedisUtil;
import com.liuy.mproxy.utils.UUIDUtil;
import com.liuy.mproxy.utils.ValidateUtil;
import com.liuy.mproxy.web.restcontroller.LoginRestController;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * <p>Title: ${className}.${END} </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author 刘阳
 * @createTime 2018/4/28 10:48
 */
@Service
public class TheDynamicServiceImpl implements ITheDynamicService {
    protected Logger logger = LoggerFactory.getLogger(LoginRestController.class);
    @Resource
    private TheDynamicMapper theDynamicMapper;
    @Resource
    private ITheUserService theUserService;
    @Override
    public List<TheDynamicVO> queryTheDynamicList(TheDynamicDTO theDynamicDTO) throws Exception {
        ValidateUtil.paramRequired(theDynamicDTO,"查询动态列表入参为空");
        ValidateUtil.paramRequired(theDynamicDTO.getPageNum(),"当前页数为空");
        ValidateUtil.paramRequired(theDynamicDTO.getPageSize(),"每页数据条数为空");
        Integer dynaType = theDynamicDTO.getDynaType();
        ValidateUtil.paramRequired(dynaType,"分组类型为空");
        String userPhone = theDynamicDTO.getUserPhone();
        ValidateUtil.paramRequired(userPhone,"用户手机号为空");
        logger.info("查询动态列表，入参={}",theDynamicDTO);
        if(ProxyConstant.DynamicGrade.DYNAMIC_GRADE_REMEN.getKey().equals(dynaType)){
            //分组为热门
            Set<ZSetOperations.TypedTuple<String>> typedTuples = RedisUtil.rangeByScore(ProxyConstant.RecommendKey.DYNAMIC_REMEN.getKey(), 0, 5);
            List<String> ids = new ArrayList<>();
            for (ZSetOperations.TypedTuple<String> result :typedTuples ) {
                String value = result.getValue();
                ids.add(value);
            }
            theDynamicDTO.setIds(ids);
        }
        Integer pageNum = theDynamicDTO.getPageNum();
        Integer pageSize = theDynamicDTO.getPageSize();
        theDynamicDTO.setPageNum((pageNum-1)*pageSize);
        theDynamicDTO.setPageSize(pageSize);
        List<TheDynamicVO> theDynamicVOS = theDynamicMapper.queryDynamicList(theDynamicDTO);
        for (TheDynamicVO t:theDynamicVOS) {
            List<String> strings = new ArrayList<>();
            String images = t.getImages();
            String[] split = images.split(";");
            for (String s:split) {
                strings.add(s);
            }
            t.setImagesList(strings);
            if(RedisUtil.exists(userPhone+t.getId())){
                t.setIsCollect(1);
            }else {
                t.setIsCollect(0);
            }
        }
        return theDynamicVOS;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addTheDynamicComment(TheDynamicDTO theDynamicDTO) throws Exception {
        ValidateUtil.paramRequired(theDynamicDTO,"添加评论入参为空");
        String userPhone = theDynamicDTO.getUserPhone();
        ValidateUtil.paramRequired(userPhone,"添加评论用户手机号为空");
        String userName = theDynamicDTO.getUserName();
        ValidateUtil.paramRequired(userName,"添加评论用户昵称为空");
        String dynaContent = theDynamicDTO.getDynaContent();
        ValidateUtil.paramRequired(dynaContent,"评论内容为空");
        String dynaId = theDynamicDTO.getDynaId();
        ValidateUtil.paramRequired(dynaId,"评论父id为空");
        TheDynamic theDynamic = new TheDynamic();
        String id = UUIDUtil.getUUID32Str();
        theDynamic.setId(id);
        theDynamic.setDynaContent(dynaContent);
        theDynamic.setUserPhone(userPhone);
        theDynamic.setUserName(userName);
        theDynamic.setDel(0);
        theDynamic.setCreateTime(new Date());
        theDynamic.setGradeCode(2);
        theDynamic.setFatherId(dynaId);
        int i = theDynamicMapper.insertSelective(theDynamic);
        if(i < 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean addTheDynamicGood(TheDynamicDTO theDynamicDTO) throws Exception {
        ValidateUtil.paramRequired(theDynamicDTO,"点赞操作入参为空");
        String userPhone = theDynamicDTO.getUserPhone();
        ValidateUtil.paramRequired(userPhone,"用户手机号为空");
        String dynaId = theDynamicDTO.getDynaId();
        ValidateUtil.paramRequired(dynaId,"动态id为空");
        Integer goodType = theDynamicDTO.getGoodType();
        ValidateUtil.paramRequired(goodType,"点赞按钮操作类型为空");
        if(goodType == 1) {
            // 1.redis添加一条用户点赞的key(userPhone+dynaId)
            RedisUtil.set(userPhone+dynaId,"yes");
            // 2.redis该条动态点赞数量加一
            Double score = RedisUtil.getScore(ProxyConstant.RecommendKey.DYNAMIC_REMEN.getKey(), dynaId);
            if(score == null){
                RedisUtil.zAdd(ProxyConstant.RecommendKey.DYNAMIC_REMEN.getKey(),dynaId,1);
            }else {
                RedisUtil.setIincrby(ProxyConstant.RecommendKey.DYNAMIC_REMEN.getKey(),dynaId,1);
            }
        }
        if(goodType == 2) {
            // 1.redis删除一条用户点赞的key(userPhone+dynaId)
            RedisUtil.del(userPhone+dynaId);
            // 2.redis该条动态点赞数量减一
            RedisUtil.setIincrby(ProxyConstant.RecommendKey.DYNAMIC_REMEN.getKey(),dynaId,-1);
        }
        return true;
    }

    @Override
    public boolean uploadTheDynamic(TheDynamicDTO theDynamicDTO) throws Exception {
        ValidateUtil.paramRequired(theDynamicDTO,"上传动态入参为空");
        String userPhone = theDynamicDTO.getUserPhone();
        ValidateUtil.paramRequired(userPhone,"上传动态用户手机号为空");
        TheUser theUser = theUserService.queryUserByUserPhone(userPhone);
        ValidateUtil.paramRequired(theUser,"用户不存在");
        String dynaContent = theDynamicDTO.getDynaContent();
        ValidateUtil.paramRequired(dynaContent,"动态内容为空");
        List<MultipartFile> images = theDynamicDTO.getImages();
        String imagePath = "";
        if(images != null){
            for (MultipartFile image:images) {
                InputStream inputStream = image.getInputStream();
                long l = System.currentTimeMillis();
                savePic(inputStream,l+".jpg");
                imagePath = imagePath + "dynamic/"+ l + ".jpg";
            }
        }
        TheDynamic theDynamic = new TheDynamic();
        theDynamic.setId(UUIDUtil.getUUID32Str());
        theDynamic.setDynaContent(dynaContent);
        theDynamic.setUserName(theUser.getUserName());
        theDynamic.setUserPhone(userPhone);
        theDynamic.setImages(imagePath);
        theDynamic.setDel(0);
        theDynamic.setGradeCode(1);
        theDynamic.setCreateTime(new Date());
        theDynamic.setHeadImage(theUser.getImagePath());
        int i = theDynamicMapper.insertSelective(theDynamic);
        if(i < 0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<TheDynamic> queryMyDynamic(TheDynamicDTO theDynamicDTO) throws Exception {
        Integer pageNum = theDynamicDTO.getPageNum();
        ValidateUtil.paramRequired(pageNum,"分页页码为空");
        Integer pageSize = theDynamicDTO.getPageSize();
        ValidateUtil.paramRequired(pageSize,"分页条数为空");
        String userPhone = theDynamicDTO.getUserPhone();
        ValidateUtil.paramRequired(userPhone,"用户手机号为空");
        TheDynamic theDynamic = new TheDynamic();
        theDynamic.setUserPhone(userPhone);
        theDynamic.setDel(0);
        RowBounds rowBounds = new RowBounds((pageNum-1)*pageSize,pageSize);
        List<TheDynamic> theDynamics = theDynamicMapper.selectByRowBounds(theDynamic, rowBounds);
        return theDynamics;
    }

    @Override
    public boolean delMyDynamic(TheDynamicDTO theDynamicDTO) throws Exception {
        String dynaId = theDynamicDTO.getDynaId();
        ValidateUtil.paramRequired(dynaId,"删除动态id为空");
        TheDynamic theDynamic = new TheDynamic();
        theDynamic.setId(dynaId);
        theDynamic.setDel(1);
        int i = theDynamicMapper.updateDynamic(theDynamic);
        if(i<0) {
            return false;
        }else {
            return true;
        }
    }

    private void savePic(InputStream inputStream, String fileName) {
        OutputStream os = null;
        try {
            String path = "E:\\imagesuuuu\\dynamic\\";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
