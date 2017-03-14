package com.itextpdf.jumpstart;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;
import java.io.IOException;

/**
 * Created by guo on 9/28/16.
 */
public class TrasparentWatermark {

    public static final String SRC = "/Users/guo/Downloads/Arvix Business Plan.pdf";
    public static final String DEST = "/Users/guo/Downloads/Arvix_Business_Plan_wp.pdf";
    public static final String LOGO = "/Users/guo/Downloads/logo06.png";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        try{
            new TrasparentWatermark().manipulatePdf(SRC,DEST);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void manipulatePdf(String src, String dest) throws IOException{
        PdfDocument markedPdf = new PdfDocument(new PdfWriter(DEST));
        PdfDocument origPdf = new PdfDocument(new PdfReader(src));
        int origPgNum = origPdf.getNumberOfPages();
        PdfPage origPage = null;
        PdfPage newPage = null;
        for (int i=1 ; i <= origPgNum; i++){
            origPage = origPdf.getPage(i);
            newPage = markedPdf.addPage(origPage.copyTo(markedPdf));
            PdfCanvas pdfCanvas = new PdfCanvas(newPage);
            Canvas canvas = new Canvas(pdfCanvas,markedPdf,newPage.getPageSize());
            canvas.setProperty(Property.FONT_COLOR, Color.GRAY);
            canvas.setProperty(Property.FONT_SIZE, 60);
            canvas.setProperty(Property.FONT, PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD));
           // canvas.showTextAligned(new Paragraph("ARVIX INC."), 400,400, i, TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);


            Image image = new Image(ImageDataFactory.create(LOGO));
            image.setFixedPosition(150,100);
            image.setRotationAngle(45);
            image.scaleAbsolute(500,500);

            PdfExtGState transtate = new PdfExtGState();
            transtate.setFillOpacity(0.2f);
            pdfCanvas.saveState().setExtGState(transtate);
            canvas.add(image);
            pdfCanvas.restoreState();
            pdfCanvas.release();
        }

        origPdf.close();
        markedPdf.close();
    }
}
