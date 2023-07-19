package com.linyun.wemedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linyun.common.constants.WemediaConstants;
import com.linyun.model.dtos.PageResponseResult;
import com.linyun.model.dtos.ResponseResult;
import com.linyun.model.enums.AppHttpCodeEnum;
import com.linyun.model.wemedia.dtos.WmNewsDto;
import com.linyun.model.wemedia.dtos.WmNewsPageReqDto;
import com.linyun.model.wemedia.pojos.WmNews;
import com.linyun.model.wemedia.pojos.WmNewsMaterial;
import com.linyun.wemedia.mapper.WmNewsMapper;
import com.linyun.wemedia.mapper.WmNewsMaterialMapper;
import com.linyun.wemedia.service.WmNewsService;
import com.linyun.wemedia.thread.WmThreadLocalUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author linyun
 * @since 2023/7/19 15:44
 */

@Service
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {

    @Resource
    private WmNewsMapper wmNewsMapper;
    @Resource
    private WmNewsMaterialMapper wmNewsMaterialMapper;

    @Override
    public ResponseResult page(WmNewsPageReqDto dto) {
        //检查分页参数
        dto.checkParam();
        //分页查询
        dto.setUserId(WmThreadLocalUtil.getUser().getId());
        List<WmNews> page = wmNewsMapper.getPage(dto);
        Integer count = wmNewsMapper.getPageCount(dto);
        ResponseResult result = new PageResponseResult(dto.getPage(), dto.getSize(), count);
        result.setData(page);
        return result;
    }

    @Override
    @Transactional
    public ResponseResult submitNews(WmNewsDto dto) {
        if (dto == null || dto.getContent() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //1.保存或修改文章
        WmNews wmNews = new WmNews();
        //属性名称和类型相同拷贝
        BeanUtils.copyProperties(dto, wmNews);
        //拷贝图片
        if (dto.getImages() == null && dto.getImages().size() > 0) {
            String imageStr = StringUtils.join(dto.getImages(), ",");
            wmNews.setImages(imageStr);
        }
        if (dto.getType().equals(WemediaConstants.WM_NEWS_TYPE_AUTO)) {
            wmNews.setType(null);
        }
        saveOrUpdateWmNews(wmNews);
        //2.判断是否为草稿 如果是草稿结束当前方法
        if (dto.getStatus().equals(WmNews.Status.NORMAL.getCode())) {
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        //3.不是草稿，保存内容图片余素材的关系
        List<String> materials = extractUrlInfo(dto.getContent());
        saveRelativeInfoForContent(materials,wmNews.getId());
        //4.不是草稿，保存文章封面图片余文章的关系
        return null;
    }

    /**
     * 处理文章内容图片余素材之间的关系
     * @param materials
     * @param id
     */
    private void saveRelativeInfoForContent(List<String> materials, Integer id) {
    }

    /**
     * 获取文章内容的图片信息
     * @param content
     * @return
     */
    private List<String> extractUrlInfo(String content) {
        List<String> materials=new ArrayList<>();
        List<Map> maps = JSON.parseArray(content,Map.class);
        for (Map map : maps) {
            if (map.get("type").equals("image")) {
                String imgUrl = (String) map.get("value");
                materials.add(imgUrl);
            }
        }
        return materials;
    }

    /**
     * 保存或修改文章
     *
     * @param wmNews
     */
    private void saveOrUpdateWmNews(WmNews wmNews) {
        wmNews.setUserId(WmThreadLocalUtil.getUser().getId());
        wmNews.setCreatedTime(new Date());
        wmNews.setPublishTime(new Date());
        wmNews.setEnable((short) 1);
        if (wmNews.getId() == null) {
            //保存
            save(wmNews);
        } else {
            //删除文章图片和素材之间的关系
            wmNewsMaterialMapper.delete(Wrappers.<WmNewsMaterial>lambdaQuery()
                    .eq(WmNewsMaterial::getNewsId, wmNews.getId()));
            //修改
//            update(wmNews);
        }
    }
}
