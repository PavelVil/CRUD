package contacts.model;

/**
 * Created by Pavel on 27.06.2017.
 */
public class Zip {

    private int id;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
