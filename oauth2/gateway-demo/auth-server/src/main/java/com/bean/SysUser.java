package com.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username; // 用户名
    private String password; // 密码，加密存储
    private String phone; // 注册手机号
    private String email; // 注册邮箱
    private Date created; // 创建时间
    private Date updated; // 更新时间
}
