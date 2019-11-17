package com.whr.springcloud.consumer.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author:whr 2019/11/15
 */
@Configuration
public class BeanConfig {

    /**
     * http请求模板工具
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 轮询（默认）
     *
     * @return
     */
    @Bean
    public IRule iRule() {
        return new RoundRobinRule();
    }


}
