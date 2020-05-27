package com.creditagricole.tax.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class H2ConsoleConfiguration.
 */
@Configuration
public class H2ConsoleConfiguration {

	/**
	 * H2 servlet registration.
	 *
	 * @return the servlet registration bean
	 */
	@Bean
	public ServletRegistrationBean<WebServlet> h2servletRegistration() {
		ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<>(new WebServlet());
		registrationBean.addUrlMappings("/h2console/*");
		return registrationBean;
	}

}
