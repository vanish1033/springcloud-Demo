package com.whr.springcloud.feignfallbackfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringCloudApplication
public class FeignFallbackfactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignFallbackfactoryApplication.class, args);
    }

}
