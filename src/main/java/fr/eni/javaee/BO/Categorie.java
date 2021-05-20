package fr.eni.javaee.BO;

import java.io.Serializable;

public class Categorie implements Serializable {
    private Integer id;
    private String libelle;

    public Categorie () {
    }

    public Categorie (Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Categorie (String libelle) {
        this.libelle = libelle;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getLibelle () {
        return libelle;
    }

    public void setLibelle (String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString () {
        return "Categorie{" +
                "numCategorie=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
