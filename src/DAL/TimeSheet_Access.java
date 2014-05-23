/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.ApprovalSheet;
import BE.Fireman;
import BE.Position;
import BE.TimeSheet;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Poul Nielsen
 */
public class TimeSheet_Access  extends DatabaseConnection{
    
    Position_Access pa;
    
    public TimeSheet_Access() throws IOException
    {
        super();
        pa = new Position_Access();
    }
    
    public ArrayList<TimeSheet> getTimeSheetByFiremanId(int id) throws SQLServerException, SQLException {
        Connection con = null;
        ArrayList<TimeSheet> timesheets = new ArrayList<>();
       try
       {
           con = getConnection();
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT * FROM TimeSheet " +
                                                                "INNER JOIN " +
                                                                "ApprovalSheet " +
                                                                "ON TimeSheet.acceptedForSalary = ApprovalSheet.id " +
                                                                "WHERE empoyeeId = "+ id +" ;");
           while(result.next())
           {
              
               int timeSheetId = result.getInt("id");
               int employeeId = result.getInt("empoyeeId");
               int alarmId = result.getInt("alarmId");
               int carNr = result.getInt("carNr"); 
               Position pos = pa.getPositionById(result.getInt("positionId"));
               Timestamp startTime = result.getTimestamp("startTime");
               Timestamp endtime = result.getTimestamp("endTime");
               int acceptedByTeamleader = result.getInt("acceptedByTeamLeader");
               int acceptedForSalary = result.getInt("acceptedForSalary");
               boolean addedToPayment = result.getBoolean("addedToPayment");
               String comment = result.getString("comment");
               
               //Approval Sheet 
               int appId = result.getInt("id");
               int firemanId = result.getInt("firemanID");
               String appcoment = result.getString("comment");
               boolean approved = result.getBoolean("approved");
               int hours = result.getInt("hours");
               
               ApprovalSheet aps = new ApprovalSheet(appId, firemanId, appcoment, approved, hours);
               
               TimeSheet c = new TimeSheet(timeSheetId, employeeId, alarmId, carNr, pos, startTime, endtime, 
                                                         acceptedByTeamleader, acceptedForSalary, addedToPayment, comment, aps);
               timesheets.add(c);
           }
       }
       finally
       {
           if(con != null)
           {
               con.close();
           }
       }
        
        
        return timesheets;
    }

    public ArrayList<TimeSheet> getTimeSheetByFiremanIdMonthYear(int id, int month, int year, boolean getApproved) throws SQLServerException, SQLException {
        
        Connection con = null;
        String approvedQuery = " AND addedToPayment = 'False' ";
        String monthQuery = " AND DATEPART(month, startTime) = "+month+"  ";
        ArrayList<TimeSheet> timesheets = new ArrayList<>();
        if (getApproved) {
            approvedQuery = " AND addedToPayment = 'True' ";
        }
        if (month==0) {
            monthQuery = "";
        }
       try
       {
           con = getConnection();
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT * FROM TimeSheet " +
                                                                "INNER JOIN ApprovalSheet " +
                                                                "ON TimeSheet.acceptedForSalary = ApprovalSheet.id " +
                                                                "WHERE empoyeeId = "+id+" " +
                                                                monthQuery +
                                                                "AND DATEPART(YEAR, startTime) = "+year+" " +
                                                                ""+approvedQuery+" ;");
           while(result.next())
           {
               int timeSheetId = result.getInt("id");
               int employeeId = result.getInt("empoyeeId");
               int alarmId = result.getInt("alarmId");
               int carNr = result.getInt("carNr"); 
               Position pos = pa.getPositionById(result.getInt("positionId"));
               Timestamp startTime = result.getTimestamp("startTime");
               
               getYearFromTimestamp(startTime);
               getMonthFromTimestamp(startTime);
               if (getYearFromTimestamp(startTime) == year && getMonthFromTimestamp(startTime) == month ) {
                   System.out.println("Yes");
               }
               Timestamp endtime = result.getTimestamp("endTime");
               int acceptedByTeamleader = result.getInt("acceptedByTeamLeader");
               int acceptedForSalary = result.getInt("acceptedForSalary");
               boolean addedToPayment = result.getBoolean("addedToPayment");
               String comment = result.getString("comment");
               
               //Approval Sheet 
               int appId = result.getInt("id");
               int firemanId = result.getInt("firemanID");
               String appcoment = result.getString("comment");
               boolean approved = result.getBoolean("approved");
               int hours = result.getInt("hours");
               
               ApprovalSheet aps = new ApprovalSheet(appId, firemanId, appcoment, approved, hours);
               
               TimeSheet c = new TimeSheet(timeSheetId, employeeId, alarmId, carNr, pos, startTime, endtime, 
                                                         acceptedByTeamleader, acceptedForSalary, addedToPayment, comment, aps);
               timesheets.add(c);
           }
       }
       finally
       {
           if(con != null)
           {
               con.close();
           }
       }
        
        
        return timesheets;
    }
    
    private int getYearFromTimestamp(Timestamp tms)
    {
        long time = tms.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.get(Calendar.YEAR);    
    }
    
     private int getMonthFromTimestamp(Timestamp tms)
    {
        long time = tms.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.get(Calendar.MONTH);    
    }
    
}
