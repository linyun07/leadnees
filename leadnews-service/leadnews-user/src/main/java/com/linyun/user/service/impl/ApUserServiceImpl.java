package com.linyun.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linyun.model.dtos.ResponseResult;
import com.linyun.model.enums.AppHttpCodeEnum;
import com.linyun.model.user.pojo.ApUser;
import com.linyun.model.user.dtos.LoginDto;
import com.linyun.user.mapper.ApUserMapper;
import com.linyun.user.service.ApUserService;
import com.linyun.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linyun
 * @since 2023/7/13 10:19
 */
@Slf4j
@Service
@Transactional
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

    @Resource
    private ApUserMapper userMapper;

    @Override
    public ResponseResult login(LoginDto loginDto) {
        //1.正常登录 判断用户名或密码不为空
        if (StringUtils.isNoneBlank(loginDto.getPhone()) && StringUtils.isNoneBlank(loginDto.getPassword())) {
            //1.1 根据手机号查询用户信息
            ApUser apUser = userMapper.getUserByPhone(loginDto);
            if (apUser == null) {
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "未找到用户 请先注册");
            }
            //1.2对照密码
            String pswd = loginDto.getPassword() + apUser.getSalt();
            //对拼接的密码进行md5加密
            String password = DigestUtils.md5DigestAsHex(pswd.getBytes());
            if (!password.equals(apUser.getPassword())) {
                //密码比对没有成功 返回错误信息
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
            //1.3 返回数据
            //通过工具类获取token+ID的组合
            String token = AppJwtUtil.getToken(apUser.getId().longValue());
            //新建map拼接返回信息
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            //设置重要信息为空
            apUser.setSalt("");
            apUser.setPassword("");
            map.put("user", apUser);

            //返回信息
            return ResponseResult.okResult(map);
        }
        //2.游客登陆
        Map<String, Object> map = new HashMap<>();
        map.put("token", AppJwtUtil.getToken(0L));
        return ResponseResult.okResult(map);
    }
}
