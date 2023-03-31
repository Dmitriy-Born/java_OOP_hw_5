import javax.management.modelmbean.XMLParseException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private List<Contact> contacts;
    private String file = "contacts";
    private Export exportFile;
    private Import importFile;

    public PhoneBook (String filePath){
        this.contacts = new ArrayList<>();
        this.exportFile = new ExportToFile(filePath);
        this.importFile = new ImportToFile(filePath);
    }

    public void addContact(Contact contact){
        contacts.add(contact);
    }

    public List<Contact> searchContact(String searchC){
        List<Contact> searchCo = new ArrayList<>();
        for (Contact contact : contacts){
            if (contact.getFirstName().contains(searchC)
            || contact.getLastName().contains(searchC)
            || contact.getNumber().contains(searchC)){
                searchCo.add(contact);
            }
        }
        if (searchCo.isEmpty()){
            System.out.println("Запрошенный контакт не найден!");
        }
        return searchCo;
    }

    public void allContacts(){
        for (Contact contact : contacts){
            System.out.println(contact.toString());
        }
    }

    public void exportContactsToCsv() throws IOException {
        exportFile.exportToCSV(contacts, this.file);
    }

    public void importContactsFromCsv() throws IOException {
        List<Contact> importedContacts = importFile.importFromCSV(this.file);
        for (Contact contact : importedContacts) {
            if (!contacts.contains(contact)) {
                contacts.add(contact);
            }
        }
    }


    public void importContactsFromXml() throws IOException, XMLStreamException, XMLParseException {
        List<Contact> importedContacts = importFile.importFromXML(this.file);
        for (Contact contact : importedContacts) {
            if (!contacts.contains(contact)) {
                contacts.add(contact);
            }
        }
    }

    public void exportContactsToXml() throws IOException, XMLStreamException {
        exportFile.exportToXML(contacts, this.file);
    }

}
