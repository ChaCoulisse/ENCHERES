package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.Utilisateur;
import fr.eni.javaee.BusinessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDAOJdbcImpl implements UtilisateursDAO{


    public static final String INSERT_LISTE = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville ,mot_de_passe, credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
    //public static final String SELECT_ALL = "SELECT * FROM UTILISATEURS;";
    public static final String SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE id_utilisateur = ?;";
    public static final String SELECT_BY_PSEUDO ="SELECT * FROM UTILISATEURS WHERE pseudo = ?;";
    public static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE id_utilisateur = ?;";


    @Override
    public Utilisateur insert(Utilisateur utilisateur) throws BusinessException{

        if(utilisateur ==null)
        {
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.INSERT_OBJECT_NULL);
            throw businessException;
        }

        try(Connection cnx = ConnectionProvider.getConnection()) {


            try {
                cnx.setAutoCommit(false);
                PreparedStatement pstmt;
                ResultSet rs;
                if (utilisateur.getId_utilisateur() == null) {
                    pstmt = cnx.prepareStatement(INSERT_LISTE, PreparedStatement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, utilisateur.getPseudo());
                    pstmt.setString(2, utilisateur.getNom());
                    pstmt.setString(3, utilisateur.getPrenom());
                    pstmt.setString(4, utilisateur.getEmail());
                    pstmt.setString(5, utilisateur.getTelephone());
                    pstmt.setString(6, utilisateur.getRue());
                    pstmt.setString(7, utilisateur.getCp());
                    pstmt.setString(8, utilisateur.getVille());
                    pstmt.setString(9, utilisateur.getMdp());
                    pstmt.setInt(10, 1000);
                    pstmt.setInt(11, 0);
                    pstmt.executeUpdate();
                    rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        utilisateur.setId_utilisateur(rs.getInt(1));
                    }
                    pstmt.executeUpdate();
                    rs.close();
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
            businessException.ajouterErreur(CodesResultatsDAL.INSERT_USER_ECHEC);
            throw businessException;
        }
        return utilisateur;
    }

    @Override
    public void delete (Integer id) {
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    /*
    public List<Utilisateur> selectAll() throws BusinessException {
        List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstm = cnx.prepareStatement(SElECT_ALL);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                listeUtilisateurs.add(new Utilisateur(rs.getInt("id"), rs.getString("pseudo"), rs.getString("nom"),rs.getString("prenom"),
                        rs.getString("email"),rs.getString("telephone"),rs.getString("rue"),rs.getString("cp"),rs.getString("ville"),rs.getString("mdp"),rs.getInt("credit"),rs.getBoolean("administrateur")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_UTILISATEUR_ECHEC);
            throw businessException;
        }
        return listeUtilisateurs;
    }
   */

    public Utilisateur selectById(int id) throws BusinessException{
        Utilisateur utilisateur = new Utilisateur();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                    utilisateur.setId_utilisateur(rs.getInt("id_utilisateur"));
                    utilisateur.setPseudo(rs.getString("pseudo"));
                    utilisateur.setNom(rs.getString("nom"));
                    utilisateur.setPrenom(rs.getString("prenom"));
                    utilisateur.setEmail(rs.getString("email"));
                    utilisateur.setTelephone(rs.getString("telephone"));
                    utilisateur.setRue(rs.getString("rue"));
                    utilisateur.setCp(rs.getString("code_postal"));
                    utilisateur.setVille(rs.getString("ville"));
                    utilisateur.setMdp(rs.getString("mot_de_passe"));
                    utilisateur.setCredit(rs.getInt("credit"));
                    utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_UTILISATEUR_ECHEC);
            throw businessException;
        }
        return utilisateur;
    }

    public Utilisateur selectByPseudo(String pseudo) throws BusinessException{
        Utilisateur utilisateur = new Utilisateur();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
            pstmt.setString(1,pseudo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                    utilisateur.setId_utilisateur(rs.getInt("id_utilisateur"));
                    utilisateur.setPseudo(rs.getString("pseudo"));
                    utilisateur.setNom(rs.getString("nom"));
                    utilisateur.setPrenom(rs.getString("prenom"));
                    utilisateur.setEmail(rs.getString("email"));
                    utilisateur.setTelephone(rs.getString("telephone"));
                    utilisateur.setRue(rs.getString("rue"));
                    utilisateur.setCp(rs.getString("code_postal"));
                    utilisateur.setVille(rs.getString("ville"));
                    utilisateur.setMdp(rs.getString("mot_de_passe"));
                    utilisateur.setCredit(rs.getInt("credit"));
                    utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_UTILISATEUR_ECHEC);
            throw businessException;
        }
        return utilisateur;
    }

}