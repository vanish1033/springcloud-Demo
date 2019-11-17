package com.whr.springcloud.zuulgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:whr 2019/11/17
 */
@Component
public class AuthFilter extends ZuulFilter {
    /**
     * filterType 方法的返回值为过滤器的类型，过滤器的类型决定了过滤器在
     * 哪个生命周期执行，pre 表示在路由之前执行过滤器，其他值还有 post、error、
     * route 和 static，当然也可以自定义
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder 方法表示过滤器的执行顺序，当过滤器很多时，我们可以通过
     * 该方法的返回值来指定过滤器的执行顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldFilter 方法用来判断过滤器是否执行，true 表示执行，false 表示不执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * run 方法则表示过滤的具体逻辑，如果请求地址中携带了 token 参数的话，
     * 则认为是合法请求，否则为非法请求，如果是非法请求的话，首先设置
     * ctx.setSendZuulResponse(false); 表示不对该请求进行路由，然后设置响应码
     * 和响应值。这个 run 方法的返回值目前暂时没有任何意义，可以返回任意值
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
//        int i = 1 / 0;
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getParameter("token");
//        if (token == null) {
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            ctx.addZuulResponseHeader("content-type", "text/html;charset=utf-8");
//            ctx.setResponseBody("非法访问");
//        }
        return null;
    }
}
