<%--
  author ROUILLY Charlotte
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <link href="<c:url value="../WEB-CONTENT/stylesheet.css"/>" rel="stylesheet">
    <meta name="viewport"content="width=device-width, initial-scale=1.">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Détail Vente</title>
</head>
<body class="container">
<!--Navbar-->
<nav class="navbar">
    <div class="marque">ENI-ENCHERES</div>

    <a href="#" class="toggle-button">
        <span class="bar"></span>
        <span class="bar"></span>
        <span class="bar"></span>
    </a>
    <div class="navbar-links">
        <ul>
            <%--            <li><a href="">Enchères  </a></li>--%>
            <li><a href="#">Vendre un article </a></li>
            <li><a href="/profil">Mon Profil  </a></li>
            <li><a href="/accueil">Deconnexion  </a></li>
        </ul>
    </div>

</nav>



<div >
    <h1 class="titre">Détail Vente</h1>
</div>

<div class="details">
    <section class="register-form">
        <form class="register">

            <div class="input-field">
            <div class="d-inline-flex p-2">Nom :</div>
            <div class="form-group col-sm-2">
                <div class="d-inline-flex p-2"><c:out value="${afficherArticle.nom }"/></div>
            </div>
        </div>

            <div class="input-field">
                <div class="d-inline-flex p-2">Description :</div>
                <div class="form-group col-sm-2">
                    <div class="d-inline-flex p-2"><c:out value="${afficherArticle.description }"/></div>
                </div>
            </div>


            <div class="input-field">
                <div class="d-inline-flex p-2">Catégorie :</div>
                <div class="form-group col-sm-2">
                    <div class="d-inline-flex p-2"><c:out value="${afficherCategorie.getLibelle }"/></div>
                </div>
            </div>

            <div class="input-field">
                <div class="d-inline-flex p-2">Mise à prix :</div>
                <div class="form-group col-sm-2">
                    <div class="d-inline-flex p-2"><c:out value="${afficherArticle.prixInitial }"/></div>
                </div>
            </div>

            <div class="input-field">
                <div class="d-inline-flex p-2">Fin de l'enchère :</div>
                <div class="form-group col-sm-2">
                    <div class="d-inline-flex p-2"><c:out value="${dateFinToString}" /></div>
                </div>
            </div>

            <div class="input-field">
                <div class="d-inline-flex p-2">Retrait :</div>
                <div class="form-group col-sm-2">
                    <div class="d-inline-flex p-2"><c:out value="${afficherRetrait.rue}"/></div>
                    <div class="d-inline-flex p-2"><c:out value="${afficherRetrait.ville}"/></div>
                </div>
            </div>

            <div class="input-field">
                <div class="d-inline-flex p-2">Vendeur :</div>
                <div class="form-group col-sm-2">
                    <div class="d-inline-flex p-2"><c:out value="${utilisateur.pseudo}"/></div>
                </div>
            </div>
        </form>
    </section>
</div>
</body>
</html>