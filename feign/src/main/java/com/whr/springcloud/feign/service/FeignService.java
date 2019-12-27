package com.whr.springcloud.feign.service;

import com.whr.springcloud.feign.fallback.MyFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author:whr 2019/11/17
 */
@FeignClient(name = "PROVIDER", fallback = MyFallback.class)
public interface FeignService {
    /**
     * @param name
     * @return
     */
    @PostMapping(value = "/provider1/post")
    Object test2(@RequestParam String name);

    /**
     * @return
     * @throws InterruptedException
     */
    @RequestMapping(value = "/provider/hystrix")
    Map hystrix() throws InterruptedException;

}
