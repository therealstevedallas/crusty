<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="row">
<div class="column">
<div class='card'>
<h3>Provide Credentials</h3>
<s:form name="loginForm" theme="xhtml" >
      <s:textfield name="accountForm.name" label="User"  cssClass="input" ></s:textfield>
      <s:password name="accountForm.password" label="Password"   cssClass="input" ></s:password>
      <s:textfield name="accountForm.firstName" label="First Name"   cssClass="input" ></s:textfield>
      <s:textfield name="accountForm.lastName" label="Last Name"   cssClass="input" ></s:textfield>
      <s:checkbox name="accountForm.active" label="Active"   cssClass="input" ></s:checkbox>
      <s:hidden name="accountForm.id"></s:hidden>
      <s:submit action="saveAccount" value="Save"></s:submit>
</s:form>
</div>
</div>
</div>
