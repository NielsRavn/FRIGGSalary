/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.TimeSheet;
import BLL.Fireman_AccessLink;
import BLL.MyUtil;
import Presentation.Components.ViewObjectTimeSheetTableModel;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Brobak
 */
public class PdfCreater {
    private static final BaseColor COLOR_BLUE = new CMYKColor(255, 255, 0, 0);
    private ViewObjectTimeSheetTableModel model;
    private String fileName;
    private final PdfPCell emptyCell = new PdfPCell();
    
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private Fireman_AccessLink fal;
    public PdfCreater(ViewObjectTimeSheetTableModel model) throws IOException{
        emptyCell.setBorderColor(BaseColor.WHITE);
        fal = new Fireman_AccessLink();
        this.model = model;
    }

    public void createPdf(String filePath) throws DocumentException, FileNotFoundException, BadElementException, IOException, SQLException {
         
        
        Calendar date = Calendar.getInstance();
        String dato;
        dato = ""+date.get(Calendar.YEAR)+"-"+MyUtil.p0(date.get(Calendar.MONTH)+1)+"-"+MyUtil.p0((date.get(Calendar.DAY_OF_MONTH)))+"-";
        
        String name = fal.getFiremanById(model.getTimeSheet(0).getEmployeeId()).getFirstName() +" "+ fal.getFiremanById(model.getTimeSheet(0).getEmployeeId()).getLastName();
        fileName = dato + name + ".pdf";
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath+fileName));
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

 

    private void addContent(Document document) throws DocumentException, BadElementException, IOException, SQLException {
        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");
        
        
        
        
        
        Paragraph spacing = new Paragraph("");
        addEmptyLine(spacing, 1);
        
        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(1);
        addHeader(catPart);
        catPart.add(spacing);
        
        addEmployeeInfo(catPart);
        catPart.add(spacing);

        createMainTable(catPart);
        catPart.add(spacing);
        
        createTotalHoursTable(catPart);
        catPart.add(spacing);
        Calendar date = Calendar.getInstance();
        String dato;
        dato = (date.get(Calendar.DAY_OF_MONTH))+"/"+(date.get(Calendar.MONTH)+1)+"-"+date.get(Calendar.YEAR)+" " + MyUtil.p0(date.get(Calendar.HOUR_OF_DAY)) + ":" + MyUtil.p0(date.get(Calendar.MINUTE));
        Paragraph printedInfo = new Paragraph("Printet "+ dato);
        printedInfo.setAlignment(Element.ALIGN_CENTER);
        catPart.add(printedInfo);
        
        document.add(catPart);

    }
    
    private void addEmployeeInfo(Section catPart) throws SQLException, IOException{
        PdfPTable employee = new PdfPTable(1);
        
        Paragraph empId = new Paragraph("Medarbejdernummer: "+model.getTimeSheet(0).getEmployeeId());
        PdfPCell empIdCell = new PdfPCell(empId);
        empIdCell.setBorderColor(BaseColor.WHITE);
        String name = fal.getFiremanById(model.getTimeSheet(0).getEmployeeId()).getFirstName() +" "+ fal.getFiremanById(model.getTimeSheet(0).getEmployeeId()).getLastName();
        Paragraph empName = new Paragraph("Navn: " + name);
        PdfPCell empNameCell = new PdfPCell(empName);
        empNameCell.setBorderColor(BaseColor.WHITE);
        
        employee.setHeaderRows(0);
        employee.addCell(empIdCell);
        employee.addCell(empNameCell);
        employee.setWidthPercentage(80.0f);
        
        catPart.add(employee);
    }
    
    private void addHeader(Section catPart) throws BadElementException, IOException{
        PdfPTable header = new PdfPTable(1);
        PdfPCell c = new PdfPCell(Image.getInstance("res/brandogredninglogo.png"));
        c.setBackgroundColor(COLOR_BLUE);
        //c.setBorderColor(BaseColor.RED);
        header.setHeaderRows(0);
        header.addCell(c);
        header.setWidthPercentage(100.0f);
        
        catPart.add(header);
    }
    
    private void createTotalHoursTable(Section catPart) throws DocumentException{
        int totalHours = 0;
        for(int row = 0; row < model.getRowCount(); row++){
            totalHours += model.getTimeSheet(row).getacceptedForSalary().getHours();
        }
        
        Paragraph hours = new Paragraph("Timer i alt: " + totalHours);
        PdfPCell hoursCell = new PdfPCell(hours);
        hoursCell.setBorderColor(BaseColor.WHITE);
        PdfPTable tblHours = new PdfPTable(2);
        tblHours.setWidths(new float[]{2,1});
        tblHours.addCell(emptyCell);
        tblHours.addCell(hoursCell);
        catPart.add(tblHours);
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
                String input = model.getValueAt(row, col).toString();
                if(input.equals("true"))
                    input = "X";
                if(input.equals("false"))
                    input = "";
                PdfPCell cell = new PdfPCell(new Phrase(input));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
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
