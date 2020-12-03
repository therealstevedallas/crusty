<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="row">
<div class="column">
<div class='card'>
<h3>${strings.get('account.dialog.title')}</h3>
<form id="editAccount" name="loginForm" action="/crusty/editAccount.action" method="post">
<table class="wwFormTable">
<tr>
    <td class="tdLabel"><label for="editAccount_accountForm_name" class="label">${strings.get('account.name')}:</label></td>
    <td class="tdInput"><input type="text" name="accountForm.name" value="${accountForm.name}" id="editAccount_accountForm_name" class="input"/></td>
</tr>
<tr>
    <td class="tdLabel"><label for="editAccount_accountForm_password" class="label">${strings.get('login.password')}:</label></td>
    <td class="tdInput"><input type="password" name="accountForm.password" value="${accountForm.password}" id="editAccount_accountForm_password" class="input"/></td>
</tr>
<tr>
    <td class="tdLabel"><label for="editAccount_accountForm_firstName" class="label">${strings.get('account.name.first')}:</label></td>
    <td class="tdInput"><input type="text" name="accountForm.firstName" value="${accountForm.lastName}" id="editAccount_accountForm_firstName" class="input"/></td>
</tr>
<tr>
    <td class="tdLabel"><label for="editAccount_accountForm_lastName" class="label">${strings.get('account.name.last')}:</label></td>
    <td class="tdInput"><input type="text" name="accountForm.lastName" value="${accountForm.firstName}" id="editAccount_accountForm_lastName" class="input"/></td>
</tr>
<tr>
    <td class="tdLabel"><label for="editAccount_accountForm_email" class="label">${strings.get('account.email')}:</label></td>
    <td class="tdInput"><input type="text" name="accountForm.email" value="${accountForm.email}" id="editAccount_accountForm_email" class="input"/></td>
</tr>
<tr>
    <td class="tdLabel"><label for="editAccount_accountForm_mobile" class="label">${strings.get('account.mobile')}:</label></td>
    <td class="tdInput"><input type="text" name="accountForm.mobile" value="${accountForm.mobile}" id="editAccount_accountForm_mobile" class="input"/></td>
</tr>
<tr>
    <td class="tdLabel"><label for="editAccount_accountForm_active" class="label">${strings.get('account.active')}:</label></td>
    </td>
    <td class="tdCheckboxInput">
      <input type="checkbox" name="accountForm.active" value="${accountForm.active}" checked="checked" id="editAccount_accountForm_active" class="checkbox"/><input type="hidden" id="__checkbox_editAccount_accountForm_active" name="__checkbox_accountForm.active" value="true" />
    </td>
</tr>
<tr style="display:none;">
  <td colspan="2">
  <input type="hidden" name="accountForm.id" value="${accountForm.id}" id="editAccount_accountForm_id"/>
</tr>
<tr>
  <td> </td>
  <td><div class="formButton"><input type="submit" value="${strings.get('button.save')}" id="editAccount_saveAccount" name="action:saveAccount"/>
  </div></td>
</tr>
</table>
</form>
</div>
</div>
</div>
