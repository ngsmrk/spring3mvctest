<%@ include file="/jsp/include.jspf" %>

<c:url value="/updateaccount.htm" var="url">
    <c:param name="accountID" value="${account.id}"/>
</c:url>

<html>
<head><title><fmt:message key="title"/></title></head>
<body>
<h1><fmt:message key="heading"/></h1>

<p>
    <fmt:message key="account.created"/>
<table>
    <tr>
        <td><fmt:message key="account.form.accountNumber"/></td>
        <td><c:out value="${account.accountNumber}"/></td>
        <td><a href="<c:out value="${url}"/>">Edit Account</a></td>
    </tr>
</table>
</p>

</body>
</html>