<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="layout.css">
    <link rel="stylesheet" type="text/css" href="menu.css">
    <link rel="stylesheet" type="text/css" href="table.css">
    <link rel="stylesheet" type="text/css" href="jquery-ui.min.css">

    <script src="jquery-3.4.1.min.js"></script>
    <script src ="jquery-ui.min.js"></script>
    <script>(function(e,t,n){var r=e.querySelectorAll("html")[0];r.className=r.className.replace(/(^|\s)no-js(\s|$)/,"$1js$2")})(document,window,0);</script>
    <title>${param.title}</title>
  </head>
    <body>
        <div class="header">
          <h2>${strings.get('app.title')}</h2><br>
          ${strings.get('app.user')}: ${account.name}
        </div>

       <div class="header-nav">
         <s:url var="accountsUrl" action="listAccounts"></s:url>
         <s:url var="aboutUrl" action="about"></s:url>
         <s:url var="logOutUrl" action="logout"></s:url>
         <a href="#" id="menu-icon"></a>
         <nav class="nav-menu">
           <ul>
             <li><a href="${accountsUrl}">{strings.get('menu.accounts')}</a></li>
             <li><a href="${aboutUrl}">${strings.get('menu.about')}</a></li>
             <li><a href="${logOutUrl}">${strings.get('menu.logout')}</a></li>
           </ul>
         </nav>
       </div>
        <div id="content" class="content">
           <div class="title"><h2>${param.title}</h2></div>
           <jsp:include page="${param.pg}.jsp" flush="true"/>
        </div>
        <jsp:include page="footerLayout.jsp" />

 <script>

$(document).ready(function() {
	$('#menu-icon').click(function() {
		$('.nav-menu ul').toggleClass('visible');
	});

});
 </script>
 </body>
</html>