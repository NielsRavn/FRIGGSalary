/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

import java.sql.Timestamp;

/**
 *
 * @author Poul Nielsen
 */
public class TimeSheet {
    
    private int timeSheetId;
    private int employeeId;
    private int alarmId;
    private int carNr;
    private Position pos;
    private Timestamp startTime;
    private Timestamp endtime;
    private int acceptedByTeamleader;
    private int acceptedForSalary;
    private boolean addedToPayment;
    private ApprovalSheet approvedSheetacceptedForSalary;
    
    public TimeSheet(   int timeSheetId, 
                                int employeeId, 
                                int alarmId, 
                                int carNr, 
                                Position pos, 
                                Timestamp startTime, 
                                Timestamp endtime, 
                                int acceptedByTeamleader, 
                                int acceptedForSalary, 
                                boolean addedToPayment, 
                                String comment,
                                ApprovalSheet approvedSheetacceptedForSalary) {
      this.timeSheetId = timeSheetId;
      this.employeeId = employeeId;
      this.alarmId = alarmId;
      this.carNr = carNr;
      this.pos = pos;
      this.startTime = startTime;
      this.endtime = endtime;
      this.acceptedByTeamleader = acceptedByTeamleader;
      this.acceptedForSalary = acceptedForSalary;
      this.addedToPayment = addedToPayment;
      this.approvedSheetacceptedForSalary = approvedSheetacceptedForSalary;
    }


    /**
     * @return the timeSheetId
     */
    public int getTimeSheetId() {
        return timeSheetId;
    }

    /**
     * @param timeSheetId the timeSheetId to set
     */
    public void setTimeSheetId(int timeSheetId) {
        this.timeSheetId = timeSheetId;
    }

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the alarmId
     */
    public int getAlarmId() {
        return alarmId;
    }

    /**
     * @param alarmId the alarmId to set
     */
    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    /**
     * @return the carNr
     */
    public int getCarNr() {
        return carNr;
    }

    /**
     * @param carNr the carNr to set
     */
    public void setCarNr(int carNr) {
        this.carNr = carNr;
    }

    /**
     * @return the pos
     */
    public Position getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(Position pos) {
        this.pos = pos;
    }

    /**
     * @return the startTime
     */
    public Timestamp getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endtime
     */
    public Timestamp getEndTime() {
        return endtime;
    }

    /**
     * @param endtime the endtime to set
     */
    public void setEndTime(Timestamp endtime) {
        this.endtime = endtime;
    }

    /**
     * @return the acceptedByTeamleader
     */
    public int getAcceptedByTeamleaderId() {
        return acceptedByTeamleader;
    }

    /**
     * @param acceptedByTeamleader the acceptedByTeamleader to set
     */
    public void setAcceptedByTeamleaderId(int acceptedByTeamleader) {
        this.acceptedByTeamleader = acceptedByTeamleader;
    }

    /**
     * @return the acceptedForSalary
     */
    public int getAcceptedForSalary() {
        return acceptedForSalary;
    }

    /**
     * @param acceptedForSalary the acceptedForSalary to set
     */
    public void setAcceptedForSalary(int acceptedForSalary) {
        this.acceptedForSalary = acceptedForSalary;
    }

    /**
     * @return the addedToPayment
     */
    public boolean isAddedToPayment() {
        return addedToPayment;
    }

    /**
     * @param addedToPayment the addedToPayment to set
     */
    public void setAddedToPayment(boolean addedToPayment) {
        this.addedToPayment = addedToPayment;
    }

    /**
     * @return the approvedSheetAcceptedbyTeamleader
     */
    public ApprovalSheet getacceptedForSalary() {
        return approvedSheetacceptedForSalary;
    }

    /**
     * 
     * @param approvedacceptedForSalary 
     */
    public void setapprovedacceptedForSalary(ApprovalSheet approvedacceptedForSalary) {
        this.approvedSheetacceptedForSalary = approvedacceptedForSalary;
    }
}
