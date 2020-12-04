<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<h3>${strings.get('accounts.title')}</h3>
<div class="row">
<div class="column">
<div class="card">

<table class="data-table">
<thead>
<tr>
  <th>${strings.get('accounts.header.name')}</th>
  <th>${strings.get('accounts.header.name.last')}</th>
  <th>${strings.get('accounts.header.name.first')}</th>
  <th>${strings.get('accounts.header.email')}</th>
  <th>${strings.get('accounts.header.mobile')}</th>
  <th>${strings.get('accounts.header.login')}</th>
  <th>${strings.get('accounts.header.active')}</th>
  <th>${strings.get('table.controls')} <a href='/crusty/editAccount?accountForm.id=-1'>
                                          <img src='/crusty/images/ic_add.png'/></a>
  </th>
</tr>
</thead>
<tfoot>
<tr>
<td colspan="8">
<div class="row-count">${strings.get('table.count')}: ${accountPager.getTotalRows()}</div>
<div id="links" class="links">
 <a id="page-back" href="#">&laquo;</a>
 <c:forEach var = "i" begin = "0" end = "${accountPager.getTotalPages()}">
   <c:choose>
     <c:when test="${i == accountPager.getCurrentPage()}">
       <a id="current-page" class="active" href="#">${i+1}</a>
     </c:when>
     <c:otherwise>
       <a class="link" href="#">${i+1}</a>
     </c:otherwise>
   </c:choose>
 </c:forEach>
 <a id="page-forward" href="#">&raquo;</a>
</div>
</td>
</tr>
</tfoot>
<tbody>
<c:forEach var="account" items="${accountPager.getRows()}">
<tr>
<td><c:out value="${account.name}" /></td>
<td><c:out value="${account.lastName}" /></td>
<td><c:out value="${account.firstName}" /></td>
<td><c:out value="${account.email}" /></td>
<td><c:out value="${account.mobile}" /></td>
<td><c:out value="${account.lastLogin}" /></td>
<td><c:out value="${account.active}" /></td>
<td>
  <a href='/crusty/editAccount?accountForm.id=${account.id}'><img src='/crusty/images/ic_edit.png'/></a>
 <a href='/crusty/deleteAccount?accountForm.id=${account.id}'/><img src='/crusty/images/ic_delete.png'></a>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</div>
</div>
<form id="tablePager" name="pagerForm" action="/crusty/listAccounts.action" method="post">
    <input type="hidden" name="currentPage" value="${accountPager.currentPage}" id="_currentPage"/>
    <input type="hidden" name="rowsPerPage" value="${accountPager.rowsPerPage}" id="_rowPerPage"/>
</form>
<script>
$(document).ready(function() {
	$('#page-back').click(function() {
	    var p = ${accountPager.currentPage}
	    p = p == 0 ? 0 : p - 1;
	    $('#_currentPage').val(p)
	    console.log($('#_currentPage').value);
	    $('#tablePager').submit();
    });
	$('#page-forward').click(function() {
	    var p = ${accountPager.currentPage}
	    var t = ${accountPager.totalPages};
	    p = p < t ? p + 1 : t;
	    $('#_currentPage').val(p);
	    console.log($('#_currentPage').value);
	    $('#tablePager').submit();
    });
	$('.link').click(function() {
	    console.log($(this).text());
	    var p = parseInt( $(this).text() ) - 1;
	    $('#_currentPage').val(p);
	    $('#tablePager').submit();
	});
});
</script>
