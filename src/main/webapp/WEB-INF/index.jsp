<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Data</title>
</head>
<body>

    <h3 style="color: red;">My school</h3>
    <div>
        Get Data 
    </div>
    <p><label>HRPersonal List</label></p>
     <a href="${contextpath}/myschool/hrpersons">List available Service Providers</a>
     
     

    <div id="postData">
    <p><h3> POST DATA OF Service Provider</h3>
    <a href="${contextpath}/myschool/addhrperson">Add Service Provider</a> 
   
  
    	<div>
    	<c:out value="${successMessage}"></c:out>
    	<c:out value="${failureMessage}"> </c:out>
    	</div> 
    
    </div>
   
   <div>
      Services Data List
      
          <c:forEach var="service" items="${servicesDataResponse}">
    	<c:out value="${servic.id}"></c:out>
    	<c:out value="${service.serviceName}"></c:out>
    	<c:out value="${service.serviceType}"></c:out>
    	<%-- <c:forEach var="services" items="${person.services}">
    		<c:out value="${person.services.serviceName}"></c:out>
    	</c:forEach> --%>
    </c:forEach>
    </div>
</body>

</html>