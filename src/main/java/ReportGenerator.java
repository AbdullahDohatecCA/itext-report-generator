import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfTrueTypeFont;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;
import java.io.FileInputStream;

public class ReportGenerator {
    private static final String reportSavePath = "src/main/resources/output/report.pdf";

    public void generate(){
        try{
            PdfWriter pWriter = new PdfWriter(reportSavePath);
            PdfDocument pDocument = new PdfDocument(pWriter);
            Document document = new Document(pDocument, PageSize.A4);
            document.setMargins(36,36,36,36);

            Image dohatecCaLogo = getImage("src/main/resources/images/dohatec_ca_logo.png");
            Image placeholderData = getImage("src/main/resources/images/placeholder.png",250f,500f);

            Paragraph header = makeHeader(dohatecCaLogo,"individual");

            Table certificateInfoTable = makeTable(new float[]{2,4,2,4});
            addData(certificateInfoTable,0,4,true,"Certificate information");
            addData(certificateInfoTable,true,"Type");
            addData(certificateInfoTable,false,"Individual");
            addData(certificateInfoTable,true,"Class");
            addData(certificateInfoTable,false,"Class 2");
            addData(certificateInfoTable,0,2,true,"Validity");
            addData(certificateInfoTable,0,2,false,"1 year");
            addData(certificateInfoTable,true,"eKYC no.");
            addData(certificateInfoTable,false,"1001");
            addData(certificateInfoTable,true,"RA name");
            addData(certificateInfoTable,false,"Dohatec RA");

            Paragraph personalInfoTitle = makeTitle("Personal information");
            Table personalInfoTable = makeTable(new float[]{2,4,2,4});
            addData(personalInfoTable,true,"Surname");
            addData(personalInfoTable,false,"Data");
            addData(personalInfoTable,true,"Given name");
            addData(personalInfoTable,false,"Demo");
            addData(personalInfoTable,true,"Fahters' name");
            addData(personalInfoTable,0,0,false,"Demo data");
            addData(personalInfoTable,true,"Mothers' name");
            addData(personalInfoTable,0,0,false,"Demo data");
            addData(personalInfoTable,true,"Gender");
            addData(personalInfoTable,false,"Demo data");
            addData(personalInfoTable,true,"Nationality");
            addData(personalInfoTable,false,"Demo data");
            addData(personalInfoTable,true,"NID");
            addData(personalInfoTable,false,"Demo data");
            addData(personalInfoTable,true,"Date of birth");
            addData(personalInfoTable,false,"Demo data");


            Paragraph personalAddressTitle = makeTitle("Parsonal address");
            Table personalAddressTable = makeTable(new float[]{2,4,2,4});
            addData(personalAddressTable,true,"House no.");
            addData(personalAddressTable,false,"Demo data");
            addData(personalAddressTable,true,"Street address");
            addData(personalAddressTable,false,"Demo data");
            addData(personalAddressTable,true,"City");
            addData(personalAddressTable,false,"Demo data");
            addData(personalAddressTable,true,"Postcode");
            addData(personalAddressTable,false,"Demo data");
            addData(personalAddressTable,true,"District");
            addData(personalAddressTable,false,"Demo data");
            addData(personalAddressTable,true,"Country");
            addData(personalAddressTable,false,"Demo data");

            Paragraph orgInfoTitle = makeTitle("Organization information");
            Table orgInfoTable = makeTable(new float[]{2,4,2,4});
            addData(orgInfoTable,true,"Type");
            addData(orgInfoTable,"Demo data");
            addData(orgInfoTable,true,"Name");
            addData(orgInfoTable,"Demo data");
            addData(orgInfoTable,true,"Applicant designation");
            addData(orgInfoTable,"Demo data");
            addData(orgInfoTable,true,"Applicant unit");
            addData(orgInfoTable,"Demo data");
            addData(orgInfoTable,true,"House no.");
            addData(orgInfoTable,"Demo data");
            addData(orgInfoTable,true,"Street address");
            addData(orgInfoTable,"Demo data");
            addData(orgInfoTable,true,"City");
            addData(orgInfoTable,"Demo data");
            addData(orgInfoTable,true,"Postcode");
            addData(orgInfoTable,"Demo data");
            addData(orgInfoTable,true,"District");
            addData(orgInfoTable,"Demo data");
            addData(orgInfoTable,true,"Country");
            addData(orgInfoTable,"Demo data");


            Paragraph documentsTitle = makeTitle("Submitted documents");

            Table documentsTable1 = makeTable(new float[]{6,6});
            addData(documentsTable1,true,"NID");
            addData(documentsTable1,true,"Passport");
            addData(documentsTable1,placeholderData);
            addData(documentsTable1,placeholderData);

            Paragraph footer = makeFooter("Dohatec New Media, Doha House, 43, Purana Paltan Line, Dhaka-1000\n" +
                    "Phone: +880-1678625336 Email: helpdesk@dohatec-ca.com.bd");

            document.add(header);
            document.add(certificateInfoTable);
            document.add(personalInfoTitle);
            document.add(personalInfoTable);
            document.add(personalAddressTitle);
            document.add(personalAddressTable);
            document.add(orgInfoTitle);
            document.add(orgInfoTable);
            document.add(footer);
            document.add(new AreaBreak());
            document.add(header);
            document.add(documentsTitle);
            document.add(documentsTable1);
            document.add(footer);

            pDocument.close();
            pWriter.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private PdfFont getNormalFont() {
        try{
            return PdfFontFactory.createFont(StandardFonts.HELVETICA);
        }
        catch (Exception e){
            System.out.println("Error in font: "+e.getMessage());
            return null;

        }
    }

    private PdfFont getBoldFont() {
        try{
            return PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        }
        catch (Exception e){
            System.out.println("Error in font: "+e.getMessage());
            return null;

        }
    }

    private Image getImage(String imagePath){
        try{
            return new Image(
                    ImageDataFactory.create(imagePath)
            );
        }
        catch (Exception e){
            System.out.println("Error in image: "+e.getMessage());
            return null;
        }
    }

    private Image getImage(String imagePath,float width,float height){
        try{
            return new Image(
                    ImageDataFactory.create(imagePath)
            ).scaleAbsolute(width,height);
        }
        catch (Exception e){
            System.out.println("Error in image: "+e.getMessage());
            return null;
        }
    }

    private Paragraph makeHeader(Image headerImage, String headerText){
        return new Paragraph().add(headerImage)
                .add(
                        new Paragraph(headerText.toUpperCase())
                                .setMarginLeft(256f)
                                .setFont(getBoldFont())
                )
                .setPaddings(8f,0,8f,0)
                .setMarginBottom(16f)
                .setBorderBottom(new SolidBorder(ColorConstants.LIGHT_GRAY,4));
    }

    private Paragraph makeTitle(String titleText){
        return new Paragraph().add(titleText)
                .setFont(getBoldFont())
                .setFontSize(16f)
                .setMarginBottom(8f);
    }

    private Table makeTable(float[] columns){
        return new Table(
                UnitValue.createPercentArray(columns)
        ).useAllAvailableWidth().setMarginBottom(16f);
    }

    private void addData(Table table, String data){
        addData(table,false,data);
    }

    private void addData(Table table, boolean isBold, String data){
        addData(table,0,0,isBold,data);
    }

    private void addData(Table table,int rowspan, int colspan, boolean isBold, String data){
        Paragraph dataParagraph;
        dataParagraph = new Paragraph().add(data);
        if(isBold) {
            dataParagraph.setFont(getBoldFont());
        }
        else {
            dataParagraph.setFont(getNormalFont());
        }

        Cell dataCell = new Cell(rowspan,colspan).add(dataParagraph);
        table.addCell(dataCell);
    }

    private void addData(Table table, Image data){
        addData(table,0,0,data);
    }

    private void addData(Table table,int rowspan, int colspan, Image data){
        Cell dataCell = new Cell(rowspan,colspan).add(data);
        table.addCell(dataCell);
    }

    private Paragraph makeFooter(String footerText) {
        return new Paragraph()
                .add(footerText)
                .setFont(getNormalFont())
                .setFontColor(ColorConstants.LIGHT_GRAY)
                .setFontSize(8f)
                .setPaddings(8f,0,8f,0)
                .setBorderTop(new SolidBorder(ColorConstants.LIGHT_GRAY,4));
    }
}