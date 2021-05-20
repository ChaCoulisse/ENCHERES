<!-- Page connecté -->
<%@page import="fr.eni.javaee.BO.Utilisateurs"%>
<% if( session.getAttribute("UtilisateurConnecte") != null){ %>
<!--Navbar-->

<nav class="navbar">
    <div class="brand-title"> <a href="<%=request.getContextPath()%>/accueilConnecte"> ENI_ENCHERES </a></div>

    <a href="#" class="toggle-button">
        <span class="bar"></span>
        <span class="bar"></span>
        <span class="bar"></span>
    </a>

    <div class="navbar-links">
        <%Utilisateurs utilisateurConnecte = (Utilisateurs) session.getAttribute("UtilisateurConnecte"); %>
        <ul>
            <li><a href="<%=request.getContextPath()%>/accueilConnecte">Enchères  <i class="fas fa-bullhorn"></i></a></li>
            <li><a href="<%=request.getContextPath()%>/vendre">Vendre  <i class="fas fa-hand-holding-usd"></i></a></li>
            <li><a href="<%=request.getContextPath()%>/profil?pseudo=<%=utilisateurConnecte.getPseudo()%>">Mon Profil  <i class="fas fa-user-circle"></i></a></li>
            <li><a href="<%=request.getContextPath()%>/AccueilDeconnecte">Deconnexion  <i class="fas fa-sign-in-alt"></i></a></li>
        </ul>
    </div>
</nav>

<% } else {  %>


<!-- Page déconnecté -->

<!--Navbar-->

<nav class="navbar">
    <div class="brand-title">ENI-ENCHERES</div>

    <a href="#" class="toggle-button">
        <span class="bar"></span>
        <span class="bar"></span>
        <span class="bar"></span>
    </a>
    <div class="navbar-links">
        <ul>
            <li><a href="<%=request.getContextPath()%>/inscription"> Créer Un Compte <i class="fas fa-sign-in-alt"></i></a></li>
            <li><a href="<%=request.getContextPath()%>/connexion"> Se Connecter  <i class="fas fa-sign-in-alt"></i></a></li>
        </ul>
    </div>
</nav>
<% } %>

<!--Banner-->
<div class="header">
</div>
