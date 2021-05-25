package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.Utilisateur;
import fr.eni.javaee.BusinessException;

public interface UtilisateursDAO {

    public Utilisateur insert(Utilisateur utilisateur) throws BusinessException; // pour l'inscription
    public void delete(Integer id) throws BusinessException; // admin
    //public List<Utilisateur> selectAll() throws BusinessException; //admin
    public Utilisateur selectById(int id) throws BusinessException;
    public Utilisateur selectByPseudo(String pseudo) throws BusinessException;

    }

