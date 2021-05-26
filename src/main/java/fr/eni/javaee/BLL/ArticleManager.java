package fr.eni.javaee.BLL;

import fr.eni.javaee.BO.Article;
import fr.eni.javaee.BO.EtatVente;
import fr.eni.javaee.BO.Retrait;
import fr.eni.javaee.BusinessException;
import fr.eni.javaee.DAL.ArticleDAO;
import fr.eni.javaee.DAL.ArticleDAOJdbcImpl;
import fr.eni.javaee.DAL.DAOFactory;

import java.time.LocalDate;

public class ArticleManager {
    private static ArticleDAO articleDAO = new ArticleDAOJdbcImpl();
    private static Article article = new Article();
    private static BusinessException businessException = new BusinessException();
    private static EtatVente etatVente;

    public ArticleManager () {
    }

    public static Article VendreArticle (Article article, Retrait retrait) throws BusinessException {
        try {
            DAOFactory.getArticleDAO().insert(article);
            retrait.setId_article(article.getId_article());
            retrait = DAOFactory.getRetraitDAO().insert(retrait);
        } catch (BusinessException e) {
            throw new BusinessException();
        }
        return article;
    }


    public static Article selectAllById (int id) throws BusinessException {
        return articleDAO.selectById(id);
    }

    public static void validerEtatVente (EtatVente etatVente) throws BusinessException {
        if (article.getEtatVente() == EtatVente.ENCHERES_TERMINEES
                || article.getEtatVente() == EtatVente.RETRAIT_EFFECTUE) {
            businessException.ajouterErreur(CodesResultatsBLL.REGLE_ARTICLES_ETAT_VENTE_ERREUR);
        }

    }

    public static void supprimerArticle (int id) throws BusinessException{
        validerEtatVente(etatVente);
        if (!businessException.hasErreurs()){
            article.setEtatVente(EtatVente.ANNULE);
        }
        if (article.getEtatVente()==EtatVente.ANNULE){
            articleDAO.delete(article.getId_article());
        }
    }
}