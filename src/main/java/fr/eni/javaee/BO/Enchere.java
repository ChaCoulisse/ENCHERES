package fr.eni.javaee.BO;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Enchere implements Serializable {
    private Integer numUtilisateur;
    private Integer numArticle;
    private LocalDateTime dateEnchere;
    private Integer montantEnchere;

    public Enchere () {
    }

    public Enchere (Integer numUtilisateur, Integer numArticle, LocalDateTime dateEnchere, Integer montantEnchere) {
        this.numUtilisateur = numUtilisateur;
        this.numArticle = numArticle;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
    }

    public Enchere (Integer numArticle, LocalDateTime dateEnchere, Integer montantEnchere) {
        this.numArticle = numArticle;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
    }

    public Integer getNumUtilisateur () {
        return numUtilisateur;
    }

    public void setNumUtilisateur (Integer numUtilisateur) {
        this.numUtilisateur = numUtilisateur;
    }

    public Integer getNumArticle () {
        return numArticle;
    }

    public void setNumArticle (Integer numArticle) {
        this.numArticle = numArticle;
    }

    public LocalDateTime getDateEnchere () {
        return dateEnchere;
    }

    public void setDateEnchere (LocalDateTime dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public Integer getMontantEnchere () {
        return montantEnchere;
    }

    public void setMontantEnchere (Integer montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

    @Override
    public String toString () {
        return "Enchere{" +
                "numUtilisateur=" + numUtilisateur +
                ", numArticle=" + numArticle +
                ", dateEnchere=" + dateEnchere +
                ", montantEnchere=" + montantEnchere +
                '}';
    }
}
