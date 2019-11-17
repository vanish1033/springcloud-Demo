package com.whr.springcloud.feignfallbackfactory.fallbackfactory;

import com.whr.springcloud.feignfallbackfactory.service.FeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:whr 2019/11/17
 */
@Component
public class MyFallbackFactory implements FallbackFactory<FeignService> {

    @Override
    public FeignService create(Throwable throwable) {
        return new FeignService() {
            @Override
            public Object test2(String name) {
                System.out.println(throwable);
                return "服务熔断了";
            }

            @Override
            public Map hystrix() {
                System.out.println(throwable);
                HashMap<Object, Object> map = new HashMap<>(16);
                map.put("msg", "服务熔断了");
                return map;
            }
        };
    }
}
