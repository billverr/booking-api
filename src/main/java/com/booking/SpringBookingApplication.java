package com.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.booking.filter.ObjectifyWebFilter;
import com.booking.objectify.model.Reservation;
import com.booking.objectify.model.Event;
import com.googlecode.objectify.ObjectifyService;

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