package fr.eni.javaee.servlet;

import fr.eni.javaee.BLL.ServiceConnexion;
import fr.eni.javaee.BusinessException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/connexion")
public class ServletConnexion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pseudo = request.getParameter("identifiant");
        String mdp = request.getParameter("mdp");
        ServiceConnexion serviceConnexion = new ServiceConnexion();
        try {
            if(serviceConnexion.validerConnexion(pseudo,mdp)){
                session.setAttribute("pseudo",request.getParameter("identifiant"));
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
                rd.forward(request, response);
            }
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
            request.setAttribute("listeCodesErreur", businessException.getListeCodesErreur());
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
        rd.forward(request, response);
    }
}
