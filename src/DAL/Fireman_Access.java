/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Fireman;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Poul Nielsen
 */
public class Fireman_Access extends DatabaseConnection  {

    /**
     * 
     * @throws IOException 
     */
    public Fireman_Access() throws IOException
    {
        super();
    }
    
    /**
     * Returns an arraylist of all firemen 
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    public ArrayList<Fireman> getAllFiremen() throws SQLServerException, SQLException {
        Connection con = null;
         ArrayList<Fireman> fireman = new ArrayList<>();
        
               try
       {
           con = getConnection();
           
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT * FROM Fireman ORDER BY firstName;");
           while(result.next())
           {
               int userId = result.getInt("employeeId");
               String firstName = result.getString("firstName");
               String lastName = result.getString("lastName");
               boolean teamleader = result.getBoolean("teamleader");
               boolean driver = result.getBoolean("driver");
               
               Fireman c = new Fireman(userId, firstName, lastName, teamleader, driver);
               
               fireman.add(c);
           }
           
       }
       finally
       {
           if(con != null)
           {
               con.close();
           }
       }
        return fireman;
    }
    
    /**
     * Returns a Fireman by an id
     * @param ID
     * @return
     * @throws SQLException 
     */
    public Fireman getFiremanByID(int ID) throws SQLException {
        Connection con = null;
        Fireman fireman = null;
        
               try
       {
           con = getConnection();
           
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT * FROM Fireman WHERE employeeId = " + ID + ";");
           if(result.next())
           {
               int userId = result.getInt("employeeId");
               String firstName = result.getString("firstName");
               String lastName = result.getString("lastName");
               boolean teamleader = result.getBoolean("teamleader");
               boolean driver = result.getBoolean("driver");
               
               
               fireman = new Fireman(userId, firstName, lastName, teamleader, driver);
               
           }
           
       }
       finally
       {
           if(con != null)
           {
               con.close();
           }
       }
        return fireman;
           
    }
    
}
