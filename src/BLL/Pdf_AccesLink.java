/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.PdfCreater;
import Presentation.Components.ViewObjectTimeSheetTableModel;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Brobak
 */
public class Pdf_AccesLink {
    ViewObjectTimeSheetTableModel model;
    public Pdf_AccesLink(ViewObjectTimeSheetTableModel model){
        this.model = model;
    }
    
    public void createPdf(String filePath) throws DocumentException, BadElementException, IOException, SQLException{
        PdfCreater pdf = new PdfCreater(model);
        pdf.createPdf(filePath);
    }
    
}
