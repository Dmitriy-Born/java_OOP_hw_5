import javax.management.modelmbean.XMLParseException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws XMLStreamException, IOException, XMLParseException {

        Menu menu = new Menu("contacts");

        menu.work();
    }
}