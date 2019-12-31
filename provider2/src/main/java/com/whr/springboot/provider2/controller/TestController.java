package com.whr.springboot.provider2.controller;

import com.google.common.collect.Maps;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:whr 2019/11/15
 */
@RestController
public class TestController {

    @RequestMapping(value = "/provider1/get/{a}", method = RequestMethod.GET)
    public Map test1(@PathVariable String a) {
        System.out.println(a);
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("1", "我");
        map.put("2", "爱");
        map.put("3", "你");
        map.put("4", "呀");
        map.put("5", "provider1");
        return map;

    }

    @PostMapping(value = "/provider1/post")
    public Object test2(@RequestParam String name) {
        System.out.println(name);
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("请求类型", "POST");
        map.put("请求参数", name);
        return map;
    }

    @PutMapping(value = "/provider1/put")
    public void put(@RequestParam String name) {
        System.out.println(name);
    }

    @DeleteMapping(value = "/provider1/delete")
    public void delete(@RequestParam String name) {
        System.out.println(name);
    }

    @RequestMapping("/provider/hystrix")
    public Map hystrix() {
        System.out.println("hystrix");
        throw new RuntimeException();
    }

    @RequestMapping(value = "/provider/test")
    public Map test() {
        HashMap<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("test", "provider2");
        return hashMap;
    }

}
