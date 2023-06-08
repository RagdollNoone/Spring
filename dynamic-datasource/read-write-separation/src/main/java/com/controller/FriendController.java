package com.controller;


import com.entity.Friend;
import com.service.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @GetMapping(value = "select")
    public List<Friend> select(){
        return friendService.list();
    }

    @GetMapping(value = "insert")
    public String insert(){
        Friend friend = new Friend();
        friend.setName("ragdollnoone");
        friendService.save(friend);
        return "data added";
    }
}
