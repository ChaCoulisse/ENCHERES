<%--
  Created by IntelliJ IDEA.
  User: txufe
  Date: 26/05/2021
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="fr.eni.javaee.messages.LecteurMessage" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar">
    <div class="marque">ENI-Enchères</div>

    <a href="#" class="toggle-button">
        <span class="bar"></span>
        <span class="bar"></span>
        <span class="bar"></span>
    </a>
    <div class="navbar-links">
        <ul>
            <li><a href="/inscription">Créer un compte  </a></li>
            <li><a href="/connexion">Se connecter  </a></li>
        </ul>
    </div>
</nav>

<form method="post" action="/accueil">
    <div class="barre-recherche">

        <select class="categories" name="categories">
            <option value="Toutes">Toutes les catégories</option>
            <c:forEach items="${liseCaterogies}" var="categorie">
                <option value="${categorie.libelle}">${categorie.libelle}</option>
            </c:forEach>
        </select>

        <div class="recherche-box">
            <input type="text"  name="recherche_nom" class="search" placeholder="Votre recherche...">
        </div>
        <button type="submit" class="search-btn">Rechercher</button>

    </div>
</form>


<div>
    <h1 class="titre">Liste des enchères en cours</h1>
</div>
<div class="container">
    <div class="card-grid">

        <c:forEach items="${listeArticles}" var="article">
            <div class="card">
                <div class="card-header">
                    <h1><a href="/DetailVente?id_article=${article.id_article}">${article.nom}</a> </h1>
                </div>
                <div class="card-img-container">
                    <img src="METTRE PHOTO PRODUIT" alt="">
                </div>
                <div class="card-body">
                    <div class="prix">Prix : ${article.prixVente} points</div>
                    <div class="card-date-enchere">
                        <p> Fin de l'enchère : ${mapFinEnchere[article.vendeur]}</p>
                    </div>
                    <div class="vendeur">
                        <p><a href="#">Vendeur : ${mapNomUtilisateur[article.vendeur]}</a></p>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>