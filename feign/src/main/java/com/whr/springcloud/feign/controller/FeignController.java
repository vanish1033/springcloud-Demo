package com.whr.springcloud.feign.controller;

import com.whr.springcloud.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author:whr 2019/11/17
 */
@Controller
public class FeignController {

    @Autowired
    private FeignService feignService;

    @ResponseBody
    @RequestMapping("/feign/test1")
    public Object feignTest1() {
        System.out.println("12");
        return feignService.test2("汪弘睿");
    }

    @ResponseBody
    @RequestMapping("/feign/test2")
    public Object feignTest2() throws InterruptedException {
        System.out.println("123");
        return feignService.hystrix();
    }

    @ResponseBody
    @RequestMapping("/feign/test3")
    public Map feignTest3() {
        System.out.println("1234");
        return feignService.test();
    }
}
