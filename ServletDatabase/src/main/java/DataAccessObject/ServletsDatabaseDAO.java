/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GENIUS
 */
public class ServletsDatabaseDAO {

    /**
     * CONNECTION OBJECT FOR CREATING A CONNECTION TO THE DATABASE...
     */
    Connection connection;

    /**
     * STATEMENT OBJECT FOR CREATING AND EXECUTING SQL QURIES..
     */
    //EXECUTING SQL QURIES FOR RUNTIME...
    PreparedStatement preparedStatement;

    
    
    String url ="jdbc:sqlserver://localhost:1433/semester2";
    String user ="SEM2";
    String pass ="sem2";

    public ServletsDatabaseDAO() {

        try {
            try {
                /**
                 * CREATE A CONNECTION OBJECT THAT REPRESENT THE CONNECTION TO THE
                 * DATBASE... CONNECTING TO OUR DATABASE WITH THE DATABASE'S URL..
                 */  
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletsDatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=semester2;user=SEM2;password=sem2");
        } catch (SQLException ex) {
            System.out.println("Problem with creating the connection :" + ex);
        }

    }

    /**
     * CREATING QURIES...
     */
    public void insertStudent(String studentName, String studentEmail) {

        try {
            /**
             * CRETING SQL QURIES FOR DI..
             */
            //FOR INSERTING TO DATABASE...
            //String insertionQuery = "INSERT INTO StudentDetails(student_name,student_email) VALUES(?, ?)";

           PreparedStatement ps= connection.prepareStatement("INSERT INTO StudentDetails(student_name,student_email) VALUES(?, ?)");
           ps.setString(1, studentName);
           ps.setString(2, studentEmail);
           
           ps.executeUpdate();
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ServletsDatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
