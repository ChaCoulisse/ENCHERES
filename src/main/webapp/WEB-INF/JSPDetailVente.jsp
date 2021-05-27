<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Détail Vente</title>
</head>
<body>
<div class="head">
    <h1>Détail Vente</h1>
</div>
<div class="details">
<div class="row justify-content-center">
    <div class="form-group col-sm-2">
        <div class="d-inline-flex p-2"></div>
    </div>
    <div class="form-group col-sm-2">
        <div class="d-inline-flex p-2"><c:out value="${afficherArticle.nom }"/></div>
    </div>
</div>
<div class="row justify-content-center">
    <div class="form-group col-sm-2">
        <div class="d-inline-flex p-2">Description :</div>
    </div>
    <div class="form-group col-sm-2">
        <div class="d-inline-flex p-2"><c:out value="${afficherArticle.description }"/></div>
    </div>
</div>
    <div class="row justify-content-center">
        <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2">Catégorie :</div>
        </div>
        <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2"><c:out value="${afficherCategorie.getLibelle }"/></div>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2">Mise à prix :</div>
        </div>
        <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2"><c:out value="${afficherArticle.prixInitial }"/></div>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2">Fin de l'enchère :</div>
        </div>
        <div class="form-group col-sm-2">
            <!--------------------------------------- A MODIFIER ------------------------------------->
            <div class="d-inline-flex p-2"><c:out value="${dateFinToString}" /></div>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2">Retrait :</div>
        </div>
        <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2"><c:out value="${afficherRetrait.rue}"/></div>
            <div class="d-inline-flex p-2"><c:out value="${afficherRetrait.ville}"/></div>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2">Vendeur :</div>
        </div>
        <div class="form-group col-sm-2">
            <div class="d-inline-flex p-2"><c:out value="${utilisateur.vendeur}"/></div>
        </div>
    </div>
</div>

</body>
</html>
