<%@ page import="fr.eni.javaee.BO.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<%@ page import="fr.eni.javaee.BLL.CategorieManager" %>
<%@ page import="fr.eni.javaee.BO.Categorie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Accueil</title>
</head>
<body>
<!-- Navbar -->

<div class="search-bar">

    <select class="input" id="categorie">
        <% for(Categorie categorie : CategorieManager.selectionnerToutesLesCategories()) { %>
        <option name="categorie" value ="<%=categorie.getId()%>"><%=categorie.getLibelle()%></option>
        <% } %>
    </select>

    <div class="search-box">
        <input type="text" class="search" placeholder="Que recherchez-vous ?">
    </div>

    <button type="submit" class="search-btn">
    </button>
</div>

<div class="head">
    <h1>Enchères En Cours</h1>
</div>
<div class="container">
    <div class="card-grid">

        <% List<Article> listeArticles = ArticleManager.selectAllArticles(); %>
        <% if(listeArticles.size() != 0) { %>
        <% for(Article article : listeArticles) { %>
        <div class="card">
            <div class="card-header">
                <h1><a href="<%=request.getContextPath()%>/detailVente?idArticle=<%=article.getId_article() %>"><%=article.getNom()%></a></h1>
            </div>
            <div class="card-img-container">
                <img src="" alt="">
            </div>
            <div class="card-body">
                <div class="prix"><i class="fas fa-tag"></i><%=article.getPrixInitial()%></div>
                <% if(article.getPrixVente() != 0) { %>
                <div class="prix"><i class="fas fa-tag"></i><%=article.getPrixVente()%></div>
                <%} %>
                <div class="card-date-enchere">
                    <p><i class="far fa-clock"></i><%=article.getDebutEnchere().format(DateTimeFormatter.ofPattern("dd/MM/YYYY", Locale.FRANCE))%></p>
                </div>
                <div class="vendeur">
                    <p><i class="fas fa-id-badge"></i> <%=article.getVendeur().getPseudo()%></p>
                </div>
            </div>
        </div>
        <% } %>

        <% } else { %>
        <h1>Aucun article</h1>
        <% } %>

    </div>
</div>
</body>
</html>
