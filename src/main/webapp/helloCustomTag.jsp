<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/TldFiles/helloCustomTag.tld" prefix="jstlpg" %>

<html>
<head>
    <title>
        Hello World Sample
    </title>
</head>

<body>
<%--Выводит «Hello, world !» в случае если не будет передано параметра name. Если же он передан, то приветствие несколько изменится. Например, при передаче параметра name=Anton сервлет должен вывести надпись «Hello, world. I’m Anton».--%>
<%--<h1>
    <%
        String name = request.getParameter("name");
        if (name == null || name.length() == 0) {
    %>
    Hello, world !
    <% } else {
    %>
    Hello, world ! I'm <%= name%>
    <%
        }
    %>
</h1>--%>

<%--Более коротка форма вышележащего блока с использование custom tag--%>
<h1>
    <jstlpg:hello name='<%= request.getParameter("name") %>'/>
</h1>

</body>
</html>