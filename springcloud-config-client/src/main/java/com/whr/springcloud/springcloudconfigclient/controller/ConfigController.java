package com.whr.springcloud.springcloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:whr 2019/11/17
 */
@RefreshScope
@RestController
public class ConfigController {

    @Value("${url}")
    private String url;

    @Autowired
    private Environment evn;

    @RequestMapping("/config/url")
    public String configUrl() {
        return this.url;
    }

    @RequestMapping("/config/url1")
    public String configUrl1() {
        return this.evn.getProperty("url");
    }

}
