package fr.eni.javaee.BLL;

import fr.eni.javaee.BO.Utilisateurs;
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

        Utilisateurs utilisateurs = new Utilisateurs();
        utilisateurs = utilisateursDAO.selectByPseudo(pseudo);

        if (utilisateurs != null && motDePasse != null && !motDePasse.equals(utilisateurs.getMdp())) {
            businessException.ajouterErreur(CodesResultatsBLL.REGLE_CONNEXION);
        }

        if (!businessException.hasErreurs()) {
            return true;
        } else {
            return false;
        }
    }
}
