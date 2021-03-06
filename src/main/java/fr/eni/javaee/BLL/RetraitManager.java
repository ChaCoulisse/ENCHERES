/**
 * @author ROUILLY Charlotte
 */


package fr.eni.javaee.BLL;

import com.oracle.wls.shaded.org.apache.bcel.generic.ATHROW;
import fr.eni.javaee.BO.Retrait;
import fr.eni.javaee.BusinessException;
import fr.eni.javaee.DAL.RetraitDAO;
import fr.eni.javaee.DAL.RetraitDAOJdbcImpl;

public class RetraitManager {
    private static RetraitDAO retraitDAO = new RetraitDAOJdbcImpl();
    private static BusinessException businessException = new BusinessException();

    public RetraitManager () {
    }

    public static Retrait ajouterRetrait (Retrait retrait) throws BusinessException{
        if(retrait.getRue() == null || retrait.getCp() == null || retrait.getVille() == null
                || retrait.getRue().trim().equals("") ||  retrait.getCp().trim().equals("")
                || retrait.getVille().trim().equals(""))
        {
            businessException.ajouterErreur(CodesResultatsBLL.REGLE_RETRAITS_ADRESSE_ERREUR);
        }

        if(!businessException.hasErreurs()) {
            retraitDAO.insert(retrait);
        }
        else
        {
            throw businessException;
        }
        return retrait;
    }


    public static Retrait selectionnerRetraitByID(int id) throws BusinessException{
        return retraitDAO.selectById(id);

    }

    public static void modifierRetrait(Retrait retrait) throws BusinessException{
        if(retrait.getRue() == null || retrait.getCp() == null || retrait.getVille() == null
                || retrait.getRue().trim().equals("") ||  retrait.getCp().trim().equals("")
                || retrait.getVille().trim().equals(""))
        {
            businessException.ajouterErreur(CodesResultatsBLL.REGLE_RETRAITS_ADRESSE_ERREUR);
        }

        if (!businessException.hasErreurs()){
            retraitDAO.update(retrait);
        }else{
            throw businessException;
        }
    }
}
