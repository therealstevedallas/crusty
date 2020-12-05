<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.walterj.util.web.ParamUtil" %>
<div class="row">
<div class="column">
<div class="card">

<form id="tablePager" name="pagerForm" action="/crusty/listAccounts.action" method="post">
<table class="data-table">
<thead>
  <tr>
    <th colspan="8">
      <h3>${strings.get('accounts.title')}</h3>
    </th>
  </tr>
<tr>
<th>
<label for="_rowsPerPage">${strings.get('table.rows.per.page')}</label>
</th>
<th>
  <select name="rowsPerPage" value="${accountPager.rowsPerPage}" id="_rowsPerPage">
   <option>5</option>
   <option>10</option>
   <option>20</option>
   <option>50</option>
  </select>
</th>
<th colspan="6"><div class="right"><a href='/crusty/editAccount?accountForm.id=-1'><img src='/crusty/images/ic_add.png'/></a></div></th>
</tr>
<tr>
  <th>${strings.get('accounts.header.name')}</th>
  <th>${strings.get('accounts.header.name.last')}</th>
  <th>${strings.get('accounts.header.name.first')}</th>
  <th>${strings.get('accounts.header.email')}</th>
  <th>${strings.get('accounts.header.mobile')}</th>
  <th>${strings.get('accounts.header.login')}</th>
  <th>${strings.get('accounts.header.active')}</th>
  <th>${strings.get('table.controls')}
  </th>
</tr>
</thead>
<tfoot>
<tr>
<td colspan="8">
<div class="row-count">
    ${ParamUtil.formatMessage(strings.get('format.message.table.contents'),
                              accountPager.rowRange.lo,
                              accountPager.rowRange.hi,
                              accountPager.totalRows)}
</div>
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
<td><c:out value="${ParamUtil.formatTimestamp(account.lastLogin, 'YYYY-MM-dd hh:mm:ss aa')}" /></td>
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
    <input type="hidden" name="currentPage" value="${accountPager.currentPage}" id="_currentPage"/>
</form>
<script>
$(document).ready(function() {

    $("#_rowsPerPage").val(${accountPager.rowsPerPage}).attr('selected', 'selected');

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
	$('#_rowsPerPage').change(function() {
	    $('#_currentPage').val(0);
        $('#tablePager').submit();
    });
});
</script>
