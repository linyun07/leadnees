package com.linyun.model.user.dtos;

import lombok.Data;

/**
 * @author linyun
 * @since 2023/7/13 10:13
 */

@Data
public class LoginDto {
    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String password;

}
