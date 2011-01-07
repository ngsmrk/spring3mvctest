<%@ include file="/jsp/include.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<style>
.error {
	color: #ff0000;
}
.errorblock{
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding:8px;
	margin:16px;
}
</style>
</head>

<body>
<h2>JSR-303 validation test</h2>

    <form:form method="POST" commandName="bean">
        <form:errors path="*" cssClass="errorblock" element="div"/>
<table>
    <tr>
        <td><fmt:message key="bean.form.name"/></td>
        <td><form:input path="name"/></td>
        <td><form:errors path="name" cssClass="error"/></td>
    </tr>
    <tr>
        <td><fmt:message key="bean.form.name"/></td>
        <td><form:input path="child.name"/></td>
        <td><form:errors path="child.name" cssClass="error"/></td>
    </tr>
    <tr>
        <td colspan="3"><input type="submit"/></td>
    </tr>
</table>
</form:form>

</body>
</html>