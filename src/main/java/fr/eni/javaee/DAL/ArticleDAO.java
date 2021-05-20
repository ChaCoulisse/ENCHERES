package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.Article;
import fr.eni.javaee.BO.Retrait;
import fr.eni.javaee.BusinessException;

import java.util.List;

public interface ArticleDAO {

    public Article insert(Article article) throws BusinessException;
    public void delete(Integer id) throws BusinessException;
    public List<Article> selectAll() throws BusinessException;
    public Article selectById(int id) throws BusinessException;
    public void update (Article article)throws BusinessException;
    public List<Article> getByVendeur(int id) throws BusinessException;
    public List<Article> getByRetrait(Retrait retrait) throws BusinessException;

}
