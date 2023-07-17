package com.linyun.model.wemedia.dtos;


import lombok.Data;

/**
 * @author linyun
 */
@Data
public class WmLoginDto {

    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
}
