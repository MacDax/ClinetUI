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
        POST Data 
    </div>
    <div id="postPersonData">
    	<form:form action="http://localhost:8090/myschool/hrpersons" method="post" modelAttribure="hRPersonalProfile">
    		<p>
    		<label>Enter Data</label>
    		<input type="text" name="fname">
    		<input type="text" name="lname">
    		<!-- <input type="date" name="birthDate">-->
    		<input type="SUBMIT" value="Post Data">
    		</p>
    	</form:form>
    	<div>
    	<c:out value=	"${successMessage}" ></c:out>
    	<c:out value=	"${failureMessage}" ></c:out>
    	</div>
    
    </div>
    
</body>