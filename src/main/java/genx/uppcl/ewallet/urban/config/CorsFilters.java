package genx.uppcl.ewallet.urban.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Component
public class CorsFilters implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CorsFilters.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("Initializing CORSFilter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("inside do filter");
		HttpServletRequest requestToUse = (HttpServletRequest) request;

		LOGGER.info("req to use " + requestToUse);
		HttpServletResponse responseToUse = (HttpServletResponse) response;

		//LOGGER.info("Adding CORS Headers ........................");
		responseToUse.setHeader("Access-Control-Allow-Origin", requestToUse.getHeader("Origin"));
		responseToUse.setHeader("Access-Control-Allow-Credentials", "true");
		responseToUse.setHeader("Access-Control-Allow-Methods", "POST, GET");
		responseToUse.setHeader("Access-Control-Max-Age", "600");
		responseToUse.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
		responseToUse.addHeader("Access-Control-Expose-Headers", "xsrf-token");
		chain.doFilter(requestToUse, responseToUse);
		LOGGER.info("exiting the do filter ");
	}

	@Override
	public void destroy() {

	}
	@Bean
	public FilterRegistrationBean corsFilterRegistrationBean() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.applyPermitDefaultValues();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setExposedHeaders(Arrays.asList("content-length"));
		config.setMaxAge(600L);
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

}
