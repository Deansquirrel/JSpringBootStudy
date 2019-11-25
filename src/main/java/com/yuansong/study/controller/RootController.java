package com.yuansong.study.controller;

import com.yuansong.tools.common.DateTool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("")
    public String root(){
        DateTool dt = new DateTool();
        return "Root Test" + " " + dt.GetDatetimeWithMillionsecond() + " " + "TTT";
    }

    @GetMapping("hello")
    public String hello(){
        return "Hello Spring boot";
    }
}
