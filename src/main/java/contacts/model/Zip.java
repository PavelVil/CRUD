package contacts.model;


public class Zip extends BaseEntity {

    private String city;
    private String state;

    public Zip() {
    }

    public Zip(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public Zip(int id, String city, String state) {
        this.id = id;
        this.city = city;
        this.state = state;
    }

    public Zip(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
