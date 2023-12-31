package com.booking.filter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;

import com.googlecode.objectify.ObjectifyFilter;

@Order(1)
public class ObjectifyWebFilter extends ObjectifyFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

}