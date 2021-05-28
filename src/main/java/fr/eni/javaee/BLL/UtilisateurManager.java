/**
 * @author ROUILLY Charlotte
 */


package fr.eni.javaee.BLL;

import fr.eni.javaee.BO.Utilisateur;
import fr.eni.javaee.BusinessException;
import fr.eni.javaee.DAL.DAOFactory;
import fr.eni.javaee.DAL.UtilisateurDAOJdbcImpl;
import fr.eni.javaee.DAL.UtilisateursDAO;

import java.util.List;

public class UtilisateurManager {

    private static UtilisateursDAO utilisateursDAO = new UtilisateurDAOJdbcImpl();
    private static Utilisateur utilisateur = new Utilisateur();
    private static BusinessException businessException = new BusinessException();

    public UtilisateurManager () {
        utilisateursDAO = DAOFactory.getUtilisateurDAO();
    }


    public static Utilisateur inscriptionUtilisateur(Utilisateur utilisateur) throws BusinessException{

        if (utilisateur.getPseudo().trim().equals("") || utilisateur.getNom().trim().equals("")
                || utilisateur.getPrenom().trim().equals("") || utilisateur.getEmail().trim().equals("")
                || utilisateur.getRue().trim().equals("") || utilisateur.getCp().trim().equals("")
                || utilisateur.getVille().trim().equals("") || utilisateur.getMdp().trim().equals("")) {

            businessException.ajouterErreur(CodesResultatsBLL.REGLE_UTILISATEURS_VALIDATION_ERREUR);
        }

        if(!businessException.hasErreurs()) {
            utilisateursDAO.insert(utilisateur);
        }else {
            throw businessException;
        }
        return utilisateur;
    }

    public static void delete(Integer id) throws BusinessException {
        utilisateursDAO.delete(id);
    }

    /*public List<Utilisateur> selectAll() throws BusinessException{
    return utilisateursDAO.selectAll();
    }
    */

    public static Utilisateur selectById(int id) throws BusinessException{
        return utilisateursDAO.selectById(id);
    }

    public static Utilisateur selectByPseudo(String pseudo) throws BusinessException{
        return utilisateursDAO.selectByPseudo(pseudo);
    }
}