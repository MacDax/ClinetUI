package com.spring.boot.configuration;

import javax.inject.Inject;

import org.apache.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.boot.hrservice.integration.HRDelegateProvider;
import com.spring.boot.hrservice.integration.HRServiceHttpClientProvider;
import com.spring.boot.hrservice.integration.HRServiceQualifier;
import com.spring.boot.hrservice.integration.ServicesDataDelegateProvider;
import com.springb.framework.common.core.Delegate;
import com.springb.hrservice.hrservicedelegate.PersonalProfileRequestTO;
import com.springb.hrservice.hrservicedelegate.PersonalProfileResponseTO;
import com.springb.hrservice.hrservicedelegate.ServiceDataRequestTO;
import com.springb.hrservice.hrservicedelegate.ServicesDataResponseTO;

@Configuration
public class DelegatesConfig {

	@Inject HRServiceHttpClientProvider hrHttpClientProvider;
	@Inject HRDelegateProvider hrDelegateProvider;
	@Inject ServicesDataDelegateProvider serviceDataDelegateProvider;
	
	@Bean
	@HRServiceQualifier
	public HttpClient getHrHttpClientProvider() {
		return hrHttpClientProvider.get();
	}
	
	@Bean
	public Delegate<PersonalProfileRequestTO, PersonalProfileResponseTO> getHrDelegateProvider() {
		return hrDelegateProvider.get();
	}
	
	@Bean
	public Delegate<ServiceDataRequestTO, ServicesDataResponseTO> getServicesDataProvider() {
		return serviceDataDelegateProvider.get();
	}
}
