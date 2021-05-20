package fr.eni.javaee.BO;

import java.io.Serializable;

public class Utilisateurs implements Serializable {
    private Integer id;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String rue;
    private String cp;
    private String ville;
    private String mdp;
    private Integer credit;
    private boolean administrateur;

    public Utilisateurs () {
    }

    public Utilisateurs (Integer id, String pseudo, String nom, String prenom, String email, String telephone, String rue, String cp, String ville, String mdp, Integer credit, boolean administrateur) {
        this.id = id;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
        this.mdp = mdp;
        this.credit = credit;
        this.administrateur = administrateur;
    }

    public Utilisateurs (String pseudo, String nom, String prenom, String email, String telephone, String rue, String cp, String ville, String mdp) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
        this.mdp = mdp;
        this.credit = 0;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getPseudo () {
        return pseudo;
    }

    public void setPseudo (String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom () {
        return nom;
    }

    public void setNom (String nom) {
        this.nom = nom;
    }

    public String getPrenom () {
        return prenom;
    }

    public void setPrenom (String prenom) {
        this.prenom = prenom;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getTelephone () {
        return telephone;
    }

    public void setTelephone (String telephone) {
        this.telephone = telephone;
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

    public String getMdp () {
        return mdp;
    }

    public void setMdp (String mdp) {
        this.mdp = mdp;
    }

    public Integer getCredit () {
        return credit;
    }

    public void setCredit (Integer credit) {
        this.credit = credit;
    }

    public boolean isAdministrateur () {
        return administrateur;
    }

    public void setAdministrateur (boolean administrateur) {
        this.administrateur = administrateur;
    }

    @Override
    public String toString () {
        return "Utilisateurs{" +
                "Id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", rue='" + rue + '\'' +
                ", cp='" + cp + '\'' +
                ", ville='" + ville + '\'' +
                ", mdp='" + mdp + '\'' +
                ", credit=" + credit +
                ", administrateur=" + administrateur +
                '}';
    }
}
