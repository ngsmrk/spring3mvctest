<%@ include file="/jsp/include.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title><fmt:message key="title"/></title>
    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body>
<h1><fmt:message key="heading"/> <c:out value="${bankName}"/></h1>

<p>
    <form:form method="POST" commandName="account">
        <form:errors path="*" cssClass="errorblock" element="div"/>
<table>
    <tr>
        <td><fmt:message key="account.form.accountNumber"/></td>
        <td><form:input path="accountNumber"/></td>
        <td><form:errors path="accountNumber" cssClass="error"/></td>
    </tr>
    <tr>
        <td colspan="3"><input type="submit"/></td>
    </tr>
</table>
</form:form>
</p>

</body>
</html>