package com.whr.springcloud.consumer.hystrixerror;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 自定义服务熔断方法
 *
 * @author:whr 2019/11/16
 */
public class MyHystrixCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    public MyHystrixCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    /**
     * 调用远程服务的方法
     *
     * @return java.lang.String
     * @throws Exception
     */
    @Override
    protected String run() {
        restTemplate.getForObject("http://PROVIDER/provider/hystrix", Map.class);
        return "success";
    }

    /**
     * 服务熔断回调方法, 返回值必须兼容run方法返回值
     *
     * @return java.lang.String
     */
    @Override
    public String getFallback() {
        Throwable throwable = super.getExecutionException();
        System.out.println(throwable.getMessage());
        return "error1";
    }
}
