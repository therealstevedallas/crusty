<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="pg" value="loginForm"/>
<jsp:include page="mainLayout.jsp">
    <jsp:param name="pg" value="${pg}"/>
    <jsp:param name="title" value="${strings.get('login.title')}"/>
</jsp:include>
