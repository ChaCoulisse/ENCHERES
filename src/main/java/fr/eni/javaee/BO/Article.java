package fr.eni.javaee.BO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Article implements Serializable {
    private Integer id;
    private String nom;
    private String description;
    private LocalDate debutEnchere;
    private LocalDate finEnchere;
    private Integer prixInitial;
    private Integer prixVente;
    private List<Article> articlesVendus;
    private List<Article> articlesAchetes;
    private Utilisateurs vendeur;
    private EtatVente etatVente;

    public Article () {
    }

    public Article (Integer id, String nom, String description, LocalDate debutEnchere, LocalDate finEnchere, Integer prixInitial, Integer prixVente, List<Article> articlesVendus, List<Article> articlesAchetes, Utilisateurs vendeur, EtatVente etatVente) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.debutEnchere = debutEnchere;
        this.finEnchere = finEnchere;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.articlesVendus = articlesVendus;
        this.articlesAchetes = articlesAchetes;
        this.vendeur = vendeur;
        this.etatVente = etatVente;
    }

    public Article (String nom, String description, LocalDate debutEnchere, LocalDate finEnchere, Integer prixInitial, Integer prixVente, Utilisateurs vendeur) {
        this.nom = nom;
        this.description = description;
        this.debutEnchere = debutEnchere;
        this.finEnchere = finEnchere;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.vendeur = vendeur;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getNom () {
        return nom;
    }

    public void setNom (String nom) {
        this.nom = nom;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public LocalDate getDebutEnchere () {
        return debutEnchere;
    }

    public void setDebutEnchere (LocalDate debutEnchere) {
        this.debutEnchere = debutEnchere;
    }

    public LocalDate getFinEnchere () {
        return finEnchere;
    }

    public void setFinEnchere (LocalDate finEnchere) {
        this.finEnchere = finEnchere;
    }

    public Integer getPrixInitial () {
        return prixInitial;
    }

    public void setPrixInitial (Integer prixInitial) {
        this.prixInitial = prixInitial;
    }

    public Integer getPrixVente () {
        return prixVente;
    }

    public void setPrixVente (Integer prixVente) {
        this.prixVente = prixVente;
    }

    public List<Article> getArticlesVendus () {
        return articlesVendus;
    }

    public void setArticlesVendus (List<Article> articlesVendus) {
        this.articlesVendus = articlesVendus;
    }

    public List<Article> getArticlesAchetes () {
        return articlesAchetes;
    }

    public void setArticlesAchetes (List<Article> articlesAchetes) {
        this.articlesAchetes = articlesAchetes;
    }

    public Utilisateurs getVendeur () {
        return vendeur;
    }

    public void setVendeur (Utilisateurs vendeur) {
        this.vendeur = vendeur;
    }

    public void setEtatVente (EtatVente etatVente) {
        this.etatVente = etatVente;
    }

    @Override
    public String toString () {
        return "Article{" +
                "numArticle=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", debutEnchere=" + debutEnchere +
                ", finEnchere=" + finEnchere +
                ", prixInitial=" + prixInitial +
                ", prixVente=" + prixVente +
                ", articlesVendus=" + articlesVendus +
                ", articlesAchetes=" + articlesAchetes +
                ",vendeur=" + vendeur +
                ",etatVente=" + etatVente +
                '}';
    }
}
