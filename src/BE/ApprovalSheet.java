/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

/**
 *
 * @author Poul Nielsen
 */
public class ApprovalSheet {

    private int appId;
    private int firemanId;
    private String appcoment;
    private boolean approved;
    private int hours;
    
    /**
     * 
     * @param appId
     * @param firemanId
     * @param appcoment
     * @param approved
     * @param hours 
     */
    public ApprovalSheet(int appId, int firemanId, String appcoment, boolean approved, int hours) {
        this.appId = appId;
        this.firemanId = firemanId;
        this.appcoment = appcoment;
        this.approved = approved;
        this.hours = hours;
    }

    /**
     * @return the appId
     */
    public int getAppId() {
        return appId;
    }

    /**
     * @param appId the appId to set
     */
    public void setAppId(int appId) {
        this.appId = appId;
    }

    /**
     * @return the firemanId
     */
    public int getFiremanId() {
        return firemanId;
    }

    /**
     * @param firemanId the firemanId to set
     */
    public void setFiremanId(int firemanId) {
        this.firemanId = firemanId;
    }

    /**
     * @return the appcoment
     */
    public String getAppcoment() {
        return appcoment;
    }

    /**
     * @param appcoment the appcoment to set
     */
    public void setAppcoment(String appcoment) {
        this.appcoment = appcoment;
    }

    /**
     * @return the approved
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * @param approved the approved to set
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * @return the hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(int hours) {
        this.hours = hours;
    }
    
}
