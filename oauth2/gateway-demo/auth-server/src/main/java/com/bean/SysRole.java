package com.bean;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long parentId; // 父角色
    private String name; // 角色名称
    private String enname; // 角色英文名称
    private String description; // 备注
    private Date created;
    private Date updated;
}
