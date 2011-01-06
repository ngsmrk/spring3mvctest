<%@ include file="/jsp/include.jspf" %>

<html>
<head><title><fmt:message key="title"/></title></head>
<body>
<h1><fmt:message key="heading"/></h1>

<p>
    <fmt:message key="greeting">
        <fmt:param value="${now}"/>
    </fmt:message>
</p>

<p>
Handling forms in Spring MVC<br/>
Click here to start playing - <a href="createaccount.htm">SimpleFormController example</a>
</p>

<p>
Handling multipage forms in Spring MVC<br/>
Click here to start playing - <a href="adduser.htm">AbstractWizardFormController example</a>
</p>

<p>
    <%@ include file="/jsp/diagnostic.jspf" %>
</p>
</body>
</html>