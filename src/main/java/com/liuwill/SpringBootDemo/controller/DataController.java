package com.liuwill.SpringBootDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2015/10/20.
 */
@RestController
public class DataController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Map greeting(@RequestParam(value="name", defaultValue="World") String name) {
        Map resultMap = new HashMap();
        resultMap.put("status","success");
        resultMap.put("id",counter.incrementAndGet());
        resultMap.put("content", String.format(template, name));
        return resultMap;
    }
}
