package com.service;

import com.entity.Friend;

import java.util.List;

public interface FriendService {
    List<Friend> list();

    void save(Friend friend);
}
