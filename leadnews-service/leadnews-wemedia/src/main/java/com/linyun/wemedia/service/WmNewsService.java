package com.linyun.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linyun.model.dtos.ResponseResult;
import com.linyun.model.wemedia.dtos.WmNewsDto;
import com.linyun.model.wemedia.dtos.WmNewsPageReqDto;
import com.linyun.model.wemedia.pojos.WmNews;

/**
 * @author linyun
 * @since 2023/7/19 15:43
 */


public interface WmNewsService extends IService<WmNews> {

    /**
     * 分页查询
     * @param dto
     * @return
     */
    ResponseResult page(WmNewsPageReqDto dto);
    /**
     * 发布 文章
     * @param dto
     * @return
     */
    ResponseResult submitNews(WmNewsDto dto);
}
