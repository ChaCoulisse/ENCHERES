/**
 * @author ROUILLY Charlotte
 */


package fr.eni.javaee.BLL;

import fr.eni.javaee.BO.Categorie;
import fr.eni.javaee.BusinessException;
import fr.eni.javaee.DAL.CategorieDAO;
import fr.eni.javaee.DAL.CategorieDAOJdbcImpl;

import java.util.List;

public class CategorieManager {
    private static CategorieDAO categorieDAO = new CategorieDAOJdbcImpl();
    private static BusinessException businessException = new BusinessException();
    private static String libelle;


    public CategorieManager () {
    }

    public static Categorie ajouterCategorie (Categorie categorie) throws BusinessException{
        if (libelle == null || libelle.trim().equals(""))
        {
            businessException.ajouterErreur(CodesResultatsBLL.REGLE_CATEGORIES_LIBELLE_ERREUR);
        }
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

    public static Categorie selectionnerCategorieById(int id) throws BusinessException{
        return categorieDAO.selectById(id);
    }

    public static void modifierCategorie(Categorie categorie) throws BusinessException {
        if (libelle == null || libelle.trim().equals(""))
        {
            businessException.ajouterErreur(CodesResultatsBLL.REGLE_CATEGORIES_LIBELLE_ERREUR);
        }

            if (!businessException.hasErreurs()) {
                categorieDAO.update(categorie);
            }
    }

    public static void supprimerCategorie(Categorie categorie) throws BusinessException {
        if (libelle == null || libelle.trim().equals(""))
        {
            businessException.ajouterErreur(CodesResultatsBLL.REGLE_CATEGORIES_LIBELLE_ERREUR);
        }

        if (!businessException.hasErreurs()) {
            categorieDAO.delete(categorie.getId());
        }
    }


}
