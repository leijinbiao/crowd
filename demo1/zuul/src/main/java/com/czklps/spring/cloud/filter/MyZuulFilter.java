package com.czklps.spring.cloud.filter;

import com.netflix.eureka.resources.CurrentRequestVersion;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyZuulFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(MyZuulFilter.class);

    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();

        String signal = request.getParameter("signal");

        return "hello".equals(signal);
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("当前请求正在进行过滤！ run() 方法执行了！");
        return null;
    }
}
