/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Fireman;
import DAL.Fireman_Access;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Poul Nielsen
 */
public class Fireman_AccessLink {

    Fireman_Access fa;

    public Fireman_AccessLink() throws IOException {
        this.fa = new Fireman_Access();
    }
   
    public ArrayList<Fireman> getAllFiremen() throws SQLException {
        return fa.getAllFiremen();
    }
    
    public Fireman getFiremanById(int ID) throws SQLException{
        return fa.getFiremanByID(ID);
    }
    
}
