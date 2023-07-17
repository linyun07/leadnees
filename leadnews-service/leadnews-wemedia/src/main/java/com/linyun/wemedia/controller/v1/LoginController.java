package com.linyun.wemedia.controller.v1;


import com.linyun.model.dtos.ResponseResult;
import com.linyun.model.wemedia.dtos.WmLoginDto;
import com.linyun.wemedia.service.WmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linyun
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private WmUserService wmUserService;

    @PostMapping("/in")
    public ResponseResult login(@RequestBody WmLoginDto dto){
        return wmUserService.login(dto);
    }
}
