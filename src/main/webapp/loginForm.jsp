<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="row">
<div class="column">
<div class='card'>
<h3>${strings.get('login.dialog.title')}</h3>
<form id="login" name="loginForm" action="/crusty/login.action" method="post">
<table class="wwFormTable">
  <tr>
    <td class="tdLabel">
      <label for="login_loginForm_account" class="label">${strings.get('login.user')}</label>
    </td>
    <td class="tdInput" >
      <input type="text" name="loginForm.account" value="" id="login_loginForm_account" class="input"/>
    </td>
  </tr>
  <tr>
    <td class="tdLabel"><label for="login_loginForm_password" class="label">${strings.get('login.password')}</label></td>
    <td class="tdInput">
      <input type="password" name="loginForm.password" id="login_loginForm_password" class="input"/>
    </td>
  </tr>
  <tr>
    <td> </td>
    <td ><div class="formButton"><input type="submit" value="${strings.get('login.login')}" id="login_verifyLogin" name="action:verifyLogin"/></div></td>
  </tr>
</table>
</form>
</div>
</div>
</div>
