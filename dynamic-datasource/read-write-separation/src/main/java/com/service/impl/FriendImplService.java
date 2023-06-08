package com.service.impl;

import com.DataSourceRoute;
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
    public List<Friend> list() {
        DataSourceRoute.setDataSource("R");
        return friendMapper.list();
    }

    @Override
    public void save(Friend friend) {
        DataSourceRoute.setDataSource("W");
        friendMapper.save(friend);
    }
}
