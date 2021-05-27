<%--
  Created by IntelliJ IDEA.
  User: txufe
  Date: 26/05/2021
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="fr.eni.javaee.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--Navbar-->
<nav class="navbar">
    <div class="marque">ENI-Enchères</div>

    <a href="#" class="toggle-button">
        <span class="bar"></span>
        <span class="bar"></span>
        <span class="bar"></span>
    </a>
    <div class="navbar-links">
        <ul>
            <li><a href="">Enchères  </a></li>
            <li><a href="">Vendre un article </a></li>
            <li><a href="">Mon Profil  </a></li>
            <li><a href="/accueil">Deconnexion  </a></li>
        </ul>
    </div>

</nav>

<form  method="post" action="/accueil">
    <div class="barre-recherche">
        <select class="categories" name="categories">
            <option value="Toutes">Toutes les catégories</option>
            <c:forEach items="${liseCaterogies}" var="categorie">
                <option value="${categorie.libelle}">${categorie.libelle}</option>
            </c:forEach>
        </select>

        <div class="recherche-box">
            <input type="text" name="recherche_nom" class="search" placeholder="Votre recherche...">
        </div>
        <button type="submit" class="search-btn">Rechercher</button>

    </div>

    <div class="recherche">
        <div class="recherche_achat">
            <div>
                <input type="radio" id="radio1" name="bouton_radio" value="achat" checked onclick="grise('encours','nondebut','terminees','ouvert','cours','gagner');">
                <label for="radio1">Achats</label>
            </div>
            <ul>
                <li>
                    <input type="checkbox" id="ouvert" name="ouvert" >
                    <label for="ouvert">Enchères ouvertes</label>
                </li>
                <li>
                    <input type="checkbox" id="cours" name="cours" >
                    <label for="cours">Mes enchères en cours</label>
                </li>
                <li>
                    <input type="checkbox" id="gagner" name="gagner" >
                    <label for="gagner">Mes enchères remportées</label>
                </li>
            </ul>
        </div>
        <div class="recherche_ventes">
            <div>
                <input type="radio" id="radio2" name="bouton_radio" value="ventes" onclick="grise('ouvert','cours','gagner','encours','nondebut','terminees');;">
                <label for="radio2">Mes ventes</label>
            </div>
            <ul>
                <li>
                    <input type="checkbox" id="encours" name="encours" disabled>
                    <label for="encours">Mes ventes en cours</label>
                </li>
                <li>
                    <input type="checkbox" id="nondebut" name="nondebut" disabled>
                    <label for="nondebut">Ventes non débutées</label>
                </li>
                <li>
                    <input type="checkbox" id="terminees" name="terminees" disabled>
                    <label for="terminees">Ventes terminées</label>
                </li>
            </ul>
        </div>
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
                    <h1><a href="/detailArticle"+"?"+"${id_article=article.id_article}">${article.nom}</a> </h1>
                </div>
                <div class="card-img-container">
                    <img src="METTRE PHOTO PRODUIT" alt="">
                </div>
                <div class="card-body">
                    <div class="prix">Prix : ${article.prixVente} points</div>
                    <div class="card-date-enchere">
                        <p> Fin de l'enchère : ${mapFinEnchere[article.finEnchere]}</p>
                    </div>
                    <div class="vendeur">
                        <p><a href="#">Vendeur : ${mapNomUtilisateur[article.vendeur]}</a></p>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>