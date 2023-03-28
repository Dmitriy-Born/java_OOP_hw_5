import javax.management.modelmbean.XMLParseException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public class Menu {
    private PhoneBook phoneBook;

    public Menu (String filePath){
        phoneBook = new PhoneBook(filePath);
    }

    public void work() throws IOException, XMLStreamException, XMLParseException {
        boolean quit = false;

        while (!quit) {

            System.out.print("Добро пожаловать в телефонную книгу. Выберите действие: \n" +
                    "1. Посмотреть все контакты.\n" +
                    "2. Добавить контакт.\n" +
                    "3. Удалить контакт.\n" +
                    "4. Поиск контакта.\n" +
                    "5. Сохранить в файл. \n" +
                    "6. Загрузить из файла. \n" +
                    "7. Выход. \n" +
                    ":_> ");
            switch (KeyScanner.getText()) {
                case ("1") -> viewAllContats();
                case ("2") -> addNewContact();
                case ("3") -> System.out.println("Нужно реализовать удаление контакта");
                case ("4") -> searchContact();
                case ("5") -> {
                    System.out.println("Выберите формат сохранения: \n" +
                            "1. Импорт контактов в CSV-файл.\n" +
                            "2. Импорт контактов в XML-файл.\n" +
                            ":_> ");

                    switch (KeyScanner.getText()){
                        case ("1") -> importContactsFromCsv();
                        case ("2") -> importContactsFromXml();
                        default -> System.out.println("Неправильный выбор");
                    }
                }
                case ("6") -> {
                    System.out.println("Выберите формат загрузки файла: \n" +
                            "1. Экспорт контактов из CSV-файла.\n" +
                            "2. Экспорт контактов в XML-файла.\n" +
                            ":_> ");

                    switch (KeyScanner.getText()){
                        case ("1") -> exportContactsToCsv();
                        case ("2") -> exportContactsToXml();
                        default -> System.out.println("Неправильный выбор");
                    }
                }
                case ("7") -> {
                    System.out.println("До скорой встречи!");
                    System.exit(0);
                }
                default -> System.out.println("Выберите задание из списка");
            }
        }
    }

    private void addNewContact(){
        System.out.println("Добавление контакта");
        String firstName = KeyScanner.getText("Введите имя: ");
        String lastName = KeyScanner.getText("Введите фамилию: ");
        String number = KeyScanner.getText("Введите номер телефона: ");
        Contact c = new Contact(firstName, lastName, number);
        phoneBook.addContact(c);
    }

    private void viewAllContats(){
        phoneBook.allContacts();
    }

    private void searchContact(){
        String searchCo = KeyScanner.getText("Введите данные контакта, которого следует найти");
        List<Contact> searchConList = phoneBook.searchContact(searchCo);
        for (Contact contact : searchConList){
            System.out.println(contact.toString());
        }
    }

    private void filePath(){
        String filepath = KeyScanner.getText("Введите путь к файлу, формата csv или xml \"формат указывать не надо\"");
        phoneBook = new PhoneBook(filepath);
    }

    private void importContactsFromCsv() throws IOException {
        phoneBook.importContactsFromCsv();
    }
    private void importContactsFromXml() throws XMLStreamException, IOException, XMLParseException {
        phoneBook.importContactsFromXml();
    }

    private void exportContactsToCsv() throws IOException {
        phoneBook.exportContactsToCsv();
    }

    private void exportContactsToXml() throws XMLStreamException, IOException {
        phoneBook.exportContactsToXml();
    }


}
