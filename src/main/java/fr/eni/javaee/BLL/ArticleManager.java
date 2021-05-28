/**
 * @author ROUILLY Charlotte
 */


package fr.eni.javaee.BLL;

import fr.eni.javaee.BO.Article;
import fr.eni.javaee.BO.EtatVente;
import fr.eni.javaee.BO.Retrait;
import fr.eni.javaee.BusinessException;
import fr.eni.javaee.DAL.ArticleDAO;
import fr.eni.javaee.DAL.ArticleDAOJdbcImpl;
import fr.eni.javaee.DAL.DAOFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ArticleManager {
    private static ArticleDAO articleDAO = new ArticleDAOJdbcImpl();
    private static Article article = new Article();
    private static BusinessException businessException = new BusinessException();
    private static EtatVente etatVente;

    public ArticleManager () {
    }

    public static Article nouvelleVente(Article article) throws BusinessException{
        Date date = new Date(System.currentTimeMillis());
        if (article.getDebutEnchere() == null || article.getFinEnchere() == null || article.getDebutEnchere().before(date) ||
                article.getFinEnchere().before(article.getDebutEnchere()))
        {
            businessException.ajouterErreur(CodesResultatsBLL.REGLE_ENCHERES_DATE_ERREUR);
        }
        if (!businessException.hasErreurs()) {
            articleDAO.insert(article);
        } else {
            throw businessException;
        }
        return article;
    }

    public static Article selectById (int id) throws BusinessException {
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

    public static List<Article> selectcionnerParVendeurNomCategorieEtat(Integer idVendeur,
                                                                        String ce_que_larticle_doit_contenir,
                                                                        String categorie,
                                                                        List<EtatVente> listeEtatVente) throws BusinessException{
        return articleDAO.getByVendeurNomCategorieEtat(idVendeur, ce_que_larticle_doit_contenir, categorie, listeEtatVente);
    }

    public static List<Article> selectionnerParNomCategorieEnCours(String ce_que_larticle_doit_contenir,
                                                                   String categorie) throws BusinessException{
        return articleDAO.getByNomcategorieEnCours(ce_que_larticle_doit_contenir,categorie);
    }
}