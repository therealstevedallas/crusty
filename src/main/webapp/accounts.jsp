<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.walterj.util.web.ParamUtil" %>
<div class="row">
<div class="column">

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
<th colspan="6"><div class="form-label-right"><a href='/crusty/editAccount?accountForm.id=-1'>
  <img src='/crusty/images/ic_add.png'/></a></div>
</th>
</tr>
<tr>
  <c:forEach var="colName" items="${accountPager.getHeaders()}" varStatus="status">
     <th><button data-id='${status.index}' class="sortable" ${accountPager.sortBy eq status.index ? 'id="sortBy"' :''}>${colName}</button></th>
  </c:forEach>
  <th>${strings.get('table.controls')}</th>
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
     <c:when test="${i == accountPager.getCurrentPage()}"><a id="current-page" class="active" href="#">${i+1}</a></c:when>
     <c:otherwise><a class="link" href="#">${i+1}</a></c:otherwise>
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
<td class="form-label-right">
  <a href='/crusty/editAccount?accountForm.id=${account.id}'><img src='/crusty/images/ic_edit.png'/></a>
 <a href='/crusty/deleteAccount?accountForm.id=${account.id}'/><img src='/crusty/images/ic_delete.png'></a>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</div>
    <input type="hidden" name="currentPage" value="${accountPager.currentPage}" id="_currentPage"/>
    <input type="hidden" name="sortBy" value="${accountPager.sortBy}" id="_sortBy"/>
    <input type="hidden" name="sortAscending" value="${accountPager.sortAscending}" id="_sortAsc"/>
</form>
<script>
$(document).ready(function() {

    $("#_rowsPerPage").selectmenu({
        change: function( event, ui ) {
            $('#_currentPage').val(0);
            $('#tablePager').submit();
        }
     });

    $('.sortable').button({
       showLabel: 'true',
       icon: 'ui-icon-caret-2-n-s',
       iconPosition: 'end', });

    $('#sortBy').button({
       icons:{
         primary: (${accountPager.sortAscending} ? 'ui-icon-caret-1-n' : 'ui-icon-caret-1-s')
       },
       iconPosition: 'end', });


    $('.sortable').click(function() {
       var fwd = Number($(this).attr('data-id'));
       var cur = ${accountPager.sortBy};
       var srt = ${accountPager.sortAscending}
       if (cur > -1) srt = !srt; // toggle
       $('#_sortBy').val(fwd);
       $('#_sortAsc').val(srt);
	   $('#tablePager').submit();
    });

    $("#_rowsPerPage").val(${accountPager.rowsPerPage}).selectmenu('refresh');

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
