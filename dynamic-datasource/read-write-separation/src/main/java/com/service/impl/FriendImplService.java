package com.service.impl;

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
        return friendMapper.list();
    }

    @Override
    public void save(Friend friend) {
        friendMapper.save(friend);
    }
}
