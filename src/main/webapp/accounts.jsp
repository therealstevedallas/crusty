<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<h3>${strings.get('accounts.title')}</h3>
<div class="row">
<div class="column">
<div class="card">
<table class="data-table">
<tr class="data-table-header">
  <th>${strings.get('accounts.header.name')}</th>
  <th>${strings.get('accounts.header.name.last')}</th>
  <th>${strings.get('accounts.header.name.first')}</th>
  <th>${strings.get('accounts.header.email')}</th>
  <th>${strings.get('accounts.header.mobile')}</th>
  <th>${strings.get('accounts.header.login')}</th>
  <th>${strings.get('accounts.header.active')}</th>
  <th>${strings.get('table.controls')} <a href='/crusty/editAccount?accountForm.id=-1'>New</a></th>
</tr>
<c:forEach var="account" items="${accountList}">
<tr>
<td><c:out value="${account.name}" /></td>
<td><c:out value="${account.lastName}" /></td>
<td><c:out value="${account.firstName}" /></td>
<td><c:out value="${account.email}" /></td>
<td><c:out value="${account.mobile}" /></td>
<td><c:out value="${account.lastLogin}" /></td>
<td><c:out value="${account.active}" /></td>
<td>
  <a href='/crusty/editAccount?accountForm.id=${account.id}'>Edit</a>
  <a href='/crusty/deleteAccount?accountForm.id=${account.id}'>Delete</a>
</td>
</tr>
</c:forEach>
</table>
</div>
</div>
</div>
