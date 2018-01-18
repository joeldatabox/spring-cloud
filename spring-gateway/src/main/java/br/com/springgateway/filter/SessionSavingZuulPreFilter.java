package br.com.springgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionSavingZuulPreFilter extends ZuulFilter {

    // private static final Log log = LogFactory.getLog(SessionSavingZuulPreFilter.class);
    private final String tokenHeader;

    public SessionSavingZuulPreFilter(@Value("${springboot.security.jwt.controller.tokenHeader}") final String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext
                .getCurrentContext()
                .addZuulRequestHeader(
                        this.tokenHeader, (
                                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
                        ).getRequest().getHeader(this.tokenHeader));
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
