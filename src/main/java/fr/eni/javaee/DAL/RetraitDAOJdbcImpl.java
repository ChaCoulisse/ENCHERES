/**
 * @author ROUILLY Charlotte
 */


package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.Retrait;
import fr.eni.javaee.BusinessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RetraitDAOJdbcImpl implements RetraitDAO {

    public static final String INSERT = "INSERT into RETRAITS(id_article, rue, code_postal, ville) VALUES (?,?,?,?);";
    public static final String SElECT_ALL = "SELECT * FROM RETRAITS;";
    public static final String SELECT_BY_ID = "SELECT * FROM RETRAITS WHERE id_article = ?;";
    private static final String UPDATE = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE id_article=?;";
    private static final String DELETE = "DELETE RETRAITS WHERE id_article=?;";


    @Override
    public Retrait insert (Retrait retrait) throws BusinessException {
        if (retrait == null) {
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.INSERT_OBJECT_NULL);
            throw businessException;
        }
        try (Connection cnx = ConnectionProvider.getConnection()) {
            try {
                cnx.setAutoCommit(false);
                PreparedStatement pstmt;
                ResultSet rs;
                pstmt = cnx.prepareStatement(INSERT);
                pstmt.setInt(1, retrait.getId_article());
                pstmt.setString(2, retrait.getRue());
                pstmt.setString(3, retrait.getCp());
                pstmt.setString(4, retrait.getVille());

                pstmt.executeUpdate();

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
        return retrait;
    }

    @Override
    public List<Retrait> selectAll () throws BusinessException {
        List<Retrait> listeRetraits = new ArrayList<Retrait>();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstm = cnx.prepareStatement(SElECT_ALL);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listeRetraits.add(new Retrait(rs.getInt("id_article"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_CATEGORIE_ECHEC);
            throw businessException;
        }
        return listeRetraits;
    }

    @Override
    public Retrait selectById (int id) throws BusinessException {
        Retrait retrait = null;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstm = cnx.prepareStatement(SELECT_BY_ID);
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                retrait = new Retrait();
                retrait.setId_article(rs.getInt("id_article"));
                retrait.setRue(rs.getString("rue"));
                retrait.setCp(rs.getString("code_postal"));
                retrait.setVille(rs.getString("ville"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_RETRAIT_ECHEC);
            throw businessException;
        }
        return retrait;
    }


    @Override
    public void update (Retrait retrait) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection()) {

            PreparedStatement pstm = cnx.prepareStatement(UPDATE);

            pstm.setInt(1, retrait.getId_article());
            pstm.setString(2, retrait.getRue());
            pstm.setString(3, retrait.getCp());
            pstm.setString(4, retrait.getVille());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.UPDATE_OBJET_ECHEC);
            throw businessException;

        }
    }

    // A modifier en fonction des méthodes utilisés dans ArticleDAO

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

}

