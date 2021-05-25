package fr.eni.javaee.BO;

import java.io.Serializable;

public class Retrait implements Serializable {
    private Integer id_article;
    private String rue;
    private String cp;
    private String ville;

    public Retrait () {
    }

    public Retrait (Integer id_article, String rue, String cp, String ville) {
        this.id_article = id_article;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
    }

    public Retrait (String rue, String cp, String ville) {
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
    }

    public Integer getId_article () {
        return id_article;
    }

    public void setId_article (Integer id_article) {
        this.id_article = id_article;
    }

    public String getRue () {
        return rue;
    }

    public void setRue (String rue) {
        this.rue = rue;
    }

    public String getCp () {
        return cp;
    }

    public void setCp (String cp) {
        this.cp = cp;
    }

    public String getVille () {
        return ville;
    }

    public void setVille (String ville) {
        this.ville = ville;
    }

    @Override
    public String toString () {
        return "Retrait{" +
                "numArticle=" + id_article +
                ", rue='" + rue + '\'' +
                ", cp='" + cp + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
