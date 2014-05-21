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
public class Fireman {

    private int userId;
    private String firstName;
    private String lastName;
    private boolean teamleader;
    private boolean driver;
    
    public Fireman(int userId, String firstName, String lastName, boolean teamleader, boolean driver) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamleader = teamleader;
        this.driver = driver;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the teamleader
     */
    public boolean isTeamleader() {
        return teamleader;
    }

    /**
     * @param teamleader the teamleader to set
     */
    public void setTeamleader(boolean teamleader) {
        this.teamleader = teamleader;
    }

    /**
     * @return the driver
     */
    public boolean isDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(boolean driver) {
        this.driver = driver;
    }
    
}
