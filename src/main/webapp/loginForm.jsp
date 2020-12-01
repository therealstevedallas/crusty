<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="row">
<div class="column">
<div class='card'>
<h3>Provide Credentials</h3>
<s:form name="loginForm" theme="xhtml" >
      <s:textfield name="loginForm.account" label="User"  cssClass="input" ></s:textfield>
      <s:password name="loginForm.password" label="Password"   cssClass="input" ></s:password>
      <s:submit action="verifyLogin" value="Verify"></s:submit>
</s:form>
</div>
</div>
</div>
