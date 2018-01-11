package br.com.springgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class SessionSavingZuulPreFilter extends ZuulFilter {

    private static final Log log = LogFactory.getLog(SessionSavingZuulPreFilter.class);

    public SessionSavingZuulPreFilter() {
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        //HttpSession httpSession = context.getRequest().getSession();
        //Session session = repository.getSession(httpSession.getId());
        final String token = context.getRequest().getHeader("Authentication");
        context.addZuulRequestHeader("Authentication", token);

        log.info("ZuulPreFilter token proxy: {} " + token);
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
