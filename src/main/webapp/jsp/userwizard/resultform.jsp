<%@ include file="/jsp/include.jspf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<body>
<h2>User details</h2>

<table>
<tr>
<td>UserName :</td><td><c:out value="${user}"/></td>
</tr>
<tr>
<td>Password :</td><td>${user}</td>
</tr>
<tr>
<td>Remark :</td><td>${user}</td>
</tr>
</table>

</body>
</html>
