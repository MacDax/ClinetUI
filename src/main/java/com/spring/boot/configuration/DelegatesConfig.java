package com.spring.boot.configuration;

import javax.inject.Inject;

import org.apache.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.boot.hrservice.integration.HRDelegateProvider;
import com.spring.boot.hrservice.integration.HRServiceHttpClientProvider;
import com.spring.boot.hrservice.integration.HRServiceQualifier;
import com.springb.framework.common.core.Delegate;
import com.springb.hrservice.hrservicedelegate.PersonalProfileRequestTO;
import com.springb.hrservice.hrservicedelegate.PersonalProfileResponseTO;

@Configuration
public class DelegatesConfig {

	@Inject HRServiceHttpClientProvider hrHttpClientProvider;
	@Inject HRDelegateProvider hrDelegateProvider;
	
	@Bean
	@HRServiceQualifier
	public HttpClient getHrHttpClientProvider() {
		return hrHttpClientProvider.get();
	}
	
	@Bean
	public Delegate<PersonalProfileRequestTO, PersonalProfileResponseTO> getHrDelegateProvider() {
		return hrDelegateProvider.get();
	}
}
