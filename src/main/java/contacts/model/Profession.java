package contacts.model;


public class Profession extends BaseEntity {

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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
