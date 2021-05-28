package fr.eni.javaee.servlet;

import fr.eni.javaee.BLL.ArticleManager;
import fr.eni.javaee.BLL.CategorieManager;
import fr.eni.javaee.BLL.RetraitManager;
import fr.eni.javaee.BLL.UtilisateurManager;
import fr.eni.javaee.BO.Article;
import fr.eni.javaee.BO.Categorie;
import fr.eni.javaee.BO.Retrait;
import fr.eni.javaee.BO.Utilisateur;
import fr.eni.javaee.BusinessException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@WebServlet("/DetailVente")
public class ServletDetailVente extends HttpServlet {

    public ServletDetailVente () {
        super();
    }

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_article = Integer.parseInt(request.getParameter("id_article"));

        // On récupère l'article avec id_article
        Article afficherArticle = null;
        try {
            afficherArticle = ArticleManager.selectById(id_article);
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        // On récupère le lieu de retrait avec id_article
        Retrait afficherRetrait = null;
        try {
            afficherRetrait = RetraitManager.selectionnerRetraitByID(id_article);
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }

        //On récupère le libellé de la catégorire via id de la categorie (getCategorie) dans la classe Article
        Categorie afficherCategorie = null;
        try {
            afficherCategorie = CategorieManager.selectionnerCategorieById(afficherArticle.getCategorie());
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }

        // On convertir la date de fin d'enchère en type String pour faciliter l'affichage dans la JSP
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        String dateFinToString = dateFormat.format(afficherArticle.getFinEnchere());

        //On récupère le pseudo du vendeur grace à l'id_utilisateur (getVendeur()) dans la classe article
        Utilisateur utilisateur = null;
        try {
            utilisateur = UtilisateurManager.selectById(afficherArticle.getVendeur());
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }

        request.setAttribute("afficherArticle",afficherArticle);
        request.setAttribute("afficherRetrait",afficherRetrait);
        request.setAttribute("afficherRetrait",afficherRetrait);
        request.setAttribute("utilisateur",utilisateur);
        request.setAttribute("dateFinToString",dateFinToString);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/DetailVente.jsp");
        rd.forward(request,response);

    }
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
