package com.linyun.user.mapper;

import com.linyun.model.user.pojo.ApUser;
import com.linyun.model.user.dtos.LoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * @author linyun
 * @since 2023/7/13 11:05
 */

@SpringBootTest
 class UserMapperTest {
    @Resource
    private ApUserMapper userMapper;

    @Test
    void testGetUser() {
        LoginDto loginDto = new LoginDto();
        loginDto.setPhone("13511223453");
        ApUser apUser = userMapper.getUserByPhone(loginDto);
        System.out.println(apUser);
        String password = apUser.getPassword();
        String salt = apUser.getSalt();
        String pswd = password + salt;
        System.out.println(pswd);
        String cc = DigestUtils.md5DigestAsHex(pswd.getBytes());
        System.out.println(cc);
    }

}
