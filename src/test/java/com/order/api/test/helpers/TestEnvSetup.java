package com.order.api.test.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.appengine.api.urlfetch.HTTPMethod;

public class TestEnvSetup extends ObjectifySetup {

	@Autowired
	protected MockMvc mockMvc;

	protected ResultActions setUpGetRequest(String orgId, String requestUri, String query) throws Exception {
		return mockMvc.perform(buildRequest(HTTPMethod.GET.toString(), orgId, requestUri, query, null));
	}

	private MockHttpServletRequestBuilder buildRequest(String httpMethod, String orgId, String requestUri, String query,
			String jsonbody) {
		MockHttpServletRequestBuilder builder = null;
		if (httpMethod.equals(HTTPMethod.POST.toString())) {
			builder = MockMvcRequestBuilders.post(requestUri);
		} else if (httpMethod.equals(HTTPMethod.PUT.toString())) {
			builder = MockMvcRequestBuilders.put(requestUri);
		} else if (httpMethod.equals(HTTPMethod.GET.toString())) {
			if (null == query) {
				query = "";
			}
			builder = MockMvcRequestBuilders.get(requestUri + query);
		} else if (httpMethod.equals(HTTPMethod.DELETE.toString())) {
			builder = MockMvcRequestBuilders.delete(requestUri);
		}

		if (orgId != null) {
			builder.header("orgId", orgId);

		}

		return builder;
	}
}
