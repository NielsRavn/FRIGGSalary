/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

/**
 *
 * @author Niels
 */
public class MyUtil {
    
    /**
     * add a 0 in front of the given number if it is lower than 10
     * this is just pretyfying
     * @param i the number to add a zero infront
     * @return a string with the number and a zero infront if it is less than 10.
     */
    public static String p0(int i){
        if(i<10){
            return "0"+i;
        }
        return "" +i;
    }
    
}
