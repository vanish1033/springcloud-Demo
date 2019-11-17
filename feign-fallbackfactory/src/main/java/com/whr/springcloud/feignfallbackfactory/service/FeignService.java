package com.whr.springcloud.feignfallbackfactory.service;

import com.whr.springcloud.feignfallbackfactory.fallbackfactory.MyFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author:whr 2019/11/17
 */
@FeignClient(name = "PROVIDER", fallbackFactory = MyFallbackFactory.class)
public interface FeignService {

    @PostMapping(value = "/provider1/post")
    Object test2(@RequestParam String name);

    @RequestMapping("/provider/hystrix")
    Map hystrix();
}
