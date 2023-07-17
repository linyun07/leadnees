package com.linyun.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linyun.model.dtos.ResponseResult;
import com.linyun.model.wemedia.dtos.WmLoginDto;
import com.linyun.model.wemedia.pojos.WmUser;


/**
 * @author linyun
 */
public interface WmUserService extends IService<WmUser> {

    /**
     * 自媒体端登录
     * @param dto
     * @return
     */
    public ResponseResult login(WmLoginDto dto);

}