package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.Enchere;
import fr.eni.javaee.BusinessException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EnchereDAOJdbcImpl implements EnchereDAO{

    // Dans la database, dans la table Encheres  il faut rajouter id(int, auto increment) + gagner(boolean/bit) + modification des colonnes no_? en id_? (article, utilisateur)

    public static final String INSERT = "INSERT into ENCHERES(id_utilisateur,id_article,date_enchere,montant_enchere,gagner) VALUES (?,?,?,?,?)";
    public static final String SElECT_ALL = "SELECT * FROM ENCHERES";
    public static final String SELECT_BY_ID = "SELECT * FROM ENCHERES WHERE id= ?";
    private static final String UPDATE = "UPDATE ENCHERES SET id_utilisateur= ?, id_article = ?,date_enchere = ?, " +
                                             "montant_enchere= ?, gagner=? WHERE id=?";
    private static final String DELETE = "DELETE ENCHERES WHERE id=?";
    private static final String SELECT_BY_UTILISATEUR = "SELECT * FROM ENCHERES WHERE id_utilisateur=?";
    private static final String SELECT_BY_GAGNER_PAR_UTILISATEUR = "SELECT * FROM ENCHERES WHERE id_utilisateur = ? AND gagner=?";
    private static final String SELECT_ALL_BY_ARTICLE = " SELECT * FROM ENCHERES WHERE id_article = ?";


    @Override
    public Enchere insert (Enchere enchere) throws BusinessException {
        if (enchere == null) {
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.INSERT_OBJECT_NULL);
            throw businessException;
        }
        try (Connection cnx = ConnectionProvider.getConnection()) {
            try {
                cnx.setAutoCommit(false);
                PreparedStatement pstmt;
                ResultSet rs;
        // L'utilisateur doit correspondre au à celui qui encherit sur l'objet (méthodes adéquates dans code)
        // Le nom doit être modifié en conséquence

                pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, enchere.getId_utilisateur());
                pstmt.setInt(2, enchere.getId_article());
                pstmt.setDate(3,Date.valueOf(enchere.getDateEnchere()));
                pstmt.setInt(4, enchere.getMontantEnchere());
                pstmt.setBoolean(5, enchere.isGagner());

                pstmt.executeUpdate();

                rs = pstmt.getGeneratedKeys();

                if (rs.next()) {
                    enchere.setId(rs.getInt(1));
                }
            } catch (Exception e) {
                e.printStackTrace();
                cnx.rollback();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.INSERT_USER_ECHEC);
            throw businessException;
        }
        return enchere;
    }

    @Override
    public void delete (Integer id) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection()) {

            PreparedStatement pstmt = cnx.prepareStatement(DELETE);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.DELETE_OBJET_ECHEC);
            throw businessException;

        }
    }

    @Override
    public List<Enchere> selectAll () throws BusinessException {
        List<Enchere> listeEncheres = new ArrayList<Enchere>();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstm = cnx.prepareStatement(SElECT_ALL);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listeEncheres.add(new Enchere(rs.getInt("id"), rs.getInt("id_utilisateur"),rs.getInt("id_article"),
                        rs.getDate("date_enchere").toLocalDate(),rs.getInt("montant_enchere"),rs.getBoolean("gagner")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_CATEGORIE_ECHEC);
            throw businessException;
        }
        return listeEncheres;
    }

    @Override
    public Enchere selectById (int id) throws BusinessException {
        Enchere enchere = null;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstm = cnx.prepareStatement(SELECT_BY_ID);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                enchere = new Enchere();
                enchere.setId(rs.getInt("id"));
                enchere.setId_utilisateur(rs.getInt("id_utilisateur"));
                enchere.setId_article(rs.getInt("id_article"));
                enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
                enchere.setMontantEnchere(rs.getInt("montant_enchere"));
                enchere.setGagner(rs.getBoolean("gagner"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_RETRAIT_ECHEC);
            throw businessException;
        }
        return enchere;
    }

    @Override
    public void update (Enchere enchere) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection()) {

            PreparedStatement pstm = cnx.prepareStatement(UPDATE);

            pstm.setInt(1, enchere.getId());
            pstm.setInt(2, enchere.getId_utilisateur());
            pstm.setInt(3, enchere.getId_article());
            pstm.setDate(4, Date.valueOf(enchere.getDateEnchere()));
            pstm.setInt(5, enchere.getMontantEnchere());
            pstm.setBoolean(6, enchere.isGagner());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.UPDATE_OBJET_ECHEC);
            throw businessException;

        }
    }

    @Override
    public List<Enchere> selectByUtilisateur (int id) throws BusinessException {
        List<Enchere> listeEncheres = new ArrayList<>();

        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstm = cnx.prepareStatement(SELECT_BY_UTILISATEUR);
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
               Enchere enchere = new Enchere();
                enchere.setId(rs.getInt("id"));
                enchere.setId_utilisateur(rs.getInt("id_utilisateur"));
                enchere.setId_article(rs.getInt("id_article"));
                enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
                enchere.setMontantEnchere(rs.getInt("montant_enchere"));
                enchere.setGagner(rs.getBoolean("gagner"));

                listeEncheres.add(enchere);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_ENCHERE_ECHEC);
            throw businessException;
        }
        return listeEncheres;

    }

    @Override
    public List<Enchere> selectByGagnerParUtilisateur (int id) throws BusinessException {
        List<Enchere> listeEncheres = new ArrayList<>();

        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstm = cnx.prepareStatement(SELECT_BY_GAGNER_PAR_UTILISATEUR);
            pstm.setInt(1,id);
            pstm.setBoolean(2,true);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Enchere enchere = new Enchere();
                enchere.setId(rs.getInt("id"));
                enchere.setId_utilisateur(rs.getInt("id_utilisateur"));
                enchere.setId_article(rs.getInt("id_article"));
                enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
                enchere.setMontantEnchere(rs.getInt("montant_enchere"));
                enchere.setGagner(rs.getBoolean("gagner"));

                listeEncheres.add(enchere);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_ENCHERE_ECHEC);
            throw businessException;
        }
        return listeEncheres;

    }

    @Override
    public List<Enchere> selectAllByArticle (int id) throws BusinessException {
        List<Enchere> listeEncheres = new ArrayList<>();

        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstm = cnx.prepareStatement(SELECT_ALL_BY_ARTICLE);
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Enchere enchere = new Enchere();
                enchere.setId(rs.getInt("id"));
                enchere.setId_utilisateur(rs.getInt("id_utilisateur"));
                enchere.setId_article(rs.getInt("id_article"));
                enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
                enchere.setMontantEnchere(rs.getInt("montant_enchere"));
                enchere.setGagner(rs.getBoolean("gagner"));

                listeEncheres.add(enchere);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_ENCHERE_ECHEC);
            throw businessException;
        }
        return listeEncheres;
    }
}
