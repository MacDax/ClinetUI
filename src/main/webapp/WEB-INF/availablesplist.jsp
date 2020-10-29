<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of ServiceProviders</title>
</head>
<body>

    <h3 style="color: red;">My school</h3>
    <div>
        Get Data 
    </div>
    <p><label>HRPersonals List</label></p>
    <%--  <a href="${contextpath}/myschool/hrpersons">List available Service Providers</a> --%>
    <c:forEach var="person" items="${personalProfileResponse}">
    	<c:out value="${person.id}"></c:out>
    	<c:out value="${person.fname}"></c:out>
    	<c:out value="${person.lname}"></c:out>
    	<c:forEach var="services" items="${person.services}">
    		<c:out value="${person.services.serviceName}"></c:out>
    	</c:forEach>
    </c:forEach>
    
</body>

</html>