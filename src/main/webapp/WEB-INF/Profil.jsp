<%@page import="fr.eni.javaee.BLL.UtilisateurManager" %>
<%@page import="fr.eni.javaee.BO.Utilisateur" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <link href="<c:url value="../WEB-CONTENT/stylesheet.css"/>" rel="stylesheet">
  <meta name="viewport"content="width=device-width, initial-scale=1.">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Mon Profil</title>
</head>
<body>
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

<div class="container">

  <h1 class="titre">Mon Profil</h1>

  <div class="profil">
    <section class="register-form">
      <form class="register">
        <div class="input-field">
          <div class="d-inline-flex p-2">Pseudo :</div>
          <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2"><c:out value="${afficherUtilisateur.pseudo }"/></div>
          </div>
        </div>

        <div class="input-field">
          <div class="d-inline-flex p-2">Nom :</div>
          <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2"><c:out value="${afficherUtilisateur.nom }"/></div>
          </div>
        </div>

        <div class="input-field">
          <div class="d-inline-flex p-2">Prénom :</div>
          <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2"><c:out value="${afficherUtilisateur.prenom }"/></div>
          </div>
        </div>

        <div class="input-field">
          <div class="d-inline-flex p-2">Email :</div>
          <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2"><c:out value="${afficherUtilisateur.email }"/></div>
          </div>
        </div>

        <div class="input-field">
          <div class="d-inline-flex p-2">Telephone :</div>
          <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2"><c:out value="${afficherUtilisateur.telephone }"/></div>
          </div>
        </div>

        <div class="input-field">
          <div class="d-inline-flex p-2">Rue :</div>
          <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2"><c:out value="${afficherUtilisateur.rue }"/></div>
          </div>
        </div>

        <div class="input-field">
          <div class="d-inline-flex p-2">Code postal :</div>
          <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2"><c:out value="${afficherUtilisateur.cp }"/></div>
          </div>
        </div>

        <div class="input-field">
          <div class="d-inline-flex p-2">Ville :</div>
          <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2"><c:out value="${afficherUtilisateur.ville }"/></div>
          </div>
        </div>
      </form>
    </section>
  </div>
</div>
</body>
</html>
