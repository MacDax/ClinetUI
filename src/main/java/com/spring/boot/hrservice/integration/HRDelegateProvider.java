package com.spring.boot.hrservice.integration;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.http.client.HttpClient;
import org.apache.http.params.HttpParams;

import com.springb.framework.common.core.Delegate;
import com.springb.framework.common.core.RESTHttpProtocolHandler;
import com.springb.framework.common.core.RestProtocolContextStatic;
import com.springb.hrservice.hrservicedelegate.PersonalProfileRequestBuilder;
import com.springb.hrservice.hrservicedelegate.PersonalProfileRequestTO;
import com.springb.hrservice.hrservicedelegate.PersonalProfileResponseBuilder;
import com.springb.hrservice.hrservicedelegate.PersonalProfileResponseTO;


@ManagedBean
public class HRDelegateProvider implements Provider<Delegate<PersonalProfileRequestTO, PersonalProfileResponseTO>>{

	private HttpClient hrHttpClient;
	private final RESTHttpProtocolHandler restHttpProtocolHandler;
	private RestProtocolContextStatic restProtocolContextStatic;
	private final Delegate<PersonalProfileRequestTO, PersonalProfileResponseTO> delegate;
	private final PersonalProfileResponseBuilder responseBuilder;
	private final PersonalProfileRequestBuilder requestBuilder;
	
	@Inject
	public HRDelegateProvider(@HRServiceQualifier HttpClient hrServiceHttpClient) {
		this.hrHttpClient = hrServiceHttpClient;
		restProtocolContextStatic = createRestProtocolContextStatic();
		restHttpProtocolHandler = createRESTHTTPProtocalHandler();
		requestBuilder = createRequestBuilder();
		responseBuilder = createResponseBuilder();
		delegate = createDelegate();
	}
	
	private Delegate<PersonalProfileRequestTO, PersonalProfileResponseTO> createDelegate() {
		return new Delegate<PersonalProfileRequestTO, PersonalProfileResponseTO>(requestBuilder, responseBuilder, restHttpProtocolHandler);
	}

	private PersonalProfileResponseBuilder createResponseBuilder() {
		return new PersonalProfileResponseBuilder();
	}

	private PersonalProfileRequestBuilder createRequestBuilder() {
		return new PersonalProfileRequestBuilder(restProtocolContextStatic);
	}

	private RestProtocolContextStatic createRestProtocolContextStatic() {
		RestProtocolContextStatic restProtocolContextStatic = new RestProtocolContextStatic();
		restProtocolContextStatic.setHostId("hostId");
		return restProtocolContextStatic;
	}

	private RESTHttpProtocolHandler createRESTHTTPProtocalHandler() {
		String endPointUrl = "http://localhost:9093/personalservice/v1";
		HttpParams httpParams = hrHttpClient.getParams();
		RESTHttpProtocolHandler protocolHandler = new RESTHttpProtocolHandler(endPointUrl, hrHttpClient, httpParams);
		return protocolHandler;
	}

	@Override
	public Delegate<PersonalProfileRequestTO, PersonalProfileResponseTO> get() {
		return delegate;
	}

}
