package fr.eni.javaee.Forms;

import fr.eni.javaee.BLL.UtilisateurManager;
import fr.eni.javaee.BO.Utilisateur;
import fr.eni.javaee.BusinessException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class InscriptionUtilisateurForm {

    public static final String CHAMP_PSEUDO = "pseudo";
    public static final String CHAMP_NOM = "nom";
    public static final String CHAMP_PRENOM = "prenom";
    public static final String CHAMP_EMAIL = "email";
    public static final String CHAMP_TELEPHONE = "telephone";
    public static final String CHAMP_RUE = "rue";
    public static final String CHAMP_CP = "cp";
    public static final String CHAMP_VILLE = "ville";
    public static final String CHAMP_MDP = "mdp";
    public static final String CHAMP_CONF = "confirmation";
    private final Map<String, String> erreurs = new HashMap<String, String>();
    private String resultat;

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp (HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }

    public Map<String, String> getErreurs () {
        return erreurs;
    }

    public String getResultat () {
        return resultat;
    }

    public Utilisateur inscrireUtilisateur (HttpServletRequest request) throws BusinessException {
        String pseudo = request.getParameter(CHAMP_PSEUDO);
        String nom = request.getParameter(CHAMP_NOM);
        String prenom = request.getParameter(CHAMP_PRENOM);
        String email = request.getParameter(CHAMP_EMAIL);
        String telephone = request.getParameter(CHAMP_TELEPHONE);
        String rue = request.getParameter(CHAMP_RUE);
        String cp = request.getParameter(CHAMP_CP);
        String ville = request.getParameter(CHAMP_VILLE);
        String mdp = request.getParameter(CHAMP_MDP);
        String confirmation = request.getParameter(CHAMP_CONF);

        Utilisateur utilisateur = new Utilisateur();

        try {
            validationPseudo(pseudo);
        } catch (Exception e) {
            setErreur(CHAMP_PSEUDO, e.getMessage());
        }
        utilisateur.setPseudo(pseudo);

        try {
            validationNom(nom);
        } catch (Exception e) {
            setErreur(CHAMP_NOM, e.getMessage());
        }
        utilisateur.setNom(nom);

        try {
            validationPrenom(prenom);
        } catch (Exception e) {
            setErreur(CHAMP_PRENOM, e.getMessage());
        }
        utilisateur.setPrenom(prenom);

        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        utilisateur.setEmail(email);

        try {
            validationTelephone(telephone);
        } catch (Exception e) {
            setErreur(CHAMP_TELEPHONE, e.getMessage());
        }
        utilisateur.setTelephone(telephone);

        try {
            validationRue(rue);
        } catch (Exception e) {
            setErreur(CHAMP_RUE, e.getMessage());
        }
        utilisateur.setRue(rue);

        try {
            validationCp(cp);
        } catch (Exception e) {
            setErreur(CHAMP_CP, e.getMessage());
        }
        utilisateur.setCp(cp);

        try {
            validationVille(ville);
        } catch (Exception e) {
            setErreur(CHAMP_VILLE, e.getMessage());
        }
        utilisateur.setVille(ville);

        try {
            validationMotsDePasse(mdp, confirmation);
        } catch (Exception e) {
            setErreur(CHAMP_MDP, e.getMessage());
            setErreur(CHAMP_CONF, null);
        }
        utilisateur.setMdp(mdp);


        if (erreurs.isEmpty()) {
            resultat = "Succès de la création de l'utilisateur.";
            UtilisateurManager.inscriptionUtilisateur(utilisateur);
        } else {
            resultat = "Échec de la création de l'utilisateur.";
        }
        return utilisateur;
    }

    private void validationPseudo (String pseudo) throws Exception {
        if (pseudo != null){
            if(pseudo.matches("\"#^[a-z0-9_]+$#\"")) {
                throw new Exception("Le pseudo ne contenir que des lettres et chiffres");
            }
        } else{
            throw new Exception("Merci d'entrer un pseudo");
        }
    }

    private void validationNom (String nom) throws Exception {
        if (nom != null) {
            if (nom.length() < 2) {
                throw new Exception("Le nom d'utilisateur doit contenir au moins 2 caractères.");
            }
        } else {
            throw new Exception("Merci d'entrer un nom d'utilisateur.");
        }
    }

    private void validationPrenom (String prenom) throws Exception {
        if (prenom != null){
            if (prenom.length() < 2) {
                throw new Exception("Le prénom d'utilisateur doit contenir au moins 2 caractères.");
            }
        } else{
        throw new Exception(("Merci de saisir un prénom"));
    }
    }

    private void validationEmail (String email) throws Exception {
        if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            throw new Exception("Merci de saisir une adresse mail valide.");
        }
    }

    private void validationTelephone (String telephone) throws Exception {
        if (telephone != null) {
            if (!telephone.matches("^\\d+$")) {
                throw new Exception("Le numéro de téléphone doit uniquement contenir des chiffres.");
            } else if (telephone.length() < 4) {
                throw new Exception("Le numéro de téléphone doit contenir au moins 4 chiffres.");
            }
        } else {
            throw new Exception("Merci d'entrer un numéro de téléphone.");
        }
    }

    private void validationRue (String adresse) throws Exception {
        if (adresse != null) {
            if (adresse.length() < 10) {
                throw new Exception("L'adresse de livraison doit contenir au moins 10 caractères.");
            }
        } else {
            throw new Exception("Merci d'entrer une adresse de livraison.");
        }
    }

    private void validationCp (String cp) throws Exception {
        if (cp != null) {
            if (cp.length() < 5) {
                throw new Exception("L'adresse de livraison doit contenir au moins 5 caractères.");
            }
        } else {
            throw new Exception("Merci d'entrer une adresse de livraison.");
        }
    }

    private void validationVille (String ville) throws Exception {
        if (ville != null) {
            if (ville.length() < 5) {
                throw new Exception("L'adresse de livraison doit contenir au moins 5 caractères.");
            }
        } else {
            throw new Exception("Merci d'entrer une adresse de livraison.");
        }
    }

    private void validationMotsDePasse (String mdp, String confirmation) throws Exception {
        if (mdp != null && confirmation != null) {
            if (!mdp.equals(confirmation)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if (mdp.length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur (String champ, String message) {
        erreurs.put(champ, message);
    }
}
