import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.List;

public class ExportToFile implements Export{
    private String filePath;
    public ExportToFile(String filePath){
        this.filePath = filePath;
    }

    @Override
    public void exportToXML(List<Contact> contacts, String filePath) throws IOException, XMLStreamException {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        OutputStream outputStream = new FileOutputStream(filePath + ".xml");
        XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(outputStream);
        xmlStreamWriter.writeStartDocument();
        xmlStreamWriter.writeStartElement("contacts");
        for (Contact contact : contacts) {
            xmlStreamWriter.writeStartElement("contact");
            xmlStreamWriter.writeStartElement("firstName");
            xmlStreamWriter.writeCharacters(contact.getFirstName());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("lastName");
            xmlStreamWriter.writeCharacters(contact.getLastName());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("phoneNumber");
            xmlStreamWriter.writeCharacters(contact.getNumber());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndElement();
        }
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.flush();
        xmlStreamWriter.close();
    }

    @Override
    public void exportToCSV(List<Contact> contacts, String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath + ".csv"))) {
            for (Contact contact : contacts) {
                bw.write(contact.getFirstName() + "," + contact.getLastName() + "," + contact.getNumber());
                bw.newLine();
            }
        }
    }
}
