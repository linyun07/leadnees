package com.linyun.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linyun.model.user.pojo.ApUser;
import com.linyun.model.user.dtos.LoginDto;

/**
 * @author linyun
 * @since 2023/7/13 10:17
 */


public interface ApUserMapper extends BaseMapper<ApUser> {

    ApUser getUserByPhone(LoginDto loginDto);
}
