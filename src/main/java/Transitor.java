import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

public class Transitor
{

    private static HSSFWorkbook readFile(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            return new HSSFWorkbook(fis); // NOSONAR - should not be closed here
        }
    }

    // private static Document getDocument() {
    //
    // return document;
    // }

    public static void main(String[] args) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");
        try {
            try (Workbook wb = readFile("example.xls")) {
                Sheet sheet = wb.getSheetAt(0);
                int rows = sheet.getPhysicalNumberOfRows();
                for (int i = 0; i < rows; i++) {
                    HSSFRow row = (HSSFRow) sheet.getRow(i);
                    // System.out.println(row.getPhysicalNumberOfCells());
                    // System.out.println(row.getCell(0) + " : " +
                    // row.getCell(1));
                    root.addElement("data")
                            .addAttribute("order", row.getCell(0)
                                    .toString())
                            .addAttribute("number", row.getCell(1)
                                    .toString());
                }
                try (FileWriter fileWriter = new FileWriter("data.xml")) {
                    XMLWriter writer = new XMLWriter(fileWriter);
                    writer.write( document );
                    writer.close();
                }
            }
        }
        catch (Exception e) {
        }
    }
}
