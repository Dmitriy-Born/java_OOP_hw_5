import javax.management.modelmbean.XMLParseException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface Import {
    public List<Contact> importFromXML (String filePath) throws IOException, XMLParseException, XMLStreamException;
    public List<Contact> importFromCSV (String filePath) throws IOException;

}
