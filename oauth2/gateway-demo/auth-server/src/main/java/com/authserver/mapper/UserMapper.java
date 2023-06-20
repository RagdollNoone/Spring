package com.authserver.mapper;

import com.authserver.bean.SysUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("select * from tb_user where username=#{username}")
    SysUser getByUsername(String username);
}
