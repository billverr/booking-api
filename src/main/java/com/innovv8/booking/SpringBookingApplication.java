package com.innovv8.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.googlecode.objectify.ObjectifyService;
import com.innovv8.booking.filter.ObjectifyWebFilter;
import com.innovv8.booking.objectify.model.Event;
import com.innovv8.booking.objectify.model.Reservation;

@SpringBootApplication
public class SpringBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBookingApplication.class);
	}

	@Bean
	public FilterRegistrationBean<ObjectifyWebFilter> objectifyFilter() {
		ObjectifyService.init();
		ObjectifyService.register(Reservation.class);
		ObjectifyService.register(Event.class);

		FilterRegistrationBean<ObjectifyWebFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new ObjectifyWebFilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1);
		return registrationBean;
	}

}