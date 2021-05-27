package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.Categorie;
import fr.eni.javaee.BusinessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CategorieDAOJdbcImpl implements CategorieDAO {
    public static final String INSERT = "INSERT into CATEGORIES(libelle) VALUES (?);";
    public static final String SElECT_ALL = "SELECT * FROM CATEGORIES;";
    public static final String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE id_categorie = ?;";
    private static final String UPDATE = "UPDATE CATEGORIES SET libelle = ? WHERE id_categorie=?;";
    private static final String DELETE = "DELETE CATEGORIES WHERE id_categorie=?;";
    private static final String SELECT_BY_LIBELLE = "SELECT * FROM CATEGORIES WHERE libelle=?;";


    @Override
    public Categorie insert (Categorie categorie) throws BusinessException {
        if (categorie == null) {
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.INSERT_OBJECT_NULL);
            throw businessException;
        }
        try (Connection cnx = ConnectionProvider.getConnection()) {
            try {
                cnx.setAutoCommit(false);
                PreparedStatement pstmt;
                ResultSet rs;
                pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, categorie.getLibelle());

                pstmt.executeUpdate();

                rs = pstmt.getGeneratedKeys();

                if (rs.next()) {
                    categorie.setId(rs.getInt(1));
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
        return categorie;
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
    public List<Categorie> selectAll () throws BusinessException {
        List<Categorie> listeCategorie = new ArrayList<Categorie>();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstm = cnx.prepareStatement(SElECT_ALL);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listeCategorie.add(new Categorie(rs.getInt("id_categorie"), rs.getString("libelle")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_CATEGORIE_ECHEC);
            throw businessException;
        }
        return listeCategorie;
    }

    @Override
    public Categorie selectById (int id) throws BusinessException {
        Categorie categorie = null;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstm = cnx.prepareStatement(SELECT_BY_ID);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                categorie = new Categorie();
                categorie.setId(rs.getInt("id"));
                categorie.setLibelle(rs.getString("libelle"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_CATEGORIE_ECHEC);
            throw businessException;
        }
        return categorie;
    }

    @Override
    public void update (Categorie categorie) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection()) {

            PreparedStatement pstm = cnx.prepareStatement(UPDATE);

            pstm.setInt(1, categorie.getId());
            pstm.setString(2, categorie.getLibelle());

            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.UPDATE_OBJET_ECHEC);
            throw businessException;

        }

    }

    public Categorie selectByLibelle (String libelle) throws BusinessException {
        Categorie categorie = null;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstm = cnx.prepareStatement(SELECT_BY_LIBELLE);
            pstm.setString(1,libelle);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                categorie = new Categorie();
                categorie.setId(rs.getInt("id"));
                categorie.setLibelle(rs.getString("libelle"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_LIBELLE_ECHEC);
            throw businessException;
        }
        return categorie;
    }
}
