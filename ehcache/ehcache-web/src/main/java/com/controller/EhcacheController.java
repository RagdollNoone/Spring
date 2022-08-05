package com.controller;

import com.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/")
public class EhcacheController {
    @Autowired
    private NumberService numberService;

    @PostMapping("square/{number}")
    public String getSquare(@PathVariable Long number) {
        log.info("[EhcacheController][getSquare]start, number: {}", number);
        return String.format("{\"square\": %s}", numberService.square(number));
    }
}
