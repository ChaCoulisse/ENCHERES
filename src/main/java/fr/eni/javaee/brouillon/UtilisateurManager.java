package fr.eni.javaee.brouillon;

import fr.eni.javaee.BLL.CodesResultatsBLL;
import fr.eni.javaee.BO.Utilisateurs;
import fr.eni.javaee.BusinessException;
import fr.eni.javaee.DAL.DAOFactory;
import fr.eni.javaee.DAL.UtilisateurDAOJdbcImpl;
import fr.eni.javaee.DAL.UtilisateursDAO;

import java.util.List;

public class UtilisateurManager {

    private static UtilisateursDAO utilisateursDAO = new UtilisateurDAOJdbcImpl();
    private static BusinessException businessException = new BusinessException();

    public UtilisateurManager () {
        utilisateursDAO = DAOFactory.getUtilisateurDAO();
    }

    public Utilisateurs inscriptionUtilisateur(Utilisateurs utilisateur) throws BusinessException{
            validerInformations(utilisateur);

        if(!businessException.hasErreurs()) {
            utilisateursDAO.insert(utilisateur);
        }else {
            throw businessException;
        }
        return utilisateur;
    }

    private void validerInformations (Utilisateurs utilisateur) {
        if (utilisateur.getPseudo().trim().equals("") || utilisateur.getNom().trim().equals("")
                || utilisateur.getPrenom().trim().equals("") || utilisateur.getEmail().trim().equals("")
                || utilisateur.getRue().trim().equals("") || utilisateur.getCp().trim().equals("")
                || utilisateur.getVille().trim().equals("") || utilisateur.getMdp().trim().equals("")) {

            businessException.ajouterErreur(CodesResultatsBLL.REGLE_UTILISATEURS_VALIDATION_ERREUR);
        }
    }

    public void delete(Integer id) throws BusinessException {}

    public List<Utilisateurs> selectAll() throws BusinessException{
    return utilisateursDAO.selectAll();
    }

    public Utilisateurs selectById(int id) throws BusinessException{
    return utilisateursDAO.selectById(id);
    }

    public Utilisateurs selectByPseudo(String pseudo) throws BusinessException{
    return utilisateursDAO.selectByPseudo(pseudo);
    }
}