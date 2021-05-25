package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.Article;
import fr.eni.javaee.BO.Enchere;
import fr.eni.javaee.BO.EtatVente;
import fr.eni.javaee.BO.Retrait;
import fr.eni.javaee.BusinessException;

import java.util.List;

public interface ArticleDAO {

    public void insert(Article article) throws BusinessException;
    public void delete(Integer id) throws BusinessException;
    public Article selectById(int id) throws BusinessException;
    public void update (Article article)throws BusinessException;

    public List<Article> getByVendeurNomCategorieEtat(int idVendeur,
                                             String ce_que_larticle_doit_contenir,
                                             String categorie,
                                             List<EtatVente> listeEtatVente) throws BusinessException;

    public List<Article> getByNomcategorieEnCours(String ce_que_larticle_doit_contenir,
                                                  String categorie) throws BusinessException;

}
