package com.whr.springcloud.feignfallbackfactory.controllrt;

import com.whr.springcloud.feignfallbackfactory.service.FeignService;
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
        return feignService.test2("汪弘睿");
    }

    @ResponseBody
    @RequestMapping("/feign/test2")
    public Map feignTest2() {
        return feignService.hystrix();
    }
}
