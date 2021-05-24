package fr.eni.javaee.BLL;

import fr.eni.javaee.BO.Enchere;
import fr.eni.javaee.BusinessException;
import fr.eni.javaee.DAL.EnchereDAO;
import fr.eni.javaee.DAL.EnchereDAOJdbcImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

public class EnchereManager {
    private static EnchereDAO enchereDAO = new EnchereDAOJdbcImpl();
    private static BusinessException businessException = new BusinessException();

    public EnchereManager () {
        super();
    }
    
    private static Enchere ajouterEnchere (Enchere enchere) throws BusinessException {

        LocalDateTime date = null;
        if( date.isAfter(ChronoLocalDateTime.from(LocalDate.now())))
        {
            businessException.ajouterErreur(CodesResultatsBLL.REGLE_ENCHERES_DATE_ERREUR);
        }

        if(!businessException.hasErreurs()) {
            enchereDAO.insert(enchere);
        }
        else
        {
            throw businessException;
        }
        return enchere;
    }


}
