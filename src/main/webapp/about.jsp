<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="row"> <div class="column"> <div class="card">
       <s:iterator value="versions">
          <s:property value="versionString"/>
       </s:iterator>
</div></div></div>
