package fr.eni.javaee.BLL;

import fr.eni.javaee.BO.Utilisateur;
import fr.eni.javaee.BusinessException;
import fr.eni.javaee.DAL.DAOFactory;
import fr.eni.javaee.DAL.UtilisateursDAO;

public class ServiceConnexion {

    private UtilisateursDAO utilisateursDAO;

    public ServiceConnexion(){
    this.utilisateursDAO = DAOFactory.getUtilisateurDAO();
    }

    public Boolean validerConnexion(String pseudo, String motDePasse) throws BusinessException {

        BusinessException businessException = new BusinessException();

        Utilisateur utilisateur = new Utilisateur();
        utilisateur = utilisateursDAO.selectByPseudo(pseudo);

        if (utilisateur != null && motDePasse != null && !motDePasse.equals(utilisateur.getMdp())) {
            businessException.ajouterErreur(CodesResultatsBLL.REGLE_CONNEXION);
        }

        if (!businessException.hasErreurs()) {
            return true;
        } else {
            return false;
        }
    }
}
