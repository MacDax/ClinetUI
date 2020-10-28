package com.spring.boot.hrservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springb.framework.common.core.CommonContextDynamic;
import com.springb.framework.common.core.Delegate;
import com.springb.framework.common.core.MessageStatus;
import com.springb.framework.common.core.RESTHttpProtocolHandler.HTTP_ENTITY_TYPE;
import com.springb.framework.common.core.RESTHttpProtocolHandler.HTTP_METHOD_TYPE;
import com.springb.hrservice.hrservicedelegate.PersonalProfileRequestTO;
import com.springb.hrservice.hrservicedelegate.PersonalProfileResponseTO;

@ManagedBean("HRPersonalService-v1")
public class HRPersonalService {

	@Inject
	private Delegate<PersonalProfileRequestTO, PersonalProfileResponseTO> getHRPersonalDelegate;
	private final Logger logger = LoggerFactory.getLogger(HRPersonalService.class);
	
	public List<HRPersonalProfile> getHRPersonsList() {
		String hrServiceUrl = "/persons";
		PersonalProfileRequestTO request = new PersonalProfileRequestTO();
		request.setRequestURI(hrServiceUrl);
		request.setRestMethodType(HTTP_METHOD_TYPE.GET);
		CommonContextDynamic commonContextDynamic = getCommonContextDynamic();
		request.setApplicationHostContext(commonContextDynamic);
		request.setContentBodyType(HTTP_ENTITY_TYPE.CONTENT_BODY);
		try {
			PersonalProfileResponseTO response = getHRPersonalDelegate.execute(request);
			logger.info("PersonalProfileResponse received : " + response.toString());
			List<HRPersonalProfile> list = createList(response);
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			MessageStatus messageStatus = new MessageStatus();
			messageStatus.setStatusCode(400);
			PersonalProfileResponseTO responseMessage = new PersonalProfileResponseTO();
			//responseMessage.setResponseContextTo(responseContextTo);
			return null;
		}
	}

	private List<HRPersonalProfile> createList(PersonalProfileResponseTO response) {
		 List<HRPersonalProfile> list = new ArrayList<>();
		 List<com.springb.hrservice.hrservicedelegate.HRPersonalProfile> hrList = response.getPersonalProfileList();
		 for(com.springb.hrservice.hrservicedelegate.HRPersonalProfile hr : hrList) {
			 HRPersonalProfile person = new HRPersonalProfile();
			 person.setAddress(hr.getAddress());
			 person.setBirthdate(hr.getBirthdate());
			 person.setFname(hr.getFname());
			 person.setLname(hr.getLname());
			 person.setId(hr.getId());
			 Collection<com.springb.hrservice.hrservicedelegate.OccupationServices> services = hr.getServices();
			 List<OccupationServices> serviceList = new ArrayList<>();
			 /*Optional.ofNullable(services)
			 	.orElse(Collections.emptySet()).stream().filter(occupation -> occupation!=null)
			 	.forEach(occupation -> {
			 		OccupationServices os = new OccupationServices();
			 		os.setServiceId(occupation.getId());
			 		String service = occupation.getServiceName();
			 		serviceList.add(e)
			 	});*/
			 person.setServices(serviceList);
			 list.add(person);
		 }
		return list;
	}

	private CommonContextDynamic getCommonContextDynamic() {
		CommonContextDynamic commonContextDynamic = new CommonContextDynamic();
		commonContextDynamic.setRequestId(UUID.randomUUID().toString());
		commonContextDynamic.setSessionId(UUID.randomUUID().toString());
		commonContextDynamic.setCreationTimeStamp(new GregorianCalendar());
		return commonContextDynamic;
	}
}