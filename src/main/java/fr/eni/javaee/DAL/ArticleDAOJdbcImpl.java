package fr.eni.javaee.DAL;

import fr.eni.javaee.BO.Article;
import fr.eni.javaee.BO.Retrait;
import fr.eni.javaee.BusinessException;

import java.util.List;

public class ArticleDAOJdbcImpl implements ArticleDAO{
    @Override
    public Article insert (Article article) throws BusinessException {
        return null;
    }

    @Override
    public void delete (Integer id) throws BusinessException {

    }

    @Override
    public List<Article> selectAll () throws BusinessException {
        return null;
    }

    @Override
    public Article selectById (int id) throws BusinessException {
        return null;
    }

    @Override
    public void update (Article article) throws BusinessException {

    }

    @Override
    public List<Article> getByVendeur (int id) throws BusinessException {
        return null;
    }

    @Override
    public List<Article> getByRetrait (Retrait retrait) throws BusinessException {
        return null;
    }
}
