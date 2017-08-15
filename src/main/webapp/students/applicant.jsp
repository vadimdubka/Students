<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Applicant List</title>
    </head>
    <body>
        <table border="1">
            <c:forEach var="applicant" items="${applicantList}">
                <tr>
                    <td>${applicant.applicantId}</td>
                    <td>${applicant.lastName}</td>
                    <td>${applicant.firstName}</td>
                    <td>${applicant.middleName}</td>
                    <td>${applicant.entranceYear}</td>
                    <td>${applicant.professionName}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
