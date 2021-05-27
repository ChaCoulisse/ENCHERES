<%@ page pageEncoding="UTF-8" language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.eni.javaee.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <link href="<c:url value="../WEB-CONTENT/stylesheet.css"/>" rel="stylesheet">
    <title>Inscription</title>
</head>
<body class="container">

<form method="post" action="${pageContext.request.contextPath}/inscription">
    <fieldset  class="fieldset">
        <legend class="titre">Mon inscription</legend>
        <p class="inscriptiontxt">Vous pouvez vous inscrire via ce formulaire.</p>

        <section class="register-form">
            <form class="register">

                <div class="input-field">
                    <label for="pseudo">Pseudo <span class="requis">*</span></label>
                    <input type="text" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" size="20"
                           maxlength="20"/>
                    <span class="erreur">${form.erreurs['pseudo']}</span>
                    <br/>
                </div>

                <div class="input-field">
                    <label for="nom">Nom<span class="requis">*</span></label>
                    <input type="text" id="nom" name="nom" value="<c:out value="${utilisateur.nom}"/>" size="20" maxlength="60"/>
                    <span class="erreur">${form.erreurs['nom']}</span>
                    <br/>
                </div>

                <div class="input-field">
                    <label for="prenom">Prénom<span class="requis">*</span></label>
                    <input type="text" id="prenom" name="prenom" value="<c:out value="${utilisateur.prenom}"/>" size="20"
                           maxlength="20"/>
                    <span class="erreur">${form.erreurs['prenom']}</span>
                    <br/>
                </div>

                <div class="input-field">
                    <label for="email">Email<span class="requis">*</span></label>
                    <input type="text" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20"
                           maxlength="20"/>
                    <span class="erreur">${form.erreurs['email']}</span>
                    <br/>
                </div>

                <div class="input-field">
                    <label for="telephone">Téléphone<span class="requis">*</span></label>
                    <input type="text" id="telephone" name="telephone" value="<c:out value="${utilisateur.telephone}"/>" size="20"
                           maxlength="20"/>
                    <span class="erreur">${form.erreurs['telephone']}</span>
                    <br/>
                </div>

                <div class="input-field">
                    <label for="rue">Rue <span class="requis">*</span></label>
                    <input type="text" id="rue" name="rue" value="<c:out value="${utilisateur.rue}"/>" size="20" maxlength="20"/>
                    <span class="erreur">${form.erreurs['rue']}</span>
                    <br/>
                </div>

                <div class="input-field">
                    <label for="cp">Code postal</label>
                    <input type="text" id="cp" name="cp" value="<c:out value="${utilisateur.cp}"/>" size="20" maxlength="20"/>
                    <span class="erreur">${form.erreurs['cp']}</span>
                    <br/>
                </div>

                <div class="input-field">
                    <label for="ville">Ville</label>
                    <input type="text" id="ville" name="ville" value="<c:out value="${utilisateur.ville}"/>" size="20"
                           maxlength="20"/>
                    <span class="erreur">${form.erreurs['ville']}</span>
                    <br/>
                </div>

                <div class="input-field">
                    <label for="mdp">Mot de passe <span class="requis">*</span></label>
                    <input type="password" id="mdp" name="mdp" value="" size="20" maxlength="20"/>
                    <span class="erreur">${form.erreurs['mdp']}</span>
                    <br/>
                </div>

                <div class="input-field">
                    <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                    <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20"/>
                    <span class="erreur">${form.erreurs['confirmation']}</span>
                    <br/>
                </div>
                <div class="btn-">
                    <div>
                        <button class="btn" type="submit">Cr&eacute;er Mon Compte
                            <input type="submit" value="Inscription" class="sansLabel"/>
                            <br/>
                        </button>
                    </div>
                </div>

                <p class="${empty erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                </div>
                <div class="btnConnexion">
                    <button class="btn" type="button"> <a href="/connexion">Se connecter</a></button>
                </div>
                </div>

            </form>
        </section>
    </fieldset>
</form>


</body>
</html>