import javax.management.modelmbean.XMLParseException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImportToFile implements Import{
    private String filePath;
    public ImportToFile(String filePath){
        this.filePath = filePath;
    }

    @Override
    public List<Contact> importFromXML(String filePath) throws IOException, XMLParseException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        InputStream inputStream = new FileInputStream(filePath + ".xml");
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
        List<Contact> contacts = new ArrayList<>();
        Contact contact = null;
        String elementName = null;
        while (xmlStreamReader.hasNext()) {
            int eventType = xmlStreamReader.next();
            switch (eventType) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = xmlStreamReader.getLocalName();
                    if ("contact".equals(elementName)) {
                        contact = new Contact();
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if (contact != null) {
                        String data = xmlStreamReader.getText();
                        switch (elementName) {
                            case "firstName":
                                contact.setFirstName(data);
                                break;
                            case "lastName":
                                contact.setLastName(data);
                                break;
//                            case "phoneNumber":
//                                contact.setNumber(data);
//                                break;
                        }
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = xmlStreamReader.getLocalName();
                    if ("contact".equals(elementName)) {
                        if (!contacts.contains(contact)) { // проверяем уникальность контакта
                            contacts.add(contact);
                        }
                        contact = null;
                    }
                    break;
            }
        }
        return contacts;
    }

    @Override
    public List<Contact> importFromCSV(String filePath) throws IOException {
        List<Contact> contacts = new ArrayList<>();
        Set<Contact> uniqueContacts = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath + ".csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    Contact contact = new Contact(parts[0], parts[1], new HashSet<>());
                    uniqueContacts.add(contact);
                }
            }
        }
        contacts.addAll(uniqueContacts);
        return contacts;
    }

}
