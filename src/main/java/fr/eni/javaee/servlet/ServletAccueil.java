package fr.eni.javaee.servlet;

import fr.eni.javaee.BLL.ArticleManager;
import fr.eni.javaee.BLL.CategorieManager;
import fr.eni.javaee.BLL.EnchereManager;
import fr.eni.javaee.BLL.UtilisateurManager;
import fr.eni.javaee.BO.*;
import fr.eni.javaee.BusinessException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/accueil")
public class ServletAccueil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer id_utilisateur = (Integer) session.getAttribute("id_utilisateur");

        // Récupération de toutes les catégorie pour les mettre dans la liste déroulate
        List<Categorie> listeCategories = new ArrayList<Categorie>();
        CategorieManager categorieManager = new CategorieManager();
        try {
            listeCategories = categorieManager.selectionnerToutesLesCategories();
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }

        //récupération de tous les articles
        List<Article> listeArticles = new ArrayList<Article>();
        ArticleManager articleManager = new ArticleManager();
        try {
            listeArticles = articleManager.selectionnerParNomCategorieEnCours("","Toutes");
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }

        // réupération des nom de la liste des vendeur dans  un tableau de type clé valeur
        HashMap<Integer,String> mapNomUtilisateur = new HashMap<Integer,String>();
        // récupértion des date convertir au type String pour afficher
        HashMap<Integer,String> mapFinEnchere = new HashMap<Integer,String>();
        for(Article article : listeArticles){
            UtilisateurManager utilisateurManager = new UtilisateurManager();
            Utilisateur utilisateur = null;
            try {
                utilisateur = utilisateurManager.selectById(article.getVendeur());
            } catch (BusinessException businessException) {
                businessException.printStackTrace();
            }
            mapNomUtilisateur.put(article.getVendeur(),utilisateur.getNom());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateToStr = dateFormat.format(article.getFinEnchere());
            mapFinEnchere.put(article.getVendeur(),dateToStr);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer id_utilisateur = (Integer) session.getAttribute("id_utilisateur");

        //Récupération de toutes els categorie pour les mettre dans la liste déroulante
        List<Categorie> listeCategories = new ArrayList<Categorie>();
        CategorieManager categorieManager = new CategorieManager();
        try {
            listeCategories = categorieManager.selectionnerToutesLesCategories();
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }

        List<Article> listeArticles = new ArrayList<Article>();
        ArticleManager articleManager = new ArticleManager();

        List<Enchere> listeEncheresEnCours = new ArrayList<Enchere>();
        List<Enchere> listeEncheresGagne = new ArrayList<Enchere>();
        List<Enchere> listeEncheresConcat = new ArrayList<Enchere>();
        EnchereManager enchereManager= new EnchereManager();

        String recherche_nom = request.getParameter("recherche_nom");
        if(recherche_nom != null){
            recherche_nom = recherche_nom.trim();
        }
        String categories = request.getParameter("categories");
        List<EtatVente> listeEtatVente = new ArrayList<EtatVente>();
        String bouton_radio = request.getParameter("bouton_radio");
        String ouvert = request.getParameter("ouvert");
        String cours = request.getParameter("cours");
        String gagner = request.getParameter("gagner");
        String encours = request.getParameter("encours");
        String nondebut = request.getParameter("nondebut");
        String terminees = request.getParameter("terminees");
        if(bouton_radio.equals("achat")){
            if(ouvert != null){
                try {
                    listeArticles = articleManager.selectionnerParNomCategorieEnCours(recherche_nom,categories);
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                }
            } else{
                if(cours != null) {
                    try {
                        listeEncheresEnCours = enchereManager.selectionnerParUtilisateur(id_utilisateur);
                    } catch (BusinessException businessException) {
                        businessException.printStackTrace();
                    }
                }
                if(gagner != null) {
                    try {
                        listeEncheresGagne = enchereManager.selectionnerParUtilisateur(id_utilisateur);
                    } catch (BusinessException businessException) {
                        businessException.printStackTrace();
                    }
                }
                listeEncheresConcat.addAll(listeEncheresGagne);
                listeEncheresConcat.addAll(listeEncheresEnCours);
                for (Enchere enchere : listeEncheresConcat){
                    try {
                        Article article = articleManager.selectAllById(enchere.getId_article());
                        listeArticles.add(article);
                    } catch (BusinessException businessException) {
                        businessException.printStackTrace();
                    }
                }
            }
        }else if (bouton_radio.equals("ventes")){
            if(encours != null){
                listeEtatVente.add(EtatVente.EN_COURS);
            }
            if (nondebut != null){
                listeEtatVente.add(EtatVente.CREE);
            }
            if (terminees != null) {
                listeEtatVente.add(EtatVente.ENCHERES_TERMINEES);
            }
        }
        try {
            listeArticles = articleManager.selectcionnerParVendeurNomCategorieEtat(id_utilisateur,recherche_nom,categories,listeEtatVente);
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
        rd.forward(request,response);
    }
}
