package fr.eni.javaee.servlet;

import fr.eni.javaee.BO.Utilisateurs;
import fr.eni.javaee.Forms.InscriptionUtilisateurForm;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/inscription")
public class ServletInscriptionUtilisateur extends HttpServlet {
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String INSCRIPTION = "/WEB-INF/InscriptionUtilisateur.jsp";

    public ServletInscriptionUtilisateur () {
        super();
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Affichage de la page d'inscription */

        RequestDispatcher rd = request.getRequestDispatcher(INSCRIPTION);
        rd.forward(request, response);
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        InscriptionUtilisateurForm form = new InscriptionUtilisateurForm();

        Utilisateurs utilisateur = form.inscrireUtilisateur(request);


        request.setAttribute(ATT_FORM, form);
        request.setAttribute(ATT_USER, utilisateur);

        this.getServletContext().getRequestDispatcher(INSCRIPTION).forward(request, response);
    }

}


