/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Fireman;
import BE.TimeSheet;
import DAL.TimeSheet_Access;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Poul Nielsen
 */
public class TimeSheet_AccessLink {
    TimeSheet_Access tsa;
    public TimeSheet_AccessLink() throws IOException
    {
        tsa = new TimeSheet_Access();
    }
    
    public ArrayList<TimeSheet> getTimeSheetByFiremanId(int id) throws SQLException {
        
        return tsa.getTimeSheetByFiremanId(id);
    }

    public ArrayList<TimeSheet> getTimeSheetByFiremanIdMonthYear(int id, int month, int year, boolean getApproved) throws SQLException {
        
        return tsa.getTimeSheetByFiremanIdMonthYearAproved(id, month, year, getApproved);
    }

  
    
}
