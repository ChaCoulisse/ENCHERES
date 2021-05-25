package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.Utilisateurs;
import fr.eni.javaee.BusinessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UtilisateurDAOJdbcImpl implements UtilisateursDAO{


    public static final String INSERT_USER = "INSERT INTO " +
            "UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville ,mot_de_passe, credit, administrateur)" +
            " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    public static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE id = ? ";
    //public static final String SElECT_ALL = "SELECT * FROM UTILISATEURS";
    public static final String SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
    public static final String SELECT_BY_PSEUDO ="SELECT * FROM UTILISATEURS WHERE pseudo = ?";


    @Override
    public void insert(Utilisateurs utilisateurs) throws BusinessException{

        if(utilisateurs==null)
        {
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.INSERT_OBJECT_NULL);
            throw businessException;
        }

        try(Connection cnx = ConnectionProvider.getConnection()) {
            try {
                cnx.setAutoCommit(false);
                PreparedStatement pstmt;
                if (utilisateurs.getId() == null) {
                    pstmt = cnx.prepareStatement(INSERT_USER);
                    pstmt.setString(1, utilisateurs.getPseudo());
                    pstmt.setString(2, utilisateurs.getNom());
                    pstmt.setString(3, utilisateurs.getPrenom());
                    pstmt.setString(4, utilisateurs.getEmail());
                    pstmt.setString(5, utilisateurs.getTelephone());
                    pstmt.setString(6, utilisateurs.getRue());
                    pstmt.setString(7, utilisateurs.getCp());
                    pstmt.setString(8, utilisateurs.getVille());
                    pstmt.setString(9, utilisateurs.getMdp());
                    pstmt.setInt(10, utilisateurs.getCredit());
                    if(!utilisateurs.isAdministrateur()){
                        pstmt.setInt(11,0);
                    } else {
                        pstmt.setInt(11, 1);
                    }
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
            businessException.ajouterErreur(CodesResultatsDAL.INSERT_USER_ECHEC);
            throw businessException;
        }
    }

    @Override
    public void delete (Integer id) {
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
            pstmt.setInt(0,id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    /*
    public List<Utilisateurs> selectAll() throws BusinessException {
        List<Utilisateurs> listeUtilisateurs = new ArrayList<Utilisateurs>();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstm = cnx.prepareStatement(SElECT_ALL);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                listeUtilisateurs.add(new Utilisateurs(rs.getInt("id"), rs.getString("pseudo"), rs.getString("nom"),rs.getString("prenom"),
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

    public Utilisateurs selectById(int id) throws BusinessException{
        Utilisateurs listeUtilisateur = new Utilisateurs();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            boolean premiereLigne = true;
            while (rs.next()){
                if (premiereLigne){
                    listeUtilisateur.setId(rs.getInt("id"));
                    listeUtilisateur.setPseudo(rs.getString("pseudo"));
                    listeUtilisateur.setNom(rs.getString("nom"));
                    listeUtilisateur.setPrenom(rs.getString("prenom"));
                    listeUtilisateur.setEmail(rs.getString("email"));
                    listeUtilisateur.setTelephone(rs.getString("telephone"));
                    listeUtilisateur.setRue(rs.getString("rue"));
                    listeUtilisateur.setCp(rs.getString("cp"));
                    listeUtilisateur.setVille(rs.getString("ville"));
                    listeUtilisateur.setMdp(rs.getString("mdp"));
                    listeUtilisateur.setCredit(rs.getInt("credit"));
                    listeUtilisateur.setAdministrateur(rs.getBoolean("administrateur"));

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_UTILISATEUR_ECHEC);
            throw businessException;
        }
        return listeUtilisateur;
    }

    public Utilisateurs selectByPseudo(String pseudo) throws BusinessException{
        Utilisateurs listeUtilisateur = new Utilisateurs();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
            pstmt.setString(1,pseudo);
            ResultSet rs = pstmt.executeQuery();
            boolean premiereLigne = true;
            while (rs.next()){
                if (premiereLigne){
                    listeUtilisateur.setId(rs.getInt("no_utilisateur"));
                    listeUtilisateur.setPseudo(rs.getString("pseudo"));
                    listeUtilisateur.setNom(rs.getString("nom"));
                    listeUtilisateur.setPrenom(rs.getString("prenom"));
                    listeUtilisateur.setEmail(rs.getString("email"));
                    listeUtilisateur.setTelephone(rs.getString("telephone"));
                    listeUtilisateur.setRue(rs.getString("rue"));
                    listeUtilisateur.setCp(rs.getString("code_postal"));
                    listeUtilisateur.setVille(rs.getString("ville"));
                    listeUtilisateur.setMdp(rs.getString("mot_de_passe"));
                    listeUtilisateur.setCredit(rs.getInt("credit"));
                    listeUtilisateur.setAdministrateur(rs.getBoolean("administrateur"));

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatsDAL.LECTURE_UTILISATEUR_ECHEC);
            throw businessException;
        }
        return listeUtilisateur;
    }
}