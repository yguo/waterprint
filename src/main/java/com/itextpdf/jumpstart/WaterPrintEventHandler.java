package com.itextpdf.jumpstart;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.awt.*;

/**
 * Created by guo on 9/28/16.
 */
 class WaterPrintEventHandler implements IEventHandler{

    static PdfFont helveticaBold = null;
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent)event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        int PageNumber = pdfDoc.getPageNumber(page);
        PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

        Canvas canvas = new Canvas(pdfCanvas, pdfDoc, page.getPageSize());
        canvas.setProperty(Property.FONT_COLOR, com.itextpdf.kernel.color.Color.GRAY);
        canvas.setProperty(Property.FONT_SIZE, 60);
       // canvas.setProperty(Property.FONT, helveticaBold);
        canvas.showTextAligned(new Paragraph("ARVIX INC."), 298,421, pdfDoc.getPageNumber(page),
                TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);
        pdfCanvas.release();

        //Add watermark
    }
}
