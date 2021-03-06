/**
 * @author ROUILLY Charlotte
 */

package fr.eni.javaee.DAL;

public class DAOFactory {

    public static UtilisateursDAO getUtilisateurDAO () {
        return new UtilisateurDAOJdbcImpl();
    }

    public static CategorieDAO getCategorieDAO () {
        return new CategorieDAOJdbcImpl();
    }

    public static ArticleDAO getArticleDAO () {
        return new ArticleDAOJdbcImpl();
    }

    public static RetraitDAO getRetraitDAO(){return new RetraitDAOJdbcImpl();}

    public static EnchereDAO getEnchereDAO(){return new EnchereDAOJdbcImpl();}

}

