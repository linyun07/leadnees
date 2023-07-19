package com.linyun.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linyun.model.wemedia.dtos.WmNewsPageReqDto;
import com.linyun.model.wemedia.pojos.WmNews;

import java.util.List;

/**
 * @author linyun
 * @since 2023/7/19 15:43
 */


public interface WmNewsMapper extends BaseMapper<WmNews> {
    /**
     * 分页查询
     * @param dto
     * @return
     */
    List<WmNews> getPage(WmNewsPageReqDto dto);

    /**
     * 分页查询总条数
     * @param dto
     * @return
     */
    Integer getPageCount(WmNewsPageReqDto dto);
}
