package com.service.impl;

import com.entity.Friend;
import com.mapper.read.ReadFriendMapper;
import com.mapper.write.WriteFriendMapper;
import com.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendImplService implements FriendService {
    @Autowired
    private ReadFriendMapper readFriendMapper;

    @Autowired
    private WriteFriendMapper writeFriendMapper;

    @Override
    public List<Friend> list() {
        return readFriendMapper.list();
    }

    @Override
    public void save(Friend friend) {
        writeFriendMapper.save(friend);
    }
}
