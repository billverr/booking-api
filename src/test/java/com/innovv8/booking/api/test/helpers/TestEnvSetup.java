package com.innovv8.booking.api.test.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class TestEnvSetup extends ObjectifySetup {

	@Autowired
	protected MockMvc mockMvc;

	protected ResultActions setUpGetRequest(String orgId, String requestUri, String query) throws Exception {
		return mockMvc.perform(buildRequest(HttpMethod.GET.toString(), orgId, requestUri, query, null));
	}

	private MockHttpServletRequestBuilder buildRequest(String httpMethod, String orgId, String requestUri, String query,
			String jsonbody) {
		MockHttpServletRequestBuilder builder = null;
		if (httpMethod.equals(HttpMethod.POST.toString())) {
			builder = MockMvcRequestBuilders.post(requestUri);
		} else if (httpMethod.equals(HttpMethod.PUT.toString())) {
			builder = MockMvcRequestBuilders.put(requestUri);
		} else if (httpMethod.equals(HttpMethod.GET.toString())) {
			if (null == query) {
				query = "";
			}
			builder = MockMvcRequestBuilders.get(requestUri + query);
		} else if (httpMethod.equals(HttpMethod.DELETE.toString())) {
			builder = MockMvcRequestBuilders.delete(requestUri);
		}

		if (orgId != null) {
			builder.header("orgId", orgId);

		}

		return builder;
	}
}
