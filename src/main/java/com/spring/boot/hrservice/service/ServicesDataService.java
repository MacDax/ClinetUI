package com.spring.boot.hrservice.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springb.framework.common.core.CommonContextDynamic;
import com.springb.framework.common.core.Delegate;
import com.springb.framework.common.core.RESTHttpProtocolHandler.HTTP_ENTITY_TYPE;
import com.springb.framework.common.core.RESTHttpProtocolHandler.HTTP_METHOD_TYPE;
import com.springb.hrservice.hrservicedelegate.ServiceDataRequestTO;
import com.springb.hrservice.hrservicedelegate.ServicesDataResponseTO;

@ManagedBean("SevicesDataService-v1")
public class ServicesDataService {

	private final Logger logger = LoggerFactory.getLogger(HRPersonalService.class);
	@Inject
	private Delegate<ServiceDataRequestTO, ServicesDataResponseTO> serviceDataDelegate;
	private static final String servicesUrl = "/persons/services";
	
	public List<OccupationServices> getServicesData() throws Exception {
		ServiceDataRequestTO request = new ServiceDataRequestTO();
		request.setRequestURI(servicesUrl);
		request.setRestMethodType(HTTP_METHOD_TYPE.GET);
		request.setContentBodyType(HTTP_ENTITY_TYPE.CONTENT_BODY);
		CommonContextDynamic commonContextDynamic = getCommonContextDynamic();
		request.setApplicationHostContext(commonContextDynamic);
		ServicesDataResponseTO response = serviceDataDelegate.execute(request);
		List<OccupationServices> occServiceList = populateServicesList(response);
		return occServiceList;
	}
	
	private List<OccupationServices> populateServicesList(ServicesDataResponseTO response) {
		 List<OccupationServices> servicesList = new ArrayList<>();
		 List<com.springb.hrservice.hrservicedelegate.OccupationServices> dbservicesList = response.getServicesList();
		 dbservicesList.stream().forEach(dbservice -> {
			 OccupationServices service = new OccupationServices();
			 service.setServiceId(dbservice.getServiceId());
			 service.setServiceName(dbservice.getServiceName());
			 logger.info("service name : " + dbservice.getServiceName());
			 service.setServiceType(dbservice.getServiceType());
			 servicesList.add(service);
		 });
		 return servicesList;
	}

	private CommonContextDynamic getCommonContextDynamic() {
		CommonContextDynamic commonContextDynamic = new CommonContextDynamic();
		commonContextDynamic.setRequestId(UUID.randomUUID().toString());
		commonContextDynamic.setSessionId(UUID.randomUUID().toString());
		commonContextDynamic.setCreationTimeStamp(new GregorianCalendar());
		return commonContextDynamic;
	}
}
