package com.linyun.user.controller.v1;

import com.linyun.model.dtos.ResponseResult;
import com.linyun.model.user.dtos.LoginDto;
import com.linyun.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author linyun
 * @since 2023/7/13 10:11
 */


@Api(value = "app端用户登录", tags = "app端用户登录")
@RestController
@RequestMapping("api/v1/login")
public class ApUserController {

    @Resource
    private ApUserService userService;

    @PostMapping("/login_auth")
    @ApiOperation("用户登录")
    public ResponseResult login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }
}
