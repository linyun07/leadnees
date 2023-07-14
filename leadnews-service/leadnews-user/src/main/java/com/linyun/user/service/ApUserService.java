package com.linyun.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linyun.model.dtos.ResponseResult;
import com.linyun.model.user.pojo.ApUser;
import com.linyun.model.user.dtos.LoginDto;

/**
 * @author linyun
 * @since 2023/7/13 10:19
 */


public interface ApUserService extends IService<ApUser> {

    /**
     * app端登录功能
     *
     * @param loginDto
     * @return
     */
    ResponseResult login(LoginDto loginDto);
}
