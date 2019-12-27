package com.whr.springcloud.feign.fallback;

import com.google.common.collect.Maps;
import com.whr.springcloud.feign.service.FeignService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:whr 2019/11/17
 */
@Component
public class MyFallback implements FeignService {

    @Override
    public Object test2(String name) {
        return "服务熔断了";
    }

    @Override
    public Map hystrix() {
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("msg", "服务熔断了");
        return map;
    }

    @Override
    public Map test() {
        HashMap<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("msg", "服务熔断了");
        return hashMap;
    }

}
