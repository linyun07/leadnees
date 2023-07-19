package com.linyun.wemedia.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.linyun.model.dtos.ResponseResult;
import com.linyun.model.wemedia.pojos.WmChannel;
import com.linyun.wemedia.mapper.WmChannelMapper;
import com.linyun.wemedia.service.WmChannelService;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author linyun
 * @since 2023/7/19 15:26
 */

@Service
public class WmChannelServiceImpl implements WmChannelService {
    @Resource
    private WmChannelMapper wmChannelMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResponseResult findAllChannel() {
        String key = "WM:CHANNELS";
        //查缓存 如果有直接返回
        String wmChannel = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(wmChannel)) {
            List<WmChannel> channels = JSONArray.parseArray(wmChannel, WmChannel.class);
            return ResponseResult.okResult(channels);
        }
        //缓存没有 查数据库
        List<WmChannel> channels = wmChannelMapper.listFindAllChannel();
        //存缓存
        stringRedisTemplate.opsForValue().set(key, JSONArray.toJSONString(channels));
        return ResponseResult.okResult(channels);
    }
}
