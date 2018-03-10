package contacts.dao;

import contacts.model.Profession;
import contacts.utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessionDao extends AbstractDao<Profession> {

    private final static String PROF_ID = "prof_id";
    private final static String PROFESSION = "profession";
    private final static String GET_ALL = "SELECT prof_id, profession FROM professions;";
    private final static String CREATE = "INSERT INTO contacts.professions(profession) VALUES (?);";
    private final static String GET_BY_ID = "SELECT prof_id, profession FROM professions WHERE prof_id=?;";
    private final static String GET_BY_TITLE = "SELECT prof_id, profession FROM professions WHERE profession=?;";
    private final static String DELETE = "DELETE FROM professions WHERE prof_id=?;";
    private final static String EDIT = "UPDATE professions SET profession=? WHERE professions.prof_id=?;";

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
    protected Profession getEntity(ResultSet rs) throws SQLException {
        return new Profession(rs.getInt("prof_id"), rs.getString("profession"));
    }

    @Override
    protected void fillCreateStatement(PreparedStatement preparedStatement, Profession entity) throws SQLException {
        preparedStatement.setString(1, entity.getProfession());
    }

    @Override
    protected void fillEditStatement(PreparedStatement preparedStatement, Profession entity) throws SQLException {
        preparedStatement.setString(1, entity.getProfession());
        preparedStatement.setInt(2, entity.getId());
    }

    @Override
    public List<Profession> getByTitle(String title, String name) {
        Connection connection = DaoUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Profession> professions = new ArrayList<>();

        try {
            statement = connection.prepareStatement(GET_BY_TITLE);
            statement.setString(1, title);
            rs = statement.executeQuery();
            while (rs.next()) {
                professions.add(new Profession(rs.getInt(PROF_ID), rs.getString(PROFESSION)));
            }
            return professions;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DaoUtil.close(statement, rs, connection);
        }
        return null;
    }
}
