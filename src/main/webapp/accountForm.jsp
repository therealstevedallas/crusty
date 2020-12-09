<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<s:url var="cancelUrl" action="listAccounts"></s:url>
<script>
 $(document).ready(function() {
	$('#cancel-button').click(function() {
	    window.location = '${cancelUrl}';
	});
});
</script>


<div class='ui-widget-content card'>
<form id="editAccount" name="acctForm" action="/crusty/editAccount.action" method="post">
    <input type="hidden" name="accountForm.id" value="${accountForm.id}" id="editAccount_accountForm_id"/>
<table>
    <caption class='ui-widget-header'>${strings.get('account.dialog.title')}</caption>
    <tr><td class="form-label-left">
    <label for="editAccount_accountForm_name" >${strings.get('account.name')}</label>
    </td><td>
    <input class="ui-widget" type="text" name="accountForm.name" value="${accountForm.name}" id="editAccount_accountForm_name" >
    </td></tr>
    <tr><td class="form-label-left">
    <label for="editAccount_accountForm_password" >${strings.get('login.password')}</label>
    </td><td>
    <input class="ui-widget" type="password" name="accountForm.password" value="${accountForm.password}" id="editAccount_accountForm_password" >
    </td></tr>
    <tr><td class="form-label-left">
    <label for="editAccount_accountForm_firstName" >${strings.get('account.name.first')}</label>
    </td><td>
    <input class="ui-widget" type="text" name="accountForm.firstName" value="${accountForm.lastName}" id="editAccount_accountForm_firstName" >
    </td></tr>
    <tr><td class="form-label-left">
    <label for="editAccount_accountForm_lastName" >${strings.get('account.name.last')}</label>
    </td><td>
    <input class="ui-widget" type="text" name="accountForm.lastName" value="${accountForm.firstName}" id="editAccount_accountForm_lastName" >
    </td></tr>
    <tr><td class="form-label-left">
    <label for="editAccount_accountForm_email" >${strings.get('account.email')}</label>
    </td><td>
    <input class="ui-widget" type="text" name="accountForm.email" value="${accountForm.email}" id="editAccount_accountForm_email" >
    </td></tr>
    <tr><td class="form-label-left">
    <label for="editAccount_accountForm_mobile" >${strings.get('account.mobile')}</label>
    </td><td>
    <input class="ui-widget" type="text" name="accountForm.mobile" value="${accountForm.mobile}" id="editAccount_accountForm_mobile" >
    </td></tr>
    <tr><td class="form-label-left">
    <label for="editAccount_accountForm_active" >${strings.get('account.active')}</label>
    </td><td>
    <input class="ui-widget" type="checkbox" name="accountForm.active" value="${accountForm.active}" checked="checked" id="editAccount_accountForm_active" ><input type="hidden" id="__checkbox_editAccount_accountForm_active" name="__checkbox_accountForm.active" value="true" />
    </td></tr>
    <tr><td>
  <input class="ui-button ui-widget ui-corner-all" type="button" value="${strings.get('button.cancel')}" id="cancel-button"/>
  <input class="ui-button ui-widget ui-corner-all" type="submit" value="${strings.get('button.save')}" id="editAccount_saveAccount" name="action:saveAccount"/>
    </td></tr>
</fieldset>
</form>
</div>

