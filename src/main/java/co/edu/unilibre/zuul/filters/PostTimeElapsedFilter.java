package co.edu.unilibre.zuul.filters;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTimeElapsedFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(PostTimeElapsedFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info("Entrando a post filter");
		
		Long startTime = (Long) request.getAttribute("startTime");
		Long finalTime = System.currentTimeMillis();
		Long timeElapsed = finalTime - startTime;
		
		log.info(String.format("Tiempo transcurrido en segundos %s ms.", timeElapsed));
		
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
