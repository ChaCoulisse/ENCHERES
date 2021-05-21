package fr.eni.javaee.brouillon;

import fr.eni.javaee.BO.Utilisateurs;
import fr.eni.javaee.BusinessException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/inscription")
public class ServletInscription extends HttpServlet {
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/InscriptionUtilisateur.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/InscriptionUtilisateur.jsp");

        Utilisateurs utilisateur = new Utilisateurs();

        try {

            String pseudo = request.getParameter("pseudo");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");
            String rue = request.getParameter("rue");
            String ville = request.getParameter("ville");
            String cp = request.getParameter("cp");
            String mdp = request.getParameter("mdp");
            String confirmation = request.getParameter("conf");


            if (pseudo != null && pseudo.matches("\"#^[a-z0-9_]+$#\"")) {
                request.setAttribute("erreur", "Le pseudo ne contenir que des lettres et chiffres");
                rd.forward(request, response);

            } else if (nom != null || nom.length() < 2) {
                request.setAttribute("erreur", "Le nom doit contenir au moins 2 caractères");
                rd.forward(request, response);

            } else if (prenom != null || prenom.length() < 2) {
                request.setAttribute("erreur", "Le prénom doit contenir au moins 2 caractères");
                rd.forward(request, response);

            } else if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                request.setAttribute("erreur", "Merci de saisir une adresse mail valide");
                rd.forward(request, response);

            } else if (telephone != null || !telephone.matches("^\\d+$") || telephone.length() < 4) {
                request.setAttribute("erreur", "Merci de saisir une adresse mail valide");
                rd.forward(request, response);

            } else if (rue != null || rue.length() < 10) {
                request.setAttribute("erreur", "Merci de saisir une adresse mail valide");
                rd.forward(request, response);

            } else if (cp != null || cp.length() < 5) {
                request.setAttribute("erreur", "Merci de saisir un code postal  valide");
                rd.forward(request, response);
            } else if (ville != null) {
                request.setAttribute("erreur", "Merci de saisir un code postal  valide");
                rd.forward(request, response);

            } else if (confirmation.equals(mdp)) {

                utilisateur = new Utilisateurs(pseudo, nom, prenom, email, telephone, rue, cp, ville, mdp);
                utilisateur = UtilisateurManager.inscriptionUtilisateur(utilisateur);

                if (utilisateur != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("UtilisateurConnecte", utilisateur);

                    this.getServletContext().getRequestDispatcher("/accueilConnecte").forward(request, response);
                } else {
                    request.setAttribute("erreur", "Aucun utilisateur");
                    rd.forward(request, response);
                }
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
