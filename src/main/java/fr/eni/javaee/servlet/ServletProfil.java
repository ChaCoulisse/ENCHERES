package fr.eni.javaee.servlet;

import fr.eni.javaee.BLL.UtilisateurManager;
import fr.eni.javaee.BO.Utilisateur;
import fr.eni.javaee.BusinessException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/profil")
public class ServletProfil extends HttpServlet {

    public ServletProfil () {
        super();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

        String pseudo = request.getParameter("pseudo");
        Utilisateur afficherUtilisateur = null;

        try{
            afficherUtilisateur = UtilisateurManager.selectByPseudo(pseudo);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        if (afficherUtilisateur != null && utilisateur != null){
            request.getRequestDispatcher("../webapp/WEB-INF/JSPProfil.jsp").forward(request,response);
        }
    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
