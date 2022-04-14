package com.example.ExchangeRates;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@SpringBootApplication
public class ExchangeRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRatesApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<OncePerRequestFilter> executionTimeLoggingFilter() {
		return new FilterRegistrationBean<OncePerRequestFilter>() {{
			setOrder(OrderedFilter.REQUEST_WRAPPER_FILTER_MAX_ORDER);
			setFilter(new OncePerRequestFilter() {

				@Override
				protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
					StopWatch watch = new StopWatch();
					watch.start();
					request.getSession();
					try {
						filterChain.doFilter(request, response);
					} finally {
						watch.stop();
						log.info("Exchange Rate calculation completed in: {} completed with {} ms", request.getRequestURI(), watch.getTotalTimeMillis());
					}
				}
			});
		}};
	}

}
