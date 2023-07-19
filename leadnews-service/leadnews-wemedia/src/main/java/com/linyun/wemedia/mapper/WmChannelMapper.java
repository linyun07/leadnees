package com.linyun.wemedia.mapper;

import com.linyun.model.wemedia.pojos.WmChannel;

import java.util.List;

/**
 * @author linyun
 * @since 2023/7/19 15:25
 */


public interface WmChannelMapper {
    /**
     * 查询所有频道
     * @return
     */
    List<WmChannel> listFindAllChannel();

}
