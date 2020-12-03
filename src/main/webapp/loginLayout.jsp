<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="layout.css">
    <link rel="stylesheet" type="text/css" href="jquery-ui.min.css">
    <script>(function(e,t,n){var r=e.querySelectorAll("html")[0];r.className=r.className.replace(/(^|\s)no-js(\s|$)/,"$1js$2")})(document,window,0);</script>
    <title>${strings.get('login.title')}</title>
  </head>
  <body>
        <div class="header">
        <h2>${strings.get('app.title')}</h2>
        </div>
        <div id="content" class="content">
           <jsp:include page="${param.pg}.jsp" flush="true"/>
        </div>
        <jsp:include page="footerLayout.jsp" />
 </body>
</html>