<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profession List</title>
    </head>
    <body>
        <table border="1">
            <%--Если вы посмотрите снова на код нашего контроллера, то увидите, что наши данные мы поместили под именем professionList. И именно по этому имени обращаемся к данным.--%>
            <c:forEach var="profession" items="${professionList}">
                <tr>
                    <td>${profession.professionId}</td>
                    <td>${profession.professionName}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
