package com.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.entity.Friend;
import com.mapper.FriendMapper;
import com.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendImplService implements FriendService {
    @Autowired
    private FriendMapper friendMapper;


    @Override
    @DS("read-datasource")
    public List<Friend> list() {
        return friendMapper.list();
    }

    @Override
    @DS("write-datasource")
    public void save(Friend friend) {
        friendMapper.save(friend);
    }
}
