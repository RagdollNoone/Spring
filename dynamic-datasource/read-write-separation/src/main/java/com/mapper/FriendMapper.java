package com.mapper;

import com.entity.Friend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FriendMapper {
    @Select("SELECT * FROM friend")
    List<Friend> list();

    @Insert("INSERT INTO  friend(`name`) VALUES (#{name})")
    void save(Friend friend);
}
