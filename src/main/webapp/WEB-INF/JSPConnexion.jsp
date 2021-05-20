<%--
  Created by IntelliJ IDEA.
  User: txufe
  Date: 19/05/2021
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="fr.eni.javaee.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Connexion</title>
</head>
<body>

    <c:if test="${!empty listeCodesErreur}">
        <div class="alert alert-danger" role="alert">
            <strong>Erreur!</strong>
            <ul>
                <c:forEach var="code" items="${listeCodesErreur}">
                    <li>${LecteurMessage.getMessageErreur(code)}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <c:choose>

        <c:when test="${!empty sessionScope.pseudo}">
            <p>Vous êtes déjà connecté</p>

        </c:when>

        <c:otherwise>
            <form method="post" action="${pageContext.request.contextPath}/connexion">
                <label for="identifiant">Identifiant</label>
                    <input id="identifiant" type="text" name="identifiant" value="${requestScope['identifiant']}">
                <label for="mdp">Mot de passe</label>
                    <input id="mdp" type="password" name="mdp" value="">
                <input type="submit" value="connexion">
            </form>
        </c:otherwise>

    </c:choose>

    <a href="/inscription">créer un nouveau compte</a>

</body>
</html>
