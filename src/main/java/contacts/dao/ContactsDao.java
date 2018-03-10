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


public class ContactsDao extends AbstractDao<Contact> {

    private final static String NAME = "name";
    private final static String SURNAME = "surname";
    private final static String CREATE = "INSERT INTO my_contacts(first_name,last_name,email,gender,birhday,phone,prof_id,zip_code) VALUES (?,?,?,?,?,?,?,?);";
    private final static String SEARCH_BY_NAME = "SELECT contact_id,last_name,first_name,email,gender,birhday,phone,professions.profession,zip_code.city,zip_code.state from my_contacts inner join professions on my_contacts.prof_id=professions.prof_id inner join zip_code on my_contacts.zip_code=zip_code.zip_code where contacts.my_contacts.first_name LIKE ?;";
    private final static String SEARCH_BY_SURNAME = "SELECT contact_id,last_name,first_name,email,gender,birhday,phone,professions.profession,zip_code.city,zip_code.state from my_contacts inner join professions on my_contacts.prof_id=professions.prof_id inner join zip_code on my_contacts.zip_code=zip_code.zip_code where contacts.my_contacts.last_name LIKE ?;";
    private final static String GET_ALL = "SELECT contact_id,last_name,first_name,email,gender,birhday,phone,professions.profession,zip_code.city,zip_code.state FROM my_contacts INNER JOIN professions ON my_contacts.prof_id=professions.prof_id INNER JOIN zip_code ON my_contacts.zip_code=zip_code.zip_code;";
    private final static String GET_BY_ID = "SELECT contact_id,last_name,first_name,email,gender,birhday,phone,professions.profession,zip_code.city,zip_code.state FROM my_contacts INNER JOIN professions ON my_contacts.prof_id=professions.prof_id INNER JOIN zip_code ON my_contacts.zip_code=zip_code.zip_code WHERE my_contacts.contact_id=?;";
    private final static String DELETE = "DELETE FROM contacts.my_contacts WHERE contact_id=?;";
    private final static String EDIT = "UPDATE contacts.my_contacts SET first_name=?,last_name=?,email=?,gender=?,birhday=?,phone=?,prof_id=?,zip_code=? WHERE contact_id=?;";

    @Override
    protected String getSelectedAllQuery() {
        return GET_ALL;
    }

    @Override
    protected String getSelectByIdQuery() {
        return GET_BY_ID;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE;
    }

    @Override
    protected String getCreateQuery() {
        return CREATE;
    }

    @Override
    protected String getEditQuery() {
        return EDIT;
    }

    @Override
    protected Contact getEntity(ResultSet rs) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getInt("contact_id"));
        contact.setFirstName(rs.getString("first_name"));
        contact.setLastName(rs.getString("last_name"));
        contact.setEmail(rs.getString("email"));
        contact.setGender(rs.getString("gender"));
        contact.setDate(rs.getDate("birhday"));
        contact.setPhone(rs.getString("phone"));
        contact.setProfession(new Profession(rs.getString("profession")));
        contact.setZip(new Zip(rs.getString("city"), rs.getString("state")));
        return contact;
    }

    @Override
    protected void fillCreateStatement(PreparedStatement preparedStatement, Contact entity) throws SQLException {
        fillPreparedStatement(preparedStatement, entity);
    }

    @Override
    protected void fillEditStatement(PreparedStatement preparedStatement, Contact entity) throws SQLException {
        fillPreparedStatement(preparedStatement, entity);
        preparedStatement.setInt(9, entity.getId());
    }

    private void fillPreparedStatement(PreparedStatement preparedStatement, Contact entity) throws SQLException {
        preparedStatement.setString(1, entity.getLastName());
        preparedStatement.setString(2, entity.getFirstName());
        preparedStatement.setString(3, entity.getEmail());
        preparedStatement.setString(4, entity.getGender());
        preparedStatement.setDate(5, entity.getDate());
        preparedStatement.setString(6, entity.getPhone());
        preparedStatement.setInt(7, entity.getProfession().getId());
        preparedStatement.setInt(8, entity.getZip().getId());
    }

    @Override
    public List<Contact> getByTitle(String title, String name) {
        List<Contact> contacts = null;
        if (title.equals(NAME)) {
            contacts = searchingContacts(name, SEARCH_BY_NAME);
        }
        if (title.equals(SURNAME)) {
            contacts = searchingContacts(name, SEARCH_BY_SURNAME);
        }
        return contacts;
    }

    private List<Contact> searchingContacts(String name, String sql) {
        Connection connection = DaoUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Contact> contacts = null;
        try {
            contacts = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            rs = statement.executeQuery();
            while (rs.next()) {
                contacts.add(getEntity(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DaoUtil.close(statement, rs, connection);
        }
        return contacts;
    }
}
