package Controlador;

import Modelo.EmpleadoDAO;
import Modelo.EmpleadoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Validar extends HttpServlet {

    EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    EmpleadoDTO empleadoDTO = new EmpleadoDTO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Validar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Validar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");

        if (accion.equalsIgnoreCase("Ingresar")) {

            String user = request.getParameter("txtuser");

            String pass = request.getParameter("txtpass");

            empleadoDTO = empleadoDAO.validar(user, pass);

            if (empleadoDTO.getUser() != null) {
                
                HttpSession mySesion = request.getSession(true);
                
                System.out.println("id de sesión: " + mySesion.getId());
                System.out.println("fecha de sesión: " + mySesion.getCreationTime());

                mySesion.setAttribute("usuario", empleadoDTO);
                
                System.out.println("Ingreso correcto ");

                request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);

            } else {
                System.out.println("Usuario o pss invalido");
                //request.getRequestDispatcher("index.jsp").forward(request, response);
                
                request.getRequestDispatcher("error.html").forward(request, response);
            }
        } else {            
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
