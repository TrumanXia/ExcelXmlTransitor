import java.io.File;
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
import org.dom4j.io.OutputFormat;
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
//                如何将文件生成到指定的文件夹中
                String folder = "C:\\Users\\Truman\\Desktop";
//                folder.replace("\\\\", "\\");
                System.out.println(folder);
                try (FileWriter fileWriter = new FileWriter(folder + "\\data.xml")) {
                    OutputFormat format = OutputFormat.createPrettyPrint();
                    format.setIndentSize(10);
                    XMLWriter writer = new XMLWriter(fileWriter, format);
                    writer.write(document);
                    writer.close();
                }
            }
        }
        catch (Exception e) {
        }
    }
}
