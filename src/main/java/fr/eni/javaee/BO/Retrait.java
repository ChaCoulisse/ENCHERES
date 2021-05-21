package fr.eni.javaee.BO;

import java.io.Serializable;

public class Retrait implements Serializable {
    private Integer id;
    private String rue;
    private String cp;
    private String ville;

    public Retrait () {
    }

    public Retrait (Integer id, String rue, String cp, String ville) {
        this.id = id;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
    }

    public Retrait (String rue, String cp, String ville) {
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
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
                "numArticle=" + id +
                ", rue='" + rue + '\'' +
                ", cp='" + cp + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
