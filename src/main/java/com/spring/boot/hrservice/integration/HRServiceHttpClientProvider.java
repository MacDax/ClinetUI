package com.spring.boot.hrservice.integration;

import javax.annotation.ManagedBean;
import javax.inject.Provider;

import org.apache.http.client.HttpClient;

import com.springb.framework.core.connector.HttpCoreConnectionFactory;

@ManagedBean("hserservice-delegate-HRServiceHttpClientProvider")
public class HRServiceHttpClientProvider implements Provider<HttpClient> {

	@Override
	public HttpClient get() {
		HttpClient httpClient = new HttpCoreConnectionFactory(5000, 3000, 2, "clientId", "clientSecret").getCustomHttpCoreClient();
		return httpClient;
	}

}
