package com.whr.springcloud.zuulgateway.filter;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Description 限流 Filter
 * @Author wanghongrui
 * @Date Created in 2020/1/1
 */
@Slf4j
@Component
public class CurrentLimitingFilter extends ZuulFilter {

    /**
     * 每秒产生 1000 个许可
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1D);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -4;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        /**
         * 匹配到需要限流的接口才执行限流逻辑
         */
        log.info(request.getRequestURI());
        return "/consumer/12".equalsIgnoreCase(request.getRequestURI());
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        if (RATE_LIMITER.tryAcquire()) {
            // 如果没有取到许可，就设置不让这个请求通过网关路由往下游服务走
            currentContext.setSendZuulResponse(Boolean.FALSE);
            currentContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
            HashMap<Object, Object> map = Maps.newHashMap();
            map.put("msg", "系统繁忙，请稍后再试");
            currentContext.setResponseBody(JSON.toJSONString(map));
        }
        return null;
    }
}
