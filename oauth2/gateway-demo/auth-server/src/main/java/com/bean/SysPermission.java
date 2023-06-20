package com.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long parentId; // 父权限
    private String name; // 权限名称
    private String enname; // 权限英文名称
    private String url; // 授权路径
    private String description; // 备注
    private Date created; // 创建时间
    private Date updated; // 更新时间
}
