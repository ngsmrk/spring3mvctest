<%@ include file="/jsp/include.jspf" %>

<html>
<head><title><fmt:message key="title"/></title></head>
<body>
<h1><fmt:message key="heading"/></h1>

<h2>Existing Accounts</h2>
<p>

    <br /><br />
    <c:if test="${!empty accounts}">
            <table>
                    <tr>
                            <th>ID</th>
                            <th>Number</th>
                            <th>Edit</th>
                            <th>Delete</th>
                    </tr>

                    <c:forEach items="${accounts}" var="account">


                        <c:url value="/updateaccount.htm" var="url">
                            <c:param name="accountID" value="${account.id}"/>
                        </c:url>

                        <c:url value="/deleteaccount.htm" var="deleteUrl">
                            <c:param name="accountID" value="${account.id}"/>
                        </c:url>

                    <tr>
                            <td><c:out value="${account.id}" /></td>
                            <td><c:out value="${account.accountNumber}" /></td>
                            <td><a href="<c:out value="${url}"/>">Edit Account</a></td>
                            <td><a href="<c:out value="${deleteUrl}"/>">Delete Account</a></td>
                    </tr>
                    </c:forEach>
            </table>
    </c:if>
    <c:if test="${empty accounts}">
      No accounts
    </c:if>
</p>

<p>
<a href="createaccount.htm">Add account</a>
</p>

<p>
    <%@ include file="/jsp/diagnostic.jspf" %>
</p>
</body>
</html>