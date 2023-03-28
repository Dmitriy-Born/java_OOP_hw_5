public class Contact {
    private String firstName;
    private String lastName;
    private String number;

    public Contact(String firstName, String lastName, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
    }

    public Contact() {
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if(!(o instanceof Contact)){
            return false;
        }
        Contact a = (Contact) o;
        return firstName.equals(a.firstName) && lastName.equals(a.lastName) && number.equals(a.number);
    }

    @Override
    public String toString(){
        return "Имя: " + firstName + ", Фамилия: " + lastName + ", Номер телеофна: " + number;
    }
}
