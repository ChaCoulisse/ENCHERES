package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.Categorie;
import fr.eni.javaee.BO.Retrait;
import fr.eni.javaee.BusinessException;

import java.util.List;

public interface RetraitDAO {

    public Retrait insert(Retrait retrait) throws BusinessException;
    public void delete(Integer id) throws BusinessException;
    public List<Retrait> selectAll() throws BusinessException;
    public Retrait selectById(int id) throws BusinessException;
    public void update (Retrait retrait)throws BusinessException;
}
