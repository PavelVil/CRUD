package contacts.model;

/**
 * Created by Pavel on 27.06.2017.
 */
public class Profession {

    private int id;
    private String profession;

    public Profession() {
    }

    public Profession(String profession) {
        this.profession = profession;
    }

    public Profession(int id, String profession) {
        this.id = id;
        this.profession = profession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
