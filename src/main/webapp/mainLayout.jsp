<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="layout.css">
    <link rel="stylesheet" type="text/css" href="table.css">
    <link rel="stylesheet" type="text/css" href="jquery-ui.min.css">

    <script src="jquery-3.4.1.min.js"></script>
    <script src ="jquery-ui.min.js"></script>

    <script>(function(e,t,n){var r=e.querySelectorAll("html")[0];r.className=r.className.replace(/(^|\s)no-js(\s|$)/,"$1js$2")})(document,window,0);</script>
    <title>${param.title}</title>
  </head>
    <body>
        <div class="header"><h2 >Spectrum&trade; Configuration Migration Tool</h2>
           <img src="./images/cropped-shawLogo_llcWhite.png"/>
        </div>
        <s:url var="exportUrl" action="export">
            <s:param name="tabName">tab-1</s:param>
        </s:url>
        <s:url var="importUrl" action="import">
            <s:param name="tabName">tab-2</s:param>
        </s:url>
        <s:url var="helpUrl" action="help">
            <s:param name="tabName">tab-3</s:param>
        </s:url>
        <button id="tab-1" class="tablink" onclick="openPage('Export', this, '${exportUrl}')">Export</button>
        <button id="tab-2" class="tablink" onclick="openPage('Import', this, '${importUrl}')">Import</button>
        <button id="tab-3" class="tablink" onclick="openPage('About', this, '${helpUrl}')">About</button>

        <div id="content" class="content">
           <div class="title"><h2>${param.title}</h2></div>
           <jsp:include page="${param.pg}.jsp" flush="true"/>
        </div>
        <jsp:include page="footerLayout.jsp" />
 <script>

 $(document).ready(function(){
   var tab = '#${param.tabName}';
   if (tab != '#')
      $('#${param.tabName}').css('text-decoration', 'underline overline solid');
 });
 </script>

<script>
 function openPage(pageName,elmnt,url) {
   var i, tabcontent, tablinks;
   location.href = url;
   tablinks = document.getElementsByClassName("tablink");
   for (i = 0; i < tablinks.length; i++) {
     tablinks[i].style.backgroundColor = "";
   }
 }
 </script>
 </body>
</html>