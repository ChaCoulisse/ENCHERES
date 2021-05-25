package fr.eni.javaee.BO;

import java.io.Serializable;
import java.time.LocalDate;

public class Enchere implements Serializable {
    private Integer id;
    private Integer id_utilisateur;
    private Integer id_article;
    private LocalDate dateEnchere;
    private Integer montantEnchere;
    private boolean gagner=false;

    public Enchere () {
    }

    public Enchere (Integer id, Integer id_utilisateur, Integer id_article, LocalDate dateEnchere, Integer montantEnchere, boolean gagner) {
        this.id = id;
        this.id_utilisateur = id_utilisateur;
        this.id_article = id_article;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.setGagner(false);
    }

    public Enchere (Integer id_article,Integer id_utilisateur, LocalDate dateEnchere, Integer montantEnchere) {
        this.id_article = id_article;
        this.id_utilisateur = id_utilisateur;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.setGagner(false);
    }

    public Integer getId_utilisateur () {
        return id_utilisateur;
    }

    public void setId_utilisateur (Integer id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Integer getId_article () {
        return id_article;
    }

    public void setId_article (Integer id_article) {
        this.id_article = id_article;
    }

    public LocalDate getDateEnchere () {
        return dateEnchere;
    }

    public void setDateEnchere (LocalDate dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public Integer getMontantEnchere () {
        return montantEnchere;
    }

    public void setMontantEnchere (Integer montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public boolean isGagner () {
        return gagner;
    }

    public void setGagner (boolean gagner) {
        this.gagner = gagner;
    }

    @Override
    public String toString () {
        return "Enchere{" +
                "id=" + id +
                ", id_utilisateur=" + id_utilisateur +
                ", id_article=" + id_article +
                ", dateEnchere=" + dateEnchere +
                ", montantEnchere=" + montantEnchere +
                ", gagner=" + gagner +
                '}';
    }
}
