/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.TimeSheet;
import Presentation.Components.ViewObjectTimeSheetTableModel;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Brobak
 */
public class PdfCreater {

    private ViewObjectTimeSheetTableModel model;
    private String file = "c:/temp/FirstPdf.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    
    public PdfCreater(ViewObjectTimeSheetTableModel model){
        this.model = model;
    }

    public void createPdf(String filePath) throws DocumentException, FileNotFoundException, BadElementException, IOException {
         
        file = filePath;
        file = "c:/temp/FirstPdf.pdf";
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        //addMetaData(document);
        //addTitlePage(document);
        addContent(document);
        document.close();

    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

 

    private void addContent(Document document) throws DocumentException, BadElementException, IOException {
        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");
        
        PdfPTable header = new PdfPTable(1);
        PdfPCell c = new PdfPCell(Image.getInstance("res/brandogredninglogo.png"));
        c.setBackgroundColor(BaseColor.BLUE);
        //c.setBorderColor(BaseColor.RED);
        header.setHeaderRows(0);
        header.addCell(c);
        header.setWidthPercentage(100.0f);
        

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(1);
        catPart.add(header);
        
        
        
        
        Section subCatPart = catPart.addSection(new Paragraph("Hello", subFont));
        Paragraph stars = new Paragraph(20);
        addEmptyLine(stars, 2);
        
        subCatPart.add(stars);

        createMainTable(subCatPart);

        document.add(catPart);

    }

    private void createMainTable(Section subCatPart)
            throws BadElementException, MalformedURLException, IOException {
        
        
        PdfPTable table = new PdfPTable(model.getColumnCount());
        table.setWidthPercentage(100.0f);
        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        
        PdfPCell c1;
        for(int i = 0; i < model.getColumnCount(); i++){
            c1 = new PdfPCell(new Phrase(model.getColumnName(i)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }
        table.setHeaderRows(1);

        for(int row = 0; row < model.getRowCount(); row++){
            for (int col = 0; col < model.getColumnCount(); col++) {
                table.addCell(model.getValueAt(row, col).toString());
                
            }
        }
       
        //setBackgroundColor(BaseColor.BLUE);
        
        //image.setAbsolutePosition(100f, 650f);
        //subCatPart.add(image);

        subCatPart.add(table);
        
        
        
        

    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
