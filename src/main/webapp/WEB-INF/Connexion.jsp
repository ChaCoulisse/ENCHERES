<%--
  author LY Txu-Feng
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="fr.eni.javaee.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="<c:url value="../WEB-CONTENT/stylesheet.css"/>" rel="stylesheet">
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
    <section class="register-form">
            <form class="register" method="post" action="${pageContext.request.contextPath}/connexion">
                <div class="input-field">
                <label for="identifiant">Identifiant</label>
                    <input id="identifiant" type="text" name="identifiant" value="${requestScope['identifiant']}">
                </div>
                    <div class="input-field">
                <label for="mdp">Mot de passe</label>
                    <input id="mdp" type="password" name="mdp" value="">
                    </div>

                <div class="btnConnexion">
                    <div>
                        <button class="btn" type="submit">Connexion</button>
                    </div>
                    <div class="btnConnexion">
                        <button class="btn" type="button"><a href="/inscription">Créer un nouveau compte</a></button>
                    </div>
                </div>
            </form>
    </section>
        </c:otherwise>

    </c:choose>


</body>
</html>
