
<%@ page import="fr.eni.javaee.messages.LecteurMessage" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <link href="<c:url value="../WEB-CONTENT/stylesheet.css"/>" rel="stylesheet">
    <title>Inscription</title>
    <link type="text/css" rel="stylesheet" href="/WEB-CONTENT/form.css"/>
</head>
<body class="container">

<!-- Navbar -->

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
<form method="post" action="${pageContext.request.contextPath}/inscription">
    <fieldset class="fieldset">
        <h1 class="titre">Mon inscription</h1>
        <p class="inscriptiontxt">Vous pouvez vous inscrire via ce formulaire.</p>

        <section class="register-form">
            <form class="register">

                    <div class="input-field">
                        <label for="pseudo">Pseudo <span class="requis">*</span></label>
                        <input type="text" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" size="20" required
                               maxlength="20"/>
                        <br/>
                    </div>

                    <div class="input-field">
                        <label for="nom">Nom<span class="requis">*</span></label>
                        <input type="text" id="nom" name="nom" value="<c:out value="${utilisateur.nom}"/>" size="20" maxlength="60"required/>
                        <br/>
                    </div>
                    <div class="input-field">
                        <label for="prenom">Pr&eacute;nom<span class="requis">*</span></label>
                        <input type="text" id="prenom" name="prenom" value="<c:out value="${utilisateur.prenom}"/>" size="20"required
                               maxlength="20"/>
                        <br/>
                    </div>
                    <div class="input-field">
                        <label for="email">Email<span class="requis">*</span></label>
                        <input type="text" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="60"
                               maxlength="50" required/>
                        <br/>
                    </div>
                    <div class="input-field">
                        <label for="telephone">T&eacute;l&eacute;phone<span class="requis">*</span></label>
                        <input type="text" id="telephone" name="telephone" value="<c:out value="${utilisateur.telephone}"/>" size="15"
                               maxlength="10"required/>
                        <br/>
                    </div>
                    <div class="input-field">
                        <label for="rue">Rue <span class="requis">*</span></label>
                        <input type="text" id="rue" name="rue" value="<c:out value="${utilisateur.rue}"/>" size="20" maxlength="60"required/>
                        <br/>
                    </div>
                    <div class="input-field">
                        <label for="cp">Code postal</label>
                        <input type="text" id="cp" name="cp" value="<c:out value="${utilisateur.cp}"/>" size="20" maxlength="10"required/>
                        <br/>
                    </div>
                    <div class="input-field">
                        <label for="ville">Ville</label>
                        <input type="text" id="ville" name="ville" value="<c:out value="${utilisateur.ville}"/>" size="30"
                               maxlength="30"required/>
                        <br/>
                    </div>
                    <div class="input-field">
                        <label for="mdp">Mot de passe <span class="requis">*</span></label>
                        <input type="password" id="mdp" name="mdp" value="" size="20" maxlength="20"required/>
                        <br/>
                    </div>
                    <div class="input-field">
                        <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                        <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                        <br/>
                    </div>

                    <div class="btn-">
                        <div>
                            <button class="btn" type="submit">Cr&eacute;er Mon Compte</button>
                    </div>
                        <div class="btnConnexion">
                            <button class="btn" type="button"> <a href="/connexion">Se connecter</a></button>

                        </div>
                         </div>

            </form>
        </section>
    </fieldset>
</form>
    </c:otherwise>
</c:choose>

</body>
</html>

