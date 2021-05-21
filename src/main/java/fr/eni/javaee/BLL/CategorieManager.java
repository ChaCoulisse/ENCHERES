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
        return categorie;
    }

    public static List<Categorie> selectionnerToutesLesCategories() throws BusinessException{
        return categorieDAO.selectAll();
    }

    public static void modifierCategorie(Categorie categorie) throws BusinessException {}

    public static void supprimerCategorie(Categorie categorie) throws BusinessException {}

    private static void validerLibelle(String libelle, BusinessException businessException){}


}
