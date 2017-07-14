package contacts.dao;

import contacts.model.Contact;
import contacts.model.Profession;
import contacts.model.Zip;
import contacts.utils.DaoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 27.06.2017.
 */
public class ContactsDao implements AbstractDao<Contact> {

    private final static String NAME = "name";
    private final static String SURNAME = "surname";
    private final static String ADD_CONTACT = "insert into my_contacts(first_name,last_name,email,gender,birhday,phone,prof_id,zip_code) VALUES (?,?,?,?,?,?,?,?);";
    private final static String SEARCH_BY_NAME = "SELECT contact_id,last_name,first_name,email,gender,birhday,phone,professions.profession,zip_code.city,zip_code.state from my_contacts inner join professions on my_contacts.prof_id=professions.prof_id inner join zip_code on my_contacts.zip_code=zip_code.zip_code where contacts.my_contacts.first_name LIKE ?;";
    private final static String SEARCH_BY_SURNAME = "SELECT contact_id,last_name,first_name,email,gender,birhday,phone,professions.profession,zip_code.city,zip_code.state from my_contacts inner join professions on my_contacts.prof_id=professions.prof_id inner join zip_code on my_contacts.zip_code=zip_code.zip_code where contacts.my_contacts.last_name LIKE ?;";
    private final static String GET_ALL = "select contact_id,last_name,first_name,email,gender,birhday,phone,professions.profession,zip_code.city,zip_code.state from my_contacts inner join professions on my_contacts.prof_id=professions.prof_id inner join zip_code on my_contacts.zip_code=zip_code.zip_code;";
    private final static String GET_BY_ID = "select contact_id,last_name,first_name,email,gender,birhday,phone,professions.profession,zip_code.city,zip_code.state from my_contacts inner join professions on my_contacts.prof_id=professions.prof_id inner join zip_code on my_contacts.zip_code=zip_code.zip_code where my_contacts.contact_id=?;";
    private final static String DELETE_BY_ID = "delete from contacts.my_contacts WHERE contact_id=?;";
    private final static String EDIT = "update contacts.my_contacts SET first_name=?,last_name=?,email=?,gender=?,birhday=?,phone=?,prof_id=?,zip_code=? where contact_id=?;";
    private final static String GET_ONE_CONTACT_BY_TITLE_NAME="SELECT contact_id,last_name,first_name,email,gender,birhday,phone,professions.profession,zip_code.city,zip_code.state from my_contacts inner join professions on my_contacts.prof_id=professions.prof_id inner join zip_code on my_contacts.zip_code=zip_code.zip_code where contacts.my_contacts.first_name=?";
    @Override
    public List<Contact> getAll() {
        List<Contact> contacts = null;
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(GET_ALL);
        ResultSet rs = statement.executeQuery()){
            contacts = new ArrayList<>();
            while (rs.next()){
                Contact contact = new Contact();
                contacts.add(getContactFromResult(rs,contact));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public void add(Contact contact) {
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(ADD_CONTACT)){
            statement.setString(1,contact.getLastName());
            statement.setString(2,contact.getFirstName());
            statement.setString(3,contact.getEmail());
            statement.setString(4,contact.getGender());
            statement.setDate(5,contact.getDate());
            statement.setString(6,contact.getPhone());
            statement.setInt(7,contact.getProfession().getId());
            statement.setInt(8,contact.getZip().getId());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Contact getById(int id) {
        Connection connection = DaoUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Contact contact = null;
        try {
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1,id);
            rs = statement.executeQuery();
            if (rs.next()){
                contact = new Contact();
                getContactFromResult(rs,contact);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            DaoUtil.close(statement,rs,connection);
        }
        return contact;
    }

    @Override
    public Contact getByTitle(String title) {
      Contact contact = null;
      try (Connection connection = DaoUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(GET_ONE_CONTACT_BY_TITLE_NAME); ResultSet rs = statement.executeQuery()){
          statement.setString(1,title);
          if (rs.next()){
              contact = new Contact();
              getContactFromResult(rs,contact);
          }
      }catch (SQLException ex){
          ex.printStackTrace();
      }
      return contact;
    }

    @Override
    public List<Contact> getByTitle(String title, String name) {
        List<Contact> contacts = null;
        if (title.equals(NAME)){
            contacts = searchingContacts(name,SEARCH_BY_NAME);
        } if (title.equals(SURNAME)){
            contacts = searchingContacts(name,SEARCH_BY_SURNAME);
        }
        return contacts;
    }

    @Override
    public void delete(Contact contact) {
        try(Connection connection = DaoUtil.getConnection();PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)){
            statement.setInt(1,contact.getContactId());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void edit(Contact contact) {
        try(Connection connection = DaoUtil.getConnection();PreparedStatement statement = connection.prepareStatement(EDIT)){
            statement.setString(1,contact.getLastName());
            statement.setString(2,contact.getFirstName());
            statement.setString(3,contact.getEmail());
            statement.setString(4,contact.getGender());
            statement.setDate(5,contact.getDate());
            statement.setString(6,contact.getPhone());
            statement.setInt(7,contact.getProfession().getId());
            statement.setInt(8,contact.getZip().getId());
            statement.setInt(9,contact.getContactId());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private List<Contact> searchingContacts(String name, String sql){
        Connection connection = DaoUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Contact> contacts = null;
        Contact contact = null;
        try{
            contacts = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setString(1,"%"+name+"%");
            rs = statement.executeQuery();
            while (rs.next()){
                contact = new Contact();
                contacts.add(getContactFromResult(rs,contact));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            DaoUtil.close(statement,rs,connection);
        }
        return contacts;
    }

    private Contact getContactFromResult(ResultSet rs, Contact contact) throws SQLException{
            contact.setContactId(rs.getInt("contact_id"));
            contact.setFirstName(rs.getString("first_name"));
            contact.setLastName(rs.getString("last_name"));
            contact.setEmail(rs.getString("email"));
            contact.setGender(rs.getString("gender"));
            contact.setDate(rs.getDate("birhday"));
            contact.setPhone(rs.getString("phone"));
            contact.setProfession(new Profession(rs.getString("profession")));
            contact.setZip(new Zip(rs.getString("city"),rs.getString("state")));
        return contact;
    }
}
