/**
 * @author ROUILLY Charlotte
 */


package fr.eni.javaee.BO;

import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;

public class Utilisateur implements Serializable {
    private Integer id_utilisateur;
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
    private Boolean administrateur;
    //private List<Article> articlesVendus;
    //private List<Article> articlesAchetes;
    //private List<Enchere> listeEncheres;


    public Utilisateur() {
    }

    /*
    public Utilisateur(Integer id_utilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, String cp, String ville, String mdp, Integer credit, boolean administrateur, List<Article> articlesVendus, List<Article> articlesAchetes, List<Enchere> listeEncheres) {
        this.id_utilisateur = id_utilisateur;
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
        this.articlesVendus = articlesVendus;
        this.articlesAchetes = articlesAchetes;
        this.listeEncheres = listeEncheres;
    }
     */
    public Utilisateur(Integer id_utilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, String cp, String ville, String mdp, Integer credit, boolean administrateur) {
        this.id_utilisateur = id_utilisateur;
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

    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue, String cp, String ville, String mdp) {
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
        //this.articlesVendus = new ArrayList<Article>();
        //this.articlesAchetes = new ArrayList<Article>();
        //this.listeEncheres = new ArrayList<Enchere>();
    }

    public Integer getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(Integer id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    /*
    public List<Article> getArticlesVendus() {
        return articlesVendus;
    }

    public void setArticlesVendus(List<Article> articlesVendus) {
        this.articlesVendus = articlesVendus;
    }

    public List<Article> getArticlesAchetes() {
        return articlesAchetes;
    }

    public void setArticlesAchetes(List<Article> articlesAchetes) {
        this.articlesAchetes = articlesAchetes;
    }

    public List<Enchere> getListeEncheres() {
        return listeEncheres;
    }

    public void setListeEncheres(List<Enchere> listeEncheres) {
        this.listeEncheres = listeEncheres;
    }
     */

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id_utilisateur +
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
                //", articlesVendus=" + articlesVendus +
                //", articlesAchetes=" + articlesAchetes +
                //", listeEncheres=" + listeEncheres +
                '}';
    }
}



