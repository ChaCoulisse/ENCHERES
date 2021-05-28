/**
 * @author LY Txu-Feng
 */


package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.*;
import fr.eni.javaee.BusinessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ArticleDAOJdbcImpl implements ArticleDAO{
    // requete pour la methode insert
    public static final String INSERT_ARTICLE = "INSERT INTO " +
            "ARTICLES(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, id_utilisateur, id_categorie)" +
            " VALUES (?,?,?,?,?,?,?,?);";
    // requete pour la methode delete
    public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES WHERE id_article = ?;";

    public static final String SELECT_BY_ID ="SELECT * FROM ARTICLES WHERE id_article = ?;";
    // pour la methode update
    public static final String UPDATE_ARTICLE= "UPDATE ARTICLES SET" +
            " nom_article = ?, description = ?, date_debut_encheres = ?," +
            " date_fin_encheres = ?, prix_initial = ?, prix_vente = ?," +
            " id_utilisateur = ?, id_categorie WHERE id_article = ?;";

    //pour la methode getByVendeurCategorieEtat
    public static final String SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES WHERE id_utilisateur = ? ORDER BY date_debut_encheres;";
    public static final String SELECT_ALL_ARTICLES_CREES = "SELECT * FROM ARTICLES WHERE date_debut_encheres >= SYSDATETIME() AND id_utilisateur = ?";
    public static final String SELECT_ALL_ARTICLES_EN_COURS = "SELECT * FROM ARTICLES WHERE SYSDATETIME() >=  date_debut_encheres AND SYSDATETIME() <= date_fin_encheres AND id_utilisateur = ?";
    public static final String SELECT_ALL_ARTICLES_TERMINES = "SELECT * FROM ARTICLES WHERE date_fin_encheres <= SYSDATETIME() AND id_utilisateur = ?";
    public static final String SELECT_BY_CATEGORIE_ARTICLES = "SELECT * FROM ARTICLES, CATEGORIES WHERE articles.id_categorie=categories.id_categorie  AND id_utilisateur = ? AND libelle = ? ORDER BY date_debut_encheres;";
    public static final String SELECT_BY_CATEGORIE_ARTICLES_CREES = "SELECT * FROM ARTICLES, CATEGORIES WHERE articles.id_categorie=categories.id_categorie AND date_debut_encheres >= SYSDATETIME() AND id_utilisateur = ? AND libelle = ?";
    public static final String SELECT_BY_CATEGORIE_ARTICLES_EN_COURS = "SELECT * FROM ARTICLES, CATEGORIES WHERE articles.id_categorie=categories.id_categorie AND SYSDATETIME() >=  date_debut_encheres AND SYSDATETIME() <= date_fin_encheres AND id_utilisateur = ? AND libelle = ?";
    public static final String SELECT_BY_CATEGORIE_ARTICLES_TERMINES = "SELECT * FROM ARTICLES, CATEGORIES WHERE articles.id_categorie=categories.id_categorie AND date_fin_encheres <= SYSDATETIME() AND id_utilisateur = ? AND libelle = ?";

    // pour la methode GetByNomCategorieEnCours
    public static final String SELECT  = "SELECT * FROM ARTICLES WHERE SYSDATETIME() >=  date_debut_encheres AND SYSDATETIME() <= date_fin_encheres";
    public static final String SELECT_CATEGORIE = "SELECT * FROM ARTICLES, CATEGORIES WHERE articles.id_categorie=categories.id_categorie AND libelle = ? AND SYSDATETIME() >=  date_debut_encheres AND SYSDATETIME() <= date_fin_encheres";

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
                if (article.getId_article() == null) {
                    pstmt = cnx.prepareStatement(INSERT_ARTICLE);
                    pstmt.setString(1, article.getNom());
                    pstmt.setString(2, article.getDescription());
                    pstmt.setDate(3, (java.sql.Date) article.getDebutEnchere());
                    pstmt.setDate(4, (java.sql.Date) article.getFinEnchere());
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
    public Article selectById (int id) throws BusinessException {
        Article article = new Article();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            boolean premiereLigne = true;
            while (rs.next()){
                    article.setId_article(rs.getInt(1));
                    article.setNom(rs.getString(2));
                    article.setDescription(rs.getString(3));
                    article.setDebutEnchere(rs.getDate(4));
                    article.setFinEnchere(rs.getDate(5));
                    article.setPrixInitial(rs.getInt(6));
                    article.setPrixVente(rs.getInt(7));
                    article.setVendeur(rs.getInt(8));
                    article.setCategorie(rs.getInt(9));
                    Date date_actuelle = new Date();
                    Date date_debut = rs.getDate("date_debut_encheres");
                    Date date_fin = rs.getDate("date_fin_encheres");
                    EtatVente etatVente = null;
                    if (date_actuelle.before(date_debut)){
                        etatVente = EtatVente.CREE;
                    } else if (date_actuelle.after(date_fin)){
                        etatVente = EtatVente.ENCHERES_TERMINEES;
                    } else if (date_actuelle.after(date_debut) && date_actuelle.before(date_fin)){
                        etatVente = EtatVente.EN_COURS;
                    }
                    article.setEtatVente(etatVente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_UTILISATEUR_ECHEC);
            throw businessException;
        }
        return article;
    }

    @Override
    public void update (Article article) throws BusinessException {
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
            pstmt.setString(1, article.getNom());
            pstmt.setString(2, article.getDescription());
            pstmt.setDate(3, (java.sql.Date) article.getDebutEnchere());
            pstmt.setDate(4, (java.sql.Date) article.getFinEnchere());
            pstmt.setInt(5, article.getPrixInitial());
            pstmt.setInt(6, article.getPrixVente());
            pstmt.setInt(7, article.getVendeur());
            pstmt.setInt(8, article.getCategorie());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.UPDATE_OBJET_ECHEC);
            throw businessException;
        }
    }

    public List<Article> getByVendeurNomCategorieEtat(int idVendeur,
                                             String ce_que_larticle_doit_contenir,
                                             String categorie,
                                             List<EtatVente>listeEtatVente) throws BusinessException{
        List<Article> listeArticles = new ArrayList<Article>();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String rqt = "";
            if (categorie.equals("toutes")) {
                if (listeEtatVente == null || listeEtatVente.size()==0) {
                    pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
                } else {
                    for (int i = 0; i < listeEtatVente.size(); i++){
                        if(i>0){
                            rqt += " UNION ";
                        }
                        else if (listeEtatVente.get(i) == EtatVente.CREE) {
                            rqt += SELECT_ALL_ARTICLES_CREES;
                            if(ce_que_larticle_doit_contenir != null && !ce_que_larticle_doit_contenir.equals("")){
                                rqt += " AND nom_article LIKE ?";
                            }
                        } else if (listeEtatVente.get(i) == EtatVente.EN_COURS) {
                            rqt += SELECT_ALL_ARTICLES_EN_COURS;
                            if(ce_que_larticle_doit_contenir != null && !ce_que_larticle_doit_contenir.equals("")){
                                rqt += " AND nom_article LIKE ?";
                            }
                        } else if (listeEtatVente.get(i) == EtatVente.ENCHERES_TERMINEES) {
                            rqt += SELECT_ALL_ARTICLES_TERMINES;
                            if(ce_que_larticle_doit_contenir != null && !ce_que_larticle_doit_contenir.equals("")){
                                rqt += " AND nom_article LIKE ?";
                            }
                        }
                        rqt += " ORDER BY date_debut_encheres;";
                    }
                    pstmt = cnx.prepareStatement(rqt);
                }
                pstmt.setInt(1, idVendeur);
                if(!(ce_que_larticle_doit_contenir == null) && !(ce_que_larticle_doit_contenir.equals(""))) {
                    pstmt.setString(2, "%" + ce_que_larticle_doit_contenir + "%");
                }
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Date date_actuelle = new Date(System.currentTimeMillis());
                    Date date_debut = rs.getDate("date_debut_encheres");
                    Date date_fin = rs.getDate("date_fin_encheres");
                    EtatVente etatVente = null;
                    if (date_actuelle.before(date_debut)){
                        etatVente = EtatVente.CREE;
                    } else if (date_actuelle.after(date_fin)){
                        etatVente = EtatVente.ENCHERES_TERMINEES;
                    } else if (date_actuelle.after(date_debut) && date_actuelle.before(date_fin)){
                        etatVente = EtatVente.EN_COURS;
                    }
                    listeArticles.add(new Article(rs.getInt("id_article"), rs.getString("nom_article"),
                            rs.getString("description"), rs.getDate("date_debut_encheres"),
                            rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"),
                            rs.getInt("prix_vente"), rs.getInt("id_utilisateur"), etatVente , rs.getInt("id_categorie")));
                }
            } else

            //
            //

            {
                if (listeEtatVente == null || listeEtatVente.size()==0) {
                    pstmt = cnx.prepareStatement(SELECT_BY_CATEGORIE_ARTICLES);
                } else {
                    for (int i = 0; i < listeEtatVente.size(); i++){
                        if(i>0){
                            rqt += " UNION ";
                        }
                        else if (listeEtatVente.get(i) == EtatVente.CREE) {
                            rqt += SELECT_BY_CATEGORIE_ARTICLES_CREES;
                            if(ce_que_larticle_doit_contenir != null && !ce_que_larticle_doit_contenir.equals("")){
                                rqt += " AND nom_article LIKE ?";
                            }
                        } else if (listeEtatVente.get(i) == EtatVente.EN_COURS) {
                            rqt += SELECT_BY_CATEGORIE_ARTICLES_EN_COURS;
                            if(ce_que_larticle_doit_contenir != null && !ce_que_larticle_doit_contenir.equals("")){
                                rqt += " AND nom_article LIKE ?";
                            }
                        } else if (listeEtatVente.get(i) == EtatVente.ENCHERES_TERMINEES) {
                            rqt += SELECT_BY_CATEGORIE_ARTICLES_TERMINES;
                            if(ce_que_larticle_doit_contenir != null && !ce_que_larticle_doit_contenir.equals("")){
                                rqt += " AND nom_article LIKE ?";
                            }
                        }
                        rqt += " ORDER BY date_debut_encheres;";
                    }
                    pstmt = cnx.prepareStatement(rqt);
                }
                pstmt.setInt(1, idVendeur);
                pstmt.setString(2, categorie);
                if(!(ce_que_larticle_doit_contenir == null) && !(ce_que_larticle_doit_contenir.equals(""))) {
                    pstmt.setString(3, "%" + ce_que_larticle_doit_contenir + "%");
                }
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Date date_actuelle = new Date(System.currentTimeMillis());
                    Date date_debut = rs.getDate("date_debut_encheres");
                    Date date_fin = rs.getDate("date_fin_encheres");
                    EtatVente etatVente = null;
                    if (date_actuelle.before(date_debut)){
                        etatVente = EtatVente.CREE;
                    } else if (date_actuelle.after(date_fin)){
                        etatVente = EtatVente.ENCHERES_TERMINEES;
                    } else if (date_actuelle.after(date_debut) && date_actuelle.before(date_fin)){
                        etatVente = EtatVente.EN_COURS;
                    }
                    listeArticles.add(new Article(rs.getInt("id_article"), rs.getString("nom_article"),
                            rs.getString("description"), rs.getDate("date_debut_encheres"),
                            rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"),
                            rs.getInt("prix_vente"), rs.getInt("id_utilisateur"), etatVente , rs.getInt("id_categorie")));
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

    @Override
    public List<Article> getByNomcategorieEnCours(String ce_que_larticle_doit_contenir, String categorie) {
        List<Article> listeArticles = new ArrayList<Article>();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String rqt = "";
            if (!categorie.equals("toutes")) {
                rqt = SELECT;
                if(ce_que_larticle_doit_contenir != null && !ce_que_larticle_doit_contenir.equals("")){
                    rqt += " AND nom_article LIKE ? ;";
                }
                pstmt = cnx.prepareStatement(rqt);

            } else{
                rqt = SELECT_CATEGORIE;
                if(ce_que_larticle_doit_contenir != null && !ce_que_larticle_doit_contenir.equals("")){
                    rqt += " AND nom_article LIKE ? ;";
                }
                pstmt = cnx.prepareStatement(rqt);
                pstmt.setString(1, categorie);
                if(ce_que_larticle_doit_contenir != null && !ce_que_larticle_doit_contenir.equals("")){
                    pstmt.setString(2,"%"+ce_que_larticle_doit_contenir+"%");
                }
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                listeArticles.add(new Article(rs.getInt("id_article"), rs.getString("nom_article"),
                        rs.getString("description"),  rs.getDate("date_debut_encheres"),
                        rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"),
                        rs.getInt("prix_vente"), rs.getInt("id_utilisateur"), EtatVente.EN_COURS , rs.getInt("id_categorie")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listeArticles;
    }


}
