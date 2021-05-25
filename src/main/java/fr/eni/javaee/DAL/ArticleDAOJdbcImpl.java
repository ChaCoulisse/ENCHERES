package fr.eni.javaee.DAL;

import com.oracle.wls.shaded.org.apache.regexp.RE;
import fr.eni.javaee.BO.Article;
import fr.eni.javaee.BO.EtatVente;
import fr.eni.javaee.BO.Retrait;
import fr.eni.javaee.BusinessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl implements ArticleDAO{

    public static final String INSERT_ARTICLE = "INSERT INTO " +
            "ARTICLES(no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)" +
            " VALUES (?,?,?,?,?,?,?,?,?);";
    public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES WHERE id = ? ";

    public static final String SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES WHERE no_utilisateur = ? AND nom_article LIKE ? ;";
    public static final String SELECT_ALL_ARTICLES_CREES = "SELECT * FROM ARTICLES WHERE date_debut_encheres >= SYSDATETIME() AND no_utilisateur = ? AND nom_article LIKE ? ;";
    public static final String SELECT_ALL_ARTICLES_EN_COURS = "SELECT * FROM ARTICLES WHERE SYSDATETIME() >=  date_debut_encheres AND SYSDATETIME() <= date_fin_encheres AND no_utilisateur = ? AND nom_article LIKE ? ;";
    public static final String SELECT_ALL_ARTICLES_TERMINES = "SELECT * FROM ARTICLES WHERE date_fin_encheres <= SYSDATETIME() AND no_utilisateur = ? AND article.nom LIKE ? ;";
    public static final String SELECT_BY_CATEGORIE_ARTICLES = "SELECT * FROM ARTICLES, CATEGORIES WHERE articles.no_categorie=categories.no_categorie  AND no_utilisateur = ? AND nom_article LIKE ?  AND categorie = ?;";
    public static final String SELECT_BY_CATEGORIE_ARTICLES_CREES = "SELECT * FROM ARTICLES, CATEGORIES WHERE articles.no_categorie=categories.no_categorie AND date_debut_encheres >= SYSDATETIME() AND no_utilisateur = ? AND nom_article LIKE ?  AND categorie = ?;";
    public static final String SELECT_BY_CATEGORIE_ARTICLES_EN_COURS = "SELECT * FROM ARTICLES, CATEGORIES WHERE articles.no_categorie=categories.no_categorie AND SYSDATETIME() >=  date_debut_encheres AND SYSDATETIME() <= date_fin_encheres AND no_utilisateur = ? AND nom_article LIKE ? AND categorie = ?;";
    public static final String SELECT_BY_CATEGORIE_ARTICLES_TERMINES = "SELECT * FROM ARTICLES, CATEGORIES WHERE articles.no_categorie=categories.no_categorie AND date_fin_encheres <= SYSDATETIME() AND no_utilisateur = ? AND nom_article.nom LIKE ? AND categorie = ?;";


    @Override
    public void insert (Article article) throws BusinessException {
        if(article==null)
        {
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.INSERT_OBJECT_NULL);
            throw businessException;
        }

        try(Connection cnx = ConnectionProvider.getConnection()) {
            try {
                cnx.setAutoCommit(false);
                PreparedStatement pstmt;
                if (article.getId() == null) {
                    pstmt = cnx.prepareStatement(INSERT_ARTICLE);
                    pstmt.setString(1, article.getNom());
                    pstmt.setString(2, article.getDescription());
                    pstmt.setDate(3, (Date) article.getDebutEnchere());
                    pstmt.setDate(4, (Date) article.getFinEnchere());
                    pstmt.setInt(5, article.getPrixInitial());
                    pstmt.setInt(6, article.getPrixVente());
                    pstmt.setInt(7, article.getVendeur());
                    pstmt.setInt(8, article.getCategorie());
                    pstmt.executeUpdate();
                    pstmt.close();
                }
                cnx.commit();
            } catch (Exception e){
                e.printStackTrace();
                cnx.rollback();
                throw e;
            }
        } catch(Exception e){
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.INSERT_ARTICLE_ECHEC);
            throw businessException;
        }
    }

    @Override
    public void delete (Integer id) throws BusinessException {
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Article> selectAll () throws BusinessException {
        return null;
    }

    @Override
    public Article selectById (int id) throws BusinessException {
        return null;
    }

    @Override
    public void update (Article article) throws BusinessException {

    }

    @Override
    public List<Article> getByVendeur (int id) throws BusinessException {
        return null;
    }

    @Override
    public List<Article> getByRetrait (Retrait retrait) throws BusinessException {
        return null;
    }

    public List<Article> getByVendeurNomCategorieEtat(int idVendeur,
                                             String ce_que_larticle_doit_contenir,
                                             String categorie,
                                             EtatVente etatVente) throws BusinessException{
        List<Article> listeArticles = new ArrayList<Article>();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            if (!categorie.equals("tout")) {
                if (etatVente == null) {
                    pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
                } else if (etatVente == EtatVente.CREE) {
                    pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES_CREES);
                } else if (etatVente == EtatVente.EN_COURS) {
                    pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES_EN_COURS);
                } else if (etatVente == EtatVente.ENCHERES_TERMINEES) {
                    pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES_TERMINES);
                }
                pstmt.setInt(1, idVendeur);
                pstmt.setString(2, "%" + ce_que_larticle_doit_contenir + "%");
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    listeArticles.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
                            rs.getString("description"), (java.util.Date) rs.getDate("date_debut_encheres"),
                            (java.util.Date) rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"),
                            rs.getInt("prix_vente"), rs.getInt("no_utilistateur"), etatVente, rs.getInt("no_categorie")));
                }
            } else {
                if (etatVente == null) {
                    pstmt = cnx.prepareStatement(SELECT_BY_CATEGORIE_ARTICLES);
                } else if (etatVente == EtatVente.CREE) {
                    pstmt = cnx.prepareStatement(SELECT_BY_CATEGORIE_ARTICLES_CREES);
                } else if (etatVente == EtatVente.EN_COURS) {
                    pstmt = cnx.prepareStatement(SELECT_BY_CATEGORIE_ARTICLES_EN_COURS);
                } else if (etatVente == EtatVente.ENCHERES_TERMINEES) {
                    pstmt = cnx.prepareStatement(SELECT_BY_CATEGORIE_ARTICLES_TERMINES);
                }
                pstmt.setInt(1, idVendeur);
                pstmt.setString(2, "%" + ce_que_larticle_doit_contenir + "%");
                pstmt.setString(3, categorie);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    listeArticles.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
                            rs.getString("description"), (java.util.Date) rs.getDate("date_debut_encheres"),
                            (java.util.Date) rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"),
                            rs.getInt("prix_vente"), rs.getInt("no_utilistateur"), etatVente, rs.getInt("no_categorie")));
                }
            }
            pstmt.close();
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_UTILISATEUR_ECHEC);
            throw businessException;
        }
        return listeArticles;
    }

}
