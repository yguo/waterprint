package com.itextpdf.jumpstart;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by guo on 9/28/16.
 */
public class HelloWorld {

    public static final String DEST = "/Users/guo/Documents/hello_world.pdf";
    public static final String LOGO = "/Users/guo/Documents/logo.png";

    public static final String ORIGINAL = "/Users/guo/Downloads/huaqiaocheng_s.pdf";
    public static final String WATER_PRINTED = "/Users/guo/Downloads/huaqiaocheng_w.pdf";

    static PdfFont helvetica = null;
    static PdfFont helveticaBold = null;

   /* public static void main1(String[] args) throws IOException{
        helvetica = PdfFontFactory.createFont(FontConstants.HELVETICA);
        helveticaBold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        File file = new File(WATER_PRINTED);
        file.getParentFile().mkdirs();
        try{
            new HelloWorld().createPdf(DEST);
        }catch(Exception e){
            e.printStackTrace();
        }


    }*/




    public void createPdf(String destination) throws Exception {

        FileOutputStream fos = new FileOutputStream(DEST);
        PdfWriter writer = new PdfWriter(fos);

        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf);
        document.add(new Paragraph("Hello World"));

        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

        document.add(new Paragraph("iText is:").setFont(font));
        List list = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(font);
        list.add(new ListItem("No 1. Never gonna give you up"))
                .add(new ListItem("No 2. Never gonna let you down"))
                .add(new ListItem("No 3. Never gonna run around and desert you"))
                .add(new ListItem("No 4. Never gonna make you cry"))
                .add(new ListItem("No 5. Never gonna say goodbye"));
        document.add(list);

        Image logo = new Image(ImageDataFactory.create(LOGO));

        Paragraph paragraph = new Paragraph("Logo is: ")
                .add(logo);
        document.add(paragraph);
        document.close();
    }


}
