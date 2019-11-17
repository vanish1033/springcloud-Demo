package com.whr.springcloud.zuulgateway.controller;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:whr 2019/11/17
 */
@RestController
public class ErrorHandlerController implements ErrorController {
    /**
     * 出异常后进入该方法，交由下面的方法处理
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Object error() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulException exception = (ZuulException) ctx.getThrowable();
        return exception.nStatusCode + "--" + exception.getMessage();
    }
}
