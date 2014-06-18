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
    /**
     * 
     * @throws IOException 
     */
    public TimeSheet_Access() throws IOException
    {
        super();
        pa = new Position_Access();
    }
    
    /**
     * Returns an arraylist of timesheets based on the fireman id
     * @param id
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    public ArrayList<TimeSheet> getTimeSheetByFiremanId(int id) throws SQLServerException, SQLException {
        Connection con = null;
        ArrayList<TimeSheet> timesheets = new ArrayList<>();
       try
       {
           con = getConnection();
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT Alarm.exercise AS exercise,* FROM TimeSheet " +
                                                                " INNER JOIN " +
                                                                " ApprovalSheet " +
                                                                " ON TimeSheet.acceptedForSalary = ApprovalSheet.id " +
                                                                " INNER JOIN Alarm " +
                                                                " ON TimeSheet.alarmId = Alarm.id " +
                                                                " WHERE empoyeeId = "+ id +" "+  
                                                                " ORDER BY startTime;");
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
               boolean exercise = result.getBoolean("exercise");
               
               //Approval Sheet 
               int appId = result.getInt("id");
               int firemanId = result.getInt("firemanID");
               String appcoment = result.getString("comment");
               boolean approved = result.getBoolean("approved");
               int hours = result.getInt("hours");
               
               ApprovalSheet aps = new ApprovalSheet(appId, firemanId, appcoment, approved, hours);
               
               TimeSheet c = new TimeSheet(timeSheetId, employeeId, alarmId, carNr, pos, startTime, endtime, 
                                                         acceptedByTeamleader, acceptedForSalary, addedToPayment, comment, aps, exercise);
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
    /**
     * Returns an arraylist of timesheets based on a search query on month day and year and not approved
     * @param id
     * @param month
     * @param year
     * @param getApproved
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    public ArrayList<TimeSheet> getTimeSheetByFiremanIdMonthYearAproved(int id, int month, int year, boolean getApproved) throws SQLServerException, SQLException {
        
        Connection con = null;
        String approvedQuery = " AND addedToPayment = 'False' ";
        String monthQuery = " AND DATEPART(month, startTime) = "+month+"  ";
        String yearQuery = " AND DATEPART(YEAR, startTime) = "+year+" ";
        ArrayList<TimeSheet> timesheets = new ArrayList<>();
        if (getApproved) {
            approvedQuery = " AND addedToPayment = 'True' ";
        }
        if (month==0) {
            monthQuery = "";
        }
        if (year==0) {
            yearQuery = "";
        }
       try
       {
           con = getConnection();
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT Alarm.exercise AS exercise, * FROM TimeSheet " +
                                                                " INNER JOIN ApprovalSheet " +
                                                                " ON TimeSheet.acceptedForSalary = ApprovalSheet.id " +
                                                                " INNER JOIN Alarm " +
                                                                " ON TimeSheet.alarmId = Alarm.id " +
                                                                " WHERE empoyeeId = "+id+" " +
                                                                monthQuery +
                                                                yearQuery +
                                                                ""+approvedQuery+""+  
                                                                " ORDER BY startTime ;");
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
               boolean exercise = result.getBoolean("exercise");
               
               //Approval Sheet 
               int appId = result.getInt("id");
               int firemanId = result.getInt("firemanID");
               String appcoment = result.getString("comment");
               boolean approved = result.getBoolean("approved");
               int hours = result.getInt("hours");
               
               ApprovalSheet aps = new ApprovalSheet(appId, firemanId, appcoment, approved, hours);
               
               TimeSheet c = new TimeSheet(timeSheetId, employeeId, alarmId, carNr, pos, startTime, endtime, 
                                                         acceptedByTeamleader, acceptedForSalary, addedToPayment, comment, aps, exercise);
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
    
    /**
     * Returns an arraylist of timesheets based on a search query on month day and year and not approved
     * @param id
     * @param month
     * @param year
     * @param getApproved
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    public int getNumberOfUnapprovedTimeSheetByFiremanIdMonthYear(int id, int month, int year, boolean getApproved) throws SQLServerException, SQLException {
        
        Connection con = null;
        String approvedQuery = " AND addedToPayment = 'False' ";
        String monthQuery = " AND DATEPART(month, startTime) = "+month+"  ";
        String yearQuery = " AND DATEPART(YEAR, startTime) = "+year+" ";
        ArrayList<TimeSheet> timesheets = new ArrayList<>();
        int res = 0;
        if (getApproved) {
            approvedQuery = " AND addedToPayment = 'True' ";
        }
        if (month==0) {
            monthQuery = "";
        }
        if (year==0) {
            yearQuery = "";
        }
       try
       {
           con = getConnection();
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT * FROM TimeSheet  WHERE empoyeeId ="  + id  +
                                                    monthQuery  +
                                                    yearQuery  +
                                                    approvedQuery + 
                                                    "AND acceptedForSalary IS NULL;");
           while(result.next())
           {
               res++;
           }
       }
       finally
       {
           if(con != null)
           {
               con.close();
           }
       }
        
        
        return res;
    }
    /**
     * Internal metode returns the year as a n int from a timestamp
     * @param tms
     * @return 
     */
    private int getYearFromTimestamp(Timestamp tms)
    {
        long time = tms.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.get(Calendar.YEAR);    
    }
    
    /**
     * Internal metode returns the month as an int from a timestamp
     * @param tms
     * @return 
     */
     private int getMonthFromTimestamp(Timestamp tms)
    {
        long time = tms.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.get(Calendar.MONTH);    
    }
    
}
