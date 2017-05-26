<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
  <a href="?lang=en">English </a> | <a href="?lang=ru">Russian </a>
  <h3> <spring:message code="welcome.spring.app"/></h3>
  <div>
  		<h2>Login with Username and Password</h2>

  		<c:if test="${not empty error}">
  			<div class="error">${error}</div>
  		</c:if>
  		<c:if test="${not empty msg}">
  			<div class="msg">${msg}</div>
  		</c:if>
        <c:url var="loginUrl" value="/login" />
  		<form name='loginForm' action="${loginUrl}" method="post">
  		<table>
  			<tr>
  				<td><spring:message code="welcome.spring.user"/>:</td>
  				<td><input type='text' name='username'></td>
  			</tr>
  			<tr>
  				<td><spring:message code="welcome.spring.password"/>:</td>
  				<td><input type='password' name='password' /></td>
  			</tr>

  			<tr>
  				<td colspan='2'><input name="submit" type="submit"
  				  value=<spring:message code="welcome.spring.enter"/> /></td>
  			</tr>
  		  </table>
            <input type="hidden" name="${_csrf.parameterName}"
              			value="${_csrf.token}" />


  		</form>
  	</div>
</body>
</html>
