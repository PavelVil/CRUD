package contacts.dao;

import contacts.model.Zip;
import contacts.utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZipDao extends AbstractDao<Zip> {

    private final static String ZIP_CODE = "zip_code";
    private final static String CITY = "city";
    private final static String STATE = "state";
    private final static String GET_ALL = "SELECT zip_code, city, state FROM zip_code;";
    private final static String CREATE = "INSERT INTO zip_code(city,state) VALUES (?,?);";
    private final static String GET_BY_ID = "SELECT zip_code, city, state FROM zip_code WHERE zip_code.zip_code=?;";
    private final static String GET_BY_TITLE = "SELECT zip_code, city, state FROM zip_code WHERE zip_code.city=?;";
    private final static String DELETE = "DELETE FROM contacts.zip_code WHERE zip_code=?;";
    private final static String EDIT = "UPDATE contacts.zip_code SET city=?, state=? WHERE contacts.zip_code.zip_code=?;";

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
    protected Zip getEntity(ResultSet rs) throws SQLException {
        return new Zip(rs.getInt("zip_code"), rs.getString("city"), rs.getString("state"));
    }

    @Override
    protected void fillCreateStatement(PreparedStatement preparedStatement, Zip entity) throws SQLException {
        preparedStatement.setString(1, entity.getCity());
        preparedStatement.setString(2, entity.getState());

    }

    @Override
    protected void fillEditStatement(PreparedStatement preparedStatement, Zip entity) throws SQLException {
        preparedStatement.setString(1, entity.getCity());
        preparedStatement.setString(2, entity.getState());
        preparedStatement.setInt(3, entity.getId());
    }

    @Override
    public List<Zip> getByTitle(String title, String name) {
        Connection connection = DaoUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Zip> zips = new ArrayList<>();
        try {
            statement = connection.prepareStatement(GET_BY_TITLE);
            statement.setString(1, title);
            rs = statement.executeQuery();
            while (rs.next()) {
                zips.add(new Zip(rs.getInt(ZIP_CODE), rs.getString(CITY), rs.getString(STATE)));
            }
            return zips;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DaoUtil.close(statement, rs, connection);
        }
        return null;
    }
}
