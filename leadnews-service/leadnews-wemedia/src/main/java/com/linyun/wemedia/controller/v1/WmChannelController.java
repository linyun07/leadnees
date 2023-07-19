package com.linyun.wemedia.controller.v1;

import com.linyun.model.dtos.ResponseResult;
import com.linyun.wemedia.service.WmChannelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linyun
 * @since 2023/7/19 15:24
 */
@RequestMapping("/api/v1/channel")
@RestController
public class WmChannelController {

    @Resource
    private WmChannelService wmChannelService;

    /**
     * 查询所有频道
     * @return
     */
    @GetMapping("/channels")
    public ResponseResult  findAllChannel(){
        return wmChannelService.findAllChannel();
    }

}
