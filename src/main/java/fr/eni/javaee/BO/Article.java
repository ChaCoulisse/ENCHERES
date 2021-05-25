package fr.eni.javaee.BO;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private Integer id_article;
    private String nom;
    private String description;
    private Date debutEnchere;
    private Date finEnchere;
    private Integer prixInitial;
    private Integer prixVente;
    private Integer vendeur;
    private EtatVente etatVente;
    private Integer categorie;

    public Article () {
    }

    public Article(Integer id_article, String nom, String description, Date debutEnchere, Date finEnchere, Integer prixInitial, Integer prixVente, Integer vendeur, EtatVente etatVente, Integer categorie) {
        this.id_article = id_article;
        this.nom = nom;
        this.description = description;
        this.debutEnchere = debutEnchere;
        this.finEnchere = finEnchere;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.vendeur = vendeur;
        this.etatVente = etatVente;
        this.categorie = categorie;
    }

    public Article(String nom, String description, Date debutEnchere, Date finEnchere, Integer prixInitial, Integer prixVente, Integer vendeur, Integer categorie) {
        this.nom = nom;
        this.description = description;
        this.debutEnchere = debutEnchere;
        this.finEnchere = finEnchere;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.vendeur = vendeur;
        this.categorie = categorie;
        this.etatVente = EtatVente.CREE;
    }

    public Integer getId_article() {
        return id_article;
    }

    public void setId_article(Integer id_article) {
        this.id_article = id_article;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDebutEnchere() {
        return debutEnchere;
    }

    public void setDebutEnchere(Date debutEnchere) {
        this.debutEnchere = debutEnchere;
    }

    public Date getFinEnchere() {
        return finEnchere;
    }

    public void setFinEnchere(Date finEnchere) {
        this.finEnchere = finEnchere;
    }

    public Integer getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(Integer prixInitial) {
        this.prixInitial = prixInitial;
    }

    public Integer getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Integer prixVente) {
        this.prixVente = prixVente;
    }

    public Integer getVendeur() {
        return vendeur;
    }

    public void setVendeur(Integer vendeur) {
        this.vendeur = vendeur;
    }

    public EtatVente getEtatVente() {
        return etatVente;
    }

    public void setEtatVente(EtatVente etatVente) {
        this.etatVente = etatVente;
    }

    public Integer getCategorie() {
        return categorie;
    }

    public void setCategorie(Integer categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id_article +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", debutEnchere=" + debutEnchere +
                ", finEnchere=" + finEnchere +
                ", prixInitial=" + prixInitial +
                ", prixVente=" + prixVente +
                ", vendeur=" + vendeur +
                ", etatVente=" + etatVente +
                ", categorie=" + categorie +
                '}';
    }
}

