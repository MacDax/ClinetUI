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
import com.springb.framework.common.core.RESTHttpProtocolHandler.REST_PROTOCOL_DATA_ELEMENTS;
import com.springb.hrservice.hrservicedelegate.Person;
import com.springb.hrservice.hrservicedelegate.PersonalProfileRequestTO;
import com.springb.hrservice.hrservicedelegate.PersonalProfileResponseTO;

@ManagedBean("HRPersonalService-v1")
public class HRPersonalService {

	@Inject
	private Delegate<PersonalProfileRequestTO, PersonalProfileResponseTO> getHRPersonalDelegate;
	private final Logger logger = LoggerFactory.getLogger(HRPersonalService.class);
	private final static String hrServiceUrl = "/persons";
	
	public boolean saveHRPersonData(HRPersonalProfile person) {
		boolean result = false;
		PersonalProfileRequestTO request = new PersonalProfileRequestTO();
		request.setRequestURI(hrServiceUrl);
		request.setRestMethodType(HTTP_METHOD_TYPE.POST);
		request.setApplicationHostContext(getCommonContextDynamic());
		//request.setContentBodyType(HTTP_ENTITY_TYPE.CONTENT_BODY); 
		//request.setHeaderContentType(REST_PROTOCOL_DATA_ELEMENTS.CONTENT_HEADER_TYPE);
		Person personData = new Person(); 
		personData.setFname(person.getFname());
		personData.setLname(person.getLname());
		personData.setBirthdate(person.getBirthdate());
		List<Person> personDataList = new ArrayList<>();
		personDataList.add(personData);
		request.setPersonData(personDataList);;
		logger.info("person data to save : " + request.toString());
		try {
		PersonalProfileResponseTO response = getHRPersonalDelegate.execute(request);
		if(null != response) {
			logger.info("response received: Person data saved in db" + response.toString());
			result = true;
		}
		}catch(Exception ex) {
			logger.info("ex received when saving person data");
			ex.printStackTrace();
		}
		return result;
	}
	
	public List<HRPersonalProfile> getHRPersonsList() {
		
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
