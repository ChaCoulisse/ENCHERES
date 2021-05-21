package fr.eni.javaee.BLL;

import fr.eni.javaee.BO.Categorie;
import fr.eni.javaee.BusinessException;
import fr.eni.javaee.DAL.CategorieDAO;
import fr.eni.javaee.DAL.CategorieDAOJdbcImpl;

import java.util.List;

public class CategorieManager {
    private static CategorieDAO categorieDAO = new CategorieDAOJdbcImpl();
    private static BusinessException businessException = new BusinessException();

    public CategorieManager () {
    }

    public static Categorie ajouterCategorie (Categorie categorie) throws BusinessException{
        validerLibelle(categorie.getLibelle(), businessException);

        if(!businessException.hasErreurs()) {
            categorieDAO.insert(categorie);
        }
        else
        {
            throw businessException;
        }
        return categorie;
    }


    public static List<Categorie> selectionnerToutesLesCategories() throws BusinessException{
        return categorieDAO.selectAll();
    }

    public Categorie selectionnerCategorieById(int id) throws BusinessException{
        return categorieDAO.selectById(id);
    }

    public static void modifierCategorie(Categorie categorie) throws BusinessException {
            validerLibelle(categorie.getLibelle(), businessException);

            if (!businessException.hasErreurs()) {
                categorieDAO.update(categorie);
            }
    }

    public static void supprimerCategorie(Categorie categorie) throws BusinessException {
        validerLibelle(categorie.getLibelle(), businessException);

        if (!businessException.hasErreurs()) {
            categorieDAO.delete(categorie.getId());
        }
    }

    private static void validerLibelle(String libelle, BusinessException businessException){
        if (libelle == null || libelle.trim().equals(""))
        {
            businessException.ajouterErreur(CodesResultatsBLL.REGLE_CATEGORIES_LIBELLE_ERREUR);
        }
    }

}
