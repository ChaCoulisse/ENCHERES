/**
 * @author ROUILLY Charlotte
 */


package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.Categorie;
import fr.eni.javaee.BusinessException;

import java.util.List;

public interface CategorieDAO {
    public Categorie insert(Categorie categorie) throws BusinessException;
    public void delete(Integer id) throws BusinessException;
    public List<Categorie> selectAll() throws BusinessException;
    public Categorie selectById(int id) throws BusinessException;
    public void update (Categorie categorie)throws BusinessException;
}
