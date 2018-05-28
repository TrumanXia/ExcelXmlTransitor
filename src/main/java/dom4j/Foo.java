package dom4j;

import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Foo {

    public void write(Document document) throws IOException {

        // lets write to a file
        try (FileWriter fileWriter = new FileWriter("data.xml")) {
            XMLWriter writer = new XMLWriter(fileWriter);
            writer.write( document );
            writer.close();
        }


        // Pretty print the document to System.out
        OutputFormat format = OutputFormat.createPrettyPrint();
        writer = new XMLWriter(System.out, format);
        writer.write( document );

        // Compact format to System.out
        format = OutputFormat.createCompactFormat();
        writer = new XMLWriter(System.out, format);
        writer.write(document);
        writer.close();
    }
}