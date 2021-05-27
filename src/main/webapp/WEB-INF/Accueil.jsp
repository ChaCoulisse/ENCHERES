<%--
  Created by IntelliJ IDEA.
  User: txufe
  Date: 26/05/2021
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.eni.javaee.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.">
    <!--
    <link rel="stylesheet" href="../WEB-CONTENT/stylesheet.css">
    --!>
    <title>Accueuil ENI-Enchère</title>

    <script type="text/javascript">
        function grise(element_gris1,element_gris2,element_gris3,element_normal1,element_normal2,element_normal3) {
            var g1 = document.getElementById(element_gris1);
            var g2 = document.getElementById(element_gris2);
            var g3 = document.getElementById(element_gris3);
            var n1 = document.getElementById(element_normal1);
            var n2 = document.getElementById(element_normal2);
            var n3 = document.getElementById(element_normal3);
            g1.disabled = true;
            g2.disabled = true;
            g3.disabled = true;
            n1.disabled = false;
            n2.disabled = false;
            n3.disabled = false;
        }
    </script>
</head>
<body>

<c:choose>

    <c:when test="${!empty sessionScope.pseudo}">
        <jsp:include page="AccueilDeconnecte.jsp">
            <jsp:param name="" value=""/>
        </jsp:include>

    </c:when>

    <c:otherwise>
        <jsp:include page="AccueilConnecte.jsp">
            <jsp:param name="" value=""/>
        </jsp:include>
    </c:otherwise>

</c:choose>

</body>
</html>
