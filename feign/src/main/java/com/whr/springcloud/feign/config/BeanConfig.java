package com.whr.springcloud.feign.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:whr 2019/11/17
 */
@Configuration
public class BeanConfig {

    /**
     * 随机
     *
     * @return
     */
    @Bean
    public IRule iRule() {
        return new RoundRobinRule();
    }
}
