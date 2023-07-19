package com.linyun.wemedia.service;

import com.linyun.model.dtos.ResponseResult;

/**
 * @author linyun
 * @since 2023/7/19 15:25
 */


public interface WmChannelService {

    /**
     * 查询所有频道
     * @return
     */
    ResponseResult findAllChannel();
}
