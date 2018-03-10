package contacts.model;

import java.sql.Date;


public class Contact extends BaseEntity {

    private String lastName;
    private String firstName;
    private String email;
    private String gender;
    private Date date;
    private String phone;
    private Zip zip;
    private Profession profession;

    public Contact() {

    }

    public Contact(String lastName, String firstName, String email, String gender, Date date, String phone, Zip zip, Profession profession) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.gender = gender;
        this.date = date;
        this.phone = phone;
        this.profession = profession;
        this.zip = zip;
    }

    public Contact(String lastName, String firstName, String email, String gender, Date date, String phone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.gender = gender;
        this.date = date;
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Zip getZip() {
        return zip;
    }

    public void setZip(Zip zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", date=" + date +
                ", phone='" + phone + '\'' +
                '}';
    }
}
