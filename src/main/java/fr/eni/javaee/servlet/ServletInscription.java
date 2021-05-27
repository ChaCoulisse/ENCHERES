package fr.eni.javaee.servlet;

import fr.eni.javaee.BLL.UtilisateurManager;
import fr.eni.javaee.BO.Utilisateur;
import fr.eni.javaee.BusinessException;
import fr.eni.javaee.Forms.InscriptionUtilisateurForm;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/inscription")
public class ServletInscription extends HttpServlet {
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String INSCRIPTION = "/WEB-INF/InscriptionUtilisateur.jsp";


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Affichage de la page d'inscription */

        RequestDispatcher rd = request.getRequestDispatcher(INSCRIPTION);
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        HttpSession session = request.getSession();
        InscriptionUtilisateurForm form = new InscriptionUtilisateurForm();

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */

        Utilisateur utilisateur_tmp = null;
        try {
            utilisateur_tmp = form.inscrireUtilisateur(request);
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute(ATT_FORM, form);
        request.setAttribute(ATT_USER, utilisateur_tmp);

        Utilisateur utilisateur = null;
        try {
            utilisateur = UtilisateurManager.selectByPseudo(request.getParameter("pseudo"));
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }

        if (utilisateur != null){
            session.setAttribute("id_utilisateur",utilisateur.getId_utilisateur());
            this.getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher(INSCRIPTION).forward(request, response);
        }
    }
}
