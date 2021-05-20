package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.Utilisateurs;
import fr.eni.javaee.BusinessException;

import java.util.List;

public interface UtilisateursDAO {

    public void insert(Utilisateurs utilisateurs) throws BusinessException; // pour l'inscription
    public void delete(Integer id) throws BusinessException; // admin
    public List<Utilisateurs> selectAll() throws BusinessException; //admin
    public Utilisateurs selectById(int id) throws BusinessException;
    public Utilisateurs selectByPseudo(String pseudo) throws BusinessException;
}
