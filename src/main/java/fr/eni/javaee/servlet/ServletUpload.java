package fr.eni.javaee.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;

@WebServlet("/upload")
@MultipartConfig( fileSizeThreshold = 1024 * 1024,
        maxFileSize =10485760, // 10 Mo
        maxRequestSize =52428800 ) //1 Mo
public class ServletUpload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final int TAILLE_TAMPON=10240;
    public static final String CHEMIN_FICHIERS = "../webapp/WEB-CONTENT/images";

    public ServletUpload () {
        super();
    }

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/Upload.jsp").forward(request,response);
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // On récupère le champs description
        String description = request.getParameter("description");
        request.setAttribute("description", description);

        //on récupère le champs du fichier
        Part part = request.getPart("fichier");

        //On vérifie qu'on a bien reçu  le fichier
        String nomFichier = getNomFichier(part);

        // Si on a bien un fichier
        if (nomFichier != null && !nomFichier.isEmpty()) {
            String nomChamp = part.getName();
            // Corrige un bug du fonctionnement d'Internet Explorer
            nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);

            // On écrit définitivement le fichier sur le disque
            ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

            request.setAttribute(nomChamp, nomFichier);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/Upload.jsp").forward(request, response);
    }

    private void ecrireFichier (Part part, String nomFichier, String chemin) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie =null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(chemin + nomFichier), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }


    private String getNomFichier (Part part) {
        for (String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }
}

