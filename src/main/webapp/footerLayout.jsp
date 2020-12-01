<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="footer">
    <s:if test="hasActionErrors()">
      <div class="errors">
        <s:actionerror/>
      </div>
    </s:if>
    <s:if test="hasActionMessages()">
      <div class="messages">
         <s:actionmessage/>
      </div>
    </s:if>
</div>
