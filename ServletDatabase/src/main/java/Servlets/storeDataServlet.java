/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataAccessObject.ServletsDatabaseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GENIUS
 */
public class storeDataServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    /**
     * REFRENECE TO DATABASE CLASS
     */
    ServletsDatabaseDAO servletsDatabaseDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        servletsDatabaseDAO = new ServletsDatabaseDAO();

        /**
         * GETTING FORM PARAMETER FROM THE REQUEST....
         */
        String studentName = request.getParameter("student_name");
        String studentEmail = request.getParameter("student_email");
        /**
         * INSERTING USING OUR DETAILS TO DATABASE
         */
        servletsDatabaseDAO.insertStudent(studentName, studentEmail);

        /**
         * RETRIVE DATA
         */
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet storeDataServlet</title>");
        out.println("</head>");
        out.println("<body>");

        try {
            /**
             * RETRIVE DATA
             */
            
            /**
             * FIRSTLY CONNECTING...
             */
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=semester2;user=SEM2;password=sem2");
            PreparedStatement ps = connection.prepareStatement("SELECT *FROM StudentDetails");
            ResultSet rs = ps.executeQuery();

            /**
             * LOOPING THROUGH TO CHECK FOR NEXT AND PRINTING..
             */
            while (rs.next()) {

                out.println(rs.getInt("student_id") + " " + rs.getString("student_name") + " " + rs.getNString("student_email"));

            }
        } catch (Exception ex) {
            System.out.println("Problem with creating the connection :" + ex);
        }

        //out.println("<h1>STUDENT RECORD ADDED SUCCESS FULLY..</h1>");
        out.println("</body>");
        out.println("</html>");

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
        processRequest(request, response);
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
