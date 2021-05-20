
<%@ page pageEncoding="UTF-8" language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.eni.javaee.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Inscription</title>
    <link type="text/css" rel="stylesheet" href="form.css"/>
</head>
<body class="container">

<!-- Navbar -->
<%@ include file = "JSPNavbar.jsp" %>

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

<form method="post" action="${pageContext.request.contextPath}/inscription">
    <fieldset>
        <legend>Mon inscription</legend>
        <p>Vous pouvez vous inscrire via ce formulaire.</p>

        <label for="pseudo">Pseudo <span class="requis">*</span></label>
        <input type="text" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" size="20"
               maxlength="20"/>
        <span class="erreur">${form.erreurs['pseudo']}</span>
        <br/>


        <label for="nom">Nom<span class="requis">*</span></label>
        <input type="text" id="nom" name="nom" value="<c:out value="${utilisateur.nom}"/>" size="20" maxlength="60"/>
        <span class="erreur">${form.erreurs['nom']}</span>
        <br/>

        <label for="prenom">Prénom<span class="requis">*</span></label>
        <input type="text" id="Prenom" name="Prenom" value="<c:out value="${utilisateur.prenom}"/>" size="20"
               maxlength="20"/>
        <span class="erreur">${form.erreurs['prenom']}</span>
        <br/>

        <label for="email">Email<span class="requis">*</span></label>
        <input type="text" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="30"
               maxlength="50"/>
        <span class="erreur">${form.erreurs['email']}</span>
        <br/>

        <label for="téléphone">Téléphone<span class="requis">*</span></label>
        <input type="text" id="Téléphone" name="Téléphone" value="<c:out value="${utilisateur.telephone}"/>" size="15"
               maxlength="10"/>
        <span class="erreur">${form.erreurs['telephone']}</span>
        <br/>

        <label for="rue">Rue <span class="requis">*</span></label>
        <input type="text" id="rue" name="rue" value="<c:out value="${utilisateur.rue}"/>" size="20" maxlength="60"/>
        <span class="erreur">${form.erreurs['rue']}</span>
        <br/>

        <label for="cp">Code postal</label>
        <input type="text" id="cp" name="cp" value="<c:out value="${utilisateur.cp}"/>" size="20" maxlength="10"/>
        <span class="erreur">${form.erreurs['cp']}</span>
        <br/>

        <label for="ville">Ville</label>
        <input type="text" id="ville" name="ville" value="<c:out value="${utilisateur.ville}"/>" size="20"
               maxlength="30"/>
        <span class="erreur">${form.erreurs['ville']}</span>
        <br/>

        <label for="mdp">Mot de passe <span class="requis">*</span></label>
        <input type="password" id="mdp" name="mdp" value="" size="20" maxlength="20"/>
        <span class="erreur">${form.erreurs['mdp']}</span>

        <br/>

        <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
        <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20"/>
        <span class="erreur">${form.erreurs['confirmation']}</span>
        <br/>


        <input type="submit" value="Inscription" class="sansLabel"/>
        <br/>

        <p class="${empty erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

    </fieldset>
</form>

<a href="/connexion"> Se connecter à son compte</a>
<footer>
    <!-- integrer fragment footer si commun  -->
</footer>

</body>
</html>

