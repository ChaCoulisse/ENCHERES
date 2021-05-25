package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.Enchere;
import fr.eni.javaee.BO.Retrait;
import fr.eni.javaee.BusinessException;

import java.util.List;

public interface EnchereDAO {
    public Enchere insert(Enchere enchere) throws BusinessException;
     public void delete(Integer id) throws BusinessException;
    public List<Enchere> selectAll() throws BusinessException;
    public void update (Enchere enchere)throws BusinessException;
    public List<Enchere> selectByUtilisateur(int id) throws BusinessException;
    public List<Enchere> selectByGagnerParUtilisateur (int id) throws BusinessException;
    public List<Enchere> selectAllByArticle(int id) throws BusinessException;
}
