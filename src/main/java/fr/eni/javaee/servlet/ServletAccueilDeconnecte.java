package fr.eni.javaee.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ServletAccueilDeconnecte", value = "/ServletAccueilDeconnecte")
public class ServletAccueilDeconnecte extends HttpServlet {

    public ServletAccueilDeconnecte () {
    }

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("UtilisateurConnecte");
        session.invalidate();

        RequestDispatcher rd = request.getRequestDispatcher("/WEB_INF/JSPAccueilDeconnecte.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
