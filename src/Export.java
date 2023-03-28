import javax.management.modelmbean.XMLParseException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface Export {
    public void exportToXML(List<Contact> contacts, String filePath) throws IOException, XMLStreamException;
    public void exportToCSV(List<Contact> contacts, String filePath) throws IOException;

}
