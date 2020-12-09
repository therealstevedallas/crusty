<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class='ui-widget-content card'>
<form id="login" name="loginForm" action="/crusty/login.action" method="post">
<table>
  <caption class="ui-widget-header">${strings.get('login.dialog.title')}</caption>
  <tr>
    <td class="form-label-left">
      <label for="login_loginForm_account">${strings.get('login.user')}</label>
    </td>
    <td>
      <input class="ui-widget" type="text" name="loginForm.account" value="" id="login_loginForm_account"/>
    </td>
  </tr>
  <tr>
    <td class="form-label-left">
    <label for="login_loginForm_password" class="label">${strings.get('login.password')}</label></td>
    <td>
      <input class="ui-widget" type="password" name="loginForm.password" id="login_loginForm_password"/>
    </td>
  </tr>
  <tr>
    <td> </td>
    <td ><div><input class="ui-button ui-widget ui-corner-all" type="submit" value="${strings.get('login.login')}" id="login_verifyLogin" name="action:verifyLogin"/></div></td>
  </tr>
</table>
</form>
</div>