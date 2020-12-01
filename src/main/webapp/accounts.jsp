<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="row">
<div class="column">
<div class='card'>
<h3>Accounts</h3>
<table>
<c:forEach var="account" items="${accountList}">
<tr>
<td><c:out value="${account.name}" /></td>
<td><c:out value="${account.active}" /></td>
<td><a href='/crusty/editAccount?accountForm.id=${account.id}'>Edit</a></td>
</tr>
</c:forEach>
</table>
</div>
</div>
</div>
