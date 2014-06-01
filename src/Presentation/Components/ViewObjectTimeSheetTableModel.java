/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import BE.TimeSheet;
import BLL.MyUtil;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Poul Nielsen
 */
    public class ViewObjectTimeSheetTableModel extends AbstractTableModel{
private ArrayList<TimeSheet> vos;
Calendar date = Calendar.getInstance();
    // The names of columns
    private String[] colNames = {"Dato" , "Start", "Slut", "Timer", "Position", "Øvelse"};
    // The type of columns
    private Class[] classes = {
        String.class, String.class , String.class, String.class, String.class, String.class};
   
    /**
     * Creates a new ViewObjectTableModel
     * @param allViewObjects a list of view objects that should be shown in the model
     */
    public ViewObjectTimeSheetTableModel() {
        vos = new ArrayList();
        fireTableDataChanged();
    }

    /**
     * Gets the number of rows in the model
     * @return the number of rows in the model
     */
    @Override
    public int getRowCount() {
            return vos.size();
    }

    /**
     * Gets the number of coloums in the model
     * @return the number of coloums in the model
     */
    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    
    
    /**
     * Gets the value at a given cell in the model
     * @param row the row of the cell
     * @param col the col of the cell
     * @return the value in the given cell
     */
    @Override
    public Object getValueAt(int row, int col) {
        TimeSheet vo = vos.get(row);
        switch (col) {
            case 0:
                date.setTimeInMillis(vo.getStartTime().getTime());
                return "" + date.get(Calendar.DAY_OF_MONTH)+"/"+(date.get(Calendar.MONTH)+1);
            case 1:
                date.setTimeInMillis(vo.getStartTime().getTime());
                return "" + MyUtil.p0(date.get(Calendar.HOUR_OF_DAY)) + ":" 
                            + MyUtil.p0(date.get(Calendar.MINUTE));
            case 2:
                date.setTimeInMillis(vo.getEndTime().getTime());
                return ""+ MyUtil.p0(date.get(Calendar.HOUR_OF_DAY)) + ":" 
                           + MyUtil.p0(date.get(Calendar.MINUTE));
            case 3:
                return " "+vo.getacceptedForSalary().getHours();
            case 4:
                return ""+vo.getPos().getName();
            case 5:
                if (vo.isExercise()) {
                    return "X";
                }
                return "";
        }

        return null;
    }
    /**
     * retuns the timesheet of the row selected
     * @param row
     * @return 
     */
    public TimeSheet getTimeSheet(int row)
    {
        return vos.get(row);
    }
   
    @Override
    public String getColumnName(int col) {
         return colNames[col];
    }

    /**
     * Gets the class of the given coloum
     * @param col the coloum
     * @return the class of the given coloum
     */
    @Override
    public Class<?> getColumnClass(int col) {
        return getValueAt(0, col).getClass();
        //return classes[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * Sets the content of the table model to the given list of firemen.
     *
     * @param procList the list of Projects to show in the JTable.
     */
    public void setViewObjectList(ArrayList<TimeSheet> voList) {
        vos = voList;
    }

    /**
     * Return the firemen instance from the table model with the given row index.
     *
     * @param row the index for the song in the cars list.
     * @return the song at the given row index.
     */
    public TimeSheet getViewObjectByRow(int row) {
        return vos.get(row);
    }
    /**
     * add the timesheet to the table and fire a tabledatachanged
     * @param timesheet 
     */
    public void addTimeSheet(TimeSheet timesheet)
    {
        vos.add(timesheet);
        fireTableDataChanged();
    }
    
    /**
     * clears the table and update the table
     */
    public void clearList(){
        vos.clear();
        fireTableDataChanged();
    }
     
}
