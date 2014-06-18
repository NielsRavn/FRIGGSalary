/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Frames;

import BE.Fireman;
import BE.TimeSheet;
import BLL.Fireman_AccessLink;
import BLL.Pdf_AccesLink;
import BLL.TimeSheet_AccessLink;
import DAL.PdfCreater;
import Presentation.Components.ViewObjectTimeSheetTableModel;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Poul Nielsen
 */
public class TimeSheetOverview extends javax.swing.JPanel {

    Fireman_AccessLink fal;
    TimeSheet_AccessLink tsa;
    DefaultListModel firemenModel;
    ViewObjectTimeSheetTableModel model;
    Header header;
    int year = 0;
    /**
     * Creates new form TimeSheetOverview
     */
    public TimeSheetOverview() {
        firemenModel = new DefaultListModel();
        model = new ViewObjectTimeSheetTableModel();
        header = new Header();
       
        try {
            fal = new Fireman_AccessLink();
            tsa = new TimeSheet_AccessLink();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Der er problemer med databasen");
        }
       
        initComponents();
        lblUnapproved.setForeground(Color.red);
         this.add(header, BorderLayout.NORTH);
        jbPrintPDF.addActionListener(new MyActionlistener());
        populateFiremanList();
        //Populates the months in dropdown
        jcbMonth.setModel(new DefaultComboBoxModel(new String[]{"", "Januar", "Februar", "Marts",
            "April", "Maj", "Juni", "Juli",
            "August", "September", "Oktober",
            "November", "December"}));
        //Gets the current year 
        txtYear.setText("" + Calendar.getInstance().get(Calendar.YEAR));

        jLFiremanList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                
        txtSearchEmployId.setText("" + ((Fireman) jLFiremanList.getSelectedValue()).getUserId());//set the search field to current selected worker id
                populateTableWithTimeSheetsFromSearchQery();
            }
        });

    }
    
    /**
     * This methode populates the data and checks if there is any search query
     */
    private void populateTableWithTimeSheetsFromSearchQery(){
        
         if (!txtYear.getText().equals("")) {
            year = testInputFromTxtBoxWithAlert(txtYear.getText(), "Det indtastet årstal er ikke et nummer");
        }
        
         boolean getApproved = cbxShowApproved.isSelected(); 
        
         int firemanId = testInputFromTxtBoxWithAlert(txtSearchEmployId.getText(), "Det indtastet medarbejds nr. er ikke et nummer");
         
         int month = jcbMonth.getSelectedIndex();
         
        try {
            ArrayList<TimeSheet> ts = tsa.getTimeSheetByFiremanIdMonthYear(firemanId, month, year, getApproved);
            model.clearList();
            for (TimeSheet a : ts) {
                model.addTimeSheet(a);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Der er problemer med databasen " + ex);
        }
        jttimesheettable.setModel(model);
        validate();
        repaint();
        
        findUnapprovedTimesheets();
    }
    
    private void findUnapprovedTimesheets(){
        int numberOfUnapprovedTimesheets = 0;
        if (!txtYear.getText().equals("")) {
            year = testInputFromTxtBoxWithAlert(txtYear.getText(), "Det indtastet årstal er ikke et nummer");
        }
        
         boolean getApproved = cbxShowApproved.isSelected(); 
        Fireman fireman = null;
        int firemanId = testInputFromTxtBoxWithAlert(txtSearchEmployId.getText(), "Det indtastet medarbejds nr. er ikke et nummer");
        try {
            fireman = fal.getFiremanById(firemanId);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Der er problemer med databasen " + ex);
        }
         
        int month = jcbMonth.getSelectedIndex();

        try {
           numberOfUnapprovedTimesheets = tsa.getNumberOfUnapprovedTimeSheetByFiremanIdMonthYear(firemanId, month, year, getApproved);
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Der er problemer med databasen " + ex);
        }
        if(numberOfUnapprovedTimesheets != 0){
            lblUnapproved.setText("Advarsel: " +fireman.getFirstName() + " " + fireman.getLastName() +" har " + numberOfUnapprovedTimesheets + " køresedler der mangler at blive godkendt i den valgte periode.");
            validate();
            repaint();
        }else{
            lblUnapproved.setText("");
            validate();
            repaint();
        }
         
    }
    
    /**
     * Sets the list of firemen
     */
    private void populateFiremanList() {
        try {
            ArrayList<Fireman> fireman = fal.getAllFiremen();

            for (Fireman a : fireman) {
                firemenModel.addElement(a);
                jLFiremanList.setModel(firemenModel);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Der er problemer med databasen " + ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpFiremanPanel = new javax.swing.JPanel();
        jSFiremanScrollpane = new javax.swing.JScrollPane();
        jLFiremanList = new javax.swing.JList();
        lblFiremen = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearchEmployId = new javax.swing.JTextField();
        cbxShowApproved = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jcbMonth = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtYear = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jbPrintPDF = new javax.swing.JButton();
        jPtableholder = new javax.swing.JPanel();
        jStable = new javax.swing.JScrollPane();
        jttimesheettable = new javax.swing.JTable();
        lblUnapproved = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jpFiremanPanel.setPreferredSize(new java.awt.Dimension(180, 800));
        jpFiremanPanel.setLayout(new java.awt.BorderLayout());

        jLFiremanList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jSFiremanScrollpane.setViewportView(jLFiremanList);

        jpFiremanPanel.add(jSFiremanScrollpane, java.awt.BorderLayout.CENTER);

        lblFiremen.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        lblFiremen.setText("Brandmænd");
        jpFiremanPanel.add(lblFiremen, java.awt.BorderLayout.PAGE_START);

        add(jpFiremanPanel, java.awt.BorderLayout.WEST);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1282, 100));
        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel1.setText("Medarbejder nr.");
        jPanel3.add(jLabel1);

        txtSearchEmployId.setPreferredSize(new java.awt.Dimension(80, 30));
        txtSearchEmployId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchEmployIdActionPerformed(evt);
            }
        });
        jPanel3.add(txtSearchEmployId);

        cbxShowApproved.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        cbxShowApproved.setText("Vis godkendte");
        cbxShowApproved.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxShowApprovedActionPerformed(evt);
            }
        });
        jPanel3.add(cbxShowApproved);

        jPanel1.add(jPanel3);

        jPanel4.setPreferredSize(new java.awt.Dimension(476, 50));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jcbMonth.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jcbMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMonthActionPerformed(evt);
            }
        });
        jPanel4.add(jcbMonth);

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel2.setText("Månede");
        jPanel4.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel3.setText("År");
        jPanel4.add(jLabel3);

        txtYear.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        txtYear.setPreferredSize(new java.awt.Dimension(80, 21));
        txtYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearActionPerformed(evt);
            }
        });
        jPanel4.add(txtYear);

        jLabel4.setPreferredSize(new java.awt.Dimension(100, 10));
        jPanel4.add(jLabel4);

        jbPrintPDF.setText("Print til pdf");
        jPanel4.add(jbPrintPDF);

        jPanel1.add(jPanel4);

        jPanel2.add(jPanel1, java.awt.BorderLayout.NORTH);

        jStable.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jttimesheettable.setModel(new ViewObjectTimeSheetTableModel());
        jStable.setViewportView(jttimesheettable);

        javax.swing.GroupLayout jPtableholderLayout = new javax.swing.GroupLayout(jPtableholder);
        jPtableholder.setLayout(jPtableholderLayout);
        jPtableholderLayout.setHorizontalGroup(
            jPtableholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPtableholderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jStable, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(440, Short.MAX_VALUE))
        );
        jPtableholderLayout.setVerticalGroup(
            jPtableholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPtableholderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jStable, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel2.add(jPtableholder, java.awt.BorderLayout.CENTER);
        jPanel2.add(lblUnapproved, java.awt.BorderLayout.SOUTH);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * if a workid is entert, this will trigger the search method
     * @param evt 
     */
    private void txtSearchEmployIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchEmployIdActionPerformed
      populateTableWithTimeSheetsFromSearchQery();
    }//GEN-LAST:event_txtSearchEmployIdActionPerformed
    /**
     * if the year is changed, then the table will update
     * @param evt 
     */
    private void txtYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYearActionPerformed
      populateTableWithTimeSheetsFromSearchQery();
    }//GEN-LAST:event_txtYearActionPerformed
    
    /**
     * If the month is changed, then the table will update
     * @param evt 
     */
    private void jcbMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMonthActionPerformed
        populateTableWithTimeSheetsFromSearchQery();
    }//GEN-LAST:event_jcbMonthActionPerformed
    /**
     * if a check is set in the show aproved timesheets checkbox, then the table will update
     * @param evt 
     */
    private void cbxShowApprovedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxShowApprovedActionPerformed
        populateTableWithTimeSheetsFromSearchQery();
    }//GEN-LAST:event_cbxShowApprovedActionPerformed
    
    /**
     * This test the input if it is an integer, and retruns an allert message
     * @param input
     * @param AlertMessage
     * @return 
     */
    private int testInputFromTxtBoxWithAlert(String input, String AlertMessage) {
        int id = 0;
        try {
            id = Integer.parseInt(input);
            return id;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, AlertMessage);
            return id;
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbxShowApproved;
    private javax.swing.JList jLFiremanList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPtableholder;
    private javax.swing.JScrollPane jSFiremanScrollpane;
    private javax.swing.JScrollPane jStable;
    private javax.swing.JButton jbPrintPDF;
    private javax.swing.JComboBox jcbMonth;
    private javax.swing.JPanel jpFiremanPanel;
    private javax.swing.JTable jttimesheettable;
    private javax.swing.JLabel lblFiremen;
    private javax.swing.JLabel lblUnapproved;
    private javax.swing.JTextField txtSearchEmployId;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables

    private class MyActionlistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbPrintPDF) {
                Pdf_AccesLink pdf = new Pdf_AccesLink(model);
                JFileChooser fc = new JFileChooser();
                String filePath = "";
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (fc.showSaveDialog(TimeSheetOverview.this) == JFileChooser.APPROVE_OPTION) {
                    filePath = (fc.getSelectedFile().getAbsolutePath() + "\\");;
                    try {
                        if (model.getRowCount() > 0) {
                            pdf.createPdf(filePath);
                        } else {
                            JOptionPane.showMessageDialog(TimeSheetOverview.this, "Du skal vælge en brandmand der har haft vagter i den pågældende periode for at kunne skrive til et dokument.");
                        }

                    } catch (DocumentException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(TimeSheetOverview.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }

    }
}
