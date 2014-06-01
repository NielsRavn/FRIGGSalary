/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Position;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Poul Nielsen
 */
public class Position_Access extends DatabaseConnection{
    
    public Position_Access() throws IOException{
        super();
    }
    /**
     * Returns the position name and id from an id
     * @param id
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    public Position getPositionById(int id) throws SQLServerException, SQLException{
        Connection con = null;
        Position position = null;
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Position WHERE id = "+id+";");
            
            if(rs.next()){
                position = new Position(rs.getInt("id"), rs.getString("name"));
                
            }
        }finally{
            if(con != null) con.close();
        }
        
        return position;
    }
}
