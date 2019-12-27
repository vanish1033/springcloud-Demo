package com.whr.springcloud.consumer.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.whr.springcloud.consumer.hystrixerror.MyHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author:whr 2019/11/15
 */
@Controller
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping("/get")
    public Map getMap() {
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity("http://PROVIDER/provider1/get/123", Map.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getStatusCodeValue());
        System.out.println(responseEntity.getHeaders());
        return responseEntity.getBody();
    }

    @ResponseBody
    @RequestMapping("/post/{value}")
    public Map postMap(@PathVariable String value) {
        LinkedMultiValueMap<Object, Object> request = new LinkedMultiValueMap<>();
        request.add("name", value);
        Map map = restTemplate.postForObject("http://PROVIDER/provider1/post",
                request,
                Map.class);
        System.out.println(map);
        return map;
    }

    @ResponseBody
    @RequestMapping("/put/{value}")
    public void put(@PathVariable String value) {
        LinkedMultiValueMap<Object, Object> request = new LinkedMultiValueMap<>();
        request.add("name", value);
        restTemplate.put("http://PROVIDER/provider1/put", request);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public void delete() {
        HashMap<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("name", "张翠山");
        restTemplate.delete("http://PROVIDER/provider1/delete?name={name}", uriVariables);
    }

    /**
     * 测试服务熔断方法
     *
     * @return java.lang.String
     */
//    @ResponseBody
    @HystrixCommand(fallbackMethod = "getFallback")
    @RequestMapping("/hystrix")
    public String hystrix() {
        System.out.println("Hystrix");
//        int i = 10 / 0;
        restTemplate.getForObject("http://PROVIDER/provider/hystrix", Map.class);
        return "success";
    }

    public String getFallback(Throwable throwable) {
        System.out.println("异常：" + throwable);
        return "error1";
    }

    //    @ResponseBody
    @RequestMapping("/hystrix2")
    public String hystrix2() throws ExecutionException, InterruptedException {
        MyHystrixCommand myHystrixCommand = new MyHystrixCommand(
                com.netflix.hystrix.HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);

//        return myHystrixCommand.execute();  // 同步
        Future<String> future = myHystrixCommand.queue(); // 异步
        return future.get();
    }

}
