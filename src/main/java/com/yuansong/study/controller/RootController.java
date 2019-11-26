package com.yuansong.study.controller;

import com.yuansong.study.config.SysConfig;
import com.yuansong.tools.common.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @Autowired
    private SysConfig sc;

    @GetMapping("")
    public String root(){
        DateTool dt = new DateTool();
        return "Root Test" + " " +
                dt.GetDatetimeWithMillionsecond() +
                " " +
                "TTT" +
                " " +
                sc.getConfigStrOne();
    }

    @GetMapping("hello")
    public String hello(){
        return "Hello Spring boot";
    }
}
