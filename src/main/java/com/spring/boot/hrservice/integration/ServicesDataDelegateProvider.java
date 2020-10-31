package com.spring.boot.hrservice.integration;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.http.client.HttpClient;
import org.apache.http.params.HttpParams;

import com.springb.framework.common.core.Delegate;
import com.springb.framework.common.core.RESTHttpProtocolHandler;
import com.springb.framework.common.core.RestProtocolContextStatic;
import com.springb.hrservice.hrservicedelegate.ServiceDataRequestBuilder;
import com.springb.hrservice.hrservicedelegate.ServiceDataRequestTO;
import com.springb.hrservice.hrservicedelegate.ServiceDataResponseBuilder;
import com.springb.hrservice.hrservicedelegate.ServicesDataResponseTO;

@ManagedBean
public class ServicesDataDelegateProvider implements Provider< Delegate<ServiceDataRequestTO, ServicesDataResponseTO>>{

	private HttpClient hrHttpClient;
	private final RESTHttpProtocolHandler restHttpProtocolHandler;
	private RestProtocolContextStatic restProtocolContextStatic;
	private final Delegate<ServiceDataRequestTO, ServicesDataResponseTO> delegate;
	private final ServiceDataResponseBuilder responseBuilder;
	private final ServiceDataRequestBuilder requestBuilder;
	
	@Inject
	public ServicesDataDelegateProvider(@HRServiceQualifier HttpClient hrHttpClient) {
		this.hrHttpClient = hrHttpClient;
		restHttpProtocolHandler = createRESTHttpProtocol();
		restProtocolContextStatic = createRestProtocolContextStatic();
		requestBuilder = createRequestBuilder();
		responseBuilder = createResponseBuilder();
		delegate = createDelegate();
	}
	
	private RestProtocolContextStatic createRestProtocolContextStatic() {
		RestProtocolContextStatic restProtocolContextStatic = new RestProtocolContextStatic();
		restProtocolContextStatic.setHostId("hostId");
		return restProtocolContextStatic;
	}

	private ServiceDataRequestBuilder createRequestBuilder() {
		return new ServiceDataRequestBuilder(restProtocolContextStatic);
	}

	private ServiceDataResponseBuilder createResponseBuilder() {
		return new ServiceDataResponseBuilder();
	}

	private Delegate<ServiceDataRequestTO, ServicesDataResponseTO> createDelegate() {
		return  new Delegate<ServiceDataRequestTO, ServicesDataResponseTO>( requestBuilder, responseBuilder, restHttpProtocolHandler);
	}

	private RESTHttpProtocolHandler createRESTHttpProtocol() {
		String endPointUrl = "http://localhost:9093/personalservice/v1";
		HttpParams httpParams = hrHttpClient.getParams();
		RESTHttpProtocolHandler protocolHandler = new RESTHttpProtocolHandler(endPointUrl, hrHttpClient, httpParams);
		return protocolHandler;
	}

	@Override
	public Delegate<ServiceDataRequestTO, ServicesDataResponseTO> get() {
		System.out.println("service data delegate created!!");
		return delegate;
	}



}
